// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

public class a6 extends DigiItem
{
    public String a;
    public String b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public long h;
    public long i;
    public long j;
    public v k;
    
    public final synchronized boolean a() {
        return this.u(61);
    }
    
    public final boolean b() {
        return this.u(45) && this.j > System.currentTimeMillis();
    }
    
    public a6(final int n, final String s) {
        super(n, s);
        this.c = 0;
        this.d = -999;
        this.e = -999;
        this.f = -999;
        this.g = -999;
        this.h = Long.MAX_VALUE;
        this.i = Long.MIN_VALUE;
        this.j = Long.MIN_VALUE;
    }
}
