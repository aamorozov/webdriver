package examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Links extends BaseTest {

  private WebDriver driver;

  @BeforeClass(alwaysRun = true)
  void setUp() {
    driver = getDriver();
  }

  @Test
  public void linksTest() {
    List<WebElement> links = driver.findElements(By.tagName("a"));
    System.out.println("Total links are " + links.size());
    for (int i = 0; i < links.size(); i++) {
      WebElement ele = links.get(i);
      String url = ele.getAttribute("href");
      verifyLinkActive(url);
    }
  }

  public static void verifyLinkActive(String linkUrl) {
    try {
      URL url = new URL(linkUrl);
      HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
      httpURLConnect.setConnectTimeout(3000);
      httpURLConnect.connect();
      if (httpURLConnect.getResponseCode() == 200) {
        System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
      }
      if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
        System.out.println(
            linkUrl
                + " - "
                + httpURLConnect.getResponseMessage()
                + " - "
                + HttpURLConnection.HTTP_NOT_FOUND);
      }
    } catch (Exception e) {
      // do nothing
    }
  }
}
