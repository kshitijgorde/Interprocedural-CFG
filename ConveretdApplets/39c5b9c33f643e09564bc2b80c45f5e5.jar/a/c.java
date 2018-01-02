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

public class c extends b
{
    private int R;
    private int S;
    private int T;
    private int U;
    private int V;
    private double W;
    private double X;
    private int Y;
    private int Z;
    private int ba;
    private int bb;
    private int bc;
    private int bd;
    private boolean be;
    private Color bf;
    private String bg;
    private String bh;
    private String bi;
    private String bj;
    private String bk;
    private int bl;
    private int bm;
    private int bn;
    
    public c(final int t, final int s, final int a, final int b, final Color g, final Color h, final int c) {
        this.U = 3;
        this.V = 6;
        this.ba = 20;
        this.be = false;
        this.bf = new Color(0, 0, 192);
        this.bn = 1;
        super.a = a;
        super.b = b;
        super.g = g;
        super.h = h;
        this.R = t + 20 + this.ba;
        this.T = t;
        this.S = s;
        super.c = c;
        this.Z = super.a - 40 - this.ba;
        this.Y = super.b - 25 - this.ba;
        this.bd = this.Y / 6;
        this.Y = this.bd * 6;
    }
    
    public boolean a(final int l, final o j, final String s, final String s2) {
        super.l = l;
        super.j = j;
        double[] array = new double[0];
        int n = 0;
        final Font font = new Font(super.f, 0, super.c);
        if (super.D != null) {
            this.Y -= 5 + (super.c + 2);
        }
        if (super.E != null) {
            this.R += 15 + (super.c + 2) / 2;
            this.Z -= 15 + (super.c + 2) / 2;
        }
        if (!super.p) {
            if (super.l > 0) {
                this.W = super.j.a[0].a;
                this.X = this.W;
            }
            for (int i = 0; i < super.l; ++i) {
                if (super.j.a[i].a > this.W) {
                    this.W = super.j.a[i].a;
                }
                if (super.j.a[i].a < this.X) {
                    this.X = super.j.a[i].a;
                }
            }
            if (this.W == 0.0 && this.X == 0.0) {
                this.W = 4.0;
            }
            if (this.X >= 0.0) {
                this.bn = 1;
            }
            else if (this.W < 0.0) {
                this.bn = 3;
            }
            else {
                this.bn = 2;
            }
            switch (this.bn) {
                case 1: {
                    this.X = 0.0;
                    array = super.v.a(5, this.X, this.W, true, super.H);
                    this.W = array[4];
                    n = (int)array[5];
                    break;
                }
                case 2: {
                    this.W = Math.max(Math.abs(this.X), Math.abs(this.W));
                    array = super.v.a(3, 0.0, this.W, true, super.H);
                    this.W = array[2];
                    n = (int)array[3];
                    break;
                }
                case 3: {
                    this.W = Math.abs(this.X);
                    this.X = 0.0;
                    array = super.v.a(5, this.X, this.W, true, super.H);
                    this.W = 0.0;
                    this.X = -array[4];
                    n = (int)array[5];
                    break;
                }
            }
        }
        else {
            array = super.v.a(5, super.n, super.o, false, super.H);
            this.X = super.n;
            this.W = array[4];
            n = (int)array[5];
            if (this.X >= 0.0) {
                this.bn = 1;
            }
            else if (this.W < 0.0) {
                this.bn = 3;
            }
            else {
                this.bn = 2;
            }
        }
        if (!super.p) {
            final double n2 = this.W + Math.abs(this.X) - super.n;
        }
        else {
            final double n3 = this.W + Math.abs(this.X);
        }
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(n);
        instance.setMinimumFractionDigits(n);
        switch (this.bn) {
            case 1: {
                try {
                    this.bk = String.valueOf(s2) + instance.format(array[0]) + s;
                    this.bh = String.valueOf(s2) + instance.format(array[1]) + s;
                    this.bi = String.valueOf(s2) + instance.format(array[2]) + s;
                    this.bj = String.valueOf(s2) + instance.format(array[3]) + s;
                    this.bg = String.valueOf(s2) + instance.format(this.W) + s;
                }
                catch (Exception ex) {}
                break;
            }
            case 2: {
                try {
                    this.bh = String.valueOf(s2) + "-" + instance.format(Math.abs(array[1])) + s;
                    this.bi = String.valueOf(s2) + instance.format(0L) + s;
                    this.bj = String.valueOf(s2) + instance.format(Math.abs(array[1])) + s;
                    this.bg = String.valueOf(s2) + instance.format(this.W) + s;
                    this.bk = String.valueOf(s2) + instance.format(-this.W) + s;
                }
                catch (Exception ex2) {}
                break;
            }
            case 3: {
                try {
                    this.bk = String.valueOf(s2) + instance.format(this.X) + s;
                    this.bh = String.valueOf(s2) + instance.format(-array[3]) + s;
                    this.bi = String.valueOf(s2) + instance.format(-array[2]) + s;
                    this.bj = String.valueOf(s2) + instance.format(-array[1]) + s;
                    this.bg = String.valueOf(s2) + instance.format(this.W) + s;
                }
                catch (Exception ex3) {}
                break;
            }
        }
        if (this.bn != 3) {
            this.bl = super.v.b(this.bg, font);
        }
        else {
            this.bl = super.v.b(this.bk, font);
        }
        this.R += this.bl;
        this.Z -= this.bl;
        this.bb = this.Z / super.l;
        if (super.M != 0 && this.bb - 4 - this.U * 2 > super.M) {
            this.bb = super.M - 4 - this.U * 2 + this.ba;
        }
        this.Z = this.bb * super.l + 20;
        if (!super.B) {
            if (super.K == 1) {
                super.A = super.v.a(super.j, super.l, this.bb, super.c);
            }
            if (super.A) {
                this.Y -= super.c + 5;
                if (super.D != null) {
                    this.Y -= super.c + 2;
                }
            }
        }
        switch (this.bn) {
            case 1: {
                for (int k = 0; k < super.l; ++k) {
                    super.j.a[k].j = (int)((super.j.a[k].a - this.X) / (this.W - this.X) * this.Y);
                    if (super.j.a[k].j > this.Y) {
                        super.j.a[k].j = this.Y;
                    }
                }
                break;
            }
            case 2: {
                this.bm = this.Y / 2;
                for (int n4 = 0; n4 < super.l; ++n4) {
                    super.j.a[n4].j = (int)(super.j.a[n4].a / this.W * this.bm);
                    if (super.j.a[n4].j > this.bm) {
                        super.j.a[n4].j = this.bm;
                    }
                    if (super.j.a[n4].j < -this.bm) {
                        super.j.a[n4].j = -this.bm;
                    }
                }
                break;
            }
            case 3: {
                for (int n5 = 0; n5 < super.l; ++n5) {
                    super.j.a[n5].j = (int)((this.W + super.j.a[n5].a) / (this.W - this.X) * this.Y);
                    if (super.j.a[n5].j > this.Y) {
                        super.j.a[n5].j = this.Y;
                    }
                }
                break;
            }
        }
        return true;
    }
    
