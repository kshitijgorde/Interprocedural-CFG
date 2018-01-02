import jagdx.IDirect3DBaseTexture;
import jagdx.PixelBuffer;
import jagdx.kg;
import jagdx.IDirect3DTexture;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class26_Sub3 extends Class26 implements Interface4_Impl2
{
    private IDirect3DTexture anIDirect3DTexture3582;
    private int anInt3583;
    boolean aBoolean3584;
    boolean aBoolean3585;
    private int anInt3586;
    
    @Override
    public final void method41(int n, final int n2, int n3, final int n4, final int i, final int n5, final byte[] array, final Class164 class164, final int n6) {
        try {
            if (class164 != this.aClass164_273 || Class162.aClass162_1266 != this.aClass162_271) {
                throw new RuntimeException();
            }
            if (n5 != -26946) {
                this.aBoolean3585 = true;
            }
            final PixelBuffer aPixelBuffer6095 = this.aHa_Sub3_Sub1_270.aPixelBuffer6095;
            if (kg.a(-21593, this.anIDirect3DTexture3582.LockRect(0, n6, n4, n3, i, 0, aPixelBuffer6095))) {
                n *= this.aClass164_273.anInt1275;
                n3 *= this.aClass164_273.anInt1275;
                final int rowPitch = aPixelBuffer6095.getRowPitch();
                if (rowPitch == n3 && ~n == ~n3) {
                    aPixelBuffer6095.a(array, n2, 0, n3 * i);
                }
                else {
                    for (int n7 = 0; i > n7; ++n7) {
                        aPixelBuffer6095.a(array, n2 - -(n7 * n), n7 * rowPitch, n3);
                    }
                }
                this.anIDirect3DTexture3582.UnlockRect(0);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    Class26_Sub3(final ha_Sub3_Sub1 ha_Sub3_Sub1, final int anInt3583, final int anInt3584, final boolean b, final int[] array, final int n, int n2) {
        super(ha_Sub3_Sub1, Class62.aClass164_486, Class162.aClass162_1266, b && ha_Sub3_Sub1.aBoolean6107, anInt3583 * anInt3584);
        try {
            if (!this.aHa_Sub3_Sub1_270.aBoolean6109) {
                this.anInt3583 = Class48.method453(423660257, anInt3583);
                this.anInt3586 = Class48.method453(423660257, anInt3584);
            }
            else {
                this.anInt3583 = anInt3583;
                this.anInt3586 = anInt3584;
            }
            if (b) {
                this.anIDirect3DTexture3582 = this.aHa_Sub3_Sub1_270.anIDirect3DDevice6098.a(this.anInt3583, this.anInt3586, 0, 1024, 21, 1);
            }
            else {
                this.anIDirect3DTexture3582 = this.aHa_Sub3_Sub1_270.anIDirect3DDevice6098.a(this.anInt3583, this.anInt3586, 1, 0, 21, 1);
            }
            final PixelBuffer aPixelBuffer6095 = this.aHa_Sub3_Sub1_270.aPixelBuffer6095;
            if (kg.a(-21593, this.anIDirect3DTexture3582.LockRect(0, 0, 0, anInt3583, anInt3584, 0, aPixelBuffer6095))) {
                if (0 == n2) {
                    n2 = anInt3583;
                }
                final int rowPitch = aPixelBuffer6095.getRowPitch();
                if (~rowPitch != ~(4 * anInt3583) || ~anInt3583 != ~n2) {
                    for (int i = 0; i < anInt3584; ++i) {
                        aPixelBuffer6095.a(array, n + n2 * i, rowPitch * i, anInt3583);
                    }
                }
                else {
                    aPixelBuffer6095.a(array, n, 0, anInt3584 * anInt3583);
                }
                this.anIDirect3DTexture3582.UnlockRect(0);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final void method5(final int n) {
        try {
            this.aHa_Sub3_Sub1_270.method2079(false, this);
            if (n < 14) {
                this.anIDirect3DTexture3582 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final float method45(final int n, final float n2) {
        float n3;
        try {
            if (n != -8473) {
                return 1.9078624f;
            }
            n3 = n2 / this.anInt3586;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return n3;
    }
    
    Class26_Sub3(final ha_Sub3_Sub1 ha_Sub3_Sub1, final Class164 class164, final Class162 class165, final int anInt3583, final int anInt3584) {
        super(ha_Sub3_Sub1, class164, class165, false, anInt3584 * anInt3583);
        try {
            if (!this.aHa_Sub3_Sub1_270.aBoolean6109) {
                this.anInt3583 = Class48.method453(423660257, anInt3583);
                this.anInt3586 = Class48.method453(423660257, anInt3584);
            }
            else {
                this.anInt3586 = anInt3584;
                this.anInt3583 = anInt3583;
            }
            this.anIDirect3DTexture3582 = this.aHa_Sub3_Sub1_270.anIDirect3DDevice6098.a(anInt3583, anInt3584, 1, 0, ha_Sub3_Sub1.method2074(-1935, this.aClass164_273, this.aClass162_271), 1);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final int method43(final int n) {
        int anInt3586;
        try {
            if (n < 109) {
                return 5;
            }
            anInt3586 = this.anInt3586;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return anInt3586;
    }
    
    @Override
    public final float method42(final byte b, final float n) {
        float n2;
        try {
            n2 = n / this.anInt3583;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return n2;
    }
    
    Class26_Sub3(final ha_Sub3_Sub1 ha_Sub3_Sub1, final Class164 class164, int anInt3583, final int anInt3584, final boolean b, final byte[] array, final int n, int n2) {
        super(ha_Sub3_Sub1, class164, Class162.aClass162_1266, b && ha_Sub3_Sub1.aBoolean6107, anInt3583 * anInt3584);
        try {
            if (!this.aHa_Sub3_Sub1_270.aBoolean6109) {
                this.anInt3583 = Class48.method453(423660257, anInt3583);
                this.anInt3586 = Class48.method453(423660257, anInt3584);
            }
            else {
                this.anInt3586 = anInt3584;
                this.anInt3583 = anInt3583;
            }
            if (b) {
                this.anIDirect3DTexture3582 = this.aHa_Sub3_Sub1_270.anIDirect3DDevice6098.a(this.anInt3583, this.anInt3586, 0, 1024, ha_Sub3_Sub1.method2074(-1935, this.aClass164_273, Class162.aClass162_1266), 1);
            }
            else {
                this.anIDirect3DTexture3582 = this.aHa_Sub3_Sub1_270.anIDirect3DDevice6098.a(this.anInt3583, this.anInt3586, 1, 0, ha_Sub3_Sub1.method2074(-1935, this.aClass164_273, Class162.aClass162_1266), 1);
            }
            final PixelBuffer aPixelBuffer6095 = this.aHa_Sub3_Sub1_270.aPixelBuffer6095;
            if (kg.a(-21593, this.anIDirect3DTexture3582.LockRect(0, 0, 0, anInt3583, anInt3584, 0, aPixelBuffer6095))) {
                if (-1 == ~n2) {
                    n2 = anInt3583;
                }
                anInt3583 *= this.aClass164_273.anInt1275;
                n2 *= this.aClass164_273.anInt1275;
                final int rowPitch = aPixelBuffer6095.getRowPitch();
                if (~rowPitch != ~anInt3583 || n2 != anInt3583) {
                    for (int n3 = 0; ~n3 > ~anInt3584; ++n3) {
                        aPixelBuffer6095.a(array, n - -(n3 * n2), n3 * rowPitch, anInt3583);
                    }
                }
                else {
                    aPixelBuffer6095.a(array, n, 0, anInt3584 * anInt3583);
                }
                this.anIDirect3DTexture3582.UnlockRect(0);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final boolean method48(final int n) {
        boolean b;
        try {
            if (n > -22) {
                this.aBoolean3584 = false;
            }
            b = true;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b;
    }
    
    @Override
    public final int method47(final int n) {
        int anInt3583;
        try {
            if (n != 12941) {
                this.aBoolean3585 = false;
            }
            anInt3583 = this.anInt3583;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return anInt3583;
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
        IDirect3DTexture anIDirect3DTexture3582;
        try {
            if (b != 18) {
                this.method4((byte)(-23), null);
            }
            anIDirect3DTexture3582 = this.anIDirect3DTexture3582;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return anIDirect3DTexture3582;
    }
    
    @Override
    public final void method44(final int n, final int n2, final byte b, final int n3, final int n4, final int[] array, final int n5) {
        try {
            if (Class62.aClass164_486 != this.aClass164_273 || this.aClass162_271 != Class162.aClass162_1266) {
                throw new RuntimeException();
            }
            final PixelBuffer aPixelBuffer6095 = this.aHa_Sub3_Sub1_270.aPixelBuffer6095;
            if (kg.a(-21593, this.anIDirect3DTexture3582.LockRect(0, n4, n5, n, n3, 16, aPixelBuffer6095))) {
                final int rowPitch = aPixelBuffer6095.getRowPitch();
                if (~rowPitch == ~(n * 4)) {
                    aPixelBuffer6095.b(array, n2, 0, array.length);
                }
                else {
                    for (int i = 0; i < n3; ++i) {
                        aPixelBuffer6095.b(array, n2 + i * n, rowPitch * i, n);
                    }
                }
                this.anIDirect3DTexture3582.UnlockRect(0);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final void method46(final boolean aBoolean3584, final boolean aBoolean3585, final int n) {
        try {
            this.aBoolean3584 = aBoolean3584;
            this.aBoolean3585 = aBoolean3585;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final void method49(final int n, final int n2, final int n3, final int[] array, final int n4, final int n5, final int n6, final int n7) {
        try {
            if (Class62.aClass164_486 != this.aClass164_273 || Class162.aClass162_1266 != this.aClass162_271) {
                throw new RuntimeException();
            }
            if (n == 17779) {
                final PixelBuffer aPixelBuffer6095 = this.aHa_Sub3_Sub1_270.aPixelBuffer6095;
                if (kg.a(-21593, this.anIDirect3DTexture3582.LockRect(0, n4, n2, n7, n3, 0, aPixelBuffer6095))) {
                    final int rowPitch = aPixelBuffer6095.getRowPitch();
                    if (~rowPitch != ~(n7 * 4) || ~n7 != ~n6) {
                        for (int n8 = 0; ~n3 < ~n8; ++n8) {
                            aPixelBuffer6095.a(array, n8 * n6 + n5, n8 * rowPitch, n7);
                        }
                    }
                    else {
                        aPixelBuffer6095.a(array, n5, 0, n3 * n7);
                    }
                    this.anIDirect3DTexture3582.UnlockRect(0);
                }
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
