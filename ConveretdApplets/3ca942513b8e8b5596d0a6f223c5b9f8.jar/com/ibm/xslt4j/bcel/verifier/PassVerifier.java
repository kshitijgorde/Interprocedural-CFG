// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.verifier;

import java.util.ArrayList;

public abstract class PassVerifier
{
    private ArrayList messages;
    private VerificationResult verificationResult;
    
    public PassVerifier() {
        this.messages = new ArrayList();
        this.verificationResult = null;
    }
    
    public VerificationResult verify() {
        if (this.verificationResult == null) {
            this.verificationResult = this.do_verify();
        }
        return this.verificationResult;
    }
    
    public abstract VerificationResult do_verify();
    
    public void addMessage(final String message) {
        this.messages.add(message);
    }
    
    public String[] getMessages() {
        this.verify();
        final String[] ret = new String[this.messages.size()];
        for (int i = 0; i < this.messages.size(); ++i) {
            ret[i] = this.messages.get(i);
        }
        return ret;
    }
}
