package day01;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import static org.hamcrest.MatcherAssert.assertThat;

public class RestAssuredIntro {

    @DisplayName("Spartan / api / hello Endpoint Test")
    @Test
    public void TestHello(){

        //This is the public ip Akbar shared for spartan2 use it if you do not have your own
        //if you have your own, use your own IP
        //http://100.26.101.158:8000/api/hello
        //Response response = get("http://100.26.101.158:8000/api/hello");

        //make sure this is what's imported for data type Response
        //import io.restassured.response.Response;



        Response response = get("http://100.26.101.158:8000/api/hello");
        //get status code out of this Response object
        System.out.println("status code is : " + response.statusCode());

        //assert the status code is 200
        assertThat(response.statusCode(), is (200));

        /*
        //assertThat(response.statusCode(), is (201));// to see a fail in the method
            java.lang.AssertionError:
            Expected: is <201>
                 but: was <200>
            Expected :is <201>
            Actual   :<200>
         */

        //how to pretty print entire response body(payload)
        // prettyPrint() -- print and return body(payload) as String
        String payload = response.prettyPrint(); // Hello from Sparta
        //assertThat the body is Hello from Spartan

        assertThat(payload, is("Hello from Sparta"));

        //There are always multiple way to same thing in RestAssured
        //get the header called ContentType from the response
        System.out.println("Content - Type from getHeader is : " + response.getHeader("Content-Type"));
        System.out.println("Content - Type from getContentType is : " + response.getContentType());
        System.out.println("Content - Type from contentType is : " + response.contentType());

        //assertThat Content - Type is text/plain;charset=UTF-8
        assertThat(response.contentType(), is ("text/plain;charset=UTF-8"));

        //assertThat Content - Type startWith text
        assertThat(response.contentType(), startsWith("text"));

        //Easy way to work with Content - Type without typing much
        //we can use ContentType Enum from RestAssured to easily get main part content-type
        //ContentType.TEXT -->> text/plain as Enum
        //startWith accept a String object
        //so use toString method to turn ContentType.TEXT toString so we can use it with startWith
        assertThat(response.contentType(), startsWith (ContentType.TEXT.toString()));
        assertThat(response.contentType(), is (not(ContentType.JSON)));





    }



}
