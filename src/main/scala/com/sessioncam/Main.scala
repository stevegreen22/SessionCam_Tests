package com.sessioncam

import java.io.FileNotFoundException

import com.sessioncam.FileParsing.InputFileParser._
import com.sessioncam.FileParsing.OutputFileGenerator
import com.sessioncam.customfilters.CustomJsonFilters.createTimezoneFilteredList
import com.sessioncam.jsonparsing.conversion.DateConvertor.convertTimezone
import com.sessioncam.jsonparsing.deserialisation.JsonDeserialiser.createTimezoneListFromJsonFile
import com.sessioncam.jsonparsing.serialisation.JsonSerialiser._
import com.sessioncam.model.TimezoneDetails
import com.typesafe.scalalogging.LazyLogging

/**
  * Created by SteveGreen on 27/01/2016.
  */
object Main extends LazyLogging {

  private val filterTimezone = "cet" //todo: pull this in from args? how many args are we going to have?!
  private var listOfTimezones = List[TimezoneDetails]()

  private val fromTimezone = "cet"
  private val toTimezone = "utc"
  private val defaultInputLocation = "/Users/steveGreen/Development/Dev Workspace/SessionCam/dataInput"

  def main(args: Array[String]) {
    try{
      val files = getListOfFilesFromDirectory(defaultInputLocation, List("json"))
      //val files = getListOfFilesFromDirectory("/home/steveg/DevResources/OtherProjects/SessionCam/data", okFileExtensions)
      if (files.nonEmpty) {
        listOfTimezones = createTimezoneListFromJsonFile(files)

        logger.info(s"Attempting to filter by $filterTimezone")
        val cetTimezoneDetails = createTimezoneFilteredList(listOfTimezones, "cet")

        logger.info(s"Converting the timezone from $fromTimezone to $toTimezone")
          for (timezone <- cetTimezoneDetails) {
            timezone.jodaDate = convertTimezone(timezone.jodaDate, fromTimezone, toTimezone)
          }

        cetTimezoneDetails.foreach(println)
        val json = createJsonFromTimezoneList(cetTimezoneDetails)
        val outputFileGenerator = new OutputFileGenerator(fileContents = json)
        outputFileGenerator.OutputFileGenerator.createOutputFile() //todo: <- can this be cleaned up?

      } else {
        //handle empty filelist without an exception?
      }
    } catch {
      case iae : IllegalArgumentException => logger.error("Exception: " + iae.getMessage)
      case npe : NullPointerException => logger.error("Null Pointer Exception: " + npe.getMessage)
      case fnf : FileNotFoundException => logger.error("Exception when searching for files: " +fnf.getMessage)
      case _ : Exception => logger.error("Unknown Exception")
    }
  }


}
//this has grouped the timezoneObjects by timezone value.
//println(listOfTimezones.groupBy(_.timezone).mapValues(_.map(_.copy())))


//Done: Update a list each time a json object from either of the files is read in.
//Done: Use this complete list to create an aggregated collection
//Done: Pass the items of the list through the time converter to adjust the time
//Done: This updated list can now be parsed into an output JSON file
//Todo: Update main method so jar can be run from terminal
//Todo: save a list of the timezones found in the json, run them all through the converter changing as neccesary

