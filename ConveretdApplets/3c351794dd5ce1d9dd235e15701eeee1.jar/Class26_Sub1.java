import jagdx.IDirect3DBaseTexture;
import jagdx.PixelBuffer;
import jagdx.kg;
import jagdx.IDirect3DCubeTexture;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class26_Sub1 extends Class26 implements Interface4_Impl3
{
    private IDirect3DCubeTexture anIDirect3DCubeTexture3576;
    private int anInt3577;
    
    Class26_Sub1(final ha_Sub3_Sub1 ha_Sub3_Sub1, final int anInt3577, final boolean b, final int[][] array) {
        super(ha_Sub3_Sub1, Class62.aClass164_486, Class162.aClass162_1266, b && ha_Sub3_Sub1.aBoolean6097, anInt3577 * (anInt3577 * 6));
        try {
            this.anInt3577 = anInt3577;
            if (!this.aBoolean269) {
                this.anIDirect3DCubeTexture3576 = this.aHa_Sub3_Sub1_270.anIDirect3DDevice6098.a(this.anInt3577, 1, 0, 21, 1);
            }
            else {
                this.anIDirect3DCubeTexture3576 = this.aHa_Sub3_Sub1_270.anIDirect3DDevice6098.a(this.anInt3577, 0, 1024, 21, 1);
            }
            final PixelBuffer aPixelBuffer6095 = this.aHa_Sub3_Sub1_270.aPixelBuffer6095;
            for (int n = 0; ~n > -7; ++n) {
                if (kg.a(-21593, this.anIDirect3DCubeTexture3576.LockRect(n, 0, 0, 0, this.anInt3577, this.anInt3577, 0, aPixelBuffer6095))) {
                    final int rowPitch = aPixelBuffer6095.getRowPitch();
                    if (~rowPitch != ~(4 * this.anInt3577)) {
                        for (int n2 = 0; ~this.anInt3577 < ~n2; ++n2) {
                            aPixelBuffer6095.a(array[n], this.anInt3577 * n2, n2 * rowPitch, this.anInt3577);
                        }
                    }
                    else {
                        aPixelBuffer6095.a(array[n], 0, 0, this.anInt3577 * this.anInt3577);
                    }
                    this.anIDirect3DCubeTexture3576.UnlockRect(n, 0);
                }
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final void method5(final int n) {
        try {
            if (n < 14) {
                this.anIDirect3DCubeTexture3576 = null;
            }
            this.aHa_Sub3_Sub1_270.method2078(this, 0);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final void method4(final byte b, final Class200 class200) {
        try {
            super.method4(b, class200);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final IDirect3DBaseTexture method293(final byte b) {
        IDirect3DCubeTexture anIDirect3DCubeTexture3576;
        try {
            if (b != 18) {
                return null;
            }
            anIDirect3DCubeTexture3576 = this.anIDirect3DCubeTexture3576;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return anIDirect3DCubeTexture3576;
    }
}
