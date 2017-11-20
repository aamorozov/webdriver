package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ContactFormPage extends BasePage {

  public ContactFormPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(id = "email")
  private WebElement emailTextBox;

  @FindBy(id = "id_order")
  private WebElement orderReferenceField;

  @FindBy(id = "id_contact")
  private WebElement selectField;

  @FindBy(id = "message")
  private WebElement messageTextarea;

  @FindBy(id = "submitMessage")
  private WebElement submitButton;

  @FindBy(id = "fileUpload")
  private WebElement uploadFileField;

  @FindBy(css = ".alert.alert-success")
  private WebElement confirmationMessage;

  public void setEmail(String strEmail) {
    emailTextBox.clear();
    emailTextBox.sendKeys(strEmail);
    Assert.assertEquals(emailTextBox.getAttribute("value"), strEmail);
  }

  public void setOrderReference(String strRef) {
    orderReferenceField.clear();
    orderReferenceField.sendKeys(strRef);
    Assert.assertEquals(orderReferenceField.getAttribute("value"), strRef);
  }

  public void selectOptionByIndex(int index) {
    Select drop = new Select(selectField);
    drop.selectByIndex(index);
  }

  public void setMessage(String strMessage) {
    messageTextarea.clear();
    messageTextarea.sendKeys(strMessage);
    Assert.assertEquals(messageTextarea.getAttribute("value"), strMessage);
  }

  public void uploadFile(String filePath) {
    uploadFileField.sendKeys(filePath);
  }

  public void submitMessage() {
    if (submitButton.isEnabled()) {
      try {
        Actions builder = new Actions(driver);
        builder.moveToElement(submitButton).build().perform();
        submitButton.click();
      } catch (Error e) {
        e.printStackTrace();
      }
    }
  }

  public void verifySubmission() {
    String text =
        new WebDriverWait(driver, 30)
            .until(ExpectedConditions.visibilityOf(confirmationMessage))
            .getText();
    String expected = "Your message has been successfully sent to our team.";
    Assert.assertTrue(text.equals(expected));
  }

  public void assertNoErrors() {
    LogEntries logs = driver.manage().logs().get("browser");
    for (LogEntry log : logs) {
      if (log.getLevel().toString().contains("Severe") || log.getMessage().contains("404")) {
        throw new Error();
      }
    }
  }
}
