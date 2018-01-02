// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;
import com.easypano.tw.d.i;
import java.awt.Insets;
import com.easypano.tw.d.c;
import com.easypano.tw.d.p;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import com.easypano.tw.d.d;

public class g extends f
{
    protected h l;
    protected d m;
    private f n;
    private h o;
    private h p;
    private h q;
    private h r;
    private h s;
    protected h t;
    protected h u;
    protected h v;
    protected h w;
    protected h x;
    private int y;
    protected GridLayout z;
    private boolean A;
    
    public g() {
        final boolean q = com.easypano.tw.h.q;
        this.l = new h();
        this.m = new d(this.l);
        this.n = new f();
        this.o = new h();
        this.p = new h();
        this.q = new h();
        this.r = new h();
        this.s = new h();
        this.t = new h();
        this.u = new h();
        this.v = new h();
        this.w = new h();
        this.x = new h();
        this.y = 42;
        this.z = new GridLayout(1, 2);
        this.A = false;
        this.e();
        this.setLayout(this.z);
        this.a(false);
        this.m.f(33);
        this.l.a(this.m);
        final c c = new c(this.o, false);
        final Insets insets = new Insets(0, 0, 0, 0);
        c.a(insets);
        this.o.a(c);
        final c c2 = new c(this.p, false);
        c2.a(insets);
        this.p.a(c2);
        final c c3 = new c(this.q, false);
        c3.a(insets);
        this.q.a(c3);
        final c c4 = new c(this.r, false);
        c4.a(insets);
        this.r.a(c4);
        final c c5 = new c(this.s, false);
        this.s.a(c5);
        c5.a(insets);
        final c c6 = new c(this.t, false);
        c6.a(insets);
        this.t.a(c6);
        final c c7 = new c(this.u, false);
        c7.a(insets);
        this.u.a(c7);
        final c c8 = new c(this.v, false);
        c8.a(insets);
        this.v.a(c8);
        final i i = new i(this.w);
        i.a(insets);
        this.w.a(i);
        final i j = new i(this.x);
        j.a(insets);
        this.x.a(j);
        this.add(this.l);
        this.add(this.n);
        this.n.a(false);
        this.n.setLayout(null);
        this.n.add(this.o);
        this.n.add(this.p);
        this.n.add(this.q);
        this.n.add(this.r);
        this.n.add(this.s);
        this.n.add(this.t);
        this.n.add(this.u);
        this.n.add(this.v);
        this.n.add(this.w);
        this.n.add(this.x);
        if (com.easypano.tw.f.k != 0) {
            com.easypano.tw.h.q = !q;
        }
    }
    
    public void e() {
        this.o.e().a(f("\fj\u00125\u001cb"));
        this.p.e().a(f("\u0012c\u0010>Cx+"));
        this.q.e().a(f("\u0004j\u0007j\u0006"));
        this.r.e().a(f("\u0015n\u001d#O6nEp"));
        this.s.e().a(f("\u0007f\u001e9Jx+"));
    }
    
    public void doLayout() {
        super.doLayout();
        this.A = true;
        this.f();
    }
    
