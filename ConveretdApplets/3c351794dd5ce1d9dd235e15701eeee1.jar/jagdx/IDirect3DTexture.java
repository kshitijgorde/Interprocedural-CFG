// 
// Decompiled by Procyon v0.5.30
// 

package jagdx;

import jaclib.peer.jaa;

public class IDirect3DTexture extends IDirect3DBaseTexture
{
    public final native int LockRect(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final PixelBuffer p6);
    
    IDirect3DTexture(final jaa jaa) {
        super(jaa);
    }
    
    public final native boolean UnlockRect(final int p0);
}
