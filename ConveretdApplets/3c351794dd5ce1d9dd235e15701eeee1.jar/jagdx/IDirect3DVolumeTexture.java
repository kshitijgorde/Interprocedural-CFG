// 
// Decompiled by Procyon v0.5.30
// 

package jagdx;

import jaclib.peer.jaa;

public class IDirect3DVolumeTexture extends IDirect3DBaseTexture
{
    public final native boolean UnlockBox(final int p0);
    
    public final native int LockBox(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final PixelBuffer p8);
    
    IDirect3DVolumeTexture(final jaa jaa) {
        super(jaa);
    }
}
