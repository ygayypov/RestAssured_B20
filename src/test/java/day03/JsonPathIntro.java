package day03;

import groovy.json.JsonOutput;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JsonPathIntro {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://blabla";
        basePath = "/api" ;

    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Extracting data out of Spartan Json Object")
    @Test
    public void test1SpartanPayload(){
        //send a request to get one spartan
        //by providing path params with valid id
        //save it into Response object
        //NEW: create an object with type JsonPath
        //by calling the method jsonPath() an response object
        //extract id, name , gender, phone
        //and save it into variable of correct type

        Response response = given()
                                    .pathParam("id", 267).
                            when()
                                    .get("/spartans/{id}")
                                    .prettyPeek(); // returns response  whole

        //response.prettyPrint(); // return String knowledge inside {}

        //JsonPath is used to navigate through the json payload
        //and extract the value according to the valid "jsonpath" provided
        JsonPath jp = response.jsonPath();
        int myId = jp.getInt("id");
        String myName = jp.getString("name");
        String myGender = jp.getString("gender");
        long myPhone = jp.getLong("phone");

        System.out.println();
        System.out.println("===========================================================");
        System.out.println("myId = " + myId);
        System.out.println("myName = " + myName);
        System.out.println("myGender = " + myGender);
        System.out.println("myPhone = " + myPhone);

    }

    @DisplayName("Extracting data from Json Array REsponse ")
    @Test
    public void getAllSpartansExtractData(){
        /*
         //Response response = get("/spartans");
        //JsonPath jp = response.jsonPath();
        // you can do in one line what written above, you can find below
         */
        JsonPath jp = get("/spartans").jsonPath();

        // get the first json object name field and phone field
        System.out.println("jp.getString(\"name[0]\") = " + jp.getString("name[0]"));
        System.out.println("jp.getLong(\"phone[0]\") = " + jp.getLong("phone[0]"));

        // get the 7th json object gender field from json array
        System.out.println("jp.getString(\"gender[6]\") = " + jp.getString("gender[6]"));

        //getting all the name fields from the jsonArray Response
        //and storing as a list
        List<String> allNames = jp.getList("name");
        System.out.println("allNames = " + allNames);

        //getting all the phone fields from the jsonArray Response
        //and storing as a list
        List<Long> allPhones = jp.getList("phone");
        System.out.println("allPhones = " + allPhones);

    }

    //send request to this request url
    //http://3.86.188.174:8000/api/spartans/search?nameContains=de&gender=Male
    //get the name of first guy in the result
    //get the phone of the 2nd guy in the result
    //get all names, all phones save them as list
    //save the value of field called empty under pageable in the response
    @DisplayName("Testing / spartans/search and extracting data ")
    @Test
    public void testSearch(){
    JsonPath jp =   given()
                            .queryParam("nameContains", "de")
                            .queryParam("gender", "Male").
                    when()
                            .get("/spartans/search").
                            jsonPath();

        System.out.println();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("First guy's name : " + jp.getString("content[0].name"));
        System.out.println("Second guy's phone : " + jp.getLong("content[1].phone"));


        System.out.println("allNames = " + jp.getList("content.name"));
        System.out.println("allPhones = " + jp.getList("content.phone"));

        System.out.println("Value of the field empty : " + jp.getBoolean("pageable.sort.empty"));

    }

}
