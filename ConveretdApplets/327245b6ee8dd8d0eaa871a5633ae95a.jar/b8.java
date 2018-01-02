// 
// Decompiled by Procyon v0.5.30
// 

public final class b8 extends bg
{
    b8() {
        super.a0 = 3;
        super.q = true;
    }
    
    int b() {
        if (super.i[3] != null && !super.i[3].h) {
            final l l = super.i[3];
            l.h = true;
            k.a(l, 12, 4, 2, 4, 1);
        }
        if (super.i[1] != null && !super.i[1].h) {
            super.i[1].h = true;
            k.a(super.i[1], 200, 4, 2, 4, 1);
            super.i[1].b();
        }
        return 1;
    }
    
    void b(final int n) {
        super.b(n);
        super.l = super.i[3].f[n];
        if (super.i[3].c != super.i[0].c) {
            System.out.println("Warning: shdtxppl env maps differ in size");
        }
    }
    
    final void c(final int n, final int n2, int n3, final int n4) {
        final boolean l = c.l;
        final int n5 = n + n2;
        int n6 = super.cu * n3 + super.cx * n4 + super.c0;
        int n7 = super.cv * n3 + super.cy * n4 + super.c1;
        int n8 = super.cw * n3 + super.cz * n4 + super.c2;
        int n9 = n;
        while (true) {
            Label_0931: {
                if (!l) {
                    break Label_0931;
                }
                Label_0833: {
                    if (super.p >= super.y[n9]) {
                        final int n10 = 1 << (n9 & 0x1F);
                        final int n11 = n9 >> 5;
                        Label_0550: {
                            if ((super.ac[n11] & n10) != 0x0 && super.p > super.ad[n9 << 1] && super.p < super.ad[(n9 << 1) + 1]) {
                                if (n6 < -34800 || n7 < -34800) {
                                    break Label_0833;
                                }
                                if (n8 < -34800) {
                                    break Label_0833;
                                }
                                final int a = this.a(n6, n7, n8);
                                if (a == 0) {
                                    break Label_0833;
                                }
                                if (a == 65535) {
                                    break Label_0550;
                                }
                                final int e = bg.e(a);
                                final int a2 = this.a(super.t, false);
                                final int n12 = (a2 & 0x7F000000) >>> 24;
                                final int n13 = 128 - n12;
                                final int b = this.b(super.k);
                                final int b2 = this.b(super.l);
                                final int n14 = (b & 0x1FE0000) * n12 + (b2 & 0x1FE0000) * n13 >> 7;
                                final int n15 = (b & 0x1FE00) * n12 + (b2 & 0x1FE00) * n13 >> 7;
                                final int n16 = (b & 0x1FE) * n12 + (b2 & 0x1FE) * n13 >> 7;
                                int n17 = (n14 & 0x1FE0000) + (a2 & 0xFF0000) - 16777216;
                                int n18 = (n15 & 0x1FE00) + (a2 & 0xFF00) - 65536;
                                int n19 = (n16 & 0x1FE) + (a2 & 0xFF) - 256;
                                Label_0413: {
                                    if (n17 < 0) {
                                        n17 = 0;
                                        if (!l) {
                                            break Label_0413;
                                        }
                                    }
                                    if (n17 > 16711680) {
                                        n17 = 16711680;
                                    }
                                }
                                Label_0437: {
                                    if (n18 < 0) {
                                        n18 = 0;
                                        if (!l) {
                                            break Label_0437;
                                        }
                                    }
                                    if (n18 > 65280) {
                                        n18 = 65280;
                                    }
                                }
                                Label_0463: {
                                    if (n19 < 0) {
                                        n19 = 0;
                                        if (!l) {
                                            break Label_0463;
                                        }
                                    }
                                    if (n19 > 255) {
                                        n19 = 255;
                                    }
                                }
                                super.ae.a(n9, super.p, e << 4, 255, n17 * e >> 20, n18 * e >> 12, n19 * e >> 4, a, this);
                                if (!l) {
                                    break Label_0833;
                                }
                            }
                            if (n6 < 0) {
                                break Label_0833;
                            }
                            if (n7 < 0) {
                                break Label_0833;
                            }
                            if (n8 < 0) {
                                break Label_0833;
                            }
                        }
                        final int a3 = this.a(super.t, false);
                        final int n20 = (a3 & 0x7F000000) >>> 24;
                        final int n21 = 128 - n20;
                        final int b3 = this.b(super.k);
                        final int b4 = this.b(super.l);
                        final int n22 = (b3 & 0x1FE0000) * n20 + (b4 & 0x1FE0000) * n21 >> 7;
                        final int n23 = (b3 & 0x1FE00) * n20 + (b4 & 0x1FE00) * n21 >> 7;
                        final int n24 = (b3 & 0x1FE) * n20 + (b4 & 0x1FE) * n21 >> 7;
                        int n25 = (n22 & 0x1FE0000) + (a3 & 0xFF0000) - 16777216;
                        int n26 = (n23 & 0x1FE00) + (a3 & 0xFF00) - 65536;
                        int n27 = (n24 & 0x1FE) + (a3 & 0xFF) - 256;
                        Label_0742: {
                            if (n25 < 0) {
                                n25 = 0;
                                if (!l) {
                                    break Label_0742;
                                }
                            }
                            if (n25 > 16711680) {
                                n25 = 16711680;
                            }
                        }
                        Label_0766: {
                            if (n26 < 0) {
                                n26 = 0;
                                if (!l) {
                                    break Label_0766;
                                }
                            }
                            if (n26 > 65280) {
                                n26 = 65280;
                            }
                        }
                        Label_0792: {
                            if (n27 < 0) {
                                n27 = 0;
                                if (!l) {
                                    break Label_0792;
                                }
                            }
                            if (n27 > 255) {
                                n27 = 255;
                            }
                        }
                        super.aa[n9] = (n25 | n26 | n27 | 0x7F000000);
                        super.y[n9] = super.p;
                        final int[] ab = super.ab;
                        final int n28 = n11;
                        ab[n28] |= n10;
                    }
                }
                ++n9;
                ++n3;
                super.q += super.s;
                super.r += super.t;
                super.w += super.y;
                super.x += super.z;
                super.p += super.bc;
                n6 += super.cu;
                n7 += super.cv;
                n8 += super.cw;
            }
            if (n9 >= n5) {
                return;
            }
            continue;
        }
    }
}
