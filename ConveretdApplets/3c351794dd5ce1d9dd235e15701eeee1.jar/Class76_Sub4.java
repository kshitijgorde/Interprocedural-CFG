// 
// Decompiled by Procyon v0.5.30
// 

final class Class76_Sub4 extends Class76
{
    static Class377 aClass377_3738;
    private boolean aBoolean3739;
    private Interface4_Impl3[] anInterface4_Impl3Array3740;
    private boolean aBoolean3741;
    
    public static void method753(final int n) {
        try {
            Class76_Sub4.aClass377_3738 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iea.O(" + n + ')');
        }
    }
    
    static final void method754(final int n, final boolean b, final int n2) {
        try {
            Class151_Sub9.method2472(true, Class309.aClass309_2598.method3615(Class374.anInt3159, (byte)25), n, b);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iea.F(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    Class76_Sub4(final ha_Sub3 ha_Sub3) {
        super(ha_Sub3);
        this.aBoolean3741 = false;
        try {
            if (ha_Sub3.aBoolean4569) {
                this.aBoolean3739 = (~ha_Sub3.anInt4608 > -4);
                final int n = this.aBoolean3739 ? 48 : 127;
                final int[][] array = new int[6][4096];
                final int[][] array2 = new int[6][4096];
                final int[][] array3 = new int[6][4096];
                int n2 = 0;
                for (int n3 = 0; ~n3 > -65; ++n3) {
                    for (int n4 = 0; ~n4 > -65; ++n4) {
                        final float n5 = -1.0f + 2.0f * n4 / 64.0f;
                        final float n6 = 2.0f * n3 / 64.0f - 1.0f;
                        final float n7 = (float)(1.0 / Math.sqrt(1.0f + n5 * n5 + n6 * n6));
                        final float n8 = n5 * n7;
                        final float n9 = n6 * n7;
                        for (int n10 = 0; ~n10 > -7; ++n10) {
                            float n11;
                            if (~n10 == -1) {
                                n11 = -n8;
                            }
                            else if (n10 == 1) {
                                n11 = n8;
                            }
                            else if (~n10 == 0xFFFFFFFD) {
                                n11 = n9;
                            }
                            else if (~n10 == 0xFFFFFFFC) {
                                n11 = -n9;
                            }
                            else if (n10 == 4) {
                                n11 = n7;
                            }
                            else {
                                n11 = -n7;
                            }
                            int n12;
                            int n13;
                            int n14;
                            if (n11 > 0.0f) {
                                n12 = (int)(Math.pow(n11, 96.0) * n);
                                n13 = (int)(Math.pow(n11, 36.0) * n);
                                n14 = (int)(Math.pow(n11, 12.0) * n);
                            }
                            else {
                                n13 = (n12 = (n14 = 0));
                            }
                            array2[n10][n2] = n12 << -1449782824;
                            array3[n10][n2] = n13 << 1473485688;
                            array[n10][n2] = n14 << -11137256;
                        }
                        ++n2;
                    }
                }
                (this.anInterface4_Impl3Array3740 = new Interface4_Impl3[3])[0] = super.aHa_Sub3_585.method1934(8, false, array2, 64);
                this.anInterface4_Impl3Array3740[1] = super.aHa_Sub3_585.method1934(8, false, array3, 64);
                this.anInterface4_Impl3Array3740[2] = super.aHa_Sub3_585.method1934(8, false, array, 64);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iea.<init>(" + ((ha_Sub3 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final int method755(final boolean b, final String s) {
        try {
            int n = 0;
            if (b) {
                return -79;
            }
            while (~Class366.aStringArray3113.length < ~n) {
                if (Class366.aStringArray3113[n].equalsIgnoreCase(s)) {
                    return n;
                }
                ++n;
            }
            return -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iea.A(" + b + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method748(final int n, final boolean b) {
        try {
            if (n != 69) {
                this.aBoolean3741 = true;
            }
            if (this.anInterface4_Impl3Array3740 == null || !b) {
                super.aHa_Sub3_585.method1953(-74, Class98_Sub43_Sub3.aClass65_5926, 0);
            }
            else {
                super.aHa_Sub3_585.method1951((byte)120, 1);
                super.aHa_Sub3_585.method1964(Class357.aClass38_3026, (byte)26);
                super.aHa_Sub3_585.method1957((byte)(-92)).method2107(1024);
                super.aHa_Sub3_585.method2008(Class144.aClass258_1168, (byte)125);
                Label_0267: {
                    if (!this.aBoolean3739) {
                        super.aHa_Sub3_585.method2019(Class249.aClass128_1903, Class288.aClass128_3381, 22831);
                        super.aHa_Sub3_585.method2051(0, -60, IncomingOpcode.aClass65_459);
                        super.aHa_Sub3_585.method1951((byte)120, 2);
                        super.aHa_Sub3_585.method2019(Class288.aClass128_3381, Class1.aClass128_64, 22831);
                        super.aHa_Sub3_585.method2051(0, n - 183, IncomingOpcode.aClass65_459);
                        super.aHa_Sub3_585.method2026(1, true, (byte)27, IncomingOpcode.aClass65_459, false);
                        super.aHa_Sub3_585.method1953(n - 177, Class98_Sub43_Sub3.aClass65_5926, 0);
                        super.aHa_Sub3_585.method2005(super.aHa_Sub3_585.anInterface4_4586, -114);
                        if (!client.aBoolean3553) {
                            break Label_0267;
                        }
                    }
                    super.aHa_Sub3_585.method2019(Class288.aClass128_3381, Class1.aClass128_64, 22831);
                    super.aHa_Sub3_585.method2026(0, true, (byte)27, Class300.aClass65_2499, false);
                    super.aHa_Sub3_585.method1953(-123, Class98_Sub43_Sub3.aClass65_5926, 0);
                }
                super.aHa_Sub3_585.method1951((byte)120, 0);
                this.aBoolean3741 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iea.B(" + n + ',' + b + ')');
        }
    }
    
    @Override
    final void method743(final int n, final boolean b) {
        try {
            if (n <= 93) {
                method755(false, null);
            }
            super.aHa_Sub3_585.method2019(Class288.aClass128_3381, Class249.aClass128_1903, 22831);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iea.D(" + n + ',' + b + ')');
        }
    }
    
    @Override
    final void method742(final int n, final int n2, final Interface4 interface4) {
        try {
            super.aHa_Sub3_585.method2005(interface4, 89);
            if (n != 6) {
                method753(49);
            }
            super.aHa_Sub3_585.method2015(n2, (byte)36);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iea.I(" + n + ',' + n2 + ',' + ((interface4 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method739(final int n) {
        try {
            if (n != -2) {
                this.aBoolean3741 = true;
            }
            if (this.aBoolean3741) {
                super.aHa_Sub3_585.method1951((byte)120, 1);
                super.aHa_Sub3_585.method2005(null, n ^ 0x7C);
                super.aHa_Sub3_585.method1964(r_Sub2.aClass38_6334, (byte)26);
                super.aHa_Sub3_585.method1985(2);
                if (!this.aBoolean3739) {
                    super.aHa_Sub3_585.method2019(Class249.aClass128_1903, Class249.aClass128_1903, 22831);
                    super.aHa_Sub3_585.method2051(0, -55, Class300.aClass65_2499);
                    super.aHa_Sub3_585.method1951((byte)120, 2);
                    super.aHa_Sub3_585.method2019(Class249.aClass128_1903, Class249.aClass128_1903, 22831);
                    super.aHa_Sub3_585.method2051(0, -110, Class300.aClass65_2499);
                    super.aHa_Sub3_585.method2051(1, -56, IncomingOpcode.aClass65_459);
                    super.aHa_Sub3_585.method1953(n ^ 0x56, Class300.aClass65_2499, 0);
                    super.aHa_Sub3_585.method2005(null, -120);
                }
                else {
                    super.aHa_Sub3_585.method2019(Class249.aClass128_1903, Class249.aClass128_1903, 22831);
                    super.aHa_Sub3_585.method2051(0, -114, Class300.aClass65_2499);
                    super.aHa_Sub3_585.method1953(-120, Class300.aClass65_2499, 0);
                }
                super.aHa_Sub3_585.method1951((byte)120, 0);
                this.aBoolean3741 = false;
            }
            else {
                super.aHa_Sub3_585.method1953(n - 83, Class300.aClass65_2499, 0);
            }
            super.aHa_Sub3_585.method2019(Class249.aClass128_1903, Class249.aClass128_1903, n + 22833);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iea.C(" + n + ')');
        }
    }
    
    @Override
    final void method746(final int n, final int n2, final int n3) {
        try {
            if (n3 >= -75) {
                this.aBoolean3741 = false;
            }
            if (this.aBoolean3741) {
                super.aHa_Sub3_585.method1951((byte)120, 1);
                super.aHa_Sub3_585.method2005(this.anInterface4_Impl3Array3740[n - 1], -125);
                super.aHa_Sub3_585.method1951((byte)120, 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iea.E(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final boolean method745(final byte b) {
        try {
            if (b != 27) {
                this.method746(56, 65, -40);
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iea.H(" + b + ')');
        }
    }
    
    static final void method756(final int n, final String s) {
        try {
            if (Class374.aClass147Array3157 != null) {
                if (n >= -60) {
                    Class76_Sub4.aClass377_3738 = null;
                }
                final Class98_Sub11 method3023 = Class246_Sub3_Sub4.method3023(260, Class59.aClass171_468, Class331.aClass117_2811);
                method3023.aClass98_Sub22_Sub1_3865.method1194(r_Sub2.method1650(s, (byte)98), -118);
                method3023.aClass98_Sub22_Sub1_3865.method1188(s, (byte)113);
                Class98_Sub10_Sub29.sendPacket(false, method3023);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iea.G(" + n + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class76_Sub4.aClass377_3738 = new Class377(16);
    }
}
