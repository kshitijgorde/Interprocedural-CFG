// 
// Decompiled by Procyon v0.5.30
// 

package jagtheora.vorbis;

import jagtheora.misc.SimplePeer;

public class DSPState extends SimplePeer
{
    public DSPState(final VorbisInfo vorbisInfo) {
        try {
            this.init(vorbisInfo);
            if (this.b()) {
                throw new IllegalStateException();
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final native void blockIn(final VorbisBlock p0);
    
    @Override
    protected final native void clear();
    
    public final native float[][] pcmOut(final int p0);
    
    public final native double granuleTime();
    
    public final native void read(final int p0);
    
    private final native void init(final VorbisInfo p0);
}
