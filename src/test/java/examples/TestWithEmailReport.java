package examples;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.TestListenerWithPdf;
import utils.ReadConfig;
import utils.SendEmail;

@Listeners(TestListenerWithPdf.class)
public class TestWithEmailReport extends BaseTest {
  private WebDriver driver;

  @BeforeClass
  public void setUp() {
    driver = getDriver();
  }

  @Test
  public void testPDFReportOne() {
    Assert.assertTrue(false);
  }

  @Test
  public void testPDFReportTwo() {
    Assert.assertTrue(false);
  }

  @Test
  public void testPDFReportThree() {
    Assert.assertTrue(true);
  }

  @AfterSuite
  public void tearDown() {
    ReadConfig config = new ReadConfig();
    SendEmail.sendEmail(
        config.getEmailFrom(), config.getPass(), config.getEmailTo(), "PDF Report", "");
  }
}
