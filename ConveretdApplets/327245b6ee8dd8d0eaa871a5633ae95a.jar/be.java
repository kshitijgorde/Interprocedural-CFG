// 
// Decompiled by Procyon v0.5.30
// 

class be extends s
{
    final void a(final int n, final int n2, int n3, final int n4, final bg bg) {
        final boolean l = c.l;
        final int n5 = n2 + n;
        int n6 = super.k * n3 + super.n * n4 + super.r;
        int n7 = super.l * n3 + super.p * n4 + super.s;
        int n8 = super.m * n3 + super.q * n4 + super.t;
        int n9 = n;
        while (true) {
            Label_0785: {
                if (!l) {
                    break Label_0785;
                }
                Label_0682: {
                    if (bg.p >= super.c[n9]) {
                        final int n10 = 1 << (n9 & 0x1F);
                        final int n11 = n9 >> 5;
                        Label_0490: {
                            if ((super.d[n11] & n10) != 0x0 && bg.p > super.g[n9 << 1] && bg.p < super.g[(n9 << 1) + 1]) {
                                if (n6 < -34800 || n7 < -34800) {
                                    break Label_0682;
                                }
                                if (n8 < -34800) {
                                    break Label_0682;
                                }
                                final int a = this.a(n6, n7, n8);
                                if (a == 0) {
                                    break Label_0682;
                                }
                                if (a == 65535) {
                                    break Label_0490;
                                }
                                final int e = bg.e(a);
                                final int b = this.b(super.ax, bg);
                                final int a2 = this.a(super.j, true, a, bg);
                                int n12 = (a2 & 0xFF0000) + (((b & 0x1FE0000) - 16777216) * e >> 4);
                                int n13 = (a2 & 0xFF00) + (((b & 0x1FE00) - 65536) * e >> 4);
                                int n14 = (a2 & 0xFF) + (((b & 0x1FE) - 256) * e >> 4);
                                final int n15 = e << 4;
                                Label_0356: {
                                    if (n12 < 0) {
                                        n12 = 0;
                                        if (!l) {
                                            break Label_0356;
                                        }
                                    }
                                    if (n12 >= n15 << 16) {
                                        n12 = (n15 << 16) - 65536;
                                    }
                                }
                                Label_0390: {
                                    if (n13 < 0) {
                                        n13 = 0;
                                        if (!l) {
                                            break Label_0390;
                                        }
                                    }
                                    if (n13 >= n15 << 8) {
                                        n13 = (n15 << 8) - 256;
                                    }
                                }
                                Label_0416: {
                                    if (n14 < 0) {
                                        n14 = 0;
                                        if (!l) {
                                            break Label_0416;
                                        }
                                    }
                                    if (n14 >= n15) {
                                        n14 = n15 - 1;
                                    }
                                }
                                super.h.a(n9, bg.p, n15, 0xFF000000 | (n12 & 0xFF0000) | (n13 & 0xFF00) | n14, a, bg);
                                if (!l) {
                                    break Label_0682;
                                }
                            }
                            if (n6 < 0) {
                                break Label_0682;
                            }
                            if (n7 < 0) {
                                break Label_0682;
                            }
                            if (n8 < 0) {
                                break Label_0682;
                            }
                        }
                        final int b2 = this.b(super.ax, bg);
                        final int a3 = this.a(super.j, true, bg);
                        int n16 = (a3 & 0xFF0000) + (b2 & 0x1FE0000) - 16777216;
                        int n17 = (a3 & 0xFF00) + (b2 & 0x1FE00) - 65536;
                        int n18 = (a3 & 0xFF) + (b2 & 0x1FE) - 256;
                        Label_0592: {
                            if (n18 < 0) {
                                n18 = 0;
                                if (!l) {
                                    break Label_0592;
                                }
                            }
                            if (n18 > 255) {
                                n18 = 255;
                            }
                        }
                        Label_0616: {
                            if (n17 < 0) {
                                n17 = 0;
                                if (!l) {
                                    break Label_0616;
                                }
                            }
                            if (n17 > 65280) {
                                n17 = 65280;
                            }
                        }
                        Label_0640: {
                            if (n16 < 0) {
                                n16 = 0;
                                if (!l) {
                                    break Label_0640;
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
                ++n3;
                bg.p += super.y;
                bg.q += super.u;
                bg.r += super.w;
                bg.w += super.v;
                bg.x += super.x;
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
