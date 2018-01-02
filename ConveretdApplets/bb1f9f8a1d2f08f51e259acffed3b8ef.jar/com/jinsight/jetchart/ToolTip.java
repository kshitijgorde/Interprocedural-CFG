// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Color;

public class ToolTip extends Note
{
    public static final int ARROW = 0;
    public static final int LINE = 1;
    private Graph j;
    private Color k;
    private int l;
    private int m;
    private Rectangle n;
    private Rectangle o;
    private int p;
    private int q;
    l[] r;
    
    public void setPointerType(final int q) {
        this.q = q;
    }
    
    public void setPointerColor(final Color k) {
        this.k = k;
    }
    
    public void setGap(int p) {
        if (p < 1) {
            p = 1;
        }
        this.p = p;
    }
    
    void a(final String[] array, final int n, final int n2) {
        final boolean g = GraphSerie.G;
        int n3 = 0;
        while (true) {
        Label_0065:
            while (true) {
                Label_0055: {
                    if (!g) {
                        break Label_0055;
                    }
                    if (this.r[n3] == null) {
                        final l[] r = this.r;
                        final int n4 = n3;
                        if (this == null) {
                            throw null;
                        }
                        r[n4] = new l(this, array, n, n2);
                        if (!g) {
                            break Label_0065;
                        }
                    }
                    ++n3;
                }
                if (n3 < this.r.length) {
                    continue;
                }
                break;
            }
            if (!g) {
                return;
            }
            continue;
        }
    }
    
    void b(final String[] a, final int d, final int e) {
        super.a = a;
        super.d = d;
        super.e = e;
    }
    
    int a() {
        final boolean g = GraphSerie.G;
        int n = 0;
        int n2 = 0;
        while (true) {
        Label_0040:
            while (true) {
                Label_0031: {
                    if (!g) {
                        break Label_0031;
                    }
                    if (this.r[n2] == null && !g) {
                        break Label_0040;
                    }
                    ++n;
                    ++n2;
                }
                if (n2 < this.r.length) {
                    continue;
                }
                break;
            }
            if (!g) {
                return n;
            }
            continue;
        }
    }
    
    private void a(final FontMetrics fontMetrics, final String[] array) {
        final boolean g = GraphSerie.G;
        this.l = 0;
        this.m = 0;
        int n = 0;
        while (true) {
            while (true) {
                Label_0072: {
                    if (!g) {
                        break Label_0072;
                    }
                    if (array[n] != null) {
                        this.l = Math.max(fontMetrics.stringWidth(array[n]), this.l);
                        this.m += fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent() + fontMetrics.getLeading();
                    }
                    ++n;
                }
                if (n < array.length) {
                    continue;
                }
                break;
            }
            this.m += 4;
            this.l += 6;
            if (!g) {
                return;
            }
            continue;
        }
    }
    
    private int a(final FontMetrics fontMetrics, final int n) {
        final boolean g = GraphSerie.G;
        int n2 = 0;
        int n3 = 0;
    Label_0053_Outer:
        while (true) {
            Label_0100: {
                if (!g) {
                    break Label_0100;
                }
                final String[] b = this.r[n3].b;
                int max = 0;
                int n4 = 0;
                int n5 = 0;
                int n6 = 0;
                while (true) {
                    while (true) {
                        Label_0061: {
                            if (!g) {
                                break Label_0061;
                            }
                            fontMetrics.stringWidth(b[n4]);
                            max = Math.max(n5, n6);
                            ++n4;
                        }
                        if (n4 < b.length) {
                            continue Label_0053_Outer;
                        }
                        break;
                    }
                    n2 += max + 6;
                    n5 = n3;
                    n6 = n - 1;
                    if (g) {
                        continue;
                    }
                    break;
                }
                if (n5 < n6) {
                    n2 += this.p;
                }
                ++n3;
            }
            if (n3 >= n) {
                return n2;
            }
            continue;
        }
    }
    
