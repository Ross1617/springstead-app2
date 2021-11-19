package baseline;

import java.util.ArrayList;

public class modifyShown {
    public ArrayList<String> modifyArrayList(ArrayList<String> oldArrayList, String newText, String oldText, int number){
        for (int i = 0; i < oldArrayList.size(); i ++){
            if (oldText.equals(oldArrayList.get(i))){
                String old = (String) oldArrayList.get(i);
                String words[] = old.split("_");
                String serialNumber = words[0];
                String name = words[1];
                String value = words[2];
                String newOne = "";
                if (number == 0){
                    newOne = newText + "_" + name + "_" + value;
                }
                if (number == 1 ){
                    newOne = serialNumber + "_" + newText + "_" + value;
                }
                if (number == 2){
                    newOne = serialNumber + "_" + name +"_" + newText;
                }

                //modify the arrayList
                oldArrayList.set(i,newOne);

            }

        }
        //returning the value
        return oldArrayList;
    }
}
