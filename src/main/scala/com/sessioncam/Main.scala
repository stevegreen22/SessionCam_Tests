package com.sessioncam

import com.sessioncam.FileInput.FileParser
import FileParser._

/**
  * Created by SteveGreen on 27/01/2016.
  */
object Main extends App{

  //get the files.
  val okFileExtensions = List("json")
  val files = getListOfFilesFromDirectory("/Users/SteveGreen/Development/Dev Workspace/SessionCam/data", okFileExtensions)
  files.foreach(println)


  //iterate over list of files building
//  files.foreach{
//    //take the json and convert it into the case class.
//
//  }

      //for each file, process json

}
