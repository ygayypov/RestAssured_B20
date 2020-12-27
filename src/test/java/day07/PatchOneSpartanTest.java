package day07;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import pojo.Spartan;
import utility.ConfigurationReader;
import utility.SpartanUtil;

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



    }


}
