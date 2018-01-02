// 
// Decompiled by Procyon v0.5.30
// 

package tmp.sunw.beanbox;

import java.io.Serializable;

public class ___Hookup_153fea8c94 implements MyMouseListener, Serializable
{
    private MyMouseBean target;
    
    public void setTarget(final MyMouseBean target) {
        this.target = target;
    }
    
    public void mouseOccured(final MyMouseEvent myMouseEvent) {
        this.target.mouseOccured(myMouseEvent);
    }
}
