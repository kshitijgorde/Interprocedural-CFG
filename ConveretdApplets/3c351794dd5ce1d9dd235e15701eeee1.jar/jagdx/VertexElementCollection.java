// 
// Decompiled by Procyon v0.5.30
// 

package jagdx;

import jaclib.peer.jaa;
import jaclib.peer.mfa;

public class VertexElementCollection extends mfa
{
    public final native void finish();
    
    private final native void init();
    
    public VertexElementCollection(final jaa jaa) {
        super(jaa);
        try {
            this.init();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final native void addElement(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    public final native void reset();
}
