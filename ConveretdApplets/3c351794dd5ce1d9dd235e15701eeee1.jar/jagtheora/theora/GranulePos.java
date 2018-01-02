// 
// Decompiled by Procyon v0.5.30
// 

package jagtheora.theora;

import jagtheora.misc.SimplePeer;

public class GranulePos extends SimplePeer
{
    public long position;
    
    @Override
    protected final native void clear();
    
    private static final native void init();
    
    static {
        init();
    }
}
