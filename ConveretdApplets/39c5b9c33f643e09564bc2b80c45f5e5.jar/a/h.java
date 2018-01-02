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

public class h extends b
{
    private int R;
    private int S;
    private int T;
    private int U;
    private int V;
    private double W;
    private int X;
    private int Y;
    private int Z;
    public double[] ba;
    public int[][] bb;
    private int bc;
    private int bd;
    private int be;
    private String bf;
    private String bg;
    private String bh;
    private String bi;
    private String bj;
    private String bk;
    private String bl;
    
    public h(final int n, final int n2, final int a, final int b, final Color g, final Color h, final int c) {
        this.Z = 20;
        this.bc = 4;
        super.a = a;
        super.b = b;
        super.g = g;
        super.h = h;
        this.R = n + 10;
        this.S = n2 + 10;
        super.c = c;
        this.X = super.a - 30;
        this.Y = super.b - 30;
    }
    
    public boolean a(final int l, final m k, final String s, final String s2) {
        super.l = l;
        super.k = k;
        super.m = super.k.a.size();
        final double[] array = new double[0];
        if (super.l < 2) {
            return false;
        }
        final Font font = new Font(super.f, 0, super.c);
        if (super.D != null) {
            this.Y -= 5 + (super.c + 2);
        }
        if (super.E != null) {
            this.X -= 15 + (super.c + 2) / 2;
        }
        this.ba = new double[super.l];
        this.bb = new int[super.l][super.m + 1];
        for (int i = 0; i < super.l; ++i) {
            for (int j = 0; j <= super.m; ++j) {
                this.bb[i][j] = 0;
            }
            this.ba[i] = 0.0;
        }
        for (int n = 0; n < super.m; ++n) {
            final o o = super.k.a.elementAt(n);
            for (int n2 = 0; n2 < super.l; ++n2) {
                if (o.a[n2] != null && !o.a[n2].k) {
                    final double[] ba = this.ba;
                    final int n3 = n2;
                    ba[n3] += o.a[n2].a;
                }
                else {
                    final double[] ba2 = this.ba;
                    final int n4 = n2;
                    ba2[n4] += 0.0;
                }
            }
        }
        for (int n5 = 0; n5 < super.l; ++n5) {
            if (this.ba[n5] > this.W) {
                this.W = this.ba[n5];
            }
        }
        if (this.W == 0.0) {
            this.W = 6.0;
        }
        double[] array2;
        if (!super.p) {
            array2 = super.v.a(7, 0.0, this.W, true, super.H);
        }
        else if (super.o > this.W) {
            array2 = super.v.a(7, 0.0, super.o, false, super.H);
        }
        else {
            array2 = super.v.a(7, 0.0, this.W, false, super.H);
        }
        this.W = array2[6];
        final int n6 = (int)array2[7];
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(n6);
        instance.setMinimumFractionDigits(n6);
        try {
            this.bl = String.valueOf(s2) + instance.format(array2[0]) + s;
            this.bg = String.valueOf(s2) + instance.format(array2[1]) + s;
            this.bh = String.valueOf(s2) + instance.format(array2[2]) + s;
            this.bi = String.valueOf(s2) + instance.format(array2[3]) + s;
            this.bj = String.valueOf(s2) + instance.format(array2[4]) + s;
            this.bk = String.valueOf(s2) + instance.format(array2[5]) + s;
            this.bf = String.valueOf(s2) + instance.format(this.W) + s;
        }
        catch (Exception ex) {}
        this.bd = super.v.b(this.bf, font);
        if (super.k.b[0] != null && super.v.b(super.k.b[0], font) > this.be) {
            this.be = super.v.b(super.k.b[0], font);
        }
        if (!super.O) {
            this.R += this.be / 2;
        }
        else {
            this.R = this.R + this.be / 2 + this.bd;
        }
        this.X = this.X - this.be / 2 - this.bd;
        this.U = this.Y / 6;
        this.Y = this.U * 6;
        this.V = this.X / (super.l - 1);
        this.T = this.V * (super.l - 1);
        if (!super.B) {
            if (super.K == 1) {
                super.A = super.v.a(super.k, super.l, this.V, super.c);
            }
            if (super.A) {
                this.Y -= super.c + 5;
                if (super.D != null) {
                    this.Y -= super.c + 2;
                }
                this.U = this.Y / 6;
                this.Y = this.U * 6;
            }
        }
        for (int n7 = 0; n7 < super.m; ++n7) {
            final o o2 = super.k.a.elementAt(n7);
            for (int n8 = 0; n8 < super.l; ++n8) {
                if (o2.a[n8] != null && !o2.a[n8].k) {
                    if (o2.a[n8].a != 0.0) {
                        this.bb[n8][n7] = this.Y - (int)(o2.a[n8].a / this.W * this.Y);
                    }
                    else {
                        this.bb[n8][n7] = this.Y;
                    }
                }
                else {
                    this.bb[n8][n7] = this.Y;
                }
            }
        }
        this.V = this.X / (super.l - 1);
        this.T = this.V * (super.l - 1);
        return true;
    }
    
