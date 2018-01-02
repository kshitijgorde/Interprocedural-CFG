// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Graphics;
import java.text.NumberFormat;
import java.awt.Font;
import java.awt.Color;

public class d extends b
{
    private int bl;
    private int bm;
    private int bn;
    private int bo;
    private int bp;
    private int bq;
    private int br;
    private int bs;
    private int bt;
    private int bu;
    private int bv;
    private double bw;
    private double bx;
    private boolean by;
    private boolean bz;
    private Color bA;
    private String bB;
    private String bC;
    private String bD;
    private String bE;
    private String bF;
    private int bG;
    private int bH;
    private int bI;
    private String[] bJ;
    private int bK;
    private int bL;
    private int bM;
    private double bN;
    
    public d(final int bo, final int bn, final int a, final int b, final Color g, final Color h, final int c) {
        this.by = false;
        this.bz = false;
        this.bA = new Color(0, 0, 192);
        this.bI = 1;
        this.bK = 1;
        this.bL = -1;
        super.a = a;
        super.b = b;
        super.g = g;
        super.h = h;
        this.bl = bo + 10;
        this.bm = bn + 20;
        this.bn = bn;
        this.bo = bo;
        super.c = c;
        this.bq = super.a - 20;
        this.bp = super.b - 20;
    }
    
    public boolean a(final int l, final p j, final String s, final String s2) {
        super.l = l;
        super.j = j;
        final double[] array = new double[11];
        final Font font = new Font(super.f, 0, super.c);
        if (super.N != null) {
            this.bm += 5 + (super.c + 2);
            this.bp -= 5 + (super.c + 2);
        }
        if (super.O != null) {
            this.bl += 15 + (super.c + 2) / 2;
            this.bq -= 15 + (super.c + 2) / 2;
        }
        if (!super.t || !super.v) {
            for (int i = 0; i < super.l; ++i) {
                if (!super.j.a[i].s) {
                    if (!this.by) {
                        this.by = true;
                        this.bw = super.j.a[i].a;
                        this.bx = this.bw;
                    }
                    else {
                        if (super.j.a[i].a > this.bw) {
                            this.bw = super.j.a[i].a;
                        }
                        if (super.j.a[i].a < this.bx) {
                            this.bx = super.j.a[i].a;
                        }
                    }
                }
            }
        }
        if (!this.by || (this.bw == 0.0 && this.bx == 0.0)) {
            this.bw = 4.0;
            this.bx = 0.0;
        }
        if (super.t) {
            this.bx = super.n;
        }
        if (super.v) {
            this.bw = super.o;
        }
        if (this.bx >= 0.0) {
            this.bI = 1;
        }
        else if (this.bw < 0.0) {
            this.bI = 3;
        }
        else {
            this.bI = 2;
        }
        final double[] a = super.C.a(this.bw, this.bx, super.t, super.v, super.R, super.S);
        this.bw = a[0];
        this.bx = a[1];
        this.bN = a[2];
        this.bK = (int)a[3];
        final int n = (int)a[4];
        this.bJ = new String[this.bK + 1];
        for (int k = 0; k <= this.bK; ++k) {
            array[k] = super.C.e(this.bx, super.C.d(this.bN, this.bK - k));
            if (array[k] == 0.0) {
                this.bL = k;
            }
        }
        final NumberFormat d = super.C.d(super.bg);
        d.setMaximumFractionDigits(n);
        d.setMinimumFractionDigits(n);
        try {
            for (int n2 = 0; n2 <= this.bK; ++n2) {
                this.bJ[n2] = String.valueOf(s2) + d.format(array[n2]) + s;
            }
        }
        catch (Exception ex) {}
        for (int n3 = 0; n3 < super.l; ++n3) {
            if (super.j.a[n3].f != null && super.C.c(super.j.a[n3].f, font) > this.bH) {
                this.bH = super.C.c(super.j.a[n3].f, font);
            }
        }
        this.bH += 10;
        this.bl += this.bH;
        this.bq = this.bq - this.bH - this.bG / 2;
        this.bv = this.bq / this.bK;
        this.bq = this.bv * this.bK;
        if (super.H > 0 && super.I) {
            this.bp -= super.C.b(b("\u000fe"), font) + 5;
        }
        if (this.bI != 2) {
            for (int n4 = 0; n4 < super.l; ++n4) {
                if (!super.j.a[n4].s) {
                    double n5;
                    if (super.j.a[n4].a < this.bx) {
                        n5 = this.bx;
                    }
                    else if (super.j.a[n4].a > this.bw) {
                        n5 = this.bw;
                    }
                    else {
                        n5 = super.j.a[n4].a;
                    }
                    if (this.bI == 1) {
                        super.j.a[n4].n = (int)((n5 - this.bx) / (this.bw - this.bx) * this.bq);
                    }
                    else {
                        super.j.a[n4].n = (int)(this.bq - (Math.abs(n5) + this.bw) / (this.bw - this.bx) * this.bq);
                    }
                }
            }
        }
        else {
            for (int n6 = 0; n6 < super.l; ++n6) {
                if (!super.j.a[n6].s) {
                    if (super.j.a[n6].a < this.bx) {
                        super.j.a[n6].n = (int)(this.bx / (this.bw - this.bx) * this.bq);
                    }
                    else if (super.j.a[n6].a > this.bw) {
                        super.j.a[n6].n = (int)(this.bw / (this.bw - this.bx) * this.bq);
                    }
                    else {
                        super.j.a[n6].n = (int)(super.j.a[n6].a / (this.bw - this.bx) * this.bq);
                    }
                }
            }
        }
        if (!super.r) {
            final double n7 = this.bw + Math.abs(this.bx) - super.n;
        }
        else {
            final double n8 = this.bw + Math.abs(this.bx);
        }
        if (super.l > 0) {
            this.br = this.bp / super.l;
        }
        else {
            this.br = this.bp;
        }
        if (super.Y != 0 && this.br + 7 > super.Y) {
            this.br = super.Y + 7;
        }
        this.bs = this.br * super.l;
        this.bt = this.bv * this.bK;
        if (this.bw >= 0.0 && this.bx < 0.0) {
            this.bM = (int)((0.0 - this.bx) / (this.bw - this.bx) * this.bq);
        }
        return true;
    }
    
