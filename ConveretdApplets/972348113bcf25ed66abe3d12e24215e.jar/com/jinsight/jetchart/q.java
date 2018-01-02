// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.Toolkit;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;

class q extends o
{
    private int b;
    private int c;
    private double d;
    private double e;
    private int f;
    private int g;
    private int h;
    private int i;
    private Rectangle j;
    private Point k;
    private Rectangle l;
    Insets m;
    Insets n;
    
    int b() {
        final Vector i = super.a.i;
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(super.a.getLegend().a());
        int n = 0;
        final Slice[] v = i.elementAt(0).v;
        int n2 = 0;
        while (true) {
            Label_0097: {
                if (!GraphSerie.G) {
                    break Label_0097;
                }
                final int stringWidth = fontMetrics.stringWidth(super.a.g[v[n2].getIndex()]);
                n = ((stringWidth > n) ? stringWidth : n);
                ++n2;
            }
            if (n2 >= v.length) {
                return n;
            }
            continue;
        }
    }
    
    void a() {
        if (super.a.cg || super.a.D || super.a.cd) {
            super.a.cd = false;
            super.a.getLegend().g();
            final int j = super.a.getLegend().j();
            final int k = super.a.k;
            final int n = super.a.n;
            final int h = super.a.getLegend().h();
            final int l = super.a.l;
            final int i = super.a.getLegend().i();
            final FontMetrics fontMetrics = super.a.getToolkit().getFontMetrics(super.a.getYAxis().r);
            this.f = super.a.m + this.d();
            this.g = l + i;
            this.h = ((super.a.bF.o != null) ? (15 + fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent()) : 0);
            this.h += n + h;
            this.i = ((super.a.getYAxis().o != null) ? (15 + fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent()) : 0);
            this.i += k + j;
        }
        this.m.top = this.f;
        this.m.left = this.i;
        this.m.bottom = this.h;
        this.m.right = this.g;
        if (super.a.cg) {
            super.a.cg = false;
        }
    }
    
    int e() {
        final boolean g = GraphSerie.G;
        int n = -1;
        final Vector i = super.a.i;
        int n2 = 0;
        while (true) {
            Label_0050: {
                if (!g) {
                    break Label_0050;
                }
                if (i.elementAt(n2) instanceof PieSerie) {
                    n = n2;
                    if (!g) {
                        return n;
                    }
                }
                ++n2;
            }
            if (n2 < i.size()) {
                continue;
            }
            break;
        }
        return n;
    }
    
    boolean f() {
        final boolean g = GraphSerie.G;
        final Vector i = super.a.i;
        boolean b = false;
        int n = 0;
        while (true) {
            Label_0050: {
                if (!g) {
                    break Label_0050;
                }
                if (i.elementAt(n) instanceof PieSerie) {
                    b = true;
                    if (!g) {
                        return b;
                    }
                }
                ++n;
            }
            if (n < i.size()) {
                continue;
            }
            break;
        }
        return b;
    }
    
    Rectangle a(final PieSerie pieSerie) {
        final boolean g = GraphSerie.G;
        this.a();
        this.b(pieSerie, this.m);
        Insets insets = this.n;
        if (this.e(pieSerie) && pieSerie.z == 1) {
            insets = this.a(pieSerie, insets);
        }
        final int r = pieSerie.r;
        final int s = pieSerie.s;
        final Insets insets2 = insets;
        insets2.left += r;
        final Insets insets3 = insets;
        insets3.right += r;
        final Insets insets4 = insets;
        insets4.top += s;
        final Insets insets5 = insets;
        insets5.bottom += s;
        final boolean t = pieSerie.t;
        this.a(insets);
        this.k.x = this.j.x + this.j.width / 2;
        this.k.y = this.j.y + this.j.height / 2;
        int x = 0;
        if (super.a.bc) {
            x = pieSerie.x;
        }
        if (t) {
            int x2 = 0;
            int y = 0;
            int n = 0;
            Label_0309: {
                if (this.j.width >= this.j.height) {
                    x2 = this.k.x - this.j.height / 2;
                    y = this.j.y;
                    n = this.j.height;
                    if (!g) {
                        break Label_0309;
                    }
                }
                if (this.j.width < this.j.height) {
                    x2 = this.j.x;
                    y = this.k.y - this.j.width / 2;
                    n = this.j.width;
                }
            }
            this.l.x = x2 + x;
            this.l.y = y + x;
            this.l.width = n - 2 * x;
            this.l.height = n - 2 * x;
            if (!g) {
                return this.l;
            }
        }
        this.l.x = this.j.x;
        this.l.y = this.j.y;
        this.l.width = this.j.width;
        this.l.height = this.j.height - x;
        return this.l;
    }
    
