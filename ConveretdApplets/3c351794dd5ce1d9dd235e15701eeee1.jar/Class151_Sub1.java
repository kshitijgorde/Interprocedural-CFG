import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class151_Sub1 extends Class151
{
    private Class51 aClass51_4964;
    private Class91 aClass91_4965;
    private Class42_Sub3 aClass42_Sub3_4966;
    static String[] aStringArray4967;
    static OutgoingOpcode aClass171_4968;
    static int[] anIntArray4969;
    static long[] aLongArray4970;
    
    static final void method2446(final byte b, final int n, final int n2, final int n3, final int n4) {
        try {
            int i = 0;
            int n5 = n3;
            int n6 = -n3;
            int n7 = -1;
            Class333.method3761(n2, Class97.anIntArrayArray814[n4], -n3 + n, n + n3, (byte)(-126));
            while (i < n5) {
                n7 += 2;
                n6 += n7;
                ++i;
                if (n6 >= 0) {
                    --n5;
                    n6 -= n5 << -1422120383;
                    final int[] array = Class97.anIntArrayArray814[n4 - -n5];
                    final int[] array2 = Class97.anIntArrayArray814[n4 - n5];
                    final int n8 = i + n;
                    final int n9 = -i + n;
                    Class333.method3761(n2, array, n9, n8, (byte)(-128));
                    Class333.method3761(n2, array2, n9, n8, (byte)(-1));
                }
                final int n10 = n5 + n;
                final int n11 = -n5 + n;
                final int[] array3 = Class97.anIntArrayArray814[n4 + i];
                final int[] array4 = Class97.anIntArrayArray814[n4 + -i];
                Class333.method3761(n2, array3, n11, n10, (byte)46);
                Class333.method3761(n2, array4, n11, n10, (byte)(-125));
            }
            if (b > -96) {
                method2450((byte)(-76));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "al.I(" + b + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    final void method2440(final boolean b, final boolean b2) {
        try {
            if (~super.aHa_Sub1_1215.anInt4441 < -1) {
                final float n = -0.5f / super.aHa_Sub1_1215.anInt4441;
                super.aHa_Sub1_1215.method1845(1, 847872872);
                Class34.aFloatArray326[1] = 0.0f;
                Class34.aFloatArray326[2] = n;
                Class34.aFloatArray326[3] = 0.25f + super.aHa_Sub1_1215.aFloat4356 * n;
                Class34.aFloatArray326[0] = 0.0f;
                OpenGL.glPushMatrix();
                OpenGL.glLoadIdentity();
                OpenGL.glTexGenfv(8192, 9474, Class34.aFloatArray326, 0);
                OpenGL.glPopMatrix();
                super.aHa_Sub1_1215.method1848(0.5f, true, super.aHa_Sub1_1215.anInt4441);
                super.aHa_Sub1_1215.method1863(1, this.aClass42_Sub3_4966);
                super.aHa_Sub1_1215.method1845(0, 847872872);
            }
            this.aClass91_4965.method888('\0', b);
            OpenGL.glMatrixMode(5890);
            OpenGL.glPushMatrix();
            OpenGL.glScalef(0.25f, 0.25f, 1.0f);
            OpenGL.glMatrixMode(5888);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "al.D(" + b + ',' + b2 + ')');
        }
    }
    
    public static void method2447(final int n) {
        try {
            Class151_Sub1.aLongArray4970 = null;
            if (n == 32132) {
                Class151_Sub1.anIntArray4969 = null;
                Class151_Sub1.aClass171_4968 = null;
                Class151_Sub1.aStringArray4967 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "al.J(" + n + ')');
        }
    }
    
    Class151_Sub1(final ha_Sub1 ha_Sub1, final Class51 aClass51_4964) {
        super(ha_Sub1);
        try {
            this.aClass51_4964 = aClass51_4964;
            this.method2449((byte)(-23));
            (this.aClass42_Sub3_4966 = new Class42_Sub3(super.aHa_Sub1_1215, 6406, 2, new byte[] { 0, -1 }, 6406)).method393(3552, false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "al.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + ((aClass51_4964 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static long method2448(final long n, final long n2) {
        try {
            return n | n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "al.H(" + n + ',' + n2 + ')');
        }
    }
    
    private final void method2449(final byte b) {
        try {
            (this.aClass91_4965 = new Class91(super.aHa_Sub1_1215, 2)).method887(0, b - 30366);
            super.aHa_Sub1_1215.method1845(1, 847872872);
            super.aHa_Sub1_1215.method1899(260, b + 8983, 7681);
            super.aHa_Sub1_1215.method1840(0, 768, -116, 34168);
            OpenGL.glTexGeni(8192, 9472, 9216);
            OpenGL.glEnable(3168);
            super.aHa_Sub1_1215.method1845(0, b ^ 0xCD767C81);
            OpenGL.glTexEnvf(8960, 34163, 2.0f);
            if (this.aClass51_4964.aBoolean424) {
                OpenGL.glTexGeni(8194, 9472, 9217);
                OpenGL.glTexGeni(8195, 9472, 9217);
                OpenGL.glTexGenfv(8195, 9473, new float[] { 0.0f, 0.0f, 0.0f, 1.0f }, 0);
                OpenGL.glEnable(3170);
                OpenGL.glEnable(3171);
            }
            this.aClass91_4965.method886((byte)(-108));
            this.aClass91_4965.method887(1, -30389);
            super.aHa_Sub1_1215.method1845(1, b + 847872895);
            super.aHa_Sub1_1215.method1899(8448, 8960, 8448);
            super.aHa_Sub1_1215.method1840(0, 768, 87, 5890);
            OpenGL.glDisable(3168);
            super.aHa_Sub1_1215.method1845(0, 847872872);
            if (b != -23) {
                this.aClass51_4964 = null;
            }
            OpenGL.glTexEnvf(8960, 34163, 1.0f);
            if (this.aClass51_4964.aBoolean424) {
                OpenGL.glDisable(3170);
                OpenGL.glDisable(3171);
            }
            this.aClass91_4965.method886((byte)54);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "al.K(" + b + ')');
        }
    }
    
    @Override
    final void method2441(final int n, final int n2, final int n3) {
        try {
            if (n3 > -2) {
                Class151_Sub1.anIntArray4969 = null;
            }
            if (~(0x1 & n) == 0xFFFFFFFE) {
                if (this.aClass51_4964.aBoolean424) {
                    super.aHa_Sub1_1215.method1863(1, this.aClass51_4964.aClass42_Sub4_422);
                    Class34.aFloatArray326[3] = super.aHa_Sub1_1215.anInt4321 % 4000 / 4000.0f;
                    Class34.aFloatArray326[0] = 0.0f;
                    Class34.aFloatArray326[1] = 0.0f;
                    Class34.aFloatArray326[2] = 0.0f;
                    OpenGL.glTexGenfv(8194, 9473, Class34.aFloatArray326, 0);
                }
                else {
                    super.aHa_Sub1_1215.method1863(1, this.aClass51_4964.aClass42_Sub1Array423[16 * (super.aHa_Sub1_1215.anInt4321 % 4000) / 4000]);
                }
            }
            else if (this.aClass51_4964.aBoolean424) {
                super.aHa_Sub1_1215.method1863(1, this.aClass51_4964.aClass42_Sub4_422);
                Class34.aFloatArray326[2] = 0.0f;
                Class34.aFloatArray326[3] = 0.0f;
                Class34.aFloatArray326[0] = 0.0f;
                Class34.aFloatArray326[1] = 0.0f;
                OpenGL.glTexGenfv(8194, 9473, Class34.aFloatArray326, 0);
            }
            else {
                super.aHa_Sub1_1215.method1863(1, this.aClass51_4964.aClass42_Sub1Array423[0]);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "al.G(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final boolean method2439(final int n) {
        try {
            if (n != 31565) {
                Class151_Sub1.anIntArray4969 = null;
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "al.A(" + n + ')');
        }
    }
    
    @Override
    final void method2443(final boolean b, final int n) {
        try {
            if (n != 255) {
                this.method2439(-35);
            }
            super.aHa_Sub1_1215.method1899(8448, n ^ 0x23FF, 260);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "al.C(" + b + ',' + n + ')');
        }
    }
    
    static final void method2450(final byte b) {
        try {
            Label_0049: {
                if (~Class98_Sub46_Sub19.anInt6065 >= -2) {
                    Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 2, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub21_4037);
                    if (!client.aBoolean3553) {
                        break Label_0049;
                    }
                }
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 4, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub21_4037);
            }
            if (b <= 45) {
                method2450((byte)(-21));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "al.B(" + b + ')');
        }
    }
    
    @Override
    final void method2445(final byte b) {
        try {
            this.aClass91_4965.method888('\u0001', false);
            if (super.aHa_Sub1_1215.anInt4441 > 0) {
                super.aHa_Sub1_1215.method1845(1, 847872872);
                super.aHa_Sub1_1215.method1863(1, null);
                super.aHa_Sub1_1215.method1848(1.0f, true, 0.0f);
                super.aHa_Sub1_1215.method1845(0, 847872872);
            }
            super.aHa_Sub1_1215.method1899(8448, 8960, 8448);
            OpenGL.glMatrixMode(5890);
            OpenGL.glPopMatrix();
            OpenGL.glMatrixMode(5888);
            if (b <= 25) {
                Class151_Sub1.aLongArray4970 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "al.E(" + b + ')');
        }
    }
    
    @Override
    final void method2442(final Class42 class42, final boolean b, final int n) {
        try {
            if (b) {
                this.aClass42_Sub3_4966 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "al.F(" + ((class42 != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    static {
        Class151_Sub1.aClass171_4968 = new OutgoingOpcode(4, 6);
        Class151_Sub1.anIntArray4969 = new int[500];
    }
}
