package baseline;

import java.util.ArrayList;
//removes an item from the arrayList
public class removeItems {
    private ArrayList<String> newTodoList = new ArrayList<>();

    public ArrayList<String> removeTask(String value,ArrayList<String> list){
        //deletes the information that is contained at that index
        list.remove(value);
        //returns updated List
        return newTodoList;
    }

}