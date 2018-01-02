// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.progress;

import org.xidget.ifeature.slider.ISliderWidgetFeature;
import org.xidget.IXidget;
import org.xidget.ifeature.model.ISingleValueWidgetFeature;

public class JProgressBarValueWidgetFeature implements ISingleValueWidgetFeature
{
    private IXidget xidget;
    
    public JProgressBarValueWidgetFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void setValue(final Object o) {
        final ISliderWidgetFeature sliderWidgetFeature = this.xidget.getFeature(ISliderWidgetFeature.class);
        if (o instanceof Number) {
            sliderWidgetFeature.setValue(((Number)o).doubleValue());
        }
        else {
            sliderWidgetFeature.setValue((int)Double.parseDouble(o.toString()));
        }
    }
    
    @Override
    public Object getValue() {
        return this.xidget.getFeature(ISliderWidgetFeature.class).getValue();
    }
}
