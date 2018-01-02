// 
// Decompiled by Procyon v0.5.30
// 

package jagtheora.theora;

import jagtheora.misc.SimplePeer;

public class Frame extends SimplePeer
{
    public int[] pixels;
    public int a;
    public int b;
    
    private static final native void init();
    
    public Frame(final int a, final int b) {
        try {
            this.b = b;
            this.a = a;
            this.pixels = new int[this.a * this.b];
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    protected final native void clear();
    
    static {
        init();
    }
}
