package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
  private static Properties props;
    public ReadConfig() {
    try {
      File src = new File(System.getProperty("user.dir") + "/src/main/resources/Config.properties");
      FileInputStream fs = new FileInputStream(src);
      props = new Properties();
      props.load(fs);
      System.out.println(fs);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getEmailTo() {
    return props.getProperty("emailTo");
  }

  public String getEmailFrom() {
    return props.getProperty("emailFrom");
  }

  public String getPass() {
    return props.getProperty("pass");
  }
}
