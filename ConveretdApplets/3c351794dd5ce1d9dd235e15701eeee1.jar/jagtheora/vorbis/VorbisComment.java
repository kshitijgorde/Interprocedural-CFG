// 
// Decompiled by Procyon v0.5.30
// 

package jagtheora.vorbis;

import jagtheora.misc.SimplePeer;

public class VorbisComment extends SimplePeer
{
    public VorbisComment() {
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
    
    @Override
    protected final native void clear();
    
    private final native void init();
}
