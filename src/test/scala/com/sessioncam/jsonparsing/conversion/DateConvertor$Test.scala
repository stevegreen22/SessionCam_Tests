package com.sessioncam.jsonparsing.conversion

import com.sessioncam.CustomException.TimezoneNotSupportedException
import com.sessioncam.CustomUnitSpec
import com.sessioncam.model.TimezoneDetails
import com.typesafe.scalalogging.Logger
import org.joda.time.DateTime
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito._
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfterEach, Matchers}
import org.slf4j.{Logger => Underlying}

/**
  * Created by SteveGreen on 30/01/2016.
  */
@RunWith(classOf[JUnitRunner])
class DateConvertor$Test extends CustomUnitSpec with Matchers with BeforeAndAfterEach{

  private val ORIGINAL_DATE = new DateTime("2016-01-30T10:51:23.697")
  private val DATE_GMT_PLUS_5 = new DateTime("2016-01-30T15:51:23.697+05:00")
  private val CONVERT_TO = "Etc/GMT-5"
  private val testTimezone = new TimezoneDetails(null, null, null)

  def initTest(mocked: Underlying): DateConvertor = {
    new DateConvertor() {
      override lazy val logger = Logger(mocked)
    }
  }

  @Before
  def setupTestTimezone(): TimezoneDetails = {
    testTimezone.apply("a", "Etc/UTC", ORIGINAL_DATE)
  }

  behavior of "Date Converter"

  it should "throw IllegalArgumentException if 'from' or 'to' are empty strings" in {

      val mockedLogger = Mockito.mock(classOf[Underlying])
      when(mockedLogger.isInfoEnabled()).thenReturn(true)
      intercept[IllegalArgumentException] {
        initTest(mockedLogger).convertTimezone(testTimezone, "")
      }
  }

  it should "throw TimezoneNotSupportedException if timezone is not in the whitelist" in {

    val mockedLogger = Mockito.mock(classOf[Underlying])
    when(mockedLogger.isInfoEnabled()).thenReturn(true)
    intercept[TimezoneNotSupportedException] {
      initTest(mockedLogger).convertTimezone(setupTestTimezone(), "some-string-that-is-hopefully-not-in-the-whitelist...")
    }
  }

  it should "accurately convert a Etc/UTC to Etc/GMT-5" in {
    val mockedLogger = Mockito.mock(classOf[Underlying])
    when(mockedLogger.isInfoEnabled()).thenReturn(true)
    val testTime = initTest(mockedLogger).
        convertTimezone(setupTestTimezone(), "Etc/GMT-5")

    testTime.toDate shouldBe DATE_GMT_PLUS_5.toDate
  }



}


