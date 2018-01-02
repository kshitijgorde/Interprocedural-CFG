// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.spinner;

import javax.swing.SpinnerModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import org.xidget.IXidget;
import org.xidget.ifeature.slider.ISliderWidgetFeature;

public class JSpinnerWidgetFeature implements ISliderWidgetFeature
{
    private IXidget xidget;
    private double y0;
    private double y1;
    private int precision;
    
    public JSpinnerWidgetFeature(final IXidget xidget) {
        this.xidget = xidget;
        this.precision = 1;
    }
    
    @Override
    public void setPrecision(final int n) {
        this.precision = (int)Math.pow(10.0, n);
    }
    
    @Override
    public double getValue() {
        return Math.round((this.getModel().getNumber().doubleValue() * (this.y1 - this.y0) / 1000000.0 + this.y0) * this.precision) / this.precision;
    }
    
    @Override
    public void setValue(final double n) {
        this.getModel().setValue((int)Math.round((n - this.y0) / (this.y1 - this.y0) * 1000000.0 * this.precision) / this.precision);
    }
    
    @Override
    public void setMaximum(final double y1) {
        this.y1 = y1;
        this.getModel().setMaximum(this.y1);
    }
    
    @Override
    public double getMaximum() {
        return this.y1;
    }
    
    @Override
    public void setMinimum(final double y0) {
        this.y0 = y0;
        this.getModel().setMinimum(this.y0);
    }
    
    @Override
    public double getMinimum() {
        return this.y0;
    }
    
    private SpinnerNumberModel getModel() {
        final JSpinner spinner = this.xidget.getFeature(JSpinner.class);
        final SpinnerModel model = spinner.getModel();
        if (model instanceof SpinnerNumberModel) {
            return (SpinnerNumberModel)model;
        }
        spinner.setModel(new SpinnerNumberModel());
        return this.getModel();
    }
}
