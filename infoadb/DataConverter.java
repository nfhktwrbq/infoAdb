/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoadb;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author Andrew
 */
public class DataConverter {
            
    public static  Map<String, String> dataToTable(InputStream is, String keyPattern, String valuePattern){
        Map<String, String> retMap = new HashMap<>();
        Pattern keyP = Pattern.compile(keyPattern);
        Pattern valueP = Pattern.compile(valuePattern);
        Scanner scanner = new Scanner(is);
        while (scanner.hasNext() ){
            Matcher keyMatcher = keyP.matcher(scanner.nextLine());
            Matcher valueMatcher = valueP.matcher(scanner.nextLine());
            if(keyMatcher.find() && valueMatcher.find()){
                retMap.put(keyMatcher.group(), valueMatcher.group());
            }            
        }
        return retMap;
    }
    
    public static Map<String, String> dataToTable(InputStream is, String delimeterPattern){
        Map<String, String> retMap = new HashMap<>();
        Scanner scanner = new Scanner(is);
        while (scanner.hasNext() ){
            String[] keyValue = scanner.nextLine().split(delimeterPattern);
            retMap.put(keyValue[0],keyValue.length > 1 ? keyValue[1] : "");
        }
        return retMap;
    }
    
    public static List<String> sortData(Map<String, String> data, List<String> groupField){        
        return data.entrySet().stream().filter(map -> groupField.contains(map.getKey())).map(map -> map.getValue()).collect(Collectors.toList());
    }
    
    public static String sortDataPresent(Map<String, String> data, List<String> groupField, String delimeter){
        StringBuilder tmpBuilder = new StringBuilder();        
        sortData(data, groupField).stream().forEachOrdered(x -> tmpBuilder.append(x).append(delimeter));
        return tmpBuilder.toString().substring(0, ((tmpBuilder.length() - delimeter.length()) < 0) ? 0 : (tmpBuilder.length() - delimeter.length()));
    }
    
}
