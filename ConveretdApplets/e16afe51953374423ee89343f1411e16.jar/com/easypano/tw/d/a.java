// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.d;

import com.easypano.tw.cu;
import java.awt.image.ImageObserver;
import com.easypano.tw.dt;
import java.awt.Graphics;
import com.easypano.tw.f;
import java.awt.Insets;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.FontMetrics;
import com.easypano.tw.h;
import java.awt.Color;
import com.easypano.tw.dc;

public class a implements dc, p
{
    public static final int a = 21;
    public static final int b = 22;
    public static final int c = 31;
    public static final int d = 32;
    protected static final Color e;
    protected static final Color f;
    protected static final Color g;
    protected static final Color h;
    protected static final Color i;
    protected static final Color j;
    protected h k;
    protected FontMetrics l;
    private Dimension m;
    protected int n;
    protected int o;
    protected Color p;
    protected Color q;
    protected Color r;
    protected Color s;
    protected Color t;
    protected Color u;
    protected Image v;
    protected Image w;
    protected Image x;
    protected Color y;
    protected Color z;
    protected Color A;
    protected Color B;
    protected Color C;
    protected Color D;
    protected Image E;
    protected Image F;
    protected Image G;
    protected Image H;
    protected Color I;
    protected Color J;
    private int K;
    private int L;
    protected int M;
    protected Insets N;
    public static boolean O;
    
    static {
        e = new Color(229, 229, 229);
        f = new Color(190, 187, 179);
        g = new Color(212, 208, 200);
        h = com.easypano.tw.d.a.e;
        i = com.easypano.tw.d.a.e;
        j = com.easypano.tw.d.a.f;
    }
    
    public a(final h k) {
        final boolean o = com.easypano.tw.d.a.O;
        this.k = null;
        this.l = null;
        this.m = new Dimension();
        this.n = 15;
        this.o = 15;
        this.p = Color.black;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = Color.black;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.E = null;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = 21;
        this.L = 31;
        this.M = 21;
        this.N = new Insets(2, 2, 2, 2);
        (this.k = k).b(true);
        if (o) {
            int i = com.easypano.tw.f.k;
            com.easypano.tw.f.k = ++i;
        }
    }
    
    public void a(final Graphics graphics) {
        this.c();
        this.b(graphics);
        this.c(graphics);
        this.d(graphics);
    }
    
