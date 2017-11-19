package examples;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.ContactFormPage;

import java.util.UUID;

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
