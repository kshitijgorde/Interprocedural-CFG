// 
// Decompiled by Procyon v0.5.30
// 

final class Class246_Sub4_Sub2_Sub1 extends Class246_Sub4_Sub2
{
    private short aShort6491;
    Class246_Sub9 aClass246_Sub9_6492;
    private short aShort6493;
    private short aShort6494;
    private int anInt6495;
    private short aShort6496;
    private short aShort6497;
    private int anInt6498;
    private short aShort6499;
    
    final void method3109(final long n, final int n2) {
        this.aShort6497 -= (short)n2;
        if (this.aShort6497 <= 0) {
            this.method3113();
        }
        else {
            final int n3 = super.anInt6176 >> 12;
            final int n4 = super.anInt6177 >> 12;
            final int n5 = super.anInt6175 >> 12;
            final Class246_Sub5 aClass246_Sub5_5133 = this.aClass246_Sub9_6492.aClass246_Sub5_5133;
            final Class92 aClass92_5132 = this.aClass246_Sub9_6492.aClass92_5132;
            if (aClass92_5132.anInt760 != 0) {
                if (this.aShort6493 - this.aShort6497 <= aClass92_5132.anInt761) {
                    int n6 = (super.anInt6178 >> 8 & 0xFF00) + (this.anInt6495 >> 16 & 0xFF) + aClass92_5132.anInt743 * n2;
                    int n7 = (super.anInt6178 & 0xFF00) + (this.anInt6495 >> 8 & 0xFF) + aClass92_5132.anInt733 * n2;
                    int n8 = (super.anInt6178 << 8 & 0xFF00) + (this.anInt6495 & 0xFF) + aClass92_5132.anInt727 * n2;
                    if (n6 < 0) {
                        n6 = 0;
                    }
                    else if (n6 > 65535) {
                        n6 = 65535;
                    }
                    if (n7 < 0) {
                        n7 = 0;
                    }
                    else if (n7 > 65535) {
                        n7 = 65535;
                    }
                    if (n8 < 0) {
                        n8 = 0;
                    }
                    else if (n8 > 65535) {
                        n8 = 65535;
                    }
                    super.anInt6178 &= 0xFF000000;
                    super.anInt6178 |= ((n6 & 0xFF00) << 8) + (n7 & 0xFF00) + ((n8 & 0xFF00) >> 8);
                    this.anInt6495 &= 0xFF000000;
                    this.anInt6495 |= ((n6 & 0xFF) << 16) + ((n7 & 0xFF) << 8) + (n8 & 0xFF);
                }
                if (this.aShort6493 - this.aShort6497 <= aClass92_5132.anInt758) {
                    int n9 = (super.anInt6178 >> 16 & 0xFF00) + (this.anInt6495 >> 24 & 0xFF) + aClass92_5132.anInt779 * n2;
                    if (n9 < 0) {
                        n9 = 0;
                    }
                    else if (n9 > 65535) {
                        n9 = 65535;
                    }
                    super.anInt6178 &= 0xFFFFFF;
                    super.anInt6178 |= (n9 & 0xFF00) << 16;
                    this.anInt6495 &= 0xFFFFFF;
                    this.anInt6495 |= (n9 & 0xFF) << 24;
                }
            }
            if (aClass92_5132.anInt745 != -1 && this.aShort6493 - this.aShort6497 <= aClass92_5132.anInt785) {
                this.anInt6498 += aClass92_5132.anInt781 * n2;
            }
            if (aClass92_5132.anInt775 != -1 && this.aShort6493 - this.aShort6497 <= aClass92_5132.anInt777) {
                super.anInt6179 += aClass92_5132.anInt742 * n2;
            }
            double n10 = this.aShort6496;
            double n11 = this.aShort6491;
            double n12 = this.aShort6494;
            boolean b = false;
            if (aClass92_5132.anInt739 == 1) {
                final int n13 = n3 - this.aClass246_Sub9_6492.aClass216_5136.anInt1617;
                final int n14 = n4 - this.aClass246_Sub9_6492.aClass216_5136.anInt1618;
                final int n15 = n5 - this.aClass246_Sub9_6492.aClass216_5136.anInt1621;
                this.anInt6498 -= this.anInt6498 * (aClass92_5132.anInt792 * ((int)Math.sqrt(n13 * n13 + n14 * n14 + n15 * n15) >> 2) * n2) >> 18;
            }
            else if (aClass92_5132.anInt739 == 2) {
                final int n16 = n3 - this.aClass246_Sub9_6492.aClass216_5136.anInt1617;
                final int n17 = n4 - this.aClass246_Sub9_6492.aClass216_5136.anInt1618;
                final int n18 = n5 - this.aClass246_Sub9_6492.aClass216_5136.anInt1621;
                this.anInt6498 -= this.anInt6498 * (aClass92_5132.anInt792 * (n16 * n16 + n17 * n17 + n18 * n18) * n2) >> 28;
            }
            if (aClass92_5132.anIntArray728 != null) {
                for (Class98 aClass98_1198 = aClass246_Sub5_5133.aClass148_5102.aClass98_1198, class98 = aClass98_1198.aClass98_836; class98 != aClass98_1198; class98 = class98.aClass98_836) {
                    final Class98_Sub46_Sub6 class98_Sub46_Sub6 = (Class98_Sub46_Sub6)class98;
                    final Class66 aClass66_5973 = class98_Sub46_Sub6.aClass66_5973;
                    if (aClass66_5973.anInt508 != 1) {
                        boolean b2 = false;
                        for (int i = 0; i < aClass92_5132.anIntArray728.length; ++i) {
                            if (aClass92_5132.anIntArray728[i] == aClass66_5973.anInt509) {
                                b2 = true;
                                break;
                            }
                        }
                        if (b2) {
                            final double n19 = n3 - class98_Sub46_Sub6.anInt5972;
                            final double n20 = n4 - class98_Sub46_Sub6.anInt5974;
                            final double n21 = n5 - class98_Sub46_Sub6.anInt5978;
                            final double n22 = n19 * n19 + n20 * n20 + n21 * n21;
                            if (n22 <= aClass66_5973.aLong516) {
                                double sqrt = Math.sqrt(n22);
                                if (sqrt == 0.0) {
                                    sqrt = 1.0;
                                }
                                if ((n19 * class98_Sub46_Sub6.anInt5976 + n20 * aClass66_5973.anInt511 + n21 * class98_Sub46_Sub6.anInt5977) * 65535.0 / (aClass66_5973.anInt517 * sqrt) >= aClass66_5973.anInt514) {
                                    double n23 = 0.0;
                                    if (aClass66_5973.anInt518 == 1) {
                                        n23 = sqrt / 16.0 * aClass66_5973.anInt512;
                                    }
                                    else if (aClass66_5973.anInt518 == 2) {
                                        n23 = sqrt / 16.0 * (sqrt / 16.0) * aClass66_5973.anInt512;
                                    }
                                    if (aClass66_5973.anInt515 == 0) {
                                        if (aClass66_5973.anInt513 == 0) {
                                            n10 += (class98_Sub46_Sub6.anInt5976 - n23) * n2;
                                            n11 += (aClass66_5973.anInt511 - n23) * n2;
                                            n12 += (class98_Sub46_Sub6.anInt5977 - n23) * n2;
                                            b = true;
                                        }
                                        else {
                                            super.anInt6176 += (int)((class98_Sub46_Sub6.anInt5976 - n23) * n2);
                                            super.anInt6177 += (int)((aClass66_5973.anInt511 - n23) * n2);
                                            super.anInt6175 += (int)((class98_Sub46_Sub6.anInt5977 - n23) * n2);
                                        }
                                    }
                                    else {
                                        final double n24 = n19 / sqrt * aClass66_5973.anInt517;
                                        final double n25 = n20 / sqrt * aClass66_5973.anInt517;
                                        final double n26 = n21 / sqrt * aClass66_5973.anInt517;
                                        if (aClass66_5973.anInt513 == 0) {
                                            n10 += n24 * n2;
                                            n11 += n25 * n2;
                                            n12 += n26 * n2;
                                            b = true;
                                        }
                                        else {
                                            super.anInt6176 += (int)(n24 * n2);
                                            super.anInt6177 += (int)(n25 * n2);
                                            super.anInt6175 += (int)(n26 * n2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (aClass92_5132.anIntArray735 != null) {
                for (int j = 0; j < aClass92_5132.anIntArray735.length; ++j) {
                    for (Class98_Sub46_Sub6 class98_Sub46_Sub7 = (Class98_Sub46_Sub6)Class246_Sub3_Sub3.aClass254_6152.method3189(aClass92_5132.anIntArray735[j], (byte)(-91)); class98_Sub46_Sub7 != null; class98_Sub46_Sub7 = (Class98_Sub46_Sub6)Class246_Sub3_Sub3.aClass254_6152.method3190(false)) {
                        final Class66 aClass66_5974 = class98_Sub46_Sub7.aClass66_5973;
                        final double n27 = n3 - class98_Sub46_Sub7.anInt5972;
                        final double n28 = n4 - class98_Sub46_Sub7.anInt5974;
                        final double n29 = n5 - class98_Sub46_Sub7.anInt5978;
                        final double n30 = n27 * n27 + n28 * n28 + n29 * n29;
                        if (n30 <= aClass66_5974.aLong516) {
                            double sqrt2 = Math.sqrt(n30);
                            if (sqrt2 == 0.0) {
                                sqrt2 = 1.0;
                            }
                            if ((n27 * class98_Sub46_Sub7.anInt5976 + n28 * aClass66_5974.anInt511 + n29 * class98_Sub46_Sub7.anInt5977) * 65535.0 / (aClass66_5974.anInt517 * sqrt2) >= aClass66_5974.anInt514) {
                                double n31 = 0.0;
                                if (aClass66_5974.anInt518 == 1) {
                                    n31 = sqrt2 / 16.0 * aClass66_5974.anInt512;
                                }
                                else if (aClass66_5974.anInt518 == 2) {
                                    n31 = sqrt2 / 16.0 * (sqrt2 / 16.0) * aClass66_5974.anInt512;
                                }
                                if (aClass66_5974.anInt515 == 0) {
                                    if (aClass66_5974.anInt513 == 0) {
                                        n10 += (class98_Sub46_Sub7.anInt5976 - n31) * n2;
                                        n11 += (aClass66_5974.anInt511 - n31) * n2;
                                        n12 += (class98_Sub46_Sub7.anInt5977 - n31) * n2;
                                        b = true;
                                    }
                                    else {
                                        super.anInt6176 += (int)((class98_Sub46_Sub7.anInt5976 - n31) * n2);
                                        super.anInt6177 += (int)((aClass66_5974.anInt511 - n31) * n2);
                                        super.anInt6175 += (int)((class98_Sub46_Sub7.anInt5977 - n31) * n2);
                                    }
                                }
                                else {
                                    final double n32 = n27 / sqrt2 * aClass66_5974.anInt517;
                                    final double n33 = n28 / sqrt2 * aClass66_5974.anInt517;
                                    final double n34 = n29 / sqrt2 * aClass66_5974.anInt517;
                                    if (aClass66_5974.anInt513 == 0) {
                                        n10 += n32 * n2;
                                        n11 += n33 * n2;
                                        n12 += n34 * n2;
                                        b = true;
                                    }
                                    else {
                                        super.anInt6176 += (int)(n32 * n2);
                                        super.anInt6177 += (int)(n33 * n2);
                                        super.anInt6175 += (int)(n34 * n2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (aClass92_5132.anIntArray772 != null) {
                if (aClass92_5132.anIntArray768 == null) {
                    aClass92_5132.anIntArray768 = new int[aClass92_5132.anIntArray772.length];
                    for (int k = 0; k < aClass92_5132.anIntArray772.length; ++k) {
                        Class21.method263(aClass92_5132.anIntArray772[k], 31866);
                        aClass92_5132.anIntArray768[k] = ((Class98_Sub34)Class294.aClass377_2397.method3990(aClass92_5132.anIntArray772[k], -1)).anInt4126;
                    }
                }
                for (int l = 0; l < aClass92_5132.anIntArray768.length; ++l) {
                    final Class66 class99 = Class336.aClass66Array2828[aClass92_5132.anIntArray768[l]];
                    if (class99.anInt513 == 0) {
                        n10 += class99.anInt506 * n2;
                        n11 += class99.anInt511 * n2;
                        n12 += class99.anInt505 * n2;
                        b = true;
                    }
                    else {
                        super.anInt6176 += class99.anInt506 * n2;
                        super.anInt6177 += class99.anInt511 * n2;
                        super.anInt6175 += class99.anInt505 * n2;
                    }
                }
            }
            if (b) {
                while (n10 > 32767.0 || n11 > 32767.0 || n12 > 32767.0 || n10 < -32767.0 || n11 < -32767.0 || n12 < -32767.0) {
                    n10 /= 2.0;
                    n11 /= 2.0;
                    n12 /= 2.0;
                    this.anInt6498 <<= 1;
                }
                this.aShort6496 = (short)n10;
                this.aShort6491 = (short)n11;
                this.aShort6494 = (short)n12;
            }
            super.anInt6176 += (this.aShort6496 * (this.anInt6498 << 2) >> 23) * n2;
            super.anInt6177 += (this.aShort6491 * (this.anInt6498 << 2) >> 23) * n2;
            super.anInt6175 += (this.aShort6494 * (this.anInt6498 << 2) >> 23) * n2;
        }
    }
    
    private final void method3110() {
        final int anInt5093 = this.aClass246_Sub9_6492.aClass246_Sub5_5133.anInt5093;
        if (this.aClass246_Sub9_6492.aClass246_Sub5_5133.aClass246_Sub4_Sub2_Sub1Array5095[anInt5093] != null) {
            this.aClass246_Sub9_6492.aClass246_Sub5_5133.aClass246_Sub4_Sub2_Sub1Array5095[anInt5093].method3113();
        }
        this.aClass246_Sub9_6492.aClass246_Sub5_5133.aClass246_Sub4_Sub2_Sub1Array5095[anInt5093] = this;
        this.aShort6499 = (short)this.aClass246_Sub9_6492.aClass246_Sub5_5133.anInt5093;
        this.aClass246_Sub9_6492.aClass246_Sub5_5133.anInt5093 = (anInt5093 + 1 & 0x1FFF);
        this.aClass246_Sub9_6492.aClass218_5140.method2808(true, this);
    }
    
    final void method3111(final ha ha, final long n) {
        final int n2 = super.anInt6176 >> 12 + Class151_Sub8.anInt5015;
        final int n3 = super.anInt6175 >> 12 + Class151_Sub8.anInt5015;
        final int n4 = super.anInt6177 >> 12;
        if (n4 > 0 || n4 < -262144 || n2 < 0 || n2 >= Class366.anInt3112 || n3 < 0 || n3 >= Class64_Sub9.anInt3662) {
            this.method3113();
        }
        else {
            final Class246_Sub5 aClass246_Sub5_5133 = this.aClass246_Sub9_6492.aClass246_Sub5_5133;
            final Class92 aClass92_5132 = this.aClass246_Sub9_6492.aClass92_5132;
            final s[] asArray594 = Class78.aSArray594;
            int n5 = aClass246_Sub5_5133.anInt5106;
            final Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[aClass246_Sub5_5133.anInt5106][n2][n3];
            if (class172 != null) {
                n5 = class172.aByte1322;
            }
            final int method3420 = asArray594[n5].method3420(n3, -12639, n2);
            int method3421;
            if (n5 < Class364.anInt3103 - 1) {
                method3421 = asArray594[n5 + 1].method3420(n3, -12639, n2);
            }
            else {
                method3421 = method3420 - (8 << Class151_Sub8.anInt5015);
            }
            if (aClass92_5132.aBoolean736) {
                if (aClass92_5132.anInt774 == -1 && n4 > method3420) {
                    this.method3113();
                    return;
                }
                if (aClass92_5132.anInt774 >= 0 && n4 > asArray594[aClass92_5132.anInt774].method3420(n3, -12639, n2)) {
                    this.method3113();
                    return;
                }
                if (aClass92_5132.anInt782 == -1 && n4 < method3421) {
                    this.method3113();
                    return;
                }
                if (aClass92_5132.anInt782 >= 0 && n4 < asArray594[aClass92_5132.anInt782 + 1].method3420(n3, -12639, n2)) {
                    this.method3113();
                    return;
                }
            }
            int n6;
            for (n6 = Class364.anInt3103 - 1; n6 > 0 && n4 > asArray594[n6].method3420(n3, -12639, n2); --n6) {}
            if (aClass92_5132.aBoolean776 && n6 == 0 && n4 > asArray594[0].method3420(n3, -12639, n2)) {
                this.method3113();
            }
            else if (n6 == Class364.anInt3103 - 1 && asArray594[n6].method3420(n3, -12639, n2) - n4 > 8 << Class151_Sub8.anInt5015) {
                this.method3113();
            }
            else {
                Class172 class173 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n6][n2][n3];
                if (class173 == null) {
                    if (n6 == 0 || Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[0][n2][n3] == null) {
                        final Class172[] array = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[0][n2];
                        final int n7 = n3;
                        final Class172 class174 = new Class172(0);
                        array[n7] = class174;
                        class173 = class174;
                    }
                    final boolean b = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[0][n2][n3].aClass172_1330 != null;
                    if (n6 == 3 && b) {
                        this.method3113();
                        return;
                    }
                    for (int i = 1; i <= n6; ++i) {
                        if (Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[i][n2][n3] == null) {
                            final Class172[] array2 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[i][n2];
                            final int n8 = n3;
                            final Class172 class175 = new Class172(i);
                            array2[n8] = class175;
                            class173 = class175;
                            if (b) {
                                final Class172 class176 = class173;
                                ++class176.aByte1322;
                            }
                        }
                    }
                }
                if (aClass92_5132.aBoolean789) {
                    final int n9 = super.anInt6176 >> 12;
                    final int n10 = super.anInt6175 >> 12;
                    if (class173.aClass246_Sub3_Sub3_1324 != null) {
                        final Class228 method3422 = class173.aClass246_Sub3_Sub3_1324.method2974((byte)(-53), ha);
                        if (method3422 != null && method3422.method2863(n9, n4, n10, 0)) {
                            this.method3113();
                            return;
                        }
                    }
                    if (class173.aClass246_Sub3_Sub3_1333 != null) {
                        final Class228 method3423 = class173.aClass246_Sub3_Sub3_1333.method2974((byte)(-53), ha);
                        if (method3423 != null && method3423.method2863(n9, n4, n10, 0)) {
                            this.method3113();
                            return;
                        }
                    }
                    if (class173.aClass246_Sub3_Sub1_1332 != null) {
                        final Class228 method3424 = class173.aClass246_Sub3_Sub1_1332.method2974((byte)(-53), ha);
                        if (method3424 != null && method3424.method2863(n9, n4, n10, 0)) {
                            this.method3113();
                            return;
                        }
                    }
                    for (Class154 class177 = class173.aClass154_1325; class177 != null; class177 = class177.aClass154_1233) {
                        final Class228 method3425 = class177.aClass246_Sub3_Sub4_1232.method2974((byte)(-53), ha);
                        if (method3425 != null && method3425.method2863(n9, n4, n10, 0)) {
                            this.method3113();
                            return;
                        }
                    }
                }
                aClass246_Sub5_5133.aClass242_5104.aClass358_1850.method3891(this, 8);
            }
        }
    }
    
    final void method3112(final Class246_Sub9 aClass246_Sub9_6492, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int anInt6498, final int n7, final int anInt6499, final int anInt6500, final int anInt6501, final boolean b, final boolean aBoolean6174) {
        this.aClass246_Sub9_6492 = aClass246_Sub9_6492;
        super.anInt6176 = n << 12;
        super.anInt6177 = n2 << 12;
        super.anInt6175 = n3 << 12;
        super.anInt6178 = anInt6499;
        final short n8 = (short)n7;
        this.aShort6497 = n8;
        this.aShort6493 = n8;
        super.anInt6179 = anInt6500;
        super.anInt6180 = anInt6501;
        super.aBoolean6174 = aBoolean6174;
        this.aShort6496 = (short)n4;
        this.aShort6491 = (short)n5;
        this.aShort6494 = (short)n6;
        this.anInt6498 = anInt6498;
        super.aByte6183 = this.aClass246_Sub9_6492.aClass87_5131.aByte658;
        this.method3110();
    }
    
    final void method3113() {
        this.aClass246_Sub9_6492.aClass246_Sub5_5133.aClass246_Sub4_Sub2_Sub1Array5095[this.aShort6499] = null;
        Class185.aClass246_Sub4_Sub2_Sub1Array1445[Class361.anInt3089] = this;
        Class361.anInt3089 = (Class361.anInt3089 + 1 & 0x3FF);
        this.method2965((byte)(-75));
        this.method3101(-37);
    }
    
    Class246_Sub4_Sub2_Sub1(final Class246_Sub9 aClass246_Sub9_6492, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int anInt6498, final int n7, final int anInt6499, final int anInt6500, final int anInt6501, final boolean b, final boolean aBoolean6174) {
        this.aClass246_Sub9_6492 = aClass246_Sub9_6492;
        super.anInt6176 = n << 12;
        super.anInt6177 = n2 << 12;
        super.anInt6175 = n3 << 12;
        super.anInt6178 = anInt6499;
        final short n8 = (short)n7;
        this.aShort6497 = n8;
        this.aShort6493 = n8;
        super.anInt6179 = anInt6500;
        super.anInt6180 = anInt6501;
        super.aBoolean6174 = aBoolean6174;
        this.aShort6496 = (short)n4;
        this.aShort6491 = (short)n5;
        this.aShort6494 = (short)n6;
        this.anInt6498 = anInt6498;
        super.aByte6183 = this.aClass246_Sub9_6492.aClass87_5131.aByte658;
        this.method3110();
    }
}
