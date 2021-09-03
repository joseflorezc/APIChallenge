package utils.steps;

import io.restassured.response.Response;
import utils.fachadaURL.FacadeURL;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class MoviesSteps extends HooksMovies {

    public static Response obtainingMovieDetail(String apiKey, String domain, String idMovieDetail) {

        FacadeURL facadeURL = new FacadeURL("" + domain, "", "movie", "" + idMovieDetail, "", false, false, "" + apiKey, "", false);

        String url = facadeURL.construirURLFaca();
        Response response = when().get(url);

        return response;

    }

    public static Response ratingAMovie(String apiKey, String domain, String idMovieDetail) {


        FacadeURL facadeURL = new FacadeURL("" + domain, "", "movie", "" + idMovieDetail, "rating", false, false, "" + apiKey, ""+session_id, false);

        String url = facadeURL.construirURLFaca();
        String jsonRatingAMovie = "{\n" +
                "\"value\": \"" + 8.5 + "\"\n}";
        Response response = given().contentType("application/json;charset=utf-8").body(jsonRatingAMovie).when().post(url);

        return response;

    }
}
