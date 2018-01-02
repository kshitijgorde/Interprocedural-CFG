import jagdx.IDirect3DDevice;
import jagdx.IDirect3DVertexShader;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class76_Sub6 extends Class76
{
    private IDirect3DVertexShader anIDirect3DVertexShader3749;
    private IDirect3DVertexShader anIDirect3DVertexShader3750;
    private boolean aBoolean3751;
    private static float[] aFloatArray3752;
    private IDirect3DVertexShader anIDirect3DVertexShader3753;
    private ha_Sub3_Sub1 aHa_Sub3_Sub1_3754;
    private Interface4_Impl2 anInterface4_Impl2_3755;
    private IDirect3DVertexShader anIDirect3DVertexShader3756;
    private boolean aBoolean3757;
    private static float[] aFloatArray3758;
    private IDirect3DVertexShader anIDirect3DVertexShader3759;
    private boolean aBoolean3760;
    
    @Override
    final void method740(final int n) {
        try {
            if (n > -49) {
                this.aBoolean3760 = false;
            }
            if (null != this.anIDirect3DVertexShader3750) {
                this.aHa_Sub3_Sub1_3754.anIDirect3DDevice6098.a(4, this.aHa_Sub3_585.method2040(Class76_Sub6.aFloatArray3758, 32227));
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method738(final int n) {
        try {
            if (n > -45) {
                this.anIDirect3DVertexShader3750 = null;
            }
            if (null != this.anIDirect3DVertexShader3750) {
                final IDirect3DDevice anIDirect3DDevice6098 = this.aHa_Sub3_Sub1_3754.anIDirect3DDevice6098;
                final int xa = this.aHa_Sub3_585.XA();
                final int i = this.aHa_Sub3_585.i();
                final float n2 = xa - (xa - i) * 0.125f;
                final float n3 = -(0.25f * (xa - i)) + xa;
                anIDirect3DDevice6098.b(10, n3, 1.0f / (n2 - n3), n2, 1.0f / (xa - n2));
                anIDirect3DDevice6098.b(11, 1.0f / this.aHa_Sub3_585.method1948(126), this.aHa_Sub3_585.method2018((byte)122) / 255.0f, this.aHa_Sub3_585.aFloat4592, 1.0f / (this.aHa_Sub3_585.aFloat4592 - this.aHa_Sub3_585.aFloat4615));
                this.aHa_Sub3_585.method1984(2, this.aHa_Sub3_585.method1998((byte)92));
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private final void method760(final boolean b) {
        try {
            if (!b) {
                this.method749(-31);
            }
            if (null != this.anIDirect3DVertexShader3750) {
                if (this.aBoolean3751) {
                    final Class111_Sub3 method2023 = this.aHa_Sub3_585.method2023(1);
                    final IDirect3DDevice anIDirect3DDevice6098 = this.aHa_Sub3_Sub1_3754.anIDirect3DDevice6098;
                    anIDirect3DDevice6098.b(13, this.aHa_Sub3_585.aFloat4576 * this.aHa_Sub3_585.aFloat4611, this.aHa_Sub3_585.aFloat4576 * this.aHa_Sub3_585.aFloat4549, this.aHa_Sub3_585.aFloat4591 * this.aHa_Sub3_585.aFloat4576, 1.0f);
                    anIDirect3DDevice6098.b(14, this.aHa_Sub3_585.aFloat4630 * this.aHa_Sub3_585.aFloat4611, this.aHa_Sub3_585.aFloat4549 * this.aHa_Sub3_585.aFloat4630, this.aHa_Sub3_585.aFloat4630 * this.aHa_Sub3_585.aFloat4591, 1.0f);
                    anIDirect3DDevice6098.b(16, this.aHa_Sub3_585.aFloat4611 * this.aHa_Sub3_585.aFloat4594, this.aHa_Sub3_585.aFloat4594 * this.aHa_Sub3_585.aFloat4549, this.aHa_Sub3_585.aFloat4594 * this.aHa_Sub3_585.aFloat4591, 1.0f);
                    method2023.method2134(-19890, this.aHa_Sub3_585.aFloatArray4596[0], this.aHa_Sub3_585.aFloatArray4596[2], Class76_Sub6.aFloatArray3752, this.aHa_Sub3_585.aFloatArray4596[1]);
                    anIDirect3DDevice6098.SetVertexShaderConstantF(15, Class76_Sub6.aFloatArray3752, 1);
                    method2023.method2134(-19890, this.aHa_Sub3_585.aFloatArray4572[0], this.aHa_Sub3_585.aFloatArray4572[2], Class76_Sub6.aFloatArray3752, this.aHa_Sub3_585.aFloatArray4572[1]);
                    anIDirect3DDevice6098.SetVertexShaderConstantF(17, Class76_Sub6.aFloatArray3752, 1);
                }
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method742(final int n, final int n2, final Interface4 interface4) {
        try {
            if (null == interface4) {
                if (!this.aBoolean3760) {
                    this.aHa_Sub3_585.method2005(this.aHa_Sub3_585.anInterface4_4586, 98);
                    this.aHa_Sub3_585.method2015(1, (byte)(-99));
                    this.aHa_Sub3_585.method2051(0, -115, IncomingOpcode.aClass65_459);
                    this.aHa_Sub3_585.method1953(-121, IncomingOpcode.aClass65_459, 0);
                    this.aBoolean3760 = true;
                }
            }
            else {
                if (this.aBoolean3760) {
                    this.aHa_Sub3_585.method2051(0, -80, Class300.aClass65_2499);
                    this.aHa_Sub3_585.method1953(-72, Class300.aClass65_2499, 0);
                    this.aBoolean3760 = false;
                }
                this.aHa_Sub3_585.method2005(interface4, n ^ 0xFFFFFF8C);
                this.aHa_Sub3_585.method2015(n2, (byte)92);
            }
            if (n != 6) {
                this.method748(-96, true);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method739(final int n) {
        try {
            this.aHa_Sub3_585.method1951((byte)120, 1);
            this.aHa_Sub3_585.method2005(null, -123);
            this.aHa_Sub3_585.method2019(Class249.aClass128_1903, Class249.aClass128_1903, 22831);
            this.aHa_Sub3_585.method2051(0, -70, Class300.aClass65_2499);
            this.aHa_Sub3_585.method2051(2, -81, Class64_Sub16.aClass65_3681);
            this.aHa_Sub3_585.method1953(-108, Class300.aClass65_2499, 0);
            this.aHa_Sub3_585.method1951((byte)120, 0);
            if (this.aBoolean3760) {
                this.aHa_Sub3_585.method2051(0, -115, Class300.aClass65_2499);
                this.aHa_Sub3_585.method1953(-82, Class300.aClass65_2499, 0);
                this.aBoolean3760 = false;
            }
            if (n != -2) {
                this.method747(96);
            }
            if (null != this.anIDirect3DVertexShader3750) {
                this.aHa_Sub3_Sub1_3754.method2070(null, true);
                this.anIDirect3DVertexShader3750 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final boolean method745(final byte b) {
        boolean aBoolean3757;
        try {
            if (b != 27) {
                this.method746(-54, -30, -35);
            }
            aBoolean3757 = this.aBoolean3757;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return aBoolean3757;
    }
    
    @Override
    final void method746(final int n, final int n2, final int n3) {
        try {
            if (n3 > -75) {
                this.aBoolean3757 = false;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method741(final byte b) {
        try {
            if (null != this.anIDirect3DVertexShader3750) {
                this.aHa_Sub3_Sub1_3754.anIDirect3DDevice6098.a(0, this.aHa_Sub3_Sub1_3754.method2027(0).method2128(123, Class76_Sub6.aFloatArray3758));
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method748(final int n, final boolean aBoolean3751) {
        try {
            this.aBoolean3751 = aBoolean3751;
            this.aHa_Sub3_585.method1951((byte)120, 1);
            this.aHa_Sub3_585.method2005(this.anInterface4_Impl2_3755, 63);
            this.aHa_Sub3_585.method2019(Class288.aClass128_3381, Class323.aClass128_2715, n + 22762);
            this.aHa_Sub3_585.method2051(0, n ^ 0xFFFFFFC3, Class64_Sub16.aClass65_3681);
            this.aHa_Sub3_585.method2026(2, true, (byte)27, Class300.aClass65_2499, false);
            this.aHa_Sub3_585.method1953(-76, IncomingOpcode.aClass65_459, 0);
            this.aHa_Sub3_585.method1951((byte)120, 0);
            if (n == 69) {
                this.method747(-25684);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method743(final int n, final boolean b) {
        try {
            if (n <= 93) {
                Class76_Sub6.aFloatArray3752 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    Class76_Sub6(final ha_Sub3_Sub1 aHa_Sub3_Sub1_3754, final Class207 class207) {
        super(aHa_Sub3_Sub1_3754);
        this.aBoolean3760 = false;
        try {
            this.aHa_Sub3_Sub1_3754 = aHa_Sub3_Sub1_3754;
            if (class207 == null || -258 < ~(this.aHa_Sub3_Sub1_3754.aD3DCAPS6093.VertexShaderVersion & 0xFFFF)) {
                this.aBoolean3757 = false;
            }
            else {
                this.anIDirect3DVertexShader3759 = this.aHa_Sub3_Sub1_3754.anIDirect3DDevice6098.a(class207.method2739("dx", "uw_ground_unlit", -32734));
                this.anIDirect3DVertexShader3753 = this.aHa_Sub3_Sub1_3754.anIDirect3DDevice6098.a(class207.method2739("dx", "uw_ground_lit", -32734));
                this.anIDirect3DVertexShader3749 = this.aHa_Sub3_Sub1_3754.anIDirect3DDevice6098.a(class207.method2739("dx", "uw_model_unlit", -32734));
                this.anIDirect3DVertexShader3756 = this.aHa_Sub3_Sub1_3754.anIDirect3DDevice6098.a(class207.method2739("dx", "uw_model_lit", -32734));
                if (this.anIDirect3DVertexShader3756 != null & (this.anIDirect3DVertexShader3753 != null & null != this.anIDirect3DVertexShader3759 & this.anIDirect3DVertexShader3749 != null)) {
                    (this.anInterface4_Impl2_3755 = this.aHa_Sub3_585.method2012(2, 1, (byte)31, new int[] { 0, -1 }, false)).method46(false, false, 76);
                    this.aBoolean3757 = true;
                }
                else {
                    this.aBoolean3757 = false;
                }
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method737(final int n) {
        try {
            if (this.anIDirect3DVertexShader3750 != null) {
                this.aHa_Sub3_Sub1_3754.anIDirect3DDevice6098.a(0, this.aHa_Sub3_Sub1_3754.method2027(0).method2128(126, Class76_Sub6.aFloatArray3758));
            }
            if (n != 2899) {
                this.method741((byte)(-1));
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method747(final int n) {
        try {
            if (n == -25684) {
                final IDirect3DDevice anIDirect3DDevice6098 = this.aHa_Sub3_Sub1_3754.anIDirect3DDevice6098;
                final int method2017 = this.aHa_Sub3_585.method2017((byte)67);
                final Class111_Sub3 method2018 = this.aHa_Sub3_585.method1956((byte)(-127));
                IDirect3DVertexShader anIDirect3DVertexShader3750;
                if (!this.aBoolean3751) {
                    anIDirect3DVertexShader3750 = ((method2017 == Integer.MAX_VALUE) ? this.anIDirect3DVertexShader3759 : this.anIDirect3DVertexShader3749);
                }
                else {
                    anIDirect3DVertexShader3750 = ((Integer.MIN_VALUE != ~method2017) ? this.anIDirect3DVertexShader3756 : this.anIDirect3DVertexShader3753);
                }
                if (this.anIDirect3DVertexShader3750 != anIDirect3DVertexShader3750) {
                    this.anIDirect3DVertexShader3750 = anIDirect3DVertexShader3750;
                    this.aHa_Sub3_Sub1_3754.method2070(anIDirect3DVertexShader3750, true);
                    this.method760(true);
                    this.method740(-101);
                    this.method749(8);
                    this.method741((byte)(-124));
                    this.method737(2899);
                    this.method738(-57);
                }
                method2018.method2120((byte)18, 0.0f, -1.0f, method2017, Class76_Sub6.aFloatArray3752, 0.0f);
                anIDirect3DDevice6098.a(12, Class76_Sub6.aFloatArray3752);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method749(final int n) {
        try {
            if (null != this.anIDirect3DVertexShader3750) {
                this.aHa_Sub3_Sub1_3754.anIDirect3DDevice6098.SetVertexShaderConstantF(8, this.aHa_Sub3_585.method2061(n - 7).method2129(Class76_Sub6.aFloatArray3758, 4), 2);
            }
            if (n != 8) {
                this.anIDirect3DVertexShader3749 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static {
        Class76_Sub6.aFloatArray3752 = new float[4];
        Class76_Sub6.aFloatArray3758 = new float[16];
    }
}
