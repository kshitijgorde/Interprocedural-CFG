// 
// Decompiled by Procyon v0.5.30
// 

package jagdx;

import jaclib.peer.jaa;
import jaclib.memory.Buffer;
import jaclib.peer.mfa;

public class GeometryBuffer extends mfa implements Buffer
{
    @Override
    public final native long getAddress();
    
    @Override
    public final native int getSize();
    
    private final native void init();
    
    public GeometryBuffer(final jaa jaa) {
        super(jaa);
        try {
            this.init();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private final native void putub(final byte[] p0, final int p1, final int p2, final int p3);
    
    private final native void getub(final byte[] p0, final int p1, final int p2, final int p3);
    
    @Override
    public final void a(final byte[] array, final int n, final int n2, final int n3) {
        try {
            if ((0 > n | null == array | ~array.length > ~(n + n3) | n2 < 0) || this.getSize() < n3 + n2) {
                throw new jc();
            }
            this.putub(array, n, n2, n3);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
