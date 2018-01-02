// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt;

import com.daysofwonder.a.o;

public class i extends o
{
    public static final i a;
    public static final String[] b;
    protected int c;
    protected int d;
    
    public i(final int c) {
        super((c == -1) ? "back" : i.b[c - 1]);
        this.c = c;
        this.d = -1;
    }
    
    public boolean equals(final Object o) {
        return o instanceof i && this.c == ((i)o).c;
    }
    
    public int b() {
        return this.c;
    }
    
    public boolean c() {
        return this.c == 9;
    }
    
    public boolean d() {
        return this.c == 10;
    }
    
    public static i a(final int n) {
        return new i(n);
    }
    
    public int a() {
        return (this.c == -1) ? this.c : (this.c - 1);
    }
    
    public String toString() {
        return this.f() + "[" + this.c + "]";
    }
    
    public void b(final int d) {
        this.d = d;
    }
    
    public int e() {
        return this.d;
    }
    
    static {
        b = new String[] { "purple", "white", "blue", "yellow", "brown", "black", "red", "green", "loco", "tunnel" };
        a = new i(-1);
    }
}
