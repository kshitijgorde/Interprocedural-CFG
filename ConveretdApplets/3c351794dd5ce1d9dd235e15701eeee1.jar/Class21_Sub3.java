import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class21_Sub3 extends Class21 implements Interface4_Impl2
{
    static int anInt5389;
    static int anInt5390;
    private int anInt5391;
    private int anInt5392;
    
    static final void method275(final boolean b, int anInt5570, final Class28 aClass28_50) {
        try {
            if (Class222.aBoolean1667) {
                Class222.aBoolean1667 = false;
                anInt5570 = 0;
            }
            Label_0132: {
                if (aa.aClass28_50 == null || !aa.aClass28_50.method299(true, aClass28_50)) {
                    aa.aClass28_50 = aClass28_50;
                    Class98_Sub46_Sub12.aLong6035 = Class343.method3819(-47);
                    Class287.anInt2196 = (Class98_Sub10_Sub6.anInt5570 = anInt5570);
                    if (~Class287.anInt2196 == -1) {
                        RuntimeException_Sub1.method4011(-38);
                        if (!client.aBoolean3553) {
                            break Label_0132;
                        }
                    }
                    Class140.aClass48_3245 = Class98_Sub46_Sub4.aClass48_5962;
                    Class162.anInt1271 = Class284_Sub1_Sub2.anInt6192;
                    Class135.aFloat1053 = Class159.aFloat1254;
                    Class346.aFloat2900 = Class260.aFloat3260;
                    Class98_Sub28_Sub1.anInt5811 = Class98_Sub46_Sub6.anInt5979;
                    Class98_Sub12.anInt3872 = Class263.anInt1965;
                    Class234.aFloat1749 = Class141.aFloat1150;
                    IOException_Sub1.aFloat31 = Class46.aFloat388;
                    Class157.aFloat1249 = Class97.aFloat831;
                    Class3.aFloat78 = Class215.aFloat1613;
                }
            }
            if (b) {
                Class21_Sub3.anInt5389 = 4;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vca.A(" + b + ',' + anInt5570 + ',' + ((aClass28_50 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final float method45(final int n, final float n2) {
        try {
            if (n != -8473) {
                this.method48(-66);
            }
            return n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vca.J(" + n + ',' + n2 + ')');
        }
    }
    
    static final boolean method276(final int n, final int n2, final int n3) {
        try {
            if (n2 != 15123) {
                method275(true, 53, null);
            }
            return Class98_Sub10_Sub1.method1005(n, n3, (byte)(-23)) & ((0x2000 & n) != 0x0 | Class373_Sub3.method3978(n3, n, (byte)88) | Class21_Sub2.method271((byte)(-104), n, n3));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vca.C(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    Class21_Sub3(final ha_Sub3_Sub2 ha_Sub3_Sub2, final Class164 class164, final int anInt5391, final int anInt5392, final byte[] array, final int n, final int n2) {
        super(ha_Sub3_Sub2, 34037, class164, Class162.aClass162_1266, anInt5392 * anInt5391, false);
        try {
            this.anInt5391 = anInt5391;
            this.anInt5392 = anInt5392;
            super.aHa_Sub3_Sub2_3233.method2005(this, -123);
            OpenGL.glPixelStorei(3317, 1);
            OpenGL.glPixelStorei(3314, n2);
            OpenGL.glTexImage2Dub(super.anInt3235, 0, this.method260(0), anInt5391, anInt5392, 0, Class196.method2665(false, super.aClass164_3237), 5121, array, n);
            OpenGL.glPixelStorei(3314, 0);
            OpenGL.glPixelStorei(3317, 4);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vca.<init>(" + ((ha_Sub3_Sub2 != null) ? "{...}" : "null") + ',' + ((class164 != null) ? "{...}" : "null") + ',' + anInt5391 + ',' + anInt5392 + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    @Override
    public final int method47(final int n) {
        try {
            if (n != 12941) {
                return 31;
            }
            return this.anInt5391;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vca.G(" + n + ')');
        }
    }
    
    @Override
    public final void method41(int n, final int n2, final int n3, final int n4, final int n5, final int n6, final byte[] array, final Class164 class164, final int n7) {
        try {
            if (n == 0) {
                n = n3;
            }
            super.aHa_Sub3_Sub2_3233.method2005(this, -123);
            if (n6 != -26946) {
                this.method45(-63, 1.4202741f);
            }
            OpenGL.glPixelStorei(3317, 1);
            if (n3 != n) {
                OpenGL.glPixelStorei(3314, n);
            }
            OpenGL.glTexSubImage2Dub(super.anInt3235, 0, n7, n4, n3, n5, Class196.method2665(false, class164), 5121, array, n2);
            if (n != n3) {
                OpenGL.glPixelStorei(3314, 0);
            }
            OpenGL.glPixelStorei(3317, 4);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vca.M(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + ((array != null) ? "{...}" : "null") + ',' + ((class164 != null) ? "{...}" : "null") + ',' + n7 + ')');
        }
    }
    
    @Override
    public final float method42(final byte b, final float n) {
        try {
            return n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vca.O(" + b + ',' + n + ')');
        }
    }
    
    @Override
    public final int method43(final int n) {
        try {
            if (n < 109) {
                this.method49(15, -62, -104, null, -50, -111, -85, 115);
            }
            return this.anInt5392;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vca.L(" + n + ')');
        }
    }
    
    @Override
    public final void method46(final boolean b, final boolean b2, final int n) {
    }
    
    Class21_Sub3(final ha_Sub3_Sub2 ha_Sub3_Sub2, final int anInt5391, final int anInt5392, final int[] array, final int n, final int n2) {
        super(ha_Sub3_Sub2, 34037, Class62.aClass164_486, Class162.aClass162_1266, anInt5391 * anInt5392, false);
        try {
            this.anInt5392 = anInt5392;
            this.anInt5391 = anInt5391;
            super.aHa_Sub3_Sub2_3233.method2005(this, -121);
            OpenGL.glPixelStorei(3314, n2);
            OpenGL.glTexImage2Di(super.anInt3235, 0, 6408, this.anInt5391, this.anInt5392, 0, 32993, super.aHa_Sub3_Sub2_3233.anInt6135, array, 4 * n);
            OpenGL.glPixelStorei(3314, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vca.<init>(" + ((ha_Sub3_Sub2 != null) ? "{...}" : "null") + ',' + anInt5391 + ',' + anInt5392 + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    Class21_Sub3(final ha_Sub3_Sub2 ha_Sub3_Sub2, final Class164 class164, final int anInt5391, final int anInt5392, final float[] array, final int n, final int n2) {
        super(ha_Sub3_Sub2, 34037, class164, Class162.aClass162_1270, anInt5392 * anInt5391, false);
        try {
            this.anInt5392 = anInt5392;
            this.anInt5391 = anInt5391;
            super.aHa_Sub3_Sub2_3233.method2005(this, 43);
            OpenGL.glPixelStorei(3314, n2);
            OpenGL.glTexImage2Df(super.anInt3235, 0, this.method260(0), anInt5391, anInt5392, 0, Class196.method2665(false, super.aClass164_3237), 5126, array, 4 * n);
            OpenGL.glPixelStorei(3314, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vca.<init>(" + ((ha_Sub3_Sub2 != null) ? "{...}" : "null") + ',' + ((class164 != null) ? "{...}" : "null") + ',' + anInt5391 + ',' + anInt5392 + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    @Override
    public final void method44(final int n, final int n2, final byte b, final int n3, final int n4, final int[] array, final int n5) {
        try {
            final int[] array2 = new int[this.anInt5391 * this.anInt5392];
            super.aHa_Sub3_Sub2_3233.method2005(this, 88);
            OpenGL.glGetTexImagei(super.anInt3235, 0, 32993, 5121, array2, 0);
            for (int i = 0; i < n3; ++i) {
                Class236.method2891(array2, (-i - 1 - (-n3 - n5)) * this.anInt5391, array, n2 - -(n * i), n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vca.H(" + n + ',' + n2 + ',' + b + ',' + n3 + ',' + n4 + ',' + ((array != null) ? "{...}" : "null") + ',' + n5 + ')');
        }
    }
    
    @Override
    public final boolean method48(final int n) {
        try {
            return n > -22 && false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vca.N(" + n + ')');
        }
    }
    
    @Override
    public final void method49(final int n, final int n2, final int n3, final int[] array, final int n4, final int n5, int n6, final int n7) {
        try {
            if (n != 17779) {
                this.method49(-84, 50, 115, null, 124, 55, -64, 94);
            }
            super.aHa_Sub3_Sub2_3233.method2005(this, n - 17894);
            if (n6 == 0) {
                n6 = n7;
            }
            if (~n6 != ~n7) {
                OpenGL.glPixelStorei(3314, n6);
            }
            OpenGL.glTexSubImage2Di(super.anInt3235, 0, n4, n2, n7, n3, 32993, super.aHa_Sub3_Sub2_3233.anInt6135, array, n5);
            if (~n7 != ~n6) {
                OpenGL.glPixelStorei(3314, 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vca.F(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    Class21_Sub3(final ha_Sub3_Sub2 ha_Sub3_Sub2, final Class164 class164, final Class162 class165, final int anInt5391, final int anInt5392) {
        super(ha_Sub3_Sub2, 34037, class164, class165, anInt5392 * anInt5391, false);
        try {
            this.anInt5392 = anInt5392;
            this.anInt5391 = anInt5391;
            super.aHa_Sub3_Sub2_3233.method2005(this, -119);
            OpenGL.glTexImage2Dub(super.anInt3235, 0, this.method260(0), anInt5391, anInt5392, 0, Class196.method2665(false, super.aClass164_3237), Class98_Sub5_Sub1.method964(super.aClass162_3234, (byte)105), null, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vca.<init>(" + ((ha_Sub3_Sub2 != null) ? "{...}" : "null") + ',' + ((class164 != null) ? "{...}" : "null") + ',' + ((class165 != null) ? "{...}" : "null") + ',' + anInt5391 + ',' + anInt5392 + ')');
        }
    }
    
    static {
        Class21_Sub3.anInt5390 = -1;
    }
}
