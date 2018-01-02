// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.verifier.exc;

public abstract class VerifierConstraintViolatedException extends RuntimeException
{
    private String detailMessage;
    
    VerifierConstraintViolatedException() {
    }
    
    VerifierConstraintViolatedException(final String message) {
        super(message);
        this.detailMessage = message;
    }
    
    public void extendMessage(String pre, String post) {
        if (pre == null) {
            pre = "";
        }
        if (this.detailMessage == null) {
            this.detailMessage = "";
        }
        if (post == null) {
            post = "";
        }
        this.detailMessage = String.valueOf(pre) + this.detailMessage + post;
    }
    
    public String getMessage() {
        return this.detailMessage;
    }
}
