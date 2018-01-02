// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Graphics;
import java.util.Observable;
import java.awt.Color;

public class OHLCSerie extends GraphSerie implements g
{
    public static final int BAR_OHLC = 0;
    public static final int BAR_HLC = 1;
    public static final int LINE_OHLC = 2;
    public static final int LINE_HLC = 3;
    public static final int CANDLESTICK = 4;
    public static final int ROUNDED_CANDLESTICK = 5;
    int[][] H;
    double[] I;
    int J;
    private int K;
    private Color L;
    private Color M;
    private k N;
    private int O;
    private int P;
    String[] Q;
    
    public void setType(final int j) {
        this.J = j;
        if (super.d != null) {
            super.d.getLegend().y = true;
            super.d.cg = true;
        }
    }
    
    public void setWidth(final int k) {
        this.K = k;
    }
    
    public void setLineThickness(int p) {
        if (p < 1) {
            p = 1;
        }
        this.P = p;
    }
    
    public void setDashSize(final int o) {
        this.O = o;
    }
    
    public void setBearishColor(final Color l) {
        this.L = l;
    }
    
    public void setBullishColor(final Color m) {
        this.M = m;
    }
    
    public void setToolTipText(final String[] array) {
        if (array != null) {
            int n = 0;
            while (true) {
                Label_0024: {
                    if (!GraphSerie.G) {
                        break Label_0024;
                    }
                    this.Q[n] = array[n];
                    ++n;
                }
                if (n < array.length) {
                    continue;
                }
                break;
            }
        }
    }
    
    public void update(final Observable observable, final Object o) {
        final boolean g = GraphSerie.G;
        super.e = (Graphics)o;
        super.A = super.d.bI.e();
        if (this.H == null) {
            this.H = new int[super.b.length][4];
        }
        if (this.I == null) {
            this.I = super.d.bI.i();
        }
        final Rectangle k = super.d.bI.k();
        if (super.m == null) {
            super.m = new Shape[super.b.length];
        }
        int n = 0;
        while (true) {
            while (true) {
                Label_0315: {
                    if (!g) {
                        break Label_0315;
                    }
                    final OHLCSerie ohlcSerie = this;
                    final double[] array = ohlcSerie.b[n];
                    final double n2 = array[0];
                    final double n3 = array[1];
                    final double n4 = array[2];
                    final double n5 = array[3];
                    this.H[n][0] = super.d.bI.a(com.jinsight.jetchart.p.b, this.I, k.y, k.y + k.height, n2);
                    this.H[n][1] = super.d.bI.a(com.jinsight.jetchart.p.b, this.I, k.y, k.y + k.height, n3);
                    this.H[n][2] = super.d.bI.a(com.jinsight.jetchart.p.b, this.I, k.y, k.y + k.height, n4);
                    this.H[n][3] = super.d.bI.a(com.jinsight.jetchart.p.b, this.I, k.y, k.y + k.height, n5);
                    ++n;
                }
                if (n < super.b.length) {
                    continue;
                }
                break;
            }
            final OHLCSerie ohlcSerie = this;
            if (!g) {
                this.b();
                return;
            }
            continue;
        }
    }
    
