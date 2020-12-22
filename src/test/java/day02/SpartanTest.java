package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SpartanTest {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://3.86.188.174:8000";
        RestAssured.basePath = "/api";
        //baseURI + basePath + whatever you provided in http method like get post
        //for example :
        // get("/spartans") -->> get(baseURI + "/spartans"


    }

    @DisplayName("Testing / api/spartans endpoint")
    @Test
    public void testGetAllSpartan(){

        //send a get request to above endpoint
        //save the response
        //print out the result
        //try to assert the status code
        //content type header

        Response response = get ("/spartans");
        response.prettyPrint();

        assertThat(response.statusCode(), is (200));
        assertThat(response.contentType(), is(ContentType.JSON.toString()));
        //response.contentType() is String
        //ContentType.JSON is ENUM for that reason we added the toString to it like ContentType.JSON.toString() to make it String


    }


    @DisplayName("Testing / api/spartans endpoint")
    @Test
    public void testGetAllSpartanXML(){

        /**
         * given
         *          --- RequestSpecification
         *          used to provide additional information about the request
         *          base url base path
         *          header, query params, path variable, body(payload)
         *          authentication authorization
         *          logging, cookie
         *
         * when
         *          --- this is where you actually send the request with http method
         *          --- like GET POST PUT DELETE ... with the URL
         *          --- we get Response Object after sending the request
         *
         * then
         *          ---ValidatableResponse
         *          ---This is where we can do validation
         *          ---validate status code, header, payload(body), cookie
         *          ---responseTime
          */

        given()
                .header("accept", "application/xml").
        when()
                .get("/spartans").
        then()
                .assertThat() // this is not required, but can be added to make it obvious that this is where we start assertions
                .statusCode(200)
                .and() // this is not required at all, just for readability, it is optional
                .header("Content-Type", "application/xml")
        ;

        //This will do the same exact thing as above in slightly different way
        //since accept header and content type header is so common, RestAssured has good support or
        //those header by providing method directly rather than using header method we use above
        given()
                .accept(ContentType.XML).
        when()
                .get("http://3.86.188.174:8000/api/spartans").
        then()
                .statusCode(is(200))
                .and()
                .contentType(ContentType.XML);








    }
}
