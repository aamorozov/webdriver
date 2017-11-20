package utils;

import org.testng.*;
import org.testng.xml.XmlSuite;
import java.util.*;

public class TestReporter implements IReporter {

  @Override
  public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1, String outputDirectory) {
    for (ISuite iSuite : arg1) {
      Map<String, ISuiteResult> results = iSuite.getResults();
      Set<String> keys = results.keySet();
      for (String key : keys) {
        ITestContext context = results.get(key).getTestContext();
        System.out.println(
            "Suite Name->"
                + context.getName()
                + "::Report output Ditectory->"
                + context.getOutputDirectory()
                + "::Suite Name->"
                + context.getSuite().getName()
                + "::Start Date Time for execution->"
                + context.getStartDate()
                + "::End Date Time for execution->"
                + context.getEndDate());

        IResultMap resultMap = context.getFailedTests();
        Collection<ITestNGMethod> failedMethods = resultMap.getAllMethods();
        System.out.println("--------FAILED TEST CASE---------");
        for (ITestNGMethod iTestNGMethod : failedMethods) {
          System.out.println(
              "TESTCASE NAME->"
                  + iTestNGMethod.getMethodName()
                  + "\nDescription->"
                  + iTestNGMethod.getDescription()
                  + "\nPriority->"
                  + iTestNGMethod.getPriority()
                  + "\n:Date->"
                  + new Date(iTestNGMethod.getDate()));
        }
      }
    }
  }
}
