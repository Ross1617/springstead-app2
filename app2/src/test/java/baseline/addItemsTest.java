package baseline;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class addItemsTest {
        //create a new test
        @Test
        public void addItemsTest(){
            addItems add = new addItems();
            // create an expected arrayList
            String expected = "a-aaa-aaa-aaa" +"\t"+"Apple" + "\t" +"$20";
            ArrayList<String> expectedArrayList = new ArrayList<>();
            ArrayList<String> actualArrayList = new ArrayList<>();
            expectedArrayList.add(expected);
            //pass information into it
            //call add function with parameters
            actualArrayList = add.addTask("a-aaa-aaa-aaa", "Apple", "$20",actualArrayList);
            //compare to see if they are the same
            assertEquals(expectedArrayList,actualArrayList);
        }
    }





