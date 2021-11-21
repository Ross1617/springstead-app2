package baseline;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class editName {
    @Test
    public void test_editName(){
        modifyShown mod = new modifyShown();
        //create an expected ArrayList
        String newName = "Bike";
        String oldText = "a-aaa-aaa-aaa" + "\t"+"Car" +"\t"+"$21";
        //create another arrayList
        ArrayList<String> expected = new ArrayList<>();
        ArrayList<String> actual = new ArrayList<>();
        //edit the information
        expected.add("a-aaa-aaa-aaa" + "\t"+"Bike" +"\t"+"$21");
        //assign the return value to  an Array list
        actual.add("a-aaa-aaa-aaa" + "\t"+"Car" +"\t"+"$21");
        actual = mod.modifyArrayList(actual,newName,oldText,1);
        //compare the two
        assertEquals(actual,expected);
    }



}
