// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Polygon;
import java.awt.Graphics;
import java.text.NumberFormat;
import java.awt.Font;
import java.awt.Color;

public class c extends b
{
    private int bl;
    private int bm;
    private int bn;
    private int bo;
    private int bp;
    private double bq;
    private double br;
    private int bs;
    private int bt;
    private int bu;
    private int bv;
    private int bw;
    private int bx;
    private boolean by;
    private Color bz;
    private String bA;
    private String bB;
    private String bC;
    private String bD;
    private String bE;
    private int bF;
    private int bG;
    private int bH;
    private String[] bI;
    private int bJ;
    private int bK;
    private int bL;
    private double bM;
    private int bN;
    
    public c(final int bn, final int bm, final int a, final int b, final Color g, final Color h, final int c) {
        this.bo = 3;
        this.bp = 6;
        this.bu = 20;
        this.by = false;
        this.bz = new Color(0, 0, 192);
        this.bH = 1;
        this.bJ = 1;
        this.bK = -1;
        this.bN = 4;
        super.a = a;
        super.b = b;
        super.g = g;
        super.h = h;
        this.bl = bn + 20 + this.bu;
        this.bn = bn;
        this.bm = bm;
        super.c = c;
        this.bt = super.a - 40 - this.bu;
        this.bs = super.b - 25 - this.bu;
        super.bh = b("\u001f46*K");
    }
    
    public boolean a(final int l, final p j, final String s, final String s2) {
        super.l = l;
        super.j = j;
        final double[] array = new double[11];
        final Font font = new Font(super.f, 0, super.c);
        if (super.N != null) {
            this.bs -= 5 + (super.c + 2);
        }
        if (super.O != null) {
            this.bl += 15 + (super.c + 2) / 2;
            this.bt -= 15 + (super.c + 2) / 2;
        }
        if (!super.t || !super.v) {
            if (super.l > 0) {
                this.bq = super.j.a[0].a;
                this.br = this.bq;
            }
            for (int i = 0; i < super.l; ++i) {
                if (super.j.a[i].a > this.bq) {
                    this.bq = super.j.a[i].a;
                }
                if (super.j.a[i].a < this.br) {
                    this.br = super.j.a[i].a;
                }
            }
        }
        if (super.t) {
            this.br = super.n;
        }
        if (super.v) {
            this.bq = super.o;
        }
        if (this.bq == 0.0 && this.br == 0.0) {
            this.bq = 5.0;
            this.br = 0.0;
        }
        if (this.br >= 0.0) {
            this.bH = 1;
        }
        else if (this.bq < 0.0) {
            this.bH = 3;
        }
        else {
            this.bH = 2;
        }
        final double[] a = super.C.a(this.bq, this.br, super.t, super.v, super.R, super.S);
        this.bq = a[0];
        this.br = a[1];
        this.bM = a[2];
        this.bJ = (int)a[3];
        final int n = (int)a[4];
        this.bI = new String[this.bJ + 1];
        for (int k = 0; k <= this.bJ; ++k) {
            array[k] = super.C.e(this.br, super.C.d(this.bM, this.bJ - k));
            if (array[k] == 0.0) {
                this.bK = k;
            }
        }
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(n);
        instance.setMinimumFractionDigits(n);
        try {
            for (int n2 = 0; n2 <= this.bJ; ++n2) {
                this.bI[n2] = String.valueOf(s2) + instance.format(array[n2]) + s;
            }
        }
        catch (Exception ex) {}
        if (!super.bd) {
            this.bF = Math.max(super.C.c(String.valueOf(s2) + this.bI[0] + s, font), super.C.c(String.valueOf(s2) + this.bI[this.bJ] + s, font));
            this.bl += this.bF;
            this.bt -= this.bF;
        }
        if (super.H > 0 && super.I) {
            int n3 = 0;
            for (int n4 = 0; n4 < super.H; ++n4) {
                final q q = super.J[n4];
                if (q.e != null) {
                    final int c = super.C.c(q.e, font);
                    if (c > n3) {
                        n3 = c;
                    }
                }
            }
            this.bt = this.bt - n3 - 3;
        }
        if (super.bh.compareTo(b("\u001f46*K")) == 0) {
            if (super.l > 0) {
                this.bv = this.bt / super.l;
            }
            else {
                this.bv = this.bt;
            }
            if (super.Y != 0 && this.bv - 4 - this.bo * 2 > super.Y) {
                this.bv = super.Y - 4 - this.bo * 2 + this.bu;
            }
            if (super.l > 0) {
                this.bt = this.bv * super.l + 20;
            }
            else {
                this.bt = this.bv + 20;
            }
        }
        else if (super.l > 1) {
            this.bv = this.bt / (super.l - 1);
            this.bt = this.bv * (super.l - 1) + 3;
        }
        else {
            this.bv = this.bt;
            this.bt = this.bv + 3;
        }
        final String[] array2 = new String[super.l];
        for (int n5 = 0; n5 < super.l; ++n5) {
            array2[n5] = super.j.a[n5].f;
        }
        if (!super.L) {
            final int a2 = super.C.a(array2, super.l, this.bv, this.bl, super.a, font);
            if (a2 == 2) {
                super.K = true;
            }
            else if (a2 == 3) {
                super.L = true;
                super.M = super.C.a(array2, super.l, font);
                this.bs -= super.M;
            }
            if (super.K) {
                this.bs -= super.c + 5;
                if (super.N != null) {
                    this.bs -= super.c + 2;
                }
            }
        }
        this.bx = this.bs / this.bJ;
        this.bs = this.bx * this.bJ;
        if (super.bh.compareTo(b("\u001f46*K")) == 0) {
            for (int n6 = 0; n6 < super.l; ++n6) {
                double n7;
                if (super.j.a[n6].a < this.br) {
                    n7 = this.br;
                }
                else if (super.j.a[n6].a > this.bq) {
                    n7 = this.bq;
                }
                else {
                    n7 = super.j.a[n6].a;
                }
                if (this.bH == 1) {
                    super.j.a[n6].n = (int)((n7 - this.br) / (this.bq - this.br) * this.bs);
                }
                else if (this.bH == 2) {
                    super.j.a[n6].n = (int)(n7 / (this.bq - this.br) * this.bs);
                }
                else {
                    super.j.a[n6].n = (int)((Math.abs(n7) + this.bq) / (this.bq - this.br) * this.bs);
                }
            }
        }
        else {
            for (int n8 = 0; n8 < super.l; ++n8) {
                double n9;
                if (super.j.a[n8].a < this.br) {
                    n9 = this.br;
                }
                else if (super.j.a[n8].a > this.bq) {
                    n9 = this.bq;
                }
                else {
                    n9 = super.j.a[n8].a;
                }
                super.j.a[n8].n = (int)(this.bs - (n9 - this.br) / (this.bq - this.br) * this.bs);
            }
        }
        if (this.bq >= 0.0 && this.br < 0.0) {
            this.bL = (int)(this.bs - (0.0 - this.br) / (this.bq - this.br) * this.bs);
        }
        return true;
    }
    
