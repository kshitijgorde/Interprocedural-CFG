// 
// Decompiled by Procyon v0.5.30
// 

public final class b7 extends bg
{
    b7() {
        super.a0 = 2;
    }
    
    final void c(final int n, final int n2, int n3, final int n4) {
        final boolean l = c.l;
        final int n5 = n2 + n;
        int n6 = super.cu * n3 + super.cx * n4 + super.c0;
        int n7 = super.cv * n3 + super.cy * n4 + super.c1;
        int n8 = super.cw * n3 + super.cz * n4 + super.c2;
        int n9 = n;
        while (true) {
            Label_0475: {
                if (!l) {
                    break Label_0475;
                }
                Label_0377: {
                    if (super.p >= super.y[n9]) {
                        Label_0324: {
                            if ((super.ac[n9 >> 5] & 1 << (n9 & 0x1F)) != 0x0 && super.p > super.ad[n9 << 1] && super.p < super.ad[(n9 << 1) + 1]) {
                                if (n6 < -34800 || n7 < -34800) {
                                    break Label_0377;
                                }
                                if (n8 < -34800) {
                                    break Label_0377;
                                }
                                final int a = this.a(n6, n7, n8);
                                if (a == 0) {
                                    break Label_0377;
                                }
                                if (a == 65535) {
                                    break Label_0324;
                                }
                                final int e = bg.e(a);
                                final int a2 = this.a(super.t, true, a);
                                super.ae.a(n9, super.p, e << 4, 255, (a2 & 0xFF0000) >> 16, (a2 & 0xFF00) >> 8, a2 & 0xFF, a, this);
                                if (!l) {
                                    break Label_0377;
                                }
                            }
                            if (n6 < 0) {
                                break Label_0377;
                            }
                            if (n7 < 0) {
                                break Label_0377;
                            }
                            if (n8 < 0) {
                                break Label_0377;
                            }
                        }
                        super.aa[n9] = (this.a(super.t, true) | 0x7F000000);
                        super.y[n9] = super.p;
                        final int[] ab = super.ab;
                        final int n10 = n9 >> 5;
                        ab[n10] |= 1 << (n9 & 0x1F);
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
