// 
// Decompiled by Procyon v0.5.30
// 

package jstella.core;

public class JSException extends Exception
{
    private static final long serialVersionUID = 3941482331908946012L;
    private String myMessage;
    private ExceptionType myExceptionType;
    
    public JSException(final ExceptionType aType, final String aMsg) {
        super(aMsg);
        this.myMessage = "";
        this.myExceptionType = ExceptionType.UNSPECIFIED;
        this.myMessage = aMsg;
        this.myExceptionType = aType;
    }
    
    public ExceptionType getExceptionType() {
        return this.myExceptionType;
    }
    
    public String getJStellaMessage() {
        return this.myMessage;
    }
    
    public String toString() {
        return this.getJStellaMessage() + " (Exception Type: " + this.myExceptionType + ")";
    }
    
    public JSException(final String msg) {
        super(msg);
        this.myMessage = "";
        this.myExceptionType = ExceptionType.UNSPECIFIED;
    }
    
    public enum ExceptionType
    {
        UNSPECIFIED, 
        IO, 
        AUDIO, 
        CART_NOT_SUPPORTED, 
        INSTRUCTION_NOT_RECOGNIZED;
    }
}
