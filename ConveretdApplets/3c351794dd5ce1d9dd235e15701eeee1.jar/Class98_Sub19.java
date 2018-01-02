// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub19 extends Class98
{
    int[] anIntArray3953;
    int anInt3954;
    static int anInt3955;
    static int anInt3956;
    int[] anIntArray3957;
    byte[][][] aByteArrayArrayArray3958;
    int[] anIntArray3959;
    int anInt3960;
    Class143[] aClass143Array3961;
    Class143[] aClass143Array3962;
    
    static final void method1164(final int anInt6051, final int anInt6052, final int anInt6053, final int n, final int n2) {
        try {
            final Class98_Sub46_Sub17 method2628 = Class185.method2628(n2, -105, n);
            method2628.method1626((byte)(-103));
            method2628.anInt6051 = anInt6051;
            method2628.anInt6054 = anInt6053;
            method2628.anInt6053 = anInt6052;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ia.B(" + anInt6051 + ',' + anInt6052 + ',' + anInt6053 + ',' + n + ',' + n2 + ')');
        }
    }
    
    static final void method1165(final byte b, final String s) {
        try {
            if (Class98_Sub10_Sub9.aBoolean5585 && ~(0x18 & Class98_Sub4.anInt3826) != -1 && b == 36) {
                boolean b2 = false;
                final int anInt71 = Class2.anInt71;
                final int[] anIntArray2705 = Class319.anIntArray2705;
                for (int n = 0; ~n > ~anInt71; ++n) {
                    final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[anIntArray2705[n]];
                    if (class246_Sub3_Sub4_Sub2_Sub2.aString6537 != null && class246_Sub3_Sub4_Sub2_Sub2.aString6537.equalsIgnoreCase(s) && ((Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660 == class246_Sub3_Sub4_Sub2_Sub2 && (0x10 & Class98_Sub4.anInt3826) != 0x0) || (class246_Sub3_Sub4_Sub2_Sub2 != null && (Class98_Sub4.anInt3826 & 0x8) != 0x0))) {
                        ++client.anInt3548;
                        final Class98_Sub11 method3023 = Class246_Sub3_Sub4.method3023(260, Class160.aClass171_1259, Class331.aClass117_2811);
                        method3023.aClass98_Sub22_Sub1_3865.writeLEShortA(anIntArray2705[n], 128);
                        method3023.aClass98_Sub22_Sub1_3865.writeShortA(Class310.anInt2652, (byte)126);
                        method3023.aClass98_Sub22_Sub1_3865.writeLEInt(Class187.anInt1450, 1046032984);
                        method3023.aClass98_Sub22_Sub1_3865.method1231(0, (byte)(-95));
                        method3023.aClass98_Sub22_Sub1_3865.writeShortA(Class376.anInt3173, (byte)126);
                        Class98_Sub10_Sub29.sendPacket(false, method3023);
                        Class76_Sub2.requestFlag(0, 0, class246_Sub3_Sub4_Sub2_Sub2.method3034(0), -2, 0, class246_Sub3_Sub4_Sub2_Sub2.anIntArray6438[0], class246_Sub3_Sub4_Sub2_Sub2.anIntArray6437[0], true, class246_Sub3_Sub4_Sub2_Sub2.method3034(0));
                        b2 = true;
                        break;
                    }
                }
                if (!b2) {
                    za_Sub2.method1684(Class309.aClass309_2607.method3615(Class374.anInt3159, (byte)25) + s, 4, (byte)71);
                }
                if (Class98_Sub10_Sub9.aBoolean5585) {
                    Class98_Sub10_Sub32.method1098((byte)119);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ia.A(" + b + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class98_Sub19.anInt3955 = 0;
    }
}
