// 
// Decompiled by Procyon v0.5.30
// 

package jagdx;

import jaclib.peer.jaa;
import jaclib.peer.IUnknown;

public class IDirect3DEventQuery extends IUnknown
{
    IDirect3DEventQuery(final jaa jaa) {
        super(jaa);
    }
    
    public final native int IsSignaled();
    
    public final native int Issue();
}
