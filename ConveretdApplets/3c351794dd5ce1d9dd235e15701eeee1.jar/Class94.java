// 
// Decompiled by Procyon v0.5.30
// 

final class Class94
{
    static Class207 aClass207_793;
    static long[] aLongArray794;
    static int anInt795;
    static Class207 aClass207_796;
    static boolean aBoolean797;
    
    static final Class154 method914(final int n, final int n2, final int n3) {
        final Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n][n2][n3];
        if (class172 == null) {
            return null;
        }
        return class172.aClass154_1325;
    }
    
    static final Class246_Sub1 method915(final int i, final byte b, final boolean aBoolean5070) {
        try {
            synchronized (Class98_Sub46_Sub20_Sub2.aClass218Array6316) {
                if (b != -47) {
                    return null;
                }
                Class246_Sub1 class246_Sub1;
                if (Class98_Sub46_Sub20_Sub2.aClass218Array6316.length > i && !Class98_Sub46_Sub20_Sub2.aClass218Array6316[i].method2812(true)) {
                    class246_Sub1 = (Class246_Sub1)Class98_Sub46_Sub20_Sub2.aClass218Array6316[i].method2810((byte)(-39));
                    class246_Sub1.method2965((byte)126);
                    final int[] anIntArray65 = Class1.anIntArray65;
                    --anIntArray65[i];
                }
                else {
                    class246_Sub1 = new Class246_Sub1();
                    class246_Sub1.aClass246_Sub6Array5067 = new Class246_Sub6[i];
                    for (int n = 0; i > n; ++n) {
                        class246_Sub1.aClass246_Sub6Array5067[n] = new Class246_Sub6();
                    }
                }
                class246_Sub1.aBoolean5070 = aBoolean5070;
                return class246_Sub1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fr.C(" + i + ',' + b + ',' + aBoolean5070 + ')');
        }
    }
    
    static final void method916(final int n, final Class207 aClass207_5783, final Class207 class207, final Class207 aClass207_5784, final Class207 aClass207_5785) {
        try {
            Class98_Sub17_Sub1.aClass207_5783 = aClass207_5783;
            Class340.aClass207_2847 = aClass207_5785;
            if (n >= -74) {
                method914(109, 98, -81);
            }
            Class166.aClass207_1280 = aClass207_5784;
            Class159.aClass293ArrayArray1252 = new Class293[Class98_Sub17_Sub1.aClass207_5783.method2752((byte)(-11))][];
            Class246_Sub3_Sub3_Sub1.aBooleanArray6256 = new boolean[Class98_Sub17_Sub1.aClass207_5783.method2752((byte)(-11))];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fr.A(" + n + ',' + ((aClass207_5783 != null) ? "{...}" : "null") + ',' + ((class207 != null) ? "{...}" : "null") + ',' + ((aClass207_5784 != null) ? "{...}" : "null") + ',' + ((aClass207_5785 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final boolean method917(final byte b, final int n, final int n2) {
        try {
            return b <= -90 && ((Class195.method2663(n2, n, false) | ~(n2 & 0x70000) != -1) || Class76_Sub7.method763(n2, n, false));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fr.E(" + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    public static void method918(final int n) {
        try {
            Class94.aLongArray794 = null;
            Class94.aClass207_793 = null;
            Class94.aClass207_796 = null;
            if (n != -19406) {
                Class94.aClass207_793 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fr.B(" + n + ')');
        }
    }
    
    static final void method919(final String aString6052, final int n, final int n2) {
        try {
            final Class98_Sub46_Sub17 method2628 = Class185.method2628(n, -78, 2);
            method2628.method1626((byte)(-103));
            method2628.aString6052 = aString6052;
            if (n2 < 28) {
                method915(30, (byte)(-45), true);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fr.F(" + ((aString6052 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class94.aLongArray794 = new long[100];
        Class94.aBoolean797 = false;
        Class94.anInt795 = -1;
    }
}
