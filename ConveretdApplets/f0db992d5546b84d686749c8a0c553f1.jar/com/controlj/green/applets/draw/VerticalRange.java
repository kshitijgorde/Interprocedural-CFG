// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets.draw;

public class VerticalRange extends Range
{
    public int getPixelFromValue(double value) {
        value += this.getValueShift();
        return this.getPixelMin() + (int)((this.getValueMax() - value) * this.getScale());
    }
    
    public double getValueFromPixel(final int pixelLocation) {
        return this.getValueMax() - (pixelLocation - this.getPixelMin()) / this.getScale() - this.getValueShift();
    }
    
    public double getValueRangeOfPixelLength(final int pixelRange) {
        return this.getValueFromPixel(this.getPixelMax() - pixelRange) - this.getValueMin() + this.getValueShift();
    }
    
    public double getPixelLengthOfValueRange(final int valueRange) {
        return this.getPixelFromValue(this.getValueMax() - valueRange - this.getValueShift()) - this.getPixelMin();
    }
}
