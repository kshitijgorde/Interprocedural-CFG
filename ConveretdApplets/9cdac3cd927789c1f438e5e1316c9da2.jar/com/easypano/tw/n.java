// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import com.easypano.tw.c.a;
import java.awt.Component;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Image;
import java.awt.Dimension;

public class n extends e implements Cloneable
{
    public static final Dimension l;
    public static final Dimension m;
    protected int n;
    protected Image o;
    protected Image p;
    protected Image q;
    protected g r;
    protected dq s;
    private double t;
    private int u;
    private int v;
    private Point w;
    
    static {
        l = new Dimension(17, 17);
        m = new Dimension(15, 15);
    }
    
    public n() {
        this.n = 42;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = new g();
        this.s = null;
        this.t = 0.0;
        this.u = 100;
        this.v = 0;
        this.w = null;
        this.setLayout(null);
        this.setSize(com.easypano.tw.n.l);
        this.r.setSize(com.easypano.tw.n.m);
        this.addMouseListener(new bd(this));
        this.r.addMouseListener(new be(this));
        this.r.addMouseMotionListener(new bm(this));
        this.add(this.r);
    }
    
    public Object clone() {
        n n = null;
        try {
            n = (n)super.clone();
            n.r = new g();
            ((a)n.r.a()).d(this.o);
            ((a)n.r.a()).e(this.p);
            ((a)n.r.a()).f(this.q);
        }
        catch (CloneNotSupportedException ex) {}
        return n;
    }
    
    public void b(final int n) {
        int n2 = n;
        final int n3 = 42;
        Label_0018: {
            if (!com.easypano.tw.g.q) {
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
        final boolean q = com.easypano.tw.g.q;
        double v;
        final int n = (int)(v = this.v);
        if (!q) {
            if (n > this.u) {
                this.v = this.u;
            }
            final int n2;
            v = (n2 = dcmpl(this.t, (double)this.u));
        }
        n n3 = null;
        Label_0071: {
            if (!q) {
                if (n > 0) {
                    this.t = this.u;
                }
                n3 = this;
                if (q) {
                    break Label_0071;
                }
                v = dcmpg(this.t, (double)this.v);
            }
            if (v >= 0) {
                return;
            }
            n3 = this;
        }
        n3.t = this.v;
    }
    
    protected void f() {
        final boolean q = com.easypano.tw.g.q;
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
                    this.r.setLocation((int)(n4 * (this.t - this.v) / n) + 1, (com.easypano.tw.n.l.height - com.easypano.tw.n.m.height) / 2);
                    if (!q) {
                        return;
                    }
                }
                this.r.setLocation(n4, (com.easypano.tw.n.l.height - com.easypano.tw.n.m.height) / 2);
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
                this.r.setLocation((com.easypano.tw.n.l.width - com.easypano.tw.n.m.width) / 2, (int)(n5 * (this.t - this.v) / n) + 1);
            }
            if (!q) {
                return;
            }
        }
        this.r.setLocation((com.easypano.tw.n.l.width - com.easypano.tw.n.m.width) / 2, n5);
    }
    
    public Dimension getPreferredSize() {
        return com.easypano.tw.n.l;
    }
    
    public g g() {
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
    
    static double a(final n n) {
        return n.t;
    }
    
    static void a(final n n, final Point w) {
        n.w = w;
    }
    
    static int b(final n n) {
        return n.u;
    }
    
    static int c(final n n) {
        return n.v;
    }
    
    static Point d(final n n) {
        return n.w;
    }
    
    static void a(final n n, final double t) {
        n.t = t;
    }
}
