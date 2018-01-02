// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

public final class Version
{
    public static final String getShortVersion() {
        return "4.33B";
    }
    
    public static final String getMajorVersion() {
        final int dot = getShortVersion().indexOf(".");
        return getShortVersion().substring(0, dot);
    }
    
    public static final String getMinorVersion() {
        final int dot = getShortVersion().indexOf(".");
        return getShortVersion().substring(dot + 1);
    }
}