    protected void c() {
        final boolean o = com.easypano.tw.d.a.O;
        int n2;
        final int n = n2 = this.L;
        Label_0281: {
            if (!o) {
                switch (n) {
                    case 32: {
                        final boolean e;
                        final int n3 = (e = ((n2 = (this.k.e().d() ? 1 : 0)) != 0)) ? 1 : 0;
                        if (o) {
                            break Label_0281;
                        }
                        if (n3 == 0) {
                            break;
                        }
                        final boolean e2 = this.k.e().e();
                        a a3 = null;
                        Label_0243: {
                            Label_0160: {
                                if (!o) {
                                    if (e2) {
                                        final boolean f = this.k.e().f();
                                        if (o) {
                                            break Label_0160;
                                        }
                                        if (f) {
                                            this.H = this.G;
                                            a a = this;
                                            Color i = null;
                                            Label_0114: {
                                                if (!o) {
                                                    if (this.A == null) {
                                                        i = this.y;
                                                        break Label_0114;
                                                    }
                                                    a = this;
                                                }
                                                i = a.A;
                                            }
                                            this.I = i;
                                            a a2 = this;
                                            Color j = null;
                                            Label_0139: {
                                                if (!o) {
                                                    if (this.D == null) {
                                                        j = com.easypano.tw.d.a.h;
                                                        break Label_0139;
                                                    }
                                                    a2 = this;
                                                }
                                                j = a2.D;
                                            }
                                            this.J = j;
                                            if (!o) {
                                                return;
                                            }
                                        }
                                    }
                                    a3 = this;
                                    if (o) {
                                        break Label_0243;
                                    }
                                    this.k.e().f();
                                }
                            }
                            if (e2) {
                                this.H = this.F;
                                a a4 = this;
                                Color k = null;
                                Label_0194: {
                                    if (!o) {
                                        if (this.z == null) {
                                            k = this.y;
                                            break Label_0194;
                                        }
                                        a4 = this;
                                    }
                                    k = a4.q;
                                }
                                this.I = k;
                                a a5 = this;
                                Color l = null;
                                Label_0219: {
                                    if (!o) {
                                        if (this.C == null) {
                                            l = com.easypano.tw.d.a.i;
                                            break Label_0219;
                                        }
                                        a5 = this;
                                    }
                                    l = a5.C;
                                }
                                this.J = l;
                                if (!o) {
                                    return;
                                }
                            }
                            this.H = this.E;
                            this.I = this.y;
                            a3 = this;
                        }
                        a a6 = this;
                        Color m = null;
                        Label_0264: {
                            if (!o) {
                                if (this.B == null) {
                                    m = com.easypano.tw.d.a.j;
                                    break Label_0264;
                                }
                                a6 = this;
                            }
                            m = a6.B;
                        }
                        a3.J = m;
                        if (o) {
                            break;
                        }
                        return;
                    }
                }
                boolean e;
                n2 = ((e = this.k.e().e()) ? 1 : 0);
            }
        }
        a a9 = null;
        Label_0465: {
            Label_0382: {
                if (!o) {
                    if (n != 0) {
                        final boolean b = (n2 = (this.k.e().f() ? 1 : 0)) != 0;
                        if (o) {
                            break Label_0382;
                        }
                        if (b) {
                            this.H = this.x;
                            a a7 = this;
                            Color i2 = null;
                            Label_0336: {
                                if (!o) {
                                    if (this.r == null) {
                                        i2 = this.p;
                                        break Label_0336;
                                    }
                                    a7 = this;
                                }
                                i2 = a7.r;
                            }
                            this.I = i2;
                            a a8 = this;
                            Color j2 = null;
                            Label_0361: {
                                if (!o) {
                                    if (this.u == null) {
                                        j2 = com.easypano.tw.d.a.e;
                                        break Label_0361;
                                    }
                                    a8 = this;
                                }
                                j2 = a8.u;
                            }
                            this.J = j2;
                            if (!o) {
                                return;
                            }
                        }
                    }
                    a9 = this;
                    if (o) {
                        break Label_0465;
                    }
                    n2 = (this.k.e().f() ? 1 : 0);
                }
            }
            if (n2 != 0) {
                this.H = this.w;
                a a10 = this;
                Color i3 = null;
                Label_0416: {
                    if (!o) {
                        if (this.q == null) {
                            i3 = this.p;
                            break Label_0416;
                        }
                        a10 = this;
                    }
                    i3 = a10.q;
                }
                this.I = i3;
                a a11 = this;
                Color j3 = null;
                Label_0441: {
                    if (!o) {
                        if (this.t == null) {
                            j3 = com.easypano.tw.d.a.f;
                            break Label_0441;
                        }
                        a11 = this;
                    }
                    j3 = a11.t;
                }
                this.J = j3;
                if (!o) {
                    return;
                }
            }
            this.H = this.v;
            this.I = this.p;
            a9 = this;
        }
        a a12 = this;
        Color j4 = null;
        Label_0486: {
            if (!o) {
                if (this.s == null) {
                    j4 = com.easypano.tw.d.a.g;
                    break Label_0486;
                }
                a12 = this;
            }
            j4 = a12.s;
        }
        a9.J = j4;
    }
    
    public void b(final Graphics graphics) {
        final int k = this.K;
        if (!com.easypano.tw.d.a.O) {
            if (k != 21) {
                return;
            }
            this.k.isOpaque();
        }
        if (k != 0) {
            graphics.setColor(this.J);
            graphics.fillRect(0, 0, this.k.getBounds().width, this.k.getBounds().height);
        }
    }
    
    public void c(final Graphics graphics) {
        this.e(graphics);
        this.f(graphics);
    }
    
    protected void e(final Graphics graphics) {
        final Image h = this.H;
        if (!com.easypano.tw.d.a.O) {
            if (h == null) {
                return;
            }
            final Image h2 = this.H;
        }
        graphics.drawImage(this.H, (this.k.getBounds().width - h.getWidth(dt.d)) / 2, (this.k.getBounds().height - this.H.getHeight(dt.d)) / 2, this.k);
    }
    