    Insets a(final PieSerie pieSerie, final Insets insets) {
        this.b(pieSerie);
        insets.top += this.c + 10;
        insets.right += this.b + 10;
        insets.left += this.b + 10;
        insets.bottom += this.c + 10;
        return insets;
    }
    
    private void b(final PieSerie pieSerie) {
        this.b = 0;
        this.c = 0;
        int n = 0;
        while (true) {
            Label_0101: {
                if (!GraphSerie.G) {
                    break Label_0101;
                }
                final SliceLegend p = pieSerie.v[n].p;
                final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(p.a);
                final int n2 = fontMetrics.stringWidth(p.a()) + 4;
                final int n3 = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent() + 4;
                this.b = Math.max(n2, this.b);
                this.c = Math.max(n3, this.c);
                ++n;
            }
            if (n >= pieSerie.v.length) {
                return;
            }
            continue;
        }
    }
    
    void b(final PieSerie pieSerie, final Insets insets) {
        final boolean g = GraphSerie.G;
        final double[] a = pieSerie.a;
        final Slice[] v = pieSerie.v;
        int max = 0;
        int max2 = 0;
        final double c = this.c(pieSerie);
        int n = 0;
        while (true) {
            while (true) {
                Label_0098: {
                    if (!g) {
                        break Label_0098;
                    }
                    if (v[n].b()) {
                        final int a2 = v[n].a();
                        final int n2 = (int)(c * a2);
                        final int n3 = a2;
                        max = Math.max(max, n2);
                        max2 = Math.max(max2, n3);
                    }
                    ++n;
                }
                if (n < v.length) {
                    continue;
                }
                break;
            }
            this.n.top = insets.top + max2;
            this.n.left = insets.left + max;
            this.n.bottom = insets.bottom + max2;
            this.n.right = insets.right + max;
            if (!g) {
                return;
            }
            continue;
        }
    }
    
