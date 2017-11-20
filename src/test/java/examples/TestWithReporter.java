package examples;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.TestReporter;

@Listeners(value = TestReporter.class)
public class TestWithReporter {
  @Test(priority = 0, description = "testReporterOne")
  public void testReporterOne() {
    Assert.assertTrue(true);
  }

  @Test(priority = 1, description = "testReporterTwo")
  public void testReporterTwo() {
    Assert.assertTrue(false);
  }
}
