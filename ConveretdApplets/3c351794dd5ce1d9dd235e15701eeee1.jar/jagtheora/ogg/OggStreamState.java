// 
// Decompiled by Procyon v0.5.30
// 

package jagtheora.ogg;

import jagtheora.misc.SimplePeer;

public class OggStreamState extends SimplePeer
{
    public final native boolean resetSerialNo(final int p0);
    
    public final native int packetOut();
    
    public final native int packetPeek(final OggPacket p0);
    
    @Override
    protected final native void clear();
    
    private final native boolean init(final int p0);
    
    public final native int packetOut(final OggPacket p0);
    
    public final native boolean isEOS();
    
    public final native boolean reset();
    
    public final native boolean pageIn(final OggPage p0);
    
    public OggStreamState(final int n) {
        try {
            if (!this.init(n)) {
                throw new IllegalStateException();
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final native int packetPeek();
}
