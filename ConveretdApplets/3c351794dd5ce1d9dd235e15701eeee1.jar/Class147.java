// 
// Decompiled by Procyon v0.5.30
// 

final class Class147
{
    String aString1185;
    String aString1186;
    byte aByte1187;
    int anInt1188;
    static String[] aStringArray1189;
    String aString1190;
    String aString1191;
    static IncomingOpcode aClass58_1192;
    static int anInt1193;
    static int anInt1194;
    
    static final void method2411(final int anInt2007) {
        try {
            Class76_Sub8.anInt3780 = 0;
            Class268.anInt2007 = anInt2007;
            for (int n = 0; Class63.anInt493 > n; ++n) {
                final int n2 = Class191.anInt1477 * n;
                for (int n3 = 0; ~n3 > ~Class191.anInt1477; ++n3) {
                    Class172.anInterface17Array1327[n3 + n2].method56(Class197.anInt1513 * n3, Class98_Sub10_Sub38.anInt5761 * n, Class197.anInt1513, Class98_Sub10_Sub38.anInt5761, 0, 0, true, true);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kb.B(" + anInt2007 + ')');
        }
    }
    
    static final void method2412(final int n) {
        try {
            Class49.method477(-5788);
            Class98_Sub30.aClass58_4094 = null;
            Class98_Sub10_Sub6.anInt5569 = 0;
            Class65.anInt496 = 0;
            Class98_Sub10_Sub21.aClass58_5641 = null;
            Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.anInt3991 = 0;
            Class92.currentIncommingOpcode = null;
            Class260.aClass58_3262 = null;
            Class224_Sub1.anInt5031 = 0;
            Class64_Sub18.method622((byte)(-38));
            Class248.method3159(0);
            if (n > -94) {
                Class147.anInt1194 = -63;
            }
            for (int n2 = 0; ~n2 > -2049; ++n2) {
                Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[n2] = null;
            }
            Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660 = null;
            for (int i = 0; i < Class98_Sub10_Sub20.anInt5640; ++i) {
                final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4187 = Class163.aClass98_Sub39Array3516[i].aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                if (aClass246_Sub3_Sub4_Sub2_Sub1_4187 != null) {
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6364 = -1;
                }
            }
            Class98_Sub10_Sub34.method1106((byte)(-61));
            Class116.anInt967 = (Class64_Sub26.anInt3712 = -1);
            Class98_Sub46_Sub20_Sub2.anInt6319 = 1;
            Class61.method538(10, false);
            for (int j = 0; j < 100; ++j) {
                aa_Sub3.aBooleanArray3574[j] = true;
            }
            Class98_Sub17_Sub1.method1158(-2);
            Class284.aClass98_Sub4_2167 = null;
            Class11.aLong121 = 0L;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kb.D(" + n + ')');
        }
    }
    
    static final void method2413(final Class98_Sub22_Sub1 class98_Sub22_Sub1, final int n) {
        try {
            for (int n2 = 0; ~Class38.anInt354 < ~n2; ++n2) {
                final int n3 = Class65.anIntArray501[n2];
                final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[n3];
                int unsignedByte = class98_Sub22_Sub1.readUnsignedByte((byte)35);
                if ((0x20 & unsignedByte) != 0x0) {
                    unsignedByte += class98_Sub22_Sub1.readUnsignedByte((byte)36) << -475812536;
                }
                if ((0x800 & unsignedByte) != 0x0) {
                    unsignedByte += class98_Sub22_Sub1.readUnsignedByte((byte)95) << 1299142480;
                }
                PlayerUpdateMask.method709(class246_Sub3_Sub4_Sub2_Sub2, unsignedByte, class98_Sub22_Sub1, (byte)82, n3);
            }
            if (n != 8429) {
                method2413(null, 30);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kb.A(" + ((class98_Sub22_Sub1 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public static void method2414(final int n) {
        try {
            Class147.aStringArray1189 = null;
            if (n <= -96) {
                Class147.aClass58_1192 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kb.C(" + n + ')');
        }
    }
    
    static {
        Class147.aClass58_1192 = new IncomingOpcode(115, 8);
        Class147.anInt1193 = 0;
    }
}