    protected void f(final Graphics graphics) {
        final boolean o = com.easypano.tw.d.a.O;
        a a = this;
        String s = null;
        Label_0093: {
            if (!o) {
                switch (this.L) {
                    case 32: {
                        final cu e = this.k.e();
                        if (!o) {
                            if (e.d()) {
                                s = this.k.e().b();
                                if (!o) {
                                    break Label_0093;
                                }
                            }
                            this.k.e();
                        }
                        s = e.a();
                        if (o) {
                            break;
                        }
                        break Label_0093;
                    }
                }
                a = this;
            }
            s = a.k.e().a();
        }
        final int length = s.length();
        if (!o) {
            if (length <= 0) {
                return;
            }
            final int left = this.N.left;
        }
        int n = length;
        int n2 = 0;
        graphics.setFont(this.k.getFont());
        graphics.setColor(this.I);
        this.l = graphics.getFontMetrics(this.k.getFont());
        final int stringWidth = this.l.stringWidth(s);
        int n4;
        final int n3 = n4 = this.n;
        if (!o) {
            Label_0251: {
                switch (n3) {
                    case 11: {
                        n2 = this.N.top + this.l.getHeight();
                        if (o) {
                            break Label_0251;
                        }
                        break;
                    }
                    case 12: {
                        n2 = this.k.getBounds().height - this.N.bottom;
                        if (o) {
                            break Label_0251;
                        }
                        break;
                    }
                    case 15: {
                        n2 = (this.k.getBounds().height - this.l.getHeight() + this.l.getLeading()) / 2 + this.l.getAscent();
                        break;
                    }
                }
            }
            final int o2;
            n4 = (o2 = this.o);
        }
        if (!o) {
            Label_0356: {
                switch (n3) {
                    case 13: {
                        if (o) {
                            break Label_0356;
                        }
                        break;
                    }
                    case 14: {
                        n = this.k.getBounds().width - stringWidth - this.N.right;
                        if (o) {
                            break Label_0356;
                        }
                        break;
                    }
                    case 15: {
                        n = (this.k.getBounds().width - stringWidth) / 2;
                        break;
                    }
                }
            }
            graphics.drawString(s, n, n2);
            n4 = n2 + this.l.getDescent();
        }
        final int n5 = n4;
        this.a(graphics, n, n5, n + stringWidth, n5);
    }
    