    public void a(final Graphics graphics) {
        int n = 1;
        final Font font = new Font(super.f, 0, super.c);
        graphics.setFont(font);
        graphics.setColor(super.x);
        if (super.N) {
            graphics.drawRect(this.R, this.S, this.Z, this.Y);
            graphics.drawRect(this.R + 1, this.S + 1, this.Z - 2, this.Y - 2);
            graphics.drawLine(this.R - this.ba, this.S + this.ba, this.R - this.ba, this.S + this.ba + this.Y);
            graphics.drawLine(this.R - this.ba + 1, this.S + this.ba, this.R - this.ba + 1, this.S + this.ba + this.Y);
            graphics.drawLine(this.R - this.ba, this.S + this.ba + this.Y, this.R - this.ba + this.Z, this.S + this.ba + this.Y);
            graphics.drawLine(this.R - this.ba, this.S + this.ba + this.Y - 1, this.R - this.ba + this.Z, this.S + this.ba + this.Y - 1);
            graphics.drawLine(this.R, this.S, this.R - this.ba, this.S + this.ba);
            graphics.drawLine(this.R + 1, this.S, this.R + 1 - this.ba, this.S + this.ba);
            graphics.drawLine(this.R, this.S + this.Y, this.R - this.ba, this.S + this.ba + this.Y);
            graphics.drawLine(this.R + 1, this.S + this.Y, this.R + 1 - this.ba, this.S + this.ba + this.Y);
            graphics.drawLine(this.R + this.Z, this.S + this.Y, this.R - this.ba + this.Z, this.S + this.ba + this.Y);
            graphics.drawLine(this.R - 1 + this.Z, this.S + this.Y, this.R - 1 - this.ba + this.Z, this.S + this.ba + this.Y);
            graphics.drawLine(this.R - this.ba, this.S + this.Y + this.ba + 6, this.R - this.ba, this.S + this.ba + this.Y);
            graphics.drawLine(this.R + 1 - this.ba, this.S + this.Y + this.ba + 6, this.R + 1 - this.ba, this.S + this.ba + this.Y);
            graphics.drawLine(this.R + this.Z - this.ba, this.S + this.Y + this.ba + 6, this.R + this.Z - this.ba, this.S + this.ba + this.Y);
            graphics.drawLine(this.R + 1 + this.Z - this.ba, this.S + this.Y + this.ba + 6, this.R + 1 + this.Z - this.ba, this.S + this.ba + this.Y);
            graphics.drawLine(this.R - this.ba - 6, this.S + this.Y + this.ba, this.R - this.ba, this.S + this.ba + this.Y);
            graphics.drawLine(this.R - this.ba - 6, this.S - 1 + this.Y + this.ba, this.R - this.ba, this.S - 1 + this.ba + this.Y);
            graphics.drawLine(this.R - this.ba - 6, this.S + this.ba + this.Y / 4, this.R - this.ba, this.S + this.ba + this.Y / 4);
            graphics.drawLine(this.R - this.ba - 6, this.S + 1 + this.ba + this.Y / 4, this.R - this.ba, this.S + 1 + this.ba + this.Y / 4);
            graphics.drawLine(this.R - this.ba - 6, this.S + this.ba + this.Y / 2, this.R - this.ba, this.S + this.ba + this.Y / 2);
            graphics.drawLine(this.R - this.ba - 6, this.S - 1 + this.ba + this.Y / 2, this.R - this.ba, this.S - 1 + this.ba + this.Y / 2);
            graphics.drawLine(this.R - this.ba - 6, this.S + this.ba + this.Y / 4 * 3, this.R - this.ba, this.S + this.ba + this.Y / 4 * 3);
            graphics.drawLine(this.R - this.ba - 6, this.S + 1 + this.ba + this.Y / 4 * 3, this.R - this.ba, this.S + 1 + this.ba + this.Y / 4 * 3);
            graphics.drawLine(this.R - this.ba - 6, this.S + this.ba, this.R - this.ba, this.S + this.ba);
            graphics.drawLine(this.R - this.ba - 6, this.S + 1 + this.ba, this.R - this.ba, this.S + 1 + this.ba);
            graphics.drawLine(this.R, this.S + this.Y / 4, this.R - this.ba, this.S + this.ba + this.Y / 4);
            graphics.drawLine(this.R + 1, this.S + this.Y / 4, this.R + 1 - this.ba, this.S + this.ba + this.Y / 4);
            graphics.drawLine(this.R, this.S + this.Y / 4 * 3, this.R - this.ba, this.S + this.ba + this.Y / 4 * 3);
            graphics.drawLine(this.R + 1, this.S + this.Y / 4 * 3, this.R + 1 - this.ba, this.S + this.ba + this.Y / 4 * 3);
            graphics.drawLine(this.R, this.S + this.Y / 4, this.R + this.Z, this.S + this.Y / 4);
            graphics.drawLine(this.R, this.S + 1 + this.Y / 4, this.R + this.Z, this.S + 1 + this.Y / 4);
            graphics.drawLine(this.R, this.S + this.Y / 4 * 3, this.R + this.Z, this.S + this.Y / 4 * 3);
            graphics.drawLine(this.R, this.S + 1 + this.Y / 4 * 3, this.R + this.Z, this.S + 1 + this.Y / 4 * 3);
            graphics.drawLine(this.R, this.S + this.Y / 2, this.R - this.ba, this.S + this.ba + this.Y / 2);
            graphics.drawLine(this.R + 1, this.S + this.Y / 2, this.R + 1 - this.ba, this.S + this.ba + this.Y / 2);
            graphics.drawLine(this.R, this.S + this.Y / 2, this.R + this.Z, this.S + this.Y / 2);
            graphics.drawLine(this.R, this.S + 1 + this.Y / 2, this.R + this.Z, this.S + 1 + this.Y / 2);
        }
        else {
            graphics.drawRect(this.R, this.S, this.Z, this.Y);
            graphics.drawLine(this.R - this.ba, this.S + this.ba, this.R - this.ba, this.S + this.ba + this.Y);
            graphics.drawLine(this.R - this.ba, this.S + this.ba + this.Y, this.R - this.ba + this.Z, this.S + this.ba + this.Y);
            graphics.drawLine(this.R, this.S, this.R - this.ba, this.S + this.ba);
            graphics.drawLine(this.R, this.S + this.Y, this.R - this.ba, this.S + this.ba + this.Y);
            graphics.drawLine(this.R + this.Z, this.S + this.Y, this.R - this.ba + this.Z, this.S + this.ba + this.Y);
            graphics.drawLine(this.R - this.ba, this.S + this.Y + this.ba + 6, this.R - this.ba, this.S + this.ba + this.Y);
            graphics.drawLine(this.R + this.Z - this.ba, this.S + this.Y + this.ba + 6, this.R + this.Z - this.ba, this.S + this.ba + this.Y);
            graphics.drawLine(this.R - this.ba - 6, this.S + this.Y + this.ba, this.R - this.ba, this.S + this.ba + this.Y);
            graphics.drawLine(this.R - this.ba - 6, this.S + this.ba + this.Y / 4, this.R - this.ba, this.S + this.ba + this.Y / 4);
            graphics.drawLine(this.R - this.ba - 6, this.S + this.ba + this.Y / 2, this.R - this.ba, this.S + this.ba + this.Y / 2);
            graphics.drawLine(this.R - this.ba - 6, this.S + this.ba + this.Y / 4 * 3, this.R - this.ba, this.S + this.ba + this.Y / 4 * 3);
            graphics.drawLine(this.R - this.ba - 6, this.S + this.ba, this.R - this.ba, this.S + this.ba);
            graphics.drawLine(this.R, this.S + this.Y / 4, this.R - this.ba, this.S + this.ba + this.Y / 4);
            graphics.drawLine(this.R, this.S + this.Y / 4 * 3, this.R - this.ba, this.S + this.ba + this.Y / 4 * 3);
            graphics.drawLine(this.R, this.S + this.Y / 4, this.R + this.Z, this.S + this.Y / 4);
            graphics.drawLine(this.R, this.S + this.Y / 4 * 3, this.R + this.Z, this.S + this.Y / 4 * 3);
            graphics.drawLine(this.R, this.S + this.Y / 2, this.R - this.ba, this.S + this.ba + this.Y / 2);
            graphics.drawLine(this.R, this.S + this.Y / 2, this.R + this.Z, this.S + this.Y / 2);
        }
        graphics.setColor(super.h);
        graphics.drawString(this.bk, this.R - 15 - this.ba - super.v.b(this.bk, font), this.S + this.ba + this.Y + 3);
        graphics.drawString(this.bh, this.R - 15 - this.ba - super.v.b(this.bh, font), this.S + this.ba + this.Y / 4 * 3 + 3);
        graphics.drawString(this.bi, this.R - 15 - this.ba - super.v.b(this.bi, font), this.S + this.ba + this.Y / 2 + 3);
        graphics.drawString(this.bj, this.R - 15 - this.ba - super.v.b(this.bj, font), this.S + this.ba + this.Y / 4 + 3);
        graphics.drawString(this.bg, this.R - 15 - this.ba - super.v.b(this.bg, font), this.S + this.ba + 3);
        if (this.bb < 25) {
            this.U = 1;
        }
        final int n2 = this.ba - this.V;
        final int n3 = this.bb - 4 - this.U * 2;
        switch (this.bn) {
            case 1: {
                for (int i = 0; i < super.l; ++i) {
                    final int j = super.j.a[i].j;
                    final int l = this.R + this.bb * i - 5;
                    final int m = this.S + this.Y - j + n2 + this.U;
                    final int[] array = { l + n3, l + n3 + n2, l + n3 + n2, l + n3 };
                    final int[] array2 = { m, m - n2, this.S + this.Y + this.U, this.S + this.Y + n2 + this.U };
                    final int[] array3 = { l + n2, l, l + n3, l + n3 + n2 };
                    final int[] array4 = { m - n2, m, m, m - n2 };
                    graphics.setColor(super.x);
                    if (i != 0) {
                        if (super.N) {
                            graphics.drawLine(this.R - this.ba + this.bb * i + 5, this.S + this.Y + this.ba + 6, this.R - this.ba + this.bb * i + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + 1 - this.ba + this.bb * i + 5, this.S + this.Y + this.ba + 6, this.R + 1 - this.ba + this.bb * i + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + this.bb * i + 5, this.S + this.Y, this.R - this.ba + this.bb * i + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + 1 + this.bb * i + 5, this.S + this.Y, this.R + 1 - this.ba + this.bb * i + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + this.bb * i + 5, this.S + this.Y, this.R + this.bb * i + 5, this.S);
                            graphics.drawLine(this.R + 1 + this.bb * i + 5, this.S + this.Y, this.R + 1 + this.bb * i + 5, this.S);
                        }
                        else {
                            graphics.drawLine(this.R - this.ba + this.bb * i + 5, this.S + this.Y + this.ba + 6, this.R - this.ba + this.bb * i + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + this.bb * i + 5, this.S + this.Y, this.R - this.ba + this.bb * i + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + this.bb * i + 5, this.S + this.Y, this.R + this.bb * i + 5, this.S);
                        }
                    }
                    if (!super.j.a[i].k) {
                        if (super.j.a[i].a >= super.n) {
                            Color color;
                            if (super.j.a[i].c == null) {
                                color = this.bf;
                            }
                            else {
                                color = super.j.a[i].c;
                            }
                            graphics.setColor(color);
                            graphics.fillRect(l, m, n3, j);
                            graphics.setColor(color.darker());
                            graphics.fillPolygon(array, array2, 4);
                            graphics.setColor(color.brighter());
                            graphics.fillPolygon(array3, array4, 4);
                        }
                        if (!super.y) {
                            super.j.a[i].l = l;
                            super.j.a[i].m = m;
                            super.j.a[i].n = l + n3;
                            super.j.a[i].o = m + j;
                        }
                    }
                }
                break;
            }
            case 2: {
                for (int k = 0; k < super.l; ++k) {
                    int bm = 0;
                    final int l2 = this.R + this.bb * k - 5;
                    int n4;
                    int m2;
                    if (super.Q) {
                        n4 = super.j.a[k].j;
                        m2 = this.S + this.bm - n4 + n2 + this.U;
                    }
                    else {
                        n4 = super.j.a[k].j + this.bm;
                        m2 = this.S + this.bm * 2 - n4 + n2 + this.U;
                        bm = this.bm;
                    }
                    final int[] array5 = { l2 + n3, l2 + n3 + n2, l2 + n3 + n2, l2 + n3 };
                    final int[] array6 = { m2, m2 - n2, this.S + this.bm + this.U + bm, this.S + this.bm + n2 + this.U + bm };
                    if (n4 < 0) {
                        m2 = this.S + this.bm + n2 + this.U;
                        n4 = Math.abs(n4);
                    }
                    final int[] array7 = { l2 + n2, l2, l2 + n3, l2 + n3 + n2 };
                    final int[] array8 = { m2 - n2, m2, m2, m2 - n2 };
                    graphics.setColor(super.x);
                    if (k != 0) {
                        if (super.N) {
                            graphics.drawLine(this.R - this.ba + this.bb * k + 5, this.S + this.Y + this.ba + 6, this.R - this.ba + this.bb * k + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + 1 - this.ba + this.bb * k + 5, this.S + this.Y + this.ba + 6, this.R + 1 - this.ba + this.bb * k + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + this.bb * k + 5, this.S + this.Y, this.R - this.ba + this.bb * k + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + 1 + this.bb * k + 5, this.S + this.Y, this.R + 1 - this.ba + this.bb * k + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + this.bb * k + 5, this.S + this.Y, this.R + this.bb * k + 5, this.S);
                            graphics.drawLine(this.R + 1 + this.bb * k + 5, this.S + this.Y, this.R + 1 + this.bb * k + 5, this.S);
                        }
                        else {
                            graphics.drawLine(this.R - this.ba + this.bb * k + 5, this.S + this.Y + this.ba + 6, this.R - this.ba + this.bb * k + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + this.bb * k + 5, this.S + this.Y, this.R - this.ba + this.bb * k + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + this.bb * k + 5, this.S + this.Y, this.R + this.bb * k + 5, this.S);
                        }
                    }
                    Color color2;
                    if (super.j.a[k].c == null) {
                        color2 = this.bf;
                    }
                    else {
                        color2 = super.j.a[k].c;
                    }
                    if (!super.j.a[k].k) {
                        if (!super.p || super.j.a[k].a >= super.n) {
                            graphics.setColor(color2);
                            graphics.fillRect(l2, m2, n3, n4);
                            graphics.setColor(color2.darker());
                            graphics.fillPolygon(array5, array6, 4);
                            graphics.setColor(color2.brighter());
                            graphics.fillPolygon(array7, array8, 4);
                        }
                        if (!super.y) {
                            super.j.a[k].l = l2;
                            super.j.a[k].m = m2;
                            super.j.a[k].n = l2 + n3;
                            super.j.a[k].o = m2 + n4;
                        }
                    }
                    graphics.setColor(super.x);
                    if (k != super.l - 1) {
                        if (super.N) {
                            graphics.drawLine(this.R + this.bb * (k + 1) + 5, this.S + this.Y / 2, this.R - this.ba + this.bb * (k + 1) + 5, this.S + this.ba + this.Y / 2);
                            graphics.drawLine(this.R + 1 + this.bb * (k + 1) + 5, this.S + this.Y / 2, this.R + 1 - this.ba + this.bb * (k + 1) + 5, this.S + this.ba + this.Y / 2);
                        }
                        else {
                            graphics.drawLine(this.R + this.bb * (k + 1) + 5, this.S + this.Y / 2, this.R - this.ba + this.bb * (k + 1) + 5, this.S + this.ba + this.Y / 2);
                        }
                    }
                }
                graphics.setColor(super.x);
                if (super.N) {
                    graphics.drawLine(this.R - this.ba, this.S + this.Y / 2 + this.ba, this.R + this.Z - this.ba, this.S + this.Y / 2 + this.ba);
                    graphics.drawLine(this.R - this.ba, this.S - 1 + this.Y / 2 + this.ba, this.R + this.Z - this.ba, this.S - 1 + this.Y / 2 + this.ba);
                    graphics.drawLine(this.R + this.Z, this.S + this.Y / 2, this.R + this.Z - this.ba, this.S + this.ba + this.Y / 2);
                    graphics.drawLine(this.R - 1 + this.Z, this.S + this.Y / 2, this.R - 1 + this.Z - this.ba, this.S + this.ba + this.Y / 2);
                    break;
                }
                graphics.drawLine(this.R - this.ba, this.S + this.Y / 2 + this.ba, this.R + this.Z - this.ba, this.S + this.Y / 2 + this.ba);
                graphics.drawLine(this.R + this.Z, this.S + this.Y / 2, this.R + this.Z - this.ba, this.S + this.ba + this.Y / 2);
                break;
            }
            case 3: {
                for (int n5 = 0; n5 < super.l; ++n5) {
                    final int l3 = this.R + this.bb * n5 - 5;
                    int abs;
                    int m3;
                    if (super.Q) {
                        abs = Math.abs(super.j.a[n5].j);
                        m3 = this.S + n2 + this.U;
                    }
                    else {
                        abs = this.Y + super.j.a[n5].j;
                        m3 = this.S + this.Y - abs + n2 + this.U;
                    }
                    final int[] array9 = { l3 + n3, l3 + n3 + n2, l3 + n3 + n2, l3 + n3 };
                    final int[] array10 = { m3, m3 - n2, m3 + abs - n2, m3 + abs };
                    final int[] array11 = { l3 + n2, l3, l3 + n3, l3 + n3 + n2 };
                    final int[] array12 = { m3 - n2, m3, m3, m3 - n2 };
                    graphics.setColor(super.x);
                    if (n5 != 0) {
                        if (super.N) {
                            graphics.drawLine(this.R - this.ba + this.bb * n5 + 5, this.S + this.Y + this.ba + 6, this.R - this.ba + this.bb * n5 + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + 1 - this.ba + this.bb * n5 + 5, this.S + this.Y + this.ba + 6, this.R + 1 - this.ba + this.bb * n5 + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + this.bb * n5 + 5, this.S + this.Y, this.R - this.ba + this.bb * n5 + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + 1 + this.bb * n5 + 5, this.S + this.Y, this.R + 1 - this.ba + this.bb * n5 + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + this.bb * n5 + 5, this.S + this.Y, this.R + this.bb * n5 + 5, this.S);
                            graphics.drawLine(this.R + 1 + this.bb * n5 + 5, this.S + this.Y, this.R + 1 + this.bb * n5 + 5, this.S);
                        }
                        else {
                            graphics.drawLine(this.R - this.ba + this.bb * n5 + 5, this.S + this.Y + this.ba + 6, this.R - this.ba + this.bb * n5 + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + this.bb * n5 + 5, this.S + this.Y, this.R - this.ba + this.bb * n5 + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + this.bb * n5 + 5, this.S + this.Y, this.R + this.bb * n5 + 5, this.S);
                        }
                    }
                    Color color3;
                    if (super.j.a[n5].c == null) {
                        color3 = this.bf;
                    }
                    else {
                        color3 = super.j.a[n5].c;
                    }
                    if (!super.j.a[n5].k) {
                        if (!super.p || super.j.a[n5].a >= super.n) {
                            graphics.setColor(color3);
                            graphics.fillRect(l3, m3, n3, abs);
                            graphics.setColor(color3.darker());
                            graphics.fillPolygon(array9, array10, 4);
                            graphics.setColor(color3.brighter());
                            graphics.fillPolygon(array11, array12, 4);
                        }
                        if (!super.y) {
                            super.j.a[n5].l = l3;
                            super.j.a[n5].m = m3;
                            super.j.a[n5].n = l3 + n3;
                            super.j.a[n5].o = m3 + abs;
                        }
                    }
                    graphics.setColor(super.x);
                    if (n5 != super.l - 1) {
                        if (super.N) {
                            graphics.drawLine(this.R + this.bb * (n5 + 1) + 5, this.S, this.R - this.ba + this.bb * (n5 + 1) + 5, this.S + this.ba);
                            graphics.drawLine(this.R + 1 + this.bb * (n5 + 1) + 5, this.S, this.R + 1 - this.ba + this.bb * (n5 + 1) + 5, this.S + this.ba);
                        }
                        else {
                            graphics.drawLine(this.R + this.bb * (n5 + 1) + 5, this.S, this.R - this.ba + this.bb * (n5 + 1) + 5, this.S + this.ba);
                        }
                    }
                }
                graphics.setColor(super.x);
                if (super.N) {
                    graphics.drawLine(this.R - this.ba, this.S + this.ba, this.R + this.Z - this.ba, this.S + this.ba);
                    graphics.drawLine(this.R - this.ba, this.S + 1 + this.ba, this.R + this.Z - this.ba, this.S + 1 + this.ba);
                    graphics.drawLine(this.R + this.Z, this.S, this.R + this.Z - this.ba, this.S + this.ba);
                    graphics.drawLine(this.R + 1 + this.Z, this.S, this.R + 1 + this.Z - this.ba, this.S + this.ba);
                    break;
                }
                graphics.drawLine(this.R - this.ba, this.S + this.ba, this.R + this.Z - this.ba, this.S + this.ba);
                graphics.drawLine(this.R + this.Z, this.S, this.R + this.Z - this.ba, this.S + this.ba);
                break;
            }
        }
        graphics.setColor(super.h);
        for (int n6 = 0; n6 < super.l; ++n6) {
            if (!super.B) {
                if (super.j.a[n6].b != null) {
                    if (!super.A) {
                        graphics.drawString(super.j.a[n6].b, this.R + this.bb * n6 + n3 / 2 - 10 - super.v.a(super.j.a[n6].b, font), this.S + this.Y + this.ba + 20);
                    }
                    else if (n != 0) {
                        graphics.drawString(super.j.a[n6].b, this.R + this.bb * n6 + n3 / 2 - 10 - super.v.a(super.j.a[n6].b, font), this.S + this.Y + this.ba + 20);
                    }
                    else {
                        graphics.drawString(super.j.a[n6].b, this.R + this.bb * n6 + n3 / 2 - 10 - super.v.a(super.j.a[n6].b, font), this.S + this.Y + this.ba + 25 + super.c);
                    }
                }
                if (n != 0) {
                    n = 0;
                }
                else {
                    n = 1;
                }
            }
            else if (super.j.a[n6].b != null) {
                final Image a = super.v.a(super.j.a[n6].b, font, true, super.h, super.g);
                if (a != null) {
                    graphics.drawImage(a, this.R + this.bb * n6 + n3 / 2 - 10 - super.c / 2, this.S + this.Y + this.ba + 10, null);
                }
            }
        }
        this.b(graphics);
        super.v.a(graphics, this);
        super.y = true;
    }
    
