// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Graphics;
import java.text.NumberFormat;
import java.awt.Font;
import java.awt.Color;

public class m extends b
{
    private int bl;
    private int bm;
    private int bn;
    private int bo;
    private int bp;
    private int bq;
    private int br;
    private int bs;
    private double bt;
    private double bu;
    private double bv;
    private double bw;
    private boolean bx;
    private boolean by;
    private int bz;
    private int bA;
    private int bB;
    private int bC;
    private int bD;
    private int bE;
    private String bF;
    private String bG;
    private String bH;
    private String bI;
    private String bJ;
    private String bK;
    private String bL;
    private String[] bM;
    private String[] bN;
    private int bO;
    private int bP;
    private int bQ;
    private int bR;
    private int bS;
    private int bT;
    private double bU;
    private double bV;
    private double bW;
    private int bX;
    
    public m(final int bl, final int n, final int a, final int b, final Color g, final Color h, final int c) {
        this.bx = false;
        this.by = false;
        this.bB = 4;
        this.bE = 1;
        this.bO = 1;
        this.bP = 1;
        this.bQ = -1;
        this.bR = -1;
        super.a = a;
        super.b = b;
        super.g = g;
        super.h = h;
        this.bl = bl;
        this.bm = n + 10;
        super.c = c;
        this.br = super.a - 10;
        this.bs = super.b - 30;
        super.bh = b(":\u001eyE\u0005\u00163OT");
    }
    