    protected void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        switch (this.M) {
            case 22: {
                graphics.drawLine(n, n2, n3, n4);
                break;
            }
        }
    }
    
    public void d(final Graphics graphics) {
        switch (this.K) {
            case 21: {
                graphics.setColor(Color.black);
                graphics.drawRect(0, 0, this.k.getBounds().width - 1, this.k.getBounds().height - 1);
                break;
            }
        }
    }
    
    public void a(final int n) {
        final boolean o = com.easypano.tw.d.a.O;
        int n2 = n;
        int n3 = n;
        int n5;
        final int n4 = n5 = 11;
        Label_0030: {
            if (!o) {
                if (n == n4) {
                    break Label_0030;
                }
                n2 = n;
                n3 = n;
                final int n6;
                n5 = (n6 = 12);
            }
            if (!o) {
                if (n3 == n4) {
                    break Label_0030;
                }
                n2 = n;
                n5 = 15;
            }
            if (n2 != n5) {
                return;
            }
        }
        this.n = n;
    }
    
    public void b(final int o) {
        final boolean o2 = com.easypano.tw.d.a.O;
        int n = o;
        int n2 = o;
        int n4;
        final int n3 = n4 = 13;
        Label_0030: {
            if (!o2) {
                if (o == n3) {
                    break Label_0030;
                }
                n = o;
                n2 = o;
                final int n5;
                n4 = (n5 = 14);
            }
            if (!o2) {
                if (n2 == n3) {
                    break Label_0030;
                }
                n = o;
                n4 = 15;
            }
            if (n != n4) {
                return;
            }
        }
        this.o = o;
    }
    
    public void c(final int k) {
        final boolean o = com.easypano.tw.d.a.O;
        int n = k;
        final int n2 = 22;
        a a = null;
        Label_0052: {
            Label_0036: {
                if (!o) {
                    if (k != n2) {
                        n = k;
                        final int n3 = 21;
                        if (o) {
                            break Label_0036;
                        }
                        if (k != n3) {
                            return;
                        }
                    }
                    a = this;
                    if (o) {
                        break Label_0052;
                    }
                    this.K = k;
                    n = k;
                }
            }
            if (n == n2) {
                this.k.a(false);
                if (!o) {
                    return;
                }
            }
            a = this;
        }
        a.k.a(true);
    }
    
    public void d(final int l) {
        int n = l;
        final int n2 = 31;
        Label_0018: {
            if (!com.easypano.tw.d.a.O) {
                if (l == n2) {
                    break Label_0018;
                }
                n = l;
            }
            if (n != n2) {
                return;
            }
        }
        this.L = l;
    }
    
    public int d() {
        return this.L;
    }
    
    public void e(final int m) {
        int n = m;
        final int n2 = 21;
        Label_0018: {
            if (!com.easypano.tw.d.a.O) {
                if (m == n2) {
                    break Label_0018;
                }
                n = m;
            }
            if (n != n2) {
                return;
            }
        }
        this.M = m;
    }
    
    public void a(final Color y) {
        if (y != null) {
            this.y = y;
        }
    }
    
    public void b(final Color a) {
        this.A = a;
    }
    
    public void c(final Color z) {
        this.z = z;
    }
    
    public void d(final Color p) {
        if (p != null) {
            this.p = p;
        }
    }
    
    public void e(final Color r) {
        this.r = r;
    }
    
    public void f(final Color q) {
        this.q = q;
    }
    
    public void a(final Image f) {
        final boolean o = com.easypano.tw.d.a.O;
        dt.a(f);
        this.E = f;
        final Image g = this.G;
        a a = null;
        Label_0041: {
            if (!o) {
                if (g == null) {
                    this.G = f;
                }
                a = this;
                if (o) {
                    break Label_0041;
                }
                final Image f2 = this.F;
            }
            if (g != null) {
                return;
            }
            a = this;
        }
        a.F = f;
    }
    
    public void b(final Image g) {
        dt.a(g);
        this.G = g;
    }
    
    public void c(final Image f) {
        dt.a(f);
        this.F = f;
    }
    
    public void d(final Image w) {
        final boolean o = com.easypano.tw.d.a.O;
        dt.a(w);
        this.v = w;
        final Image x = this.x;
        a a = null;
        Label_0041: {
            if (!o) {
                if (x == null) {
                    this.x = w;
                }
                a = this;
                if (o) {
                    break Label_0041;
                }
                final Image w2 = this.w;
            }
            if (x != null) {
                return;
            }
            a = this;
        }
        a.w = w;
    }
    
    public void e(final Image x) {
        dt.a(x);
        this.x = x;
    }
    
    public void f(final Image w) {
        dt.a(w);
        this.w = w;
    }
    
    public void g(final Color u) {
        final boolean o = com.easypano.tw.d.a.O;
        this.s = u;
        final Color t = this.t;
        a a = null;
        Label_0037: {
            if (!o) {
                if (t == null) {
                    this.t = u;
                }
                a = this;
                if (o) {
                    break Label_0037;
                }
                final Color u2 = this.u;
            }
            if (t != null) {
                return;
            }
            a = this;
        }
        a.u = u;
    }
    
    public void h(final Color t) {
        this.t = t;
    }
    
    public void i(final Color u) {
        this.u = u;
    }
    
    public Dimension a() {
        final boolean o = com.easypano.tw.d.a.O;
        final Dimension dimension = new Dimension(0, 0);
        Image image2;
        final Image image = image2 = this.v;
        if (!o) {
            if (image != null) {
                dimension.width = this.v.getWidth(dt.d);
                dimension.height = this.v.getHeight(dt.d);
            }
            final Image w;
            image2 = (w = this.w);
        }
        a a = null;
        Label_0130: {
            if (!o) {
                if (image != null) {
                    dt.a(dimension, this.w.getWidth(dt.d), this.w.getHeight(dt.d));
                }
                a = this;
                if (o) {
                    break Label_0130;
                }
                image2 = this.x;
            }
            if (image2 != null) {
                dt.a(dimension, this.x.getWidth(dt.d), this.x.getHeight(dt.d));
            }
            a = this;
        }
        final h k = a.k;
        Label_0233: {
            if (!o) {
                if (k == null) {
                    break Label_0233;
                }
                final h i = this.k;
            }
            final Graphics graphics = k.getGraphics();
            if (o) {
                return dimension;
            }
            if (graphics != null) {
                try {
                    this.l = graphics.getFontMetrics(this.k.getFont());
                    dt.a(dimension, this.l.stringWidth(this.k.e().a()), this.l.getHeight());
                }
                finally {
                    graphics.dispose();
                }
                graphics.dispose();
            }
        }
        final Dimension dimension2 = dimension;
        dimension2.width += this.N.left + this.N.right;
        final Dimension dimension3 = dimension;
        dimension3.height += this.N.top + this.N.bottom;
        return dimension;
    }
    
    public Insets b() {
        return this.N;
    }
    
    public void a(final Insets insets) {
        this.N.left = insets.left;
        this.N.bottom = insets.bottom;
        this.N.right = insets.right;
        this.N.top = insets.top;
    }
    
    public void destroyResource() {
        this.k = null;
        this.l = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.H = null;
    }
}
