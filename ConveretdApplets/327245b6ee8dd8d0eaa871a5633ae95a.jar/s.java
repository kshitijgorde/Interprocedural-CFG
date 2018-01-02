// 
// Decompiled by Procyon v0.5.30
// 

class s
{
    int a;
    int b;
    float[] c;
    int[] d;
    int[] e;
    int[] f;
    float[] g;
    r h;
    int[] i;
    int[] j;
    int k;
    int l;
    int m;
    int n;
    int p;
    int q;
    int r;
    int s;
    int t;
    int u;
    int v;
    int w;
    int x;
    float y;
    float[] z;
    float[] aa;
    int ab;
    int ac;
    int ad;
    int ae;
    boolean af;
    float ag;
    int ah;
    int ai;
    int aj;
    int ak;
    int al;
    int am;
    int an;
    int ao;
    int ap;
    int aq;
    int ar;
    int as;
    int at;
    int au;
    int av;
    int aw;
    int[] ax;
    int ay;
    
    s() {
        this.a = -1;
        this.b = -1;
        this.z = null;
        this.aa = null;
        this.ay = 0;
    }
    
    void a(final int n, final int n2, final int n3, final int n4, final bg bg) {
    }
    
    boolean a() {
        return true;
    }
    
    boolean b() {
        return false;
    }
    
    void a(final int n) {
    }
    
    void a(final k k) {
        if (k == null) {
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = null;
            if (!c.l) {
                return;
            }
        }
        this.c = k.y;
        this.d = k.ac;
        this.e = k.aa;
        this.f = k.ab;
        this.g = k.ad;
        this.h = k.ae;
    }
    
    void b(final k k) {
        this.i = k.u;
        this.j = k.t;
    }
    
    void a(final bg bg) {
        this.k = bg.cu;
        this.l = bg.cv;
        this.m = bg.cw;
        this.n = bg.cx;
        this.p = bg.cy;
        this.q = bg.cz;
        this.r = bg.c0;
        this.s = bg.c1;
        this.t = bg.c2;
        this.u = bg.s;
        this.v = bg.y;
        this.w = bg.t;
        this.x = bg.z;
        this.y = bg.bc;
        this.z = bg.aj;
        this.aa = bg.ak;
        this.af = bg.b3;
        this.ag = bg.cd;
        this.ab = bg.b1;
        this.ah = bg.bq;
        this.ai = bg.bo;
        this.aj = bg.b4;
        this.ak = bg.b7;
        this.al = bg.b8;
        this.am = bg.b9;
        this.an = bg.b5;
        this.ao = bg.b6;
        this.ap = bg.bp;
        this.aq = bg.v;
        this.ar = bg.w;
        this.as = bg.m;
        this.at = bg.n;
        this.au = bg.bm;
        this.av = bg.bn;
        this.aw = bg.bl;
        this.ax = bg.k;
        final int ae = -1;
        this.ac = ae;
        this.ad = ae;
        this.ae = ae;
    }
    
    void c() {
        ++this.ay;
    }
    
    void d() {
        --this.ay;
        if (this.ay == 0) {}
        this.e();
    }
    
    void e() {
        this.a((k)null);
        this.ax = null;
        this.j = null;
        this.i = null;
    }
    
    final int a(final int n, final int n2, final int n3) {
        int n4 = 65535;
        if (n < 34800) {
            if (this.ac == -1) {
                this.ac = bg.b(this.z[0], this.aa[0]) + bg.cp;
            }
            final int n5 = this.ac + (n + bg.cq >> bg.cn);
            final int n6 = bg.co[n5];
            n4 = bg.co[n5];
        }
        if (n2 < 34800) {
            if (this.ad == -1) {
                this.ad = bg.b(this.z[1], this.aa[1]) + bg.cp;
            }
            final int n7 = this.ad + (n2 + bg.cq >> bg.cn);
            final int n8 = bg.co[n7];
            n4 &= bg.co[n7];
        }
        if (n3 < 34800) {
            if (this.ae == -1) {
                this.ae = bg.b(this.z[2], this.aa[2]) + bg.cp;
            }
            final int n9 = this.ae + (n3 + bg.cq >> bg.cn);
            final int n10 = bg.co[n9];
            n4 &= bg.co[n9];
        }
        return n4;
    }
    
