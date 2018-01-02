// 
// Decompiled by Procyon v0.5.30
// 

final class Class76_Sub5 extends Class76
{
    private boolean aBoolean3742;
    static int[] anIntArray3743;
    static int[] anIntArray3744;
    static Class111 aClass111_3745;
    static int anInt3746;
    static double aDouble3747;
    static int anInt3748;
    
    @Override
    final boolean method745(final byte b) {
        try {
            return b == 27;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jh.H(" + b + ')');
        }
    }
    
    @Override
    final void method746(final int n, final int n2, final int n3) {
        try {
            if (n3 >= -75) {
                method757((byte)40);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jh.E(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method742(final int n, final int n2, final Interface4 interface4) {
        try {
            if (n != 6) {
                this.method746(62, 22, -72);
            }
            super.aHa_Sub3_585.method2005(interface4, 0);
            super.aHa_Sub3_585.method2015(n2, (byte)79);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jh.I(" + n + ',' + n2 + ',' + ((interface4 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method743(final int n, final boolean b) {
        try {
            if (n < 93) {
                this.method746(-61, 44, -112);
            }
            super.aHa_Sub3_585.method2019(Class288.aClass128_3381, Class249.aClass128_1903, 22831);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jh.D(" + n + ',' + b + ')');
        }
    }
    
    public static void method757(final byte b) {
        try {
            Class76_Sub5.aClass111_3745 = null;
            if (b == 4) {
                Class76_Sub5.anIntArray3744 = null;
                Class76_Sub5.anIntArray3743 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jh.A(" + b + ')');
        }
    }
    
    static final boolean method758(final byte b, final int n, final int n2, final int n3) {
        try {
            if (!Class98_Sub17.aBoolean3942 || !Class135.aBoolean1052) {
                return false;
            }
            if (Class4.anInt81 < 100) {
                return false;
            }
            final int n4 = Class98_Sub46_Sub13_Sub2.anIntArrayArrayArray6311[n][n3][n2];
            if (~n4 == ~(-Class356.anInt3020)) {
                return false;
            }
            if (Class356.anInt3020 == n4) {
                return true;
            }
            if (Class78.aSArray594 == Class81.aSArray618) {
                return false;
            }
            final int n5 = n3 << Class151_Sub8.anInt5015;
            final int n6 = n2 << Class151_Sub8.anInt5015;
            if (Class254.method3187(Class78.aSArray594[n].method3420(n2 + 1, -12639, n3 + 1), 1 + n5, n6 + 1, (byte)82, Class78.aSArray594[n].method3420(n2, -12639, n3), n6 - -r_Sub2.anInt6333 - 1, Class78.aSArray594[n].method3420(1 + n2, -12639, n3), n6 - (-r_Sub2.anInt6333 + 1), r_Sub2.anInt6333 + n5 - 1, 1 + n5) && Class254.method3187(Class78.aSArray594[n].method3420(n2, -12639, n3 + 1), r_Sub2.anInt6333 + n5 - 1, n6 + 1, (byte)82, Class78.aSArray594[n].method3420(n2, -12639, n3), -1 + r_Sub2.anInt6333 + n6, Class78.aSArray594[n].method3420(1 + n2, -12639, n3 + 1), n6 + 1, -1 + (n5 - -r_Sub2.anInt6333), 1 + n5)) {
                ++Class302.anInt2518;
                Class98_Sub46_Sub13_Sub2.anIntArrayArrayArray6311[n][n3][n2] = Class356.anInt3020;
                return true;
            }
            Class98_Sub46_Sub13_Sub2.anIntArrayArrayArray6311[n][n3][n2] = -Class356.anInt3020;
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jh.G(" + b + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method739(final int n) {
        try {
            Label_0130: {
                if (!this.aBoolean3742) {
                    super.aHa_Sub3_585.method1953(n ^ 0x6B, Class300.aClass65_2499, 0);
                    if (!client.aBoolean3553) {
                        break Label_0130;
                    }
                }
                super.aHa_Sub3_585.method1951((byte)120, 1);
                super.aHa_Sub3_585.method1964(r_Sub2.aClass38_6334, (byte)26);
                super.aHa_Sub3_585.method2019(Class249.aClass128_1903, Class249.aClass128_1903, 22831);
                super.aHa_Sub3_585.method2051(2, -92, Class64_Sub16.aClass65_3681);
                super.aHa_Sub3_585.method1953(-84, Class300.aClass65_2499, 0);
                super.aHa_Sub3_585.method1985(2);
                super.aHa_Sub3_585.method2005(null, n - 116);
                super.aHa_Sub3_585.method1951((byte)120, 0);
                this.aBoolean3742 = false;
            }
            super.aHa_Sub3_585.method2019(Class249.aClass128_1903, Class249.aClass128_1903, 22831);
            if (n != -2) {
                method757((byte)(-25));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jh.C(" + n + ')');
        }
    }
    
    static final void method759(final byte b, final int[][] anIntArrayArray814) {
        try {
            Class97.anIntArrayArray814 = anIntArrayArray814;
            if (b >= -62) {
                Class76_Sub5.anIntArray3744 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jh.F(" + b + ',' + ((anIntArrayArray814 != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class76_Sub5(final ha_Sub3 ha_Sub3) {
        super(ha_Sub3);
        this.aBoolean3742 = false;
    }
    
    @Override
    final void method748(final int n, final boolean b) {
        try {
            final Interface4_Impl3 method1939 = super.aHa_Sub3_585.method1939(-109);
            Label_0180: {
                if (method1939 != null && b) {
                    super.aHa_Sub3_585.method1951((byte)120, 1);
                    super.aHa_Sub3_585.method2005(method1939, n - 6);
                    super.aHa_Sub3_585.method1964(Class204.aClass38_1552, (byte)26);
                    super.aHa_Sub3_585.method1951((byte)120, 1);
                    super.aHa_Sub3_585.method2019(Class288.aClass128_3381, Class323.aClass128_2715, 22831);
                    super.aHa_Sub3_585.method2026(2, true, (byte)27, IncomingOpcode.aClass65_459, false);
                    super.aHa_Sub3_585.method1953(-101, Class98_Sub43_Sub3.aClass65_5926, 0);
                    super.aHa_Sub3_585.method1957((byte)(-101)).method2140(super.aHa_Sub3_585.method2023(1), 0);
                    super.aHa_Sub3_585.method2008(Class144.aClass258_1168, (byte)120);
                    super.aHa_Sub3_585.method1951((byte)120, 0);
                    this.aBoolean3742 = true;
                    if (!client.aBoolean3553) {
                        break Label_0180;
                    }
                }
                super.aHa_Sub3_585.method1953(-127, Class98_Sub43_Sub3.aClass65_5926, 0);
            }
            if (n != 69) {
                this.method743(-119, true);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jh.B(" + n + ',' + b + ')');
        }
    }
    
    static {
        Class76_Sub5.anInt3748 = 0;
        Class76_Sub5.anIntArray3743 = new int[50];
    }
}
