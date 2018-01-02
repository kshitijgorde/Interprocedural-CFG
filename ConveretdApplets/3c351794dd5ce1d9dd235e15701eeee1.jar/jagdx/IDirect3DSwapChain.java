// 
// Decompiled by Procyon v0.5.30
// 

package jagdx;

import jaclib.peer.jaa;
import jaclib.peer.IUnknown;

public class IDirect3DSwapChain extends IUnknown
{
    private jaa b;
    
    private final native int _GetBackBuffer(final int p0, final int p1, final IDirect3DSurface p2);
    
    public final native int Present(final int p0);
    
    public final IDirect3DSurface a(final int n, final int n2) {
        IDirect3DSurface direct3DSurface2;
        try {
            final IDirect3DSurface direct3DSurface = new IDirect3DSurface(this.b);
            final int getBackBuffer = this._GetBackBuffer(n, n2, direct3DSurface);
            if (kg.b(-7175, getBackBuffer)) {
                throw new jc(String.valueOf(getBackBuffer));
            }
            direct3DSurface2 = direct3DSurface;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return direct3DSurface2;
    }
    
    IDirect3DSwapChain(final jaa b) {
        super(b);
        try {
            this.b = b;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
