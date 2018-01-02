// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.slider;

import javax.swing.JSlider;
import org.xidget.IXidget;
import org.xidget.ifeature.slider.ISliderWidgetFeature;

public class JSliderWidgetFeature implements ISliderWidgetFeature
{
    private IXidget xidget;
    private double y0;
    private double y1;
    private int precision;
    
    public JSliderWidgetFeature(final IXidget xidget) {
        this.xidget = xidget;
        this.precision = 1;
    }
    
    @Override
    public void setPrecision(final int n) {
        this.precision = (int)Math.pow(10.0, n);
    }
    
    @Override
    public double getValue() {
        return Math.round((this.xidget.getFeature(JSlider.class).getValue() * (this.y1 - this.y0) / 1000000.0 + this.y0) * this.precision) / this.precision;
    }
    
    @Override
    public void setValue(final double n) {
        this.xidget.getFeature(JSlider.class).setValue((int)Math.round((n - this.y0) / (this.y1 - this.y0) * 1000000.0 * this.precision) / this.precision);
    }
    
    @Override
    public void setMaximum(final double y1) {
        this.y1 = y1;
        this.xidget.getFeature(JSlider.class).setMaximum(1000000);
    }
    
    @Override
    public double getMaximum() {
        return this.y1;
    }
    
    @Override
    public void setMinimum(final double y0) {
        this.y0 = y0;
        this.xidget.getFeature(JSlider.class).setMinimum(0);
    }
    
    @Override
    public double getMinimum() {
        return this.y0;
    }
}
