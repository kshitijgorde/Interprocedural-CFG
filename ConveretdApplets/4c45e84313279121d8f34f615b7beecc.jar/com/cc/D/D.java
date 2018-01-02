// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.D;

import com.cc.B.E;
import A.A.B;

public class D
{
    public int K;
    public int I;
    public B M;
    private String B;
    public String E;
    private String C;
    public A F;
    private int H;
    public int D;
    public int J;
    public E L;
    private com.cc.B.A A;
    private static int G;
    
    public D(final int h, final com.cc.B.A a) {
        this.K = -1;
        this.I = -1;
        this.M = null;
        this.E = "";
        this.D = 0;
        this.H = h;
        this.A = a;
    }
    
    public void B(final String c) {
        this.C = c;
    }
    
    public String E() {
        if (this.C == null) {
            throw new IllegalStateException("url is not initialized");
        }
        return this.C;
    }
    
    public boolean F() {
        return this.C != null;
    }
    
    public boolean B(final E e) {
        return this.A.D(this.B).A(e);
    }
    
    public E C() {
        return this.A.D(this.B).G();
    }
    
    public void C(final String s) {
        char c = '\u0001';
        String s2 = this.A(c, false);
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 < '\u0005') {
                final String string = s2 + this.A(c, true);
                c = char1;
                s2 = string + this.A(c, false);
            }
            else {
                s2 += A.A.B.C(String.valueOf(char1));
            }
        }
        this.F = com.cc.D.A.A(A.A.E.A("<tag>" + (s2 + this.A(c, true)) + "</tag>").B());
    }
    
    private String A(final char c, final boolean b) {
        final String s = b ? "</" : "<";
        if (c - '\u0001' == '\0') {
            return s + "span>";
        }
        if (c - '\u0001' == '\u0001') {
            return s + "b>";
        }
        if (c - '\u0001' == '\u0002') {
            return s + "i>";
        }
        if (c - '\u0001' == '\u0003') {
            return b ? (s + "i>" + s + "b>") : (s + "b>" + s + "i>");
        }
        throw new RuntimeException("Unknown style modifier: " + c);
    }
    
    public String toString() {
        return "question=" + this.F + ", wordId = " + this.B;
    }
    
    public void B() {
        this.A.D(this.B).A(this.A);
    }
    
    public int D() {
        return this.H;
    }
    
    public void A(final String b) {
        this.B = b;
    }
    
    public String A() {
        return this.B;
    }
    
    public void A(final E e) {
        if (this.B == null) {
            ++com.cc.D.D.G;
            this.B = String.valueOf(com.cc.D.D.G);
            this.A.B(new com.cc.B.D(this.B));
        }
        this.A.D(this.B).B(e);
    }
    
    static {
        D.G = 0;
    }
}
