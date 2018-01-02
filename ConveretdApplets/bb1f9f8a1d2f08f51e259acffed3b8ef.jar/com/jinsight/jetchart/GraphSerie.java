// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.Shape;
import java.awt.Polygon;
import java.awt.FontMetrics;
import java.awt.Font;
import java.text.DecimalFormat;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.Observable;
import java.awt.Color;

public abstract class GraphSerie extends AbstractSerie
{
    boolean o;
    boolean p;
    boolean q;
    boolean r;
    Color s;
    Color t;
    Color u;
    Color v;
    Color w;
    String x;
    int y;
    double z;
    int[] A;
    int[] B;
    int C;
    int D;
    int E;
    private k F;
    static boolean G;
    
    public void setMarksEnabled(final boolean o) {
        this.o = o;
    }
    
    public void setMarksColor(final Color u) {
        this.u = u;
    }
    
    public void setMarkLegendEnabled(final boolean p) {
        this.p = p;
        if (super.d != null) {
            super.d.cg = true;
        }
    }
    
    public void setMarkLegendOpacityEnabled(final boolean q) {
        this.q = q;
    }
    
    public void setMarkLegendForeground(final Color v) {
        this.v = v;
    }
    
    public void setMarkLegendBackground(final Color w) {
        this.w = w;
    }
    
    public void setColor(final Color s) {
        this.s = s;
    }
    
    public void setTitle(final String x) {
        this.x = x;
        if (super.d != null) {
            super.d.cg = true;
        }
    }
    
    public void setStartPoint(int e) {
        if (e < 0) {
            e = 0;
        }
        this.E = e;
    }
    
    public void update(final Observable observable, final Object o) {
        final boolean g = GraphSerie.G;
        final p p2 = (p)observable;
        super.e = (Graphics)o;
        int n = 0;
        if (super.d.bc) {
            n = (super.d.bf ? super.d.p : super.d.o);
        }
        this.y = n * this.e();
        Label_0185: {
            if (!super.d.bf) {
                this.A = p2.e();
                this.B = new int[this.A.length];
                if (!g) {
                    break Label_0185;
                }
            }
            this.B = p2.e();
            final int[] b = new int[this.B.length];
            int n2 = 0;
            while (true) {
                while (true) {
                    Label_0153: {
                        if (!g) {
                            break Label_0153;
                        }
                        b[this.B.length - 1 - n2] = this.B[n2];
                        ++n2;
                    }
                    if (n2 < this.B.length) {
                        continue;
                    }
                    break;
                }
                this.B = b;
                if (g) {
                    continue;
                }
                break;
            }
            this.A = new int[this.B.length];
        }
        final Rectangle j = p2.j();
        final Rectangle k = p2.k();
        int n3 = 0;
        while (true) {
            Label_0320: {
                if (!g) {
                    break Label_0320;
                }
                Label_0317: {
                    if (!super.d.bf) {
                        this.B[n3] = p2.a(com.jinsight.jetchart.p.b, p2.i(), k.y, k.y + k.height, super.a[n3]);
                        if (!g) {
                            break Label_0317;
                        }
                    }
                    this.A[n3] = p2.a(com.jinsight.jetchart.p.c, p2.i(), j.x, j.x + j.width, super.a[n3]);
                }
                ++n3;
            }
            if (n3 >= super.a.length) {
                this.c();
                this.b();
                if (this.F == null) {
                    this.F = new k();
                }
                this.F.a(super.d, super.e);
                this.f();
                return;
            }
            continue;
        }
    }
    
    void c() {
        final boolean g = GraphSerie.G;
        if (super.m == null) {
            super.m = new Rectangle[this.B.length];
        }
        if (super.d.cg && !super.d.D && !super.d.C) {
            int n = 0;
            boolean bc = false;
            while (true) {
                while (true) {
                    Label_0154: {
                        if (!g) {
                            break Label_0154;
                        }
                        final boolean bf = super.d.bf;
                        Label_0151: {
                            if (!bc) {
                                super.m[n] = new Rectangle(this.A[n] - 2, this.B[n] - 2, 5, 5);
                                if (!g) {
                                    break Label_0151;
                                }
                            }
                            super.m[n] = new Rectangle(this.A[this.A.length - 1 - n] - 2, this.B[this.B.length - 1 - n] - 2, 5, 5);
                        }
                        ++n;
                    }
                    if (n < this.B.length) {
                        continue;
                    }
                    break;
                }
                bc = super.d.bc;
                if (g) {
                    continue;
                }
                break;
            }
            if (bc) {
                this.a(this.y);
            }
        }
    }
    
