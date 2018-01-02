// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.FontMetrics;
import java.awt.Font;
import java.text.DecimalFormat;
import java.awt.Toolkit;
import java.awt.Polygon;
import java.awt.Graphics;
import java.util.Observable;
import java.awt.Color;
import java.awt.Rectangle;

public class BarSerie extends GraphSerie
{
    private int H;
    Rectangle[] I;
    Rectangle J;
    Rectangle K;
    Rectangle L;
    int[] M;
    int[] N;
    int[] O;
    int[] P;
    int[] Q;
    int[] R;
    Rectangle[] S;
    int T;
    Color[] U;
    protected k V;
    
    public void setColors(final Color[] u) {
        this.U = u;
    }
    
    public void setValues(final double[] a) {
        super.a = a;
        this.i();
    }
    
    public void setWidth(final int h) {
        this.H = h;
        if (super.d != null) {
            super.d.cg = true;
        }
    }
    
    public void update(final Observable observable, final Object o) {
        final boolean g = GraphSerie.G;
        final p p2 = (p)observable;
        super.e = (Graphics)o;
        this.I = p2.b(this);
        this.K = p2.j();
        this.L = p2.k();
        if (super.m == null) {
            super.m = new Polygon[this.I.length];
        }
        super.y = (super.d.bc ? (super.d.bf ? super.d.p : super.d.o) : 0) * this.e();
        this.T = 0;
        while (true) {
            while (true) {
                Label_0349: {
                    if (!g) {
                        break Label_0349;
                    }
                    final BarSerie barSerie = this;
                    if (((barSerie.d.bf || this.T >= super.E) && (!super.d.bf || this.I.length - 1 - this.T >= super.E)) || g) {
                        Label_0233: {
                            if (this.U != null) {
                                if (!super.d.bf) {
                                    super.s = this.U[this.T];
                                    if (!g) {
                                        break Label_0233;
                                    }
                                }
                                super.s = this.U[this.I.length - 1 - this.T];
                            }
                        }
                        this.J = this.I[this.T];
                        this.b();
                        Rectangle rectangle;
                        if (!super.d.bf) {
                            rectangle = this.S[this.T];
                        }
                        else {
                            rectangle = this.S[this.I.length - this.T - 1];
                        }
                        rectangle.setBounds(this.M[0], this.N[0], this.M[1] - this.M[0], this.N[2] - this.N[0]);
                    }
                    ++this.T;
                }
                if (this.T < this.I.length) {
                    continue;
                }
                break;
            }
            final BarSerie barSerie = this;
            if (!g) {
                if (this.V == null) {
                    this.V = new k();
                }
                this.V.a(super.d, super.e);
                return;
            }
            continue;
        }
    }
    
    boolean a(final int n, final int n2, final int n3) {
        final boolean g = GraphSerie.G;
        Label_0044: {
            if (n == 0) {
                super.f = -1;
                if (!g) {
                    break Label_0044;
                }
            }
            if (n == 1) {
                super.h = -1;
                if (!g) {
                    break Label_0044;
                }
            }
            if (n == 2) {
                super.i = -1;
            }
        }
        boolean b = false;
        if (super.m != null) {
            final boolean b2 = super.d.ba && !super.d.bc;
            int n4 = super.m.length - 1;
            while (true) {
                Label_0353: {
                    if (!g) {
                        break Label_0353;
                    }
                    if (((super.d.bf || n4 >= super.E) && (!super.d.bf || super.m.length - 1 - n4 >= super.E)) || g) {
                        final Polygon polygon = (Polygon)super.m[n4];
                        final Rectangle rectangle = super.d.bf ? this.S[super.m.length - 1 - n4] : this.S[n4];
                        this.a(rectangle, n2, n3);
                        b = ((n == 0 || !super.d.Z) ? polygon.contains(n2, n3) : (b2 ? super.r : polygon.contains(n2, n3)));
                        if (b) {
                            this.a(rectangle);
                            final int i = super.d.bf ? (super.m.length - n4 - 1) : n4;
                            Label_0345: {
                                if (n == 0) {
                                    super.f = i;
                                    if (!g) {
                                        break Label_0345;
                                    }
                                }
                                if (n == 1) {
                                    super.h = i;
                                    super.d.bn = super.d.g[i];
                                    if (!g) {
                                        break Label_0345;
                                    }
                                }
                                if (n == 2) {
                                    super.i = i;
                                }
                            }
                            if (!g) {
                                return b;
                            }
                        }
                    }
                    --n4;
                }
                if (n4 >= 0) {
                    continue;
                }
                break;
            }
        }
        return b;
    }
    
