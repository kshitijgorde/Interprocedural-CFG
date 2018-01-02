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
        int n4 = c2;
        int n6;
        final int n5 = n6 = d;
        if (c == 0) {
            if (n5 > c2) {
                n4 = d;
            }
            final boolean l;
            n6 = ((l = this.l) ? 1 : 0);
        }
        if (c == 0) {
            if (n5 != 0) {
                this.l = false;
                System.arraycopy(super.a, 0, this.o, 0, n2);
                this.m = 0;
                this.n = 0;
            }
            n6 = (int)n * n4 * 11 / super.f / 8;
        }
        final int n7 = n6;
        int n8 = -1;
        int n9 = 99999999;
        int n10 = 0;
        while (true) {
            Label_0947: {
                if (c == 0) {
                    break Label_0947;
                }
                final int n13;
                final int n14;
                int n12;
                final int n11 = n12 = n13 * n14;
                int n15 = -1;
                int n16 = n11;
                final int n17 = n7 - (d - n10) / 2;
                int n18 = 0;
                int n19 = 0;
                final int n20 = 9830;
                final int n21 = 13107;
                int n22 = n20;
                int n23 = n21;
                final int n24 = n20 / (c2 / 8);
                final int n25 = n21 / (d / 8);
                final int n26 = n20 / 2;
                int n27 = c2 / 32;
                int n28 = 0;
                final int n29 = 16384 / n27;
                int n30 = 0;
                final int n31 = n17;
                Label_0944: {
                    if (c != 0 || n31 > 0) {
                        int n32 = n31;
                        while (true) {
                            Label_0938: {
                                if (c == 0) {
                                    break Label_0938;
                                }
                                int n37;
                                int n36;
                                int n35;
                                int n34;
                                final int n33 = n34 = (n35 = (n36 = (n37 = n32)));
                                Label_0752: {
                                    Label_0750: {
                                        if (c == 0) {
                                            if (n33 < n17) {
                                                this.o[n12++] = super.b[n16++];
                                                if (c == 0) {
                                                    break Label_0750;
                                                }
                                            }
                                            final int n38;
                                            n34 = (n38 = (n35 = (n36 = (n37 = n30))));
                                        }
                                        if (c != 0) {
                                            break Label_0752;
                                        }
                                        if (n33 == 0) {
                                            final int n39 = n32;
                                            final int n40 = n9;
                                            int n43 = 0;
                                            int n41 = 0;
                                            Label_0353: {
                                                if (c == 0) {
                                                    if (n39 < n40) {
                                                        n9 = n32;
                                                    }
                                                    final int n42;
                                                    n41 = (n42 = (n43 = n15));
                                                    if (c != 0) {
                                                        break Label_0353;
                                                    }
                                                }
                                                if (n39 == n40) {
                                                    n18 = n32 * 16384 + 8192;
                                                    n19 = (d - n10 - 1) * 16384 + 8192;
                                                }
                                                n43 = (n41 = n18);
                                            }
                                            int n46 = 0;
                                            int n45 = 0;
                                            final int n44;
                                            Label_0423: {
                                                Label_0383: {
                                                    if (c == 0) {
                                                        if (n41 >= 0) {
                                                            n44 = (n45 = (n46 = n19));
                                                            if (c != 0) {
                                                                break Label_0423;
                                                            }
                                                            if (n44 >= 0) {
                                                                break Label_0383;
                                                            }
                                                        }
                                                        n30 = 1;
                                                        n43 = n12;
                                                    }
                                                    n15 = n43;
                                                    if (c == 0) {
                                                        break Label_0750;
                                                    }
                                                }
                                                n15 = n3 * (n19 / 16384) + n18 / 16384;
                                                this.o[n12++] = super.a[n15++];
                                                final int n47;
                                                n45 = (n47 = (n46 = n22));
                                            }
                                            if (c == 0) {
                                                if (n44 > n26) {
                                                    int n48 = n22 - n26 - n26 / 2;
                                                    int n50;
                                                    final int n49 = n50 = n48;
                                                    if (c == 0) {
                                                        if (n49 < 0) {
                                                            n48 = -n48;
                                                        }
                                                        final int n51 = n26 / 2 - n48;
                                                        n48 = (n51 + n51 / 2) / 16;
                                                        final int n52;
                                                        n50 = (n52 = n48);
                                                    }
                                                    final int n53 = 255;
                                                    if (c == 0) {
                                                        if (n49 > n53) {
                                                            n48 = 255;
                                                        }
                                                        n50 = 255;
                                                    }
                                                    final int n55;
                                                    int n54 = n55 = ((n50 - n53) * ((this.o[n12 - 1] & 0xFFFFFF) >> 16) + n48 * 255) / 256;
                                                    final int n56 = 255;
                                                    if (c == 0 && n55 > n56) {
                                                        n54 = 255;
                                                        goto Label_0568;
                                                    }
                                                    final int n58;
                                                    int n57 = n58 = n55 / n56;
                                                    final int n59 = 255;
                                                    if (c == 0 && n58 > n59) {
                                                        n57 = 255;
                                                        goto Label_0607;
                                                    }
                                                    final int n61;
                                                    int n60 = n61 = n58 / n59;
                                                    if (c != 0 || n61 > 255) {
                                                        n60 = n61;
                                                    }
                                                    this.o[n12 - 1] = n60 + (n57 << 8) + (n54 << 16);
                                                }
                                                n46 = (n45 = n22);
                                            }
                                            Label_0701: {
                                                if (c == 0) {
                                                    if (n45 >= 0) {
                                                        n18 -= n20 + n22;
                                                        if (c == 0) {
                                                            break Label_0701;
                                                        }
                                                    }
                                                    n46 = n18 - (n20 - n22);
                                                }
                                                n18 = n46;
                                            }
                                            final int n62 = n23;
                                            if (c == 0 && n62 >= 0) {
                                                n19 -= n21 + n23;
                                                if (c != 0) {
                                                    goto Label_0726;
                                                }
                                            }
                                            else {
                                                n19 = n62;
                                            }
                                            n22 -= n24;
                                            n23 -= n25;
                                        }
                                    }
                                    n35 = (n34 = (n36 = (n37 = n30)));
                                }
                                Label_0935: {
                                    if (c == 0) {
                                        if (n34 == 0) {
                                            break Label_0935;
                                        }
                                        n36 = (n35 = (n37 = n32));
                                    }
                                    if (c == 0) {
                                        if (n35 > n8) {
                                            n8 = n32;
                                        }
                                        n37 = (n36 = --n27);
                                    }
                                    if (c == 0) {
                                        if (n36 < 0) {
                                            break Label_0944;
                                        }
                                        n28 += n29;
                                        n37 = (this.o[n12] & 0xFFFFFF);
                                    }
                                    final int n63 = n37;
                                    final int n64 = super.a[n15++] & 0xFFFFFF;
                                    this.o[n12++] = (n63 & 0xFF) / 2 + (n64 & 0xFF) * n28 / 32768 + ((n63 >> 8 & 0xFF) / 2 + (n64 >> 8 & 0xFF) * n28 / 32768 << 8) + ((n63 >> 16) / 2 + (n64 >> 16) * n28 / 32768 << 16);
                                }
                                ++n32;
                            }
                            if (n32 < c2) {
                                continue;
                            }
                            break;
                        }
                    }
                }
                ++n10;
            }
            if (n10 >= d) {
                int n66;
                int n65;
                final int n13 = n65 = (n66 = n8);
                final int n67;
                Label_0990: {
                    if (c == 0) {
                        final int n14 = -1;
                        if (c != 0) {
                            continue;
                        }
                        if (n13 == n14) {
                            n67 = (n66 = n9);
                            if (c != 0) {
                                break Label_0990;
                            }
                            if (n67 == 99999999) {
                                n8 = 0;
                                n9 = 0;
                            }
                        }
                        n66 = (n65 = n8);
                    }
                }
                if (c == 0) {
                    if (n67 < 0) {}
                    this.n = n9;
                    --this.n;
                    final e e = this;
                    if (c != 0) {
                        return e.o;
                    }
                    n66 = this.n;
                }
                if (n66 < 0) {
                    this.n = 0;
                }
                final e e = this;
                return e.o;
            }
            continue;
        }
    }
}
