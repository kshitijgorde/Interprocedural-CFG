// 
// Decompiled by Procyon v0.5.30
// 

package jagtheora.vorbis;

import jagtheora.ogg.OggPacket;
import jagtheora.misc.SimplePeer;

public class VorbisBlock extends SimplePeer
{
    public final native int synthesis(final OggPacket p0);
    
    private final native void init(final DSPState p0);
    
    @Override
    protected final native void clear();
    
    public VorbisBlock(final DSPState dspState) {
        try {
            this.init(dspState);
            if (this.b()) {
                throw new IllegalStateException();
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
