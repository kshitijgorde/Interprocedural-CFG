import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

class Class42_Sub1 extends Class42
{
    private int anInt5351;
    int anInt5352;
    private int anInt5353;
    static Class197 aClass197_5354;
    int anInt5355;
    static int anInt5356;
    
    Class42_Sub1(final ha_Sub1 ha_Sub1, final int n, final int n2, final int anInt5355, final int anInt5356) {
        super(ha_Sub1, n, n2, anInt5356 * anInt5355, false);
        this.anInt5351 = -1;
        this.anInt5353 = -1;
        try {
            this.anInt5352 = anInt5356;
            this.anInt5355 = anInt5355;
            super.aHa_Sub1_3227.method1863(1, this);
            OpenGL.glTexImage2Dub(super.anInt3226, 0, super.anInt3230, anInt5355, anInt5356, 0, Class98_Sub31_Sub2.method1339(super.anInt3230, 126), 5121, null, 0);
            this.method372(-28003, true);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aaa.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + anInt5355 + ',' + anInt5356 + ')');
        }
    }
    
    Class42_Sub1(final ha_Sub1 ha_Sub1, final int n, final int n2, final int anInt5355, final int anInt5356, final boolean b, byte[] array, final int n3, final boolean b2) {
        super(ha_Sub1, n, n2, anInt5355 * anInt5356, b);
        this.anInt5351 = -1;
        this.anInt5353 = -1;
        try {
            this.anInt5355 = anInt5355;
            this.anInt5352 = anInt5356;
            if (b2) {
                final byte[] array2 = new byte[array.length];
                for (int n4 = 0; ~anInt5356 < ~n4; ++n4) {
                    int n5 = anInt5355 * n4;
                    int n6 = (-1 + -n4 + anInt5356) * anInt5355;
                    for (int n7 = 0; ~anInt5355 < ~n7; ++n7) {
                        array2[n5++] = array[n6++];
                    }
                }
                array = array2;
            }
            super.aHa_Sub1_3227.method1863(1, this);
            OpenGL.glPixelStorei(3317, 1);
            if (!b || ~super.anInt3226 == 0xFFFF7B0A) {
                OpenGL.glTexImage2Dub(super.anInt3226, 0, super.anInt3230, this.anInt5355, this.anInt5352, 0, n3, 5121, array, 0);
                this.method373(true, false);
            }
            else {
                Class98_Sub46_Sub16.method1613(anInt5355, n, array, -1, anInt5356, n2, n3);
                this.method373(true, true);
            }
            OpenGL.glPixelStorei(3317, 4);
            this.method372(-28003, true);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aaa.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + anInt5355 + ',' + anInt5356 + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n3 + ',' + b2 + ')');
        }
    }
    
    final void method378(final int n, final int n2, final boolean b, int n3, byte[] array, final int n4, final byte b2, final int n5, final int n6, final int n7) {
        try {
            if (~n3 == -1) {
                n3 = n;
            }
            if (b) {
                final int method3014 = Class246_Sub3_Sub3.method3014(1, n2);
                final int n8 = method3014 * n;
                final int n9 = n3 * method3014;
                final byte[] array2 = new byte[n8 * n7];
                for (int n10 = 0; ~n7 < ~n10; ++n10) {
                    int n11 = n8 * n10;
                    int n12 = n9 * (-n10 + n7 - 1) - -n6;
                    for (int n13 = 0; ~n8 < ~n13; ++n13) {
                        array2[n11++] = array[n12++];
                    }
                }
                array = array2;
            }
            super.aHa_Sub1_3227.method1863(1, this);
            OpenGL.glPixelStorei(3317, 1);
            if (~n != ~n3) {
                OpenGL.glPixelStorei(3314, n3);
            }
            OpenGL.glTexSubImage2Dub(super.anInt3226, 0, n5, n4, n, n7, n2, 5121, array, n6);
            if (~n3 != ~n) {
                OpenGL.glPixelStorei(3314, 0);
            }
            OpenGL.glPixelStorei(3317, 4);
            if (b2 != -80) {
                this.method383(false, -121, false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aaa.C(" + n + ',' + n2 + ',' + b + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ',' + n4 + ',' + b2 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    Class42_Sub1(final ha_Sub1 ha_Sub1, final int n, final int n2, final int n3, final int anInt5355, final int anInt5356) {
        super(ha_Sub1, n, 6407, anInt5355 * anInt5356, false);
        this.anInt5351 = -1;
        this.anInt5353 = -1;
        try {
            this.anInt5352 = anInt5356;
            this.anInt5355 = anInt5355;
            final int n4 = super.aHa_Sub1_3227.anInt4304 - anInt5356 - n3;
            super.aHa_Sub1_3227.method1863(1, this);
            OpenGL.glCopyTexImage2D(super.anInt3226, 0, super.anInt3230, n2, n4, anInt5355, anInt5356, 0);
            this.method372(-28003, true);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aaa.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + anInt5355 + ',' + anInt5356 + ')');
        }
    }
    
    final void method379(final int n, int[] array, final int n2, final int n3, final boolean b, final int n4, int n5, final int n6, final int n7) {
        try {
            if (n3 == 3656) {
                if (~n5 == -1) {
                    n5 = n6;
                }
                if (b) {
                    final int[] array2 = new int[n * n6];
                    for (int n8 = 0; ~n < ~n8; ++n8) {
                        int n9 = n6 * n8;
                        int n10 = (n + (-n8 - 1)) * n5 + n2;
                        for (int n11 = 0; ~n11 > ~n6; ++n11) {
                            array2[n9++] = array[n10++];
                        }
                    }
                    array = array2;
                }
                super.aHa_Sub1_3227.method1863(1, this);
                if (~n6 != ~n5) {
                    OpenGL.glPixelStorei(3314, n5);
                }
                OpenGL.glTexSubImage2Di(super.anInt3226, 0, n7, this.anInt5352 + -n4 + -n, n6, n, 32993, super.aHa_Sub1_3227.anInt4425, array, n2);
                if (~n5 != ~n6) {
                    OpenGL.glPixelStorei(3314, 0);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aaa.F(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + b + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    final void method380(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            final int n8 = super.aHa_Sub1_3227.anInt4304 - (n4 - -n7);
            super.aHa_Sub1_3227.method1863(1, this);
            OpenGL.glCopyTexSubImage2D(super.anInt3226, 0, n2, this.anInt5352 - (n3 + n7), n5, n8, n, n7);
            if (n6 <= 79) {
                this.method383(true, -52, false);
            }
            OpenGL.glFlush();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aaa.H(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    static final void method381(final int n, final int n2, final ha ha, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            ha.method1760(n5, n, n2, n4, (byte)(-66), n7);
            ha.method1760(n5 - 2, 16, 1 + n2, n3, (byte)(-66), 1 + n7);
            if (n6 == 8516) {
                ha.method1781(true, n - 19, n5 - 2, n3, 1 + n7, 18 + n2);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aaa.E(" + n + ',' + n2 + ',' + ((ha != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    Class42_Sub1(final ha_Sub1 ha_Sub1, final int n, final int n2, final int anInt5355, final int anInt5356, final boolean b, final float[] array, final int n3) {
        super(ha_Sub1, n, n2, anInt5355 * anInt5356, b);
        this.anInt5351 = -1;
        this.anInt5353 = -1;
        try {
            this.anInt5355 = anInt5355;
            this.anInt5352 = anInt5356;
            super.aHa_Sub1_3227.method1863(1, this);
            Label_0126: {
                if (!b || ~super.anInt3226 == 0xFFFF7B0A) {
                    OpenGL.glTexImage2Df(super.anInt3226, 0, super.anInt3230, this.anInt5355, this.anInt5352, 0, n3, 5126, array, 0);
                    this.method373(true, false);
                    if (!client.aBoolean3553) {
                        break Label_0126;
                    }
                }
                Class2.method168(anInt5355, n, n3, n2, array, anInt5356, -70);
                this.method373(true, true);
            }
            this.method372(-28003, true);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aaa.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + anInt5355 + ',' + anInt5356 + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n3 + ')');
        }
    }
    
    final void method382(final int anInt5353, final boolean b, final int anInt5354, final int n) {
        try {
            OpenGL.glFramebufferTexture2DEXT(anInt5353, anInt5354, super.anInt3226, super.anInt3229, n);
            this.anInt5351 = anInt5354;
            this.anInt5353 = anInt5353;
            if (!b) {
                this.method383(false, 107, false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aaa.A(" + anInt5353 + ',' + b + ',' + anInt5354 + ',' + n + ')');
        }
    }
    
    final void method383(final boolean b, final int n, final boolean b2) {
        try {
            if (n != 10242) {
                this.method380(-75, 26, 70, 110, -90, 106, 103);
            }
            if (~super.anInt3226 == 0xFFFFF21E) {
                super.aHa_Sub1_3227.method1863(1, this);
                OpenGL.glTexParameteri(super.anInt3226, 10242, b2 ? 10497 : 33071);
                OpenGL.glTexParameteri(super.anInt3226, 10243, b ? 10497 : 33071);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aaa.I(" + b + ',' + n + ',' + b2 + ')');
        }
    }
    
    public static void method384(final int n) {
        try {
            if (n >= -33) {
                Class42_Sub1.anInt5356 = 68;
            }
            Class42_Sub1.aClass197_5354 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aaa.D(" + n + ')');
        }
    }
    
    Class42_Sub1(final ha_Sub1 ha_Sub1, final int n, final int n2, final int i, final int anInt5352, final boolean b, int[] array, final int n3, final int n4, final boolean b2) {
        super(ha_Sub1, n, n2, anInt5352 * i, b);
        this.anInt5351 = -1;
        this.anInt5353 = -1;
        try {
            this.anInt5352 = anInt5352;
            this.anInt5355 = i;
            if (b2) {
                final int[] array2 = new int[array.length];
                for (int n5 = 0; ~anInt5352 < ~n5; ++n5) {
                    int n6 = i * n5;
                    int n7 = (-1 + (anInt5352 + -n5)) * i;
                    for (int n8 = 0; i > n8; ++n8) {
                        array2[n6++] = array[n7++];
                    }
                }
                array = array2;
            }
            super.aHa_Sub1_3227.method1863(1, this);
            if (super.anInt3226 == 34037 || !b || n3 != 0 || n4 != 0) {
                OpenGL.glPixelStorei(3314, n3);
                OpenGL.glTexImage2Di(super.anInt3226, 0, super.anInt3230, this.anInt5355, this.anInt5352, 0, 32993, super.aHa_Sub1_3227.anInt4425, array, 4 * n4);
                OpenGL.glPixelStorei(3314, 0);
                this.method373(true, false);
            }
            else {
                Class336.method3773(super.anInt3230, -121, super.aHa_Sub1_3227.anInt4425, super.anInt3226, this.anInt5355, 32993, this.anInt5352, array);
                this.method373(true, true);
            }
            this.method372(-28003, true);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aaa.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + i + ',' + anInt5352 + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + b2 + ')');
        }
    }
    
    @Override
    public final void method3(final byte b) {
        try {
            OpenGL.glFramebufferTexture2DEXT(this.anInt5353, this.anInt5351, super.anInt3226, 0, 0);
            this.anInt5353 = -1;
            this.anInt5351 = -1;
            if (b >= -117) {
                this.anInt5351 = 4;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aaa.B(" + b + ')');
        }
    }
    
    static final void method385(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        try {
            Class168.method2533(n9, n3, n8, 0, (byte)82, n7, n, n2, n5, n6);
            if (n4 != -1) {
                method385(-52, 78, -128, 103, 42, -10, -6, -22, -116);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aaa.G(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ')');
        }
    }
}
