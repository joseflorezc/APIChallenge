package Steps;

import FachadaURL.FachadaURL;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    @Test
    public void CreatingGuestSession(){
        FachadaURL fachadaURL = new FachadaURL("https://api.themoviedb.org/3", "","authentication","","guest_session", true, ""+apiKey);

        String url = fachadaURL.construirURLFach();

        Response response = when().get(url);

        response.then().extract().path("success").equals(true);
    }

    @Test
    public void CreatingRequestToken(){
        FachadaURL fachadaURL = new FachadaURL("https://api.themoviedb.org/3", "","authentication","","token", true, ""+apiKey);

        String url = fachadaURL.construirURLFach();


        Response response = when().get(url);


        response.then().extract().path("success").equals(true);

        if (response.then().extract().path("success").equals(true)){
            requestToken = response.then().log().body().extract().path("request_token");
            System.out.println(requestToken);
        }


    }

    @Test
    public void CreatingSessionLoginIn(){
        FachadaURL fachadaURL = new FachadaURL("https://api.themoviedb.org/3", "","authentication","","token", true, ""+apiKey);

        String url = fachadaURL.construirURLFach();

        Response response = when().get(url);


        response.then().extract().path("success").equals(true);

        if (response.then().extract().path("success").equals(true)){
            requestToken = response.then().log().body().extract().path("request_token");
            System.out.println(requestToken);
        }

        String jsonPayload = "{\n" +
                " \"username\": \"" + username+ "\",\n" +
                " \"password\": \"" + password+ "\",\n" +
                " \"username\": \"" + requestToken+ "\"\n";

        System.out.println(jsonPayload);

        FachadaURL fachadaURL2 = new FachadaURL("https://api.themoviedb.org/3", "","authentication","","token", true, ""+apiKey);

        String url2 = fachadaURL.construirURLFach();



    }

    @Test
    public void getAlternativeTitles(){
        FachadaURL fachadaURL = new FachadaURL("https://api.themoviedb.org/3", "","movie","619297-sweet-girl","alternative_titles",false, "2e172e18c00b19621224226c2fff60f9");

        LOGGER.info("Starts running test getAlternativeTitles");
        String url = fachadaURL.construirURLFach();

        when().get(url).then().statusCode(200);
        LOGGER.info("Finish running test getAlternativeTitles");

    }

    @Test
    public void getCreditsMovie(){
        //fachada es para steps
        FachadaURL fachadaURL = new FachadaURL("https://api.themoviedb.org/3", "","movie","619297-sweet-girl","credits",false, "2e172e18c00b19621224226c2fff60f9");

        LOGGER.info("Starts running test getAlternativeTitles");
        String url = fachadaURL.construirURLFach();

        given().when().get(url).then().statusCode(200);
        LOGGER.info("Finish running test getAlternativeTitles");
    }

    @Test
    public void getCreditsTV(){
        FachadaURL fachadaURL = new FachadaURL("https://api.themoviedb.org/3", "","tv","91363-what-if","credits",false , "2e172e18c00b19621224226c2fff60f9");

        LOGGER.info("Starts running test getCreditsTV");
        String url = fachadaURL.construirURLFach();

        given().when().get(url).then().statusCode(200);
        LOGGER.info("Finish running test getCreditsTV");
    }

    @Test
    public void getEpisodeGroupsTV(){
        FachadaURL fachadaURL = new FachadaURL("https://api.themoviedb.org/3", "","tv","91363-what-if","episode_groups",false,"2e172e18c00b19621224226c2fff60f9");

        LOGGER.info("Empezo a correr el test getEpisodeGroupsTV");
        String url = fachadaURL.construirURLFach();

        given().when().get(url).then().statusCode(200);
        LOGGER.info("Termino de correr el test getEpisodeGroupsTV");
    }

    @Test
    public void getPeopleDetail(){
        FachadaURL fachadaURL = new FachadaURL("https://api.themoviedb.org/3", "","person","169337-katherine-lanasa","",false ,"2e172e18c00b19621224226c2fff60f9");

        LOGGER.info("Empezo a correr el test getPeopleDetail");
        String url = fachadaURL.construirURLFach();

        given().when().get(url).then().statusCode(200);
        LOGGER.info("Termino de correr el test getPeopleDetail");
    }

}