    public void a(final Graphics graphics, final n n) {
        final Color bf = this.bf;
        final int n2 = this.ba - this.V;
        final int n3 = this.bb - 4 - this.U * 2;
        switch (this.bn) {
            case 1: {
                for (int i = 0; i < super.l; ++i) {
                    Color color;
                    if (super.j.a[i] == n) {
                        color = super.w;
                    }
                    else if (super.j.a[i].c == null) {
                        color = this.bf;
                    }
                    else {
                        color = super.j.a[i].c;
                    }
                    final int j = super.j.a[i].j;
                    final int n4 = this.R + this.bb * i - 5;
                    final int n5 = this.S + this.Y - j + n2 + this.U;
                    final int[] array = { n4 + n3, n4 + n3 + n2, n4 + n3 + n2, n4 + n3 };
                    final int[] array2 = { n5, n5 - n2, this.S + this.Y + this.U, this.S + this.Y + n2 + this.U };
                    final int[] array3 = { n4 + n2, n4, n4 + n3, n4 + n3 + n2 };
                    final int[] array4 = { n5 - n2, n5, n5, n5 - n2 };
                    if (!super.j.a[i].k && super.j.a[i].a >= super.n) {
                        graphics.setColor(color);
                        graphics.fillRect(n4, n5, n3, j);
                        graphics.setColor(color.darker());
                        graphics.fillPolygon(array, array2, 4);
                        graphics.setColor(color.brighter());
                        graphics.fillPolygon(array3, array4, 4);
                    }
                }
            }
            case 2: {
                for (int k = 0; k < super.l; ++k) {
                    Color color2;
                    if (super.j.a[k] == n) {
                        color2 = super.w;
                    }
                    else if (super.j.a[k].c == null) {
                        color2 = this.bf;
                    }
                    else {
                        color2 = super.j.a[k].c;
                    }
                    int bm = 0;
                    final int n6 = this.R + this.bb * k - 5;
                    int n7;
                    int n8;
                    if (super.Q) {
                        n7 = super.j.a[k].j;
                        n8 = this.S + this.bm - n7 + n2 + this.U;
                    }
                    else {
                        n7 = super.j.a[k].j + this.bm;
                        n8 = this.S + this.bm * 2 - n7 + n2 + this.U;
                        bm = this.bm;
                    }
                    final int[] array5 = { n6 + n3, n6 + n3 + n2, n6 + n3 + n2, n6 + n3 };
                    final int[] array6 = { n8, n8 - n2, this.S + this.bm + this.U + bm, this.S + this.bm + n2 + this.U + bm };
                    if (n7 < 0) {
                        n8 = this.S + this.bm + n2 + this.U;
                        n7 = Math.abs(n7);
                    }
                    final int[] array7 = { n6 + n2, n6, n6 + n3, n6 + n3 + n2 };
                    final int[] array8 = { n8 - n2, n8, n8, n8 - n2 };
                    if (!super.j.a[k].k && (!super.p || super.j.a[k].a >= super.n)) {
                        graphics.setColor(color2);
                        graphics.fillRect(n6, n8, n3, n7);
                        graphics.setColor(color2.darker());
                        graphics.fillPolygon(array5, array6, 4);
                        graphics.setColor(color2.brighter());
                        graphics.fillPolygon(array7, array8, 4);
                    }
                    graphics.setColor(super.x);
                    if (k != super.l - 1) {
                        if (super.N) {
                            graphics.drawLine(this.R + this.bb * (k + 1) + 5, this.S + this.Y / 2, this.R - this.ba + this.bb * (k + 1) + 5, this.S + this.ba + this.Y / 2);
                            graphics.drawLine(this.R + 1 + this.bb * (k + 1) + 5, this.S + this.Y / 2, this.R + 1 - this.ba + this.bb * (k + 1) + 5, this.S + this.ba + this.Y / 2);
                        }
                        else {
                            graphics.drawLine(this.R + this.bb * (k + 1) + 5, this.S + this.Y / 2, this.R - this.ba + this.bb * (k + 1) + 5, this.S + this.ba + this.Y / 2);
                        }
                    }
                }
                graphics.setColor(super.x);
                if (super.N) {
                    graphics.drawLine(this.R - this.ba, this.S + this.Y / 2 + this.ba, this.R + this.Z - this.ba, this.S + this.Y / 2 + this.ba);
                    graphics.drawLine(this.R - this.ba, this.S - 1 + this.Y / 2 + this.ba, this.R + this.Z - this.ba, this.S - 1 + this.Y / 2 + this.ba);
                    return;
                }
                graphics.drawLine(this.R - this.ba, this.S + this.Y / 2 + this.ba, this.R + this.Z - this.ba, this.S + this.Y / 2 + this.ba);
            }
            case 3: {
                for (int l = 0; l < super.l; ++l) {
                    Color color3;
                    if (super.j.a[l] == n) {
                        color3 = super.w;
                    }
                    else if (super.j.a[l].c == null) {
                        color3 = this.bf;
                    }
                    else {
                        color3 = super.j.a[l].c;
                    }
                    final int n9 = this.R + this.bb * l - 5;
                    int abs;
                    int n10;
                    if (super.Q) {
                        abs = Math.abs(super.j.a[l].j);
                        n10 = this.S + n2 + this.U;
                    }
                    else {
                        abs = this.Y + super.j.a[l].j;
                        n10 = this.S + this.Y - abs + n2 + this.U;
                    }
                    final int[] array9 = { n9 + n3, n9 + n3 + n2, n9 + n3 + n2, n9 + n3 };
                    final int[] array10 = { n10, n10 - n2, n10 + abs - n2, n10 + abs };
                    final int[] array11 = { n9 + n2, n9, n9 + n3, n9 + n3 + n2 };
                    final int[] array12 = { n10 - n2, n10, n10, n10 - n2 };
                    graphics.setColor(super.x);
                    if (l != 0) {
                        if (super.N) {
                            graphics.drawLine(this.R - this.ba + this.bb * l + 5, this.S + this.Y + this.ba + 6, this.R - this.ba + this.bb * l + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + 1 - this.ba + this.bb * l + 5, this.S + this.Y + this.ba + 6, this.R + 1 - this.ba + this.bb * l + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + this.bb * l + 5, this.S + this.Y, this.R - this.ba + this.bb * l + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + 1 + this.bb * l + 5, this.S + this.Y, this.R + 1 - this.ba + this.bb * l + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + this.bb * l + 5, this.S + this.Y, this.R + this.bb * l + 5, this.S);
                            graphics.drawLine(this.R + 1 + this.bb * l + 5, this.S + this.Y, this.R + 1 + this.bb * l + 5, this.S);
                        }
                        else {
                            graphics.drawLine(this.R - this.ba + this.bb * l + 5, this.S + this.Y + this.ba + 6, this.R - this.ba + this.bb * l + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + this.bb * l + 5, this.S + this.Y, this.R - this.ba + this.bb * l + 5, this.S + this.ba + this.Y);
                            graphics.drawLine(this.R + this.bb * l + 5, this.S + this.Y, this.R + this.bb * l + 5, this.S);
                        }
                    }
                    if (!super.j.a[l].k && (!super.p || super.j.a[l].a >= super.n)) {
                        graphics.setColor(color3);
                        graphics.fillRect(n9, n10, n3, abs);
                        graphics.setColor(color3.darker());
                        graphics.fillPolygon(array9, array10, 4);
                        graphics.setColor(color3.brighter());
                        graphics.fillPolygon(array11, array12, 4);
                    }
                    graphics.setColor(super.x);
                    if (l != super.l - 1) {
                        if (super.N) {
                            graphics.drawLine(this.R + this.bb * (l + 1) + 5, this.S, this.R - this.ba + this.bb * (l + 1) + 5, this.S + this.ba);
                            graphics.drawLine(this.R + 1 + this.bb * (l + 1) + 5, this.S, this.R + 1 - this.ba + this.bb * (l + 1) + 5, this.S + this.ba);
                        }
                        else {
                            graphics.drawLine(this.R + this.bb * (l + 1) + 5, this.S, this.R - this.ba + this.bb * (l + 1) + 5, this.S + this.ba);
                        }
                    }
                }
                graphics.setColor(super.x);
                if (super.N) {
                    graphics.drawLine(this.R - this.ba, this.S + this.ba, this.R + this.Z - this.ba, this.S + this.ba);
                    graphics.drawLine(this.R - this.ba, this.S - 1 + this.ba, this.R + this.Z - this.ba, this.S - 1 + this.ba);
                    graphics.drawLine(this.R + this.Z, this.S, this.R + this.Z - this.ba, this.S + this.ba);
                    graphics.drawLine(this.R + 1 + this.Z, this.S, this.R + 1 + this.Z - this.ba, this.S + this.ba);
                    return;
                }
                graphics.drawLine(this.R - this.ba, this.S + this.ba, this.R + this.Z - this.ba, this.S + this.ba);
                graphics.drawLine(this.R + this.Z, this.S, this.R + this.Z - this.ba, this.S + this.ba);
            }
            default: {}
        }
    }
    
    public n a(final int n, final int n2) {
        for (int i = 0; i < super.l; ++i) {
            if (!super.j.a[i].k && n >= super.j.a[i].l && n <= super.j.a[i].n && n2 >= super.j.a[i].m && n2 <= super.j.a[i].o) {
                return super.j.a[i];
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
                graphics.drawString(super.D, this.R - this.ba + super.v.a(super.D, font, this.Z), this.S + this.Y + this.ba + super.C + (super.c + 17));
            }
            else {
                graphics.drawString(super.D, this.R - this.ba + super.v.a(super.D, font, this.Z), this.S + super.b);
            }
        }
        if (super.E != null) {
            super.v.a(this.T + 5, this.S + this.ba, graphics, super.E, super.c + 2, this.Y);
        }
    }
    
    public void a(final Color bf) {
        this.bf = bf;
    }
}
