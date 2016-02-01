package com.sessioncam.FileParsing

import java.io.{File, FileNotFoundException}

import com.sessioncam.CustomUnitSpec
import com.typesafe.scalalogging.Logger
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito._
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfterEach, Matchers}
import org.slf4j.{Logger => Underlying}

/**
  * Created by SteveGreen on 31/01/2016.
  */
@RunWith(classOf[JUnitRunner])
class InputFileParser$Test extends CustomUnitSpec with Matchers with BeforeAndAfterEach{

  def initTest(mocked: Underlying): InputFileParser = {
    new InputFileParser() {
      override lazy val logger = Logger(mocked)
    }
  }

  val DIR = "/Users/steveGreen/Development/Dev Workspace/SessionCam/dataInput"
  val EMPTY_DIR = "/Users/steveGreen/Development/Dev Workspace/SessionCam/" //no json
  val EXT = List(".json")
  val BAD_EXT = List(".some-ext")

  behavior of "File Input Parser"

  it should "throw a FileNotFoundException if the directory does not exist " in {
    val mockedLogger = Mockito.mock(classOf[Underlying])
    when(mockedLogger.isInfoEnabled()).thenReturn(true)
    intercept[FileNotFoundException] {
      initTest(mockedLogger).getListOfFilesFromDirectory(
        "some-directory", EXT)
    }
  }

  //any real need for this test? - remove or rework method
  it should "throw a FileNotFoundException if the stated dir name is not a directory " in {
    val mockedLogger = Mockito.mock(classOf[Underlying])
    when(mockedLogger.isInfoEnabled()).thenReturn(true)
    intercept[FileNotFoundException] {
      initTest(mockedLogger).getListOfFilesFromDirectory(
        "as-above", EXT)
    }
  }

  //todo: worth adding a whitelist to the extension?
  it should "return an empty list if there are no files to be found that match" in {

    val mockedLogger = Mockito.mock(classOf[Underlying])
    when(mockedLogger.isInfoEnabled()).thenReturn(true)
    val list = initTest(mockedLogger).getListOfFilesFromDirectory(EMPTY_DIR, EXT)

    assert(list.isEmpty)
  }

  it should "return an empty list if there are no files of that extension" in {

    val mockedLogger = Mockito.mock(classOf[Underlying])
    when(mockedLogger.isInfoEnabled()).thenReturn(true)
    val list = initTest(mockedLogger).getListOfFilesFromDirectory(EMPTY_DIR, BAD_EXT)

    list shouldBe empty //assert(list.isEmpty)
  }

  it should "return a list of filenames for the specified directory" in {
    val mockedLogger = Mockito.mock(classOf[Underlying])
    when(mockedLogger.isInfoEnabled()).thenReturn(true)
    val list = initTest(mockedLogger).getListOfFilesFromDirectory(DIR, EXT)

    list shouldBe a [List[File]]

    //todo: worth looking at this again - test is not isolated
//    list should contain(
//      new File("/Users/steveGreen/Development/Dev Workspace/SessionCam/dataInput/TimeZone1.json"),
//      new File("/Users/steveGreen/Development/Dev Workspace/SessionCam/dataInput/TimeZone2.json"))
  }
}
