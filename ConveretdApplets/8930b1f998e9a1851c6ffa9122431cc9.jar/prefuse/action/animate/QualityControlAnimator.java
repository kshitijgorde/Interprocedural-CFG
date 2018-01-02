// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.animate;

import prefuse.action.Action;

public class QualityControlAnimator extends Action
{
    public void run(final double n) {
        if (this.m_vis == null) {
            return;
        }
        if (n == 0.0 || n == 1.0) {
            final boolean highQuality = n >= 1.0;
            for (int i = 0; i < this.m_vis.getDisplayCount(); ++i) {
                this.m_vis.getDisplay(i).setHighQuality(highQuality);
            }
            this.qualityValue(highQuality);
        }
    }
    
    protected void qualityValue(final boolean b) {
    }
}
