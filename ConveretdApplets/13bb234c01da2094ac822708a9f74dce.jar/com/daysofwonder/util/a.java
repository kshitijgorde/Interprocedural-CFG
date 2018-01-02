// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

public class a
{
    public static final d a;
    public static final d b;
    public static final d c;
    private d d;
    private long e;
    private long f;
    private boolean g;
    private volatile B h;
    private volatile x i;
    private volatile z j;
    
    public a(final d d, final long e, final boolean g) {
        this.h = null;
        this.i = null;
        this.j = null;
        this.d = d;
        this.e = e;
        this.g = g;
    }
    
    public synchronized void a() {
        if (this.i != null) {
            this.i.a();
            this.i.interrupt();
        }
        (this.i = new x(this)).setDaemon(this.g);
        this.i.start();
    }
    
    public synchronized void b() {
        if (this.i != null) {
            this.i.interrupt();
        }
        this.i = null;
        this.notifyAll();
    }
    
    public synchronized void a(final B b) {
        this.h = L.a(this.h, b);
        if (this.j == null) {
            t.a("creating execute thread");
            (this.j = new z(this)).start();
        }
    }
    
    public synchronized void b(final B b) {
        t.a("task removing");
        this.h = L.b(this.h, b);
        t.a("tasklist: " + this.h + " exe thread: " + this.j);
        if (this.h == null && this.j != null) {
            t.a("destroying execute thread");
            this.j.interrupt();
            this.j = null;
        }
    }
    
    private synchronized void c() {
        this.e = this.f;
    }
    
    private synchronized void d() {
        if (this.j != null) {
            this.j.a(new h(this));
        }
        this.notifyAll();
    }
    
    private synchronized void a(final x x) {
        if (this.i == x) {
            this.i = null;
        }
    }
    
    static {
        a = null;
        b = new d(null);
        c = new d(null);
    }
}
