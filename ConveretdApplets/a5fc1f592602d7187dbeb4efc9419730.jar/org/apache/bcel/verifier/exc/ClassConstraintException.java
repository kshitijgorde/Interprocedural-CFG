// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.verifier.exc;

public class ClassConstraintException extends VerificationException
{
    private String detailMessage;
    
    public ClassConstraintException() {
    }
    
    public ClassConstraintException(final String message) {
        super(message);
        this.detailMessage = message;
    }
}
