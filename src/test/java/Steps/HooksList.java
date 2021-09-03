package Steps;

import io.restassured.response.Response;
import org.apache.logging.log4j.core.config.Order;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class HooksList extends Hooks{

    protected String name = "list of testing";
    protected String description = "a little description";
    protected String language = "en";

    @BeforeClass(dependsOnMethods = {"preparingCredentials"})
    public void setupLists(){
        Response responseCreatingSession = AuthenticationSteps.creatingSession(apiKey, username, password);
        session_id = responseCreatingSession.then().extract().path("session_id");


    }



}