    public void a(final Graphics graphics) {
        int n = 0;
        int n2 = 0;
        final Font font = new Font(super.f, 0, super.c);
        graphics.setFont(font);
        if (super.H > 0) {
            if (!super.bi) {
                super.bj = b("\u0016[\f^\u0005");
            }
            for (int i = 0; i < super.H; ++i) {
                final q q = super.J[i];
                if (q != null) {
                    if (q.a(this.bx, this.bw)) {
                        final int n3 = (int)((q.a() - this.bx) / (this.bw - this.bx) * this.bt);
                        graphics.setColor(q.d);
                        graphics.drawLine(this.bl + n3, this.bm, this.bl + n3, this.bm + this.bs);
                        if (q.e != null) {
                            final int n4 = this.bl + n3 - super.C.a(q.e, font) - 3;
                            final int n5 = this.bm + this.bs + super.C.b(q.e, font) / 2 + 8;
                            graphics.setColor(super.h);
                            graphics.drawString(q.e, n4, n5);
                        }
                    }
                    else if (q.b(this.bx, this.bw)) {
                        final double[] c = q.c(this.bx, this.bw);
                        final double n6 = c[0];
                        final double n7 = c[1];
                        final int n8 = (int)((n6 - this.bx) / (this.bw - this.bx) * this.bt);
                        final int n9 = (int)((n7 - this.bx) / (this.bw - this.bx) * this.bt);
                        final int n10 = n9 - n8;
                        if (n10 > 0) {
                            graphics.setColor(q.d);
                            graphics.fillRect(this.bl + n8, this.bm, n10, this.bs);
                        }
                        if (q.e != null) {
                            final int n11 = this.bl + n9 - n10 / 2 - super.C.a(q.e, font) - 3;
                            final int n12 = this.bm + this.bs + super.C.b(q.e, font) / 2 + 8;
                            graphics.setColor(super.h);
                            graphics.drawString(q.e, n11, n12);
                        }
                    }
                }
            }
        }
        graphics.setColor(super.E);
        if (super.Z) {
            graphics.setColor(super.E);
            if (super.C.b(super.bj, b("\u000fx\u000e")) || super.C.b(super.bj, b("\u0016[\f^\u0005"))) {
                for (int j = 1; j < super.l; ++j) {
                    graphics.drawLine(this.bl, this.bm + this.br * j, this.bl + this.bq, this.bm + this.br * j);
                    graphics.drawLine(this.bl, this.bm + 1 + this.br * j, this.bl + this.bq, this.bm + 1 + this.br * j);
                }
            }
            if (super.C.b(super.bj, b("\u000fx\u000e")) || super.C.b(super.bj, b("\u0017[\f^\u0005"))) {
                for (int k = 1; k < this.bK; ++k) {
                    graphics.drawLine(this.bl + this.bv * (this.bK - k), this.bm, this.bl + this.bv * (this.bK - k), this.bm + this.bs);
                    graphics.drawLine(this.bl + this.bv * (this.bK - k) + 1, this.bm, this.bl + this.bv * (this.bK - k) + 1, this.bm + this.bs);
                }
            }
            if (!super.C.b(super.bj, b("\u0000{\fW"))) {
                graphics.drawLine(this.bl + this.bv * this.bK, this.bm, this.bl + this.bv * this.bK, this.bm + this.bs);
                graphics.drawLine(this.bl + this.bv * this.bK + 1, this.bm, this.bl + this.bv * this.bK + 1, this.bm + this.bs);
                graphics.drawLine(this.bl, this.bm + this.bs, this.bl + this.bq, this.bm + this.bs);
                graphics.drawLine(this.bl, this.bm + 1 + this.bs, this.bl + this.bq, this.bm + 1 + this.bs);
            }
            graphics.setColor(super.h);
            for (int l = 0; l < super.l; ++l) {
                graphics.drawLine(this.bl - 2, this.bm + this.br * (l + 1), this.bl + 2, this.bm + this.br * (l + 1));
                graphics.drawLine(this.bl - 2, this.bm + 1 + this.br * (l + 1), this.bl + 2, this.bm + 1 + this.br * (l + 1));
            }
            graphics.setColor(super.h);
            graphics.drawLine(this.bl, this.bm, this.bl + this.bq, this.bm);
            graphics.drawLine(this.bl, this.bm - 1, this.bl + this.bq, this.bm - 1);
            graphics.drawLine(this.bl, this.bm, this.bl, this.bm + this.bs);
            graphics.drawLine(this.bl + 1, this.bm, this.bl + 1, this.bm + this.bs);
            for (int n13 = 0; n13 <= this.bK; ++n13) {
                graphics.drawLine(this.bl + this.bv * (this.bK - n13) + 1, this.bm - 2, this.bl + this.bv * (this.bK - n13) + 1, this.bm + 2);
                graphics.drawLine(this.bl + this.bv * (this.bK - n13), this.bm - 2, this.bl + this.bv * (this.bK - n13), this.bm + 2);
            }
            if (!super.bd) {
                for (int n14 = 0; n14 <= this.bK; ++n14) {
                    graphics.drawString(this.bJ[n14], this.bl + this.bv * (this.bK - n14) - super.C.a(this.bJ[n14], font), this.bm - 5);
                }
            }
        }
        else {
            graphics.setColor(super.E);
            if (!super.C.b(super.bj, b("\u0000{\fW"))) {
                graphics.drawLine(this.bl + this.bt, this.bm, this.bl + this.bv * this.bK, this.bm + this.bs);
                graphics.drawLine(this.bl, this.bm + this.br * super.l, this.bl + this.bq, this.bm + this.br * super.l);
            }
            graphics.setColor(super.E);
            if (super.C.b(super.bj, b("\u000fx\u000e")) || super.C.b(super.bj, b("\u0017[\f^\u0005"))) {
                for (int n15 = 0; n15 <= this.bK; ++n15) {
                    graphics.drawLine(this.bl + this.bv * (this.bK - n15), this.bm, this.bl + this.bv * (this.bK - n15), this.bm + this.bs);
                }
            }
            if (super.C.b(super.bj, b("\u000fx\u000e")) || super.C.b(super.bj, b("\u0016[\f^\u0005"))) {
                for (int n16 = 0; n16 < super.l; ++n16) {
                    graphics.drawLine(this.bl, this.bm + this.br * (n16 + 1), this.bl + this.bq, this.bm + this.br * (n16 + 1));
                }
            }
            graphics.setColor(super.h);
            for (int n17 = 0; n17 < super.l; ++n17) {
                graphics.drawLine(this.bl - 2, this.bm + this.br * (n17 + 1), this.bl + 2, this.bm + this.br * (n17 + 1));
            }
            graphics.setColor(super.h);
            graphics.drawLine(this.bl, this.bm, this.bl + this.bq, this.bm);
            graphics.drawLine(this.bl, this.bm, this.bl, this.bm + this.bs);
            for (int n18 = 0; n18 <= this.bK; ++n18) {
                graphics.drawLine(this.bl + this.bv * (this.bK - n18), this.bm - 2, this.bl + this.bv * (this.bK - n18), this.bm + 2);
            }
            if (!super.bd) {
                for (int n19 = 0; n19 <= this.bK; ++n19) {
                    graphics.drawString(this.bJ[n19], this.bl + this.bv * (this.bK - n19) - super.C.a(this.bJ[n19], font), this.bm - 5);
                }
            }
        }
        if (this.bI != 2) {
            for (int n20 = 0; n20 < super.l; ++n20) {
                if (super.j.a[n20].g == null) {
                    graphics.setColor(this.bA);
                }
                else {
                    graphics.setColor(super.j.a[n20].g);
                }
                int t;
                int n21;
                if (this.bI == 1) {
                    t = this.bl + 1;
                    n21 = super.j.a[n20].n;
                }
                else if (!super.bc) {
                    t = this.bl + 1;
                    n21 = super.j.a[n20].n;
                }
                else {
                    t = this.bl + 1 + super.j.a[n20].n;
                    n21 = this.bq - super.j.a[n20].n;
                }
                final int u = this.bm + (this.br * (n20 + 1) - this.br + 4);
                final int n22 = this.br - 7;
                if (super.be) {
                    if (super.bc) {
                        if (this.bI == 1) {
                            n = t + n21 + 3;
                        }
                        else {
                            n = t;
                        }
                    }
                    else {
                        n = t + n21 + 3;
                    }
                    n2 = u + n22 / 2 + super.c / 2;
                }
                if (!super.j.a[n20].s) {
                    graphics.fillRect(t, u, n21, n22);
                }
                if (super.j.a[n20].f != null) {
                    graphics.setColor(super.h);
                    graphics.drawString(super.j.a[n20].f, this.bl - 10 - super.C.c(super.j.a[n20].f, font), this.bm + this.br * (n20 + 1) - this.br / 2 + 5);
                }
                if (!super.j.a[n20].s && !super.F) {
                    super.j.a[n20].t = t;
                    super.j.a[n20].u = u;
                    super.j.a[n20].v = t + n21;
                    super.j.a[n20].w = u + n22;
                }
                if (super.be) {
                    graphics.setColor(super.h);
                    final String m = super.j.a[n20].k;
                    final int c2 = super.C.c(m, font);
                    if (this.bI == 3 && super.bc) {
                        n = n - c2 - 3;
                    }
                    if (n + c2 > this.bl + this.bq) {
                        n = n - c2 - 6;
                    }
                    if (n < this.bl) {
                        n = n + c2 + 6;
                    }
                    graphics.drawString(m, n, n2);
                }
            }
        }
        else {
            for (int n23 = 0; n23 < super.l; ++n23) {
                if (super.j.a[n23].g == null) {
                    graphics.setColor(this.bA);
                }
                else {
                    graphics.setColor(super.j.a[n23].g);
                }
                if (!super.j.a[n23].s) {
                    boolean b;
                    int t2;
                    int u2;
                    int abs;
                    int n24;
                    if (super.bc) {
                        if (super.j.a[n23].n < 0) {
                            b = true;
                            t2 = this.bl + 1 + this.bM + super.j.a[n23].n;
                        }
                        else {
                            t2 = this.bl + 1 + this.bM;
                            b = false;
                        }
                        u2 = this.bm + (this.br * (n23 + 1) - this.br + 4);
                        abs = Math.abs(super.j.a[n23].n);
                        n24 = this.br - 7;
                    }
                    else {
                        if (super.j.a[n23].n < 0) {
                            b = true;
                            t2 = this.bl + 1;
                            abs = this.bM - Math.abs(super.j.a[n23].n);
                        }
                        else {
                            b = false;
                            t2 = this.bl + 1;
                            abs = Math.abs(super.j.a[n23].n) + this.bM;
                        }
                        u2 = this.bm + (this.br * (n23 + 1) - this.br + 4);
                        n24 = this.br - 7;
                    }
                    if (super.be) {
                        if (super.bc) {
                            if (b) {
                                n = t2;
                            }
                            else {
                                n = t2 + abs + 3;
                            }
                        }
                        else {
                            n = t2 + abs + 3;
                        }
                        n2 = u2 + n24 / 2 + super.c / 2;
                    }
                    graphics.fillRect(t2, u2, abs, n24);
                    if (!super.F) {
                        super.j.a[n23].t = t2;
                        super.j.a[n23].u = u2;
                        super.j.a[n23].v = t2 + abs;
                        super.j.a[n23].w = u2 + n24;
                    }
                    if (super.j.a[n23].f != null) {
                        graphics.setColor(super.h);
                        graphics.drawString(super.j.a[n23].f, this.bl - 10 - super.C.c(super.j.a[n23].f, font), this.bm + this.br * (n23 + 1) - this.br / 2 + 5);
                    }
                    if (super.be) {
                        graphics.setColor(super.h);
                        final String k2 = super.j.a[n23].k;
                        final int c3 = super.C.c(k2, font);
                        if (b && super.bc) {
                            n = n - c3 - 3;
                        }
                        if (n + c3 > this.bl + this.bq) {
                            n = n - c3 - 6;
                        }
                        if (n < this.bl) {
                            n = n + c3 + 6;
                        }
                        graphics.drawString(k2, n, n2);
                    }
                }
            }
        }
        if (this.bI == 2 || this.bI == 3) {
            graphics.setColor(super.h);
            if (super.Z) {
                graphics.drawLine(this.bl + this.bM, this.bm, this.bl + this.bM, this.bm + this.bs);
                graphics.drawLine(this.bl + 1 + this.bM, this.bm, this.bl + 1 + this.bM, this.bm + this.bs);
                for (int n25 = 0; n25 < super.l; ++n25) {
                    graphics.drawLine(this.bl + this.bM - 2, this.bm + this.br * (n25 + 1), this.bl + this.bM + 3, this.bm + this.br * (n25 + 1));
                    graphics.drawLine(this.bl + this.bM - 2, this.bm + this.br * (n25 + 1) + 1, this.bl + this.bM + 3, this.bm + this.br * (n25 + 1) + 1);
                }
            }
            else {
                graphics.drawLine(this.bl + this.bM, this.bm, this.bl + this.bM, this.bm + this.bs);
                for (int n26 = 0; n26 < super.l; ++n26) {
                    graphics.drawLine(this.bl + this.bM - 2, this.bm + this.br * (n26 + 1), this.bl + this.bM + 2, this.bm + this.br * (n26 + 1));
                }
            }
        }
        this.b(graphics);
        super.C.a(graphics, this);
        super.F = true;
    }
    
