// 
// Decompiled by Procyon v0.5.30
// 

package tmp.sunw.beanbox;

import java.io.Serializable;

public class ___Hookup_153fea77ae implements BodeListener, Serializable
{
    private TimeDomainBean target;
    
    public void setTarget(final TimeDomainBean target) {
        this.target = target;
    }
    
    public void changeOccured(final BodeEvent bodeEvent) {
        this.target.changeOccured(bodeEvent);
    }
}
