package day07;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import pojo.Spartan;
import utility.ConfigurationReader;
import utility.SpartanUtil;

import java.util.LinkedHashMap;
import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PatchOneSpartanTest {//partial update

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

    @DisplayName("Patching 1 data with Java Object")
    @Test
    public void testPath1DataPartialUpdate(){

        //we just wanted to update the name and phone number

        Map<String , Object> patchBodyMap = new LinkedHashMap<>();
        patchBodyMap.put("name", "Bolajonim");
        patchBodyMap.put("phone", 1233211236L);

        given()
                .auth().basic("admin", "admin")
                .log().all()
                .pathParam("id" , 279)
                .contentType(ContentType.JSON)
                .body(patchBodyMap).
        when()
                .patch("/spartans/{id}").
        then()
                .log().all()
                .statusCode(204);

    }


    @DisplayName("Patching 1 data with POJO")
    @Test
    public void testPath1DataPartialUpdateWithPOJO(){

        //we just wanted to update the name and phone number

        Spartan sp = new Spartan();
        sp.setName("BAYE");
        sp.setPhone(3456984562L);

        //Map is a better option with minimal effort
        //POJO class need some handling to ignore empty values when being serialized
        given()
                .auth().basic("admin", "admin")
                .log().all()
                .pathParam("id" , 280)
                .contentType(ContentType.JSON)
                .body(sp).
        when()
                .patch("/spartans/{id}").
        then()
                .log().all()
                .statusCode(204);

    }


}
