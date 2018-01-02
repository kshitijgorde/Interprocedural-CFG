import jagdx.PixelBuffer;
import jagdx.kg;
import jagdx.IDirect3DBaseTexture;
import jagdx.IDirect3DVolumeTexture;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class26_Sub2 extends Class26 implements Interface4_Impl1
{
    private IDirect3DVolumeTexture anIDirect3DVolumeTexture3578;
    private int anInt3579;
    private int anInt3580;
    private int anInt3581;
    
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
        IDirect3DVolumeTexture anIDirect3DVolumeTexture3578;
        try {
            if (b != 18) {
                this.anInt3580 = 43;
            }
            anIDirect3DVolumeTexture3578 = this.anIDirect3DVolumeTexture3578;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return anIDirect3DVolumeTexture3578;
    }
    
    @Override
    public final void method5(final int n) {
        try {
            this.aHa_Sub3_Sub1_270.method2077(false, this);
            if (n <= 14) {
                this.anIDirect3DVolumeTexture3578 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    Class26_Sub2(final ha_Sub3_Sub1 ha_Sub3_Sub1, final Class164 class164, final int anInt3579, final int anInt3580, final int anInt3581, final byte[] array) {
        super(ha_Sub3_Sub1, class164, Class162.aClass162_1266, false, anInt3581 * (anInt3579 * anInt3580));
        try {
            this.anInt3580 = anInt3580;
            this.anInt3581 = anInt3581;
            this.anInt3579 = anInt3579;
            this.anIDirect3DVolumeTexture3578 = this.aHa_Sub3_Sub1_270.anIDirect3DDevice6098.a(anInt3579, anInt3580, anInt3581, 1, 0, ha_Sub3_Sub1.method2074(-1935, class164, this.aClass162_271), 1);
            final PixelBuffer aPixelBuffer6095 = this.aHa_Sub3_Sub1_270.aPixelBuffer6095;
            if (kg.a(-21593, this.anIDirect3DVolumeTexture3578.LockBox(0, 0, 0, 0, anInt3579, anInt3580, anInt3581, 0, aPixelBuffer6095))) {
                final int n = this.anInt3579 * this.aClass164_273.anInt1275;
                final int n2 = n * this.anInt3580;
                final int slicePitch = aPixelBuffer6095.getSlicePitch();
                if (slicePitch == n2) {
                    aPixelBuffer6095.a(array, 0, 0, this.anInt3580 * (n * this.anInt3581));
                }
                else {
                    final int rowPitch = aPixelBuffer6095.getRowPitch();
                    if (~rowPitch == ~n) {
                        for (int n3 = 0; ~this.anInt3581 < ~n3; ++n3) {
                            aPixelBuffer6095.a(array, n3 * n2, n3 * slicePitch, n2);
                        }
                    }
                    else {
                        for (int i = 0; i < this.anInt3581; ++i) {
                            for (int n4 = 0; ~n4 > ~this.anInt3580; ++n4) {
                                aPixelBuffer6095.a(array, n4 * n + n2 * i, n4 * rowPitch + slicePitch * i, n);
                            }
                        }
                    }
                }
                this.anIDirect3DVolumeTexture3578.UnlockBox(0);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
