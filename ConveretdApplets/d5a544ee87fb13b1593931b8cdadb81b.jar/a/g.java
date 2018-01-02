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

public class g extends b
{
    private int bl;
    private int bm;
    private int bn;
    private int bo;
    private int bp;
    private int bq;
    private int br;
    private double bs;
    private double bt;
    private int bu;
    private int bv;
    private int bw;
    private int bx;
    private int by;
    private int bz;
    public double[] bA;
    public double[] bB;
    public int[][] bC;
    public int[][] bD;
    private String bE;
    private String bF;
    private String bG;
    private String bH;
    private String bI;
    private int bJ;
    private String[] bK;
    private int bL;
    private int bM;
    private int bN;
    private double bO;
    private String[] bP;
    private String[] bQ;
    private int bR;
    private int[][] bS;
    private int bT;
    private int bU;
    private int bV;
    private int bW;
    
    public g(final int bn, final int bm, final int a, final int b, final Color g, final Color h, final int c) {
        this.bo = 3;
        this.bp = 6;
        this.bw = 20;
        this.bL = 1;
        this.bM = -1;
        this.bR = 4;
        this.bT = 6;
        this.bU = 2;
        super.a = a;
        super.b = b;
        super.g = g;
        super.h = h;
        this.bl = bn + 20 + this.bw;
        this.bm = bm;
        this.bn = bn;
        super.c = c;
        this.bu = super.a - 40 - this.bw;
        this.bv = super.b - 25 - this.bw;
        super.bh = b("\fS\"{\u000erb\fn\u0015");
    }
    
    public boolean a(final int l, final n k, final String s, final String s2) {
        super.l = l;
        super.k = k;
        this.bz = super.k.a.size();
        final double[] array = new double[11];
        final Font font = new Font(super.f, 0, super.c);
        if (super.N != null) {
            this.bv -= 5 + (super.c + 2);
        }
        if (super.O != null) {
            this.bl += 15 + (super.c + 2) / 2;
            this.bu -= 15 + (super.c + 2) / 2;
        }
        this.bA = new double[super.l + 1];
        this.bB = new double[super.l + 1];
        this.bC = new int[super.l][this.bz + 1];
        this.bD = new int[super.l][this.bz + 1];
        if (super.bh.compareTo(b("\fS\"{\u000erb\fn\u0015")) == 0 || super.bh.compareTo(b("\fS\"{\u000erb\bf\u000b-C*C\u000e/C")) == 0) {
            for (int i = 0; i < this.bz; ++i) {
                final p p4 = super.k.a.elementAt(i);
                for (int j = 0; j < super.l; ++j) {
                    if (p4.a[j] != null) {
                        if (p4.a[j].a >= 0.0) {
                            final double[] ba = this.bA;
                            final int n = j;
                            ba[n] += p4.a[j].a;
                        }
                        else {
                            final double[] bb = this.bB;
                            final int n2 = j;
                            bb[n2] += p4.a[j].a;
                        }
                    }
                }
            }
            for (int n3 = 0; n3 < super.l; ++n3) {
                if (this.bA[n3] > this.bs) {
                    this.bs = this.bA[n3];
                }
                if (this.bB[n3] < this.bt) {
                    this.bt = this.bB[n3];
                }
            }
        }
        else if (!super.t || !super.v) {
            for (int n4 = 0; n4 < this.bz; ++n4) {
                final p p5 = super.k.a.elementAt(n4);
                for (int n5 = 0; n5 < super.l; ++n5) {
                    if (p5.a[n5] != null) {
                        if (p5.a[n5].a > this.bs) {
                            this.bs = p5.a[n5].a;
                        }
                        if (p5.a[n5].a < this.bt) {
                            this.bt = p5.a[n5].a;
                        }
                    }
                }
            }
        }
        if (this.bs == 0.0 && this.bt == 0.0) {
            this.bs = 5.0;
            this.bt = 0.0;
        }
        if (super.t) {
            this.bt = super.n;
        }
        if (super.v) {
            this.bs = super.o;
        }
        final double[] a = super.C.a(this.bs, this.bt, super.t, super.v, super.R, super.S);
        this.bs = a[0];
        this.bt = a[1];
        this.bO = a[2];
        this.bL = (int)a[3];
        final int n6 = (int)a[4];
        this.bK = new String[this.bL + 1];
        for (int bm = 0; bm <= this.bL; ++bm) {
            array[bm] = super.C.e(this.bt, super.C.d(this.bO, this.bL - bm));
            if (array[bm] == 0.0) {
                this.bM = bm;
            }
        }
        final NumberFormat d = super.C.d(super.bg);
        d.setMaximumFractionDigits(n6);
        d.setMinimumFractionDigits(n6);
        try {
            for (int n7 = 0; n7 <= this.bL; ++n7) {
                this.bK[n7] = String.valueOf(s2) + d.format(array[n7]) + s;
            }
        }
        catch (Exception ex) {}
        if (super.be) {
            this.bP = new String[super.l];
            this.bQ = new String[super.l];
            try {
                for (int n8 = 0; n8 < super.l; ++n8) {
                    this.bP[n8] = String.valueOf(s2) + d.format(this.bA[n8]) + s;
                    this.bQ[n8] = String.valueOf(s2) + d.format(this.bB[n8]) + s;
                }
            }
            catch (Exception ex2) {}
        }
        if (!super.bd) {
            this.bJ = super.C.c(this.bK[0], new Font(super.f, 0, super.c));
            this.bl += this.bJ;
            this.bu -= this.bJ;
        }
        if (super.H > 0 && super.I) {
            int n9 = 0;
            for (int n10 = 0; n10 < super.H; ++n10) {
                final q q = super.J[n10];
                if (q.e != null) {
                    final int c = super.C.c(q.e, font);
                    if (c > n9) {
                        n9 = c;
                    }
                }
            }
            this.bu = this.bu - n9 - 3;
        }
        if (super.bh.compareTo(b("\fS\"{\u000erb\fn\u0015")) == 0) {
            if (super.l > 0) {
                this.bx = this.bu / super.l;
            }
            else {
                this.bx = this.bu;
            }
            if (super.Y != 0 && this.bx - 4 - this.bp > super.Y) {
                this.bx = super.Y - 4 - this.bp + this.bw;
            }
            this.bu = this.bx * super.l + 20;
        }
        else if (super.bh.compareTo(b("\fS\"{\u000erb\bf\u000b-C*C\u000e/C")) == 0) {
            if (super.l > 1) {
                this.bx = this.bu / (super.l - 1);
                this.bu = this.bx * (super.l - 1) + 20;
            }
            else {
                this.bx = this.bu;
                this.bu = this.bx + 20;
            }
        }
        else {
            if (super.l > 0) {
                if (super.Z) {
                    this.bV = (this.bu - this.bT * super.l) / super.l - 1;
                }
                else {
                    this.bV = (this.bu - this.bT * super.l) / super.l;
                }
            }
            else {
                this.bV = this.bu;
            }
            if (this.bz > 0) {
                this.bW = (this.bV - this.bU * (this.bz - 1)) / this.bz;
                if (super.Y != 0 && this.bW > super.Y) {
                    this.bW = super.Y;
                    this.bV = (this.bW + this.bU) * this.bz;
                }
            }
            else {
                this.bW = this.bV - this.bU;
                this.bV = this.bW + this.bU;
            }
        }
        int n11;
        if (super.bh.compareTo(b("\u0006T!z\u0017$B}K% T")) == 0) {
            n11 = this.bV;
        }
        else {
            n11 = this.bx;
        }
        if (!super.L) {
            final int a2 = super.C.a(super.k.b, super.l, n11, this.bl, super.a, font);
            if (a2 == 2) {
                super.K = true;
            }
            else if (a2 == 3) {
                super.L = true;
                super.M = super.C.a(super.k.b, super.l, font);
                this.bv -= super.M;
            }
            if (super.K) {
                this.bv -= super.c + 5;
                if (super.N != null) {
                    this.bv -= super.c + 2;
                }
            }
        }
        this.by = this.bv / this.bL;
        this.bv = this.by * this.bL;
        if (super.bh.compareTo(b("\fS\"{\u000erb\fn\u0015")) == 0) {
            for (int n12 = 0; n12 < this.bz; ++n12) {
                final p p6 = super.k.a.elementAt(n12);
                for (int n13 = 0; n13 < super.l; ++n13) {
                    if (p6.a[n13] != null) {
                        double n14;
                        if (p6.a[n13].a < this.bt) {
                            n14 = this.bt;
                        }
                        else if (p6.a[n13].a > this.bs) {
                            n14 = this.bs;
                        }
                        else {
                            n14 = p6.a[n13].a;
                        }
                        if (n14 >= 0.0) {
                            this.bC[n13][n12] = (int)(n14 / (this.bs - this.bt) * this.bv);
                        }
                        else {
                            this.bD[n13][n12] = (int)(n14 / (this.bs - this.bt) * this.bv);
                        }
                    }
                    else {
                        this.bC[n13][n12] = 0;
                    }
                }
            }
        }
        else if (super.bh.compareTo(b("\fS\"{\u000erb\bf\u000b-C*C\u000e/C")) == 0) {
            for (int n15 = 0; n15 < this.bz; ++n15) {
                final p p7 = super.k.a.elementAt(n15);
                for (int n16 = 0; n16 < super.l; ++n16) {
                    if (p7.a[n16] != null) {
                        p7.a[n16].n = (int)(p7.a[n16].a / (this.bs - this.bt) * this.bv);
                    }
                }
            }
        }
        else {
            for (int n17 = 0; n17 < this.bz; ++n17) {
                final p p8 = super.k.a.elementAt(n17);
                for (int n18 = 0; n18 < super.l; ++n18) {
                    if (p8.a[n18] != null) {
                        double n19;
                        if (p8.a[n18].a < this.bt) {
                            n19 = this.bt;
                        }
                        else if (p8.a[n18].a > this.bs) {
                            n19 = this.bs;
                        }
                        else {
                            n19 = p8.a[n18].a;
                        }
                        if (n19 >= 0.0) {
                            this.bC[n18][n17] = (int)(n19 / (this.bs - this.bt) * this.bv);
                        }
                        else {
                            this.bD[n18][n17] = (int)(n19 / (this.bs - this.bt) * this.bv);
                        }
                    }
                    else {
                        this.bC[n18][n17] = 0;
                    }
                }
            }
        }
        if (this.bs >= 0.0 && this.bt < 0.0) {
            this.bN = (int)(this.bv - (0.0 - this.bt) / (this.bs - this.bt) * this.bv);
        }
        else {
            this.bN = this.bv;
        }
        return true;
    }
    
