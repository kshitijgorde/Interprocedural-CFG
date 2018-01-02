// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

import com.easypano.tourweaver.a.e;

public class j extends i
{
    m q;
    double r;
    double s;
    double t;
    double u;
    double v;
    double w;
    y x;
    y y;
    int z;
    int A;
    int B;
    String C;
    private static String D;
    
    public j() {
        this.r = 0.0;
        this.s = 0.0;
        this.t = 0.0;
        this.u = 0.0;
        this.v = 0.0;
        this.w = 0.0;
        this.z = 6;
        this.A = 0;
        this.B = 0;
        this.C = j.D;
    }
    
    public void a(final m q) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        this.q = q;
        this.C = q.v;
        this.r = q.q();
        this.s = q.r();
        this.t = q.s();
        this.x = (y)q.m();
        a j = this;
        if (!i) {
            if (!this.x.j()) {
                this.r += 180.0;
                this.r = com.easypano.tourweaver.a.e.a(this.r);
                this.s = com.easypano.tourweaver.a.e.a(this.s);
                this.t = com.easypano.tourweaver.a.e.a(this.t);
            }
            j = q.j();
        }
        final a a = j;
        a a3;
        final a a2 = a3 = a;
        if (!i) {
            if (a2 == null) {
                return;
            }
            a3 = a;
        }
        final m m = (m)a3;
        this.u = m.q();
        this.v = m.r();
        this.w = m.s();
        this.y = (y)m.m();
        j k = this;
        if (!i) {
            if (!this.y.j()) {
                this.u += 180.0;
                this.u = com.easypano.tourweaver.a.e.a(this.u);
                this.v = com.easypano.tourweaver.a.e.a(this.v);
                this.w = com.easypano.tourweaver.a.e.a(this.w);
            }
            k = this;
        }
        k.a(m.o() - q.o());
    }
    
    public void a(final double r, final double s, final double t) {
        this.r = r;
        this.s = s;
        this.t = t;
    }
    
    public void b(final double u, final double v, final double w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
    
    static {
        final char[] charArray = "/Ej<".toCharArray();
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
                            c2 = 'n';
                            break;
                        }
                        case 1: {
                            c2 = '0';
                            break;
                        }
                        case 2: {
                            c2 = '\u001e';
                            break;
                        }
                        case 3: {
                            c2 = 'S';
                            break;
                        }
                        default: {
                            c2 = '^';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                j.D = new String(charArray).intern();
                return;
            }
            continue;
        }
    }
}
