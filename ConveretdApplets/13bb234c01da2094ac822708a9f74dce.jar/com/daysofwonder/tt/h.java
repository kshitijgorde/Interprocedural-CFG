// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt;

public class h
{
    protected int a;
    protected int b;
    protected int c;
    protected int d;
    protected int e;
    protected int f;
    
    public h(final int a, final int b) {
        this.c = -1;
        this.d = -1;
        this.a = a;
        this.b = b;
        this.e = this.a + this.b;
    }
    
    public int a(final Object o) {
        final h h = (h)o;
        final int n = this.e - h.e;
        return (n == 0) ? (this.a - h.a) : n;
    }
    
    public int b() {
        return this.a;
    }
    
    public int c() {
        return this.b;
    }
    
    public int d() {
        return this.c;
    }
    
    public int e() {
        return this.d;
    }
    
    public void a(final int c) {
        this.c = c;
    }
    
    public void b(final int d) {
        this.d = d;
    }
    
    public int f() {
        return this.f;
    }
    
    public void c(final int f) {
        this.f = f;
    }
}
