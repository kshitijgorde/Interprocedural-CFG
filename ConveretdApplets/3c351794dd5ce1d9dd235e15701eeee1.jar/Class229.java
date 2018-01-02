import jagdx.IDirect3DSwapChain;
import jagdx.IDirect3DSurface;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class229
{
    private IDirect3DSurface anIDirect3DSurface1718;
    private IDirect3DSurface anIDirect3DSurface1719;
    private IDirect3DSwapChain anIDirect3DSwapChain1720;
    
    final int method2865(final boolean b, final int n) {
        int present;
        try {
            if (!b) {
                return 100;
            }
            present = this.anIDirect3DSwapChain1720.Present(n);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return present;
    }
    
    final void method2866(final IDirect3DSurface anIDirect3DSurface1718, final byte b, final IDirect3DSwapChain anIDirect3DSwapChain1720) {
        try {
            this.anIDirect3DSwapChain1720 = anIDirect3DSwapChain1720;
            this.anIDirect3DSurface1718 = anIDirect3DSurface1718;
            this.anIDirect3DSurface1719 = this.anIDirect3DSwapChain1720.a(0, 0);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final boolean method2867(final int n) {
        boolean b;
        try {
            if (n != 0) {
                this.method2865(true, 65);
            }
            if (null != this.anIDirect3DSwapChain1720) {
                return true;
            }
            b = false;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b;
    }
    
    final void method2868(final byte b) {
        try {
            if (this.anIDirect3DSurface1719 != null) {
                this.anIDirect3DSurface1719.b(49);
                this.anIDirect3DSurface1719 = null;
            }
            if (null != this.anIDirect3DSurface1718) {
                this.anIDirect3DSurface1718.b(99);
                this.anIDirect3DSurface1718 = null;
            }
            if (null != this.anIDirect3DSwapChain1720) {
                this.anIDirect3DSwapChain1720.b(63);
                this.anIDirect3DSwapChain1720 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    Class229(final IDirect3DSwapChain direct3DSwapChain, final IDirect3DSurface direct3DSurface) {
        try {
            this.method2866(direct3DSurface, (byte)(-113), direct3DSwapChain);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
