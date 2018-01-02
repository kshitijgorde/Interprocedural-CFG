// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Graphics;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.Color;

public class j extends b
{
    private int R;
    private int S;
    private int T;
    private int U;
    private int V;
    private int W;
    private int X;
    public Color Y;
    
    public j(final int r, final int s, final int a, final int b, final Color g, final Color h, final int c) {
        this.V = 30;
        this.Y = Color.darkGray;
        super.a = a;
        super.b = b;
        super.g = g;
        super.h = h;
        this.R = r;
        this.S = s;
        super.c = c;
        this.W = super.a - 10;
        this.X = super.b - this.V - 15;
        this.T = this.R + this.W / 2;
        this.U = this.S + this.X / 2;
    }
    
    public boolean a(final int l, final o j, final String s, final String s2) {
        double n = 0.0;
        super.l = l;
        super.j = j;
        for (int i = 0; i < super.l; ++i) {
            n = (float)(n + super.j.a[i].a);
        }
        final double n2 = 360.0 / n;
        final NumberFormat instance = NumberFormat.getInstance();
        final DecimalFormat decimalFormat = new DecimalFormat(a("\u0000\u0012.aK"));
        for (int k = 0; k < super.l; ++k) {
            if (super.j.a[k].g == null) {
                if (super.j.a[k].a == 0.0) {
                    super.j.a[k].g = String.valueOf(s2) + instance.format(0L) + s + a("\u0003\u0019") + decimalFormat.format(0L) + a("\u0006\u0018");
                }
                else {
                    super.j.a[k].g = String.valueOf(s2) + instance.format(super.j.a[k].a) + s + a("\u0003\u0019") + decimalFormat.format(super.j.a[k].a * (100.0 / n)) + a("\u0006\u0018");
                }
            }
        }
        super.j.a[0].p = 0;
        super.j.a[0].q = (int)Math.round(super.j.a[0].a * n2);
        for (int n3 = 1; n3 < super.l; ++n3) {
            super.j.a[n3].p = super.j.a[n3 - 1].q;
            super.j.a[n3].q = (int)(Math.round(super.j.a[n3].a * n2) + super.j.a[n3].p);
        }
        super.j.a[super.l - 1].q = 360;
        return true;
    }
    
    public void a(final Graphics graphics) {
        graphics.setColor(this.Y);
        graphics.fillOval(this.R, this.S + this.V - 5, super.a, super.b - this.V + 5);
        for (int i = 0; i < super.l; ++i) {
            if (super.j.a[i].q >= 180) {
                graphics.setColor(super.j.a[i].c.darker());
                for (int j = 0; j < this.V; ++j) {
                    graphics.drawArc(this.R, this.S + j, this.W, this.X, super.j.a[i].p, super.j.a[i].q - super.j.a[i].p);
                }
            }
        }
        for (int k = 0; k < super.l; ++k) {
            graphics.setColor(super.j.a[k].c);
            graphics.fillArc(this.R, this.S, this.W, this.X, super.j.a[k].p, super.j.a[k].q - super.j.a[k].p);
        }
        super.v.a(graphics, this);
    }
    
    public void a(final Graphics graphics, final n n) {
        for (int i = 0; i < super.l; ++i) {
            if (super.j.a[i].q >= 180) {
                if (n == super.j.a[i]) {
                    graphics.setColor(super.w.darker());
                }
                else {
                    graphics.setColor(super.j.a[i].c.darker());
                }
                for (int j = 0; j < this.V; ++j) {
                    graphics.drawArc(this.R, this.S + j, this.W, this.X, super.j.a[i].p, super.j.a[i].q - super.j.a[i].p);
                }
            }
        }
        for (int k = 0; k < super.l; ++k) {
            if (n == super.j.a[k]) {
                graphics.setColor(super.w);
            }
            else {
                graphics.setColor(super.j.a[k].c);
            }
            graphics.fillArc(this.R, this.S, this.W, this.X, super.j.a[k].p, super.j.a[k].q - super.j.a[k].p);
        }
    }
    
    public n a(final int n, final int n2) {
        final double n3 = n - this.T;
        final double n4 = this.U - n2;
        final double n5 = n3 / (this.W / 2);
        final double n6 = n4 / (this.X / 2);
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
                if (n8 >= super.j.a[i].p && n8 <= super.j.a[i].q) {
                    return super.j.a[i];
                }
            }
        }
        return null;
    }
    
    public void a(final Color y) {
        this.Y = y;
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
                    c2 = '#';
                    break;
                }
                case 1: {
                    c2 = '1';
                    break;
                }
                case 2: {
                    c2 = '\r';
                    break;
                }
                case 3: {
                    c2 = 'O';
                    break;
                }
                default: {
                    c2 = 'h';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