    public void a(final Graphics graphics) {
        int n = 1;
        final Font font = new Font(super.f, 0, super.c);
        graphics.setFont(font);
        if (super.H > 0) {
            if (!super.bi) {
                super.bj = b("\u0019i c\u001e");
            }
            for (int i = 0; i < super.H; ++i) {
                final q q = super.J[i];
                if (q != null) {
                    if (q.a(this.bt, this.bs)) {
                        final int n2 = (int)(this.bv - (q.a() - this.bt) / (this.bs - this.bt) * this.bv);
                        graphics.setColor(q.d);
                        graphics.drawLine(this.bl, this.bm + n2, this.bl + this.bu, this.bm + n2);
                        graphics.drawLine(this.bl, this.bm + n2, this.bl - this.bw, this.bm + n2 + this.bw);
                        if (q.e != null) {
                            final int n3 = this.bl + this.bu + 3;
                            final int n4 = this.bm + n2 + super.C.b(q.e, font) / 2 - 1;
                            graphics.setColor(super.h);
                            graphics.drawString(q.e, n3, n4);
                        }
                    }
                    else if (q.b(this.bt, this.bs)) {
                        final double[] c = q.c(this.bt, this.bs);
                        final double n5 = c[0];
                        final double n6 = c[1];
                        final int n7 = (int)(this.bv - (n5 - this.bt) / (this.bs - this.bt) * this.bv);
                        final int n8 = (int)(this.bv - (n6 - this.bt) / (this.bs - this.bt) * this.bv);
                        final int n9 = n7 - n8;
                        if (n9 > 0) {
                            graphics.setColor(q.d);
                            graphics.fillRect(this.bl, this.bm + n8, this.bu, n9);
                            final Polygon polygon = new Polygon();
                            polygon.addPoint(this.bl, this.bm + n8);
                            polygon.addPoint(this.bl, this.bm + n8 + n9);
                            polygon.addPoint(this.bl - this.bw, this.bm + n8 + n9 + this.bw);
                            polygon.addPoint(this.bl - this.bw, this.bm + n8 + this.bw);
                            graphics.fillPolygon(polygon);
                        }
                        if (q.e != null) {
                            final int n10 = this.bl + this.bu + 3;
                            final int n11 = this.bm + n7 - n9 / 2 + super.C.b(q.e, font) / 2 - 1;
                            graphics.setColor(super.h);
                            graphics.drawString(q.e, n10, n11);
                        }
                    }
                }
            }
        }
        graphics.setColor(super.E);
        final int n12 = this.bl - this.bw;
        final int n13 = this.bm + this.bw + this.bv;
        final int n14 = this.bm + this.bw;
        final int n15 = this.bm + this.bv;
        if (super.Z) {
            graphics.drawRect(this.bl, this.bm, this.bu, this.bv);
            graphics.drawRect(this.bl + 1, this.bm + 1, this.bu - 2, this.bv - 2);
            graphics.drawLine(n12, n14, n12, n13);
            graphics.drawLine(n12 + 1, n14, n12 + 1, n13);
            graphics.drawLine(n12, n13, n12 + this.bu, n13);
            graphics.drawLine(n12, n13 - 1, n12 + this.bu, n13 - 1);
            graphics.drawLine(this.bl, this.bm, n12, n14);
            graphics.drawLine(this.bl + 1, this.bm, this.bl + 1 - this.bw, n14);
            graphics.drawLine(this.bl, n15, n12, n13);
            graphics.drawLine(this.bl + 1, n15, this.bl + 1 - this.bw, n13);
            graphics.drawLine(this.bl + this.bu, n15, n12 + this.bu, n13);
            graphics.drawLine(this.bl - 1 + this.bu, n15, this.bl - 1 - this.bw + this.bu, n13);
            graphics.drawLine(n12, n13 + 6, n12, n13);
            graphics.drawLine(this.bl + 1 - this.bw, n13 + 6, this.bl + 1 - this.bw, n13);
            graphics.drawLine(this.bl + this.bu - this.bw, n13 + 6, this.bl + this.bu - this.bw, n13);
            graphics.drawLine(this.bl + 1 + this.bu - this.bw, n13 + 6, this.bl + 1 + this.bu - this.bw, n13);
            graphics.drawLine(n12 - 6, n13, n12, n13);
            graphics.drawLine(n12 - 6, this.bm - 1 + this.bv + this.bw, n12, this.bm - 1 + this.bw + this.bv);
            for (int j = 0; j < this.bL; ++j) {
                graphics.drawLine(n12 - 6, n14 + this.by * j, n12, n14 + this.by * j);
                graphics.drawLine(n12 - 6, this.bm + 1 + this.bw + this.by * j, n12, this.bm + 1 + this.bw + this.by * j);
                if (j != 0) {
                    graphics.drawLine(this.bl, this.bm + this.by * j, n12, n14 + this.by * j);
                    graphics.drawLine(this.bl + 1, this.bm + this.by * j, this.bl + 1 - this.bw, n14 + this.by * j);
                    if (super.C.b(super.bj, b("\u0000J\"")) || super.C.b(super.bj, b("\u0018i c\u001e"))) {
                        graphics.drawLine(this.bl, this.bm + this.by * j, this.bl + this.bu, this.bm + this.by * j);
                        graphics.drawLine(this.bl, this.bm + 1 + this.by * j, this.bl + this.bu, this.bm + 1 + this.by * j);
                    }
                }
            }
        }
        else {
            graphics.drawRect(this.bl, this.bm, this.bu, this.bv);
            graphics.drawLine(n12, n14, n12, n13);
            graphics.drawLine(n12, n13, n12 + this.bu, n13);
            graphics.drawLine(this.bl, this.bm, n12, n14);
            graphics.drawLine(this.bl, n15, n12, n13);
            graphics.drawLine(this.bl + this.bu, n15, n12 + this.bu, n13);
            graphics.drawLine(n12, n13 + 6, n12, n13);
            graphics.drawLine(this.bl + this.bu - this.bw, n13 + 6, this.bl + this.bu - this.bw, n13);
            graphics.drawLine(n12 - 6, n13, n12, n13);
            for (int k = 0; k < this.bL; ++k) {
                graphics.drawLine(n12 - 6, n14 + this.by * k, n12, n14 + this.by * k);
                if (k != 0) {
                    graphics.drawLine(this.bl, this.bm + this.by * k, n12, n14 + this.by * k);
                    if (super.C.b(super.bj, b("\u0000J\"")) || super.C.b(super.bj, b("\u0018i c\u001e"))) {
                        graphics.drawLine(this.bl, this.bm + this.by * k, this.bl + this.bu, this.bm + this.by * k);
                    }
                }
            }
        }
        graphics.setColor(super.h);
        if (!super.bd) {
            for (int l = 0; l <= this.bL; ++l) {
                graphics.drawString(this.bK[l], this.bl - 10 - this.bw - super.C.c(this.bK[l], font), n14 + l * this.by + super.c / 2);
            }
        }
        final int n16 = this.bw - 6;
        if (super.bh.compareTo(b("\fS\"{\u000erb\fn\u0015")) == 0) {
            this.bW = this.bx - 4 - this.bp;
            if (this.bx < 25) {
                this.bo = 1;
            }
            for (int n17 = 0; n17 < super.l; ++n17) {
                graphics.setColor(super.E);
                if (n17 != 0) {
                    if (super.Z) {
                        graphics.drawLine(n12 + this.bx * n17 + 5, n15 + this.bw + 6, n12 + this.bx * n17 + 5, n13);
                        graphics.drawLine(this.bl + 1 - this.bw + this.bx * n17 + 5, n15 + this.bw + 6, this.bl + 1 - this.bw + this.bx * n17 + 5, n13);
                        graphics.drawLine(this.bl + this.bx * n17 + 5, n15, n12 + this.bx * n17 + 5, n13);
                        graphics.drawLine(this.bl + 1 + this.bx * n17 + 5, n15, this.bl + 1 - this.bw + this.bx * n17 + 5, n13);
                        if (super.C.b(super.bj, b("\u0000J\"")) || super.C.b(super.bj, b("\u0019i c\u001e"))) {
                            graphics.drawLine(this.bl + this.bx * n17 + 5, n15, this.bl + this.bx * n17 + 5, this.bm);
                            graphics.drawLine(this.bl + 1 + this.bx * n17 + 5, n15, this.bl + 1 + this.bx * n17 + 5, this.bm);
                        }
                    }
                    else {
                        graphics.drawLine(n12 + this.bx * n17 + 5, n15 + this.bw + 6, n12 + this.bx * n17 + 5, n13);
                        graphics.drawLine(this.bl + this.bx * n17 + 5, n15, n12 + this.bx * n17 + 5, n13);
                        if (super.C.b(super.bj, b("\u0000J\"")) || super.C.b(super.bj, b("\u0019i c\u001e"))) {
                            graphics.drawLine(this.bl + this.bx * n17 + 5, n15, this.bl + this.bx * n17 + 5, this.bm);
                        }
                    }
                }
                for (int n18 = 0; n18 < this.bz; ++n18) {
                    final p p = super.k.a.elementAt(n18);
                    final Color c2 = p.c;
                    if (this.bC[n17][n18] != 0 || this.bD[n17][n18] != 0) {
                        int abs;
                        int n19;
                        int t;
                        int u;
                        boolean b;
                        if (this.bC[n17][n18] != 0 && this.bD[n17][n18] == 0) {
                            abs = this.bC[n17][n18];
                            n19 = this.bC[n17][this.bz];
                            t = this.bl + this.bx * n17 - 5;
                            u = this.bm + this.bN - abs + n16 + this.bo - n19;
                            b = false;
                        }
                        else {
                            abs = Math.abs(this.bD[n17][n18]);
                            n19 = this.bD[n17][this.bz];
                            t = this.bl + this.bx * n17 - 5;
                            u = this.bm + this.bN + n16 + this.bo - n19;
                            b = true;
                        }
                        final int[] array = { t + this.bW, t + this.bW + n16, t + this.bW + n16, t + this.bW };
                        final int[] array2 = { u, u - n16, this.bm + this.bN + this.bo - n19, this.bm + this.bN + n16 + this.bo - n19 };
                        final int[] array3 = { t + n16, t, t + this.bW, t + this.bW + n16 };
                        final int[] array4 = { u - n16, u, u, u - n16 };
                        if (b) {
                            final int[] array5 = array2;
                            final int n20 = 2;
                            array5[n20] += abs;
                            final int[] array6 = array2;
                            final int n21 = 3;
                            array6[n21] += abs;
                        }
                        Color g;
                        if (p.a[n17].g != null) {
                            g = p.a[n17].g;
                        }
                        else {
                            g = c2;
                        }
                        graphics.setColor(g);
                        graphics.fillRect(t, u, this.bW, abs);
                        graphics.setColor(g.darker());
                        graphics.fillPolygon(array, array2, 4);
                        if (!b || (b && this.bA[n17] == 0.0 && n19 == 0)) {
                            graphics.setColor(g.brighter());
                            graphics.fillPolygon(array3, array4, 4);
                        }
                        if (!super.F && p.a[n17] != null) {
                            p.a[n17].t = t;
                            p.a[n17].u = u;
                            p.a[n17].v = t + this.bW;
                            p.a[n17].w = u + abs;
                        }
                        final int[] array7 = this.bC[n17];
                        final int bz = this.bz;
                        array7[bz] += this.bC[n17][n18];
                        final int[] array8 = this.bD[n17];
                        final int bz2 = this.bz;
                        array8[bz2] += this.bD[n17][n18];
                    }
                }
                graphics.setColor(super.E);
                if (n17 != super.l - 1) {
                    if (super.Z) {
                        graphics.drawLine(this.bl + this.bx * (n17 + 1) + 5, this.bm + this.bN, n12 + this.bx * (n17 + 1) + 5, n14 + this.bN);
                        graphics.drawLine(this.bl + 1 + this.bx * (n17 + 1) + 5, this.bm + this.bN, this.bl + 1 - this.bw + this.bx * (n17 + 1) + 5, n14 + this.bN);
                    }
                    else {
                        graphics.drawLine(this.bl + this.bx * (n17 + 1) + 5, this.bm + this.bN, n12 + this.bx * (n17 + 1) + 5, n14 + this.bN);
                    }
                }
                for (int n22 = 0; n22 < super.l; ++n22) {
                    this.bC[n22][this.bz] = 0;
                    this.bD[n22][this.bz] = 0;
                }
            }
            graphics.setColor(super.h);
            for (int n23 = 0; n23 < super.l; ++n23) {
                if (!super.L) {
                    if (super.k.b[n23] != null) {
                        if (!super.K) {
                            graphics.drawString(super.k.b[n23], this.bl + n23 * this.bx + this.bW / 2 - 10 - super.C.a(super.k.b[n23], font), this.bm + this.bv + this.bw + 20);
                        }
                        else if (n != 0) {
                            graphics.drawString(super.k.b[n23], this.bl + n23 * this.bx + this.bW / 2 - 10 - super.C.a(super.k.b[n23], font), this.bm + this.bv + this.bw + 20);
                        }
                        else {
                            graphics.drawString(super.k.b[n23], this.bl + n23 * this.bx + this.bW / 2 - 10 - super.C.a(super.k.b[n23], font), this.bm + this.bv + this.bw + 25 + super.c);
                        }
                    }
                    if (n != 0) {
                        n = 0;
                    }
                    else {
                        n = 1;
                    }
                }
                else if (super.k.b[n23] != null) {
                    final Image a = super.C.a(super.k.b[n23], font, true, super.h, super.g);
                    if (a != null) {
                        graphics.drawImage(a, this.bl + n23 * this.bx + this.bW / 2 - 10 - super.c / 2, this.bm + this.bv + this.bw + 5, null);
                    }
                }
            }
        }
        else if (super.bh.compareTo(b("\fS\"{\u000erb\bf\u000b-C*C\u000e/C")) == 0) {
            if (this.bx < 25) {
                this.bo = 1;
            }
            this.bS = new int[this.bz + 1][super.l];
            for (int n24 = 0; n24 <= this.bz; ++n24) {
                for (int n25 = 0; n25 < super.l; ++n25) {
                    this.bS[n24][n25] = 0;
                }
            }
            graphics.setColor(super.E);
            for (int n26 = 1; n26 < super.l; ++n26) {
                if (n26 != 0) {
                    if (super.Z) {
                        graphics.drawLine(this.bl - this.bw + this.bx * n26 + 5, this.bm + this.bv + this.bw + 6, this.bl - this.bw + this.bx * n26 + 5, this.bm + this.bw + this.bv);
                        graphics.drawLine(this.bl + 1 - this.bw + this.bx * n26 + 5, this.bm + this.bv + this.bw + 6, this.bl + 1 - this.bw + this.bx * n26 + 5, this.bm + this.bw + this.bv);
                        graphics.drawLine(this.bl + this.bx * n26 + 5, this.bm + this.bv, this.bl - this.bw + this.bx * n26 + 5, this.bm + this.bw + this.bv);
                        graphics.drawLine(this.bl + 1 + this.bx * n26 + 5, this.bm + this.bv, this.bl + 1 - this.bw + this.bx * n26 + 5, this.bm + this.bw + this.bv);
                        if (super.C.b(super.bj, b("\u0000J\"")) || super.C.b(super.bj, b("\u0019i c\u001e"))) {
                            graphics.drawLine(this.bl + this.bx * n26 + 5, this.bm + this.bv, this.bl + this.bx * n26 + 5, this.bm);
                            graphics.drawLine(this.bl + 1 + this.bx * n26 + 5, this.bm + this.bv, this.bl + 1 + this.bx * n26 + 5, this.bm);
                        }
                    }
                    else {
                        graphics.drawLine(this.bl - this.bw + this.bx * n26 + 5, this.bm + this.bv + this.bw + 6, this.bl - this.bw + this.bx * n26 + 5, this.bm + this.bw + this.bv);
                        graphics.drawLine(this.bl + this.bx * n26 + 5, this.bm + this.bv, this.bl - this.bw + this.bx * n26 + 5, this.bm + this.bw + this.bv);
                        if (super.C.b(super.bj, b("\u0000J\"")) || super.C.b(super.bj, b("\u0019i c\u001e"))) {
                            graphics.drawLine(this.bl + this.bx * n26 + 5, this.bm + this.bv, this.bl + this.bx * n26 + 5, this.bm);
                        }
                    }
                }
            }
            for (int n27 = 0; n27 < this.bz; ++n27) {
                final p p2 = super.k.a.elementAt(n27);
                final Color c3 = p2.c;
                int n28 = this.bl - n16;
                int n29;
                if (p2.a[0] != null) {
                    n29 = this.bm + this.bv - this.bS[n27][0] - p2.a[0].n + n16 + this.bo;
                    final int[] array9 = this.bS[n27 + 1];
                    final int n30 = 0;
                    array9[n30] += this.bS[n27][0] + p2.a[0].n;
                }
                else {
                    n29 = this.bm + this.bv - this.bS[n27][0] + n16 + this.bo;
                    final int[] array10 = this.bS[n27 + 1];
                    final int n31 = 0;
                    array10[n31] += this.bS[n27][0];
                }
                final Polygon polygon2 = new Polygon();
                polygon2.addPoint(n28, n29);
                final Color darker = c3.darker();
                graphics.setColor(darker);
                if (!super.F && p2.a[0] != null) {
                    p2.a[0].t = n28 - this.bR;
                    p2.a[0].u = n29 - this.bR;
                    p2.a[0].v = n28 + this.bR;
                    p2.a[0].w = n29 + this.bR;
                }
                for (int n32 = 1; n32 < super.l; ++n32) {
                    final int n33 = n28;
                    final int n34 = n29;
                    n28 = this.bl + n32 * this.bx - n16;
                    if (p2.a[n32] != null) {
                        n29 = this.bm + this.bv - this.bS[n27][n32] - p2.a[n32].n + n16 + this.bo;
                        final int[] array11 = this.bS[n27 + 1];
                        final int n35 = n32;
                        array11[n35] += this.bS[n27][n32] + p2.a[n32].n;
                        polygon2.addPoint(n28, n29);
                        final int n36 = n28;
                        final int n37 = n29;
                        final int n38 = n33;
                        final int n39 = n34;
                        final int n40 = n33 + n16;
                        final int n41 = n34 - n16;
                        final int n42 = n28 + n16;
                        final int n43 = n29 - n16;
                        final Polygon polygon3 = new Polygon();
                        polygon3.addPoint(n36, n37);
                        polygon3.addPoint(n38, n39);
                        polygon3.addPoint(n40, n41);
                        polygon3.addPoint(n42, n43);
                        graphics.setColor(darker);
                        graphics.fillPolygon(polygon3);
                        if (!super.F) {
                            p2.a[n32].t = n28 - this.bR;
                            p2.a[n32].u = n29 - this.bR;
                            p2.a[n32].v = n28 + this.bR;
                            p2.a[n32].w = n29 + this.bR;
                        }
                    }
                    else {
                        n29 = n34;
                    }
                }
                final int n44 = n28;
                final int n45 = n29;
                final int n46 = n44;
                final int n47 = this.bm + this.bv - this.bS[n27][super.l - 1] + this.bo;
                polygon2.addPoint(n46, n47);
                final int n48 = n46;
                final int n49 = n47 + n16;
                final int n50 = n44;
                final int n51 = n45;
                final int n52 = n44 + n16;
                final int n53 = n45 - n16;
                final int n54 = n46 + n16;
                final int n55 = n47;
                final Polygon polygon4 = new Polygon();
                polygon4.addPoint(n48, n49);
                polygon4.addPoint(n50, n51);
                polygon4.addPoint(n52, n53);
                polygon4.addPoint(n54, n55);
                graphics.setColor(darker);
                graphics.fillPolygon(polygon4);
                for (int n56 = super.l - 1; n56 >= 0; --n56) {
                    polygon2.addPoint(this.bl + n56 * this.bx - n16, this.bm + this.bv - this.bS[n27][n56] + n16 + this.bo);
                }
                graphics.setColor(c3);
                graphics.fillPolygon(polygon2);
            }
            graphics.setColor(super.h);
            for (int n57 = 0; n57 < super.l; ++n57) {
                final int n58 = this.bl - this.bw + this.bx * n57 + 5;
                if (!super.L) {
                    if (super.k.b[n57] != null) {
                        if (!super.K) {
                            graphics.drawString(super.k.b[n57], n58 - super.C.a(super.k.b[n57], font), this.bm + this.bv + this.bw + 20);
                        }
                        else if (n != 0) {
                            graphics.drawString(super.k.b[n57], n58 - super.C.a(super.k.b[n57], font), this.bm + this.bv + this.bw + 20);
                        }
                        else {
                            graphics.drawString(super.k.b[n57], n58 - super.C.a(super.k.b[n57], font), this.bm + this.bv + this.bw + 25 + super.c);
                        }
                    }
                    if (n != 0) {
                        n = 0;
                    }
                    else {
                        n = 1;
                    }
                }
                else if (super.k.b[n57] != null) {
                    final Image a2 = super.C.a(super.k.b[n57], font, true, super.h, super.g);
                    if (a2 != null) {
                        graphics.drawImage(a2, n58 - super.c / 2, this.bm + this.bv + this.bw + 5, null);
                    }
                }
            }
        }
        else {
            for (int n59 = 0; n59 < super.l; ++n59) {
                final int n60 = this.bl + 2 + this.bT + this.bT / 2 + this.bV * n59 + this.bT * n59 - this.bw;
                graphics.setColor(super.E);
                if (n59 != 0) {
                    final int n61 = n60 - this.bT - 1;
                    if (super.Z) {
                        graphics.drawLine(n61, this.bm + this.bv + this.bw + 6, n61, this.bm + this.bw + this.bv);
                        graphics.drawLine(n61 + 1, this.bm + this.bv + this.bw + 6, n61 + 1, this.bm + this.bw + this.bv);
                        graphics.drawLine(n61 + this.bw, this.bm + this.bv, n61, this.bm + this.bw + this.bv);
                        graphics.drawLine(n61 + this.bw + 1, this.bm + this.bv, n61 + 1, this.bm + this.bw + this.bv);
                        if (super.C.b(super.bj, b("\u0000J\"")) || super.C.b(super.bj, b("\u0019i c\u001e"))) {
                            graphics.drawLine(n61 + this.bw, this.bm + this.bv, n61 + this.bw, this.bm);
                            graphics.drawLine(n61 + this.bw + 1, this.bm + this.bv, n61 + this.bw + 1, this.bm);
                        }
                    }
                    else {
                        graphics.drawLine(n61, this.bm + this.bv + this.bw + 6, n61, this.bm + this.bw + this.bv);
                        graphics.drawLine(n61 + this.bw, this.bm + this.bv, n61, this.bm + this.bw + this.bv);
                        if (super.C.b(super.bj, b("\u0000J\"")) || super.C.b(super.bj, b("\u0019i c\u001e"))) {
                            graphics.drawLine(n61 + this.bw, this.bm + this.bv, n61 + this.bw, this.bm);
                        }
                    }
                }
                for (int n62 = 0; n62 < this.bz; ++n62) {
                    final p p3 = super.k.a.elementAt(n62);
                    final Color c4 = p3.c;
                    if (this.bC[n59][n62] != 0 || this.bD[n59][n62] != 0) {
                        int abs2;
                        int u2;
                        boolean b2;
                        if (this.bC[n59][n62] != 0 && this.bD[n59][n62] == 0) {
                            abs2 = this.bC[n59][n62];
                            u2 = this.bm + this.bN - abs2 + n16 + this.bo;
                            b2 = false;
                        }
                        else {
                            abs2 = Math.abs(this.bD[n59][n62]);
                            u2 = this.bm + this.bN + n16 + this.bo;
                            b2 = true;
                        }
                        final int t2 = n60 + this.bW * n62 + this.bU * n62;
                        final int[] array12 = { t2 + this.bW, t2 + this.bW + n16, t2 + this.bW + n16, t2 + this.bW };
                        final int[] array13 = { u2, u2 - n16, this.bm + this.bN + this.bo, this.bm + this.bN + n16 + this.bo };
                        final int[] array14 = { t2 + n16, t2, t2 + this.bW, t2 + this.bW + n16 };
                        final int[] array15 = { u2 - n16, u2, u2, u2 - n16 };
                        if (b2) {
                            final int[] array16 = array13;
                            final int n63 = 2;
                            array16[n63] += abs2;
                            final int[] array17 = array13;
                            final int n64 = 3;
                            array17[n64] += abs2;
                        }
                        Color g2;
                        if (p3.a[n59].g != null) {
                            g2 = p3.a[n59].g;
                        }
                        else {
                            g2 = c4;
                        }
                        graphics.setColor(g2);
                        graphics.fillRect(t2, u2, this.bW, abs2);
                        graphics.setColor(g2.darker());
                        graphics.fillPolygon(array12, array13, 4);
                        if (!b2 || (b2 && this.bA[n59] == 0.0)) {
                            graphics.setColor(g2.brighter());
                            graphics.fillPolygon(array14, array15, 4);
                        }
                        if (!super.F && p3.a[n59] != null) {
                            p3.a[n59].t = t2;
                            p3.a[n59].u = u2;
                            p3.a[n59].v = t2 + this.bW;
                            p3.a[n59].w = u2 + abs2;
                        }
                        final int[] array18 = this.bC[n59];
                        final int bz3 = this.bz;
                        array18[bz3] += this.bC[n59][n62];
                        final int[] array19 = this.bD[n59];
                        final int bz4 = this.bz;
                        array19[bz4] += this.bD[n59][n62];
                    }
                }
                graphics.setColor(super.E);
                if (n59 != super.l - 1) {
                    final int n65 = n60 - 1 + this.bV;
                    if (super.Z) {
                        graphics.drawLine(n65 + this.bw, this.bm + this.bN, n65, this.bm + this.bw + this.bN);
                        graphics.drawLine(n65 + this.bw + 1, this.bm + this.bN, n65 + 1, this.bm + this.bw + this.bN);
                    }
                    else {
                        graphics.drawLine(n65 + this.bw, this.bm + this.bN, n65, this.bm + this.bw + this.bN);
                    }
                }
                for (int n66 = 0; n66 < super.l; ++n66) {
                    this.bC[n66][this.bz] = 0;
                    this.bD[n66][this.bz] = 0;
                }
            }
            graphics.setColor(super.h);
            for (int n67 = 0; n67 < super.l; ++n67) {
                final int n68 = this.bl + 2 + this.bT + this.bT / 2 + this.bV * n67 + this.bT * n67 - this.bw + this.bV / 2;
                if (!super.L) {
                    if (super.k.b[n67] != null) {
                        if (!super.K) {
                            graphics.drawString(super.k.b[n67], n68 - super.C.a(super.k.b[n67], font), this.bm + this.bv + this.bw + 20);
                        }
                        else if (n != 0) {
                            graphics.drawString(super.k.b[n67], n68 - super.C.a(super.k.b[n67], font), this.bm + this.bv + this.bw + 20);
                        }
                        else {
                            graphics.drawString(super.k.b[n67], n68 - super.C.a(super.k.b[n67], font), this.bm + this.bv + this.bw + 25 + super.c);
                        }
                    }
                    if (n != 0) {
                        n = 0;
                    }
                    else {
                        n = 1;
                    }
                }
                else if (super.k.b[n67] != null) {
                    final Image a3 = super.C.a(super.k.b[n67], font, true, super.h, super.g);
                    if (a3 != null) {
                        graphics.drawImage(a3, n68 - this.bV / 2, this.bm + this.bv + this.bw + 5, null);
                    }
                }
            }
        }
        if (super.be) {
            if (super.bh.compareTo(b("\fS\"{\u000erb\fn\u0015")) == 0) {
                graphics.setColor(super.h);
                for (int n69 = 0; n69 < super.l; ++n69) {
                    final String s = this.bP[n69];
                    final String s2 = this.bQ[n69];
                    int n70 = 0;
                    for (int n71 = 0; n71 <= this.bz; ++n71) {
                        n70 += this.bC[n69][n71];
                    }
                    if (n70 > 0) {
                        int n72 = this.bm + this.bN - n70;
                        int n73 = this.bl + this.bx * n69 - 5 + n16 + super.C.a(s, font, this.bW);
                        if (n72 - super.c < this.bm) {
                            n72 = n72 + n16 + 6 + super.c;
                            n73 -= n16;
                        }
                        graphics.drawString(s, n73, n72);
                    }
                    int n74 = 0;
                    for (int n75 = 0; n75 <= this.bz; ++n75) {
                        n74 += this.bD[n69][n75];
                    }
                    if (n74 < 0) {
                        int n76 = this.bm + this.bN - n74 + n16 + 3 + super.c;
                        final int n77 = this.bl + this.bx * n69 - 5 + super.C.a(s2, font, this.bW);
                        if (n76 > this.bm + this.bv) {
                            n76 = n76 - 6 - super.c;
                        }
                        graphics.drawString(s2, n77, n76);
                    }
                }
            }
            else if (super.bh.compareTo(b("\u0006T!z\u0017$B}K% T")) == 0) {
                for (int n78 = 0; n78 < super.l; ++n78) {
                    final int n79 = this.bl + 2 + this.bT + this.bT / 2 + this.bV * n78 + this.bT * n78 - this.bw;
                    for (int n80 = 0; n80 < this.bz; ++n80) {
                        final int n81 = n79 + this.bW * n80 + this.bU * n80;
                        if (this.bC[n78][n80] != 0 || this.bD[n78][n80] != 0) {
                            int n82;
                            if (this.bC[n78][n80] != 0 && this.bD[n78][n80] == 0) {
                                n82 = this.bm + this.bN - this.bC[n78][n80] + n16 + this.bo;
                            }
                            else {
                                Math.abs(this.bD[n78][n80]);
                                n82 = this.bm + this.bN + n16 + this.bo;
                            }
                            final String m = super.k.a.elementAt(n80).a[n78].k;
                            int n83 = n82 - 3;
                            int n84 = n81 + super.C.a(m, font, this.bW);
                            if (n83 - super.c < this.bm) {
                                n83 = n83 + n16 + 6 + super.c;
                                n84 -= n16;
                            }
                            graphics.setColor(super.h);
                            graphics.drawString(m, n84, n83);
                        }
                    }
                }
            }
            else {
                for (int n85 = 0; n85 < super.l; ++n85) {
                    final String s3 = this.bP[n85];
                    int n86 = this.bm + this.bv - this.bS[this.bz][n85] + n16 - 3;
                    final int n87 = this.bl + this.bx * n85 - super.C.c(s3, font) / 2 - n16;
                    if (n86 - super.c < this.bm) {
                        n86 = n86 + 6 + super.c;
                    }
                    graphics.setColor(super.h);
                    graphics.drawString(s3, n87, n86);
                }
            }
        }
        graphics.setColor(super.E);
        if (super.Z) {
            graphics.drawLine(this.bl - this.bw, this.bm + this.bN + this.bw, this.bl + this.bu - this.bw, this.bm + this.bN + this.bw);
            graphics.drawLine(this.bl - this.bw, this.bm - 1 + this.bN + this.bw, this.bl + this.bu - this.bw, this.bm - 1 + this.bN + this.bw);
            graphics.drawLine(this.bl + this.bu, this.bm + this.bN, this.bl + this.bu - this.bw, this.bm + this.bw + this.bN);
            graphics.drawLine(this.bl - 1 + this.bu, this.bm + this.bN, this.bl - 1 + this.bu - this.bw, this.bm + this.bw + this.bN);
        }
        else {
            graphics.drawLine(this.bl - this.bw, this.bm + this.bN + this.bw, this.bl + this.bu - this.bw, this.bm + this.bN + this.bw);
            graphics.drawLine(this.bl + this.bu, this.bm + this.bN, this.bl + this.bu - this.bw, this.bm + this.bw + this.bN);
        }
        this.b(graphics);
        super.C.a(graphics, this);
        super.F = true;
    }
    
