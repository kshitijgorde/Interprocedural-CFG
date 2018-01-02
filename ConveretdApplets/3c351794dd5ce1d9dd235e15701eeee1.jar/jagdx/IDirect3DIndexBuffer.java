// 
// Decompiled by Procyon v0.5.30
// 

package jagdx;

import jaclib.peer.jaa;
import jaclib.peer.IUnknown;

public class IDirect3DIndexBuffer extends IUnknown
{
    public final native int Unlock();
    
    public final native int Lock(final int p0, final int p1, final int p2, final GeometryBuffer p3);
    
    private final native boolean _Update(final long p0, final int p1, final int p2);
    
    @Override
    protected final long a() {
        long a;
        try {
            a = super.a();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return a;
    }
    
    IDirect3DIndexBuffer(final jaa jaa) {
        super(jaa);
    }
}
