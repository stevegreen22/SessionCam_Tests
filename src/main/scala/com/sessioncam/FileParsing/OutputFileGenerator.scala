package com.sessioncam.FileParsing

import java.io._
import java.nio.file.{FileAlreadyExistsException, Paths, Files}

import com.typesafe.scalalogging.LazyLogging

/**
  * Created by SteveGreen on 30/01/2016.
  *
  * Purpose of the class is to create a file in a location (specified or not) and write the contents to it.
  */
object OutputFileGenerator extends LazyLogging{

  /*Todo: determine just how generic this function should be...
  1 - it just takes the string and creates the file in the location we tell it - removed as hardcoded strings etc
  2 - it takes filename/location and contents
  3 - filename, location, contents
  4 - filename, extension, location, contents

  --Todo: case class with default values set? Worth making a builder for this?

   --provide overloaded defaults to cover all cases?
   --Varags with if else structure = removes overloads but messy
   */

  //small helper method to clean up some exception handling
  //todo: consider updating the file name and saving it instead of exception?
  private def doesFileExist(completeFilename: String) : Unit = {
    logger.info("Checking if file already exists")
    if (Files.exists(Paths.get(completeFilename))) {
      throw new FileAlreadyExistsException("Exception: File already exists.")
    }
  }

  //Todo: really not happy with this repeated code, eugh.

  //Version 2
  def createOutputFile(fileNameAndSaveLocation: String, fileContents: String): Unit = {

    try{
      doesFileExist(fileNameAndSaveLocation)
      logger.info(s"Writing contents to file to: ${fileNameAndSaveLocation}")
      val writer = new PrintWriter(new File(fileNameAndSaveLocation))
      writer.write(fileContents)
      writer.close()
      logger.info(s"The file ${fileNameAndSaveLocation} has been successfully created.")
    } catch {
      case fnf : FileNotFoundException => logger.error("Exception when writing file : " + fnf.getMessage)
      case fae : FileAlreadyExistsException => logger.error(s"Exception: The File ${fileNameAndSaveLocation} already exists.")
      case _ : Exception => logger.error("Unexpected Exception when writing file: ")
    }
  }

  //Version 3
  def createOutputFile(saveFileLocation: String, fileName: String, fileContents: String): Unit = {

    val completeFileName = saveFileLocation + "/" + fileName
    try {
      doesFileExist(completeFileName)
      logger.info(s"Writing contents to file (${fileName}) to: ${saveFileLocation}")
      val writer = new PrintWriter(new File(completeFileName))
      writer.write(fileContents)
      writer.close()
      logger.info(s"The file ${completeFileName} has been successfully created.")
    } catch {
      case fnf : FileNotFoundException => logger.error("Exception when writing file : " + fnf.getMessage)
      case fae : FileAlreadyExistsException => logger.error(s"Exception: The File ${completeFileName} already exists.")
      case _ : Exception => logger.error("Unexpected Exception when writing file: ")
    }
  }

  //Version 4
  def createOutputFile(saveFileLocation: String, fileName: String, fileExtension: String, fileContents: String): Unit = {

    val completeFileName = saveFileLocation + "/" + fileName + fileExtension
    try {
       doesFileExist(completeFileName)
      logger.info(s"Writing contents to file (${fileName}) with extension ${fileExtension} to: ${saveFileLocation}")
      val writer = new PrintWriter(new File(completeFileName))
      writer.write(fileContents)
      writer.close()
      logger.info(s"The file ${completeFileName} has been successfully created.")
    } catch {
      case fnf : FileNotFoundException => logger.error("Exception when writing file : " + fnf.getMessage)
      case fae : FileAlreadyExistsException => logger.error(s"Exception: The File ${completeFileName} already exists.")
      case _ : Exception => logger.error("Unexpected Exception when writing file: ")
    }

  }
}