    public void a(final Graphics graphics) {
        int n = 1;
        final Font font = new Font(super.f, 0, super.c);
        graphics.setFont(font);
        graphics.setColor(super.x);
        if (super.N) {
            graphics.drawLine(this.R, this.S, this.R + this.T, this.S);
            graphics.drawLine(this.R, this.S + 1, this.R + this.T, this.S + 1);
            graphics.drawLine(this.R, this.S, this.R, this.S + this.Y);
            graphics.drawLine(this.R + 1, this.S, this.R + 1, this.S + this.Y);
            for (int i = 1; i < 6; ++i) {
                graphics.drawLine(this.R, this.S + this.U * i, this.R + this.T, this.S + this.U * i);
                graphics.drawLine(this.R, this.S + 1 + this.U * i, this.R + this.T, this.S + 1 + this.U * i);
            }
            for (int j = 1; j < super.l; ++j) {
                if (super.L == 1 || j % super.L == 0) {
                    graphics.drawLine(this.R + this.V * j, this.S, this.R + this.V * j, this.S + this.Y);
                    graphics.drawLine(this.R + 1 + this.V * j, this.S, this.R + 1 + this.V * j, this.S + this.Y);
                }
            }
        }
        else {
            graphics.drawLine(this.R, this.S, this.R + this.T, this.S);
            graphics.drawLine(this.R, this.S, this.R, this.S + this.Y);
            for (int k = 1; k < 6; ++k) {
                graphics.drawLine(this.R, this.S + this.U * k, this.R + this.T, this.S + this.U * k);
            }
            for (int l = 1; l < super.l; ++l) {
                if (super.L == 1 || l % super.L == 0) {
                    graphics.drawLine(this.R + this.V * l, this.S, this.R + this.V * l, this.S + this.Y);
                }
            }
        }
        for (int n2 = 0; n2 < super.m; ++n2) {
            final o o = super.k.a.elementAt(n2);
            final Polygon polygon = new Polygon();
            graphics.setColor(o.c);
            for (int n3 = super.l - 1; n3 > 0; --n3) {
                polygon.addPoint(this.R + n3 * this.V, this.S + (this.Y - this.bb[n3][super.m]));
            }
            polygon.addPoint(this.R, this.S + (this.Y - this.bb[0][super.m]));
            polygon.addPoint(this.R, this.S + this.bb[0][n2] - this.bb[0][super.m]);
            if (!super.y && o.a[0] != null) {
                o.a[0].l = this.R - this.bc;
                o.a[0].m = this.S + this.bb[0][n2] - this.bb[0][super.m] - this.bc;
                o.a[0].n = this.R + this.bc;
                o.a[0].o = this.S + this.bb[0][n2] - this.bb[0][super.m] + this.bc;
            }
            this.bb[0][super.m] += this.Y - this.bb[0][n2];
            for (int n4 = 1; n4 < super.l; ++n4) {
                polygon.addPoint(this.R + n4 * this.V, this.S + this.bb[n4][n2] - this.bb[n4][super.m]);
                if (!super.y && o.a[n4] != null && !o.a[n4].k) {
                    o.a[n4].l = this.R + n4 * this.V - this.bc;
                    o.a[n4].m = this.S + this.bb[n4][n2] - this.bb[n4][super.m] - this.bc;
                    o.a[n4].n = this.R + n4 * this.V + this.bc;
                    o.a[n4].o = this.S + this.bb[n4][n2] - this.bb[n4][super.m] + this.bc;
                }
                this.bb[n4][super.m] += this.Y - this.bb[n4][n2];
            }
            graphics.fillPolygon(polygon);
        }
        for (int n5 = 0; n5 < super.l; ++n5) {
            this.bb[n5][super.m] = 0;
        }
        graphics.setColor(super.h);
        if (super.N) {
            graphics.drawLine(this.R, this.S + this.Y, this.R + this.T, this.S + this.Y);
            graphics.drawLine(this.R, this.S + 1 + this.Y, this.R + this.T, this.S + 1 + this.Y);
            if (!super.O) {
                graphics.drawLine(this.R + this.T, this.S, this.R + this.T, this.S + this.Y);
                graphics.drawLine(this.R + 1 + this.T, this.S, this.R + 1 + this.T, this.S + this.Y + 1);
            }
            else {
                graphics.drawLine(this.R, this.S, this.R, this.S + this.Y);
                graphics.drawLine(this.R + 1, this.S, this.R + 1, this.S + this.Y + 1);
            }
        }
        else {
            graphics.drawLine(this.R, this.S + this.Y, this.R + this.T, this.S + this.Y);
            if (!super.O) {
                graphics.drawLine(this.R + this.T, this.S, this.R + this.T, this.S + this.Y);
            }
            else {
                graphics.drawLine(this.R, this.S, this.R, this.S + this.Y);
            }
        }
        if (super.N) {
            for (int n6 = 0; n6 < 7; ++n6) {
                if (!super.O) {
                    graphics.drawLine(this.R + this.T - 2, this.S + this.U * n6, this.R + this.T + 3, this.S + this.U * n6);
                    graphics.drawLine(this.R + this.T - 2, this.S + 1 + this.U * n6, this.R + this.T + 3, this.S + 1 + this.U * n6);
                }
                else {
                    graphics.drawLine(this.R - 2, this.S + this.U * n6, this.R + 3, this.S + this.U * n6);
                    graphics.drawLine(this.R - 2, this.S + 1 + this.U * n6, this.R + 3, this.S + 1 + this.U * n6);
                }
            }
            for (int n7 = 0; n7 < super.l; ++n7) {
                if (super.J == 1 || n7 % super.J == 0) {
                    graphics.drawLine(this.R + this.V * n7, this.S + this.Y - 2, this.R + this.V * n7, this.S + this.Y + 3);
                    graphics.drawLine(this.R + 1 + this.V * n7, this.S + this.Y - 2, this.R + 1 + this.V * n7, this.S + this.Y + 3);
                }
            }
        }
        else {
            for (int n8 = 0; n8 < 7; ++n8) {
                if (!super.O) {
                    graphics.drawLine(this.R + this.T - 2, this.S + this.U * n8, this.R + this.T + 2, this.S + this.U * n8);
                }
                else {
                    graphics.drawLine(this.R - 2, this.S + this.U * n8, this.R + 2, this.S + this.U * n8);
                }
            }
            for (int n9 = 0; n9 < super.l; ++n9) {
                if (super.J == 1 || n9 % super.J == 0) {
                    graphics.drawLine(this.R + this.V * n9, this.S + this.Y - 2, this.R + this.V * n9, this.S + this.Y + 2);
                }
            }
        }
        if (!super.O) {
            graphics.drawString(this.bl, this.R + this.T + 5, this.S + this.Y + 4);
            graphics.drawString(this.bg, this.R + this.T + 5, this.S + this.U * 5 + 4);
            graphics.drawString(this.bh, this.R + this.T + 5, this.S + this.U * 4 + 4);
            graphics.drawString(this.bi, this.R + this.T + 5, this.S + this.U * 3 + 4);
            graphics.drawString(this.bj, this.R + this.T + 5, this.S + this.U * 2 + 4);
            graphics.drawString(this.bk, this.R + this.T + 5, this.S + this.U + 4);
            graphics.drawString(this.bf, this.R + this.T + 5, this.S + 4);
        }
        else {
            graphics.drawString(this.bl, this.R - 5 - super.v.b(this.bl, font), this.S + this.Y + 4);
            graphics.drawString(this.bg, this.R - 5 - super.v.b(this.bg, font), this.S + this.U * 5 + 4);
            graphics.drawString(this.bh, this.R - 5 - super.v.b(this.bh, font), this.S + this.U * 4 + 4);
            graphics.drawString(this.bi, this.R - 5 - super.v.b(this.bi, font), this.S + this.U * 3 + 4);
            graphics.drawString(this.bj, this.R - 5 - super.v.b(this.bj, font), this.S + this.U * 2 + 4);
            graphics.drawString(this.bk, this.R - 5 - super.v.b(this.bk, font), this.S + this.U + 4);
            graphics.drawString(this.bf, this.R - 5 - super.v.b(this.bf, font), this.S + 4);
        }
        for (int n10 = 0; n10 < super.l; ++n10) {
            if (super.K == 1 || n10 % super.K == 0) {
                if (!super.B) {
                    if (super.k.b[n10] != null) {
                        if (!super.A) {
                            graphics.drawString(super.k.b[n10], this.R + n10 * this.V - super.v.a(super.k.b[n10], font), this.S + this.Y + 17);
                        }
                        else if (n != 0) {
                            graphics.drawString(super.k.b[n10], this.R + n10 * this.V - super.v.a(super.k.b[n10], font), this.S + this.Y + 17);
                        }
                        else {
                            graphics.drawString(super.k.b[n10], this.R + n10 * this.V - super.v.a(super.k.b[n10], font), this.S + this.Y + 23 + super.c);
                        }
                    }
                    if (n != 0) {
                        n = 0;
                    }
                    else {
                        n = 1;
                    }
                }
                else if (super.k.b[n10] != null) {
                    final Image a = super.v.a(super.k.b[n10], font, true, super.h, super.g);
                    if (a != null) {
                        graphics.drawImage(a, this.R + n10 * this.V - super.c / 2, this.S + this.Y + 10, null);
                    }
                }
            }
        }
        if (super.r) {
            for (int n11 = 0; n11 < super.m; ++n11) {
                final o o2 = super.k.a.elementAt(n11);
                final Color c = o2.c;
                for (int n12 = 0; n12 < super.l; ++n12) {
                    if (o2.a[n12] != null && !o2.a[n12].k && ((o2.a[n12].a <= super.o && o2.a[n12].a >= super.n) || !super.p)) {
                        if (o2.a[n12].c != null) {
                            graphics.setColor(o2.a[n12].c);
                        }
                        else {
                            graphics.setColor(c);
                        }
                        graphics.fillRect(o2.a[n12].l, o2.a[n12].m, this.bc * 2, this.bc * 2);
                    }
                }
            }
        }
        this.b(graphics);
        super.v.a(graphics, this);
        super.y = true;
    }
    
