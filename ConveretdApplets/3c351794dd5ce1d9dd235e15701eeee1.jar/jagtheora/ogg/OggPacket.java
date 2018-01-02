// 
// Decompiled by Procyon v0.5.30
// 

package jagtheora.ogg;

import jagtheora.misc.SimplePeer;

public class OggPacket extends SimplePeer
{
    public final native boolean isVorbis();
    
    public final native boolean isTheora();
    
    public final native byte[] getData();
    
    @Override
    protected final native void clear();
    
    public final native int isHeader();
    
    public final native int isKeyFrame();
}
