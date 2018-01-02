import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class4
{
    static boolean aBoolean79;
    static Class113 aClass113_80;
    static int anInt81;
    static Class348 aClass348_82;
    static int[] anIntArray83;
    static boolean aBoolean84;
    static Class301 aClass301_85;
    static Class279 aClass279_86;
    
    static final void method173(final int n, final int n2, final int n3, final Class293 class293, final aa aa, final int n4, final byte b, final Class332 class294) {
        try {
            if (class294 != null) {
                int n5 = 0;
                Label_0045: {
                    if (Class98_Sub46_Sub20_Sub2.anInt6319 != 4) {
                        n5 = ((int)Class98_Sub22_Sub2.aFloat5794 + Class204.anInt1553 & 0x3FFF);
                        if (!client.aBoolean3553) {
                            break Label_0045;
                        }
                    }
                    n5 = ((int)Class98_Sub22_Sub2.aFloat5794 & 0x3FFF);
                }
                final int n6 = 10 + Math.max(class293.anInt2311 / 2, class293.anInt2258 / 2);
                if (~(n6 * n6) <= ~(n4 * n4 - -(n2 * n2))) {
                    int n7 = Class284_Sub2_Sub2.anIntArray6200[n5];
                    if (b != -24) {
                        Class4.aClass348_82 = null;
                    }
                    int n8 = Class284_Sub2_Sub2.anIntArray6202[n5];
                    if (~Class98_Sub46_Sub20_Sub2.anInt6319 != 0xFFFFFFFB) {
                        n7 = 256 * n7 / (256 + Class151.anInt1213);
                        n8 = 256 * n8 / (256 + Class151.anInt1213);
                    }
                    class294.method3729((n7 * n2 - -(n8 * n4) >> -1366947250) + (class293.anInt2311 / 2 + n3 - class294.method3737() / 2), -(n2 * n8 + -(n4 * n7) >> -1991891442) + (class293.anInt2258 / 2 + n + -(class294.method3749() / 2)), aa, n3, n);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aea.C(" + n + ',' + n2 + ',' + n3 + ',' + ((class293 != null) ? "{...}" : "null") + ',' + ((aa != null) ? "{...}" : "null") + ',' + n4 + ',' + b + ',' + ((class294 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method174(final byte b) {
        try {
            if (Class64_Sub29.aClass196_3720 != Class98_Sub43_Sub2.aClass196_5908) {
                if (b != 99) {
                    method174((byte)(-1));
                }
                try {
                    Class203.method2704("tbrefresh", Class315.aClient3529, -26978);
                }
                catch (Throwable t) {}
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aea.A(" + b + ')');
        }
    }
    
    public static void method175(final int n) {
        try {
            if (n != 10) {
                Class4.aClass301_85 = null;
            }
            Class4.aClass348_82 = null;
            Class4.aClass301_85 = null;
            Class4.aClass113_80 = null;
            Class4.anIntArray83 = null;
            Class4.aClass279_86 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aea.B(" + n + ')');
        }
    }
    
    static {
        Class4.aBoolean79 = false;
        Class4.anInt81 = 0;
        Class4.aClass113_80 = new Class113(8, 1);
        Class4.aClass348_82 = new Class348(2, 4, 4, 0);
        Class4.anIntArray83 = new int[1];
        Class4.aClass279_86 = null;
    }
}
