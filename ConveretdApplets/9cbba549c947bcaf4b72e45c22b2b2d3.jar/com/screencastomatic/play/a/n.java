// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.a;

import javax.sound.sampled.AudioFormat;
import com.screencastomatic.play.stream.e;
import com.screencastomatic.play.stream.r;

public class n
{
    private boolean b;
    private com.screencastomatic.play.n c;
    private Thread d;
    private Thread e;
    private long f;
    private j g;
    private r h;
    private long i;
    private e j;
    private boolean k;
    private boolean l;
    public boolean a;
    
    public n(final com.screencastomatic.play.n n, final String s, final long i, final int n2) {
        this.a = true;
        this.i = i;
        this.c = new i(this, n);
        this.j = new e(s, i, n2);
        this.f = this.j.b();
        System.out.println("Duration (msec): " + this.f);
        AudioFormat a = this.j.a();
        System.out.println("Audio: " + ((a != null) ? a.toString() : "none"));
        this.a = (a != null);
        if (a != null) {
            try {
                final g g = new g(this, a);
                this.g = g;
                this.h = g;
            }
            catch (Exception ex) {
                a = null;
                ex.printStackTrace();
            }
        }
        if (a == null) {
            final h h = new h(this);
            this.g = h;
            this.h = h;
        }
        (this.d = new Thread(new b(this, null))).start();
        (this.e = new Thread(new p(this, null))).start();
    }
    
    public long a() {
        return this.f;
    }
    
    public synchronized void b() {
        if (this.k) {
            return;
        }
        this.k = true;
        this.g.c();
        this.notify();
    }
    
    public synchronized boolean c() {
        return this.k;
    }
    
    public synchronized boolean d() {
        return this.b;
    }
    
    public synchronized boolean e() {
        return this.l;
    }
    
    public boolean f() {
        return this.a;
    }
    
    public synchronized void a(final boolean b) {
        if ((this.b || !this.k) && !b) {
            this.notify();
        }
        this.b = b;
    }
    
    public synchronized void a(final int n) {
        this.h.a(n);
    }
    
    public void g() {
        this.k = false;
        this.e = null;
        this.d = null;
        this.a(false);
        this.g.d();
    }
    
    public long h() {
        return this.l ? this.a() : this.g.b();
    }
    
    public com.screencastomatic.play.n i() {
        return this.c;
    }
    
    public long j() {
        return this.i;
    }
    
    public synchronized boolean a(final c c) {
        if (this.k && !this.b) {
            return false;
        }
        if (c != null) {
            c.a();
        }
        while (this.d != null && (!this.k || this.b)) {
            System.out.println("Playing starting wait...");
            this.wait();
            System.out.println("Playing ending wait...");
        }
        if (c != null) {
            c.b();
        }
        return true;
    }
}
