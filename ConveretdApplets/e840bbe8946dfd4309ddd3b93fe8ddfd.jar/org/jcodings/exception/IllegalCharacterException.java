// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.exception;

public class IllegalCharacterException extends EncodingException
{
    public static final IllegalCharacterException INSTANCE;
    
    private IllegalCharacterException() {
        super("illegal character");
    }
    
    static {
        INSTANCE = new IllegalCharacterException();
    }
}
