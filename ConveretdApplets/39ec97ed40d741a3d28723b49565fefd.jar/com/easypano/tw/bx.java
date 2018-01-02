// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.util.MissingResourceException;
import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;

public class bx
{
    public static final int a = 1;
    public static final int b = 0;
    private PropertyResourceBundle c;
    private int d;
    
    public bx() {
        this(null);
    }
    
    public bx(final InputStream inputStream) {
        this.c = null;
        this.d = 0;
        try {
            this.c = new PropertyResourceBundle(inputStream);
        }
        catch (IOException ex) {}
    }
    
    public String a(final String s) {
        return this.a(s, "");
    }
    
    public String a(final String s, final String s2) {
        final PropertyResourceBundle c = this.c;
        Label_0029: {
            if (g.q) {
                break Label_0029;
            }
            if (c == null) {
                this.d = 1;
                return s2;
            }
            try {
                this.d = 0;
                final PropertyResourceBundle c2 = this.c;
                return c.getString(s);
            }
            catch (MissingResourceException ex) {
                this.d = 1;
                return s2;
            }
        }
    }
    
    public int a() {
        return this.d;
    }
}
