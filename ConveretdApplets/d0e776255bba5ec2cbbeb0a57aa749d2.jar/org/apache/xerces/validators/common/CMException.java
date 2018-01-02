// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.common;

public class CMException extends Exception
{
    static final int fUnused = -1000;
    private int fErrorCode;
    
    public CMException(final int fErrorCode) {
        this.fErrorCode = fErrorCode;
    }
    
    public int getErrorCode() {
        return this.fErrorCode;
    }
}
