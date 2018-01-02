// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.progress;

import javax.swing.JProgressBar;
import org.xidget.IXidget;
import org.xidget.ifeature.slider.ISliderWidgetFeature;

public class JProgressBarWidgetFeature implements ISliderWidgetFeature
{
    private IXidget xidget;
    private double y0;
    private double y1;
    private int precision;
    
    public JProgressBarWidgetFeature(final IXidget xidget) {
        this.xidget = xidget;
        this.precision = 1;
    }
    
    @Override
    public void setPrecision(final int n) {
        this.precision = (int)Math.pow(10.0, n);
    }
    
    @Override
    public double getValue() {
        return Math.round((this.xidget.getFeature(JProgressBar.class).getValue() * (this.y1 - this.y0) / 1000000.0 + this.y0) * this.precision) / this.precision;
    }
    
    @Override
    public void setValue(final double n) {
        this.xidget.getFeature(JProgressBar.class).setValue((int)Math.round((n - this.y0) / (this.y1 - this.y0) * 1000000.0 * this.precision) / this.precision);
    }
    
    @Override
    public void setMaximum(final double y1) {
        this.y1 = y1;
        this.xidget.getFeature(JProgressBar.class).setMaximum(1000000);
    }
    
    @Override
    public double getMaximum() {
        return this.y1;
    }
    
    @Override
    public void setMinimum(final double y0) {
        this.y0 = y0;
        this.xidget.getFeature(JProgressBar.class).setMinimum(0);
    }
    
    @Override
    public double getMinimum() {
        return this.y0;
    }
}
