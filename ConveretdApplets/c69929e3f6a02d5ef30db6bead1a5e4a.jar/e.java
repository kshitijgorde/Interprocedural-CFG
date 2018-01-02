// 
// Decompiled by Procyon v0.5.30
// 

public class e extends b
{
    int k;
    boolean l;
    int m;
    int n;
    int[] o;
    
    public e(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4) {
        super(array, array2, n, n2, n3, n4);
        this.k = 0;
        this.l = true;
        this.o = new int[super.c * super.d];
    }
    
    public int[] a(final long n) {
        final int c = a.c;
        final int c2 = super.c;
        final int d = super.d;
        final int n2 = super.c * super.d;
        final int n3 = c2;
        if (this.l) {
            this.l = false;
            System.arraycopy(super.a, 0, this.o, 0, n2);
            this.m = 0;
            this.n = 0;
        }
        final int n4 = (int)n * c2 * 11 / super.f / 8;
        int n5 = -1;
        int n6 = 99999999;
        int n7 = 0;
        while (true) {
            Label_0833: {
                if (c == 0) {
                    break Label_0833;
                }
                final int n10;
                final int n11;
                int n9;
                final int n8 = n9 = n10 * n11;
                int n12 = -1;
                int n13 = n8;
                final int n14 = n4 - (d - n7) / 2;
                int n15 = 0;
                int n16 = 0;
                final int n17 = 9830;
                final int n18 = 13107;
                int n19 = n17;
                int n20 = n18;
                final int n21 = n17 / (c2 / 8);
                final int n22 = n18 / (d / 8);
                final int n23 = n17 / 2;
                int n24 = c2 / 32;
                int n25 = 0;
                final int n26 = 16384 / n24;
                int n27 = 0;
                Label_0830: {
                    if (n14 > 0) {
                        int n28 = 0;
                        while (true) {
                            Label_0824: {
                                if (c == 0) {
                                    break Label_0824;
                                }
                                Label_0651: {
                                    if (n28 < n14) {
                                        this.o[n9++] = super.b[n13++];
                                        if (c == 0) {
                                            break Label_0651;
                                        }
                                    }
                                    if (n27 == 0) {
                                        if (n28 < n6) {
                                            n6 = n28;
                                        }
                                        if (n12 == -1) {
                                            n15 = n28 * 16384 + 8192;
                                            n16 = (d - n7 - 1) * 16384 + 8192;
                                        }
                                        if (n15 < 0 || n16 < 0) {
                                            n27 = 1;
                                            n12 = n9;
                                            if (c == 0) {
                                                break Label_0651;
                                            }
                                        }
                                        n12 = n3 * (n16 / 16384) + n15 / 16384;
                                        this.o[n9++] = super.a[n12++];
                                        if (n19 > n23) {
                                            int n29 = n19 - n23 - n23 / 2;
                                            if (n29 < 0) {
                                                n29 = -n29;
                                            }
                                            final int n30 = n23 / 2 - n29;
                                            int n31 = (n30 + n30 / 2) / 16;
                                            if (n31 > 255) {
                                                n31 = 255;
                                            }
                                            final int n32 = 255 - n31;
                                            final int n33 = n31 * 255;
                                            final int n34 = this.o[n9 - 1] & 0xFFFFFF;
                                            int n35 = (n32 * (n34 >> 16) + n33) / 256;
                                            if (n35 > 255) {
                                                n35 = 255;
                                            }
                                            int n36 = (n32 * (n34 >> 8 & 0xFF) + n33) / 256;
                                            if (n36 > 255) {
                                                n36 = 255;
                                            }
                                            int n37 = (n32 * (n34 & 0xFF) + n33) / 256;
                                            if (n37 > 255) {
                                                n37 = 255;
                                            }
                                            this.o[n9 - 1] = n37 + (n36 << 8) + (n35 << 16);
                                        }
                                        Label_0607: {
                                            if (n19 >= 0) {
                                                n15 -= n17 + n19;
                                                if (c == 0) {
                                                    break Label_0607;
                                                }
                                            }
                                            n15 -= n17 - n19;
                                        }
                                        Label_0637: {
                                            if (n20 >= 0) {
                                                n16 -= n18 + n20;
                                                if (c == 0) {
                                                    break Label_0637;
                                                }
                                            }
                                            n16 -= n18 - n20;
                                        }
                                        n19 -= n21;
                                        n20 -= n22;
                                    }
                                }
                                if (n27 != 0) {
                                    if (n28 > n5) {
                                        n5 = n28;
                                    }
                                    if (--n24 < 0) {
                                        break Label_0830;
                                    }
                                    n25 += n26;
                                    final int n38 = this.o[n9] & 0xFFFFFF;
                                    final int n39 = super.a[n12++] & 0xFFFFFF;
                                    this.o[n9++] = (n38 & 0xFF) / 2 + (n39 & 0xFF) * n25 / 32768 + ((n38 >> 8 & 0xFF) / 2 + (n39 >> 8 & 0xFF) * n25 / 32768 << 8) + ((n38 >> 16) / 2 + (n39 >> 16) * n25 / 32768 << 16);
                                }
                                ++n28;
                            }
                            if (n28 < c2) {
                                continue;
                            }
                            break;
                        }
                    }
                }
                ++n7;
            }
            if (n7 < d) {
                continue;
            }
            final int n10 = n5;
            final int n11 = -1;
            if (c == 0) {
                if (n10 == n11 && n6 == 99999999) {
                    n5 = 0;
                    n6 = 0;
                }
                if (n5 < 0) {}
                this.n = n6;
                --this.n;
                if (this.n < 0) {
                    this.n = 0;
                }
                return this.o;
            }
            continue;
        }
    }
}
