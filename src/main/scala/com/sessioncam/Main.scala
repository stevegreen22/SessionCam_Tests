package com.sessioncam

import java.io.FileNotFoundException

import com.sessioncam.FileParsing.FileParser._
import com.sessioncam.JsonExample.Timezone
import org.json4s.native.JsonMethods._

/**
  * Created by SteveGreen on 27/01/2016.
  */
object Main extends App with CustomJsonFormats{

  //get the files.
  val okFileExtensions = List("json")
  val listOfTimezones = List[Timezone]()
  try {
//    val files = getListOfFilesFromDirectory("/Users/SteveGreen/Development/Dev Workspace/SessionCam/data", okFileExtensions)
    val files = getListOfFilesFromDirectory("/home/steveg/DevResources/OtherProjects/SessionCam/data", okFileExtensions)
    //Todo: For each file, convert it to a string, close the file.
    for (file <- files) {
      //Obtain the string of each file.
      val str = getFileContentsAsString(file)
      println(str)

      val timezones = parse(str) \ "data"
      println(timezones)

      val tzList = timezones.extract[List[Timezone]]
      println(tzList)

      tzList.foreach { timezone: Timezone => println(
        timezone.name + " " + timezone.date) }

      //printing the entire list of objects.
      tzList.foreach(println)

      val x = listOfTimezones ::: tzList
      println(x)




      //Todo: Update a list each time a json object from either of the files is read in.

      //Todo: Use this complete list to create an aggregated collection

      //Todo: Pass the items of the list through the time converter to adjust the time

      //Todo: This updated list can now be parsed into an output JSON file


    }
  } catch {
    case e: FileNotFoundException => println("No files were found - Exiting: " + e);
  }


  //iterate over list of files building
//  files.foreach{
//    //take the json and convert it into the case class.
//
//  }

      //for each file, process json

}
