// 
// Decompiled by Procyon v0.5.30
// 

package com.kaon.meson;

public class MesonException extends Exception
{
    public Throwable nested;
    
    public MesonException(final String s) {
        super(s);
    }
    
    public MesonException(final String s, final Throwable nested) {
        super(s);
        this.nested = nested;
    }
}
