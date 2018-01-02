// 
// Decompiled by Procyon v0.5.30
// 

package tmp.sunw.beanbox;

import java.io.Serializable;

public class ___Hookup_153fea7b5e implements BodeListener, Serializable
{
    private ControlBean target;
    
    public void setTarget(final ControlBean target) {
        this.target = target;
    }
    
    public void changeOccured(final BodeEvent bodeEvent) {
        this.target.changeOccured(bodeEvent);
    }
}
