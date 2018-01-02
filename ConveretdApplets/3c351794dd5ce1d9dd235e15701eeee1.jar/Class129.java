// 
// Decompiled by Procyon v0.5.30
// 

final class Class129
{
    static int anInt1026;
    static int[] anIntArray1027;
    
    static final synchronized byte[] method2225(final boolean b, final int n) {
        try {
            if (~n == 0xFFFFFF9B && Class69_Sub1.anInt5329 > 0) {
                final byte[] array = Class157.aByteArrayArray1248[--Class69_Sub1.anInt5329];
                Class157.aByteArrayArray1248[Class69_Sub1.anInt5329] = null;
                return array;
            }
            if (b) {
                Class129.anIntArray1027 = null;
            }
            if (n == 5000 && Class35.anInt336 > 0) {
                final byte[] array2 = Class366.aByteArrayArray3119[--Class35.anInt336];
                Class366.aByteArrayArray3119[Class35.anInt336] = null;
                return array2;
            }
            if (~n == 0xFFFF8ACF && Class10.anInt119 > 0) {
                final byte[] array3 = Class64_Sub11.aByteArrayArray3670[--Class10.anInt119];
                Class64_Sub11.aByteArrayArray3670[Class10.anInt119] = null;
                return array3;
            }
            if (Class190.aByteArrayArrayArray1468 != null) {
                for (int n2 = 0; Class111_Sub1.anIntArray4681.length > n2; ++n2) {
                    if (n == Class111_Sub1.anIntArray4681[n2] && ~Class98_Sub46_Sub13_Sub1.anIntArray6308[n2] < -1) {
                        final byte[][] array4 = Class190.aByteArrayArrayArray1468[n2];
                        final int[] anIntArray6308 = Class98_Sub46_Sub13_Sub1.anIntArray6308;
                        final int n3 = n2;
                        final int n4 = anIntArray6308[n3] - 1;
                        anIntArray6308[n3] = n4;
                        final byte[] array5 = array4[n4];
                        Class190.aByteArrayArrayArray1468[n2][Class98_Sub46_Sub13_Sub1.anIntArray6308[n2]] = null;
                        return array5;
                    }
                }
            }
            return new byte[n];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "io.D(" + b + ',' + n + ')');
        }
    }
    
    public static void method2226(final byte b) {
        try {
            Class129.anIntArray1027 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "io.C(" + b + ')');
        }
    }
    
    static final void method2227(final Class246_Sub3 class246_Sub3) {
        if (class246_Sub3 != null) {
            for (int i = 0; i < 2; ++i) {
                Class246_Sub3 class246_Sub4 = null;
                for (Class246_Sub3 aClass246_Sub3_5090 = Class379.aClass246_Sub3Array3198[i]; aClass246_Sub3_5090 != null; aClass246_Sub3_5090 = aClass246_Sub3_5090.aClass246_Sub3_5090) {
                    if (aClass246_Sub3_5090 == class246_Sub3) {
                        if (class246_Sub4 != null) {
                            class246_Sub4.aClass246_Sub3_5090 = aClass246_Sub3_5090.aClass246_Sub3_5090;
                        }
                        else {
                            Class379.aClass246_Sub3Array3198[i] = aClass246_Sub3_5090.aClass246_Sub3_5090;
                        }
                        Class358.aBoolean3033 = true;
                        return;
                    }
                    class246_Sub4 = aClass246_Sub3_5090;
                }
                Class246_Sub3 class246_Sub5 = null;
                for (Class246_Sub3 aClass246_Sub3_5091 = Class359.aClass246_Sub3Array3056[i]; aClass246_Sub3_5091 != null; aClass246_Sub3_5091 = aClass246_Sub3_5091.aClass246_Sub3_5090) {
                    if (aClass246_Sub3_5091 == class246_Sub3) {
                        if (class246_Sub5 != null) {
                            class246_Sub5.aClass246_Sub3_5090 = aClass246_Sub3_5091.aClass246_Sub3_5090;
                        }
                        else {
                            Class359.aClass246_Sub3Array3056[i] = aClass246_Sub3_5091.aClass246_Sub3_5090;
                        }
                        Class358.aBoolean3033 = true;
                        return;
                    }
                    class246_Sub5 = aClass246_Sub3_5091;
                }
                Class246_Sub3 class246_Sub6 = null;
                for (Class246_Sub3 aClass246_Sub3_5092 = Class130.aClass246_Sub3Array1029[i]; aClass246_Sub3_5092 != null; aClass246_Sub3_5092 = aClass246_Sub3_5092.aClass246_Sub3_5090) {
                    if (aClass246_Sub3_5092 == class246_Sub3) {
                        if (class246_Sub6 != null) {
                            class246_Sub6.aClass246_Sub3_5090 = aClass246_Sub3_5092.aClass246_Sub3_5090;
                        }
                        else {
                            Class130.aClass246_Sub3Array1029[i] = aClass246_Sub3_5092.aClass246_Sub3_5090;
                        }
                        Class358.aBoolean3033 = true;
                        return;
                    }
                    class246_Sub6 = aClass246_Sub3_5092;
                }
            }
        }
    }
    
    static final synchronized void method2228(final byte b, final byte[] array) {
        try {
            if (b <= 61) {
                Class129.anIntArray1027 = null;
            }
            if (array.length == 100 && Class69_Sub1.anInt5329 < 1000) {
                Class157.aByteArrayArray1248[Class69_Sub1.anInt5329++] = array;
            }
            else if (array.length == 5000 && Class35.anInt336 < 250) {
                Class366.aByteArrayArray3119[Class35.anInt336++] = array;
            }
            else if (~array.length == 0xFFFF8ACF && ~Class10.anInt119 > -51) {
                Class64_Sub11.aByteArrayArray3670[Class10.anInt119++] = array;
            }
            else if (Class190.aByteArrayArrayArray1468 != null) {
                for (int n = 0; ~n > ~Class111_Sub1.anIntArray4681.length; ++n) {
                    if (Class111_Sub1.anIntArray4681[n] == array.length && ~Class98_Sub46_Sub13_Sub1.anIntArray6308[n] > ~Class190.aByteArrayArrayArray1468[n].length) {
                        Class190.aByteArrayArrayArray1468[n][Class98_Sub46_Sub13_Sub1.anIntArray6308[n]++] = array;
                        break;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "io.A(" + b + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method2229() {
        if (Class246_Sub2.aClass172ArrayArrayArray5077 != null) {
            for (int i = 0; i < Class246_Sub2.aClass172ArrayArrayArray5077.length; ++i) {
                for (int j = 0; j < Class366.anInt3112; ++j) {
                    for (int k = 0; k < Class64_Sub9.anInt3662; ++k) {
                        if (Class246_Sub2.aClass172ArrayArrayArray5077[i][j][k] != null) {
                            Class246_Sub2.aClass172ArrayArrayArray5077[i][j][k].method2544(6730);
                        }
                        Class246_Sub2.aClass172ArrayArrayArray5077[i][j][k] = null;
                    }
                }
            }
        }
        Class246_Sub2.aClass172ArrayArrayArray5077 = null;
        Class98_Sub46_Sub2_Sub2.aSArray6298 = null;
        if (Class252.aClass172ArrayArrayArray1927 != null) {
            for (int l = 0; l < Class252.aClass172ArrayArrayArray1927.length; ++l) {
                for (int n = 0; n < Class366.anInt3112; ++n) {
                    for (int n2 = 0; n2 < Class64_Sub9.anInt3662; ++n2) {
                        if (Class252.aClass172ArrayArrayArray1927[l][n][n2] != null) {
                            Class252.aClass172ArrayArrayArray1927[l][n][n2].method2544(6730);
                        }
                        Class252.aClass172ArrayArrayArray1927[l][n][n2] = null;
                    }
                }
            }
        }
        Class252.aClass172ArrayArrayArray1927 = null;
        Class81.aSArray618 = null;
        Class98_Sub46_Sub1.aClass172ArrayArrayArray5948 = null;
        Class78.aSArray594 = null;
        Class74.aBooleanArrayArray551 = null;
        Class319.aBooleanArrayArray2702 = null;
        Class347.anIntArray2906 = null;
        Class34.aBooleanArrayArrayArray325 = null;
        Class64_Sub12.aBooleanArrayArrayArray3673 = null;
        Class64_Sub20.method633(0);
        if (Class246_Sub3_Sub5_Sub2.aClass246_Sub3_Sub4Array6273 != null) {
            for (int n3 = 0; n3 < Class347.anInt2907; ++n3) {
                Class246_Sub3_Sub5_Sub2.aClass246_Sub3_Sub4Array6273[n3] = null;
            }
            Class347.anInt2907 = 0;
        }
        Class379.aClass246_Sub3Array3198 = null;
        Class359.aClass246_Sub3Array3056 = null;
        Class130.aClass246_Sub3Array1029 = null;
        if (Class32.aClass246_Sub3Array307 != null) {
            for (int n4 = 0; n4 < Class32.aClass246_Sub3Array307.length; ++n4) {
                Class32.aClass246_Sub3Array307[n4] = null;
            }
            Class302.anInt2523 = 0;
        }
        if (Class246_Sub4_Sub2.aClass246_Sub3Array6173 != null) {
            for (int n5 = 0; n5 < Class246_Sub4_Sub2.aClass246_Sub3Array6173.length; ++n5) {
                Class246_Sub4_Sub2.aClass246_Sub3Array6173[n5] = null;
            }
            Class353.anInt3009 = 0;
        }
        if (Class98_Sub10_Sub31.aClass1Array5717 != null) {
            for (int n6 = 0; n6 < Class226.anInt1705; ++n6) {
                Class98_Sub10_Sub31.aClass1Array5717[n6] = null;
            }
            for (int n7 = 0; n7 < Class364.anInt3103; ++n7) {
                for (int n8 = 0; n8 < Class366.anInt3112; ++n8) {
                    for (int n9 = 0; n9 < Class64_Sub9.anInt3662; ++n9) {
                        Class373_Sub3.aLongArrayArrayArray5476[n7][n8][n9] = 0L;
                    }
                }
            }
            Class226.anInt1705 = 0;
        }
        Class160.method2511(1350);
        (Class98_Sub10_Sub27.aClass84_5692 = Class98_Sub10_Sub27.aClass84_5693).method833(0);
        Class299_Sub2.aByteArrayArray5291 = null;
        Class40.anIntArrayArray367 = null;
        Class304.aShortArrayArray2534 = null;
        if (Class98_Sub46_Sub5.aClass174Array5970 != null) {
            Class249.method3162();
            Class98_Sub10_Sub30.aHa5709.method1783(1);
            Class98_Sub10_Sub30.aHa5709.method1807(0);
        }
        if (Class98_Sub43_Sub3.aClass245Array5922 != null) {
            Class98_Sub43_Sub3.aClass245Array5922 = null;
        }
        Class98_Sub10_Sub30.aHa5709 = null;
    }
    
    static {
        Class129.anInt1026 = 0;
        Class129.anIntArray1027 = new int[] { 0, 2, 2, 2, 1, 1, 3, 3, 1, 3, 3, 4, 4 };
    }
}
