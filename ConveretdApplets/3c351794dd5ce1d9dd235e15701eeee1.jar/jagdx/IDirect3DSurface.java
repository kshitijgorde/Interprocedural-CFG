// 
// Decompiled by Procyon v0.5.30
// 

package jagdx;

import jaclib.peer.jaa;
import jaclib.peer.IUnknown;

public class IDirect3DSurface extends IUnknown
{
    public final native int LockRect(final int p0, final int p1, final int p2, final int p3, final int p4, final PixelBuffer p5);
    
    IDirect3DSurface(final jaa jaa) {
        super(jaa);
    }
    
    public final native boolean UnlockRect();
}
