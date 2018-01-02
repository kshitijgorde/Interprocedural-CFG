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
    private int R;
    private int S;
    private int T;
    private int U;
    private int V;
    private int W;
    private int X;
    private int Y;
    private int Z;
    private int ba;
    private double bb;
    private double bc;
    private boolean bd;
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
    
    public d(final int u, final int t, final int a, final int b, final Color g, final Color h, final int c) {
        this.bd = false;
        this.be = false;
        this.bf = new Color(0, 0, 192);
        this.bn = 1;
        super.a = a;
        super.b = b;
        super.g = g;
        super.h = h;
        this.R = u + 10;
        this.S = t + 20;
        this.T = t;
        this.U = u;
        super.c = c;
        this.W = super.a - 20;
        this.V = super.b - 20;
    }
    
    public boolean a(final int l, final o j, final String s, final String s2) {
        super.l = l;
        super.j = j;
        double[] array = new double[0];
        int n = 0;
        final Font font = new Font(super.f, 0, super.c);
        if (super.D != null) {
            this.S += 5 + (super.c + 2);
            this.V -= 5 + (super.c + 2);
        }
        if (super.E != null) {
            this.R += 15 + (super.c + 2) / 2;
            this.W -= 15 + (super.c + 2) / 2;
        }
        if (!super.p) {
            for (int i = 0; i < super.l; ++i) {
                if (!super.j.a[i].k) {
                    if (!this.bd) {
                        this.bd = true;
                        this.bb = super.j.a[i].a;
                        this.bc = this.bb;
                    }
                    else {
                        if (super.j.a[i].a > this.bb) {
                            this.bb = super.j.a[i].a;
                        }
                        if (super.j.a[i].a < this.bc) {
                            this.bc = super.j.a[i].a;
                        }
                    }
                }
            }
            if (!this.bd || (this.bb == 0.0 && this.bc == 0.0)) {
                this.bb = 4.0;
                this.bc = 0.0;
            }
            if (this.bc >= 0.0) {
                this.bn = 1;
            }
            else if (this.bb < 0.0) {
                this.bn = 3;
            }
            else {
                this.bn = 2;
            }
            switch (this.bn) {
                case 1: {
                    this.bc = 0.0;
                    array = super.v.a(5, this.bc, this.bb, true, super.H);
                    this.bb = array[4];
                    n = (int)array[5];
                    break;
                }
                case 2: {
                    this.bb = Math.max(Math.abs(this.bc), Math.abs(this.bb));
                    array = super.v.a(3, 0.0, this.bb, true, super.H);
                    this.bb = array[2];
                    n = (int)array[3];
                    break;
                }
                case 3: {
                    this.bb = Math.abs(this.bc);
                    this.bc = 0.0;
                    array = super.v.a(5, this.bc, this.bb, true, super.H);
                    this.bb = array[4];
                    n = (int)array[5];
                    break;
                }
            }
        }
        else {
            array = super.v.a(5, super.n, super.o, false, super.H);
            this.bc = super.n;
            this.bb = array[4];
            n = (int)array[5];
            if (this.bc >= 0.0) {
                this.bn = 1;
            }
            else if (this.bb <= 0.0) {
                this.bn = 3;
            }
            else {
                this.bn = 2;
            }
        }
        this.bg = String.valueOf(s2) + (int)this.bb + s;
        this.bl = super.v.b(this.bg, font);
        for (int k = 0; k < super.l; ++k) {
            if (super.j.a[k].b != null && super.v.b(super.j.a[k].b, font) > this.bm) {
                this.bm = super.v.b(super.j.a[k].b, font);
            }
        }
        this.bm += 10;
        this.R += this.bm;
        this.W = this.W - this.bm - this.bl / 2;
        this.ba = this.W / 4;
        this.W = this.ba * 4;
        if (this.bn != 2) {
            for (int n2 = 0; n2 < super.l; ++n2) {
                if (!super.j.a[n2].k) {
                    super.j.a[n2].j = (int)((Math.abs(super.j.a[n2].a) - this.bc) / (this.bb - this.bc) * this.W);
                }
            }
            if (super.p) {
                for (int n3 = 0; n3 < super.l; ++n3) {
                    if (super.j.a[n3].j > this.W) {
                        super.j.a[n3].j = this.W;
                    }
                }
            }
        }
        else {
            for (int n4 = 0; n4 < super.l; ++n4) {
                if (!super.j.a[n4].k) {
                    super.j.a[n4].j = (int)(super.j.a[n4].a / this.bb * (this.W / 2));
                }
            }
        }
        if (!super.p) {
            final double n5 = this.bb + Math.abs(this.bc) - super.n;
        }
        else {
            final double n6 = this.bb + Math.abs(this.bc);
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
                    this.bg = String.valueOf(s2) + instance.format(this.bb) + s;
                }
                catch (Exception ex) {}
                break;
            }
            case 2: {
                try {
                    this.bh = String.valueOf(s2) + instance.format(-array[1]) + s;
                    this.bi = String.valueOf(s2) + instance.format(0L) + s;
                    this.bj = String.valueOf(s2) + instance.format(array[1]) + s;
                    this.bg = String.valueOf(s2) + instance.format(this.bb) + s;
                    this.bk = String.valueOf(s2) + instance.format(-this.bb) + s;
                }
                catch (Exception ex2) {}
                break;
            }
            case 3: {
                try {
                    this.bk = String.valueOf(s2) + instance.format(array[0]) + s;
                    this.bh = String.valueOf(s2) + instance.format(-array[1]) + s;
                    this.bi = String.valueOf(s2) + instance.format(-array[2]) + s;
                    this.bj = String.valueOf(s2) + instance.format(-array[3]) + s;
                    this.bg = String.valueOf(s2) + instance.format(-this.bb) + s;
                }
                catch (Exception ex3) {}
                break;
            }
        }
        this.X = this.V / super.l;
        if (super.M != 0 && this.X + 7 > super.M) {
            this.X = super.M + 7;
        }
        this.Y = this.X * super.l;
        return true;
    }
    
    public void a(final Graphics graphics) {
        final Font font = new Font(super.f, 0, super.c);
        graphics.setFont(font);
        graphics.setColor(super.x);
        if (super.N) {
            graphics.drawLine(this.R, this.S + this.Y, this.R + this.W, this.S + this.Y);
            graphics.drawLine(this.R, this.S + 1 + this.Y, this.R + this.W, this.S + 1 + this.Y);
            graphics.drawLine(this.R + this.ba, this.S, this.R + this.ba, this.S + this.Y);
            graphics.drawLine(this.R + 1 + this.ba, this.S, this.R + 1 + this.ba, this.S + this.Y);
            graphics.drawLine(this.R + this.ba * 2, this.S, this.R + this.ba * 2, this.S + this.Y);
            graphics.drawLine(this.R + 1 + this.ba * 2, this.S, this.R + 1 + this.ba * 2, this.S + this.Y);
            graphics.drawLine(this.R + this.ba * 3, this.S, this.R + this.ba * 3, this.S + this.Y);
            graphics.drawLine(this.R + 1 + this.ba * 3, this.S, this.R + 1 + this.ba * 3, this.S + this.Y);
            graphics.drawLine(this.R + this.W, this.S, this.R + this.W, this.S + this.Y);
            graphics.drawLine(this.R + 1 + this.W, this.S, this.R + 1 + this.W, this.S + this.Y);
            for (int i = 0; i < super.l; ++i) {
                graphics.setColor(super.x);
                graphics.drawLine(this.R, this.S + this.X * (i + 1), this.R + this.W, this.S + this.X * (i + 1));
                graphics.drawLine(this.R, this.S + 1 + this.X * (i + 1), this.R + this.W, this.S + 1 + this.X * (i + 1));
                graphics.setColor(super.h);
                graphics.drawLine(this.R - 2, this.S + this.X * (i + 1), this.R + 2, this.S + this.X * (i + 1));
                graphics.drawLine(this.R - 2, this.S + 1 + this.X * (i + 1), this.R + 2, this.S + 1 + this.X * (i + 1));
            }
            graphics.setColor(super.h);
            graphics.drawLine(this.R, this.S, this.R + this.W, this.S);
            graphics.drawLine(this.R, this.S - 1, this.R + this.W, this.S - 1);
            graphics.drawLine(this.R, this.S, this.R, this.S + this.Y);
            graphics.drawLine(this.R + 1, this.S, this.R + 1, this.S + this.Y);
            graphics.drawLine(this.R + this.ba, this.S - 2, this.R + this.ba, this.S + 2);
            graphics.drawLine(this.R + 1 + this.ba, this.S - 2, this.R + 1 + this.ba, this.S + 2);
            graphics.drawLine(this.R + this.ba * 2, this.S - 2, this.R + this.ba * 2, this.S + 2);
            graphics.drawLine(this.R + 1 + this.ba * 2, this.S - 2, this.R + 1 + this.ba * 2, this.S + 2);
            graphics.drawLine(this.R + this.ba * 3, this.S - 2, this.R + this.ba * 3, this.S + 2);
            graphics.drawLine(this.R + 1 + this.ba * 3, this.S - 2, this.R + 1 + this.ba * 3, this.S + 2);
            graphics.drawLine(this.R + this.W, this.S - 2, this.R + this.W, this.S + 2);
            graphics.drawLine(this.R + 1 + this.W, this.S - 2, this.R + 1 + this.W, this.S + 2);
            graphics.drawString(this.bk, this.R - super.v.a(this.bk, font) - 3, this.S - 5);
            graphics.drawString(this.bh, this.R + this.ba - super.v.a(this.bh, font), this.S - 5);
            graphics.drawString(this.bi, this.R + this.ba * 2 - super.v.a(this.bi, font), this.S - 5);
            graphics.drawString(this.bj, this.R + this.ba * 3 - super.v.a(this.bj, font), this.S - 5);
            graphics.drawString(this.bg, this.R + this.W - super.v.a(this.bg, font), this.S - 5);
        }
        else {
            graphics.drawLine(this.R, this.S + this.Y, this.R + this.W, this.S + this.Y);
            graphics.drawLine(this.R + this.ba, this.S, this.R + this.ba, this.S + this.Y);
            graphics.drawLine(this.R + this.ba * 2, this.S, this.R + this.ba * 2, this.S + this.Y);
            graphics.drawLine(this.R + this.ba * 3, this.S, this.R + this.ba * 3, this.S + this.Y);
            graphics.drawLine(this.R + this.W, this.S, this.R + this.W, this.S + this.Y);
            for (int j = 0; j < super.l; ++j) {
                graphics.setColor(super.x);
                graphics.drawLine(this.R, this.S + this.X * (j + 1), this.R + this.W, this.S + this.X * (j + 1));
                graphics.setColor(super.h);
                graphics.drawLine(this.R - 2, this.S + this.X * (j + 1), this.R + 2, this.S + this.X * (j + 1));
            }
            graphics.setColor(super.h);
            graphics.drawLine(this.R, this.S, this.R + this.W, this.S);
            graphics.drawLine(this.R, this.S, this.R, this.S + this.Y);
            graphics.drawLine(this.R + this.ba, this.S - 2, this.R + this.ba, this.S + 2);
            graphics.drawLine(this.R + this.ba * 2, this.S - 2, this.R + this.ba * 2, this.S + 2);
            graphics.drawLine(this.R + this.ba * 3, this.S - 2, this.R + this.ba * 3, this.S + 2);
            graphics.drawLine(this.R + this.W, this.S - 2, this.R + this.W, this.S + 2);
            graphics.drawString(this.bk, this.R - super.v.a(this.bk, font) - 3, this.S - 5);
            graphics.drawString(this.bh, this.R + this.ba - super.v.a(this.bh, font), this.S - 5);
            graphics.drawString(this.bi, this.R + this.ba * 2 - super.v.a(this.bi, font), this.S - 5);
            graphics.drawString(this.bj, this.R + this.ba * 3 - super.v.a(this.bj, font), this.S - 5);
            graphics.drawString(this.bg, this.R + this.W - super.v.a(this.bg, font), this.S - 5);
        }
        if (this.bn != 2) {
            for (int k = 0; k < super.l; ++k) {
                if (super.j.a[k].c == null) {
                    graphics.setColor(this.bf);
                }
                else {
                    graphics.setColor(super.j.a[k].c);
                }
                int l;
                int m;
                if (super.Q) {
                    l = this.R + 1;
                    m = super.j.a[k].j;
                }
                else {
                    l = this.R + 1 + super.j.a[k].j;
                    m = this.W - super.j.a[k].j;
                }
                final int m2 = this.S + (this.X * (k + 1) - this.X + 4);
                final int n = this.X - 7;
                System.out.println(a("xq[u\u0016Nq[Yx") + l + a("\u00160kL7cC]L7N-") + m2 + a("\u00160kL7myMY-") + m + a("\u00160kL7ru@J-N") + n);
                if (!super.j.a[k].k) {
                    graphics.fillRect(l, m2, m, n);
                }
                if (super.j.a[k].b != null) {
                    graphics.setColor(super.h);
                    graphics.drawString(super.j.a[k].b, this.R - 10 - super.v.b(super.j.a[k].b, font), this.S + this.X * (k + 1) - this.X / 2 + 5);
                }
                if (!super.j.a[k].k && !super.y) {
                    super.j.a[k].l = l;
                    super.j.a[k].m = m2;
                    super.j.a[k].n = l + m;
                    super.j.a[k].o = m2 + n;
                }
            }
        }
        else {
            for (int n2 = 0; n2 < super.l; ++n2) {
                if (super.j.a[n2].c == null) {
                    graphics.setColor(this.bf);
                }
                else {
                    graphics.setColor(super.j.a[n2].c);
                }
                if (!super.j.a[n2].k) {
                    int l2;
                    int m3;
                    int abs;
                    int n3;
                    if (super.Q) {
                        if (super.j.a[n2].j < 0) {
                            l2 = this.R + 1 + this.W / 2 + super.j.a[n2].j;
                        }
                        else {
                            l2 = this.R + 1 + this.W / 2;
                        }
                        m3 = this.S + (this.X * (n2 + 1) - this.X + 4);
                        abs = Math.abs(super.j.a[n2].j);
                        n3 = this.X - 7;
                    }
                    else {
                        if (super.j.a[n2].j < 0) {
                            l2 = this.R + 1;
                            abs = this.W / 2 - Math.abs(super.j.a[n2].j);
                        }
                        else {
                            l2 = this.R + 1;
                            abs = Math.abs(super.j.a[n2].j) + this.W / 2;
                        }
                        m3 = this.S + (this.X * (n2 + 1) - this.X + 4);
                        n3 = this.X - 7;
                    }
                    graphics.fillRect(l2, m3, abs, n3);
                    if (!super.y) {
                        super.j.a[n2].l = l2;
                        super.j.a[n2].m = m3;
                        super.j.a[n2].n = l2 + abs;
                        super.j.a[n2].o = m3 + n3;
                    }
                    if (super.j.a[n2].b != null) {
                        graphics.setColor(super.h);
                        graphics.drawString(super.j.a[n2].b, this.R - 10 - super.v.b(super.j.a[n2].b, font), this.S + this.X * (n2 + 1) - this.X / 2 + 5);
                    }
                }
            }
            graphics.setColor(super.h);
            if (super.N) {
                graphics.drawLine(this.R + this.ba * 2, this.S, this.R + this.ba * 2, this.S + this.Y);
                graphics.drawLine(this.R + 1 + this.ba * 2, this.S, this.R + 1 + this.ba * 2, this.S + this.Y);
            }
            else {
                graphics.drawLine(this.R + this.ba * 2, this.S, this.R + this.ba * 2, this.S + this.Y);
            }
            for (int n4 = 0; n4 < super.l; ++n4) {
                graphics.drawLine(this.R + this.ba * 2 - 2, this.S + this.X * (n4 + 1), this.R + this.ba * 2 + 2, this.S + this.X * (n4 + 1));
            }
        }
        this.b(graphics);
        super.v.a(graphics, this);
        super.y = true;
    }
    
    public void a(final Graphics graphics, final n n) {
        graphics.setFont(new Font(super.f, 0, super.c));
        graphics.setColor(super.w);
        if (this.bn != 2) {
            for (int i = 0; i < super.l; ++i) {
                if (n == super.j.a[i]) {
                    if (super.Q) {
                        graphics.fillRect(this.R + 1, this.S + (this.X * (i + 1) - this.X + 4), super.j.a[i].j, this.X - 7);
                    }
                    else {
                        graphics.fillRect(this.R + 1 + super.j.a[i].j, this.S + (this.X * (i + 1) - this.X + 4), this.W - super.j.a[i].j, this.X - 7);
                    }
                }
            }
            return;
        }
        for (int j = 0; j < super.l; ++j) {
            if (n == super.j.a[j]) {
                if (super.Q) {
                    if (super.j.a[j].j < 0) {
                        graphics.fillRect(this.R + 1 + this.W / 2 + super.j.a[j].j, this.S + (this.X * (j + 1) - this.X + 4), Math.abs(super.j.a[j].j), this.X - 7);
                    }
                    else {
                        graphics.fillRect(this.R + 1 + this.W / 2, this.S + (this.X * (j + 1) - this.X + 4), super.j.a[j].j, this.X - 7);
                    }
                }
                else if (super.j.a[j].j < 0) {
                    graphics.fillRect(this.R + 1, this.S + (this.X * (j + 1) - this.X + 4), this.W / 2 - Math.abs(super.j.a[j].j), this.X - 7);
                }
                else {
                    graphics.fillRect(this.R + 1, this.S + (this.X * (j + 1) - this.X + 4), super.j.a[j].j + this.W / 2, this.X - 7);
                }
            }
        }
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
            graphics.drawString(super.D, this.R + super.v.a(super.D, font, this.W), this.T + super.c);
        }
        if (super.E != null) {
            super.v.a(this.U + 5, this.S, graphics, super.E, super.c + 2, this.V);
        }
    }
    
    public void a(final Color bf) {
        this.bf = bf;
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = ':';
                    break;
                }
                case 1: {
                    c2 = '\u0010';
                    break;
                }
                case 2: {
                    c2 = ')';
                    break;
                }
                case 3: {
                    c2 = '-';
                    break;
                }
                default: {
                    c2 = 'E';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
