package utils.steps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadListData {

    public static String [] readListData(String file) throws IOException {
        String [] credentials =  new String[3];

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentLine = reader.readLine();
        credentials = currentLine.split(",");

        reader.close();

        return credentials;
    }


}
