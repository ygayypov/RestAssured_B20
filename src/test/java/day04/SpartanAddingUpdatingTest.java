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
        baseURI = "blabla";
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

    @DisplayName("Add 1 Data with Raw Json String POST / api/ spartans")
    @Test
    public void testAddOneData(){

        /*
        {
        "name": "Roma",
        "gender": "Male",
        "phone": 1234567890
         }
         */

        String newSpartanStr = "  {\n" +
                               "        \"name\": \"Roma\",\n" +
                               "        \"gender\": \"Male\",\n" +
                               "        \"phone\": 1234567890\n" +
                               "   }" ;

        System.out.println("newSpartanStr = " + newSpartanStr);

        given()
                .log().all()
                .auth().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(newSpartanStr).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(201) ;
    }

}
