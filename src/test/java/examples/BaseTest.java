package examples;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class BaseTest {

  private WebDriver driver;
  private static String workDir = System.getProperty("user.dir");
  private static String driverPath = workDir + "/src/drivers/";

  WebDriver getDriver() {
    return this.driver;
  }

  private void setDriver(String browserType, String appURL) {
    switch (browserType) {
      case "chrome":
        driver = initChromeDriver(appURL);
        break;
      case "firefox":
        driver = initFirefoxDriver(appURL);
        break;
      case "headlessChrome":
        driver = initChromeDriverHeadLess(appURL);
        break;
      case "headlessFf":
        driver = initFirefoxHeadless(appURL);
        break;
      case "travisChrome":
        driver = initChromesCI(appURL);
        break;
      default:
        driver = initChromeDriverHeadLess(appURL);
    }
  }

  private WebDriver initChromesCI(String appURL) {
    ChromeOptions options = new ChromeOptions();
    options.setBinary("/usr/bin/chromium-browser");
    options.addArguments("--no-sandbox");
    options.addArguments("--no-default-browser-check");
    options.addArguments("--no-first-run");
    options.addArguments("--disable-default-apps");
    driver = new ChromeDriver(options);
    driver.navigate().to(appURL);
    return driver;
  }

  private WebDriver initFirefoxHeadless(String appURL) {
    System.out.println("Start Firefox in Headless");
    this.driver = new FirefoxDriver();
    driver.navigate().to(appURL);
    return driver;
  }

  private WebDriver initChromeDriverHeadLess(String appURL) {
    System.out.println("Start Chrome in Headless");
    System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("headless");
    options.addArguments("window-size=1200x600");
    this.driver = new ChromeDriver(options);
    driver.navigate().to(appURL);
    return driver;
  }

  private WebDriver initChromeDriver(String appURL) {
    System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver");
    this.driver = new ChromeDriver();
    defConfiguration(driver);
    driver.navigate().to(appURL);
    return driver;
  }

  private WebDriver initFirefoxDriver(String appURL) {
    this.driver = new FirefoxDriver();
    defConfiguration(driver);
    driver.navigate().to(appURL);
    return driver;
  }

  private static void defConfiguration(WebDriver driver) {
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @BeforeClass(alwaysRun = true)
  @Parameters({"browserType", "appURL"})
  public void initializeTestBaseSetup(
      @Optional("chrome") String browserType,
      @Optional("http://automationpractice.com/index.php?controller=contact") String appURL) {
    try {
      setDriver(browserType, appURL);
    } catch (Exception e) {
      System.out.println("Error in browser Setup: " + Arrays.toString(e.getStackTrace()));
    }
  }

  @AfterClass
  public void tearDown() {
    driver.manage().deleteAllCookies();
    driver.close();
    driver.quit();
  }
}
