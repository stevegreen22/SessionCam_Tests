package com.sessioncam.JsonParsing.Deserialisation

import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods._

/**
  * Created by SteveGreen on 28/01/2016.
  *
  * Basic Test Harness to prove functionality - no DateTime Deserialising added as yet
  */
object BasicJsonDeserialising {

  case class Timezone(name: String,
                      timezone: String,
                      date: String,
                      time: String,
                      stringDate: String)

  def main(args: Array[String]): Unit = {

    implicit val formats = DefaultFormats

    val json =
      """{
                    "name" : "United Kingdom",
                    "timezone" : "UTC",
                    "date" : "27/01/2016",
                    "time" : "14:35",
                    "stringDate"    : "20150226"
                  }"""

    println(parse(json).extract[Timezone])

  }
}
