package baseline;

import org.json.simple.JSONObject;

public class openFileJSON {
    private String serialNumber;
    private String value;
    private String name;
    public void parseJSONFile(JSONObject item){
        JSONObject invItem = (JSONObject) item.get("Item");
        serialNumber = (String) invItem.get("Serial Number");
        value = (String) invItem.get("Value");
        name = (String) invItem.get("Name");

    }
    public String getNumber(){
        return serialNumber;
    }
    public String getValue(){
        return value;
    }
    public String getName(){
        return name;
    }



}
