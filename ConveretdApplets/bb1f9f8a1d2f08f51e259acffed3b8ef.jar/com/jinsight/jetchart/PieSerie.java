// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.Graphics;
import java.util.Observable;
import java.awt.Color;
import java.awt.Rectangle;

public class PieSerie extends AbstractSerie
{
    Rectangle o;
    private i p;
    private k q;
    int r;
    int s;
    boolean t;
    boolean u;
    Slice[] v;
    int w;
    int x;
    int y;
    int z;
    int A;
    String B;
    private double C;
    Color[] D;
    Color[] E;
    
    public void setSliceColors(final Color[] e) {
        this.E = e;
    }
    
    public void setValues(final double[] a) {
        if (a != null) {
            super.a = a;
            this.d();
            if (this.E == null) {
                this.a(a.length);
            }
        }
    }
    
    public double[] getValues() {
        return super.a;
    }
    
    public void setXRadiusInset(final int r) {
        this.r = r;
    }
    
    public void setYRadiusInset(final int s) {
        this.s = s;
    }
    
    public void setCircledEnabled(final boolean t) {
        this.t = t;
    }
    
    public void setBorderEnabled(final boolean u) {
        this.u = u;
    }
    
    public void setSliceLegendPosition(final int z) {
        this.z = z;
    }
    
    public void setPercentageFormat(final String b) {
        this.B = b;
    }
    
    public void setAngleOffset(final int w) {
        this.w = w;
    }
    
    public void set3DDepth(final int x) {
        this.x = x;
    }
    
    public Slice[] getSlices() {
        return this.v;
    }
    
    public final Slice getSlice(final int n, final int n2) {
        final boolean g = GraphSerie.G;
        Slice slice = null;
        int n3 = 0;
        while (true) {
        Label_0062:
            while (true) {
                Label_0052: {
                    if (!g) {
                        break Label_0052;
                    }
                    if (!super.d.bJ.a(this, this.v[n3], n, n2)) {
                        ++n3;
                        break Label_0052;
                    }
                    slice = this.v[n3];
                    break Label_0062;
                }
                if (n3 < this.v.length) {
                    continue;
                }
                break;
            }
            if (!g) {
                return slice;
            }
            continue;
        }
    }
    
    public Slice getSlice(final int n) {
        return this.v[n];
    }
    
    public final void update(final Observable observable, final Object o) {
        super.e = (Graphics)o;
        this.o = ((q)observable).a(this);
        this.b();
    }
    
    i c() {
        if (this.o != null) {
            final double a = this.o.x + this.o.width / 2.0;
            final double b = this.o.y + this.o.height / 2.0;
            this.p.a = a;
            this.p.b = b;
            return this.p;
        }
        return null;
    }
    
