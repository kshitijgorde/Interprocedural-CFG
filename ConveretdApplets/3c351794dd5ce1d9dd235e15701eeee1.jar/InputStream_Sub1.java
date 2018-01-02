import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

final class InputStream_Sub1 extends InputStream
{
    static int[][][] anIntArrayArrayArray27;
    static IncomingOpcode aClass58_28;
    
    static final boolean method121(final Class246_Sub3 class246_Sub3, final boolean b, final byte[][][] array, final int n, final byte b2) {
        if (!Class302.aBoolean2526) {
            return false;
        }
        int aShort6159;
        int aShort6158 = aShort6159 = class246_Sub3.anInt5084 >> Class151_Sub8.anInt5015;
        int aShort6161;
        int aShort6160 = aShort6161 = class246_Sub3.anInt5079 >> Class151_Sub8.anInt5015;
        if (class246_Sub3 instanceof Class246_Sub3_Sub4) {
            aShort6159 = ((Class246_Sub3_Sub4)class246_Sub3).aShort6160;
            aShort6161 = ((Class246_Sub3_Sub4)class246_Sub3).aShort6159;
            aShort6158 = ((Class246_Sub3_Sub4)class246_Sub3).aShort6158;
            aShort6160 = ((Class246_Sub3_Sub4)class246_Sub3).aShort6157;
        }
        for (int i = aShort6158; i <= aShort6159; ++i) {
            for (int j = aShort6160; j <= aShort6161; ++j) {
                if (class246_Sub3.aByte5081 < Class364.anInt3103 && i >= Class306.anInt2561 && i < Class21_Sub2.anInt5388 && j >= OutgoingOpcode.anInt1318 && j < Class32.anInt303) {
                    if ((array == null || class246_Sub3.aByte5088 < n || array[class246_Sub3.aByte5088][i][j] != b2) && class246_Sub3.method2991(false) && !class246_Sub3.method2977(Class98_Sub10_Sub30.aHa5709, (byte)77)) {
                        return false;
                    }
                    if (!b && i >= Class241.anInt1845 - 16 && i <= Class241.anInt1845 + 16 && j >= Class64_Sub26.anInt3714 - 16 && j <= Class64_Sub26.anInt3714 + 16) {
                        if (Class375.aBoolean3170) {
                            Class98_Sub43_Sub3.aClass245Array5922[Class252.anInt1923++].method2958((byte)115, class246_Sub3);
                            Class252.anInt1923 %= Class18.anInt212;
                        }
                        else {
                            class246_Sub3.method2988(Class98_Sub10_Sub30.aHa5709, -51);
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public static void method122(final boolean b) {
        try {
            if (b) {
                method122(true);
            }
            InputStream_Sub1.aClass58_28 = null;
            InputStream_Sub1.anIntArrayArrayArray27 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "baa.B(" + b + ')');
        }
    }
    
    @Override
    public final int read() {
        try {
            Class246_Sub7.method3131(0, 30000L);
            return -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "baa.read()");
        }
    }
    
    static {
        InputStream_Sub1.anIntArrayArrayArray27 = new int[2][][];
        InputStream_Sub1.aClass58_28 = new IncomingOpcode(22, 6);
    }
}
