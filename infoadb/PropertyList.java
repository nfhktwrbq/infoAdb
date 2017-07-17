/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoadb;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Andrew
 */
public final class PropertyList {
    public static final List<String> CPU = Arrays.asList("Processor");    
    public static final List<String> MEMORY = Arrays.asList("MemTotal", "Active");
    public static final List<String> PHONEMODEL = Arrays.asList("[ro.product.model]", "[ro.hardware]", "[ro.product.manufacturer]");
    public static final List<String> ANDROID = Arrays.asList("[ro.build.description]");
    public static final List<String> OTHER = Arrays.asList("[gsm.operator.alpha]");
}
