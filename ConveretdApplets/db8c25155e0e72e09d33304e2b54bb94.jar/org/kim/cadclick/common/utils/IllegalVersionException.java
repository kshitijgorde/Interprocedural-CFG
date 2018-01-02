// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.common.utils;

public class IllegalVersionException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    public IllegalVersionException() {
        this("No valid version String or no version found.");
    }
    
    public IllegalVersionException(final String s) {
        super(s);
    }
}
