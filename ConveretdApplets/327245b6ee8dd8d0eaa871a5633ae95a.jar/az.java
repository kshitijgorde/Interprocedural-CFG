// 
// Decompiled by Procyon v0.5.30
// 

class az extends ad
{
    a0 a;
    boolean b;
    int c;
    String d;
    String e;
    int f;
    int g;
    ao h;
    int i;
    int j;
    int k;
    int l;
    int m;
    boolean n;
    boolean p;
    boolean q;
    boolean r;
    boolean s;
    boolean t;
    int u;
    boolean v;
    boolean w;
    int x;
    a0 y;
    ai z;
    
    az() {
        super(3);
        this.d = "";
        this.e = "";
        this.g = -65281;
        this.n = false;
        this.p = false;
        this.q = false;
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = 0;
        this.v = false;
        this.w = false;
        super.a = 7;
        this.b = false;
        super.g = true;
        super.i = new ai[2];
        super.p = new ai[2];
        super.q = new int[2];
        this.a = new a0();
    }
    
    int a(final x x) {
        super.d = x.e();
        super.c = x.k();
        final int e = x.e();
        final boolean b = (e & 0x1) != 0x0;
        final boolean b2 = (e & 0x2) != 0x0;
        final boolean b3 = (e & 0x4) != 0x0;
        final boolean b4 = (e & 0x8) != 0x0;
        this.v = ((e & 0x10) != 0x0);
        this.r = ((e & 0x20) != 0x0);
        this.b = ((e & 0x40) != 0x0);
        final boolean b5 = (e & 0x80) != 0x0;
        this.w = ((e & 0x200) != 0x0);
        this.t = ((e & 0x800) != 0x0);
        this.s = ((e & 0x1000) != 0x0);
        final boolean b6 = (e & 0x2000) != 0x0;
        if ((e & 0x4000) != 0x0) {
            this.c = 1;
        }
        this.x = (b4 ? 0 : 1);
        if (b) {
            this.a.g = (ao)x.b.a(x.e());
            this.a.l = x.e();
        }
        if (b3) {
            this.a.e = x.c(3);
        }
        if (b2) {
            this.u = x.e();
        }
        Label_0412: {
            if (b6) {
                this.a.a = x.d();
                this.a.j = x.f();
                this.a.k = x.f();
                x.f();
                this.a.i = x.f();
                if (!c.l) {
                    break Label_0412;
                }
            }
            if (b) {
                this.a.i = this.a.g.d * this.a.l / this.a.g.k;
            }
        }
        this.d = x.m();
        this.e = "";
        if (b5) {
            this.e = x.m();
        }
        return super.d;
    }
    
    void a(final b b, final af af, final int n) {
        b.a(this, af.e, af.d, af, n);
    }
    
