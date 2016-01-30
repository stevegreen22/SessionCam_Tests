package com.sessioncam.FileParsing

import java.io._

import com.typesafe.scalalogging.LazyLogging

/**
  * Created by SteveGreen on 30/01/2016.
  *
  * Purpose of the class is to create a file in a location (specified or not) and write the contents to it.
  */
object OutputFileGenerator extends LazyLogging{

  /*Todo: determine just how generic this function should be...
  1 - it just takes the string and creates the file in the location we tell it
  2 - it takes filename/location and contents
  3 - filename, location, contents
  4 - filename, extension, location, contents

   --provide overloaded defaults to cover all cases?
   --Varags with if else structure = removes overloads but messy
   */

  //Todo: really not happy with this repeated code, eugh.

  //Version 1
  def createOutputFile(fileContents: String): Unit = {

    try{
      logger.info("Writing contents to file")
      val writer = new PrintWriter(new File("/Users/SteveGreen/Development/Dev Workspace/SessionCam/dataOutput/testVersion1.json"))
      writer.write(fileContents)
      writer.close()
      logger.info(s"File ${"/Users/SteveGreen/Development/Dev Workspace/SessionCam/dataOutput/testVersion1.json"} has been successfully created.")
    } catch {
      case e : FileNotFoundException => println("Exception when writing file : " + e.getMessage)
      case _ : Exception => println("Unexpected Exception when writing file: ")
     }

  }

  //Version 2
  def createOutputFile(fileNameAndSaveLocation: String, fileContents: String): Unit = {

    try{
      logger.info(s"Writing contents to file to: ${fileNameAndSaveLocation}")
      val writer = new PrintWriter(new File(fileNameAndSaveLocation))
      writer.write(fileContents)
      writer.close()
      logger.info(s"The file ${fileNameAndSaveLocation} has been successfully created.")
    } catch {
      case e : FileNotFoundException => println("Exception when writing file : " + e.getMessage)
      case _ : Exception => println("Unexpected Exception when writing file: ")
    }
  }

  //Version 3
  def createOutputFile(saveFileLocation: String, fileName: String, fileContents: String): Unit = {

    try {
      logger.info(s"Writing contents to file (${fileName}) to: ${saveFileLocation}")
      val writer = new PrintWriter(new File(saveFileLocation + fileName))
      writer.write(fileContents)
      writer.close()
      logger.info(s"The file ${saveFileLocation.concat(fileName)} has been successfully created.")
    } catch {
      case e : FileNotFoundException => println("Exception when writing file : " + e.getMessage)
      case _ : Exception => println("Unexpected Exception when writing file: ")
    }
  }

  //Version 4
  def createOutputFile(saveFileLocation: String, fileName: String, fileExtension: String, fileContents: String): Unit = {

    try {
      logger.info(s"Writing contents to file (${fileName}) with extension ${fileExtension} to: ${saveFileLocation}")
      val writer = new PrintWriter(new File(saveFileLocation + fileName + fileExtension))
      writer.write(fileContents)
      writer.close()
      logger.info(s"The file ${saveFileLocation+fileName+fileExtension} has been successfully created.")
    } catch {
      case e : FileNotFoundException => println("Exception when writing file : " + e.getMessage)
      case _ : Exception => println("Unexpected Exception when writing file: ")
    }

  }
}
