// 
// Decompiled by Procyon v0.5.30
// 

final class Class76_Sub7 extends Class76
{
    static boolean aBoolean3761;
    private Class195 aClass195_3762;
    private float aFloat3763;
    static Class332[] aClass332Array3764;
    static int[] anIntArray3765;
    
    public static void method761(final byte b) {
        try {
            if (b == 19) {
                Class76_Sub7.aClass332Array3764 = null;
                Class76_Sub7.anIntArray3765 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kha.F(" + b + ')');
        }
    }
    
    @Override
    final void method743(final int n, final boolean b) {
        try {
            super.aHa_Sub3_585.method2019(Class288.aClass128_3381, Class249.aClass128_1903, 22831);
            if (n < 93) {
                Class76_Sub7.anIntArray3765 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kha.D(" + n + ',' + b + ')');
        }
    }
    
    @Override
    final void method749(final int n) {
        try {
            if (n != 8) {
                this.aClass195_3762 = null;
            }
            if (~super.aHa_Sub3_585.method1967(n + 114) == -1) {
                final Class111_Sub3 method2061 = super.aHa_Sub3_585.method2061(1);
                super.aHa_Sub3_585.method1951((byte)120, 1);
                final Class111_Sub3 method2062 = super.aHa_Sub3_585.method1957((byte)(-105));
                method2062.method2092(method2061);
                method2062.method2138(1.0f, 0.125f, 0.125f, n - 115);
                method2062.method2141(-122, 0.0f, this.aFloat3763, 0.0f);
                super.aHa_Sub3_585.method2008(Class144.aClass258_1168, (byte)103);
                super.aHa_Sub3_585.method1951((byte)120, 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kha.M(" + n + ')');
        }
    }
    
    @Override
    final void method748(final int n, final boolean b) {
        try {
            super.aHa_Sub3_585.method1951((byte)120, 1);
            if (n != 69) {
                this.aClass195_3762 = null;
            }
            super.aHa_Sub3_585.method2019(Class288.aClass128_3381, Class1.aClass128_64, 22831);
            super.aHa_Sub3_585.method2026(0, true, (byte)27, Class300.aClass65_2499, false);
            super.aHa_Sub3_585.method1953(-78, IncomingOpcode.aClass65_459, 0);
            super.aHa_Sub3_585.method1994((byte)32, 0);
            super.aHa_Sub3_585.method1951((byte)120, 0);
            super.aHa_Sub3_585.method1984(2, -16777216);
            super.aHa_Sub3_585.method1953(n - 174, Class64_Sub16.aClass65_3681, 0);
            this.method749(8);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kha.B(" + n + ',' + b + ')');
        }
    }
    
    Class76_Sub7(final ha_Sub3 ha_Sub3, final Class195 aClass195_3762) {
        super(ha_Sub3);
        this.aFloat3763 = 0.0f;
        try {
            this.aClass195_3762 = aClass195_3762;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kha.<init>(" + ((ha_Sub3 != null) ? "{...}" : "null") + ',' + ((aClass195_3762 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method762(final int n, final Class98_Sub22 class98_Sub22, final boolean b) {
        try {
            if (Class195.aClass225_1502 != null) {
                try {
                    Class195.aClass225_1502.method2846(0L, 0);
                    Class195.aClass225_1502.method2852(24, n, -1, class98_Sub22.aByteArray3992);
                }
                catch (Exception ex2) {}
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kha.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    final void method746(final int n, final int n2, final int n3) {
        try {
            if (n3 > -75) {
                Class76_Sub7.aClass332Array3764 = null;
            }
            super.aHa_Sub3_585.method1951((byte)120, 1);
            if (~(0x80 & n) == -1) {
                if (~(n2 & 0x1) != 0xFFFFFFFE) {
                    if (this.aClass195_3762.aBoolean1501) {
                        super.aHa_Sub3_585.method2005(this.aClass195_3762.anInterface4_Impl1_1498, -1);
                    }
                    else {
                        super.aHa_Sub3_585.method2005(this.aClass195_3762.anInterface4_Impl2Array1503[0], 113);
                    }
                }
                else if (!this.aClass195_3762.aBoolean1501) {
                    super.aHa_Sub3_585.method2005(this.aClass195_3762.anInterface4_Impl2Array1503[super.aHa_Sub3_585.anInt4556 % 4000 * 16 / 4000], -122);
                }
                else {
                    this.aFloat3763 = super.aHa_Sub3_585.anInt4556 % 4000 / 4000.0f;
                    super.aHa_Sub3_585.method2005(this.aClass195_3762.anInterface4_Impl1_1498, -114);
                }
            }
            else {
                super.aHa_Sub3_585.method2005(null, 102);
            }
            super.aHa_Sub3_585.method1951((byte)120, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kha.E(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final boolean method763(final int n, final int n2, final boolean b) {
        try {
            if (b) {
                method763(-71, 94, true);
            }
            return (Class373_Sub2.method3974(n2, n, -35) | Class98.method944(n, n2, (byte)85) | Class195.method2663(n, n2, b)) & Class246_Sub2.method2973(n, n2, (byte)(-96));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kha.G(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    @Override
    final boolean method745(final byte b) {
        try {
            return b == 27 && this.aClass195_3762.method2659(b ^ 0xFFFFA889);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kha.H(" + b + ')');
        }
    }
    
    @Override
    final void method742(final int n, final int n2, final Interface4 interface4) {
        try {
            super.aHa_Sub3_585.method2005(interface4, -119);
            if (n != 6) {
                this.aClass195_3762 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kha.I(" + n + ',' + n2 + ',' + ((interface4 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method739(final int n) {
        try {
            super.aHa_Sub3_585.method1951((byte)120, 1);
            super.aHa_Sub3_585.method2019(Class249.aClass128_1903, Class249.aClass128_1903, 22831);
            if (n == -2) {
                super.aHa_Sub3_585.method2051(0, -98, Class300.aClass65_2499);
                super.aHa_Sub3_585.method1953(-110, Class300.aClass65_2499, 0);
                super.aHa_Sub3_585.method1994((byte)119, 1);
                super.aHa_Sub3_585.method2005(null, 63);
                super.aHa_Sub3_585.method1951((byte)120, 0);
                super.aHa_Sub3_585.method1953(-68, Class300.aClass65_2499, 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kha.C(" + n + ')');
        }
    }
    
    static {
        Class76_Sub7.aBoolean3761 = false;
    }
}
