package com.sessioncam.FileParsing

import java.io._
import java.nio.file.{FileAlreadyExistsException, Paths, Files}

import com.typesafe.scalalogging.LazyLogging

/**
  * Created by SteveGreen on 30/01/2016.
  *
  * Purpose of the class is to create a file in a location (specified or not) and write the contents to it.
  */
case class OutputFileGenerator(saveFileLocation: String = "/Users/steveGreen/Development/Dev Workspace/SessionCam/dataOutput",
                          var fileName: String = "JsonOutputFile",
                          fileExtension: String = ".json",
                          fileContents: String) extends LazyLogging{

  object OutputFileGenerator {

    private var completeFilename = saveFileLocation + "/" + fileName + fileExtension


    //Version 4
    def createOutputFile(): Unit = {
      try {
        if (doesFileExist(completeFilename)) {
          createNewFilename
        }
        logger.info(s"Writing contents to file (${fileName}) with extension ${fileExtension} to: ${saveFileLocation}")
        val writer = new PrintWriter(new File(completeFilename))
        writer.write(fileContents)
        writer.close()
        logger.info(s"The file ${completeFilename} has been successfully created.")
      } catch {
        case fnf: FileNotFoundException => logger.error("Exception when writing file : " + fnf.getMessage)
        case fae: FileAlreadyExistsException => logger.error(s"Exception: The File ${completeFilename} already exists.")
        case _: Exception => logger.error("Unexpected Exception when writing file: ")
      }
    }

    private def doesFileExist(completeFilename: String) : Boolean = {
      logger.info("Checking if file already exists")
      Files.exists(Paths.get(completeFilename))
    }

    private def createNewFilename: Unit = {

      val RegexPattern = "(.+(?=\\/))(\\/)(.+(?=\\.))(.*)".r
      val RegexPattern(dir, delimiter, file, ext) = "direct/ory/filename(1).ext"
      println(dir + " - "+ delimiter + " - " + file + " - " + ext)

      val RegexUpdated = "([\\d]{1,2})".r

      file match {
        case RegexUpdated(_*) => println("dsdfsdfdsdsdsf")
        case _ => println(s"filename $file doesn't have an updated version")}

      //setter method for completeFilename?
      //completeFilename = saveFileLocation + "/" + fileName + fileExtension
    }
    //if it exists, we want to append the (1) standard to it.
    //first need to check if the apended exists
    //need to get the substring within () at the end, assuming that there isn't a () in the name already
    //if they are (*) then we need to parse that int
    //increment the int
    //rename it
    //assuming this is the first rename
    //add (1) to the end of the filename




  }
}
