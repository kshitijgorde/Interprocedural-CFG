// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Graphics;
import java.text.NumberFormat;
import java.awt.Font;
import java.text.DecimalFormat;
import java.awt.Color;

public class i extends b
{
    private int bl;
    private int bm;
    private int bn;
    private int bo;
    private int bp;
    private int bq;
    private int br;
    public Color bs;
    private boolean bt;
    
    public i(final int bl, final int bm, final int a, final int b, final Color g, final Color h, final int c) {
        this.bp = 30;
        this.bs = Color.darkGray;
        this.bt = false;
        super.a = a;
        super.b = b;
        super.g = g;
        super.h = h;
        this.bl = bl;
        this.bm = bm;
        super.c = c;
        this.bq = super.a - 10;
        this.br = super.b - this.bp - 15;
        this.bn = this.bl + this.bq / 2;
        this.bo = this.bm + this.br / 2;
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
        final DecimalFormat decimalFormat = new DecimalFormat(b("UPr;i"));
        if (super.l > 0) {
            for (int k = 0; k < super.l; ++k) {
                if (super.j.a[k].k == null) {
                    if (super.j.a[k].a == 0.0) {
                        super.j.a[k].k = String.valueOf(s2) + d.format(0L) + s + b("V[") + decimalFormat.format(0L) + b("SZ");
                    }
                    else {
                        super.j.a[k].k = String.valueOf(s2) + d.format(super.j.a[k].a) + s + b("V[") + decimalFormat.format(super.j.a[k].a * (100.0 / n)) + b("SZ");
                    }
                }
            }
            if (super.l > 0) {
                super.j.a[0].x = 0;
                super.j.a[0].y = (int)Math.round(super.j.a[0].a * n2);
                if (!super.j.a[0].s) {
                    this.bt = true;
                }
                for (int n3 = 1; n3 < super.l; ++n3) {
                    if (!super.j.a[n3].s) {
                        super.j.a[n3].x = super.j.a[n3 - 1].y;
                        super.j.a[n3].y = (int)(Math.round(super.j.a[n3].a * n2) + super.j.a[n3].x);
                        if (!this.bt) {
                            this.bt = true;
                        }
                    }
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
                this.br -= (super.c + 5) * 2;
                super.b -= (super.c + 5) * 2;
                this.bq -= n4 * 2;
                super.a -= n4 * 2;
                this.bm += super.c + 5;
                this.bl += n4;
                this.bq = super.a - 10;
                this.br = super.b - this.bp - 15;
                this.bn = this.bl + this.bq / 2;
                this.bo = this.bm + this.br / 2;
            }
        }
        return true;
    }
    
    public void a(final Graphics graphics) {
        if (this.bt) {
            graphics.setColor(this.bs);
            graphics.fillOval(this.bl, this.bm + this.bp - 5, super.a, super.b - this.bp + 5);
        }
        for (int i = 0; i < super.l; ++i) {
            if (!super.j.a[i].s && super.j.a[i].y >= 180) {
                graphics.setColor(super.j.a[i].g.darker());
                for (int j = 0; j < this.bp; ++j) {
                    graphics.drawArc(this.bl, this.bm + j, this.bq, this.br, super.j.a[i].x, super.j.a[i].y - super.j.a[i].x);
                }
            }
        }
        for (int k = 0; k < super.l; ++k) {
            graphics.setColor(super.j.a[k].g);
            graphics.fillArc(this.bl, this.bm, this.bq, this.br, super.j.a[k].x, super.j.a[k].y - super.j.a[k].x);
        }
        super.C.a(graphics, this);
        if (super.be) {
            final Font font = new Font(super.f, 0, super.c);
            graphics.setFont(font);
            graphics.setColor(super.h);
            final double n = this.br / this.bq;
            final int n2 = this.bq / 2;
            final int n3 = 7;
            for (int l = 0; l < super.l; ++l) {
                final String m = super.j.a[l].k;
                final int x = super.j.a[l].x;
                final int n4 = 360 - x - (super.j.a[l].y - x) / 2;
                final int n5 = (int)(n2 * Math.cos(super.C.i(n4)));
                final int n6 = (int)(n2 * n * Math.sin(super.C.i(n4)));
                final int n7 = n5 + this.bn;
                final int n8 = n6 + this.bo;
                final int n9 = (int)(n3 * Math.cos(super.C.i(n4)));
                final int n10 = (int)(n3 * Math.sin(super.C.i(n4)));
                int n11 = n9 + n7;
                int n12 = n10 + n8;
                final int c = super.C.c(m, font);
                if (n4 < 180) {
                    n12 += this.bp + 10;
                }
                if (n4 > 250 && n4 < 290) {
                    n11 -= c / 2;
                }
                else if (n4 > 70 && n4 < 110) {
                    n11 -= c / 2;
                    n12 += super.c;
                }
                else if (n4 > 160 && n4 < 200) {
                    n11 -= c;
                    n12 += super.c / 2;
                }
                else if (n4 > 340 && n4 < 20) {
                    n12 += super.c / 2;
                }
                else if (n4 >= 200 && n4 <= 250) {
                    n11 -= c;
                }
                else if (n4 < 290 || n4 > 340) {
                    if (n4 >= 20 && n4 <= 70) {
                        n12 += super.c;
                    }
                    else if (n4 >= 110 && n4 <= 160) {
                        n11 -= c;
                        n12 += super.c;
                    }
                }
                graphics.drawString(m, n11, n12);
            }
        }
    }
    
    public void a(final Graphics graphics, final o o) {
        for (int i = 0; i < super.l; ++i) {
            if (!super.j.a[i].s && super.j.a[i].y >= 180) {
                if (o == super.j.a[i]) {
                    graphics.setColor(super.D.darker());
                }
                else {
                    graphics.setColor(super.j.a[i].g.darker());
                }
                for (int j = 0; j < this.bp; ++j) {
                    graphics.drawArc(this.bl, this.bm + j, this.bq, this.br, super.j.a[i].x, super.j.a[i].y - super.j.a[i].x);
                }
            }
        }
        for (int k = 0; k < super.l; ++k) {
            if (!super.j.a[k].s) {
                if (o == super.j.a[k]) {
                    graphics.setColor(super.D);
                }
                else {
                    graphics.setColor(super.j.a[k].g);
                }
                graphics.fillArc(this.bl, this.bm, this.bq, this.br, super.j.a[k].x, super.j.a[k].y - super.j.a[k].x);
            }
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
    
    public void a(final Color bs) {
        this.bs = bs;
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
                    c2 = 'v';
                    break;
                }
                case 1: {
                    c2 = 's';
                    break;
                }
                case 2: {
                    c2 = 'Q';
                    break;
                }
                case 3: {
                    c2 = '\u0015';
                    break;
                }
                default: {
                    c2 = 'J';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
