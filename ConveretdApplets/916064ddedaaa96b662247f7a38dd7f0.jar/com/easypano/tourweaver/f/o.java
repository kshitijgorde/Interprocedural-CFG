// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

import com.easypano.tourweaver.a.e;

public class o extends i
{
    public static final String q;
    public static final String r;
    public static final String s;
    double t;
    double u;
    double v;
    double w;
    double x;
    double y;
    int z;
    String A;
    int B;
    boolean C;
    double D;
    double E;
    double F;
    
    public o() {
        this.t = 0.0;
        this.u = 0.0;
        this.v = 0.0;
        this.w = 0.0;
        this.x = 0.0;
        this.y = 0.0;
        this.z = 6;
        this.B = 2;
        this.C = false;
        this.D = 0.0;
        this.E = 0.0;
        this.F = 0.0;
    }
    
    public void a() {
        final boolean i = com.easypano.tourweaver.f.r.i;
        o o = this;
        if (!i) {
            if (super.m == null) {
                return;
            }
            o = this;
        }
        final h l = o.l;
        if (!i) {
            if (l == null) {
                return;
            }
            super.l.a(super.m);
            final h j = super.l;
        }
        final y y = (y)l;
        this.a(super.l, 1);
        super.m.k().setEnabled(true);
    }
    
    public boolean d() {
        return super.k;
    }
    
    public void a(final double n, final double n2, final double n3) {
        final d m = super.m;
        if (!com.easypano.tourweaver.f.r.i) {
            if (m == null) {
                return;
            }
            final d i = super.m;
        }
        m.a(n, n2, n3);
    }
    
    public double q() {
        return super.m.h();
    }
    
    public double r() {
        return super.m.i();
    }
    
    public double s() {
        return super.m.j();
    }
    
    public void a(final int b) {
        this.B = b;
    }
    
    public void b(final double t, final double v, final double u) {
        this.t = t;
        this.v = v;
        this.u = u;
        this.C = true;
        this.w = t / this.z;
        this.x = v / this.z;
        this.y = u / this.z;
    }
    
    public void f() {
        final boolean i = com.easypano.tourweaver.f.r.i;
        o o = this;
        o o2 = this;
        if (!i) {
            if (super.k) {
                this.w();
            }
            o = this;
            o2 = this;
        }
        final double n;
        final double n2;
        Label_0073: {
            Label_0069: {
                if (!i) {
                    if (o2.m != null) {
                        final double c;
                        n = (c = dcmpl(this.t, 0.0));
                        if (i) {
                            break Label_0073;
                        }
                        if (n != 0) {
                            break Label_0069;
                        }
                    }
                    o = this;
                }
                n2 = dcmpl(o.v, 0.0);
                if (i) {
                    break Label_0073;
                }
                if (n2 == 0) {
                    final double n3 = dcmpl(this.u, 0.0);
                    if (i) {
                        break Label_0073;
                    }
                    if (n3 == 0) {
                        return;
                    }
                }
            }
            final int b = this.B;
        }
        o o3 = null;
        Label_0304: {
            if (!i) {
                Label_0119: {
                    switch (n) {
                        case 0:
                        case 1: {
                            super.m.a(this.B);
                            if (i) {
                                break Label_0119;
                            }
                            break;
                        }
                        case 2: {
                            final double preFPS = super.m.k().getPreFPS();
                            Label_0156: {
                                if (!i) {
                                    if (preFPS < 15.0) {
                                        break Label_0156;
                                    }
                                    super.m.a(1);
                                }
                                if (!i) {
                                    break;
                                }
                            }
                            super.m.a(0);
                            break;
                        }
                    }
                }
                o3 = this;
                if (i) {
                    break Label_0304;
                }
                final double c = this.C ? 1 : 0;
            }
            if (n2 == 0) {
                o3 = this;
                if (i) {
                    break Label_0304;
                }
                if (this.D == super.m.h()) {
                    o3 = this;
                    if (i) {
                        break Label_0304;
                    }
                    if (this.E == super.m.i()) {
                        o3 = this;
                        if (i) {
                            break Label_0304;
                        }
                        if (this.F == super.m.j()) {
                            this.t = 0.0;
                            this.v = 0.0;
                            this.u = 0.0;
                            this.c();
                        }
                    }
                }
            }
            this.D = super.m.h();
            this.E = super.m.i();
            this.F = super.m.j();
            super.m.b(this.t, this.v, this.u);
            o3 = this;
        }
        o3.C = false;
    }
    
    public double t() {
        return this.D;
    }
    
    public double u() {
        return this.E;
    }
    
