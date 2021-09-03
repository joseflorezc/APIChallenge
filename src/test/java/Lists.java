import FachadaURL.FacadeURL;
import Steps.AuthenticationSteps;
import Steps.Hooks;
import Steps.HooksList;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Lists extends HooksList {


    private String idList = "7104769";



    @Test
    public void obtainingListDetail(){

        FacadeURL fachadaURL = new FacadeURL("https://api.themoviedb.org/3", "","list",""+idList,"", false,false, ""+apiKey,"");

        String url = fachadaURL.construirURLFaca();


        Response response = when().get(url);

        response.then().extract().path("created_by").equals("joseflorez");
        response.then().extract().path("id").equals(idList.toString());
    }

    @Test
    public void CreatingListOfMovies(){

        String jsonCreatingListOfMovies = "{\n" +
                "\"name\": \"" + name+ "\",\n" +
                "\"description\": \"" + description+ "\",\n" +
                "\"language\": \"" + language + "\"\n}";

        FacadeURL facadeURL = new FacadeURL("" + domain, "","list","","", false,false, ""+apiKey,""+session_id);

        String urlCreatingListOfMovies = facadeURL.construirURLFaca();

        System.out.println(urlCreatingListOfMovies);
        Response response = given().contentType("application/json").body(jsonCreatingListOfMovies).when().post(urlCreatingListOfMovies);

        response.then().log().body();


    }

    @Test
    public void addingMovieToList(){}

    @Test
    public void ClearingMoviesFromList(){}

    @Test
    public void DeletingListWithID(){}

}
