// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

public enum CompatVersion
{
    RUBY1_8, 
    RUBY1_9, 
    BOTH;
    
    public static CompatVersion getVersionFromString(final String compatString) {
        if (compatString.equalsIgnoreCase("RUBY1_8")) {
            return CompatVersion.RUBY1_8;
        }
        if (compatString.equalsIgnoreCase("RUBY1_9")) {
            return CompatVersion.RUBY1_9;
        }
        return null;
    }
}
