package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SingleSpartanTest {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://blabla";
        basePath = "/api" ;

    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Testing GET /spartans/{id} endpoint")
    @Test
    public void test1Spartan(){
        //I want to get json result out
        //when I send Get request to /spartans/{id} endpoint
        //and expecting 200 status code
        given()
                .accept(ContentType.JSON).
        when()
                .get("/spartans/267").
        then()
                .statusCode(is (200))
                .contentType(ContentType.JSON) ;

        //I want to make it obvious for the value 100 is path variable/ params to uniquely identify the resource
        //this will be whole Request URL on this test
        // http://3.86.188.174:8000/api/spartans/267
        given()
                .accept(ContentType.JSON)
                .pathParam("id", 267).
        when()
                .get("spartans/{id}").
        then()
                .assertThat()
                .statusCode(is(200))
                .contentType(ContentType.JSON);

        //this is the easiest one, same result
        given()
                .accept(ContentType.JSON).
        when()
                .get("/spartans/{id}", 267).
        then()
                .assertThat()
                .statusCode(is (200))
                .contentType(ContentType.JSON) ;

    }

    @DisplayName("Testing GET /spartans/{id} endpoint Payload")
    @Test
    public void test1SpartanPayload(){
        /**
         * {
         *   "id": 267,
         *   "name": "Ansel",
         *   "gender": "Female",
         *   "phone": 1608180090
         * }
         */

        given()
                .accept(ContentType.JSON).
        when()
                .get("/spartans/{id}", 267).
        then()
                .assertThat()
                .statusCode(is (200))
                .contentType(ContentType.JSON)
                .body("id",is(267) )
                .body("name", equalTo("Ansel"))
                .body("gender", is(equalTo("Female")))
                .body("phone", equalTo(1608180090));
    }



}
