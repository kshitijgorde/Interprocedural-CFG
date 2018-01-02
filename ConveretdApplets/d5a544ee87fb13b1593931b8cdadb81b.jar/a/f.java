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

public class f extends b
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
    private double bv;
    private double bw;
    private int bx;
    private int by;
    private int bz;
    private int bA;
    public double[] bB;
    public double[] bC;
    public int[][] bD;
    public int[][] bE;
    private String bF;
    private String bG;
    private String bH;
    private String bI;
    private String bJ;
    private int bK;
    private int bL;
    private String[] bM;
    private int bN;
    private int bO;
    private int bP;
    private double bQ;
    private int bR;
    private String[] bS;
    private String[] bT;
    
    public f(final int bn, final int bm, final int a, final int b, final Color g, final Color h, final int c) {
        this.bo = 3;
        this.br = 6;
        this.bs = 2;
        this.bL = 1;
        this.bN = 1;
        this.bO = -1;
        super.a = a;
        super.b = b;
        super.g = g;
        super.h = h;
        this.bl = bn + 20;
        this.bm = bm;
        this.bn = bn;
        super.c = c;
        this.bx = super.a - 40;
        this.by = super.b - 25;
        super.bh = b(":g -\u000b\u0018q\r9\t");
    }
    
    public boolean a(final int l, final n k, final String s, final String s2) {
        super.l = l;
        super.k = k;
        this.bA = super.k.a.size();
        final double[] array = new double[11];
        double n = 0.0;
        final Font font = new Font(super.f, 0, super.c);
        if (super.N != null) {
            this.by -= 5 + (super.c + 2);
        }
        if (super.O != null) {
            this.bl += 15 + (super.c + 2) / 2;
            this.bx -= 15 + (super.c + 2) / 2;
        }
        this.bD = new int[super.l][this.bA + 1];
        this.bE = new int[super.l][this.bA + 1];
        this.bB = new double[super.l + 1];
        this.bC = new double[super.l + 1];
        if (super.bh.compareTo(b("0`#,\u0012OQ\r9\t")) == 0) {
            for (int i = 0; i < this.bA; ++i) {
                final p p4 = super.k.a.elementAt(i);
                for (int j = 0; j < super.l; ++j) {
                    if (p4.a[j] != null) {
                        if (p4.a[j].a >= 0.0) {
                            final double[] bb = this.bB;
                            final int n2 = j;
                            bb[n2] += p4.a[j].a;
                        }
                        else {
                            final double[] bc = this.bC;
                            final int n3 = j;
                            bc[n3] += p4.a[j].a;
                        }
                    }
                }
            }
            for (int n4 = 0; n4 < super.l; ++n4) {
                if (this.bB[n4] > this.bv) {
                    this.bv = this.bB[n4];
                }
                if (this.bC[n4] < this.bw) {
                    this.bw = this.bC[n4];
                }
            }
        }
        else if (!super.t || !super.v) {
            for (int n5 = 0; n5 < this.bA; ++n5) {
                final p p5 = super.k.a.elementAt(n5);
                for (int n6 = 0; n6 < super.l; ++n6) {
                    if (p5.a[n6] != null) {
                        if (p5.a[n6].a > this.bv) {
                            this.bv = p5.a[n6].a;
                        }
                        if (p5.a[n6].a < this.bw) {
                            this.bw = p5.a[n6].a;
                        }
                    }
                }
            }
        }
        if (this.bv == 0.0 && this.bw == 0.0) {
            this.bv = 5.0;
            this.bw = 0.0;
        }
        if (super.t) {
            this.bw = super.n;
        }
        if (super.v) {
            this.bv = super.o;
        }
        if (this.bw >= 0.0) {
            this.bL = 1;
        }
        else if (this.bv <= 0.0) {
            this.bL = 3;
        }
        else {
            this.bL = 2;
        }
        final double[] a = super.C.a(this.bv, this.bw, super.t, super.v, super.R, super.S);
        this.bv = a[0];
        this.bw = a[1];
        this.bQ = a[2];
        this.bN = (int)a[3];
        final int n7 = (int)a[4];
        this.bM = new String[this.bN + 1];
        for (int bo = 0; bo <= this.bN; ++bo) {
            array[bo] = super.C.e(this.bw, super.C.d(this.bQ, this.bN - bo));
            if (array[bo] == 0.0) {
                this.bO = bo;
            }
        }
        final NumberFormat d = super.C.d(super.bg);
        d.setMaximumFractionDigits(n7);
        d.setMinimumFractionDigits(n7);
        try {
            for (int n8 = 0; n8 <= this.bN; ++n8) {
                this.bM[n8] = String.valueOf(s2) + d.format(array[n8]) + s;
            }
        }
        catch (Exception ex) {}
        if (!super.bd) {
            this.bK = Math.max(super.C.c(String.valueOf(s2) + this.bM[0] + s, font), super.C.c(String.valueOf(s2) + this.bM[this.bN] + s, font));
            this.bl += this.bK;
            this.bx -= this.bK;
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
            this.bx = this.bx - n9 - 3;
        }
        if (super.bh.compareTo(b("0`#,\u0012OQ\r9\t")) == 0) {
            if (super.l > 0 && this.bA > 0) {
                if (super.Z) {
                    this.bt = (this.bx - this.br * super.l) / super.l - 1;
                }
                else {
                    this.bt = (this.bx - this.br * super.l) / super.l;
                }
                this.bu = this.bt - this.bs;
                if (super.Y != 0 && this.bu > super.Y) {
                    this.bu = super.Y;
                    this.bt = (this.bu + this.bs) * this.bA;
                }
            }
            else {
                this.bu = this.bx - this.bs;
                this.bt = this.bu + this.bs;
            }
        }
        else if (super.l > 0 && this.bA > 0) {
            if (super.Z) {
                this.bt = (this.bx - this.br * super.l) / super.l - 1;
            }
            else {
                this.bt = (this.bx - this.br * super.l) / super.l;
            }
            this.bu = (this.bt - this.bs * (this.bA - 1)) / this.bA;
            if (super.Y != 0 && this.bu > super.Y) {
                this.bu = super.Y;
                this.bt = (this.bu + this.bs) * this.bA;
            }
        }
        else {
            this.bu = this.bx - this.bs;
            this.bt = this.bu + this.bs;
        }
        if (!super.L) {
            final int a2 = super.C.a(super.k.b, super.l, this.bt, this.bl, super.a, font);
            if (a2 == 2) {
                super.K = true;
            }
            else if (a2 == 3) {
                super.L = true;
                super.M = super.C.a(super.k.b, super.l, font);
                this.by -= super.M;
            }
            if (super.K) {
                this.by -= super.c + 5;
                if (super.N != null) {
                    this.by -= super.c + 2;
                }
            }
        }
        this.bR = this.by / this.bN;
        this.by = this.bR * this.bN;
        if (super.bh.compareTo(b("0`#,\u0012OQ\r9\t")) == 0) {
            for (int n11 = 0; n11 < this.bA; ++n11) {
                final p p6 = super.k.a.elementAt(n11);
                for (int n12 = 0; n12 < super.l; ++n12) {
                    if (p6.a[n12] != null) {
                        if (p6.a[n12].a < this.bw) {
                            n = this.bw;
                        }
                        else if (p6.a[n12].a > this.bv) {
                            n = this.bv;
                        }
                        else {
                            n = p6.a[n12].a;
                        }
                    }
                    if (n >= 0.0) {
                        this.bD[n12][n11] = (int)(n / (this.bv - this.bw) * this.by);
                    }
                    else {
                        this.bE[n12][n11] = (int)(n / (this.bv - this.bw) * this.by);
                    }
                }
            }
            if (super.be) {
                this.bS = new String[super.l];
                this.bT = new String[super.l];
                try {
                    for (int n13 = 0; n13 < super.l; ++n13) {
                        this.bS[n13] = String.valueOf(s2) + d.format(this.bB[n13]) + s;
                        this.bT[n13] = String.valueOf(s2) + d.format(this.bC[n13]) + s;
                    }
                }
                catch (Exception ex2) {}
            }
        }
        else {
            for (int n14 = 0; n14 < this.bA; ++n14) {
                final p p7 = super.k.a.elementAt(n14);
                for (int n15 = 0; n15 < super.l; ++n15) {
                    if (p7.a[n15] != null) {
                        if (p7.a[n15].a < this.bw) {
                            n = this.bw;
                        }
                        else if (p7.a[n15].a > this.bv) {
                            n = this.bv;
                        }
                        else {
                            n = p7.a[n15].a;
                        }
                    }
                    if (this.bL == 1) {
                        this.bD[n15][n14] = (int)((n - this.bw) / (this.bv - this.bw) * this.by);
                    }
                    else if (this.bL == 2) {
                        this.bD[n15][n14] = (int)(n / (this.bv - this.bw) * this.by);
                    }
                    else {
                        this.bD[n15][n14] = (int)((Math.abs(n) + this.bv) / (this.bv - this.bw) * this.by);
                    }
                }
            }
        }
        if (this.bv >= 0.0 && this.bw < 0.0) {
            this.bP = (int)(this.by - (0.0 - this.bw) / (this.bv - this.bw) * this.by);
        }
        return true;
    }
    
    public void a(final Graphics graphics) {
        int n = 1;
        int n2 = 0;
        int n3 = 0;
        final Font font = new Font(super.f, 0, super.c);
        graphics.setFont(font);
        if (super.H > 0) {
            if (!super.bi) {
                super.bj = b("%Z!4\u0002");
            }
            for (int i = 0; i < super.H; ++i) {
                final q q = super.J[i];
                if (q != null) {
                    if (q.a(this.bw, this.bv)) {
                        final int n4 = (int)(this.by - (q.a() - this.bw) / (this.bv - this.bw) * this.by);
                        graphics.setColor(q.d);
                        graphics.drawLine(this.bl, this.bm + n4, this.bl + this.bx, this.bm + n4);
                        if (q.e != null) {
                            final int n5 = this.bl + this.bx + 3;
                            final int n6 = this.bm + n4 + super.C.b(q.e, font) / 2 - 1;
                            graphics.setColor(super.h);
                            graphics.drawString(q.e, n5, n6);
                        }
                    }
                    else if (q.b(this.bw, this.bv)) {
                        final double[] c = q.c(this.bw, this.bv);
                        final double n7 = c[0];
                        final double n8 = c[1];
                        final int n9 = (int)(this.by - (n7 - this.bw) / (this.bv - this.bw) * this.by);
                        final int n10 = (int)(this.by - (n8 - this.bw) / (this.bv - this.bw) * this.by);
                        final int n11 = n9 - n10;
                        if (n11 > 0) {
                            graphics.setColor(q.d);
                            graphics.fillRect(this.bl, this.bm + n10, this.bx, n11);
                        }
                        if (q.e != null) {
                            final int n12 = this.bl + this.bx + 3;
                            final int n13 = this.bm + n9 - n11 / 2 + super.C.b(q.e, font) / 2 - 1;
                            graphics.setColor(super.h);
                            graphics.drawString(q.e, n12, n13);
                        }
                    }
                }
            }
        }
        graphics.setColor(super.E);
        if (super.Z) {
            if (!super.C.b(super.bj, b("3z!="))) {
                graphics.drawRect(this.bl, this.bm, this.bx - 1, this.by);
                graphics.drawRect(this.bl + 1, this.bm - 1, this.bx - 1, this.by + 2);
            }
            graphics.drawLine(this.bl, this.bm + this.by + 6, this.bl, this.bm + this.by);
            graphics.drawLine(this.bl + 1, this.bm + this.by + 6, this.bl + 1, this.bm + this.by);
            graphics.drawLine(this.bl + this.bx, this.bm + this.by + 6, this.bl + this.bx, this.bm + this.by);
            graphics.drawLine(this.bl - 1 + this.bx, this.bm + this.by + 6, this.bl - 1 + this.bx, this.bm + this.by);
            if (super.C.b(super.bj, b("<y#")) || super.C.b(super.bj, b("$Z!4\u0002"))) {
                for (int j = 0; j <= this.bN; ++j) {
                    graphics.drawLine(this.bl, this.bm + j * this.bR, this.bl + this.bx, this.bm + j * this.bR);
                    graphics.drawLine(this.bl, this.bm + 1 + j * this.bR, this.bl + this.bx, this.bm + 1 + j * this.bR);
                }
            }
            graphics.setColor(super.h);
            if (!super.bd) {
                for (int k = 0; k <= this.bN; ++k) {
                    graphics.drawString(this.bM[k], this.bl - 15 - super.C.c(this.bM[k], font), this.bm + k * this.bR + super.c / 2);
                }
            }
            graphics.setColor(super.h);
            graphics.drawLine(this.bl - 6, this.bm + this.by, this.bl, this.bm + this.by);
            graphics.drawLine(this.bl - 6, this.bm + 1 + this.by, this.bl, this.bm + 1 + this.by);
            graphics.drawLine(this.bl, this.bm - 1, this.bl, this.bm + this.by + 6);
            graphics.drawLine(this.bl + 1, this.bm - 1, this.bl + 1, this.bm + this.by + 6);
            graphics.drawLine(this.bl, this.bm + this.by, this.bl + this.bx, this.bm + this.by);
            graphics.drawLine(this.bl, this.bm + this.by + 1, this.bl + this.bx, this.bm + this.by + 1);
            for (int l = 0; l <= this.bN; ++l) {
                graphics.drawLine(this.bl - 6, this.bm + l * this.bR, this.bl, this.bm + l * this.bR);
                graphics.drawLine(this.bl - 6, this.bm + 1 + l * this.bR, this.bl, this.bm + 1 + l * this.bR);
            }
        }
        else {
            if (!super.C.b(super.bj, b("3z!="))) {
                graphics.drawRect(this.bl, this.bm, this.bx, this.by);
            }
            graphics.drawLine(this.bl, this.bm + this.by + 6, this.bl, this.bm + this.by);
            graphics.drawLine(this.bl + this.bx, this.bm + this.by + 6, this.bl + this.bx, this.bm + this.by);
            graphics.drawLine(this.bl - 6, this.bm + this.by, this.bl, this.bm + this.by);
            if (super.C.b(super.bj, b("<y#")) || super.C.b(super.bj, b("$Z!4\u0002"))) {
                for (int n14 = 0; n14 <= this.bN; ++n14) {
                    graphics.drawLine(this.bl, this.bm + n14 * this.bR, this.bl + this.bx, this.bm + n14 * this.bR);
                }
            }
            graphics.setColor(super.h);
            if (!super.bd) {
                for (int n15 = 0; n15 <= this.bN; ++n15) {
                    graphics.drawString(this.bM[n15], this.bl - 15 - super.C.c(this.bM[n15], font), this.bm + n15 * this.bR + super.c / 2);
                }
            }
            graphics.setColor(super.h);
            graphics.drawLine(this.bl - 6, this.bm + this.by, this.bl, this.bm + this.by);
            graphics.drawLine(this.bl, this.bm, this.bl, this.bm + this.by + 6);
            graphics.drawLine(this.bl, this.bm + this.by, this.bl + this.bx, this.bm + this.by);
            for (int n16 = 0; n16 <= this.bN; ++n16) {
                graphics.drawLine(this.bl - 6, this.bm + n16 * this.bR, this.bl, this.bm + n16 * this.bR);
            }
            graphics.drawLine(this.bl + this.bx, this.bm + this.by + 6, this.bl + this.bx, this.bm + this.by);
        }
        if (this.bz < 25) {
            this.bo = 1;
        }
        final int n17 = -3;
        for (int n18 = 0; n18 < super.l; ++n18) {
            final int n19 = this.bl + 2 + this.br / 2 + this.bt * n18 + this.br * n18;
            graphics.setColor(super.E);
            if (n18 != 0) {
                final int n20 = n19 - this.br / 2 - 1;
                if (super.Z) {
                    if (super.C.b(super.bj, b("<y#")) || super.C.b(super.bj, b("%Z!4\u0002"))) {
                        graphics.drawLine(n20, this.bm + this.by - 1, n20, this.bm);
                        graphics.drawLine(n20 + 1, this.bm + this.by - 1, n20 + 1, this.bm);
                    }
                    graphics.setColor(super.h);
                    graphics.drawLine(n20, this.bm + this.by + 6, n20, this.bm + this.by);
                    graphics.drawLine(n20 + 1, this.bm + this.by + 6, n20 + 1, this.bm + this.by);
                }
                else {
                    if (super.C.b(super.bj, b("<y#")) || super.C.b(super.bj, b("%Z!4\u0002"))) {
                        graphics.drawLine(n20, this.bm + this.by, n20, this.bm);
                    }
                    graphics.setColor(super.h);
                    graphics.drawLine(n20, this.bm + this.by + 6, n20, this.bm + this.by);
                }
            }
            graphics.setColor(super.h);
            if (!super.L) {
                if (super.k.b[n18] != null) {
                    final int n21 = n19 + this.bt / 2 - super.C.a(super.k.b[n18], font);
                    if (!super.K) {
                        graphics.drawString(super.k.b[n18], n21, this.bm + this.by + 20);
                    }
                    else if (n != 0) {
                        graphics.drawString(super.k.b[n18], n21, this.bm + this.by + 20);
                    }
                    else {
                        graphics.drawString(super.k.b[n18], n21, this.bm + this.by + 25 + super.c);
                    }
                }
                if (n != 0) {
                    n = 0;
                }
                else {
                    n = 1;
                }
            }
            else if (super.k.b[n18] != null) {
                final Image a = super.C.a(super.k.b[n18], font, true, super.h, super.g);
                if (a != null) {
                    graphics.drawImage(a, n19 + this.bt / 2 - super.c / 2, this.bm + this.by + 10, null);
                }
            }
            if (super.bh.compareTo(b("0`#,\u0012OQ\r9\t")) == 0) {
                switch (this.bL) {
                    case 1: {
                        this.bP = this.by;
                        break;
                    }
                }
                for (int n22 = 0; n22 < this.bA; ++n22) {
                    final p p = super.k.a.elementAt(n22);
                    Color color = p.c;
                    if (this.bD[n18][n22] != 0 || this.bE[n18][n22] != 0) {
                        int abs;
                        int u;
                        if (this.bD[n18][n22] != 0 && this.bE[n18][n22] == 0) {
                            abs = this.bD[n18][n22];
                            u = this.bm + this.bP - abs - this.bD[n18][this.bA];
                        }
                        else {
                            abs = Math.abs(this.bE[n18][n22]);
                            u = this.bm + this.bP + this.bE[n18][this.bA];
                        }
                        final int t = n19;
                        if (p.a[n18].g != null) {
                            color = p.a[n18].g;
                        }
                        graphics.setColor(color);
                        graphics.fillRect(t, u, this.bu, abs);
                        if (!super.F && p.a[n18] != null) {
                            p.a[n18].t = t;
                            p.a[n18].u = u;
                            p.a[n18].v = t + this.bu;
                            p.a[n18].w = u + abs;
                        }
                        final int[] array = this.bD[n18];
                        final int ba = this.bA;
                        array[ba] += this.bD[n18][n22];
                        final int[] array2 = this.bE[n18];
                        final int ba2 = this.bA;
                        array2[ba2] += Math.abs(this.bE[n18][n22]);
                    }
                }
                for (int n23 = 0; n23 < super.l; ++n23) {
                    this.bD[n23][this.bA] = 0;
                    this.bE[n23][this.bA] = 0;
                }
            }
            else {
                for (int n24 = 0; n24 < this.bA; ++n24) {
                    final p p2 = super.k.a.elementAt(n24);
                    Color color2 = p2.c;
                    if (this.bD[n18][n24] != 0) {
                        int n25 = this.bD[n18][n24];
                        int bm = 0;
                        final int t2 = n19 + this.bu * n24 + this.bs * n24;
                        if (p2.a[n18].g != null) {
                            color2 = p2.a[n18].g;
                        }
                        graphics.setColor(color2);
                        switch (this.bL) {
                            case 1: {
                                bm = this.bm + this.by - n25;
                                graphics.fillRect(t2, bm, this.bu, n25);
                                break;
                            }
                            case 2: {
                                final boolean b = n25 < 0;
                                if (super.bc) {
                                    if (!b) {
                                        bm = this.bm + this.bP - n25;
                                    }
                                    else {
                                        bm = this.bm + this.bP;
                                        n25 = Math.abs(n25);
                                    }
                                }
                                else if (n25 >= 0) {
                                    bm = this.bm + this.by / 2 - n25;
                                    n25 += this.by / 2;
                                }
                                else {
                                    bm = this.bm + Math.abs(n25) + this.by / 2;
                                    n25 = this.by / 2 - Math.abs(n25);
                                }
                                graphics.fillRect(t2, bm, this.bu, n25);
                                break;
                            }
                            case 3: {
                                if (super.bc) {
                                    n25 = Math.abs(n25);
                                    bm = this.bm;
                                }
                                else {
                                    bm = this.bm + Math.abs(n25);
                                    n25 = this.by - Math.abs(n25);
                                }
                                graphics.fillRect(t2, bm, this.bu, n25);
                                break;
                            }
                        }
                        if (!super.F && p2.a[n18] != null) {
                            p2.a[n18].t = t2;
                            p2.a[n18].u = bm;
                            p2.a[n18].v = t2 + this.bu;
                            p2.a[n18].w = bm + n25;
                        }
                        final int[] array3 = this.bD[n18];
                        final int ba3 = this.bA;
                        array3[ba3] += this.bD[n18][n24];
                    }
                }
            }
        }
        if (super.be) {
            if (super.bh.compareTo(b(":g -\u000b\u0018q\r9\t")) == 0) {
                for (int n26 = 0; n26 < super.l; ++n26) {
                    final int n27 = this.bl + 2 + this.br / 2 + this.bt * n26 + this.br * n26;
                    for (int n28 = 0; n28 < this.bA; ++n28) {
                        final p p3 = super.k.a.elementAt(n28);
                        if (this.bD[n26][n28] != 0) {
                            int abs2 = this.bD[n26][n28];
                            final int n29 = n27 + this.bu * n28 + this.bs * n28;
                            switch (this.bL) {
                                case 1: {
                                    final int n30 = this.bm + this.by - abs2;
                                    n2 = n29;
                                    n3 = n30 - 3;
                                    if (n3 < this.bm) {
                                        n3 = n3 + 6 + super.c;
                                        break;
                                    }
                                    break;
                                }
                                case 2: {
                                    final boolean b2 = abs2 < 0;
                                    int n31;
                                    if (super.bc) {
                                        if (!b2) {
                                            n31 = this.bm + this.bP - abs2;
                                        }
                                        else {
                                            n31 = this.bm + this.bP;
                                            abs2 = Math.abs(abs2);
                                        }
                                    }
                                    else if (abs2 >= 0) {
                                        n31 = this.bm + this.by / 2 - abs2;
                                        abs2 += this.by / 2;
                                    }
                                    else {
                                        n31 = this.bm + Math.abs(abs2) + this.by / 2;
                                        abs2 = this.by / 2 - Math.abs(abs2);
                                    }
                                    n2 = n29;
                                    if (!b2) {
                                        n3 = n31 - 3;
                                        if (n3 < this.bm) {
                                            n3 = n3 + 6 + super.c;
                                            break;
                                        }
                                        break;
                                    }
                                    else {
                                        n3 = n31 + abs2 + 3 + super.c;
                                        if (n3 > this.bm + this.by) {
                                            n3 = n3 - 6 - super.c;
                                            break;
                                        }
                                        break;
                                    }
                                    break;
                                }
                                case 3: {
                                    int abs3;
                                    int bm2;
                                    if (super.bc) {
                                        abs3 = Math.abs(abs2);
                                        bm2 = this.bm;
                                    }
                                    else {
                                        bm2 = this.bm + Math.abs(abs2);
                                        abs3 = this.by - Math.abs(abs2);
                                    }
                                    n2 = n29;
                                    n3 = bm2 + abs3 + 3 + super.c;
                                    if (n3 > this.bm + this.by) {
                                        n3 = n3 - 6 - super.c;
                                        break;
                                    }
                                    break;
                                }
                            }
                            graphics.setColor(super.h);
                            final String m = p3.a[n26].k;
                            n2 += super.C.a(m, font, this.bu);
                            graphics.drawString(m, n2, n3);
                            final int[] array4 = this.bD[n26];
                            final int ba4 = this.bA;
                            array4[ba4] += this.bD[n26][n28];
                        }
                    }
                }
            }
            else {
                graphics.setColor(super.h);
                for (int n32 = 0; n32 < super.l; ++n32) {
                    final String s = this.bS[n32];
                    final String s2 = this.bT[n32];
                    int n33 = 0;
                    for (int n34 = 0; n34 <= this.bA; ++n34) {
                        n33 += this.bD[n32][n34];
                    }
                    if (n33 > 0) {
                        int n35 = this.bm + this.bP - n33 - 3;
                        final int n36 = this.bl + 2 + this.br / 2 + this.bt * n32 + this.br * n32 + super.C.a(s, font, this.bu);
                        if (n35 - super.c < this.bm) {
                            n35 = n35 + n17 + 6 + super.c;
                        }
                        graphics.drawString(s, n36, n35);
                    }
                    int n37 = 0;
                    for (int n38 = 0; n38 <= this.bA; ++n38) {
                        n37 += this.bE[n32][n38];
                    }
                    if (n37 < 0) {
                        int n39 = this.bm + this.bP - n37 + super.c + 3;
                        final int n40 = this.bl + 2 + this.br / 2 + this.bt * n32 + this.br * n32 + super.C.a(s2, font, this.bu);
                        if (n39 + super.c > this.bm + this.by) {
                            n39 = n39 + n17 - 6 - super.c;
                        }
                        graphics.drawString(s2, n40, n39);
                    }
                }
            }
        }
        graphics.setColor(super.h);
        if (this.bv <= 0.0 || this.bw < 0.0) {
            this.bP += this.bm;
            if (super.Z) {
                graphics.drawLine(this.bl, this.bP, this.bl + this.bx, this.bP);
                graphics.drawLine(this.bl, this.bP + 1, this.bl + this.bx, this.bP + 1);
                for (int n41 = 1; n41 < super.l; ++n41) {
                    final int n42 = this.bl + 1 + this.bt * n41 + this.br * n41;
                    graphics.drawLine(n42, this.bP - 2, n42, this.bP + 3);
                    graphics.drawLine(n42 + 1, this.bP - 2, n42 + 1, this.bP + 3);
                }
            }
            else {
                graphics.drawLine(this.bl, this.bP, this.bl + this.bx, this.bP);
                for (int n43 = 1; n43 < super.l; ++n43) {
                    final int n44 = this.bl + 1 + this.bt * n43 + this.br * n43;
                    graphics.drawLine(n44, this.bP - 2, n44, this.bP + 2);
                }
            }
        }
        for (int n45 = 0; n45 < super.l; ++n45) {
            this.bD[n45][this.bA] = 0;
        }
        this.b(graphics);
        super.C.a(graphics, this);
        super.F = true;
    }
    
    public void a(final Graphics graphics, final o o) {
        graphics.setColor(super.D);
        graphics.fillRect(o.t, o.u, o.v - o.t, o.w - o.u);
    }
    
    public o a(final int n, final int n2) {
        for (int i = 0; i < this.bA; ++i) {
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
                graphics.drawString(super.N, this.bl + super.C.a(super.N, font, this.bx), this.bm + this.by + super.M + super.c + 17);
            }
            else {
                graphics.drawString(super.N, this.bl + super.C.a(super.N, font, this.bx), this.bm + super.b);
            }
        }
        if (super.O != null) {
            super.C.a(this.bn + 5, this.bm, graphics, super.O, super.c + 2, this.by);
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
                    c2 = '}';
                    break;
                }
                case 1: {
                    c2 = '\u0015';
                    break;
                }
                case 2: {
                    c2 = 'O';
                    break;
                }
                case 3: {
                    c2 = 'X';
                    break;
                }
                default: {
                    c2 = '{';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