    public void a(final Graphics graphics) {
        int n = 1;
        final Font font = new Font(super.f, 0, super.c);
        graphics.setFont(font);
        if (super.H > 0) {
            if (!super.bi) {
                super.bj = b("\u0005\u001a*uv");
            }
            for (int i = 0; i < super.H; ++i) {
                final q q = super.J[i];
                if (q != null) {
                    if (q.a(this.br, this.bq)) {
                        final int n2 = (int)(this.bs - (q.a() - this.br) / (this.bq - this.br) * this.bs);
                        graphics.setColor(q.d);
                        graphics.drawLine(this.bl, this.bm + n2, this.bl + this.bt, this.bm + n2);
                        graphics.drawLine(this.bl, this.bm + n2, this.bl - this.bu, this.bm + n2 + this.bu);
                        if (q.e != null) {
                            final int n3 = this.bl + this.bt + 3;
                            final int n4 = this.bm + n2 + super.C.b(q.e, font) / 2 - 1;
                            graphics.setColor(super.h);
                            graphics.drawString(q.e, n3, n4);
                        }
                    }
                    else if (q.b(this.br, this.bq)) {
                        final double[] c = q.c(this.br, this.bq);
                        final double n5 = c[0];
                        final double n6 = c[1];
                        final int n7 = (int)(this.bs - (n5 - this.br) / (this.bq - this.br) * this.bs);
                        final int n8 = (int)(this.bs - (n6 - this.br) / (this.bq - this.br) * this.bs);
                        final int n9 = n7 - n8;
                        if (n9 > 0) {
                            graphics.setColor(q.d);
                            graphics.fillRect(this.bl, this.bm + n8, this.bt, n9);
                            final Polygon polygon = new Polygon();
                            polygon.addPoint(this.bl, this.bm + n8);
                            polygon.addPoint(this.bl, this.bm + n8 + n9);
                            polygon.addPoint(this.bl - this.bu, this.bm + n8 + n9 + this.bu);
                            polygon.addPoint(this.bl - this.bu, this.bm + n8 + this.bu);
                            graphics.fillPolygon(polygon);
                        }
                        if (q.e != null) {
                            final int n10 = this.bl + this.bt + 3;
                            final int n11 = this.bm + n7 - n9 / 2 + super.C.b(q.e, font) / 2 - 1;
                            graphics.setColor(super.h);
                            graphics.drawString(q.e, n10, n11);
                        }
                    }
                }
            }
        }
        graphics.setColor(super.E);
        if (super.Z) {
            graphics.drawRect(this.bl, this.bm, this.bt, this.bs);
            graphics.drawRect(this.bl + 1, this.bm + 1, this.bt - 2, this.bs - 2);
            graphics.drawLine(this.bl - this.bu, this.bm + this.bu, this.bl - this.bu, this.bm + this.bu + this.bs);
            graphics.drawLine(this.bl - this.bu + 1, this.bm + this.bu, this.bl - this.bu + 1, this.bm + this.bu + this.bs);
            graphics.drawLine(this.bl - this.bu, this.bm + this.bu + this.bs, this.bl - this.bu + this.bt, this.bm + this.bu + this.bs);
            graphics.drawLine(this.bl - this.bu, this.bm + this.bu + this.bs - 1, this.bl - this.bu + this.bt, this.bm + this.bu + this.bs - 1);
            graphics.drawLine(this.bl, this.bm, this.bl - this.bu, this.bm + this.bu);
            graphics.drawLine(this.bl + 1, this.bm, this.bl + 1 - this.bu, this.bm + this.bu);
            graphics.drawLine(this.bl, this.bm + this.bs, this.bl - this.bu, this.bm + this.bu + this.bs);
            graphics.drawLine(this.bl + 1, this.bm + this.bs, this.bl + 1 - this.bu, this.bm + this.bu + this.bs);
            graphics.drawLine(this.bl + this.bt, this.bm + this.bs, this.bl - this.bu + this.bt, this.bm + this.bu + this.bs);
            graphics.drawLine(this.bl - 1 + this.bt, this.bm + this.bs, this.bl - 1 - this.bu + this.bt, this.bm + this.bu + this.bs);
            graphics.drawLine(this.bl - this.bu, this.bm + this.bs + this.bu + 6, this.bl - this.bu, this.bm + this.bu + this.bs);
            graphics.drawLine(this.bl + 1 - this.bu, this.bm + this.bs + this.bu + 6, this.bl + 1 - this.bu, this.bm + this.bu + this.bs);
            graphics.drawLine(this.bl + this.bt - this.bu, this.bm + this.bs + this.bu + 6, this.bl + this.bt - this.bu, this.bm + this.bu + this.bs);
            graphics.drawLine(this.bl + 1 + this.bt - this.bu, this.bm + this.bs + this.bu + 6, this.bl + 1 + this.bt - this.bu, this.bm + this.bu + this.bs);
            graphics.drawLine(this.bl - this.bu - 6, this.bm + this.bs + this.bu, this.bl - this.bu, this.bm + this.bu + this.bs);
            graphics.drawLine(this.bl - this.bu - 6, this.bm - 1 + this.bs + this.bu, this.bl - this.bu, this.bm - 1 + this.bu + this.bs);
            for (int j = 0; j < this.bJ; ++j) {
                graphics.drawLine(this.bl - this.bu - 6, this.bm + this.bu + this.bx * j, this.bl - this.bu, this.bm + this.bu + this.bx * j);
                graphics.drawLine(this.bl - this.bu - 6, this.bm + 1 + this.bu + this.bx * j, this.bl - this.bu, this.bm + 1 + this.bu + this.bx * j);
                if (j != 0) {
                    graphics.drawLine(this.bl, this.bm + this.bx * j, this.bl - this.bu, this.bm + this.bu + this.bx * j);
                    graphics.drawLine(this.bl + 1, this.bm + this.bx * j, this.bl + 1 - this.bu, this.bm + this.bu + this.bx * j);
                    if (super.C.b(super.bj, b("\u001c9(")) || super.C.b(super.bj, b("\u0004\u001a*uv"))) {
                        graphics.drawLine(this.bl, this.bm + this.bx * j, this.bl + this.bt, this.bm + this.bx * j);
                        graphics.drawLine(this.bl, this.bm + 1 + this.bx * j, this.bl + this.bt, this.bm + 1 + this.bx * j);
                    }
                }
            }
        }
        else {
            graphics.drawRect(this.bl, this.bm, this.bt, this.bs);
            graphics.drawLine(this.bl - this.bu, this.bm + this.bu, this.bl - this.bu, this.bm + this.bu + this.bs);
            graphics.drawLine(this.bl - this.bu, this.bm + this.bu + this.bs, this.bl - this.bu + this.bt, this.bm + this.bu + this.bs);
            graphics.drawLine(this.bl, this.bm, this.bl - this.bu, this.bm + this.bu);
            graphics.drawLine(this.bl, this.bm + this.bs, this.bl - this.bu, this.bm + this.bu + this.bs);
            graphics.drawLine(this.bl + this.bt, this.bm + this.bs, this.bl - this.bu + this.bt, this.bm + this.bu + this.bs);
            graphics.drawLine(this.bl - this.bu, this.bm + this.bs + this.bu + 6, this.bl - this.bu, this.bm + this.bu + this.bs);
            graphics.drawLine(this.bl + this.bt - this.bu, this.bm + this.bs + this.bu + 6, this.bl + this.bt - this.bu, this.bm + this.bu + this.bs);
            graphics.drawLine(this.bl - this.bu - 6, this.bm + this.bs + this.bu, this.bl - this.bu, this.bm + this.bu + this.bs);
            for (int k = 0; k < this.bJ; ++k) {
                graphics.drawLine(this.bl - this.bu - 6, this.bm + this.bu + this.bx * k, this.bl - this.bu, this.bm + this.bu + this.bx * k);
                if (k != 0) {
                    graphics.drawLine(this.bl, this.bm + this.bx * k, this.bl - this.bu, this.bm + this.bu + this.bx * k);
                    if (super.C.b(super.bj, b("\u001c9(")) || super.C.b(super.bj, b("\u0004\u001a*uv"))) {
                        graphics.drawLine(this.bl, this.bm + this.bx * k, this.bl + this.bt, this.bm + this.bx * k);
                    }
                }
            }
        }
        graphics.setColor(super.h);
        if (!super.bd) {
            for (int l = 0; l <= this.bJ; ++l) {
                graphics.drawString(this.bI[l], this.bl - 10 - this.bu - super.C.c(this.bI[l], font), this.bm + this.bu + l * this.bx + super.c / 2);
            }
        }
        if (this.bv < 25) {
            this.bo = 1;
        }
        final int n12 = this.bu - this.bp;
        final int n13 = this.bv - 4 - this.bo * 2;
        if (super.bh.compareTo(b("\u001f46*K")) == 0) {
            switch (this.bH) {
                case 1: {
                    for (int n14 = 0; n14 < super.l; ++n14) {
                        final int n15 = super.j.a[n14].n;
                        final int t = this.bl + this.bv * n14 - 5;
                        final int u = this.bm + this.bs - n15 + n12 + this.bo;
                        final int[] array = { t + n13, t + n13 + n12, t + n13 + n12, t + n13 };
                        final int[] array2 = { u, u - n12, this.bm + this.bs + this.bo, this.bm + this.bs + n12 + this.bo };
                        final int[] array3 = { t + n12, t, t + n13, t + n13 + n12 };
                        final int[] array4 = { u - n12, u, u, u - n12 };
                        graphics.setColor(super.E);
                        if (n14 != 0) {
                            if (super.Z) {
                                graphics.drawLine(this.bl - this.bu + this.bv * n14 + 5, this.bm + this.bs + this.bu + 6, this.bl - this.bu + this.bv * n14 + 5, this.bm + this.bu + this.bs);
                                graphics.drawLine(this.bl + 1 - this.bu + this.bv * n14 + 5, this.bm + this.bs + this.bu + 6, this.bl + 1 - this.bu + this.bv * n14 + 5, this.bm + this.bu + this.bs);
                                graphics.drawLine(this.bl + this.bv * n14 + 5, this.bm + this.bs, this.bl - this.bu + this.bv * n14 + 5, this.bm + this.bu + this.bs);
                                graphics.drawLine(this.bl + 1 + this.bv * n14 + 5, this.bm + this.bs, this.bl + 1 - this.bu + this.bv * n14 + 5, this.bm + this.bu + this.bs);
                                if (super.C.b(super.bj, b("\u001c9(")) || super.C.b(super.bj, b("\u0005\u001a*uv"))) {
                                    graphics.drawLine(this.bl + this.bv * n14 + 5, this.bm + this.bs, this.bl + this.bv * n14 + 5, this.bm);
                                    graphics.drawLine(this.bl + 1 + this.bv * n14 + 5, this.bm + this.bs, this.bl + 1 + this.bv * n14 + 5, this.bm);
                                }
                            }
                            else {
                                graphics.drawLine(this.bl - this.bu + this.bv * n14 + 5, this.bm + this.bs + this.bu + 6, this.bl - this.bu + this.bv * n14 + 5, this.bm + this.bu + this.bs);
                                graphics.drawLine(this.bl + this.bv * n14 + 5, this.bm + this.bs, this.bl - this.bu + this.bv * n14 + 5, this.bm + this.bu + this.bs);
                                if (super.C.b(super.bj, b("\u001c9(")) || super.C.b(super.bj, b("\u0005\u001a*uv"))) {
                                    graphics.drawLine(this.bl + this.bv * n14 + 5, this.bm + this.bs, this.bl + this.bv * n14 + 5, this.bm);
                                }
                            }
                        }
                        if (!super.j.a[n14].s) {
                            if (super.j.a[n14].a >= super.n) {
                                Color color;
                                if (super.j.a[n14].g == null) {
                                    color = this.bz;
                                }
                                else {
                                    color = super.j.a[n14].g;
                                }
                                graphics.setColor(color);
                                graphics.fillRect(t, u, n13, n15);
                                graphics.setColor(color.darker());
                                graphics.fillPolygon(array, array2, 4);
                                graphics.setColor(color.brighter());
                                graphics.fillPolygon(array3, array4, 4);
                            }
                            if (!super.F) {
                                super.j.a[n14].t = t;
                                super.j.a[n14].u = u;
                                super.j.a[n14].v = t + n13;
                                super.j.a[n14].w = u + n15;
                            }
                        }
                        if (super.be) {
                            int n16 = t + n12;
                            int n17 = u - 3 - n12;
                            if (n17 - super.c < this.bm) {
                                n17 = n17 + n12 + 6 + super.c;
                                n16 -= n12;
                            }
                            graphics.setColor(super.h);
                            final String m = super.j.a[n14].k;
                            graphics.drawString(m, n16 + super.C.a(m, font, n13), n17);
                        }
                    }
                    break;
                }
                case 2: {
                    for (int n18 = 0; n18 < super.l; ++n18) {
                        int n19 = 0;
                        final int t2 = this.bl + this.bv * n18 - 5;
                        final boolean b = super.j.a[n18].a < 0.0;
                        int n20;
                        int u2;
                        if (super.bc) {
                            n20 = super.j.a[n18].n;
                            u2 = this.bm + this.bL - n20 + n12 + this.bo;
                        }
                        else {
                            n20 = super.j.a[n18].n + (this.bs - this.bL);
                            u2 = this.bm + this.bs - n20 + n12 + this.bo;
                            n19 = this.bs - this.bL;
                        }
                        final int[] array5 = { t2 + n13, t2 + n13 + n12, t2 + n13 + n12, t2 + n13 };
                        final int[] array6 = { u2, u2 - n12, this.bm + this.bL + this.bo + n19, this.bm + this.bL + n12 + this.bo + n19 };
                        if (super.bc && n20 < 0) {
                            u2 = this.bm + this.bL + n12 + this.bo;
                            n20 = Math.abs(n20);
                        }
                        final int[] array7 = { t2 + n12, t2, t2 + n13, t2 + n13 + n12 };
                        final int[] array8 = { u2 - n12, u2, u2, u2 - n12 };
                        graphics.setColor(super.E);
                        if (n18 != 0) {
                            if (super.Z) {
                                graphics.drawLine(this.bl - this.bu + this.bv * n18 + 5, this.bm + this.bs + this.bu + 6, this.bl - this.bu + this.bv * n18 + 5, this.bm + this.bu + this.bs);
                                graphics.drawLine(this.bl + 1 - this.bu + this.bv * n18 + 5, this.bm + this.bs + this.bu + 6, this.bl + 1 - this.bu + this.bv * n18 + 5, this.bm + this.bu + this.bs);
                                graphics.drawLine(this.bl + this.bv * n18 + 5, this.bm + this.bs, this.bl - this.bu + this.bv * n18 + 5, this.bm + this.bu + this.bs);
                                graphics.drawLine(this.bl + 1 + this.bv * n18 + 5, this.bm + this.bs, this.bl + 1 - this.bu + this.bv * n18 + 5, this.bm + this.bu + this.bs);
                                if (super.C.b(super.bj, b("\u001c9(")) || super.C.b(super.bj, b("\u0005\u001a*uv"))) {
                                    graphics.drawLine(this.bl + this.bv * n18 + 5, this.bm + this.bs, this.bl + this.bv * n18 + 5, this.bm);
                                    graphics.drawLine(this.bl + 1 + this.bv * n18 + 5, this.bm + this.bs, this.bl + 1 + this.bv * n18 + 5, this.bm);
                                }
                            }
                            else {
                                graphics.drawLine(this.bl - this.bu + this.bv * n18 + 5, this.bm + this.bs + this.bu + 6, this.bl - this.bu + this.bv * n18 + 5, this.bm + this.bu + this.bs);
                                graphics.drawLine(this.bl + this.bv * n18 + 5, this.bm + this.bs, this.bl - this.bu + this.bv * n18 + 5, this.bm + this.bu + this.bs);
                                if (super.C.b(super.bj, b("\u001c9(")) || super.C.b(super.bj, b("\u0005\u001a*uv"))) {
                                    graphics.drawLine(this.bl + this.bv * n18 + 5, this.bm + this.bs, this.bl + this.bv * n18 + 5, this.bm);
                                }
                            }
                        }
                        Color color2;
                        if (super.j.a[n18].g == null) {
                            color2 = this.bz;
                        }
                        else {
                            color2 = super.j.a[n18].g;
                        }
                        if (!super.j.a[n18].s) {
                            if (!super.r || super.j.a[n18].a >= super.n) {
                                graphics.setColor(color2);
                                graphics.fillRect(t2, u2, n13, n20);
                                graphics.setColor(color2.darker());
                                graphics.fillPolygon(array5, array6, 4);
                                graphics.setColor(color2.brighter());
                                graphics.fillPolygon(array7, array8, 4);
                            }
                            if (!super.F) {
                                super.j.a[n18].t = t2;
                                super.j.a[n18].u = u2;
                                super.j.a[n18].v = t2 + n13;
                                super.j.a[n18].w = u2 + n20;
                            }
                        }
                        graphics.setColor(super.E);
                        if (n18 != super.l - 1) {
                            if (super.Z) {
                                graphics.drawLine(this.bl + this.bv * (n18 + 1) + 5, this.bm + this.bL, this.bl - this.bu + this.bv * (n18 + 1) + 5, this.bm + this.bu + this.bL);
                                graphics.drawLine(this.bl + 1 + this.bv * (n18 + 1) + 5, this.bm + this.bL, this.bl + 1 - this.bu + this.bv * (n18 + 1) + 5, this.bm + this.bu + this.bL);
                            }
                            else {
                                graphics.drawLine(this.bl + this.bv * (n18 + 1) + 5, this.bm + this.bL, this.bl - this.bu + this.bv * (n18 + 1) + 5, this.bm + this.bu + this.bL);
                            }
                        }
                        if (super.be) {
                            int n21;
                            int n22;
                            if (b) {
                                n21 = t2;
                                n22 = u2 + n20 + 3 + super.c;
                                if (n22 > this.bm + this.bs) {
                                    n22 = n22 - super.c - 6;
                                }
                            }
                            else {
                                n21 = t2 + n12;
                                n22 = u2 - 3 - n12;
                                if (n22 - super.c < this.bm) {
                                    n22 = n22 + n12 + 6 + super.c;
                                    n21 -= n12;
                                }
                            }
                            graphics.setColor(super.h);
                            final String k2 = super.j.a[n18].k;
                            graphics.drawString(k2, n21 + super.C.a(k2, font, n13), n22);
                        }
                    }
                    graphics.setColor(super.E);
                    if (super.Z) {
                        graphics.drawLine(this.bl - this.bu, this.bm + this.bL + this.bu, this.bl + this.bt - this.bu, this.bm + this.bL + this.bu);
                        graphics.drawLine(this.bl - this.bu, this.bm - 1 + this.bL + this.bu, this.bl + this.bt - this.bu, this.bm - 1 + this.bL + this.bu);
                        graphics.drawLine(this.bl + this.bt, this.bm + this.bL, this.bl + this.bt - this.bu, this.bm + this.bu + this.bL);
                        graphics.drawLine(this.bl - 1 + this.bt, this.bm + this.bL, this.bl - 1 + this.bt - this.bu, this.bm + this.bu + this.bL);
                        break;
                    }
                    graphics.drawLine(this.bl - this.bu, this.bm + this.bL + this.bu, this.bl + this.bt - this.bu, this.bm + this.bL + this.bu);
                    graphics.drawLine(this.bl + this.bt, this.bm + this.bL, this.bl + this.bt - this.bu, this.bm + this.bu + this.bL);
                    break;
                }
                case 3: {
                    for (int n23 = 0; n23 < super.l; ++n23) {
                        final int t3 = this.bl + this.bv * n23 - 5;
                        int abs;
                        int u3;
                        if (super.bc) {
                            abs = Math.abs(super.j.a[n23].n);
                            u3 = this.bm + n12 + this.bo;
                        }
                        else {
                            abs = this.bs - Math.abs(super.j.a[n23].n);
                            u3 = this.bm + this.bs - abs + n12 + this.bo;
                        }
                        final int[] array9 = { t3 + n13, t3 + n13 + n12, t3 + n13 + n12, t3 + n13 };
                        final int[] array10 = { u3, u3 - n12, u3 + abs - n12, u3 + abs };
                        final int[] array11 = { t3 + n12, t3, t3 + n13, t3 + n13 + n12 };
                        final int[] array12 = { u3 - n12, u3, u3, u3 - n12 };
                        graphics.setColor(super.E);
                        if (n23 != 0) {
                            if (super.Z) {
                                graphics.drawLine(this.bl - this.bu + this.bv * n23 + 5, this.bm + this.bs + this.bu + 6, this.bl - this.bu + this.bv * n23 + 5, this.bm + this.bu + this.bs);
                                graphics.drawLine(this.bl + 1 - this.bu + this.bv * n23 + 5, this.bm + this.bs + this.bu + 6, this.bl + 1 - this.bu + this.bv * n23 + 5, this.bm + this.bu + this.bs);
                                graphics.drawLine(this.bl + this.bv * n23 + 5, this.bm + this.bs, this.bl - this.bu + this.bv * n23 + 5, this.bm + this.bu + this.bs);
                                graphics.drawLine(this.bl + 1 + this.bv * n23 + 5, this.bm + this.bs, this.bl + 1 - this.bu + this.bv * n23 + 5, this.bm + this.bu + this.bs);
                                graphics.drawLine(this.bl + this.bv * n23 + 5, this.bm + this.bs, this.bl + this.bv * n23 + 5, this.bm);
                                graphics.drawLine(this.bl + 1 + this.bv * n23 + 5, this.bm + this.bs, this.bl + 1 + this.bv * n23 + 5, this.bm);
                            }
                            else {
                                graphics.drawLine(this.bl - this.bu + this.bv * n23 + 5, this.bm + this.bs + this.bu + 6, this.bl - this.bu + this.bv * n23 + 5, this.bm + this.bu + this.bs);
                                graphics.drawLine(this.bl + this.bv * n23 + 5, this.bm + this.bs, this.bl - this.bu + this.bv * n23 + 5, this.bm + this.bu + this.bs);
                                graphics.drawLine(this.bl + this.bv * n23 + 5, this.bm + this.bs, this.bl + this.bv * n23 + 5, this.bm);
                            }
                        }
                        Color color3;
                        if (super.j.a[n23].g == null) {
                            color3 = this.bz;
                        }
                        else {
                            color3 = super.j.a[n23].g;
                        }
                        if (!super.j.a[n23].s) {
                            if (!super.r || super.j.a[n23].a >= super.n) {
                                graphics.setColor(color3);
                                graphics.fillRect(t3, u3, n13, abs);
                                graphics.setColor(color3.darker());
                                graphics.fillPolygon(array9, array10, 4);
                                graphics.setColor(color3.brighter());
                                graphics.fillPolygon(array11, array12, 4);
                            }
                            if (!super.F) {
                                super.j.a[n23].t = t3;
                                super.j.a[n23].u = u3;
                                super.j.a[n23].v = t3 + n13;
                                super.j.a[n23].w = u3 + abs;
                            }
                        }
                        graphics.setColor(super.E);
                        if (n23 != super.l - 1) {
                            if (super.Z) {
                                graphics.drawLine(this.bl + this.bv * (n23 + 1) + 5, this.bm, this.bl - this.bu + this.bv * (n23 + 1) + 5, this.bm + this.bu);
                                graphics.drawLine(this.bl + 1 + this.bv * (n23 + 1) + 5, this.bm, this.bl + 1 - this.bu + this.bv * (n23 + 1) + 5, this.bm + this.bu);
                            }
                            else {
                                graphics.drawLine(this.bl + this.bv * (n23 + 1) + 5, this.bm, this.bl - this.bu + this.bv * (n23 + 1) + 5, this.bm + this.bu);
                            }
                        }
                        if (super.be) {
                            final int n24 = t3;
                            int n25 = u3 + abs + 3 + super.c;
                            if (n25 > this.bm + this.bs) {
                                n25 = n25 - super.c - 6;
                            }
                            graphics.setColor(super.h);
                            final String k3 = super.j.a[n23].k;
                            graphics.drawString(k3, n24 + super.C.a(k3, font, n13), n25);
                        }
                    }
                    graphics.setColor(super.E);
                    if (super.Z) {
                        graphics.drawLine(this.bl - this.bu, this.bm + this.bu, this.bl + this.bt - this.bu, this.bm + this.bu);
                        graphics.drawLine(this.bl - this.bu, this.bm + 1 + this.bu, this.bl + this.bt - this.bu, this.bm + 1 + this.bu);
                        graphics.drawLine(this.bl + this.bt, this.bm, this.bl + this.bt - this.bu, this.bm + this.bu);
                        graphics.drawLine(this.bl + 1 + this.bt, this.bm, this.bl + 1 + this.bt - this.bu, this.bm + this.bu);
                        break;
                    }
                    graphics.drawLine(this.bl - this.bu, this.bm + this.bu, this.bl + this.bt - this.bu, this.bm + this.bu);
                    graphics.drawLine(this.bl + this.bt, this.bm, this.bl + this.bt - this.bu, this.bm + this.bu);
                    break;
                }
            }
            graphics.setColor(super.h);
            for (int n26 = 0; n26 < super.l; ++n26) {
                if (!super.L) {
                    if (super.j.a[n26].f != null) {
                        if (!super.K) {
                            graphics.drawString(super.j.a[n26].f, this.bl + this.bv * n26 + n13 / 2 - 10 - super.C.a(super.j.a[n26].f, font), this.bm + this.bs + this.bu + 20);
                        }
                        else if (n != 0) {
                            graphics.drawString(super.j.a[n26].f, this.bl + this.bv * n26 + n13 / 2 - 10 - super.C.a(super.j.a[n26].f, font), this.bm + this.bs + this.bu + 20);
                        }
                        else {
                            graphics.drawString(super.j.a[n26].f, this.bl + this.bv * n26 + n13 / 2 - 10 - super.C.a(super.j.a[n26].f, font), this.bm + this.bs + this.bu + 25 + super.c);
                        }
                    }
                    if (n != 0) {
                        n = 0;
                    }
                    else {
                        n = 1;
                    }
                }
                else if (super.j.a[n26].f != null) {
                    final Image a = super.C.a(super.j.a[n26].f, font, true, super.h, super.g);
                    if (a != null) {
                        graphics.drawImage(a, this.bl + this.bv * n26 + n13 / 2 - 10 - super.c / 2, this.bm + this.bs + this.bu + 10, null);
                    }
                }
            }
        }
        else {
            final int n27 = this.bv * super.l;
            graphics.setColor(super.E);
            for (int n28 = 1; n28 < super.l - 1; ++n28) {
                if (n28 != 0) {
                    if (super.Z) {
                        graphics.drawLine(this.bl - this.bu + this.bv * n28 + 5, this.bm + this.bs + this.bu + 6, this.bl - this.bu + this.bv * n28 + 5, this.bm + this.bu + this.bs);
                        graphics.drawLine(this.bl + 1 - this.bu + this.bv * n28 + 5, this.bm + this.bs + this.bu + 6, this.bl + 1 - this.bu + this.bv * n28 + 5, this.bm + this.bu + this.bs);
                        graphics.drawLine(this.bl + this.bv * n28 + 5, this.bm + this.bs, this.bl - this.bu + this.bv * n28 + 5, this.bm + this.bu + this.bs);
                        graphics.drawLine(this.bl + 1 + this.bv * n28 + 5, this.bm + this.bs, this.bl + 1 - this.bu + this.bv * n28 + 5, this.bm + this.bu + this.bs);
                        if (super.C.b(super.bj, b("\u001c9(")) || super.C.b(super.bj, b("\u0005\u001a*uv"))) {
                            graphics.drawLine(this.bl + this.bv * n28 + 5, this.bm + this.bs, this.bl + this.bv * n28 + 5, this.bm);
                            graphics.drawLine(this.bl + 1 + this.bv * n28 + 5, this.bm + this.bs, this.bl + 1 + this.bv * n28 + 5, this.bm);
                        }
                    }
                    else {
                        graphics.drawLine(this.bl - this.bu + this.bv * n28 + 5, this.bm + this.bs + this.bu + 6, this.bl - this.bu + this.bv * n28 + 5, this.bm + this.bu + this.bs);
                        graphics.drawLine(this.bl + this.bv * n28 + 5, this.bm + this.bs, this.bl - this.bu + this.bv * n28 + 5, this.bm + this.bu + this.bs);
                        if (super.C.b(super.bj, b("\u001c9(")) || super.C.b(super.bj, b("\u0005\u001a*uv"))) {
                            graphics.drawLine(this.bl + this.bv * n28 + 5, this.bm + this.bs, this.bl + this.bv * n28 + 5, this.bm);
                        }
                    }
                }
            }
            int n29 = this.bl - n12;
            int n30 = this.bm + super.j.a[0].n + n12;
            if (super.l > 0) {
                final Polygon polygon2 = new Polygon();
                polygon2.addPoint(n29, n30);
                final Color darker = this.bz.darker();
                graphics.setColor(darker);
                if (!super.F) {
                    super.j.a[0].t = n29 - this.bN;
                    super.j.a[0].u = n30 - this.bN;
                    super.j.a[0].v = n29 + this.bN;
                    super.j.a[0].w = n30 + this.bN;
                }
                for (int n31 = 1; n31 < super.l; ++n31) {
                    final int n32 = n29;
                    final int n33 = n30;
                    n29 = this.bl + n31 * this.bv - n12;
                    n30 = this.bm + super.j.a[n31].n + n12;
                    polygon2.addPoint(n29, n30);
                    final int n34 = n29;
                    final int n35 = n30;
                    final int n36 = n32;
                    final int n37 = n33;
                    final int n38 = n32 + n12;
                    final int n39 = n33 - n12;
                    final int n40 = n29 + n12;
                    final int n41 = n30 - n12;
                    final Polygon polygon3 = new Polygon();
                    polygon3.addPoint(n34, n35);
                    polygon3.addPoint(n36, n37);
                    polygon3.addPoint(n38, n39);
                    polygon3.addPoint(n40, n41);
                    graphics.setColor(darker);
                    graphics.fillPolygon(polygon3);
                    if (n31 != super.l - 1 && this.bH != 1) {
                        graphics.setColor(super.E);
                        if (super.Z) {
                            graphics.drawLine(n29 + this.bu, this.bm + this.bL, n29, this.bm + this.bL + this.bu);
                            graphics.drawLine(n29 + this.bu + 1, this.bm + this.bL, n29 + 1, this.bm + this.bL + this.bu);
                        }
                        else {
                            graphics.drawLine(n29 + this.bu, this.bm + this.bL, n29, this.bm + this.bL + this.bu);
                        }
                    }
                    if (!super.F) {
                        super.j.a[n31].t = n29 - this.bN;
                        super.j.a[n31].u = n30 - this.bN;
                        super.j.a[n31].v = n29 + this.bN;
                        super.j.a[n31].w = n30 + this.bN;
                    }
                }
                final boolean b2 = false;
                final int n42 = n29;
                final int n43 = n30;
                if (!b2) {
                    n29 = n42;
                    n30 = this.bm + this.bs + n12;
                    polygon2.addPoint(n29, n30);
                    polygon2.addPoint(this.bl - n12, this.bm + this.bs + n12);
                }
                else if (this.bq == Math.abs(this.br)) {
                    polygon2.addPoint(this.bl + n27, this.bm + this.bs / 2);
                    polygon2.addPoint(this.bl, this.bm + this.bs / 2);
                }
                else {
                    polygon2.addPoint(this.bl + n27, this.bm);
                    polygon2.addPoint(this.bl, this.bm);
                }
                final int n44 = n29;
                final int n45 = n30;
                final int n46 = n42;
                final int n47 = n43;
                final int n48 = n42 + n12;
                final int n49 = n43 - n12;
                final int n50 = n29 + n12;
                final int n51 = n30 - n12;
                final Polygon polygon4 = new Polygon();
                polygon4.addPoint(n44, n45);
                polygon4.addPoint(n46, n47);
                polygon4.addPoint(n48, n49);
                polygon4.addPoint(n50, n51);
                graphics.setColor(darker);
                graphics.fillPolygon(polygon4);
                graphics.setColor(this.bz);
                graphics.fillPolygon(polygon2);
            }
            if (this.bH == 2 || this.bH == 3) {
                graphics.setColor(super.E);
                if (super.Z) {
                    graphics.drawLine(this.bl - this.bu, this.bm + this.bL + this.bu, this.bl + this.bt - this.bu, this.bm + this.bL + this.bu);
                    graphics.drawLine(this.bl - this.bu, this.bm - 1 + this.bL + this.bu, this.bl + this.bt - this.bu, this.bm - 1 + this.bL + this.bu);
                    graphics.drawLine(this.bl + this.bt, this.bm + this.bL, this.bl + this.bt - this.bu, this.bm + this.bu + this.bL);
                    graphics.drawLine(this.bl - 1 + this.bt, this.bm + this.bL, this.bl - 1 + this.bt - this.bu, this.bm + this.bu + this.bL);
                }
                else {
                    graphics.drawLine(this.bl - this.bu, this.bm + this.bL + this.bu, this.bl + this.bt - this.bu, this.bm + this.bL + this.bu);
                    graphics.drawLine(this.bl + this.bt, this.bm + this.bL, this.bl + this.bt - this.bu, this.bm + this.bu + this.bL);
                }
            }
            if (super.be) {
                graphics.setColor(super.h);
                for (int n52 = 0; n52 < super.l; ++n52) {
                    final String k4 = super.j.a[n52].k;
                    graphics.drawString(k4, this.bl + n52 * this.bv - n12 - super.C.c(k4, font) / 2, this.bm + super.j.a[n52].n + n12);
                }
            }
            graphics.setColor(super.h);
            for (int n53 = 0; n53 < super.l; ++n53) {
                if (!super.L) {
                    if (super.j.a[n53].f != null) {
                        final int n54 = this.bl - this.bu + this.bv * n53 - super.C.a(super.j.a[n53].f, font);
                        if (super.j.a[n53].f != null) {
                            if (!super.K) {
                                graphics.drawString(super.j.a[n53].f, n54, this.bm + this.bs + this.bu + 20);
                            }
                            else if (n != 0) {
                                graphics.drawString(super.j.a[n53].f, n54, this.bm + this.bs + this.bu + 20);
                            }
                            else {
                                graphics.drawString(super.j.a[n53].f, n54, this.bm + this.bs + this.bu + 25 + super.c);
                            }
                        }
                        if (n != 0) {
                            n = 0;
                        }
                        else {
                            n = 1;
                        }
                    }
                }
                else if (super.j.a[n53].f != null) {
                    final Image a2 = super.C.a(super.j.a[n53].f, font, true, super.h, super.g);
                    int n55;
                    if (super.bh.compareTo(b("\u001f46*K")) == 0) {
                        n55 = this.bl + this.bv * n53 + n13 / 2 - 10 - super.c / 2;
                    }
                    else {
                        n55 = this.bl - this.bu + this.bv * n53;
                    }
                    if (a2 != null) {
                        graphics.drawImage(a2, n55, this.bm + this.bs + this.bu + 10, null);
                    }
                }
            }
        }
        this.b(graphics);
        super.C.a(graphics, this);
        super.F = true;
    }
    
