// 
// Decompiled by Procyon v0.5.30
// 

public final class cb extends bg
{
    private int a;
    
    cb() {
        super.d = true;
        super.a0 = 3;
        this.a = 0;
    }
    
    protected boolean a(final float[][] array) {
        if (super.i[0] == null || super.i[1] == null) {
            return false;
        }
        super.l = array[0];
        super.m = array[1];
        this.a = (super.b.e[1] & 0x7F000000) >> 24;
        return true;
    }
    
    final void c(final int n, final int n2, int n3, final int n4) {
        final boolean l = c.l;
        final int n5 = n2 + n;
        int n6 = super.cu * n3 + super.cx * n4 + super.c0;
        int n7 = super.cv * n3 + super.cy * n4 + super.c1;
        int n8 = super.cw * n3 + super.cz * n4 + super.c2;
        int n9 = n;
        while (true) {
            Label_0865: {
                if (!l) {
                    break Label_0865;
                }
                Label_0767: {
                    if (super.p >= super.y[n9]) {
                        Label_0533: {
                            if ((super.ac[n9 >> 5] & 1 << (n9 & 0x1F)) != 0x0 && super.p > super.ad[n9 << 1] && super.p < super.ad[(n9 << 1) + 1]) {
                                if (n6 < -34800 || n7 < -34800) {
                                    break Label_0767;
                                }
                                if (n8 < -34800) {
                                    break Label_0767;
                                }
                                final int a = this.a(n6, n7, n8);
                                if (a == 0) {
                                    break Label_0767;
                                }
                                if (a == 65535) {
                                    break Label_0533;
                                }
                                final int e = bg.e(a);
                                final int b = this.b(super.k);
                                final int a2 = this.a(super.t, true, a);
                                final int n10 = (this.a * ((a2 & 0xFF0000) >> 16) << 9 & 0xFF0000) * e >> 4;
                                final int n11 = (this.a * ((a2 & 0xFF00) >> 8) << 1 & 0xFF00) * e >> 4;
                                final int n12 = (this.a * (a2 & 0xFF) >> 7 & 0xFF) * e >> 4;
                                final int n13 = n10 + (((b & 0x1FE0000) - 16777216) * e >> 4);
                                final int n14 = n11 + (((b & 0x1FE00) - 65536) * e >> 4);
                                int n15 = n12 + (((b & 0x1FE) - 256) * e >> 4);
                                int n16 = n13 >> 16;
                                int n17 = n14 >> 8;
                                final int n18 = e << 4;
                                Label_0422: {
                                    if (n16 < 0) {
                                        n16 = 0;
                                        if (!l) {
                                            break Label_0422;
                                        }
                                    }
                                    if (n16 >= n18) {
                                        n16 = n18 - 1;
                                    }
                                }
                                Label_0448: {
                                    if (n17 < 0) {
                                        n17 = 0;
                                        if (!l) {
                                            break Label_0448;
                                        }
                                    }
                                    if (n17 >= n18) {
                                        n17 = n18 - 1;
                                    }
                                }
                                Label_0474: {
                                    if (n15 < 0) {
                                        n15 = 0;
                                        if (!l) {
                                            break Label_0474;
                                        }
                                    }
                                    if (n15 >= n18) {
                                        n15 = n18 - 1;
                                    }
                                }
                                super.ae.a(n9, super.p, n18, this.a << 1, n16, n17, n15, a, this);
                                if (!l) {
                                    break Label_0767;
                                }
                            }
                            if (n6 < 0) {
                                break Label_0767;
                            }
                            if (n7 < 0) {
                                break Label_0767;
                            }
                            if (n8 < 0) {
                                break Label_0767;
                            }
                        }
                        final int b2 = this.b(super.k);
                        final int a3 = this.a(super.t, true);
                        final int n19 = this.a * ((a3 & 0xFF0000) >> 16) << 9 & 0xFF0000;
                        final int n20 = this.a * ((a3 & 0xFF00) >> 8) << 1 & 0xFF00;
                        final int n21 = this.a * (a3 & 0xFF) >> 7 & 0xFF;
                        int n22 = n19 + ((b2 & 0x1FE0000) - 16777216);
                        int n23 = n20 + ((b2 & 0x1FE00) - 65536);
                        int n24 = n21 + ((b2 & 0x1FE) - 256);
                        Label_0682: {
                            if (n24 < 0) {
                                n24 = 0;
                                if (!l) {
                                    break Label_0682;
                                }
                            }
                            if (n24 > 255) {
                                n24 = 255;
                            }
                        }
                        Label_0706: {
                            if (n23 < 0) {
                                n23 = 0;
                                if (!l) {
                                    break Label_0706;
                                }
                            }
                            if (n23 > 65280) {
                                n23 = 65280;
                            }
                        }
                        Label_0730: {
                            if (n22 < 0) {
                                n22 = 0;
                                if (!l) {
                                    break Label_0730;
                                }
                            }
                            if (n22 > 16711680) {
                                n22 = 16711680;
                            }
                        }
                        super.ae.a(n9, super.p, 256, this.a << 1, n22 >> 16, n23 >> 8, n24, 65535, this);
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
