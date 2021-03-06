package day01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

//Hamcrest assertion library already part of our RestAssured Dependency in pom file, no separate dependency needed
public class HamcrestMatchersTest { // Java Hamcrest Matchers that can be combined to create flexible expressions of intent

    @DisplayName("Test 1 + 3 is 4")
    @Test
    public void test1() {

        assertThat(1 + 3, is(4));

        assertThat(1 + 3, equalTo(4));

        //add some nice error message if it fails
        //assertThat("Wrong Result!!!", 1 + 3, equalTo(5));

        //test 1+3 is not 5
        //we can nest a matcher inside another matcher
        assertThat(1 + 3, is(not(5)));
        assertThat(1 + 3, not(5));
        assertThat(1 + 3, not(equalTo(5)));
        assertThat(1 + 3, is(not(equalTo(5))));

        // test 1 + 3 is less than 5
        assertThat(1 + 3, lessThan(5));

        // test 1 + 3 is more than 2
        assertThat(1 + 3, greaterThan(2));

        // test 4 + 3 is greater than or equal to 7
        assertThat(4 + 3, greaterThanOrEqualTo(7));
    }


        @DisplayName("Common Matchers for String")
        @Test

        public void testString(){

            String str = "Rest Assured is cool so far";

            //assert the str is "Rest Assured is cool so far"
            assertThat(str, is("Rest Assured is cool so far"));

            //assert the str is "Rest Assured IS COOL so far" in case insensitive manner
            assertThat(str, equalToIgnoringCase ("Rest Assured IS COOL so far"));

            //assert the str startWith "Rest"
            assertThat(str, startsWith("Rest"));

            //assert the str startWith "so far"
            assertThat(str, endsWith("so far"));

            //assert the str contains "is cool"
            assertThat(str, containsString("is cool"));

            //assert the str contains "IS COOL" in case insensitive manner
            assertThat(str, containsStringIgnoringCase("IS COOL"));



        }




}
