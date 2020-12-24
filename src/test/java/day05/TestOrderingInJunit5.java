package day05;
//by default the test is running on alphabetical order
//or the test method name
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestOrderingInJunit5 {

    @Order(3)
    @Test
    public void testA(){
        System.out.println("running test A");
    }

    @Order(1)
    @Test
    public void testC(){
        System.out.println("running test C");
    }

    @Order(4)
    @Test
    public void testD(){
        System.out.println("running test D");
    }

    @Order(2)
    @Test
    public void testB(){
        System.out.println("running test B");
    }
//
}
