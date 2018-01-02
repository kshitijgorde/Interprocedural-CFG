// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl;

public class Version
{
    public static String fVersion;
    private static final String fImmutableVersion = "XML4J 4.4.5";
    
    public static String getVersion() {
        return "XML4J 4.4.5";
    }
    
    public static void main(final String[] array) {
        System.out.println(Version.fVersion);
    }
    
    static {
        Version.fVersion = "XML4J 4.4.5";
    }
}