    public double v() {
        return this.F;
    }
    
    private void w() {
        final boolean i = com.easypano.tourweaver.f.r.i;
        boolean b2;
        int b;
        int n4;
        int n3;
        final int n2;
        final int n = n2 = (n3 = (n4 = (b = ((b2 = dcmpl(this.t, 0.0)) ? 1 : 0))));
        final int n5;
        final int n6;
        final boolean b3;
        Label_0051: {
            if (!i) {
                if (n == 0) {
                    n5 = (n3 = (n4 = (b = ((b2 = dcmpl(this.v, 0.0)) ? 1 : 0))));
                    if (i) {
                        break Label_0051;
                    }
                    if (n5 == 0) {
                        n6 = (n4 = (b = ((b2 = dcmpl(this.u, 0.0)) ? 1 : 0)));
                        if (i) {
                            break Label_0051;
                        }
                        if (n6 == 0) {
                            return;
                        }
                    }
                }
                b3 = (b2 = com.easypano.tourweaver.a.e.b(this.t));
            }
        }
        if (!i) {
            if (n == 0) {
                this.t -= this.w;
            }
            b2 = com.easypano.tourweaver.a.e.b(this.v);
        }
        if (!i) {
            if (n5 == 0) {
                this.v -= this.x;
            }
            b2 = com.easypano.tourweaver.a.e.b(this.u);
        }
        if (!i) {
            if (n6 == 0) {
                this.u -= this.y;
            }
            b2 = com.easypano.tourweaver.a.e.b(this.t);
        }
        if (!i) {
            if (!b3) {
                return;
            }
            b2 = ((b = (com.easypano.tourweaver.a.e.b(this.v) ? 1 : 0)) != 0);
        }
        o o = null;
        Label_0183: {
            if (!i) {
                if (!b3) {
                    return;
                }
                o = this;
                if (i) {
                    break Label_0183;
                }
                b2 = com.easypano.tourweaver.a.e.b(this.u);
            }
            if (!b2) {
                return;
            }
            this.t = 0.0;
            this.v = 0.0;
            this.u = 0.0;
            o = this;
        }
        o.m.a(1);
    }
    
    static {
        final char[] charArray = "\u001c\u00128%\u007f\b\u0017$#".toCharArray();
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
                            c2 = '\u007f';
                            break;
                        }
                        case 1: {
                            c2 = '~';
                            break;
                        }
                        case 2: {
                            c2 = 'W';
                            break;
                        }
                        case 3: {
                            c2 = 'F';
                            break;
                        }
                        default: {
                            c2 = '\u0014';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n > n3) {
                continue;
            }
            break;
        }
        r = new String(charArray).intern();
        final char[] charArray2 = "\u0011\u00119#".toCharArray();
        int length2;
        int n5;
        final int n4 = n5 = (length2 = charArray2.length);
        int n6 = 0;
        while (true) {
            Label_0210: {
                if (n4 > 1) {
                    break Label_0210;
                }
                length2 = (n5 = n6);
                do {
                    final char c3 = charArray2[n5];
                    char c4 = '\0';
                    switch (n6 % 5) {
                        case 0: {
                            c4 = '\u007f';
                            break;
                        }
                        case 1: {
                            c4 = '~';
                            break;
                        }
                        case 2: {
                            c4 = 'W';
                            break;
                        }
                        case 3: {
                            c4 = 'F';
                            break;
                        }
                        default: {
                            c4 = '\u0014';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n6;
                } while (n4 == 0);
            }
            if (n4 > n6) {
                continue;
            }
            break;
        }
        q = new String(charArray2).intern();
        final char[] charArray3 = "\u001e\u0010#/w\u0013\u00114-c\u0016\r2".toCharArray();
        int length3;
        int n8;
        final int n7 = n8 = (length3 = charArray3.length);
        int n9 = 0;
        while (true) {
            Label_0326: {
                if (n7 > 1) {
                    break Label_0326;
                }
                length3 = (n8 = n9);
                do {
                    final char c5 = charArray3[n8];
                    char c6 = '\0';
                    switch (n9 % 5) {
                        case 0: {
                            c6 = '\u007f';
                            break;
                        }
                        case 1: {
                            c6 = '~';
                            break;
                        }
                        case 2: {
                            c6 = 'W';
                            break;
                        }
                        case 3: {
                            c6 = 'F';
                            break;
                        }
                        default: {
                            c6 = '\u0014';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n9;
                } while (n7 == 0);
            }
            if (n7 <= n9) {
                s = new String(charArray3).intern();
                return;
            }
            continue;
        }
    }
}
