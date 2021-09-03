package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.steps.*;

import static io.restassured.RestAssured.given;

public class People extends HooksPeople {


    @Test
    public void obtainingPeopleDetail() {

        Response response = PeopleSteps.obtainingPeopleDetail(apiKey, domain, idPeopleDetail);


        CommonAsserts.notNullAssertWithPath(response, "id","The id of a celebrity can't be null");



    }

}
