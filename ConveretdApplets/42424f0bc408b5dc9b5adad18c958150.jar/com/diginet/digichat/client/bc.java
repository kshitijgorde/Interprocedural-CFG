// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.util.ap;
import com.esial.util.d;
import com.diginet.digichat.awt.m;
import com.diginet.digichat.common.a4;

public class bc extends a4 implements m
{
    public boolean a;
    public boolean b;
    
    public Object e(final String s) {
        if ("users".equals(s)) {
            return new Integer(super.c);
        }
        if (!"lock".equals(s)) {
            return super.e(s);
        }
        if (super.h == null || super.h.a()) {
            return null;
        }
        if (i.c != null) {
            return i.c;
        }
        return "*";
    }
    
    public String f(final String s) {
        String s2;
        if (this.a) {
            s2 = ap.a(com.esial.util.d.a("You are in %1."), new String[] { this.r() });
        }
        else {
            s2 = ap.a(com.esial.util.d.a("Double-click here to enter %1."), new String[] { this.r() });
        }
        if (super.a != null) {
            s2 = s2.substring(0, s2.length() - 1) + ": " + super.a;
        }
        return s2;
    }
    
    public int a(final m m, final String s) {
        if ("users".equals(s) && m instanceof a4) {
            return ((a4)m).c - super.c;
        }
        return super.a(m, s);
    }
    
    public bc(final int n, final String s) {
        super(n, s);
        this.a = false;
        this.b = true;
    }
}
