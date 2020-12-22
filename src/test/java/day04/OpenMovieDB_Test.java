package day04;

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

public class OpenMovieDB_Test {

    //http://www.omdbapi.com/?t=The Orville&apiKey=5b5d0fe8
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://www.omdbapi.com";
    }
    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Test search Movie or OpenMovieDB Test")
    @Test
    public void testMovie(){

        given()
                .queryParam("apiKey", "5b5d0fe8")
                .queryParam("t", "The Orville").
        when()
                .get(). // our request URL is already complete , do not need to add anything here
        then()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("Title", is("The Orville"))
                .body("Ratings[0].Source" , is("Internet Movie Database"));

    }


}