    void a(final ax ax, final int n, final int n2) {
        final boolean l = c.l;
        final ac a = ac.a(new ac(), ax.j);
        final f ab = ax.ab;
        if (ax.ai || ax.ak) {
            ai ai = null;
            if (ax.ai) {
                ai = new ai(ax.aj);
                ax.aa.addElement(ai);
                if (ax.n != null) {
                    ax.n.a(ai);
                }
            }
            ai ai2 = null;
            if (ax.ak) {
                ai2 = new ai(ax.al);
                ax.aa.addElement(ai2);
                if (ax.n != null) {
                    ax.n.a(ai2);
                }
            }
            super.aq = null;
            this.a(ax, a);
            super.i[1] = ai;
            super.p[1] = ai2;
            super.q[1] = 1;
            super.r = 1;
            this.a(ai, null);
            final int d = ab.d;
            final int b = ab.b;
            final int a2 = ab.a;
            final int c = ab.c;
            this.c(a2, b);
            this.e(a2, d);
            this.e(c, d);
            this.e(c, b);
            this.e(a2, b);
            this.d();
        }
        if (ax.m == 0) {
            return;
        }
        final int size = ax.v.size();
        final int av = ax.av();
        final String j = ax.j;
        final a0[] n3 = ax.n;
        int n4 = ax.b << 16;
        final boolean an = ax.an;
        final ac ac = new ac();
        ai ai3 = null;
        ai ai4 = null;
        if (n != -1 && n2 != -1) {
            ai4 = new ai(-16777216);
            ax.aa.addElement(ai4);
            ai3 = new ai(-1);
            ai3.i = true;
            ax.aa.addElement(ai3);
        }
        this.a(ax.n[0], ax, n4++);
        try {
            if (this.h == null) {
                return;
            }
            final ac ac2 = ac;
            final ac ac3 = ac;
            final int n5 = (this.j << 16) / this.h.k;
            ac3.d = n5;
            ac2.a = n5;
            final int ae = ax.ae;
            final int[] array = { 0 };
            final int a3 = ax.a(ax.ad, array);
            int n6 = ab.b + a3 + 0 + 40;
            if (array[0] < 360) {
                n6 = (n6 + 10) / 20 * 20;
            }
            int f = n6 - ax.h(ax.ad);
            int ad = ax.ad;
            while (true) {
            Label_1363:
                while (true) {
                    Label_1356: {
                        if (!l) {
                            break Label_1356;
                        }
                        f += ax.h(ad);
                        final int n7 = (ad < size - 1) ? ax.v.elementAt(ad + 1) : ax.j.length();
                        int intValue = ax.v.elementAt(ad);
                        if (intValue < ax.m) {
                            final a0 a4 = n3[intValue];
                            if (a4 != this.y) {
                                this.a(a4, ax, n4++);
                            }
                        }
                        this.f = this.y.a;
                        if (this.h.e != 0 && this.h.h != null) {
                            ac.e = this.a(ax, ad);
                            boolean b2 = this.q;
                            int n8 = 0;
                            final ac ac4 = new ac();
                            ac.f = f;
                            int e = -1;
                            int n9 = -1;
                            int n12 = 0;
                            int n13 = 0;
                        Label_1086_Outer:
                            while (true) {
                                while (true) {
                                    Label_1197: {
                                        if (intValue >= n7) {
                                            break Label_1197;
                                        }
                                        final ac ac5 = ac;
                                        final ac ac6 = ac;
                                        final int n10 = (this.j << 16) / this.h.k;
                                        ac6.d = n10;
                                        ac5.a = n10;
                                        if (this.h.e != 0 && this.h.h != null) {
                                            int char1 = j.charAt(intValue);
                                            if (an) {
                                                char1 = 42;
                                            }
                                            if (char1 >= this.h.g.length) {
                                                char1 = 32;
                                            }
                                            if (char1 == 10) {
                                                char1 = 32;
                                            }
                                            final int n11 = this.h.g[char1];
                                            if (n11 != -1) {
                                                final int e2 = ac.e + this.h.h[n11] * this.j / this.h.k;
                                                if (e2 > ab.c) {
                                                    break Label_1197;
                                                }
                                                if (ac.e > ab.a + this.k + this.m) {
                                                    Label_0944: {
                                                        if (intValue >= n && intValue < n2) {
                                                            if (e == -1) {
                                                                e = ac.e;
                                                            }
                                                            super.i[1] = ai3;
                                                            if (!l) {
                                                                break Label_0944;
                                                            }
                                                        }
                                                        if (n9 == -1 && e != -1) {
                                                            n9 = ac.e;
                                                        }
                                                        super.i[1] = this.z;
                                                    }
                                                    super.aq = this.h.a(n11, this);
                                                    final ac a5 = ac.a(ac, a);
                                                    this.a(ax, a5);
                                                    this.d();
                                                    if (this.q) {
                                                        if (n8 == 0) {
                                                            ac4.a = a5.a;
                                                            ac4.b = a5.b;
                                                            ac4.c = a5.c;
                                                            ac4.d = a5.d;
                                                            ac4.e = a5.e;
                                                            ac4.f = a5.f;
                                                        }
                                                        n8 += this.h.h[n11];
                                                    }
                                                }
                                                ac.e = e2;
                                            }
                                        }
                                        b2 = this.q;
                                        final int m = ax.m;
                                        Label_1129: {
                                            if (n12 < n13) {
                                                final a0 a6 = n3[intValue + 1];
                                                if (a6 == this.y) {
                                                    break Label_1129;
                                                }
                                                this.a(a6, ax, n4++);
                                                if (!l) {
                                                    break Label_1129;
                                                }
                                            }
                                            this.q = false;
                                        }
                                        if (b2 && !this.q) {
                                            super.aq = null;
                                            this.d(1, this.y.e);
                                            this.c(0, 120);
                                            this.e(n8, 120);
                                            this.a(ax, ac4);
                                            this.d();
                                            n8 = 0;
                                        }
                                        ++intValue;
                                        continue Label_1086_Outer;
                                    }
                                    n12 = e;
                                    n13 = -1;
                                    if (!l) {
                                        break;
                                    }
                                    continue;
                                }
                            }
                            if (n12 != n13) {
                                if (n9 == -1) {
                                    n9 = ac.e;
                                }
                                super.aq = null;
                                this.a(ax, a);
                                this.a(super.i[1] = ai4, null);
                                int n14 = ac.f + ax.b(ad, array);
                                final int n15 = ac.f - a3;
                                if (n2 > n7) {
                                    n14 += this.i;
                                }
                                this.c(e, n15);
                                this.e(e, n14);
                                this.e(n9, n14);
                                this.e(n9, n15);
                                this.e(e, n15);
                                this.d();
                            }
                            if (f > ab.d && this.c == 0) {
                                break Label_1363;
                            }
                        }
                        ++ad;
                    }
                    if (ad < av) {
                        continue;
                    }
                    break;
                }
                if (l) {
                    continue;
                }
                break;
            }
        }
        catch (Exception ex) {}
    }
    
