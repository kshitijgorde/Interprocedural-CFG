// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

import java.awt.Image;

public class w extends s
{
    double A;
    double B;
    double C;
    double D;
    double E;
    double F;
    double G;
    double H;
    double I;
    double J;
    double K;
    double L;
    double M;
    double N;
    double O;
    int P;
    private static String Q;
    
    public w() {
        this.M = 0.0;
        this.N = 0.0;
        this.O = 0.0;
    }
    
    public boolean a(final h h) {
        return false;
    }
    
    public void a(final Image image, final Image image2, final Image image3) {
    }
    
    public void a(final d d) {
        super.a(d);
        this.A = d.h();
        this.B = d.i();
        this.C = d.j();
        this.F = this.C;
        this.l();
    }
    
    public void a(final double n, final double n2, double c, final double n3, final double n4, final double i, final double j, final double k, final double l) {
        this.D = n3;
        this.E = n4;
        System.out.println(w.Q + n + " " + n2);
        c = this.C;
        this.G = n3;
        this.H = n4;
        this.I = i;
        this.J = j;
        this.K = k;
        this.L = l;
        this.l();
    }
    
    private double a(final double n) {
        return n * n;
    }
    
    private void l() {
        final double sqrt = Math.sqrt(this.a(this.D - this.A) + this.a(this.E - this.B) + this.a(this.F - this.C));
        this.P = (int)(sqrt / (sqrt + Math.sqrt(this.a(this.D - this.G) + this.a(this.E - this.H) + this.a(this.F - this.I))) * 100.0);
    }
    
    public void a(final int n) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        int n2 = n;
        int n3 = n;
        int n5;
        final int n4 = n5 = this.P;
        if (!i) {
            if (n < n4) {
                final double n6 = n / this.P;
                this.M = this.A + (this.D - this.A) * n6;
                this.N = this.B + (this.E - this.B) * n6;
                this.O = this.C + (this.F - this.C) * n6;
                if (!i) {
                    return;
                }
            }
            n2 = n;
            n3 = n;
            final int p;
            n5 = (p = this.P);
        }
        if (!i) {
            if (n3 < n4) {
                return;
            }
            n2 = n;
            n5 = this.P;
        }
        final double n7 = (n2 - n5) / (100 - this.P);
        this.M = this.D + (this.G - this.D) * n7;
        this.N = this.E + (this.H - this.E) * n7;
        this.O = this.F + (this.I - this.F) * n7;
    }
    
    public void a(final h h, final d d) {
        if (super.r == null) {
            return;
        }
        this.a(d.A());
        d.a(this.M, this.N, this.O);
        super.t.a(super.r, d);
        d.q();
        d.a(super.t.g());
        d.a(this.J, this.K, this.L);
    }
    
    static {
        final char[] charArray = "MP#\u0016oRC \b|R\u0011".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0094: {
                if (n > 1) {
                    break Label_0094;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = ':';
                            break;
                        }
                        case 1: {
                            c2 = '1';
                            break;
                        }
                        case 2: {
                            c2 = 'O';
                            break;
                        }
                        case 3: {
                            c2 = '}';
                            break;
                        }
                        default: {
                            c2 = '\u001b';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                w.Q = new String(charArray).intern();
                return;
            }
            continue;
        }
    }
}
