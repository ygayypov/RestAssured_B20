package testbase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import utility.DB_Utility;

import static io.restassured.RestAssured.*;

public abstract class HR_ORDS_TestBase {
    //http://54.90.101.103:1000/ords/hr/regions/3
    @BeforeAll
    @Test
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("ords.baseURI");
        basePath = ConfigurationReader.getProperty("ords.basePath");

        //create DB Connection here
        DB_Utility.createConnection(ConfigurationReader.getProperty("hr.database.url"),
                                    ConfigurationReader.getProperty("hr.database.username"),
                                    ConfigurationReader.getProperty("hr.database.password"));

    }

    @AfterAll
    public static void tearDown(){
        reset();
        //Destroy DB Connection here
        DB_Utility.destroy();
    }

}