    public void a(final Graphics graphics, final o o) {
        final int n = this.bw - 6;
        final boolean b = false;
        final Font font = new Font(super.f, 0, super.c);
        graphics.setFont(font);
        if (super.bh.compareTo(b("\fS\"{\u000erb\fn\u0015")) == 0) {
            this.bW = this.bx - 4 - this.bp;
            for (int i = 0; i < super.l; ++i) {
                for (int j = 0; j < this.bz; ++j) {
                    final p p2 = super.k.a.elementAt(j);
                    final Color c = p2.c;
                    if (this.bC[i][j] != 0 || this.bD[i][j] != 0) {
                        int abs;
                        int n2;
                        int n3;
                        int n4;
                        boolean b2;
                        if (this.bC[i][j] != 0 && this.bD[i][j] == 0) {
                            abs = this.bC[i][j];
                            n2 = this.bC[i][this.bz];
                            n3 = this.bl + this.bx * i - 5;
                            n4 = this.bm + this.bN - abs + n + this.bo - n2;
                            b2 = false;
                        }
                        else {
                            abs = Math.abs(this.bD[i][j]);
                            n2 = this.bD[i][this.bz];
                            n3 = this.bl + this.bx * i - 5;
                            n4 = this.bm + this.bN + n + this.bo - n2;
                            b2 = true;
                        }
                        final int[] array = { n3 + this.bW, n3 + this.bW + n, n3 + this.bW + n, n3 + this.bW };
                        final int[] array2 = { n4, n4 - n, this.bm + this.bN + this.bo - n2, this.bm + this.bN + n + this.bo - n2 };
                        final int[] array3 = { n3 + n, n3, n3 + this.bW, n3 + this.bW + n };
                        final int[] array4 = { n4 - n, n4, n4, n4 - n };
                        if (b2) {
                            final int[] array5 = array2;
                            final int n5 = 2;
                            array5[n5] += abs;
                            final int[] array6 = array2;
                            final int n6 = 3;
                            array6[n6] += abs;
                        }
                        if (p2.a[i] == o) {
                            graphics.setColor(super.D);
                            graphics.fillRect(n3, n4, this.bW, abs);
                            graphics.setColor(super.D.darker());
                            graphics.fillPolygon(array, array2, 4);
                            if (!b2 || (b2 && this.bA[i] == 0.0 && n2 == 0)) {
                                graphics.setColor(super.D.brighter());
                                graphics.fillPolygon(array3, array4, 4);
                            }
                        }
                        else {
                            Color g;
                            if (p2.a[i].g != null) {
                                g = p2.a[i].g;
                            }
                            else {
                                g = c;
                            }
                            graphics.setColor(g);
                            graphics.fillRect(n3, n4, this.bW, abs);
                            graphics.setColor(g.darker());
                            graphics.fillPolygon(array, array2, 4);
                            if (!b2 || (b2 && this.bA[i] == 0.0 && n2 == 0)) {
                                graphics.setColor(g.brighter());
                                graphics.fillPolygon(array3, array4, 4);
                            }
                        }
                        final int[] array7 = this.bC[i];
                        final int bz = this.bz;
                        array7[bz] += this.bC[i][j];
                        final int[] array8 = this.bD[i];
                        final int bz2 = this.bz;
                        array8[bz2] += this.bD[i][j];
                    }
                }
                graphics.setColor(super.E);
                if (i != super.l - 1) {
                    if (super.Z) {
                        graphics.drawLine(this.bl + this.bx * (i + 1) + 5, this.bm + this.bN, this.bl - this.bw + this.bx * (i + 1) + 5, this.bm + this.bw + this.bN);
                        graphics.drawLine(this.bl + 1 + this.bx * (i + 1) + 5, this.bm + this.bN, this.bl + 1 - this.bw + this.bx * (i + 1) + 5, this.bm + this.bw + this.bN);
                    }
                    else {
                        graphics.drawLine(this.bl + this.bx * (i + 1) + 5, this.bm + this.bN, this.bl - this.bw + this.bx * (i + 1) + 5, this.bm + this.bw + this.bN);
                    }
                }
                for (int k = 0; k < super.l; ++k) {
                    this.bC[k][this.bz] = 0;
                    this.bD[k][this.bz] = 0;
                }
            }
            if (super.be) {
                graphics.setColor(super.h);
                for (int l = 0; l < super.l; ++l) {
                    final String s = this.bP[l];
                    final String s2 = this.bQ[l];
                    int n7 = 0;
                    for (int n8 = 0; n8 <= this.bz; ++n8) {
                        n7 += this.bC[l][n8];
                    }
                    if (n7 > 0) {
                        int n9 = this.bm + this.bN - n7;
                        int n10 = this.bl + this.bx * l - 5 + n + super.C.a(s, font, this.bW);
                        if (n9 - super.c < this.bm) {
                            n9 = n9 + n + 6 + super.c;
                            n10 -= n;
                        }
                        graphics.drawString(s, n10, n9);
                    }
                    int n11 = 0;
                    for (int n12 = 0; n12 <= this.bz; ++n12) {
                        n11 += this.bD[l][n12];
                    }
                    if (n11 < 0) {
                        int n13 = this.bm + this.bN - n11 + n + 3 + super.c;
                        final int n14 = this.bl + this.bx * l - 5 + super.C.a(s2, font, this.bW);
                        if (n13 > this.bm + this.bv) {
                            n13 = n13 - 6 - super.c;
                        }
                        graphics.drawString(s2, n14, n13);
                    }
                }
            }
            graphics.setColor(super.E);
            if (super.Z) {
                graphics.drawLine(this.bl - this.bw, this.bm + this.bN + this.bw, this.bl + this.bu - this.bw, this.bm + this.bN + this.bw);
                graphics.drawLine(this.bl - this.bw, this.bm - 1 + this.bN + this.bw, this.bl + this.bu - this.bw, this.bm - 1 + this.bN + this.bw);
                graphics.drawLine(this.bl + this.bu, this.bm + this.bN, this.bl + this.bu - this.bw, this.bm + this.bw + this.bN);
                graphics.drawLine(this.bl - 1 + this.bu, this.bm + this.bN, this.bl - 1 + this.bu - this.bw, this.bm + this.bw + this.bN);
                return;
            }
            graphics.drawLine(this.bl - this.bw, this.bm + this.bN + this.bw, this.bl + this.bu - this.bw, this.bm + this.bN + this.bw);
            graphics.drawLine(this.bl + this.bu, this.bm + this.bN, this.bl + this.bu - this.bw, this.bm + this.bw + this.bN);
        }
        else {
            if (super.bh.compareTo(b("\fS\"{\u000erb\bf\u000b-C*C\u000e/C")) == 0) {
                graphics.setColor(super.D);
                for (int n15 = 0; n15 < super.k.a.size(); ++n15) {
                    final p p3 = super.k.a.elementAt(n15);
                    for (int n16 = 0; n16 < super.k.c; ++n16) {
                        if (o == p3.a[n16]) {
                            graphics.fillRect(p3.a[n16].t, p3.a[n16].u, this.bR * 2, this.bR * 2);
                            break;
                        }
                    }
                }
                return;
            }
            for (int n17 = 0; n17 < super.l; ++n17) {
                final int n18 = this.bl + 2 + this.bT + this.bT / 2 + this.bV * n17 + this.bT * n17 - this.bw;
                for (int n19 = 0; n19 < this.bz; ++n19) {
                    final p p4 = super.k.a.elementAt(n19);
                    final Color c2 = p4.c;
                    if (this.bC[n17][n19] != 0 || this.bD[n17][n19] != 0) {
                        int abs2;
                        int n20;
                        boolean b3;
                        if (this.bC[n17][n19] != 0 && this.bD[n17][n19] == 0) {
                            abs2 = this.bC[n17][n19];
                            n20 = this.bm + this.bN - abs2 + n + this.bo;
                            b3 = false;
                        }
                        else {
                            abs2 = Math.abs(this.bD[n17][n19]);
                            n20 = this.bm + this.bN + n + this.bo;
                            b3 = true;
                        }
                        final int n21 = n18 + this.bW * n19 + this.bU * n19;
                        final int[] array9 = { n21 + this.bW, n21 + this.bW + n, n21 + this.bW + n, n21 + this.bW };
                        final int[] array10 = { n20, n20 - n, this.bm + this.bN + this.bo, this.bm + this.bN + n + this.bo };
                        final int[] array11 = { n21 + n, n21, n21 + this.bW, n21 + this.bW + n };
                        final int[] array12 = { n20 - n, n20, n20, n20 - n };
                        if (b3) {
                            final int[] array13 = array10;
                            final int n22 = 2;
                            array13[n22] += abs2;
                            final int[] array14 = array10;
                            final int n23 = 3;
                            array14[n23] += abs2;
                        }
                        if (p4.a[n17] == o) {
                            graphics.setColor(super.D);
                            graphics.fillRect(n21, n20, this.bW, abs2);
                            graphics.setColor(super.D.darker());
                            graphics.fillPolygon(array9, array10, 4);
                            if (!b3 || (b3 && this.bA[n17] == 0.0)) {
                                graphics.setColor(super.D.brighter());
                                graphics.fillPolygon(array11, array12, 4);
                            }
                        }
                        else {
                            Color g2;
                            if (p4.a[n17].g != null) {
                                g2 = p4.a[n17].g;
                            }
                            else {
                                g2 = c2;
                            }
                            graphics.setColor(g2);
                            graphics.fillRect(n21, n20, this.bW, abs2);
                            graphics.setColor(g2.darker());
                            graphics.fillPolygon(array9, array10, 4);
                            if (!b3 || (b3 && this.bA[n17] == 0.0 && !b)) {
                                graphics.setColor(g2.brighter());
                                graphics.fillPolygon(array11, array12, 4);
                            }
                        }
                        final int[] array15 = this.bC[n17];
                        final int bz3 = this.bz;
                        array15[bz3] += this.bC[n17][n19];
                        final int[] array16 = this.bD[n17];
                        final int bz4 = this.bz;
                        array16[bz4] += this.bD[n17][n19];
                    }
                }
                graphics.setColor(super.E);
                if (n17 != super.l - 1) {
                    final int n24 = n18 - 1 + this.bV;
                    if (super.Z) {
                        graphics.drawLine(n24 + this.bw, this.bm + this.bN, n24, this.bm + this.bw + this.bN);
                        graphics.drawLine(n24 + this.bw + 1, this.bm + this.bN, n24 + 1, this.bm + this.bw + this.bN);
                    }
                    else {
                        graphics.drawLine(n24 + this.bw, this.bm + this.bN, n24, this.bm + this.bw + this.bN);
                    }
                }
                for (int n25 = 0; n25 < super.l; ++n25) {
                    this.bC[n25][this.bz] = 0;
                    this.bD[n25][this.bz] = 0;
                }
            }
            if (super.be) {
                for (int n26 = 0; n26 < super.l; ++n26) {
                    final int n27 = this.bl + 2 + this.bT + this.bT / 2 + this.bV * n26 + this.bT * n26 - this.bw;
                    for (int n28 = 0; n28 < this.bz; ++n28) {
                        final int n29 = n27 + this.bW * n28 + this.bU * n28;
                        if (this.bC[n26][n28] != 0 || this.bD[n26][n28] != 0) {
                            int n30;
                            if (this.bC[n26][n28] != 0 && this.bD[n26][n28] == 0) {
                                n30 = this.bm + this.bN - this.bC[n26][n28] + n + this.bo;
                            }
                            else {
                                Math.abs(this.bD[n26][n28]);
                                n30 = this.bm + this.bN + n + this.bo;
                            }
                            final String m = super.k.a.elementAt(n28).a[n26].k;
                            int n31 = n30 - 3;
                            int n32 = n29 + super.C.a(m, font, this.bW);
                            if (n31 - super.c < this.bm) {
                                n31 = n31 + n + 6 + super.c;
                                n32 -= n;
                            }
                            graphics.setColor(super.h);
                            graphics.drawString(m, n32, n31);
                        }
                    }
                }
            }
            graphics.setColor(super.E);
            if (super.Z) {
                graphics.drawLine(this.bl - this.bw, this.bm + this.bN + this.bw, this.bl + this.bu - this.bw, this.bm + this.bN + this.bw);
                graphics.drawLine(this.bl - this.bw, this.bm - 1 + this.bN + this.bw, this.bl + this.bu - this.bw, this.bm - 1 + this.bN + this.bw);
                graphics.drawLine(this.bl + this.bu, this.bm + this.bN, this.bl + this.bu - this.bw, this.bm + this.bw + this.bN);
                graphics.drawLine(this.bl - 1 + this.bu, this.bm + this.bN, this.bl - 1 + this.bu - this.bw, this.bm + this.bw + this.bN);
                return;
            }
            graphics.drawLine(this.bl - this.bw, this.bm + this.bN + this.bw, this.bl + this.bu - this.bw, this.bm + this.bN + this.bw);
            graphics.drawLine(this.bl + this.bu, this.bm + this.bN, this.bl + this.bu - this.bw, this.bm + this.bw + this.bN);
        }
    }
    
    public o a(final int n, final int n2) {
        for (int i = 0; i < this.bz; ++i) {
            final p p2 = super.k.a.elementAt(i);
            for (int j = 0; j < super.l; ++j) {
                if (p2.a[j] != null && n >= p2.a[j].t && n <= p2.a[j].v && n2 >= p2.a[j].u && n2 <= p2.a[j].w) {
                    return p2.a[j];
                }
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
                graphics.drawString(super.N, this.bl - this.bw + super.C.a(super.N, font, this.bu), this.bm + this.bv + this.bw + super.c + 10 + super.M);
            }
            else {
                graphics.drawString(super.N, this.bl - this.bw + super.C.a(super.N, font, this.bu), this.bm + super.b);
            }
        }
        if (super.O != null) {
            super.C.a(this.bn + 5, this.bm + this.bw, graphics, super.O, super.c + 2, this.bv);
        }
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
                    c2 = 'A';
                    break;
                }
                case 1: {
                    c2 = '&';
                    break;
                }
                case 2: {
                    c2 = 'N';
                    break;
                }
                case 3: {
                    c2 = '\u000f';
                    break;
                }
                default: {
                    c2 = 'g';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
