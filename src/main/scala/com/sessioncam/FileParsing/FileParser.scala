package com.sessioncam.FileParsing

import com.typesafe.scalalogging.LazyLogging
import java.io.{FileNotFoundException, File}

/**
  * Created by SteveGreen on 27/01/2016.
  */
object FileParser extends LazyLogging{

  /**
    * Function to take in a directory name as string and return a list
    * of files within that directory also checking that their extension
    * matches those given
    *
    * @param directory
    * @param extensions
    * @return A List containing the files
    */
  @throws(classOf[FileNotFoundException])
  def getListOfFilesFromDirectory(directory: String, extensions: List[String]):List[File] = {
    logger.info("Attempting to read files from specified directory")
    val dir = new File(directory)
    if (dir.exists && dir.isDirectory) {
      dir.listFiles.filter(_.isFile).toList.filter { file =>
        extensions.exists(file.getName.endsWith)
      }
    } else {
      logger.error("No files were found within the specified directory")
      throw new FileNotFoundException("No files were found within the directory")
    }
  }




}

