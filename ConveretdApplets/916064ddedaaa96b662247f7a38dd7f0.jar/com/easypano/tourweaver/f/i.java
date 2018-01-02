// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

import java.util.Enumeration;
import java.util.Vector;

public class i implements a
{
    a e;
    h f;
    String g;
    long h;
    long i;
    boolean j;
    boolean k;
    h l;
    d m;
    a n;
    long o;
    Vector p;
    
    public i() {
        this.h = 0L;
        this.i = 0L;
        this.j = true;
        this.k = true;
        this.o = 0L;
        this.p = new Vector();
    }
    
    public void a(final a e) {
        this.e = e;
    }
    
    public void a(final h f) {
        this.f = f;
    }
    
    public void a(final d m) {
        this.m = m;
    }
    
    public a j() {
        return this.e;
    }
    
    public h k() {
        return this.f;
    }
    
    public String getName() {
        return this.g;
    }
    
    public long h() {
        return this.h;
    }
    
    public void f() {
    }
    
    public void a() {
    }
    
    public boolean i() {
        final boolean j = this.j;
        if (!r.i && j) {}
        return j;
    }
    
    public void a(final double n) {
    }
    
    public void c() {
        this.k = true;
    }
    
    public void b() {
        this.j = false;
        this.k = false;
    }
    
    public d l() {
        return this.m;
    }
    
    public void setName(final String g) {
        this.g = g;
    }
    
    public void a(final long h) {
        this.h = h;
    }
    
    public void e() {
        this.j = true;
        this.k = true;
        final a e = this.e;
        if (!r.i) {
            if (e == null) {
                return;
            }
            final a e2 = this.e;
        }
        e.a(this.m);
    }
    
    public void b(final h l) {
        this.l = l;
    }
    
    public h m() {
        return this.l;
    }
    
    public long g() {
        return this.i;
    }
    
    public void b(final a n) {
        this.n = n;
    }
    
    public boolean c(final a a) {
        return false;
    }
    
    public a n() {
        return this.n;
    }
    
    public void b(final long o) {
        this.o = o;
    }
    
    public long o() {
        return this.o;
    }
    
    public synchronized void a(final b b) {
        Vector vector2;
        final Vector vector = vector2 = this.p;
        b b2 = b;
        if (!r.i) {
            if (vector.contains(b)) {
                return;
            }
            vector2 = this.p;
            b2 = b;
        }
        vector2.addElement(b2);
    }
    
    public synchronized void b(final b b) {
        this.p.removeElement(b);
    }
    
    protected synchronized void a(final h h, final int n) {
        final boolean i = r.i;
        final Enumeration<b> elements = this.p.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().updateRenderable(this, h, n);
            if (i) {
                break;
            }
        }
    }
    
    protected synchronized void a(final a a, final int n) {
        final boolean i = r.i;
        final Enumeration<b> elements = this.p.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().updateAnimation(this, a, n);
            if (i) {
                break;
            }
        }
    }
    
    protected synchronized void b(final double n) {
        final boolean i = r.i;
        final Enumeration<b> elements = this.p.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().updateProgress(this, n);
            if (i) {
                break;
            }
        }
    }
    
    protected synchronized void b(final String s) {
        final boolean i = r.i;
        final Enumeration<b> elements = this.p.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().pause(s);
            if (i) {
                break;
            }
        }
    }
    
    protected synchronized void c(final String s) {
        final boolean i = r.i;
        final Enumeration<b> elements = this.p.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().movieStoped(s);
            if (i) {
                break;
            }
        }
    }
    
    public boolean d(final a a) {
        return false;
    }
    
    public boolean p() {
        return this.j;
    }
}
