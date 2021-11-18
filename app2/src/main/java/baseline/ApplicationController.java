package baseline;

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

    @FXML
    void LoadClicked(ActionEvent event) {

    }

    @FXML
    void LoadTSVClicked(ActionEvent event) {

    }

    @FXML
    void SaveClicked(ActionEvent event) {

    }

    @FXML
    void SaveHTMLClicked(ActionEvent event) {

    }

    @FXML
    void SaveJSONClicked(ActionEvent event) {

    }

    @FXML
    void SaveTSVCLICKED(ActionEvent event) {

    }

    @FXML
    void addButtonClicked(ActionEvent event) {

    }

    @FXML
    void clearButtonClicked(ActionEvent event) {

    }

    @FXML
    void loadHTMLClicked(ActionEvent event) {

    }

    @FXML
    void loadJSONClicked(ActionEvent event) {

    }

    @FXML
    void removeButtonClicked(ActionEvent event) {

    }

    @FXML
    void searchButtonClicked(ActionEvent event) {

    }

}