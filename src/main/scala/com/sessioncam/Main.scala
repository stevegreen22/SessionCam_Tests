package com.sessioncam

import com.sessioncam.FileParsing.InputFileParser._
import com.sessioncam.FileParsing.OutputFileGenerator.createOutputFile
import com.sessioncam.jsonparsing.conversion.DateConvertor
import com.sessioncam.jsonparsing.deserialisation.JsonDeserialiser.createTimezoneListFromJsonFile
import com.sessioncam.model.TimezoneDetails

/**
  * Created by SteveGreen on 27/01/2016.
  */
object Main extends App with CustomJsonFormats{

  var listOfTimezones = List[TimezoneDetails]();
  try{
    val files = getListOfFilesFromDirectory("/Users/SteveGreen/Development/Dev Workspace/SessionCam/dataInput", List("json"))
    //val files = getListOfFilesFromDirectory("/home/steveg/DevResources/OtherProjects/SessionCam/data", okFileExtensions)
    listOfTimezones = createTimezoneListFromJsonFile(files)
  } catch {
    case e : Exception => println("Exception!!!")
  }

  listOfTimezones.foreach {
    timezone: TimezoneDetails => println(timezone)
  }


  def createTimezoneFilteredList(tzList: List[TimezoneDetails], tx: String): List[TimezoneDetails] = tzList match {
    case Nil => tzList
    case x :: tzList => if (x.timezone.equalsIgnoreCase(tx)) x :: createTimezoneFilteredList(tzList, tx) else createTimezoneFilteredList(tzList, tx)
  }


  println("filter on utc")
  var utcTimezoneDetails = createTimezoneFilteredList(listOfTimezones, "utc")
  utcTimezoneDetails.foreach(println)



  println("filter on cet")
  var cetTimezoneDetails = createTimezoneFilteredList(listOfTimezones, "cet")
  cetTimezoneDetails.foreach(println)

  for (timezone <- cetTimezoneDetails) {
    timezone.jodaDate = DateConvertor.convertTimezone(timezone.jodaDate, "cet", "utc")
  }
  println("updated dates")
  cetTimezoneDetails.foreach(println)




  //this has grouped the timezoneObjects by timezone value.
  //println(listOfTimezones.groupBy(_.timezone).mapValues(_.map(_.copy())))


  import org.json4s.native.Serialization.write
  val json = write(cetTimezoneDetails)

//  1 - it just takes the string and creates the file in the location we tell it
//  2 - it takes filename/location and contents
//  3 - filename, location, contents
//  4 - filename, extension, location, contents

  createOutputFile(json)
  createOutputFile("/Users/SteveGreen/Development/Dev Workspace/SessionCam/dataOutput/testVersion2.json", json)
  createOutputFile("/Users/SteveGreen/Development/Dev Workspace/SessionCam/dataOutput", "testVersion3.json", json)
  createOutputFile("/Users/SteveGreen/Development/Dev Workspace/SessionCam/dataOutput", "testVersion4", ".json", json)




}





//Done: Update a list each time a json object from either of the files is read in.
//Done: Use this complete list to create an aggregated collection
//Done: Pass the items of the list through the time converter to adjust the time
//Done: This updated list can now be parsed into an output JSON file
//Todo: Update main method so jar can be run from terminal

