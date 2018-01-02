// 
// Decompiled by Procyon v0.5.30
// 

class bf extends s
{
    int a;
    int b;
    int c;
    
    bf() {
        this.a = 16711680;
        this.b = 0;
        this.c = 0;
    }
    
    void a(final int n) {
        this.a = (n & 0xFF0000);
        this.b = (n & 0xFF00);
        this.c = (n & 0xFF);
    }
    
    boolean a() {
        return false;
    }
    
    boolean b() {
        return true;
    }
    
    final void a(final int n, final int n2, final int n3, final int n4, final bg bg) {
        final boolean l = c.l;
        final int n5 = n2 + n;
        int n6 = super.k * n3 + super.n * n4 + super.r;
        int n7 = super.l * n3 + super.p * n4 + super.s;
        int n8 = super.m * n3 + super.q * n4 + super.t;
        int n9 = n;
        while (true) {
            Label_0718: {
                if (!l) {
                    break Label_0718;
                }
                Label_0646: {
                    if (bg.p >= super.c[n9]) {
                        final int n10 = 1 << (n9 & 0x1F);
                        final int n11 = n9 >> 5;
                        Label_0471: {
                            if ((super.d[n11] & n10) != 0x0 && bg.p > super.g[n9 << 1] && bg.p < super.g[(n9 << 1) + 1]) {
                                if (n6 < -34800 || n7 < -34800) {
                                    break Label_0646;
                                }
                                if (n8 < -34800) {
                                    break Label_0646;
                                }
                                final int a = this.a(n6, n7, n8);
                                if (a == 0) {
                                    break Label_0646;
                                }
                                if (a == 65535) {
                                    break Label_0471;
                                }
                                final int e = bg.e(a);
                                final int b = this.b(super.ax, bg);
                                int n12 = (this.a + ((b & 0x1FE0000) - 16777216)) * e >> 4;
                                int n13 = (this.b + ((b & 0x1FE00) - 65536)) * e >> 4;
                                int n14 = (this.c + ((b & 0x1FE) - 256)) * e >> 4;
                                final int n15 = e << 4;
                                Label_0337: {
                                    if (n12 < 0) {
                                        n12 = 0;
                                        if (!l) {
                                            break Label_0337;
                                        }
                                    }
                                    if (n12 >= n15 << 16) {
                                        n12 = (n15 << 16) - 65536;
                                    }
                                }
                                Label_0371: {
                                    if (n13 < 0) {
                                        n13 = 0;
                                        if (!l) {
                                            break Label_0371;
                                        }
                                    }
                                    if (n13 >= n15 << 8) {
                                        n13 = (n15 << 8) - 256;
                                    }
                                }
                                Label_0397: {
                                    if (n14 < 0) {
                                        n14 = 0;
                                        if (!l) {
                                            break Label_0397;
                                        }
                                    }
                                    if (n14 >= n15) {
                                        n14 = n15 - 1;
                                    }
                                }
                                super.h.a(n9, bg.p, n15, 0xFF000000 | (n12 & 0xFF0000) | (n13 & 0xFF00) | n14, a, bg);
                                if (!l) {
                                    break Label_0646;
                                }
                            }
                            if (n6 < 0) {
                                break Label_0646;
                            }
                            if (n7 < 0) {
                                break Label_0646;
                            }
                            if (n8 < 0) {
                                break Label_0646;
                            }
                        }
                        final int b2 = this.b(super.ax, bg);
                        int n16 = this.a + (b2 & 0x1FE0000) - 16777216;
                        int n17 = this.b + (b2 & 0x1FE00) - 65536;
                        int n18 = this.c + (b2 & 0x1FE) - 256;
                        Label_0556: {
                            if (n18 < 0) {
                                n18 = 0;
                                if (!l) {
                                    break Label_0556;
                                }
                            }
                            if (n18 > 255) {
                                n18 = 255;
                            }
                        }
                        Label_0580: {
                            if (n17 < 0) {
                                n17 = 0;
                                if (!l) {
                                    break Label_0580;
                                }
                            }
                            if (n17 > 65280) {
                                n17 = 65280;
                            }
                        }
                        Label_0604: {
                            if (n16 < 0) {
                                n16 = 0;
                                if (!l) {
                                    break Label_0604;
                                }
                            }
                            if (n16 > 16711680) {
                                n16 = 16711680;
                            }
                        }
                        super.e[n9] = (n16 | n17 | n18 | 0x7F000000);
                        super.c[n9] = bg.p;
                        final int[] f = super.f;
                        final int n19 = n11;
                        f[n19] |= n10;
                    }
                }
                ++n9;
                bg.p += super.y;
                bg.q += super.u;
                bg.r += super.w;
                n6 += super.k;
                n7 += super.l;
                n8 += super.m;
            }
            if (n9 >= n5) {
                return;
            }
            continue;
        }
    }
}