    public void a(final Graphics graphics, final o o) {
        final Color bz = this.bz;
        final int n = this.bu - this.bp;
        new Font(super.f, 0, super.c);
        if (super.bh.compareTo(b("\u001f46*K")) == 0) {
            final int n2 = this.bv - 4 - this.bo * 2;
            switch (this.bH) {
                case 1: {
                    for (int i = 0; i < super.l; ++i) {
                        Color color;
                        if (super.j.a[i] == o) {
                            color = super.D;
                        }
                        else if (super.j.a[i].g == null) {
                            color = this.bz;
                        }
                        else {
                            color = super.j.a[i].g;
                        }
                        final int n3 = super.j.a[i].n;
                        final int n4 = this.bl + this.bv * i - 5;
                        final int n5 = this.bm + this.bs - n3 + n + this.bo;
                        final int[] array = { n4 + n2, n4 + n2 + n, n4 + n2 + n, n4 + n2 };
                        final int[] array2 = { n5, n5 - n, this.bm + this.bs + this.bo, this.bm + this.bs + n + this.bo };
                        final int[] array3 = { n4 + n, n4, n4 + n2, n4 + n2 + n };
                        final int[] array4 = { n5 - n, n5, n5, n5 - n };
                        if (!super.j.a[i].s && super.j.a[i].a >= super.n) {
                            graphics.setColor(color);
                            graphics.fillRect(n4, n5, n2, n3);
                            graphics.setColor(color.darker());
                            graphics.fillPolygon(array, array2, 4);
                            graphics.setColor(color.brighter());
                            graphics.fillPolygon(array3, array4, 4);
                        }
                    }
                }
                case 2: {
                    for (int j = 0; j < super.l; ++j) {
                        Color color2;
                        if (super.j.a[j] == o) {
                            color2 = super.D;
                        }
                        else if (super.j.a[j].g == null) {
                            color2 = this.bz;
                        }
                        else {
                            color2 = super.j.a[j].g;
                        }
                        int n6 = 0;
                        final int n7 = this.bl + this.bv * j - 5;
                        if (super.j.a[j].a < 0.0) {}
                        int n8;
                        int n9;
                        if (super.bc) {
                            n8 = super.j.a[j].n;
                            n9 = this.bm + this.bL - n8 + n + this.bo;
                        }
                        else {
                            n8 = super.j.a[j].n + (this.bs - this.bL);
                            n9 = this.bm + this.bs - n8 + n + this.bo;
                            n6 = this.bs - this.bL;
                        }
                        final int[] array5 = { n7 + n2, n7 + n2 + n, n7 + n2 + n, n7 + n2 };
                        final int[] array6 = { n9, n9 - n, this.bm + this.bL + this.bo + n6, this.bm + this.bL + n + this.bo + n6 };
                        if (super.bc && n8 < 0) {
                            n9 = this.bm + this.bL + n + this.bo;
                            n8 = Math.abs(n8);
                        }
                        final int[] array7 = { n7 + n, n7, n7 + n2, n7 + n2 + n };
                        final int[] array8 = { n9 - n, n9, n9, n9 - n };
                        if (!super.j.a[j].s && (!super.r || super.j.a[j].a >= super.n)) {
                            graphics.setColor(color2);
                            graphics.fillRect(n7, n9, n2, n8);
                            graphics.setColor(color2.darker());
                            graphics.fillPolygon(array5, array6, 4);
                            graphics.setColor(color2.brighter());
                            graphics.fillPolygon(array7, array8, 4);
                        }
                        graphics.setColor(super.E);
                        if (j != super.l - 1) {
                            if (super.Z) {
                                graphics.drawLine(this.bl + this.bv * (j + 1) + 5, this.bm + this.bL, this.bl - this.bu + this.bv * (j + 1) + 5, this.bm + this.bu + this.bL);
                                graphics.drawLine(this.bl + 1 + this.bv * (j + 1) + 5, this.bm + this.bL, this.bl + 1 - this.bu + this.bv * (j + 1) + 5, this.bm + this.bu + this.bL);
                            }
                            else {
                                graphics.drawLine(this.bl + this.bv * (j + 1) + 5, this.bm + this.bL, this.bl - this.bu + this.bv * (j + 1) + 5, this.bm + this.bu + this.bL);
                            }
                        }
                    }
                    graphics.setColor(super.E);
                    if (super.Z) {
                        graphics.drawLine(this.bl - this.bu, this.bm + this.bL + this.bu, this.bl + this.bt - this.bu, this.bm + this.bL + this.bu);
                        graphics.drawLine(this.bl - this.bu, this.bm - 1 + this.bL + this.bu, this.bl + this.bt - this.bu, this.bm - 1 + this.bL + this.bu);
                        return;
                    }
                    graphics.drawLine(this.bl - this.bu, this.bm + this.bL + this.bu, this.bl + this.bt - this.bu, this.bm + this.bL + this.bu);
                }
                case 3: {
                    for (int k = 0; k < super.l; ++k) {
                        Color color3;
                        if (super.j.a[k] == o) {
                            color3 = super.D;
                        }
                        else if (super.j.a[k].g == null) {
                            color3 = this.bz;
                        }
                        else {
                            color3 = super.j.a[k].g;
                        }
                        final int n10 = this.bl + this.bv * k - 5;
                        int abs;
                        int n11;
                        if (super.bc) {
                            abs = Math.abs(super.j.a[k].n);
                            n11 = this.bm + n + this.bo;
                        }
                        else {
                            abs = this.bs - Math.abs(super.j.a[k].n);
                            n11 = this.bm + this.bs - abs + n + this.bo;
                        }
                        final int[] array9 = { n10 + n2, n10 + n2 + n, n10 + n2 + n, n10 + n2 };
                        final int[] array10 = { n11, n11 - n, n11 + abs - n, n11 + abs };
                        final int[] array11 = { n10 + n, n10, n10 + n2, n10 + n2 + n };
                        final int[] array12 = { n11 - n, n11, n11, n11 - n };
                        graphics.setColor(super.E);
                        if (k != 0) {
                            if (super.Z) {
                                graphics.drawLine(this.bl - this.bu + this.bv * k + 5, this.bm + this.bs + this.bu + 6, this.bl - this.bu + this.bv * k + 5, this.bm + this.bu + this.bs);
                                graphics.drawLine(this.bl + 1 - this.bu + this.bv * k + 5, this.bm + this.bs + this.bu + 6, this.bl + 1 - this.bu + this.bv * k + 5, this.bm + this.bu + this.bs);
                                graphics.drawLine(this.bl + this.bv * k + 5, this.bm + this.bs, this.bl - this.bu + this.bv * k + 5, this.bm + this.bu + this.bs);
                                graphics.drawLine(this.bl + 1 + this.bv * k + 5, this.bm + this.bs, this.bl + 1 - this.bu + this.bv * k + 5, this.bm + this.bu + this.bs);
                                graphics.drawLine(this.bl + this.bv * k + 5, this.bm + this.bs, this.bl + this.bv * k + 5, this.bm);
                                graphics.drawLine(this.bl + 1 + this.bv * k + 5, this.bm + this.bs, this.bl + 1 + this.bv * k + 5, this.bm);
                            }
                            else {
                                graphics.drawLine(this.bl - this.bu + this.bv * k + 5, this.bm + this.bs + this.bu + 6, this.bl - this.bu + this.bv * k + 5, this.bm + this.bu + this.bs);
                                graphics.drawLine(this.bl + this.bv * k + 5, this.bm + this.bs, this.bl - this.bu + this.bv * k + 5, this.bm + this.bu + this.bs);
                                graphics.drawLine(this.bl + this.bv * k + 5, this.bm + this.bs, this.bl + this.bv * k + 5, this.bm);
                            }
                        }
                        if (!super.j.a[k].s && (!super.r || super.j.a[k].a >= super.n)) {
                            graphics.setColor(color3);
                            graphics.fillRect(n10, n11, n2, abs);
                            graphics.setColor(color3.darker());
                            graphics.fillPolygon(array9, array10, 4);
                            graphics.setColor(color3.brighter());
                            graphics.fillPolygon(array11, array12, 4);
                        }
                        graphics.setColor(super.E);
                        if (k != super.l - 1) {
                            if (super.Z) {
                                graphics.drawLine(this.bl + this.bv * (k + 1) + 5, this.bm, this.bl - this.bu + this.bv * (k + 1) + 5, this.bm + this.bu);
                                graphics.drawLine(this.bl + 1 + this.bv * (k + 1) + 5, this.bm, this.bl + 1 - this.bu + this.bv * (k + 1) + 5, this.bm + this.bu);
                            }
                            else {
                                graphics.drawLine(this.bl + this.bv * (k + 1) + 5, this.bm, this.bl - this.bu + this.bv * (k + 1) + 5, this.bm + this.bu);
                            }
                        }
                    }
                    graphics.setColor(super.E);
                    if (super.Z) {
                        graphics.drawLine(this.bl - this.bu, this.bm + this.bu, this.bl + this.bt - this.bu, this.bm + this.bu);
                        graphics.drawLine(this.bl - this.bu, this.bm - 1 + this.bu, this.bl + this.bt - this.bu, this.bm - 1 + this.bu);
                        graphics.drawLine(this.bl + this.bt, this.bm, this.bl + this.bt - this.bu, this.bm + this.bu);
                        graphics.drawLine(this.bl + 1 + this.bt, this.bm, this.bl + 1 + this.bt - this.bu, this.bm + this.bu);
                        return;
                    }
                    graphics.drawLine(this.bl - this.bu, this.bm + this.bu, this.bl + this.bt - this.bu, this.bm + this.bu);
                    graphics.drawLine(this.bl + this.bt, this.bm, this.bl + this.bt - this.bu, this.bm + this.bu);
                }
            }
        }
        else {
            graphics.setColor(super.D);
            for (int l = 0; l < super.l; ++l) {
                if (o == super.j.a[l]) {
                    graphics.fillRect(super.j.a[l].t, super.j.a[l].u, this.bN * 2, this.bN * 2);
                    return;
                }
            }
        }
    }
    
