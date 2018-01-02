// 
// Decompiled by Procyon v0.5.30
// 

package jagtheora.theora;

import jagtheora.misc.SimplePeer;

public class TheoraInfo extends SimplePeer
{
    public int pixelFormat;
    public int colourSpace;
    public byte versionMinor;
    public int frameHeight;
    public int picX;
    public int frameWidth;
    public int picY;
    public byte versionSubMinor;
    public int aspectNumerator;
    public int picHeight;
    public int aspectDenominator;
    public int fpsDenominator;
    public int fpsNumerator;
    public byte versionMajor;
    public int picWidth;
    
    @Override
    protected final native void clear();
    
    private final native void init();
    
    private static final native void initFields();
    
    public TheoraInfo() {
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
