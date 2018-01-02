// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Polygon;
import java.awt.Graphics;
import java.text.NumberFormat;
import java.awt.Font;
import java.awt.Color;

public class k extends b
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
    private String[] bA;
    private int bB;
    private int bC;
    private int bD;
    private double bE;
    private int bF;
    private int bG;
    private double bH;
    private double bI;
    private int bJ;
    private int bK;
    private boolean bL;
    private boolean bM;
    
    public k(final int bl, final int n, final int a, final int b, final Color g, final Color h, final int c) {
        this.bv = false;
        this.bw = false;
        this.bx = 4;
        this.bB = 1;
        this.bC = -1;
        this.bL = true;
        this.bM = false;
        super.a = a;
        super.b = b;
        super.g = g;
        super.h = h;
        this.bl = bl;
        this.bm = n + 10;
        super.c = c;
        this.bp = super.a - 10;
        this.bq = super.b - 30;
    }
    
    public boolean a(final int l, final n k, final String s, final String s2) {
        super.l = l;
        super.k = k;
        super.m = super.k.a.size();
        final double[] array = new double[11];
        final Font font = new Font(super.f, 0, super.c);
        if (!super.t || !super.v) {
            final double n = 0.0;
            this.bt = n;
            this.bs = n;
            for (int i = 0; i < super.m; ++i) {
                final p p4 = super.k.a.elementAt(i);
                for (int j = 0; j < super.l; ++j) {
                    if (p4.a[j] != null && !p4.a[j].s) {
                        if (p4.a[j].a > this.bs) {
                            this.bs = p4.a[j].a;
                        }
                        if (p4.a[j].a < this.bt) {
                            this.bt = p4.a[j].a;
                        }
                    }
                }
            }
            if (this.bs == 0.0 && this.bt == 0.0) {
                this.bs = 5.0;
                this.bt = 0.0;
            }
        }
        if (super.t) {
            this.bt = super.n;
        }
        if (super.v) {
            this.bs = super.o;
        }
        this.bI = super.l;
        this.bG = (int)(360.0 / this.bI);
        final double[] a = super.C.a(this.bs, this.bt, super.t, super.v, super.R, super.S);
        this.bs = a[0];
        this.bt = a[1];
        this.bE = a[2];
        this.bB = (int)a[3];
        final int n2 = (int)a[4];
        this.bA = new String[this.bB + 1];
        for (int bc = 0; bc <= this.bB; ++bc) {
            array[bc] = super.C.e(this.bt, super.C.d(this.bE, this.bB - bc));
            if (array[bc] == 0.0) {
                this.bC = bc;
            }
        }
        final NumberFormat d = super.C.d(super.bg);
        d.setMaximumFractionDigits(n2);
        d.setMinimumFractionDigits(n2);
        try {
            for (int n3 = 0; n3 <= this.bB; ++n3) {
                this.bA[n3] = String.valueOf(s2) + d.format(array[n3]) + s;
            }
        }
        catch (Exception ex) {}
        int c = 0;
        for (int n4 = 0; n4 < this.bI; ++n4) {
            final String s3 = super.k.b[n4];
            if (super.C.c(s3, font) > c) {
                c = super.C.c(s3, font);
            }
        }
        this.bl += c;
        this.bm += super.c + super.c / 2;
        this.bp -= c * 2;
        this.bq -= super.c;
        this.bF = Math.min(this.bp, this.bq) / 2;
        this.bH = this.bs - this.bt;
        this.bJ = this.bl + this.bp / 2;
        this.bK = this.bm + this.bq / 2;
        this.bo = this.bF / this.bB;
        this.bF = this.bo * this.bB;
        for (int n5 = 0; n5 < super.m; ++n5) {
            final p p5 = super.k.a.elementAt(n5);
            for (int n6 = 0; n6 < super.l; ++n6) {
                if (p5.a[n6] != null) {
                    if (!p5.a[n6].s && this.bH != 0.0) {
                        final int n7 = (int)((p5.a[n6].a - this.bt) / this.bH * this.bF);
                        final int n8 = this.bG * n6 - 90;
                        p5.a[n6].n = (int)(this.bJ + n7 * Math.cos(super.C.i(n8)));
                        p5.a[n6].o = (int)(this.bK + n7 * Math.sin(super.C.i(n8)));
                    }
                    else {
                        p5.a[n6].n = -1;
                    }
                }
            }
        }
        return true;
    }
    
    public void a(final Graphics graphics) {
        int n = 0;
        final Font font = new Font(super.f, 0, super.c);
        graphics.setFont(font);
        graphics.setColor(super.E);
        if (!super.C.b(super.bj, b("V%`N")) && !super.Z) {
            for (int i = 0; i < this.bB; ++i) {
                int n2 = 0;
                final int n3 = (i + 1) * this.bo;
                for (int j = 0; j <= super.l; ++j) {
                    final int n4 = this.bG * j - 90;
                    final int n5 = (int)(this.bJ + n3 * Math.cos(super.C.i(n4)));
                    final int n6 = (int)(this.bK + n3 * Math.sin(super.C.i(n4)));
                    if (n2 != 0) {
                        graphics.drawLine(n2, n, n5, n6);
                    }
                    n2 = n5;
                    n = n6;
                }
            }
        }
        if (!this.bL) {
            graphics.setColor(super.h);
            for (int n7 = 0; n7 < this.bI; ++n7) {
                final int n8 = this.bG * n7 - 90;
                graphics.drawLine(this.bJ, this.bK, (int)(this.bJ + this.bF * Math.cos(super.C.i(n8))), (int)(this.bK + this.bF * Math.sin(super.C.i(n8))));
            }
        }
        if (this.bL) {
            for (int k = 0; k < super.m; ++k) {
                final p p = super.k.a.elementAt(k);
                graphics.setColor(p.c);
                final Polygon polygon = new Polygon();
                for (int l = 0; l < super.l; ++l) {
                    int n9;
                    if (l == super.l) {
                        n9 = 0;
                    }
                    else {
                        n9 = l;
                    }
                    int n10;
                    int n11;
                    if (p.a[n9] != null && !p.a[n9].s) {
                        n10 = p.a[n9].n;
                        n11 = p.a[n9].o;
                    }
                    else {
                        n10 = this.bJ;
                        n11 = this.bK;
                    }
                    polygon.addPoint(n10, n11);
                }
                graphics.fillPolygon(polygon);
            }
        }
        for (int n12 = 0; n12 < super.m; ++n12) {
            final p p2 = super.k.a.elementAt(n12);
            if (this.bL) {
                graphics.setColor(p2.c.darker());
            }
            else {
                graphics.setColor(p2.c);
            }
            int n13 = 0;
            int n14 = 0;
            for (int n15 = 0; n15 <= super.l; ++n15) {
                int n16;
                if (n15 == super.l) {
                    n16 = 0;
                }
                else {
                    n16 = n15;
                }
                if (p2.a[n16] != null && !p2.a[n16].s) {
                    final int n17 = p2.a[n16].n;
                    final int o = p2.a[n16].o;
                    if (n13 != 0) {
                        if (this.bL) {
                            graphics.drawLine(n13, n14, n17, o);
                        }
                        else {
                            super.C.a(graphics, n13, n14, n17, o, 2, p2.c);
                        }
                    }
                    n13 = n17;
                    n14 = o;
                    if (!super.F) {
                        p2.a[n16].t = n17 - this.bx;
                        p2.a[n16].u = o - this.bx;
                        p2.a[n16].v = n17 + this.bx;
                        p2.a[n16].w = o + this.bx;
                    }
                }
            }
        }
        graphics.setColor(super.h);
        for (int n18 = 0; n18 < this.bI; ++n18) {
            final int n19 = this.bG * n18 - 90;
            final int n20 = (int)(this.bJ + this.bF * Math.cos(super.C.i(n19)));
            final int n21 = (int)(this.bK + this.bF * Math.sin(super.C.i(n19)));
            if (this.bL) {
                graphics.drawLine(this.bJ, this.bK, n20, n21);
            }
            if (n18 == 0) {
                for (int n22 = 0; n22 <= this.bB; ++n22) {
                    final int n23 = n22 * this.bo;
                    final int n24 = -90;
                    final int n25 = (int)(this.bJ + n23 * Math.cos(super.C.i(n24)));
                    final int n26 = (int)(this.bK + n23 * Math.sin(super.C.i(n24)));
                    graphics.drawString(this.bA[this.bB - n22], n25 + 5, n26 + super.c / 2);
                    if (n22 != 0) {
                        graphics.drawLine(n25 - 2, n26, n25 + 2, n26);
                    }
                }
            }
            final String s = super.k.b[n18];
            if (s != null) {
                final int[] a = super.C.a(s, font, super.c, n19, (int)(this.bJ + (this.bF + super.c / 2) * Math.cos(super.C.i(n19))), (int)(this.bK + (this.bF + super.c / 2) * Math.sin(super.C.i(n19))));
                graphics.drawString(s, a[0], a[1]);
            }
        }
        if (!this.bL && this.bM) {
            for (int n27 = 0; n27 < super.m; ++n27) {
                final p p3 = super.k.a.elementAt(n27);
                graphics.setColor(p3.c);
                for (int n28 = 0; n28 < super.l; ++n28) {
                    if (p3.a[n28] != null && !p3.a[n28].s) {
                        graphics.fillRect(p3.a[n28].n - this.bx, p3.a[n28].o - this.bx, this.bx * 2, this.bx * 2);
                    }
                }
            }
        }
        super.C.a(graphics, this);
        super.F = true;
    }
    
    public void a(final Graphics graphics, final o o) {
        graphics.setColor(super.D);
        for (int i = 0; i < super.m; ++i) {
            final p p2 = super.k.a.elementAt(i);
            for (int j = 0; j < super.l; ++j) {
                if (o == p2.a[j]) {
                    graphics.fillRect(p2.a[j].t, p2.a[j].u, this.bx * 2, this.bx * 2);
                    break;
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
    
    private static String b(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '\u0018';
                    break;
                }
                case 1: {
                    c2 = 'J';
                    break;
                }
                case 2: {
                    c2 = '\u000e';
                    break;
                }
                case 3: {
                    c2 = '+';
                    break;
                }
                default: {
                    c2 = 'w';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
