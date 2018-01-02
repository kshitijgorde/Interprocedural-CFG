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

public class l extends b
{
    private int R;
    private int S;
    private int T;
    private int U;
    private int V;
    private int W;
    private int X;
    private int Y;
    private double Z;
    private double ba;
    private int bb;
    private int bc;
    private int bd;
    private Color be;
    private boolean bf;
    private String bg;
    private String bh;
    private String bi;
    private String bj;
    private String bk;
    private int bl;
    private int bm;
    
    public l(final int t, final int s, final int a, final int b, final Color g, final Color h, final int c) {
        this.U = 3;
        this.X = 3;
        this.be = new Color(0, 0, 192);
        this.bf = false;
        this.bm = 1;
        super.a = a;
        super.b = b;
        super.g = g;
        super.h = h;
        this.R = t + 20;
        this.S = s;
        this.T = t;
        super.c = c;
        this.bb = super.a - 40;
        this.bc = super.b - 25;
    }
    
    public boolean a(final int l, final o j, final String s, final String s2) {
        super.l = l;
        super.j = j;
        double[] array = new double[0];
        int n = 0;
        if (super.D != null) {
            this.bc -= 5 + (super.c + 2);
        }
        if (super.E != null) {
            this.R += 15 + (super.c + 2) / 2;
            this.bb -= 15 + (super.c + 2) / 2;
        }
        if (!super.p) {
            for (int i = 0; i < super.l; ++i) {
                if (!super.j.a[i].k) {
                    if (!this.bf) {
                        this.bf = true;
                        this.Z = super.j.a[i].a;
                        this.ba = this.Z;
                    }
                    else {
                        if (super.j.a[i].a > this.Z) {
                            this.Z = super.j.a[i].a;
                        }
                        if (super.j.a[i].a < this.ba) {
                            this.ba = super.j.a[i].a;
                        }
                    }
                }
            }
            if (this.Z == 0.0 && this.ba == 0.0) {
                this.Z = 4.0;
            }
            if (this.ba >= 0.0) {
                this.bm = 1;
            }
            else if (this.Z <= 0.0) {
                this.bm = 3;
            }
            else {
                this.bm = 2;
            }
            switch (this.bm) {
                case 1: {
                    this.ba = 0.0;
                    array = super.v.a(5, this.ba, this.Z, true, super.H);
                    this.Z = array[4];
                    n = (int)array[5];
                    break;
                }
                case 2: {
                    this.Z = Math.max(Math.abs(this.ba), Math.abs(this.Z));
                    array = super.v.a(3, 0.0, this.Z, true, super.H);
                    this.Z = array[2];
                    n = (int)array[3];
                    break;
                }
                case 3: {
                    this.Z = Math.abs(this.ba);
                    this.ba = 0.0;
                    array = super.v.a(5, this.ba, this.Z, true, super.H);
                    this.Z = 0.0;
                    this.ba = -array[4];
                    n = (int)array[5];
                    break;
                }
            }
        }
        else {
            array = super.v.a(5, super.n, super.o, false, super.H);
            this.ba = super.n;
            this.Z = array[4];
            n = (int)array[5];
            if (this.ba >= 0.0) {
                this.bm = 1;
            }
            else if (this.Z <= 0.0) {
                this.bm = 3;
            }
            else {
                this.bm = 2;
            }
        }
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(n);
        instance.setMinimumFractionDigits(n);
        switch (this.bm) {
            case 1: {
                try {
                    this.bk = String.valueOf(s2) + instance.format(array[0]) + s;
                    this.bh = String.valueOf(s2) + instance.format(array[1]) + s;
                    this.bi = String.valueOf(s2) + instance.format(array[2]) + s;
                    this.bj = String.valueOf(s2) + instance.format(array[3]) + s;
                    this.bg = String.valueOf(s2) + instance.format(this.Z) + s;
                }
                catch (Exception ex) {}
                break;
            }
            case 2: {
                try {
                    this.bh = String.valueOf(s2) + instance.format(-array[1]) + s;
                    this.bi = String.valueOf(s2) + instance.format(0L) + s;
                    this.bj = String.valueOf(s2) + instance.format(array[1]) + s;
                    this.bg = String.valueOf(s2) + instance.format(this.Z) + s;
                    this.bk = String.valueOf(s2) + instance.format(-this.Z) + s;
                }
                catch (Exception ex2) {}
                break;
            }
            case 3: {
                try {
                    this.bk = String.valueOf(s2) + instance.format(this.ba) + s;
                    this.bh = String.valueOf(s2) + instance.format(-array[3]) + s;
                    this.bi = String.valueOf(s2) + instance.format(-array[2]) + s;
                    this.bj = String.valueOf(s2) + instance.format(-array[1]) + s;
                    this.bg = String.valueOf(s2) + instance.format(this.Z) + s;
                }
                catch (Exception ex3) {}
                break;
            }
        }
        this.bl = super.v.b(this.bg, new Font(super.f, 0, super.c));
        this.R += this.bl;
        this.bb -= this.bl;
        this.Y = (this.bb - (this.X + this.X) * super.l + 1) / super.l;
        if (super.M != 0 && this.Y > super.M) {
            this.Y = super.M;
        }
        if (!super.B) {
            if (super.K == 1) {
                super.A = super.v.a(super.j, super.l, this.Y, super.c);
            }
            if (super.A) {
                this.bc -= super.c + 5;
                if (super.D != null) {
                    this.bc -= super.c + 2;
                }
            }
        }
        switch (this.bm) {
            case 1: {
                for (int k = 0; k < super.l; ++k) {
                    if (!super.j.a[k].k) {
                        super.j.a[k].j = (int)((super.j.a[k].a - this.ba) / (this.Z - this.ba) * this.bc);
                        if (super.j.a[k].j > this.bc) {
                            super.j.a[k].j = this.bc;
                        }
                    }
                }
                break;
            }
            case 2: {
                final int m = this.bc / 2;
                for (int n2 = 0; n2 < super.l; ++n2) {
                    if (!super.j.a[n2].k) {
                        super.j.a[n2].j = (int)(super.j.a[n2].a / this.Z * m);
                        if (super.j.a[n2].j > m) {
                            super.j.a[n2].j = m;
                        }
                        if (super.j.a[n2].j < -m) {
                            super.j.a[n2].j = -m;
                        }
                    }
                }
                break;
            }
            case 3: {
                for (int n3 = 0; n3 < super.l; ++n3) {
                    if (!super.j.a[n3].k) {
                        super.j.a[n3].j = (int)((this.Z + super.j.a[n3].a) / (this.Z - this.ba) * this.bc);
                        if (super.j.a[n3].j > this.bc) {
                            super.j.a[n3].j = this.bc;
                        }
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
            graphics.drawRect(this.R, this.S, this.bb - 1, this.bc);
            graphics.drawRect(this.R + 1, this.S - 1, this.bb - 1, this.bc + 2);
            graphics.drawLine(this.R, this.S + this.bc + 6, this.R, this.S + this.bc);
            graphics.drawLine(this.R + 1, this.S + this.bc + 6, this.R + 1, this.S + this.bc);
            graphics.drawLine(this.R + this.bb, this.S + this.bc + 6, this.R + this.bb, this.S + this.bc);
            graphics.drawLine(this.R - 1 + this.bb, this.S + this.bc + 6, this.R - 1 + this.bb, this.S + this.bc);
            graphics.drawLine(this.R, this.S + this.bc / 4, this.R + this.bb, this.S + this.bc / 4);
            graphics.drawLine(this.R, this.S + 1 + this.bc / 4, this.R + this.bb, this.S + 1 + this.bc / 4);
            graphics.drawLine(this.R, this.S + this.bc / 2, this.R + this.bb, this.S + this.bc / 2);
            graphics.drawLine(this.R, this.S + 1 + this.bc / 2, this.R + this.bb, this.S + 1 + this.bc / 2);
            graphics.drawLine(this.R, this.S + this.bc / 4 * 3, this.R + this.bb, this.S + this.bc / 4 * 3);
            graphics.drawLine(this.R, this.S + 1 + this.bc / 4 * 3, this.R + this.bb, this.S + 1 + this.bc / 4 * 3);
            graphics.setColor(super.h);
            graphics.drawString(this.bk, this.R - 15 - super.v.b(this.bk, font), this.S + this.bc + 3);
            graphics.drawString(this.bh, this.R - 15 - super.v.b(this.bh, font), this.S + this.bc / 4 * 3 + 3);
            graphics.drawString(this.bi, this.R - 15 - super.v.b(this.bi, font), this.S + this.bc / 2 + 3);
            graphics.drawString(this.bj, this.R - 15 - super.v.b(this.bj, font), this.S + this.bc / 4 + 3);
            graphics.drawString(this.bg, this.R - 15 - super.v.b(this.bg, font), this.S + 3);
            graphics.setColor(super.h);
            graphics.drawLine(this.R - 6, this.S + this.bc, this.R, this.S + this.bc);
            graphics.drawLine(this.R - 6, this.S + 1 + this.bc, this.R, this.S + 1 + this.bc);
            graphics.drawLine(this.R, this.S - 1, this.R, this.S + this.bc + 6);
            graphics.drawLine(this.R + 1, this.S - 1, this.R + 1, this.S + this.bc + 6);
            graphics.drawLine(this.R, this.S + this.bc, this.R + this.bb, this.S + this.bc);
            graphics.drawLine(this.R, this.S + this.bc + 1, this.R + this.bb, this.S + this.bc + 1);
            graphics.drawLine(this.R - 6, this.S + this.bc / 4, this.R, this.S + this.bc / 4);
            graphics.drawLine(this.R - 6, this.S + 1 + this.bc / 4, this.R, this.S + 1 + this.bc / 4);
            graphics.drawLine(this.R - 6, this.S + this.bc / 2, this.R, this.S + this.bc / 2);
            graphics.drawLine(this.R - 6, this.S + 1 + this.bc / 2, this.R, this.S + 1 + this.bc / 2);
            graphics.drawLine(this.R - 6, this.S + this.bc / 4 * 3, this.R, this.S + this.bc / 4 * 3);
            graphics.drawLine(this.R - 6, this.S + 1 + this.bc / 4 * 3, this.R, this.S + 1 + this.bc / 4 * 3);
            graphics.drawLine(this.R - 6, this.S, this.R, this.S);
            graphics.drawLine(this.R - 6, this.S - 1, this.R, this.S - 1);
            graphics.drawLine(this.R + this.bb, this.S + this.bc + 6, this.R + this.bb, this.S + this.bc);
            graphics.drawLine(this.R + this.bb - 1, this.S + this.bc + 6, this.R + this.bb - 1, this.S + this.bc);
        }
        else {
            graphics.drawRect(this.R, this.S, this.bb, this.bc);
            graphics.drawLine(this.R, this.S + this.bc + 6, this.R, this.S + this.bc);
            graphics.drawLine(this.R + this.bb, this.S + this.bc + 6, this.R + this.bb, this.S + this.bc);
            graphics.drawLine(this.R - 6, this.S + this.bc, this.R, this.S + this.bc);
            graphics.drawLine(this.R - 6, this.S + this.bc / 4, this.R, this.S + this.bc / 4);
            graphics.drawLine(this.R - 6, this.S + this.bc / 2, this.R, this.S + this.bc / 2);
            graphics.drawLine(this.R - 6, this.S + this.bc / 4 * 3, this.R, this.S + this.bc / 4 * 3);
            graphics.drawLine(this.R - 6, this.S, this.R, this.S);
            graphics.drawLine(this.R, this.S + this.bc / 4, this.R + this.bb, this.S + this.bc / 4);
            graphics.drawLine(this.R, this.S + this.bc / 2, this.R + this.bb, this.S + this.bc / 2);
            graphics.drawLine(this.R, this.S + this.bc / 4 * 3, this.R + this.bb, this.S + this.bc / 4 * 3);
            graphics.setColor(super.h);
            graphics.drawString(this.bk, this.R - 15 - super.v.b(this.bk, font), this.S + this.bc + 3);
            graphics.drawString(this.bh, this.R - 15 - super.v.b(this.bh, font), this.S + this.bc / 4 * 3 + 3);
            graphics.drawString(this.bi, this.R - 15 - super.v.b(this.bi, font), this.S + this.bc / 2 + 3);
            graphics.drawString(this.bj, this.R - 15 - super.v.b(this.bj, font), this.S + this.bc / 4 + 3);
            graphics.drawString(this.bg, this.R - 15 - super.v.b(this.bg, font), this.S + 3);
            graphics.setColor(super.h);
            graphics.drawLine(this.R - 6, this.S + this.bc, this.R, this.S + this.bc);
            graphics.drawLine(this.R, this.S, this.R, this.S + this.bc + 6);
            graphics.drawLine(this.R, this.S + this.bc, this.R + this.bb, this.S + this.bc);
            graphics.drawLine(this.R - 6, this.S + this.bc / 4, this.R, this.S + this.bc / 4);
            graphics.drawLine(this.R - 6, this.S + this.bc / 2, this.R, this.S + this.bc / 2);
            graphics.drawLine(this.R - 6, this.S + this.bc / 4 * 3, this.R, this.S + this.bc / 4 * 3);
            graphics.drawLine(this.R - 6, this.S, this.R, this.S);
            graphics.drawLine(this.R + this.bb, this.S + this.bc + 6, this.R + this.bb, this.S + this.bc);
        }
        int s = 0;
        int l = 0;
        final int n2 = this.Y + this.X + this.X;
        for (int i = 0; i < super.l; ++i) {
            if (!super.j.a[i].k) {
                if (super.j.a[i].c == null) {
                    graphics.setColor(this.be);
                }
                else {
                    graphics.setColor(super.j.a[i].c);
                }
                l = this.R + 1 + this.X + i * n2;
                int n3 = super.j.a[i].j;
                switch (this.bm) {
                    case 1: {
                        s = this.S + this.bc - n3;
                        graphics.fillRect(l, s, this.Y, n3);
                        break;
                    }
                    case 2: {
                        if (super.Q) {
                            if (n3 > 0) {
                                s = this.S + this.bc / 2 - n3;
                            }
                            else {
                                s = this.S + this.bc / 2;
                                n3 = Math.abs(n3);
                            }
                        }
                        else {
                            s = this.S + this.bc / 2 - n3;
                            n3 += this.bc / 2;
                        }
                        graphics.fillRect(l, s, this.Y, n3);
                        break;
                    }
                    case 3: {
                        n3 = Math.abs(n3);
                        if (!super.Q) {
                            s = this.S + n3;
                            n3 = this.bc - n3;
                        }
                        else {
                            s = this.S;
                        }
                        graphics.fillRect(l, s, this.Y, n3);
                        break;
                    }
                }
                if (!super.y && super.j.a[i] != null) {
                    super.j.a[i].l = l;
                    super.j.a[i].m = s;
                    super.j.a[i].n = l + this.Y;
                    super.j.a[i].o = s + n3;
                }
            }
            graphics.setColor(super.h);
            if (!super.B) {
                if (super.j.a[i].b != null) {
                    final int n4 = this.R + 1 + this.X + (this.X * 2 + this.Y) * i + this.Y / 2 - super.v.a(super.j.a[i].b, font);
                    if (!super.A) {
                        graphics.drawString(super.j.a[i].b, n4, this.S + this.bc + 20);
                    }
                    else if (n != 0) {
                        graphics.drawString(super.j.a[i].b, n4, this.S + this.bc + 20);
                    }
                    else {
                        graphics.drawString(super.j.a[i].b, n4, this.S + this.bc + 25 + super.c);
                    }
                }
                if (n != 0) {
                    n = 0;
                }
                else {
                    n = 1;
                }
            }
            else if (super.j.a[i].b != null) {
                final int n5 = this.R + 1 + this.X + (this.X * 2 + this.Y) * i + this.Y / 2 - super.c / 2;
                final Image a = super.v.a(super.j.a[i].b, font, true, super.h, super.g);
                if (a != null) {
                    graphics.drawImage(a, n5, this.S + this.bc + 10, null);
                }
            }
            graphics.setColor(super.x);
            if (i != 0) {
                l = l - this.X - 1;
                if (super.N) {
                    graphics.drawLine(l, this.S + this.bc - 1, l, this.S);
                    graphics.drawLine(l + 1, this.S + this.bc - 1, l + 1, this.S);
                    graphics.setColor(super.h);
                    graphics.drawLine(l, this.S + this.bc + 6, l, this.S + this.bc);
                    graphics.drawLine(l + 1, this.S + this.bc + 6, l + 1, this.S + this.bc);
                }
                else {
                    graphics.drawLine(l, this.S + this.bc, l, this.S);
                    graphics.setColor(super.h);
                    graphics.drawLine(l, this.S + this.bc + 6, l, this.S + this.bc);
                }
            }
        }
        graphics.setColor(super.h);
        switch (this.bm) {
            case 2: {
                final int n6 = this.bc / 2;
                if (super.N) {
                    graphics.drawLine(this.R, this.S + n6, this.R + this.bb, this.S + n6);
                    graphics.drawLine(this.R, this.S + n6 + 1, this.R + this.bb, this.S + n6 + 1);
                    break;
                }
                graphics.drawLine(this.R, this.S + n6, this.R + this.bb, this.S + n6);
                break;
            }
            case 3: {
                if (super.N) {
                    graphics.drawLine(this.R, this.S, this.R + this.bb, this.S);
                    graphics.drawLine(this.R, this.S - 1, this.R + this.bb, this.S - 1);
                    break;
                }
                graphics.drawLine(this.R, this.S, this.R + this.bb, this.S);
                break;
            }
        }
        this.b(graphics);
        super.v.a(graphics, this);
        super.y = true;
    }
    
    public void a(final Graphics graphics, final n n) {
        graphics.setColor(super.w);
        graphics.fillRect(n.l, n.m, n.n - n.l, n.o - n.m);
    }
    
    public n a(final int n, final int n2) {
        for (int i = 0; i < super.l; ++i) {
            if (n >= super.j.a[i].l && n <= super.j.a[i].n && n2 >= super.j.a[i].m && n2 <= super.j.a[i].o) {
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
                graphics.drawString(super.D, this.R + super.v.a(super.D, font, this.bb), this.S + this.bc + super.C + super.c + 15);
            }
            else {
                graphics.drawString(super.D, this.R + super.v.a(super.D, font, this.bb), this.S + super.b);
            }
        }
        if (super.E != null) {
            super.v.a(this.T + 5, this.S, graphics, super.E, super.c + 2, this.bc);
        }
    }
    
    public void a(final Color be) {
        this.be = be;
    }
}
