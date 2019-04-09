package sandbox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadTextFile {
  /**
   * Method used to extract data from the txt file and return it as a String.
   */
  static StringBuilder readLines(File f) throws IOException {
    FileReader fr = new FileReader(f);
    BufferedReader br = new BufferedReader(fr);
    String line;
    StringBuilder results = new StringBuilder();
    while ((line = br.readLine()) != null) {
      if (results.equals("")) {
        results.append(line);
      } else {
        results.append(line).append(" ");
      }
    }
    br.close();
    fr.close();
    return results;
  }
}
