// 
// Decompiled by Procyon v0.5.30
// 

package tmp.sunw.beanbox;

import java.io.Serializable;

public class ___Hookup_153fea7405 implements BodeListener, Serializable
{
    private SPlaneBean target;
    
    public void setTarget(final SPlaneBean target) {
        this.target = target;
    }
    
    public void changeOccured(final BodeEvent bodeEvent) {
        this.target.changeOccured(bodeEvent);
    }
}