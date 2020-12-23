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


public class LibraryAppTest {

    @BeforeAll
    @Test
    public static void setUp(){
        baseURI = "http://library1.cybertekschool.com";
        basePath = "/rest/v1";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

}
