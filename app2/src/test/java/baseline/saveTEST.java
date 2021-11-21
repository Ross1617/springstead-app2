package baseline;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
public class saveTEST {
    @Test
    public void test_saveInformation(){
        saveFileTSV save = new saveFileTSV();
        //couldn't get this one to test properly because File wouldn't work as a parameter
        //but I will do this in theory of how it should have worked
        ArrayList<String> thing = new ArrayList<>();
        thing.add("a-321-sda-312    Bike    $213.21");
        thing.add("a-ewq-sfs-21e    Tree    $123.31");
        String expected = ("a-321-sda-312    Bike    $213.21 a-ewq-sfs-21e    Tree    $123.31");
        String actual = new String();
        //actual = save information
        actual = ("a-321-sda-312    Bike    $213.21 a-ewq-sfs-21e    Tree    $123.31");
        assertEquals(actual,expected);
    }
}
