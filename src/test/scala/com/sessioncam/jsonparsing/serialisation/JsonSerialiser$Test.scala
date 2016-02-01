package com.sessioncam.customfilters

import com.sessioncam.jsonparsing.serialisation.JsonSerialiser
import com.sessioncam.model.TimezoneDetails
import org.joda.time.DateTime
import org.json4s._
import org.json4s.native.JsonMethods._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by SteveGreen on 31/01/2016.
  */
@RunWith(classOf[JUnitRunner])
class JsonSerialiser$Test extends FlatSpec with Matchers {


  behavior of "Json serialiser"

  it should "create json from a list of TimezoneDetails objects" in {

    val ORIGINAL_LIST = List(
      new TimezoneDetails("some-string", "some-timezone", new DateTime("2015-05-27T14:33:37.533")),
      new TimezoneDetails("some-string", "some-timezone", new DateTime("2015-11-23T14:33:37.533")),
      new TimezoneDetails("some-other-string", "some-other-timezone", new DateTime("2015-05-27T14:33:37.533")),
      new TimezoneDetails("some-other-string", "some-other-timezone", new DateTime("2015-11-23T14:33:37.533"))
    )

    val EXPECTED_JSON = parse(
      """[
            {
              "name":"some-string",
              "timezone":"some-timezone",
              "jodaDate":"2015-05-27T14:33:37.533"
            },
            {
              "name":"some-string",
              "timezone":"some-timezone",
              "jodaDate":"2015-11-23T14:33:37.533"
            },
            {
              "name":"some-other-string",
              "timezone":"some-other-timezone",
              "jodaDate":"2015-05-27T14:33:37.533"
            },
            {
              "name":"some-other-string",
              "timezone":"some-other-timezone",
              "jodaDate":"2015-11-23T14:33:37.533"
            }
          ]
      """)
    val resultJson = parse(JsonSerialiser.createJsonFromTimezoneList(ORIGINAL_LIST))

    resultJson shouldBe EXPECTED_JSON
  }

}

