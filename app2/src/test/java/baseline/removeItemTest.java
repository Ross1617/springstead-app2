package baseline;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class removeItemTest {
    //create a new instance of removeItems
    @Test
    public void removeItems_test(){
        removeItems remove = new removeItems();
        //create an expected array list
        ArrayList<String> expected = new ArrayList<>();
        ArrayList <String> actual = new ArrayList<>();
        String expected1 = "a-212-211-212"+"\t" +"Bike"+"\t"+"$21";
        String expected2 = "a-212-aaa-212"+"\t" +"Car"+"\t"+"$2321";
        String expected3 = "a-ccc-211-212"+"\t" +"Tree"+"\t"+"$21.43";

        //adding elements
        expected.add(expected1);
        expected.add(expected3);
        actual.add(expected1);
        actual.add(expected2);
        actual.add(expected3);
        //call removeTask with a parameter
        remove.removeTask("a-212-aaa-212"+"\t" +"Car"+"\t"+"$2321",actual);
        //assign the return to an array list
        //compare the two array Lists
        assertEquals(actual,expected);

    }

}
