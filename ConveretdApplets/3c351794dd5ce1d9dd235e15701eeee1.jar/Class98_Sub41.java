// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub41 extends Class98
{
    static IncomingOpcode aClass58_4199;
    static int anInt4200;
    String aString4201;
    static int anInt4202;
    static Object anObject4203;
    static float aFloat4204;
    
    public static void method1474(final boolean b) {
        try {
            Class98_Sub41.anObject4203 = null;
            Class98_Sub41.aClass58_4199 = null;
            if (b) {
                Class98_Sub41.aClass58_4199 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qda.B(" + b + ')');
        }
    }
    
    static final void method1475(final int n) {
        try {
            Class151_Sub9.anInt5028 = 0;
            final int leShortA = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)104);
            if (n >= 4) {
                final int shortA = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(121);
                final int unsignedByte = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)80);
                final boolean b = ~Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteA(true) == 0xFFFFFFFE;
                Class98_Sub10_Sub13.method1043((byte)(-65));
                Class98_Sub27.method1282((byte)8, unsignedByte);
                final int n2 = (-Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.anInt3991 + Class65.anInt496) / 16;
                Class98_Sub46_Sub17.anIntArrayArray6049 = new int[n2][4];
                for (int n3 = 0; ~n2 < ~n3; ++n3) {
                    for (int n4 = 0; ~n4 > -5; ++n4) {
                        Class98_Sub46_Sub17.anIntArrayArray6049[n3][n4] = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt(-2);
                    }
                }
                Class105.aByteArrayArray3414 = null;
                Class121.anIntArray1006 = new int[n2];
                client.aByteArrayArray3551 = new byte[n2][];
                Class377.anIntArray3178 = new int[n2];
                Class287.anIntArray2188 = new int[n2];
                Class246_Sub3_Sub4_Sub2_Sub2.aByteArrayArray6533 = new byte[n2][];
                Class255.aByteArrayArray3211 = new byte[n2][];
                Class377.aByteArrayArray3182 = new byte[n2][];
                Class98_Sub36.anIntArray4162 = new int[n2];
                Class76_Sub7.anIntArray3765 = null;
                Class302.anIntArray2517 = new int[n2];
                int n5 = 0;
                for (int n6 = (-(Class165.anInt1276 >> -1188333180) + shortA) / 8; ((Class165.anInt1276 >> -222953052) + shortA) / 8 >= n6; ++n6) {
                    for (int n7 = (leShortA - (Class98_Sub10_Sub7.anInt5572 >> 907774532)) / 8; (leShortA + (Class98_Sub10_Sub7.anInt5572 >> -1113312988)) / 8 >= n7; ++n7) {
                        Class121.anIntArray1006[n5] = (n6 << -1191642008) + n7;
                        Class287.anIntArray2188[n5] = Class234.aClass207_1748.method2750((byte)(-90), "m" + n6 + "_" + n7);
                        Class98_Sub36.anIntArray4162[n5] = Class234.aClass207_1748.method2750((byte)(-114), "l" + n6 + "_" + n7);
                        Class302.anIntArray2517[n5] = Class234.aClass207_1748.method2750((byte)(-77), "um" + n6 + "_" + n7);
                        Class377.anIntArray3178[n5] = Class234.aClass207_1748.method2750((byte)(-61), "ul" + n6 + "_" + n7);
                        ++n5;
                    }
                }
                Class251.method3170(-6547, leShortA, b, shortA, 11);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qda.A(" + n + ')');
        }
    }
    
    public Class98_Sub41() {
    }
    
    Class98_Sub41(final String aString4201, final int n) {
        try {
            this.aString4201 = aString4201;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qda.<init>(" + ((aString4201 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static {
        Class98_Sub41.anInt4200 = 0;
        Class98_Sub41.aClass58_4199 = new IncomingOpcode(20, 7);
    }
}
