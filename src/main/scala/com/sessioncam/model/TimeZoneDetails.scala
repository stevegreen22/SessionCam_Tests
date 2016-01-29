package com.sessioncam.model

import org.joda.time.DateTime

/**
  * Created by SteveGreen on 29/01/2016.
  */
case class TimezoneDetails(var name: String,
                           timezone: String,
                           date: String,
                           time: String,
                           var jodaDate: DateTime)
