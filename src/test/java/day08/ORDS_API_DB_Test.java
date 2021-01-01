package day08;


import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Region;
import testbase.HR_ORDS_TestBase;
import utility.DB_Utility;

import java.util.Map;


public class ORDS_API_DB_Test extends HR_ORDS_TestBase {

    @DisplayName("Testing the connection with query")
    @Test
    public void testDB_Connection(){
        DB_Utility.runQuery( "SELECT * FROM REGIONS");
        DB_Utility.displayAllData();
    }

    /**
     * Send an GET /regions/{region_id} request with region_ of 3
     * check status code is 200
     * save it as Region POJO after status check
     * Get your expected result from Database query
     * SELECT * FROM REGIONS WHERE REGION_ID = 3(myID)
     * SAVE THE THIRD ROW AS A MAP
     * Verify the data from response match the data from Database
     */
    @DisplayName("Testing GET /regions/{region_id} DATA Match Database DATA")
    @Test
    public void testRegionDataFromResponseMatchDB_Data(){

        int myID =  3;
        Response response = given()
                                    .pathParam("region_id" , myID ).
                            when()
                                    .get("regions/{region_id}").
                            then()
                                    .log().all()
                                    .statusCode(is(200))
                                 .extract()
                                    .response();

        Region r3 = response.as(Region.class);
        System.out.println("r3 = " + r3);//POJO result

        DB_Utility.runQuery("SELECT * FROM REGIONS WHERE REGION_ID = " + myID);
        Map<String , String> expectedResultMap = DB_Utility.getRowMap(1);

        System.out.println("expectedResultMap = " + expectedResultMap);//Map result
        //verify the actual result from API response match expected database reslut
        assertThat(r3.getRegion_id() + "" , is(expectedResultMap.get("REGION_ID")));
        assertThat(r3.getRegion_name() , equalTo(expectedResultMap.get("REGION_NAME")));
    }

    @DisplayName("Testing GET /regions/{region_id} DATA Match Database DATA with Both MAPS")
    @Test
    public void testRegionDataFromResponseMatchDB_Data2(){

        int myID =  3;
        JsonPath jp  =      given()
                                    .pathParam("region_id" , myID ).
                            when()
                                    .get("regions/{region_id}").
                            then()
                                    .log().all()
                                    .statusCode(is(200))
                                .extract()
                                    .jsonPath();


        //save the response json as a Map object
        //Here we are calling the overloaded version of getMap method with 3 params
        //1. jsonPath String
        //2. Data type MAp key
        //3. Data type Map value
        //so we can make sure we get exactly what we asked for
        Map<String, String> actualResultMap = jp.getMap("", String.class , String.class);
        //actualResultMap.remove("links");
        //do not need to remove extra links from json result
        //because we are checking key value pair, anything we do not check will not matter
        System.out.println("actualResultMap = " + actualResultMap);


        DB_Utility.runQuery("SELECT * FROM REGIONS WHERE REGION_ID = " + myID);
        Map<String , String> expectedResultMap = DB_Utility.getRowMap(1);

        //since the keyName is different in both map we cannot compare directly
        //map to map object
        //we have to compare the value of the key step by step
        assertThat(actualResultMap.get("region_id"), equalTo(expectedResultMap.get("REGION_ID")));
        assertThat(actualResultMap.get("region_name"), equalTo(expectedResultMap.get("REGION_NAME")));
    }

    @DisplayName("Testing GET /regions/{region_id} DATA Match Database DATA with Just value by value")
    @Test
    public void testRegionDataFromResponseMatchDB_Data3() {

        int myID = 3;
        JsonPath jp = given()
                                .pathParam("region_id", myID).
                      when()
                                .get("regions/{region_id}").
                      then()
                                .log().all()
                                .statusCode(is(200))
                            .extract()
                                .jsonPath();



        String actualRegionId = jp.getString("region_id");
        String actualRegionName = jp.getString("region_name");

        DB_Utility.runQuery("SELECT * FROM REGION_ID, REGION_NAME WHERE REGIONS_ID = " + myID);
        String expectedRegionId = DB_Utility.getColumnDataAtRow(1,"REGION_ID");
        String expectedRegionName = DB_Utility.getColumnDataAtRow(1,"REGION_NAME");

        assertThat(actualRegionId , is(expectedRegionId));
        assertThat(actualRegionName , equalTo(expectedRegionName));
    }
}
