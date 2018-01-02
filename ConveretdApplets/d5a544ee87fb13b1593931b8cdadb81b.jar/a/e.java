// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.text.NumberFormat;
import java.awt.Font;
import java.awt.Color;

public class e extends b
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
    private double bu;
    private boolean bv;
    private boolean bw;
    private int bx;
    private int by;
    private int bz;
    private int bA;
    private int bB;
    private String bC;
    private String bD;
    private String bE;
    private String bF;
    private String bG;
    private String bH;
    private String bI;
    private String[] bJ;
    private int bK;
    private int bL;
    private int bM;
    private double bN;
    private int bO;
    private int bP;
    private Color bQ;
    private Color bR;
    private String bS;
    private String bT;
    private String bU;
    private boolean bV;
    
    public e(final int bl, final int n, final int a, final int b, final Color g, final Color h, final int c) {
        this.bv = false;
        this.bw = false;
        this.by = 4;
        this.bB = 1;
        this.bK = 1;
        this.bL = -1;
        this.bO = 6;
        this.bQ = new Color(7, 166, 7);
        this.bR = new Color(213, 4, 4);
        this.bS = b("JxcH`y");
        this.bT = "";
        this.bU = "";
        this.bV = false;
        super.a = a;
        super.b = b;
        super.g = g;
        super.h = h;
        this.bl = bl;
        this.bm = n + 10;
        super.c = c;
        this.bp = super.a - 10;
        this.bq = super.b - 30;
        super.bh = b("_vaYay");
    }
    
    public boolean a(final int l, final n k, final String s, final String s2) {
        super.l = l;
        super.k = k;
        super.m = super.k.a.size();
        final double[] array = new double[11];
        if (super.bh.compareTo(b("_vaYay@fIeJxcH`yd")) == 0 || super.bh.compareTo(b("Oc`^fK~{U[s{zPho")) == 0) {
            this.bV = true;
        }
        final Font font = new Font(super.f, 0, super.c);
        if (super.N != null) {
            this.bq -= 5 + (super.c + 2);
        }
        if (super.O != null) {
            this.bp -= 15 + (super.c + 2) / 2;
            if (super.ba) {
                this.bl += 15 + (super.c + 2) / 2;
            }
        }
        if (!super.t || !super.v) {
            for (int i = 0; i < super.k.a.size(); ++i) {
                final p p4 = super.k.a.elementAt(i);
                for (int j = 0; j < super.k.c; ++j) {
                    if (p4.a[j] != null && !p4.a[j].s) {
                        if (i == 0) {
                            if (!this.bw) {
                                this.bw = true;
                                this.bs = p4.a[j].a;
                                this.bt = p4.a[j].b;
                            }
                            else {
                                if (p4.a[j].a > this.bs) {
                                    this.bs = p4.a[j].a;
                                }
                                if (p4.a[j].b < this.bt) {
                                    this.bt = p4.a[j].b;
                                }
                            }
                        }
                        else if (!this.bw) {
                            this.bw = true;
                            this.bs = p4.a[j].a;
                            this.bt = this.bs;
                        }
                        else {
                            if (p4.a[j].a > this.bs) {
                                this.bs = p4.a[j].a;
                            }
                            if (p4.a[j].a < this.bt) {
                                this.bt = p4.a[j].a;
                            }
                        }
                    }
                }
            }
            if (!this.bw || (this.bs == 0.0 && this.bt == 0.0)) {
                this.bs = 5.0;
                this.bt = 0.0;
            }
            if (this.bt != 0.0) {
                final int n = (int)(this.bt - (this.bs - this.bt) * 0.1);
                this.bt = n;
                super.t = true;
                super.n = n;
            }
        }
        if (super.t) {
            this.bt = super.n;
        }
        if (super.v) {
            this.bs = super.o;
        }
        if (this.bV) {
            if (super.k.a.size() > 0) {
                final p p5 = super.k.a.elementAt(0);
                for (int n2 = 0; n2 < super.k.c; ++n2) {
                    if (p5.a[n2] != null && !p5.a[n2].s && p5.a[n2].e > this.bu) {
                        this.bu = p5.a[n2].e;
                    }
                }
            }
            int n3 = 0;
            final int e = super.C.e(this.bu);
            this.bu = Math.ceil(this.bu / Math.pow(10.0, e - 1)) * Math.pow(10.0, e - 1);
            final int e2 = super.C.e(this.bu);
            if (e2 >= 4) {
                if (e2 < 7) {
                    this.bS = b("JxcH`y7fS-h\u007f`H~}ykN7");
                    n3 = 3;
                }
                else if (e2 < 10) {
                    this.bS = b("JxcH`y7fS-q~cQdsy|\u0007");
                    n3 = 6;
                }
                else {
                    this.bS = b("JxcH`y7fS-~~cQdsy|\u0007");
                    n3 = 9;
                }
            }
            this.bT = String.valueOf(this.bu / Math.pow(10.0, n3));
            this.bU = String.valueOf(this.bu / (2.0 * Math.pow(10.0, n3)));
        }
        final double[] a = super.C.a(this.bs, this.bt, super.t, super.v, super.R, super.S);
        this.bs = a[0];
        this.bt = a[1];
        this.bN = a[2];
        this.bK = (int)a[3];
        final int n4 = (int)a[4];
        this.bJ = new String[this.bK + 1];
        for (int bl = 0; bl <= this.bK; ++bl) {
            array[bl] = super.C.e(this.bt, super.C.d(this.bN, this.bK - bl));
            if (array[bl] == 0.0) {
                this.bL = bl;
            }
        }
        final NumberFormat d = super.C.d(super.bg);
        d.setMaximumFractionDigits(n4);
        d.setMinimumFractionDigits(n4);
        try {
            for (int n5 = 0; n5 <= this.bK; ++n5) {
                this.bJ[n5] = String.valueOf(s2) + d.format(array[n5]) + s;
            }
        }
        catch (Exception ex) {}
        if (!super.bd) {
            this.bz = Math.max(super.C.c(String.valueOf(s2) + this.bJ[0] + s, font), super.C.c(String.valueOf(s2) + this.bJ[this.bK] + s, font));
            if (super.k.b[0] != null && super.C.c(super.k.b[0], font) > this.bA) {
                this.bA = super.C.c(super.k.b[0], font);
            }
            if (!super.ba) {
                this.bl += this.bA / 2;
            }
            else {
                this.bl = this.bl + this.bA / 2 + this.bz;
            }
        }
        if (super.H > 0 && super.I) {
            int n6 = 0;
            for (int n7 = 0; n7 < super.H; ++n7) {
                final q q = super.J[n7];
                if (q.e != null) {
                    final int c = super.C.c(q.e, font);
                    if (c > n6) {
                        n6 = c;
                    }
                }
            }
            if (!super.ba) {
                this.bl = this.bl + n6 + 3;
                this.bp = this.bp - n6 - 3;
            }
            else {
                this.bp = this.bp - n6 - 3;
            }
        }
        if (this.bV) {
            this.br = (int)(this.bq * 0.2);
            this.bP = super.c + super.c;
            this.bq -= this.br + this.bP;
        }
        this.bp = this.bp - this.bA / 2 - this.bz;
        this.bo = this.bp / (super.l + 1);
        this.bn = this.bo * (super.l + 1);
        if (!super.L) {
            final int a2 = super.C.a(super.k.b, super.l, this.bo, this.bl, super.a, font);
            if (a2 == 2) {
                super.K = true;
            }
            else if (a2 == 3) {
                super.L = true;
                super.M = super.C.a(super.k.b, super.l, font);
                this.bq -= super.M;
            }
            if (super.K) {
                this.bq -= super.c + 5;
                if (super.N != null) {
                    this.bq -= super.c + 2;
                }
            }
        }
        this.bx = this.bq / this.bK;
        this.bq = this.bx * this.bK;
        for (int n8 = 0; n8 < super.k.a.size(); ++n8) {
            final p p6 = super.k.a.elementAt(n8);
            for (int n9 = 0; n9 < super.k.c; ++n9) {
                if (p6.a[n9] != null) {
                    if (!p6.a[n9].s && this.bs - this.bt != 0.0) {
                        if (n8 == 0) {
                            p6.a[n9].n = (int)(this.bq - (p6.a[n9].a - this.bt) / (this.bs - this.bt) * this.bq);
                            p6.a[n9].o = (int)(this.bq - (p6.a[n9].b - this.bt) / (this.bs - this.bt) * this.bq);
                            p6.a[n9].p = (int)(this.bq - (p6.a[n9].c - this.bt) / (this.bs - this.bt) * this.bq);
                            p6.a[n9].q = (int)(this.bq - (p6.a[n9].d - this.bt) / (this.bs - this.bt) * this.bq);
                            p6.a[n9].r = (int)(p6.a[n9].e / this.bu * this.br);
                        }
                        else {
                            p6.a[n9].n = (int)(this.bq - (p6.a[n9].a - this.bt) / (this.bs - this.bt) * this.bq);
                        }
                    }
                    else {
                        p6.a[n9].n = -1;
                    }
                }
            }
        }
        if (this.bs >= 0.0 && this.bt < 0.0) {
            this.bM = (int)(this.bq - (0.0 - this.bt) / (this.bs - this.bt) * this.bq);
        }
        return true;
    }
    
    public void a(final Graphics graphics) {
        int n = 1;
        if (!super.bi) {
            super.bj = b("EXaQt");
        }
        final Font font = new Font(super.f, 0, super.c);
        graphics.setFont(font);
        if (super.H > 0) {
            if (!super.bi) {
                super.bj = b("RxaX");
            }
            for (int i = 0; i < super.H; ++i) {
                final q q = super.J[i];
                if (q != null) {
                    if (q.a(this.bt, this.bs)) {
                        final int n2 = (int)(this.bq - (q.a() - this.bt) / (this.bs - this.bt) * this.bq);
                        graphics.setColor(q.d);
                        graphics.drawLine(this.bl, this.bm + n2, this.bl + this.bn, this.bm + n2);
                        if (q.e != null) {
                            int n3;
                            if (!super.ba) {
                                n3 = this.bl - super.C.c(q.e, font) - 3;
                            }
                            else {
                                n3 = this.bl + this.bn + 7;
                            }
                            final int n4 = this.bm + n2 + super.C.b(q.e, font) / 2 - 1;
                            graphics.setColor(super.h);
                            graphics.drawString(q.e, n3, n4);
                        }
                    }
                    else if (q.b(this.bt, this.bs)) {
                        final double[] c = q.c(this.bt, this.bs);
                        final double n5 = c[0];
                        final double n6 = c[1];
                        final int n7 = (int)(this.bq - (n5 - this.bt) / (this.bs - this.bt) * this.bq);
                        final int n8 = (int)(this.bq - (n6 - this.bt) / (this.bs - this.bt) * this.bq);
                        final int n9 = n7 - n8;
                        if (n9 > 0) {
                            graphics.setColor(q.d);
                            graphics.fillRect(this.bl, this.bm + n8, this.bn, n9);
                        }
                        if (q.e != null) {
                            int n10;
                            if (!super.ba) {
                                n10 = this.bl - super.C.c(q.e, font) - 3;
                            }
                            else {
                                n10 = this.bl + this.bn + 7;
                            }
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
            if (!super.C.b(super.bj, b("RxaX"))) {
                graphics.drawLine(this.bl, this.bm, this.bl + this.bn, this.bm);
                graphics.drawLine(this.bl, this.bm + 1, this.bl + this.bn, this.bm + 1);
                graphics.drawLine(this.bl, this.bm, this.bl, this.bm + this.bq);
                graphics.drawLine(this.bl + 1, this.bm, this.bl + 1, this.bm + this.bq);
                graphics.drawLine(this.bl + this.bn, this.bm, this.bl + this.bn, this.bm + this.bq);
                graphics.drawLine(this.bl + this.bn + 1, this.bm, this.bl + this.bn + 1, this.bm + this.bq);
            }
            if (super.C.b(super.bj, b("]{c")) || super.C.b(super.bj, b("EXaQt"))) {
                for (int j = 1; j < this.bK; ++j) {
                    graphics.drawLine(this.bl, this.bm + this.bx * j, this.bl + this.bn, this.bm + this.bx * j);
                    graphics.drawLine(this.bl, this.bm + 1 + this.bx * j, this.bl + this.bn, this.bm + 1 + this.bx * j);
                }
            }
            if (super.C.b(super.bj, b("]{c")) || super.C.b(super.bj, b("DXaQt"))) {
                for (int k = 1; k <= super.l; ++k) {
                    if (super.W == 1 || k % super.W == 0) {
                        graphics.drawLine(this.bl + this.bo * k, this.bm, this.bl + this.bo * k, this.bm + this.bq);
                        graphics.drawLine(this.bl + 1 + this.bo * k, this.bm, this.bl + 1 + this.bo * k, this.bm + this.bq);
                    }
                }
            }
        }
        else {
            if (!super.C.b(super.bj, b("RxaX"))) {
                graphics.drawLine(this.bl, this.bm, this.bl + this.bn, this.bm);
                graphics.drawLine(this.bl, this.bm, this.bl, this.bm + this.bq);
                graphics.drawLine(this.bl + this.bn, this.bm, this.bl + this.bn, this.bm + this.bq);
            }
            if (super.C.b(super.bj, b("]{c")) || super.C.b(super.bj, b("DXaQt"))) {
                for (int l = 1; l <= super.l; ++l) {
                    if (super.W == 1 || l % super.W == 0) {
                        graphics.drawLine(this.bl + this.bo * l, this.bm, this.bl + this.bo * l, this.bm + this.bq);
                    }
                }
            }
            if (super.C.b(super.bj, b("]{c")) || super.C.b(super.bj, b("EXaQt"))) {
                for (int n12 = 1; n12 < this.bK; ++n12) {
                    graphics.drawLine(this.bl, this.bm + this.bx * n12, this.bl + this.bn, this.bm + this.bx * n12);
                }
            }
        }
        final int n13 = this.bO / 2;
        int n14 = 0;
        int n15 = 0;
        for (int n16 = super.k.a.size() - 1; n16 >= 0; --n16) {
            int n17 = 1;
            this.bv = true;
            final p p = super.k.a.elementAt(n16);
            final Color c2 = p.c;
            if (n16 == 0) {
                for (int n18 = 0; n18 < super.k.c; ++n18) {
                    if (p.a[n18] != null) {
                        final int t = this.bl + (n18 + 1) * this.bo;
                        final int u = this.bm + p.a[n18].n;
                        final int w = this.bm + p.a[n18].o;
                        final int n19 = this.bm + p.a[n18].p;
                        final int n20 = this.bm + p.a[n18].q;
                        final int n21 = t - n13;
                        if (p.a[n18].g != null) {
                            graphics.setColor(p.a[n18].g);
                        }
                        else {
                            graphics.setColor(c2);
                        }
                        if (!p.a[n18].s) {
                            if (super.bh.compareTo(b("_vaYay")) == 0 || super.bh.compareTo(b("_vaYay@fIeJxcH`yd")) == 0) {
                                if (n19 >= n20) {
                                    graphics.setColor(this.bQ);
                                    int n22 = n19 - n20;
                                    if (n22 == 0) {
                                        n22 = 1;
                                    }
                                    graphics.drawLine(t, u, t, n20);
                                    graphics.drawLine(t, n19, t, w);
                                    graphics.drawRect(n21, n20, this.bO, n22);
                                }
                                else {
                                    graphics.setColor(this.bR);
                                    int n23 = n20 - n19;
                                    if (n23 == 0) {
                                        n23 = 1;
                                    }
                                    graphics.drawLine(t, u, t, n19);
                                    graphics.drawLine(t, n20, t, w);
                                    graphics.fillRect(n21, n19, this.bO + 1, n23);
                                }
                            }
                            else {
                                if (n19 >= n20) {
                                    graphics.setColor(this.bQ);
                                }
                                else {
                                    graphics.setColor(this.bR);
                                }
                                if (n19 - n20 == 0) {}
                                graphics.drawLine(t, u, t, w);
                                graphics.drawLine(t - n13, n19, t, n19);
                                graphics.drawLine(t, n20, t + n13, n20);
                            }
                            if (this.bV) {
                                graphics.fillRect(n21, this.bm + this.bq + this.br + this.bP - p.a[n18].r, this.bO + 1, p.a[n18].r);
                            }
                            if (!super.F) {
                                p.a[n18].t = t;
                                p.a[n18].u = u;
                                p.a[n18].v = t + this.bO;
                                p.a[n18].w = w;
                            }
                        }
                    }
                }
            }
            else {
                for (int n24 = 0; n24 < super.k.c; ++n24) {
                    if (p.a[n24] != null) {
                        final int n25 = this.bl + (n24 + 1) * this.bo;
                        final int n26 = this.bm + p.a[n24].n;
                        if (p.a[n24].g != null) {
                            graphics.setColor(p.a[n24].g);
                        }
                        else {
                            graphics.setColor(c2);
                        }
                        if (!p.a[n24].s) {
                            if (super.G) {
                                if (n17 == 0) {
                                    super.C.a(graphics, n14, n15, n25, n26, super.T, graphics.getColor());
                                }
                                else {
                                    n17 = 0;
                                }
                            }
                            else {
                                if (!this.bv) {
                                    super.C.a(graphics, n14, n15, n25, n26, super.T, graphics.getColor());
                                }
                                this.bv = false;
                            }
                        }
                        n14 = n25;
                        n15 = n26;
                        if (!super.F) {
                            p.a[n24].t = n14 - this.by;
                            p.a[n24].u = n15 - this.by;
                            p.a[n24].v = n14 + this.by;
                            p.a[n24].w = n15 + this.by;
                        }
                    }
                    else {
                        this.bv = true;
                    }
                }
            }
        }
        graphics.setColor(super.h);
        if (super.Z) {
            graphics.drawLine(this.bl, this.bm + this.bq, this.bl + this.bn, this.bm + this.bq);
            graphics.drawLine(this.bl, this.bm + 1 + this.bq, this.bl + this.bn, this.bm + 1 + this.bq);
            if (!super.ba) {
                graphics.drawLine(this.bl + this.bn, this.bm, this.bl + this.bn, this.bm + this.bq);
                graphics.drawLine(this.bl + 1 + this.bn, this.bm, this.bl + 1 + this.bn, this.bm + this.bq + 1);
            }
            else {
                graphics.drawLine(this.bl, this.bm, this.bl, this.bm + this.bq);
                graphics.drawLine(this.bl + 1, this.bm, this.bl + 1, this.bm + this.bq + 1);
            }
        }
        else {
            graphics.drawLine(this.bl, this.bm + this.bq, this.bl + this.bn, this.bm + this.bq);
            if (!super.ba) {
                graphics.drawLine(this.bl + this.bn, this.bm, this.bl + this.bn, this.bm + this.bq);
            }
            else {
                graphics.drawLine(this.bl, this.bm, this.bl, this.bm + this.bq);
            }
        }
        if (this.bV) {
            final int n27 = this.bm + this.bq + this.br + this.bP;
            int bl;
            if (!super.ba) {
                bl = this.bl + this.bn;
            }
            else {
                bl = this.bl;
            }
            final int n28 = n27 - this.br / 2;
            final double n29 = this.bu / 2.0;
            if (super.Z) {
                graphics.drawLine(bl, n27 - this.br, bl, n27);
                graphics.drawLine(bl + 1, n27 - this.br, bl + 1, n27);
                graphics.drawLine(bl - 2, n27 - this.br, bl + 3, n27 - this.br);
                graphics.drawLine(bl - 2, n27 - this.br + 1, bl + 3, n27 - this.br + 1);
                graphics.drawLine(bl - 2, n28, bl + 3, n28);
                graphics.drawLine(bl - 2, n28 + 1, bl + 3, n28 + 1);
                graphics.drawLine(bl - 2, n27, bl + 3, n27);
                graphics.drawLine(bl - 2, n27 + 1, bl + 3, n27 + 1);
                graphics.drawLine(this.bl, n27, this.bl + this.bn, n27);
                graphics.drawLine(this.bl, n27 + 1, this.bl + this.bn, n27 + 1);
                for (int n30 = 0; n30 <= super.l + 1; ++n30) {
                    graphics.drawLine(this.bl + this.bo * n30, n27 - 2, this.bl + this.bo * n30, n27 + 3);
                    graphics.drawLine(this.bl + 1 + this.bo * n30, n27 - 2, this.bl + 1 + this.bo * n30, n27 + 3);
                }
            }
            else {
                graphics.drawLine(bl, n27 - this.br, bl, n27);
                graphics.drawLine(bl - 2, n27 - this.br, bl + 2, n27 - this.br);
                graphics.drawLine(bl - 2, n28, bl + 2, n28);
                graphics.drawLine(bl - 2, n27, bl + 2, n27);
                graphics.drawLine(this.bl, n27, this.bl + this.bn, n27);
                for (int n31 = 0; n31 <= super.l + 1; ++n31) {
                    graphics.drawLine(this.bl + this.bo * n31, n27 - 2, this.bl + this.bo * n31, n27 + 2);
                }
            }
            if (!super.ba) {
                graphics.drawString(String.valueOf(this.bT), bl + 5, n27 - this.br + super.c / 2);
                graphics.drawString(this.bU, bl + 5, n28 + super.c / 2);
                graphics.drawString("0", bl + 5, n27 + super.c / 2);
            }
            else {
                graphics.drawString(String.valueOf(this.bT), bl - 5 - super.C.c(String.valueOf(this.bT), font), n27 - this.br + super.c / 2);
                graphics.drawString(this.bU, bl - 5 - super.C.c(this.bU, font), n28 + super.c / 2);
                graphics.drawString("0", bl - 5 - super.C.c("0", font), n27 + super.c / 2);
            }
            graphics.drawString(this.bS, this.bl + 5, this.bm + this.bq + this.bP - 2);
        }
        if (super.Z) {
            for (int n32 = 0; n32 <= this.bK; ++n32) {
                if (!super.ba) {
                    graphics.drawLine(this.bl + this.bn - 2, this.bm + this.bx * n32, this.bl + this.bn + 3, this.bm + this.bx * n32);
                    graphics.drawLine(this.bl + this.bn - 2, this.bm + 1 + this.bx * n32, this.bl + this.bn + 3, this.bm + 1 + this.bx * n32);
                }
                else {
                    graphics.drawLine(this.bl - 2, this.bm + this.bx * n32, this.bl + 3, this.bm + this.bx * n32);
                    graphics.drawLine(this.bl - 2, this.bm + 1 + this.bx * n32, this.bl + 3, this.bm + 1 + this.bx * n32);
                }
            }
            for (int n33 = 0; n33 <= super.l + 1; ++n33) {
                if (super.U == 1 || n33 % super.U == 0) {
                    graphics.drawLine(this.bl + this.bo * n33, this.bm + this.bq - 2, this.bl + this.bo * n33, this.bm + this.bq + 3);
                    graphics.drawLine(this.bl + 1 + this.bo * n33, this.bm + this.bq - 2, this.bl + 1 + this.bo * n33, this.bm + this.bq + 3);
                }
            }
        }
        else {
            for (int n34 = 0; n34 <= this.bK; ++n34) {
                if (!super.ba) {
                    graphics.drawLine(this.bl + this.bn - 2, this.bm + this.bx * n34, this.bl + this.bn + 2, this.bm + this.bx * n34);
                }
                else {
                    graphics.drawLine(this.bl - 2, this.bm + this.bx * n34, this.bl + 2, this.bm + this.bx * n34);
                }
            }
            for (int n35 = 0; n35 <= super.l + 1; ++n35) {
                if (super.U == 1 || n35 % super.U == 0) {
                    graphics.drawLine(this.bl + this.bo * n35, this.bm + this.bq - 2, this.bl + this.bo * n35, this.bm + this.bq + 2);
                }
            }
        }
        if (!super.bd) {
            if (!super.ba) {
                for (int n36 = 0; n36 <= this.bK; ++n36) {
                    graphics.drawString(this.bJ[n36], this.bl + this.bn + 5, this.bm + this.bx * n36 + 4);
                }
            }
            else {
                for (int n37 = 0; n37 <= this.bK; ++n37) {
                    graphics.drawString(this.bJ[n37], this.bl - 5 - super.C.c(this.bJ[n37], font), this.bm + this.bx * n37 + 4);
                }
            }
        }
        for (int n38 = 0; n38 < super.l; ++n38) {
            final int n39 = this.bl + (n38 + 1) * this.bo;
            final int n40 = this.bm + this.bq + this.br + this.bP;
            if (super.V == 1 || n38 % super.V == 0) {
                if (!super.L) {
                    if (super.k.b[n38] != null) {
                        if (!super.K) {
                            graphics.drawString(super.k.b[n38], n39 - super.C.a(super.k.b[n38], font), n40 + 17);
                        }
                        else if (n != 0) {
                            graphics.drawString(super.k.b[n38], n39 - super.C.a(super.k.b[n38], font), n40 + 17);
                        }
                        else {
                            graphics.drawString(super.k.b[n38], n39 - super.C.a(super.k.b[n38], font), n40 + 23 + super.c);
                        }
                    }
                    if (n != 0) {
                        n = 0;
                    }
                    else {
                        n = 1;
                    }
                }
                else if (super.k.b[n38] != null) {
                    final Image a = super.C.a(super.k.b[n38], font, true, super.h, super.g);
                    if (a != null) {
                        graphics.drawImage(a, n39 - super.c / 2, n40 + 10, null);
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
                graphics.drawString(super.N, this.bl + super.C.a(super.N, font, this.bp), this.bm + this.bq + super.M + (super.c + 17));
            }
            else {
                graphics.drawString(super.N, this.bl + super.C.a(super.N, font, this.bp), this.bm + super.b - (super.c + 2));
            }
        }
        if (super.O != null) {
            if (!super.ba) {
                super.C.a(super.a - (super.c + 2) / 2, this.bm, graphics, super.O, super.c + 2, this.bq);
                return;
            }
            super.C.a((super.c + 2) / 2, this.bm, graphics, super.O, super.c + 2, this.bq);
        }
    }
    
    public void a(final Color color) {
        this.bQ = color;
        this.bR = color;
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
                    c2 = '\u001c';
                    break;
                }
                case 1: {
                    c2 = '\u0017';
                    break;
                }
                case 2: {
                    c2 = '\u000f';
                    break;
                }
                case 3: {
                    c2 = '=';
                    break;
                }
                default: {
                    c2 = '\r';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
