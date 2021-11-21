package baseline;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class editValueTest {
    @Test
    public void test_editNumber(){
        modifyShown mod = new modifyShown();
        //create an expected ArrayList
        String newValue = "$213.21";
        String oldText = "a-aaa-aaa-aaa" + "\t"+"Car" +"\t"+"$21";
        //create another arrayList
        ArrayList<String> expected = new ArrayList<>();
        ArrayList<String> actual = new ArrayList<>();
        //edit the information
        expected.add("a-aaa-aaa-aaa" + "\t"+"Car" +"\t"+"$213.21");
        //assign the return value to  an Array list
        actual.add("a-aaa-aaa-aaa" + "\t"+"Car" +"\t"+"$21");
        actual = mod.modifyArrayList(actual,newValue,oldText,2);
        //compare the two
        assertEquals(actual,expected);
    }
}
