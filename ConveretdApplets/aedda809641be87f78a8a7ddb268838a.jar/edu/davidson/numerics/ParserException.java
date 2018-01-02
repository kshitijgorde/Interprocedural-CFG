// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.numerics;

class ParserException extends Exception
{
    private int errorcode;
    
    ParserException(final int errorcode) {
        this.errorcode = errorcode;
    }
    
    public int getErrorCode() {
        return this.errorcode;
    }
}
