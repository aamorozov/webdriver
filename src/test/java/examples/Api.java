package examples;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Api {
  @Test
  public void apiTest() throws Exception {
    try {
      URL url =
          new URL(
              "http://maps.googleapis.com/maps/api/geocode/json?address=chicago&sensor=false&#8221");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");

      if (conn.getResponseCode() != 200) {
        throw new RuntimeException("HTTP error code : " + conn.getResponseCode());
      }

      Scanner scan = new Scanner(url.openStream());
      StringBuilder entireResponse = new StringBuilder();
      while (scan.hasNext()) entireResponse.append(scan.nextLine());

      System.out.println("Response : " + entireResponse);

      scan.close();

      JSONObject obj = new JSONObject(entireResponse.toString());
      String responseCode = obj.getString("status");
      System.out.println("status : " + responseCode);

      JSONArray arr = obj.getJSONArray("results");
      for (int i = 0; i < arr.length(); i++) {
        String placeid = arr.getJSONObject(i).getString("place_id");
        System.out.println("Place id : " + placeid);
        String formatAddress = arr.getJSONObject(i).getString("formatted_address");
        System.out.println("Address : " + formatAddress);
        if (formatAddress.equalsIgnoreCase("Chicago, IL, USA")) {
          System.out.println("Address is as Expected");
        } else {
          System.out.println("Address is not as Expected");
        }
      }

      conn.disconnect();
    } catch (IOException e) {

      e.printStackTrace();
    }
  }
}
