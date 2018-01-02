// 
// Decompiled by Procyon v0.5.30
// 

package jagdx;

import jaclib.peer.jaa;
import jaclib.peer.mfa;

public class D3DLIGHT extends mfa
{
    public final native void SetSpotParams(final float p0, final float p1, final float p2);
    
    public final native void SetPosition(final float p0, final float p1, final float p2);
    
    public final native void SetDirection(final float p0, final float p1, final float p2);
    
    public final native void SetAttenuation(final float p0, final float p1, final float p2);
    
    public final native void SetAmbient(final float p0, final float p1, final float p2, final float p3);
    
    public D3DLIGHT(final jaa jaa) {
        super(jaa);
        try {
            this.Init();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final native void SetRange(final float p0);
    
    public final native void SetDiffuse(final float p0, final float p1, final float p2, final float p3);
    
    private final native void Init();
    
    public final native void SetSpecular(final float p0, final float p1, final float p2, final float p3);
    
    public final native void SetType(final int p0);
}
