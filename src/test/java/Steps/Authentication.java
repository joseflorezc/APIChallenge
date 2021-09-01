package Steps;

import FachadaURL.FachadaURL;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;


public class Authentication {



    private static final Logger LOGGER = LogManager.getLogger(Authentication.class);


    private static String apiKey = "2e172e18c00b19621224226c2fff60f9";
    private static String username = "joseflorez";
    private static String password = "WsF6XRQ!r8QcZpW";

    private String requestToken = "";
    private String session_id = "";

    @Test
    public void CreatingGuestSession(){
        FachadaURL fachadaURL = new FachadaURL("https://api.themoviedb.org/3", "","authentication","","guest_session", true,false, ""+apiKey);

        String url = fachadaURL.construirURLFach();

        Response response = when().get(url);

        response.then().extract().path("success").equals(true);
    }

    @Test
    public void CreatingRequestToken(){
        FachadaURL fachadaURL = new FachadaURL("https://api.themoviedb.org/3", "","authentication","","token", true,false, ""+apiKey);

        String url = fachadaURL.construirURLFach();


        Response response = when().get(url);


        response.then().extract().path("success").equals(true);

        if (response.then().extract().path("success").equals(true)){
            requestToken = response.then().extract().path("request_token");
            System.out.println(requestToken);
        }


    }

    @Test
    public void CreatingSessionLoginIn(){
        FachadaURL fachadaURL = new FachadaURL("https://api.themoviedb.org/3", "","authentication","","token", true,false, ""+apiKey);

        String url = fachadaURL.construirURLFach();

        Response response = when().get(url);


        response.then().extract().path("success").equals(true);

        if (response.then().extract().path("success").equals(true)){
            requestToken = response.then().extract().path("request_token");
        }

        String jsonPayload = "{\n" +
                " \"username\": \"" + username+ "\",\n" +
                " \"password\": \"" + password+ "\",\n" +
                " \"request_token\": \"" + requestToken+ "\"\n}";

        System.out.println(jsonPayload);

        FachadaURL fachadaURL2 = new FachadaURL("https://api.themoviedb.org/3", "","authentication","","token", false,true , ""+apiKey);

        String url2 = fachadaURL2.construirURLFach();


        Response response2 = given().contentType("application/json").body(jsonPayload).when().post(url2);


        response2.then().extract().path("success").equals(true);
        response2.then().extract().path("request_token").equals(requestToken);

    }

    @Test
    public void CreatingSession(){
        FachadaURL fachadaURL = new FachadaURL("https://api.themoviedb.org/3", "","authentication","","token", true,false, ""+apiKey);

        String url = fachadaURL.construirURLFach();

        Response response = when().get(url);


        response.then().extract().path("success").equals(true);

        if (response.then().extract().path("success").equals(true)){
            requestToken = response.then().extract().path("request_token");
        }

        String jsonPayload = "{\n" +
                " \"username\": \"" + username+ "\",\n" +
                " \"password\": \"" + password+ "\",\n" +
                " \"request_token\": \"" + requestToken+ "\"\n}";


        FachadaURL fachadaURL2 = new FachadaURL("https://api.themoviedb.org/3", "","authentication","","token", false,true , ""+apiKey);

        String url2 = fachadaURL2.construirURLFach();


        Response response2 = given().contentType("application/json").body(jsonPayload).when().post(url2);


        response2.then().extract().path("success").equals(true);
        response2.then().extract().path("request_token").equals(requestToken);

        String jsonCreatingSession = "{\n" +
                "\"request_token\": \"" + requestToken+ "\"\n}";

        FachadaURL fachadaURL3 = new FachadaURL("https://api.themoviedb.org/3", "","authentication","","session", true,false, ""+apiKey);

        String url3 = fachadaURL3.construirURLFach();

        Response response3 = given().contentType("application/json").body(jsonCreatingSession).when().post(url3);

        response3.then().extract().path("success").equals(true);
        response3.then().extract().path("session_id").equals(Matchers.notNullValue());
    }

