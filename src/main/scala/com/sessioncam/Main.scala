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
  try {
    val files = getListOfFilesFromDirectory("/Users/SteveGreen/Development/Dev Workspace/SessionCam/data", okFileExtensions)
    //Todo: For each file, convert it to a string, close the file.
    for (file <- files) {
      //Obtain the string of each file.
      val str = getFileContentsAsString(file)
      println(str)


      val timezones = parse(str) \ "data"
      val tzList = timezones.extract[List[Timezone]]

      println(timezones)


//PROGRESS!!!!!
      tzList.foreach { timezone: Timezone => println(
        timezone.name + " " + timezone.date) }

      tzList.foreach(println)

      //for each JSON block, create a new POJO
      //Add each to a list

      //Combine the lists together using a key.




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
