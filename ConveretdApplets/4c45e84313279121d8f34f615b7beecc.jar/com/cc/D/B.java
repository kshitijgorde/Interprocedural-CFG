// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.D;

public class B
{
    public A E;
    public static final String A = "normal";
    public static final String C = "by-position";
    public boolean G;
    public boolean B;
    public String F;
    public D[] D;
    
    public B() {
        this.G = false;
        this.B = false;
        this.F = "normal";
        this.D = new D[0];
    }
    
    public boolean A() {
        return !"normal".equals(this.F) && !"by-position".equals(this.F);
    }
}