    public void f() {
        final boolean q = com.easypano.tw.h.q;
        try {
            final Dimension preferredSize = this.o.getPreferredSize();
            int n = preferredSize.width;
            final int height = preferredSize.height;
            final double n3;
            double n2 = n3 = this.n.getBounds().height / 5.0;
            final double n4 = height;
            if (!q) {
                if (n3 < n4) {
                    n2 = height;
                }
                final double n5 = n2 - height;
            }
            final double n6 = n3 / n4;
            Dimension dimension = this.p.getPreferredSize();
            int width;
            int n9;
            int n8;
            final int n7 = n8 = (n9 = (width = n));
            int n12;
            int width2;
            int n11;
            final int n10 = n11 = (width2 = (n12 = dimension.width));
            if (!q) {
                if (n7 < n10) {
                    n = dimension.width;
                }
                dimension = this.q.getPreferredSize();
                final int n13;
                n8 = (n13 = (n9 = (width = n)));
                final int n14;
                n11 = (n14 = (width2 = (n12 = dimension.width)));
            }
            if (!q) {
                if (n7 < n10) {
                    n = dimension.width;
                }
                dimension = this.r.getPreferredSize();
                n9 = (n8 = (width = n));
                width2 = (n11 = (n12 = dimension.width));
            }
            if (!q) {
                if (n8 < n11) {
                    n = dimension.width;
                }
                dimension = this.s.getPreferredSize();
                width = (n9 = n);
                n12 = (width2 = dimension.width);
            }
            if (!q) {
                if (n9 < width2) {
                    n = dimension.width;
                }
                width = this.n.getBounds().width;
                n12 = n;
            }
            final int n15 = width - n12;
            this.o.setBounds(0, (int)n6, n, height);
            this.t.setBounds(n, (int)n6, n15, height);
            final double n16 = n6 + n2;
            this.p.setBounds(0, (int)n16, n, height);
            this.u.setBounds(n, (int)n16, n15, height);
            final double n17 = n16 + n2;
            this.q.setBounds(0, (int)n17, n, height);
            this.v.setBounds(n, (int)n17, n15, height);
            final double n18 = n17 + n2;
            this.r.setBounds(0, (int)n18, n, height);
            this.w.setBounds(n, (int)n18, n15, height);
            final double n19 = n18 + n2;
            this.s.setBounds(0, (int)n19, n, height);
            this.x.setBounds(n, (int)n19, n15, height);
            this.A = false;
        }
        catch (Exception ex) {}
    }
    
    public void paint(final Graphics graphics) {
        g g = this;
        if (!com.easypano.tw.h.q) {
            if (this.A) {
                this.f();
            }
            g = this;
        }
        g.paint(graphics);
    }
    
    public void b(final int y) {
        final boolean q = com.easypano.tw.h.q;
        int y2 = y;
        final int n = 42;
        while (true) {
            Label_0084: {
                int n3 = 0;
                Label_0046: {
                    Label_0033: {
                        if (!q) {
                            if (y != n) {
                                y2 = y;
                                final int n2 = 41;
                                if (q) {
                                    break Label_0033;
                                }
                                if (y != n2) {
                                    return;
                                }
                            }
                            n3 = (y2 = this.y);
                            if (q) {
                                break Label_0046;
                            }
                        }
                    }
                    if (y2 == n) {
                        return;
                    }
                    this.y = y;
                    if (q) {
                        break Label_0084;
                    }
                    n3 = y;
                }
                switch (n3) {
                    case 42: {
                        this.z.setRows(1);
                        this.z.setColumns(2);
                        break;
                    }
                    case 41: {
                        this.z.setRows(2);
                        this.z.setColumns(1);
                        return;
                    }
                }
            }
            if (q) {
                continue;
            }
            break;
        }
    }
    
    public void setName(final String s) {
        this.t.e().a(s);
    }
    
    public void b(final String s) {
        this.u.e().a(s);
    }
    
    public void c(final String s) {
        this.v.e().a(s);
    }
    
    public void d(final String s) {
        this.w.e().a(s);
    }
    
    public void a(final ActionListener actionListener) {
        this.w.a(actionListener);
    }
    
    public void e(final String s) {
        this.x.e().a(s);
    }
    
    public void b(final ActionListener actionListener) {
        this.x.a(actionListener);
    }
    
    public void a(final Image image) {
        ((d)this.l.a()).d(image);
    }
    
    private static String f(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0089: {
                if (length > 1) {
                    break Label_0089;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = 'B';
                            break;
                        }
                        case 1: {
                            c2 = '\u000b';
                            break;
                        }
                        case 2: {
                            c2 = '\u007f';
                            break;
                        }
                        case 3: {
                            c2 = 'P';
                            break;
                        }
                        default: {
                            c2 = '&';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
