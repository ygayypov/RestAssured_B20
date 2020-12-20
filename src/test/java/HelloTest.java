import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HelloTest {

    //Junit5 Annotations
    //@BeforeAll @AfterAll @BeforeEach @AfterEach

    @BeforeAll
    public static void setUp(){
        System.out.println("@BeforeAll is running");
    }

    @AfterAll
    public static void tearDown(){
        System.out.println("@AfterAll is running");
    }

    @BeforeEach
    public void setUpTest(){
        System.out.println("@BeforeEach is running");
    }

    @AfterEach
    public void tearDownTest(){
        System.out.println("@AfterEach is running");
    }

    @Test
    public void test1(){
        System.out.println("test 1 is running");
        Assertions.assertEquals(4, 1+3);
    }

    @Test
    public void test2(){
        //assert 4*3 = 12
        System.out.println("test 2 is running");
        assertEquals(12, 4*3);
    }

//commit
    //test
}
