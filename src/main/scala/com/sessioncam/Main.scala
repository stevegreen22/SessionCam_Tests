package com.sessioncam

import java.io.FileNotFoundException
import com.sessioncam.FileParsing.FileParser._

/**
  * Created by SteveGreen on 27/01/2016.
  */
object Main extends App{

  //get the files.
  val okFileExtensions = List("json")
  try {
    val files = getListOfFilesFromDirectory("/Users/SteveGreen/Development/Dev Workspace/SessionCam/data", okFileExtensions)

    //Todo: For each file, convert it to a string, close the file.
    for (file <- files) {
      val str = getFileContentsAsString(file)
      println(str)
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
