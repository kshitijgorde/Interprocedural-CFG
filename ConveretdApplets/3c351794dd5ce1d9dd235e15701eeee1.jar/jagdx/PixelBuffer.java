// 
// Decompiled by Procyon v0.5.30
// 

package jagdx;

import jaclib.peer.jaa;
import jaclib.memory.Buffer;
import jaclib.peer.mfa;

public class PixelBuffer extends mfa implements Buffer
{
    @Override
    public final long getAddress() {
        long n;
        try {
            n = 0L;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return n;
    }
    
    private final native void putub(final byte[] p0, final int p1, final int p2, final int p3);
    
    public PixelBuffer(final jaa jaa) {
        super(jaa);
        try {
            this.init();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private final native void puti(final int[] p0, final int p1, final int p2, final int p3);
    
    public final void a(final int[] array, final int n, final int n2, final int n3) {
        try {
            if ((~n2 > -1 | (array == null | -1 < ~n | ~array.length > ~(n + n3))) || ~(n3 * 4 + n2) < ~this.getSize()) {
                throw new jc();
            }
            this.puti(array, n, n2, n3);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final void a(final byte[] array, final int n, final int n2, final int n3) {
        try {
            if ((~array.length > ~(n + n3) | (0 > n | array == null) | 0 > n2) || ~this.getSize() > ~(n2 - -n3)) {
                throw new jc();
            }
            this.putub(array, n, n2, n3);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final native int getSize();
    
    public final native int getRowPitch();
    
    public final native int getSlicePitch();
    
    private final native void init();
    
    private final native void getub(final byte[] p0, final int p1, final int p2, final int p3);
    
    private final native void geti(final int[] p0, final int p1, final int p2, final int p3);
    
    public final void b(final int[] array, final int n, final int n2, final int n3) {
        try {
            if ((array == null | 0 > n | ~(n + n3) < ~array.length | -1 < ~n2) || this.getSize() < 4 * n3 + n2) {
                throw new jc();
            }
            this.geti(array, n, n2, n3);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
