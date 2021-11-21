package baseline;

import java.util.ArrayList;
//searches for the word and if it is there returns a desired output
public class searchFor {
    public ArrayList<String> searchFor(ArrayList<String> list, String word, int number){
        ArrayList<String> newArray = new ArrayList<String>();
        int lengthOfArray = list.size();
        for (int i =0 ; i< lengthOfArray; i++){
            String old =  list.get(i);
            String words[] = old.split("\t");
            String serialNumber = words[0];
            String name = words[1];
            String value = words[2];
            if (number ==1){
                if(serialNumber.equals(word)){
                    String newLine = serialNumber + "\t" + name + "\t" + value;
                    newArray.add(newLine);
                }
            }
            if (number ==2){
                if(name.equals(word)){
                    String newLine = serialNumber + "\t" + name + "\t" + value;
                    newArray.add(newLine);
                }
            }

        }
        //parse through the array list
        //check to see if the word is in the array List by using a loop
        //return a blank string if it is not found
        //if it is found then create a new String with the information
        // return the new string
        return newArray;
    }
}
