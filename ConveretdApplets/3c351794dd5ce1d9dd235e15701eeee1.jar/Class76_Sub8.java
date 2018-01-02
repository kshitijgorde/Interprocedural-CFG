import jaggl.OpenGL;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class76_Sub8 extends Class76
{
    static int anInt3766;
    static Random aRandom3767;
    private boolean aBoolean3768;
    private boolean aBoolean3769;
    static int anInt3770;
    static boolean aBoolean3771;
    private boolean aBoolean3772;
    private Class273 aClass273_3773;
    private Class273 aClass273_3774;
    private boolean aBoolean3775;
    private float[] aFloatArray3776;
    private Class273 aClass273_3777;
    static int anInt3778;
    private Interface4_Impl2 anInterface4_Impl2_3779;
    static int anInt3780;
    private Class273 aClass273_3781;
    
    @Override
    final void method746(final int n, final int n2, final int n3) {
        try {
            if (n3 >= -75) {
                this.method748(69, true);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "laa.E(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method743(final int n, final boolean b) {
        try {
            if (n <= 93) {
                this.anInterface4_Impl2_3779 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "laa.D(" + n + ',' + b + ')');
        }
    }
    
    @Override
    final void method739(final int n) {
        try {
            super.aHa_Sub3_585.method1951((byte)120, 1);
            super.aHa_Sub3_585.method2005(null, 76);
            super.aHa_Sub3_585.method2019(Class249.aClass128_1903, Class249.aClass128_1903, 22831);
            super.aHa_Sub3_585.method2051(0, -108, Class300.aClass65_2499);
            super.aHa_Sub3_585.method2051(2, -54, Class64_Sub16.aClass65_3681);
            super.aHa_Sub3_585.method1953(n ^ 0x5C, Class300.aClass65_2499, 0);
            super.aHa_Sub3_585.method1951((byte)120, 0);
            if (this.aBoolean3768) {
                super.aHa_Sub3_585.method2051(0, -87, Class300.aClass65_2499);
                super.aHa_Sub3_585.method1953(-93, Class300.aClass65_2499, 0);
                this.aBoolean3768 = false;
            }
            if (this.aBoolean3769) {
                OpenGL.glBindProgramARB(34336, 0);
                OpenGL.glDisable(34820);
                OpenGL.glDisable(34336);
                this.aBoolean3769 = false;
            }
            if (n != -2) {
                this.method746(-34, -50, 9);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "laa.C(" + n + ')');
        }
    }
    
    @Override
    final void method747(final int n) {
        try {
            if (n != -25684) {
                Class76_Sub8.anInt3778 = -102;
            }
            final int method2017 = super.aHa_Sub3_585.method2017((byte)67);
            final Class111_Sub3 method2018 = super.aHa_Sub3_585.method1956((byte)(-90));
            Label_0106: {
                if (!this.aBoolean3775) {
                    OpenGL.glBindProgramARB(34336, (~method2017 != Integer.MIN_VALUE) ? this.aClass273_3773.anInt2040 : this.aClass273_3781.anInt2040);
                    if (!client.aBoolean3553) {
                        break Label_0106;
                    }
                }
                OpenGL.glBindProgramARB(34336, (method2017 == Integer.MAX_VALUE) ? this.aClass273_3774.anInt2040 : this.aClass273_3777.anInt2040);
            }
            OpenGL.glEnable(34336);
            this.aBoolean3769 = true;
            method2018.method2120((byte)51, 0.0f, -1.0f, method2017, this.aFloatArray3776, 0.0f);
            OpenGL.glProgramLocalParameter4fARB(34336, 1, this.aFloatArray3776[0], this.aFloatArray3776[1], this.aFloatArray3776[2], this.aFloatArray3776[3]);
            this.method738(n + 25626);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "laa.P(" + n + ')');
        }
    }
    
    @Override
    final void method742(final int n, final int n2, final Interface4 interface4) {
        try {
            Label_0141: {
                if (interface4 == null) {
                    if (this.aBoolean3768) {
                        break Label_0141;
                    }
                    super.aHa_Sub3_585.method2005(super.aHa_Sub3_585.anInterface4_4586, 41);
                    super.aHa_Sub3_585.method2015(1, (byte)(-124));
                    super.aHa_Sub3_585.method2051(0, n - 132, IncomingOpcode.aClass65_459);
                    super.aHa_Sub3_585.method1953(-108, IncomingOpcode.aClass65_459, 0);
                    this.aBoolean3768 = true;
                    if (!client.aBoolean3553) {
                        break Label_0141;
                    }
                }
                if (this.aBoolean3768) {
                    super.aHa_Sub3_585.method2051(0, -57, Class300.aClass65_2499);
                    super.aHa_Sub3_585.method1953(-67, Class300.aClass65_2499, 0);
                    this.aBoolean3768 = false;
                }
                super.aHa_Sub3_585.method2005(interface4, -124);
                super.aHa_Sub3_585.method2015(n2, (byte)(-128));
            }
            if (n != 6) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "laa.I(" + n + ',' + n2 + ',' + ((interface4 != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class76_Sub8(final ha_Sub3_Sub2 ha_Sub3_Sub2, final Class207 class207) {
        super(ha_Sub3_Sub2);
        this.aFloatArray3776 = new float[4];
        this.aBoolean3768 = false;
        try {
            if (class207 == null || !ha_Sub3_Sub2.aBoolean6134) {
                this.aBoolean3772 = false;
            }
            else {
                this.aClass273_3781 = Class240.method2927(class207.method2739("gl", "uw_ground_unlit", -32734), ha_Sub3_Sub2, 34336, 25246);
                this.aClass273_3774 = Class240.method2927(class207.method2739("gl", "uw_ground_lit", -32734), ha_Sub3_Sub2, 34336, 25246);
                this.aClass273_3773 = Class240.method2927(class207.method2739("gl", "uw_model_unlit", -32734), ha_Sub3_Sub2, 34336, 25246);
                this.aClass273_3777 = Class240.method2927(class207.method2739("gl", "uw_model_lit", -32734), ha_Sub3_Sub2, 34336, 25246);
                if (this.aClass273_3777 != null & (this.aClass273_3773 != null & (this.aClass273_3781 != null & this.aClass273_3774 != null))) {
                    (this.anInterface4_Impl2_3779 = super.aHa_Sub3_585.method2012(2, 1, (byte)31, new int[] { 0, -1 }, false)).method46(false, false, -37);
                    this.aBoolean3772 = true;
                }
                else {
                    this.aBoolean3772 = false;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "laa.<init>(" + ((ha_Sub3_Sub2 != null) ? "{...}" : "null") + ',' + ((class207 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method738(final int n) {
        try {
            if (n > -45) {
                Class76_Sub8.aRandom3767 = null;
            }
            if (this.aBoolean3769) {
                final int xa = super.aHa_Sub3_585.XA();
                final int i = super.aHa_Sub3_585.i();
                OpenGL.glProgramLocalParameter4fARB(34336, 0, xa - (-i + xa) * 0.25f, -((xa + -i) * 0.125f) + xa, 1.0f / super.aHa_Sub3_585.method1948(126), super.aHa_Sub3_585.method2018((byte)98) / 255.0f);
                super.aHa_Sub3_585.method1951((byte)120, 1);
                super.aHa_Sub3_585.method1984(2, super.aHa_Sub3_585.method1998((byte)89));
                super.aHa_Sub3_585.method1951((byte)120, 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "laa.K(" + n + ')');
        }
    }
    
    public static void method764(final byte b) {
        try {
            Class76_Sub8.aRandom3767 = null;
            if (b != 122) {
                method764((byte)(-116));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "laa.A(" + b + ')');
        }
    }
    
    @Override
    final boolean method745(final byte b) {
        try {
            if (b != 27) {
                this.method748(-106, true);
            }
            return this.aBoolean3772;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "laa.H(" + b + ')');
        }
    }
    
    @Override
    final void method748(final int n, final boolean aBoolean3775) {
        try {
            if (n == 69) {
                this.aBoolean3775 = aBoolean3775;
                super.aHa_Sub3_585.method1951((byte)120, 1);
                super.aHa_Sub3_585.method2005(this.anInterface4_Impl2_3779, -114);
                super.aHa_Sub3_585.method2019(Class288.aClass128_3381, Class323.aClass128_2715, 22831);
                super.aHa_Sub3_585.method2051(0, -81, Class64_Sub16.aClass65_3681);
                super.aHa_Sub3_585.method2026(2, true, (byte)27, Class300.aClass65_2499, false);
                super.aHa_Sub3_585.method1953(-125, IncomingOpcode.aClass65_459, 0);
                super.aHa_Sub3_585.method1951((byte)120, 0);
                this.method747(-25684);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "laa.B(" + n + ',' + aBoolean3775 + ')');
        }
    }
    
    static {
        Class76_Sub8.aBoolean3771 = false;
        Class76_Sub8.anInt3766 = 0;
        Class76_Sub8.anInt3778 = 0;
        Class76_Sub8.aRandom3767 = new Random();
    }
}