    int a(final ax ax, final int n) {
        final boolean l = c.l;
        int n2 = 0;
        if (this.h.e != 0 && this.h.h != null) {
            final int n3 = (n < ax.w.size()) ? ax.w.elementAt(n) : 0;
            final f ab = ax.ab;
            Label_0160: {
                if (this.f == 0) {
                    n2 = ab.a + this.k + this.m + 40;
                    if (!l) {
                        break Label_0160;
                    }
                }
                if (this.f == 1) {
                    n2 = ab.c - this.l - 40 - n3;
                    if (!l) {
                        break Label_0160;
                    }
                }
                if (this.f == 2) {
                    n2 = ab.c - (ab.c - ab.a + n3 >> 1);
                }
            }
            if (n2 < ab.a + this.k + 40 + this.m) {
                n2 = ab.a + this.k + 40 + this.m;
            }
            n2 -= ax.ae;
        }
        return n2;
    }
    
    int a(final ax ax, final int n, final Integer[] array) {
        final boolean l = c.l;
        final String j = ax.j;
        final boolean an = ax.an;
        final int length = j.length();
        int i = n;
        int n2 = 0;
        int n3 = 0;
        Label_0731: {
            if (!this.b) {
                while (i < length) {
                    final a0 a0 = ax.n[i];
                    if (a0 != this.y) {
                        this.a(a0, ax, -1);
                    }
                    int char1 = j.charAt(i);
                    ++i;
                    if (char1 == 10) {
                        break;
                    }
                    if (char1 == 13) {
                        break;
                    }
                    if (an) {
                        char1 = 42;
                    }
                    if (this.h != null && this.h.g != null && this.h.g.length != 0) {
                        int n4 = 0;
                        Label_0205: {
                            if (char1 >= this.h.g.length) {
                                char1 = 32;
                                if (char1 >= this.h.g.length) {
                                    n4 = -1;
                                    if (!l) {
                                        break Label_0205;
                                    }
                                }
                                n4 = this.h.g[char1];
                                if (!l) {
                                    break Label_0205;
                                }
                            }
                            n4 = this.h.g[char1];
                        }
                        n2 = ((n4 > -1 && this.h.h != null) ? (this.h.h[n4] * this.j / this.h.k) : 0);
                    }
                    n3 += n2;
                }
            }
            else {
                final f ab = ax.ab;
                final int n5 = ab.c - this.l - 40 - (ab.a + this.k + this.m + 40);
                int n6 = n;
                int n7 = 0;
            Label_0708:
                while (true) {
                    Label_0701: {
                        if (!l) {
                            break Label_0701;
                        }
                        final a0 a2 = ax.n[i];
                        if (a2 != this.y) {
                            this.a(a2, ax, -1);
                        }
                        final char char2 = j.charAt(i);
                        Label_0698: {
                            if (char2 == '\n' || char2 == '\r') {
                                if (i + 1 < length) {
                                    final char char3 = j.charAt(i + 1);
                                    if ((char2 == '\n' && char3 == '\r') || (char2 == '\r' && char3 == '\n')) {
                                        ++i;
                                    }
                                }
                                n3 += n7;
                                n7 = 0;
                                n6 = i + 1;
                                if (i + 1 > n) {
                                    break Label_0708;
                                }
                                n3 = 0;
                                if (!l) {
                                    break Label_0698;
                                }
                            }
                            int n8 = char2;
                            if (an) {
                                n8 = 42;
                            }
                            if (this.h != null && this.h.g != null && this.h.g.length != 0) {
                                if (n8 >= this.h.g.length) {
                                    n8 = 32;
                                }
                                final int n9 = this.h.g[n8];
                                if (this.h.h != null) {
                                    n2 = ((n9 > -1) ? (this.h.h[n9] * this.j / this.h.k) : 0);
                                }
                            }
                            if (Character.isSpaceChar(char2)) {
                                n3 += n7;
                                n7 = 0;
                                n6 = i + 1;
                                if (n3 > n5 - 1) {
                                    if (n3 != 0) {
                                        break Label_0708;
                                    }
                                    n3 = n7 - n2;
                                    n6 = i;
                                    if (!l) {
                                        break Label_0708;
                                    }
                                }
                                n3 += n2;
                                if (!l) {
                                    break Label_0698;
                                }
                            }
                            n7 += n2;
                            if (n3 + n7 > n5) {
                                if (n3 != 0) {
                                    break Label_0708;
                                }
                                n3 = n7 - n2;
                                n6 = i;
                                if (!l) {
                                    break Label_0708;
                                }
                            }
                            if (char2 == '-') {
                                n3 += n7;
                                n7 = 0;
                                n6 = i + 1;
                            }
                        }
                        ++i;
                    }
                    if (i < length) {
                        continue;
                    }
                    break;
                }
                if (i < length) {
                    i = n6;
                    if (!l) {
                        break Label_0731;
                    }
                }
                n3 += n7;
            }
        }
        array[0] = new Integer(n3);
        return Math.min(i, length);
    }
    