    double g() {
        final boolean g = GraphSerie.G;
        final double[] a = super.a.i.elementAt(0).a;
        double n = 0.0;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0043: {
                    if (!g) {
                        break Label_0043;
                    }
                    final double n3 = n + a[n2];
                    final double n4;
                    n = n4;
                    ++n2;
                }
                if (n2 < a.length) {
                    continue;
                }
                break;
            }
            final double n4 = n;
            if (!g) {
                return n4;
            }
            continue;
        }
    }
    
    void a(final Insets insets) {
        final int width = super.a.getSize().width - insets.left - insets.right;
        final int height = super.a.getSize().height - insets.top - insets.bottom;
        final int left = insets.left;
        final int top = insets.top;
        this.j.x = left;
        this.j.y = top;
        this.j.width = width;
        this.j.height = height;
    }
    
    int a(final int n, final double[] array, final double n2) {
        int b = 0;
        if (n != 0) {
            b = this.b(n - 1, array, n2);
        }
        return b;
    }
    
    int b(final int n, final double[] array, final double n2) {
        final boolean g = GraphSerie.G;
        final double n3 = 360.0;
        int n4 = 0;
        double n5 = 0.0;
        int n6 = 0;
        while (true) {
        Label_0071:
            while (true) {
                Label_0064: {
                    if (!g) {
                        break Label_0064;
                    }
                    n5 += array[n6];
                    final int n7;
                    if (n7 == n6) {
                        n4 = (int)(n5 / n2 * n3);
                        if (!g) {
                            break Label_0071;
                        }
                    }
                    ++n6;
                }
                if (n6 < array.length) {
                    continue;
                }
                break;
            }
            final int n7 = n4;
            if (!g) {
                return n7;
            }
            continue;
        }
    }
    
    double c(final PieSerie pieSerie) {
        if (pieSerie.t) {
            return 1;
        }
        this.a();
        this.c(pieSerie, this.m);
        return this.d / this.e;
    }
    
    double d(final PieSerie pieSerie) {
        if (pieSerie.t) {
            return 1;
        }
        this.a();
        this.c(pieSerie, this.m);
        return this.e / this.d;
    }
    
    private void c(final PieSerie pieSerie, final Insets insets) {
        final int r = pieSerie.r;
        final int s = pieSerie.s;
        double n = super.a.getSize().width - insets.left - insets.right - 2 * r;
        double n2 = super.a.getSize().height - insets.top - insets.bottom - 2 * s;
        if (this.e(pieSerie) && pieSerie.z == 1) {
            this.b(pieSerie);
            n = n - 2 * this.b - 20.0;
            n2 = n2 - 2 * this.c - 20.0;
        }
        this.d = n / 2.0;
        if (super.a.bc) {
            n2 -= pieSerie.x;
        }
        this.e = n2 / 2.0;
    }
    
    int a(final PieSerie pieSerie, final int n, final int n2) {
        final i c = pieSerie.c();
        final double n3 = n - c.a;
        final double acos = Math.acos(n3 / Math.sqrt(Math.pow(n3, 2.0) + Math.pow(c.b - n2, 2.0)));
        if (n2 < c.b) {
            final int n4 = (int)(acos * 180.0 / 3.141592653589793);
            if (!GraphSerie.G) {
                return n4;
            }
        }
        return 360 - (int)(acos * 180.0 / 3.141592653589793);
    }
    
    boolean a(final PieSerie pieSerie, final Slice slice, final int n, final int n2) {
        slice.f();
        final double n3 = slice.d.x + slice.d.width / 2.0;
        final double n4 = slice.d.y + slice.d.height / 2.0;
        final double n5 = slice.g();
        final double n6 = slice.h();
        final double n7 = n4 - n2;
        final double n8 = (n - n3) * this.d(pieSerie);
        final double sqrt = Math.sqrt(Math.pow(n7, 2.0) + Math.pow(n8, 2.0));
        double n9 = Math.acos(n8 / sqrt) * 180.0 / 3.141592653589793;
        if (n2 > n4) {
            n9 = 360.0 - n9;
        }
        final double a = this.a(n5);
        final double a2 = this.a(n6);
        final double a3 = slice.a((int)n9);
        boolean b = false;
        if (((a <= a2 && n9 <= a2 && n9 >= a) || (a > a2 && (n9 < a2 || n9 > a))) && sqrt <= a3) {
            b = true;
        }
        return b;
    }
    
    private double a(double n) {
        final double cos = Math.cos(n * 3.141592653589793 / 180.0);
        final double sin = Math.sin(n * 3.141592653589793 / 180.0);
        n = Math.acos(cos) * 180.0 / 3.141592653589793;
        if ((cos < 0.0 && sin < 0.0) || (cos > 0.0 && sin < 0.0)) {
            n = 360.0 - n;
        }
        return n;
    }
    
    int a(final Slice slice, final int n, final int n2) {
        final Rectangle e = slice.e();
        final double n3 = e.x + e.width / 2.0;
        final double n4 = e.y + e.height / 2.0;
        final double n5 = slice.g();
        final double n6 = slice.h();
        return (int)Math.sqrt(Math.pow(n4 - n2, 2.0) + Math.pow((n - n3) * this.d(slice.o), 2.0));
    }
    
    boolean a(final int n, final int n2) {
        final double n3 = n2 * 3.141592653589793 / 180.0;
        if (n == 1) {
            return Math.cos(n3) >= 0.0 && Math.sin(n3) >= 0.0;
        }
        if (n == 2) {
            return Math.cos(n3) < 0.0 && Math.sin(n3) > 0.0;
        }
        if (n == 3) {
            return Math.cos(n3) < 0.0 && Math.sin(n3) < 0.0;
        }
        return n == 4 && Math.cos(n3) > 0.0 && Math.sin(n3) < 0.0;
    }
    
    boolean e(final PieSerie pieSerie) {
        final boolean g = GraphSerie.G;
        boolean b = false;
        final Slice[] slices = pieSerie.getSlices();
        int n = 0;
        while (true) {
        Label_0047:
            while (true) {
                Label_0040: {
                    if (!g) {
                        break Label_0040;
                    }
                    final boolean n2 = slices[n].n;
                    final boolean b2;
                    if (b2) {
                        b = true;
                        if (!g) {
                            break Label_0047;
                        }
                    }
                    ++n;
                }
                if (n < slices.length) {
                    continue;
                }
                break;
            }
            final boolean b2 = b;
            if (!g) {
                return b2;
            }
            continue;
        }
    }
    
    q(final Graph a) {
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = new Rectangle();
        this.k = new Point();
        this.l = new Rectangle();
        this.m = new Insets(0, 0, 0, 0);
        this.n = new Insets(0, 0, 0, 0);
        super.a = a;
    }
}
