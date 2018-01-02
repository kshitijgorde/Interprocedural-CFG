// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.util.a5;
import com.esial.util.c;
import com.diginet.digichat.awt.m;
import com.diginet.digichat.common.bg;

public class bo extends bg implements m
{
    public boolean a;
    public boolean b;
    
    public Object e(final String s) {
        if ("current".equals(s)) {
            return this.a ? i.imgPointer : null;
        }
        if ("lock".equals(s)) {
            if (super.h == null || super.h.a()) {
                return null;
            }
            if (i.c == null) {
                return "*";
            }
            return i.c;
        }
        else {
            if ("users".equals(s)) {
                return new Integer(super.c);
            }
            return super.e(s);
        }
    }
    
    public String f(final String s) {
        String s2;
        if (this.a) {
            s2 = a5.a(com.esial.util.c.a("You are in %1."), new String[] { this.x() });
        }
        else {
            s2 = a5.a(com.esial.util.c.a("Double-click here to enter %1."), new String[] { this.x() });
        }
        if (super.a != null) {
            s2 = String.valueOf(String.valueOf(s2.substring(0, s2.length() - 1)).concat(String.valueOf(": "))).concat(String.valueOf(super.a));
        }
        return s2;
    }
    
    public int a(final m m, final String s) {
        if ("users".equals(s) && m instanceof bg) {
            return ((bg)m).c - super.c;
        }
        return super.a(m, s);
    }
    
    public bo(final int n, final String s) {
        super(n, s);
        this.a = false;
        this.b = true;
    }
}
