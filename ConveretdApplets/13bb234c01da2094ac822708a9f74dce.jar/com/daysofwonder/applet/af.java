// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

public class af extends RuntimeException
{
    private boolean a;
    
    public af(final String s) {
        super(s);
    }
    
    public af(final String s, final boolean a) {
        this(s);
        this.a = a;
    }
}
