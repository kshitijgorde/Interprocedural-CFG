// 
// Decompiled by Procyon v0.5.30
// 

package sTools;

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
