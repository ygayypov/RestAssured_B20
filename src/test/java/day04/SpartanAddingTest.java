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

public class SpartanAddingTest {

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
                .statusCode(is (200));
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
                .assertThat()
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("Roma"))
                .body("data.gender", is("Male"))
                .body("data.phone", is(1234567890));
    }


    @DisplayName("Add 1 Data with Map Object POST / api/ spartans")
    @Test
    public void testAddOneDataWithMapAsBody(){

        Map<String, Object> payloadMap = new LinkedHashMap<>();
        payloadMap.put("name", "Sergio");
        payloadMap.put("gender", "Male");
        payloadMap.put("phone", 6578946253L);
        System.out.println("payloadMap = " + payloadMap);

        given()
                .auth().basic("admin", "admin")
                .log().all()
                .contentType(ContentType.JSON)
                .body(payloadMap).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("Sergio"))
                .body("data.gender", is("Male"))
                .body("data.phone", is(6578946253L));

    }


    @DisplayName("Add 1 Data with External Json file POST / api/ spartans")
    @Test
    public void testAddOneDataWithJsonFileBody(){
        //create a file called a singleSpartan.json right under root directory
        //with below content
        /*
        {
        "name": "Marta",
        "gender": "Female",
        "phone": 1678365789
         }
         add below code to point File Object to this singleSpartan.json
         */

        File externalJson = new File ("singleSpartan.json");
        given()
                .auth().basic("admin", "admin")
                .log().all()
                .contentType(ContentType.JSON)
                .body(externalJson).
        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("Marta"))
                .body("data.gender", is("Female"))
                .body("data.phone", is(1678365789));
    }


}