    private int b(final FontMetrics fontMetrics, final int n) {
        final boolean g = GraphSerie.G;
        int n2 = 0;
        int n3 = 0;
    Label_0066_Outer:
        while (true) {
            Label_0110: {
                if (!g) {
                    break Label_0110;
                }
                final String[] b = this.r[n3].b;
                int n4 = 0;
                int n5 = 0;
                int n7 = 0;
                int n8 = 0;
                while (true) {
                    while (true) {
                        Label_0072: {
                            if (!g) {
                                break Label_0072;
                            }
                            Label_0069: {
                                if (b[n5] == null) {
                                    break Label_0069;
                                }
                                final int n6 = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent() + fontMetrics.getLeading();
                                n4 = n7 + n8;
                            }
                            ++n5;
                        }
                        if (n5 < b.length) {
                            continue Label_0066_Outer;
                        }
                        break;
                    }
                    n2 += n4 + 4;
                    n7 = n3;
                    n8 = n - 1;
                    if (g) {
                        continue;
                    }
                    break;
                }
                if (n7 < n8) {
                    n2 += this.p;
                }
                ++n3;
            }
            if (n3 >= n) {
                return n2;
            }
            continue;
        }
    }
    
    private void a(final int n) {
        final boolean g = GraphSerie.G;
        int n2 = 0;
        while (true) {
            Label_0179: {
                if (!g) {
                    break Label_0179;
                }
                l l = this.r[n2];
                int n3 = this.j.bf ? l.c : l.d;
                int n4 = n2 + 1;
                while (true) {
                    Label_0170: {
                        if (!g) {
                            break Label_0170;
                        }
                        final l i = this.r[n4];
                        final int n5 = this.j.bf ? i.c : i.d;
                        if (this.j.bf ? (n5 > n3) : (n5 < n3)) {
                            this.r[n4] = null;
                            this.r[n2] = i;
                            this.r[n4] = l;
                            n3 = n5;
                            l = this.r[n2];
                        }
                        ++n4;
                    }
                    if (n4 < n) {
                        continue;
                    }
                    break;
                }
                ++n2;
            }
            if (n2 >= n - 1) {
                return;
            }
            continue;
        }
    }
    