    public void a(final Graphics graphics, final o o) {
        graphics.setFont(new Font(super.f, 0, super.c));
        graphics.setColor(super.D);
        if (this.bI == 1) {
            for (int i = 0; i < super.l; ++i) {
                if (o == super.j.a[i]) {
                    graphics.fillRect(this.bl + 1, this.bm + (this.br * (i + 1) - this.br + 4), super.j.a[i].n, this.br - 7);
                }
            }
        }
        else if (this.bI == 3) {
            for (int j = 0; j < super.l; ++j) {
                if (o == super.j.a[j]) {
                    if (!super.bc) {
                        graphics.fillRect(this.bl + 1, this.bm + (this.br * (j + 1) - this.br + 4), super.j.a[j].n, this.br - 7);
                    }
                    else {
                        graphics.fillRect(this.bl + 1 + super.j.a[j].n, this.bm + (this.br * (j + 1) - this.br + 4), this.bq - super.j.a[j].n, this.br - 7);
                    }
                }
            }
        }
        else {
            for (int k = 0; k < super.l; ++k) {
                if (o == super.j.a[k]) {
                    if (super.bc) {
                        if (super.j.a[k].n < 0) {
                            graphics.fillRect(this.bl + 1 + this.bM + super.j.a[k].n, this.bm + (this.br * (k + 1) - this.br + 4), Math.abs(super.j.a[k].n), this.br - 7);
                        }
                        else {
                            graphics.fillRect(this.bl + 1 + this.bM, this.bm + (this.br * (k + 1) - this.br + 4), super.j.a[k].n, this.br - 7);
                        }
                    }
                    else if (super.j.a[k].n < 0) {
                        graphics.fillRect(this.bl + 1, this.bm + (this.br * (k + 1) - this.br + 4), this.bM - Math.abs(super.j.a[k].n), this.br - 7);
                    }
                    else {
                        graphics.fillRect(this.bl + 1, this.bm + (this.br * (k + 1) - this.br + 4), super.j.a[k].n + this.bM, this.br - 7);
                    }
                }
            }
        }
        if (this.bI == 2 || this.bI == 3) {
            graphics.setColor(super.h);
            if (super.Z) {
                graphics.drawLine(this.bl + this.bM, this.bm, this.bl + this.bM, this.bm + this.bs);
                graphics.drawLine(this.bl + 1 + this.bM, this.bm, this.bl + 1 + this.bM, this.bm + this.bs);
                for (int l = 0; l < super.l; ++l) {
                    graphics.drawLine(this.bl + this.bM - 2, this.bm + this.br * (l + 1), this.bl + this.bM + 3, this.bm + this.br * (l + 1));
                    graphics.drawLine(this.bl + this.bM - 2, this.bm + this.br * (l + 1) + 1, this.bl + this.bM + 3, this.bm + this.br * (l + 1) + 1);
                }
                return;
            }
            graphics.drawLine(this.bl + this.bM, this.bm, this.bl + this.bM, this.bm + this.bs);
            for (int n = 0; n < super.l; ++n) {
                graphics.drawLine(this.bl + this.bM - 2, this.bm + this.br * (n + 1), this.bl + this.bM + 2, this.bm + this.br * (n + 1));
            }
        }
    }
    
    public o a(final int n, final int n2) {
        for (int i = 0; i < super.l; ++i) {
            if (n >= super.j.a[i].t && n <= super.j.a[i].v && n2 >= super.j.a[i].u && n2 <= super.j.a[i].w) {
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
            graphics.drawString(super.N, this.bl + super.C.a(super.N, font, this.bq), this.bn + super.c);
        }
        if (super.O != null) {
            super.C.a(this.bo + 5, this.bm, graphics, super.O, super.c + 2, this.bp);
        }
    }
    
    public void a(final Color ba) {
        this.bA = ba;
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
                    c2 = 'N';
                    break;
                }
                case 1: {
                    c2 = '\u0014';
                    break;
                }
                case 2: {
                    c2 = 'b';
                    break;
                }
                case 3: {
                    c2 = '2';
                    break;
                }
                default: {
                    c2 = '|';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