    @Test
    public void DeletingExistingSession(){
        FachadaURL fachadaURL = new FachadaURL("https://api.themoviedb.org/3", "","authentication","","token", true,false, ""+apiKey);

        String url = fachadaURL.construirURLFach();

        Response response = when().get(url);


        response.then().extract().path("success").equals(true);

        if (response.then().extract().path("success").equals(true)){
            requestToken = response.then().extract().path("request_token");
        }

        String jsonPayload = "{\n" +
                " \"username\": \"" + username+ "\",\n" +
                " \"password\": \"" + password+ "\",\n" +
                " \"request_token\": \"" + requestToken+ "\"\n}";

        FachadaURL fachadaURL2 = new FachadaURL("https://api.themoviedb.org/3", "","authentication","","token", false,true , ""+apiKey);

        String url2 = fachadaURL2.construirURLFach();


        Response response2 = given().contentType("application/json").body(jsonPayload).when().post(url2);


        response2.then().extract().path("success").equals(true);
        response2.then().extract().path("request_token").equals(requestToken);

        String jsonCreatingSession = "{\n" +
                "\"request_token\": \"" + requestToken+ "\"\n}";

        FachadaURL fachadaURL3 = new FachadaURL("https://api.themoviedb.org/3", "","authentication","","session", true,false, ""+apiKey);

        String url3 = fachadaURL3.construirURLFach();

        Response response3 = given().contentType("application/json").body(jsonCreatingSession).when().post(url3);

        response3.then().extract().path("success").equals(true);
        response3.then().extract().path("session_id").equals(Matchers.notNullValue());
        if( response3.then().extract().path("session_id").equals(Matchers.notNullValue())){

            session_id = response.jsonPath().getString("session_id");

        }
        FachadaURL fachadaURL4 = new FachadaURL("https://api.themoviedb.org/3", "","authentication","","session", false,false, ""+apiKey);

        String url4 = fachadaURL4.construirURLFach();

        String jsonDeletingSession = "{\n" +
                "\"session_id\": \"" + session_id+ "\"\n}";

        Response response4 = given().contentType("application/json").body(jsonDeletingSession).when().delete(url4);

        response4.then().assertThat().extract().path("success").equals(true);

    }


    @Test
    public void getAlternativeTitles(){
        FachadaURL fachadaURL = new FachadaURL("https://api.themoviedb.org/3", "","movie","619297-sweet-girl","alternative_titles",false, false, "2e172e18c00b19621224226c2fff60f9");

        LOGGER.info("Starts running test getAlternativeTitles");
        String url = fachadaURL.construirURLFach();

        when().get(url).then().statusCode(200);
        LOGGER.info("Finish running test getAlternativeTitles");

    }

    @Test
    public void getCreditsMovie(){
        //fachada es para steps
        FachadaURL fachadaURL = new FachadaURL("https://api.themoviedb.org/3", "","movie","619297-sweet-girl","credits",false, false,"2e172e18c00b19621224226c2fff60f9");

        LOGGER.info("Starts running test getAlternativeTitles");
        String url = fachadaURL.construirURLFach();

        given().when().get(url).then().statusCode(200);
        LOGGER.info("Finish running test getAlternativeTitles");
    }

    @Test
    public void getCreditsTV(){
        FachadaURL fachadaURL = new FachadaURL("https://api.themoviedb.org/3", "","tv","91363-what-if","credits",false , false,"2e172e18c00b19621224226c2fff60f9");

        LOGGER.info("Starts running test getCreditsTV");
        String url = fachadaURL.construirURLFach();

        given().when().get(url).then().statusCode(200);
        LOGGER.info("Finish running test getCreditsTV");
    }

    @Test
    public void getEpisodeGroupsTV(){
        FachadaURL fachadaURL = new FachadaURL("https://api.themoviedb.org/3", "","tv","91363-what-if","episode_groups",false,false,"2e172e18c00b19621224226c2fff60f9");

        LOGGER.info("Empezo a correr el test getEpisodeGroupsTV");
        String url = fachadaURL.construirURLFach();

        given().when().get(url).then().statusCode(200);
        LOGGER.info("Termino de correr el test getEpisodeGroupsTV");
    }

    @Test
    public void getPeopleDetail(){
        FachadaURL fachadaURL = new FachadaURL("https://api.themoviedb.org/3", "","person","169337-katherine-lanasa","",false , false,"2e172e18c00b19621224226c2fff60f9");

        LOGGER.info("Empezo a correr el test getPeopleDetail");
        String url = fachadaURL.construirURLFach();

        given().when().get(url).then().statusCode(200);
        LOGGER.info("Termino de correr el test getPeopleDetail");
    }

}
