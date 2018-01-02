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

public class e extends b
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
    private int bd;
    private int be;
    private int bf;
    private int bg;
    public double[] bh;
    public int[][] bi;
    private String bj;
    private String bk;
    private String bl;
    private String bm;
    private String bn;
    private int bo;
    private int bp;
    
    public e(final int t, final int s, final int a, final int b, final Color g, final Color h, final int c) {
        this.U = 3;
        this.X = 6;
        this.Y = 2;
        this.bp = 1;
        super.a = a;
        super.b = b;
        super.g = g;
        super.h = h;
        this.R = t + 20;
        this.S = s;
        this.T = t;
        super.c = c;
        this.bd = super.a - 40;
        this.be = super.b - 25;
    }
    
    public boolean a(final int l, final m k, final String s, final String s2) {
        super.l = l;
        super.k = k;
        this.bg = super.k.a.size();
        double[] array = new double[0];
        int n = 0;
        if (super.l < 1 || this.bg < 1) {
            return false;
        }
        if (super.D != null) {
            this.be -= 5 + (super.c + 2);
        }
        if (super.E != null) {
            this.R += 15 + (super.c + 2) / 2;
            this.bd -= 15 + (super.c + 2) / 2;
        }
        this.bi = new int[super.l][this.bg + 1];
        if (!super.p) {
            for (int i = 0; i < this.bg; ++i) {
                final o o = super.k.a.elementAt(i);
                for (int j = 0; j < super.l; ++j) {
                    if (o.a[j] != null) {
                        if (o.a[j].a > this.bb) {
                            this.bb = o.a[j].a;
                        }
                        if (o.a[j].a < this.bc) {
                            this.bc = o.a[j].a;
                        }
                    }
                }
            }
            if (this.bb == 0.0 && this.bc == 0.0) {
                this.bb = 4.0;
            }
            if (this.bc >= 0.0) {
                this.bp = 1;
            }
            else if (this.bb <= 0.0) {
                this.bp = 3;
            }
            else {
                this.bp = 2;
            }
            switch (this.bp) {
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
                    this.bb = 0.0;
                    this.bc = -array[4];
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
                this.bp = 1;
            }
            else if (this.bb <= 0.0) {
                this.bp = 3;
            }
            else {
                this.bp = 2;
            }
        }
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(n);
        instance.setMinimumFractionDigits(n);
        switch (this.bp) {
            case 1: {
                try {
                    this.bn = String.valueOf(s2) + instance.format(array[0]) + s;
                    this.bk = String.valueOf(s2) + instance.format(array[1]) + s;
                    this.bl = String.valueOf(s2) + instance.format(array[2]) + s;
                    this.bm = String.valueOf(s2) + instance.format(array[3]) + s;
                    this.bj = String.valueOf(s2) + instance.format(this.bb) + s;
                }
                catch (Exception ex) {}
                break;
            }
            case 2: {
                try {
                    this.bk = String.valueOf(s2) + instance.format(-array[1]) + s;
                    this.bl = String.valueOf(s2) + instance.format(0L) + s;
                    this.bm = String.valueOf(s2) + instance.format(array[1]) + s;
                    this.bj = String.valueOf(s2) + instance.format(this.bb) + s;
                    this.bn = String.valueOf(s2) + instance.format(-this.bb) + s;
                }
                catch (Exception ex2) {}
                break;
            }
            case 3: {
                try {
                    this.bn = String.valueOf(s2) + instance.format(this.bc) + s;
                    this.bk = String.valueOf(s2) + instance.format(-array[3]) + s;
                    this.bl = String.valueOf(s2) + instance.format(-array[2]) + s;
                    this.bm = String.valueOf(s2) + instance.format(-array[1]) + s;
                    this.bj = String.valueOf(s2) + instance.format(this.bb) + s;
                }
                catch (Exception ex3) {}
                break;
            }
        }
        this.bo = super.v.b(this.bj, new Font(super.f, 0, super.c));
        this.R += this.bo;
        this.bd -= this.bo;
        if (super.N) {
            this.Z = (this.bd - this.X * super.l) / super.l - 1;
        }
        else {
            this.Z = (this.bd - this.X * super.l) / super.l;
        }
        this.ba = (this.Z - this.Y * (this.bg - 1)) / this.bg;
        if (super.M != 0 && this.ba > super.M) {
            this.ba = super.M;
            System.out.println(a("d]\u0006/Rc"));
            this.Z = (this.ba + this.Y) * this.bg;
            System.out.println(a("g^\u0014%R"));
            System.out.println(a("AJ\u000f5PUH\u0001#E<") + this.Z);
        }
        if (!super.B) {
            if (super.K == 1) {
                super.A = super.v.a(super.k, super.l, this.Z, super.c);
            }
            if (super.A) {
                this.be -= super.c + 5;
                if (super.D != null) {
                    this.be -= super.c + 2;
                }
            }
        }
        switch (this.bp) {
            case 1: {
                for (int n2 = 0; n2 < this.bg; ++n2) {
                    final o o2 = super.k.a.elementAt(n2);
                    for (int n3 = 0; n3 < super.l; ++n3) {
                        if (o2.a[n3] != null) {
                            this.bi[n3][n2] = (int)((o2.a[n3].a - this.bc) / (this.bb - this.bc) * this.be);
                        }
                        if (this.bi[n3][n2] > this.be) {
                            this.bi[n3][n2] = this.be;
                        }
                    }
                }
                break;
            }
            case 2: {
                final int n4 = this.be / 2;
                for (int n5 = 0; n5 < this.bg; ++n5) {
                    final o o3 = super.k.a.elementAt(n5);
                    for (int n6 = 0; n6 < super.l; ++n6) {
                        if (o3.a[n6] != null) {
                            this.bi[n6][n5] = (int)(o3.a[n6].a / this.bb * n4);
                        }
                        if (this.bi[n6][n5] > n4) {
                            this.bi[n6][n5] = n4;
                        }
                        if (this.bi[n6][n5] < -n4) {
                            this.bi[n6][n5] = -n4;
                        }
                    }
                }
                break;
            }
            case 3: {
                for (int n7 = 0; n7 < this.bg; ++n7) {
                    final o o4 = super.k.a.elementAt(n7);
                    for (int n8 = 0; n8 < super.l; ++n8) {
                        if (o4.a[n8] != null) {
                            this.bi[n8][n7] = (int)((o4.a[n8].a + this.bb) / (this.bb - this.bc) * this.be);
                        }
                        if (this.bi[n8][n7] < -this.be) {
                            this.bi[n8][n7] = -this.be;
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
            graphics.drawRect(this.R, this.S, this.bd - 1, this.be);
            graphics.drawRect(this.R + 1, this.S - 1, this.bd - 1, this.be + 2);
            graphics.drawLine(this.R, this.S + this.be + 6, this.R, this.S + this.be);
            graphics.drawLine(this.R + 1, this.S + this.be + 6, this.R + 1, this.S + this.be);
            graphics.drawLine(this.R + this.bd, this.S + this.be + 6, this.R + this.bd, this.S + this.be);
            graphics.drawLine(this.R - 1 + this.bd, this.S + this.be + 6, this.R - 1 + this.bd, this.S + this.be);
            graphics.drawLine(this.R, this.S + this.be / 4, this.R + this.bd, this.S + this.be / 4);
            graphics.drawLine(this.R, this.S + 1 + this.be / 4, this.R + this.bd, this.S + 1 + this.be / 4);
            graphics.drawLine(this.R, this.S + this.be / 2, this.R + this.bd, this.S + this.be / 2);
            graphics.drawLine(this.R, this.S + 1 + this.be / 2, this.R + this.bd, this.S + 1 + this.be / 2);
            graphics.drawLine(this.R, this.S + this.be / 4 * 3, this.R + this.bd, this.S + this.be / 4 * 3);
            graphics.drawLine(this.R, this.S + 1 + this.be / 4 * 3, this.R + this.bd, this.S + 1 + this.be / 4 * 3);
            graphics.setColor(super.h);
            graphics.drawString(this.bn, this.R - 15 - super.v.b(this.bn, font), this.S + this.be + 3);
            graphics.drawString(this.bk, this.R - 15 - super.v.b(this.bk, font), this.S + this.be / 4 * 3 + 3);
            graphics.drawString(this.bl, this.R - 15 - super.v.b(this.bl, font), this.S + this.be / 2 + 3);
            graphics.drawString(this.bm, this.R - 15 - super.v.b(this.bm, font), this.S + this.be / 4 + 3);
            graphics.drawString(this.bj, this.R - 15 - super.v.b(this.bj, font), this.S + 3);
            graphics.setColor(super.h);
            graphics.drawLine(this.R - 6, this.S + this.be, this.R, this.S + this.be);
            graphics.drawLine(this.R - 6, this.S + 1 + this.be, this.R, this.S + 1 + this.be);
            graphics.drawLine(this.R, this.S - 1, this.R, this.S + this.be + 6);
            graphics.drawLine(this.R + 1, this.S - 1, this.R + 1, this.S + this.be + 6);
            graphics.drawLine(this.R, this.S + this.be, this.R + this.bd, this.S + this.be);
            graphics.drawLine(this.R, this.S + this.be + 1, this.R + this.bd, this.S + this.be + 1);
            graphics.drawLine(this.R - 6, this.S + this.be / 4, this.R, this.S + this.be / 4);
            graphics.drawLine(this.R - 6, this.S + 1 + this.be / 4, this.R, this.S + 1 + this.be / 4);
            graphics.drawLine(this.R - 6, this.S + this.be / 2, this.R, this.S + this.be / 2);
            graphics.drawLine(this.R - 6, this.S + 1 + this.be / 2, this.R, this.S + 1 + this.be / 2);
            graphics.drawLine(this.R - 6, this.S + this.be / 4 * 3, this.R, this.S + this.be / 4 * 3);
            graphics.drawLine(this.R - 6, this.S + 1 + this.be / 4 * 3, this.R, this.S + 1 + this.be / 4 * 3);
            graphics.drawLine(this.R - 6, this.S, this.R, this.S);
            graphics.drawLine(this.R - 6, this.S - 1, this.R, this.S - 1);
            graphics.drawLine(this.R + this.bd, this.S + this.be + 6, this.R + this.bd, this.S + this.be);
            graphics.drawLine(this.R + this.bd - 1, this.S + this.be + 6, this.R + this.bd - 1, this.S + this.be);
        }
        else {
            graphics.drawRect(this.R, this.S, this.bd, this.be);
            graphics.drawLine(this.R, this.S + this.be + 6, this.R, this.S + this.be);
            graphics.drawLine(this.R + this.bd, this.S + this.be + 6, this.R + this.bd, this.S + this.be);
            graphics.drawLine(this.R - 6, this.S + this.be, this.R, this.S + this.be);
            graphics.drawLine(this.R - 6, this.S + this.be / 4, this.R, this.S + this.be / 4);
            graphics.drawLine(this.R - 6, this.S + this.be / 2, this.R, this.S + this.be / 2);
            graphics.drawLine(this.R - 6, this.S + this.be / 4 * 3, this.R, this.S + this.be / 4 * 3);
            graphics.drawLine(this.R - 6, this.S, this.R, this.S);
            graphics.drawLine(this.R, this.S + this.be / 4, this.R + this.bd, this.S + this.be / 4);
            graphics.drawLine(this.R, this.S + this.be / 2, this.R + this.bd, this.S + this.be / 2);
            graphics.drawLine(this.R, this.S + this.be / 4 * 3, this.R + this.bd, this.S + this.be / 4 * 3);
            graphics.setColor(super.h);
            graphics.drawString(this.bn, this.R - 15 - super.v.b(this.bn, font), this.S + this.be + 3);
            graphics.drawString(this.bk, this.R - 15 - super.v.b(this.bk, font), this.S + this.be / 4 * 3 + 3);
            graphics.drawString(this.bl, this.R - 15 - super.v.b(this.bl, font), this.S + this.be / 2 + 3);
            graphics.drawString(this.bm, this.R - 15 - super.v.b(this.bm, font), this.S + this.be / 4 + 3);
            graphics.drawString(this.bj, this.R - 15 - super.v.b(this.bj, font), this.S + 3);
            graphics.setColor(super.h);
            graphics.drawLine(this.R - 6, this.S + this.be, this.R, this.S + this.be);
            graphics.drawLine(this.R, this.S, this.R, this.S + this.be + 6);
            graphics.drawLine(this.R, this.S + this.be, this.R + this.bd, this.S + this.be);
            graphics.drawLine(this.R - 6, this.S + this.be / 4, this.R, this.S + this.be / 4);
            graphics.drawLine(this.R - 6, this.S + this.be / 2, this.R, this.S + this.be / 2);
            graphics.drawLine(this.R - 6, this.S + this.be / 4 * 3, this.R, this.S + this.be / 4 * 3);
            graphics.drawLine(this.R - 6, this.S, this.R, this.S);
            graphics.drawLine(this.R + this.bd, this.S + this.be + 6, this.R + this.bd, this.S + this.be);
        }
        if (this.bf < 25) {
            this.U = 1;
        }
        for (int i = 0; i < super.l; ++i) {
            final int n2 = this.R + 2 + this.X / 2 + this.Z * i + this.X * i;
            graphics.setColor(super.x);
            if (i != 0) {
                final int n3 = n2 - this.X / 2 - 1;
                if (super.N) {
                    graphics.drawLine(n3, this.S + this.be - 1, n3, this.S);
                    graphics.drawLine(n3 + 1, this.S + this.be - 1, n3 + 1, this.S);
                    graphics.setColor(super.h);
                    graphics.drawLine(n3, this.S + this.be + 6, n3, this.S + this.be);
                    graphics.drawLine(n3 + 1, this.S + this.be + 6, n3 + 1, this.S + this.be);
                }
                else {
                    graphics.drawLine(n3, this.S + this.be, n3, this.S);
                    graphics.setColor(super.h);
                    graphics.drawLine(n3, this.S + this.be + 6, n3, this.S + this.be);
                }
            }
            graphics.setColor(super.h);
            if (!super.B) {
                if (super.k.b[i] != null) {
                    final int n4 = n2 + this.Z / 2 - super.v.a(super.k.b[i], font);
                    if (!super.A) {
                        graphics.drawString(super.k.b[i], n4, this.S + this.be + 20);
                    }
                    else if (n != 0) {
                        graphics.drawString(super.k.b[i], n4, this.S + this.be + 20);
                    }
                    else {
                        graphics.drawString(super.k.b[i], n4, this.S + this.be + 25 + super.c);
                    }
                }
                if (n != 0) {
                    n = 0;
                }
                else {
                    n = 1;
                }
            }
            else if (super.k.b[i] != null) {
                final Image a = super.v.a(super.k.b[i], font, true, super.h, super.g);
                if (a != null) {
                    graphics.drawImage(a, n2 + this.Z / 2 - super.c / 2, this.S + this.be + 10, null);
                }
            }
            for (int j = 0; j < this.bg; ++j) {
                final o o = super.k.a.elementAt(j);
                Color color = o.c;
                if (this.bi[i][j] != 0) {
                    int n5 = this.bi[i][j];
                    int s = 0;
                    final int l = n2 + this.ba * j + this.Y * j;
                    if (o.a[i].c != null) {
                        color = o.a[i].c;
                    }
                    graphics.setColor(color);
                    switch (this.bp) {
                        case 1: {
                            s = this.S + this.be - n5;
                            graphics.fillRect(l, s, this.ba, n5);
                            break;
                        }
                        case 2: {
                            if (super.Q) {
                                if (n5 >= 0) {
                                    s = this.S + this.be / 2 - n5;
                                }
                                else {
                                    s = this.S + this.be / 2;
                                    n5 = Math.abs(n5);
                                }
                            }
                            else if (n5 >= 0) {
                                s = this.S + this.be / 2 - n5;
                                n5 += this.be / 2;
                            }
                            else {
                                s = this.S + Math.abs(n5) + this.be / 2;
                                n5 = this.be / 2 - Math.abs(n5);
                            }
                            graphics.fillRect(l, s, this.ba, n5);
                            break;
                        }
                        case 3: {
                            if (super.Q) {
                                n5 = Math.abs(n5);
                                s = this.S;
                            }
                            else {
                                s = this.S + Math.abs(n5);
                                n5 = this.be - Math.abs(n5);
                            }
                            graphics.fillRect(l, s, this.ba, n5);
                            break;
                        }
                    }
                    if (!super.y && o.a[i] != null) {
                        o.a[i].l = l;
                        o.a[i].m = s;
                        o.a[i].n = l + this.ba;
                        o.a[i].o = s + n5;
                    }
                    final int[] array = this.bi[i];
                    final int bg = this.bg;
                    array[bg] += this.bi[i][j];
                }
            }
        }
        graphics.setColor(super.h);
        switch (this.bp) {
            case 2: {
                final int n6 = this.be / 2;
                if (super.N) {
                    graphics.drawLine(this.R, this.S + n6, this.R + this.bd, this.S + n6);
                    graphics.drawLine(this.R, this.S + n6 + 1, this.R + this.bd, this.S + n6 + 1);
                    break;
                }
                graphics.drawLine(this.R, this.S + n6, this.R + this.bd, this.S + n6);
                break;
            }
            case 3: {
                if (super.N) {
                    graphics.drawLine(this.R, this.S, this.R + this.bd, this.S);
                    graphics.drawLine(this.R, this.S - 1, this.R + this.bd, this.S - 1);
                    break;
                }
                graphics.drawLine(this.R, this.S, this.R + this.bd, this.S);
                break;
            }
        }
        for (int k = 0; k < super.l; ++k) {
            this.bi[k][this.bg] = 0;
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
        for (int i = 0; i < this.bg; ++i) {
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
                graphics.drawString(super.D, this.R + super.v.a(super.D, font, this.bd), this.S + this.be + super.C + super.c + 17);
            }
            else {
                graphics.drawString(super.D, this.R + super.v.a(super.D, font, this.bd), this.S + super.b);
            }
        }
        if (super.E != null) {
            super.v.a(this.T + 5, this.S, graphics, super.E, super.c + 2, this.be);
        }
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
                    c2 = '\u0006';
                    break;
                }
                case 1: {
                    c2 = '8';
                    break;
                }
                case 2: {
                    c2 = '`';
                    break;
                }
                case 3: {
                    c2 = '@';
                    break;
                }
                default: {
                    c2 = ' ';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