    void a(final Rectangle rectangle) {
        if (!super.d.bf) {
            super.C = rectangle.x + rectangle.width / 2;
            super.D = rectangle.y;
            if (!GraphSerie.G) {
                return;
            }
        }
        super.C = rectangle.x + rectangle.width;
        super.D = rectangle.y + rectangle.height / 2;
    }
    
    int g() {
        return this.H;
    }
    
    void d() {
        final boolean g = GraphSerie.G;
        final Color color = super.e.getColor();
        final Font font = super.e.getFont();
        if (this.I != null) {
            final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(super.c);
            final int n = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
            super.e.setFont(super.c);
            DecimalFormat decimalFormat = null;
            final String bw = super.d.bw;
            if (bw != null) {
                decimalFormat = new DecimalFormat();
                decimalFormat.applyPattern(bw);
            }
            for (int i = 0; i < this.I.length; ++i) {
                Rectangle rectangle = null;
                Label_0147: {
                    if (!super.d.bf) {
                        rectangle = this.I[i];
                        if (!g) {
                            break Label_0147;
                        }
                    }
                    rectangle = this.I[this.I.length - 1 - i];
                }
                String s = null;
                Label_0183: {
                    if (decimalFormat != null) {
                        s = decimalFormat.format(super.a[i]);
                        if (!g) {
                            break Label_0183;
                        }
                    }
                    s = Double.toString(super.a[i]);
                }
                final int stringWidth = fontMetrics.stringWidth(s);
                int n3 = 0;
                int n4 = 0;
                Label_0355: {
                    if (!super.d.bf) {
                        final int n2 = (super.a[i] >= 0.0) ? (rectangle.y - fontMetrics.getMaxDescent() - 10) : (rectangle.y + rectangle.height + fontMetrics.getMaxAscent() + 10);
                        n3 = rectangle.width / 2 + rectangle.x - stringWidth / 2;
                        n4 = n2;
                        if (!g) {
                            break Label_0355;
                        }
                    }
                    Label_0330: {
                        if (super.a[i] >= 0.0) {
                            n3 = rectangle.x + rectangle.width + 10;
                            if (!g) {
                                break Label_0330;
                            }
                        }
                        n3 = rectangle.x - 5 - stringWidth;
                    }
                    n4 = rectangle.y + rectangle.height / 2 + n / 2 - fontMetrics.getMaxDescent();
                }
                if (super.q) {
                    super.e.setColor(super.w);
                    super.e.fillRect(n3 + super.y - 4, n4 - super.y - fontMetrics.getMaxAscent() - 2, stringWidth + 8, n + 4);
                    super.e.setColor(Color.black);
                    super.e.drawRect(n3 + super.y - 4, n4 - super.y - fontMetrics.getMaxAscent() - 2, stringWidth + 8, n + 4);
                }
                super.e.setColor(super.v);
                super.e.drawString(s, n3 + super.y, n4 - super.y);
            }
        }
        super.e.setFont(font);
        super.e.setColor(color);
    }
    
    void f() {
    }
    
