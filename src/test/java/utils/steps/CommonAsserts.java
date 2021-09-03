package utils.steps;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;

public class CommonAsserts {

    public static Boolean successAssert(Response response){
       return response.then().extract().path("success").equals(true);
    }
    public static Boolean nullAssertWithPath(Response response, String path){
        return response.then().extract().path(path).equals(null);
    }

    public static Boolean expectedStringAssertWithPath(Response response, String path, String expected){
        return response.then().extract().path(path).equals(expected);
    }
    public static Boolean expectedNumberAssertWithPath(Response response, String path, int expected){
        return response.then().extract().path(path).equals(expected);
    }

    public static void notNullAssertWithPath(Response response, String path, String message){
        Assert.assertNotNull(response.then().extract().path(path), message);
    }


}
