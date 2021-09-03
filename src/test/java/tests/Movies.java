package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.steps.CommonAsserts;
import utils.steps.HooksMovies;
import utils.steps.MoviesSteps;

public class Movies extends HooksMovies {

    @Test
    public void obtainingPeopleDetail() {

        Response response = MoviesSteps.obtainingMovieDetail(apiKey, domain, idMovieDetail);


        CommonAsserts.notNullAssertWithPath(response, "id","The id of the movie can't be null");
    }

    @Test
    public void ratingAMovie(){

        Response response = MoviesSteps.ratingAMovie(apiKey, domain, idMovieDetail);

        Assert.assertTrue(CommonAsserts.successAssert(response), "The success message of rating a movie was not successful");
        Assert.assertTrue(CommonAsserts.expectedNumberAssertWithPath(response, "status_code", 12),
                "The status code of rating a movie was not successful");
        Assert.assertTrue(CommonAsserts.expectedStringAssertWithPath(response, "status_message",
                "The item/record was updated successfully."), "The status code of rating a movie was not successful");
//        Assert.assertTrue(CommonAsserts.expectedNumberAssertWithPath(response, "status_code", 1),
//                "The status code of rating a movie was not successful");
//        Assert.assertTrue(CommonAsserts.expectedStringAssertWithPath(response, "status_message",
//                "Success."), "The status code of rating a movie was not successful");
    }



}
