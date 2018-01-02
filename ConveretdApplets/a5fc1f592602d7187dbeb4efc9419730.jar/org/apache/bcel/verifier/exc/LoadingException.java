// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.verifier.exc;

public class LoadingException extends VerifierConstraintViolatedException
{
    private String detailMessage;
    
    public LoadingException() {
    }
    
    public LoadingException(final String message) {
        super(message);
        this.detailMessage = message;
    }
}
