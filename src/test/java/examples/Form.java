package examples;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactFormPage;
import utils.TestListenerWithPdf;
import utils.Screenshot;

import java.util.UUID;

@Listeners(TestListenerWithPdf.class)
public class Form extends BaseTest {

  private WebDriver driver;
  private String workingDir = System.getProperty("user.dir");
  private String filePath = workingDir + "/src/test/resources/upload.txt";

  private static String randomEmail() {
    return "random-" + UUID.randomUUID().toString() + "@example.com";
  }

  @BeforeClass
  public void setUp() {
    driver = getDriver();
  }

  @AfterMethod
  public void tearDown(ITestResult result) {
    if (ITestResult.FAILURE == result.getStatus()) {
      Screenshot.captureScreenshot(driver, result.getName());
    }
  }

  @Test(alwaysRun = true)
  public void formTest() {
    ContactFormPage form = new ContactFormPage(driver);
    final String randomEmail = randomEmail();
    form.selectOptionByIndex(2);
    form.setEmail(randomEmail);
    form.setOrderReference("test");
    form.setMessage("test");
    form.uploadFile(filePath);
    form.submitMessage();
    form.verifySubmission();
    form.assertNoErrors();
  }
}
