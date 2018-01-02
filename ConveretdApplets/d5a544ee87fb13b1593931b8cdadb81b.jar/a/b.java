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
    public o i;
    public p j;
    public n k;
    public int l;
    public int m;
    public double n;
    public double o;
    public double p;
    public double q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public boolean y;
    public boolean z;
    public String A;
    public String B;
    public a C;
    public Color D;
    public Color E;
    public boolean F;
    public boolean G;
    public int H;
    public boolean I;
    public q[] J;
    public boolean K;
    public boolean L;
    public int M;
    public String N;
    public String O;
    public String P;
    public int Q;
    public int R;
    public boolean S;
    public int T;
    public int U;
    public int V;
    public int W;
    public int X;
    public int Y;
    public boolean Z;
    public boolean ba;
    public boolean bb;
    public boolean bc;
    public boolean bd;
    public boolean be;
    public boolean bf;
    public String bg;
    public String bh;
    public boolean bi;
    public String bj;
    public int bk;
    
    public boolean a(final int n, final p p4, final String s, final String s2) {
        return true;
    }
    
    public boolean a(final int n, final n n2, final String s, final String s2) {
        return true;
    }
    
    public void a(final Graphics graphics) {
    }
    
    public void a(final Graphics graphics, final o o) {
    }
    
    public o a(final int n, final int n2) {
        return null;
    }
    
    public void b(final Graphics graphics) {
    }
    
    public void a(final Color color) {
    }
    
    public void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        final int n6 = this.bk / n5;
        final int n7 = this.bk % n5;
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
                    if (this.j.a[n10].f != null) {
                        graphics.setColor(this.h);
                        graphics.fillRect(n11, n12, 12, 12);
                        graphics.setColor(this.j.a[n10].g);
                        graphics.fillRect(n11 + 1, n12 + 1, 10, 10);
                        graphics.setColor(this.h);
                        graphics.drawString(this.j.a[n10].f, n11 + 15, n12 + 11);
                        this.j.a[n10].l = n11 + 1;
                        this.j.a[n10].m = n12 + 1;
                    }
                    ++n10;
                }
            }
        }
    }
    
    public void b(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        this.m = this.k.a.size();
        final int n6 = this.bk / n5;
        final int n7 = this.bk % n5;
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
                    final p p6 = this.k.a.elementAt(n10);
                    if (j < n6 || (j == n8 - 1 && n7 > i)) {
                        if (p6.b != null) {
                            graphics.setColor(this.h);
                            graphics.fillRect(n11, n12, 12, 12);
                            graphics.setColor(p6.c);
                            graphics.fillRect(n11 + 1, n12 + 1, 10, 10);
                            graphics.setColor(this.h);
                            graphics.drawString(p6.b, n11 + 15, n12 + 11);
                        }
                        ++n10;
                    }
                }
            }
        }
    }
    
    public b() {
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = false;
        this.v = false;
        this.w = false;
        this.z = false;
        this.A = "";
        this.B = "";
        this.F = false;
        this.G = false;
        this.I = false;
        this.K = false;
        this.L = false;
        this.S = false;
        this.T = 1;
        this.U = 1;
        this.V = 1;
        this.W = 1;
        this.X = 1;
        this.Z = false;
        this.ba = false;
        this.bb = false;
        this.bc = false;
        this.bd = false;
        this.be = false;
        this.bf = false;
        this.bg = a("\u001f8\u0017+\u0002\u0017)");
        this.bh = a("\u0017\u0014?\u000f");
        this.bi = false;
        this.bj = a(":\u0011=");
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
                    c2 = '[';
                    break;
                }
                case 1: {
                    c2 = '}';
                    break;
                }
                case 2: {
                    c2 = 'Q';
                    break;
                }
                case 3: {
                    c2 = 'j';
                    break;
                }
                default: {
                    c2 = 'W';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
