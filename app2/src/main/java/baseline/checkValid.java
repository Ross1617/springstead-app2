package baseline;

import java.util.ArrayList;
import java.lang.*;

//should compare a word to the list to see if it in the list already
public class checkValid {
    public String checkValid(String number, String name, String value, ArrayList<String> list) {
        //check to see if the text is valid

        int sizeOfArrayList = list.size();
        System.out.printf("Size of array %s",sizeOfArrayList);
        //if the number is wrong
        String text = checkSocialNumber(number);
        //return a string
        if (name.length()>256 || name.length()<2){
            text += "Name length not correct, ";
        }
        for(int i=0; i <sizeOfArrayList; i++){
            String word = list.get(i);
            String words[]= word.split("_");
            String listNumber = words[0];
            String listName = words[1];
            String listValue = words[2];
            System.out.printf("listNumber %s, number %s",listNumber, number);
            if(listNumber.equals(number)){
                text += "SocialNumber already in the table!";
            }
        }
        try{
            double val = Double.parseDouble(value);
        }

        catch (NumberFormatException ex){
            text += "Value is not in USD";
        }




        //else if the number is used already
        //return a different error message
        //else
        //return a different error message
        return text;
    }

    public String checkSocialNumber(String number) {
        int lengthOfWord = number.length();
        if (lengthOfWord != 13) {
            return "Invalid input for Serial Number length error";
        } else {
            char char1 = number.charAt(1);
            char char2 = number.charAt(5);
            char char3 = number.charAt(9);
            if (char1 != '-'){
                return "Invalid input - in the wrong place, ";
            }
            if (char2 != '-'){
                return "Invalid input - in the wrong place, ";
            }
            if (char3 != '-'){
                return "Invalid input - in the wrong place, ";
            }
            for (int i = 0; i < 13; i++) {
                if (i == 0) {
                    if (!Character.isLetter(number.charAt(0))) {
                        return "Invalid input for Serial Number, ";
                    }
                }
                if (!(i == 1 || i == 5 || i == 9)) {
                    if (!(Character.isLetter(number.charAt(i)) || Character.isDigit(number.charAt(i)))) {
                        return "Invalid input number or letter not in correct place, ";
                    }
                }
            }
        }
        return "";
    }

}
