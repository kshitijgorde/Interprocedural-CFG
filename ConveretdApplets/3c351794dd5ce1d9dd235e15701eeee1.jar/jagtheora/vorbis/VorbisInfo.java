// 
// Decompiled by Procyon v0.5.30
// 

package jagtheora.vorbis;

import jagtheora.ogg.OggPacket;
import jagtheora.misc.SimplePeer;

public class VorbisInfo extends SimplePeer
{
    public int channels;
    public int rate;
    
    public final native int headerIn(final VorbisComment p0, final OggPacket p1);
    
    @Override
    protected final native void clear();
    
    private static final native void initFields();
    
    private final native void init();
    
    public VorbisInfo() {
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
    
    static {
        initFields();
    }
}
