import jagdx.IDirect3DDevice;
import jagdx.IDirect3DVertexShader;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class76_Sub3 extends Class76
{
    private IDirect3DVertexShader anIDirect3DVertexShader3734;
    private Class195 aClass195_3735;
    private static float[] aFloatArray3736;
    private ha_Sub3_Sub1 aHa_Sub3_Sub1_3737;
    
    @Override
    final void method743(final int n, final boolean b) {
        try {
            this.aHa_Sub3_585.method2019(Class1.aClass128_64, Class323.aClass128_2715, 22831);
            if (n <= 93) {
                this.aClass195_3735 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method741(final byte b) {
        try {
            if (null != this.anIDirect3DVertexShader3734) {
                this.aHa_Sub3_Sub1_3737.anIDirect3DDevice6098.a(0, this.aHa_Sub3_Sub1_3737.method2027(0).method2128(127, Class76_Sub3.aFloatArray3736));
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method742(final int n, final int n2, final Interface4 interface4) {
        try {
            if (n != 6) {
                this.aClass195_3735 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method746(final int n, final int n2, final int n3) {
        try {
            if (n3 <= -75) {
                final IDirect3DDevice anIDirect3DDevice6098 = this.aHa_Sub3_Sub1_3737.anIDirect3DDevice6098;
                if (!this.aClass195_3735.aBoolean1501) {
                    this.aHa_Sub3_585.method2005(this.aClass195_3735.anInterface4_Impl2Array1503[this.aHa_Sub3_585.anInt4556 % 4000 * 16 / 4000], 17);
                    anIDirect3DDevice6098.b(11, 0.0f, 0.0f, 0.0f, 0.0f);
                }
                else {
                    final float n4 = this.aHa_Sub3_585.anInt4556 % 4000 / 4000.0f;
                    this.aHa_Sub3_585.method2005(this.aClass195_3735.anInterface4_Impl1_1498, 22);
                    anIDirect3DDevice6098.b(11, n4, 0.0f, 0.0f, 0.0f);
                }
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method740(final int n) {
        try {
            if (n >= -49) {
                this.method742(61, 19, null);
            }
            if (null != this.anIDirect3DVertexShader3734) {
                this.aHa_Sub3_Sub1_3737.anIDirect3DDevice6098.a(4, this.aHa_Sub3_585.method2040(Class76_Sub3.aFloatArray3736, 32227));
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method738(final int n) {
        try {
            if (n < -45) {
                if (null != this.anIDirect3DVertexShader3734) {
                    final IDirect3DDevice anIDirect3DDevice6098 = this.aHa_Sub3_Sub1_3737.anIDirect3DDevice6098;
                    if (~this.aHa_Sub3_585.anInt4581 >= -1) {
                        anIDirect3DDevice6098.b(10, 0.0f, 0.0f, 0.0f, 0.0f);
                    }
                    else {
                        final float aFloat4592 = this.aHa_Sub3_585.aFloat4592;
                        final float aFloat4593 = this.aHa_Sub3_585.aFloat4615;
                        final float n2 = aFloat4593 - 512.0f;
                        anIDirect3DDevice6098.b(10, n2, 1.0f / (aFloat4593 - n2), aFloat4593, 1.0f / (-aFloat4593 + aFloat4592));
                    }
                    this.aHa_Sub3_585.method1984(2, this.aHa_Sub3_585.anInt4636);
                }
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method739(final int n) {
        try {
            this.aHa_Sub3_Sub1_3737.method2070(null, true);
            this.aHa_Sub3_585.method2051(0, -56, Class300.aClass65_2499);
            this.aHa_Sub3_585.method2051(1, -113, Class98_Sub43_Sub3.aClass65_5926);
            if (n != -2) {
                this.anIDirect3DVertexShader3734 = null;
            }
            this.aHa_Sub3_585.method2051(2, -89, Class64_Sub16.aClass65_3681);
            this.aHa_Sub3_585.method1945((byte)121, true);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final boolean method745(final byte b) {
        boolean b2;
        try {
            if (b != 27) {
                this.anIDirect3DVertexShader3734 = null;
            }
            if (null != this.anIDirect3DVertexShader3734) {
                return true;
            }
            b2 = false;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b2;
    }
    
    @Override
    final void method749(final int n) {
        try {
            if (null != this.anIDirect3DVertexShader3734) {
                final IDirect3DDevice anIDirect3DDevice6098 = this.aHa_Sub3_Sub1_3737.anIDirect3DDevice6098;
                this.aHa_Sub3_585.method2061(1).method2129(Class76_Sub3.aFloatArray3736, 4);
                final float[] aFloatArray3736 = Class76_Sub3.aFloatArray3736;
                final int n2 = 5;
                aFloatArray3736[n2] *= 0.25f;
                final float[] aFloatArray3737 = Class76_Sub3.aFloatArray3736;
                final int n3 = 2;
                aFloatArray3737[n3] *= 0.25f;
                final float[] aFloatArray3738 = Class76_Sub3.aFloatArray3736;
                final int n4 = 7;
                aFloatArray3738[n4] *= 0.25f;
                final float[] aFloatArray3739 = Class76_Sub3.aFloatArray3736;
                final int n5 = 4;
                aFloatArray3739[n5] *= 0.25f;
                final float[] aFloatArray3740 = Class76_Sub3.aFloatArray3736;
                final int n6 = 0;
                aFloatArray3740[n6] *= 0.25f;
                final float[] aFloatArray3741 = Class76_Sub3.aFloatArray3736;
                final int n7 = 6;
                aFloatArray3741[n7] *= 0.25f;
                final float[] aFloatArray3742 = Class76_Sub3.aFloatArray3736;
                final int n8 = 1;
                aFloatArray3742[n8] *= 0.25f;
                final float[] aFloatArray3743 = Class76_Sub3.aFloatArray3736;
                final int n9 = 3;
                aFloatArray3743[n9] *= 0.25f;
                anIDirect3DDevice6098.SetVertexShaderConstantF(8, Class76_Sub3.aFloatArray3736, 2);
            }
            if (n != 8) {
                this.aClass195_3735 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    Class76_Sub3(final ha_Sub3_Sub1 aHa_Sub3_Sub1_3737, final Class207 class207, final Class195 aClass195_3735) {
        super(aHa_Sub3_Sub1_3737);
        try {
            this.aHa_Sub3_Sub1_3737 = aHa_Sub3_Sub1_3737;
            this.aClass195_3735 = aClass195_3735;
            if (class207 != null && this.aClass195_3735.method2659(-22382) && 257 <= (this.aHa_Sub3_Sub1_3737.aD3DCAPS6093.VertexShaderVersion & 0xFFFF)) {
                this.anIDirect3DVertexShader3734 = this.aHa_Sub3_Sub1_3737.anIDirect3DDevice6098.a(class207.method2739("dx", "transparent_water", -32734));
            }
            else {
                this.anIDirect3DVertexShader3734 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method737(final int n) {
        try {
            if (n != 2899) {
                Class76_Sub3.aFloatArray3736 = null;
            }
            if (this.anIDirect3DVertexShader3734 != null) {
                this.aHa_Sub3_Sub1_3737.anIDirect3DDevice6098.a(0, this.aHa_Sub3_Sub1_3737.method2027(0).method2128(127, Class76_Sub3.aFloatArray3736));
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method748(final int n, final boolean b) {
        try {
            this.aHa_Sub3_585.method2051(0, n ^ 0xFFFFFFC0, Class300.aClass65_2499);
            this.aHa_Sub3_585.method2051(1, -88, Class64_Sub16.aClass65_3681);
            this.aHa_Sub3_585.method2026(2, false, (byte)27, Class98_Sub43_Sub3.aClass65_5926, true);
            this.aHa_Sub3_585.method1945((byte)110, false);
            this.aHa_Sub3_Sub1_3737.method2070(this.anIDirect3DVertexShader3734, true);
            if (n == 69) {
                this.method741((byte)(-111));
                this.method749(n ^ 0x4D);
                this.method740(-116);
                this.method738(-75);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static {
        Class76_Sub3.aFloatArray3736 = new float[16];
    }
}
