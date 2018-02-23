package ru.stqa.noy.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance() {
    Point p1 = new Point(1,4);
    Point p2 = new Point(4,1);
    Assert.assertEquals( p1.distance(p2), 5.242640687119285);
  }

}
