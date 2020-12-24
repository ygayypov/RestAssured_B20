package day05;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestCollectionSupport {

    @Test
    public void testList(){

        List<Integer> numList = Arrays.asList(4,6,7,9,5,88,90);

        //use hamcrest matcher to test the size of this list
        assertThat(numList, hasSize(7));

        //assert that this list contains 9
        assertThat(numList, hasItem(9));

        //assert that this list contains 9, 88
        assertThat(numList, hasItems(9, 88));

        //assert that everyItems in the list are more than 3
        assertThat(numList, everyItem(is(greaterThan(3))));

        List<String> allNames = Arrays.asList("Virginia", "Roary", "Marianna", "Olivia", "Gulbadan", "Ayxamgul", "Kareem", "Fiora", "Tahir");
        assertThat(allNames, hasSize(9));

        assertThat(allNames, hasItems("Virginia", "Ayxamgul", "Roary"));

        //check every items has letter a
        assertThat(allNames, everyItem(containsString("a")));

        //check every items has letter a in case insensitive manner
        assertThat(allNames, everyItem(containsStringIgnoringCase("a")));

        //how to do and or in hamcrest syntax
        //allOf -->> and  logic, all of the matchers should match or it fails
        assertThat("Murat Degirmenci", allOf(startsWith("Mu"), containsString("men")));

        //anyOf -->> or logic as long as one matcher match it will pass
        assertThat("Ramazan Alic", anyOf(is("Ramazan"), endsWith("ic"), startsWith("Re")));




    }


}
