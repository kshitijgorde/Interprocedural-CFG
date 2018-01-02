// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

import java.util.NoSuchElementException;

class J implements g
{
    private A b;
    private A c;
    private int d;
    final /* synthetic */ K a;
    
    J(final K a, final int n) {
        this.a = a;
        this.b = this.a.a;
        if (n < a.b >> 1) {
            this.c = a.a.b;
            this.d = 0;
            while (this.d < n) {
                this.c = this.c.b;
                ++this.d;
            }
        }
        else {
            this.c = a.a;
            this.d = a.b;
            while (this.d > n) {
                this.c = this.c.c;
                --this.d;
            }
        }
    }
    
    public boolean a() {
        return this.d != this.a.b;
    }
    
    public Object b() {
        if (this.d == this.a.b) {
            throw new NoSuchElementException();
        }
        this.b = this.c;
        this.c = this.c.b;
        ++this.d;
        return this.b.a;
    }
    
    public void c() {
        try {
            this.a.a(this.b);
        }
        catch (NoSuchElementException ex) {
            throw new IllegalStateException();
        }
        if (this.c == this.b) {
            this.c = this.b.b;
        }
        else {
            --this.d;
        }
        this.b = this.a.a;
    }
}
