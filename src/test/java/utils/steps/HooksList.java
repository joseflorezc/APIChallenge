package utils.steps;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class HooksList extends Hooks{

    protected String name = "list of testing";
    protected String description = "a little description";
    protected String language = "en";
    protected String idMovieToAdd = "";
    protected String idListToAdd = "";
    protected String idListDetail = "";

    @BeforeClass(dependsOnMethods = {"preparingCredentials"})
    public void setupLists() throws IOException {
        String [] moviesData = ReadListData.readListData("src/test/resources/data/dataLists.txt");
        idListDetail = moviesData[0];
        idMovieToAdd = moviesData[1];

        Response responseCreatingSession = AuthenticationSteps.creatingSession(apiKey, username, password);
        session_id = responseCreatingSession.then().extract().path("session_id");

    }



}
