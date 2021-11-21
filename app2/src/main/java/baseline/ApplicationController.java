package baseline;
/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Ross Springstead
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ApplicationController implements Initializable {
    private ArrayList<String> inventoryList = new ArrayList<>();
    //creates a new instance of TodoListModify
    FileChooser fileChooser = new FileChooser();

    @FXML
    private MenuButton Load;

    @FXML
    private MenuButton Save;

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private TextField errorMessage;

    @FXML
    private MenuItem loadHTML;


    @FXML
    private MenuItem loadJSON;

    @FXML
    private MenuItem loadTSV;

    @FXML
    private Button removeButton;

    @FXML
    private MenuItem saveJSON;

    @FXML
    private MenuItem saveTSV;

    @FXML
    private Button searchButton;

    @FXML
    private TextField serialNumber;

    @FXML
    private TableView<inventoryItem> tableOfValues;

    @FXML
    private TextField nameOfProduct;

    @FXML
    private TextField valueOfProduct;

    @FXML
    private TableColumn<inventoryItem, String> valueColumn;

    @FXML
    private TableColumn<inventoryItem, String> SerialNumberColumn;

    @FXML
    private TableColumn<inventoryItem, String> nameColumn;

    //overriding the initialize function so I can set the table up
    // set each column to be editable
    // get the value, so I can check to see if it is valid or not
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        SerialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("Number"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("Value"));
        valueColumn.setCellFactory(TextFieldTableCell.<inventoryItem>forTableColumn());
        nameColumn.setCellFactory(TextFieldTableCell.<inventoryItem>forTableColumn());
        SerialNumberColumn.setCellFactory(TextFieldTableCell.<inventoryItem>forTableColumn());
        tableOfValues.setItems(inventory);
        //allows to edit the table
        tableOfValues.setEditable(true);
        valueColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<inventoryItem,String> t) -> {
                    modifyShown changes = new modifyShown();
                    //getting the values of the selected row
                    String oldValue = t.getTableView().getItems().get(t.getTablePosition().getRow()).getValue();
                    String oldNumber = t.getTableView().getItems().get(t.getTablePosition().getRow()).getNumber();
                    String oldName = t.getTableView().getItems().get(t.getTablePosition().getRow()).getName();
                    ((inventoryItem)t.getTableView().getItems().get(t.getTablePosition().getRow())).setValue(t.getNewValue());

                    String newValue = t.getTableView().getItems().get(t.getTablePosition().getRow()).getValue();
                    String old = oldNumber+"\t" + oldName + "\t"+ oldValue;
                    //changing the array list
                    checkValid check = new checkValid();
                    String error = check.testValue(newValue);
                    System.out.printf("error %s",error);
                    if (!(error.equals(""))){
                        errorMessage.setText("New Value is not Valid");
                        ((inventoryItem)t.getTableView().getItems().get(t.getTablePosition().getRow())).setValue(oldValue);
                    }
                    else{
                        inventoryList = changes.modifyArrayList(inventoryList, newValue, old, 2);
                        errorMessage.clear();
                    }


                }
        );
        nameColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<inventoryItem,String> t) -> {
                    modifyShown changes = new modifyShown();
                    String oldValue = t.getTableView().getItems().get(t.getTablePosition().getRow()).getValue();
                    String oldNumber = t.getTableView().getItems().get(t.getTablePosition().getRow()).getNumber();
                    String oldName = t.getTableView().getItems().get(t.getTablePosition().getRow()).getName();
                    ((inventoryItem)t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
                    String newName = t.getTableView().getItems().get(t.getTablePosition().getRow()).getName();
                    String old = oldNumber+"\t" + oldName + "\t"+ oldValue;
                    //changing the array list
                    checkValid check = new checkValid();
                    String error = check.testName(newName);
                    if (!(error.equals(""))){
                        errorMessage.setText("New Name is not Valid");
                        ((inventoryItem)t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(oldName);
                    }
                    else{
                        inventoryList = changes.modifyArrayList(inventoryList, newName, old, 1);
                        errorMessage.clear();
                    }

                }
        );
        SerialNumberColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<inventoryItem,String> t) -> {
                    modifyShown changes = new modifyShown();
                    String oldValue = t.getTableView().getItems().get(t.getTablePosition().getRow()).getValue();
                    String oldNumber = t.getTableView().getItems().get(t.getTablePosition().getRow()).getNumber();
                    String oldName = t.getTableView().getItems().get(t.getTablePosition().getRow()).getName();
                    ((inventoryItem)t.getTableView().getItems().get(t.getTablePosition().getRow())).setNumber(t.getNewValue());
                    String newNumber = t.getTableView().getItems().get(t.getTablePosition().getRow()).getNumber();
                    String old = oldNumber+"\t" + oldName + "\t"+ oldValue;
                    //changing the array list
                    checkValid check = new checkValid();
                    String error = check.checkingValid(newNumber,oldName,oldValue,inventoryList);
                    if (!(error.equals(" "))){
                        errorMessage.setText("New SerialNumber is not Valid");
                        ((inventoryItem)t.getTableView().getItems().get(t.getTablePosition().getRow())).setNumber(oldNumber);
                    }
                    else{
                        inventoryList = changes.modifyArrayList(inventoryList, newNumber, old, 0);
                        errorMessage.clear();
                    }
                }
        );
    }


    private ObservableList<inventoryItem> inventory = FXCollections.observableArrayList();



    @FXML
    void LoadTSVClicked(ActionEvent event)throws FileNotFoundException{
        openFileTSV parser = new openFileTSV();
        ArrayList<String> temp = new ArrayList<String>();
        //clears what was left
        addItems add = new addItems();
        tableOfValues.getItems().clear();
        clearEverything clear = new clearEverything();
        inventoryList = clear.clearArrayList();
        File file = fileChooser.showOpenDialog(new Stage());
        //making sure it is a txt file
        if(file.getName().endsWith(".txt")){
            try{
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()){
                    temp.add((scanner.nextLine()));
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            // goes through every line that was just scanned in
            for (int i = 0; i < temp.size(); i++ ){
                parser.parseInformation(temp.get(i));
                String number1 = parser.getNumber();
                String name1 = parser.getName();
                String value1 = parser.getValue();
                checkValid check = new checkValid();
                //making sure the information was correct
                String error = check.checkingValid(number1,name1,value1,inventoryList);
                if (error.equals(" ")) {
                    inventoryList = add.addTask(number1,name1,value1,inventoryList);
                    inventoryItem todoItem = new inventoryItem(number1, name1, value1);
                    tableOfValues.getItems().add(todoItem);
                }
                else {
                    //if there is a valid error in the file then it doesn't update the table, instead leaves error message
                    errorMessage.setText("In proper formatting of the TSV txt file");
                    tableOfValues.getItems().clear();
                    inventoryList = clear.clearArrayList();
                    break;
                }
            }
        }
        else{
            errorMessage.setText("Not a txt File");
        }

    }

    @FXML
    void loadJSONClicked(ActionEvent event) throws IOException {
        openFileJSON parser = new openFileJSON();
        ArrayList<String> temp = new ArrayList<String>();
        //clears what was left
        addItems add = new addItems();
        tableOfValues.getItems().clear();
        clearEverything clear = new clearEverything();
        inventoryList = clear.clearArrayList();
        File file = fileChooser.showOpenDialog(new Stage());
        JSONParser jsonParser = new JSONParser();
        //making sure it is in the correct format
        if(file.getName().endsWith(".json")) {
            try (FileReader reader = new FileReader(file)) {
                Object obj = jsonParser.parse(reader);
                JSONArray item = (JSONArray) obj;
                for (int i = 0; i< item.size(); i++){
                    //parsing through all the information and storing it
                    parser.parseJSONFile((JSONObject) item.get(i));
                    String number1 = parser.getNumber();
                    String name1 = parser.getName();
                    String value1 = parser.getValue();
                    checkValid check = new checkValid();
                    String error = " ";
                    error = check.checkingValid(number1,name1,value1,inventoryList);
                    //making sure there is no errors
                    if (error.equals(" ")) {
                        inventoryList = add.addTask(number1,name1,value1,inventoryList);
                        inventoryItem todoItem = new inventoryItem(number1, name1, value1);
                        tableOfValues.getItems().add(todoItem);
                    }
                    else {
                        //if there is a valid error in the file then it doesn't update the table, instead leaves error message
                        errorMessage.setText("In proper formatting of the JSON txt file");
                        tableOfValues.getItems().clear();
                        inventoryList = clear.clearArrayList();
                        break;
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else{
            errorMessage.setText("Not a JSON File");
        }

    }


    @FXML
    void SaveHTMLClicked(ActionEvent event) throws IOException {
        //open Filechoser
        saveFileHTML file = new saveFileHTML();
        file.saveFile(inventoryList);
        //call the saveFile function with HTMl parameter
    }

    @FXML
    void SaveJSONClicked(ActionEvent event) throws IOException {
        //open Filechoser
        //calls saveFile with the filename
        saveFIleJSON file = new saveFIleJSON();
        file.fileSave(inventoryList);
        //call the saveFile function with TSV parameter

    }

    @FXML
    void SaveTSVCLICKED(ActionEvent event) throws FileNotFoundException {
        //open Filechoser
        //calls saveFile with the filename
        saveFileTSV file = new saveFileTSV();
        fileChooser.setInitialFileName("enterName.txt");
        File fileMaker = fileChooser.showSaveDialog(new Stage());

        file.fileSave(fileMaker,inventoryList);
        //call the saveFile function with TSV parameter
    }

    @FXML
    ArrayList<String> addButtonClicked(ActionEvent event) {
        //get the value of the price, item number, and name
        addItems add = new addItems();
        //check to see if they are valid inputs
        String number, value, name;
        number = serialNumber.getText();
        name = nameOfProduct.getText();
        value = valueOfProduct.getText();
        checkValid check = new checkValid();
        String error = check.checkingValid(number,name,value,inventoryList);
        //check to see if there is any problem with the input
        if (error.equals(" ")){
            inventoryItem item = new inventoryItem(number, name, value);
            tableOfValues.getItems().add(item);
            inventoryList = add.addTask(number,name,value,inventoryList);
            //update the tableViewer
            serialNumber.clear();
            nameOfProduct.clear();
            valueOfProduct.clear();
        }
        else {
            //if there is a problem it displays the error
            errorMessage.setText(error);
        }
        return inventoryList;

    }

    @FXML
    void clearButtonClicked(ActionEvent event) {
        tableOfValues.getItems().clear();
        clearEverything clear = new clearEverything();
        inventoryList = clear.clearArrayList();

    }

    @FXML
    void loadHTMLClicked(ActionEvent event) throws FileNotFoundException {
        addItems add = new addItems();
        //clears what was left
        tableOfValues.getItems().clear();
        clearEverything clear = new clearEverything();
        inventoryList = clear.clearArrayList();
        File file = fileChooser.showOpenDialog(new Stage());
        FileReader fr = new FileReader(file);
        StringBuilder html = new StringBuilder();
        //opening the file only if it is a html
        if(file.getName().endsWith(".html")){
            try{
                //reading the input
                BufferedReader br = new BufferedReader(fr);
                String val;
                while ((val = br.readLine()) != null){
                    html.append(val);
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //if there is a valid error in the file then it doesn't update the table, instead leaves error message
            Document doc = Jsoup.parse(String.valueOf(html));
            org.jsoup.select.Elements rows = doc.select("tr");
            int counter = 0;
            for(org.jsoup.nodes.Element row :rows)
            {
                String words = "";
                org.jsoup.select.Elements columns = row.select("td");
                ArrayList<String> holder = new ArrayList<String>();
                for (org.jsoup.nodes.Element column:columns)
                {
                    if (counter != 0){
                        holder.add(column.text());
                        System.out.printf("%s",holder);
                    }
                }
                if(counter !=0){
                    String name1= holder.get(1);
                    String number1 = holder.get(0);
                    String value1 = holder.get(2);
                    checkValid check = new checkValid();
                    String error = check.checkingValid(number1,name1,value1,inventoryList);
                    //if there is no error
                    if (error.equals(" ")) {
                        inventoryList = add.addTask(number1,name1,value1,inventoryList);
                        inventoryItem todoItem = new inventoryItem(number1, name1, value1);
                        tableOfValues.getItems().add(todoItem);

                    }
                    else {
                        //if there is a valid error in the file then it doesn't update the table, instead leaves error message
                        errorMessage.setText("In proper formatting of a HTML file");
                        tableOfValues.getItems().clear();
                        inventoryList = clear.clearArrayList();
                        break;
                    }
                }
                counter++;
            }
        }
        else{
            errorMessage.setText("Not a HTMl File");
        }

    }
    @FXML
    ArrayList<String> removeButtonClicked(ActionEvent event) {
        removeItems remove = new removeItems();
        inventoryItem item = tableOfValues.getSelectionModel().getSelectedItem();
        String itemString = item.getNumber() + "\t" + item.getName() + "\t" + item.getValue();
        //calls the removeTask function with the value
        tableOfValues.getItems().removeAll((tableOfValues.getSelectionModel().getSelectedItem()));
        remove.removeTask(itemString, inventoryList);
        //it returns the new arrayList and assigns it to todoList
        return inventoryList;
    }

    @FXML
    void searchButtonClicked(ActionEvent event) {
        //creates a blank arrayList
        ArrayList<String> newDisplay = new ArrayList<String>();
        String number = serialNumber.getText();
        String name = nameOfProduct.getText();
        searchFor search = new searchFor();
        int test = 0;
        //checks to see which parameter the user wants to search for
        if(!(number.equals(""))){
            tableOfValues.getItems().clear();
            //calls the function
            newDisplay = search.searchForStuff(inventoryList,number,1);
            test =1 ;
        }
        else if(!(name.equals(""))){
            tableOfValues.getItems().clear();
            //calls the function
            newDisplay = search.searchForStuff(inventoryList,name,2);
            test = 1;
        }
        //if search is called with no parameters it shows everything in the table
        else{
            tableOfValues.getItems().clear();
            int size = inventoryList.size();
            for(int i = 0; i < size; i ++){
                String old = inventoryList.get(i);
                String words[] = old.split("\t");
                String number1 = words[0];
                String name1 = words[1];
                String value1 = words[2];
                inventoryItem item = new inventoryItem(number1, name1, value1);
                tableOfValues.getItems().add(item);
            }
        }
        //if the user wants to search for something this will be called
        if (test == 1){
            int size = newDisplay.size();
            for(int i = 0; i < size; i ++){
                String old = newDisplay.get(i);
                String words[] = old.split("\t");
                String number1 = words[0];
                String name1 = words[1];
                String value1 = words[2];
                inventoryItem item = new inventoryItem(number1, name1, value1);
                tableOfValues.getItems().add(item);
            }
        }
    }
    @FXML
    void SaveClicked(ActionEvent event) {
        return;
    }
    @FXML
    void LoadClicked(ActionEvent event) {
        return;
    }


}