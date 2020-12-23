package day04;

import groovy.json.JsonOutput;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class SpartanAddingUpdatingTest {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://blabla";
        basePath = "/api" ;

    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Testing GET/api/spartans with Basic auth")
    @Test
    public void testAllSpartanWithBasicAuth(){

        given()
                .log().all()
                .auth().basic("admin", "admin").
        when()
                .get("/spartans").
        then()
                .log().all()
                .statusCode(200);
    }
}
