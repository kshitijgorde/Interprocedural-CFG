// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xmlcommons;

public class Version
{
    public static String getVersion() {
        return getProduct() + " " + getVersionNum();
    }
    
    public static String getProduct() {
        return "IBM JAXP";
    }
    
    public static String getVersionNum() {
        return "1.3.5";
    }
    
    public static void main(final String[] array) {
        System.out.println(getVersion());
    }
}