    public void a(final Graphics graphics, final n n) {
        graphics.setColor(super.w);
        for (int i = 0; i < super.k.a.size(); ++i) {
            final o o = super.k.a.elementAt(i);
            for (int j = 0; j < super.k.c; ++j) {
                if (n == o.a[j]) {
                    graphics.fillRect(o.a[j].l, o.a[j].m, this.bc * 2, this.bc * 2);
                    break;
                }
            }
        }
    }
    
    public n a(final int n, final int n2) {
        for (int i = 0; i < super.m; ++i) {
            final o o = super.k.a.elementAt(i);
            for (int j = 0; j < super.l; ++j) {
                if (o.a[j] != null && n >= o.a[j].l && n <= o.a[j].n && n2 >= o.a[j].m && n2 <= o.a[j].o) {
                    return o.a[j];
                }
            }
        }
        return null;
    }
    
    public void b(final Graphics graphics) {
        final Font font = new Font(super.f, 1, super.c + 2);
        graphics.setFont(font);
        graphics.setColor(super.h);
        if (super.D != null) {
            if (super.B) {
                graphics.drawString(super.D, this.R + super.v.a(super.D, font, this.X), this.S + this.Y + super.C + (super.c + 17));
            }
            else {
                graphics.drawString(super.D, this.R + super.v.a(super.D, font, this.X), this.S + super.b - (super.c + 2));
            }
        }
        if (super.E != null) {
            if (!super.O) {
                super.v.a(super.a - (super.c + 2) / 2, this.S, graphics, super.E, super.c + 2, this.Y);
                return;
            }
            super.v.a((super.c + 2) / 2, this.S, graphics, super.E, super.c + 2, this.Y);
        }
    }
}
