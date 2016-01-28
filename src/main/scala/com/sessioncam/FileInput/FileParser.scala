package com.sessioncam.FileInput

import java.io.File

/**
  * Created by SteveGreen on 27/01/2016.
  */
object FileParser {

  /**
    * Function to take in a directory name as string and return a list
    * of files within that directory also checking that their extension
    * matches those given
    * @param directory
    * @param extensions
    * @return A List containing the files
    */
  def getListOfFilesFromDirectory(directory: String, extensions: List[String]):List[File] = {
    val dir = new File(directory)
    if (dir.exists && dir.isDirectory) {
      dir.listFiles.filter(_.isFile).toList.filter { file =>
        extensions.exists(file.getName.endsWith(_))
      }
    } else {
      List[File]()
    }
  }

}

