package Steps;

import Steps.ReadCredentials;
import org.testng.annotations.*;

import java.io.IOException;

public class Hooks {

    protected String apiKey;
    protected String username;
    protected String password;
    protected static String requestToken;
    protected static String session_id;
    protected static String domain;
    protected static Boolean alreadyLoggedInSession;

    @BeforeClass
    public void preparingCredentials () throws IOException {
        String [] credentials = ReadCredentials.readCredentials("src/test/resources/Credentials/Credentials.txt");
        username = credentials[0];
        password = credentials[1];
        apiKey = credentials[2];
        domain = "https://api.themoviedb.org/3";
        requestToken = "";
        session_id = "";
        alreadyLoggedInSession = false;
    }





//    @AfterMethod
//    public void testingHook(){
//        System.out.println(domain);
//    }

}
