import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class76_Sub2 extends Class76
{
    static int anInt3728;
    private Class273 aClass273_3729;
    private ha_Sub3_Sub2 aHa_Sub3_Sub2_3730;
    static IncomingOpcode aClass58_3731;
    private Class195 aClass195_3732;
    static Class207 aClass207_3733;
    
    @Override
    final void method743(final int n, final boolean b) {
        try {
            if (n < 93) {
                this.method745((byte)(-62));
            }
            super.aHa_Sub3_585.method2019(Class1.aClass128_64, Class288.aClass128_3381, 22831);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fs.D(" + n + ',' + b + ')');
        }
    }
    
    @Override
    final void method746(final int n, final int n2, final int n3) {
        try {
            Label_0112: {
                if (this.aClass195_3732.aBoolean1501) {
                    final float n4 = super.aHa_Sub3_585.anInt4556 % 4000 / 4000.0f;
                    super.aHa_Sub3_585.method2005(this.aClass195_3732.anInterface4_Impl1_1498, 36);
                    OpenGL.glProgramLocalParameter4fARB(34336, 0, n4, 0.0f, 0.0f, 1.0f);
                    if (!client.aBoolean3553) {
                        break Label_0112;
                    }
                }
                super.aHa_Sub3_585.method2005(this.aClass195_3732.anInterface4_Impl2Array1503[16 * (super.aHa_Sub3_585.anInt4556 % 4000) / 4000], -117);
                OpenGL.glProgramLocalParameter4fARB(34336, 0, 0.0f, 0.0f, 0.0f, 1.0f);
            }
            if (n3 > -75) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fs.E(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method742(final int n, final int n2, final Interface4 interface4) {
        try {
            if (n != 6) {
                this.method748(52, false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fs.I(" + n + ',' + n2 + ',' + ((interface4 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method750(final int n) {
        try {
            Class76_Sub2.aClass58_3731 = null;
            Class76_Sub2.aClass207_3733 = null;
            if (n != 0) {
                method751(-105, null, 58, null, -58, 58, 30, -85, -104);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fs.F(" + n + ')');
        }
    }
    
    static final void method751(final int n, final byte[] array, final int n2, final byte[] array2, int n3, final int n4, int n5, int n6, final int n7) {
        try {
            final int n8 = -(n6 >> -1972136990);
            if (n7 == 0) {
                n6 = -(n6 & 0x3);
                for (int n9 = -n2; ~n9 > -1; ++n9) {
                    for (int i = n8; i < 0; ++i) {
                        final int n10 = n5++;
                        array[n10] += array2[n3++];
                        final int n11 = n5++;
                        array[n11] += array2[n3++];
                        final int n12 = n5++;
                        array[n12] += array2[n3++];
                        final int n13 = n5++;
                        array[n13] += array2[n3++];
                    }
                    for (int n14 = n6; ~n14 > -1; ++n14) {
                        final int n15 = n5++;
                        array[n15] += array2[n3++];
                    }
                    n5 += n4;
                    n3 += n;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fs.G(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    @Override
    final void method748(final int n, final boolean b) {
        try {
            OpenGL.glBindProgramARB(34336, this.aClass273_3729.anInt2040);
            OpenGL.glEnable(34336);
            if (n != 69) {
                Class76_Sub2.aClass207_3733 = null;
            }
            super.aHa_Sub3_585.method2051(0, -128, IncomingOpcode.aClass65_459);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fs.B(" + n + ',' + b + ')');
        }
    }
    
    @Override
    final void method739(final int n) {
        try {
            super.aHa_Sub3_585.method2051(0, -114, Class300.aClass65_2499);
            OpenGL.glBindProgramARB(34336, 0);
            if (n != -2) {
                this.method739(102);
            }
            OpenGL.glDisable(34820);
            OpenGL.glDisable(34336);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fs.C(" + n + ')');
        }
    }
    
    static final boolean requestFlag(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final boolean b, final int n8) {
        try {
            final int n9 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6437[0];
            final int n10 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6438[0];
            if (n9 < 0 || Class165.anInt1276 <= n9 || n10 < 0 || n10 >= Class98_Sub10_Sub7.anInt5572) {
                return false;
            }
            if (n5 > n7 || n7 >= Class165.anInt1276 || n6 < 0 || ~n6 <= ~Class98_Sub10_Sub7.anInt5572) {
                return false;
            }
            final int method96 = Applet_Sub1.method96(Class167.aClass243Array1281[Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088], Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.method3034(0), Class76_Sub5.anIntArray3743, n2, n, n6, n3, n10, Class117.anIntArray974, b, n7, n4, 48, n9, n8);
            if (~method96 > -2) {
                return false;
            }
            Class269.anInt2024 = Class76_Sub5.anIntArray3743[-1 + method96];
            Class246_Sub3_Sub1_Sub2.anInt6251 = Class117.anIntArray974[-1 + method96];
            Class365.aBoolean3110 = false;
            Class98_Sub22.method1216(-17470);
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fs.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + b + ',' + n8 + ')');
        }
    }
    
    Class76_Sub2(final ha_Sub3_Sub2 aHa_Sub3_Sub2_3730, final Class207 class207, final Class195 aClass195_3732) {
        super(aHa_Sub3_Sub2_3730);
        try {
            this.aClass195_3732 = aClass195_3732;
            this.aHa_Sub3_Sub2_3730 = aHa_Sub3_Sub2_3730;
            if (class207 == null || !this.aClass195_3732.method2659(-22382) || !this.aHa_Sub3_Sub2_3730.aBoolean6134) {
                this.aClass273_3729 = null;
            }
            else {
                this.aClass273_3729 = Class240.method2927(class207.method2739("gl", "transparent_water", -32734), this.aHa_Sub3_Sub2_3730, 34336, 25246);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fs.<init>(" + ((aHa_Sub3_Sub2_3730 != null) ? "{...}" : "null") + ',' + ((class207 != null) ? "{...}" : "null") + ',' + ((aClass195_3732 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final boolean method745(final byte b) {
        try {
            if (b != 27) {
                method751(-3, null, 8, null, 91, 11, -110, 7, 116);
            }
            return this.aClass273_3729 != null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fs.H(" + b + ')');
        }
    }
    
    static {
        Class76_Sub2.aClass58_3731 = new IncomingOpcode(104, -2);
    }
}
