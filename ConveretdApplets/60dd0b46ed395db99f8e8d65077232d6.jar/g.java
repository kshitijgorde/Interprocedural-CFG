// 
// Decompiled by Procyon v0.5.30
// 

public class g extends b
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
    boolean y;
    boolean z;
    int A;
    
    public g(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4) {
        super(array, array2, n, n2, n3, n4);
        this.k = 0L;
        this.l = true;
        this.o = super.c * super.d;
        this.p = new int[this.o];
        this.t = super.c;
        this.u = super.d;
        this.v = this.t;
        this.A = 0;
    }
    
    public int[] a(final long n) {
        final int c = a.c;
        int l;
        final int n2 = l = (this.l ? 1 : 0);
        if (c == 0) {
            if (n2 != 0) {
                this.l = false;
                this.x = this.t / 2 - 1;
                this.y = false;
                this.z = false;
                System.arraycopy(super.a, 0, this.p, 0, this.o);
            }
            l = 32;
        }
        final int n3 = l;
        int n4 = (65536 - n3) * (int)n / super.f + n3;
        int n5 = 0;
        int y;
        final int n7;
        final int n6 = n7 = (y = (this.y ? 1 : 0));
        final int n8;
        final int z;
        Label_0120: {
            if (c == 0) {
                if (n6 == 0) {
                    n8 = (y = n4);
                    if (c != 0) {
                        break Label_0120;
                    }
                    if (n8 >= 16384) {
                        n4 = 16383;
                    }
                }
                z = (this.z ? 1 : 0);
            }
        }
        int n11 = 0;
        Label_0174: {
            Label_0172: {
                final int n10;
                if (c == 0 && n6 == 0) {
                    final int n9 = n4;
                    n10 = 32768;
                    if (c == 0) {
                        if (n9 >= n10) {
                            this.z = true;
                            n4 = 32767;
                            goto Label_0150;
                        }
                        goto Label_0150;
                    }
                }
                else if (c != 0) {
                    break Label_0172;
                }
                if (n8 < n10) {
                    n11 = 1;
                    if (c == 0) {
                        break Label_0174;
                    }
                }
            }
            n11 = z;
        }
        int a;
        int n13;
        final int n12 = n13 = (a = this.A);
        if (c == 0) {
            if (n12 != 0) {
                n11 = -n11;
            }
            final int n14;
            n13 = (n14 = (a = n4));
        }
        final int n16;
        final int n15 = n16 = 65536;
        if (c == 0) {
            if (n12 >= n15) {
                n4 = 65535;
            }
            n13 = n4;
        }
        final int n17;
        final int n18;
        if (c == 0) {
            if (n13 >= n15) {
                n5 = 1;
                n11 = -1;
                n4 = (n17 = (n4 & 0x7FFF) / 2);
                n18 = 16382;
                if (c == 0) {
                    if (n17 >= n18) {
                        n4 = 16382;
                    }
                }
            }
        }
        int n19 = n17 & n18;
        int n21;
        final int n20 = n21 = n19;
        if (c == 0) {
            if (n20 == 0) {
                n19 = 1;
            }
            final int n22;
            n21 = (n22 = n5);
        }
        int n23 = 0;
        Label_0299: {
            if (c == 0) {
                if (n20 != 0) {
                    n23 = 2;
                    if (c == 0) {
                        break Label_0299;
                    }
                }
                n21 = 1;
            }
            n23 = n21;
        }
        int x = 0;
        int n25 = 0;
        int n24;
        int n26;
        int n27;
        int n28;
        int n29;
        int n30;
        int n31;
        int n33;
        int n32;
        int n34 = 0;
        int n35 = 0;
        int a2;
        int b;
        int n36;
        int n37;
        int n38;
        int n39;
        int n40;
        int n41;
        int n42;
        int n45;
        int n44;
        int n43;
        int n48;
        int n47;
        int n46;
        int n49;
        int n50;
        int n51;
        int n52;
        int n53;
        int n55;
        int n54;
        int n56;
        int n57;
        int n58;
        int n59;
        int n60;
        int n61;
        int n62 = 0;
        int n63;
        int n64;
        int u;
        int n65;
        int n66;
        int n68 = 0;
        int n67 = 0;
        int n70 = 0;
        int n69 = 0;
        int n71;
        int n72;
        int n73;
        int n74;
        int n75;
        int n76;
        int n77;
        int n78;
        int u2;
        int u3;
        int n79;
        int n80;
        int x2;
        int x3;
        int x4;
        int n81;
        int n83;
        int n82;
        int n84;
        int n85;
        int n86;
        int n87;
        g g;
        Label_0389_Outer:Label_1211_Outer:
        while (true) {
            Label_1351: {
                if (c == 0) {
                    break Label_1351;
                }
                n24 = n25;
                x = 0;
                n26 = -1;
                n27 = 229376;
                n28 = n27 * 20 / this.t;
                n29 = n5;
                if (c == 0 && n29 != 0) {
                    n28 = n28 * 1024 / (1024 - n19 / 16 + 1);
                    goto Label_0370;
                }
                n30 = n29;
                n31 = 0;
                while (true) {
                    while (true) {
                        Label_1144: {
                            if (c == 0) {
                                break Label_1144;
                            }
                            n32 = (n33 = n5);
                            Label_0433: {
                                if (c == 0) {
                                    if (n34 != 0) {
                                        n35 = 16 * n31 * 16384 / this.t;
                                        if (c == 0) {
                                            break Label_0433;
                                        }
                                    }
                                    n32 = 16 * n31 * n19 / this.t;
                                }
                                n35 = n32;
                            }
                            a.a(n35);
                            a2 = a.a;
                            b = a.b;
                            n36 = this.t * 41721 / n19;
                            n37 = n5;
                            if (c == 0 && n37 != 0) {
                                n36 = this.t * 41721 / 16384 * (16384 - n19) / 16384;
                                goto Label_0500;
                            }
                            n38 = n37;
                            n39 = n5;
                            if (c == 0 && n39 != 0) {
                                n38 = 16384 - 4915 * (16384 - n19) / 16384;
                                goto Label_0535;
                            }
                            n40 = n39;
                            n41 = n36 / 16 * a2 / 262144;
                            n42 = n38 * n40 / 16384;
                            n43 = (n44 = (n45 = n41));
                            n46 = (n47 = (n48 = n26));
                            Label_1141: {
                                if (c == 0) {
                                    if (n43 == n46) {
                                        break Label_1141;
                                    }
                                    n44 = (n49 = (n45 = n41));
                                    n47 = (n50 = (n48 = n26 + 1));
                                }
                                Label_0647: {
                                    Label_0635: {
                                        if (c == 0) {
                                            if (n43 > n46) {
                                                n41 = n26 + 1;
                                                if (c == 0) {
                                                    break Label_0635;
                                                }
                                            }
                                            n45 = (n44 = n41);
                                            n48 = (n47 = n26 - 1);
                                        }
                                        if (c != 0) {
                                            break Label_0647;
                                        }
                                        if (n44 < n47) {
                                            n41 = n26 - 1;
                                        }
                                    }
                                    n26 = n41;
                                    n45 = this.u - 1;
                                    n48 = n42;
                                }
                                n51 = n45 - n48;
                                n52 = n5;
                                if (c == 0) {
                                    if (n52 != 0) {
                                        n51 -= this.u * n19 / 32768;
                                    }
                                    n53 = this.t / 2 - 1 - n31 * n11;
                                }
                                n54 = (n55 = n52);
                                n56 = this.t / 2 - 1 - n41 * n11;
                                n57 = this.t / 2 - 1 - n41 * n11;
                                n58 = x;
                                if (c == 0 && n58 <= n41) {
                                    x = n41;
                                    if (c != 0) {
                                        goto Label_0748;
                                    }
                                }
                                else {
                                    n24 = n58;
                                }
                                n59 = 0;
                                n60 = n11;
                                n61 = -1;
                                Label_0810: {
                                    if (c == 0) {
                                        if (n60 == n61) {
                                            n62 = n24;
                                            if (c != 0) {
                                                break Label_0810;
                                            }
                                            if (n62 != 0) {
                                                n30 = (n63 = n30 + n28);
                                                n64 = n27;
                                                if (c != 0) {
                                                    break Label_0810;
                                                }
                                                if (n63 >= n64) {
                                                    n30 = n27 - 1;
                                                    n28 = -n28;
                                                }
                                            }
                                        }
                                        u = this.u;
                                    }
                                }
                                n65 = n62;
                                while (true) {
                                    Label_1136: {
                                        if (c == 0) {
                                            break Label_1136;
                                        }
                                        n66 = n54;
                                        Label_1113: {
                                            Label_0883: {
                                                if (c == 0 && n66 < this.o) {
                                                    n67 = (n68 = n65);
                                                    n69 = (n70 = n51);
                                                    if (c != 0) {
                                                        break Label_0883;
                                                    }
                                                    if (n67 > n69) {
                                                        goto Label_0844;
                                                    }
                                                }
                                                else {
                                                    if (n66 != 0) {
                                                        ++n56;
                                                        if (c == 0) {
                                                            break Label_1113;
                                                        }
                                                    }
                                                    this.p[n56++] = super.b[n57];
                                                    if (c == 0) {
                                                        break Label_1113;
                                                    }
                                                }
                                                n68 = (n71 = n30);
                                                n70 = (n72 = 10);
                                            }
                                            Label_1026: {
                                                Label_1006: {
                                                    if (c == 0) {
                                                        if (n67 <= n69) {
                                                            break Label_1006;
                                                        }
                                                        n68 = n30;
                                                        n70 = 256;
                                                    }
                                                    n73 = n68 * n70;
                                                    n74 = 262144 - n30;
                                                    n75 = (super.a[n54++] & 0xFFFFFF);
                                                    this.p[n56++] = (n74 * (n75 & 0xFF) + n73) / 262144 + ((n74 * (n75 >> 8 & 0xFF) + n73) / 262144 << 8) + ((n74 * (n75 >> 16) + n73) / 262144 << 16);
                                                    if (c == 0) {
                                                        break Label_1026;
                                                    }
                                                }
                                                this.p[n56++] = super.a[n54++];
                                            }
                                            n76 = n5;
                                            if (c == 0) {
                                                if (n76 != 0) {
                                                    n77 = 16384 - n19;
                                                    u2 = (n78 = ++n59 * 16384 / n77 * 16384 / n77);
                                                    u3 = this.u;
                                                    if (c == 0) {
                                                        if (n78 > u3) {
                                                            u2 = this.u;
                                                        }
                                                        n79 = this.v * u2;
                                                    }
                                                    n54 = n78 + u3;
                                                    if (c == 0) {
                                                        break Label_1113;
                                                    }
                                                }
                                                n80 = n54 + (this.v - 1);
                                            }
                                            n54 = n76;
                                        }
                                        n56 += this.v - 1;
                                        n57 += this.v;
                                        --n65;
                                    }
                                    if (n65 >= 0) {
                                        continue;
                                    }
                                    break;
                                }
                            }
                            ++n31;
                        }
                        if (n31 < this.t / 2) {
                            continue Label_0389_Outer;
                        }
                        break;
                    }
                    n34 = (n32 = (x2 = this.x));
                    if (c != 0) {
                        continue;
                    }
                    break;
                }
                if (c == 0) {
                    while (true) {
                        Label_1334: {
                            if (n34 < 0) {
                                break Label_1334;
                            }
                            x3 = this.x;
                            if (c != 0) {
                                break Label_1351;
                            }
                            if (x3 == x) {
                                break Label_1334;
                            }
                            n31 = x + 1;
                            while (true) {
                                Label_1323: {
                                    if (c == 0) {
                                        break Label_1323;
                                    }
                                    x4 = this.t / 2 - 1 - n31 * n11;
                                    n81 = x4;
                                    n82 = (n83 = n81);
                                    if (c == 0) {
                                        if (n82 < 0) {
                                            n81 = 0;
                                        }
                                        n83 = (n84 = n81);
                                    }
                                    if (c == 0) {
                                        if (n82 > this.t - 1) {
                                            n81 = this.t - 1;
                                        }
                                        n83 = n81;
                                    }
                                    n85 = n83;
                                    n86 = n81;
                                    n87 = 0;
                                    while (true) {
                                        Label_1311: {
                                            if (c == 0) {
                                                break Label_1311;
                                            }
                                            this.p[n85++] = super.b[n86++];
                                            n86 += this.v - 1;
                                            n85 += this.v - 1;
                                            ++n87;
                                        }
                                        if (n87 < this.u) {
                                            continue;
                                        }
                                        break;
                                    }
                                    ++n31;
                                }
                                if (n31 <= this.x + 1) {
                                    continue Label_1211_Outer;
                                }
                                break;
                            }
                        }
                        n11 = -n11;
                        --n23;
                        x4 = this.x;
                        if (c != 0) {
                            continue;
                        }
                        break;
                    }
                }
            }
            if (n23 <= 0) {
                g = this;
                if (c == 0) {
                    this.x = x;
                    n25 = n19;
                    if (c != 0) {
                        continue;
                    }
                    if (n25 == 16383) {
                        g = this;
                        if (c != 0) {
                            return g.p;
                        }
                        if (!this.y) {
                            this.y = true;
                            this.x = this.t / 2;
                        }
                    }
                    g = this;
                }
                return g.p;
            }
            continue;
        }
    }
}
