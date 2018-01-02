// 
// Decompiled by Procyon v0.5.30
// 

package ManPack;

import jclass.bwt.JCAdjustmentEvent;
import jclass.bwt.JCAdjustmentListener;

class Applet1_speedSlider_adjustmentAdapter implements JCAdjustmentListener
{
    Applet1 adaptee;
    
    Applet1_speedSlider_adjustmentAdapter(final Applet1 adaptee) {
        this.adaptee = adaptee;
    }
    
    public void adjustmentValueChanged(final JCAdjustmentEvent e) {
        this.adaptee.speedSlider_adjustmentValueChanged(e);
    }
}
