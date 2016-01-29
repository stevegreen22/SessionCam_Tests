package com.sessioncam.jsonparsing.deserialisation

import java.io.File

import com.sessioncam.CustomJsonFormats
import com.sessioncam.model.TimezoneDetails
import com.typesafe.scalalogging.LazyLogging
import org.json4s.native.JsonMethods._

/**
  * Created by SteveGreen on 29/01/2016.
  */
object JsonDeserialiser extends LazyLogging with CustomJsonFormats{

  /**
    *
    * @param file
    * @return
    */
  def getJsonContentsAsString(file : java.io.File): String = {
    logger.info(s"Attempting to get contents of file: ${file.getName} from directory: ${file.getCanonicalPath}")
    try {
      val fileDetails = scala.io.Source.fromFile(file)
      val str = fileDetails.mkString
      fileDetails.close
      return str
    }
  }

  /**
    *
    * @param files
    * @return
    */
  def createTimezoneListFromJsonFile(files : List[File]): List[TimezoneDetails] = {
    logger.info("Creating list of timezones from JSON")
    var listOfTimezones = List[TimezoneDetails]()
    for (file <- files) {
      try {
        val str = getJsonContentsAsString(file)
        val timezones = parse(str) \ "data"
        val tzList = timezones.extract[List[TimezoneDetails]]
        listOfTimezones = listOfTimezones ::: tzList
      } catch {
        case e : Exception =>
          logger.error("There was a problem with creating the timezone list")
          print("An exception occurred: " +e.getMessage)
        case _ =>
          logger.error("There was a problem with creating the timezone list")
          print("Some exception occurred...")
      }
    }
    return listOfTimezones
  }



}
