// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

import java.util.NoSuchElementException;

public class K
{
    private A a;
    private int b;
    
    public K() {
        this.a = new A();
        this.b = 0;
        final A a = this.a;
        final A a2 = this.a;
        final A a3 = this.a;
        a2.c = a3;
        a.b = a3;
    }
    
    public Object a() {
        final Object a = this.a.b.a;
        this.a(this.a.b);
        return a;
    }
    
    public Object b() {
        final Object a = this.a.c.a;
        this.a(this.a.c);
        return a;
    }
    
    public void a(final Object o) {
        this.a(o, this.a.b);
    }
    
    public void b(final Object o) {
        this.a(o, this.a);
    }
    
    public int c() {
        return this.b;
    }
    
    public boolean c(final Object o) {
        this.a(o, this.a);
        return true;
    }
    
    public void d() {
        final A a = this.a;
        final A a2 = this.a;
        final A a3 = this.a;
        a2.c = a3;
        a.b = a3;
        this.b = 0;
    }
    
    public y e() {
        return new J(this, 0);
    }
    
    private A a(final Object o, final A a) {
        final A a2 = new A(o, a, a.c);
        a2.c.b = a2;
        a2.b.c = a2;
        ++this.b;
        return a2;
    }
    
    private void a(final A a) {
        if (a == this.a) {
            throw new NoSuchElementException();
        }
        a.c.b = a.b;
        a.b.c = a.c;
        --this.b;
    }
}
