package day05;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AssertingCollectionInTheChain {


    @BeforeAll
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api" ;

    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @DisplayName("Testing GET/api/spartans with Basic auth")
    @Test
    public void testSearchAndExtractData(){

        //search for nameContains : a , gender Female
        //verify statusCode is 200
        //check the size of the result is Some hardcoded number
        //verify all names from the result contains a
        //verify all gender is Female only
        //do it in the chain


                        given()
                                .log().all()
                                .auth().basic("admin", "admin")
                                .queryParam("nameContains", "a")
                                .queryParam("gender", "Female").
                        when()
                                .get("/spartans/search").
                        then()
                                .log().all()
                                .assertThat()
                                .statusCode(is(200))
                                .body("numberOfElements", equalTo(62))
                                .body("content", hasSize(62))
                                .body("content.name" , everyItem(containsStringIgnoringCase("a")))
                                .body("content.gender", everyItem(is("Female")))
                                ;




    }


}