    void a(final a0 y, final ax ax, final int n) {
        this.g = y.e;
        this.j = y.l;
        this.h = y.g;
        this.i = y.i;
        this.k = y.j;
        this.l = y.k;
        this.m = y.c;
        this.n = y.d;
        this.p = y.h;
        this.q = y.n;
        if (n != -1) {
            this.z = new ai(this.g);
            this.z.i = true;
            ax.aa.addElement(this.z);
            if (ax.n != null) {
                ax.n.a(this.z);
            }
        }
        this.y = y;
    }
    
    void a(final ax ax) {
        this.a(ax.p, ax, -1);
        if (this.h.e != 0 && this.h.h != null) {
            final int ad = ax.ad;
            final int ba = ax.ba();
            final ac a = ac.a(new ac(), ax.j);
            int f = (ba - ad) * ((this.j * (this.h.c + this.h.b) >> 10) + this.i);
            if (this.j < 360) {
                f = (f + 10) / 20 * 20;
            }
            this.f = this.y.a;
            final ac ac3;
            final ac ac2;
            final ac ac = ac2 = (ac3 = new ac());
            final int n = this.j << 6;
            ac2.d = n;
            ac3.a = n;
            ac.e = this.a(ax, ba);
            ac.f = f;
            final f ab = ax.ab;
            if (ac.e > ab.a + this.k + this.m) {
                int n2 = 0;
                if (ba < ax.v.size()) {
                    final boolean an = ax.an;
                    int intValue = ax.v.elementAt(ba);
                    while (true) {
                        Label_0351: {
                            if (!c.l) {
                                break Label_0351;
                            }
                            int char1 = ax.j.charAt(intValue);
                            if (an) {
                                char1 = 42;
                            }
                            if (char1 >= this.h.g.length) {
                                char1 = 32;
                            }
                            final int n3 = this.h.g[char1];
                            n2 += ((n3 > -1 && this.h.h != null) ? this.h.h[n3] : 0);
                            ++intValue;
                        }
                        if (intValue < ax.a.as.e) {
                            continue;
                        }
                        break;
                    }
                }
                if (n2 > (ab.c - this.l - 40) * this.h.k / this.j) {
                    n2 = (ab.c - this.l - 40) * this.h.k / this.j;
                }
                super.aq = null;
                this.d(1, this.y.e);
                this.c(n2, 0);
                this.e(n2, this.h.b + this.h.c);
                this.a(ax, ac.a(ac, a));
                this.d();
            }
        }
    }
}
