// 
// Decompiled by Procyon v0.5.30
// 

final class Class332_Sub3_Sub3 extends Class332_Sub3
{
    private int[] anIntArray6214;
    private byte[] aByteArray6215;
    
    Class332_Sub3_Sub3(final ha_Sub2 ha_Sub2, final byte[] aByteArray6215, final int[] anIntArray6214, final int n, final int n2) {
        super(ha_Sub2, n, n2);
        this.aByteArray6215 = aByteArray6215;
        this.anIntArray6214 = anIntArray6214;
    }
    
    @Override
    final void method3745(int n, int n2, int n3, int n4, final int n5, int n6, final int n7, final int n8) {
        if (super.aHa_Sub2_5434.method1920()) {
            throw new IllegalStateException();
        }
        if (n3 <= 0 || n4 <= 0) {
            return;
        }
        int n9 = 0;
        int n10 = 0;
        final int anInt4505 = super.aHa_Sub2_5434.anInt4505;
        final int n11 = super.anInt5446 + super.anInt5433 + super.anInt5455;
        final int n12 = super.anInt5439 + super.anInt5454 + super.anInt5447;
        final int n13 = (n11 << 16) / n3;
        final int n14 = (n12 << 16) / n4;
        if (super.anInt5446 > 0) {
            final int n15 = ((super.anInt5446 << 16) + n13 - 1) / n13;
            n += n15;
            n9 += n15 * n13 - (super.anInt5446 << 16);
        }
        if (super.anInt5439 > 0) {
            final int n16 = ((super.anInt5439 << 16) + n14 - 1) / n14;
            n2 += n16;
            n10 += n16 * n14 - (super.anInt5439 << 16);
        }
        if (super.anInt5433 < n11) {
            n3 = ((super.anInt5433 << 16) - n9 + n13 - 1) / n13;
        }
        if (super.anInt5454 < n12) {
            n4 = ((super.anInt5454 << 16) - n10 + n14 - 1) / n14;
        }
        int n17 = n + n2 * anInt4505;
        int n18 = anInt4505 - n3;
        if (n2 + n4 > super.aHa_Sub2_5434.anInt4492) {
            n4 -= n2 + n4 - super.aHa_Sub2_5434.anInt4492;
        }
        if (n2 < super.aHa_Sub2_5434.anInt4495) {
            final int n19 = super.aHa_Sub2_5434.anInt4495 - n2;
            n4 -= n19;
            n17 += n19 * anInt4505;
            n10 += n14 * n19;
        }
        if (n + n3 > super.aHa_Sub2_5434.anInt4507) {
            final int n20 = n + n3 - super.aHa_Sub2_5434.anInt4507;
            n3 -= n20;
            n18 += n20;
        }
        if (n < super.aHa_Sub2_5434.anInt4509) {
            final int n21 = super.aHa_Sub2_5434.anInt4509 - n;
            n3 -= n21;
            n17 += n21;
            n9 += n13 * n21;
            n18 += n21;
        }
        final int[] anIntArray4504 = super.aHa_Sub2_5434.anIntArray4504;
        if (n7 == 0) {
            if (n5 == 1) {
                final int n22 = n9;
                for (int i = -n4; i < 0; ++i) {
                    final int n23 = (n10 >> 16) * super.anInt5433;
                    for (int j = -n3; j < 0; ++j) {
                        anIntArray4504[n17++] = this.anIntArray6214[this.aByteArray6215[(n9 >> 16) + n23] & 0xFF];
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n22;
                    n17 += n18;
                }
                return;
            }
            if (n5 == 0) {
                final int n24 = (n6 & 0xFF0000) >> 16;
                final int n25 = (n6 & 0xFF00) >> 8;
                final int n26 = n6 & 0xFF;
                final int n27 = n9;
                for (int k = -n4; k < 0; ++k) {
                    final int n28 = (n10 >> 16) * super.anInt5433;
                    for (int l = -n3; l < 0; ++l) {
                        final int n29 = this.anIntArray6214[this.aByteArray6215[(n9 >> 16) + n28] & 0xFF];
                        anIntArray4504[n17++] = (((n29 & 0xFF0000) * n24 & 0xFF000000) | ((n29 & 0xFF00) * n25 & 0xFF0000) | ((n29 & 0xFF) * n26 & 0xFF00)) >>> 8;
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n27;
                    n17 += n18;
                }
                return;
            }
            if (n5 == 3) {
                final int n30 = n9;
                for (int n31 = -n4; n31 < 0; ++n31) {
                    final int n32 = (n10 >> 16) * super.anInt5433;
                    for (int n33 = -n3; n33 < 0; ++n33) {
                        final byte b = this.aByteArray6215[(n9 >> 16) + n32];
                        final int n34 = (b > 0) ? this.anIntArray6214[b] : 0;
                        final int n35 = n34 + n6;
                        final int n36 = (n34 & 0xFF00FF) + (n6 & 0xFF00FF);
                        final int n37 = (n36 & 0x1000100) + (n35 - n36 & 0x10000);
                        anIntArray4504[n17++] = (n35 - n37 | n37 - (n37 >>> 8));
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n30;
                    n17 += n18;
                }
                return;
            }
            if (n5 == 2) {
                final int n38 = n6 >>> 24;
                final int n39 = 256 - n38;
                n6 = (((n6 & 0xFF00FF) * n39 & 0xFF00FF00) | ((n6 & 0xFF00) * n39 & 0xFF0000)) >>> 8;
                final int n40 = n9;
                for (int n41 = -n4; n41 < 0; ++n41) {
                    final int n42 = (n10 >> 16) * super.anInt5433;
                    for (int n43 = -n3; n43 < 0; ++n43) {
                        final int n44 = this.anIntArray6214[this.aByteArray6215[(n9 >> 16) + n42] & 0xFF];
                        anIntArray4504[n17++] = ((((n44 & 0xFF00FF) * n38 & 0xFF00FF00) | ((n44 & 0xFF00) * n38 & 0xFF0000)) >>> 8) + n6;
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n40;
                    n17 += n18;
                }
                return;
            }
            throw new IllegalArgumentException();
        }
        else if (n7 == 1) {
            if (n5 == 1) {
                final int n45 = n9;
                for (int n46 = -n4; n46 < 0; ++n46) {
                    final int n47 = (n10 >> 16) * super.anInt5433;
                    for (int n48 = -n3; n48 < 0; ++n48) {
                        final byte b2 = this.aByteArray6215[(n9 >> 16) + n47];
                        if (b2 != 0) {
                            anIntArray4504[n17++] = this.anIntArray6214[b2 & 0xFF];
                        }
                        else {
                            ++n17;
                        }
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n45;
                    n17 += n18;
                }
                return;
            }
            if (n5 == 0) {
                final int n49 = n9;
                if ((n6 & 0xFFFFFF) == 0xFFFFFF) {
                    final int n50 = n6 >>> 24;
                    final int n51 = 256 - n50;
                    for (int n52 = -n4; n52 < 0; ++n52) {
                        final int n53 = (n10 >> 16) * super.anInt5433;
                        for (int n54 = -n3; n54 < 0; ++n54) {
                            final byte b3 = this.aByteArray6215[(n9 >> 16) + n53];
                            if (b3 != 0) {
                                final int n55 = this.anIntArray6214[b3 & 0xFF];
                                final int n56 = anIntArray4504[n17];
                                anIntArray4504[n17++] = ((n55 & 0xFF00FF) * n50 + (n56 & 0xFF00FF) * n51 & 0xFF00FF00) + ((n55 & 0xFF00) * n50 + (n56 & 0xFF00) * n51 & 0xFF0000) >> 8;
                            }
                            else {
                                ++n17;
                            }
                            n9 += n13;
                        }
                        n10 += n14;
                        n9 = n49;
                        n17 += n18;
                    }
                    return;
                }
                final int n57 = (n6 & 0xFF0000) >> 16;
                final int n58 = (n6 & 0xFF00) >> 8;
                final int n59 = n6 & 0xFF;
                final int n60 = n6 >>> 24;
                final int n61 = 256 - n60;
                for (int n62 = -n4; n62 < 0; ++n62) {
                    final int n63 = (n10 >> 16) * super.anInt5433;
                    for (int n64 = -n3; n64 < 0; ++n64) {
                        final byte b4 = this.aByteArray6215[(n9 >> 16) + n63];
                        if (b4 != 0) {
                            final int n65 = this.anIntArray6214[b4 & 0xFF];
                            if (n60 != 255) {
                                final int n66 = (((n65 & 0xFF0000) * n57 & 0xFF000000) | ((n65 & 0xFF00) * n58 & 0xFF0000) | ((n65 & 0xFF) * n59 & 0xFF00)) >>> 8;
                                final int n67 = anIntArray4504[n17];
                                anIntArray4504[n17++] = ((n66 & 0xFF00FF) * n60 + (n67 & 0xFF00FF) * n61 & 0xFF00FF00) + ((n66 & 0xFF00) * n60 + (n67 & 0xFF00) * n61 & 0xFF0000) >> 8;
                            }
                            else {
                                anIntArray4504[n17++] = (((n65 & 0xFF0000) * n57 & 0xFF000000) | ((n65 & 0xFF00) * n58 & 0xFF0000) | ((n65 & 0xFF) * n59 & 0xFF00)) >>> 8;
                            }
                        }
                        else {
                            ++n17;
                        }
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n49;
                    n17 += n18;
                }
            }
            else {
                if (n5 == 3) {
                    final int n68 = n9;
                    final int n69 = n6 >>> 24;
                    final int n70 = 256 - n69;
                    for (int n71 = -n4; n71 < 0; ++n71) {
                        final int n72 = (n10 >> 16) * super.anInt5433;
                        for (int n73 = -n3; n73 < 0; ++n73) {
                            final byte b5 = this.aByteArray6215[(n9 >> 16) + n72];
                            final int n74 = (b5 > 0) ? this.anIntArray6214[b5] : 0;
                            final int n75 = n74 + n6;
                            final int n76 = (n74 & 0xFF00FF) + (n6 & 0xFF00FF);
                            final int n77 = (n76 & 0x1000100) + (n75 - n76 & 0x10000);
                            int n78 = n75 - n77 | n77 - (n77 >>> 8);
                            if (n74 == 0 && n69 != 255) {
                                final int n79 = n78;
                                final int n80 = anIntArray4504[n17];
                                n78 = ((n79 & 0xFF00FF) * n69 + (n80 & 0xFF00FF) * n70 & 0xFF00FF00) + ((n79 & 0xFF00) * n69 + (n80 & 0xFF00) * n70 & 0xFF0000) >> 8;
                            }
                            anIntArray4504[n17++] = n78;
                            n9 += n13;
                        }
                        n10 += n14;
                        n9 = n68;
                        n17 += n18;
                    }
                    return;
                }
                if (n5 == 2) {
                    final int n81 = n6 >>> 24;
                    final int n82 = 256 - n81;
                    n6 = (((n6 & 0xFF00FF) * n82 & 0xFF00FF00) | ((n6 & 0xFF00) * n82 & 0xFF0000)) >>> 8;
                    final int n83 = n9;
                    for (int n84 = -n4; n84 < 0; ++n84) {
                        final int n85 = (n10 >> 16) * super.anInt5433;
                        for (int n86 = -n3; n86 < 0; ++n86) {
                            final byte b6 = this.aByteArray6215[(n9 >> 16) + n85];
                            if (b6 != 0) {
                                final int n87 = this.anIntArray6214[b6 & 0xFF];
                                anIntArray4504[n17++] = ((((n87 & 0xFF00FF) * n81 & 0xFF00FF00) | ((n87 & 0xFF00) * n81 & 0xFF0000)) >>> 8) + n6;
                            }
                            else {
                                ++n17;
                            }
                            n9 += n13;
                        }
                        n10 += n14;
                        n9 = n83;
                        n17 += n18;
                    }
                    return;
                }
                throw new IllegalArgumentException();
            }
        }
        else {
            if (n7 != 2) {
                throw new IllegalArgumentException();
            }
            if (n5 == 1) {
                final int n88 = n9;
                for (int n89 = -n4; n89 < 0; ++n89) {
                    final int n90 = (n10 >> 16) * super.anInt5433;
                    for (int n91 = -n3; n91 < 0; ++n91) {
                        final byte b7 = this.aByteArray6215[(n9 >> 16) + n90];
                        if (b7 != 0) {
                            final int n92 = this.anIntArray6214[b7 & 0xFF];
                            final int n93 = anIntArray4504[n17];
                            final int n94 = n92 + n93;
                            final int n95 = (n92 & 0xFF00FF) + (n93 & 0xFF00FF);
                            final int n96 = (n95 & 0x1000100) + (n94 - n95 & 0x10000);
                            anIntArray4504[n17++] = (n94 - n96 | n96 - (n96 >>> 8));
                        }
                        else {
                            ++n17;
                        }
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n88;
                    n17 += n18;
                }
                return;
            }
            if (n5 == 0) {
                final int n97 = n9;
                final int n98 = (n6 & 0xFF0000) >> 16;
                final int n99 = (n6 & 0xFF00) >> 8;
                final int n100 = n6 & 0xFF;
                for (int n101 = -n4; n101 < 0; ++n101) {
                    final int n102 = (n10 >> 16) * super.anInt5433;
                    for (int n103 = -n3; n103 < 0; ++n103) {
                        final byte b8 = this.aByteArray6215[(n9 >> 16) + n102];
                        if (b8 != 0) {
                            final int n104 = this.anIntArray6214[b8 & 0xFF];
                            final int n105 = (((n104 & 0xFF0000) * n98 & 0xFF000000) | ((n104 & 0xFF00) * n99 & 0xFF0000) | ((n104 & 0xFF) * n100 & 0xFF00)) >>> 8;
                            final int n106 = anIntArray4504[n17];
                            final int n107 = n105 + n106;
                            final int n108 = (n105 & 0xFF00FF) + (n106 & 0xFF00FF);
                            final int n109 = (n108 & 0x1000100) + (n107 - n108 & 0x10000);
                            anIntArray4504[n17++] = (n107 - n109 | n109 - (n109 >>> 8));
                        }
                        else {
                            ++n17;
                        }
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n97;
                    n17 += n18;
                }
                return;
            }
            if (n5 == 3) {
                final int n110 = n9;
                for (int n111 = -n4; n111 < 0; ++n111) {
                    final int n112 = (n10 >> 16) * super.anInt5433;
                    for (int n113 = -n3; n113 < 0; ++n113) {
                        final byte b9 = this.aByteArray6215[(n9 >> 16) + n112];
                        final int n114 = (b9 > 0) ? this.anIntArray6214[b9] : 0;
                        final int n115 = n114 + n6;
                        final int n116 = (n114 & 0xFF00FF) + (n6 & 0xFF00FF);
                        final int n117 = (n116 & 0x1000100) + (n115 - n116 & 0x10000);
                        final int n118 = n115 - n117 | n117 - (n117 >>> 8);
                        final int n119 = anIntArray4504[n17];
                        final int n120 = n118 + n119;
                        final int n121 = (n118 & 0xFF00FF) + (n119 & 0xFF00FF);
                        final int n122 = (n121 & 0x1000100) + (n120 - n121 & 0x10000);
                        anIntArray4504[n17++] = (n120 - n122 | n122 - (n122 >>> 8));
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n110;
                    n17 += n18;
                }
                return;
            }
            if (n5 == 2) {
                final int n123 = n6 >>> 24;
                final int n124 = 256 - n123;
                n6 = (((n6 & 0xFF00FF) * n124 & 0xFF00FF00) | ((n6 & 0xFF00) * n124 & 0xFF0000)) >>> 8;
                final int n125 = n9;
                for (int n126 = -n4; n126 < 0; ++n126) {
                    final int n127 = (n10 >> 16) * super.anInt5433;
                    for (int n128 = -n3; n128 < 0; ++n128) {
                        final byte b10 = this.aByteArray6215[(n9 >> 16) + n127];
                        if (b10 != 0) {
                            final int n129 = this.anIntArray6214[b10 & 0xFF];
                            final int n130 = ((((n129 & 0xFF00FF) * n123 & 0xFF00FF00) | ((n129 & 0xFF00) * n123 & 0xFF0000)) >>> 8) + n6;
                            final int n131 = anIntArray4504[n17];
                            final int n132 = n130 + n131;
                            final int n133 = (n130 & 0xFF00FF) + (n131 & 0xFF00FF);
                            final int n134 = (n133 & 0x1000100) + (n132 - n133 & 0x10000);
                            anIntArray4504[n17++] = (n132 - n134 | n134 - (n134 >>> 8));
                        }
                        else {
                            ++n17;
                        }
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n125;
                    n17 += n18;
                }
                return;
            }
            throw new IllegalArgumentException();
        }
    }
    
    @Override
    final void method3736(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        throw new IllegalStateException();
    }
    
    @Override
    final void method3757(int n, int n2, final int n3, int n4, int n5, final int n6, int n7, final int n8, final int n9) {
        if (n4 <= 0 || n5 <= 0) {
            return;
        }
        int n10 = 0;
        int n11 = 0;
        final int n12 = super.anInt5446 + super.anInt5433 + super.anInt5455;
        final int n13 = super.anInt5439 + super.anInt5454 + super.anInt5447;
        final int n14 = (n12 << 16) / n4;
        final int n15 = (n13 << 16) / n5;
        if (super.anInt5446 > 0) {
            final int n16 = ((super.anInt5446 << 16) + n14 - 1) / n14;
            n += n16;
            n10 += n16 * n14 - (super.anInt5446 << 16);
        }
        if (super.anInt5439 > 0) {
            final int n17 = ((super.anInt5439 << 16) + n15 - 1) / n15;
            n2 += n17;
            n11 += n17 * n15 - (super.anInt5439 << 16);
        }
        if (super.anInt5433 < n12) {
            n4 = ((super.anInt5433 << 16) - n10 + n14 - 1) / n14;
        }
        if (super.anInt5454 < n13) {
            n5 = ((super.anInt5454 << 16) - n11 + n15 - 1) / n15;
        }
        int n18 = n + n2 * super.aHa_Sub2_5434.anInt4505;
        int n19 = super.aHa_Sub2_5434.anInt4505 - n4;
        if (n2 + n5 > super.aHa_Sub2_5434.anInt4492) {
            n5 -= n2 + n5 - super.aHa_Sub2_5434.anInt4492;
        }
        if (n2 < super.aHa_Sub2_5434.anInt4495) {
            final int n20 = super.aHa_Sub2_5434.anInt4495 - n2;
            n5 -= n20;
            n18 += n20 * super.aHa_Sub2_5434.anInt4505;
            n11 += n15 * n20;
        }
        if (n + n4 > super.aHa_Sub2_5434.anInt4507) {
            final int n21 = n + n4 - super.aHa_Sub2_5434.anInt4507;
            n4 -= n21;
            n19 += n21;
        }
        if (n < super.aHa_Sub2_5434.anInt4509) {
            final int n22 = super.aHa_Sub2_5434.anInt4509 - n;
            n4 -= n22;
            n18 += n22;
            n10 += n14 * n22;
            n19 += n22;
        }
        final float[] aFloatArray4487 = super.aHa_Sub2_5434.aFloatArray4487;
        final int[] anIntArray4504 = super.aHa_Sub2_5434.anIntArray4504;
        if (n8 == 0) {
            if (n6 == 1) {
                final int n23 = n10;
                for (int i = -n5; i < 0; ++i) {
                    final int n24 = (n11 >> 16) * super.anInt5433;
                    for (int j = -n4; j < 0; ++j) {
                        if (n3 < aFloatArray4487[n18]) {
                            anIntArray4504[n18] = this.anIntArray6214[this.aByteArray6215[(n10 >> 16) + n24] & 0xFF];
                            aFloatArray4487[n18] = n3;
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n23;
                    n18 += n19;
                }
                return;
            }
            if (n6 == 0) {
                final int n25 = (n7 & 0xFF0000) >> 16;
                final int n26 = (n7 & 0xFF00) >> 8;
                final int n27 = n7 & 0xFF;
                final int n28 = n10;
                for (int k = -n5; k < 0; ++k) {
                    final int n29 = (n11 >> 16) * super.anInt5433;
                    for (int l = -n4; l < 0; ++l) {
                        if (n3 < aFloatArray4487[n18]) {
                            final int n30 = this.anIntArray6214[this.aByteArray6215[(n10 >> 16) + n29] & 0xFF];
                            anIntArray4504[n18] = (((n30 & 0xFF0000) * n25 & 0xFF000000) | ((n30 & 0xFF00) * n26 & 0xFF0000) | ((n30 & 0xFF) * n27 & 0xFF00)) >>> 8;
                            aFloatArray4487[n18] = n3;
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n28;
                    n18 += n19;
                }
                return;
            }
            if (n6 == 3) {
                final int n31 = n10;
                for (int n32 = -n5; n32 < 0; ++n32) {
                    final int n33 = (n11 >> 16) * super.anInt5433;
                    for (int n34 = -n4; n34 < 0; ++n34) {
                        if (n3 < aFloatArray4487[n18]) {
                            final byte b = this.aByteArray6215[(n10 >> 16) + n33];
                            final int n35 = (b > 0) ? this.anIntArray6214[b] : 0;
                            final int n36 = n35 + n7;
                            final int n37 = (n35 & 0xFF00FF) + (n7 & 0xFF00FF);
                            final int n38 = (n37 & 0x1000100) + (n36 - n37 & 0x10000);
                            anIntArray4504[n18] = (n36 - n38 | n38 - (n38 >>> 8));
                            aFloatArray4487[n18] = n3;
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n31;
                    n18 += n19;
                }
                return;
            }
            if (n6 == 2) {
                final int n39 = n7 >>> 24;
                final int n40 = 256 - n39;
                n7 = (((n7 & 0xFF00FF) * n40 & 0xFF00FF00) | ((n7 & 0xFF00) * n40 & 0xFF0000)) >>> 8;
                final int n41 = n10;
                for (int n42 = -n5; n42 < 0; ++n42) {
                    final int n43 = (n11 >> 16) * super.anInt5433;
                    for (int n44 = -n4; n44 < 0; ++n44) {
                        if (n3 < aFloatArray4487[n18]) {
                            final int n45 = this.anIntArray6214[this.aByteArray6215[(n10 >> 16) + n43] & 0xFF];
                            anIntArray4504[n18] = ((((n45 & 0xFF00FF) * n39 & 0xFF00FF00) | ((n45 & 0xFF00) * n39 & 0xFF0000)) >>> 8) + n7;
                            aFloatArray4487[n18] = n3;
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n41;
                    n18 += n19;
                }
                return;
            }
            throw new IllegalArgumentException();
        }
        else if (n8 == 1) {
            if (n6 == 1) {
                final int n46 = n10;
                for (int n47 = -n5; n47 < 0; ++n47) {
                    final int n48 = (n11 >> 16) * super.anInt5433;
                    for (int n49 = -n4; n49 < 0; ++n49) {
                        if (n3 < aFloatArray4487[n18]) {
                            final byte b2 = this.aByteArray6215[(n10 >> 16) + n48];
                            if (b2 != 0) {
                                anIntArray4504[n18] = this.anIntArray6214[b2 & 0xFF];
                                aFloatArray4487[n18] = n3;
                            }
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n46;
                    n18 += n19;
                }
                return;
            }
            if (n6 == 0) {
                final int n50 = n10;
                if ((n7 & 0xFFFFFF) == 0xFFFFFF) {
                    final int n51 = n7 >>> 24;
                    final int n52 = 256 - n51;
                    for (int n53 = -n5; n53 < 0; ++n53) {
                        final int n54 = (n11 >> 16) * super.anInt5433;
                        for (int n55 = -n4; n55 < 0; ++n55) {
                            if (n3 < aFloatArray4487[n18]) {
                                final byte b3 = this.aByteArray6215[(n10 >> 16) + n54];
                                if (b3 != 0) {
                                    final int n56 = this.anIntArray6214[b3 & 0xFF];
                                    final int n57 = anIntArray4504[n18];
                                    anIntArray4504[n18] = ((n56 & 0xFF00FF) * n51 + (n57 & 0xFF00FF) * n52 & 0xFF00FF00) + ((n56 & 0xFF00) * n51 + (n57 & 0xFF00) * n52 & 0xFF0000) >> 8;
                                    aFloatArray4487[n18] = n3;
                                }
                            }
                            n10 += n14;
                            ++n18;
                        }
                        n11 += n15;
                        n10 = n50;
                        n18 += n19;
                    }
                    return;
                }
                final int n58 = (n7 & 0xFF0000) >> 16;
                final int n59 = (n7 & 0xFF00) >> 8;
                final int n60 = n7 & 0xFF;
                final int n61 = n7 >>> 24;
                final int n62 = 256 - n61;
                for (int n63 = -n5; n63 < 0; ++n63) {
                    final int n64 = (n11 >> 16) * super.anInt5433;
                    for (int n65 = -n4; n65 < 0; ++n65) {
                        if (n3 < aFloatArray4487[n18]) {
                            final byte b4 = this.aByteArray6215[(n10 >> 16) + n64];
                            if (b4 != 0) {
                                final int n66 = this.anIntArray6214[b4 & 0xFF];
                                if (n61 != 255) {
                                    final int n67 = (((n66 & 0xFF0000) * n58 & 0xFF000000) | ((n66 & 0xFF00) * n59 & 0xFF0000) | ((n66 & 0xFF) * n60 & 0xFF00)) >>> 8;
                                    final int n68 = anIntArray4504[n18];
                                    anIntArray4504[n18] = ((n67 & 0xFF00FF) * n61 + (n68 & 0xFF00FF) * n62 & 0xFF00FF00) + ((n67 & 0xFF00) * n61 + (n68 & 0xFF00) * n62 & 0xFF0000) >> 8;
                                    aFloatArray4487[n18] = n3;
                                }
                                else {
                                    anIntArray4504[n18] = (((n66 & 0xFF0000) * n58 & 0xFF000000) | ((n66 & 0xFF00) * n59 & 0xFF0000) | ((n66 & 0xFF) * n60 & 0xFF00)) >>> 8;
                                    aFloatArray4487[n18] = n3;
                                }
                            }
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n50;
                    n18 += n19;
                }
            }
            else {
                if (n6 == 3) {
                    final int n69 = n10;
                    final int n70 = n7 >>> 24;
                    final int n71 = 256 - n70;
                    for (int n72 = -n5; n72 < 0; ++n72) {
                        final int n73 = (n11 >> 16) * super.anInt5433;
                        for (int n74 = -n4; n74 < 0; ++n74) {
                            if (n3 < aFloatArray4487[n18]) {
                                final byte b5 = this.aByteArray6215[(n10 >> 16) + n73];
                                final int n75 = (b5 > 0) ? this.anIntArray6214[b5] : 0;
                                final int n76 = n75 + n7;
                                final int n77 = (n75 & 0xFF00FF) + (n7 & 0xFF00FF);
                                final int n78 = (n77 & 0x1000100) + (n76 - n77 & 0x10000);
                                int n79 = n76 - n78 | n78 - (n78 >>> 8);
                                if (n75 == 0 && n70 != 255) {
                                    final int n80 = n79;
                                    final int n81 = anIntArray4504[n18];
                                    n79 = ((n80 & 0xFF00FF) * n70 + (n81 & 0xFF00FF) * n71 & 0xFF00FF00) + ((n80 & 0xFF00) * n70 + (n81 & 0xFF00) * n71 & 0xFF0000) >> 8;
                                }
                                anIntArray4504[n18] = n79;
                                aFloatArray4487[n18] = n3;
                            }
                            n10 += n14;
                            ++n18;
                        }
                        n11 += n15;
                        n10 = n69;
                        n18 += n19;
                    }
                    return;
                }
                if (n6 == 2) {
                    final int n82 = n7 >>> 24;
                    final int n83 = 256 - n82;
                    n7 = (((n7 & 0xFF00FF) * n83 & 0xFF00FF00) | ((n7 & 0xFF00) * n83 & 0xFF0000)) >>> 8;
                    final int n84 = n10;
                    for (int n85 = -n5; n85 < 0; ++n85) {
                        final int n86 = (n11 >> 16) * super.anInt5433;
                        for (int n87 = -n4; n87 < 0; ++n87) {
                            if (n3 < aFloatArray4487[n18]) {
                                final byte b6 = this.aByteArray6215[(n10 >> 16) + n86];
                                if (b6 != 0) {
                                    final int n88 = this.anIntArray6214[b6 & 0xFF];
                                    anIntArray4504[n18] = ((((n88 & 0xFF00FF) * n82 & 0xFF00FF00) | ((n88 & 0xFF00) * n82 & 0xFF0000)) >>> 8) + n7;
                                    aFloatArray4487[n18] = n3;
                                }
                            }
                            n10 += n14;
                            ++n18;
                        }
                        n11 += n15;
                        n10 = n84;
                        n18 += n19;
                    }
                    return;
                }
                throw new IllegalArgumentException();
            }
        }
        else {
            if (n8 != 2) {
                throw new IllegalArgumentException();
            }
            if (n6 == 1) {
                final int n89 = n10;
                for (int n90 = -n5; n90 < 0; ++n90) {
                    final int n91 = (n11 >> 16) * super.anInt5433;
                    for (int n92 = -n4; n92 < 0; ++n92) {
                        if (n3 < aFloatArray4487[n18]) {
                            final byte b7 = this.aByteArray6215[(n10 >> 16) + n91];
                            if (b7 != 0) {
                                final int n93 = this.anIntArray6214[b7 & 0xFF];
                                final int n94 = anIntArray4504[n18];
                                final int n95 = n93 + n94;
                                final int n96 = (n93 & 0xFF00FF) + (n94 & 0xFF00FF);
                                final int n97 = (n96 & 0x1000100) + (n95 - n96 & 0x10000);
                                anIntArray4504[n18] = (n95 - n97 | n97 - (n97 >>> 8));
                                aFloatArray4487[n18] = n3;
                            }
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n89;
                    n18 += n19;
                }
                return;
            }
            if (n6 == 0) {
                final int n98 = n10;
                final int n99 = (n7 & 0xFF0000) >> 16;
                final int n100 = (n7 & 0xFF00) >> 8;
                final int n101 = n7 & 0xFF;
                for (int n102 = -n5; n102 < 0; ++n102) {
                    final int n103 = (n11 >> 16) * super.anInt5433;
                    for (int n104 = -n4; n104 < 0; ++n104) {
                        if (n3 < aFloatArray4487[n18]) {
                            final byte b8 = this.aByteArray6215[(n10 >> 16) + n103];
                            if (b8 != 0) {
                                final int n105 = this.anIntArray6214[b8 & 0xFF];
                                final int n106 = (((n105 & 0xFF0000) * n99 & 0xFF000000) | ((n105 & 0xFF00) * n100 & 0xFF0000) | ((n105 & 0xFF) * n101 & 0xFF00)) >>> 8;
                                final int n107 = anIntArray4504[n18];
                                final int n108 = n106 + n107;
                                final int n109 = (n106 & 0xFF00FF) + (n107 & 0xFF00FF);
                                final int n110 = (n109 & 0x1000100) + (n108 - n109 & 0x10000);
                                anIntArray4504[n18] = (n108 - n110 | n110 - (n110 >>> 8));
                                aFloatArray4487[n18] = n3;
                            }
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n98;
                    n18 += n19;
                }
                return;
            }
            if (n6 == 3) {
                final int n111 = n10;
                for (int n112 = -n5; n112 < 0; ++n112) {
                    final int n113 = (n11 >> 16) * super.anInt5433;
                    for (int n114 = -n4; n114 < 0; ++n114) {
                        if (n3 < aFloatArray4487[n18]) {
                            final byte b9 = this.aByteArray6215[(n10 >> 16) + n113];
                            final int n115 = (b9 > 0) ? this.anIntArray6214[b9] : 0;
                            final int n116 = n115 + n7;
                            final int n117 = (n115 & 0xFF00FF) + (n7 & 0xFF00FF);
                            final int n118 = (n117 & 0x1000100) + (n116 - n117 & 0x10000);
                            final int n119 = n116 - n118 | n118 - (n118 >>> 8);
                            final int n120 = anIntArray4504[n18];
                            final int n121 = n119 + n120;
                            final int n122 = (n119 & 0xFF00FF) + (n120 & 0xFF00FF);
                            final int n123 = (n122 & 0x1000100) + (n121 - n122 & 0x10000);
                            anIntArray4504[n18] = (n121 - n123 | n123 - (n123 >>> 8));
                            aFloatArray4487[n18] = n3;
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n111;
                    n18 += n19;
                }
                return;
            }
            if (n6 == 2) {
                final int n124 = n7 >>> 24;
                final int n125 = 256 - n124;
                n7 = (((n7 & 0xFF00FF) * n125 & 0xFF00FF00) | ((n7 & 0xFF00) * n125 & 0xFF0000)) >>> 8;
                final int n126 = n10;
                for (int n127 = -n5; n127 < 0; ++n127) {
                    final int n128 = (n11 >> 16) * super.anInt5433;
                    for (int n129 = -n4; n129 < 0; ++n129) {
                        if (n3 < aFloatArray4487[n18]) {
                            final byte b10 = this.aByteArray6215[(n10 >> 16) + n128];
                            if (b10 != 0) {
                                final int n130 = this.anIntArray6214[b10 & 0xFF];
                                final int n131 = ((((n130 & 0xFF00FF) * n124 & 0xFF00FF00) | ((n130 & 0xFF00) * n124 & 0xFF0000)) >>> 8) + n7;
                                final int n132 = anIntArray4504[n18];
                                final int n133 = n131 + n132;
                                final int n134 = (n131 & 0xFF00FF) + (n132 & 0xFF00FF);
                                final int n135 = (n134 & 0x1000100) + (n133 - n134 & 0x10000);
                                anIntArray4504[n18] = (n133 - n135 | n135 - (n135 >>> 8));
                                aFloatArray4487[n18] = n3;
                            }
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n126;
                    n18 += n19;
                }
                return;
            }
            throw new IllegalArgumentException();
        }
    }
    
    @Override
    final void method3729(int anInt4509, int anInt4510, final aa aa, final int n, final int n2) {
        if (super.aHa_Sub2_5434.method1920()) {
            throw new IllegalStateException();
        }
        anInt4509 += super.anInt5446;
        anInt4510 += super.anInt5439;
        int n3 = 0;
        final int anInt4511 = super.aHa_Sub2_5434.anInt4505;
        int anInt4512 = super.anInt5433;
        int anInt4513 = super.anInt5454;
        int n4 = anInt4511 - anInt4512;
        int n5 = 0;
        int n6 = anInt4509 + anInt4510 * anInt4511;
        if (anInt4510 < super.aHa_Sub2_5434.anInt4495) {
            final int n7 = super.aHa_Sub2_5434.anInt4495 - anInt4510;
            anInt4513 -= n7;
            anInt4510 = super.aHa_Sub2_5434.anInt4495;
            n3 += n7 * anInt4512;
            n6 += n7 * anInt4511;
        }
        if (anInt4510 + anInt4513 > super.aHa_Sub2_5434.anInt4492) {
            anInt4513 -= anInt4510 + anInt4513 - super.aHa_Sub2_5434.anInt4492;
        }
        if (anInt4509 < super.aHa_Sub2_5434.anInt4509) {
            final int n8 = super.aHa_Sub2_5434.anInt4509 - anInt4509;
            anInt4512 -= n8;
            anInt4509 = super.aHa_Sub2_5434.anInt4509;
            n3 += n8;
            n6 += n8;
            n5 += n8;
            n4 += n8;
        }
        if (anInt4509 + anInt4512 > super.aHa_Sub2_5434.anInt4507) {
            final int n9 = anInt4509 + anInt4512 - super.aHa_Sub2_5434.anInt4507;
            anInt4512 -= n9;
            n5 += n9;
            n4 += n9;
        }
        if (anInt4512 > 0 && anInt4513 > 0) {
            final aa_Sub1 aa_Sub1 = (aa_Sub1)aa;
            final int[] anIntArray3555 = aa_Sub1.anIntArray3555;
            final int[] anIntArray3556 = aa_Sub1.anIntArray3557;
            final int[] anIntArray3557 = super.aHa_Sub2_5434.anIntArray4504;
            int n10 = anInt4510;
            if (n2 > n10) {
                n10 = n2;
                n6 += (n2 - anInt4510) * anInt4511;
                n3 += (n2 - anInt4510) * super.anInt5433;
            }
            for (int n11 = (n2 + anIntArray3555.length < anInt4510 + anInt4513) ? (n2 + anIntArray3555.length) : (anInt4510 + anInt4513), i = n10; i < n11; ++i) {
                final int n12 = anIntArray3555[i - n2] + n;
                int n13 = anIntArray3556[i - n2];
                int n14 = anInt4512;
                if (anInt4509 > n12) {
                    final int n15 = anInt4509 - n12;
                    if (n15 >= n13) {
                        n3 += anInt4512 + n5;
                        n6 += anInt4512 + n4;
                        continue;
                    }
                    n13 -= n15;
                }
                else {
                    final int n16 = n12 - anInt4509;
                    if (n16 >= anInt4512) {
                        n3 += anInt4512 + n5;
                        n6 += anInt4512 + n4;
                        continue;
                    }
                    n3 += n16;
                    n14 -= n16;
                    n6 += n16;
                }
                int n17 = 0;
                if (n14 < n13) {
                    n13 = n14;
                }
                else {
                    n17 = n14 - n13;
                }
                for (int j = -n13; j < 0; ++j) {
                    final byte b = this.aByteArray6215[n3++];
                    if (b != 0) {
                        anIntArray3557[n6++] = this.anIntArray6214[b & 0xFF];
                    }
                    else {
                        ++n6;
                    }
                }
                n3 += n17 + n5;
                n6 += n17 + n4;
            }
        }
    }
    
    @Override
    final void method3742(final int n, final int n2, final int n3) {
        throw new IllegalStateException();
    }
    
    @Override
    final void method3759(final int n, final int n2) {
        final int[] anIntArray4504 = super.aHa_Sub2_5434.anIntArray4504;
        if (Class332_Sub3.anInt5444 == 0) {
            if (Class332_Sub3.anInt5436 == 0) {
                for (int i = Class332_Sub3.anInt5431; i < 0; ++i, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    int anInt5451 = Class332_Sub3.anInt5451;
                    final int anInt5452 = Class332_Sub3.anInt5450;
                    final int anInt5453 = Class332_Sub3.anInt5443;
                    int j = Class332_Sub3.anInt5448;
                    if (anInt5452 >= 0 && anInt5453 >= 0 && anInt5452 - (super.anInt5433 << 12) < 0 && anInt5453 - (super.anInt5454 << 12) < 0) {
                        while (j < 0) {
                            final int n3 = (anInt5453 >> 12) * super.anInt5433 + (anInt5452 >> 12);
                            int n4 = anInt5451++;
                            final int[] array = anIntArray4504;
                            if (n2 == 0) {
                                if (n == 1) {
                                    array[n4] = this.anIntArray6214[this.aByteArray6215[n3] & 0xFF];
                                }
                                else if (n == 0) {
                                    final int n5 = this.anIntArray6214[this.aByteArray6215[n3] & 0xFF];
                                    array[n4] = (((n5 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n5 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n5 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                }
                                else if (n == 3) {
                                    final int n6 = this.anIntArray6214[this.aByteArray6215[n3] & 0xFF];
                                    final int anInt5454 = Class332_Sub3.anInt5432;
                                    final int n7 = n6 + anInt5454;
                                    final int n8 = (n6 & 0xFF00FF) + (anInt5454 & 0xFF00FF);
                                    final int n9 = (n8 & 0x1000100) + (n7 - n8 & 0x10000);
                                    array[n4] = (n7 - n9 | n9 - (n9 >>> 8));
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final int n10 = this.anIntArray6214[this.aByteArray6215[n3] & 0xFF];
                                    array[n4] = ((((n10 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n10 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                }
                            }
                            else if (n2 == 1) {
                                if (n == 1) {
                                    final byte b = this.aByteArray6215[n3];
                                    if (b != 0) {
                                        array[n4] = this.anIntArray6214[b & 0xFF];
                                    }
                                }
                                else if (n == 0) {
                                    final byte b2 = this.aByteArray6215[n3];
                                    if (b2 != 0) {
                                        final int n11 = this.anIntArray6214[b2 & 0xFF];
                                        if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                            final int n12 = Class332_Sub3.anInt5432 >>> 24;
                                            final int n13 = 256 - n12;
                                            final int n14 = array[n4];
                                            array[n4] = ((n11 & 0xFF00FF) * n12 + (n14 & 0xFF00FF) * n13 & 0xFF00FF00) + ((n11 & 0xFF00) * n12 + (n14 & 0xFF00) * n13 & 0xFF0000) >> 8;
                                        }
                                        else if (Class332_Sub3.anInt5449 != 255) {
                                            final int n15 = (((n11 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n11 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n11 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                            final int n16 = array[n4];
                                            array[n4] = ((n15 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n16 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n15 & 0xFF00) * Class332_Sub3.anInt5449 + (n16 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                        }
                                        else {
                                            array[n4] = (((n11 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n11 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n11 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        }
                                    }
                                }
                                else if (n == 3) {
                                    final byte b3 = this.aByteArray6215[n3];
                                    final int n17 = (b3 > 0) ? this.anIntArray6214[b3] : 0;
                                    final int anInt5455 = Class332_Sub3.anInt5432;
                                    final int n18 = n17 + anInt5455;
                                    final int n19 = (n17 & 0xFF00FF) + (anInt5455 & 0xFF00FF);
                                    final int n20 = (n19 & 0x1000100) + (n18 - n19 & 0x10000);
                                    int n21 = n18 - n20 | n20 - (n20 >>> 8);
                                    if (n17 == 0 && Class332_Sub3.anInt5449 != 255) {
                                        final int n22 = n21;
                                        final int n23 = array[n4];
                                        n21 = ((n22 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n23 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n22 & 0xFF00) * Class332_Sub3.anInt5449 + (n23 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                    }
                                    array[n4] = n21;
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final byte b4 = this.aByteArray6215[n3];
                                    if (b4 != 0) {
                                        final int n24 = this.anIntArray6214[b4 & 0xFF];
                                        array[n4++] = ((((n24 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n24 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    }
                                }
                            }
                            else {
                                if (n2 != 2) {
                                    throw new IllegalArgumentException();
                                }
                                if (n == 1) {
                                    final byte b5 = this.aByteArray6215[n3];
                                    if (b5 != 0) {
                                        final int n25 = this.anIntArray6214[b5 & 0xFF];
                                        final int n26 = array[n4];
                                        final int n27 = n25 + n26;
                                        final int n28 = (n25 & 0xFF00FF) + (n26 & 0xFF00FF);
                                        final int n29 = (n28 & 0x1000100) + (n27 - n28 & 0x10000);
                                        array[n4] = (n27 - n29 | n29 - (n29 >>> 8));
                                    }
                                }
                                else if (n == 0) {
                                    final byte b6 = this.aByteArray6215[n3];
                                    if (b6 != 0) {
                                        final int n30 = this.anIntArray6214[b6 & 0xFF];
                                        final int n31 = (((n30 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n30 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n30 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        final int n32 = array[n4];
                                        final int n33 = n31 + n32;
                                        final int n34 = (n31 & 0xFF00FF) + (n32 & 0xFF00FF);
                                        final int n35 = (n34 & 0x1000100) + (n33 - n34 & 0x10000);
                                        array[n4] = (n33 - n35 | n35 - (n35 >>> 8));
                                    }
                                }
                                else if (n == 3) {
                                    final byte b7 = this.aByteArray6215[n3];
                                    final int n36 = (b7 > 0) ? this.anIntArray6214[b7] : 0;
                                    final int anInt5456 = Class332_Sub3.anInt5432;
                                    final int n37 = n36 + anInt5456;
                                    final int n38 = (n36 & 0xFF00FF) + (anInt5456 & 0xFF00FF);
                                    final int n39 = (n38 & 0x1000100) + (n37 - n38 & 0x10000);
                                    int n40 = n37 - n39 | n39 - (n39 >>> 8);
                                    if (n36 == 0 && Class332_Sub3.anInt5449 != 255) {
                                        final int n41 = n40;
                                        final int n42 = array[n4];
                                        n40 = ((n41 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n42 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n41 & 0xFF00) * Class332_Sub3.anInt5449 + (n42 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                    }
                                    array[n4] = n40;
                                }
                                else if (n == 2) {
                                    final byte b8 = this.aByteArray6215[n3];
                                    if (b8 != 0) {
                                        final int n43 = this.anIntArray6214[b8 & 0xFF];
                                        final int n44 = ((((n43 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n43 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                        final int n45 = array[n4];
                                        final int n46 = n44 + n45;
                                        final int n47 = (n44 & 0xFF00FF) + (n45 & 0xFF00FF);
                                        final int n48 = (n47 & 0x1000100) + (n46 - n47 & 0x10000);
                                        array[n4] = (n46 - n48 | n48 - (n48 >>> 8));
                                    }
                                }
                            }
                            ++j;
                        }
                    }
                }
            }
            else if (Class332_Sub3.anInt5436 < 0) {
                for (int k = Class332_Sub3.anInt5431; k < 0; ++k, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    int anInt5457 = Class332_Sub3.anInt5451;
                    final int anInt5458 = Class332_Sub3.anInt5450;
                    int n49 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                    int l = Class332_Sub3.anInt5448;
                    if (anInt5458 >= 0 && anInt5458 - (super.anInt5433 << 12) < 0) {
                        final int n50;
                        if ((n50 = n49 - (super.anInt5454 << 12)) >= 0) {
                            final int n51 = (Class332_Sub3.anInt5436 - n50) / Class332_Sub3.anInt5436;
                            l += n51;
                            n49 += Class332_Sub3.anInt5436 * n51;
                            anInt5457 += n51;
                        }
                        final int n52;
                        if ((n52 = (n49 - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > l) {
                            l = n52;
                        }
                        while (l < 0) {
                            final int n53 = (n49 >> 12) * super.anInt5433 + (anInt5458 >> 12);
                            int n54 = anInt5457++;
                            final int[] array2 = anIntArray4504;
                            if (n2 == 0) {
                                if (n == 1) {
                                    array2[n54] = this.anIntArray6214[this.aByteArray6215[n53] & 0xFF];
                                }
                                else if (n == 0) {
                                    final int n55 = this.anIntArray6214[this.aByteArray6215[n53] & 0xFF];
                                    array2[n54] = (((n55 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n55 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n55 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                }
                                else if (n == 3) {
                                    final int n56 = this.anIntArray6214[this.aByteArray6215[n53] & 0xFF];
                                    final int anInt5459 = Class332_Sub3.anInt5432;
                                    final int n57 = n56 + anInt5459;
                                    final int n58 = (n56 & 0xFF00FF) + (anInt5459 & 0xFF00FF);
                                    final int n59 = (n58 & 0x1000100) + (n57 - n58 & 0x10000);
                                    array2[n54] = (n57 - n59 | n59 - (n59 >>> 8));
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final int n60 = this.anIntArray6214[this.aByteArray6215[n53] & 0xFF];
                                    array2[n54] = ((((n60 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n60 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                }
                            }
                            else if (n2 == 1) {
                                if (n == 1) {
                                    final byte b9 = this.aByteArray6215[n53];
                                    if (b9 != 0) {
                                        array2[n54] = this.anIntArray6214[b9 & 0xFF];
                                    }
                                }
                                else if (n == 0) {
                                    final byte b10 = this.aByteArray6215[n53];
                                    if (b10 != 0) {
                                        final int n61 = this.anIntArray6214[b10 & 0xFF];
                                        if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                            final int n62 = Class332_Sub3.anInt5432 >>> 24;
                                            final int n63 = 256 - n62;
                                            final int n64 = array2[n54];
                                            array2[n54] = ((n61 & 0xFF00FF) * n62 + (n64 & 0xFF00FF) * n63 & 0xFF00FF00) + ((n61 & 0xFF00) * n62 + (n64 & 0xFF00) * n63 & 0xFF0000) >> 8;
                                        }
                                        else if (Class332_Sub3.anInt5449 != 255) {
                                            final int n65 = (((n61 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n61 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n61 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                            final int n66 = array2[n54];
                                            array2[n54] = ((n65 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n66 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n65 & 0xFF00) * Class332_Sub3.anInt5449 + (n66 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                        }
                                        else {
                                            array2[n54] = (((n61 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n61 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n61 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        }
                                    }
                                }
                                else if (n == 3) {
                                    final byte b11 = this.aByteArray6215[n53];
                                    final int n67 = (b11 > 0) ? this.anIntArray6214[b11] : 0;
                                    final int anInt5460 = Class332_Sub3.anInt5432;
                                    final int n68 = n67 + anInt5460;
                                    final int n69 = (n67 & 0xFF00FF) + (anInt5460 & 0xFF00FF);
                                    final int n70 = (n69 & 0x1000100) + (n68 - n69 & 0x10000);
                                    int n71 = n68 - n70 | n70 - (n70 >>> 8);
                                    if (n67 == 0 && Class332_Sub3.anInt5449 != 255) {
                                        final int n72 = n71;
                                        final int n73 = array2[n54];
                                        n71 = ((n72 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n73 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n72 & 0xFF00) * Class332_Sub3.anInt5449 + (n73 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                    }
                                    array2[n54] = n71;
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final byte b12 = this.aByteArray6215[n53];
                                    if (b12 != 0) {
                                        final int n74 = this.anIntArray6214[b12 & 0xFF];
                                        array2[n54++] = ((((n74 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n74 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    }
                                }
                            }
                            else {
                                if (n2 != 2) {
                                    throw new IllegalArgumentException();
                                }
                                if (n == 1) {
                                    final byte b13 = this.aByteArray6215[n53];
                                    if (b13 != 0) {
                                        final int n75 = this.anIntArray6214[b13 & 0xFF];
                                        final int n76 = array2[n54];
                                        final int n77 = n75 + n76;
                                        final int n78 = (n75 & 0xFF00FF) + (n76 & 0xFF00FF);
                                        final int n79 = (n78 & 0x1000100) + (n77 - n78 & 0x10000);
                                        array2[n54] = (n77 - n79 | n79 - (n79 >>> 8));
                                    }
                                }
                                else if (n == 0) {
                                    final byte b14 = this.aByteArray6215[n53];
                                    if (b14 != 0) {
                                        final int n80 = this.anIntArray6214[b14 & 0xFF];
                                        final int n81 = (((n80 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n80 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n80 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        final int n82 = array2[n54];
                                        final int n83 = n81 + n82;
                                        final int n84 = (n81 & 0xFF00FF) + (n82 & 0xFF00FF);
                                        final int n85 = (n84 & 0x1000100) + (n83 - n84 & 0x10000);
                                        array2[n54] = (n83 - n85 | n85 - (n85 >>> 8));
                                    }
                                }
                                else if (n == 3) {
                                    final byte b15 = this.aByteArray6215[n53];
                                    final int n86 = (b15 > 0) ? this.anIntArray6214[b15] : 0;
                                    final int anInt5461 = Class332_Sub3.anInt5432;
                                    final int n87 = n86 + anInt5461;
                                    final int n88 = (n86 & 0xFF00FF) + (anInt5461 & 0xFF00FF);
                                    final int n89 = (n88 & 0x1000100) + (n87 - n88 & 0x10000);
                                    int n90 = n87 - n89 | n89 - (n89 >>> 8);
                                    if (n86 == 0 && Class332_Sub3.anInt5449 != 255) {
                                        final int n91 = n90;
                                        final int n92 = array2[n54];
                                        n90 = ((n91 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n92 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n91 & 0xFF00) * Class332_Sub3.anInt5449 + (n92 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                    }
                                    array2[n54] = n90;
                                }
                                else if (n == 2) {
                                    final byte b16 = this.aByteArray6215[n53];
                                    if (b16 != 0) {
                                        final int n93 = this.anIntArray6214[b16 & 0xFF];
                                        final int n94 = ((((n93 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n93 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                        final int n95 = array2[n54];
                                        final int n96 = n94 + n95;
                                        final int n97 = (n94 & 0xFF00FF) + (n95 & 0xFF00FF);
                                        final int n98 = (n97 & 0x1000100) + (n96 - n97 & 0x10000);
                                        array2[n54] = (n96 - n98 | n98 - (n98 >>> 8));
                                    }
                                }
                            }
                            n49 += Class332_Sub3.anInt5436;
                            ++l;
                        }
                    }
                }
            }
            else {
                for (int anInt5462 = Class332_Sub3.anInt5431; anInt5462 < 0; ++anInt5462, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    int anInt5463 = Class332_Sub3.anInt5451;
                    final int anInt5464 = Class332_Sub3.anInt5450;
                    int n99 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                    int anInt5465 = Class332_Sub3.anInt5448;
                    if (anInt5464 >= 0 && anInt5464 - (super.anInt5433 << 12) < 0) {
                        if (n99 < 0) {
                            final int n100 = (Class332_Sub3.anInt5436 - 1 - n99) / Class332_Sub3.anInt5436;
                            anInt5465 += n100;
                            n99 += Class332_Sub3.anInt5436 * n100;
                            anInt5463 += n100;
                        }
                        final int n101;
                        if ((n101 = (1 + n99 - (super.anInt5454 << 12) - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5465) {
                            anInt5465 = n101;
                        }
                        while (anInt5465 < 0) {
                            final int n102 = (n99 >> 12) * super.anInt5433 + (anInt5464 >> 12);
                            int n103 = anInt5463++;
                            final int[] array3 = anIntArray4504;
                            if (n2 == 0) {
                                if (n == 1) {
                                    array3[n103] = this.anIntArray6214[this.aByteArray6215[n102] & 0xFF];
                                }
                                else if (n == 0) {
                                    final int n104 = this.anIntArray6214[this.aByteArray6215[n102] & 0xFF];
                                    array3[n103] = (((n104 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n104 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n104 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                }
                                else if (n == 3) {
                                    final int n105 = this.anIntArray6214[this.aByteArray6215[n102] & 0xFF];
                                    final int anInt5466 = Class332_Sub3.anInt5432;
                                    final int n106 = n105 + anInt5466;
                                    final int n107 = (n105 & 0xFF00FF) + (anInt5466 & 0xFF00FF);
                                    final int n108 = (n107 & 0x1000100) + (n106 - n107 & 0x10000);
                                    array3[n103] = (n106 - n108 | n108 - (n108 >>> 8));
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final int n109 = this.anIntArray6214[this.aByteArray6215[n102] & 0xFF];
                                    array3[n103] = ((((n109 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n109 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                }
                            }
                            else if (n2 == 1) {
                                if (n == 1) {
                                    final byte b17 = this.aByteArray6215[n102];
                                    if (b17 != 0) {
                                        array3[n103] = this.anIntArray6214[b17 & 0xFF];
                                    }
                                }
                                else if (n == 0) {
                                    final byte b18 = this.aByteArray6215[n102];
                                    if (b18 != 0) {
                                        final int n110 = this.anIntArray6214[b18 & 0xFF];
                                        if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                            final int n111 = Class332_Sub3.anInt5432 >>> 24;
                                            final int n112 = 256 - n111;
                                            final int n113 = array3[n103];
                                            array3[n103] = ((n110 & 0xFF00FF) * n111 + (n113 & 0xFF00FF) * n112 & 0xFF00FF00) + ((n110 & 0xFF00) * n111 + (n113 & 0xFF00) * n112 & 0xFF0000) >> 8;
                                        }
                                        else if (Class332_Sub3.anInt5449 != 255) {
                                            final int n114 = (((n110 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n110 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n110 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                            final int n115 = array3[n103];
                                            array3[n103] = ((n114 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n115 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n114 & 0xFF00) * Class332_Sub3.anInt5449 + (n115 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                        }
                                        else {
                                            array3[n103] = (((n110 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n110 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n110 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        }
                                    }
                                }
                                else if (n == 3) {
                                    final byte b19 = this.aByteArray6215[n102];
                                    final int n116 = (b19 > 0) ? this.anIntArray6214[b19] : 0;
                                    final int anInt5467 = Class332_Sub3.anInt5432;
                                    final int n117 = n116 + anInt5467;
                                    final int n118 = (n116 & 0xFF00FF) + (anInt5467 & 0xFF00FF);
                                    final int n119 = (n118 & 0x1000100) + (n117 - n118 & 0x10000);
                                    int n120 = n117 - n119 | n119 - (n119 >>> 8);
                                    if (n116 == 0 && Class332_Sub3.anInt5449 != 255) {
                                        final int n121 = n120;
                                        final int n122 = array3[n103];
                                        n120 = ((n121 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n122 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n121 & 0xFF00) * Class332_Sub3.anInt5449 + (n122 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                    }
                                    array3[n103] = n120;
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final byte b20 = this.aByteArray6215[n102];
                                    if (b20 != 0) {
                                        final int n123 = this.anIntArray6214[b20 & 0xFF];
                                        array3[n103++] = ((((n123 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n123 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    }
                                }
                            }
                            else {
                                if (n2 != 2) {
                                    throw new IllegalArgumentException();
                                }
                                if (n == 1) {
                                    final byte b21 = this.aByteArray6215[n102];
                                    if (b21 != 0) {
                                        final int n124 = this.anIntArray6214[b21 & 0xFF];
                                        final int n125 = array3[n103];
                                        final int n126 = n124 + n125;
                                        final int n127 = (n124 & 0xFF00FF) + (n125 & 0xFF00FF);
                                        final int n128 = (n127 & 0x1000100) + (n126 - n127 & 0x10000);
                                        array3[n103] = (n126 - n128 | n128 - (n128 >>> 8));
                                    }
                                }
                                else if (n == 0) {
                                    final byte b22 = this.aByteArray6215[n102];
                                    if (b22 != 0) {
                                        final int n129 = this.anIntArray6214[b22 & 0xFF];
                                        final int n130 = (((n129 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n129 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n129 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        final int n131 = array3[n103];
                                        final int n132 = n130 + n131;
                                        final int n133 = (n130 & 0xFF00FF) + (n131 & 0xFF00FF);
                                        final int n134 = (n133 & 0x1000100) + (n132 - n133 & 0x10000);
                                        array3[n103] = (n132 - n134 | n134 - (n134 >>> 8));
                                    }
                                }
                                else if (n == 3) {
                                    final byte b23 = this.aByteArray6215[n102];
                                    final int n135 = (b23 > 0) ? this.anIntArray6214[b23] : 0;
                                    final int anInt5468 = Class332_Sub3.anInt5432;
                                    final int n136 = n135 + anInt5468;
                                    final int n137 = (n135 & 0xFF00FF) + (anInt5468 & 0xFF00FF);
                                    final int n138 = (n137 & 0x1000100) + (n136 - n137 & 0x10000);
                                    int n139 = n136 - n138 | n138 - (n138 >>> 8);
                                    if (n135 == 0 && Class332_Sub3.anInt5449 != 255) {
                                        final int n140 = n139;
                                        final int n141 = array3[n103];
                                        n139 = ((n140 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n141 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n140 & 0xFF00) * Class332_Sub3.anInt5449 + (n141 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                    }
                                    array3[n103] = n139;
                                }
                                else if (n == 2) {
                                    final byte b24 = this.aByteArray6215[n102];
                                    if (b24 != 0) {
                                        final int n142 = this.anIntArray6214[b24 & 0xFF];
                                        final int n143 = ((((n142 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n142 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                        final int n144 = array3[n103];
                                        final int n145 = n143 + n144;
                                        final int n146 = (n143 & 0xFF00FF) + (n144 & 0xFF00FF);
                                        final int n147 = (n146 & 0x1000100) + (n145 - n146 & 0x10000);
                                        array3[n103] = (n145 - n147 | n147 - (n147 >>> 8));
                                    }
                                }
                            }
                            n99 += Class332_Sub3.anInt5436;
                            ++anInt5465;
                        }
                    }
                }
            }
        }
        else if (Class332_Sub3.anInt5444 < 0) {
            if (Class332_Sub3.anInt5436 == 0) {
                for (int anInt5469 = Class332_Sub3.anInt5431; anInt5469 < 0; ++anInt5469, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    int anInt5470 = Class332_Sub3.anInt5451;
                    int n148 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                    final int anInt5471 = Class332_Sub3.anInt5443;
                    int anInt5472 = Class332_Sub3.anInt5448;
                    if (anInt5471 >= 0 && anInt5471 - (super.anInt5454 << 12) < 0) {
                        final int n149;
                        if ((n149 = n148 - (super.anInt5433 << 12)) >= 0) {
                            final int n150 = (Class332_Sub3.anInt5444 - n149) / Class332_Sub3.anInt5444;
                            anInt5472 += n150;
                            n148 += Class332_Sub3.anInt5444 * n150;
                            anInt5470 += n150;
                        }
                        final int n151;
                        if ((n151 = (n148 - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5472) {
                            anInt5472 = n151;
                        }
                        while (anInt5472 < 0) {
                            final int n152 = (anInt5471 >> 12) * super.anInt5433 + (n148 >> 12);
                            int n153 = anInt5470++;
                            final int[] array4 = anIntArray4504;
                            if (n2 == 0) {
                                if (n == 1) {
                                    array4[n153] = this.anIntArray6214[this.aByteArray6215[n152] & 0xFF];
                                }
                                else if (n == 0) {
                                    final int n154 = this.anIntArray6214[this.aByteArray6215[n152] & 0xFF];
                                    array4[n153] = (((n154 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n154 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n154 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                }
                                else if (n == 3) {
                                    final int n155 = this.anIntArray6214[this.aByteArray6215[n152] & 0xFF];
                                    final int anInt5473 = Class332_Sub3.anInt5432;
                                    final int n156 = n155 + anInt5473;
                                    final int n157 = (n155 & 0xFF00FF) + (anInt5473 & 0xFF00FF);
                                    final int n158 = (n157 & 0x1000100) + (n156 - n157 & 0x10000);
                                    array4[n153] = (n156 - n158 | n158 - (n158 >>> 8));
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final int n159 = this.anIntArray6214[this.aByteArray6215[n152] & 0xFF];
                                    array4[n153] = ((((n159 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n159 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                }
                            }
                            else if (n2 == 1) {
                                if (n == 1) {
                                    final byte b25 = this.aByteArray6215[n152];
                                    if (b25 != 0) {
                                        array4[n153] = this.anIntArray6214[b25 & 0xFF];
                                    }
                                }
                                else if (n == 0) {
                                    final byte b26 = this.aByteArray6215[n152];
                                    if (b26 != 0) {
                                        final int n160 = this.anIntArray6214[b26 & 0xFF];
                                        if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                            final int n161 = Class332_Sub3.anInt5432 >>> 24;
                                            final int n162 = 256 - n161;
                                            final int n163 = array4[n153];
                                            array4[n153] = ((n160 & 0xFF00FF) * n161 + (n163 & 0xFF00FF) * n162 & 0xFF00FF00) + ((n160 & 0xFF00) * n161 + (n163 & 0xFF00) * n162 & 0xFF0000) >> 8;
                                        }
                                        else if (Class332_Sub3.anInt5449 != 255) {
                                            final int n164 = (((n160 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n160 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n160 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                            final int n165 = array4[n153];
                                            array4[n153] = ((n164 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n165 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n164 & 0xFF00) * Class332_Sub3.anInt5449 + (n165 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                        }
                                        else {
                                            array4[n153] = (((n160 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n160 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n160 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        }
                                    }
                                }
                                else if (n == 3) {
                                    final byte b27 = this.aByteArray6215[n152];
                                    final int n166 = (b27 > 0) ? this.anIntArray6214[b27] : 0;
                                    final int anInt5474 = Class332_Sub3.anInt5432;
                                    final int n167 = n166 + anInt5474;
                                    final int n168 = (n166 & 0xFF00FF) + (anInt5474 & 0xFF00FF);
                                    final int n169 = (n168 & 0x1000100) + (n167 - n168 & 0x10000);
                                    int n170 = n167 - n169 | n169 - (n169 >>> 8);
                                    if (n166 == 0 && Class332_Sub3.anInt5449 != 255) {
                                        final int n171 = n170;
                                        final int n172 = array4[n153];
                                        n170 = ((n171 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n172 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n171 & 0xFF00) * Class332_Sub3.anInt5449 + (n172 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                    }
                                    array4[n153] = n170;
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final byte b28 = this.aByteArray6215[n152];
                                    if (b28 != 0) {
                                        final int n173 = this.anIntArray6214[b28 & 0xFF];
                                        array4[n153++] = ((((n173 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n173 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    }
                                }
                            }
                            else {
                                if (n2 != 2) {
                                    throw new IllegalArgumentException();
                                }
                                if (n == 1) {
                                    final byte b29 = this.aByteArray6215[n152];
                                    if (b29 != 0) {
                                        final int n174 = this.anIntArray6214[b29 & 0xFF];
                                        final int n175 = array4[n153];
                                        final int n176 = n174 + n175;
                                        final int n177 = (n174 & 0xFF00FF) + (n175 & 0xFF00FF);
                                        final int n178 = (n177 & 0x1000100) + (n176 - n177 & 0x10000);
                                        array4[n153] = (n176 - n178 | n178 - (n178 >>> 8));
                                    }
                                }
                                else if (n == 0) {
                                    final byte b30 = this.aByteArray6215[n152];
                                    if (b30 != 0) {
                                        final int n179 = this.anIntArray6214[b30 & 0xFF];
                                        final int n180 = (((n179 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n179 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n179 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        final int n181 = array4[n153];
                                        final int n182 = n180 + n181;
                                        final int n183 = (n180 & 0xFF00FF) + (n181 & 0xFF00FF);
                                        final int n184 = (n183 & 0x1000100) + (n182 - n183 & 0x10000);
                                        array4[n153] = (n182 - n184 | n184 - (n184 >>> 8));
                                    }
                                }
                                else if (n == 3) {
                                    final byte b31 = this.aByteArray6215[n152];
                                    final int n185 = (b31 > 0) ? this.anIntArray6214[b31] : 0;
                                    final int anInt5475 = Class332_Sub3.anInt5432;
                                    final int n186 = n185 + anInt5475;
                                    final int n187 = (n185 & 0xFF00FF) + (anInt5475 & 0xFF00FF);
                                    final int n188 = (n187 & 0x1000100) + (n186 - n187 & 0x10000);
                                    int n189 = n186 - n188 | n188 - (n188 >>> 8);
                                    if (n185 == 0 && Class332_Sub3.anInt5449 != 255) {
                                        final int n190 = n189;
                                        final int n191 = array4[n153];
                                        n189 = ((n190 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n191 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n190 & 0xFF00) * Class332_Sub3.anInt5449 + (n191 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                    }
                                    array4[n153] = n189;
                                }
                                else if (n == 2) {
                                    final byte b32 = this.aByteArray6215[n152];
                                    if (b32 != 0) {
                                        final int n192 = this.anIntArray6214[b32 & 0xFF];
                                        final int n193 = ((((n192 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n192 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                        final int n194 = array4[n153];
                                        final int n195 = n193 + n194;
                                        final int n196 = (n193 & 0xFF00FF) + (n194 & 0xFF00FF);
                                        final int n197 = (n196 & 0x1000100) + (n195 - n196 & 0x10000);
                                        array4[n153] = (n195 - n197 | n197 - (n197 >>> 8));
                                    }
                                }
                            }
                            n148 += Class332_Sub3.anInt5444;
                            ++anInt5472;
                        }
                    }
                }
            }
            else if (Class332_Sub3.anInt5436 < 0) {
                for (int anInt5476 = Class332_Sub3.anInt5431; anInt5476 < 0; ++anInt5476, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    int anInt5477 = Class332_Sub3.anInt5451;
                    int n198 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                    int n199 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                    int anInt5478 = Class332_Sub3.anInt5448;
                    final int n200;
                    if ((n200 = n198 - (super.anInt5433 << 12)) >= 0) {
                        final int n201 = (Class332_Sub3.anInt5444 - n200) / Class332_Sub3.anInt5444;
                        anInt5478 += n201;
                        n198 += Class332_Sub3.anInt5444 * n201;
                        n199 += Class332_Sub3.anInt5436 * n201;
                        anInt5477 += n201;
                    }
                    final int n202;
                    if ((n202 = (n198 - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5478) {
                        anInt5478 = n202;
                    }
                    final int n203;
                    if ((n203 = n199 - (super.anInt5454 << 12)) >= 0) {
                        final int n204 = (Class332_Sub3.anInt5436 - n203) / Class332_Sub3.anInt5436;
                        anInt5478 += n204;
                        n198 += Class332_Sub3.anInt5444 * n204;
                        n199 += Class332_Sub3.anInt5436 * n204;
                        anInt5477 += n204;
                    }
                    final int n205;
                    if ((n205 = (n199 - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5478) {
                        anInt5478 = n205;
                    }
                    while (anInt5478 < 0) {
                        final int n206 = (n199 >> 12) * super.anInt5433 + (n198 >> 12);
                        int n207 = anInt5477++;
                        final int[] array5 = anIntArray4504;
                        if (n2 == 0) {
                            if (n == 1) {
                                array5[n207] = this.anIntArray6214[this.aByteArray6215[n206] & 0xFF];
                            }
                            else if (n == 0) {
                                final int n208 = this.anIntArray6214[this.aByteArray6215[n206] & 0xFF];
                                array5[n207] = (((n208 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n208 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n208 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                            }
                            else if (n == 3) {
                                final int n209 = this.anIntArray6214[this.aByteArray6215[n206] & 0xFF];
                                final int anInt5479 = Class332_Sub3.anInt5432;
                                final int n210 = n209 + anInt5479;
                                final int n211 = (n209 & 0xFF00FF) + (anInt5479 & 0xFF00FF);
                                final int n212 = (n211 & 0x1000100) + (n210 - n211 & 0x10000);
                                array5[n207] = (n210 - n212 | n212 - (n212 >>> 8));
                            }
                            else {
                                if (n != 2) {
                                    throw new IllegalArgumentException();
                                }
                                final int n213 = this.anIntArray6214[this.aByteArray6215[n206] & 0xFF];
                                array5[n207] = ((((n213 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n213 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                            }
                        }
                        else if (n2 == 1) {
                            if (n == 1) {
                                final byte b33 = this.aByteArray6215[n206];
                                if (b33 != 0) {
                                    array5[n207] = this.anIntArray6214[b33 & 0xFF];
                                }
                            }
                            else if (n == 0) {
                                final byte b34 = this.aByteArray6215[n206];
                                if (b34 != 0) {
                                    final int n214 = this.anIntArray6214[b34 & 0xFF];
                                    if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                        final int n215 = Class332_Sub3.anInt5432 >>> 24;
                                        final int n216 = 256 - n215;
                                        final int n217 = array5[n207];
                                        array5[n207] = ((n214 & 0xFF00FF) * n215 + (n217 & 0xFF00FF) * n216 & 0xFF00FF00) + ((n214 & 0xFF00) * n215 + (n217 & 0xFF00) * n216 & 0xFF0000) >> 8;
                                    }
                                    else if (Class332_Sub3.anInt5449 != 255) {
                                        final int n218 = (((n214 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n214 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n214 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        final int n219 = array5[n207];
                                        array5[n207] = ((n218 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n219 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n218 & 0xFF00) * Class332_Sub3.anInt5449 + (n219 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                    }
                                    else {
                                        array5[n207] = (((n214 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n214 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n214 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    }
                                }
                            }
                            else if (n == 3) {
                                final byte b35 = this.aByteArray6215[n206];
                                final int n220 = (b35 > 0) ? this.anIntArray6214[b35] : 0;
                                final int anInt5480 = Class332_Sub3.anInt5432;
                                final int n221 = n220 + anInt5480;
                                final int n222 = (n220 & 0xFF00FF) + (anInt5480 & 0xFF00FF);
                                final int n223 = (n222 & 0x1000100) + (n221 - n222 & 0x10000);
                                int n224 = n221 - n223 | n223 - (n223 >>> 8);
                                if (n220 == 0 && Class332_Sub3.anInt5449 != 255) {
                                    final int n225 = n224;
                                    final int n226 = array5[n207];
                                    n224 = ((n225 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n226 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n225 & 0xFF00) * Class332_Sub3.anInt5449 + (n226 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                }
                                array5[n207] = n224;
                            }
                            else {
                                if (n != 2) {
                                    throw new IllegalArgumentException();
                                }
                                final byte b36 = this.aByteArray6215[n206];
                                if (b36 != 0) {
                                    final int n227 = this.anIntArray6214[b36 & 0xFF];
                                    array5[n207++] = ((((n227 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n227 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                }
                            }
                        }
                        else {
                            if (n2 != 2) {
                                throw new IllegalArgumentException();
                            }
                            if (n == 1) {
                                final byte b37 = this.aByteArray6215[n206];
                                if (b37 != 0) {
                                    final int n228 = this.anIntArray6214[b37 & 0xFF];
                                    final int n229 = array5[n207];
                                    final int n230 = n228 + n229;
                                    final int n231 = (n228 & 0xFF00FF) + (n229 & 0xFF00FF);
                                    final int n232 = (n231 & 0x1000100) + (n230 - n231 & 0x10000);
                                    array5[n207] = (n230 - n232 | n232 - (n232 >>> 8));
                                }
                            }
                            else if (n == 0) {
                                final byte b38 = this.aByteArray6215[n206];
                                if (b38 != 0) {
                                    final int n233 = this.anIntArray6214[b38 & 0xFF];
                                    final int n234 = (((n233 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n233 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n233 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    final int n235 = array5[n207];
                                    final int n236 = n234 + n235;
                                    final int n237 = (n234 & 0xFF00FF) + (n235 & 0xFF00FF);
                                    final int n238 = (n237 & 0x1000100) + (n236 - n237 & 0x10000);
                                    array5[n207] = (n236 - n238 | n238 - (n238 >>> 8));
                                }
                            }
                            else if (n == 3) {
                                final byte b39 = this.aByteArray6215[n206];
                                final int n239 = (b39 > 0) ? this.anIntArray6214[b39] : 0;
                                final int anInt5481 = Class332_Sub3.anInt5432;
                                final int n240 = n239 + anInt5481;
                                final int n241 = (n239 & 0xFF00FF) + (anInt5481 & 0xFF00FF);
                                final int n242 = (n241 & 0x1000100) + (n240 - n241 & 0x10000);
                                int n243 = n240 - n242 | n242 - (n242 >>> 8);
                                if (n239 == 0 && Class332_Sub3.anInt5449 != 255) {
                                    final int n244 = n243;
                                    final int n245 = array5[n207];
                                    n243 = ((n244 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n245 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n244 & 0xFF00) * Class332_Sub3.anInt5449 + (n245 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                }
                                array5[n207] = n243;
                            }
                            else if (n == 2) {
                                final byte b40 = this.aByteArray6215[n206];
                                if (b40 != 0) {
                                    final int n246 = this.anIntArray6214[b40 & 0xFF];
                                    final int n247 = ((((n246 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n246 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    final int n248 = array5[n207];
                                    final int n249 = n247 + n248;
                                    final int n250 = (n247 & 0xFF00FF) + (n248 & 0xFF00FF);
                                    final int n251 = (n250 & 0x1000100) + (n249 - n250 & 0x10000);
                                    array5[n207] = (n249 - n251 | n251 - (n251 >>> 8));
                                }
                            }
                        }
                        n198 += Class332_Sub3.anInt5444;
                        n199 += Class332_Sub3.anInt5436;
                        ++anInt5478;
                    }
                }
            }
            else {
                for (int anInt5482 = Class332_Sub3.anInt5431; anInt5482 < 0; ++anInt5482, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    int anInt5483 = Class332_Sub3.anInt5451;
                    int n252 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                    int n253 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                    int anInt5484 = Class332_Sub3.anInt5448;
                    final int n254;
                    if ((n254 = n252 - (super.anInt5433 << 12)) >= 0) {
                        final int n255 = (Class332_Sub3.anInt5444 - n254) / Class332_Sub3.anInt5444;
                        anInt5484 += n255;
                        n252 += Class332_Sub3.anInt5444 * n255;
                        n253 += Class332_Sub3.anInt5436 * n255;
                        anInt5483 += n255;
                    }
                    final int n256;
                    if ((n256 = (n252 - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5484) {
                        anInt5484 = n256;
                    }
                    if (n253 < 0) {
                        final int n257 = (Class332_Sub3.anInt5436 - 1 - n253) / Class332_Sub3.anInt5436;
                        anInt5484 += n257;
                        n252 += Class332_Sub3.anInt5444 * n257;
                        n253 += Class332_Sub3.anInt5436 * n257;
                        anInt5483 += n257;
                    }
                    final int n258;
                    if ((n258 = (1 + n253 - (super.anInt5454 << 12) - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5484) {
                        anInt5484 = n258;
                    }
                    while (anInt5484 < 0) {
                        final int n259 = (n253 >> 12) * super.anInt5433 + (n252 >> 12);
                        int n260 = anInt5483++;
                        final int[] array6 = anIntArray4504;
                        if (n2 == 0) {
                            if (n == 1) {
                                array6[n260] = this.anIntArray6214[this.aByteArray6215[n259] & 0xFF];
                            }
                            else if (n == 0) {
                                final int n261 = this.anIntArray6214[this.aByteArray6215[n259] & 0xFF];
                                array6[n260] = (((n261 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n261 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n261 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                            }
                            else if (n == 3) {
                                final int n262 = this.anIntArray6214[this.aByteArray6215[n259] & 0xFF];
                                final int anInt5485 = Class332_Sub3.anInt5432;
                                final int n263 = n262 + anInt5485;
                                final int n264 = (n262 & 0xFF00FF) + (anInt5485 & 0xFF00FF);
                                final int n265 = (n264 & 0x1000100) + (n263 - n264 & 0x10000);
                                array6[n260] = (n263 - n265 | n265 - (n265 >>> 8));
                            }
                            else {
                                if (n != 2) {
                                    throw new IllegalArgumentException();
                                }
                                final int n266 = this.anIntArray6214[this.aByteArray6215[n259] & 0xFF];
                                array6[n260] = ((((n266 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n266 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                            }
                        }
                        else if (n2 == 1) {
                            if (n == 1) {
                                final byte b41 = this.aByteArray6215[n259];
                                if (b41 != 0) {
                                    array6[n260] = this.anIntArray6214[b41 & 0xFF];
                                }
                            }
                            else if (n == 0) {
                                final byte b42 = this.aByteArray6215[n259];
                                if (b42 != 0) {
                                    final int n267 = this.anIntArray6214[b42 & 0xFF];
                                    if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                        final int n268 = Class332_Sub3.anInt5432 >>> 24;
                                        final int n269 = 256 - n268;
                                        final int n270 = array6[n260];
                                        array6[n260] = ((n267 & 0xFF00FF) * n268 + (n270 & 0xFF00FF) * n269 & 0xFF00FF00) + ((n267 & 0xFF00) * n268 + (n270 & 0xFF00) * n269 & 0xFF0000) >> 8;
                                    }
                                    else if (Class332_Sub3.anInt5449 != 255) {
                                        final int n271 = (((n267 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n267 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n267 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        final int n272 = array6[n260];
                                        array6[n260] = ((n271 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n272 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n271 & 0xFF00) * Class332_Sub3.anInt5449 + (n272 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                    }
                                    else {
                                        array6[n260] = (((n267 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n267 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n267 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    }
                                }
                            }
                            else if (n == 3) {
                                final byte b43 = this.aByteArray6215[n259];
                                final int n273 = (b43 > 0) ? this.anIntArray6214[b43] : 0;
                                final int anInt5486 = Class332_Sub3.anInt5432;
                                final int n274 = n273 + anInt5486;
                                final int n275 = (n273 & 0xFF00FF) + (anInt5486 & 0xFF00FF);
                                final int n276 = (n275 & 0x1000100) + (n274 - n275 & 0x10000);
                                int n277 = n274 - n276 | n276 - (n276 >>> 8);
                                if (n273 == 0 && Class332_Sub3.anInt5449 != 255) {
                                    final int n278 = n277;
                                    final int n279 = array6[n260];
                                    n277 = ((n278 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n279 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n278 & 0xFF00) * Class332_Sub3.anInt5449 + (n279 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                }
                                array6[n260] = n277;
                            }
                            else {
                                if (n != 2) {
                                    throw new IllegalArgumentException();
                                }
                                final byte b44 = this.aByteArray6215[n259];
                                if (b44 != 0) {
                                    final int n280 = this.anIntArray6214[b44 & 0xFF];
                                    array6[n260++] = ((((n280 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n280 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                }
                            }
                        }
                        else {
                            if (n2 != 2) {
                                throw new IllegalArgumentException();
                            }
                            if (n == 1) {
                                final byte b45 = this.aByteArray6215[n259];
                                if (b45 != 0) {
                                    final int n281 = this.anIntArray6214[b45 & 0xFF];
                                    final int n282 = array6[n260];
                                    final int n283 = n281 + n282;
                                    final int n284 = (n281 & 0xFF00FF) + (n282 & 0xFF00FF);
                                    final int n285 = (n284 & 0x1000100) + (n283 - n284 & 0x10000);
                                    array6[n260] = (n283 - n285 | n285 - (n285 >>> 8));
                                }
                            }
                            else if (n == 0) {
                                final byte b46 = this.aByteArray6215[n259];
                                if (b46 != 0) {
                                    final int n286 = this.anIntArray6214[b46 & 0xFF];
                                    final int n287 = (((n286 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n286 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n286 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    final int n288 = array6[n260];
                                    final int n289 = n287 + n288;
                                    final int n290 = (n287 & 0xFF00FF) + (n288 & 0xFF00FF);
                                    final int n291 = (n290 & 0x1000100) + (n289 - n290 & 0x10000);
                                    array6[n260] = (n289 - n291 | n291 - (n291 >>> 8));
                                }
                            }
                            else if (n == 3) {
                                final byte b47 = this.aByteArray6215[n259];
                                final int n292 = (b47 > 0) ? this.anIntArray6214[b47] : 0;
                                final int anInt5487 = Class332_Sub3.anInt5432;
                                final int n293 = n292 + anInt5487;
                                final int n294 = (n292 & 0xFF00FF) + (anInt5487 & 0xFF00FF);
                                final int n295 = (n294 & 0x1000100) + (n293 - n294 & 0x10000);
                                int n296 = n293 - n295 | n295 - (n295 >>> 8);
                                if (n292 == 0 && Class332_Sub3.anInt5449 != 255) {
                                    final int n297 = n296;
                                    final int n298 = array6[n260];
                                    n296 = ((n297 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n298 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n297 & 0xFF00) * Class332_Sub3.anInt5449 + (n298 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                }
                                array6[n260] = n296;
                            }
                            else if (n == 2) {
                                final byte b48 = this.aByteArray6215[n259];
                                if (b48 != 0) {
                                    final int n299 = this.anIntArray6214[b48 & 0xFF];
                                    final int n300 = ((((n299 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n299 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    final int n301 = array6[n260];
                                    final int n302 = n300 + n301;
                                    final int n303 = (n300 & 0xFF00FF) + (n301 & 0xFF00FF);
                                    final int n304 = (n303 & 0x1000100) + (n302 - n303 & 0x10000);
                                    array6[n260] = (n302 - n304 | n304 - (n304 >>> 8));
                                }
                            }
                        }
                        n252 += Class332_Sub3.anInt5444;
                        n253 += Class332_Sub3.anInt5436;
                        ++anInt5484;
                    }
                }
            }
        }
        else if (Class332_Sub3.anInt5436 == 0) {
            for (int anInt5488 = Class332_Sub3.anInt5431; anInt5488 < 0; ++anInt5488, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                int anInt5489 = Class332_Sub3.anInt5451;
                int n305 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                final int anInt5490 = Class332_Sub3.anInt5443;
                int anInt5491 = Class332_Sub3.anInt5448;
                if (anInt5490 >= 0 && anInt5490 - (super.anInt5454 << 12) < 0) {
                    if (n305 < 0) {
                        final int n306 = (Class332_Sub3.anInt5444 - 1 - n305) / Class332_Sub3.anInt5444;
                        anInt5491 += n306;
                        n305 += Class332_Sub3.anInt5444 * n306;
                        anInt5489 += n306;
                    }
                    final int n307;
                    if ((n307 = (1 + n305 - (super.anInt5433 << 12) - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5491) {
                        anInt5491 = n307;
                    }
                    while (anInt5491 < 0) {
                        final int n308 = (anInt5490 >> 12) * super.anInt5433 + (n305 >> 12);
                        int n309 = anInt5489++;
                        final int[] array7 = anIntArray4504;
                        if (n2 == 0) {
                            if (n == 1) {
                                array7[n309] = this.anIntArray6214[this.aByteArray6215[n308] & 0xFF];
                            }
                            else if (n == 0) {
                                final int n310 = this.anIntArray6214[this.aByteArray6215[n308] & 0xFF];
                                array7[n309] = (((n310 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n310 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n310 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                            }
                            else if (n == 3) {
                                final int n311 = this.anIntArray6214[this.aByteArray6215[n308] & 0xFF];
                                final int anInt5492 = Class332_Sub3.anInt5432;
                                final int n312 = n311 + anInt5492;
                                final int n313 = (n311 & 0xFF00FF) + (anInt5492 & 0xFF00FF);
                                final int n314 = (n313 & 0x1000100) + (n312 - n313 & 0x10000);
                                array7[n309] = (n312 - n314 | n314 - (n314 >>> 8));
                            }
                            else {
                                if (n != 2) {
                                    throw new IllegalArgumentException();
                                }
                                final int n315 = this.anIntArray6214[this.aByteArray6215[n308] & 0xFF];
                                array7[n309] = ((((n315 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n315 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                            }
                        }
                        else if (n2 == 1) {
                            if (n == 1) {
                                final byte b49 = this.aByteArray6215[n308];
                                if (b49 != 0) {
                                    array7[n309] = this.anIntArray6214[b49 & 0xFF];
                                }
                            }
                            else if (n == 0) {
                                final byte b50 = this.aByteArray6215[n308];
                                if (b50 != 0) {
                                    final int n316 = this.anIntArray6214[b50 & 0xFF];
                                    if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                        final int n317 = Class332_Sub3.anInt5432 >>> 24;
                                        final int n318 = 256 - n317;
                                        final int n319 = array7[n309];
                                        array7[n309] = ((n316 & 0xFF00FF) * n317 + (n319 & 0xFF00FF) * n318 & 0xFF00FF00) + ((n316 & 0xFF00) * n317 + (n319 & 0xFF00) * n318 & 0xFF0000) >> 8;
                                    }
                                    else if (Class332_Sub3.anInt5449 != 255) {
                                        final int n320 = (((n316 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n316 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n316 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        final int n321 = array7[n309];
                                        array7[n309] = ((n320 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n321 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n320 & 0xFF00) * Class332_Sub3.anInt5449 + (n321 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                    }
                                    else {
                                        array7[n309] = (((n316 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n316 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n316 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    }
                                }
                            }
                            else if (n == 3) {
                                final byte b51 = this.aByteArray6215[n308];
                                final int n322 = (b51 > 0) ? this.anIntArray6214[b51] : 0;
                                final int anInt5493 = Class332_Sub3.anInt5432;
                                final int n323 = n322 + anInt5493;
                                final int n324 = (n322 & 0xFF00FF) + (anInt5493 & 0xFF00FF);
                                final int n325 = (n324 & 0x1000100) + (n323 - n324 & 0x10000);
                                int n326 = n323 - n325 | n325 - (n325 >>> 8);
                                if (n322 == 0 && Class332_Sub3.anInt5449 != 255) {
                                    final int n327 = n326;
                                    final int n328 = array7[n309];
                                    n326 = ((n327 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n328 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n327 & 0xFF00) * Class332_Sub3.anInt5449 + (n328 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                }
                                array7[n309] = n326;
                            }
                            else {
                                if (n != 2) {
                                    throw new IllegalArgumentException();
                                }
                                final byte b52 = this.aByteArray6215[n308];
                                if (b52 != 0) {
                                    final int n329 = this.anIntArray6214[b52 & 0xFF];
                                    array7[n309++] = ((((n329 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n329 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                }
                            }
                        }
                        else {
                            if (n2 != 2) {
                                throw new IllegalArgumentException();
                            }
                            if (n == 1) {
                                final byte b53 = this.aByteArray6215[n308];
                                if (b53 != 0) {
                                    final int n330 = this.anIntArray6214[b53 & 0xFF];
                                    final int n331 = array7[n309];
                                    final int n332 = n330 + n331;
                                    final int n333 = (n330 & 0xFF00FF) + (n331 & 0xFF00FF);
                                    final int n334 = (n333 & 0x1000100) + (n332 - n333 & 0x10000);
                                    array7[n309] = (n332 - n334 | n334 - (n334 >>> 8));
                                }
                            }
                            else if (n == 0) {
                                final byte b54 = this.aByteArray6215[n308];
                                if (b54 != 0) {
                                    final int n335 = this.anIntArray6214[b54 & 0xFF];
                                    final int n336 = (((n335 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n335 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n335 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    final int n337 = array7[n309];
                                    final int n338 = n336 + n337;
                                    final int n339 = (n336 & 0xFF00FF) + (n337 & 0xFF00FF);
                                    final int n340 = (n339 & 0x1000100) + (n338 - n339 & 0x10000);
                                    array7[n309] = (n338 - n340 | n340 - (n340 >>> 8));
                                }
                            }
                            else if (n == 3) {
                                final byte b55 = this.aByteArray6215[n308];
                                final int n341 = (b55 > 0) ? this.anIntArray6214[b55] : 0;
                                final int anInt5494 = Class332_Sub3.anInt5432;
                                final int n342 = n341 + anInt5494;
                                final int n343 = (n341 & 0xFF00FF) + (anInt5494 & 0xFF00FF);
                                final int n344 = (n343 & 0x1000100) + (n342 - n343 & 0x10000);
                                int n345 = n342 - n344 | n344 - (n344 >>> 8);
                                if (n341 == 0 && Class332_Sub3.anInt5449 != 255) {
                                    final int n346 = n345;
                                    final int n347 = array7[n309];
                                    n345 = ((n346 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n347 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n346 & 0xFF00) * Class332_Sub3.anInt5449 + (n347 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                }
                                array7[n309] = n345;
                            }
                            else if (n == 2) {
                                final byte b56 = this.aByteArray6215[n308];
                                if (b56 != 0) {
                                    final int n348 = this.anIntArray6214[b56 & 0xFF];
                                    final int n349 = ((((n348 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n348 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    final int n350 = array7[n309];
                                    final int n351 = n349 + n350;
                                    final int n352 = (n349 & 0xFF00FF) + (n350 & 0xFF00FF);
                                    final int n353 = (n352 & 0x1000100) + (n351 - n352 & 0x10000);
                                    array7[n309] = (n351 - n353 | n353 - (n353 >>> 8));
                                }
                            }
                        }
                        n305 += Class332_Sub3.anInt5444;
                        ++anInt5491;
                    }
                }
            }
        }
        else if (Class332_Sub3.anInt5436 < 0) {
            for (int anInt5495 = Class332_Sub3.anInt5431; anInt5495 < 0; ++anInt5495) {
                int anInt5496 = Class332_Sub3.anInt5451;
                int n354 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                int n355 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                int anInt5497 = Class332_Sub3.anInt5448;
                if (n354 < 0) {
                    final int n356 = (Class332_Sub3.anInt5444 - 1 - n354) / Class332_Sub3.anInt5444;
                    anInt5497 += n356;
                    n354 += Class332_Sub3.anInt5444 * n356;
                    n355 += Class332_Sub3.anInt5436 * n356;
                    anInt5496 += n356;
                }
                final int n357;
                if ((n357 = (1 + n354 - (super.anInt5433 << 12) - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5497) {
                    anInt5497 = n357;
                }
                final int n358;
                if ((n358 = n355 - (super.anInt5454 << 12)) >= 0) {
                    final int n359 = (Class332_Sub3.anInt5436 - n358) / Class332_Sub3.anInt5436;
                    anInt5497 += n359;
                    n354 += Class332_Sub3.anInt5444 * n359;
                    n355 += Class332_Sub3.anInt5436 * n359;
                    anInt5496 += n359;
                }
                final int n360;
                if ((n360 = (n355 - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5497) {
                    anInt5497 = n360;
                }
                while (anInt5497 < 0) {
                    final int n361 = (n355 >> 12) * super.anInt5433 + (n354 >> 12);
                    int n362 = anInt5496++;
                    final int[] array8 = anIntArray4504;
                    if (n2 == 0) {
                        if (n == 1) {
                            array8[n362] = this.anIntArray6214[this.aByteArray6215[n361] & 0xFF];
                        }
                        else if (n == 0) {
                            final int n363 = this.anIntArray6214[this.aByteArray6215[n361] & 0xFF];
                            array8[n362] = (((n363 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n363 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n363 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                        }
                        else if (n == 3) {
                            final int n364 = this.anIntArray6214[this.aByteArray6215[n361] & 0xFF];
                            final int anInt5498 = Class332_Sub3.anInt5432;
                            final int n365 = n364 + anInt5498;
                            final int n366 = (n364 & 0xFF00FF) + (anInt5498 & 0xFF00FF);
                            final int n367 = (n366 & 0x1000100) + (n365 - n366 & 0x10000);
                            array8[n362] = (n365 - n367 | n367 - (n367 >>> 8));
                        }
                        else {
                            if (n != 2) {
                                throw new IllegalArgumentException();
                            }
                            final int n368 = this.anIntArray6214[this.aByteArray6215[n361] & 0xFF];
                            array8[n362] = ((((n368 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n368 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                        }
                    }
                    else if (n2 == 1) {
                        if (n == 1) {
                            final byte b57 = this.aByteArray6215[n361];
                            if (b57 != 0) {
                                array8[n362] = this.anIntArray6214[b57 & 0xFF];
                            }
                        }
                        else if (n == 0) {
                            final byte b58 = this.aByteArray6215[n361];
                            if (b58 != 0) {
                                final int n369 = this.anIntArray6214[b58 & 0xFF];
                                if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                    final int n370 = Class332_Sub3.anInt5432 >>> 24;
                                    final int n371 = 256 - n370;
                                    final int n372 = array8[n362];
                                    array8[n362] = ((n369 & 0xFF00FF) * n370 + (n372 & 0xFF00FF) * n371 & 0xFF00FF00) + ((n369 & 0xFF00) * n370 + (n372 & 0xFF00) * n371 & 0xFF0000) >> 8;
                                }
                                else if (Class332_Sub3.anInt5449 != 255) {
                                    final int n373 = (((n369 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n369 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n369 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    final int n374 = array8[n362];
                                    array8[n362] = ((n373 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n374 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n373 & 0xFF00) * Class332_Sub3.anInt5449 + (n374 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                }
                                else {
                                    array8[n362] = (((n369 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n369 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n369 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                }
                            }
                        }
                        else if (n == 3) {
                            final byte b59 = this.aByteArray6215[n361];
                            final int n375 = (b59 > 0) ? this.anIntArray6214[b59] : 0;
                            final int anInt5499 = Class332_Sub3.anInt5432;
                            final int n376 = n375 + anInt5499;
                            final int n377 = (n375 & 0xFF00FF) + (anInt5499 & 0xFF00FF);
                            final int n378 = (n377 & 0x1000100) + (n376 - n377 & 0x10000);
                            int n379 = n376 - n378 | n378 - (n378 >>> 8);
                            if (n375 == 0 && Class332_Sub3.anInt5449 != 255) {
                                final int n380 = n379;
                                final int n381 = array8[n362];
                                n379 = ((n380 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n381 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n380 & 0xFF00) * Class332_Sub3.anInt5449 + (n381 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                            }
                            array8[n362] = n379;
                        }
                        else {
                            if (n != 2) {
                                throw new IllegalArgumentException();
                            }
                            final byte b60 = this.aByteArray6215[n361];
                            if (b60 != 0) {
                                final int n382 = this.anIntArray6214[b60 & 0xFF];
                                array8[n362++] = ((((n382 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n382 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                            }
                        }
                    }
                    else {
                        if (n2 != 2) {
                            throw new IllegalArgumentException();
                        }
                        if (n == 1) {
                            final byte b61 = this.aByteArray6215[n361];
                            if (b61 != 0) {
                                final int n383 = this.anIntArray6214[b61 & 0xFF];
                                final int n384 = array8[n362];
                                final int n385 = n383 + n384;
                                final int n386 = (n383 & 0xFF00FF) + (n384 & 0xFF00FF);
                                final int n387 = (n386 & 0x1000100) + (n385 - n386 & 0x10000);
                                array8[n362] = (n385 - n387 | n387 - (n387 >>> 8));
                            }
                        }
                        else if (n == 0) {
                            final byte b62 = this.aByteArray6215[n361];
                            if (b62 != 0) {
                                final int n388 = this.anIntArray6214[b62 & 0xFF];
                                final int n389 = (((n388 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n388 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n388 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                final int n390 = array8[n362];
                                final int n391 = n389 + n390;
                                final int n392 = (n389 & 0xFF00FF) + (n390 & 0xFF00FF);
                                final int n393 = (n392 & 0x1000100) + (n391 - n392 & 0x10000);
                                array8[n362] = (n391 - n393 | n393 - (n393 >>> 8));
                            }
                        }
                        else if (n == 3) {
                            final byte b63 = this.aByteArray6215[n361];
                            final int n394 = (b63 > 0) ? this.anIntArray6214[b63] : 0;
                            final int anInt5500 = Class332_Sub3.anInt5432;
                            final int n395 = n394 + anInt5500;
                            final int n396 = (n394 & 0xFF00FF) + (anInt5500 & 0xFF00FF);
                            final int n397 = (n396 & 0x1000100) + (n395 - n396 & 0x10000);
                            int n398 = n395 - n397 | n397 - (n397 >>> 8);
                            if (n394 == 0 && Class332_Sub3.anInt5449 != 255) {
                                final int n399 = n398;
                                final int n400 = array8[n362];
                                n398 = ((n399 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n400 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n399 & 0xFF00) * Class332_Sub3.anInt5449 + (n400 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                            }
                            array8[n362] = n398;
                        }
                        else if (n == 2) {
                            final byte b64 = this.aByteArray6215[n361];
                            if (b64 != 0) {
                                final int n401 = this.anIntArray6214[b64 & 0xFF];
                                final int n402 = ((((n401 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n401 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                final int n403 = array8[n362];
                                final int n404 = n402 + n403;
                                final int n405 = (n402 & 0xFF00FF) + (n403 & 0xFF00FF);
                                final int n406 = (n405 & 0x1000100) + (n404 - n405 & 0x10000);
                                array8[n362] = (n404 - n406 | n406 - (n406 >>> 8));
                            }
                        }
                    }
                    n354 += Class332_Sub3.anInt5444;
                    n355 += Class332_Sub3.anInt5436;
                    ++anInt5497;
                }
                Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441;
                Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428;
                Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438;
            }
        }
        else {
            for (int anInt5501 = Class332_Sub3.anInt5431; anInt5501 < 0; ++anInt5501) {
                int anInt5502 = Class332_Sub3.anInt5451;
                int n407 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                int n408 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                int anInt5503 = Class332_Sub3.anInt5448;
                if (n407 < 0) {
                    final int n409 = (Class332_Sub3.anInt5444 - 1 - n407) / Class332_Sub3.anInt5444;
                    anInt5503 += n409;
                    n407 += Class332_Sub3.anInt5444 * n409;
                    n408 += Class332_Sub3.anInt5436 * n409;
                    anInt5502 += n409;
                }
                final int n410;
                if ((n410 = (1 + n407 - (super.anInt5433 << 12) - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5503) {
                    anInt5503 = n410;
                }
                if (n408 < 0) {
                    final int n411 = (Class332_Sub3.anInt5436 - 1 - n408) / Class332_Sub3.anInt5436;
                    anInt5503 += n411;
                    n407 += Class332_Sub3.anInt5444 * n411;
                    n408 += Class332_Sub3.anInt5436 * n411;
                    anInt5502 += n411;
                }
                final int n412;
                if ((n412 = (1 + n408 - (super.anInt5454 << 12) - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5503) {
                    anInt5503 = n412;
                }
                while (anInt5503 < 0) {
                    final int n413 = (n408 >> 12) * super.anInt5433 + (n407 >> 12);
                    int n414 = anInt5502++;
                    final int[] array9 = anIntArray4504;
                    if (n2 == 0) {
                        if (n == 1) {
                            array9[n414] = this.anIntArray6214[this.aByteArray6215[n413] & 0xFF];
                        }
                        else if (n == 0) {
                            final int n415 = this.anIntArray6214[this.aByteArray6215[n413] & 0xFF];
                            array9[n414] = (((n415 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n415 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n415 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                        }
                        else if (n == 3) {
                            final int n416 = this.anIntArray6214[this.aByteArray6215[n413] & 0xFF];
                            final int anInt5504 = Class332_Sub3.anInt5432;
                            final int n417 = n416 + anInt5504;
                            final int n418 = (n416 & 0xFF00FF) + (anInt5504 & 0xFF00FF);
                            final int n419 = (n418 & 0x1000100) + (n417 - n418 & 0x10000);
                            array9[n414] = (n417 - n419 | n419 - (n419 >>> 8));
                        }
                        else {
                            if (n != 2) {
                                throw new IllegalArgumentException();
                            }
                            final int n420 = this.anIntArray6214[this.aByteArray6215[n413] & 0xFF];
                            array9[n414] = ((((n420 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n420 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                        }
                    }
                    else if (n2 == 1) {
                        if (n == 1) {
                            final byte b65 = this.aByteArray6215[n413];
                            if (b65 != 0) {
                                array9[n414] = this.anIntArray6214[b65 & 0xFF];
                            }
                        }
                        else if (n == 0) {
                            final byte b66 = this.aByteArray6215[n413];
                            if (b66 != 0) {
                                final int n421 = this.anIntArray6214[b66 & 0xFF];
                                if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                    final int n422 = Class332_Sub3.anInt5432 >>> 24;
                                    final int n423 = 256 - n422;
                                    final int n424 = array9[n414];
                                    array9[n414] = ((n421 & 0xFF00FF) * n422 + (n424 & 0xFF00FF) * n423 & 0xFF00FF00) + ((n421 & 0xFF00) * n422 + (n424 & 0xFF00) * n423 & 0xFF0000) >> 8;
                                }
                                else if (Class332_Sub3.anInt5449 != 255) {
                                    final int n425 = (((n421 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n421 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n421 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    final int n426 = array9[n414];
                                    array9[n414] = ((n425 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n426 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n425 & 0xFF00) * Class332_Sub3.anInt5449 + (n426 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                }
                                else {
                                    array9[n414] = (((n421 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n421 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n421 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                }
                            }
                        }
                        else if (n == 3) {
                            final byte b67 = this.aByteArray6215[n413];
                            final int n427 = (b67 > 0) ? this.anIntArray6214[b67] : 0;
                            final int anInt5505 = Class332_Sub3.anInt5432;
                            final int n428 = n427 + anInt5505;
                            final int n429 = (n427 & 0xFF00FF) + (anInt5505 & 0xFF00FF);
                            final int n430 = (n429 & 0x1000100) + (n428 - n429 & 0x10000);
                            int n431 = n428 - n430 | n430 - (n430 >>> 8);
                            if (n427 == 0 && Class332_Sub3.anInt5449 != 255) {
                                final int n432 = n431;
                                final int n433 = array9[n414];
                                n431 = ((n432 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n433 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n432 & 0xFF00) * Class332_Sub3.anInt5449 + (n433 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                            }
                            array9[n414] = n431;
                        }
                        else {
                            if (n != 2) {
                                throw new IllegalArgumentException();
                            }
                            final byte b68 = this.aByteArray6215[n413];
                            if (b68 != 0) {
                                final int n434 = this.anIntArray6214[b68 & 0xFF];
                                array9[n414++] = ((((n434 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n434 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                            }
                        }
                    }
                    else {
                        if (n2 != 2) {
                            throw new IllegalArgumentException();
                        }
                        if (n == 1) {
                            final byte b69 = this.aByteArray6215[n413];
                            if (b69 != 0) {
                                final int n435 = this.anIntArray6214[b69 & 0xFF];
                                final int n436 = array9[n414];
                                final int n437 = n435 + n436;
                                final int n438 = (n435 & 0xFF00FF) + (n436 & 0xFF00FF);
                                final int n439 = (n438 & 0x1000100) + (n437 - n438 & 0x10000);
                                array9[n414] = (n437 - n439 | n439 - (n439 >>> 8));
                            }
                        }
                        else if (n == 0) {
                            final byte b70 = this.aByteArray6215[n413];
                            if (b70 != 0) {
                                final int n440 = this.anIntArray6214[b70 & 0xFF];
                                final int n441 = (((n440 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n440 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n440 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                final int n442 = array9[n414];
                                final int n443 = n441 + n442;
                                final int n444 = (n441 & 0xFF00FF) + (n442 & 0xFF00FF);
                                final int n445 = (n444 & 0x1000100) + (n443 - n444 & 0x10000);
                                array9[n414] = (n443 - n445 | n445 - (n445 >>> 8));
                            }
                        }
                        else if (n == 3) {
                            final byte b71 = this.aByteArray6215[n413];
                            final int n446 = (b71 > 0) ? this.anIntArray6214[b71] : 0;
                            final int anInt5506 = Class332_Sub3.anInt5432;
                            final int n447 = n446 + anInt5506;
                            final int n448 = (n446 & 0xFF00FF) + (anInt5506 & 0xFF00FF);
                            final int n449 = (n448 & 0x1000100) + (n447 - n448 & 0x10000);
                            int n450 = n447 - n449 | n449 - (n449 >>> 8);
                            if (n446 == 0 && Class332_Sub3.anInt5449 != 255) {
                                final int n451 = n450;
                                final int n452 = array9[n414];
                                n450 = ((n451 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n452 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n451 & 0xFF00) * Class332_Sub3.anInt5449 + (n452 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                            }
                            array9[n414] = n450;
                        }
                        else if (n == 2) {
                            final byte b72 = this.aByteArray6215[n413];
                            if (b72 != 0) {
                                final int n453 = this.anIntArray6214[b72 & 0xFF];
                                final int n454 = ((((n453 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n453 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                final int n455 = array9[n414];
                                final int n456 = n454 + n455;
                                final int n457 = (n454 & 0xFF00FF) + (n455 & 0xFF00FF);
                                final int n458 = (n457 & 0x1000100) + (n456 - n457 & 0x10000);
                                array9[n414] = (n456 - n458 | n458 - (n458 >>> 8));
                            }
                        }
                    }
                    n407 += Class332_Sub3.anInt5444;
                    n408 += Class332_Sub3.anInt5436;
                    ++anInt5503;
                }
                Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441;
                Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428;
                Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438;
            }
        }
    }
    
    @Override
    final void method3748(int anInt4509, int anInt4510, final int n, int n2, final int n3) {
        if (super.aHa_Sub2_5434.method1920()) {
            throw new IllegalStateException();
        }
        final int anInt4511 = super.aHa_Sub2_5434.anInt4505;
        anInt4509 += super.anInt5446;
        anInt4510 += super.anInt5439;
        int i = anInt4510 * anInt4511 + anInt4509;
        int n4 = 0;
        int anInt4512 = super.anInt5454;
        int anInt4513 = super.anInt5433;
        int n5 = anInt4511 - anInt4513;
        int n6 = 0;
        if (anInt4510 < super.aHa_Sub2_5434.anInt4495) {
            final int n7 = super.aHa_Sub2_5434.anInt4495 - anInt4510;
            anInt4512 -= n7;
            anInt4510 = super.aHa_Sub2_5434.anInt4495;
            n4 += n7 * anInt4513;
            i += n7 * anInt4511;
        }
        if (anInt4510 + anInt4512 > super.aHa_Sub2_5434.anInt4492) {
            anInt4512 -= anInt4510 + anInt4512 - super.aHa_Sub2_5434.anInt4492;
        }
        if (anInt4509 < super.aHa_Sub2_5434.anInt4509) {
            final int n8 = super.aHa_Sub2_5434.anInt4509 - anInt4509;
            anInt4513 -= n8;
            anInt4509 = super.aHa_Sub2_5434.anInt4509;
            n4 += n8;
            i += n8;
            n6 += n8;
            n5 += n8;
        }
        if (anInt4509 + anInt4513 > super.aHa_Sub2_5434.anInt4507) {
            final int n9 = anInt4509 + anInt4513 - super.aHa_Sub2_5434.anInt4507;
            anInt4513 -= n9;
            n6 += n9;
            n5 += n9;
        }
        if (anInt4513 <= 0 || anInt4512 <= 0) {
            return;
        }
        final int[] anIntArray4504 = super.aHa_Sub2_5434.anIntArray4504;
        if (n3 == 0) {
            if (n == 1) {
                for (int j = -anInt4512; j < 0; ++j) {
                    int n10;
                    for (n10 = i + anInt4513 - 3; i < n10; anIntArray4504[i++] = this.anIntArray6214[this.aByteArray6215[n4++] & 0xFF], anIntArray4504[i++] = this.anIntArray6214[this.aByteArray6215[n4++] & 0xFF], anIntArray4504[i++] = this.anIntArray6214[this.aByteArray6215[n4++] & 0xFF], anIntArray4504[i++] = this.anIntArray6214[this.aByteArray6215[n4++] & 0xFF]) {}
                    for (n10 += 3; i < n10; anIntArray4504[i++] = this.anIntArray6214[this.aByteArray6215[n4++] & 0xFF]) {}
                    i += n5;
                    n4 += n6;
                }
                return;
            }
            if (n == 0) {
                final int n11 = (n2 & 0xFF0000) >> 16;
                final int n12 = (n2 & 0xFF00) >> 8;
                final int n13 = n2 & 0xFF;
                for (int k = -anInt4512; k < 0; ++k) {
                    for (int l = -anInt4513; l < 0; ++l) {
                        final int n14 = this.anIntArray6214[this.aByteArray6215[n4++] & 0xFF];
                        anIntArray4504[i++] = (((n14 & 0xFF0000) * n11 & 0xFF000000) | ((n14 & 0xFF00) * n12 & 0xFF0000) | ((n14 & 0xFF) * n13 & 0xFF00)) >>> 8;
                    }
                    i += n5;
                    n4 += n6;
                }
                return;
            }
            if (n == 3) {
                for (int n15 = -anInt4512; n15 < 0; ++n15) {
                    for (int n16 = -anInt4513; n16 < 0; ++n16) {
                        final int n17 = this.anIntArray6214[this.aByteArray6215[n4++] & 0xFF];
                        final int n18 = n17 + n2;
                        final int n19 = (n17 & 0xFF00FF) + (n2 & 0xFF00FF);
                        final int n20 = (n19 & 0x1000100) + (n18 - n19 & 0x10000);
                        anIntArray4504[i++] = (n18 - n20 | n20 - (n20 >>> 8));
                    }
                    i += n5;
                    n4 += n6;
                }
                return;
            }
            if (n == 2) {
                final int n21 = n2 >>> 24;
                final int n22 = 256 - n21;
                n2 = (((n2 & 0xFF00FF) * n22 & 0xFF00FF00) | ((n2 & 0xFF00) * n22 & 0xFF0000)) >>> 8;
                for (int n23 = -anInt4512; n23 < 0; ++n23) {
                    for (int n24 = -anInt4513; n24 < 0; ++n24) {
                        final int n25 = this.anIntArray6214[this.aByteArray6215[n4++] & 0xFF];
                        anIntArray4504[i++] = ((((n25 & 0xFF00FF) * n21 & 0xFF00FF00) | ((n25 & 0xFF00) * n21 & 0xFF0000)) >>> 8) + n2;
                    }
                    i += n5;
                    n4 += n6;
                }
                return;
            }
            throw new IllegalArgumentException();
        }
        else if (n3 == 1) {
            if (n == 1) {
                for (int n26 = -anInt4512; n26 < 0; ++n26) {
                    for (int n27 = -anInt4513; n27 < 0; ++n27) {
                        final byte b = this.aByteArray6215[n4++];
                        if (b != 0) {
                            final int n28 = this.anIntArray6214[b & 0xFF] | 0xFF000000;
                            final int n29 = 255;
                            final int n30 = 0;
                            final int n31 = anIntArray4504[i];
                            anIntArray4504[i++] = (((n28 & 0xFF00FF) * n29 + (n31 & 0xFF00FF) * n30 & 0xFF00FF00) >> 8) + (((n28 & 0xFF00FF00) >>> 8) * n29 + ((n31 & 0xFF00FF00) >>> 8) * n30 & 0xFF00FF00);
                        }
                        else {
                            ++i;
                        }
                    }
                    i += n5;
                    n4 += n6;
                }
                return;
            }
            if (n == 0) {
                if ((n2 & 0xFFFFFF) == 0xFFFFFF) {
                    final int n32 = n2 >>> 24;
                    final int n33 = 256 - n32;
                    for (int n34 = -anInt4512; n34 < 0; ++n34) {
                        for (int n35 = -anInt4513; n35 < 0; ++n35) {
                            final byte b2 = this.aByteArray6215[n4++];
                            if (b2 != 0) {
                                final int n36 = this.anIntArray6214[b2 & 0xFF];
                                final int n37 = anIntArray4504[i];
                                anIntArray4504[i++] = ((n36 & 0xFF00FF) * n32 + (n37 & 0xFF00FF) * n33 & 0xFF00FF00) + ((n36 & 0xFF00) * n32 + (n37 & 0xFF00) * n33 & 0xFF0000) >> 8;
                            }
                            else {
                                ++i;
                            }
                        }
                        i += n5;
                        n4 += n6;
                    }
                    return;
                }
                final int n38 = (n2 & 0xFF0000) >> 16;
                final int n39 = (n2 & 0xFF00) >> 8;
                final int n40 = n2 & 0xFF;
                final int n41 = n2 >>> 24;
                final int n42 = 256 - n41;
                for (int n43 = -anInt4512; n43 < 0; ++n43) {
                    for (int n44 = -anInt4513; n44 < 0; ++n44) {
                        final byte b3 = this.aByteArray6215[n4++];
                        if (b3 != 0) {
                            final int n45 = this.anIntArray6214[b3 & 0xFF];
                            if (n41 != 255) {
                                final int n46 = (((n45 & 0xFF0000) * n38 & 0xFF000000) | ((n45 & 0xFF00) * n39 & 0xFF0000) | ((n45 & 0xFF) * n40 & 0xFF00)) >>> 8;
                                final int n47 = anIntArray4504[i];
                                anIntArray4504[i++] = ((n46 & 0xFF00FF) * n41 + (n47 & 0xFF00FF) * n42 & 0xFF00FF00) + ((n46 & 0xFF00) * n41 + (n47 & 0xFF00) * n42 & 0xFF0000) >> 8;
                            }
                            else {
                                anIntArray4504[i++] = (((n45 & 0xFF0000) * n38 & 0xFF000000) | ((n45 & 0xFF00) * n39 & 0xFF0000) | ((n45 & 0xFF) * n40 & 0xFF00)) >>> 8;
                            }
                        }
                        else {
                            ++i;
                        }
                    }
                    i += n5;
                    n4 += n6;
                }
            }
            else {
                if (n == 3) {
                    final int n48 = n2 >>> 24;
                    final int n49 = 256 - n48;
                    for (int n50 = -anInt4512; n50 < 0; ++n50) {
                        for (int n51 = -anInt4513; n51 < 0; ++n51) {
                            final byte b4 = this.aByteArray6215[n4++];
                            final int n52 = (b4 > 0) ? this.anIntArray6214[b4] : 0;
                            final int n53 = n52 + n2;
                            final int n54 = (n52 & 0xFF00FF) + (n2 & 0xFF00FF);
                            final int n55 = (n54 & 0x1000100) + (n53 - n54 & 0x10000);
                            int n56 = n53 - n55 | n55 - (n55 >>> 8);
                            if (n52 == 0 && n48 != 255) {
                                final int n57 = n56;
                                final int n58 = anIntArray4504[i];
                                n56 = ((n57 & 0xFF00FF) * n48 + (n58 & 0xFF00FF) * n49 & 0xFF00FF00) + ((n57 & 0xFF00) * n48 + (n58 & 0xFF00) * n49 & 0xFF0000) >> 8;
                            }
                            anIntArray4504[i++] = n56;
                        }
                        i += n5;
                        n4 += n6;
                    }
                    return;
                }
                if (n == 2) {
                    final int n59 = n2 >>> 24;
                    final int n60 = 256 - n59;
                    n2 = (((n2 & 0xFF00FF) * n60 & 0xFF00FF00) | ((n2 & 0xFF00) * n60 & 0xFF0000)) >>> 8;
                    for (int n61 = -anInt4512; n61 < 0; ++n61) {
                        for (int n62 = -anInt4513; n62 < 0; ++n62) {
                            final byte b5 = this.aByteArray6215[n4++];
                            if (b5 != 0) {
                                final int n63 = this.anIntArray6214[b5 & 0xFF];
                                anIntArray4504[i++] = ((((n63 & 0xFF00FF) * n59 & 0xFF00FF00) | ((n63 & 0xFF00) * n59 & 0xFF0000)) >>> 8) + n2;
                            }
                            else {
                                ++i;
                            }
                        }
                        i += n5;
                        n4 += n6;
                    }
                    return;
                }
                throw new IllegalArgumentException();
            }
        }
        else {
            if (n3 != 2) {
                throw new IllegalArgumentException();
            }
            if (n == 1) {
                for (int n64 = -anInt4512; n64 < 0; ++n64) {
                    for (int n65 = -anInt4513; n65 < 0; ++n65) {
                        final byte b6 = this.aByteArray6215[n4++];
                        if (b6 != 0) {
                            final int n66 = this.anIntArray6214[b6 & 0xFF];
                            final int n67 = anIntArray4504[i];
                            final int n68 = n66 + n67;
                            final int n69 = (n66 & 0xFF00FF) + (n67 & 0xFF00FF);
                            final int n70 = (n69 & 0x1000100) + (n68 - n69 & 0x10000);
                            anIntArray4504[i++] = (n68 - n70 | n70 - (n70 >>> 8));
                        }
                        else {
                            ++i;
                        }
                    }
                    i += n5;
                    n4 += n6;
                }
                return;
            }
            if (n == 0) {
                final int n71 = (n2 & 0xFF0000) >> 16;
                final int n72 = (n2 & 0xFF00) >> 8;
                final int n73 = n2 & 0xFF;
                for (int n74 = -anInt4512; n74 < 0; ++n74) {
                    for (int n75 = -anInt4513; n75 < 0; ++n75) {
                        final byte b7 = this.aByteArray6215[n4++];
                        if (b7 != 0) {
                            final int n76 = this.anIntArray6214[b7 & 0xFF];
                            final int n77 = (((n76 & 0xFF0000) * n71 & 0xFF000000) | ((n76 & 0xFF00) * n72 & 0xFF0000) | ((n76 & 0xFF) * n73 & 0xFF00)) >>> 8;
                            final int n78 = anIntArray4504[i];
                            final int n79 = n77 + n78;
                            final int n80 = (n77 & 0xFF00FF) + (n78 & 0xFF00FF);
                            final int n81 = (n80 & 0x1000100) + (n79 - n80 & 0x10000);
                            anIntArray4504[i++] = (n79 - n81 | n81 - (n81 >>> 8));
                        }
                        else {
                            ++i;
                        }
                    }
                    i += n5;
                    n4 += n6;
                }
                return;
            }
            if (n == 3) {
                for (int n82 = -anInt4512; n82 < 0; ++n82) {
                    for (int n83 = -anInt4513; n83 < 0; ++n83) {
                        final byte b8 = this.aByteArray6215[n4++];
                        final int n84 = (b8 > 0) ? this.anIntArray6214[b8] : 0;
                        final int n85 = n84 + n2;
                        final int n86 = (n84 & 0xFF00FF) + (n2 & 0xFF00FF);
                        final int n87 = (n86 & 0x1000100) + (n85 - n86 & 0x10000);
                        final int n88 = n85 - n87 | n87 - (n87 >>> 8);
                        final int n89 = anIntArray4504[i];
                        final int n90 = n88 + n89;
                        final int n91 = (n88 & 0xFF00FF) + (n89 & 0xFF00FF);
                        final int n92 = (n91 & 0x1000100) + (n90 - n91 & 0x10000);
                        anIntArray4504[i++] = (n90 - n92 | n92 - (n92 >>> 8));
                    }
                    i += n5;
                    n4 += n6;
                }
                return;
            }
            if (n == 2) {
                final int n93 = n2 >>> 24;
                final int n94 = 256 - n93;
                n2 = (((n2 & 0xFF00FF) * n94 & 0xFF00FF00) | ((n2 & 0xFF00) * n94 & 0xFF0000)) >>> 8;
                for (int n95 = -anInt4512; n95 < 0; ++n95) {
                    for (int n96 = -anInt4513; n96 < 0; ++n96) {
                        final byte b9 = this.aByteArray6215[n4++];
                        if (b9 != 0) {
                            final int n97 = this.anIntArray6214[b9 & 0xFF];
                            final int n98 = ((((n97 & 0xFF00FF) * n93 & 0xFF00FF00) | ((n97 & 0xFF00) * n93 & 0xFF0000)) >>> 8) + n2;
                            final int n99 = anIntArray4504[i];
                            final int n100 = n98 + n99;
                            final int n101 = (n98 & 0xFF00FF) + (n99 & 0xFF00FF);
                            final int n102 = (n101 & 0x1000100) + (n100 - n101 & 0x10000);
                            anIntArray4504[i++] = (n100 - n102 | n102 - (n102 >>> 8));
                        }
                        else {
                            ++i;
                        }
                    }
                    i += n5;
                    n4 += n6;
                }
                return;
            }
            throw new IllegalArgumentException();
        }
    }
    
    @Override
    final void method3758(final int[] array, final int[] array2, final int n, final int n2) {
        final int[] anIntArray4504 = super.aHa_Sub2_5434.anIntArray4504;
        if (Class332_Sub3.anInt5444 == 0) {
            if (Class332_Sub3.anInt5436 == 0) {
                for (int i = Class332_Sub3.anInt5431; i < 0; ++i, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    final int n3 = i + n2;
                    if (n3 >= 0) {
                        if (n3 >= array.length) {
                            break;
                        }
                        int anInt5451 = Class332_Sub3.anInt5451;
                        int anInt5452 = Class332_Sub3.anInt5450;
                        int anInt5453 = Class332_Sub3.anInt5443;
                        int j = Class332_Sub3.anInt5448;
                        if (anInt5452 >= 0 && anInt5453 >= 0 && anInt5452 - (super.anInt5433 << 12) < 0 && anInt5453 - (super.anInt5454 << 12) < 0) {
                            final int n4 = array[n3] - n;
                            int n5 = -array2[n3];
                            final int n6 = n4 - (anInt5451 - Class332_Sub3.anInt5451);
                            if (n6 > 0) {
                                anInt5451 += n6;
                                j += n6;
                                anInt5452 += Class332_Sub3.anInt5444 * n6;
                                anInt5453 += Class332_Sub3.anInt5436 * n6;
                            }
                            else {
                                n5 -= n6;
                            }
                            if (j < n5) {
                                j = n5;
                            }
                            while (j < 0) {
                                final byte b = this.aByteArray6215[(anInt5453 >> 12) * super.anInt5433 + (anInt5452 >> 12)];
                                if (b != 0) {
                                    anIntArray4504[anInt5451++] = this.anIntArray6214[b & 0xFF];
                                }
                                else {
                                    ++anInt5451;
                                }
                                ++j;
                            }
                        }
                    }
                }
            }
            else if (Class332_Sub3.anInt5436 < 0) {
                for (int k = Class332_Sub3.anInt5431; k < 0; ++k, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    final int n7 = k + n2;
                    if (n7 >= 0) {
                        if (n7 >= array.length) {
                            break;
                        }
                        int anInt5454 = Class332_Sub3.anInt5451;
                        int anInt5455 = Class332_Sub3.anInt5450;
                        int n8 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                        int l = Class332_Sub3.anInt5448;
                        if (anInt5455 >= 0 && anInt5455 - (super.anInt5433 << 12) < 0) {
                            final int n9;
                            if ((n9 = n8 - (super.anInt5454 << 12)) >= 0) {
                                final int n10 = (Class332_Sub3.anInt5436 - n9) / Class332_Sub3.anInt5436;
                                l += n10;
                                n8 += Class332_Sub3.anInt5436 * n10;
                                anInt5454 += n10;
                            }
                            final int n11;
                            if ((n11 = (n8 - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > l) {
                                l = n11;
                            }
                            final int n12 = array[n7] - n;
                            int n13 = -array2[n7];
                            final int n14 = n12 - (anInt5454 - Class332_Sub3.anInt5451);
                            if (n14 > 0) {
                                anInt5454 += n14;
                                l += n14;
                                anInt5455 += Class332_Sub3.anInt5444 * n14;
                                n8 += Class332_Sub3.anInt5436 * n14;
                            }
                            else {
                                n13 -= n14;
                            }
                            if (l < n13) {
                                l = n13;
                            }
                            while (l < 0) {
                                final byte b2 = this.aByteArray6215[(n8 >> 12) * super.anInt5433 + (anInt5455 >> 12)];
                                if (b2 != 0) {
                                    anIntArray4504[anInt5454++] = this.anIntArray6214[b2 & 0xFF];
                                }
                                else {
                                    ++anInt5454;
                                }
                                n8 += Class332_Sub3.anInt5436;
                                ++l;
                            }
                        }
                    }
                }
            }
            else {
                for (int anInt5456 = Class332_Sub3.anInt5431; anInt5456 < 0; ++anInt5456, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    final int n15 = anInt5456 + n2;
                    if (n15 >= 0) {
                        if (n15 >= array.length) {
                            break;
                        }
                        int anInt5457 = Class332_Sub3.anInt5451;
                        int anInt5458 = Class332_Sub3.anInt5450;
                        int n16 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                        int anInt5459 = Class332_Sub3.anInt5448;
                        if (anInt5458 >= 0 && anInt5458 - (super.anInt5433 << 12) < 0) {
                            if (n16 < 0) {
                                final int n17 = (Class332_Sub3.anInt5436 - 1 - n16) / Class332_Sub3.anInt5436;
                                anInt5459 += n17;
                                n16 += Class332_Sub3.anInt5436 * n17;
                                anInt5457 += n17;
                            }
                            final int n18;
                            if ((n18 = (1 + n16 - (super.anInt5454 << 12) - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5459) {
                                anInt5459 = n18;
                            }
                            final int n19 = array[n15] - n;
                            int n20 = -array2[n15];
                            final int n21 = n19 - (anInt5457 - Class332_Sub3.anInt5451);
                            if (n21 > 0) {
                                anInt5457 += n21;
                                anInt5459 += n21;
                                anInt5458 += Class332_Sub3.anInt5444 * n21;
                                n16 += Class332_Sub3.anInt5436 * n21;
                            }
                            else {
                                n20 -= n21;
                            }
                            if (anInt5459 < n20) {
                                anInt5459 = n20;
                            }
                            while (anInt5459 < 0) {
                                final byte b3 = this.aByteArray6215[(n16 >> 12) * super.anInt5433 + (anInt5458 >> 12)];
                                if (b3 != 0) {
                                    anIntArray4504[anInt5457++] = this.anIntArray6214[b3 & 0xFF];
                                }
                                else {
                                    ++anInt5457;
                                }
                                n16 += Class332_Sub3.anInt5436;
                                ++anInt5459;
                            }
                        }
                    }
                }
            }
        }
        else if (Class332_Sub3.anInt5444 < 0) {
            if (Class332_Sub3.anInt5436 == 0) {
                for (int anInt5460 = Class332_Sub3.anInt5431; anInt5460 < 0; ++anInt5460, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    final int n22 = anInt5460 + n2;
                    if (n22 >= 0) {
                        if (n22 >= array.length) {
                            break;
                        }
                        int anInt5461 = Class332_Sub3.anInt5451;
                        int n23 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                        int anInt5462 = Class332_Sub3.anInt5443;
                        int anInt5463 = Class332_Sub3.anInt5448;
                        if (anInt5462 >= 0 && anInt5462 - (super.anInt5454 << 12) < 0) {
                            final int n24;
                            if ((n24 = n23 - (super.anInt5433 << 12)) >= 0) {
                                final int n25 = (Class332_Sub3.anInt5444 - n24) / Class332_Sub3.anInt5444;
                                anInt5463 += n25;
                                n23 += Class332_Sub3.anInt5444 * n25;
                                anInt5461 += n25;
                            }
                            final int n26;
                            if ((n26 = (n23 - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5463) {
                                anInt5463 = n26;
                            }
                            final int n27 = array[n22] - n;
                            int n28 = -array2[n22];
                            final int n29 = n27 - (anInt5461 - Class332_Sub3.anInt5451);
                            if (n29 > 0) {
                                anInt5461 += n29;
                                anInt5463 += n29;
                                n23 += Class332_Sub3.anInt5444 * n29;
                                anInt5462 += Class332_Sub3.anInt5436 * n29;
                            }
                            else {
                                n28 -= n29;
                            }
                            if (anInt5463 < n28) {
                                anInt5463 = n28;
                            }
                            while (anInt5463 < 0) {
                                final byte b4 = this.aByteArray6215[(anInt5462 >> 12) * super.anInt5433 + (n23 >> 12)];
                                if (b4 != 0) {
                                    anIntArray4504[anInt5461++] = this.anIntArray6214[b4 & 0xFF];
                                }
                                else {
                                    ++anInt5461;
                                }
                                n23 += Class332_Sub3.anInt5444;
                                ++anInt5463;
                            }
                        }
                    }
                }
            }
            else if (Class332_Sub3.anInt5436 < 0) {
                for (int anInt5464 = Class332_Sub3.anInt5431; anInt5464 < 0; ++anInt5464, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    final int n30 = anInt5464 + n2;
                    if (n30 >= 0) {
                        if (n30 >= array.length) {
                            break;
                        }
                        int anInt5465 = Class332_Sub3.anInt5451;
                        int n31 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                        int n32 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                        int anInt5466 = Class332_Sub3.anInt5448;
                        final int n33;
                        if ((n33 = n31 - (super.anInt5433 << 12)) >= 0) {
                            final int n34 = (Class332_Sub3.anInt5444 - n33) / Class332_Sub3.anInt5444;
                            anInt5466 += n34;
                            n31 += Class332_Sub3.anInt5444 * n34;
                            n32 += Class332_Sub3.anInt5436 * n34;
                            anInt5465 += n34;
                        }
                        final int n35;
                        if ((n35 = (n31 - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5466) {
                            anInt5466 = n35;
                        }
                        final int n36;
                        if ((n36 = n32 - (super.anInt5454 << 12)) >= 0) {
                            final int n37 = (Class332_Sub3.anInt5436 - n36) / Class332_Sub3.anInt5436;
                            anInt5466 += n37;
                            n31 += Class332_Sub3.anInt5444 * n37;
                            n32 += Class332_Sub3.anInt5436 * n37;
                            anInt5465 += n37;
                        }
                        final int n38;
                        if ((n38 = (n32 - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5466) {
                            anInt5466 = n38;
                        }
                        final int n39 = array[n30] - n;
                        int n40 = -array2[n30];
                        final int n41 = n39 - (anInt5465 - Class332_Sub3.anInt5451);
                        if (n41 > 0) {
                            anInt5465 += n41;
                            anInt5466 += n41;
                            n31 += Class332_Sub3.anInt5444 * n41;
                            n32 += Class332_Sub3.anInt5436 * n41;
                        }
                        else {
                            n40 -= n41;
                        }
                        if (anInt5466 < n40) {
                            anInt5466 = n40;
                        }
                        while (anInt5466 < 0) {
                            final byte b5 = this.aByteArray6215[(n32 >> 12) * super.anInt5433 + (n31 >> 12)];
                            if (b5 != 0) {
                                anIntArray4504[anInt5465++] = this.anIntArray6214[b5 & 0xFF];
                            }
                            else {
                                ++anInt5465;
                            }
                            n31 += Class332_Sub3.anInt5444;
                            n32 += Class332_Sub3.anInt5436;
                            ++anInt5466;
                        }
                    }
                }
            }
            else {
                for (int anInt5467 = Class332_Sub3.anInt5431; anInt5467 < 0; ++anInt5467, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    final int n42 = anInt5467 + n2;
                    if (n42 >= 0) {
                        if (n42 >= array.length) {
                            break;
                        }
                        int anInt5468 = Class332_Sub3.anInt5451;
                        int n43 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                        int n44 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                        int anInt5469 = Class332_Sub3.anInt5448;
                        final int n45;
                        if ((n45 = n43 - (super.anInt5433 << 12)) >= 0) {
                            final int n46 = (Class332_Sub3.anInt5444 - n45) / Class332_Sub3.anInt5444;
                            anInt5469 += n46;
                            n43 += Class332_Sub3.anInt5444 * n46;
                            n44 += Class332_Sub3.anInt5436 * n46;
                            anInt5468 += n46;
                        }
                        final int n47;
                        if ((n47 = (n43 - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5469) {
                            anInt5469 = n47;
                        }
                        if (n44 < 0) {
                            final int n48 = (Class332_Sub3.anInt5436 - 1 - n44) / Class332_Sub3.anInt5436;
                            anInt5469 += n48;
                            n43 += Class332_Sub3.anInt5444 * n48;
                            n44 += Class332_Sub3.anInt5436 * n48;
                            anInt5468 += n48;
                        }
                        final int n49;
                        if ((n49 = (1 + n44 - (super.anInt5454 << 12) - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5469) {
                            anInt5469 = n49;
                        }
                        final int n50 = array[n42] - n;
                        int n51 = -array2[n42];
                        final int n52 = n50 - (anInt5468 - Class332_Sub3.anInt5451);
                        if (n52 > 0) {
                            anInt5468 += n52;
                            anInt5469 += n52;
                            n43 += Class332_Sub3.anInt5444 * n52;
                            n44 += Class332_Sub3.anInt5436 * n52;
                        }
                        else {
                            n51 -= n52;
                        }
                        if (anInt5469 < n51) {
                            anInt5469 = n51;
                        }
                        while (anInt5469 < 0) {
                            final byte b6 = this.aByteArray6215[(n44 >> 12) * super.anInt5433 + (n43 >> 12)];
                            if (b6 != 0) {
                                anIntArray4504[anInt5468++] = this.anIntArray6214[b6 & 0xFF];
                            }
                            else {
                                ++anInt5468;
                            }
                            n43 += Class332_Sub3.anInt5444;
                            n44 += Class332_Sub3.anInt5436;
                            ++anInt5469;
                        }
                    }
                }
            }
        }
        else if (Class332_Sub3.anInt5436 == 0) {
            for (int anInt5470 = Class332_Sub3.anInt5431; anInt5470 < 0; ++anInt5470, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                final int n53 = anInt5470 + n2;
                if (n53 >= 0) {
                    if (n53 >= array.length) {
                        break;
                    }
                    int anInt5471 = Class332_Sub3.anInt5451;
                    int n54 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                    int anInt5472 = Class332_Sub3.anInt5443;
                    int anInt5473 = Class332_Sub3.anInt5448;
                    if (anInt5472 >= 0 && anInt5472 - (super.anInt5454 << 12) < 0) {
                        if (n54 < 0) {
                            final int n55 = (Class332_Sub3.anInt5444 - 1 - n54) / Class332_Sub3.anInt5444;
                            anInt5473 += n55;
                            n54 += Class332_Sub3.anInt5444 * n55;
                            anInt5471 += n55;
                        }
                        final int n56;
                        if ((n56 = (1 + n54 - (super.anInt5433 << 12) - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5473) {
                            anInt5473 = n56;
                        }
                        final int n57 = array[n53] - n;
                        int n58 = -array2[n53];
                        final int n59 = n57 - (anInt5471 - Class332_Sub3.anInt5451);
                        if (n59 > 0) {
                            anInt5471 += n59;
                            anInt5473 += n59;
                            n54 += Class332_Sub3.anInt5444 * n59;
                            anInt5472 += Class332_Sub3.anInt5436 * n59;
                        }
                        else {
                            n58 -= n59;
                        }
                        if (anInt5473 < n58) {
                            anInt5473 = n58;
                        }
                        while (anInt5473 < 0) {
                            final byte b7 = this.aByteArray6215[(anInt5472 >> 12) * super.anInt5433 + (n54 >> 12)];
                            if (b7 != 0) {
                                anIntArray4504[anInt5471++] = this.anIntArray6214[b7 & 0xFF];
                            }
                            else {
                                ++anInt5471;
                            }
                            n54 += Class332_Sub3.anInt5444;
                            ++anInt5473;
                        }
                    }
                }
            }
        }
        else if (Class332_Sub3.anInt5436 < 0) {
            for (int anInt5474 = Class332_Sub3.anInt5431; anInt5474 < 0; ++anInt5474, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                final int n60 = anInt5474 + n2;
                if (n60 >= 0) {
                    if (n60 >= array.length) {
                        break;
                    }
                    int anInt5475 = Class332_Sub3.anInt5451;
                    int n61 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                    int n62 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                    int anInt5476 = Class332_Sub3.anInt5448;
                    if (n61 < 0) {
                        final int n63 = (Class332_Sub3.anInt5444 - 1 - n61) / Class332_Sub3.anInt5444;
                        anInt5476 += n63;
                        n61 += Class332_Sub3.anInt5444 * n63;
                        n62 += Class332_Sub3.anInt5436 * n63;
                        anInt5475 += n63;
                    }
                    final int n64;
                    if ((n64 = (1 + n61 - (super.anInt5433 << 12) - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5476) {
                        anInt5476 = n64;
                    }
                    final int n65;
                    if ((n65 = n62 - (super.anInt5454 << 12)) >= 0) {
                        final int n66 = (Class332_Sub3.anInt5436 - n65) / Class332_Sub3.anInt5436;
                        anInt5476 += n66;
                        n61 += Class332_Sub3.anInt5444 * n66;
                        n62 += Class332_Sub3.anInt5436 * n66;
                        anInt5475 += n66;
                    }
                    final int n67;
                    if ((n67 = (n62 - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5476) {
                        anInt5476 = n67;
                    }
                    final int n68 = array[n60] - n;
                    int n69 = -array2[n60];
                    final int n70 = n68 - (anInt5475 - Class332_Sub3.anInt5451);
                    if (n70 > 0) {
                        anInt5475 += n70;
                        anInt5476 += n70;
                        n61 += Class332_Sub3.anInt5444 * n70;
                        n62 += Class332_Sub3.anInt5436 * n70;
                    }
                    else {
                        n69 -= n70;
                    }
                    if (anInt5476 < n69) {
                        anInt5476 = n69;
                    }
                    while (anInt5476 < 0) {
                        final byte b8 = this.aByteArray6215[(n62 >> 12) * super.anInt5433 + (n61 >> 12)];
                        if (b8 != 0) {
                            anIntArray4504[anInt5475++] = this.anIntArray6214[b8 & 0xFF];
                        }
                        else {
                            ++anInt5475;
                        }
                        n61 += Class332_Sub3.anInt5444;
                        n62 += Class332_Sub3.anInt5436;
                        ++anInt5476;
                    }
                }
            }
        }
        else {
            for (int anInt5477 = Class332_Sub3.anInt5431; anInt5477 < 0; ++anInt5477, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                final int n71 = anInt5477 + n2;
                if (n71 >= 0) {
                    if (n71 >= array.length) {
                        break;
                    }
                    int anInt5478 = Class332_Sub3.anInt5451;
                    int n72 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                    int n73 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                    int anInt5479 = Class332_Sub3.anInt5448;
                    if (n72 < 0) {
                        final int n74 = (Class332_Sub3.anInt5444 - 1 - n72) / Class332_Sub3.anInt5444;
                        anInt5479 += n74;
                        n72 += Class332_Sub3.anInt5444 * n74;
                        n73 += Class332_Sub3.anInt5436 * n74;
                        anInt5478 += n74;
                    }
                    final int n75;
                    if ((n75 = (1 + n72 - (super.anInt5433 << 12) - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5479) {
                        anInt5479 = n75;
                    }
                    if (n73 < 0) {
                        final int n76 = (Class332_Sub3.anInt5436 - 1 - n73) / Class332_Sub3.anInt5436;
                        anInt5479 += n76;
                        n72 += Class332_Sub3.anInt5444 * n76;
                        n73 += Class332_Sub3.anInt5436 * n76;
                        anInt5478 += n76;
                    }
                    final int n77;
                    if ((n77 = (1 + n73 - (super.anInt5454 << 12) - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5479) {
                        anInt5479 = n77;
                    }
                    final int n78 = array[n71] - n;
                    int n79 = -array2[n71];
                    final int n80 = n78 - (anInt5478 - Class332_Sub3.anInt5451);
                    if (n80 > 0) {
                        anInt5478 += n80;
                        anInt5479 += n80;
                        n72 += Class332_Sub3.anInt5444 * n80;
                        n73 += Class332_Sub3.anInt5436 * n80;
                    }
                    else {
                        n79 -= n80;
                    }
                    if (anInt5479 < n79) {
                        anInt5479 = n79;
                    }
                    while (anInt5479 < 0) {
                        final byte b9 = this.aByteArray6215[(n73 >> 12) * super.anInt5433 + (n72 >> 12)];
                        if (b9 != 0) {
                            anIntArray4504[anInt5478++] = this.anIntArray6214[b9 & 0xFF];
                        }
                        else {
                            ++anInt5478;
                        }
                        n72 += Class332_Sub3.anInt5444;
                        n73 += Class332_Sub3.anInt5436;
                        ++anInt5479;
                    }
                }
            }
        }
    }
}
