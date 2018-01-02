// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.verifier.exc;

public class InvalidMethodException extends RuntimeException
{
    private InvalidMethodException() {
    }
    
    public InvalidMethodException(final String message) {
        super(message);
    }
}
