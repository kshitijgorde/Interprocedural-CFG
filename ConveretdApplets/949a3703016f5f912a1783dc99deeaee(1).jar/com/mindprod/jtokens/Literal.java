// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jtokens;

public abstract class Literal extends Token
{
    static final long serialVersionUID = 1L;
    
    public Literal(final char c) {
        super(c);
    }
    
    public Literal(final String text) {
        super(text);
    }
}
