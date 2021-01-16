package day09;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class JUnit5_ParameterizedTest {

    @ParameterizedTest
    @ValueSource(ints = {5,6,7,8,9})
    public void test1(int myNumber ){

        System.out.println("myNumber = " + myNumber);
        //assert 5,6,7,8,9 all less than 10
        assertTrue(myNumber < 10);

    }

    //using CSV(comma separated values) file as source for parametrized
    @ParameterizedTest
    @CsvFileSource(resources = "/zipcode.csv", numLinesToSkip = 1)//we use numLinesToSkip = 1 to skip the header
    public void test2 (String zip){

        System.out.println("zip = " + zip);

        //sending request to zipcode endpoint here
        //api.zippopotam.us/us/87112
        //api.zippopotam.us/us/{zipcode}
        //baseurl: api.zippopotam.us
        //endpoint is : /us/{zipcode}

        given()
                .log().uri()
                .baseUri("https://api.zippopotam.us")
                .pathParam("zipcode", zip).
        when()
                .get("/us/{zipcode}").
        then()
                .statusCode(200);

        //restAssured test you did above

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/country_zipcode.csv", numLinesToSkip = 1)//we use numLinesToSkip = 1 to skip the header
    public void testCountryZipCodeCombo (String csvCountry , int csvZip){

        given()
                .log().uri()
                .baseUri("https://api.zippopotam.us")
                .pathParam("country", csvCountry)
                .pathParam ("zipcode", csvZip).
        when()
                .get("/{country}/{zipcode}").
        then()
                .statusCode(200);
    }


}
