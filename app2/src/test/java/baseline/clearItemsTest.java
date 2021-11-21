package baseline;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class clearItemsTest {
    @Test
    public void clearTest(){
        clearEverything clear = new clearEverything();
        //create an expected blank array list
        ArrayList<String> expected = new ArrayList<>();
        //create another array list
        ArrayList<String> actual = new ArrayList<>();
        //add items into the array list
        actual.add("a-212-321-321   Plane   $10");
        actual.add("z-132-321-321   bike    $20");
        actual.add("z-1e2-e21-321   car    $2320");
        //then clear it
        //check to see if they are the same
        actual = clear.clearArrayList();
        assertEquals(actual,expected);
    }




}
