// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens.java;

import com.mindprod.jtokens.Token;

public abstract class Definable extends Token
{
    static final long serialVersionUID = 1L;
    boolean defining;
    
    public boolean isCollapsible() {
        return true;
    }
    
    public boolean isCollapsible(final Token t) {
        return this.isCollapsible() && t.getClass() == this.getClass() && ((Definable)t).defining == this.defining;
    }
    
    Definable(final String identifierName, final boolean defining) {
        super(identifierName);
        this.defining = defining;
        if (identifierName == null || identifierName.length() == 0) {
            throw new IllegalArgumentException("missing identifierName for Definable");
        }
    }
    
    private boolean isDefining() {
        return this.defining;
    }
    
    public void setDefining(final boolean defining) {
        this.defining = defining;
    }
}
