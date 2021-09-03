package utils.steps;

import io.restassured.response.Response;
import utils.fachadaURL.FacadeURL;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ListsSteps extends HooksList {

    public static Response obtainingListDetail(String apiKey, String domain, String idListDetail) {

        FacadeURL fachadaURL = new FacadeURL("" + domain, "", "list", "" + idListDetail, "", false, false, "" + apiKey, "", false);

        String url = fachadaURL.construirURLFaca();


        Response response = when().get(url);

        return response;

    }

    public static Response creatingListOfMovies(String apiKey, String domain, String session_id, String name, String description, String language) {

        String jsonCreatingListOfMovies = "{\n" +
                "\"name\": \"" + name + "\",\n" +
                "\"description\": \"" + description + "\",\n" +
                "\"language\": \"" + language + "\"\n}";

        FacadeURL facadeURL = new FacadeURL("" + domain, "", "list", "", "", false, false, "" + apiKey, "" + session_id, false);
        String urlCreatingListOfMovies = facadeURL.construirURLFaca();

        Response response = given().contentType("application/json").body(jsonCreatingListOfMovies).when().post(urlCreatingListOfMovies);


        if (response.then().extract().path("list_id") != "") {

            idListToAdd = response.then().extract().path("list_id").toString();
        }
        return response;

    }

    public static Response addingMovieToList(String apiKey, String domain, String session_id, String name, String description, String language) {

        if (idListToAdd == "") {
            creatingListOfMovies(apiKey, domain, session_id, name, description, language);
        }


        String jsonAddingMovieToList = "{\n" +
                "\"media_id\": \"" + idMovieToAdd + "\"\n}";

        FacadeURL facadeURL = new FacadeURL("" + domain, "", "list", "" + idListToAdd, "add_item", false, false, "" + apiKey, "" + session_id, false);
        String urlAddingMovieToList = facadeURL.construirURLFaca();


        Response response = given().contentType("application/json").body(jsonAddingMovieToList).when().post(urlAddingMovieToList);
        if (response.body().path("success").equals(true)) {
            flagAddedMovieToList = true;
        }

        return response;
    }

    public static Response clearingMoviesFromList(String apiKey, String domain, String session_id, String name, String description, String language) {

        if (flagAddedMovieToList == false) {
            addingMovieToList(apiKey, domain, session_id, name, description, language);
        }


        FacadeURL facadeURL = new FacadeURL("" + domain, "", "list", "" + idListToAdd, "clear", false, false, "" + apiKey, "" + session_id, true);
        String urlClearingMoviesFromList = facadeURL.construirURLFaca();

        Response response = when().post(urlClearingMoviesFromList);


        return response;

    }

    public static Response deletingListWithID(String apiKey, String domain, String session_id, String name, String description, String language) {

        if (idListToAdd == "") {
            creatingListOfMovies(apiKey, domain, session_id, name, description + "deleting", language);
        }

        FacadeURL facadeURL = new FacadeURL("" + domain, "", "list", "" + idListToAdd, "", false, false, "" + apiKey, "" + session_id, false);
        String urlClearingMoviesFromList = facadeURL.construirURLFaca();


        Response response = when().delete(urlClearingMoviesFromList);

        if (CommonAsserts.expectedNumberAssertWithPath(response, "status_code", 11)) {
            idListToAdd = "";
        }

        return response;

    }
}
