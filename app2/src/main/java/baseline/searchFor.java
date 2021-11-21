package baseline;

import java.util.ArrayList;
//searches for the word and if it is there returns a desired output
public class searchFor {
    public ArrayList<String> searchForStuff(ArrayList<String> list, String word, int number){
        //return a blank string if it is not found
        ArrayList<String> newArray = new ArrayList<String>();
        int lengthOfArray = list.size();
        //parse through the array list
        for (int i =0 ; i< lengthOfArray; i++){
            String old =  list.get(i);
            String words[] = old.split("\t");
            String serialNumber = words[0];
            String name = words[1];
            String value = words[2];
            //check to see if the word is in the array List by using a loop
            //1 is a search for the serial number
            if ((number ==1)&& serialNumber.equals(word)){
                //if it is found then create a new String with the information
                String newLine = serialNumber + "\t" + name + "\t" + value;
                newArray.add(newLine);

            }
            //2 is a search for a name search
            if ((number ==2) && (name.equals(word))){
                //if it is found then create a new String with the information
                String newLine = serialNumber + "\t" + name + "\t" + value;
                newArray.add(newLine);
            }

        }
        // return the new string
        return newArray;
    }
}
