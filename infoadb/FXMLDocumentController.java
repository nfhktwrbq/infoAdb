/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoadb;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author muaddib
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private Button button;
    @FXML
    private TableView tableView;
    @FXML
    private BorderPane borderPane;

    private FXMLInfoAdbModel infoAdbModel;
    private final ResourceBundle resources;
    private Map<String, DeviceInfo> infotable;
    private ObservableList<Property> data;
    private ObservableList<String> choiceBoxItems;

    @FXML
    private void handleButtonAction(ActionEvent event) {         
        this.choiceBox.getItems().clear();                  
        this.choiceBoxItems.clear();
        clearTable();
        this.infoAdbModel = new FXMLInfoAdbModel();  
        loadAdbDevices();        
    }

    @FXML
    private void handleChoiceBoxAction(ActionEvent event) {
        clearTable();        
        String serial = String.valueOf(choiceBox.getSelectionModel().getSelectedItem());  
        if(serial.equals("null")) return;
        this.infotable = this.infoAdbModel.getInfoTable();
        if (this.infotable == null || !this.infotable.containsKey(serial)) {
            this.infoAdbModel.addDataToInfoTable(serial, resources.getString("delimeter"));
        }
        TableColumn propertyNameCol = new TableColumn("Name");
        TableColumn propertyValueCol = new TableColumn("Property");        
        DeviceInfo deviceInfo = this.infotable.get(serial);
        data = FXCollections.observableArrayList(
                new Property(resources.getString("CPU"), deviceInfo.getCpuInfo()),
                new Property(resources.getString("MEMORY"), deviceInfo.getMemoryInfo()),
                new Property(resources.getString("ANDROID"), deviceInfo.getAndroidInfo()),
                new Property(resources.getString("PHONEMODEL"), deviceInfo.getPhoneModelInfo()),
                new Property(resources.getString("OTHER"), deviceInfo.getOtherInfo())
        );

        propertyNameCol.setCellValueFactory(new PropertyValueFactory<Property, String>("propertyName"));
        propertyValueCol.setCellValueFactory(new PropertyValueFactory<Property, String>("propertyValue"));

        this.tableView.getColumns().addAll(propertyNameCol, propertyValueCol);
        this.tableView.setItems(data);        
    }

    public FXMLDocumentController() {        
        this.infoAdbModel = new FXMLInfoAdbModel(); 
        this.data = FXCollections.observableArrayList();
        this.choiceBoxItems = FXCollections.observableArrayList();
        resources = ResourceBundle.getBundle("infoadb.StringResource");
    }
    
    public void clearTable(){
        this.data.clear();
        this.tableView.getColumns().clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {             
        this.choiceBox.setItems(this.choiceBoxItems);
        this.tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        loadAdbDevices();        
    }
    
    public void loadAdbDevices(){
        
        if (this.infoAdbModel != null) {
            this.choiceBoxItems.addAll(this.infoAdbModel.getSerial());           
            this.choiceBox.getSelectionModel().selectFirst();
        } else {
            this.choiceBox.setItems(FXCollections.observableArrayList("None"));
        }        
    }
    
    public class Property {

            private final SimpleStringProperty propertyName;
            private final SimpleStringProperty propertyValue;

            public Property(String name, String value) {
                this.propertyName = new SimpleStringProperty(name);
                this.propertyValue = new SimpleStringProperty(value);
            }

            public void setPropertyName(String name) {
                this.propertyName.set(name);
            }

            public void setPropertyValue(String value) {
                this.propertyName.set(value);
            }

            public String getPropertyName() {
                return this.propertyName.get();
            }

            public String getPropertyValue() {
                return this.propertyValue.get();
            }
        }

}
