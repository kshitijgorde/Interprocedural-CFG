// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import com.easypano.tw.d.a;
import java.awt.Component;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Image;
import java.awt.Dimension;

public class o extends f implements Cloneable
{
    public static final Dimension l;
    public static final Dimension m;
    protected int n;
    protected Image o;
    protected Image p;
    protected Image q;
    protected h r;
    protected dq s;
    private double t;
    private int u;
    private int v;
    private Point w;
    
    static {
        l = new Dimension(17, 17);
        m = new Dimension(15, 15);
    }
    
    public o() {
        this.n = 42;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = new h();
        this.s = null;
        this.t = 0.0;
        this.u = 100;
        this.v = 0;
        this.w = null;
        this.setLayout(null);
        this.setSize(com.easypano.tw.o.l);
        this.r.setSize(com.easypano.tw.o.m);
        this.addMouseListener(new bd(this));
        this.r.addMouseListener(new be(this));
        this.r.addMouseMotionListener(new bl(this));
        this.add(this.r);
    }
    
    public Object clone() {
        o o = null;
        try {
            o = (o)super.clone();
            o.r = new h();
            ((a)o.r.a()).d(this.o);
            ((a)o.r.a()).e(this.p);
            ((a)o.r.a()).f(this.q);
        }
        catch (CloneNotSupportedException ex) {}
        return o;
    }
    
    public void b(final int n) {
        int n2 = n;
        final int n3 = 42;
        Label_0018: {
            if (!com.easypano.tw.h.q) {
                if (n == n3) {
                    break Label_0018;
                }
                n2 = n;
            }
            if (n2 != n3) {
                return;
            }
        }
        this.n = n;
    }
    
    public void a(final Image o) {
        ((a)this.r.a()).d(o);
        this.o = o;
    }
    
    public void b(final Image p) {
        ((a)this.r.a()).e(p);
        this.p = p;
    }
    
    public void c(final Image q) {
        ((a)this.r.a()).f(q);
        this.q = q;
    }
    
    public void a(final dq s) {
        this.s = s;
    }
    
    public void c(final int u) {
        this.u = u;
        this.e();
        this.f();
        this.s.a(this.t);
    }
    
    public void d(final int v) {
        this.v = v;
        this.e();
        this.f();
        this.s.a(this.t);
    }
    
    public void a(final double t) {
        this.t = t;
        this.e();
        this.f();
        this.s.a(this.t);
    }
    
    private void e() {
        final boolean q = com.easypano.tw.h.q;
        double v;
        final int n = (int)(v = this.v);
        if (!q) {
            if (n > this.u) {
                this.v = this.u;
            }
            final int n2;
            v = (n2 = dcmpl(this.t, (double)this.u));
        }
        o o = null;
        Label_0071: {
            if (!q) {
                if (n > 0) {
                    this.t = this.u;
                }
                o = this;
                if (q) {
                    break Label_0071;
                }
                v = dcmpg(this.t, (double)this.v);
            }
            if (v >= 0) {
                return;
            }
            o = this;
        }
        o.t = this.v;
    }
    
    protected void f() {
        final boolean q = com.easypano.tw.h.q;
        final int n = this.u - this.v;
        final int n2 = this.n;
        Label_0145: {
            if (!q) {
                switch (n2) {
                    case 42: {
                        final int n3 = this.getBounds().width - this.r.getBounds().width - 2;
                        break;
                    }
                    case 41: {
                        break Label_0145;
                    }
                }
            }
            final int n4 = n2;
            if (!q) {
                if (n > 0) {
                    this.r.setLocation((int)(n4 * (this.t - this.v) / n) + 1, (com.easypano.tw.o.l.height - com.easypano.tw.o.m.height) / 2);
                    if (!q) {
                        return;
                    }
                }
                this.r.setLocation(n4, (com.easypano.tw.o.l.height - com.easypano.tw.o.m.height) / 2);
            }
            if (!q) {
                return;
            }
        }
        final int n5 = this.getBounds().height - this.r.getBounds().height - 2;
        Label_0219: {
            if (!q) {
                if (n <= 0) {
                    break Label_0219;
                }
                this.r.setLocation((com.easypano.tw.o.l.width - com.easypano.tw.o.m.width) / 2, (int)(n5 * (this.t - this.v) / n) + 1);
            }
            if (!q) {
                return;
            }
        }
        this.r.setLocation((com.easypano.tw.o.l.width - com.easypano.tw.o.m.width) / 2, n5);
    }
    
    public Dimension getPreferredSize() {
        return com.easypano.tw.o.l;
    }
    
    public h g() {
        return this.r;
    }
    
    public void b(final boolean b) {
        super.b(b);
        this.r.b(b);
    }
    
    public void a(final du du) {
        super.a(du);
        this.r.a(du);
    }
    
    public void destroyResource() {
        super.destroyResource();
        this.o = null;
        this.p = null;
        this.q = null;
        if (this.r != null) {
            this.r.destroyResource();
            this.r = null;
        }
        this.s = null;
    }
    
    static double a(final o o) {
        return o.t;
    }
    
    static void a(final o o, final Point w) {
        o.w = w;
    }
    
    static int b(final o o) {
        return o.u;
    }
    
    static int c(final o o) {
        return o.v;
    }
    
    static Point d(final o o) {
        return o.w;
    }
    
    static void a(final o o, final double t) {
        o.t = t;
    }
}
