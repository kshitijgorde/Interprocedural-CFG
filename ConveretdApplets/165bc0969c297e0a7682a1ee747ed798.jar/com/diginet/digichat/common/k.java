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
    private long ddd;
    
    public int w() {
        return this.b;
    }
    
    public void h(final int b) {
        this.b = b;
    }
    
    public String x() {
        return this.c;
    }
    
    public void d(final String c) {
        this.c = c;
    }
    
    public final boolean i(final int n) {
        if (n < 0 || n > 127) {
            throw new ArrayIndexOutOfBoundsException(String.valueOf("flag must be between 0 and 127, inclusive: ").concat(String.valueOf(n)));
        }
        return ((n < 64) ? (this.d & 1L << n) : (this.ddd & 1L << n - 64)) != 0L;
    }
    
    private long bits(final long n, final int n2, final boolean b) {
        final long n3 = 1L << n2;
        return b ? (n | n3) : (n & ~n3);
    }
    
    public void a(final int n, final boolean b) throws ArrayIndexOutOfBoundsException {
        if (n < 0 || n > 127) {
            throw new ArrayIndexOutOfBoundsException(String.valueOf("flag must be between 0 and 127, inclusive: ").concat(String.valueOf(n)));
        }
        if (n < 64) {
            this.d = this.bits(this.d, n, b);
        }
        else {
            this.ddd = this.bits(this.ddd, n - 64, b);
        }
    }
    
    public void j(final int n) {
        this.a(n, true);
    }
    
    public void a(final long d) {
        this.d = d;
    }
    
    public void aaa(final long d, final long ddd) {
        this.d = d;
        this.ddd = ddd;
    }
    
    public long y() {
        return this.d;
    }
    
    public long yyy() {
        return this.ddd;
    }
    
    public void k(final int n) {
        this.a(n, false);
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(String.valueOf(this.c).concat(String.valueOf(" (ID="))).concat(String.valueOf(this.b))).concat(String.valueOf(")"));
    }
    
    public int a(final m m, final String s) {
        if ("name".equals(s) && m instanceof k) {
            return this.x().toLowerCase().compareTo(((k)m).x().toLowerCase());
        }
        if ("ID".equals(s) && m instanceof k) {
            return this.w() - ((k)m).w();
        }
        return 0;
    }
    
    public boolean a(final String s, final Object o) {
        return true;
    }
    
    public Object e(final String s) {
        if ("name".equals(s)) {
            return this.x();
        }
        if ("ID".equals(s)) {
            return new Integer(this.w());
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
        final long n2 = 0L;
        this.ddd = n2;
        this.d = n2;
        this.h(n);
        this.d(s);
    }
}
