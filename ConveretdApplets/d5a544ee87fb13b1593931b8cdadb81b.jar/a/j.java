// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Graphics;
import java.text.NumberFormat;
import java.awt.Font;
import java.text.DecimalFormat;
import java.awt.Color;

public class j extends b
{
    private int bl;
    private int bm;
    private int bn;
    private int bo;
    private int bp;
    private int bq;
    private int br;
    private int bs;
    
    public j(final int bl, final int bm, final int a, final int b, final Color g, final Color h, final int c) {
        this.bp = 30;
        this.bs = 50;
        super.a = a;
        super.b = b;
        super.g = g;
        super.h = h;
        this.bl = bl;
        this.bm = bm;
        super.c = c;
        if (super.a > super.b) {
            final int n = super.b - 10;
            this.bq = n;
            this.br = n;
        }
        else {
            final int n2 = super.a - 10;
            this.bq = n2;
            this.br = n2;
        }
        this.bl = (super.a - this.bq) / 2;
        this.bn = this.bl + this.bq / 2;
        this.bo = this.bm + this.br / 2;
        super.bh = b("8]LKH");
    }
    
    public boolean a(final int l, final p j, final String s, final String s2) {
        double n = 0.0;
        super.l = l;
        super.j = j;
        for (int i = 0; i < super.l; ++i) {
            n = (float)(n + super.j.a[i].a);
        }
        final double n2 = 360.0 / n;
        final NumberFormat d = super.C.d(super.bg);
        final DecimalFormat decimalFormat = new DecimalFormat(b("):?\f\u000e"));
        if (super.l > 0) {
            for (int k = 0; k < super.l; ++k) {
                if (super.j.a[k].k == null) {
                    if (super.j.a[k].a == 0.0) {
                        super.j.a[k].k = String.valueOf(s2) + d.format(0L) + s + b("*1") + decimalFormat.format(0L) + b("/0");
                    }
                    else {
                        super.j.a[k].k = String.valueOf(s2) + d.format(super.j.a[k].a) + s + b("*1") + decimalFormat.format(super.j.a[k].a * (100.0 / n)) + b("/0");
                    }
                }
            }
            super.j.a[0].x = 0;
            super.j.a[0].y = (int)Math.round(super.j.a[0].a * n2);
            for (int n3 = 1; n3 < super.l; ++n3) {
                super.j.a[n3].x = super.j.a[n3 - 1].y;
                super.j.a[n3].y = (int)(Math.round(super.j.a[n3].a * n2) + super.j.a[n3].x);
            }
            super.j.a[super.l - 1].y = 360;
        }
        if (super.be) {
            int n4 = 0;
            final Font font = new Font(super.f, 0, super.c);
            for (int n5 = 0; n5 < super.l; ++n5) {
                final int c = super.C.c(super.j.a[n5].k, font);
                if (c > n4) {
                    n4 = c;
                }
            }
            this.br = super.b - 10 - (super.c + 2) * 2;
            this.bq = super.a - 10 - n4 * 2;
            final int min = Math.min(this.br, this.bq);
            this.bq = min;
            this.br = min;
            this.bl = (super.a - this.bq) / 2;
            this.bm += super.c + 2;
            this.bn = this.bl + this.bq / 2;
            this.bo = this.bm + this.br / 2;
        }
        return true;
    }
    
