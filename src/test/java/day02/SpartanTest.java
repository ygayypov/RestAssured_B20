package day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SpartanTest {

    @DisplayName("Testing / api/spartans endpoint")
    @Test
    public void testGetAllSpartan(){

        //send a get request to above endpoint
        //save the response
        //print out the result
        //try to assert the status code
        //content type header

        Response response = get ("http://3.86.188.174:8000/api/spartans");
        response.prettyPrint();

        assertThat(response.statusCode(), is (200));
        assertThat(response.contentType(), is(ContentType.JSON.toString()));
        //response.contentType() is String
        //ContentType.JSON is ENUM for that reason we added the toString to it like ContentType.JSON.toString() to make it String


    }


    @DisplayName("Testing / api/spartans endpoint")
    @Test
    public void testGetAllSpartanXML(){



    }
}
