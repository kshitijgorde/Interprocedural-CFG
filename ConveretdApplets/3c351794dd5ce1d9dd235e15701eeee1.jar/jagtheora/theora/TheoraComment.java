// 
// Decompiled by Procyon v0.5.30
// 

package jagtheora.theora;

import jagtheora.misc.SimplePeer;

public class TheoraComment extends SimplePeer
{
    private final native void init();
    
    public TheoraComment() {
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
}
