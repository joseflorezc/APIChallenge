package Steps;

import io.restassured.response.Response;

public class CommonAsserts {

    public static Boolean successAssert(Response response){
       return response.then().extract().path("success").equals(true);
    }

}
