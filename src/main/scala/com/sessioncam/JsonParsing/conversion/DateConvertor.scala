package com.sessioncam.jsonparsing.conversion

import org.joda.time.DateTime
import com.typesafe.scalalogging.LazyLogging

/**
  * Created by SteveGreen on 28/01/2016.
  *
  * Class will be used to convert DateTime objects.  Typical usage will
  * be converting from UTC to ECT and vice versa.
  *
  * Note: Currently only UTC <=> ECT (+7) is supported.
  */
class DateConvertor(date: DateTime, from: String, to: String) extends LazyLogging{

  /**
    * Convert method will attempt to convert from UTC to ECT and vice versa depending
    * on what the arguments of the class are.
    * @return new modified DateTime object
    */
  def convert: DateTime = {
    logger.info(s"Attempting to convert date ${date.toString} from ${from.toUpperCase} to ${to.toUpperCase}")
    if (from == "" || to == "") {
      throw new IllegalArgumentException("Date Conversion Failed: From and To must be set")
    } else if (date == null) {
      throw new IllegalArgumentException("Date Conversion Failed: Date must not be null")
    } else {
      //Todo: generify this so that we can utilise to and from to freely convert between dates.
      //Note: For now, we just need to check one of them and convert to the other.
      if (to.equalsIgnoreCase("utc")) {
        return new DateTime(date.minusHours(7))
      } else {
        return new DateTime(date.plusHours(7))
      }
    }
  }

}