    public boolean a(final int l, final n k, final String s, final String s2) {
        super.l = l;
        super.k = k;
        super.m = super.k.a.size();
        final double[] array = new double[11];
        final double[] array2 = new double[11];
        final Font font = new Font(super.f, 0, super.c);
        if (super.N != null) {
            this.bs -= 5 + (super.c + 2);
        }
        if (super.O != null) {
            this.br -= 15 + (super.c + 2) / 2;
            if (super.ba) {
                this.bl += 15 + (super.c + 2) / 2;
            }
        }
        if (!super.t || !super.v) {
            for (int i = 0; i < super.k.a.size(); ++i) {
                final p p4 = super.k.a.elementAt(i);
                for (int j = 0; j < super.k.c; ++j) {
                    if (p4.a[j] != null && !p4.a[j].s) {
                        if (!this.by) {
                            this.by = true;
                            this.bv = p4.a[j].a;
                            this.bw = this.bv;
                        }
                        else {
                            if (p4.a[j].a > this.bv) {
                                this.bv = p4.a[j].a;
                            }
                            if (p4.a[j].a < this.bw) {
                                this.bw = p4.a[j].a;
                            }
                        }
                    }
                }
            }
            if (!this.by || (this.bv == 0.0 && this.bw == 0.0)) {
                this.bv = 5.0;
                this.bw = 0.0;
            }
        }
        if (super.t) {
            this.bw = super.n;
        }
        if (super.v) {
            this.bv = super.o;
        }
        if (!super.u || !super.w) {
            this.by = false;
            for (int n = 0; n < super.k.a.size(); ++n) {
                final p p5 = super.k.a.elementAt(n);
                for (int n2 = 0; n2 < super.k.c; ++n2) {
                    if (p5.a[n2] != null && !p5.a[n2].s) {
                        if (!this.by) {
                            this.by = true;
                            this.bt = p5.a[n2].b;
                            this.bu = this.bt;
                        }
                        else {
                            if (p5.a[n2].b > this.bt) {
                                this.bt = p5.a[n2].b;
                            }
                            if (p5.a[n2].b < this.bu) {
                                this.bu = p5.a[n2].b;
                            }
                        }
                    }
                }
            }
            if (!this.by || (this.bt == 0.0 && this.bu == 0.0)) {
                this.bt = 5.0;
                this.bu = 0.0;
            }
        }
        if (super.u) {
            this.bu = super.p;
        }
        if (super.w) {
            this.bt = super.q;
        }
        if (super.bh.compareTo(b(" 2HD\b\u0007")) == 0) {
            for (int n3 = 0; n3 < super.k.a.size(); ++n3) {
                final p p6 = super.k.a.elementAt(n3);
                for (int n4 = 0; n4 < super.k.c; ++n4) {
                    if (p6.a[n4] != null && !p6.a[n4].s && Math.abs(p6.a[n4].c) > this.bW) {
                        this.bW = Math.abs(p6.a[n4].c);
                    }
                }
            }
            double bt = this.bt;
            double bu = this.bu;
            double bv = this.bv;
            double bw = this.bw;
            final double n5 = (this.bt - this.bu) * 0.25;
            final double n6 = (this.bv - this.bw) * 0.25;
            for (int n7 = 0; n7 < super.k.a.size(); ++n7) {
                final p p7 = super.k.a.elementAt(n7);
                for (int n8 = 0; n8 < super.k.c; ++n8) {
                    if (p7.a[n8] != null && !p7.a[n8].s) {
                        final double a = p7.a[n8].a;
                        final double b = p7.a[n8].b;
                        final double n9 = Math.abs(p7.a[n8].c) / this.bW;
                        if (b + n5 * n9 > bt) {
                            bt = b + n5 * n9;
                        }
                        if (b - n5 * n9 < bu) {
                            bu = b - n5 * n9;
                        }
                        if (a + n6 * n9 > bv) {
                            bv = a + n6 * n9;
                        }
                        if (a - n6 * n9 < bw) {
                            bw = a - n6 * n9;
                        }
                    }
                }
            }
            this.bt = bt;
            this.bu = bu;
            this.bv = bv;
            this.bw = bw;
            this.bX = (int)(Math.min(this.br, this.bs) * 0.25);
        }
        final double[] a2 = super.C.a(this.bv, this.bw, super.t, super.v, super.R, super.S);
        this.bv = a2[0];
        this.bw = a2[1];
        this.bV = a2[2];
        this.bP = (int)a2[3];
        final int n10 = (int)a2[4];
        this.bM = new String[this.bP + 1];
        final double[] a3 = super.C.a(this.bt, this.bu, super.u, super.w, super.R, super.S);
        this.bt = a3[0];
        this.bu = a3[1];
        this.bU = a3[2];
        this.bO = (int)a3[3];
        final int n11 = (int)a3[4];
        this.bN = new String[this.bO + 1];
        for (int br = 0; br <= this.bP; ++br) {
            array2[br] = super.C.e(this.bw, super.C.d(this.bV, this.bP - br));
            if (array2[br] == 0.0) {
                this.bR = br;
            }
        }
        for (int bq = 0; bq <= this.bO; ++bq) {
            array[bq] = super.C.e(this.bu, super.C.d(this.bU, this.bO - bq));
            if (array[bq] == 0.0) {
                this.bQ = bq;
            }
        }
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(n10);
        instance.setMinimumFractionDigits(n10);
        try {
            for (int n12 = 0; n12 <= this.bP; ++n12) {
                this.bM[n12] = String.valueOf(s2) + instance.format(array2[n12]) + s;
            }
        }
        catch (Exception ex) {}
        instance.setMaximumFractionDigits(n11);
        instance.setMinimumFractionDigits(n11);
        try {
            for (int n13 = 0; n13 <= this.bO; ++n13) {
                this.bN[n13] = String.valueOf(s2) + instance.format(array[n13]) + s;
            }
        }
        catch (Exception ex2) {}
        if (!super.bd) {
            this.bC = Math.max(super.C.c(String.valueOf(s2) + this.bM[0] + s, font), super.C.c(String.valueOf(s2) + this.bM[this.bP] + s, font));
            if (super.k.b[0] != null && super.C.c(super.k.b[0], font) > this.bD) {
                this.bD = super.C.c(super.k.b[0], font);
            }
            if (!super.ba) {
                this.bl += this.bD / 2;
            }
            else {
                this.bl = this.bl + this.bD / 2 + this.bC;
            }
        }
        this.br -= super.C.c(String.valueOf(s2) + this.bN[0] + s, font) / 2;
        if (super.H > 0 && super.I) {
            int n14 = 0;
            for (int n15 = 0; n15 < super.H; ++n15) {
                final q q = super.J[n15];
                if (q.e != null) {
                    final int c = super.C.c(q.e, font);
                    if (c > n14) {
                        n14 = c;
                    }
                }
            }
            if (!super.ba) {
                this.bl = this.bl + n14 + 3;
                this.br = this.br - n14 - 3;
            }
            else {
                this.br = this.br - n14 - 3;
            }
        }
        this.br = this.br - this.bD / 2 - this.bC;
        this.bA = this.bs / this.bP;
        this.bs = this.bA * this.bP;
        this.bz = this.br / this.bO;
        this.br = this.bz * this.bO;
        this.bq = this.bz;
        this.bo = this.br;
        this.bn = this.bs;
        for (int n16 = 0; n16 < super.k.a.size(); ++n16) {
            final p p8 = super.k.a.elementAt(n16);
            for (int n17 = 0; n17 < super.k.c; ++n17) {
                if (p8.a[n17] != null) {
                    if (!p8.a[n17].s) {
                        p8.a[n17].n = (int)(this.bs - (p8.a[n17].a - this.bw) / (this.bv - this.bw) * this.bs);
                        p8.a[n17].o = (int)((p8.a[n17].b - this.bu) / (this.bt - this.bu) * this.br);
                        if (super.bh.compareTo(b(" 2HD\b\u0007")) == 0 && this.bW > 0.0) {
                            p8.a[n17].p = (int)(p8.a[n17].c / this.bW * this.bX);
                        }
                    }
                    else {
                        p8.a[n17].n = -1;
                        p8.a[n17].o = -1;
                    }
                }
            }
        }
        if (this.bv >= 0.0 && this.bw < 0.0) {
            this.bT = (int)(this.bs - (0.0 - this.bw) / (this.bv - this.bw) * this.bs);
        }
        if (this.bt >= 0.0 && this.bu < 0.0) {
            this.bS = (int)((0.0 - this.bu) / (this.bt - this.bu) * this.br);
        }
        return true;
    }
    
