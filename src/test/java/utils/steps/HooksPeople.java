package utils.steps;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class HooksPeople extends Hooks{


    protected String idPeopleDetail = "";

    @BeforeClass(dependsOnMethods = {"preparingCredentials"})
    public void setupPeople() throws IOException {
        String [] peopleData = ReadData.readData("src/test/resources/data/dataPeople.txt");
        idPeopleDetail = peopleData[0];

        Response responseCreatingSession = AuthenticationSteps.creatingSession(apiKey, username, password);
        session_id = responseCreatingSession.then().extract().path("session_id");

    }



}
