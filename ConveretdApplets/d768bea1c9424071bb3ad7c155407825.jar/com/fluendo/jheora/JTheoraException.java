// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

public class JTheoraException extends Exception
{
    private int error;
    
    public JTheoraException() {
    }
    
    public JTheoraException(final String s, final int error) {
        this.error = error;
    }
}
