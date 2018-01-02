import jagdx.IDirect3DDevice;
import jagdx.IDirect3DVertexShader;
import jagdx.IDirect3DPixelShader;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class76_Sub1 extends Class76
{
    private Class195 aClass195_3721;
    private boolean aBoolean3722;
    private IDirect3DPixelShader anIDirect3DPixelShader3723;
    private static float[] aFloatArray3724;
    private boolean aBoolean3725;
    private IDirect3DVertexShader anIDirect3DVertexShader3726;
    private ha_Sub3_Sub1 aHa_Sub3_Sub1_3727;
    
    @Override
    final void method742(final int n, final int n2, final Interface4 interface4) {
        try {
            if (n != 6) {
                this.method738(72);
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
                this.aBoolean3722 = true;
            }
            if (this.aBoolean3725) {
                final IDirect3DDevice anIDirect3DDevice6098 = this.aHa_Sub3_Sub1_3727.anIDirect3DDevice6098;
                if (-1 <= ~this.aHa_Sub3_585.anInt4581) {
                    anIDirect3DDevice6098.b(16, 0.0f, 0.0f, 0.0f, 0.0f);
                }
                else {
                    final float aFloat4592 = this.aHa_Sub3_585.aFloat4592;
                    anIDirect3DDevice6098.b(16, aFloat4592, 1.0f / (aFloat4592 - this.aHa_Sub3_585.aFloat4615), 0.0f, 0.0f);
                }
                anIDirect3DDevice6098.a(0, ((0xFF21E2 & this.aHa_Sub3_585.anInt4636) >> -2101860432) / 255.0f, ((this.aHa_Sub3_585.anInt4636 & 0xFF68) >> 1976566696) / 255.0f, (0xFF & this.aHa_Sub3_585.anInt4636) / 255.0f, 0.0f);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method741(final byte b) {
        try {
            if (this.aBoolean3725) {
                this.aHa_Sub3_Sub1_3727.anIDirect3DDevice6098.a(0, this.aHa_Sub3_Sub1_3727.method2027(0).method2128(109, Class76_Sub1.aFloatArray3724));
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
                this.anIDirect3DVertexShader3726 = null;
            }
            if (this.aBoolean3725) {
                final IDirect3DDevice anIDirect3DDevice6098 = this.aHa_Sub3_Sub1_3727.anIDirect3DDevice6098;
                final Class111_Sub3 method1956 = this.aHa_Sub3_Sub1_3727.method1956((byte)78);
                anIDirect3DDevice6098.a(0, this.aHa_Sub3_Sub1_3727.method2027(0).method2128(122, Class76_Sub1.aFloatArray3724));
                anIDirect3DDevice6098.a(4, method1956.method2125(Class76_Sub1.aFloatArray3724, (byte)(-80)));
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
                Class76_Sub1.aFloatArray3724 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    Class76_Sub1(final ha_Sub3_Sub1 aHa_Sub3_Sub1_3727, final Class207 class207, final Class195 aClass195_3721) {
        super(aHa_Sub3_Sub1_3727);
        try {
            this.aClass195_3721 = aClass195_3721;
            this.aHa_Sub3_Sub1_3727 = aHa_Sub3_Sub1_3727;
            if (null == class207 || !this.aHa_Sub3_585.aBoolean4569 || !this.aHa_Sub3_585.aBoolean4588 || (0xFFFF & this.aHa_Sub3_Sub1_3727.aD3DCAPS6093.VertexShaderVersion) < 257) {
                this.anIDirect3DVertexShader3726 = null;
                this.aBoolean3722 = false;
                this.anIDirect3DPixelShader3723 = null;
            }
            else {
                this.anIDirect3DVertexShader3726 = this.aHa_Sub3_Sub1_3727.anIDirect3DDevice6098.a(class207.method2739("dx", "environment_mapped_water_v", -32734));
                this.anIDirect3DPixelShader3723 = this.aHa_Sub3_Sub1_3727.anIDirect3DDevice6098.b(class207.method2739("dx", "environment_mapped_water_f", -32734));
                this.aBoolean3722 = (null != this.anIDirect3DVertexShader3726 && null != this.anIDirect3DPixelShader3723 && this.aClass195_3721.method2664(-119));
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method749(final int n) {
        try {
            if (this.aBoolean3725) {
                this.aHa_Sub3_Sub1_3727.anIDirect3DDevice6098.SetVertexShaderConstantF(12, this.aHa_Sub3_585.method2061(n - 7).method2129(Class76_Sub1.aFloatArray3724, 4), 2);
            }
            if (n != 8) {
                this.aHa_Sub3_Sub1_3727 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method740(final int n) {
        try {
            if (this.aBoolean3725) {
                this.aHa_Sub3_Sub1_3727.anIDirect3DDevice6098.a(8, this.aHa_Sub3_585.method2040(Class76_Sub1.aFloatArray3724, 32227));
            }
            if (n >= -49) {
                this.aBoolean3722 = false;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method746(final int n, final int n2, final int n3) {
        try {
            if (this.aBoolean3725) {
                final IDirect3DDevice anIDirect3DDevice6098 = this.aHa_Sub3_Sub1_3727.anIDirect3DDevice6098;
                final int n4 = 1 << (n & 0x3);
                final float n5 = (1 << ((n & 0x3A) >> 1073146595)) / 32.0f;
                final int n6 = 0xFFFF & n2;
                final float n7 = ((0x367AF & n2) >> 1913070960) / 8.0f;
                anIDirect3DDevice6098.b(14, n4 * this.aHa_Sub3_585.anInt4556 % 40000 / 40000.0f, 0.0f, 0.0f, 0.0f);
                anIDirect3DDevice6098.b(15, n5, 0.0f, 0.0f, 0.0f);
                anIDirect3DDevice6098.a(4, n6, 0.0f, 0.0f, 0.0f);
                anIDirect3DDevice6098.a(5, n7, 0.0f, 0.0f, 0.0f);
            }
            if (n3 > -75) {
                this.method746(0, -115, -17);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method739(final int n) {
        try {
            if (this.aBoolean3725) {
                this.aHa_Sub3_Sub1_3727.method2070(null, true);
                this.aHa_Sub3_Sub1_3727.method2075(28, null);
                this.aHa_Sub3_585.method1951((byte)120, 1);
                this.aHa_Sub3_585.method2005(null, -127);
                this.aHa_Sub3_585.method1951((byte)120, 0);
                this.aHa_Sub3_585.method2005(null, 39);
                this.aBoolean3725 = false;
            }
            if (n != -2) {
                this.method741((byte)(-37));
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method748(final int n, final boolean b) {
        try {
            final Interface4_Impl3 method1939 = this.aHa_Sub3_585.method1939(-113);
            if (this.aBoolean3722 && method1939 != null) {
                final IDirect3DDevice anIDirect3DDevice6098 = this.aHa_Sub3_Sub1_3727.anIDirect3DDevice6098;
                this.aHa_Sub3_Sub1_3727.method2070(this.anIDirect3DVertexShader3726, true);
                this.aHa_Sub3_Sub1_3727.method2075(28, this.anIDirect3DPixelShader3723);
                this.aHa_Sub3_585.method1951((byte)120, 1);
                this.aHa_Sub3_585.method2005(method1939, 7);
                this.aHa_Sub3_585.method1951((byte)120, 0);
                this.aHa_Sub3_585.method2005(this.aClass195_3721.anInterface4_Impl1_1500, n ^ 0xFFFFFFC1);
                this.aBoolean3725 = true;
                this.method737(2899);
                this.method749(8);
                this.method740(-71);
                this.method738(n ^ 0xFFFFFF8D);
                anIDirect3DDevice6098.a(1, -this.aHa_Sub3_585.aFloatArray4596[0], -this.aHa_Sub3_585.aFloatArray4596[1], -this.aHa_Sub3_585.aFloatArray4596[2], 0.0f);
                anIDirect3DDevice6098.a(2, this.aHa_Sub3_585.aFloat4611, this.aHa_Sub3_585.aFloat4549, this.aHa_Sub3_585.aFloat4591, 1.0f);
                anIDirect3DDevice6098.a(3, Math.abs(this.aHa_Sub3_585.aFloatArray4596[1]) * 928.0f + 96.0f, 0.0f, 0.0f, 0.0f);
            }
            if (n != 69) {
                this.method741((byte)(-113));
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final boolean method745(final byte b) {
        boolean aBoolean3722;
        try {
            if (b != 27) {
                this.method740(103);
            }
            aBoolean3722 = this.aBoolean3722;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return aBoolean3722;
    }
    
    static {
        Class76_Sub1.aFloatArray3724 = new float[16];
    }
}
