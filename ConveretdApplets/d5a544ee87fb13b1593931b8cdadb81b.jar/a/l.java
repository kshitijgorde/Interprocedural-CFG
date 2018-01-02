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

public class l extends b
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
    private int bv;
    private int bw;
    private int bx;
    private Color by;
    private boolean bz;
    private String bA;
    private String bB;
    private int bC;
    private int bD;
    private int bE;
    private String[] bF;
    private int bG;
    private int bH;
    private int bI;
    private double bJ;
    private int bK;
    
    public l(final int bn, final int bm, final int a, final int b, final Color g, final Color h, final int c) {
        this.bo = 3;
        this.br = 3;
        this.by = new Color(0, 0, 192);
        this.bz = false;
        this.bE = 1;
        this.bG = 1;
        this.bH = -1;
        super.a = a;
        super.b = b;
        super.g = g;
        super.h = h;
        this.bl = bn + 20;
        this.bm = bm;
        this.bn = bn;
        super.c = c;
        this.bv = super.a - 40;
        this.bw = super.b - 25;
    }
    
    public boolean a(final int l, final p j, final String s, final String s2) {
        super.l = l;
        super.j = j;
        final double[] array = new double[11];
        final Font font = new Font(super.f, 0, super.c);
        if (super.N != null) {
            this.bw -= 5 + (super.c + 2);
        }
        if (super.O != null) {
            this.bl += 15 + (super.c + 2) / 2;
            this.bv -= 15 + (super.c + 2) / 2;
        }
        if (!super.t || !super.v) {
            for (int i = 0; i < super.l; ++i) {
                if (!super.j.a[i].s) {
                    if (!this.bz) {
                        this.bz = true;
                        this.bt = super.j.a[i].a;
                        this.bu = this.bt;
                    }
                    else {
                        if (super.j.a[i].a > this.bt) {
                            this.bt = super.j.a[i].a;
                        }
                        if (super.j.a[i].a < this.bu) {
                            this.bu = super.j.a[i].a;
                        }
                    }
                }
            }
        }
        if (!this.bz || (this.bt == 0.0 && this.bu == 0.0)) {
            this.bt = 4.0;
            this.bu = 0.0;
        }
        if (super.t) {
            this.bu = super.n;
        }
        if (super.v) {
            this.bt = super.o;
        }
        if (this.bu >= 0.0) {
            this.bE = 1;
        }
        else if (this.bt < 0.0) {
            this.bE = 3;
        }
        else {
            this.bE = 2;
        }
        final double[] a = super.C.a(this.bt, this.bu, super.t, super.v, super.R, super.S);
        this.bt = a[0];
        this.bu = a[1];
        this.bJ = a[2];
        this.bG = (int)a[3];
        final int n = (int)a[4];
        this.bF = new String[this.bG + 1];
        for (int k = 0; k <= this.bG; ++k) {
            array[k] = super.C.e(this.bu, super.C.d(this.bJ, this.bG - k));
            if (array[k] == 0.0) {
                this.bH = k;
            }
        }
        final NumberFormat d = super.C.d(super.bg);
        d.setMaximumFractionDigits(n);
        d.setMinimumFractionDigits(n);
        try {
            for (int n2 = 0; n2 <= this.bG; ++n2) {
                this.bF[n2] = String.valueOf(s2) + d.format(array[n2]) + s;
            }
        }
        catch (Exception ex) {}
        if (!super.bd) {
            this.bC = Math.max(super.C.c(String.valueOf(s2) + this.bF[0] + s, font), super.C.c(String.valueOf(s2) + this.bF[this.bG] + s, font));
            this.bl += this.bC;
            this.bv -= this.bC;
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
            this.bv = this.bv - n3 - 3;
        }
        if (super.l > 1) {
            this.bq = this.bv / (super.l - 1);
            this.bp = this.bq * (super.l - 1);
            this.bs = (this.bv - (this.br + this.br) * super.l + 1) / super.l;
        }
        else {
            this.bq = this.bv;
            this.bp = this.bq;
            this.bs = 1;
        }
        if (super.Y != 0 && this.bs > super.Y) {
            this.bs = super.Y;
        }
        final String[] array2 = new String[super.l];
        for (int n5 = 0; n5 < super.l; ++n5) {
            array2[n5] = super.j.a[n5].f;
        }
        if (!super.L) {
            final int a2 = super.C.a(array2, super.l, this.bs, this.bl, super.a, font);
            if (a2 == 2) {
                super.K = true;
            }
            else if (a2 == 3) {
                super.L = true;
                super.M = super.C.a(array2, super.l, font);
                this.bw -= super.M;
            }
            if (super.K) {
                this.bw -= super.c + 5;
                if (super.N != null) {
                    this.bw -= super.c + 2;
                }
                this.bK = this.bw / this.bG;
                this.bw = this.bK * this.bG;
            }
        }
        this.bK = this.bw / this.bG;
        this.bw = this.bK * this.bG;
        switch (this.bE) {
            case 1: {
                for (int n6 = 0; n6 < super.l; ++n6) {
                    if (!super.j.a[n6].s) {
                        super.j.a[n6].n = (int)((super.j.a[n6].a - this.bu) / (this.bt - this.bu) * this.bw);
                        if (super.j.a[n6].n > this.bw) {
                            super.j.a[n6].n = this.bw;
                        }
                    }
                }
                break;
            }
            case 2: {
                for (int n7 = 0; n7 < super.l; ++n7) {
                    if (!super.j.a[n7].s) {
                        double n8;
                        if (super.j.a[n7].a < this.bu) {
                            n8 = this.bu;
                        }
                        else if (super.j.a[n7].a > this.bt) {
                            n8 = this.bt;
                        }
                        else {
                            n8 = super.j.a[n7].a;
                        }
                        if (this.bE == 1) {
                            super.j.a[n7].n = (int)((n8 - this.bu) / (this.bt - this.bu) * this.bw);
                        }
                        else if (this.bE == 2) {
                            super.j.a[n7].n = (int)(n8 / (this.bt - this.bu) * this.bw);
                        }
                        else {
                            super.j.a[n7].n = (int)(this.bw - (Math.abs(n8) + this.bt) / (this.bt - this.bu) * this.bw);
                        }
                    }
                }
                break;
            }
            case 3: {
                for (int n9 = 0; n9 < super.l; ++n9) {
                    if (!super.j.a[n9].s) {
                        super.j.a[n9].n = (int)((this.bt + super.j.a[n9].a) / (this.bt - this.bu) * this.bw);
                        if (super.j.a[n9].n > this.bw) {
                            super.j.a[n9].n = this.bw;
                        }
                    }
                }
                break;
            }
        }
        if (this.bt >= 0.0 && this.bu < 0.0) {
            this.bI = (int)(this.bw - (0.0 - this.bu) / (this.bt - this.bu) * this.bw);
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
                super.bj = b("C{\u007fB>");
            }
            for (int i = 0; i < super.H; ++i) {
                final q q = super.J[i];
                if (q != null) {
                    if (q.a(this.bu, this.bt)) {
                        final int n4 = (int)(this.bw - (q.a() - this.bu) / (this.bt - this.bu) * this.bw);
                        graphics.setColor(q.d);
                        graphics.drawLine(this.bl, this.bm + n4, this.bl + this.bv, this.bm + n4);
                        if (q.e != null) {
                            final int n5 = this.bl + this.bv + 3;
                            final int n6 = this.bm + n4 + super.C.b(q.e, font) / 2 - 1;
                            graphics.setColor(super.h);
                            graphics.drawString(q.e, n5, n6);
                        }
                    }
                    else if (q.b(this.bu, this.bt)) {
                        final double[] c = q.c(this.bu, this.bt);
                        final double n7 = c[0];
                        final double n8 = c[1];
                        final int n9 = (int)(this.bw - (n7 - this.bu) / (this.bt - this.bu) * this.bw);
                        final int n10 = (int)(this.bw - (n8 - this.bu) / (this.bt - this.bu) * this.bw);
                        final int n11 = n9 - n10;
                        if (n11 > 0) {
                            graphics.setColor(q.d);
                            graphics.fillRect(this.bl, this.bm + n10, this.bv, n11);
                        }
                        if (q.e != null) {
                            final int n12 = this.bl + this.bv + 3;
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
            graphics.drawLine(this.bl, this.bm + this.bw + 6, this.bl, this.bm + this.bw);
            graphics.drawLine(this.bl + 1, this.bm + this.bw + 6, this.bl + 1, this.bm + this.bw);
            graphics.drawLine(this.bl + this.bv, this.bm + this.bw + 6, this.bl + this.bv, this.bm + this.bw + 2);
            graphics.drawLine(this.bl - 1 + this.bv, this.bm + this.bw + 6, this.bl - 1 + this.bv, this.bm + this.bw + 2);
            if (!super.C.b(super.bj, b("U[\u007fK"))) {
                graphics.drawLine(this.bl + this.bv, this.bm - 1, this.bl + this.bv, this.bm + this.bw);
                graphics.drawLine(this.bl + this.bv - 1, this.bm - 1, this.bl + this.bv - 1, this.bm + this.bw);
                graphics.drawLine(this.bl, this.bm, this.bl + this.bv, this.bm);
                graphics.drawLine(this.bl, this.bm + 1, this.bl + this.bv, this.bm + 1);
            }
            if (super.C.b(super.bj, b("ZX}")) || super.C.b(super.bj, b("B{\u007fB>"))) {
                for (int j = 1; j <= this.bG; ++j) {
                    graphics.drawLine(this.bl, this.bm + j * this.bK, this.bl + this.bv, this.bm + j * this.bK);
                    graphics.drawLine(this.bl, this.bm + 1 + j * this.bK, this.bl + this.bv, this.bm + 1 + j * this.bK);
                }
            }
            graphics.setColor(super.h);
            if (!super.bd) {
                for (int k = 0; k <= this.bG; ++k) {
                    graphics.drawString(this.bF[k], this.bl - 15 - super.C.c(this.bF[k], font), this.bm + k * this.bK + super.c / 2);
                }
            }
            graphics.setColor(super.h);
            graphics.drawLine(this.bl - 6, this.bm + this.bw, this.bl, this.bm + this.bw);
            graphics.drawLine(this.bl - 6, this.bm + 1 + this.bw, this.bl, this.bm + 1 + this.bw);
            graphics.drawLine(this.bl, this.bm - 1, this.bl, this.bm + this.bw + 6);
            graphics.drawLine(this.bl + 1, this.bm - 1, this.bl + 1, this.bm + this.bw + 6);
            graphics.drawLine(this.bl, this.bm + this.bw, this.bl + this.bv, this.bm + this.bw);
            graphics.drawLine(this.bl, this.bm + this.bw + 1, this.bl + this.bv, this.bm + this.bw + 1);
            for (int l = 0; l <= this.bG; ++l) {
                graphics.drawLine(this.bl - 6, this.bm + l * this.bK, this.bl, this.bm + l * this.bK);
                graphics.drawLine(this.bl - 6, this.bm + 1 + l * this.bK, this.bl, this.bm + 1 + l * this.bK);
            }
        }
        else {
            graphics.drawLine(this.bl, this.bm + this.bw + 6, this.bl, this.bm + this.bw);
            graphics.drawLine(this.bl + this.bv, this.bm + this.bw + 6, this.bl + this.bv, this.bm + this.bw);
            graphics.drawLine(this.bl - 6, this.bm + this.bw, this.bl, this.bm + this.bw);
            if (!super.C.b(super.bj, b("U[\u007fK"))) {
                graphics.drawRect(this.bl, this.bm, this.bv, this.bw);
            }
            if (super.C.b(super.bj, b("ZX}")) || super.C.b(super.bj, b("B{\u007fB>"))) {
                for (int n14 = 0; n14 <= this.bG; ++n14) {
                    graphics.drawLine(this.bl, this.bm + n14 * this.bK, this.bl + this.bv, this.bm + n14 * this.bK);
                }
            }
            graphics.setColor(super.h);
            if (!super.bd) {
                for (int n15 = 0; n15 <= this.bG; ++n15) {
                    graphics.drawString(this.bF[n15], this.bl - 15 - super.C.c(this.bF[n15], font), this.bm + n15 * this.bK + super.c / 2);
                }
            }
            graphics.setColor(super.h);
            graphics.drawLine(this.bl - 6, this.bm + this.bw, this.bl, this.bm + this.bw);
            graphics.drawLine(this.bl, this.bm, this.bl, this.bm + this.bw + 6);
            graphics.drawLine(this.bl, this.bm + this.bw, this.bl + this.bv, this.bm + this.bw);
            for (int n16 = 0; n16 <= this.bG; ++n16) {
                graphics.drawLine(this.bl - 6, this.bm + n16 * this.bK, this.bl, this.bm + n16 * this.bK);
            }
        }
        int bm = 0;
        final int n17 = this.bs + this.br + this.br;
        for (int n18 = 0; n18 < super.l; ++n18) {
            final int t = this.bl + 1 + this.br + n18 * n17;
            if (!super.j.a[n18].s) {
                if (super.j.a[n18].g == null) {
                    graphics.setColor(this.by);
                }
                else {
                    graphics.setColor(super.j.a[n18].g);
                }
                int n19 = super.j.a[n18].n;
                switch (this.bE) {
                    case 1: {
                        bm = this.bm + this.bw - n19;
                        graphics.fillRect(t, bm, this.bs, n19);
                        if (!super.be) {
                            break;
                        }
                        n2 = t;
                        n3 = bm - 3;
                        if (n3 - super.c < this.bm) {
                            n3 = n3 + 6 + super.c;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        final boolean b = n19 <= 0;
                        if (super.bc) {
                            if (!b) {
                                bm = this.bm + this.bI - n19;
                            }
                            else {
                                bm = this.bm + this.bI;
                                n19 = Math.abs(n19);
                            }
                        }
                        else {
                            bm = this.bm - n19 + this.bI;
                            n19 = n19 + this.bw - this.bI;
                        }
                        graphics.fillRect(t, bm, this.bs, n19);
                        if (!super.be) {
                            break;
                        }
                        n2 = t;
                        if (!b) {
                            n3 = bm - 3;
                            if (n3 < this.bm) {
                                n3 = n3 + 6 + super.c;
                                break;
                            }
                            break;
                        }
                        else {
                            n3 = bm + n19 + 3 + super.c;
                            if (n3 > this.bm + this.bw) {
                                n3 = n3 - 6 - super.c;
                                break;
                            }
                            break;
                        }
                        break;
                    }
                    case 3: {
                        n19 = Math.abs(n19);
                        if (!super.bc) {
                            bm = this.bm + n19;
                            n19 = this.bw - n19;
                        }
                        else {
                            bm = this.bm;
                        }
                        graphics.fillRect(t, bm, this.bs, n19);
                        if (!super.be) {
                            break;
                        }
                        n2 = t;
                        n3 = bm + n19 + 3 + super.c;
                        if (n3 > this.bm + this.bw) {
                            n3 = n3 - 6 - super.c;
                            break;
                        }
                        break;
                    }
                }
                if (!super.F && super.j.a[n18] != null) {
                    super.j.a[n18].t = t;
                    super.j.a[n18].u = bm;
                    super.j.a[n18].v = t + this.bs;
                    super.j.a[n18].w = bm + n19;
                }
                if (super.be) {
                    graphics.setColor(super.h);
                    final String m = super.j.a[n18].k;
                    n2 += super.C.a(m, font, this.bs);
                    graphics.drawString(m, n2, n3);
                }
            }
            graphics.setColor(super.h);
            if (!super.L) {
                if (super.j.a[n18].f != null) {
                    final int n20 = this.bl + 1 + this.br + (this.br * 2 + this.bs) * n18 + this.bs / 2 - super.C.a(super.j.a[n18].f, font);
                    if (!super.K) {
                        graphics.drawString(super.j.a[n18].f, n20, this.bm + this.bw + 20);
                    }
                    else if (n != 0) {
                        graphics.drawString(super.j.a[n18].f, n20, this.bm + this.bw + 20);
                    }
                    else {
                        graphics.drawString(super.j.a[n18].f, n20, this.bm + this.bw + 25 + super.c);
                    }
                }
                if (n != 0) {
                    n = 0;
                }
                else {
                    n = 1;
                }
            }
            else if (super.j.a[n18].f != null) {
                final int n21 = this.bl + 1 + this.br + (this.br * 2 + this.bs) * n18 + this.bs / 2 - super.c / 2;
                final Image a = super.C.a(super.j.a[n18].f, font, true, super.h, super.g);
                if (a != null) {
                    graphics.drawImage(a, n21, this.bm + this.bw + 10, null);
                }
            }
            graphics.setColor(super.E);
            if (n18 != 0) {
                final int n22 = t - this.br - 1;
                if (super.Z) {
                    if (super.C.b(super.bj, b("ZX}")) || super.C.b(super.bj, b("C{\u007fB>"))) {
                        graphics.drawLine(n22, this.bm + this.bw - 1, n22, this.bm);
                        graphics.drawLine(n22 + 1, this.bm + this.bw - 1, n22 + 1, this.bm);
                    }
                    graphics.setColor(super.h);
                    graphics.drawLine(n22, this.bm + this.bw + 6, n22, this.bm + this.bw);
                    graphics.drawLine(n22 + 1, this.bm + this.bw + 6, n22 + 1, this.bm + this.bw);
                }
                else {
                    if (super.C.b(super.bj, b("ZX}")) || super.C.b(super.bj, b("C{\u007fB>"))) {
                        graphics.drawLine(n22, this.bm + this.bw, n22, this.bm);
                    }
                    graphics.setColor(super.h);
                    graphics.drawLine(n22, this.bm + this.bw + 6, n22, this.bm + this.bw);
                }
            }
        }
        graphics.setColor(super.h);
        if (super.Z) {
            graphics.drawLine(this.bl + this.bv, this.bm + this.bw + 6, this.bl + this.bv, this.bm + this.bw);
            graphics.drawLine(this.bl + this.bv - 1, this.bm + this.bw + 6, this.bl + this.bv - 1, this.bm + this.bw);
        }
        else {
            graphics.drawLine(this.bl + this.bv, this.bm + this.bw + 6, this.bl + this.bv, this.bm + this.bw);
        }
        graphics.setColor(super.h);
        if (this.bt <= 0.0 || this.bu < 0.0) {
            this.bI += this.bm;
            if (super.Z) {
                graphics.drawLine(this.bl, this.bI, this.bl + this.bp, this.bI);
                graphics.drawLine(this.bl, this.bI + 1, this.bl + this.bp, this.bI + 1);
                for (int n23 = 1; n23 < super.l; ++n23) {
                    final int n24 = this.bl + n23 * n17;
                    graphics.drawLine(n24, this.bI - 2, n24, this.bI + 3);
                    graphics.drawLine(n24 + 1, this.bI - 2, n24 + 1, this.bI + 3);
                }
            }
            else {
                graphics.drawLine(this.bl, this.bI, this.bl + this.bp, this.bI);
                for (int n25 = 1; n25 < super.l; ++n25) {
                    final int n26 = this.bl + n25 * n17;
                    graphics.drawLine(n26, this.bI - 2, n26, this.bI + 2);
                }
            }
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
            if (super.L) {
                graphics.drawString(super.N, this.bl + super.C.a(super.N, font, this.bv), this.bm + this.bw + super.M + super.c + 15);
            }
            else {
                graphics.drawString(super.N, this.bl + super.C.a(super.N, font, this.bv), this.bm + super.b);
            }
        }
        if (super.O != null) {
            super.C.a(this.bn + 5, this.bm, graphics, super.O, super.c + 2, this.bw);
        }
    }
    
    public void a(final Color by) {
        this.by = by;
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
                    c2 = '\u001b';
                    break;
                }
                case 1: {
                    c2 = '4';
                    break;
                }
                case 2: {
                    c2 = '\u0011';
                    break;
                }
                case 3: {
                    c2 = '.';
                    break;
                }
                default: {
                    c2 = 'G';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
