package tests;

import org.hamcrest.Matchers;
import org.testng.Assert;
import utils.fachadaURL.FacadeURL;
import utils.steps.CommonAsserts;
import utils.steps.HooksList;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.steps.ListsSteps;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Lists extends HooksList {


    @Test
    public void obtainingListDetail() {
        Response response = ListsSteps.obtainingListDetail(apiKey, domain, idListDetail);

        Assert.assertTrue(CommonAsserts.expectedStringAssertWithPath(response,"created_by", "joseflorez"), "Error in created by of the list detail");
        Assert.assertTrue(CommonAsserts.expectedStringAssertWithPath(response,"id", idListDetail),"Error in the id of the list detail");
    }

    @Test
    public void creatingListOfMovies() {

        Response response = ListsSteps.creatingListOfMovies(apiKey,domain,session_id,name,description,language);

        Assert.assertTrue(CommonAsserts.successAssert(response),"The success message of creating a list was not successful");
        CommonAsserts.notNullAssertWithPath(response, "list_id", "The list id of creating a list appears to be null");

    }

    @Test
    public void addingMovieToList() {

        Response response = ListsSteps.addingMovieToList(apiKey,domain,session_id,name,description,language);

        Assert.assertTrue(CommonAsserts.successAssert(response), "The success message of adding movie to list was not successful");
        Assert.assertTrue(CommonAsserts.expectedNumberAssertWithPath(response, "status_code", 12),"The status code of adding movie to list was not successful");
        Assert.assertTrue(CommonAsserts.expectedStringAssertWithPath(response, "status_message",
                "The item/record was updated successfully."),"The status message of adding movie to list was not successful");


    }

    @Test
    public void clearingMoviesFromList() {
        Response response = ListsSteps.clearingMoviesFromList(apiKey,domain,session_id,name,description,language);

        Assert.assertTrue(CommonAsserts.successAssert(response), "The success message of clearing movies of list was not successful");

        Assert.assertTrue(CommonAsserts.expectedNumberAssertWithPath(response, "status_code", 12),
                "The status code of clearing movies of list was not successful");
        Assert.assertTrue(CommonAsserts.expectedStringAssertWithPath(response, "status_message",
                "The item/record was updated successfully."), "The status code of clearing movies of list was not successful");
    }

    @Test
    public void deletingListWithID() {

        Response response = ListsSteps.deletingListWithID(apiKey,domain,session_id,name,description,language);

        Assert.assertTrue(CommonAsserts.expectedNumberAssertWithPath(response, "status_code", 11),
                "The status code of clearing movies of list was not successful");
        Assert.assertTrue(CommonAsserts.expectedStringAssertWithPath(response, "status_message",
                "Internal error: Something went wrong, contact TMDb."), "The status code of clearing movies of list was not successful");
    }

}
