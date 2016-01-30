package com.sessioncam.FileParsing

import java.io._

/**
  * Created by SteveGreen on 30/01/2016.
  *
  * Purpose of the class is to create a file in a location (specified or not) and write the contents to it.
  */
object OutputFileGenerator {

  /*Todo: determine just how generic this function should be...
  1 - it just takes the string and creates the file in the location we tell it
  2 - it takes filename/location and contents
  3 - filename, location, contents
  4 - filename, extension, location, contents

   --provide overloaded defaults to cover all cases?
   --Varags with if else structure = removes overloads but messy
   */

  //Version 1
  def createOutputFile(fileContents: String): Unit = {

    try{
      val writer = new PrintWriter(new File("/Users/SteveGreen/Development/Dev Workspace/SessionCam/dataOutput/test.json"))
      writer.write(fileContents)
      writer.close()
    } catch {
      case e : FileNotFoundException => println("Exception when writing file : " + e.getMessage)
      case _ : Exception => println("Unexpected Exception when writing file: ")
     }

  }

  //Version 2
  def createOutputFile(fileNameAndSaveLocation: String, fileContents: String): Unit = {

    try{
      val writer = new PrintWriter(new File(fileNameAndSaveLocation))
      writer.write(fileContents)
      writer.close()
    } catch {
      case e : FileNotFoundException => println("Exception when writing file : " + e.getMessage)
      case _ : Exception => println("Unexpected Exception when writing file: ")
    }
  }

  //Version 3
  def createOutputFile(fileName: String, saveFileLocation: String, fileContents: String): Unit = {

    try {
      val writer = new PrintWriter(new File(saveFileLocation + fileName))
      writer.write(fileContents)
      writer.close()
    } catch {
      case e : FileNotFoundException => println("Exception when writing file : " + e.getMessage)
      case _ : Exception => println("Unexpected Exception when writing file: ")
    }
  }

  //Version 4
  def createOutputFile(fileLocation: String, fileName: String, fileExtension: String, fileContents: String): Unit = {

    try {
      val writer = new PrintWriter(new File(fileLocation + fileName + fileExtension))
      writer.write(fileContents)
      writer.close()
    } catch {
      case e : FileNotFoundException => println("Exception when writing file : " + e.getMessage)
      case _ : Exception => println("Unexpected Exception when writing file: ")
    }

  }
}