    public void a(final Graphics graphics) {
        for (int i = 0; i < super.l; ++i) {
            graphics.setColor(super.j.a[i].g);
            graphics.fillArc(this.bl, this.bm, this.bq, this.br, super.j.a[i].x, super.j.a[i].y - super.j.a[i].x);
        }
        super.C.a(graphics, this);
        if (super.be) {
            final Font font = new Font(super.f, 0, super.c);
            graphics.setFont(font);
            graphics.setColor(super.h);
            for (int j = 0; j < super.l; ++j) {
                final String k = super.j.a[j].k;
                final int n = this.bq / 2;
                final int n2 = 5;
                final int x = super.j.a[j].x;
                final int n3 = 360 - x - (super.j.a[j].y - x) / 2;
                final int n4 = (int)(n * Math.cos(super.C.i(n3)));
                final int n5 = (int)(n * Math.sin(super.C.i(n3)));
                final int n6 = n4 + this.bn;
                final int n7 = n5 + this.bo;
                final int n8 = (int)(n2 * Math.cos(super.C.i(n3)));
                final int n9 = (int)(n2 * Math.sin(super.C.i(n3)));
                int n10 = n8 + n6;
                int n11 = n9 + n7;
                final int c = super.C.c(k, font);
                if (n3 > 250 && n3 < 290) {
                    n10 -= c / 2;
                }
                else if (n3 > 70 && n3 < 110) {
                    n10 -= c / 2;
                    n11 += super.c;
                }
                else if (n3 > 160 && n3 < 200) {
                    n10 -= c;
                    n11 += super.c / 2;
                }
                else if (n3 > 340 && n3 < 20) {
                    n11 += super.c / 2;
                }
                else if (n3 >= 200 && n3 <= 250) {
                    n10 -= c;
                }
                else if (n3 < 290 || n3 > 340) {
                    if (n3 >= 20 && n3 <= 70) {
                        n11 += super.c;
                    }
                    else if (n3 >= 110 && n3 <= 160) {
                        n10 -= c;
                        n11 += super.c;
                    }
                }
                graphics.drawString(k, n10, n11);
            }
        }
        if (super.bh.compareTo(b("8]XMC\u007fm")) == 0) {
            final int n12 = this.bq * this.bs / 100;
            final int n13 = this.br * this.bs / 100;
            final int n14 = this.bl + this.bq * (100 - this.bs) / 200;
            final int n15 = this.bm + this.bq * (100 - this.bs) / 200;
            graphics.setColor(super.g);
            graphics.fillOval(n14, n15, n12, n13);
        }
    }
    
    public void a(final Graphics graphics, final o o) {
        for (int i = 0; i < super.l; ++i) {
            if (!super.j.a[i].s) {
                if (o == super.j.a[i]) {
                    graphics.setColor(super.D);
                }
                else {
                    graphics.setColor(super.j.a[i].g);
                }
                graphics.fillArc(this.bl, this.bm, this.bq, this.br, super.j.a[i].x, super.j.a[i].y - super.j.a[i].x);
            }
        }
        if (super.bh.compareTo(b("8]XMC\u007fm")) == 0) {
            final int n = this.bq * this.bs / 100;
            final int n2 = this.br * this.bs / 100;
            final int n3 = this.bl + this.bq * (100 - this.bs) / 200;
            final int n4 = this.bm + this.bq * (100 - this.bs) / 200;
            graphics.setColor(super.g);
            graphics.fillOval(n3, n4, n, n2);
        }
    }
    
    public o a(final int n, final int n2) {
        final double n3 = n - this.bn;
        final double n4 = this.bo - n2;
        final double n5 = n3 / (this.bq / 2);
        final double n6 = n4 / (this.br / 2);
        if (n5 * n5 + n6 * n6 <= 1.0) {
            final int n7 = (int)(57.29577951308232 * Math.atan(n5 / n6));
            int n8;
            if (n4 >= 0.0) {
                n8 = 90 - n7;
            }
            else {
                n8 = 270 - n7;
            }
            for (int i = 0; i < super.l; ++i) {
                if (!super.j.a[i].s && n8 >= super.j.a[i].x && n8 <= super.j.a[i].y) {
                    return super.j.a[i];
                }
            }
        }
        return null;
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
                    c2 = '\n';
                    break;
                }
                case 1: {
                    c2 = '\u0019';
                    break;
                }
                case 2: {
                    c2 = '\u001c';
                    break;
                }
                case 3: {
                    c2 = '\"';
                    break;
                }
                default: {
                    c2 = '-';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
