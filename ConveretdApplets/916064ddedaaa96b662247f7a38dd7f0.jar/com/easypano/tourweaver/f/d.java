// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

import java.awt.Image;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.Shape;
import java.awt.Rectangle;

public class d
{
    int a;
    int b;
    double c;
    double d;
    String e;
    Rectangle f;
    Shape g;
    double h;
    double i;
    double j;
    Vector k;
    Vector l;
    ab m;
    h n;
    boolean o;
    Vector p;
    int q;
    int r;
    boolean s;
    int t;
    boolean u;
    double v;
    double w;
    boolean x;
    
    public d() {
        this.a = -1;
        this.b = -1;
        this.c = 0.0;
        this.d = 0.0;
        this.e = "";
        this.f = new Rectangle();
        this.h = 0.0;
        this.i = 0.0;
        this.j = 0.0;
        this.k = new Vector();
        this.l = new Vector();
        this.o = false;
        this.p = new Vector();
        this.q = 1;
        this.r = 0;
        this.s = false;
        this.u = false;
        this.w = 0.0;
        this.x = false;
    }
    
    public void a(final boolean s) {
        this.s = s;
    }
    
    public boolean a() {
        return this.s;
    }
    
    public void a(final int q) {
        this.q = q;
    }
    
    public int b() {
        return this.q;
    }
    
    public void a(final double h, final double i, final double j) {
        this.h = h;
        this.i = i;
        this.j = j;
        this.g();
    }
    
    public void c() {
        final boolean i = com.easypano.tourweaver.f.r.i;
        final Enumeration t = this.t();
        while (t.hasMoreElements()) {
            final d d = t.nextElement();
            d.a(this.h, this.i, this.j);
            d.u = this.u;
            if (i) {
                break;
            }
        }
    }
    
    public void b(final int t) {
        this.t = t;
    }
    
    public int d() {
        return this.t;
    }
    
    public void e() {
        final boolean i = com.easypano.tourweaver.f.r.i;
        final Enumeration t = this.t();
        while (t.hasMoreElements()) {
            final d d = t.nextElement();
            d.b(this.c);
            d.d(this.w);
            d.c(this.v);
            if (i) {
                break;
            }
        }
    }
    
    public void b(final double n, final double n2, final double n3) {
        this.h += n;
        this.i += n2;
        this.j += n3;
        this.g();
    }
    
    public double f() {
        return this.d;
    }
    
    private void g() {
        this.d = this.w - this.h;
        this.v = this.j;
    }
    
    public double h() {
        return this.h;
    }
    
    public double i() {
        return this.i;
    }
    
    public double j() {
        return this.j;
    }
    
    public void a(final ab m) {
        this.m = m;
    }
    
    public ab k() {
        return this.m;
    }
    
    public void a(final h n) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        h h3;
        h h2;
        final h h = h2 = (h3 = this.n);
        if (!i) {
            if (h == n) {
                return;
            }
            final h h4;
            h2 = (h4 = (h3 = this.n));
        }
        if (!i) {
            if (h != null) {
                this.n.b(this);
            }
            this.n = n;
            this.r = 0;
            h3 = n;
            h2 = n;
        }
        if (!i) {
            if (!(h2 instanceof y)) {
                return;
            }
            this.u = false;
            h3 = n;
        }
        if (((y)h3).j()) {
            this.u = true;
        }
    }
    
    public boolean l() {
        return this.u;
    }
    
    public h m() {
        return this.n;
    }
    
    public void b(final boolean o) {
        this.o = o;
    }
    
    public boolean n() {
        return this.o;
    }
    
    public h o() {
        return this.n;
    }
    
    public int p() {
        return this.k.size();
    }
    
    public void q() {
        this.k.removeAllElements();
    }
    
    public void a(final Image image) {
        this.k.addElement(image);
    }
    
    public Image r() {
        Object o;
        final Vector vector = (Vector)(o = this.k);
        if (!com.easypano.tourweaver.f.r.i) {
            if (vector.isEmpty()) {
                return null;
            }
            o = this.k.elementAt(0);
        }
        final Image image = (Image)o;
        this.k.removeElementAt(0);
        return image;
    }
    
    public void a(final e e) {
        this.l.addElement(e);
    }
    
    public void a(final Vector l) {
        this.l = l;
    }
    
    public Vector s() {
        return this.l;
    }
    
    public void a(final d d) {
        Vector vector2;
        final Vector vector = vector2 = this.p;
        d d2 = d;
        if (!com.easypano.tourweaver.f.r.i) {
            if (vector.contains(d)) {
                return;
            }
            d.u = this.u;
            vector2 = this.p;
            d2 = d;
        }
        vector2.addElement(d2);
        d.a(this);
    }
    
    public Enumeration t() {
        return this.p.elements();
    }
    
    public void a(final Shape g) {
        this.g = g;
        this.f = g.getBounds();
    }
    
    public Rectangle u() {
        return this.f;
    }
    
    public String v() {
        return this.e;
    }
    
    public Shape w() {
        return this.g;
    }
    
    public void a(final int a, final int b) {
        this.a = a;
        this.b = b;
    }
    
    public int x() {
        return this.a;
    }
    
    public int y() {
        return this.b;
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        this.f.setBounds(n, n2, n3, n4);
        this.x = true;
    }
    
    public void a(final Rectangle bounds) {
        this.f.setBounds(bounds);
    }
    
    public void a(final String e) {
        this.e = e;
    }
    
    public void a(final double n) {
        this.c += n;
    }
    
    public void b(final double c) {
        this.c = c;
        this.h = this.w - c;
    }
    
    public double z() {
        return this.c;
    }
    
    public void c(final int r) {
        this.r = r;
    }
    
    public int A() {
        return this.r;
    }
    
    public void c(final double v) {
        this.v = v;
    }
    
    public double B() {
        return this.v;
    }
    
    public void d(final double w) {
        this.w = w;
    }
    
    public double C() {
        return this.w;
    }
    
    public boolean D() {
        return this.x;
    }
    
    public void c(final boolean x) {
        this.x = x;
    }
}
