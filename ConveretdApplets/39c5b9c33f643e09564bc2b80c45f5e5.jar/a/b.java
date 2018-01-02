// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;

public class b
{
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public String f;
    public Color g;
    public Color h;
    public n i;
    public o j;
    public m k;
    public int l;
    public int m;
    public double n;
    public double o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public String t;
    public String u;
    public a v;
    public Color w;
    public Color x;
    public boolean y;
    public boolean z;
    public boolean A;
    public boolean B;
    public int C;
    public String D;
    public String E;
    public String F;
    public int G;
    public int H;
    public int I;
    public int J;
    public int K;
    public int L;
    public int M;
    public boolean N;
    public boolean O;
    public boolean P;
    public boolean Q;
    
    public boolean a(final int n, final o o, final String s, final String s2) {
        return true;
    }
    
    public boolean a(final int n, final m m, final String s, final String s2) {
        return true;
    }
    
    public void a(final Graphics graphics) {
    }
    
    public void a(final Graphics graphics, final n n) {
    }
    
    public n a(final int n, final int n2) {
        return null;
    }
    
    public void b(final Graphics graphics) {
    }
    
    public void a(final Color color) {
    }
    
    public void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        final int n6 = this.l / n5;
        final int n7 = this.l % n5;
        int n8 = n6;
        final int n9 = n3 / n5;
        if (n7 > 0) {
            ++n8;
        }
        int n10 = 0;
        graphics.setFont(new Font(this.f, 0, this.c));
        for (int i = 0; i < n5; ++i) {
            for (int j = 0; j < n8; ++j) {
                final int n11 = n + n9 * i;
                final int n12 = n2 + n4 * j;
                if (j < n6 || (j == n8 - 1 && n7 > i)) {
                    if (this.j.a[n10].b != null) {
                        graphics.setColor(this.h);
                        graphics.fillRect(n11, n12, 12, 12);
                        graphics.setColor(this.j.a[n10].c);
                        graphics.fillRect(n11 + 1, n12 + 1, 10, 10);
                        graphics.setColor(this.h);
                        graphics.drawString(this.j.a[n10].b, n11 + 15, n12 + 11);
                        this.j.a[n10].h = n11 + 1;
                        this.j.a[n10].i = n12 + 1;
                    }
                    ++n10;
                }
            }
        }
    }
    
    public void b(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        this.m = this.k.a.size();
        final int n6 = this.m / n5;
        final int n7 = this.m % n5;
        int n8 = n6;
        final int n9 = n3 / n5;
        if (n7 > 0) {
            ++n8;
        }
        int n10 = 0;
        graphics.setFont(new Font(this.f, 0, this.c));
        for (int i = 0; i < n5; ++i) {
            for (int j = 0; j < n8; ++j) {
                if (n10 < this.m) {
                    final int n11 = n + n9 * i;
                    final int n12 = n2 + n4 * j;
                    final o o = this.k.a.elementAt(n10);
                    if (j < n6 || (j == n8 - 1 && n7 > i)) {
                        if (o.b != null) {
                            graphics.setColor(this.h);
                            graphics.fillRect(n11, n12, 12, 12);
                            graphics.setColor(o.c);
                            graphics.fillRect(n11 + 1, n12 + 1, 10, 10);
                            graphics.setColor(this.h);
                            graphics.drawString(o.b, n11 + 15, n12 + 11);
                        }
                        ++n10;
                    }
                }
            }
        }
    }
    
    public b() {
        this.p = false;
        this.s = false;
        this.t = "";
        this.u = "";
        this.y = false;
        this.z = false;
        this.A = false;
        this.B = false;
        this.I = 1;
        this.J = 1;
        this.K = 1;
        this.L = 1;
        this.N = false;
        this.O = false;
        this.P = false;
        this.Q = false;
    }
}
