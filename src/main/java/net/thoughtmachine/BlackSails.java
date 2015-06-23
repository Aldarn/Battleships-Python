package net.thoughtmachine;

import java.io.*;

public class BlackSails {

  private static final String input = "/input.txt";

  public void loadInput() {
    InputStream is = getClass().getResourceAsStream(input);
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));

    try {
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String... args) {
    BlackSails app = new BlackSails();
    app.loadInput();
  }
}
