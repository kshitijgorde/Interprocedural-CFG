import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class21_Sub2 extends Class21 implements Interface4_Impl3
{
    static int[][] anIntArrayArray5386;
    static int anInt5387;
    static int anInt5388;
    
    static final boolean method271(final byte b, final int n, final int n2) {
        try {
            if (b > -100) {
                method273((byte)(-47));
            }
            return ~(0x10000 & n) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lq.E(" + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    public static void method272(final byte b) {
        try {
            if (b == 86) {
                Class21_Sub2.anIntArrayArray5386 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lq.A(" + b + ')');
        }
    }
    
    Class21_Sub2(final ha_Sub3_Sub2 ha_Sub3_Sub2, final int n, final boolean b, final int[][] array) {
        super(ha_Sub3_Sub2, 34067, Class62.aClass164_486, Class162.aClass162_1266, 6 * n * n, b);
        try {
            super.aHa_Sub3_Sub2_3233.method2005(this, -115);
            if (b) {
                for (int i = 0; i < 6; ++i) {
                    this.method264(n, array[i], n, 526364520, i + 34069);
                }
            }
            else {
                for (int n2 = 0; ~n2 > -7; ++n2) {
                    OpenGL.glTexImage2Di(34069 - -n2, 0, this.method260(0), n, n, 0, Class196.method2665(false, super.aClass164_3237), super.aHa_Sub3_Sub2_3233.anInt6135, array[n2], 0);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lq.<init>(" + ((ha_Sub3_Sub2 != null) ? "{...}" : "null") + ',' + n + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method273(final byte b) {
        try {
            for (int i = 0; i < Class65.anInt502; ++i) {
                final int n = Class76_Sub11.anIntArray3796[i];
                final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4187 = ((Class98_Sub39)Class260.aClass377_3254.method3990(n, -1)).aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                int unsignedByte = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-106));
                if ((0x20 & unsignedByte) != 0x0) {
                    unsignedByte += Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)20) << 1706038888;
                }
                if ((0x2000 & unsignedByte) != 0x0) {
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6378 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1187((byte)0);
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6347 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1187((byte)0);
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6362 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1187((byte)(-112));
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6392 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1187((byte)0);
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6390 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127) - -Class215.anInt1614;
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6424 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort1((byte)(-123)) + Class215.anInt1614;
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6407 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteC((byte)(-109));
                    final Class246_Sub3_Sub4_Sub2_Sub1 class246_Sub3_Sub4_Sub2_Sub1 = aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                    class246_Sub3_Sub4_Sub2_Sub1.anInt6362 += aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6437[0];
                    final Class246_Sub3_Sub4_Sub2_Sub1 class246_Sub3_Sub4_Sub2_Sub2 = aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                    class246_Sub3_Sub4_Sub2_Sub2.anInt6392 += aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6438[0];
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6434 = 1;
                    final Class246_Sub3_Sub4_Sub2_Sub1 class246_Sub3_Sub4_Sub2_Sub3 = aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                    class246_Sub3_Sub4_Sub2_Sub3.anInt6378 += aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6437[0];
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6436 = 0;
                    final Class246_Sub3_Sub4_Sub2_Sub1 class246_Sub3_Sub4_Sub2_Sub4 = aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                    class246_Sub3_Sub4_Sub2_Sub4.anInt6347 += aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6438[0];
                }
                if ((unsignedByte & 0x4) != 0x0) {
                    final int j = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteC((byte)(-121));
                    if (j > 0) {
                        for (int n2 = 0; j > n2; ++n2) {
                            int smart = -1;
                            int n3 = -1;
                            int smart2 = -1;
                            int n4 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readSmart(1689622712);
                            if (~n4 == 0xFFFF8000) {
                                n4 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readSmart(1689622712);
                                n3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readSmart(1689622712);
                                smart = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readSmart(1689622712);
                                smart2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readSmart(1689622712);
                            }
                            else if (n4 != 32766) {
                                n3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readSmart(1689622712);
                            }
                            else {
                                n4 = -1;
                            }
                            aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3037(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-128)), false, n3, n4, Class215.anInt1614, smart2, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readSmart(1689622712), smart);
                        }
                    }
                }
                if (~(0x4000 & unsignedByte) != -1) {
                    final int length = aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.anIntArray1117.length;
                    int n5 = 0;
                    if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.aShortArray1105 != null) {
                        n5 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.aShortArray1105.length;
                    }
                    if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.aShortArray1137 != null) {
                        n5 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.aShortArray1137.length;
                    }
                    final int n6 = 0;
                    final int byteS = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(106);
                    if (~(0x1 & byteS) != 0xFFFFFFFE) {
                        int[] array = null;
                        if ((0x2 & byteS) == 0x2) {
                            array = new int[length];
                            for (int n7 = 0; ~length < ~n7; ++n7) {
                                array[n7] = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)(-84));
                            }
                        }
                        short[] array2 = null;
                        if ((0x4 & byteS) == 0x4) {
                            array2 = new short[n5];
                            for (int k = 0; k < n5; ++k) {
                                array2[k] = (short)Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(51);
                            }
                        }
                        short[] array3 = null;
                        if (~(0x8 & byteS) == 0xFFFFFFF7) {
                            array3 = new short[n6];
                            for (int n8 = 0; ~n6 < ~n8; ++n8) {
                                array3[n8] = (short)Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)108);
                            }
                        }
                        new Class40(n | aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6501++ << -1851073888, array, array2, array3);
                    }
                }
                if (~(0x400 & unsignedByte) != -1) {
                    final int shortA = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(96);
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6394 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteC((byte)(-114));
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6401 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-101));
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.aBoolean6348 = ((shortA & 0x8000) != 0x0);
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6420 = (shortA & 0x7FFF);
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6412 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6420 + Class215.anInt1614 - -aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6394;
                }
                if ((0x2 & unsignedByte) != 0x0) {
                    int shortA2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(65);
                    if (~shortA2 == 0xFFFF0000) {
                        shortA2 = -1;
                    }
                    final int intReverse = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readIntReverse(true);
                    final int byteC = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteC((byte)119);
                    final int n9 = 0x7 & byteC;
                    int n10 = (0x79 & byteC) >> 2140717763;
                    if (~n10 == 0xFFFFFFF0) {
                        n10 = -1;
                    }
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3032(n10, false, intReverse, n9, shortA2, -117);
                }
                if (~(unsignedByte & 0x8) != -1) {
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6364 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                    if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6364 == 65535) {
                        aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6364 = -1;
                    }
                }
                if (~(unsignedByte & 0x8000) != -1) {
                    final int length2 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.anIntArray1107.length;
                    int l = 0;
                    if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.aShortArray1105 != null) {
                        l = aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.aShortArray1105.length;
                    }
                    int length3 = 0;
                    if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.aShortArray1137 != null) {
                        length3 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.aShortArray1137.length;
                    }
                    final int unsignedByte2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-98));
                    if ((unsignedByte2 & 0x1) == 0x1) {
                        aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass40_6502 = null;
                    }
                    else {
                        int[] array4 = null;
                        if ((unsignedByte2 & 0x2) == 0x2) {
                            array4 = new int[length2];
                            for (int n11 = 0; ~n11 > ~length2; ++n11) {
                                array4[n11] = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(65);
                            }
                        }
                        short[] array5 = null;
                        if (~(unsignedByte2 & 0x4) == 0xFFFFFFFB) {
                            array5 = new short[l];
                            for (int n12 = 0; l > n12; ++n12) {
                                array5[n12] = (short)Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(85);
                            }
                        }
                        short[] array6 = null;
                        if ((unsignedByte2 & 0x8) == 0x8) {
                            array6 = new short[length3];
                            for (int n13 = 0; n13 < length3; ++n13) {
                                array6[n13] = (short)Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(106);
                            }
                        }
                        aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass40_6502 = new Class40(n | aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6503++ << 270238944, array4, array5, array6);
                    }
                }
                if ((0x800 & unsignedByte) != 0x0) {
                    final int byteA = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteA(true);
                    final int[] array7 = new int[byteA];
                    final int[] array8 = new int[byteA];
                    for (int n14 = 0; n14 < byteA; ++n14) {
                        final int leShortA = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)119);
                        if (~(leShortA & 0xC000) == 0xFFFF3FFF) {
                            array7[n14] = Class41.method366(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)(-28)), leShortA << 1873040720);
                        }
                        else {
                            array7[n14] = leShortA;
                        }
                        array8[n14] = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                    }
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3038(array8, array7, true);
                }
                if ((0x80 & unsignedByte) != 0x0) {
                    if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.method2302((byte)97)) {
                        Class98_Sub43_Sub4.method1504(aClass246_Sub3_Sub4_Sub2_Sub1_4187, -16255);
                    }
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3054(Class4.aClass301_85.method3538(5, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort1((byte)(-86))), 1);
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3045((byte)88, aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.anInt1112);
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6414 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.anInt1091 << -821006205;
                    if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.method2302((byte)44)) {
                        Class98_Sub31_Sub4.method1383(null, null, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6437[0], 0, 3, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6438[0], aClass246_Sub3_Sub4_Sub2_Sub1_4187.aByte5088, aClass246_Sub3_Sub4_Sub2_Sub1_4187);
                    }
                }
                if (~(0x10 & unsignedByte) != -1) {
                    final int[] array9 = new int[4];
                    for (int n15 = 0; n15 < 4; ++n15) {
                        array9[n15] = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(49);
                        if (~array9[n15] == 0xFFFF0000) {
                            array9[n15] = -1;
                        }
                    }
                    Class98_Sub43.method1483(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(-85), aClass246_Sub3_Sub4_Sub2_Sub1_4187, 1, array9);
                }
                if ((0x1000 & unsignedByte) != 0x0) {
                    final int byteA2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteA(true);
                    final int[] array10 = new int[byteA2];
                    final int[] array11 = new int[byteA2];
                    final int[] array12 = new int[byteA2];
                    for (int n16 = 0; ~n16 > ~byteA2; ++n16) {
                        int short1 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort1((byte)58);
                        if (short1 == 65535) {
                            short1 = -1;
                        }
                        array10[n16] = short1;
                        array11[n16] = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(-26);
                        array12[n16] = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                    }
                    Class262.method3215(26256, array11, array12, aClass246_Sub3_Sub4_Sub2_Sub1_4187, array10);
                }
                if (~(unsignedByte & 0x100) != -1) {
                    int shortA3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(83);
                    if (shortA3 == 65535) {
                        shortA3 = -1;
                    }
                    final int int1 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt(-2);
                    final int byteC2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteC((byte)(-106));
                    final int n17 = byteC2 & 0x7;
                    int n18 = byteC2 >> -481470685 & 0xF;
                    if (~n18 == 0xFFFFFFF0) {
                        n18 = -1;
                    }
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3032(n18, true, int1, n17, shortA3, -96);
                }
                if ((unsignedByte & 0x40) != 0x0) {
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6510 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)115);
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6505 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)116);
                }
                if ((unsignedByte & 0x200) != 0x0) {
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.aByte6404 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readSignedByte((byte)(-19));
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.aByte6381 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1187((byte)(-112));
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.aByte6368 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1234(128);
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.aByte6422 = (byte)Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteA(true);
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6403 = Class215.anInt1614 + Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)96);
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6349 = Class215.anInt1614 - -Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort1((byte)100);
                }
                if ((0x1 & unsignedByte) != 0x0) {
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.aString6374 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6384 = 100;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lq.C(" + b + ')');
        }
    }
    
    static final void method274(final byte b, final int n) {
        try {
            Class185.method2628(n, -23, 9).method1621(b - 83);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lq.D(" + b + ',' + n + ')');
        }
    }
    
    static {
        Class21_Sub2.anIntArrayArray5386 = new int[][] { { 2, 4, 6, 0 }, { 0, 2, 4, 6 }, { 0, 2, 4 }, { 4, 0, 2 }, { 2, 4, 0 }, { 0, 2, 4 }, { 6, 0, 1, 2, 4, 5 }, { 0, 4, 7, 6 }, { 4, 7, 6, 0 }, { 0, 8, 6, 2, 9, 4 }, { 2, 9, 4, 0, 8, 6 }, { 2, 11, 4, 6, 10, 0 }, { 2, 4, 6, 0 } };
        Class21_Sub2.anInt5387 = -1;
    }
}
