package baseline;

import java.util.ArrayList;
//should add Items to the arrayList
public class addItems {
    //create a new array list
    ArrayList<String> newInventory = new ArrayList<>();
    public ArrayList<String> addTask(String socialNumber, String product, String value, ArrayList<String> inventory){
        // gets the information in the date field and saves it
        // gets the information in the description and saves it
        // gets the information in the completed and saves it
        //add all three to items
        String newRow = socialNumber + "\t" + product + "\t" +value;
        //adds the row
        inventory.add(newRow);
        //returns the new ArrayList

        return inventory;
    }
}
