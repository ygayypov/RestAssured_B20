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
        baseURI = "http://3.86.188.174:8000";
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
                .statusCode(is (200));


    }




}
