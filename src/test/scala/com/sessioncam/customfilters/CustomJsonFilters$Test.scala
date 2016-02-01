package com.sessioncam.customfilters

import com.sessioncam.model.TimezoneDetails
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

/**
  * Created by SteveGreen on 31/01/2016.
  */
@RunWith(classOf[JUnitRunner])
class CustomJsonFilters$Test extends FlatSpec with Matchers {

  def initTest: CustomJsonFiltering = {
    new CustomJsonFiltering() {
    }
  }

  behavior of "Json Filter"

  it should "return a filtered list of TimezoneDetails on given type" in {

    val ORIGINAL_LIST = List(
      new TimezoneDetails("some-string", "some-timezone", null),
      new TimezoneDetails("some-string", "some-timezone", null),
      new TimezoneDetails("some-other-string", "some-other-timezone", null),
      new TimezoneDetails("some-other-string", "some-other-timezone", null)
    )

    val EXPECTED_LIST = List(
      new TimezoneDetails("some-other-string", "some-other-timezone", null),
      new TimezoneDetails("some-other-string", "some-other-timezone", null)
    )

    val resultList = initTest.createTimezoneFilteredList(ORIGINAL_LIST, "some-other-timezone")

    resultList shouldBe EXPECTED_LIST
  }

  it should "return an empty list if no matches are found" in {

    val ORIGINAL_LIST = List(
      new TimezoneDetails("some-string", "some-timezone", null),
      new TimezoneDetails("some-string", "some-timezone", null),
      new TimezoneDetails("some-other-string", "some-other-timezone", null),
      new TimezoneDetails("some-other-string", "some-other-timezone", null)
    )

    val resultList = initTest.createTimezoneFilteredList(ORIGINAL_LIST, ":(")

    resultList shouldBe empty
  }

}
