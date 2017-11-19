package examples;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

public class Images extends BaseTest {

  private static int invalidImageCount;
  private WebDriver driver;

  public static void imageResponse(WebElement imgElement) {
    try {
      HttpClient client = HttpClientBuilder.create().build();
      HttpGet request = new HttpGet(imgElement.getAttribute("src"));
      HttpResponse response = client.execute(request);
      if (response.getStatusLine().getStatusCode() != 200) invalidImageCount++;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @BeforeClass(alwaysRun = true)
  void setUp() {
    driver = getDriver();
  }

  @Test(alwaysRun = true)
  public void imageTest() {
    try {
      invalidImageCount = 0;
      List<WebElement> imagesList = driver.findElements(By.tagName("img"));
      System.out.println("Total no. of images are " + imagesList.size());
      for (WebElement imgElement : imagesList) {
        if (imgElement != null) {
          imageResponse(imgElement);
        } else {
          driver.quit();
        }
      }
      System.out.println("Total no. of invalid images are " + invalidImageCount);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
  }
}
