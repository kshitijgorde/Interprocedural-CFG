// 
// Decompiled by Procyon v0.5.30
// 

package jagdx;

import jaclib.memory.Source;
import jaclib.peer.jaa;
import jaclib.peer.IUnknown;

public class IDirect3DVertexBuffer extends IUnknown
{
    int b;
    
    @Override
    protected final long a() {
        long a;
        try {
            this.b = 0;
            a = super.a();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return a;
    }
    
    public final native int Unlock();
    
    private final native boolean _Update(final long p0, final int p1, final int p2, final int p3);
    
    IDirect3DVertexBuffer(final jaa jaa) {
        super(jaa);
    }
    
    public final native int Lock(final int p0, final int p1, final int p2, final GeometryBuffer p3);
    
    public final boolean a(final Source source, final int n, final int n2, final int n3, final int n4) {
        boolean update;
        try {
            if (source == null || -1 < ~n || ~n3 < ~(n + source.getSize())) {
                throw new jc("");
            }
            if (~n2 > -1 || ~(this.b + n2) > ~n3) {
                throw new jc("");
            }
            update = this._Update(n + source.getAddress(), n2, n3, n4);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return update;
    }
}