    void b() {
        final boolean g = GraphSerie.G;
        final Color color = super.e.getColor();
        super.e.setColor(super.s);
        final int n = this.J.x + super.y;
        int x = this.J.x + this.J.width + super.y;
        if (this.J != this.I[this.I.length - 1] && super.d.bf && super.d.bc && this.J.x + this.J.width == this.L.x) {
            x = this.L.x;
        }
        final int n2 = x;
        final int n3 = n;
        this.M[0] = n;
        this.M[1] = x;
        this.M[2] = n2;
        this.M[3] = n3;
        final int[] array = { this.J.x + super.y, this.J.x + this.J.width + super.y, this.J.x + this.J.width + super.y, this.J.x + super.y };
        int y = this.J.y - super.y;
        if (this.J != this.I[this.I.length - 1] && !super.d.bf && super.d.bc && this.J.y == this.K.y) {
            y = this.K.y;
        }
        final int n4 = y;
        int n5 = this.J.y + this.J.height - super.y;
        if (!super.d.bf && this.J.y == this.K.y && n5 < this.K.y) {
            n5 = y;
        }
        final int n6 = n5;
        this.N[0] = y;
        this.N[1] = n4;
        this.N[2] = n5;
        this.N[3] = n6;
        final int[] array2 = { this.J.y - super.y, this.J.y - super.y, this.J.y + this.J.height - super.y, this.J.y + this.J.height - super.y };
        final Color h = this.h();
        Label_0589: {
            if (super.d.bc) {
                if (!super.d.bf) {
                    this.a(this.M, this.N, array2, h);
                    if (!g) {
                        break Label_0589;
                    }
                    AbstractSerie.n = !AbstractSerie.n;
                }
                this.b(this.M, this.N, array, h);
            }
        }
        super.e.setColor(h);
        super.e.fillPolygon(this.M, this.N, 4);
        super.e.setColor(Color.black);
        super.e.drawPolygon(this.M, this.N, 4);
        super.e.setColor(color);
        if (super.d.cg) {
            Polygon polygon;
            if (!super.d.bc) {
                polygon = new Polygon(this.M, this.N, 4);
            }
            else {
                polygon = new Polygon(new int[] { this.M[0], this.Q[1], this.Q[2], this.O[2], this.O[3], this.M[3] }, new int[] { this.N[0], this.R[1], this.R[2], this.P[2], this.P[3], this.N[3] }, 6);
            }
            super.m[this.T] = polygon;
        }
    }
    
    Color h() {
        Color color;
        if (super.k) {
            boolean b = false;
            Label_0083: {
                if (!super.d.bf) {
                    b = (this.T == super.i && super.j);
                    if (!GraphSerie.G) {
                        break Label_0083;
                    }
                }
                b = (this.I.length - 1 - this.T == super.i && super.j);
            }
            if (b) {
                color = super.l;
            }
            else {
                color = super.s;
            }
        }
        else {
            color = super.s;
        }
        return color;
    }
    
    void a(final int[] array, final int[] array2, final int[] array3, final Color color) {
        final boolean g = GraphSerie.G;
        this.O = new int[4];
        this.P = new int[4];
        this.Q = new int[4];
        this.R = new int[4];
        final int n = array2[0];
        final int n2 = array3[0];
        final int n3 = array3[2];
        final int n4 = array[0];
        final int n5 = array[1];
        final int n6 = array[1] - array[0];
        final int n7 = array3[3] - array3[0];
        final int o = super.d.o;
        Label_0542: {
            if (super.a[this.T] >= 0.0) {
                this.O[0] = n5;
                this.O[1] = n5 + o;
                this.O[2] = n5 + o;
                this.O[3] = n5;
                this.P[0] = n2;
                this.P[1] = n2 - o;
                this.P[2] = n3 - o;
                this.P[3] = n3;
                this.Q[0] = n4;
                this.Q[1] = n4 + o;
                this.Q[2] = n5 + o;
                this.Q[3] = n5;
                this.R[0] = n2;
                this.R[1] = n2 - o;
                this.R[2] = n2 - o;
                this.R[3] = n2;
                if (!g) {
                    break Label_0542;
                }
            }
            this.O[0] = n5;
            this.O[1] = n5 + o;
            this.O[2] = n5 + o;
            this.O[3] = n5;
            Label_0379: {
                if (this.J.x + this.J.width <= this.K.x + this.K.width) {
                    this.P[0] = n;
                    this.P[1] = n;
                    if (!g) {
                        break Label_0379;
                    }
                }
                this.P[0] = n2;
                this.P[1] = n2 - o;
            }
            this.P[2] = n3 - o;
            if (this.J != this.I[this.I.length - 1] && this.P[2] < n) {
                final int[] o2 = this.O;
                final int n8 = 1;
                o2[n8] -= n - this.P[2];
                this.O[2] = this.O[1];
                this.P[2] = n;
            }
            this.P[3] = n3;
            this.Q[0] = n4;
            this.Q[1] = n4 + o;
            this.Q[2] = n5 + o;
            this.Q[3] = n5;
            this.R[0] = n2;
            this.R[1] = n2 - o;
            this.R[2] = n2 - o;
            this.R[3] = n2;
        }
        if (this.J == this.I[this.I.length - 1] || n3 > n) {
            super.e.setColor(color.darker());
            super.e.fillPolygon(this.O, this.P, 4);
            super.e.setColor(Color.black);
            super.e.drawPolygon(this.O, this.P, 4);
        }
        super.e.setColor(color.darker());
        super.e.fillPolygon(this.Q, this.R, 4);
        super.e.setColor(Color.black);
        super.e.drawPolygon(this.Q, this.R, 4);
        super.e.setColor(color);
        if (AbstractSerie.n) {
            GraphSerie.G = !g;
        }
    }
    