    public o a(final int n, final int n2) {
        for (int i = 0; i < super.l; ++i) {
            if (!super.j.a[i].s && n >= super.j.a[i].t && n <= super.j.a[i].v && n2 >= super.j.a[i].u && n2 <= super.j.a[i].w) {
                return super.j.a[i];
            }
        }
        return null;
    }
    
    public void b(final Graphics graphics) {
        final Font font = new Font(super.f, 1, super.c + 2);
        graphics.setFont(font);
        graphics.setColor(super.h);
        if (super.N != null) {
            if (super.L) {
                graphics.drawString(super.N, this.bl - this.bu + super.C.a(super.N, font, this.bt), this.bm + this.bs + this.bu + super.M + (super.c + 17));
            }
            else {
                graphics.drawString(super.N, this.bl - this.bu + super.C.a(super.N, font, this.bt), this.bm + super.b);
            }
        }
        if (super.O != null) {
            super.C.a(this.bn + 5, this.bm + this.bu, graphics, super.O, super.c + 2, this.bs);
        }
    }
    
    public void a(final Color bz) {
        this.bz = bz;
    }
    
    private static String b(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = ']';
                    break;
                }
                case 1: {
                    c2 = 'U';
                    break;
                }
                case 2: {
                    c2 = 'D';
                    break;
                }
                case 3: {
                    c2 = '\u0019';
                    break;
                }
                default: {
                    c2 = '\u000f';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
