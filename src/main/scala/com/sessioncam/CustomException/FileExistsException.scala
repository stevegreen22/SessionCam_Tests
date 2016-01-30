package com.sessioncam.CustomException

/**
  * Created by SteveGreen on 30/01/2016.
  */

//:( exception is in nio after all
case class FileExistsException(message: String) extends Exception(message)