    final int a(final int[] array, final boolean b, final bg bg) {
        final boolean l = c.l;
        if (b) {
            final int n = (bg.x >> this.ap) * this.aq + (bg.w >> this.ap);
            if (n < 0 || n >= this.ab) {
                return array[0];
            }
            final int n2 = array[n];
            if (this.ag < (n2 & 0x7F000000) >>> 24 << this.ap - 16) {
                return n2;
            }
        }
        if (this.af) {
            return this.a(array, bg);
        }
        int n3 = 0;
        int n4 = 0;
        int n5 = bg.w + this.al;
        int n6 = bg.x + this.am;
        final int n7 = this.an - (this.aj << 2);
        final int n8 = this.ao - (this.ak << 2);
        int n9 = 4;
    Label_0199_Outer:
        while (true) {
            Label_0301: {
                if (!l) {
                    break Label_0301;
                }
                final int n11;
                int n10 = n11;
                int n14 = 0;
                while (true) {
                    while (true) {
                        Label_0274: {
                            if (!l) {
                                break Label_0274;
                            }
                            final int n12 = (n6 >>> this.ap) * this.aq + (n5 >>> this.ap);
                            final int n13 = n14;
                            int n15 = 0;
                            Label_0231: {
                                if (n13 < 0 || n13 >= this.ab) {
                                    n15 = array[0];
                                    if (!l) {
                                        break Label_0231;
                                    }
                                }
                                n15 = array[n13];
                            }
                            n3 += (n15 & 0x7F00FF00) >> 4;
                            n4 += (n15 & 0xFF00FF);
                            --n10;
                            n5 += this.aj;
                            n6 += this.ak;
                        }
                        if (n10 > 0) {
                            continue Label_0199_Outer;
                        }
                        break;
                    }
                    --n9;
                    n5 += n7;
                    n14 = n6 + n8;
                    if (l) {
                        continue;
                    }
                    break;
                }
                n6 = n14;
            }
            if (n9 > 0) {
                continue;
            }
            final int n11 = (n4 >> 4 & 0xFF00FF) | (n3 & 0x7F00FF00);
            if (!l) {
                return n11;
            }
            continue;
        }
    }
    
    protected final int a(final int[] array, final bg bg) {
        int n = (bg.x >>> this.ap) * this.aq + (bg.w >>> this.ap);
        if (n < 0 || n >= this.ab) {
            n = 0;
        }
        final int n2 = array[n];
        if ((n2 & Integer.MIN_VALUE) != 0x0) {
            final int n3 = bg.w - this.ah;
            final int n4 = bg.x - this.ah;
            int n5 = (n4 >> this.ap) * this.aq + (n3 >> this.ap);
            if (n5 < 0 || n5 + this.aq + 1 >= this.ab) {
                n5 = 0;
            }
            final int n6 = array[n5];
            final int n7 = array[n5 + 1];
            final int n8 = array[n5 + this.aq];
            final int n9 = array[n5 + this.aq + 1];
            final int n10 = n3 >> this.ai & 0xFF;
            final int n11 = 256 - n10;
            final int n12 = n4 >> this.ai & 0xFF;
            final int n13 = 256 - n12;
            final int n14 = n11 * n13 >> 9;
            final int n15 = n10 * n13 >> 9;
            final int n16 = n11 * n12 >> 9;
            final int n17 = 128 - n14 - n15 - n16;
            return (((n6 & 0x7F00FF00) >>> 7) * n14 + ((n7 & 0x7F00FF00) >>> 7) * n15 + ((n8 & 0x7F00FF00) >>> 7) * n16 + ((n9 & 0x7F00FF00) >>> 7) * n17 & 0x7F00FF00) | ((n6 & 0xFF00FF) * n14 + (n7 & 0xFF00FF) * n15 + (n8 & 0xFF00FF) * n16 + (n9 & 0xFF00FF) * n17 >> 7 & 0xFF00FF);
        }
        return n2;
    }
    
