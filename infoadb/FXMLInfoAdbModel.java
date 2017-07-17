/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoadb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author muaddib
 */
public class FXMLInfoAdbModel {
    
    //private List<JadbDevice> devices;
    private JadbConnection jadb;
    private Map<String, JadbDevice> devicesTable;
    private Map<String, DeviceInfo> infoTab; 
    private Map<String, String> commonInfo;
    

    
    public FXMLInfoAdbModel(){ 
        this.infoTab = new  HashMap<>();
        this.commonInfo = new  HashMap<>();
        try {
            this.jadb = new JadbConnection();
            this.devicesTable = jadb.getDevices().stream().collect(Collectors.toMap(x -> x.getSerial(), x -> x));            
        } catch (IOException | JadbException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Map<String, JadbDevice> refreshDeviceList(){
        this.devicesTable.clear();
        this.devicesTable = null;
        try {
            this.jadb = new JadbConnection();
            this.devicesTable = jadb.getDevices().stream().collect(Collectors.toMap(x -> x.getSerial(), x -> x));            
        } catch (IOException | JadbException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.devicesTable;
    }
    
    public List<String> getSerial(){
        if(this.devicesTable != null){
            return new ArrayList<>(this.devicesTable.keySet());
        }
        return null;
    }
    
    public Map<String, JadbDevice> getDeviceTable(){        
        return this.devicesTable;
    }

    public Map<String, String> getDeviceProperties(JadbDevice jAdbDevice, String delimeter, String command, String... args){        
        if(jAdbDevice != null){  
            try {
                return DataConverter.dataToTable(jAdbDevice.executeShell(command, args), delimeter);
            } catch (IOException | JadbException ex) {
                Logger.getLogger(FXMLInfoAdbModel.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        return null;
    }
    
    public Map<String, String> getDeviceProperties(String serial, String delimeter, String command, String... args){
        if(this.devicesTable != null){
            return getDeviceProperties(this.devicesTable.get(serial), delimeter, command, args);
        }
        return null;
    }
    
    public void addDataToInfoTable(String serial, String delimeter){        
        this.commonInfo = getDeviceProperties(serial, ": ", "getprop", "");
        String phoneModelInfo = DataConverter.sortDataPresent(commonInfo, PropertyList.PHONEMODEL, delimeter);
        DeviceInfo deviceInfo;
        deviceInfo =  DeviceInfo.newBuilder()
                .setAndroidInfo(phoneModelInfo)
                .setCpuInfo(phoneModelInfo)
                .setPhoneModelInfo(phoneModelInfo)
                .setOtherInfo(phoneModelInfo)
                .build();        
        this.infoTab.put(serial, deviceInfo);
    }
    
    public Map<String, DeviceInfo> getInfoTable(){
        if(this.infoTab != null){
            return this.infoTab;
        }
        return null;
    }
}
