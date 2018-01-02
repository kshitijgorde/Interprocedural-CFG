// 
// Decompiled by Procyon v0.5.30
// 

public class j extends b
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
    a w;
    int x;
    int[] y;
    public static final int z = 0;
    public static final int A = 1;
    public static final int B = 2;
    public static final int C = 3;
    public static final int D = 4;
    public static final int E = 5;
    
    public j(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4) {
        super(array, array2, n, n2, n3, n4);
        this.k = 0L;
        this.l = true;
        this.o = super.c * super.d;
        this.p = new int[this.o];
        this.t = super.c;
        this.u = super.d;
        this.v = this.t;
        this.y = new int[4000];
    }
    
    public int[] a(final long n) {
        final int c = a.c;
        int n2 = -1;
        int n3 = 1000000000;
        int n4 = 0;
        int u = this.u;
        int n5 = this.t;
        int u2;
        final int n6 = u2 = this.u;
        if (c == 0) {
            if (n6 > this.t) {
                n5 = this.u;
            }
            final boolean l;
            u2 = ((l = this.l) ? 1 : 0);
        }
        if (c == 0) {
            if (n6 != 0) {
                this.l = false;
                System.arraycopy(super.a, 0, this.p, 0, this.o);
                this.x = 0;
            }
            u2 = 16384 * (int)n / super.f;
        }
        final int n7 = u2;
        final int n8 = 16383;
        int n9 = 0;
        final int n10 = 262144 / this.t * 20480 / (n7 + 4096);
        int x = 0;
        int n11 = 0;
        int n13;
        int n12;
        int n15;
        int n14;
        int n16;
        int n19;
        int n20;
        int n21;
        while (true) {
            while (true) {
                Label_0170: {
                    if (c == 0) {
                        break Label_0170;
                    }
                    int j = b.j;
                    b.j = ++j;
                    this.y[n11] = 0;
                    ++n11;
                }
                if (n11 < 4000) {
                    continue;
                }
                break;
            }
            n12 = (n13 = n8 * (-n7 + 18432) / 18432);
            n14 = (n15 = n12 * 32 / n5);
            n16 = 16000 / n5;
            final int n17 = -n14 * (n7 - 1000) / n16;
            final int n18 = -n15 * (n7 - 1000) / n16;
            n19 = 400;
            n20 = n18 + n15 * n19 / n16;
            n21 = n17 - n14 * n19 / n16;
            if (c != 0) {
                continue;
            }
            break;
        }
        final int n22 = n21;
        final int n23 = n12;
        int n24 = 0;
        Label_0340: {
            if (c == 0) {
                if (n22 >= n23) {
                    n21 = 2 * n12 - n21;
                    n14 = -n14;
                }
                final int n25;
                n24 = (n25 = n20);
                if (c != 0) {
                    break Label_0340;
                }
            }
            if (n22 >= n23) {
                n20 = 2 * n13 - n20;
                n15 = -n15;
            }
            n24 = n12;
        }
        final int n27;
        final int n26 = n27 = n24;
        int n29;
        int n28 = n29 = n26 * 32 / n5;
        final int n30 = -n28 * (n7 * 4 / 3 - 3500) / n16;
        int n31 = -n29 * (n7 * 4 / 3 - 3500) / n16 + n29 * n19 / n16;
        final int n33;
        int n32 = n33 = n30 - n28 * n19 / n16;
        final int n34 = n26;
        int n35 = 0;
        Label_0477: {
            if (c == 0) {
                if (n33 >= n34) {
                    n32 = 2 * n26 - n32;
                    n28 = -n28;
                }
                final int n36;
                n35 = (n36 = n31);
                if (c != 0) {
                    break Label_0477;
                }
            }
            if (n33 >= n34) {
                n31 = 2 * n27 - n31;
                n29 = -n29;
            }
            n35 = 0;
        }
        int n37 = n35;
        int n64;
        int n65;
        long n66;
        long n67;
        long n68;
        long n69;
        long n70;
        int t;
        int n71;
        int n72;
        while (true) {
            while (true) {
                Label_0859: {
                    if (c == 0) {
                        break Label_0859;
                    }
                    n21 += n14;
                    int n40;
                    int n39;
                    final int n38 = n39 = (n40 = n21);
                    int n43;
                    int n42;
                    final int n41 = n42 = (n43 = n12);
                    if (c == 0) {
                        if (n38 >= n41) {
                            n21 = n12 - 1;
                            n14 = -n14;
                        }
                        n20 += n15;
                        final int n44;
                        n39 = (n44 = (n40 = n20));
                        final int n45;
                        n42 = (n45 = (n43 = n13));
                    }
                    if (c == 0) {
                        if (n38 >= n41) {
                            n20 = n13 - 1;
                            n15 = -n15;
                        }
                        n32 += n28;
                        n40 = (n39 = n32);
                        n43 = (n42 = n26);
                    }
                    int n46 = 0;
                    Label_0605: {
                        if (c == 0) {
                            if (n39 >= n42) {
                                n32 = n26 - 1;
                                n28 = -n28;
                            }
                            n31 += n29;
                            n46 = (n40 = n31);
                            if (c != 0) {
                                break Label_0605;
                            }
                            n43 = n27;
                        }
                        if (n40 >= n43) {
                            n31 = n27 - 1;
                            n29 = -n29;
                        }
                        n46 = 0;
                    }
                    int n47 = n46;
                    int n48 = 0;
                    int n53;
                    int n52;
                    int n51;
                    int n50;
                    final int n49 = n50 = (n51 = (n52 = (n53 = n20)));
                    if (c == 0) {
                        if (n49 > 0) {
                            n47 += n20;
                        }
                        final int n54;
                        n50 = (n54 = (n51 = (n52 = (n53 = n31))));
                    }
                    if (c == 0) {
                        if (n49 > 0) {
                            n47 += n31;
                        }
                        n51 = (n50 = (n52 = (n53 = n21)));
                    }
                    if (c == 0) {
                        if (n50 > 0) {
                            n48 += n21;
                        }
                        n52 = (n51 = (n53 = n32));
                    }
                    if (c == 0) {
                        if (n51 > 0) {
                            n48 += n32;
                        }
                        n53 = (n52 = n48);
                    }
                    final int n55 = 16383;
                    int n60 = 0;
                    int n59 = 0;
                    int n58 = 0;
                    int n57 = 0;
                    int n56 = 0;
                    Label_0716: {
                        if (c == 0) {
                            if (n52 > n55) {
                                n48 = 16383;
                            }
                            final boolean b;
                            n56 = (n53 = ((b = ((n57 = (n58 = (n59 = (n60 = n47)))) != 0)) ? 1 : 0));
                            if (c != 0) {
                                break Label_0716;
                            }
                        }
                        if (n53 > n55) {
                            n47 = 16383;
                        }
                        n56 = (n57 = (n58 = (n59 = (n60 = n48))));
                    }
                    final int n61;
                    Label_0773: {
                        if (c == 0) {
                            if (n56 > 0) {
                                n61 = (n57 = (n58 = (n59 = (n60 = n47))));
                                if (c != 0) {
                                    break Label_0773;
                                }
                                if (n61 > 0) {
                                    final int n62 = n48;
                                    final int n63 = n47;
                                    if (c == 0 && n62 > n63) {
                                        n48 -= n47;
                                        n47 = 0;
                                        if (c != 0) {
                                            goto Label_0761;
                                        }
                                    }
                                    else {
                                        n47 = n62 - n63;
                                        n48 = 0;
                                    }
                                }
                            }
                            final boolean b;
                            n57 = ((b = ((n58 = (n59 = (n60 = n48))) != 0)) ? 1 : 0);
                        }
                    }
                    Label_0830: {
                        Label_0823: {
                            if (c == 0) {
                                if (n61 > 0) {
                                    this.y[n37] = n48 / 128;
                                    if (c == 0) {
                                        break Label_0823;
                                    }
                                }
                                n58 = (n57 = (n59 = (n60 = n47)));
                            }
                            if (c != 0) {
                                break Label_0830;
                            }
                            if (n57 > 0) {
                                this.y[n37] = -n47 / 128;
                            }
                        }
                        n59 = (n58 = (n60 = this.y[n37]));
                    }
                    Label_0856: {
                        if (c == 0) {
                            if (n58 == 0) {
                                break Label_0856;
                            }
                            n9 = n37;
                            n60 = (n59 = x);
                        }
                        if (c == 0) {
                            if (n59 != 0) {
                                break Label_0856;
                            }
                            n60 = n37;
                        }
                        x = n60;
                    }
                    ++n37;
                }
                if (n37 < 4000) {
                    continue;
                }
                break;
            }
            n64 = this.u * 1 / 4;
            n65 = this.t * 3 / 4;
            n66 = n5 * (n7 + 300) >> 11;
            n67 = 0L;
            n68 = n66 * 3L / 8L;
            n69 = n66 * 3L / 4L;
            n70 = n66 * 7L / 8L;
            t = this.t;
            n71 = this.t * this.u - this.t;
            n72 = 0;
            n37 = 589824 / this.t;
            if (c != 0) {
                continue;
            }
            break;
        }
        long n73;
        while (true) {
            while (true) {
                Label_0994: {
                    if (c == 0) {
                        break Label_0994;
                    }
                    n37 >>= 1;
                    ++n72;
                }
                if (n37 != 0) {
                    continue;
                }
                break;
            }
            n73 = 0L;
            if (c != 0) {
                continue;
            }
            break;
        }
        while (true) {
            Label_2022: {
                if (c == 0) {
                    break Label_2022;
                }
                int n74 = 0;
                int a;
                int n77;
                int n76;
                final int n75 = n76 = (n77 = (a = lcmp(n73, n67)));
                Label_1114: {
                    Label_1094: {
                        if (c == 0) {
                            if (n75 == 0) {
                                n74 = 1;
                                if (c == 0) {
                                    break Label_1094;
                                }
                            }
                            final int n78;
                            n76 = (n78 = (n77 = (a = lcmp(n73, n68))));
                        }
                        if (c == 0) {
                            if (n75 == 0) {
                                n74 = 3;
                                if (c == 0) {
                                    break Label_1094;
                                }
                            }
                            n77 = (n76 = (a = lcmp(n73, n69)));
                        }
                        if (c == 0) {
                            if (n76 == 0) {
                                n74 = 4;
                                if (c == 0) {
                                    break Label_1094;
                                }
                            }
                            a = (n77 = lcmp(n73, n70));
                        }
                        if (c != 0) {
                            break Label_1114;
                        }
                        if (n77 == 0) {
                            n74 = 5;
                        }
                    }
                    a.a((int)(n73 * 262144L / n66));
                    a = a.a;
                }
                final int n79 = a;
                final int n80 = a.b * 5734 / 8192;
                final int n82;
                final int n81 = n82 = a.a - a.b;
                if (c == 0 && n82 >= 0) {}
                final int n83 = n82;
                int n84 = n81 / 16;
                int n85 = n84 / 91;
                int n87;
                final int n86 = n87 = n85;
                if (c == 0) {
                    if (n86 < 0) {
                        n85 = -n85;
                    }
                    n84 = (n85 + n84 * n84 / 2097152) / 2;
                    final int n88;
                    n87 = (n88 = n84);
                }
                if (c == 0) {
                    if (n86 >= 256) {
                        n84 = 255;
                    }
                    n87 = 0;
                }
                int n89 = n87;
                int n90 = 16384;
                int x2 = this.x;
            Label_2016:
                while (true) {
                    Label_2008: {
                        if (c == 0) {
                            break Label_2008;
                        }
                        final int n91 = n65 + n79 * x2 / 262144;
                        int n93;
                        final int n92 = n93 = n91;
                        if (c == 0) {
                            if (n92 < 0) {
                                break Label_2016;
                            }
                            final int n94;
                            n93 = (n94 = n91);
                        }
                        final int t2 = this.t;
                        if (c == 0) {
                            if (n92 >= t2) {
                                break Label_2016;
                            }
                            n93 = n64;
                        }
                        final int n95 = n93 + t2;
                        int n100;
                        int n99;
                        int n98;
                        int n97;
                        final int n96 = n97 = (n98 = (n99 = (n100 = n95)));
                        if (c == 0) {
                            if (n96 < 0) {
                                break Label_2016;
                            }
                            final int n101;
                            n97 = (n101 = (n98 = (n99 = (n100 = n95))));
                        }
                        if (c == 0) {
                            if (n96 >= this.u) {
                                break Label_2016;
                            }
                            n97 = (n99 = (n100 = n74));
                        }
                        final int n102;
                        final int n103;
                        final int n104;
                        if (c == 0) {
                            if (n97 != 0) {
                                n102 = (n99 = (n100 = n74));
                                if (c == 0) {
                                    Label_1428: {
                                        switch (n102) {
                                            case 1: {
                                                n103 = (n100 = n95);
                                                if (c != 0) {
                                                    break;
                                                }
                                                if (n103 <= n2) {
                                                    break;
                                                }
                                                n2 = n95;
                                                if (c != 0) {
                                                    break Label_1428;
                                                }
                                                break;
                                            }
                                            case 3: {
                                                if (c != 0) {
                                                    break Label_1428;
                                                }
                                                break;
                                            }
                                            case 4: {
                                                n104 = n91;
                                                if (c != 0) {
                                                    break;
                                                }
                                                if (n104 >= n3) {
                                                    break;
                                                }
                                                n3 = n91;
                                                if (c != 0) {
                                                    break Label_1428;
                                                }
                                                break;
                                            }
                                            case 5: {
                                                final int n105 = n91;
                                                final int n106 = n4;
                                                int n107 = 0;
                                                Label_1459: {
                                                    if (c == 0) {
                                                        if (n105 > n106) {
                                                            n4 = n91;
                                                        }
                                                        final int n108;
                                                        n107 = (n108 = n95);
                                                        if (c != 0) {
                                                            break Label_1459;
                                                        }
                                                    }
                                                    if (n105 < n106) {
                                                        u = n95;
                                                    }
                                                    n107 = 0;
                                                }
                                                n74 = n107;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        final int n110;
                        if (c == 0 && n102 == 0) {
                            final int n109 = n91;
                            final int n111;
                            n110 = (n111 = n4);
                            if (c == 0) {
                                if (n109 > n110) {
                                    n4 = n91;
                                    goto Label_1485;
                                }
                                goto Label_1485;
                            }
                        }
                        else {
                            final int n112 = this.t - 1;
                        }
                        final int n114;
                        Label_1531: {
                            if (c == 0) {
                                if (n103 == n110) {
                                    final int n113 = n95;
                                    n114 = u;
                                    if (c != 0) {
                                        break Label_1531;
                                    }
                                    if (n113 < n114) {
                                        u = n95;
                                    }
                                }
                                final int n111 = this.v * (this.u - n95 - 1);
                            }
                        }
                        int n116;
                        final int n115 = n116 = n104 + n114;
                        int n118;
                        final int n117 = n118 = x2;
                        Label_2005: {
                            if (c == 0) {
                                if (n117 < n9) {
                                    int n119 = n115;
                                    if (c == 0) {
                                        int n120 = this.y[x2] * n84;
                                        int n123;
                                        int n122;
                                        final int n121 = n122 = (n123 = n83);
                                        if (c == 0) {
                                            if (n121 != 0) {
                                                n120 = -n120;
                                            }
                                            final int n124;
                                            n122 = (n124 = (n123 = n120));
                                        }
                                        if (c == 0) {
                                            if (n121 == 0) {
                                                this.p[n116++] = super.b[n119++];
                                                if (c == 0) {
                                                    break Label_2005;
                                                }
                                            }
                                            n123 = (n122 = n120);
                                        }
                                        int n132 = 0;
                                        int n133 = 0;
                                        int n134 = 0;
                                        Label_1984: {
                                            if (c == 0) {
                                                if (n122 > 0) {
                                                    final int n125 = n120 * 512;
                                                    final int n126 = 65536 - n120 * 2;
                                                    final int n128;
                                                    int n127 = n128 = n119;
                                                    final int n129 = t;
                                                    if (c == 0) {
                                                        if (n128 > n129) {
                                                            n127 -= n120 >> n72;
                                                        }
                                                        final int n130 = super.b[n127];
                                                    }
                                                    final int n131 = n128 & n129;
                                                    n132 = (n126 * (n131 >> 16) + n125) / 65536;
                                                    n133 = (n126 * (n131 >> 8 & 0xFF) + n125) / 65536;
                                                    n134 = (n126 * (n131 & 0xFF) + n125) / 65536;
                                                    if (c == 0) {
                                                        break Label_1984;
                                                    }
                                                }
                                                n123 = n119;
                                            }
                                            final int n136;
                                            int n135 = n136 = n123;
                                            final int n137 = n71;
                                            if (c == 0 && n136 < n137) {
                                                n135 -= n120 >> n72;
                                                goto Label_1921;
                                            }
                                            final int n138 = n136 + n137;
                                            final int n139 = super.b[n135] & 0xFFFFFF;
                                            n132 = n138 * (n139 >> 16) / 65536;
                                            n133 = n138 * (n139 >> 8 & 0xFF) / 65536;
                                            n134 = n138 * (n139 & 0xFF) / 65536;
                                        }
                                        this.p[n115] = n134 + (n133 << 8) + (n132 << 16);
                                        break Label_2005;
                                    }
                                }
                                final int n140;
                                n118 = (n140 = n90);
                            }
                            if (c == 0) {
                                if (n117 <= 0) {
                                    break Label_2016;
                                }
                                n118 = (super.a[n115] & 0xFFFFFF);
                            }
                            final int n141 = n118;
                            final int n142 = super.b[n115] & 0xFFFFFF;
                            this.p[n115] = (n89 * (n141 & 0xFF) + n90 * (n142 & 0xFF)) / 16384 + ((n89 * (n141 >> 8 & 0xFF) + n90 * (n142 >> 8 & 0xFF)) / 16384 << 8) + ((n89 * (n141 >> 16) + n90 * (n142 >> 16)) / 16384 << 16);
                            n89 += n10;
                            n90 -= n10;
                        }
                        ++x2;
                    }
                    if (x2 < 4000) {
                        continue;
                    }
                    break;
                }
                ++n73;
            }
            if (n73 >= n66) {
                this.x = x;
                return this.p;
            }
            continue;
        }
    }
}
