// 
// Decompiled by Procyon v0.5.30
// 

final class Class45
{
    static Class75 aClass75_381;
    static String aString382;
    static Class332 aClass332_383;
    static int anInt384;
    static Class207 aClass207_385;
    
    static final void method430(final boolean b, final int anInt6054, final int n) {
        try {
            final Class98_Sub46_Sub17 method2628 = Class185.method2628(n, -126, 14);
            method2628.method1626((byte)(-103));
            method2628.anInt6054 = anInt6054;
            if (!b) {
                method432((byte)111);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dd.C(" + b + ',' + anInt6054 + ',' + n + ')');
        }
    }
    
    static final void method431(final long n, final boolean b, final ha ha) {
        try {
            Class246_Sub3_Sub1_Sub2.anInt6252 = 0;
            if (!b) {
                Class45.aClass207_385 = null;
            }
            Class98_Sub43_Sub3.anInt5924 = 0;
            Class237_Sub1.anInt5047 = Class113.anInt952;
            Class113.anInt952 = 0;
            final long method3819 = Class343.method3819(-47);
            for (Class246_Sub5 class246_Sub5 = (Class246_Sub5)Class267.aClass218_2002.method2803((byte)15); class246_Sub5 != null; class246_Sub5 = (Class246_Sub5)Class267.aClass218_2002.method2809(false)) {
                if (class246_Sub5.method3121(ha, n)) {
                    ++Class98_Sub43_Sub3.anInt5924;
                }
            }
            if (Class246.aBoolean1870 && n % 100L == 0L) {
                System.out.println("Particle system count: " + Class267.aClass218_2002.method2811(15) + ", running: " + Class98_Sub43_Sub3.anInt5924);
                System.out.println("Emitters: " + Class246_Sub3_Sub1_Sub2.anInt6252 + " Particles: " + Class113.anInt952 + ". Time taken: " + (-method3819 + Class343.method3819(-47)) + "ms");
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dd.B(" + n + ',' + b + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method432(final byte b) {
        try {
            Class45.aString382 = null;
            Class45.aClass332_383 = null;
            Class45.aClass207_385 = null;
            Class45.aClass75_381 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dd.A(" + b + ')');
        }
    }
    
    static {
        Class45.aString382 = "";
    }
}
