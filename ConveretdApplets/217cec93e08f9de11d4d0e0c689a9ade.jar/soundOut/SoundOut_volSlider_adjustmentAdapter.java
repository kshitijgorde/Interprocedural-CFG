// 
// Decompiled by Procyon v0.5.30
// 

package soundOut;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

class SoundOut_volSlider_adjustmentAdapter implements AdjustmentListener
{
    SoundOut adaptee;
    
    SoundOut_volSlider_adjustmentAdapter(final SoundOut adaptee) {
        this.adaptee = adaptee;
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.adaptee.volSlider_adjustmentValueChanged(adjustmentEvent);
    }
}
