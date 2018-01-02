import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class76_Sub9 extends Class76
{
    private boolean aBoolean3782;
    static Thread aThread3783;
    static float[] aFloatArray3784;
    static int[] anIntArray3785;
    static int anInt3786;
    static Class207 aClass207_3787;
    static boolean aBoolean3788;
    private boolean aBoolean3789;
    private Class195 aClass195_3790;
    static int[] anIntArray3791;
    private Class82 aClass82_3792;
    
    static final String method765(final String s, final int n, final String s2, String string) {
        try {
            if (n != 4185) {
                return null;
            }
            for (int n2 = string.indexOf(s2); ~n2 != 0x0; n2 = string.indexOf(s2, s.length() + n2)) {
                string = string.substring(0, n2) + s + string.substring(n2 - -s2.length());
            }
            return string;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static final boolean method766(final int n, final int n2, final int n3) {
        try {
            if (n > -84) {
                Class76_Sub9.anInt3786 = -19;
            }
            return (0x34 & n3) != 0x0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "q.F(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method739(final int n) {
        try {
            if (n != -2) {
                method768(31);
            }
            if (this.aBoolean3789) {
                super.aHa_Sub3_585.method1951((byte)120, 1);
                super.aHa_Sub3_585.method2005(null, 40);
                super.aHa_Sub3_585.method1951((byte)120, 0);
                super.aHa_Sub3_585.method2005(null, -114);
                OpenGL.glUseProgramObjectARB(0L);
                this.aBoolean3789 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "q.C(" + n + ')');
        }
    }
    
    @Override
    final void method748(final int n, final boolean b) {
        try {
            if (n != 69) {
                this.aBoolean3789 = true;
            }
            final Interface4_Impl3 method1939 = super.aHa_Sub3_585.method1939(-93);
            if (this.aBoolean3782 && method1939 != null) {
                super.aHa_Sub3_585.method1951((byte)120, 1);
                super.aHa_Sub3_585.method2005(method1939, n + 11);
                super.aHa_Sub3_585.method1951((byte)120, 0);
                super.aHa_Sub3_585.method2005(this.aClass195_3790.anInterface4_Impl1_1500, n - 197);
                final long aLong628 = this.aClass82_3792.aLong628;
                OpenGL.glUseProgramObjectARB(aLong628);
                OpenGL.glUniform1iARB(OpenGL.glGetUniformLocationARB(aLong628, "normalSampler"), 0);
                OpenGL.glUniform1iARB(OpenGL.glGetUniformLocationARB(aLong628, "envMapSampler"), 1);
                OpenGL.glUniform3fARB(OpenGL.glGetUniformLocationARB(aLong628, "sunDir"), -super.aHa_Sub3_585.aFloatArray4596[0], -super.aHa_Sub3_585.aFloatArray4596[1], -super.aHa_Sub3_585.aFloatArray4596[2]);
                OpenGL.glUniform4fARB(OpenGL.glGetUniformLocationARB(aLong628, "sunColour"), super.aHa_Sub3_585.aFloat4611, super.aHa_Sub3_585.aFloat4549, super.aHa_Sub3_585.aFloat4591, 1.0f);
                OpenGL.glUniform1fARB(OpenGL.glGetUniformLocationARB(aLong628, "sunExponent"), 96.0f + 928.0f * Math.abs(super.aHa_Sub3_585.aFloatArray4596[1]));
                this.aBoolean3789 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "q.B(" + n + ',' + b + ')');
        }
    }
    
    public static void method767(final boolean b) {
        try {
            Class76_Sub9.anIntArray3791 = null;
            Class76_Sub9.aClass207_3787 = null;
            Class76_Sub9.aFloatArray3784 = null;
            if (!b) {
                Class76_Sub9.aThread3783 = null;
            }
            Class76_Sub9.anIntArray3785 = null;
            Class76_Sub9.aThread3783 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "q.A(" + b + ')');
        }
    }
    
    @Override
    final boolean method745(final byte b) {
        try {
            if (b != 27) {
                Class76_Sub9.aClass207_3787 = null;
            }
            return this.aBoolean3782;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "q.H(" + b + ')');
        }
    }
    
    @Override
    final void method743(final int n, final boolean b) {
        try {
            if (n <= 93) {
                Class76_Sub9.aBoolean3788 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "q.D(" + n + ',' + b + ')');
        }
    }
    
    @Override
    final void method746(final int n, final int n2, final int n3) {
        try {
            if (n3 <= -75 && this.aBoolean3789) {
                final int n4 = 1 << (0x3 & n);
                final float n5 = (1 << ((0x3F & n) >> 1719984675)) / 32.0f;
                final int n6 = 0xFFFF & n2;
                final float n7 = (n2 >> -1594334576 & 0x3) / 8.0f;
                final long aLong628 = this.aClass82_3792.aLong628;
                OpenGL.glUniform1fARB(OpenGL.glGetUniformLocationARB(aLong628, "time"), n4 * super.aHa_Sub3_585.anInt4556 % 40000 / 40000.0f);
                OpenGL.glUniform1fARB(OpenGL.glGetUniformLocationARB(aLong628, "scale"), n5);
                OpenGL.glUniform1fARB(OpenGL.glGetUniformLocationARB(aLong628, "breakWaterDepth"), n6);
                OpenGL.glUniform1fARB(OpenGL.glGetUniformLocationARB(aLong628, "breakWaterOffset"), n7);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "q.E(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method742(final int n, final int n2, final Interface4 interface4) {
        try {
            if (n == 6 && !this.aBoolean3789) {
                super.aHa_Sub3_585.method2005(interface4, 103);
                super.aHa_Sub3_585.method2015(n2, (byte)(-105));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "q.I(" + n + ',' + n2 + ',' + ((interface4 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method768(final int n) {
        try {
            Class159.aClass293ArrayArray1252 = new Class293[Class98_Sub17_Sub1.aClass207_5783.method2752((byte)(-11))][];
            Class64_Sub13.aClass293ArrayArray3674 = new Class293[Class98_Sub17_Sub1.aClass207_5783.method2752((byte)(-11))][];
            Class246_Sub3_Sub3_Sub1.aBooleanArray6256 = new boolean[Class98_Sub17_Sub1.aClass207_5783.method2752((byte)(-11))];
            if (n < 103) {
                method766(-4, -90, 36);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "q.O(" + n + ')');
        }
    }
    
    Class76_Sub9(final ha_Sub3_Sub2 ha_Sub3_Sub2, final Class207 class207, final Class195 aClass195_3790) {
        super(ha_Sub3_Sub2);
        try {
            this.aClass195_3790 = aClass195_3790;
            if (class207 != null && ha_Sub3_Sub2.aBoolean6133 && ha_Sub3_Sub2.aBoolean6136) {
                this.aClass82_3792 = Class205.method2713(ha_Sub3_Sub2, 0, new Class230[] { Class98_Sub46.method1526(class207.method2739("gl", "environment_mapped_water_v", -32734), ha_Sub3_Sub2, -25671, 35633), Class98_Sub46.method1526(class207.method2739("gl", "environment_mapped_water_f", -32734), ha_Sub3_Sub2, -25671, 35632) });
                this.aBoolean3782 = (this.aClass82_3792 != null && this.aClass195_3790.method2664(-118));
            }
            else {
                this.aBoolean3782 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "q.<init>(" + ((ha_Sub3_Sub2 != null) ? "{...}" : "null") + ',' + ((class207 != null) ? "{...}" : "null") + ',' + ((aClass195_3790 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class76_Sub9.anIntArray3785 = new int[1];
        Class76_Sub9.anInt3786 = 1;
        Class76_Sub9.aBoolean3788 = false;
        Class76_Sub9.aFloatArray3784 = new float[2];
        Class76_Sub9.anIntArray3791 = new int[2048];
    }
}
