// 
// Decompiled by Procyon v0.5.30
// 

public class m extends b
{
    long k;
    boolean l;
    int m;
    int n;
    int o;
    int[] p;
    int q;
    int r;
    int s;
    int t;
    int u;
    int v;
    
    public m(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4) {
        super(array, array2, n, n2, n3, n4);
        this.k = 0L;
        this.l = true;
        this.o = super.c * super.d;
        this.p = new int[this.o];
        this.t = super.c;
        this.u = super.d;
        this.v = this.t;
    }
    
    public int[] a(final long n) {
        final int c = a.c;
        final int n2 = 16384 * (int)n / super.f;
        int n3;
        final boolean b = (n3 = (this.l ? 1 : 0)) != 0;
        if (c == 0) {
            if (b) {
                this.l = false;
                System.arraycopy(super.a, 0, this.p, 0, this.o);
                this.n = 0;
            }
            n3 = this.n;
        }
        int n4 = n3;
        int n5 = this.n;
        while (true) {
        Label_1315:
            while (true) {
                Label_1307: {
                    if (c == 0) {
                        break Label_1307;
                    }
                    final m m = this;
                    final int t = m.t;
                    final int n6 = n5 * 10240 / this.t - n2 - 512 + 3000;
                    final int n7 = n5 * 10240 / this.t - n2 + 512 + 3000;
                    int n8 = n6 * 2;
                    int n9 = n7 * 2;
                    int n12;
                    int n11;
                    final int n10 = n11 = (n12 = n8);
                    if (c == 0) {
                        if (n10 < 0) {
                            n8 = -n8;
                        }
                        final int n13;
                        n11 = (n13 = (n12 = n9));
                    }
                    if (c == 0) {
                        if (n10 < 0) {
                            n9 = -n9;
                        }
                        n12 = (n11 = n8);
                    }
                    int n14 = 0;
                    Label_0212: {
                        if (c == 0) {
                            if (n11 < 8192) {
                                n14 = 16384 * n8 - n8 * n8;
                                if (c == 0) {
                                    break Label_0212;
                                }
                            }
                            n12 = 67108864;
                        }
                        n14 = n12;
                    }
                    final int n16;
                    int n15 = n16 = n14 / 8192 * this.u / 8192;
                    final int n17 = 1;
                    int n20 = 0;
                    Label_0279: {
                        int n18 = 0;
                        Label_0277: {
                            if (c == 0) {
                                if (n16 < n17) {
                                    n15 = 1;
                                }
                                final int n19;
                                n18 = (n19 = n9);
                                if (c != 0) {
                                    break Label_0277;
                                }
                            }
                            if (n16 < n17) {
                                n20 = 16384 * n9 - n9 * n9;
                                if (c == 0) {
                                    break Label_0279;
                                }
                            }
                            n18 = 67108864;
                        }
                        n20 = n18;
                    }
                    final int n22;
                    int n21 = n22 = n20 / 8192 * this.u / 8192;
                    final int n23 = 1;
                    if (c == 0) {
                        if (n22 < n23) {
                            n21 = 1;
                        }
                        final int n24 = this.u - n15;
                    }
                    final int n25 = n22 / n23;
                    final int n26 = this.u - n25;
                    final int n27 = (this.u - n21 + 1) / 2;
                    final int n28 = n25;
                    if (c == 0) {
                        if (n28 == 0) {
                            final int n29 = n6;
                            if (c == 0 && n29 < 0) {
                                n4 = n5 + 1;
                                if (c != 0) {
                                    goto Label_0373;
                                }
                            }
                            else if (c == 0) {
                                break Label_1315;
                            }
                        }
                        final int n30 = 16384 * this.u / n15;
                    }
                    final int n31 = n28;
                    int n32 = 0;
                    int n33 = 0;
                    int n34 = n26 - n27;
                    int n37;
                    int n36;
                    final int n35 = n36 = (n37 = n6);
                    if (c == 0) {
                        if (n35 < 0) {
                            n34 = n25 - n27;
                        }
                        final int n38;
                        n36 = (n38 = (n37 = n34));
                    }
                    if (c == 0) {
                        if (n35 < 0) {
                            n34 = 0;
                        }
                        n37 = (n36 = n34);
                    }
                    if (c == 0) {
                        if (n36 > 0) {
                            n33 = 16384 * this.u / n34;
                        }
                        n37 = 0;
                    }
                    int n39 = n37;
                    int n40 = 262144;
                    int n42;
                    final int n41 = n42 = n25;
                    int n44;
                    final int n43 = n44 = this.u / 4;
                    if (c == 0) {
                        if (n41 < n43) {
                            n40 -= 262144 * (this.u / 4 - n25) / (this.u / 4);
                        }
                        final int n45;
                        n42 = (n45 = n40);
                        final int n46;
                        n44 = (n46 = 262144);
                    }
                    if (c == 0) {
                        if (n41 > n43) {
                            n40 = 262144;
                        }
                        n42 = -n40 / ((this.u / 2 - n25) / 4 + 1);
                        n44 = 2;
                    }
                    int n47 = n42 / n44;
                    int n48 = n40 / ((this.u / 2 - n27) / 4 + 1);
                    int n49 = n40 - 1;
                    int n50 = 0;
                    int n51 = 0;
                    while (true) {
                        Label_1295: {
                            if (c == 0) {
                                break Label_1295;
                            }
                            int n52 = n5 + (this.u - n51 - 1) * this.v;
                            final int n53 = n51;
                            final int n54 = n25;
                            Label_1292: {
                                int n59 = 0;
                                int n57 = 0;
                                Label_0663: {
                                    Label_0661: {
                                        Label_0642: {
                                            Label_0639: {
                                                if (c == 0) {
                                                    if (n53 < n54) {
                                                        final int n55 = n51;
                                                        final int n56 = n27;
                                                        if (c != 0) {
                                                            break Label_0639;
                                                        }
                                                        if (n55 < n56) {
                                                            break Label_0642;
                                                        }
                                                    }
                                                    final int n58;
                                                    n57 = (n58 = (n59 = n51));
                                                    if (c != 0) {
                                                        break Label_0663;
                                                    }
                                                }
                                            }
                                            if (n53 <= n54) {
                                                break Label_0661;
                                            }
                                        }
                                        this.p[n52++] = super.e;
                                        if (c == 0) {
                                            break Label_1292;
                                        }
                                    }
                                    n59 = (n57 = n6);
                                }
                                int n62 = 0;
                                final int n66;
                                final int n67;
                                final int n70;
                                final int n73;
                                Label_0908: {
                                    Label_0902: {
                                        if (c == 0) {
                                            final int n60;
                                            final int n61;
                                            Label_0804: {
                                                if (n57 < 0) {
                                                    n60 = n51;
                                                    n61 = n25;
                                                    if (c != 0) {
                                                        break Label_0804;
                                                    }
                                                    if (n60 >= n61) {
                                                        n62 = n32++ * n31 / 16384;
                                                        n49 += n47;
                                                        int n65;
                                                        final int n64;
                                                        final int n63 = n64 = (n65 = n49);
                                                        if (c == 0 && n63 < 0) {
                                                            n66 = (n65 = (n51 - n25) * 4);
                                                            final int n68;
                                                            n67 = (n68 = (n26 - n25) * 3);
                                                            if (c != 0) {
                                                                break Label_0908;
                                                            }
                                                            if (n66 <= n67) {
                                                                break Label_0902;
                                                            }
                                                            n49 = 0;
                                                            final int n69 = n47;
                                                            if (c == 0 && n69 < 0) {
                                                                n47 = -n47;
                                                                goto Label_0754;
                                                            }
                                                            n47 = n69;
                                                            if (c != 0) {
                                                                goto Label_0765;
                                                            }
                                                            break Label_0902;
                                                        }
                                                        else {
                                                            n70 = n40;
                                                            if (c != 0) {
                                                                break Label_0908;
                                                            }
                                                            if (n63 < n70) {
                                                                break Label_0902;
                                                            }
                                                            n47 = -n47;
                                                            n49 = n40 - 1;
                                                            if (c == 0) {
                                                                break Label_0902;
                                                            }
                                                        }
                                                    }
                                                }
                                                final int n71 = n39++ * n33;
                                            }
                                            n59 = n60 / n61;
                                        }
                                        n62 = n59;
                                        n50 += n48;
                                        int n65;
                                        int n64;
                                        final int n72 = n64 = (n65 = n50);
                                        if (c == 0 && n72 < 0) {
                                            n73 = (n51 - n25) * 4;
                                            final int n74 = (n26 - n25) * 3;
                                            if (c != 0) {
                                                break Label_0908;
                                            }
                                            if (n73 > n74) {
                                                n50 = 0;
                                                final int n75 = n48;
                                                if (c == 0 && n75 < 0) {
                                                    n48 = -n48;
                                                    goto Label_0864;
                                                }
                                                n48 = n75;
                                                if (c != 0) {
                                                    goto Label_0875;
                                                }
                                            }
                                        }
                                        else {
                                            final int n76 = n40 / 2;
                                            if (c != 0) {
                                                break Label_0908;
                                            }
                                            if (n72 >= n76) {
                                                n48 = -n48;
                                                n50 = n40 / 2 - 1;
                                            }
                                        }
                                    }
                                    final int u = this.u;
                                }
                                if (c == 0) {
                                    if (n66 >= n67) {
                                        n62 = this.u - 1;
                                    }
                                    final int n68 = (this.u - n62 - 1) * this.v;
                                }
                                final int n77 = n73 + n70;
                                final int n79;
                                final int n78 = n79 = n6;
                                final int n80;
                                int n82;
                                final int n81;
                                if (c == 0 && n78 < 0) {
                                    n80 = n51;
                                    n81 = (n82 = n25);
                                    if (c == 0) {
                                        if (n80 < n81) {
                                            goto Label_1131;
                                        }
                                        final int n83 = n49;
                                        final int n84 = 10;
                                        if (c != 0 || n83 > n84) {
                                            int n85 = n83 * n84;
                                            final int n86 = 262144 - n85 / 256;
                                            final int n87 = n51;
                                            final int n88 = this.u / 2;
                                            if (c == 0) {
                                                if (n87 < n88) {
                                                    n85 = 0;
                                                }
                                                final int n89 = super.b[n77];
                                            }
                                            final int n90 = n87 & n88;
                                            this.p[n52++] = (n86 * (n90 & 0xFF) + n85) / 262144 + ((n86 * (n90 >> 8 & 0xFF) + n85) / 262144 << 8) + ((n86 * (n90 >> 16) + n85) / 262144 << 16);
                                            if (c == 0) {
                                                break Label_1292;
                                            }
                                        }
                                        this.p[n52++] = super.b[n77];
                                        if (c != 0) {
                                            goto Label_1131;
                                        }
                                        break Label_1292;
                                    }
                                }
                                else {
                                    final int n91;
                                    n82 = (n91 = 10);
                                }
                                Label_1275: {
                                    if (c == 0) {
                                        if (n78 <= n81) {
                                            break Label_1275;
                                        }
                                        n82 = 256;
                                    }
                                    int n92 = n80 * n82;
                                    final int n93 = 262144 - n92 / 256;
                                    final int n94 = n51;
                                    final int n95 = this.u / 2;
                                    if (c == 0) {
                                        if (n94 > n95) {
                                            n92 = 0;
                                        }
                                        final int n96 = super.a[n77];
                                    }
                                    final int n97 = n94 & n95;
                                    this.p[n52++] = (n93 * (n97 & 0xFF) + n92) / 262144 + ((n93 * (n97 >> 8 & 0xFF) + n92) / 262144 << 8) + ((n93 * (n97 >> 16) + n92) / 262144 << 16);
                                    if (c == 0) {
                                        break Label_1292;
                                    }
                                }
                                this.p[n52++] = super.a[n77];
                            }
                            ++n51;
                        }
                        if (n51 < this.u) {
                            continue;
                        }
                        break;
                    }
                    ++n5;
                }
                if (n5 < this.t) {
                    continue;
                }
                break;
            }
            this.n = n4;
            final m m = this;
            if (c == 0) {
                return this.p;
            }
            continue;
        }
    }
}
