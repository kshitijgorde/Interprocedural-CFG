// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import com.easypano.tw.c.p;
import com.easypano.tw.c.j;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.Point;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Component;

public class dv
{
    public static final int a = 1;
    public static final int b = 2;
    protected static final int c = 20;
    protected static final int d = 14;
    private Component e;
    private Dimension f;
    protected e g;
    private Image h;
    private Image i;
    private Image j;
    private Point k;
    private Point l;
    private Point m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private ActionListener s;
    private boolean t;
    private int u;
    private Color v;
    private Image w;
    private Point x;
    private Dimension y;
    private boolean z;
    
    public dv() {
        this.e = null;
        this.f = new Dimension(0, 0);
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = new Point(-1000, -1000);
        this.l = new Point(-1000, -1000);
        this.m = new Point(-1000, -1000);
        this.s = null;
        this.t = false;
        this.u = 2;
        this.v = Color.red;
        this.y = new Dimension(20, 20);
        this.z = true;
    }
    
    public void a(final Component e) {
        this.e = e;
        dv dv = this;
        if (!com.easypano.tw.g.q) {
            if (this.e == null) {
                return;
            }
            dv = this;
        }
        dv.f = this.e.getSize();
    }
    
    public boolean a(int r, final int n, final int n2) {
        final boolean q = com.easypano.tw.g.q;
        int n3 = n;
        int n7 = 0;
        int n6 = 0;
        int n5 = 0;
        Label_0131: {
            Label_0127: {
                Label_0126: {
                    if (!q) {
                        if (n > this.n) {
                            n3 = n;
                            if (q) {
                                break Label_0126;
                            }
                            if (n < this.n + this.p) {
                                n3 = n2;
                                if (q) {
                                    break Label_0126;
                                }
                                if (n2 > this.o) {
                                    n3 = n2;
                                    if (q) {
                                        break Label_0126;
                                    }
                                    if (n2 < this.o + this.q) {
                                        this.t = true;
                                        this.e.setCursor(Cursor.getPredefinedCursor(12));
                                        final int n4 = n5 = (n6 = (n7 = ((this.e instanceof db) ? 1 : 0)));
                                        if (q) {
                                            break Label_0131;
                                        }
                                        if (n4 == 0) {
                                            break Label_0127;
                                        }
                                        ((db)this.e).a(this.g);
                                        if (!q) {
                                            break Label_0127;
                                        }
                                    }
                                }
                            }
                        }
                        this.t = false;
                        n3 = -1;
                    }
                }
                r = n3;
            }
            n6 = (n5 = (n7 = this.r));
        }
        if (!q) {
            if (n5 != r) {
                this.r = r;
                this.b();
            }
            n7 = (n6 = r);
        }
        if (!q) {
            Label_0206: {
                switch (n6) {
                    case 502: {
                        this.s.actionPerformed(new ActionEvent(this.e, 1001, ""));
                        if (q) {
                            break Label_0206;
                        }
                        break;
                    }
                    case 501: {
                        final boolean b = (n7 = ((this.e instanceof db) ? 1 : 0)) != 0;
                        if (q) {
                            return n7 != 0;
                        }
                        if (b) {
                            ((db)this.e).a((e)null);
                            break;
                        }
                        break;
                    }
                }
            }
            n7 = (this.t ? 1 : 0);
        }
        return n7 != 0;
    }
    
    public void a(final int n, final int o, final int p4, final int q) {
        this.n = n;
        this.o = o;
        this.p = p4;
        this.q = q;
        this.c();
    }
    
    public void a(final int n, final int o) {
        this.n = n;
        this.o = o;
    }
    
    public void a(final Graphics graphics) {
        final boolean q = com.easypano.tw.g.q;
        dv dv = this;
        if (!q) {
            if (this.e == null) {
                return;
            }
            dv = this;
        }
        int o;
        final int n = o = dv.n + this.p;
        if (!q) {
            if (n <= 0) {
                return;
            }
            final int n2;
            o = (n2 = this.n);
        }
        final int width = this.f.width;
        if (!q) {
            if (n >= width) {
                return;
            }
            o = this.o;
            final int q2 = this.q;
        }
        int n4;
        int t;
        final int n3 = t = (n4 = o + width);
        if (!q) {
            if (n3 <= 0) {
                return;
            }
            final int n5;
            t = (n5 = (n4 = this.o));
        }
        if (!q) {
            if (n3 >= this.f.height) {
                return;
            }
            n4 = (t = (this.t ? 1 : 0));
        }
        Label_0222: {
            dv dv3 = null;
            Label_0218: {
                if (!q) {
                    if (t != 0) {
                        dv dv2 = this;
                        Label_0156: {
                            if (!q) {
                                switch (this.r) {
                                    case 501: {
                                        this.w = this.i;
                                        this.x = this.l;
                                        if (q) {
                                            break;
                                        }
                                        break Label_0156;
                                    }
                                }
                                this.w = this.h;
                                dv2 = this;
                            }
                            dv2.x = this.k;
                        }
                        this.z = true;
                        if (!q) {
                            break Label_0222;
                        }
                    }
                    dv3 = this;
                    if (q) {
                        break Label_0218;
                    }
                    n4 = this.u;
                }
                switch (n4) {
                    case 2: {
                        this.w = this.j;
                        this.x = this.m;
                        this.z = true;
                        if (q) {
                            break;
                        }
                        break Label_0222;
                    }
                }
                dv3 = this;
            }
            dv3.z = false;
        }
        dv dv4 = this;
        if (!q) {
            if (!this.z) {
                return;
            }
            dv4 = this;
        }
        if (dv4.w != null) {
            graphics.drawImage(this.w, this.n + this.x.x, this.o + this.x.y, this.e);
            if (!q) {
                return;
            }
        }
        graphics.setColor(this.v);
        graphics.fillOval(this.n + (this.p - 14) / 2, this.o + (this.q - 14) / 2, 14, 14);
        graphics.drawOval(this.n + (this.p - 20) / 2, this.o + (this.q - 20) / 2, 20, 20);
    }
    
