// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

import com.diginet.digichat.awt.j;
import com.diginet.digichat.util.i;

public abstract class DigiItem implements i, Cloneable, j
{
    public boolean a;
    private int b;
    private String c;
    private long[] d;
    
    public final int x() {
        return this.b;
    }
    
    public final void t(final int b) {
        this.b = b;
    }
    
    public final String getName() {
        return this.c;
    }
    
    public final void g(final String c) {
        this.c = c;
    }
    
    public static String a(final long[] array) {
        String s = "";
        for (int i = 0; i < 128; ++i) {
            s += ((i < 64) ? (((array[0] & 1L << i) != 0x0L) ? "1" : "0") : (((array[1] & 1L << i - 64) != 0x0L) ? "1" : "0"));
            if ((i + 1) % 8 == 0) {
                s += " ";
            }
            if ((i + 1) % 32 == 0) {
                s += "\n";
            }
        }
        return s;
    }
    
    public final String y() {
        return a(this.z());
    }
    
    public String toString() {
        return this.c + " (ID=" + this.b + ")\n" + this.y();
    }
    
    public final boolean u(final int n) {
        if (n < 0 || n > 127) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 127, inclusive: " + n);
        }
        final long n2 = (n < 64) ? (1L << n) : (1L << n - 64);
        if (n < 64) {
            return (this.d[0] & n2) != 0x0L;
        }
        return (this.d[1] & n2) != 0x0L;
    }
    
    public final void a(final int n, final boolean b) throws ArrayIndexOutOfBoundsException {
        if (n < 0 || n > 127) {
            throw new ArrayIndexOutOfBoundsException("flag must be between 0 and 127, inclusive: " + n);
        }
        final long n2 = (n < 64) ? (1L << n) : (1L << n - 64);
        if (n < 64) {
            if (b) {
                final long[] d = this.d;
                final int n3 = 0;
                d[n3] |= n2;
            }
            else {
                final long[] d2 = this.d;
                final int n4 = 0;
                d2[n4] &= (n2 ^ -1L);
            }
        }
        else if (b) {
            final long[] d3 = this.d;
            final int n5 = 1;
            d3[n5] |= n2;
        }
        else {
            final long[] d4 = this.d;
            final int n6 = 1;
            d4[n6] &= (n2 ^ -1L);
        }
    }
    
    public final void b(final long[] d) {
        this.d = d;
    }
    
    public final long[] z() {
        return this.d;
    }
    
    public final void clearFlag(final int n) {
        this.a(n, false);
    }
    
    public int a(final j j, final String s) {
        if ("name".equals(s) && j instanceof DigiItem) {
            return this.getName().toLowerCase().compareTo(((DigiItem)j).getName().toLowerCase());
        }
        if ("ID".equals(s) && j instanceof DigiItem) {
            return this.x() - ((DigiItem)j).x();
        }
        return 0;
    }
    
    public Object h(final String s) {
        if ("name".equals(s)) {
            return this.getName();
        }
        if ("ID".equals(s)) {
            return new Integer(this.x());
        }
        return null;
    }
    
    public String i(final String s) {
        return null;
    }
    
    public final synchronized Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    public DigiItem(final int n, final String s) {
        this.a = true;
        this.d = new long[] { 0L, 0L };
        this.t(n);
        this.g(s);
    }
}
