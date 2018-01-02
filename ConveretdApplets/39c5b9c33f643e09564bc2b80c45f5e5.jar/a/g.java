// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Font;
import java.text.NumberFormat;
import java.awt.Color;

public class g extends b
{
    private int R;
    private int S;
    private int T;
    private int U;
    private int V;
    private int W;
    private int X;
    private double Y;
    private int Z;
    private int ba;
    private int bb;
    private int bc;
    private int bd;
    public double[] be;
    public int[][] bf;
    private String bg;
    private String bh;
    private String bi;
    private String bj;
    private String bk;
    private int bl;
    
    public g(final int t, final int s, final int a, final int b, final Color g, final Color h, final int c) {
        this.U = 3;
        this.V = 6;
        this.bb = 20;
        super.a = a;
        super.b = b;
        super.g = g;
        super.h = h;
        this.R = t + 20 + this.bb;
        this.S = s;
        this.T = t;
        super.c = c;
        this.Z = super.a - 40 - this.bb;
        this.ba = super.b - 25 - this.bb;
    }
    
    public boolean a(final int l, final m k, final String s, final String s2) {
        super.l = l;
        super.k = k;
        this.bd = super.k.a.size();
        final double[] array = new double[0];
        if (super.l == 0) {
            return false;
        }
        if (super.D != null) {
            this.ba -= 5 + (super.c + 2);
        }
        if (super.E != null) {
            this.R += 15 + (super.c + 2) / 2;
            this.Z -= 15 + (super.c + 2) / 2;
        }
        this.be = new double[super.l + 1];
        this.bf = new int[super.l][this.bd + 1];
        for (int i = 0; i < this.bd; ++i) {
            final o o = super.k.a.elementAt(i);
            for (int j = 0; j < super.l; ++j) {
                if (o.a[j] != null) {
                    final double[] be = this.be;
                    final int n = j;
                    be[n] += o.a[j].a;
                }
            }
        }
        for (int n2 = 0; n2 < super.l; ++n2) {
            if (this.be[n2] > this.Y) {
                this.Y = this.be[n2];
            }
        }
        if (this.Y == 0.0) {
            this.Y = 4.0;
        }
        double[] array2;
        if (!super.p) {
            array2 = super.v.a(5, 0.0, this.Y, true, super.H);
        }
        else if (super.o > this.Y) {
            array2 = super.v.a(5, 0.0, super.o, false, super.H);
        }
        else {
            array2 = super.v.a(5, 0.0, this.Y, false, super.H);
        }
        this.Y = array2[4];
        final int n3 = (int)array2[5];
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(n3);
        instance.setMinimumFractionDigits(n3);
        try {
            this.bk = String.valueOf(s2) + instance.format(array2[0]) + s;
            this.bh = String.valueOf(s2) + instance.format(array2[1]) + s;
            this.bi = String.valueOf(s2) + instance.format(array2[2]) + s;
            this.bj = String.valueOf(s2) + instance.format(array2[3]) + s;
            this.bg = String.valueOf(s2) + instance.format(this.Y) + s;
        }
        catch (Exception ex) {}
        this.bl = super.v.b(this.bg, new Font(super.f, 0, super.c));
        this.R += this.bl;
        this.Z -= this.bl;
        this.bc = this.Z / super.l;
        if (super.M != 0 && this.bc - 4 - this.V > super.M) {
            this.bc = super.M - 4 - this.V + this.bb;
        }
        this.Z = this.bc * super.l + 20;
        if (!super.B) {
            if (super.K == 1) {
                super.A = super.v.a(super.k, super.l, this.bc, super.c);
            }
            if (super.A) {
                this.ba -= super.c + 5;
                if (super.D != null) {
                    this.ba -= super.c + 2;
                }
            }
        }
        for (int n4 = 0; n4 < this.bd; ++n4) {
            final o o2 = super.k.a.elementAt(n4);
            for (int n5 = 0; n5 < super.l; ++n5) {
                if (o2.a[n5] != null) {
                    this.bf[n5][n4] = (int)(o2.a[n5].a / this.Y * this.ba);
                }
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
            graphics.drawRect(this.R, this.S, this.Z, this.ba);
            graphics.drawRect(this.R + 1, this.S + 1, this.Z - 2, this.ba - 2);
            graphics.drawLine(this.R - this.bb, this.S + this.bb, this.R - this.bb, this.S + this.bb + this.ba);
            graphics.drawLine(this.R - this.bb + 1, this.S + this.bb, this.R - this.bb + 1, this.S + this.bb + this.ba);
            graphics.drawLine(this.R - this.bb, this.S + this.bb + this.ba, this.R - this.bb + this.Z, this.S + this.bb + this.ba);
            graphics.drawLine(this.R - this.bb, this.S + this.bb + this.ba - 1, this.R - this.bb + this.Z, this.S + this.bb + this.ba - 1);
            graphics.drawLine(this.R, this.S, this.R - this.bb, this.S + this.bb);
            graphics.drawLine(this.R + 1, this.S, this.R + 1 - this.bb, this.S + this.bb);
            graphics.drawLine(this.R, this.S + this.ba, this.R - this.bb, this.S + this.bb + this.ba);
            graphics.drawLine(this.R + 1, this.S + this.ba, this.R + 1 - this.bb, this.S + this.bb + this.ba);
            graphics.drawLine(this.R + this.Z, this.S + this.ba, this.R - this.bb + this.Z, this.S + this.bb + this.ba);
            graphics.drawLine(this.R - 1 + this.Z, this.S + this.ba, this.R - 1 - this.bb + this.Z, this.S + this.bb + this.ba);
            graphics.drawLine(this.R - this.bb, this.S + this.ba + this.bb + 6, this.R - this.bb, this.S + this.bb + this.ba);
            graphics.drawLine(this.R + 1 - this.bb, this.S + this.ba + this.bb + 6, this.R + 1 - this.bb, this.S + this.bb + this.ba);
            graphics.drawLine(this.R + this.Z - this.bb, this.S + this.ba + this.bb + 6, this.R + this.Z - this.bb, this.S + this.bb + this.ba);
            graphics.drawLine(this.R + 1 + this.Z - this.bb, this.S + this.ba + this.bb + 6, this.R + 1 + this.Z - this.bb, this.S + this.bb + this.ba);
            graphics.drawLine(this.R - this.bb - 6, this.S + this.ba + this.bb, this.R - this.bb, this.S + this.bb + this.ba);
            graphics.drawLine(this.R - this.bb - 6, this.S - 1 + this.ba + this.bb, this.R - this.bb, this.S - 1 + this.bb + this.ba);
            graphics.drawLine(this.R - this.bb - 6, this.S + this.bb + this.ba / 4, this.R - this.bb, this.S + this.bb + this.ba / 4);
            graphics.drawLine(this.R - this.bb - 6, this.S + 1 + this.bb + this.ba / 4, this.R - this.bb, this.S + 1 + this.bb + this.ba / 4);
            graphics.drawLine(this.R - this.bb - 6, this.S + this.bb + this.ba / 2, this.R - this.bb, this.S + this.bb + this.ba / 2);
            graphics.drawLine(this.R - this.bb - 6, this.S - 1 + this.bb + this.ba / 2, this.R - this.bb, this.S - 1 + this.bb + this.ba / 2);
            graphics.drawLine(this.R - this.bb - 6, this.S + this.bb + this.ba / 4 * 3, this.R - this.bb, this.S + this.bb + this.ba / 4 * 3);
            graphics.drawLine(this.R - this.bb - 6, this.S + 1 + this.bb + this.ba / 4 * 3, this.R - this.bb, this.S + 1 + this.bb + this.ba / 4 * 3);
            graphics.drawLine(this.R - this.bb - 6, this.S + this.bb, this.R - this.bb, this.S + this.bb);
            graphics.drawLine(this.R - this.bb - 6, this.S + 1 + this.bb, this.R - this.bb, this.S + 1 + this.bb);
            graphics.drawLine(this.R, this.S + this.ba / 4, this.R - this.bb, this.S + this.bb + this.ba / 4);
            graphics.drawLine(this.R + 1, this.S + this.ba / 4, this.R + 1 - this.bb, this.S + this.bb + this.ba / 4);
            graphics.drawLine(this.R, this.S + this.ba / 4 * 3, this.R - this.bb, this.S + this.bb + this.ba / 4 * 3);
            graphics.drawLine(this.R + 1, this.S + this.ba / 4 * 3, this.R + 1 - this.bb, this.S + this.bb + this.ba / 4 * 3);
            graphics.drawLine(this.R, this.S + this.ba / 4, this.R + this.Z, this.S + this.ba / 4);
            graphics.drawLine(this.R, this.S + 1 + this.ba / 4, this.R + this.Z, this.S + 1 + this.ba / 4);
            graphics.drawLine(this.R, this.S + this.ba / 4 * 3, this.R + this.Z, this.S + this.ba / 4 * 3);
            graphics.drawLine(this.R, this.S + 1 + this.ba / 4 * 3, this.R + this.Z, this.S + 1 + this.ba / 4 * 3);
            graphics.drawLine(this.R, this.S + this.ba / 2, this.R - this.bb, this.S + this.bb + this.ba / 2);
            graphics.drawLine(this.R + 1, this.S + this.ba / 2, this.R + 1 - this.bb, this.S + this.bb + this.ba / 2);
            graphics.drawLine(this.R, this.S + this.ba / 2, this.R + this.Z, this.S + this.ba / 2);
            graphics.drawLine(this.R, this.S + 1 + this.ba / 2, this.R + this.Z, this.S + 1 + this.ba / 2);
        }
        else {
            graphics.drawRect(this.R, this.S, this.Z, this.ba);
            graphics.drawLine(this.R - this.bb, this.S + this.bb, this.R - this.bb, this.S + this.bb + this.ba);
            graphics.drawLine(this.R - this.bb, this.S + this.bb + this.ba, this.R - this.bb + this.Z, this.S + this.bb + this.ba);
            graphics.drawLine(this.R, this.S, this.R - this.bb, this.S + this.bb);
            graphics.drawLine(this.R, this.S + this.ba, this.R - this.bb, this.S + this.bb + this.ba);
            graphics.drawLine(this.R + this.Z, this.S + this.ba, this.R - this.bb + this.Z, this.S + this.bb + this.ba);
            graphics.drawLine(this.R - this.bb, this.S + this.ba + this.bb + 6, this.R - this.bb, this.S + this.bb + this.ba);
            graphics.drawLine(this.R + this.Z - this.bb, this.S + this.ba + this.bb + 6, this.R + this.Z - this.bb, this.S + this.bb + this.ba);
            graphics.drawLine(this.R - this.bb - 6, this.S + this.ba + this.bb, this.R - this.bb, this.S + this.bb + this.ba);
            graphics.drawLine(this.R - this.bb - 6, this.S + this.bb + this.ba / 4, this.R - this.bb, this.S + this.bb + this.ba / 4);
            graphics.drawLine(this.R - this.bb - 6, this.S + this.bb + this.ba / 2, this.R - this.bb, this.S + this.bb + this.ba / 2);
            graphics.drawLine(this.R - this.bb - 6, this.S + this.bb + this.ba / 4 * 3, this.R - this.bb, this.S + this.bb + this.ba / 4 * 3);
            graphics.drawLine(this.R - this.bb - 6, this.S + this.bb, this.R - this.bb, this.S + this.bb);
            graphics.drawLine(this.R, this.S + this.ba / 4, this.R - this.bb, this.S + this.bb + this.ba / 4);
            graphics.drawLine(this.R, this.S + this.ba / 2, this.R - this.bb, this.S + this.bb + this.ba / 2);
            graphics.drawLine(this.R, this.S + this.ba / 4 * 3, this.R - this.bb, this.S + this.bb + this.ba / 4 * 3);
            graphics.drawLine(this.R, this.S + this.ba / 4, this.R + this.Z, this.S + this.ba / 4);
            graphics.drawLine(this.R, this.S + this.ba / 2, this.R + this.Z, this.S + this.ba / 2);
            graphics.drawLine(this.R, this.S + this.ba / 4 * 3, this.R + this.Z, this.S + this.ba / 4 * 3);
        }
        graphics.setColor(super.h);
        graphics.drawString(this.bk, this.R - 15 - this.bb - super.v.b(this.bk, font), this.S + this.bb + this.ba + 3);
        graphics.drawString(this.bh, this.R - 15 - this.bb - super.v.b(this.bh, font), this.S + this.bb + this.ba / 4 * 3 + 3);
        graphics.drawString(this.bi, this.R - 15 - this.bb - super.v.b(this.bi, font), this.S + this.bb + this.ba / 2 + 3);
        graphics.drawString(this.bj, this.R - 15 - this.bb - super.v.b(this.bj, font), this.S + this.bb + this.ba / 4 + 3);
        graphics.drawString(this.bg, this.R - 15 - this.bb - super.v.b(this.bg, font), this.S + this.bb + 3);
        if (this.bc < 25) {
            this.U = 1;
        }
        final int n2 = this.bb - 6;
        final int n3 = this.bc - 4 - this.V;
        for (int i = 0; i < super.l; ++i) {
            graphics.setColor(super.x);
            if (i != 0) {
                if (super.N) {
                    graphics.drawLine(this.R - this.bb + this.bc * i + 5, this.S + this.ba + this.bb + 6, this.R - this.bb + this.bc * i + 5, this.S + this.bb + this.ba);
                    graphics.drawLine(this.R + 1 - this.bb + this.bc * i + 5, this.S + this.ba + this.bb + 6, this.R + 1 - this.bb + this.bc * i + 5, this.S + this.bb + this.ba);
                    graphics.drawLine(this.R + this.bc * i + 5, this.S + this.ba, this.R - this.bb + this.bc * i + 5, this.S + this.bb + this.ba);
                    graphics.drawLine(this.R + 1 + this.bc * i + 5, this.S + this.ba, this.R + 1 - this.bb + this.bc * i + 5, this.S + this.bb + this.ba);
                    graphics.drawLine(this.R + this.bc * i + 5, this.S + this.ba, this.R + this.bc * i + 5, this.S);
                    graphics.drawLine(this.R + 1 + this.bc * i + 5, this.S + this.ba, this.R + 1 + this.bc * i + 5, this.S);
                }
                else {
                    graphics.drawLine(this.R - this.bb + this.bc * i + 5, this.S + this.ba + this.bb + 6, this.R - this.bb + this.bc * i + 5, this.S + this.bb + this.ba);
                    graphics.drawLine(this.R + this.bc * i + 5, this.S + this.ba, this.R - this.bb + this.bc * i + 5, this.S + this.bb + this.ba);
                    graphics.drawLine(this.R + this.bc * i + 5, this.S + this.ba, this.R + this.bc * i + 5, this.S);
                }
            }
            for (int j = 0; j < this.bd; ++j) {
                final o o = super.k.a.elementAt(j);
                final Color c = o.c;
                if (this.bf[i][j] != 0) {
                    final int n4 = this.bf[i][j];
                    final int l = this.R + this.bc * i - 5;
                    final int m = this.S + this.ba - n4 + n2 + this.U - this.bf[i][this.bd];
                    final int[] array = { l + n3, l + n3 + n2, l + n3 + n2, l + n3 };
                    final int[] array2 = { m, m - n2, this.S + this.ba + this.U - this.bf[i][this.bd], this.S + this.ba + n2 + this.U - this.bf[i][this.bd] };
                    final int[] array3 = { l + n2, l, l + n3, l + n3 + n2 };
                    final int[] array4 = { m - n2, m, m, m - n2 };
                    Color c2;
                    if (o.a[i].c != null) {
                        c2 = o.a[i].c;
                    }
                    else {
                        c2 = c;
                    }
                    graphics.setColor(c2);
                    graphics.fillRect(l, m, n3, n4);
                    graphics.setColor(c2.darker());
                    graphics.fillPolygon(array, array2, 4);
                    graphics.setColor(c2.brighter());
                    graphics.fillPolygon(array3, array4, 4);
                    if (!super.y && o.a[i] != null) {
                        o.a[i].l = l;
                        o.a[i].m = m;
                        o.a[i].n = l + n3;
                        o.a[i].o = m + n4;
                    }
                    final int[] array5 = this.bf[i];
                    final int bd = this.bd;
                    array5[bd] += this.bf[i][j];
                }
            }
            for (int k = 0; k < super.l; ++k) {
                this.bf[k][this.bd] = 0;
            }
        }
        graphics.setColor(super.h);
        for (int n5 = 0; n5 < super.l; ++n5) {
            if (!super.B) {
                if (super.k.b[n5] != null) {
                    if (!super.A) {
                        graphics.drawString(super.k.b[n5], this.R + n5 * this.bc + n3 / 2 - 10 - super.v.a(super.k.b[n5], font), this.S + this.ba + this.bb + 20);
                    }
                    else if (n != 0) {
                        graphics.drawString(super.k.b[n5], this.R + n5 * this.bc + n3 / 2 - 10 - super.v.a(super.k.b[n5], font), this.S + this.ba + this.bb + 20);
                    }
                    else {
                        graphics.drawString(super.k.b[n5], this.R + n5 * this.bc + n3 / 2 - 10 - super.v.a(super.k.b[n5], font), this.S + this.ba + this.bb + 25 + super.c);
                    }
                }
                if (n != 0) {
                    n = 0;
                }
                else {
                    n = 1;
                }
            }
            else if (super.k.b[n5] != null) {
                final Image a = super.v.a(super.k.b[n5], font, true, super.h, super.g);
                if (a != null) {
                    graphics.drawImage(a, this.R + n5 * this.bc + n3 / 2 - 10 - super.c / 2, this.S + this.ba + this.bb + 5, null);
                }
            }
        }
        this.b(graphics);
        super.v.a(graphics, this);
        super.y = true;
    }
    
    public void a(final Graphics graphics, final n n) {
        final int n2 = this.bb - 6;
        final int n3 = this.bc - 4 - this.V;
        for (int i = 0; i < super.l; ++i) {
            for (int j = 0; j < this.bd; ++j) {
                final o o = super.k.a.elementAt(j);
                final Color c = o.c;
                if (this.bf[i][j] != 0) {
                    final int n4 = this.bf[i][j];
                    final int n5 = this.R + this.bc * i - 5;
                    final int n6 = this.S + this.ba - n4 + n2 + this.U - this.bf[i][this.bd];
                    final int[] array = { n5 + n3, n5 + n3 + n2, n5 + n3 + n2, n5 + n3 };
                    final int[] array2 = { n6, n6 - n2, this.S + this.ba + this.U - this.bf[i][this.bd], this.S + this.ba + n2 + this.U - this.bf[i][this.bd] };
                    final int[] array3 = { n5 + n2, n5, n5 + n3, n5 + n3 + n2 };
                    final int[] array4 = { n6 - n2, n6, n6, n6 - n2 };
                    if (o.a[i] == n) {
                        graphics.setColor(super.w);
                        graphics.fillRect(n5, n6, n3, n4);
                        graphics.setColor(super.w.darker());
                        graphics.fillPolygon(array, array2, 4);
                        graphics.setColor(super.w.brighter());
                        graphics.fillPolygon(array3, array4, 4);
                    }
                    else {
                        Color c2;
                        if (o.a[i].c != null) {
                            c2 = o.a[i].c;
                        }
                        else {
                            c2 = c;
                        }
                        graphics.setColor(c2);
                        graphics.fillRect(n5, n6, n3, n4);
                        graphics.setColor(c2.darker());
                        graphics.fillPolygon(array, array2, 4);
                        graphics.setColor(c2.brighter());
                        graphics.fillPolygon(array3, array4, 4);
                    }
                    final int[] array5 = this.bf[i];
                    final int bd = this.bd;
                    array5[bd] += this.bf[i][j];
                }
            }
            for (int k = 0; k < super.l; ++k) {
                this.bf[k][this.bd] = 0;
            }
        }
    }
    
    public n a(final int n, final int n2) {
        for (int i = 0; i < this.bd; ++i) {
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
                graphics.drawString(super.D, this.R - this.bb + super.v.a(super.D, font, this.Z), this.S + this.ba + this.bb + super.c + 10 + super.C);
            }
            else {
                graphics.drawString(super.D, this.R - this.bb + super.v.a(super.D, font, this.Z), this.S + super.b);
            }
        }
        if (super.E != null) {
            super.v.a(this.T + 5, this.S + this.bb, graphics, super.E, super.c + 2, this.ba);
        }
    }
}
