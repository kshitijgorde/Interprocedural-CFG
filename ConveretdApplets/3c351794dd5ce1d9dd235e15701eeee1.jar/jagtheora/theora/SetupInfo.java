// 
// Decompiled by Procyon v0.5.30
// 

package jagtheora.theora;

import jagtheora.ogg.OggPacket;
import jagtheora.misc.SimplePeer;

public class SetupInfo extends SimplePeer
{
    @Override
    protected final native void clear();
    
    public final native int decodeHeader(final TheoraInfo p0, final TheoraComment p1, final OggPacket p2);
}
