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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {
    private ArrayList<String> inventoryList = new ArrayList<>();

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
        nameColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<inventoryItem,String> t) -> {
                    modifyShown changes = new modifyShown();
                    //getting the values of the selected row
                    String oldValue = t.getTableView().getItems().get(t.getTablePosition().getRow()).getValue();
                    String oldNumber = t.getTableView().getItems().get(t.getTablePosition().getRow()).getNumber();
                    String oldName = t.getTableView().getItems().get(t.getTablePosition().getRow()).getName();
                    ((inventoryItem)t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
                    String newDescription = t.getTableView().getItems().get(t.getTablePosition().getRow()).getValue();
                    String old = oldNumber+"_" + oldName + "_"+ oldValue;
                    //changing the array list
                    inventoryList = changes.modifyArrayList(inventoryList, newDescription, old, 2);

                }
        );
        valueColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<inventoryItem,String> t) -> {
                    modifyShown changes = new modifyShown();
                    String oldValue = t.getTableView().getItems().get(t.getTablePosition().getRow()).getValue();
                    String oldNumber = t.getTableView().getItems().get(t.getTablePosition().getRow()).getNumber();
                    String oldName = t.getTableView().getItems().get(t.getTablePosition().getRow()).getName();
                    ((inventoryItem)t.getTableView().getItems().get(t.getTablePosition().getRow())).setValue(t.getNewValue());
                    String newName = t.getTableView().getItems().get(t.getTablePosition().getRow()).getName();
                    String old = oldNumber+"_" + oldName + "_"+ oldValue;
                    //changing the array list
                    inventoryList = changes.modifyArrayList(inventoryList, newName, old, 1);
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
                    String old = oldNumber+"_" + oldName + "_"+ oldValue;
                    //changing the array list
                    inventoryList = changes.modifyArrayList(inventoryList, newNumber, old, 0);
                }
        );
    }


    private ObservableList<inventoryItem> inventory = FXCollections.observableArrayList();
    @FXML
    void LoadClicked(ActionEvent event) {


    }

    @FXML
    void LoadTSVClicked(ActionEvent event) {
        //call the openFile function with the TSV as the parameter
        // call the clear Function
        //call the add item function for the information

    }

    @FXML
    void SaveClicked(ActionEvent event) {


    }

    @FXML
    void SaveHTMLClicked(ActionEvent event) {
        //open Filechoser
        //call the saveFile function with HTMl parameter
    }

    @FXML
    void SaveJSONClicked(ActionEvent event) {
        //open Filechoser
        //call the saveFile function with JSON parameter

    }

    @FXML
    void SaveTSVCLICKED(ActionEvent event) {
        //open Filechoser
        //call the saveFile function with TSV parameter
    }

    @FXML
    ArrayList<String> addButtonClicked(ActionEvent event) {
        //get the value of the price, item number, and name
        addItems add = new addItems();
        System.out.printf("HEY");
        //check to see if they are valid inputs
        String number, value, name;
        number = serialNumber.getText();
        name = nameOfProduct.getText();
        value = valueOfProduct.getText();
        String error = "";
        checkValid check = new checkValid();
        error = check.checkValid(number,name,value,inventoryList);
        if (error.equals("")){
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
    void loadHTMLClicked(ActionEvent event) {
        //call the openFile function with the HTML as the parameter
        // call the clear Function
        //call the add item function for the information

    }

    @FXML
    void loadJSONClicked(ActionEvent event) {
        //call the openFile function with the JSON as the parameter
        // call the clear Function
        //call the add item function for the information
    }

    @FXML
    ArrayList<String> removeButtonClicked(ActionEvent event) {
        removeItems remove = new removeItems();
        inventoryItem item = tableOfValues.getSelectionModel().getSelectedItem();
        String itemString = item.getNumber() + "_" + item.getName() + "_" + item.getValue();
        //calls the removeTask function with the value
        tableOfValues.getItems().removeAll((tableOfValues.getSelectionModel().getSelectedItem()));
        remove.removeTask(itemString, inventoryList);
        //it returns the new arrayList and assigns it to todoList
        return inventoryList;

    }

    @FXML
    void searchButtonClicked(ActionEvent event) {
        //get the value in the tableMaker
        //call searchFor function
        //update the table viewer
        //call checkValid if it is not found
    }

}