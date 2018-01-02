import java.math.BigInteger;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub45 extends Class98
{
    static BigInteger aBigInteger4253;
    Class148 aClass148_4254;
    static String[] aStringArray4255;
    static OutgoingOpcode aClass171_4256;
    static int[] anIntArray4257;
    static int[][] anIntArrayArray4258;
    
    public static void method1518(final int n) {
        try {
            Class98_Sub45.aStringArray4255 = null;
            Class98_Sub45.aBigInteger4253 = null;
            Class98_Sub45.anIntArray4257 = null;
            Class98_Sub45.anIntArrayArray4258 = null;
            if (n != 5) {
                method1520(-23, 5);
            }
            Class98_Sub45.aClass171_4256 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tia.D(" + n + ')');
        }
    }
    
    static final void method1519(final Class293 class293, final boolean b) {
        try {
            final Class293 method3910 = Class360.method3910(b, class293);
            int n = 0;
            int n2 = 0;
            Label_0039: {
                if (method3910 == null) {
                    n = Class98_Sub25.anInt4024;
                    n2 = Class39_Sub1.anInt3593;
                    if (!client.aBoolean3553) {
                        break Label_0039;
                    }
                }
                n = method3910.anInt2258;
                n2 = method3910.anInt2311;
            }
            Class253.method3180(n, n2, 1375731712, class293, false);
            Class98_Sub8.method986(n2, n, class293, 115);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tia.C(" + ((class293 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final Class92 method1520(final int n, final int n2) {
        try {
            if (n2 != 14883) {
                Class98_Sub45.anIntArray4257 = null;
            }
            final Class92 class92 = (Class92)Class142.aClass79_1158.method802(-126, n);
            if (class92 != null) {
                return class92;
            }
            final byte[] method2745 = Class42_Sub1_Sub1.aClass207_6206.method2745(n, 0, false);
            final Class92 class93 = new Class92();
            if (method2745 != null) {
                class93.method894(-62, new Class98_Sub22(method2745));
            }
            class93.method897((byte)115);
            Class142.aClass79_1158.method805(n, class93, (byte)(-80));
            return class93;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tia.A(" + n + ',' + n2 + ')');
        }
    }
    
    public Class98_Sub45() {
        this.aClass148_4254 = new Class148();
    }
    
    static final void method1521(final byte b, final int n, final String s, final int n2, final String s2, final String s3, final String s4) {
        try {
            Class137.method2276(s4, n, s3, s, -1, null, (byte)(-99), n2, s2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tia.B(" + b + ',' + n + ',' + ((s != null) ? "{...}" : "null") + ',' + n2 + ',' + ((s2 != null) ? "{...}" : "null") + ',' + ((s3 != null) ? "{...}" : "null") + ',' + ((s4 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class98_Sub45.aStringArray4255 = new String[100];
        Class98_Sub45.aBigInteger4253 = new BigInteger("87d2c867cb1ce29ac740deea9b15092fe92b9bb7daa489ad025ea95852070fac17ae66144119f134f0ae13236b003a8399f8ff59e9c857b81c4ef765ee48b7d7", 16);
        Class98_Sub45.aClass171_4256 = new OutgoingOpcode(33, 3);
        Class98_Sub45.anIntArray4257 = new int[32];
        Class98_Sub45.anIntArrayArray4258 = new int[][] { { 2, 4 }, { 2, 4 }, { 5, 2, 4 }, { 4, 5, 2 }, { 2, 4, 5 }, { 5, 2, 4 }, { 1, 6, 2, 5 }, { 1, 6, 7, 1 }, { 6, 7, 1, 1 }, { 0, 8, 9, 8, 9, 4 }, { 8, 9, 4, 0, 8, 9 }, { 2, 10, 0, 10, 11, 11 }, { 2, 4 }, { 1, 6, 7, 1 }, { 1, 6, 7, 1 } };
    }
}
