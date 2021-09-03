package tests;

import utils.fachadaURL.FacadeURL;
import utils.steps.AuthenticationSteps;
import utils.steps.CommonAsserts;
import utils.steps.Hooks;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.testng.Assert;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.*;


public class Authentication extends Hooks {

    private static final Logger LOGGER = LogManager.getLogger(Authentication.class);



    @Test
    public void CreatingGuestSession(){
        FacadeURL fachadaURL = new FacadeURL(""+ domain, "","authentication","","guest_session", true,false, ""+apiKey,"");

        String url = fachadaURL.construirURLFaca();

        Response response = when().get(url);

        Assert.assertTrue(CommonAsserts.successAssert(response));
    }

    @Test
    public void creatingRequestToken(){

        Response responseCreatingRequestToken = AuthenticationSteps.creatingRequestToken(apiKey);

        assertThat("The token was not successfully created", CommonAsserts.successAssert(responseCreatingRequestToken));

    }

    @Test
    public void creatingSessionLoginIn(){

        Response responseCreatingSessionLoginIn = AuthenticationSteps.creatingSessionLoginIn(apiKey, username, password);

        assertThat("The session login in was not successfully completed, the success message was false", CommonAsserts.successAssert(responseCreatingSessionLoginIn));
        assertThat("The session login in was not successfully completed, the token was not equal to the one sent in the body", CommonAsserts.expectedStringAssertWithPath(responseCreatingSessionLoginIn, "request_token", requestToken));


    }

    @Test(dependsOnMethods = "creatingSessionLoginIn")
    public void creatingSession(){

        Response responseCreatingSession = AuthenticationSteps.creatingSession(apiKey, username, password);

        CommonAsserts.successAssert(responseCreatingSession);
        CommonAsserts.notNullAssertWithPath(responseCreatingSession, "session_id");
    }

    @Test(dependsOnMethods = "creatingSession")
    public void deletingExistingSession(){

        Response responseDeletingExistingSession = AuthenticationSteps.deletingExistingSession(apiKey, username,password);

        responseDeletingExistingSession.then().assertThat().extract().path("success").equals(true);

    }


}