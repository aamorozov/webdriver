package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import annotations.RetryCountIfFailed;

public class RetryAnalyzer implements IRetryAnalyzer {

  private int counter = 0;

  @Override
  public boolean retry(ITestResult result) {
    RetryCountIfFailed annotation =
        result
            .getMethod()
            .getConstructorOrMethod()
            .getMethod()
            .getAnnotation(RetryCountIfFailed.class);

    if ((annotation != null) && (counter < annotation.value())) {
      counter++;
      return true;
    }
    return false;
  }
}
