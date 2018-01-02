// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

import com.diginet.digichat.awt.m;
import com.diginet.digichat.util.l;

public abstract class k implements l, Cloneable, m
{
    public boolean a;
    private int b;
    private String c;
    private long d;
    
    public int q() {
        return this.b;
    }
    
    public void h(final int b) {
        this.b = b;
    }
    
    public String r() {
        return this.c;
    }
    
    public void d(final String c) {
        this.c = c;
    }
    
    public final boolean i(final int n) {
        if (n < 0 || n > 63) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 63, inclusive: " + n);
        }
        return (this.d & 1L << n) != 0x0L;
    }
    
    public void a(final int n, final boolean b) throws ArrayIndexOutOfBoundsException {
        if (n < 0 || n > 63) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 63, inclusive: " + n);
        }
        final long n2 = 1L << n;
        if (b) {
            this.d |= n2;
        }
        else {
            this.d &= (n2 ^ -1L);
        }
    }
    
    public void a(final long d) {
        this.d = d;
    }
    
    public long s() {
        return this.d;
    }
    
    public String toString() {
        return this.c + " (ID=" + this.b + ")";
    }
    
    public int a(final m m, final String s) {
        if ("name".equals(s) && m instanceof k) {
            return this.r().toLowerCase().compareTo(((k)m).r().toLowerCase());
        }
        if ("ID".equals(s) && m instanceof k) {
            return this.q() - ((k)m).q();
        }
        return 0;
    }
    
    public Object e(final String s) {
        if ("name".equals(s)) {
            return this.r();
        }
        if ("ID".equals(s)) {
            return new Integer(this.q());
        }
        return null;
    }
    
    public String f(final String s) {
        return null;
    }
    
    public synchronized Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    public k(final int n, final String s) {
        this.a = true;
        this.d = 0L;
        this.h(n);
        this.d(s);
    }
}
