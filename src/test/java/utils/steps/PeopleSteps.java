package utils.steps;

import io.restassured.response.Response;
import utils.fachadaURL.FacadeURL;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class PeopleSteps extends HooksPeople {

    public static Response obtainingPeopleDetail(String apiKey, String domain, String idPeopleDetail) {

        FacadeURL facadeURL = new FacadeURL("" + domain, "", "person", "" + idPeopleDetail, "", false, false, "" + apiKey, "", false);

        String url = facadeURL.construirURLFaca();
        Response response = when().get(url);

        return response;

    }

}