    final int a(final int[] array, final boolean b, int n, final bg bg) {
        final boolean l = c.l;
        if (b) {
            int n2 = bg.w >> this.ap;
            if (n2 < 0) {
                n2 = 0;
            }
            if (n2 >= this.aq) {
                n2 = this.aq - 1;
            }
            int n3 = bg.x >> this.ap;
            if (n3 < 0) {
                n3 = 0;
            }
            if (n3 >= this.ar) {
                n3 = this.ar - 1;
            }
            final int n4 = array[n3 * this.aq + n2];
            int n5 = (n4 & 0x7F000000) >>> 24;
            if (this.ag < --n5 << this.ap - 16) {
                int a = 0;
                Label_0163: {
                    if ((n4 & Integer.MIN_VALUE) == 0x0) {
                        a = n4;
                        if (!l) {
                            break Label_0163;
                        }
                    }
                    a = this.a(array, bg);
                }
                final int n6 = a & 0xFF00FF;
                final int n7 = (a & 0x7F00FF00) >> 8;
                final int e = bg.e(n);
                return (n6 * e >> 4 & 0xFF00FF) | (n7 * e << 4 & 0x7F00FF00);
            }
        }
        if (this.af) {
            final int a2 = this.a(array, bg);
            return ((a2 & 0xFF00FF) * bg.e(n) >> 4 & 0xFF00FF) | (((a2 & 0x7F00FF00) >> 8) * bg.e(n) << 4 & 0x7F00FF00);
        }
        int n8 = 0;
        int n9 = 0;
        int n10 = bg.w + this.al;
        int n11 = bg.x + this.am;
        while (true) {
            Label_0462: {
                if (!l) {
                    break Label_0462;
                }
                final int n13;
                int n12 = n13;
                int n14 = n11;
                int n15 = n & 0xF;
                int n16 = 0;
                while (true) {
                    Label_0430: {
                        if (!l) {
                            break Label_0430;
                        }
                        if (n16 != 0) {
                            final int n17 = (n14 >>> this.ap) * this.aq + (n12 >>> this.ap);
                            int n18 = 0;
                            Label_0383: {
                                if (n17 < 0 || n17 >= this.ab) {
                                    n18 = array[0];
                                    if (!l) {
                                        break Label_0383;
                                    }
                                }
                                n18 = array[n17];
                            }
                            n8 += (n18 & 0x7F00FF00) >> 8;
                            n9 += (n18 & 0xFF00FF);
                        }
                        n12 += this.aj;
                        n14 += this.ak;
                        n15 >>= 1;
                    }
                    if (n15 != 0) {
                        continue;
                    }
                    n >>= 4;
                    n10 += this.an;
                    n16 = n11 + this.ao;
                    if (l) {
                        continue;
                    }
                    break;
                }
                n11 = n16;
            }
            if (n != 0) {
                continue;
            }
            n9 = (n9 >> 4 & 0xFF00FF);
            n8 = (n8 << 4 & 0x7F00FF00);
            final int n13 = n9 | n8;
            if (!l) {
                return n13;
            }
            continue;
        }
    }
    
    final int b(final int[] array, final bg bg) {
        int n = bg.q;
        int n2 = bg.r;
        final int n3 = (this.as << this.au) - this.av - 1;
        final int n4 = (this.at << this.au) - this.av - 1;
        if (n < this.av) {
            n = this.av;
        }
        if (n2 < this.av) {
            n2 = this.av;
        }
        if (n > n3) {
            n = n3;
        }
        if (n2 > n4) {
            n2 = n4;
        }
        final int n5 = array[(n2 >> this.au) * this.as + (n >> this.au)];
        if ((n5 & Integer.MIN_VALUE) != 0x0) {
            final int n6 = n - this.av;
            final int n7 = n2 - this.av;
            final int n8 = (n7 >> this.au) * this.as + (n6 >> this.au);
            final int n9 = array[n8];
            final int n10 = array[n8 + 1];
            final int n11 = array[n8 + this.as];
            final int n12 = array[n8 + this.as + 1];
            final int n13 = n6 >> this.aw & 0xFF;
            final int n14 = 256 - n13;
            final int n15 = n7 >> this.aw & 0xFF;
            final int n16 = 256 - n15;
            final int n17 = n14 * n16 >> 9;
            final int n18 = n13 * n16 >> 9;
            final int n19 = n14 * n15 >> 9;
            final int n20 = 128 - n17 - n18 - n19;
            return ((n9 & 0xFF00) * n17 + (n10 & 0xFF00) * n18 + (n11 & 0xFF00) * n19 + (n12 & 0xFF00) * n20 >> 6 & 0x1FE00) | ((n9 & 0xFF00FF) * n17 + (n10 & 0xFF00FF) * n18 + (n11 & 0xFF00FF) * n19 + (n12 & 0xFF00FF) * n20 >> 6 & 0x1FE01FE);
        }
        return n5 << 1;
    }
}
