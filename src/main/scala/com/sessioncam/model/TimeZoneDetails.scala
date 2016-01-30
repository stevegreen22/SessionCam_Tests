package com.sessioncam.model

import org.joda.time.DateTime

/**
  * Created by SteveGreen on 29/01/2016.
  *
  * A simple model to represent the Timezone details
  */
case class TimezoneDetails(name: String,
                           timezone: String,
                           date: String,
                           time: String,
                           var jodaDate: DateTime)
