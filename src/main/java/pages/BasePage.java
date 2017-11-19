package pages;

import org.openqa.selenium.WebDriver;

abstract class BasePage {

  WebDriver driver;

  BasePage(WebDriver driver) {
    this.driver = driver;
  }
}
