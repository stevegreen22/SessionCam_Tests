package com.sessioncam

import org.json4s.DefaultFormats
import org.json4s.ext.JodaTimeSerializers

/**
  * Created by SteveGreen on 28/01/2016.
  */
trait CustomJsonFormats {

  val customDateFormat = new DefaultFormats {
    override def dateFormatter = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
  }

  implicit val formats = customDateFormat ++ JodaTimeSerializers.all
}