    void b(final int[] array, final int[] array2, final int[] array3, final Color color) {
        final boolean g = GraphSerie.G;
        this.O = new int[4];
        this.P = new int[4];
        this.Q = new int[4];
        this.R = new int[4];
        final int n = array2[0];
        final int n2 = array2[2];
        final int n3 = array[0];
        final int n4 = array3[1];
        final int n5 = array[1];
        final int n6 = array3[1] - array3[0];
        final int n7 = array2[3] - array2[0];
        final int p4 = super.d.p;
        Label_0505: {
            if (n3 >= this.L.x + super.y) {
                this.O[0] = n4;
                this.O[1] = n4 + p4;
                this.O[2] = n4 + p4;
                this.O[3] = n4;
                this.P[0] = n;
                this.P[1] = n - p4;
                this.P[2] = n2 - p4;
                this.P[3] = n2;
                this.Q[0] = n3;
                this.Q[1] = n3 + p4;
                this.Q[2] = n4 + p4;
                this.Q[3] = n4;
                this.R[0] = n;
                this.R[1] = n - p4;
                this.R[2] = n - p4;
                this.R[3] = n;
                if (!g) {
                    break Label_0505;
                }
            }
            this.Q[0] = n3;
            this.Q[1] = n3 + p4;
            Label_0349: {
                if (this.J != this.I[this.I.length - 1]) {
                    this.Q[2] = n5;
                    this.Q[3] = n5;
                    if (!g) {
                        break Label_0349;
                    }
                }
                this.Q[2] = n4 + p4;
                this.Q[3] = n4;
            }
            this.R[0] = n;
            this.R[1] = n - p4;
            this.R[2] = n - p4;
            this.R[3] = n;
            if (this.Q[1] > this.L.x) {
                final int[] r = this.R;
                final int n8 = 2;
                r[n8] -= this.Q[1] - n5;
                this.Q[1] = n5;
            }
            this.O[0] = n4;
            this.O[1] = n4 + p4;
            this.O[2] = n4 + p4;
            this.O[3] = n4;
            this.P[0] = n;
            this.P[1] = n - p4;
            this.P[2] = n2 - p4;
            this.P[3] = n2;
        }
        super.e.setColor(color.darker());
        super.e.fillPolygon(this.O, this.P, 4);
        super.e.setColor(Color.black);
        super.e.drawPolygon(this.O, this.P, 4);
        if (n3 < n5) {
            super.e.setColor(color.darker());
            super.e.fillPolygon(this.Q, this.R, 4);
            super.e.setColor(Color.black);
            super.e.drawPolygon(this.Q, this.R, 4);
        }
        super.e.setColor(color);
    }
    
    private void i() {
        if (super.a != null) {
            this.S = new Rectangle[super.a.length];
            int n = 0;
            while (true) {
                Label_0043: {
                    if (!GraphSerie.G) {
                        break Label_0043;
                    }
                    this.S[n] = new Rectangle();
                    ++n;
                }
                if (n < this.S.length) {
                    continue;
                }
                break;
            }
        }
    }
    
    public BarSerie(final double[] array, final String s) {
        super(array, s);
        this.H = 10;
        this.M = new int[4];
        this.N = new int[4];
        this.O = new int[4];
        this.P = new int[4];
        this.Q = new int[4];
        this.R = new int[4];
        this.S = null;
        this.T = 0;
        this.i();
        super.o = false;
    }
}
