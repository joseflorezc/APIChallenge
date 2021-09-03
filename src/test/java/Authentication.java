import FachadaURL.FacadeURL;
import Steps.AuthenticationSteps;
import Steps.CommonAsserts;
import Steps.Hooks;
import Steps.HooksList;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.hamcrest.Matchers;
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

        assertThat("esto es si sale mal",CommonAsserts.successAssert(responseCreatingRequestToken));

    }

    @Test
    public void creatingSessionLoginIn(){

        Response responseCreatingSessionLoginIn = AuthenticationSteps.creatingSessionLoginIn(apiKey, username, password);

        responseCreatingSessionLoginIn.then().extract().path("success").equals(true);
        responseCreatingSessionLoginIn.then().extract().path("request_token").equals(requestToken);

    }

    @Test(dependsOnMethods = "creatingSessionLoginIn")
    public void creatingSession(){

        Response responseCreatingSession = AuthenticationSteps.creatingSession(apiKey, username, password);

        System.out.println("-----------------body dentro de authentication Test --------------------");
        responseCreatingSession.then().log().body();

        responseCreatingSession.then().extract().path("success").equals(true);
        responseCreatingSession.then().log().body().extract().path("session_id").equals(Matchers.notNullValue());
    }

    @Test(dependsOnMethods = "creatingSession")
    public void deletingExistingSession(){

        Response responseDeletingExistingSession = AuthenticationSteps.deletingExistingSession(apiKey, username,password);

        responseDeletingExistingSession.then().assertThat().extract().path("success").equals(true);

    }


}