    public void a(final Graphics graphics) {
        final Font font = new Font(super.f, 0, super.c);
        graphics.setFont(font);
        graphics.setColor(super.E);
        if (super.Z) {
            if (!super.C.b(super.bj, b(",(DC"))) {
                graphics.drawLine(this.bl, this.bm, this.bl + this.bo, this.bm);
                graphics.drawLine(this.bl, this.bm + 1, this.bl + this.bo, this.bm + 1);
                graphics.drawLine(this.bl, this.bm, this.bl, this.bm + this.bs);
                graphics.drawLine(this.bl + 1, this.bm, this.bl + 1, this.bm + this.bs);
            }
            if (super.C.b(super.bj, b("#+F")) || super.C.b(super.bj, b(";\bDJ\u001d"))) {
                for (int i = 1; i < this.bP; ++i) {
                    graphics.drawLine(this.bl, this.bm + this.bA * i, this.bl + this.bo, this.bm + this.bA * i);
                    graphics.drawLine(this.bl, this.bm + 1 + this.bA * i, this.bl + this.bo, this.bm + 1 + this.bA * i);
                }
            }
            if (super.C.b(super.bj, b("#+F")) || super.C.b(super.bj, b(":\bDJ\u001d"))) {
                for (int j = 1; j <= this.bO; ++j) {
                    if (super.W == 1 || j % super.W == 0) {
                        graphics.drawLine(this.bl + this.bq * j, this.bm, this.bl + this.bq * j, this.bm + this.bs);
                        graphics.drawLine(this.bl + 1 + this.bq * j, this.bm, this.bl + 1 + this.bq * j, this.bm + this.bs);
                    }
                }
            }
        }
        else {
            if (!super.C.b(super.bj, b(",(DC"))) {
                graphics.drawLine(this.bl, this.bm, this.bl + this.bo, this.bm);
                graphics.drawLine(this.bl, this.bm, this.bl, this.bm + this.bs);
            }
            if (super.C.b(super.bj, b("#+F")) || super.C.b(super.bj, b(":\bDJ\u001d"))) {
                for (int k = 1; k <= this.bO; ++k) {
                    if (super.W == 1 || k % super.W == 0) {
                        graphics.drawLine(this.bl + this.bq * k, this.bm, this.bl + this.bq * k, this.bm + this.bs);
                    }
                }
            }
            if (super.C.b(super.bj, b("#+F")) || super.C.b(super.bj, b(";\bDJ\u001d"))) {
                for (int l = 1; l < this.bP; ++l) {
                    graphics.drawLine(this.bl, this.bm + this.bA * l, this.bl + this.bo, this.bm + this.bA * l);
                }
            }
        }
        graphics.setColor(super.h);
        if (super.Z) {
            graphics.drawLine(this.bl, this.bm + this.bs, this.bl + this.bo, this.bm + this.bs);
            graphics.drawLine(this.bl, this.bm + 1 + this.bs, this.bl + this.bo, this.bm + 1 + this.bs);
            if (!super.ba) {
                graphics.drawLine(this.bl + this.bo, this.bm, this.bl + this.bo, this.bm + this.bs);
                graphics.drawLine(this.bl + 1 + this.bo, this.bm, this.bl + 1 + this.bo, this.bm + this.bs + 1);
            }
            else {
                graphics.drawLine(this.bl, this.bm, this.bl, this.bm + this.bs);
                graphics.drawLine(this.bl + 1, this.bm, this.bl + 1, this.bm + this.bs + 1);
            }
        }
        else {
            graphics.drawLine(this.bl, this.bm + this.bs, this.bl + this.bo, this.bm + this.bs);
            if (!super.ba) {
                graphics.drawLine(this.bl + this.bo, this.bm, this.bl + this.bo, this.bm + this.bs);
            }
            else {
                graphics.drawLine(this.bl, this.bm, this.bl, this.bm + this.bs);
            }
        }
        if (super.Z) {
            for (int n = 0; n <= this.bO; ++n) {
                graphics.drawLine(this.bl + this.bq * n, this.bm + this.bs - 2, this.bl + this.bq * n, this.bm + this.bs + 3);
                graphics.drawLine(this.bl + 1 + this.bq * n, this.bm + this.bs - 2, this.bl + 1 + this.bq * n, this.bm + this.bs + 3);
            }
        }
        else {
            for (int n2 = 0; n2 <= this.bO; ++n2) {
                graphics.drawLine(this.bl + this.bq * n2, this.bm + this.bs - 2, this.bl + this.bq * n2, this.bm + this.bs + 2);
            }
        }
        if (super.Z) {
            for (int n3 = 0; n3 <= this.bP; ++n3) {
                if (!super.ba) {
                    graphics.drawLine(this.bl + this.bo - 2, this.bm + this.bA * n3, this.bl + this.bo + 3, this.bm + this.bA * n3);
                    graphics.drawLine(this.bl + this.bo - 2, this.bm + 1 + this.bA * n3, this.bl + this.bo + 3, this.bm + 1 + this.bA * n3);
                }
                else {
                    graphics.drawLine(this.bl - 2, this.bm + this.bA * n3, this.bl + 3, this.bm + this.bA * n3);
                    graphics.drawLine(this.bl - 2, this.bm + 1 + this.bA * n3, this.bl + 3, this.bm + 1 + this.bA * n3);
                }
            }
        }
        else {
            for (int n4 = 0; n4 <= this.bP; ++n4) {
                if (!super.ba) {
                    graphics.drawLine(this.bl + this.bo - 2, this.bm + this.bA * n4, this.bl + this.bo + 2, this.bm + this.bA * n4);
                }
                else {
                    graphics.drawLine(this.bl - 2, this.bm + this.bA * n4, this.bl + 2, this.bm + this.bA * n4);
                }
            }
        }
        if (this.bv <= 0.0 || this.bw < 0.0) {
            this.bT += this.bm;
            if (super.Z) {
                graphics.drawLine(this.bl, this.bT, this.bl + this.bo, this.bT);
                graphics.drawLine(this.bl, this.bT + 1, this.bl + this.bo, this.bT + 1);
                for (int n5 = 0; n5 <= this.bO; ++n5) {
                    graphics.drawLine(this.bl + this.bq * n5, this.bT - 2, this.bl + this.bq * n5, this.bT + 3);
                    graphics.drawLine(this.bl + 1 + this.bq * n5, this.bT - 2, this.bl + 1 + this.bq * n5, this.bT + 3);
                }
            }
            else {
                graphics.drawLine(this.bl, this.bT, this.bl + this.bo, this.bT);
                for (int n6 = 0; n6 <= this.bO; ++n6) {
                    graphics.drawLine(this.bl + this.bq * n6, this.bT - 2, this.bl + this.bq * n6, this.bT + 2);
                }
            }
        }
        if (this.bt <= 0.0 || this.bu < 0.0) {
            this.bS += this.bl;
            if (super.Z) {
                graphics.drawLine(this.bS, this.bm, this.bS, this.bm + this.bs);
                graphics.drawLine(this.bS + 1, this.bm, this.bS + 1, this.bm + this.bs);
                for (int n7 = 0; n7 <= this.bP; ++n7) {
                    graphics.drawLine(this.bS - 2, this.bm + this.bA * n7, this.bS + 3, this.bm + this.bA * n7);
                    graphics.drawLine(this.bS - 2, this.bm + 1 + this.bA * n7, this.bS + 3, this.bm + 1 + this.bA * n7);
                }
            }
            else {
                graphics.drawLine(this.bS, this.bm, this.bS, this.bm + this.bs);
                for (int n8 = 0; n8 <= this.bP; ++n8) {
                    graphics.drawLine(this.bS - 2, this.bm + this.bA * n8, this.bS + 2, this.bm + this.bA * n8);
                }
            }
        }
        if (!super.bd) {
            for (int n9 = 0; n9 <= this.bO; ++n9) {
                graphics.drawString(this.bN[this.bO - n9], this.bl + this.bz * n9 - super.C.a(this.bN[this.bO - n9], font), this.bm + this.bn + 5 + super.c);
            }
        }
        if (!super.ba) {
            for (int n10 = 0; n10 <= this.bP; ++n10) {
                graphics.drawString(this.bM[n10], this.bl + 4 + this.bo, this.bm + this.bA * n10 + super.c / 2);
            }
        }
        else {
            for (int n11 = 0; n11 <= this.bP; ++n11) {
                graphics.drawString(this.bM[n11], this.bl - 4 - super.C.c(this.bM[n11], font), this.bm + this.bA * n11 + super.c / 2);
            }
        }
        if (super.bh.compareTo(b(":\u001eyE\u0005\u00163OT")) == 0) {
            for (int n12 = 0; n12 < super.k.a.size(); ++n12) {
                final p p = super.k.a.elementAt(n12);
                graphics.setColor(p.c);
                for (int n13 = 0; n13 < super.k.c; ++n13) {
                    if (p.a[n13] != null && !p.a[n13].s) {
                        final int t = this.bl + p.a[n13].o - this.bB;
                        final int u = this.bm + p.a[n13].n - this.bB;
                        if (p.a[n13].g != null) {
                            graphics.setColor(p.a[n13].g);
                            graphics.fillRect(t, u, this.bB * 2, this.bB * 2);
                            graphics.setColor(p.c);
                        }
                        else {
                            graphics.fillRect(t, u, this.bB * 2, this.bB * 2);
                        }
                        p.a[n13].t = t;
                        p.a[n13].u = u;
                        p.a[n13].v = t + this.bB * 2;
                        p.a[n13].w = u + this.bB * 2;
                    }
                }
            }
        }
        else {
            for (int n14 = 0; n14 < super.k.a.size(); ++n14) {
                final p p2 = super.k.a.elementAt(n14);
                graphics.setColor(p2.c);
                for (int n15 = 0; n15 < super.k.c; ++n15) {
                    if (p2.a[n15] != null && !p2.a[n15].s) {
                        final int p3 = p2.a[n15].p;
                        final int t2 = this.bl + p2.a[n15].o - p3 / 2;
                        final int u2 = this.bm + p2.a[n15].n - p3 / 2;
                        if (p2.a[n15].g != null) {
                            graphics.setColor(p2.a[n15].g);
                            graphics.fillOval(t2, u2, p3, p3);
                            graphics.setColor(p2.c);
                        }
                        else {
                            graphics.fillOval(t2, u2, p3, p3);
                        }
                        p2.a[n15].t = t2;
                        p2.a[n15].u = u2;
                        p2.a[n15].v = t2 + p3;
                        p2.a[n15].w = u2 + p3;
                    }
                }
            }
        }
        if (super.be) {
            final int n16 = 3;
            graphics.setColor(super.h);
            if (super.bh.compareTo(b(":\u001eyE\u0005\u00163OT")) == 0) {
                for (int n17 = 0; n17 < super.k.a.size(); ++n17) {
                    final p p4 = super.k.a.elementAt(n17);
                    for (int n18 = 0; n18 < super.k.c; ++n18) {
                        if (p4.a[n18] != null && !p4.a[n18].s) {
                            final String m = p4.a[n18].k;
                            final int c = super.C.c(m, font);
                            int n19 = this.bl + p4.a[n18].o - c / 2;
                            int n20 = this.bm + p4.a[n18].n - this.bB - n16;
                            if (n20 - super.c < this.bm) {
                                n20 = n20 + n16 * 2 + super.c;
                            }
                            else if (n20 > this.bm + this.bs) {
                                n20 = n20 - n16 * 2 - super.c;
                            }
                            if (n19 < this.bl) {
                                n19 = n19 + c / 2 + this.bB;
                            }
                            else if (n19 + c > this.bl + this.br) {
                                n19 = n19 - c / 2 - this.bB;
                            }
                            graphics.drawString(m, n19, n20);
                        }
                    }
                }
            }
            else {
                for (int n21 = 0; n21 < super.k.a.size(); ++n21) {
                    final p p5 = super.k.a.elementAt(n21);
                    for (int n22 = 0; n22 < super.k.c; ++n22) {
                        if (p5.a[n22] != null && !p5.a[n22].s) {
                            final String k2 = p5.a[n22].k;
                            graphics.drawString(k2, this.bl + p5.a[n22].o - super.C.c(k2, font) / 2, this.bm + p5.a[n22].n - n16 - p5.a[n22].p / 2);
                        }
                    }
                }
            }
        }
        this.b(graphics);
        super.C.a(graphics, this);
        super.F = true;
    }
    
