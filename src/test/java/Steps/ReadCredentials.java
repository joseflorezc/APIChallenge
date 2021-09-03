package Steps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCredentials {

    public static String [] readCredentials(String file) throws IOException {
        String [] credentials =  new String[3];

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentLine = reader.readLine();
        credentials = currentLine.split(",");

        reader.close();

        return credentials;
    }


}
