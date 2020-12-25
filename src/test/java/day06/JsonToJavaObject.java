package day06;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import pojo.Spartan;
import utility.ConfigurationReader;
import utility.SpartanUtil;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JsonToJavaObject {//

    @BeforeAll
    public static void setUp(){
        //RestAssured.filters().add(new AllureRestAssured());
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Grt 1 data with Save Response Json As Java Object")
    @Test
    public void testGetOneSpartanAndSaveResponseJsonAsMap(){


        Response response =    given()
                                        .auth().basic("admin" , "admin")
                                        .log().all()
                                        .pathParam("id" , 937).
                                when()
                                        .get("/spartans/{id}").prettyPeek();


        // get JsonPath object
        JsonPath jp = response.jsonPath();
        Map<String ,Object> responseMap = jp.getMap("");
        System.out.println("responseMap = " + responseMap);

        /**
         * {
         *     "id": 937,
         *     "name": "Gerald",
         *     "gender": "Female",
         *     "phone": 6075183602
         * }
         *
         * JsonPath to get whole json object is just empty string
         *
         * assume this is a car object
         * {
         *      "make" : "Toyota"
         *      "color" : "purple"
         *      "engine" : {
         *                    "type" : "Avalon"
         *                    "horsepower" :
         *                 }
         *
         *}
         * ==> important
         * JsonPath for horsepower -->> engine.horsepower
         * JsonPath for engine itself -->> engine
         * JsonPath for entire car JsonObject -->> ""
         *
         */

    }


}