    void b() {
        final boolean g = GraphSerie.G;
        int n = 0;
        while (true) {
            while (true) {
                Label_1191: {
                    if (!g) {
                        break Label_1191;
                    }
                    Label_1188: {
                        if (n < super.E && !g) {
                            break Label_1188;
                        }
                        final OHLCSerie ohlcSerie = this;
                        final int n2 = ohlcSerie.H[n][0];
                        final int n3 = this.H[n][1];
                        final int n4 = this.H[n][2];
                        final int n5 = this.H[n][3];
                        final int n6 = this.K / 2;
                        Shape shape = null;
                        if (this.J == 0 || this.J == 1) {
                            super.e.setColor(super.s);
                            super.e.fillRect(super.A[n] - n6, n2, this.K, n3 - n2 + 1);
                            if (this.J == 0) {
                                super.e.drawLine(super.A[n] - n6 - 1, n4, super.A[n] - n6 - this.O - 1, n4);
                            }
                            super.e.drawLine(super.A[n] - n6 + this.K, n5, super.A[n] - n6 + this.K + this.O, n5);
                            shape = new Polygon(new int[] { super.A[n] - n6 - 1, super.A[n] - n6 + this.K + 1, super.A[n] - n6 + this.K + 1, super.A[n] - n6 - 1 }, new int[] { n2, n2, n3, n3 }, 4);
                        }
                        else if (this.J == 4 || this.J == 5) {
                            super.e.setColor(Color.black);
                            super.e.drawLine(super.A[n], n2, super.A[n], n3);
                            Label_0734: {
                                if (super.b[n][2] <= super.b[n][3]) {
                                    super.e.setColor(this.M);
                                    Label_0563: {
                                        if (this.J == 4) {
                                            super.e.fillRect(super.A[n] - n6, n5, this.K, n4 - n5);
                                            super.e.setColor(Color.black);
                                            super.e.drawRect(super.A[n] - n6, n5, this.K, n4 - n5);
                                            if (!g) {
                                                break Label_0563;
                                            }
                                        }
                                        super.e.fillRoundRect(super.A[n] - n6, n5, this.K, n4 - n5, this.K, 10);
                                        super.e.setColor(Color.black);
                                        super.e.drawRoundRect(super.A[n] - n6, n5, this.K, n4 - n5 - 1, this.K, 10);
                                    }
                                    if (!g) {
                                        break Label_0734;
                                    }
                                }
                                super.e.setColor(this.L);
                                if (this.J == 4) {
                                    super.e.fillRect(super.A[n] - n6, n4, this.K, n5 - n4);
                                    super.e.setColor(Color.black);
                                    super.e.drawRect(super.A[n] - n6, n4, this.K, n5 - n4);
                                    if (!g) {
                                        break Label_0734;
                                    }
                                }
                                super.e.fillRoundRect(super.A[n] - n6, n4, this.K, n5 - n4, this.K, 10);
                                super.e.setColor(Color.black);
                                super.e.drawRoundRect(super.A[n] - n6, n4, this.K, n5 - n4 - 1, this.K, 10);
                            }
                            final int[] array = { super.A[n] - 1, super.A[n] + 1, super.A[n] + 1, super.A[n] - n6 + this.K, super.A[n] - n6 + this.K, super.A[n] + 1, super.A[n] + 1, super.A[n] - 1, super.A[n] - 1, super.A[n] - n6, super.A[n] - n6, super.A[n] - 1 };
                            int[] array2;
                            if (super.b[n][2] <= super.b[n][3]) {
                                array2 = new int[] { n2, n2, n5, n5, n4, n4, n3, n3, n4, n4, n5, n5 };
                            }
                            else {
                                array2 = new int[] { n2, n2, n4, n4, n5, n5, n3, n3, n5, n5, n4, n4 };
                            }
                            shape = new Polygon(array, array2, 12);
                        }
                        else if (this.J == 2 || this.J == 3) {
                            if (n < this.H.length - 1) {
                                super.e.setColor(super.s);
                                super.d.bI.a(super.e, super.A[n], this.H[n][3], super.A[n + 1], this.H[n + 1][3], this.P);
                            }
                            shape = new Rectangle(super.A[n] - 2, n5 - 2, 5, 5);
                        }
                        super.m[n] = shape;
                    }
                    ++n;
                }
                if (n < this.H.length) {
                    continue;
                }
                break;
            }
            this.f();
            final OHLCSerie ohlcSerie = this;
            if (!g) {
                if (this.N == null) {
                    this.N = new k();
                }
                this.N.a(super.d, super.e);
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
            int i = 0;
            while (true) {
                Label_0290: {
                    if (!g) {
                        break Label_0290;
                    }
                    if (i >= super.E || g) {
                        Rectangle bounds;
                        if (this.J == 2 || this.J == 3) {
                            bounds = (Rectangle)super.m[i];
                        }
                        else {
                            bounds = ((Polygon)super.m[i]).getBounds();
                        }
                        this.a(bounds, n2, n3);
                        b = ((n == 0 || !super.d.Z) ? bounds.contains(n2, n3) : (b2 ? super.r : bounds.contains(n2, n3)));
                        if (b) {
                            this.a(bounds);
                            Label_0265: {
                                if (n == 0) {
                                    super.f = i;
                                    if (!g) {
                                        break Label_0265;
                                    }
                                }
                                if (n == 1) {
                                    super.h = i;
                                    if (!g) {
                                        break Label_0265;
                                    }
                                }
                                if (n == 2) {
                                    super.i = i;
                                }
                            }
                            super.d.bn = super.d.g[i];
                            if (!g) {
                                return b;
                            }
                        }
                    }
                    ++i;
                }
                if (i < super.m.length) {
                    continue;
                }
                break;
            }
        }
        return b;
    }
    
    void a(final Rectangle rectangle) {
        super.C = rectangle.x + rectangle.width / 2;
        super.D = rectangle.y + ((this.J == 2 || this.J == 3) ? (rectangle.height / 2) : 0);
    }
    
    double[] a() {
        super.z = super.b[super.h][3];
        return super.b[super.h];
    }
    
    void f() {
        final boolean g = GraphSerie.G;
        if (this.J == 2 || this.J == 3) {
            int n = 0;
            while (true) {
                Label_0175: {
                    if (!g) {
                        break Label_0175;
                    }
                    if (n >= super.E || g) {
                        Label_0093: {
                            if (super.k && super.j && super.f == n) {
                                super.e.setColor(super.l);
                                if (!g) {
                                    break Label_0093;
                                }
                            }
                            if (super.o) {
                                super.e.setColor(super.u);
                            }
                        }
                        if ((super.k && super.j && super.f == n) || super.o) {
                            super.e.fillRect(super.A[n] - 2, this.H[n][3] - 2, 5, 5);
                        }
                        this.b(n, super.A[n] - 2, this.H[n][3] - 2);
                    }
                    ++n;
                }
                if (n < super.A.length) {
                    continue;
                }
                break;
            }
        }
    }
    
    void d() {
    }
    
    public OHLCSerie(final double[][] array) {
        super(array);
        this.J = 0;
        this.K = 2;
        this.L = Color.black;
        this.M = Color.white;
        this.O = 7;
        this.P = 1;
        this.Q = new String[] { b("\u000bc\u0006#\u0010"), b("\u000fe\u0016q"), b("\fz\u0004%\u0010"), b("\u0000f\u000e8Oy") };
        super.s = Color.black;
    }
    
    public OHLCSerie(final double[][] array, final String x) {
        this(array);
        super.x = x;
    }
    
    private static String b(final String s) {
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
                            c2 = 'C';
                            break;
                        }
                        case 1: {
                            c2 = '\n';
                            break;
                        }
                        case 2: {
                            c2 = 'a';
                            break;
                        }
                        case 3: {
                            c2 = 'K';
                            break;
                        }
                        default: {
                            c2 = '*';
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
