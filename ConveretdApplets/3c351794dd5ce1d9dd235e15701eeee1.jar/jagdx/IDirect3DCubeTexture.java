// 
// Decompiled by Procyon v0.5.30
// 

package jagdx;

import jaclib.peer.jaa;

public class IDirect3DCubeTexture extends IDirect3DBaseTexture
{
    IDirect3DCubeTexture(final jaa jaa) {
        super(jaa);
    }
    
    public final native boolean UnlockRect(final int p0, final int p1);
    
    public final native int LockRect(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final PixelBuffer p7);
}
