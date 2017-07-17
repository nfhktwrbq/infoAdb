/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoadb;

import java.net.URL;
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

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");

    }

    @FXML
    private void handleChoiceBoxAction(ActionEvent event) {
        
        String serial = String.valueOf(choiceBox.getSelectionModel().getSelectedItem());
        this.infotable = this.infoAdbModel.getInfoTable();
        if (this.infotable == null || !this.infotable.containsKey(serial)) {
            this.infoAdbModel.addDataToInfoTable(serial, resources.getString("delimeter"));
        }
        TableColumn propertyNameCol = new TableColumn("Name");
        TableColumn propertyValueCol = new TableColumn("Property");
        /*DeviceInfo testDeviceInfo = DeviceInfo.newBuilder()
                .setAndroidInfo("fuck1")
                .setCpuInfo("fuck2")
                .setOtherInfo("fuck3")
                .setPhoneModelInfo("fuck4")
                .build();*/
        DeviceInfo deviceInfo = this.infotable.get(serial);
        data = FXCollections.observableArrayList(
                new Property(resources.getString("CPU"), deviceInfo.getCpuInfo()),
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
        infoAdbModel = new FXMLInfoAdbModel();
        resources = ResourceBundle.getBundle("infoadb.StringResource");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.infoAdbModel = new FXMLInfoAdbModel();
        if (this.infoAdbModel != null) {
            this.choiceBox.setItems(FXCollections.observableArrayList(this.infoAdbModel.getSerial()));
        } else {
            this.choiceBox.setItems(FXCollections.observableArrayList("None"));
        }
        this.tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.choiceBox.getSelectionModel().selectFirst();
        /*BorderPane.setAlignment(tableView, Pos.CENTER);
        BorderPane.setAlignment(choiceBox, Pos.CENTER);*/
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
