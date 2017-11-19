package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

  private Properties props;

  public ReadConfig() {
    try {
      File src = new File("Config.properties");
      FileInputStream fs = new FileInputStream(src);
      props = new Properties();
      props.load(fs);
      System.out.println(src);
    } catch (Exception e) {
      e.getLocalizedMessage();
    }
  }
}
