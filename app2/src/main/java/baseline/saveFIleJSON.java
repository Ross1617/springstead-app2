package baseline;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class saveFIleJSON {
    private FileWriter file;
    public void fileSave( ArrayList<String> list) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("enterName.json");
        File fileMaker = fileChooser.showSaveDialog(new Stage());
        file = new FileWriter(fileMaker);
        int lines = list.size();
        JSONArray itemList = new JSONArray();
        for(int i = 0; i < lines; i++){
            //goes through all the information
            String old = list.get(i);
            String words[] = old.split("\t");
            String serialNumber = words[0];
            String name = words[1];
            String value = words[2];
            JSONObject Item = new JSONObject();
            JSONObject obj = new JSONObject();
            //adds everything with a key
            Item.put("Serial Number",serialNumber);
            Item.put("Name",name);
            Item.put("Value",value);
            obj.put("Item", Item);
            itemList.add(obj);
        }
        try{
            //writes it then closes
            file.write(itemList.toJSONString());
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
