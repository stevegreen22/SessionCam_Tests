package com.sessioncam

import com.sessioncam.FileInput.FileParser
import FileParser._
/**
  * Created by SteveGreen on 27/01/2016.
  */
object FileParseTest extends App{
      val okFileExtensions = List("json")
      val files = getListOfFilesFromDirectory("/Users/SteveGreen/Development/Dev Workspace/SessionCam/data", okFileExtensions)
      files.foreach(println)

  }
