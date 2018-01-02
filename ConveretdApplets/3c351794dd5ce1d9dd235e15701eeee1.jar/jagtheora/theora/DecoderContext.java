// 
// Decompiled by Procyon v0.5.30
// 

package jagtheora.theora;

import jagtheora.ogg.OggPacket;
import jagtheora.misc.SimplePeer;

public class DecoderContext extends SimplePeer
{
    public DecoderContext(final TheoraInfo theoraInfo, final SetupInfo setupInfo) {
        try {
            this.init(theoraInfo, setupInfo);
            if (this.b()) {
                throw new IllegalArgumentException("");
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final native int decodePacketIn(final OggPacket p0, final GranulePos p1);
    
    @Override
    protected final native void clear();
    
    public final native int decodeFrame(final Frame p0);
    
    public final native int setPostProcessingLevel(final int p0);
    
    private final native void init(final TheoraInfo p0, final SetupInfo p1);
    
    public final native double granuleTime(final GranulePos p0);
    
    public final native long granuleFrame(final GranulePos p0);
    
    public final native int getMaxPostProcessingLevel();
    
    public final native int setGranulePosition(final long p0);
}