    void a(final Graphics graphics) {
        final boolean g = GraphSerie.G;
        this.o = null;
        this.n = null;
        final int a = this.a();
        if (this.j.ba) {
            this.a(a);
        }
        graphics.setFont(super.f);
        final FontMetrics fontMetrics = graphics.getFontMetrics(super.f);
        final int width = this.j.getSize().width;
        final int height = this.j.getSize().height;
        int a2 = 0;
        int b = 0;
        Label_0131: {
            if (!this.j.bf && a != 0) {
                b = this.b(fontMetrics, a);
                if (!g) {
                    break Label_0131;
                }
            }
            if (this.j.bf && a != 0) {
                a2 = this.a(fontMetrics, a);
            }
        }
        int n = 0;
    Label_1475_Outer:
        while (true) {
            Label_1589: {
                if (!g) {
                    break Label_1589;
                }
                boolean b2 = false;
                boolean b3 = false;
                final l l = this.r[n];
                final String[] b4 = l.b;
                this.a(fontMetrics, b4);
                final int c = l.c;
                final int d = l.d;
                int x = 0;
                int n2 = 0;
                final int n3 = (!this.j.bc && this.j.ba) ? 20 : 10;
                Label_0348: {
                    if (!this.j.bf) {
                        x = c + n3;
                        Label_0286: {
                            if (!this.j.bc && this.j.ba) {
                                if (this.n == null) {
                                    n2 = (height - b) / 2 + this.m;
                                }
                                if (!g) {
                                    break Label_0286;
                                }
                            }
                            n2 = d - 4;
                        }
                        if (!g) {
                            break Label_0348;
                        }
                    }
                    n2 = d - n3;
                    if (!this.j.bc && this.j.ba) {
                        if (this.n == null) {
                            x = (width + a2) / 2 - this.l;
                        }
                        if (!g) {
                            break Label_0348;
                        }
                    }
                    x = c;
                }
                if (c + n3 + this.l > width) {
                    Label_0434: {
                        if (this.j.bf && (!this.j.ba || (this.j.ba && this.j.bc))) {
                            x = c - this.l;
                            if (!g) {
                                break Label_0434;
                            }
                        }
                        if (!this.j.bf) {
                            x = c - n3;
                        }
                    }
                    b2 = true;
                }
                if (n2 - this.m < 0) {
                    Label_0520: {
                        if (!this.j.bf && (!this.j.ba || (this.j.ba && this.j.bc))) {
                            n2 = d + 4 + this.m;
                            if (!g) {
                                break Label_0520;
                            }
                        }
                        if (this.j.bf) {
                            n2 = d + n3;
                        }
                    }
                    b3 = true;
                }
                this.o = new Rectangle(x, n2 - this.m, this.l, this.m);
                Label_0642: {
                    if (this.n != null) {
                        if (!this.j.bf) {
                            n2 = this.n.y + this.n.height + this.p + this.m;
                            this.o.y = n2 - this.m;
                            if (!g) {
                                break Label_0642;
                            }
                        }
                        x = this.n.x - this.p - this.l;
                        this.o.x = x;
                    }
                }
                this.n = this.o;
                Label_1386: {
                    if (super.g) {
                        graphics.setColor(super.b);
                        Label_1320: {
                            if (this.q == 0) {
                                final int n4 = n2 - this.m / 2 - this.m / 5;
                                final int n5 = n2 - this.m / 2 + this.m / 5;
                                final int n6 = x + this.l / 2 - this.l / 7;
                                final int n7 = x + this.l / 2 + this.l / 7;
                                int[] array;
                                int[] array2;
                                if (!this.j.bf) {
                                    array = new int[] { c, x, x, x + (b2 ? (-this.l) : this.l), x + (b2 ? (-this.l) : this.l), x, x };
                                    array2 = new int[] { d, n4, n2 - this.m, n2 - this.m, n2, n2, n5 };
                                }
                                else {
                                    array = new int[] { c, n6, x, x, x + this.l, x + this.l, n7 };
                                    array2 = new int[] { d, n2, n2, n2 + (b3 ? this.m : (-this.m)), n2 + (b3 ? this.m : (-this.m)), n2, n2 };
                                }
                                graphics.fillPolygon(array, array2, array.length);
                                if (super.h) {
                                    graphics.setColor(Color.black);
                                    graphics.drawPolygon(array, array2, array.length);
                                }
                                if (!g) {
                                    break Label_1320;
                                }
                            }
                            if (!this.j.bf) {
                                graphics.fillRect(b2 ? (x - this.l) : x, n2 - this.m, this.l, this.m);
                                if (super.h) {
                                    graphics.setColor(Color.black);
                                    graphics.drawRect(b2 ? (x - this.l) : x, n2 - this.m, this.l, this.m);
                                }
                                graphics.setColor(this.k);
                                graphics.drawLine(c, d, x + (b2 ? 1 : -1), n2 - this.m / 2);
                                if (!g) {
                                    break Label_1320;
                                }
                            }
                            graphics.fillRect(x, b3 ? n2 : (n2 - this.m), this.l, this.m);
                            if (super.h) {
                                graphics.setColor(Color.black);
                                graphics.drawRect(x, b3 ? n2 : (n2 - this.m), this.l, this.m);
                            }
                            graphics.setColor(this.k);
                            graphics.drawLine(c, d, x + this.l / 2, n2);
                        }
                        if (!g) {
                            break Label_1386;
                        }
                    }
                    graphics.setColor(this.k);
                    if (!this.j.bf) {
                        graphics.drawLine(c, d, x, n2 - this.m / 2);
                        if (!g) {
                            break Label_1386;
                        }
                    }
                    graphics.drawLine(c, d, x + this.l / 2, n2);
                }
                graphics.setColor(super.c);
                int n8 = 0;
                Label_1454: {
                    if (!this.j.bf) {
                        n8 = n2 - this.m + 3 + fontMetrics.getMaxAscent();
                        if (!g) {
                            break Label_1454;
                        }
                    }
                    n8 = n2 + (b3 ? 0 : (-this.m)) + 3 + fontMetrics.getMaxAscent();
                }
                int n9 = 0;
                while (true) {
                    while (true) {
                        Label_1557: {
                            if (!g) {
                                break Label_1557;
                            }
                            Label_1554: {
                                if (b4[n9] == null && !g) {
                                    break Label_1554;
                                }
                                Label_1535: {
                                    if (!this.j.bf) {
                                        graphics.drawString(b4[n9], x + (b2 ? (-this.l) : 0) + 3, n8);
                                        if (!g) {
                                            break Label_1535;
                                        }
                                    }
                                    graphics.drawString(b4[n9], x + 3, n8);
                                }
                                n8 += fontMetrics.getMaxDescent() + fontMetrics.getMaxAscent() + fontMetrics.getLeading();
                            }
                            ++n9;
                        }
                        if (n9 < b4.length) {
                            continue Label_1475_Outer;
                        }
                        break;
                    }
                    graphics.setColor(super.b);
                    this.r[n] = null;
                    if (g) {
                        continue;
                    }
                    break;
                }
                ++n;
            }
            if (n >= a) {
                return;
            }
            continue;
        }
    }
    
    ToolTip(final Graph j) {
        this.k = Color.black;
        this.p = 4;
        this.q = 0;
        this.r = new l[30];
        this.j = j;
        super.b = new Color(153, 255, 204);
    }
}