    public void a(final Graphics graphics, final o o) {
        graphics.setColor(super.D);
        for (int i = 0; i < super.k.a.size(); ++i) {
            final p p2 = super.k.a.elementAt(i);
            int j = 0;
            while (j < super.k.c) {
                if (o == p2.a[j]) {
                    if (super.bh.compareTo(b(":\u001eyE\u0005\u00163OT")) == 0) {
                        graphics.fillRect(p2.a[j].t, p2.a[j].u, this.bB * 2, this.bB * 2);
                        break;
                    }
                    final int p3 = p2.a[j].p;
                    graphics.fillOval(p2.a[j].t, p2.a[j].u, p3, p3);
                    break;
                }
                else {
                    ++j;
                }
            }
        }
    }
    
    public o a(final int n, final int n2) {
        for (int i = 0; i < super.m; ++i) {
            final p p2 = super.k.a.elementAt(i);
            int j = 0;
            while (j < super.l) {
                if (p2.a[j] != null && n >= p2.a[j].t && n <= p2.a[j].v && n2 >= p2.a[j].u && n2 <= p2.a[j].w) {
                    if ((p2.a[j].a <= super.o && p2.a[j].a >= super.n) || !super.r) {
                        return p2.a[j];
                    }
                    return null;
                }
                else {
                    ++j;
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
                graphics.drawString(super.N, this.bl + super.C.a(super.N, font, this.br), this.bm + this.bs + super.M + (super.c + 17));
            }
            else {
                graphics.drawString(super.N, this.bl + super.C.a(super.N, font, this.br), this.bm + super.b - (super.c + 2));
            }
        }
        if (super.O != null) {
            if (!super.ba) {
                super.C.a(super.a - (super.c + 2) / 2, this.bm, graphics, super.O, super.c + 2, this.bs);
                return;
            }
            super.C.a((super.c + 2) / 2, this.bm, graphics, super.O, super.c + 2, this.bs);
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
                    c2 = 'b';
                    break;
                }
                case 1: {
                    c2 = 'G';
                    break;
                }
                case 2: {
                    c2 = '*';
                    break;
                }
                case 3: {
                    c2 = '&';
                    break;
                }
                default: {
                    c2 = 'd';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
