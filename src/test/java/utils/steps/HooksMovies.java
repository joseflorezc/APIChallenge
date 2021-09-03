package utils.steps;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class HooksMovies extends Hooks{

    protected String idMovieDetail = "";

    @BeforeClass(dependsOnMethods = {"preparingCredentials"})
    public void setupPeople() throws IOException {
        String [] movieFeatureData = ReadData.readData("src/test/resources/data/dataMovies.txt");
        idMovieDetail = movieFeatureData[0];

        Response responseCreatingSession = AuthenticationSteps.creatingSession(apiKey, username, password);
        session_id = responseCreatingSession.then().extract().path("session_id");

    }



}
