// 
// Decompiled by Procyon v0.5.30
// 

package tmp.sunw.beanbox;

import java.io.Serializable;

public class ___Hookup_153fea70c5 implements BodeListener, Serializable
{
    private PlotCanvasBean target;
    
    public void setTarget(final PlotCanvasBean target) {
        this.target = target;
    }
    
    public void changeOccured(final BodeEvent bodeEvent) {
        this.target.changeOccured(bodeEvent);
    }
}
