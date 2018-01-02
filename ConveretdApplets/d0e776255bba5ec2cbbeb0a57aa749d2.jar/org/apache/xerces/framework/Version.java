// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.framework;

public class Version
{
    public static String fVersion;
    
    public static void main(final String[] array) {
        System.out.println(Version.fVersion);
    }
    
    static {
        Version.fVersion = "Xerces 1.3.1";
    }
}
