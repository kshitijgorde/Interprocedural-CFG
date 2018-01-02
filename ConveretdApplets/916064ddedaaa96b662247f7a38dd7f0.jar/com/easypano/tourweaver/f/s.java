// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

import java.awt.Image;

public abstract class s extends r implements q
{
    public static final String j;
    public static final String k;
    public static final String l;
    public static final String m;
    int n;
    Image o;
    Image p;
    Image q;
    h r;
    h s;
    q t;
    double u;
    double v;
    double w;
    double x;
    double y;
    double z;
    
    public s() {
        this.n = -1;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.u = 0.0;
        this.v = 0.0;
        this.w = 0.0;
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }
    
    public void c() {
        this.o = null;
        this.p = null;
        this.q = null;
    }
    
    public static boolean b(final String s) {
        final boolean i = r.i;
        String s2 = s;
        if (!i) {
            if (s == null) {
                return false;
            }
            s2 = s;
        }
        final boolean equals = s2.equals(s.j);
        if (!i) {
            if (!equals) {
                final boolean equals2 = s.equals(s.k);
                if (!i) {
                    if (!equals2) {
                        final boolean equals3 = s.equals(s.l);
                        if (!i) {
                            if (!equals3) {
                                final boolean equals4 = s.equals(s.m);
                                if (!i) {
                                    if (equals4) {}
                                }
                            }
                        }
                    }
                }
            }
        }
        return equals;
    }
    
    public void a(final double u, final double v, final double w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
    
    public void b(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public h j() {
        return this.r;
    }
    
    public h k() {
        return this.s;
    }
    
    public void c(final h r) {
        this.r = r;
    }
    
    public void d(final h s) {
        this.s = s;
    }
    
    protected void c(final d d) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        h s = this;
        Label_0044: {
            s s2 = null;
            Label_0040: {
                if (!i) {
                    if (this.q != null) {
                        return;
                    }
                    s2 = this;
                    if (i) {
                        break Label_0040;
                    }
                    s = this.s;
                }
                if (s instanceof y) {
                    this.e(d);
                    if (!i) {
                        break Label_0044;
                    }
                }
                s2 = this;
            }
            s2.d(d);
        }
        d.k().takeEmptyPicture();
        this.a(this.p, this.q, d.r());
        this.a(d);
    }
    
    protected void d(final d d) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        final Image p = this.p;
        h s = null;
        Label_0050: {
            if (!i) {
                if (p == null) {
                    d.k().takePictureOfSelf();
                    this.p = d.r();
                }
                s = this;
                if (i) {
                    break Label_0050;
                }
                final Image q = this.q;
            }
            if (p != null) {
                return;
            }
            s = this.s;
        }
        d.a(((x)s).n());
        d.k().takeEmptyPicture(d);
        this.q = d.r();
    }
    
    protected void e(final d d) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        final Image p = this.p;
        h s = null;
        Label_0184: {
            if (!i) {
                s s2 = null;
                Label_0170: {
                    Label_0169: {
                        if (p == null) {
                            s = this;
                            s2 = this;
                            if (i) {
                                break Label_0170;
                            }
                            if (this.t != null) {
                                s s3 = this;
                                if (!i) {
                                    if (this.t.b(this.r)) {
                                        this.p = this.t.g();
                                    }
                                    s3 = this;
                                }
                                final h r;
                                Label_0149: {
                                    if (!i) {
                                        if (s3.p == null) {
                                            r = this.r;
                                            if (i) {
                                                break Label_0149;
                                            }
                                            if (r != null) {
                                                final y y = (y)this.r;
                                                d.a(this.u, this.v, this.w);
                                                this.r.a(d);
                                                this.t.a(this.r, d);
                                                this.p = d.r();
                                                if (!i) {
                                                    break Label_0169;
                                                }
                                            }
                                        }
                                        s = this;
                                        s2 = this;
                                        if (i) {
                                            break Label_0170;
                                        }
                                        final h r2 = this.r;
                                    }
                                }
                                if (r == null) {
                                    d.k().takePictureOfSelf();
                                    this.p = d.r();
                                }
                            }
                        }
                    }
                    s = this;
                    s2 = this;
                }
                if (i) {
                    break Label_0184;
                }
                final Image q = s2.q;
            }
            if (p != null) {
                return;
            }
            s = this.s;
        }
        final y y2 = (y)s;
        d.a(this.x, this.y, this.z);
        this.s.a(d);
        this.t.a(this.s, d);
        this.q = d.r();
    }
    
    public abstract void a(final Image p0, final Image p1, final Image p2);
    
    public abstract void a(final int p0);
    
    public void a(final q t) {
        this.t = t;
    }
    
    public Image g() {
        return this.o;
    }
    
    public q h() {
        return this.t;
    }
    
    public boolean b(final h h) {
        return h == this;
    }
    
    public void a(final h h, final d d) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        s s = this;
        Label_0031: {
            if (!i) {
                if (this.q == null) {
                    s = this;
                    if (i) {
                        break Label_0031;
                    }
                    if (this.s == null) {
                        return;
                    }
                }
                s = this;
            }
        }
        s.c(d);
        this.a(d.A());
        d.a(this.o);
    }
    
    static {
        final char[] charArray = "\f;\u001f#C8".toCharArray();
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
                            c2 = 'V';
                            break;
                        }
                        case 1: {
                            c2 = 'T';
                            break;
                        }
                        case 2: {
                            c2 = 'p';
                            break;
                        }
                        case 3: {
                            c2 = 'N';
                            break;
                        }
                        default: {
                            c2 = '\n';
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
        m = new String(charArray).intern();
        final char[] charArray2 = "\u00105\u0014+C8\u0012\u0011*o\u0019!\u0004".toCharArray();
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
                            c4 = 'V';
                            break;
                        }
                        case 1: {
                            c4 = 'T';
                            break;
                        }
                        case 2: {
                            c4 = 'p';
                            break;
                        }
                        case 3: {
                            c4 = 'N';
                            break;
                        }
                        default: {
                            c4 = '\n';
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
        j = new String(charArray2).intern();
        final char[] charArray3 = "\u00015\u001c%^>&\u001f;m>".toCharArray();
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
                            c6 = 'V';
                            break;
                        }
                        case 1: {
                            c6 = 'T';
                            break;
                        }
                        case 2: {
                            c6 = 'p';
                            break;
                        }
                        case 3: {
                            c6 = 'N';
                            break;
                        }
                        default: {
                            c6 = '\n';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n9;
                } while (n7 == 0);
            }
            if (n7 > n9) {
                continue;
            }
            break;
        }
        l = new String(charArray3).intern();
        final char[] charArray4 = "\u00148\u0019 n%".toCharArray();
        int length4;
        int n11;
        final int n10 = n11 = (length4 = charArray4.length);
        int n12 = 0;
        while (true) {
            Label_0442: {
                if (n10 > 1) {
                    break Label_0442;
                }
                length4 = (n11 = n12);
                do {
                    final char c7 = charArray4[n11];
                    char c8 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c8 = 'V';
                            break;
                        }
                        case 1: {
                            c8 = 'T';
                            break;
                        }
                        case 2: {
                            c8 = 'p';
                            break;
                        }
                        case 3: {
                            c8 = 'N';
                            break;
                        }
                        default: {
                            c8 = '\n';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                k = new String(charArray4).intern();
                return;
            }
            continue;
        }
    }
}
