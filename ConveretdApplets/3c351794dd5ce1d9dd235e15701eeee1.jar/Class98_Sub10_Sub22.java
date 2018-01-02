// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub22 extends Class98_Sub10
{
    static OutgoingOpcode aClass171_5652;
    static volatile Object anObject5653;
    private int anInt5654;
    private int anInt5655;
    private int anInt5656;
    
    static final void method1069(final int n, final ha ha) {
        try {
            if (n != 256) {
                method1072((byte)(-94));
            }
            if (Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540) {
                Class116.method2159((byte)80, ha);
            }
            else {
                Class96.method926(2, ha);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nn.D(" + n + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final int[][] method997(final int n, final int n2) {
        try {
            if (n >= -76) {
                this.anInt5656 = -70;
            }
            final int[][] method2828 = super.aClass223_3859.method2828(n2, 0);
            if (super.aClass223_3859.aBoolean1683) {
                final int[] array = method2828[0];
                final int[] array2 = method2828[1];
                final int[] array3 = method2828[2];
                for (int n3 = 0; ~n3 > ~Class25.anInt268; ++n3) {
                    array[n3] = this.anInt5654;
                    array2[n3] = this.anInt5656;
                    array3[n3] = this.anInt5655;
                }
            }
            return method2828;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nn.C(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method1070(final byte b, final Class85 class85) {
        try {
            if (class85 == ha_Sub1.aClass85_4299) {
                final int byteA = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteA(true);
                final int n = Class53.anInt430 - -((0x7C & byteA) >> 1327059812);
                final int n2 = Class335.anInt2819 + (byteA & 0x7);
                final int byteA2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteA(true);
                final Class352 method3546 = Class130.aClass302_1028.method3546(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort1((byte)51), (byte)119);
                int n3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteA(true) >> 948044066;
                final int n4 = Class64_Sub17.anIntArray3685[n3];
                if (n3 == 11) {
                    n3 = 10;
                }
                int length = 0;
                if (method3546.aByteArray2994 != null) {
                    int n5 = -1;
                    for (int n6 = 0; method3546.aByteArray2994.length > n6; ++n6) {
                        if (method3546.aByteArray2994[n6] == n3) {
                            n5 = n6;
                            break;
                        }
                    }
                    length = method3546.anIntArrayArray2951[n5].length;
                }
                int length2 = 0;
                if (method3546.aShortArray2965 != null) {
                    length2 = method3546.aShortArray2965.length;
                }
                int length3 = 0;
                if (method3546.aShortArray2974 != null) {
                    length3 = method3546.aShortArray2974.length;
                }
                if ((byteA2 & 0x1) == 0x1) {
                    Class152.method2478(n2, n4, null, n, -75, Class206.anInt1568);
                }
                else {
                    int[] array = null;
                    if (~(byteA2 & 0x2) == 0xFFFFFFFD) {
                        array = new int[length];
                        for (int i = 0; i < length; ++i) {
                            array[i] = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                        }
                    }
                    short[] array2 = null;
                    if ((byteA2 & 0x4) == 0x4) {
                        array2 = new short[length2];
                        for (int n7 = 0; ~n7 > ~length2; ++n7) {
                            array2[n7] = (short)Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                        }
                    }
                    short[] array3 = null;
                    if ((0x8 & byteA2) == 0x8) {
                        array3 = new short[length3];
                        for (int j = 0; j < length3; ++j) {
                            array3[j] = (short)Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                        }
                    }
                    Class152.method2478(n2, n4, new Class185(Class280.aLong2112++, array, array2, array3), n, -92, Class206.anInt1568);
                }
            }
            else if (class85 == Class39.aClass85_362) {
                final int unsignedByte = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-124));
                final int n8 = Class53.anInt430 - -(unsignedByte >> -1645863036 & 0x7);
                final int n9 = Class335.anInt2819 + (unsignedByte & 0x7);
                int short1 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                if (short1 == 65535) {
                    short1 = -1;
                }
                final int unsignedByte2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-2));
                final int n10 = 0xF & unsignedByte2 >> 218942468;
                final int n11 = 0x7 & unsignedByte2;
                final int unsignedByte3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-103));
                final int unsignedByte4 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-113));
                final int short2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                if (~n8 <= -1 && n9 >= 0 && Class165.anInt1276 > n8 && Class98_Sub10_Sub7.anInt5572 > n9) {
                    final int n12 = 1 + n10;
                    if (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6437[0] >= -n12 + n8 && n8 + n12 >= Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6437[0] && ~(n9 - n12) >= ~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6438[0] && Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6438[0] <= n9 - -n12) {
                        Class21_Sub4.method278(unsignedByte4, short2, short1, n11, unsignedByte3, (byte)65, (n9 << -1680627448) + (n8 << 129807376) + (Class206.anInt1568 << -1551112) - -n10);
                    }
                }
            }
            else if (Class98_Sub23.aClass85_4007 == class85) {
                final int byteC = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteC((byte)(-128));
                final int n13 = byteC >> 1098097986;
                final int n14 = byteC & 0x3;
                final int n15 = Class64_Sub17.anIntArray3685[n13];
                final int byteS = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(b ^ 0xFFFFFFA9);
                final int n16 = Class53.anInt430 + (byteS >> 909525444 & 0x7);
                final int n17 = Class335.anInt2819 + (byteS & 0x7);
                if (Class312.method3623(-114, Class151_Sub9.anInt5028) || (~n16 <= -1 && ~n17 <= -1 && Class165.anInt1276 > n16 && n17 < Class98_Sub10_Sub7.anInt5572)) {
                    Class64_Sub9.method591(-85, n16, n13, n17, n15, -1, n14, Class206.anInt1568);
                }
            }
            else if (Class35.aClass85_332 == class85) {
                final int unsignedByte5 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)17);
                final int n18 = Class53.anInt430 * 2 + ((unsignedByte5 & 0xFA) >> -1945955580);
                final int n19 = (0xF & unsignedByte5) + Class335.anInt2819 * 2;
                final int unsignedByte6 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)36);
                final boolean b2 = (unsignedByte6 & 0x1) != 0x0;
                final boolean b3 = (0x2 & unsignedByte6) != 0x0;
                final int n20 = b3 ? (unsignedByte6 >> 1165493090) : -1;
                final byte b4 = (byte)(n18 - -Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readSignedByte((byte)(-19)));
                final byte b5 = (byte)(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readSignedByte((byte)(-19)) + n19);
                final int uShort = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUShort(false);
                final int uShort2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUShort(false);
                final int short3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                final int unsignedByte7 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-121));
                int n21;
                if (!b3) {
                    n21 = unsignedByte7 * 4;
                }
                else {
                    n21 = (byte)unsignedByte7;
                }
                final int n22 = 4 * Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)71);
                final int short4 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                final int short5 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                int unsignedByte8 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-119));
                final int short6 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                if (~unsignedByte8 == 0xFFFFFF00) {
                    unsignedByte8 = -1;
                }
                if (~n18 <= -1 && ~n19 <= -1 && ~n18 > ~(Class165.anInt1276 * 2) && n19 < Class165.anInt1276 * 2 && b4 >= 0 && b5 >= 0 && Class98_Sub10_Sub7.anInt5572 * 2 > b4 && Class98_Sub10_Sub7.anInt5572 * 2 > b5 && ~short3 != 0xFFFF0000) {
                    final int n23 = 256 * n19;
                    final int n24 = n22 << 2;
                    final int n25 = short6 << 2;
                    int n26 = n21 << 2;
                    final int n27 = 256 * n18;
                    final int n28 = 256 * b5;
                    final int n29 = b4 * 256;
                    if (~uShort != -1 && n20 != -1) {
                        Class246_Sub3_Sub4_Sub2 class246_Sub3_Sub4_Sub2 = null;
                        if (~uShort <= -1) {
                            final Class98_Sub39 class98_Sub39 = (Class98_Sub39)Class260.aClass377_3254.method3990(-1 + uShort, -1);
                            if (class98_Sub39 != null) {
                                class246_Sub3_Sub4_Sub2 = class98_Sub39.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                            }
                        }
                        else {
                            final int n30 = -1 + -uShort;
                            if (~n30 != ~za_Sub2.anInt6080) {
                                class246_Sub3_Sub4_Sub2 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[n30];
                            }
                            else {
                                class246_Sub3_Sub4_Sub2 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660;
                            }
                        }
                        if (class246_Sub3_Sub4_Sub2 != null) {
                            final Class294 method3547 = class246_Sub3_Sub4_Sub2.method3039(1);
                            if (method3547.anIntArrayArray2366 != null && method3547.anIntArrayArray2366[n20] != null) {
                                n26 -= method3547.anIntArrayArray2366[n20][1];
                            }
                            if (method3547.anIntArrayArray2364 != null && method3547.anIntArrayArray2364[n20] != null) {
                                n26 -= method3547.anIntArrayArray2364[n20][1];
                            }
                        }
                    }
                    final Class246_Sub3_Sub4_Sub4 class246_Sub3_Sub4_Sub3 = new Class246_Sub3_Sub4_Sub4(short3, Class206.anInt1568, Class206.anInt1568, n27, n23, n26, short4 + Class215.anInt1614, short5 - -Class215.anInt1614, unsignedByte8, n25, uShort, uShort2, n24, b2, n20);
                    class246_Sub3_Sub4_Sub3.method3074(n29, -n24 + Class98_Sub46_Sub2_Sub2.method1538(Class206.anInt1568, n28, n29, b ^ 0xFFFFA1FC), Class215.anInt1614 + short4, (byte)108, n28);
                    Class280.aClass148_2108.method2419(new Class98_Sub46_Sub5(class246_Sub3_Sub4_Sub3), b - 20866);
                }
            }
            else if (Class242.aClass85_1849 == class85) {
                final int unsignedByte9 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-120));
                final boolean b6 = ~(unsignedByte9 & 0x80) != -1;
                final int n31 = Class53.anInt430 - -(0x7 & unsignedByte9 >> -1600745245);
                final int n32 = (unsignedByte9 & 0x7) + Class335.anInt2819;
                final byte b7 = (byte)(n31 + Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readSignedByte((byte)(-19)));
                final byte b8 = (byte)(n32 - -Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readSignedByte((byte)(-19)));
                final int uShort3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUShort(false);
                final int short7 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                final int n33 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)0) * 4;
                final int n34 = 4 * Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-8));
                final int short8 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                final int short9 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                int unsignedByte10 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-100));
                final int short10 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                if (unsignedByte10 == 255) {
                    unsignedByte10 = -1;
                }
                if (n31 >= 0 && n32 >= 0 && Class165.anInt1276 > n31 && ~Class98_Sub10_Sub7.anInt5572 < ~n32 && b7 >= 0 && ~b8 <= -1 && b7 < Class165.anInt1276 && ~b8 > ~Class98_Sub10_Sub7.anInt5572 && ~short7 != 0xFFFF0000) {
                    final int n35 = n34 << 2;
                    final int n36 = n31 * 512 + 256;
                    final int n37 = n33 << 2;
                    final int n38 = 256 + n32 * 512;
                    final int n39 = 256 + b7 * 512;
                    final int n40 = b8 * 512 + 256;
                    final Class246_Sub3_Sub4_Sub4 class246_Sub3_Sub4_Sub4 = new Class246_Sub3_Sub4_Sub4(short7, Class206.anInt1568, Class206.anInt1568, n36, n38, n37, short8 + Class215.anInt1614, short9 - -Class215.anInt1614, unsignedByte10, short10 << 2, 0, uShort3, n35, b6, -1);
                    class246_Sub3_Sub4_Sub4.method3074(n39, Class98_Sub46_Sub2_Sub2.method1538(Class206.anInt1568, n40, n39, 24111) - n35, short8 - -Class215.anInt1614, (byte)108, n40);
                    Class280.aClass148_2108.method2419(new Class98_Sub46_Sub5(class246_Sub3_Sub4_Sub4), -20911);
                }
            }
            else if (class85 == Class373_Sub3.aClass85_5474) {
                final int unsignedByte11 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-102));
                final int n41 = Class335.anInt2819 + (unsignedByte11 & 0x7);
                final int n42 = aa_Sub2.anInt3562 + n41;
                final int n43 = (0x7 & unsignedByte11 >> -1172232540) + Class53.anInt430;
                final int n44 = n43 - -Class272.anInt2038;
                final int short11 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                final int short12 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                final int short13 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                if (Class146.aClass377_1180 != null) {
                    final Class98_Sub45 class98_Sub40 = (Class98_Sub45)Class146.aClass377_1180.method3990(n44 | (n42 << -45976338 | Class206.anInt1568 << -2048549060), b ^ 0x2C);
                    if (class98_Sub40 != null) {
                        for (Class98_Sub26 class98_Sub41 = (Class98_Sub26)class98_Sub40.aClass148_4254.method2418(32); class98_Sub41 != null; class98_Sub41 = (Class98_Sub26)class98_Sub40.aClass148_4254.method2417(b + 142)) {
                            if (~(short11 & 0x7FFF) == ~class98_Sub41.anInt4031 && class98_Sub41.anInt4032 == short12) {
                                class98_Sub41.method942(44);
                                class98_Sub41.anInt4032 = short13;
                                Class48_Sub1.method458(class98_Sub41, Class206.anInt1568, n42, n44, true);
                                break;
                            }
                        }
                        if (n43 >= 0 && n41 >= 0 && ~n43 > ~Class165.anInt1276 && n41 < Class98_Sub10_Sub7.anInt5572) {
                            Class98_Sub32.method1437(n43, Class206.anInt1568, (byte)114, n41);
                        }
                    }
                }
            }
            else if (class85 == Class64_Sub10.aClass85_3667) {
                Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-113));
                final int unsignedByte12 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-122));
                Class325.method3696((byte)118, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1186(-123), (0x7 & unsignedByte12) + Class335.anInt2819, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127), Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84), Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-7)), Class206.anInt1568, ((0x72 & unsignedByte12) >> 889841188) + Class53.anInt430);
            }
            else if (Class6.aClass85_89 == class85) {
                final int leShortA = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)(-68));
                final int byteS2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(b ^ 0xFFFFFFAF);
                final int n45 = (byteS2 & 0x7) + Class335.anInt2819;
                final int n46 = aa_Sub2.anInt3562 - -n45;
                final int n47 = Class53.anInt430 + (0x7 & byteS2 >> 4);
                final int n48 = Class272.anInt2038 - -n47;
                final int leShortA2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)95);
                final boolean b9 = ~n47 <= -1 && ~n45 <= -1 && ~n47 > ~Class165.anInt1276 && Class98_Sub10_Sub7.anInt5572 > n45;
                if (b9 || Class312.method3623(b - 56, Class151_Sub9.anInt5028)) {
                    Class48_Sub1.method458(new Class98_Sub26(leShortA, leShortA2), Class206.anInt1568, n46, n48, true);
                    if (b9) {
                        Class98_Sub32.method1437(n47, Class206.anInt1568, (byte)59, n45);
                    }
                }
            }
            else if (class85 == Class79.aClass85_600) {
                final int byteS3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(-32);
                final int n49 = Class53.anInt430 - -(0x7 & byteS3 >> -1992025084);
                final int n50 = Class335.anInt2819 - -(0x7 & byteS3);
                int leShortA3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)105);
                if (~leShortA3 == 0xFFFF0000) {
                    leShortA3 = -1;
                }
                final int byteA3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteA(true);
                final int n51 = byteA3 >> -1003983550;
                Class283.method3351(n51, 0x3 & byteA3, true, n49, n50, Class206.anInt1568, leShortA3, Class64_Sub17.anIntArray3685[n51]);
            }
            else if (class85 == Class246_Sub4_Sub2.aClass85_6186) {
                final int unsignedByte13 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-107));
                final int n52 = Class53.anInt430 + ((0x7D & unsignedByte13) >> -1581133244);
                final int n53 = (0x7 & unsignedByte13) + Class335.anInt2819;
                int short14 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                if (short14 == 65535) {
                    short14 = -1;
                }
                final int unsignedByte14 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)56);
                final int n54 = (unsignedByte14 & 0xF6) >> -1409175612;
                final int n55 = unsignedByte14 & 0x7;
                final int unsignedByte15 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-123));
                final int unsignedByte16 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-107));
                final int short15 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                if (n52 >= 0 && ~n53 <= -1 && ~Class165.anInt1276 < ~n52 && ~Class98_Sub10_Sub7.anInt5572 < ~n53) {
                    final int n56 = n54 + 1;
                    if (~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6437[0] <= ~(n52 + -n56) && Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6437[0] <= n56 + n52 && -n56 + n53 <= Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6438[0] && ~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6438[0] >= ~(n56 + n53)) {
                        Class86.method844(short15, false, unsignedByte15, n55, unsignedByte16, (n52 << 1399144080) + (Class206.anInt1568 << -26386888) + ((n53 << -1188209688) - -n54), short14, b - 21);
                    }
                }
            }
            else if (class85 == Class98_Sub10_Sub16.aClass85_5621) {
                final int byteS4 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(-20);
                final int n57 = (byteS4 & 0x7) + Class335.anInt2819;
                final int n58 = aa_Sub2.anInt3562 + n57;
                final int n59 = Class53.anInt430 - -((byteS4 & 0x79) >> 1994183780);
                final int n60 = n59 - -Class272.anInt2038;
                final int shortA = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(90);
                final Class98_Sub45 class98_Sub42 = (Class98_Sub45)Class146.aClass377_1180.method3990(n60 | (n58 << -1573020242 | Class206.anInt1568 << -1438380164), -1);
                if (class98_Sub42 != null) {
                    for (Class98_Sub26 class98_Sub43 = (Class98_Sub26)class98_Sub42.aClass148_4254.method2418(32); class98_Sub43 != null; class98_Sub43 = (Class98_Sub26)class98_Sub42.aClass148_4254.method2417(117)) {
                        if (~class98_Sub43.anInt4031 == ~(shortA & 0x7FFF)) {
                            class98_Sub43.method942(107);
                            break;
                        }
                    }
                    if (class98_Sub42.aClass148_4254.method2420(-127)) {
                        class98_Sub42.method942(64);
                    }
                    if (n59 >= 0 && ~n57 <= -1 && n59 < Class165.anInt1276 && ~n57 > ~Class98_Sub10_Sub7.anInt5572) {
                        Class98_Sub32.method1437(n59, Class206.anInt1568, (byte)94, n57);
                    }
                }
            }
            else if (class85 == Class60.aClass85_471) {
                final int byteC2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteC((byte)102);
                final int n61 = Class53.anInt430 + (byteC2 >> 2113611716 & 0x7);
                final int n62 = Class335.anInt2819 + (0x7 & byteC2);
                final int short16 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort1((byte)49);
                final int unsignedByte17 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)45);
                final int n63 = unsignedByte17 >> -705226718;
                final int n64 = unsignedByte17 & 0x3;
                final int n65 = Class64_Sub17.anIntArray3685[n63];
                if (Class312.method3623(-100, Class151_Sub9.anInt5028) || (~n61 <= -1 && ~n62 <= -1 && ~Class165.anInt1276 < ~n61 && Class98_Sub10_Sub7.anInt5572 > n62)) {
                    Class64_Sub9.method591(-60, n61, n63, n62, n65, short16, n64, Class206.anInt1568);
                }
            }
            else if (Class133.aClass85_3454 == class85) {
                Class130.aClass302_1028.method3546(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127), (byte)119).method3853((byte)49, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-107)));
            }
            else if (Class351.aClass85_2921 == class85) {
                final int leShortA4 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)109);
                final int shortA2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(99);
                final int short17 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                final int byteC3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteC((byte)25);
                final int n66 = Class335.anInt2819 + (0x7 & byteC3);
                final int n67 = n66 + aa_Sub2.anInt3562;
                final int n68 = (0x7 & byteC3 >> -999540380) + Class53.anInt430;
                final int n69 = Class272.anInt2038 + n68;
                if (~za_Sub2.anInt6080 != ~short17) {
                    final boolean b10 = n68 >= 0 && ~n66 <= -1 && n68 < Class165.anInt1276 && ~Class98_Sub10_Sub7.anInt5572 < ~n66;
                    if (b10 || Class312.method3623(-69, Class151_Sub9.anInt5028)) {
                        Class48_Sub1.method458(new Class98_Sub26(leShortA4, shortA2), Class206.anInt1568, n67, n69, true);
                        if (b10) {
                            Class98_Sub32.method1437(n68, Class206.anInt1568, (byte)39, n66);
                        }
                    }
                }
            }
            else if (class85 == Class98_Sub11.aClass85_3868) {
                final int unsignedByte18 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-117));
                final int n70 = (unsignedByte18 >> -1502134172 & 0x7) + Class53.anInt430;
                final int n71 = Class335.anInt2819 - -(0x7 & unsignedByte18);
                final int short18 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                final int unsignedByte19 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-114));
                final int short19 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                final int unsignedByte20 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)40);
                if (n70 >= 0 && n71 >= 0 && ~Class165.anInt1276 < ~n70 && Class98_Sub10_Sub7.anInt5572 > n71) {
                    final int n72 = n70 * 512 + 256;
                    final int n73 = n71 * 512 + 256;
                    int anInt1568 = Class206.anInt1568;
                    if (anInt1568 < 3 && Class1.method162(n71, (byte)(-104), n70)) {
                        ++anInt1568;
                    }
                    Class98_Sub10_Sub11.aClass148_5596.method2419(new Class98_Sub46_Sub3(new Class246_Sub3_Sub4_Sub3(short18, short19, Class215.anInt1614, Class206.anInt1568, anInt1568, n72, Class98_Sub46_Sub2_Sub2.method1538(Class206.anInt1568, n73, n72, 24111) - unsignedByte19, n73, n70, n70, n71, n71, unsignedByte20)), b ^ 0x5182);
                }
            }
            else {
                Class305_Sub1.method3585(null, -123, "T3 - " + class85);
                Class98_Sub10_Sub1.method1003(false, false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nn.F(" + b + ',' + ((class85 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method1071(final int n, final int n2) {
        try {
            if (n > 121) {
                this.anInt5656 = (0xFF00 & n2) >> 376071844;
                this.anInt5655 = (n2 << -339638620 & 0xFF0);
                this.anInt5654 = (n2 & 0xFF0000) >> -1095348980;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nn.E(" + n + ',' + n2 + ')');
        }
    }
    
    private Class98_Sub10_Sub22(final int n) {
        super(0, false);
        try {
            this.method1071(122, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nn.<init>(" + n + ')');
        }
    }
    
    public Class98_Sub10_Sub22() {
        this(0);
    }
    
    public static void method1072(final byte b) {
        try {
            Class98_Sub10_Sub22.aClass171_5652 = null;
            Class98_Sub10_Sub22.anObject5653 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nn.B(" + b + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (b > -92) {
                this.anInt5656 = 88;
            }
            if (~n == -1) {
                this.method1071(122, class98_Sub22.method1186(-124));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nn.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub22.anObject5653 = null;
        Class98_Sub10_Sub22.aClass171_5652 = new OutgoingOpcode(23, 2);
    }
}
