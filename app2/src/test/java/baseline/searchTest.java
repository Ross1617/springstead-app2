package baseline;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class searchTest {
    @Test
    public void search_Test(){
        searchFor search = new searchFor();
        //create an expected array list
        ArrayList<String> expected = new ArrayList<>();
        ArrayList <String> actual = new ArrayList<>();
        ArrayList <String> actual2 = new ArrayList<>();
        String expected1 = "a-212-211-212"+"\t" +"Bike"+"\t"+"$21";
        String expected2 = "a-212-aaa-212"+"\t" +"Car"+"\t"+"$2321";
        String expected3 = "a-ccc-211-212"+"\t" +"Tree"+"\t"+"$21.43";

        //adding elements
        expected.add(expected1);
        actual.add(expected1);
        actual.add(expected2);
        actual.add(expected3);
        actual2.add(expected1);
        actual2.add(expected2);
        actual2.add(expected3);
        //call removeTask with a parameter
        actual = search.searchForStuff(actual,"a-212-211-212",1);
        actual2 = search.searchForStuff(actual,"Bike",2);
        //assign the return to an array list
        //compare the two array Lists
        assertEquals(actual,expected);
        assertEquals(expected,actual2);
    }

}
