import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class339
{
    static IncomingOpcode aClass58_2844;
    static Interface7[] anInterface7Array2845;
    static int[][] anIntArrayArray2846;
    
    static final void method3783(final ha ha, final d d, final int n) {
        try {
            if (Class278.aClass98_Sub46_Sub10_2056 != null && n >= 47) {
                if (Class212.anInt1600 < 10) {
                    if (!Class278.aClass207_2054.method2741(Class278.aClass98_Sub46_Sub10_2056.aString6017, 0)) {
                        Class212.anInt1600 = Class257.aClass207_1947.method2748(29952, Class278.aClass98_Sub46_Sub10_2056.aString6017) / 10;
                        return;
                    }
                    Class81.method817(false);
                    Class212.anInt1600 = 10;
                }
                if (Class212.anInt1600 == 10) {
                    Class278.anInt2078 = Class278.aClass98_Sub46_Sub10_2056.anInt6009 >> -91123994 << 2115773958;
                    Class278.anInt2075 = Class278.aClass98_Sub46_Sub10_2056.anInt6008 >> 1322363302 << 1884411078;
                    Class278.anInt2084 = -Class278.anInt2078 + ((Class278.aClass98_Sub46_Sub10_2056.anInt6023 >> 1885364678 << 969285766) + 64);
                    Class278.anInt2089 = 64 + (Class278.aClass98_Sub46_Sub10_2056.anInt6016 >> 1668330822 << 910225286) - Class278.anInt2075;
                    final int[] array = new int[3];
                    int n2 = -1;
                    int n3 = -1;
                    if (Class278.aClass98_Sub46_Sub10_2056.method1573(Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088, array, -90, aa_Sub2.anInt3562 + (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079 >> 219261193), (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084 >> -86706999) + Class272.anInt2038)) {
                        n3 = -Class278.anInt2078 + array[2];
                        n2 = array[1] + -Class278.anInt2075;
                    }
                    if (Class211.aBoolean1593 || ~n2 > -1 || ~Class278.anInt2089 >= ~n2 || ~n3 > -1 || ~Class278.anInt2084 >= ~n3) {
                        if (RuntimeException_Sub1.anInt3205 != -1 && ~Class98.anInt835 != 0x0) {
                            Class278.aClass98_Sub46_Sub10_2056.method1570((byte)100, Class98.anInt835, array, RuntimeException_Sub1.anInt3205);
                            if (array != null) {
                                Class42_Sub4.anInt5371 = array[1] - Class278.anInt2075;
                                Class98_Sub40.anInt4197 = array[2] - Class278.anInt2078;
                            }
                            RuntimeException_Sub1.anInt3205 = (Class98.anInt835 = -1);
                            Class211.aBoolean1593 = false;
                        }
                        else {
                            Class278.aClass98_Sub46_Sub10_2056.method1570((byte)117, Class278.aClass98_Sub46_Sub10_2056.anInt6006 & 0x3FFF, array, (Class278.aClass98_Sub46_Sub10_2056.anInt6006 & 0xFFFC780) >> 809556622);
                            Class98_Sub40.anInt4197 = -Class278.anInt2078 + array[2];
                            Class42_Sub4.anInt5371 = -Class278.anInt2075 + array[1];
                        }
                    }
                    else {
                        final int anInt4197 = n3 + (-5 + (int)(Math.random() * 10.0));
                        Class42_Sub4.anInt5371 = n2 + (-5 + (int)(10.0 * Math.random()));
                        Class98_Sub40.anInt4197 = anInt4197;
                    }
                    if (Class278.aClass98_Sub46_Sub10_2056.anInt6007 != 37) {
                        if (Class278.aClass98_Sub46_Sub10_2056.anInt6007 != 50) {
                            if (~Class278.aClass98_Sub46_Sub10_2056.anInt6007 != 0xFFFFFFB4) {
                                if (~Class278.aClass98_Sub46_Sub10_2056.anInt6007 == 0xFFFFFF9B) {
                                    Class278.aFloat2068 = (Class278.aFloat2064 = 8.0f);
                                }
                                else if (Class278.aClass98_Sub46_Sub10_2056.anInt6007 == 200) {
                                    Class278.aFloat2068 = (Class278.aFloat2064 = 16.0f);
                                }
                                else {
                                    Class278.aFloat2068 = (Class278.aFloat2064 = 8.0f);
                                }
                            }
                            else {
                                Class278.aFloat2068 = (Class278.aFloat2064 = 6.0f);
                            }
                        }
                        else {
                            Class278.aFloat2068 = (Class278.aFloat2064 = 4.0f);
                        }
                    }
                    else {
                        Class278.aFloat2068 = (Class278.aFloat2064 = 3.0f);
                    }
                    Class278.anInt2069 = (int)Class278.aFloat2064 >> 898065889;
                    Class278.aByteArrayArrayArray2072 = Class287_Sub2.method3392(Class278.anInt2069, (byte)126);
                    aa_Sub1.method155(-1);
                    Class278.method3297();
                    Class8.aClass148_110 = new Class148();
                    Class278.anInt2063 += -2 + (int)(Math.random() * 5.0);
                    if (Class278.anInt2063 < -8) {
                        Class278.anInt2063 = -8;
                    }
                    Class278.anInt2071 += (int)(Math.random() * 5.0) - 2;
                    if (Class278.anInt2063 > 8) {
                        Class278.anInt2063 = 8;
                    }
                    if (~Class278.anInt2071 > 15) {
                        Class278.anInt2071 = -16;
                    }
                    if (~Class278.anInt2071 < -17) {
                        Class278.anInt2071 = 16;
                    }
                    Class278.method3304(d, Class278.anInt2063 >> -2097260862 << 776330122, Class278.anInt2071 >> 386662625);
                    Class278.aClass341_2057.method3809(256, -30502, 1024);
                    Class278.aClass335_2059.method3771(119, 256, 256);
                    Class278.aClass302_2062.method3550(-129, 4096);
                    Class17.aClass198_205.method2679(256, (byte)(-90));
                    Class212.anInt1600 = 20;
                }
                else if (~Class212.anInt1600 == 0xFFFFFFEB) {
                    Class257.method3201((byte)86, true);
                    Class278.method3305(ha, Class278.anInt2063, Class278.anInt2071);
                    Class212.anInt1600 = 60;
                    Class257.method3201((byte)85, true);
                    Class32.method316(false);
                }
                else if (~Class212.anInt1600 == 0xFFFFFFC3) {
                    if (!Class278.aClass207_2054.method2728(Class278.aClass98_Sub46_Sub10_2056.aString6017 + "_staticelements", 0)) {
                        Class278.aClass370_2066 = new Class370(0);
                    }
                    else {
                        if (!Class278.aClass207_2054.method2741(Class278.aClass98_Sub46_Sub10_2056.aString6017 + "_staticelements", 0)) {
                            return;
                        }
                        Class278.aClass370_2066 = Class52.method491(77, Class79.aBoolean602, Class278.aClass207_2054, Class278.aClass98_Sub46_Sub10_2056.aString6017 + "_staticelements");
                    }
                    Class278.method3302();
                    Class212.anInt1600 = 70;
                    Class257.method3201((byte)65, true);
                    Class32.method316(false);
                }
                else if (Class212.anInt1600 == 70) {
                    Class339_Sub1.aClass326_5315 = new Class326(ha, 11, true, Class42_Sub3.aCanvas5361);
                    Class212.anInt1600 = 73;
                    Class257.method3201((byte)80, true);
                    Class32.method316(false);
                }
                else if (~Class212.anInt1600 == 0xFFFFFFB6) {
                    Class77_Sub1.aClass326_3805 = new Class326(ha, 12, true, Class42_Sub3.aCanvas5361);
                    Class212.anInt1600 = 76;
                    Class257.method3201((byte)46, true);
                    Class32.method316(false);
                }
                else if (Class212.anInt1600 == 76) {
                    Class339_Sub1.aClass326_5308 = new Class326(ha, 14, true, Class42_Sub3.aCanvas5361);
                    Class212.anInt1600 = 79;
                    Class257.method3201((byte)96, true);
                    Class32.method316(false);
                }
                else if (~Class212.anInt1600 == 0xFFFFFFB0) {
                    Class137.aClass326_1080 = new Class326(ha, 17, true, Class42_Sub3.aCanvas5361);
                    Class212.anInt1600 = 82;
                    Class257.method3201((byte)94, true);
                    Class32.method316(false);
                }
                else if (Class212.anInt1600 == 82) {
                    Class151_Sub7.aClass326_5009 = new Class326(ha, 19, true, Class42_Sub3.aCanvas5361);
                    Class212.anInt1600 = 85;
                    Class257.method3201((byte)49, true);
                    Class32.method316(false);
                }
                else if (Class212.anInt1600 == 85) {
                    Class271.aClass326_2033 = new Class326(ha, 22, true, Class42_Sub3.aCanvas5361);
                    Class212.anInt1600 = 88;
                    Class257.method3201((byte)104, true);
                    Class32.method316(false);
                }
                else if (~Class212.anInt1600 == 0xFFFFFFA7) {
                    Class224.aClass326_1686 = new Class326(ha, 26, true, Class42_Sub3.aCanvas5361);
                    Class212.anInt1600 = 91;
                    Class257.method3201((byte)60, true);
                    Class32.method316(false);
                }
                else {
                    Class260.aClass326_3263 = new Class326(ha, 30, true, Class42_Sub3.aCanvas5361);
                    Class212.anInt1600 = 100;
                    Class257.method3201((byte)62, true);
                    Class32.method316(false);
                    System.gc();
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "um.G(" + ((ha != null) ? "{...}" : "null") + ',' + ((d != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    abstract Class312 method3784(final byte p0);
    
    abstract void method3785(final int p0, final boolean p1);
    
    abstract byte[] method3786(final int p0, final int p1);
    
    public static void method3787(final byte b) {
        try {
            if (b == -69) {
                Class339.anInterface7Array2845 = null;
                Class339.anIntArrayArray2846 = null;
                Class339.aClass58_2844 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "um.C(" + b + ')');
        }
    }
    
    static final void method3788(final int n, final int n2, final int n3, final int n4, final byte b, final int n5, final int n6, final int n7, final int n8, final int n9) {
        try {
            if (n4 != n || ~n8 != ~n9 || n6 != n5 || ~n7 != ~n3) {
                int n10 = n;
                int n11 = n8;
                final int n12 = n * 3;
                final int n13 = n8 * 3;
                final int n14 = 3 * n4;
                final int n15 = n9 * 3;
                final int n16 = 3 * n6;
                final int n17 = n3 * 3;
                final int n18 = n14 + n5 + (-n16 - n);
                final int n19 = n7 + (-n17 + (n15 - n8));
                final int n20 = n16 + (-n14 - (n14 + -n12));
                final int n21 = -n15 + (-n15 + (n17 - -n13));
                final int n22 = n14 + -n12;
                final int n23 = n15 + -n13;
                for (int n24 = 128; ~n24 >= -4097; n24 += 128) {
                    final int n25 = n24 * n24 >> 671351852;
                    final int n26 = n24 * n25 >> 962258476;
                    final int n27 = n18 * n26;
                    final int n28 = n26 * n19;
                    final int n29 = n25 * n20;
                    final int n30 = n21 * n25;
                    final int n31 = n24 * n22;
                    final int n32 = n23 * n24;
                    final int n33 = n - -(n27 - (-n29 - n31) >> -1199794356);
                    final int n34 = (n32 + (n28 + n30) >> 962061708) + n8;
                    InputStream_Sub2.method125(n33, n2, n34, n11, n10, 21597);
                    n11 = n34;
                    n10 = n33;
                }
            }
            else {
                InputStream_Sub2.method125(n5, n2, n7, n8, n, 21597);
            }
            if (b != -67) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "um.F(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ')');
        }
    }
    
    static final void method3789(final int n) {
        try {
            if (Class308.aClass98_Sub46_Sub9_2583 != null) {
                Class308.aClass98_Sub46_Sub9_2583 = null;
                if (n != 70) {
                    method3788(-45, -2, -88, -42, (byte)(-67), 70, -12, -2, 77, 14);
                }
                Class246_Sub3_Sub4_Sub3.method3071(Class98_Sub43_Sub4.anInt5938, -1, Class5.anInt3439, Class282.anInt2128, Class163.anInt3518);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "um.E(" + n + ')');
        }
    }
    
    abstract int method3790(final int p0, final byte p1);
    
    static {
        Class339.anInterface7Array2845 = new Interface7[75];
        Class339.aClass58_2844 = new IncomingOpcode(74, 11);
        Class339.anIntArrayArray2846 = new int[128][128];
    }
}
