package com.sessioncam

import com.sessioncam.JsonParsing.conversion.DateConvertor
import com.typesafe.scalalogging.LazyLogging
import org.joda.time.DateTime
import org.json4s._
import org.json4s.native.JsonMethods._
/**
 * Created by steveg on 27/01/16.
  *
  * Test harness.
 */
object JsonExample extends LazyLogging with CustomJsonFormats{

  case class Timezone(var name: String,
                      timezone: String,
                      date: String,
                      time: String,
                      var jodaDate: DateTime)

  def main(args: Array[String]): Unit = {

    val json = """{
                   "name" : "United Kingdom",
                   "timezone" : "UTC",
                   "date" : "27/01/2016",
                   "time" : "14:35",
                   "jodaDate" : "2016-01-27T14:33:37.533Z"
                 }"""

    val timeline = parse(json).extract[Timezone]
    println("Original Value: " + timeline)

    timeline.jodaDate = timeline.jodaDate.plusDays(4)
    println("Updated Value: " + timeline)

    val s = new DateConvertor(timeline.jodaDate, "utc", "cet")
    val convertedDate = s.convert
    print("Converted Date:" + convertedDate)


    logger.info("Info logging")
    logger.warn("Warning")
  }
}
