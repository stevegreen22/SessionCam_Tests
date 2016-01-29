package com.sessioncam

/**
  * Created by SteveGreen on 28/01/2016.
  */
class SetSpec extends UnitSpec {

  "An empty Set" should "have size 0" in {
    assert(Set.empty.size == 0)
  }

  it should "produce NoSuchElementException when head is invoked" in {
    intercept[NoSuchElementException] {
      Set.empty.head
    }
  }
}