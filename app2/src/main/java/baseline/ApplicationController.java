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

import javax.swing.*;
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
    private MenuItem loadHTMl;

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
                    String error = check.checkValid(newNumber,oldName,oldValue,inventoryList);
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
    void LoadClicked(ActionEvent event) {


    }

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
        if(file.getName().endsWith(".txt")){
            try{
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()){
                    temp.add((scanner.nextLine()));
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            // goes through every line

            for (int i = 0; i < temp.size(); i++ ){
                parser.parseInformation(temp.get(i));
                String number1 = parser.getNumber();
                String name1 = parser.getName();
                String value1 = parser.getValue();
                checkValid check = new checkValid();
                String error = " ";
                error = check.checkValid(number1,name1,value1,inventoryList);

                if (error.equals(" ")) {
                    inventoryList = add.addTask(number1,name1,value1,inventoryList);
                    inventoryItem todoItem = new inventoryItem(number1, name1, value1);
                    tableOfValues.getItems().add(todoItem);

                }
                else {
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
        if(file.getName().endsWith(".json")) {
            try (FileReader reader = new FileReader(file)) {
                Object obj = jsonParser.parse(reader);
                JSONArray item = (JSONArray) obj;
                for (int i = 0; i< item.size(); i++){
                    parser.parseJSONFile((JSONObject) item.get(i));
                    String number1 = parser.getNumber();
                    String name1 = parser.getName();
                    String value1 = parser.getValue();
                    checkValid check = new checkValid();
                    String error = " ";
                    error = check.checkValid(number1,name1,value1,inventoryList);
                    if (error.equals(" ")) {
                        inventoryList = add.addTask(number1,name1,value1,inventoryList);
                        inventoryItem todoItem = new inventoryItem(number1, name1, value1);
                        tableOfValues.getItems().add(todoItem);
                    }
                    else {
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
    void SaveClicked(ActionEvent event) {


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
        String error = " ";
        checkValid check = new checkValid();
        error = check.checkValid(number,name,value,inventoryList);
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
        tableOfValues.getItems().clear();
        clearEverything clear = new clearEverything();
        inventoryList = clear.clearArrayList();
        File file = fileChooser.showOpenDialog(new Stage());
        FileReader fr = new FileReader(file);
        StringBuilder html = new StringBuilder();
        //clears what was left
        if(file.getName().endsWith(".html")){
            try{
                BufferedReader br = new BufferedReader(fr);
                String val;
                while ((val = br.readLine()) != null){
                    html.append(val);
                }
                br.close();
                String result = html.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                    String error = " ";
                    error = check.checkValid(number1,name1,value1,inventoryList);

                    if (error.equals(" ")) {
                        inventoryList = add.addTask(number1,name1,value1,inventoryList);
                        inventoryItem todoItem = new inventoryItem(number1, name1, value1);
                        tableOfValues.getItems().add(todoItem);

                    }
                    else {
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
        ArrayList<String> newDisplay = new ArrayList<String>();
        String number = serialNumber.getText();
        String name = nameOfProduct.getText();
        searchFor search = new searchFor();
        int test = 0;
        if(!(number.equals(""))){
            tableOfValues.getItems().clear();
            newDisplay = search.searchFor(inventoryList,number,1);
            test =1 ;
        }
        else if(!(name.equals(""))){
            tableOfValues.getItems().clear();
            newDisplay = search.searchFor(inventoryList,name,2);
            test = 1;
        }
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
        //get the value in the tableMaker
        //call searchFor function
        //update the table viewer
        //call checkValid if it is not found
    }

}