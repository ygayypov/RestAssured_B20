package day05;

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

public class ExtractPractice {

    /*
    extract() method of RestAssured enable you to extract data after validation in then section of the method chaining
     */

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
    public void testSearchAndExtractData(){

    }


}
