// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.Random;
import java.io.Serializable;

public final class bz implements Serializable
{
    private long o;
    private long n;
    private long p;
    private long q;
    
    private final String a(final String s, final int n) {
        final Random random = new Random();
        final StringBuffer sb = new StringBuffer(s);
        for (int i = s.length(); i < n; ++i) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }
    
    public final String a() {
        if (this.p == 0L && this.q == 0L) {
            return "";
        }
        return this.a(new Long(this.q).toString(), 8) + this.a(new Long(this.p).toString(), 8);
    }
    
    public final String d() {
        if (this.o == 0L && this.n == 0L) {
            return "";
        }
        return this.a(new Long(this.n).toString(), 8) + this.a(new Long(this.o).toString(), 8);
    }
    
    public final int g() {
        final int n = (int)(this.o & 0x3FFL);
        final int n2 = (int)(this.q & 0x3FFL);
        if (n2 == n) {
            return n2;
        }
        return -999;
    }
    
    public final int i() {
        final int n = (int)(this.n & 0x3FFL);
        final int n2 = ((int)(this.q >> 11 & 0x3FFL) ^ (int)(this.q & 0x3FFL)) & 0x3FF;
        if (n2 == n) {
            return n2;
        }
        return -999;
    }
    
    public final int c() {
        final int n = ((int)(this.o >> 11 & 0x3FFL) ^ (int)(this.n & 0x3FFL)) & 0x3FF;
        final int n2 = (int)(this.p >> 13 & 0x3FFL);
        if (n2 == n) {
            return n2;
        }
        return -999;
    }
    
    public final boolean l() {
        try {
            this.m();
            this.k();
            return this.i() != -999 && this.c() != -999 && this.g() != -999;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public final boolean k() {
        final long n = (int)(this.o >> 10 & 0x1L);
        final long n2 = (int)(this.q >> 10 & 0x1L);
        if (n2 == n) {
            return n2 == 0L;
        }
        throw new IllegalStateException(ao.e("Illegal key"));
    }
    
    public final boolean m() {
        final int n = (int)(this.o >> 21 & 0x1L);
        final int n2 = (int)(this.p >> 23 & 0x1L);
        if (n2 == n) {
            return n2 == 0;
        }
        throw new IllegalStateException(ao.e("Illegal key"));
    }
    
    public final synchronized String toString() {
        final String a = this.a();
        if (a.equals("")) {
            return "";
        }
        String s = a.substring(0, a.length() - 3);
        String s2 = a.substring(a.length() - 3);
        if (s2.length() < 3) {
            s2 = "0000".substring(0, 3 - s2.length()) + s2;
        }
        if (s.length() < 5) {
            s = "0000".substring(0, 5 - s.length()) + s;
        }
        return "DC-" + s2 + "-" + s;
    }
    
    public bz() {
    }
    
    public bz(final String s, final String s2) {
        final String string = s.substring(7) + s.substring(3, 6);
        final int length = s2.length();
        final int length2 = string.length();
        this.o = new Long(s2.substring(length - 8));
        this.n = new Long(s2.substring(length - 18, length - 8));
        this.p = new Long(string.substring(length2 - 8));
        this.q = new Long(string.substring(length2 - 16, length2 - 8));
    }
}