    void b() {
        final boolean g = GraphSerie.G;
        super.e.setColor(Color.black);
        this.C = super.d.bJ.g();
        int x = 0;
        Slice slice = null;
        if (super.d.bc) {
            x = this.x;
        }
        final double c = super.d.bJ.c(this);
        final double d = super.d.bJ.d(this);
        this.y = x;
        int u = 0;
    Label_0096_Outer:
        while (true) {
            if (this.y < 0) {
                u = (this.u ? 1 : 0);
                if (!g) {
                    break;
                }
            }
            int n = u;
            while (true) {
                while (true) {
                    Label_0260: {
                        if (!g) {
                            break Label_0260;
                        }
                        final PieSerie pieSerie = this;
                        slice = pieSerie.v[n];
                        final int n2 = n;
                        slice.a(super.d.bJ.a(n2, super.a, this.C) + this.w, super.d.bJ.b(n2, super.a, this.C) + this.w);
                        slice.a(c);
                        slice.b(d);
                        slice.a(this.o.x, this.o.y + this.y, this.o.width, this.o.height);
                        slice.setColor((this.E == null) ? this.D[n] : this.E[n]);
                        slice.a(super.e);
                        ++n;
                    }
                    if (n < this.v.length) {
                        continue Label_0096_Outer;
                    }
                    break;
                }
                final PieSerie pieSerie = this;
                if (g) {
                    continue;
                }
                break;
            }
            --this.y;
        }
        int n3;
        while (true) {
            Label_0394: {
                if (u == 0) {
                    break Label_0394;
                }
                this.y = x;
                while (true) {
                    Label_0387: {
                        if (!g) {
                            break Label_0387;
                        }
                        n3 = 0;
                        while (true) {
                            Label_0367: {
                                if (!g) {
                                    break Label_0367;
                                }
                                slice.e().y = this.o.y + this.y;
                                slice = this.v[n3];
                                slice.b(super.e);
                                ++n3;
                            }
                            if (n3 < this.v.length) {
                                continue;
                            }
                            break;
                        }
                        --this.y;
                    }
                    if (this.y >= 0) {
                        continue;
                    }
                    break;
                }
            }
            n3 = 0;
            if (g) {
                continue;
            }
            break;
        }
        while (true) {
            while (true) {
                Label_0439: {
                    if (!g) {
                        break Label_0439;
                    }
                    final PieSerie pieSerie2 = this;
                    final Slice slice2 = pieSerie2.v[n3];
                    if (slice2.n) {
                        slice2.p.a(super.e);
                    }
                    ++n3;
                }
                if (n3 < this.v.length) {
                    continue;
                }
                break;
            }
            final PieSerie pieSerie2 = this;
            if (!g) {
                if (this.q == null) {
                    this.q = new k();
                }
                this.q.a(super.d, super.e);
                return;
            }
            continue;
        }
    }
    
    boolean a(final int n, final int n2, final int n3) {
        return false;
    }
    
    double[] a() {
        return null;
    }
    
    private void d() {
        this.v = new Slice[super.a.length];
        int n = 0;
        while (true) {
            Label_0045: {
                if (!GraphSerie.G) {
                    break Label_0045;
                }
                this.v[n] = new Slice(this, super.a[n]);
                ++n;
            }
            if (n >= super.a.length) {
                return;
            }
            continue;
        }
    }
    
    private void a(final int n) {
        final boolean g = GraphSerie.G;
        this.D = new Color[n];
        double n2 = 0.0;
        double n3 = 0.0;
        double n4 = 0.0;
        int n5 = 0;
    Label_0055:
        while (true) {
            while (true) {
                if (g) {
                    break Label_0029;
                }
                Label_0104: {
                    break Label_0104;
                    while (!this.b((int)n2, (int)n3, (int)n4)) {
                        this.D[n5] = new Color((int)n2, (int)n3, (int)n4);
                        if (!g) {
                            ++n5;
                            break Label_0104;
                        }
                    }
                    n2 = Math.random() * 255.0;
                    n3 = Math.random() * 255.0;
                    n4 = Math.random() * 255.0;
                    continue Label_0055;
                }
                if (n5 < n) {
                    continue;
                }
                break;
            }
            if (!g) {
                return;
            }
            continue Label_0055;
        }
    }
    
    private boolean b(final int n, final int n2, final int n3) {
        final boolean g = GraphSerie.G;
        boolean b = false;
        int n4 = 0;
        while (true) {
            Label_0068: {
                if (!g) {
                    break Label_0068;
                }
                final Color color = this.D[n4];
                if (color != null && color.getRed() == n && color.getGreen() == n2 && color.getBlue() == n3) {
                    b = true;
                    if (!g) {
                        return b;
                    }
                }
                ++n4;
            }
            if (n4 < this.D.length) {
                continue;
            }
            break;
        }
        return b;
    }
    
    public PieSerie(final double[] a) {
        this.p = new i(0.0, 0.0);
        this.x = 10;
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.B = b("\u0011keB\n\u0011xh^\u0019");
        super.a = a;
        this.p = new i(0.0, 0.0);
        if (a != null) {
            this.d();
            this.a(a.length);
        }
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
                            c2 = '2';
                            break;
                        }
                        case 1: {
                            c2 = 'H';
                            break;
                        }
                        case 2: {
                            c2 = 'F';
                            break;
                        }
                        case 3: {
                            c2 = 'n';
                            break;
                        }
                        default: {
                            c2 = ')';
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
