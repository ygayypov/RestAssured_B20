package day06;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import pojo.Spartan;
import pojo.SpartanRead;
import utility.ConfigurationReader;
import utility.SpartanUtil;

import java.util.List;
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

    @DisplayName("Get 1 data and Save Response Json As Java Object")
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

        //Java to Json ===>>> Serialization
        //Json to Java ===>>> De-Serialization


        SpartanRead sr = jp.getObject("", SpartanRead.class);
        System.out.println("sr = " + sr);

        //jp.get("jsonPathHere or Empty") will return the type
        //specified in variable data type , similar to list <Type>
//        SpartanRead sp2 = jp.get("");
//        System.out.println("sp2 = " + sp2);


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


    @DisplayName("Get All data and Save Response JsonArray As Java Object")
    @Test
    public void testGetAllSpartanAndSaveResponseJsonAsJavaObject(){


        Response response = given()
                                    .auth().basic("admin", "admin").
                            when()
                                    .get(("/spartans"));

        JsonPath jp = response.jsonPath();
        List<SpartanRead> allSpartansPOJOs = jp.getList("", SpartanRead.class);
        System.out.println("allSpartansPOJOs = " + allSpartansPOJOs);
        allSpartansPOJOs.forEach((System.out::println));


    }

    //send the request to /api/spartans/search endpoint
    //save your JsonArray from search result into
    //List of SpartanRead POJO




}
