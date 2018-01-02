// 
// Decompiled by Procyon v0.5.30
// 

final class ObjectManager
{
    private static int anInt123;
    private final int[] anIntArray124;
    private final int[] anIntArray125;
    private final int[] anIntArray126;
    private final int[] anIntArray127;
    private final int[] anIntArray128;
    private final int[][][] anIntArrayArrayArray129;
    private final byte[][][] aByteArrayArrayArray130;
    static int anInt131;
    private static int anInt133;
    private final byte[][][] aByteArrayArrayArray134;
    private final int[][][] anIntArrayArrayArray135;
    private final byte[][][] aByteArrayArrayArray136;
    private static final int[] anIntArray137;
    private static final int anInt138 = 323;
    private final int[][] anIntArrayArray139;
    private static final int[] anIntArray140;
    private final byte[][][] aByteArrayArrayArray142;
    private static final int[] anIntArray144;
    static int anInt145;
    private final int anInt146;
    private final int anInt147;
    private final byte[][][] aByteArrayArrayArray148;
    private final byte[][][] aByteArrayArrayArray149;
    static boolean lowMem;
    private static final int[] anIntArray152;
    
    public ObjectManager(final byte[][][] aByteArrayArrayArray149, final int[][][] anIntArrayArrayArray129) {
        ObjectManager.anInt145 = 99;
        this.anInt146 = 104;
        this.anInt147 = 104;
        this.anIntArrayArrayArray129 = anIntArrayArrayArray129;
        this.aByteArrayArrayArray149 = aByteArrayArrayArray149;
        this.aByteArrayArrayArray142 = new byte[4][this.anInt146][this.anInt147];
        this.aByteArrayArrayArray130 = new byte[4][this.anInt146][this.anInt147];
        this.aByteArrayArrayArray136 = new byte[4][this.anInt146][this.anInt147];
        this.aByteArrayArrayArray148 = new byte[4][this.anInt146][this.anInt147];
        this.anIntArrayArrayArray135 = new int[4][this.anInt146 + 1][this.anInt147 + 1];
        this.aByteArrayArrayArray134 = new byte[4][this.anInt146 + 1][this.anInt147 + 1];
        this.anIntArrayArray139 = new int[this.anInt146 + 1][this.anInt147 + 1];
        this.anIntArray124 = new int[this.anInt147];
        this.anIntArray125 = new int[this.anInt147];
        this.anIntArray126 = new int[this.anInt147];
        this.anIntArray127 = new int[this.anInt147];
        this.anIntArray128 = new int[this.anInt147];
    }
    
    private static int method170(final int n, final int n2) {
        final int n3 = n + n2 * 57;
        final int n4 = n3 << 13 ^ n3;
        return (n4 * (n4 * n4 * 15731 + 789221) + 1376312589 & Integer.MAX_VALUE) >> 19 & 0xFF;
    }
    
