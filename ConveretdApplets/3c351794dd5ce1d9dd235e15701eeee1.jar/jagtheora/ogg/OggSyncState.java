// 
// Decompiled by Procyon v0.5.30
// 

package jagtheora.ogg;

import jagtheora.misc.SimplePeer;

public class OggSyncState extends SimplePeer
{
    public OggSyncState() {
        try {
            this.init();
            if (this.b()) {
                throw new IllegalStateException();
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final native int pageOut(final OggPage p0);
    
    public final native boolean write(final byte[] p0, final int p1);
    
    @Override
    protected final native void clear();
    
    public final native long pageSeek(final OggPage p0);
    
    public final native boolean reset();
    
    private final native void init();
}