    public void a(final ActionListener s) {
        this.s = s;
    }
    
    public void a(final Image h) {
        final boolean q = com.easypano.tw.g.q;
        dt.a(this.j = h);
        final Image i = this.i;
        dv dv = null;
        Label_0049: {
            if (!q) {
                if (i == null) {
                    this.i = h;
                }
                dv = this;
                if (q) {
                    break Label_0049;
                }
                final Image h2 = this.h;
            }
            if (i == null) {
                this.h = h;
            }
            dv = this;
        }
        dv.c();
    }
    
    public void b(final Image h) {
        dt.a(this.h = h);
        this.c();
    }
    
    public void c(final Image i) {
        dt.a(this.i = i);
        this.c();
    }
    
    public void a(final String s, final Color color) {
        this.a(s, color, null);
    }
    
    public void a(final String s, final Color background, final Color color) {
        final boolean q = com.easypano.tw.g.q;
        String s2 = s;
        Label_0033: {
            Label_0023: {
                if (!q) {
                    if (s == null) {
                        break Label_0023;
                    }
                    s2 = s;
                }
                if (!s2.equals("")) {
                    break Label_0033;
                }
            }
            this.g = null;
            if (!q) {
                return;
            }
        }
        final g g = new g();
        g.setBackground(background);
        final j j = new j(g);
        j.d(color);
        g.a(j);
        g.e().a(s);
        this.g = g;
    }
    
    public e a() {
        return this.g;
    }
    
    protected void b() {
        final Component e = this.e;
        if (!com.easypano.tw.g.q) {
            if (e == null) {
                return;
            }
            final Component e2 = this.e;
        }
        e.repaint(this.n + (this.p - this.y.width) / 2, this.o + (this.q - this.y.height) / 2, this.y.width, this.y.height);
    }
    
    private void c() {
        final boolean q = com.easypano.tw.g.q;
        dv dv = this;
        dv dv2 = null;
        dv dv3 = null;
        Label_0162: {
            Label_0161: {
                if (!q) {
                    if (this.j != null) {
                        this.m.setLocation((this.p - this.j.getWidth(this.e)) / 2, (this.q - this.j.getHeight(this.e)) / 2);
                        final int width = this.y.width;
                        final int width2 = this.j.getWidth(this.e);
                        if (!q) {
                            if (width < width2) {
                                this.y.width = this.j.getWidth(this.e);
                            }
                            dv2 = this;
                            dv3 = this;
                            if (q) {
                                break Label_0162;
                            }
                            final int height = this.y.height;
                            this.j.getHeight(this.e);
                        }
                        if (width >= width2) {
                            break Label_0161;
                        }
                        this.y.height = this.j.getHeight(this.e);
                        if (!q) {
                            break Label_0161;
                        }
                    }
                    dv = this;
                }
                dv.m.setLocation(-1000, -1000);
            }
            dv2 = this;
            dv3 = this;
        }
        dv dv4 = null;
        dv dv5 = null;
        Label_0319: {
            Label_0318: {
                if (!q) {
                    if (dv3.h != null) {
                        this.k.setLocation((this.p - this.h.getWidth(this.e)) / 2, (this.q - this.h.getHeight(this.e)) / 2);
                        final int width3 = this.y.width;
                        final int width4 = this.h.getWidth(this.e);
                        if (!q) {
                            if (width3 < width4) {
                                this.y.width = this.h.getWidth(this.e);
                            }
                            dv4 = this;
                            dv5 = this;
                            if (q) {
                                break Label_0319;
                            }
                            final int height2 = this.y.height;
                            this.h.getHeight(this.e);
                        }
                        if (width3 >= width4) {
                            break Label_0318;
                        }
                        this.y.height = this.h.getHeight(this.e);
                        if (!q) {
                            break Label_0318;
                        }
                    }
                    dv2 = this;
                }
                dv2.k.setLocation(-1000, -1000);
            }
            dv4 = this;
            dv5 = this;
        }
        if (!q) {
            if (dv5.i != null) {
                this.l.setLocation((this.p - this.i.getWidth(this.e)) / 2, (this.q - this.i.getHeight(this.e)) / 2);
                final int width5 = this.y.width;
                final int width6 = this.i.getWidth(this.e);
                final Dimension y;
                Label_0444: {
                    if (!q) {
                        if (width5 < width6) {
                            this.y.width = this.i.getWidth(this.e);
                        }
                        y = this.y;
                        if (q) {
                            break Label_0444;
                        }
                        final int height3 = y.height;
                        this.i.getHeight(this.e);
                    }
                    if (width5 >= width6) {
                        return;
                    }
                    final Dimension y2 = this.y;
                }
                y.height = this.i.getHeight(this.e);
                if (!q) {
                    return;
                }
            }
            dv4 = this;
        }
        dv4.l.setLocation(-1000, -1000);
    }
    
    public void a(final int u) {
        this.u = u;
    }
    
    public int d() {
        return this.u;
    }
    
    public void a(final Color v) {
        this.v = v;
    }
    
    public void destroyResource() {
        this.e = null;
        this.f = new Dimension(0, 0);
        if (this.g != null) {
            this.g.destroyResource();
            this.g = null;
        }
        this.h = null;
        this.i = null;
        this.j = null;
        this.w = null;
    }
}