    void d() {
        final Color color = super.e.getColor();
        final Font font = super.e.getFont();
        if (this.A != null) {
            final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(super.c);
            super.e.setFont(super.c);
            DecimalFormat decimalFormat = null;
            final String bw = super.d.bw;
            if (bw != null) {
                decimalFormat = new DecimalFormat();
                decimalFormat.applyPattern(bw);
            }
            for (int i = 0; i < this.A.length; ++i) {
                String s = null;
                Label_0130: {
                    if (decimalFormat != null) {
                        s = decimalFormat.format(super.a[i]);
                        if (!GraphSerie.G) {
                            break Label_0130;
                        }
                    }
                    s = Double.toString(super.a[i]);
                }
                final int stringWidth = fontMetrics.stringWidth(s);
                final int n = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
                final int n2 = this.A[i] - stringWidth / 2;
                final int n3 = (super.a[i] >= 0.0) ? (this.B[i] - fontMetrics.getMaxDescent() - 10) : (this.B[i] + fontMetrics.getMaxAscent() + 10);
                if (this.q) {
                    super.e.setColor(this.w);
                    super.e.fillRect(n2 + this.y - 4, n3 - this.y - fontMetrics.getMaxAscent() - 2, stringWidth + 8, n + 4);
                    super.e.setColor(Color.black);
                    super.e.drawRect(n2 + this.y - 4, n3 - this.y - fontMetrics.getMaxAscent() - 2, stringWidth + 8, n + 4);
                }
                super.e.setColor(this.v);
                super.e.drawString(s, n2 + this.y, n3 - this.y);
            }
        }
        super.e.setColor(color);
        super.e.setFont(font);
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
            int n4 = 0;
            while (true) {
                Label_0310: {
                    if (!g) {
                        break Label_0310;
                    }
                    if (((super.d.bf || n4 >= this.E) && (!super.d.bf || super.m.length - 1 - n4 >= this.E)) || g) {
                        final Rectangle rectangle = (Rectangle)super.m[n4];
                        this.a(rectangle, n2, n3);
                        b = ((n == 0 || !super.d.Z) ? rectangle.contains(n2, n3) : (b2 ? this.r : rectangle.contains(n2, n3)));
                        if (b) {
                            this.a(rectangle);
                            final int i = super.d.bf ? (super.m.length - n4 - 1) : n4;
                            Label_0302: {
                                if (n == 0) {
                                    super.f = i;
                                    if (!g) {
                                        break Label_0302;
                                    }
                                }
                                if (n == 1) {
                                    super.h = i;
                                    super.d.bn = super.d.g[i];
                                    if (!g) {
                                        break Label_0302;
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
                    ++n4;
                }
                if (n4 < super.m.length) {
                    continue;
                }
                break;
            }
        }
        return b;
    }
    
    void a(final Rectangle rectangle) {
        if (!super.d.bf) {
            this.C = rectangle.x + rectangle.width / 2;
            this.D = rectangle.y + rectangle.height / 2;
            if (!GraphSerie.G) {
                return;
            }
        }
        this.C = rectangle.x + rectangle.width / 2;
        this.D = rectangle.y + rectangle.height / 2;
    }
    
    void a(final Rectangle rectangle, final int n, final int n2) {
        if (!super.d.bf) {
            this.r = (rectangle.x <= n && n <= rectangle.x + rectangle.width);
            if (!GraphSerie.G) {
                return;
            }
        }
        this.r = (rectangle.y <= n2 && n2 <= rectangle.y + rectangle.height);
    }
    
    double[] a() {
        this.z = super.a[super.h];
        return new double[] { this.z };
    }
    
    void a(final int n) {
        final boolean g = GraphSerie.G;
        int n2 = 0;
        while (true) {
            Label_0082: {
                if (!g) {
                    break Label_0082;
                }
                final Shape shape = super.m[n2];
                Label_0079: {
                    if (shape instanceof Rectangle) {
                        final Rectangle rectangle2;
                        final Rectangle rectangle = rectangle2 = (Rectangle)shape;
                        rectangle2.x += n;
                        final Rectangle rectangle3 = rectangle;
                        rectangle3.y -= n;
                        if (!g) {
                            break Label_0079;
                        }
                    }
                    if (shape instanceof Polygon) {
                        ((Polygon)shape).translate(n, n);
                    }
                }
                ++n2;
            }
            if (n2 >= super.m.length) {
                return;
            }
            continue;
        }
    }
    
    int e() {
        return super.d.bI.a(this);
    }
    
    abstract void f();
    
    void b(final int n, final int n2, final int n3) {
        if (super.k && super.j && super.i == n) {
            super.e.setColor(super.l);
            super.e.fillRect(n2, n3, 5, 5);
        }
    }
    
    GraphSerie(final double[] a, final String x) {
        this.o = true;
        this.q = true;
        this.s = Color.lightGray;
        this.t = Color.black;
        this.u = Color.black;
        this.v = Color.black;
        this.w = Color.yellow;
        this.x = "";
        super.a = a;
        this.x = x;
    }
    
    GraphSerie(final double[][] b) {
        this.o = true;
        this.q = true;
        this.s = Color.lightGray;
        this.t = Color.black;
        this.u = Color.black;
        this.v = Color.black;
        this.w = Color.yellow;
        this.x = "";
        super.b = b;
    }
    
    GraphSerie(final double[][] b, final String x) {
        this.o = true;
        this.q = true;
        this.s = Color.lightGray;
        this.t = Color.black;
        this.u = Color.black;
        this.v = Color.black;
        this.w = Color.yellow;
        this.x = "";
        super.b = b;
        this.x = x;
    }
}
