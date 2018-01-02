// 
// Decompiled by Procyon v0.5.30
// 

public final class b1 extends bg
{
    b1() {
        super.a0 = 3;
        super.q = true;
    }
    
    int a() {
        return 2;
    }
    
    final void c(final int n, final int n2, int n3, final int n4) {
        final boolean l = c.l;
        final int n5 = n2 + n;
        int n6 = super.cu * n3 + super.cx * n4 + super.c0;
        int n7 = super.cv * n3 + super.cy * n4 + super.c1;
        int n8 = super.cw * n3 + super.cz * n4 + super.c2;
        int n9 = n;
        while (true) {
            Label_0766: {
                if (!l) {
                    break Label_0766;
                }
                Label_0668: {
                    if (super.p >= super.y[n9]) {
                        final int n10 = 1 << (n9 & 0x1F);
                        final int n11 = n9 >> 5;
                        Label_0481: {
                            if ((super.ac[n11] & n10) != 0x0 && super.p > super.ad[n9 << 1] && super.p < super.ad[(n9 << 1) + 1]) {
                                if (n6 < -34800 || n7 < -34800) {
                                    break Label_0668;
                                }
                                if (n8 < -34800) {
                                    break Label_0668;
                                }
                                final int a = this.a(n6, n7, n8);
                                if (a == 0) {
                                    break Label_0668;
                                }
                                if (a == 65535) {
                                    break Label_0481;
                                }
                                final int e = bg.e(a);
                                final int b = this.b(super.k);
                                final int a2 = this.a(super.t, true, a);
                                int n12 = (a2 & 0xFF0000) + (((b & 0x1FE0000) - 16777216) * e >> 4);
                                int n13 = (a2 & 0xFF00) + (((b & 0x1FE00) - 65536) * e >> 4);
                                int n14 = (a2 & 0xFF) + (((b & 0x1FE) - 256) * e >> 4);
                                final int n15 = e << 4;
                                Label_0349: {
                                    if (n12 < 0) {
                                        n12 = 0;
                                        if (!l) {
                                            break Label_0349;
                                        }
                                    }
                                    if (n12 >= n15 << 16) {
                                        n12 = (n15 << 16) - 65536;
                                    }
                                }
                                Label_0383: {
                                    if (n13 < 0) {
                                        n13 = 0;
                                        if (!l) {
                                            break Label_0383;
                                        }
                                    }
                                    if (n13 >= n15 << 8) {
                                        n13 = (n15 << 8) - 256;
                                    }
                                }
                                Label_0409: {
                                    if (n14 < 0) {
                                        n14 = 0;
                                        if (!l) {
                                            break Label_0409;
                                        }
                                    }
                                    if (n14 >= n15) {
                                        n14 = n15 - 1;
                                    }
                                }
                                super.ae.a(n9, super.p, n15, 0xFF000000 | (n12 & 0xFF0000) | (n13 & 0xFF00) | n14, a, this);
                                if (!l) {
                                    break Label_0668;
                                }
                            }
                            if (n6 < 0) {
                                break Label_0668;
                            }
                            if (n7 < 0) {
                                break Label_0668;
                            }
                            if (n8 < 0) {
                                break Label_0668;
                            }
                        }
                        final int b2 = this.b(super.k);
                        final int a3 = this.a(super.t, true);
                        int n16 = (a3 & 0xFF0000) + (b2 & 0x1FE0000) - 16777216;
                        int n17 = (a3 & 0xFF00) + (b2 & 0x1FE00) - 65536;
                        int n18 = (a3 & 0xFF) + (b2 & 0x1FE) - 256;
                        Label_0579: {
                            if (n18 < 0) {
                                n18 = 0;
                                if (!l) {
                                    break Label_0579;
                                }
                            }
                            if (n18 > 255) {
                                n18 = 255;
                            }
                        }
                        Label_0603: {
                            if (n17 < 0) {
                                n17 = 0;
                                if (!l) {
                                    break Label_0603;
                                }
                            }
                            if (n17 > 65280) {
                                n17 = 65280;
                            }
                        }
                        Label_0627: {
                            if (n16 < 0) {
                                n16 = 0;
                                if (!l) {
                                    break Label_0627;
                                }
                            }
                            if (n16 > 16711680) {
                                n16 = 16711680;
                            }
                        }
                        super.aa[n9] = (n16 | n17 | n18 | 0x7F000000);
                        super.y[n9] = super.p;
                        final int[] ab = super.ab;
                        final int n19 = n11;
                        ab[n19] |= n10;
                    }
                }
                ++n9;
                ++n3;
                super.p += super.bc;
                super.q += super.s;
                super.r += super.t;
                super.w += super.y;
                super.x += super.z;
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
