package day07;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import pojo.BookCategory;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


@TestMethodOrder(MethodOrderer.DisplayName.class)
public class LibraryAppTest {

    private static String myToken;

    @BeforeAll
    @Test
    public static void setUp(){
        baseURI = "http://library1.cybertekschool.com";
        basePath = "/rest/v1";

        //myToken = someUtility.getToke()
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }


    @DisplayName("1. Testing POST /login Endpoint")
    @Test
    public void testLogin(){
        /*
        Librarian1   username(email)   librarian69@library
        Librarian1   password          KNPXrm3S
         */

         myToken =
                given()
                        .log().all()
                        .contentType(ContentType.URLENC)
                        .formParam("email", "librarian69@library")
                        .formParam("password", "KNPXrm3S").
                when()
                        .post("/login").
                then()
                        .log().all()
                        .assertThat()
                        .statusCode(is(200))
                        .contentType(ContentType.JSON)
                        .body("token" , is(not(emptyString())))
                        .body("redirect_uri", not(emptyString()))
                    .extract()
                         .jsonPath()
                         .getString("token")
                        ;

        System.out.println("myToken = " + myToken);

        //authentication tells the system who you are
        //authorization according who you are things you can do


        //How to extract some data out of response object after doing validation in then section without
        // breaking the chain -->> use extract() method that return

    }

    @DisplayName("2. Testing GET /dashboard_stats Endpoint")
    @Test
    public void testzDashboard_stats(){
        // z before your nameMethod gives an order in testing
        //you can use it zTestName or testzName

        //this is how we provide header -->> header("headerName", "headerValue")
        given()
                .log().all()
                .header("x-library-token", myToken).
        when()
                .get("dashboard_stats")
        .then()
                .log().all()
                .assertThat()
                .statusCode(is(200))
                .contentType(ContentType.JSON);

        //create a utility class LibraryUtility
        //create a static method called getToken(environment, username, password)

    }


    @DisplayName("3. Save the result of Get Dashboard Stat as Map Object")
    @Test
    public void testGetDashboardStatAsMap(){
//        {
//    "book_count": "1171",
//    "borrowed_books": "653",
//    "users": "7438"
//     }

        JsonPath jp = given()
                                .log().all()
                                .header("x-library-token", myToken)
                      .when()
                                .get("/dashboard_stats")
                                .jsonPath();
        //Get the Response as a map and print it out
        Map<String , Object> responseJsonAsMap = jp.getMap("");
        System.out.println("responseJsonAsMap = " + responseJsonAsMap);


    }


    @DisplayName("4. Save / get_book_categories response as POJO")
    @Test
    public void testGetBookCategoriesAsPOJO(){

        JsonPath jp = given()
                            .log().all()
                            .header("x-library-token", myToken).
                      when()
                            .get("/get_book_categories")
                            .prettyPeek()
                            .jsonPath();


        List<BookCategory> allCategories = jp.getList("", BookCategory.class);
        // I use BookCategory.class to make explicitly obvious of my result
        allCategories.forEach(System.out::println);

    }

}
