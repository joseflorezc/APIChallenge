package utils.steps;

import utils.fachadaURL.FacadeURL;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;


public class AuthenticationSteps extends Hooks {


    public static Response creatingRequestToken(String apiKey) {
        FacadeURL facadeURL = new FacadeURL("" + domain, "", "authentication", "", "token", true, false, "" + apiKey, "",false);

        String url = facadeURL.construirURLFaca();

        Response response = when().get(url);

        if (CommonAsserts.successAssert(response)) {
            requestToken = response.then().extract().path("request_token");
        }
        return response;
    }

    public static Response creatingSessionLoginIn(String apiKey, String username, String password) {


        if (requestToken == "") {
            Response responseCreatingRequestToken = creatingRequestToken(apiKey);
            if (CommonAsserts.successAssert(responseCreatingRequestToken)) {
                requestToken = responseCreatingRequestToken.then().extract().path("request_token");

            }
        }

        String jsonPayload = "{\n" +
                " \"username\": \"" + username + "\",\n" +
                " \"password\": \"" + password + "\",\n" +
                " \"request_token\": \"" + requestToken + "\"\n}";


        FacadeURL facadeURLcreatingSessionLoginIn = new FacadeURL("" + domain, "", "authentication", "", "token", false, true, "" + apiKey, "",false);

        String urlCreatingSessionLoginIn = facadeURLcreatingSessionLoginIn.construirURLFaca();


        Response responseCreatingSessionLoginIn = given().contentType("application/json").body(jsonPayload).when().post(urlCreatingSessionLoginIn);

        if (CommonAsserts.successAssert(responseCreatingSessionLoginIn).equals(true)) {

            alreadyLoggedInSession = true;
        }

        return responseCreatingSessionLoginIn;

    }

    public static Response creatingSession(String apiKey, String username, String password) {

        if (alreadyLoggedInSession == false) {

            Response responseCreatingSessionLoginIn = creatingSessionLoginIn(apiKey, username, password);
            CommonAsserts.successAssert(responseCreatingSessionLoginIn);
            CommonAsserts.expectedStringAssertWithPath(responseCreatingSessionLoginIn,"request_token", requestToken );
        }


        String jsonCreatingSession = "{\n" +
                "\"request_token\": \"" + requestToken + "\"\n}";

        FacadeURL facadeURLCreatingSession = new FacadeURL("" + domain, "", "authentication", "", "session", true, false, "" + apiKey, "",false);

        String urlCreatingSession = facadeURLCreatingSession.construirURLFaca();

        Response responseCreatingSession = given().contentType("application/json").body(jsonCreatingSession).when().post(urlCreatingSession);

        if (CommonAsserts.successAssert(responseCreatingSession) == true) {

            session_id = responseCreatingSession.then().extract().path("session_id");
        }

        return responseCreatingSession;
    }

    public static Response deletingExistingSession(String apiKey, String username, String password) {

        if (session_id == "") {
            creatingSession(apiKey, username, password);
        }
        String jsonDeletingSession = "{\n" +
                "\"session_id\": \"" + session_id + "\"\n}";

        FacadeURL facadeURLDeletingExistingSession = new FacadeURL("https://api.themoviedb.org/3", "", "authentication", "", "session", false, false, "" + apiKey, "",false);

        String urlDeletingExistingSession = facadeURLDeletingExistingSession.construirURLFaca();

        Response responseDeletingExistingSession = given().contentType("application/json").body(jsonDeletingSession).when().delete(urlDeletingExistingSession);

        if (CommonAsserts.successAssert(responseDeletingExistingSession) == true ){
            session_id = "";
            requestToken = "";
            alreadyLoggedInSession = true;
        }
        return responseDeletingExistingSession;

    }


}
