// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl;

public class Version
{
    public static String fVersion;
    private static final String fImmutableVersion = "Xerces-J 2.2.1";
    
    public static String getVersion() {
        return "Xerces-J 2.2.1";
    }
    
    public static void main(final String[] argv) {
        System.out.println(Version.fVersion);
    }
    
    static {
        Version.fVersion = "Xerces-J 2.2.1";
    }
}
