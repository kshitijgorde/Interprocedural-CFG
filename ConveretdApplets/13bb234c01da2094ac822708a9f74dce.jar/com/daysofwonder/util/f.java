// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

import java.util.NoSuchElementException;

public class f
{
    private K c;
    private boolean d;
    private int e;
    private int f;
    private final int g;
    private final N h;
    private final Object i;
    public static final N a;
    public static final N b;
    
    public f() {
        this(0, com.daysofwonder.util.f.a);
    }
    
    public f(final int g, final N h) {
        this.c = new K();
        this.d = false;
        this.e = 0;
        this.f = 0;
        this.g = g;
        this.h = h;
        if (this.h == com.daysofwonder.util.f.b) {
            this.i = new Object();
        }
        else {
            this.i = null;
        }
    }
    
    public final synchronized void a(final Object o) {
        if (this.d) {
            throw new p(this);
        }
        if (this.g != 0 && this.c.c() >= this.g) {
            if (this.h == com.daysofwonder.util.f.a) {
                this.c.b(o);
                this.notify();
            }
            throw new s(this);
        }
        this.c.b(o);
        this.notify();
    }
    
    public final synchronized Object a(final long n) {
        final long n2 = (n == 0L) ? Long.MAX_VALUE : (w.a() + n);
        if (this.d) {
            throw new p(this);
        }
        try {
            if (this.c.c() <= 0) {
                ++this.e;
                while (this.c.c() <= 0) {
                    if (n != 0L) {
                        this.wait(n);
                    }
                    else {
                        this.wait();
                    }
                    if (w.a() > n2) {
                        --this.e;
                        throw new D(this);
                    }
                    if (this.d) {
                        --this.e;
                        throw new p(this);
                    }
                }
                --this.e;
            }
            if (this.i != null) {
                this.notify();
            }
            return this.c.a();
        }
        catch (NoSuchElementException ex) {
            throw new Error("Internal error TBBlockingQueue");
        }
    }
    
    public final synchronized Object a() {
        return this.a(0L);
    }
    
    public synchronized void b() {
        this.c.d();
        this.notifyAll();
    }
    
    public synchronized void c() {
        this.d = true;
        this.c = null;
        this.notifyAll();
    }
    
    static {
        a = null;
        b = new N(null);
    }
}
