package examples;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.TestListener;

@Listeners(TestListener.class)
public class TestRealTimeReporter {

  @Test
  public void testRealReportOne() {
    Assert.assertTrue(true);
  }

  @Test
  public void testRealReportTwo() {
    Assert.assertTrue(false);
  }

  @Test(dependsOnMethods = "testRealReportTwo")
  public void testRealReportThree() {}
}
