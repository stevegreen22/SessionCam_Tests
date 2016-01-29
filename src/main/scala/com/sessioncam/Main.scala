package com.sessioncam

import com.sessioncam.FileParsing.FileParser._
import com.sessioncam.jsonparsing.deserialisation.JsonDeserialiser.createTimezoneListFromJsonFile
import com.sessioncam.model.TimezoneDetails

/**
  * Created by SteveGreen on 27/01/2016.
  */
object Main extends App {

  var listOfTimezones = List[TimezoneDetails]();
  try{
    val files = getListOfFilesFromDirectory("/Users/SteveGreen/Development/Dev Workspace/SessionCam/data", List("json"))
    //val files = getListOfFilesFromDirectory("/home/steveg/DevResources/OtherProjects/SessionCam/data", okFileExtensions)
    listOfTimezones = createTimezoneListFromJsonFile(files)
  } catch {
    case e : Exception => println("Exception!!!")
  }

  listOfTimezones.foreach {
    timezone: TimezoneDetails => println(timezone)
  }

  //todo: playing but re evaluate the utility of this.
  def filterByGMT(timezone: String) = timezone match {
    case "gmt"  => true
    case _ => false
  }

  def createTimezoneFilteredListJAVA(tzList: List[TimezoneDetails]): List[TimezoneDetails] = tzList match {
    case Nil => tzList
    case x :: tzList =>
      if (x.timezone.equalsIgnoreCase("utc")) {
        x :: createTimezoneFilteredList(tzList)
      } else {
        createTimezoneFilteredList(tzList)
      }
  }

  def createTimezoneFilteredList(tzList: List[TimezoneDetails]): List[TimezoneDetails] = tzList match {
    case Nil => tzList
    case x :: tzList => if (x.timezone.equalsIgnoreCase("utc")) x :: createTimezoneFilteredList(tzList) else createTimezoneFilteredList(tzList)
  }


  var utcTimezoneDetails = createTimezoneFilteredList(listOfTimezones)
  utcTimezoneDetails.foreach(println)


  //also filter as an option.
  val x = for (timezone <- listOfTimezones) yield filterByGMT("gmt")
  println(x)

  listOfTimezones.foreach{
    timezone => filterByGMT("gmt")
  }

  //will aggregate the files on UTC / CET - the 'timezone' property.

  //this has grouped the timezoneObjects by timezone value.
  println(listOfTimezones.groupBy(_.timezone).mapValues(_.map(_.copy())))



}





//Done: Update a list each time a json object from either of the files is read in.
//Todo: Use this complete list to create an aggregated collection
//Todo: Pass the items of the list through the time converter to adjust the time
//Todo: This updated list can now be parsed into an output JSON file

