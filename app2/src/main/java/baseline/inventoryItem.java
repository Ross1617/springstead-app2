package baseline;

import javafx.beans.property.SimpleStringProperty;

//class that creates everything
public class inventoryItem {
    //create simpleString property for value, name, and number
    private SimpleStringProperty serialNumber;
    private SimpleStringProperty value;
    private SimpleStringProperty name;
    public inventoryItem(String number, String name , String value){
    //set each thing to the variables
        this.name = new SimpleStringProperty(name) ;
        this.value = new SimpleStringProperty(value);
        this.serialNumber = new SimpleStringProperty(number);
    }
    public String getNumber(){
        //gets the number and returns it
        return serialNumber.get();

    }
    public void setNumber(String newNumber){
        //check if new number is used
        this.serialNumber = new SimpleStringProperty(newNumber);
        //sets the number
    }
    public String getValue(){
        //gets the value and returns it
        return value.get();

    }
    public void setValue(String newValue){
        //sets the value
        this.value = new SimpleStringProperty(newValue);
    }
    public String getName(){
        //gets the name and returns it
        return name.get();

    }
    public void setName(String newName){
        //sets the name
        this.name = new SimpleStringProperty(newName);
    }
}

