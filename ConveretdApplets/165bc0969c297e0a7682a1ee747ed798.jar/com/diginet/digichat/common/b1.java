// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

public class b1 extends bp
{
    public boolean fRest;
    public String c;
    
    public static boolean a(final String s, final int n) {
        return n < 0 || n >= s.length() || Character.isSpace(s.charAt(n));
    }
    
    public boolean a(final String s, final Object o) {
        if ("restricted".equals(s)) {
            this.fRest = (boolean)o;
            return true;
        }
        return super.a(s, o);
    }
    
    public Object e(final String s) {
        if ("image".equals(s)) {
            return super.a;
        }
        if ("name".equals(s)) {
            return this.c;
        }
        if ("restricted".equals(s)) {
            return new Boolean(this.fRest);
        }
        return super.e(s);
    }
    
    public String f(final String s) {
        return null;
    }
    
    public b1(final int n, final String s) {
        super(n, s);
        this.fRest = false;
    }
}
