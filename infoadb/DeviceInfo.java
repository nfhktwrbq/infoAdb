/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoadb;

import java.util.Map;

/**
 *
 * @author Andrew
 */
public class DeviceInfo {
    
    private String cpuInfo;
    private String phoneModelInfo;
    private String androidInfo;
    private String otherInfo;
    
    private DeviceInfo(){
        
    }
    
    public String getCpuInfo(){
        return this.cpuInfo;
    }
    
    public String getPhoneModelInfo(){
        return this.phoneModelInfo;
    }
    
    public String getAndroidInfo(){
        return this.androidInfo;
    }
    
    public String getOtherInfo(){
        return this.otherInfo;
    }
    
    public static Builder newBuilder(){
        return new DeviceInfo().new Builder();
    }
    
    public class Builder{
        
        private Builder(){
            
        }
      
        public Builder setCpuInfo(String cpuInfo){
            DeviceInfo.this.cpuInfo = cpuInfo;
            return this;
        }
        
        public Builder setPhoneModelInfo(String phoneModelInfo){
            DeviceInfo.this.phoneModelInfo = phoneModelInfo;
            return this;
        }
        
        public Builder setAndroidInfo(String androidInfo){
            DeviceInfo.this.androidInfo = androidInfo;
            return this;
        }
        
        public Builder setOtherInfo(String otherInfo){
            DeviceInfo.this.otherInfo = otherInfo;
            return this;
        }
        
        public DeviceInfo build(){
            return DeviceInfo.this;
        }
    }
    
}
