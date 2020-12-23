package day04;

import groovy.json.JsonOutput;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanUpdatingTest {

    @BeforeAll
    public static void setUp(){
        baseURI = "blabla";
        basePath = "/api" ;

    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Testing PUT/api/spartans/{id}")
    @Test
    public void testUpdatingSingleSpartanStringBody(){

        String updateStrPayload = "  {\n" +
                                   "        \"name\": \"Fiona\",\n" +
                                   "        \"gender\": \"Female\",\n" +
                                   "        \"phone\": 1234567555\n" +
                                   "   }" ;

        given()
                .log().all()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .pathParam("id", 691)
                .body(updateStrPayload).
        when()
                .put("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(204))
                //This is how we can check a header exists by checking the value is not null
                //using notNullValue() matcher
                .header("Date", is(notNullValue()))
                .body(emptyString());

    }

    @DisplayName("Testing PATCH/api/spartans/{id} with String body")
    @Test
    public void testPartialUpdatingSingleSpartanStringBody(){


        //update the name to Marianna
        //{"name" : "Marianna"}
        String patchBody = "{\"name\" : \"Marianna\"}";
        given()
                .auth().basic("admin","admin")
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id",637)
                .body(patchBody).
        when()
                .patch("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(204))
                .body(emptyString());

    }


}
