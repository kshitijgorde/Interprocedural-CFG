// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub39 extends Class98
{
    Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4187;
    static double aDouble4188;
    static float[] aFloatArray4189;
    
    public static void method1467(final boolean b) {
        try {
            Class98_Sub39.aFloatArray4189 = null;
            if (!b) {
                Class98_Sub39.aDouble4188 = 0.4390183000949001;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pw.B(" + b + ')');
        }
    }
    
    static final void method1468(final int n) {
        try {
            Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1256(n ^ n);
            final int bits = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readBits((byte)(-104), 8);
            if (bits < Class150.anInt1211) {
                for (int i = bits; i < Class150.anInt1211; ++i) {
                    Class246_Sub3_Sub4_Sub3.anIntArray6451[Class259.anInt1954++] = Class325.anIntArray2726[i];
                }
            }
            if (Class150.anInt1211 < bits) {
                throw new RuntimeException("gnpov1");
            }
            Class150.anInt1211 = 0;
            for (int j = 0; j < bits; ++j) {
                final int n2 = Class325.anIntArray2726[j];
                final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4187 = ((Class98_Sub39)Class260.aClass377_3254.method3990(n2, -1)).aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                if (Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readBits((byte)(-74), 1) == 0) {
                    Class325.anIntArray2726[Class150.anInt1211++] = n2;
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6406 = Class201.anInt1544;
                }
                else {
                    final int bits2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readBits((byte)(-117), 2);
                    if (bits2 == 0) {
                        Class325.anIntArray2726[Class150.anInt1211++] = n2;
                        aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6406 = Class201.anInt1544;
                        Class76_Sub11.anIntArray3796[Class65.anInt502++] = n2;
                    }
                    else if (bits2 == 1) {
                        Class325.anIntArray2726[Class150.anInt1211++] = n2;
                        aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6406 = Class201.anInt1544;
                        aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3050(0, 1, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readBits((byte)(-16), 3));
                        if (Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readBits((byte)(-107), 1) == 1) {
                            Class76_Sub11.anIntArray3796[Class65.anInt502++] = n2;
                        }
                    }
                    else if (bits2 == 2) {
                        Class325.anIntArray2726[Class150.anInt1211++] = n2;
                        aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6406 = Class201.anInt1544;
                        if (Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readBits((byte)(-42), 1) != 1) {
                            aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3050(0, 0, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readBits((byte)(-36), 3));
                        }
                        else {
                            aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3050(n ^ 0xFFFFECB2, 2, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readBits((byte)(-58), 3));
                            aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3050(0, 2, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readBits((byte)(-45), 3));
                        }
                        if (Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readBits((byte)(-65), 1) == 1) {
                            Class76_Sub11.anIntArray3796[Class65.anInt502++] = n2;
                        }
                    }
                    else if (bits2 == 3) {
                        Class246_Sub3_Sub4_Sub3.anIntArray6451[Class259.anInt1954++] = n2;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pw.A(" + n + ')');
        }
    }
    
    Class98_Sub39(final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4187) {
        try {
            this.aClass246_Sub3_Sub4_Sub2_Sub1_4187 = aClass246_Sub3_Sub4_Sub2_Sub1_4187;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pw.<init>(" + ((aClass246_Sub3_Sub4_Sub2_Sub1_4187 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class98_Sub39.aFloatArray4189 = new float[4];
    }
}
