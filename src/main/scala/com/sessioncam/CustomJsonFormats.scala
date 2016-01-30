package com.sessioncam

import org.json4s.DefaultFormats
import org.json4s.ext.JodaTimeSerializers

/**
  * Created by SteveGreen on 28/01/2016.
  *
  * Trait allows for redefinition of the formats used for converting json objects, required for jodatime
  */
trait CustomJsonFormats {

  val customDateFormat = new DefaultFormats {
    override def dateFormatter = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
  }

  implicit val formats = customDateFormat ++ JodaTimeSerializers.all
}


