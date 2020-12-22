package day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GithubRestAPITest {

    //create a test for testing github rest api users/user endpoint

    @DisplayName("Test GitHub GET /users/{username}")
    @Test
    public void testGitHub(){

        given()
                .pathParam("username", "ygayypov").
        when()
                .get("https://api.github.com/users/{username}").
        then()
                .assertThat()
                .statusCode(is(200));
    }


}
