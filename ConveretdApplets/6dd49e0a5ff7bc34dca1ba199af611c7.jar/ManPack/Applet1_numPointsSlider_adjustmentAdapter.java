// 
// Decompiled by Procyon v0.5.30
// 

package ManPack;

import jclass.bwt.JCAdjustmentEvent;
import jclass.bwt.JCAdjustmentListener;

class Applet1_numPointsSlider_adjustmentAdapter implements JCAdjustmentListener
{
    Applet1 adaptee;
    
    Applet1_numPointsSlider_adjustmentAdapter(final Applet1 adaptee) {
        this.adaptee = adaptee;
    }
    
    public void adjustmentValueChanged(final JCAdjustmentEvent e) {
        this.adaptee.numPointsSlider_adjustmentValueChanged(e);
    }
}
