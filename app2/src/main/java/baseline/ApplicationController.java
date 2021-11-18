package baseline;
/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Ross Springstead
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ApplicationController {

    @FXML
    private MenuButton Load;

    @FXML
    private MenuButton Save;

    @FXML
    private TableColumn<?, ?> SerialNumberColumn;

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
    private TableColumn<?, ?> nameColumn;

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
    private TableView<?> tableOfValues;

    @FXML
    private TableColumn<?, ?> valueColumn;

    //overriding the initialize function so I can set the table up
    // set each column to be editable
    // get the value, so I can check to see if it is valid or not


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
    void addButtonClicked(ActionEvent event) {
        //get the value of the price, item number, and name
        //check to see if they are valid inputs
        //call the addItem function
        //update the tableViewer
    }

    @FXML
    void clearButtonClicked(ActionEvent event) {
        //clear the tableViewer
        //call the clearEverything function

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
    void removeButtonClicked(ActionEvent event) {
        //update the tableViewer by getting the selected row
        //call removeItem function

    }

    @FXML
    void searchButtonClicked(ActionEvent event) {
        //get the value in the tableMaker
        //call searchFor function
        //update the table viewer
        //call checkValid if it is not found
    }

}