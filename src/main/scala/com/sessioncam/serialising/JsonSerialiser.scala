package com.sessioncam.serialising

import com.sessioncam.CustomJsonFormats
import com.sessioncam.model.TimezoneDetails
import com.typesafe.scalalogging.LazyLogging
import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization.{write => swrite}
import org.json4s.{NoTypeHints, native}
/**
  * Created by SteveGreen on 29/01/2016.
  */
object JsonSerialiser extends CustomJsonFormats with LazyLogging{

  def main(args: Array[String]): Unit = {

    implicit val formats = native.Serialization.formats(NoTypeHints)

    val json = """{
                   "name" : "United Kingdom",
                   "timezone" : "UTC",
                   "date" : "27/01/2016",
                   "time" : "14:35",
                   "jodaDate" : "2016-01-27T14:33:37.533Z"
                 }"""

    val timeline = parse(json).extract[TimezoneDetails]

    println("Original Value: " + timeline)




  }

}