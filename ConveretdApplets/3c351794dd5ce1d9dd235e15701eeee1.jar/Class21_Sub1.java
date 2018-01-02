import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class21_Sub1 extends Class21 implements Interface4_Impl2
{
    private int anInt5382;
    static Class269 aClass269_5383;
    private int anInt5384;
    static Interface7[] anInterface7Array5385;
    
    Class21_Sub1(final ha_Sub3_Sub2 ha_Sub3_Sub2, final Class164 class164, final Class162 class165, final int anInt5382, final int anInt5383) {
        super(ha_Sub3_Sub2, 3553, class164, class165, anInt5382 * anInt5383, false);
        try {
            this.anInt5384 = anInt5383;
            this.anInt5382 = anInt5382;
            super.aHa_Sub3_Sub2_3233.method2005(this, -118);
            OpenGL.glTexImage2Dub(super.anInt3235, 0, this.method260(0), anInt5382, anInt5383, 0, Class196.method2665(false, super.aClass164_3237), Class98_Sub5_Sub1.method964(super.aClass162_3234, (byte)105), null, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bf.<init>(" + ((ha_Sub3_Sub2 != null) ? "{...}" : "null") + ',' + ((class164 != null) ? "{...}" : "null") + ',' + ((class165 != null) ? "{...}" : "null") + ',' + anInt5382 + ',' + anInt5383 + ')');
        }
    }
    
    Class21_Sub1(final ha_Sub3_Sub2 ha_Sub3_Sub2, final Class164 class164, final int anInt5382, final int anInt5383, final boolean b, final float[] array, final int n, final int n2) {
        super(ha_Sub3_Sub2, 3553, class164, Class162.aClass162_1270, anInt5382 * anInt5383, b);
        try {
            this.anInt5384 = anInt5383;
            this.anInt5382 = anInt5382;
            super.aHa_Sub3_Sub2_3233.method2005(this, -115);
            if (!b && n2 == 0 && ~n == -1) {
                this.method256(anInt5382, array, false, anInt5383, super.anInt3235);
            }
            else {
                OpenGL.glPixelStorei(3314, n2);
                OpenGL.glTexImage2Df(super.anInt3235, 0, this.method260(0), anInt5382, anInt5383, 0, Class196.method2665(false, super.aClass164_3237), 5126, array, n * 4);
                OpenGL.glPixelStorei(3314, 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bf.<init>(" + ((ha_Sub3_Sub2 != null) ? "{...}" : "null") + ',' + ((class164 != null) ? "{...}" : "null") + ',' + anInt5382 + ',' + anInt5383 + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    Class21_Sub1(final ha_Sub3_Sub2 ha_Sub3_Sub2, final Class164 class164, final int anInt5382, final int anInt5383, final boolean b, final byte[] array, final int n, final int n2) {
        super(ha_Sub3_Sub2, 3553, class164, Class162.aClass162_1266, anInt5382 * anInt5383, b);
        try {
            this.anInt5384 = anInt5383;
            this.anInt5382 = anInt5382;
            super.aHa_Sub3_Sub2_3233.method2005(this, 50);
            OpenGL.glPixelStorei(3317, 1);
            Label_0135: {
                if (!b || n2 != 0 || ~n != -1) {
                    OpenGL.glPixelStorei(3314, n2);
                    OpenGL.glTexImage2Dub(super.anInt3235, 0, this.method260(0), anInt5382, anInt5383, 0, Class196.method2665(false, super.aClass164_3237), 5121, array, n);
                    OpenGL.glPixelStorei(3314, 0);
                    if (!client.aBoolean3553) {
                        break Label_0135;
                    }
                }
                this.method259(array, 54, anInt5383, anInt5382, super.anInt3235);
            }
            OpenGL.glPixelStorei(3317, 4);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bf.<init>(" + ((ha_Sub3_Sub2 != null) ? "{...}" : "null") + ',' + ((class164 != null) ? "{...}" : "null") + ',' + anInt5382 + ',' + anInt5383 + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    @Override
    public final void method46(final boolean b, final boolean b2, final int n) {
        try {
            super.aHa_Sub3_Sub2_3233.method2005(this, -128);
            OpenGL.glTexParameteri(super.anInt3235, 10242, b ? 10497 : 33071);
            OpenGL.glTexParameteri(super.anInt3235, 10243, b2 ? 10497 : 33071);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bf.K(" + b + ',' + b2 + ',' + n + ')');
        }
    }
    
    @Override
    public final int method47(final int n) {
        try {
            if (n != 12941) {
                return 59;
            }
            return this.anInt5382;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bf.G(" + n + ')');
        }
    }
    
    final void method267(final float[] array, final Class164 class164, final int n, final int n2, final byte b, final int n3, final int n4, final int n5, final int n6) {
        try {
            super.aHa_Sub3_Sub2_3233.method2005(this, -111);
            OpenGL.glPixelStorei(3314, n4);
            OpenGL.glTexSubImage2Df(super.anInt3235, 0, n3, n5, n2, n, Class196.method2665(false, class164), 5121, array, n6);
            if (b <= 94) {
                this.anInt5382 = 117;
            }
            OpenGL.glPixelStorei(3314, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bf.E(" + ((array != null) ? "{...}" : "null") + ',' + ((class164 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + b + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    @Override
    public final int method43(final int n) {
        try {
            if (n <= 109) {
                return -89;
            }
            return this.anInt5384;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bf.L(" + n + ')');
        }
    }
    
    Class21_Sub1(final ha_Sub3_Sub2 ha_Sub3_Sub2, final int anInt5382, final int anInt5383, final boolean b, final int[] array, final int n, final int n2) {
        super(ha_Sub3_Sub2, 3553, Class62.aClass164_486, Class162.aClass162_1266, anInt5383 * anInt5382, b);
        try {
            this.anInt5384 = anInt5383;
            this.anInt5382 = anInt5382;
            super.aHa_Sub3_Sub2_3233.method2005(this, 69);
            if (b && ~n2 == -1 && ~n == -1) {
                this.method264(anInt5382, array, anInt5383, 526364520, super.anInt3235);
            }
            else {
                OpenGL.glPixelStorei(3314, n2);
                OpenGL.glTexImage2Di(super.anInt3235, 0, 6408, this.anInt5382, this.anInt5384, 0, 32993, super.aHa_Sub3_Sub2_3233.anInt6135, array, n * 4);
                OpenGL.glPixelStorei(3314, 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bf.<init>(" + ((ha_Sub3_Sub2 != null) ? "{...}" : "null") + ',' + anInt5382 + ',' + anInt5383 + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    static final Class246_Sub3_Sub3 method268(final int n, final int n2, final int n3) {
        final Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n][n2][n3];
        if (class172 == null) {
            return null;
        }
        return class172.aClass246_Sub3_Sub3_1324;
    }
    
    @Override
    public final void method41(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final byte[] array, final Class164 class164, final int n7) {
        try {
            super.aHa_Sub3_Sub2_3233.method2005(this, 124);
            if (n6 == -26946) {
                OpenGL.glPixelStorei(3317, 1);
                OpenGL.glPixelStorei(3314, n);
                OpenGL.glTexSubImage2Dub(super.anInt3235, 0, n7, n4, n3, n5, Class196.method2665(false, class164), 5121, array, n2);
                OpenGL.glPixelStorei(3314, 0);
                OpenGL.glPixelStorei(3317, 4);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bf.M(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + ((array != null) ? "{...}" : "null") + ',' + ((class164 != null) ? "{...}" : "null") + ',' + n7 + ')');
        }
    }
    
    @Override
    public final float method45(final int n, final float n2) {
        try {
            if (n != -8473) {
                return -1.7685026f;
            }
            return n2 / this.anInt5384;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bf.J(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    public final void method49(final int n, final int n2, final int n3, final int[] array, final int n4, final int n5, final int n6, final int n7) {
        try {
            if (n == 17779) {
                super.aHa_Sub3_Sub2_3233.method2005(this, -119);
                OpenGL.glPixelStorei(3314, n6);
                OpenGL.glTexSubImage2Di(super.anInt3235, 0, n4, n2, n7, n3, 32993, super.aHa_Sub3_Sub2_3233.anInt6135, array, n5);
                OpenGL.glPixelStorei(3314, 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bf.F(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    @Override
    public final boolean method48(final int n) {
        try {
            if (n >= -22) {
                Class21_Sub1.anInterface7Array5385 = null;
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bf.N(" + n + ')');
        }
    }
    
    @Override
    public final float method42(final byte b, final float n) {
        try {
            return n / this.anInt5382;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bf.O(" + b + ',' + n + ')');
        }
    }
    
    static final void method269(final boolean b) {
        try {
            Class246_Sub4_Sub1.aClass79_6170.method794(41);
            if (!b) {
                method270(-50);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bf.D(" + b + ')');
        }
    }
    
    @Override
    public final void method44(final int n, final int n2, final byte b, final int n3, final int n4, final int[] array, final int n5) {
        try {
            final int[] array2 = new int[this.anInt5384 * this.anInt5382];
            super.aHa_Sub3_Sub2_3233.method2005(this, 24);
            OpenGL.glGetTexImagei(super.anInt3235, 0, 32993, 5121, array2, 0);
            for (int i = 0; i < n3; ++i) {
                Class236.method2891(array2, (-1 - (-n3 - n5 - -i)) * this.anInt5382, array, n * i + n2, n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bf.H(" + n + ',' + n2 + ',' + b + ',' + n3 + ',' + n4 + ',' + ((array != null) ? "{...}" : "null") + ',' + n5 + ')');
        }
    }
    
    public static void method270(final int n) {
        try {
            Class21_Sub1.anInterface7Array5385 = null;
            if (n != 0) {
                method269(false);
            }
            Class21_Sub1.aClass269_5383 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bf.C(" + n + ')');
        }
    }
    
    static {
        Class21_Sub1.anInterface7Array5385 = new Interface7[128];
    }
}
