import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class35
{
    int anInt327;
    Class35 aClass35_328;
    int anInt329;
    int anInt330;
    int anInt331;
    static Class85 aClass85_332;
    static int[] anIntArray333;
    Class111 aClass111_334;
    static String[] aStringArray335;
    static int anInt336;
    int anInt337;
    
    final Class66 method331(final byte b) {
        try {
            if (b < 89) {
                return null;
            }
            return Class21.method263(this.anInt329, 31866);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cia.D(" + b + ')');
        }
    }
    
    static final int method332(final int n) {
        try {
            if (n != 2) {
                method334(-6);
            }
            if (Class2.anIntArray70 == null) {
                return 0;
            }
            return Class2.anIntArray70.length * 2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cia.E(" + n + ')');
        }
    }
    
    static final void method333(final Class246_Sub1 class246_Sub1, final int n) {
        try {
            class246_Sub1.aClass246_Sub3_5069 = null;
            final int length = class246_Sub1.aClass246_Sub6Array5067.length;
            for (int n2 = 0; ~length < ~n2; ++n2) {
                class246_Sub1.aClass246_Sub6Array5067[n2].aBoolean5114 = false;
            }
            synchronized (Class98_Sub46_Sub20_Sub2.aClass218Array6316) {
                if (Class98_Sub46_Sub20_Sub2.aClass218Array6316.length > length && ~Class1.anIntArray65[length] > -201) {
                    Class98_Sub46_Sub20_Sub2.aClass218Array6316[length].method2808(true, class246_Sub1);
                    final int[] anIntArray65 = Class1.anIntArray65;
                    final int n3 = length;
                    ++anIntArray65[n3];
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cia.A(" + ((class246_Sub1 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public static void method334(final int n) {
        try {
            if (n > -103) {
                Class35.aStringArray335 = null;
            }
            Class35.aClass85_332 = null;
            Class35.anIntArray333 = null;
            Class35.aStringArray335 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cia.C(" + n + ')');
        }
    }
    
    static long method335(final long n, final long n2) {
        try {
            return n & n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cia.H(" + n + ',' + n2 + ')');
        }
    }
    
    final Class35 method336(final int n, final int n2) {
        try {
            if (n != -1854) {
                this.method331((byte)(-2));
            }
            return new Class35(this.anInt329, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cia.B(" + n + ',' + n2 + ')');
        }
    }
    
    static final int method337(final int n, final String s) {
        try {
            if (!Class242.aClass88_1848.aBoolean682) {
                return -1;
            }
            if (Class124.aHashtable1015.containsKey(s)) {
                return 100;
            }
            final String method2315 = Class145.method2315(79, s);
            if (method2315 == null) {
                return -1;
            }
            final String string = Class343.aString2863 + method2315;
            if (!Class223.aClass207_1681.method2737(true, "", string)) {
                return -1;
            }
            if (!Class223.aClass207_1681.method2741(string, 0)) {
                return Class223.aClass207_1681.method2748(29952, string);
            }
            if (n > -28) {
                Class35.anIntArray333 = null;
            }
            final byte[] method2316 = Class223.aClass207_1681.method2739(string, "", -32734);
            File method2317;
            try {
                method2317 = Class316.method3649(method2315, -127);
            }
            catch (RuntimeException ex2) {
                return -1;
            }
            if (method2316 != null && method2317 != null) {
                boolean b = true;
                final byte[] method2318 = Class273.method3281(-67, method2317);
                if (method2318 != null && method2318.length == method2316.length) {
                    for (int n2 = 0; ~n2 > ~method2318.length; ++n2) {
                        if (method2316[n2] != method2318[n2]) {
                            b = false;
                            break;
                        }
                    }
                }
                else {
                    b = false;
                }
                try {
                    if (!b) {
                        Class242.aClass88_1848.method876(method2316, true, method2317);
                    }
                }
                catch (Throwable t) {
                    return -1;
                }
                Class221.method2821(method2317, s, -320);
                return 100;
            }
            return -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cia.G(" + n + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final int method338(int n, int n2, final int n3, final int n4, final int n5, int n6, final byte b) {
        try {
            n2 &= 0x3;
            if (b != -23) {
                return 76;
            }
            if ((n3 & 0x1) == 0x1) {
                final int n7 = n6;
                n6 = n;
                n = n7;
            }
            if (n2 == 0) {
                return n5;
            }
            if (~n2 == 0xFFFFFFFE) {
                return 1 - n6 + (-n4 + 7);
            }
            if (~n2 == 0xFFFFFFFD) {
                return -n5 + 7 - n + 1;
            }
            return n4;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cia.F(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + b + ')');
        }
    }
    
    Class35(final int anInt329, final int anInt330) {
        try {
            this.anInt329 = anInt329;
            this.anInt327 = anInt330;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cia.<init>(" + anInt329 + ',' + anInt330 + ')');
        }
    }
    
    static {
        Class35.aStringArray335 = new String[5];
        Class35.anInt336 = 0;
        Class35.aClass85_332 = new Class85(7, 19);
    }
}
