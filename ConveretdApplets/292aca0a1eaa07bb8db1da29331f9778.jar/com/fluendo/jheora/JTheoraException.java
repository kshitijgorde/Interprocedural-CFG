// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

public class JTheoraException extends Exception
{
    private static final long serialVersionUID = 1L;
    private int error;
    
    public JTheoraException() {
    }
    
    public JTheoraException(final String str, final int error) {
        super(str);
        this.error = error;
    }
    
    public int getErrorCode() {
        return this.error;
    }
}
