package tests;

import utils.fachadaURL.FacadeURL;
import utils.steps.CommonAsserts;
import utils.steps.HooksList;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Lists extends HooksList {



    @Test
    public void obtainingListDetail(){

        FacadeURL fachadaURL = new FacadeURL("https://api.themoviedb.org/3", "","list",""+idListDetail,"", false,false, ""+apiKey,"");

        String url = fachadaURL.construirURLFaca();


        Response response = when().get(url);

        response.then().extract().path("created_by").equals("joseflorez");
        response.then().extract().path("id").equals(idListDetail);
    }

    @Test
    public void CreatingListOfMovies(){

        String jsonCreatingListOfMovies = "{\n" +
                "\"name\": \"" + name+ "\",\n" +
                "\"description\": \"" + description+ "\",\n" +
                "\"language\": \"" + language + "\"\n}";

        FacadeURL facadeURL = new FacadeURL("" + domain, "","list","","", false,false, ""+apiKey,""+session_id);
        String urlCreatingListOfMovies = facadeURL.construirURLFaca();

        Response response = given().contentType("application/json").body(jsonCreatingListOfMovies).when().post(urlCreatingListOfMovies);

        response.then().log().body();
        CommonAsserts.successAssert(response);
        CommonAsserts.notNullAssertWithPath(response, "list_id");

        if(CommonAsserts.notNullAssertWithPath(response, "list_id")){
            System.out.println("id lista to add: " );
            idListToAdd = response.then().extract().path("list_id");
        }




    }

    @Test
    public void addingMovieToList(){

        //new Faker().letterify("random ??????? description ??????? ??????????")
    }

    @Test
    public void ClearingMoviesFromList(){}

    @Test
    public void DeletingListWithID(){}

}
