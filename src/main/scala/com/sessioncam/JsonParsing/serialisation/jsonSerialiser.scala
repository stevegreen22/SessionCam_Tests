package com.sessioncam.jsonparsing.serialisation

import com.sessioncam.AllMyTraits
import com.sessioncam.model.TimezoneDetails
import org.json4s.native.Serialization.write

/**
  * Created by SteveGreen on 30/01/2016.
  */

//this is getting daft, class for a one liner...for now...on the other hand - shows how easy it is to do things in scala
object JsonSerialiser extends AllMyTraits{ //<- this is interesting...

  def createJsonFromTimezoneList(timezones: List[TimezoneDetails]) : String = {
    logger.info("Creating JSON from the data")
    write(timezones)
  }

}
