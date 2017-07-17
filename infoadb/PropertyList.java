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
    public static final List<String> CPU = Arrays.asList("ro.build.display.id", "sup2", "sup3");
    public static final String CPUs = "vdfv";
    public static final List<String> MEMORY = Arrays.asList("sup1", "sup2", "sup3");
    public static final List<String> PHONEMODEL = Arrays.asList("[ro.product.model]", "[ro.hardware]", "[ro.product.manufacturer]");
}