    public final void method171(final Class11[] array, final WorldController worldController) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 104; ++j) {
                for (int k = 0; k < 104; ++k) {
                    if ((this.aByteArrayArrayArray149[i][j][k] & 0x1) == 0x1) {
                        int n = i;
                        if ((this.aByteArrayArrayArray149[1][j][k] & 0x2) == 0x2) {
                            --n;
                        }
                        if (n >= 0) {
                            array[n].method213(k, j);
                        }
                    }
                }
            }
        }
        ObjectManager.anInt123 += (int)(Math.random() * 5.0) - 2;
        if (ObjectManager.anInt123 < -8) {
            ObjectManager.anInt123 = -8;
        }
        if (ObjectManager.anInt123 > 8) {
            ObjectManager.anInt123 = 8;
        }
        ObjectManager.anInt133 += (int)(Math.random() * 5.0) - 2;
        if (ObjectManager.anInt133 < -16) {
            ObjectManager.anInt133 = -16;
        }
        if (ObjectManager.anInt133 > 16) {
            ObjectManager.anInt133 = 16;
        }
        for (int l = 0; l < 4; ++l) {
            final byte[][] array2 = this.aByteArrayArrayArray134[l];
            final int n2 = 96;
            final int n3 = 768;
            final int n4 = -50;
            final int n5 = -10;
            final int n6 = -50;
            final int n7 = n3 * (int)Math.sqrt(n4 * n4 + n5 * n5 + n6 * n6) >> 8;
            for (int n8 = 1; n8 < this.anInt147 - 1; ++n8) {
                for (int n9 = 1; n9 < this.anInt146 - 1; ++n9) {
                    final int n10 = this.anIntArrayArrayArray129[l][n9 + 1][n8] - this.anIntArrayArrayArray129[l][n9 - 1][n8];
                    final int n11 = this.anIntArrayArrayArray129[l][n9][n8 + 1] - this.anIntArrayArrayArray129[l][n9][n8 - 1];
                    final int n12 = (int)Math.sqrt(n10 * n10 + 65536 + n11 * n11);
                    this.anIntArrayArray139[n9][n8] = n2 + (n4 * ((n10 << 8) / n12) + n5 * (65536 / n12) + n6 * ((n11 << 8) / n12)) / n7 - ((array2[n9 - 1][n8] >> 2) + (array2[n9 + 1][n8] >> 3) + (array2[n9][n8 - 1] >> 2) + (array2[n9][n8 + 1] >> 3) + (array2[n9][n8] >> 1));
                }
            }
            for (int n13 = 0; n13 < this.anInt147; ++n13) {
                this.anIntArray124[n13] = 0;
                this.anIntArray125[n13] = 0;
                this.anIntArray126[n13] = 0;
                this.anIntArray127[n13] = 0;
                this.anIntArray128[n13] = 0;
            }
            for (int n14 = -5; n14 < this.anInt146 + 5; ++n14) {
                for (int n15 = 0; n15 < this.anInt147; ++n15) {
                    final int n16 = n14 + 5;
                    if (n16 >= 0 && n16 < this.anInt146) {
                        final int n17 = this.aByteArrayArrayArray142[l][n16][n15] & 0xFF;
                        if (n17 > 0) {
                            final Flo flo = Flo.cache[n17 - 1];
                            final int[] anIntArray124 = this.anIntArray124;
                            final int n18 = n15;
                            anIntArray124[n18] += flo.anInt397;
                            final int[] anIntArray125 = this.anIntArray125;
                            final int n19 = n15;
                            anIntArray125[n19] += flo.anInt395;
                            final int[] anIntArray126 = this.anIntArray126;
                            final int n20 = n15;
                            anIntArray126[n20] += flo.anInt396;
                            final int[] anIntArray127 = this.anIntArray127;
                            final int n21 = n15;
                            anIntArray127[n21] += flo.anInt398;
                            final int[] anIntArray128 = this.anIntArray128;
                            final int n22 = n15;
                            ++anIntArray128[n22];
                        }
                    }
                    final int n23 = n14 - 5;
                    if (n23 >= 0 && n23 < this.anInt146) {
                        final int n24 = this.aByteArrayArrayArray142[l][n23][n15] & 0xFF;
                        if (n24 > 0) {
                            final Flo flo2 = Flo.cache[n24 - 1];
                            final int[] anIntArray129 = this.anIntArray124;
                            final int n25 = n15;
                            anIntArray129[n25] -= flo2.anInt397;
                            final int[] anIntArray130 = this.anIntArray125;
                            final int n26 = n15;
                            anIntArray130[n26] -= flo2.anInt395;
                            final int[] anIntArray131 = this.anIntArray126;
                            final int n27 = n15;
                            anIntArray131[n27] -= flo2.anInt396;
                            final int[] anIntArray132 = this.anIntArray127;
                            final int n28 = n15;
                            anIntArray132[n28] -= flo2.anInt398;
                            final int[] anIntArray133 = this.anIntArray128;
                            final int n29 = n15;
                            --anIntArray133[n29];
                        }
                    }
                }
                if (n14 >= 1 && n14 < this.anInt146 - 1) {
                    int n30 = 0;
                    int n31 = 0;
                    int n32 = 0;
                    int n33 = 0;
                    int n34 = 0;
                    for (int n35 = -5; n35 < this.anInt147 + 5; ++n35) {
                        final int n36 = n35 + 5;
                        if (n36 >= 0 && n36 < this.anInt147) {
                            n30 += this.anIntArray124[n36];
                            n31 += this.anIntArray125[n36];
                            n32 += this.anIntArray126[n36];
                            n33 += this.anIntArray127[n36];
                            n34 += this.anIntArray128[n36];
                        }
                        final int n37 = n35 - 5;
                        if (n37 >= 0 && n37 < this.anInt147) {
                            n30 -= this.anIntArray124[n37];
                            n31 -= this.anIntArray125[n37];
                            n32 -= this.anIntArray126[n37];
                            n33 -= this.anIntArray127[n37];
                            n34 -= this.anIntArray128[n37];
                        }
                        if (n35 >= 1 && n35 < this.anInt147 - 1 && (!ObjectManager.lowMem || (this.aByteArrayArrayArray149[0][n14][n35] & 0x2) != 0x0 || ((this.aByteArrayArrayArray149[l][n14][n35] & 0x10) == 0x0 && this.method182(n35, l, n14) == ObjectManager.anInt131))) {
                            if (l < ObjectManager.anInt145) {
                                ObjectManager.anInt145 = l;
                            }
                            final int n38 = this.aByteArrayArrayArray142[l][n14][n35] & 0xFF;
                            final int n39 = this.aByteArrayArrayArray130[l][n14][n35] & 0xFF;
                            if (n38 > 0 || n39 > 0) {
                                final int n40 = this.anIntArrayArrayArray129[l][n14][n35];
                                final int n41 = this.anIntArrayArrayArray129[l][n14 + 1][n35];
                                final int n42 = this.anIntArrayArrayArray129[l][n14 + 1][n35 + 1];
                                final int n43 = this.anIntArrayArrayArray129[l][n14][n35 + 1];
                                final int n44 = this.anIntArrayArray139[n14][n35];
                                final int n45 = this.anIntArrayArray139[n14 + 1][n35];
                                final int n46 = this.anIntArrayArray139[n14 + 1][n35 + 1];
                                final int n47 = this.anIntArrayArray139[n14][n35 + 1];
                                int method177 = -1;
                                int method178 = -1;
                                if (n38 > 0) {
                                    final int n48 = n30 * 256 / n33;
                                    final int n49 = n31 / n34;
                                    final int n50 = n32 / n34;
                                    method177 = this.method177(n48, n49, n50);
                                    final int n51 = n48 + ObjectManager.anInt123 & 0xFF;
                                    int n52 = n50 + ObjectManager.anInt133;
                                    if (n52 < 0) {
                                        n52 = 0;
                                    }
                                    else if (n52 > 255) {
                                        n52 = 255;
                                    }
                                    method178 = this.method177(n51, n49, n52);
                                }
                                if (l > 0) {
                                    boolean b = true;
                                    if (n38 == 0 && this.aByteArrayArrayArray136[l][n14][n35] != 0) {
                                        b = false;
                                    }
                                    if (n39 > 0 && !Flo.cache[n39 - 1].aBoolean393) {
                                        b = false;
                                    }
                                    if (b && n40 == n41 && n40 == n42 && n40 == n43) {
                                        final int[] array3 = this.anIntArrayArrayArray135[l][n14];
                                        final int n53 = n35;
                                        array3[n53] |= 0x924;
                                    }
                                }
                                int n54 = 0;
                                if (method177 != -1) {
                                    n54 = Texture.anIntArray1482[method187(method178, 96)];
                                }
                                if (n39 == 0) {
                                    worldController.method279(l, n14, n35, 0, 0, -1, n40, n41, n42, n43, method187(method177, n44), method187(method177, n45), method187(method177, n46), method187(method177, n47), 0, 0, 0, 0, n54, 0);
                                }
                                else {
                                    final byte b2 = (byte)(this.aByteArrayArrayArray136[l][n14][n35] + 1);
                                    final byte b3 = this.aByteArrayArrayArray148[l][n14][n35];
                                    final Flo flo3 = Flo.cache[n39 - 1];
                                    int anInt391 = flo3.anInt391;
                                    int method179;
                                    int method180;
                                    if (anInt391 >= 0) {
                                        method179 = Texture.method369(anInt391);
                                        method180 = -1;
                                    }
                                    else if (flo3.anInt390 == 16711935) {
                                        method179 = 0;
                                        method180 = -2;
                                        anInt391 = -1;
                                    }
                                    else {
                                        method180 = this.method177(flo3.anInt394, flo3.anInt395, flo3.anInt396);
                                        method179 = Texture.anIntArray1482[this.method185(flo3.anInt399, 96)];
                                    }
                                    worldController.method279(l, n14, n35, b2, b3, anInt391, n40, n41, n42, n43, method187(method177, n44), method187(method177, n45), method187(method177, n46), method187(method177, n47), this.method185(method180, n44), this.method185(method180, n45), this.method185(method180, n46), this.method185(method180, n47), n54, method179);
                                }
                            }
                        }
                    }
                }
            }
            for (int n55 = 1; n55 < this.anInt147 - 1; ++n55) {
                for (int n56 = 1; n56 < this.anInt146 - 1; ++n56) {
                    worldController.method278(l, n56, n55, this.method182(n55, l, n56));
                }
            }
        }
        worldController.method305(-10, -50, -50);
        for (int n57 = 0; n57 < this.anInt146; ++n57) {
            for (int n58 = 0; n58 < this.anInt147; ++n58) {
                if ((this.aByteArrayArrayArray149[1][n57][n58] & 0x2) == 0x2) {
                    worldController.method276(n58, n57);
                }
            }
        }
        int n59 = 1;
        int n60 = 2;
        int n61 = 4;
        for (int n62 = 0; n62 < 4; ++n62) {
            if (n62 > 0) {
                n59 <<= 3;
                n60 <<= 3;
                n61 <<= 3;
            }
            for (int n63 = 0; n63 <= n62; ++n63) {
                for (int n64 = 0; n64 <= this.anInt147; ++n64) {
                    for (int n65 = 0; n65 <= this.anInt146; ++n65) {
                        if ((this.anIntArrayArrayArray135[n63][n65][n64] & n59) != 0x0) {
                            int n66 = n64;
                            int n67 = n64;
                            int n68 = n63;
                            int n69 = n63;
                            while (n66 > 0 && (this.anIntArrayArrayArray135[n63][n65][n66 - 1] & n59) != 0x0) {
                                --n66;
                            }
                            while (n67 < this.anInt147 && (this.anIntArrayArrayArray135[n63][n65][n67 + 1] & n59) != 0x0) {
                                ++n67;
                            }
                        Label_2174:
                            while (n68 > 0) {
                                for (int n70 = n66; n70 <= n67; ++n70) {
                                    if ((this.anIntArrayArrayArray135[n68 - 1][n65][n70] & n59) == 0x0) {
                                        break Label_2174;
                                    }
                                }
                                --n68;
                            }
                        Label_2227:
                            while (n69 < n62) {
                                for (int n71 = n66; n71 <= n67; ++n71) {
                                    if ((this.anIntArrayArrayArray135[n69 + 1][n65][n71] & n59) == 0x0) {
                                        break Label_2227;
                                    }
                                }
                                ++n69;
                            }
                            if ((n69 + 1 - n68) * (n67 - n66 + 1) >= 8) {
                                WorldController.method277(n62, n65 * 128, this.anIntArrayArrayArray129[n68][n65][n66], n65 * 128, n67 * 128 + 128, this.anIntArrayArrayArray129[n69][n65][n66] - 240, n66 * 128, 1);
                                for (int n72 = n68; n72 <= n69; ++n72) {
                                    for (int n73 = n66; n73 <= n67; ++n73) {
                                        final int[] array4 = this.anIntArrayArrayArray135[n72][n65];
                                        final int n74 = n73;
                                        array4[n74] &= ~n59;
                                    }
                                }
                            }
                        }
                        if ((this.anIntArrayArrayArray135[n63][n65][n64] & n60) != 0x0) {
                            int n75 = n65;
                            int n76 = n65;
                            int n77 = n63;
                            int n78 = n63;
                            while (n75 > 0 && (this.anIntArrayArrayArray135[n63][n75 - 1][n64] & n60) != 0x0) {
                                --n75;
                            }
                            while (n76 < this.anInt146 && (this.anIntArrayArrayArray135[n63][n76 + 1][n64] & n60) != 0x0) {
                                ++n76;
                            }
                        Label_2535:
                            while (n77 > 0) {
                                for (int n79 = n75; n79 <= n76; ++n79) {
                                    if ((this.anIntArrayArrayArray135[n77 - 1][n79][n64] & n60) == 0x0) {
                                        break Label_2535;
                                    }
                                }
                                --n77;
                            }
                        Label_2589:
                            while (n78 < n62) {
                                for (int n80 = n75; n80 <= n76; ++n80) {
                                    if ((this.anIntArrayArrayArray135[n78 + 1][n80][n64] & n60) == 0x0) {
                                        break Label_2589;
                                    }
                                }
                                ++n78;
                            }
                            if ((n78 + 1 - n77) * (n76 - n75 + 1) >= 8) {
                                WorldController.method277(n62, n75 * 128, this.anIntArrayArrayArray129[n77][n75][n64], n76 * 128 + 128, n64 * 128, this.anIntArrayArrayArray129[n78][n75][n64] - 240, n64 * 128, 2);
                                for (int n81 = n77; n81 <= n78; ++n81) {
                                    for (int n82 = n75; n82 <= n76; ++n82) {
                                        final int[] array5 = this.anIntArrayArrayArray135[n81][n82];
                                        final int n83 = n64;
                                        array5[n83] &= ~n60;
                                    }
                                }
                            }
                        }
                        if ((this.anIntArrayArrayArray135[n63][n65][n64] & n61) != 0x0) {
                            int n84 = n65;
                            int n85 = n65;
                            int n86 = n64;
                            int n87 = n64;
                            while (n86 > 0 && (this.anIntArrayArrayArray135[n63][n65][n86 - 1] & n61) != 0x0) {
                                --n86;
                            }
                            while (n87 < this.anInt147 && (this.anIntArrayArrayArray135[n63][n65][n87 + 1] & n61) != 0x0) {
                                ++n87;
                            }
                        Label_2898:
                            while (n84 > 0) {
                                for (int n88 = n86; n88 <= n87; ++n88) {
                                    if ((this.anIntArrayArrayArray135[n63][n84 - 1][n88] & n61) == 0x0) {
                                        break Label_2898;
                                    }
                                }
                                --n84;
                            }
                        Label_2954:
                            while (n85 < this.anInt146) {
                                for (int n89 = n86; n89 <= n87; ++n89) {
                                    if ((this.anIntArrayArrayArray135[n63][n85 + 1][n89] & n61) == 0x0) {
                                        break Label_2954;
                                    }
                                }
                                ++n85;
                            }
                            if ((n85 - n84 + 1) * (n87 - n86 + 1) >= 4) {
                                final int n90 = this.anIntArrayArrayArray129[n63][n84][n86];
                                WorldController.method277(n62, n84 * 128, n90, n85 * 128 + 128, n87 * 128 + 128, n90, n86 * 128, 4);
                                for (int n91 = n84; n91 <= n85; ++n91) {
                                    for (int n92 = n86; n92 <= n87; ++n92) {
                                        final int[] array6 = this.anIntArrayArrayArray135[n63][n91];
                                        final int n93 = n92;
                                        array6[n93] &= ~n61;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    private static int method172(final int n, final int n2) {
        int n3 = (int)((method176(n + 45365, n2 + 91923, 4) - 128 + (method176(n + 10294, n2 + 37821, 2) - 128 >> 1) + (method176(n, n2, 1) - 128 >> 2)) * 0.3) + 35;
        if (n3 < 10) {
            n3 = 10;
        }
        else if (n3 > 60) {
            n3 = 60;
        }
        return n3;
    }
    
    public static void method173(final Stream stream, final OnDemandFetcher onDemandFetcher) {
        int n = -1;
        while (true) {
            final int uSmart2 = stream.readUSmart2();
            if (uSmart2 == 0) {
                break;
            }
            n += uSmart2;
            ObjectDef.forID(n).method574(onDemandFetcher);
            while (stream.method422() != 0) {
                stream.readUnsignedByte();
            }
        }
    }
    
    public final void method174(final int n, final int n2, final int n3, final int n4) {
        for (int i = n; i <= n + n2; ++i) {
            for (int j = n4; j <= n4 + n3; ++j) {
                if (j >= 0 && j < this.anInt146 && i >= 0 && i < this.anInt147) {
                    this.aByteArrayArrayArray134[0][j][i] = 127;
                    if (j == n4 && j > 0) {
                        this.anIntArrayArrayArray129[0][j][i] = this.anIntArrayArrayArray129[0][j - 1][i];
                    }
                    if (j == n4 + n3 && j < this.anInt146 - 1) {
                        this.anIntArrayArrayArray129[0][j][i] = this.anIntArrayArrayArray129[0][j + 1][i];
                    }
                    if (i == n && i > 0) {
                        this.anIntArrayArrayArray129[0][j][i] = this.anIntArrayArrayArray129[0][j][i - 1];
                    }
                    if (i == n + n2 && i < this.anInt147 - 1) {
                        this.anIntArrayArrayArray129[0][j][i] = this.anIntArrayArrayArray129[0][j][i + 1];
                    }
                }
            }
        }
    }
    
    private void method175(final int n, final WorldController worldController, final Class11 class11, final int n2, final int anInt145, final int n3, final int n4, final int n5) {
        if (ObjectManager.lowMem && (this.aByteArrayArrayArray149[0][n3][n] & 0x2) == 0x0) {
            if ((this.aByteArrayArrayArray149[anInt145][n3][n] & 0x10) != 0x0) {
                return;
            }
            if (this.method182(n, anInt145, n3) != ObjectManager.anInt131) {
                return;
            }
        }
        if (anInt145 < ObjectManager.anInt145) {
            ObjectManager.anInt145 = anInt145;
        }
        int n6 = this.anIntArrayArrayArray129[anInt145][n3][n];
        int n7 = this.anIntArrayArrayArray129[anInt145][n3 + 1][n];
        int n8 = this.anIntArrayArrayArray129[anInt145][n3 + 1][n + 1];
        int n9 = this.anIntArrayArrayArray129[anInt145][n3][n + 1];
        final int n10 = n6 + n7 + n8 + n9 >> 2;
        final ObjectDef forID = ObjectDef.forID(n4);
        int n11 = n3 + (n << 7) + (n4 << 14) + 1073741824;
        if (!forID.hasActions) {
            n11 -= Integer.MIN_VALUE;
        }
        final byte b = (byte)((n5 << 6) + n2);
        if (n2 == 22) {
            if (ObjectManager.lowMem && !forID.hasActions && !forID.aBoolean736) {
                return;
            }
            Animable method578;
            if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                method578 = forID.method578(22, n5, n6, n7, n8, n9, -1);
            }
            else {
                method578 = new Animable_Sub5(n4, n5, 22, n7, n8, n6, n9, forID.anInt781, true);
            }
            worldController.method280(anInt145, n10, n, method578, b, n11, n3);
            if (forID.aBoolean767 && forID.hasActions && class11 != null) {
                class11.method213(n, n3);
            }
        }
        else {
            if (n2 == 10 || n2 == 11) {
                Animable method579;
                if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                    method579 = forID.method578(10, n5, n6, n7, n8, n9, -1);
                }
                else {
                    method579 = new Animable_Sub5(n4, n5, 10, n7, n8, n6, n9, forID.anInt781, true);
                }
                if (method579 != null) {
                    int n12 = 0;
                    if (n2 == 11) {
                        n12 += 256;
                    }
                    int n13;
                    int n14;
                    if (n5 == 1 || n5 == 3) {
                        n13 = forID.anInt761;
                        n14 = forID.anInt744;
                    }
                    else {
                        n13 = forID.anInt744;
                        n14 = forID.anInt761;
                    }
                    if (worldController.method284(n11, b, n10, n14, method579, n13, anInt145, n12, n, n3) && forID.aBoolean779) {
                        Model method580;
                        if (method579 instanceof Model) {
                            method580 = (Model)method579;
                        }
                        else {
                            method580 = forID.method578(10, n5, n6, n7, n8, n9, -1);
                        }
                        if (method580 != null) {
                            for (int i = 0; i <= n13; ++i) {
                                for (int j = 0; j <= n14; ++j) {
                                    int n15 = method580.anInt1650 / 4;
                                    if (n15 > 30) {
                                        n15 = 30;
                                    }
                                    if (n15 > this.aByteArrayArrayArray134[anInt145][n3 + i][n + j]) {
                                        this.aByteArrayArrayArray134[anInt145][n3 + i][n + j] = (byte)n15;
                                    }
                                }
                            }
                        }
                    }
                }
                if (forID.aBoolean767 && class11 != null) {
                    class11.method212(forID.aBoolean757, forID.anInt744, forID.anInt761, n3, n, n5);
                }
                return;
            }
            if (n2 >= 12) {
                Animable method581;
                if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                    method581 = forID.method578(n2, n5, n6, n7, n8, n9, -1);
                }
                else {
                    method581 = new Animable_Sub5(n4, n5, n2, n7, n8, n6, n9, forID.anInt781, true);
                }
                worldController.method284(n11, b, n10, 1, method581, 1, anInt145, 0, n, n3);
                if (n2 >= 12 && n2 <= 17 && n2 != 13 && anInt145 > 0) {
                    final int[] array = this.anIntArrayArrayArray135[anInt145][n3];
                    array[n] |= 0x924;
                }
                if (forID.aBoolean767 && class11 != null) {
                    class11.method212(forID.aBoolean757, forID.anInt744, forID.anInt761, n3, n, n5);
                }
                return;
            }
            if (n2 == 0) {
                Animable method582;
                if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                    method582 = forID.method578(0, n5, n6, n7, n8, n9, -1);
                }
                else {
                    method582 = new Animable_Sub5(n4, n5, 0, n7, n8, n6, n9, forID.anInt781, true);
                }
                worldController.method282(ObjectManager.anIntArray152[n5], method582, n11, n, b, n3, null, n10, 0, anInt145);
                if (n5 == 0) {
                    if (forID.aBoolean779) {
                        this.aByteArrayArrayArray134[anInt145][n3][n] = 50;
                        this.aByteArrayArrayArray134[anInt145][n3][n + 1] = 50;
                    }
                    if (forID.aBoolean764) {
                        final int[] array2 = this.anIntArrayArrayArray135[anInt145][n3];
                        array2[n] |= 0x249;
                    }
                }
                else if (n5 == 1) {
                    if (forID.aBoolean779) {
                        this.aByteArrayArrayArray134[anInt145][n3][n + 1] = 50;
                        this.aByteArrayArrayArray134[anInt145][n3 + 1][n + 1] = 50;
                    }
                    if (forID.aBoolean764) {
                        final int[] array3 = this.anIntArrayArrayArray135[anInt145][n3];
                        final int n16 = n + 1;
                        array3[n16] |= 0x492;
                    }
                }
                else if (n5 == 2) {
                    if (forID.aBoolean779) {
                        this.aByteArrayArrayArray134[anInt145][n3 + 1][n] = 50;
                        this.aByteArrayArrayArray134[anInt145][n3 + 1][n + 1] = 50;
                    }
                    if (forID.aBoolean764) {
                        final int[] array4 = this.anIntArrayArrayArray135[anInt145][n3 + 1];
                        array4[n] |= 0x249;
                    }
                }
                else if (n5 == 3) {
                    if (forID.aBoolean779) {
                        this.aByteArrayArrayArray134[anInt145][n3][n] = 50;
                        this.aByteArrayArrayArray134[anInt145][n3 + 1][n] = 50;
                    }
                    if (forID.aBoolean764) {
                        final int[] array5 = this.anIntArrayArrayArray135[anInt145][n3];
                        array5[n] |= 0x492;
                    }
                }
                if (forID.aBoolean767 && class11 != null) {
                    class11.method211(n, n5, n3, n2, forID.aBoolean757);
                }
                if (forID.anInt775 != 16) {
                    worldController.method290(n, forID.anInt775, n3, anInt145);
                }
                return;
            }
            if (n2 == 1) {
                Animable method583;
                if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                    method583 = forID.method578(1, n5, n6, n7, n8, n9, -1);
                }
                else {
                    method583 = new Animable_Sub5(n4, n5, 1, n7, n8, n6, n9, forID.anInt781, true);
                }
                worldController.method282(ObjectManager.anIntArray140[n5], method583, n11, n, b, n3, null, n10, 0, anInt145);
                if (forID.aBoolean779) {
                    if (n5 == 0) {
                        this.aByteArrayArrayArray134[anInt145][n3][n + 1] = 50;
                    }
                    else if (n5 == 1) {
                        this.aByteArrayArrayArray134[anInt145][n3 + 1][n + 1] = 50;
                    }
                    else if (n5 == 2) {
                        this.aByteArrayArrayArray134[anInt145][n3 + 1][n] = 50;
                    }
                    else if (n5 == 3) {
                        this.aByteArrayArrayArray134[anInt145][n3][n] = 50;
                    }
                }
                if (forID.aBoolean767 && class11 != null) {
                    class11.method211(n, n5, n3, n2, forID.aBoolean757);
                }
                return;
            }
            if (n2 == 2) {
                final int n17 = n5 + 1 & 0x3;
                Animable method584;
                Animable method585;
                if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                    method584 = forID.method578(2, 4 + n5, n6, n7, n8, n9, -1);
                    method585 = forID.method578(2, n17, n6, n7, n8, n9, -1);
                }
                else {
                    method584 = new Animable_Sub5(n4, 4 + n5, 2, n7, n8, n6, n9, forID.anInt781, true);
                    method585 = new Animable_Sub5(n4, n17, 2, n7, n8, n6, n9, forID.anInt781, true);
                }
                worldController.method282(ObjectManager.anIntArray152[n5], method584, n11, n, b, n3, method585, n10, ObjectManager.anIntArray152[n17], anInt145);
                if (forID.aBoolean764) {
                    if (n5 == 0) {
                        final int[] array6 = this.anIntArrayArrayArray135[anInt145][n3];
                        array6[n] |= 0x249;
                        final int[] array7 = this.anIntArrayArrayArray135[anInt145][n3];
                        final int n18 = n + 1;
                        array7[n18] |= 0x492;
                    }
                    else if (n5 == 1) {
                        final int[] array8 = this.anIntArrayArrayArray135[anInt145][n3];
                        final int n19 = n + 1;
                        array8[n19] |= 0x492;
                        final int[] array9 = this.anIntArrayArrayArray135[anInt145][n3 + 1];
                        array9[n] |= 0x249;
                    }
                    else if (n5 == 2) {
                        final int[] array10 = this.anIntArrayArrayArray135[anInt145][n3 + 1];
                        array10[n] |= 0x249;
                        final int[] array11 = this.anIntArrayArrayArray135[anInt145][n3];
                        array11[n] |= 0x492;
                    }
                    else if (n5 == 3) {
                        final int[] array12 = this.anIntArrayArrayArray135[anInt145][n3];
                        array12[n] |= 0x492;
                        final int[] array13 = this.anIntArrayArrayArray135[anInt145][n3];
                        array13[n] |= 0x249;
                    }
                }
                if (forID.aBoolean767 && class11 != null) {
                    class11.method211(n, n5, n3, n2, forID.aBoolean757);
                }
                if (forID.anInt775 != 16) {
                    worldController.method290(n, forID.anInt775, n3, anInt145);
                }
                return;
            }
            if (n2 == 3) {
                Animable method586;
                if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                    method586 = forID.method578(3, n5, n6, n7, n8, n9, -1);
                }
                else {
                    method586 = new Animable_Sub5(n4, n5, 3, n7, n8, n6, n9, forID.anInt781, true);
                }
                worldController.method282(ObjectManager.anIntArray140[n5], method586, n11, n, b, n3, null, n10, 0, anInt145);
                if (forID.aBoolean779) {
                    if (n5 == 0) {
                        this.aByteArrayArrayArray134[anInt145][n3][n + 1] = 50;
                    }
                    else if (n5 == 1) {
                        this.aByteArrayArrayArray134[anInt145][n3 + 1][n + 1] = 50;
                    }
                    else if (n5 == 2) {
                        this.aByteArrayArrayArray134[anInt145][n3 + 1][n] = 50;
                    }
                    else if (n5 == 3) {
                        this.aByteArrayArrayArray134[anInt145][n3][n] = 50;
                    }
                }
                if (forID.aBoolean767 && class11 != null) {
                    class11.method211(n, n5, n3, n2, forID.aBoolean757);
                }
                return;
            }
            if (n2 == 9) {
                Animable method587;
                if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                    method587 = forID.method578(n2, n5, n6, n7, n8, n9, -1);
                }
                else {
                    method587 = new Animable_Sub5(n4, n5, n2, n7, n8, n6, n9, forID.anInt781, true);
                }
                worldController.method284(n11, b, n10, 1, method587, 1, anInt145, 0, n, n3);
                if (forID.aBoolean767 && class11 != null) {
                    class11.method212(forID.aBoolean757, forID.anInt744, forID.anInt761, n3, n, n5);
                }
                return;
            }
            if (forID.aBoolean762) {
                if (n5 == 1) {
                    final int n20 = n9;
                    n9 = n8;
                    n8 = n7;
                    n7 = n6;
                    n6 = n20;
                }
                else if (n5 == 2) {
                    final int n21 = n9;
                    n9 = n7;
                    n7 = n21;
                    final int n22 = n8;
                    n8 = n6;
                    n6 = n22;
                }
                else if (n5 == 3) {
                    final int n23 = n9;
                    n9 = n6;
                    n6 = n7;
                    n7 = n8;
                    n8 = n23;
                }
            }
            if (n2 == 4) {
                Animable method588;
                if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                    method588 = forID.method578(4, 0, n6, n7, n8, n9, -1);
                }
                else {
                    method588 = new Animable_Sub5(n4, 0, 4, n7, n8, n6, n9, forID.anInt781, true);
                }
                worldController.method283(n11, n, n5 * 512, anInt145, 0, n10, method588, n3, b, 0, ObjectManager.anIntArray152[n5]);
                return;
            }
            if (n2 == 5) {
                int anInt146 = 16;
                final int method589 = worldController.method300(anInt145, n3, n);
                if (method589 > 0) {
                    anInt146 = ObjectDef.forID(method589 >> 14 & 0x7FFF).anInt775;
                }
                Animable method590;
                if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                    method590 = forID.method578(4, 0, n6, n7, n8, n9, -1);
                }
                else {
                    method590 = new Animable_Sub5(n4, 0, 4, n7, n8, n6, n9, forID.anInt781, true);
                }
                worldController.method283(n11, n, n5 * 512, anInt145, ObjectManager.anIntArray137[n5] * anInt146, n10, method590, n3, b, ObjectManager.anIntArray144[n5] * anInt146, ObjectManager.anIntArray152[n5]);
                return;
            }
            if (n2 == 6) {
                Animable method591;
                if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                    method591 = forID.method578(4, 0, n6, n7, n8, n9, -1);
                }
                else {
                    method591 = new Animable_Sub5(n4, 0, 4, n7, n8, n6, n9, forID.anInt781, true);
                }
                worldController.method283(n11, n, n5, anInt145, 0, n10, method591, n3, b, 0, 256);
                return;
            }
            if (n2 == 7) {
                Animable method592;
                if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                    method592 = forID.method578(4, 0, n6, n7, n8, n9, -1);
                }
                else {
                    method592 = new Animable_Sub5(n4, 0, 4, n7, n8, n6, n9, forID.anInt781, true);
                }
                worldController.method283(n11, n, n5, anInt145, 0, n10, method592, n3, b, 0, 512);
                return;
            }
            if (n2 == 8) {
                Animable method593;
                if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                    method593 = forID.method578(4, 0, n6, n7, n8, n9, -1);
                }
                else {
                    method593 = new Animable_Sub5(n4, 0, 4, n7, n8, n6, n9, forID.anInt781, true);
                }
                worldController.method283(n11, n, n5, anInt145, 0, n10, method593, n3, b, 0, 768);
            }
        }
    }
    
    private static int method176(final int n, final int n2, final int n3) {
        final int n4 = n / n3;
        final int n5 = n & n3 - 1;
        final int n6 = n2 / n3;
        return method184(method184(method186(n4, n6), method186(n4 + 1, n6), n5, n3), method184(method186(n4, n6 + 1), method186(n4 + 1, n6 + 1), n5, n3), n2 & n3 - 1, n3);
    }
    
    private int method177(final int n, int n2, final int n3) {
        if (n3 > 179) {
            n2 /= 2;
        }
        if (n3 > 192) {
            n2 /= 2;
        }
        if (n3 > 217) {
            n2 /= 2;
        }
        if (n3 > 243) {
            n2 /= 2;
        }
        return (n / 4 << 10) + (n2 / 32 << 7) + n3 / 2;
    }
    
    public static boolean method178(final int n, int n2) {
        final ObjectDef forID = ObjectDef.forID(n);
        if (n2 == 11) {
            n2 = 10;
        }
        if (n2 >= 5 && n2 <= 8) {
            n2 = 4;
        }
        return forID.method577(n2);
    }
    
    public final void method179(final int n, final int n2, final Class11[] array, final int n3, final int n4, final byte[] array2, final int n5, final int n6, final int n7) {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (n3 + i > 0 && n3 + i < 103 && n7 + j > 0 && n7 + j < 103) {
                    final int[] array3 = array[n6].anIntArrayArray294[n3 + i];
                    final int n8 = n7 + j;
                    array3[n8] &= 0xFEFFFFFF;
                }
            }
        }
        final Stream stream = new Stream(array2);
        for (int k = 0; k < 4; ++k) {
            for (int l = 0; l < 64; ++l) {
                for (int n9 = 0; n9 < 64; ++n9) {
                    if (k == n && l >= n4 && l < n4 + 8 && n9 >= n5 && n9 < n5 + 8) {
                        this.method181(n7 + Class4.method156(n9 & 0x7, n2, l & 0x7), 0, stream, n3 + Class4.method155(n2, n9 & 0x7, l & 0x7), n6, n2, 0);
                    }
                    else {
                        this.method181(-1, 0, stream, -1, 0, 0, 0);
                    }
                }
            }
        }
    }
    
    public final void method180(final byte[] array, final int n, final int n2, final int n3, final int n4, final Class11[] array2) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 64; ++j) {
                for (int k = 0; k < 64; ++k) {
                    if (n2 + j > 0 && n2 + j < 103 && n + k > 0 && n + k < 103) {
                        final int[] array3 = array2[i].anIntArrayArray294[n2 + j];
                        final int n5 = n + k;
                        array3[n5] &= 0xFEFFFFFF;
                    }
                }
            }
        }
        final Stream stream = new Stream(array);
        for (int l = 0; l < 4; ++l) {
            for (int n6 = 0; n6 < 64; ++n6) {
                for (int n7 = 0; n7 < 64; ++n7) {
                    this.method181(n7 + n, n4, stream, n6 + n2, l, 0, n3);
                }
            }
        }
    }
    
    private void method181(final int n, final int n2, final Stream stream, final int n3, final int n4, final int n5, final int n6) {
        if (n3 >= 0 && n3 < 104 && n >= 0 && n < 104) {
            this.aByteArrayArrayArray149[n4][n3][n] = 0;
            while (true) {
                final int unsignedByte = stream.readUnsignedByte();
                if (unsignedByte == 0) {
                    if (n4 == 0) {
                        this.anIntArrayArrayArray129[0][n3][n] = -method172(932731 + n3 + n6, 556238 + n + n2) * 8;
                        return;
                    }
                    this.anIntArrayArrayArray129[n4][n3][n] = this.anIntArrayArrayArray129[n4 - 1][n3][n] - 240;
                }
                else if (unsignedByte == 1) {
                    int unsignedByte2 = stream.readUnsignedByte();
                    if (unsignedByte2 == 1) {
                        unsignedByte2 = 0;
                    }
                    if (n4 == 0) {
                        this.anIntArrayArrayArray129[0][n3][n] = -unsignedByte2 * 8;
                        return;
                    }
                    this.anIntArrayArrayArray129[n4][n3][n] = this.anIntArrayArrayArray129[n4 - 1][n3][n] - unsignedByte2 * 8;
                }
                else if (unsignedByte <= 49) {
                    this.aByteArrayArrayArray130[n4][n3][n] = stream.readSignedByte();
                    this.aByteArrayArrayArray136[n4][n3][n] = (byte)((unsignedByte - 2) / 4);
                    this.aByteArrayArrayArray148[n4][n3][n] = (byte)(unsignedByte - 2 + n5 & 0x3);
                }
                else if (unsignedByte <= 81) {
                    this.aByteArrayArrayArray149[n4][n3][n] = (byte)(unsignedByte - 49);
                }
                else {
                    this.aByteArrayArrayArray142[n4][n3][n] = (byte)(unsignedByte - 81);
                }
            }
        }
        else {
            while (true) {
                final int unsignedByte3 = stream.readUnsignedByte();
                if (unsignedByte3 == 0) {
                    return;
                }
                if (unsignedByte3 == 1) {
                    stream.readUnsignedByte();
                    return;
                }
                if (unsignedByte3 > 49) {
                    continue;
                }
                stream.readUnsignedByte();
            }
        }
    }
    
    private int method182(final int n, final int n2, final int n3) {
        if ((this.aByteArrayArrayArray149[n2][n3][n] & 0x8) != 0x0) {
            return 0;
        }
        if (n2 > 0 && (this.aByteArrayArrayArray149[1][n3][n] & 0x2) != 0x0) {
            return n2 - 1;
        }
        return n2;
    }
    
    public final void method183(final Class11[] array, final WorldController worldController, final int n, final int n2, final int n3, final int n4, final byte[] array2, final int n5, final int n6, final int n7) {
        final Stream stream = new Stream(array2);
        int n8 = -1;
        while (true) {
            final int uSmart2 = stream.readUSmart2();
            if (uSmart2 == 0) {
                break;
            }
            n8 += uSmart2;
            int n9 = 0;
            while (true) {
                final int method422 = stream.method422();
                if (method422 == 0) {
                    break;
                }
                n9 += method422 - 1;
                final int n10 = n9 & 0x3F;
                final int n11 = n9 >> 6 & 0x3F;
                final int n12 = n9 >> 12;
                final int unsignedByte = stream.readUnsignedByte();
                final int n13 = unsignedByte >> 2;
                final int n14 = unsignedByte & 0x3;
                if (n12 != n || n11 < n5 || n11 >= n5 + 8 || n10 < n3 || n10 >= n3 + 8) {
                    continue;
                }
                final ObjectDef forID = ObjectDef.forID(n8);
                final int n15 = n2 + Class4.method157(n6, forID.anInt761, n11 & 0x7, n10 & 0x7, forID.anInt744);
                final int n16 = n7 + Class4.method158(n10 & 0x7, forID.anInt761, n6, forID.anInt744, n11 & 0x7);
                if (n15 <= 0 || n16 <= 0 || n15 >= 103 || n16 >= 103) {
                    continue;
                }
                int n17 = n12;
                if ((this.aByteArrayArrayArray149[1][n15][n16] & 0x2) == 0x2) {
                    --n17;
                }
                Class11 class11 = null;
                if (n17 >= 0) {
                    class11 = array[n17];
                }
                this.method175(n16, worldController, class11, n13, n4, n15, n8, n14 + n6 & 0x3);
            }
        }
    }
    
    private static int method184(final int n, final int n2, final int n3, final int n4) {
        final int n5 = 65536 - Texture.anIntArray1471[n3 * 1024 / n4] >> 1;
        return (n * (65536 - n5) >> 16) + (n2 * n5 >> 16);
    }
    
    private int method185(final int n, int n2) {
        if (n == -2) {
            return 12345678;
        }
        if (n == -1) {
            if (n2 < 0) {
                n2 = 0;
            }
            else if (n2 > 127) {
                n2 = 127;
            }
            n2 = 127 - n2;
            return n2;
        }
        n2 = n2 * (n & 0x7F) / 128;
        if (n2 < 2) {
            n2 = 2;
        }
        else if (n2 > 126) {
            n2 = 126;
        }
        return (n & 0xFF80) + n2;
    }
    
    private static int method186(final int n, final int n2) {
        return (method170(n - 1, n2 - 1) + method170(n + 1, n2 - 1) + method170(n - 1, n2 + 1) + method170(n + 1, n2 + 1)) / 16 + (method170(n - 1, n2) + method170(n + 1, n2) + method170(n, n2 - 1) + method170(n, n2 + 1)) / 8 + method170(n, n2) / 4;
    }
    
    private static int method187(final int n, int n2) {
        if (n == -1) {
            return 12345678;
        }
        n2 = n2 * (n & 0x7F) / 128;
        if (n2 < 2) {
            n2 = 2;
        }
        else if (n2 > 126) {
            n2 = 126;
        }
        return (n & 0xFF80) + n2;
    }
    
    public static void method188(final WorldController worldController, final int n, final int n2, final int n3, final int n4, final Class11 class11, final int[][][] array, final int n5, final int n6, final int n7) {
        int n8 = array[n4][n5][n2];
        int n9 = array[n4][n5 + 1][n2];
        int n10 = array[n4][n5 + 1][n2 + 1];
        int n11 = array[n4][n5][n2 + 1];
        final int n12 = n8 + n9 + n10 + n11 >> 2;
        final ObjectDef forID = ObjectDef.forID(n6);
        int n13 = n5 + (n2 << 7) + (n6 << 14) + 1073741824;
        if (!forID.hasActions) {
            n13 -= Integer.MIN_VALUE;
        }
        final byte b = (byte)((n << 6) + n3);
        if (n3 == 22) {
            Animable method578;
            if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                method578 = forID.method578(22, n, n8, n9, n10, n11, -1);
            }
            else {
                method578 = new Animable_Sub5(n6, n, 22, n9, n10, n8, n11, forID.anInt781, true);
            }
            worldController.method280(n7, n12, n2, method578, b, n13, n5);
            if (forID.aBoolean767 && forID.hasActions) {
                class11.method213(n2, n5);
            }
            return;
        }
        if (n3 == 10 || n3 == 11) {
            Animable method579;
            if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                method579 = forID.method578(10, n, n8, n9, n10, n11, -1);
            }
            else {
                method579 = new Animable_Sub5(n6, n, 10, n9, n10, n8, n11, forID.anInt781, true);
            }
            if (method579 != null) {
                int n14 = 0;
                if (n3 == 11) {
                    n14 += 256;
                }
                int n15;
                int n16;
                if (n == 1 || n == 3) {
                    n15 = forID.anInt761;
                    n16 = forID.anInt744;
                }
                else {
                    n15 = forID.anInt744;
                    n16 = forID.anInt761;
                }
                worldController.method284(n13, b, n12, n16, method579, n15, n7, n14, n2, n5);
            }
            if (forID.aBoolean767) {
                class11.method212(forID.aBoolean757, forID.anInt744, forID.anInt761, n5, n2, n);
            }
            return;
        }
        if (n3 >= 12) {
            Animable method580;
            if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                method580 = forID.method578(n3, n, n8, n9, n10, n11, -1);
            }
            else {
                method580 = new Animable_Sub5(n6, n, n3, n9, n10, n8, n11, forID.anInt781, true);
            }
            worldController.method284(n13, b, n12, 1, method580, 1, n7, 0, n2, n5);
            if (forID.aBoolean767) {
                class11.method212(forID.aBoolean757, forID.anInt744, forID.anInt761, n5, n2, n);
            }
            return;
        }
        if (n3 == 0) {
            Animable method581;
            if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                method581 = forID.method578(0, n, n8, n9, n10, n11, -1);
            }
            else {
                method581 = new Animable_Sub5(n6, n, 0, n9, n10, n8, n11, forID.anInt781, true);
            }
            worldController.method282(ObjectManager.anIntArray152[n], method581, n13, n2, b, n5, null, n12, 0, n7);
            if (forID.aBoolean767) {
                class11.method211(n2, n, n5, n3, forID.aBoolean757);
            }
            return;
        }
        if (n3 == 1) {
            Animable method582;
            if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                method582 = forID.method578(1, n, n8, n9, n10, n11, -1);
            }
            else {
                method582 = new Animable_Sub5(n6, n, 1, n9, n10, n8, n11, forID.anInt781, true);
            }
            worldController.method282(ObjectManager.anIntArray140[n], method582, n13, n2, b, n5, null, n12, 0, n7);
            if (forID.aBoolean767) {
                class11.method211(n2, n, n5, n3, forID.aBoolean757);
            }
            return;
        }
        if (n3 == 2) {
            final int n17 = n + 1 & 0x3;
            Animable method583;
            Animable method584;
            if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                method583 = forID.method578(2, 4 + n, n8, n9, n10, n11, -1);
                method584 = forID.method578(2, n17, n8, n9, n10, n11, -1);
            }
            else {
                method583 = new Animable_Sub5(n6, 4 + n, 2, n9, n10, n8, n11, forID.anInt781, true);
                method584 = new Animable_Sub5(n6, n17, 2, n9, n10, n8, n11, forID.anInt781, true);
            }
            worldController.method282(ObjectManager.anIntArray152[n], method583, n13, n2, b, n5, method584, n12, ObjectManager.anIntArray152[n17], n7);
            if (forID.aBoolean767) {
                class11.method211(n2, n, n5, n3, forID.aBoolean757);
            }
            return;
        }
        if (n3 == 3) {
            Animable method585;
            if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                method585 = forID.method578(3, n, n8, n9, n10, n11, -1);
            }
            else {
                method585 = new Animable_Sub5(n6, n, 3, n9, n10, n8, n11, forID.anInt781, true);
            }
            worldController.method282(ObjectManager.anIntArray140[n], method585, n13, n2, b, n5, null, n12, 0, n7);
            if (forID.aBoolean767) {
                class11.method211(n2, n, n5, n3, forID.aBoolean757);
            }
            return;
        }
        if (n3 == 9) {
            Animable method586;
            if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                method586 = forID.method578(n3, n, n8, n9, n10, n11, -1);
            }
            else {
                method586 = new Animable_Sub5(n6, n, n3, n9, n10, n8, n11, forID.anInt781, true);
            }
            worldController.method284(n13, b, n12, 1, method586, 1, n7, 0, n2, n5);
            if (forID.aBoolean767) {
                class11.method212(forID.aBoolean757, forID.anInt744, forID.anInt761, n5, n2, n);
            }
            return;
        }
        if (forID.aBoolean762) {
            if (n == 1) {
                final int n18 = n11;
                n11 = n10;
                n10 = n9;
                n9 = n8;
                n8 = n18;
            }
            else if (n == 2) {
                final int n19 = n11;
                n11 = n9;
                n9 = n19;
                final int n20 = n10;
                n10 = n8;
                n8 = n20;
            }
            else if (n == 3) {
                final int n21 = n11;
                n11 = n8;
                n8 = n9;
                n9 = n10;
                n10 = n21;
            }
        }
        if (n3 == 4) {
            Animable method587;
            if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                method587 = forID.method578(4, 0, n8, n9, n10, n11, -1);
            }
            else {
                method587 = new Animable_Sub5(n6, 0, 4, n9, n10, n8, n11, forID.anInt781, true);
            }
            worldController.method283(n13, n2, n * 512, n7, 0, n12, method587, n5, b, 0, ObjectManager.anIntArray152[n]);
            return;
        }
        if (n3 == 5) {
            int anInt775 = 16;
            final int method588 = worldController.method300(n7, n5, n2);
            if (method588 > 0) {
                anInt775 = ObjectDef.forID(method588 >> 14 & 0x7FFF).anInt775;
            }
            Animable method589;
            if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                method589 = forID.method578(4, 0, n8, n9, n10, n11, -1);
            }
            else {
                method589 = new Animable_Sub5(n6, 0, 4, n9, n10, n8, n11, forID.anInt781, true);
            }
            worldController.method283(n13, n2, n * 512, n7, ObjectManager.anIntArray137[n] * anInt775, n12, method589, n5, b, ObjectManager.anIntArray144[n] * anInt775, ObjectManager.anIntArray152[n]);
            return;
        }
        if (n3 == 6) {
            Animable method590;
            if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                method590 = forID.method578(4, 0, n8, n9, n10, n11, -1);
            }
            else {
                method590 = new Animable_Sub5(n6, 0, 4, n9, n10, n8, n11, forID.anInt781, true);
            }
            worldController.method283(n13, n2, n, n7, 0, n12, method590, n5, b, 0, 256);
            return;
        }
        if (n3 == 7) {
            Animable method591;
            if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                method591 = forID.method578(4, 0, n8, n9, n10, n11, -1);
            }
            else {
                method591 = new Animable_Sub5(n6, 0, 4, n9, n10, n8, n11, forID.anInt781, true);
            }
            worldController.method283(n13, n2, n, n7, 0, n12, method591, n5, b, 0, 512);
            return;
        }
        if (n3 == 8) {
            Animable method592;
            if (forID.anInt781 == -1 && forID.childrenIDs == null) {
                method592 = forID.method578(4, 0, n8, n9, n10, n11, -1);
            }
            else {
                method592 = new Animable_Sub5(n6, 0, 4, n9, n10, n8, n11, forID.anInt781, true);
            }
            worldController.method283(n13, n2, n, n7, 0, n12, method592, n5, b, 0, 768);
        }
    }
    
    public static boolean method189(final int n, final byte[] array, final int n2) {
        boolean b = true;
        final Stream stream = new Stream(array);
        int n3 = -1;
        while (true) {
            final int method422 = stream.method422();
            if (method422 == 0) {
                break;
            }
            n3 += method422;
            int n4 = 0;
            int n5 = 0;
            while (true) {
                if (n5 != 0) {
                    if (stream.method422() == 0) {
                        break;
                    }
                    stream.readUnsignedByte();
                }
                else {
                    final int method423 = stream.method422();
                    if (method423 == 0) {
                        break;
                    }
                    n4 += method423 - 1;
                    final int n6 = n4 & 0x3F;
                    final int n7 = n4 >> 6 & 0x3F;
                    final int n8 = stream.readUnsignedByte() >> 2;
                    final int n9 = n7 + n;
                    final int n10 = n6 + n2;
                    if (n9 <= 0 || n10 <= 0 || n9 >= 103 || n10 >= 103) {
                        continue;
                    }
                    final ObjectDef forID = ObjectDef.forID(n3);
                    if (n8 == 22 && ObjectManager.lowMem && !forID.hasActions && !forID.aBoolean736) {
                        continue;
                    }
                    b &= forID.method579();
                    n5 = 1;
                }
            }
        }
        return b;
    }
    
    public final void method190(final int n, final Class11[] array, final int n2, final WorldController worldController, final byte[] array2) {
        final Stream stream = new Stream(array2);
        int n3 = -1;
        while (true) {
            final int method422 = stream.method422();
            if (method422 == 0) {
                break;
            }
            n3 += method422;
            int n4 = 0;
            while (true) {
                final int method423 = stream.method422();
                if (method423 == 0) {
                    break;
                }
                n4 += method423 - 1;
                final int n5 = n4 & 0x3F;
                final int n6 = n4 >> 6 & 0x3F;
                final int n7 = n4 >> 12;
                final int unsignedByte = stream.readUnsignedByte();
                final int n8 = unsignedByte >> 2;
                final int n9 = unsignedByte & 0x3;
                final int n10 = n6 + n;
                final int n11 = n5 + n2;
                if (n10 <= 0 || n11 <= 0 || n10 >= 103 || n11 >= 103) {
                    continue;
                }
                int n12 = n7;
                if ((this.aByteArrayArrayArray149[1][n10][n11] & 0x2) == 0x2) {
                    --n12;
                }
                Class11 class11 = null;
                if (n12 >= 0) {
                    class11 = array[n12];
                }
                this.method175(n11, worldController, class11, n8, n7, n10, n3, n9);
            }
        }
    }
    
    static {
        ObjectManager.anInt123 = (int)(Math.random() * 17.0) - 8;
        ObjectManager.anInt133 = (int)(Math.random() * 33.0) - 16;
        anIntArray137 = new int[] { 1, 0, -1, 0 };
        anIntArray140 = new int[] { 16, 32, 64, 128 };
        anIntArray144 = new int[] { 0, -1, 0, 1 };
        ObjectManager.anInt145 = 99;
        ObjectManager.lowMem = true;
        anIntArray152 = new int[] { 1, 2, 4, 8 };
    }
}
