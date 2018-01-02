// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import jclass.util.JCUtilConverter;

class ArrowButtonConverter
{
    static int[] orientation_values;
    static String[] orientation_strings;
    
    static void getParams(final JCArrowButton jcArrowButton) {
        jcArrowButton.orientation = JCComponent.conv.toEnum(jcArrowButton.getParam("Orientation"), "orientation", ArrowButtonConverter.orientation_strings, ArrowButtonConverter.orientation_values, jcArrowButton.orientation);
    }
    
    static void checkOrientation(final int n) {
        JCUtilConverter.checkEnum(n, "orientation", ArrowButtonConverter.orientation_values);
    }
    
    static {
        ArrowButtonConverter.orientation_values = new int[] { 10, 9, 0, 2 };
        ArrowButtonConverter.orientation_strings = new String[] { "UP", "DOWN", "LEFT", "RIGHT" };
    }
}
