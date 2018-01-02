// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets.draw;

public class HorizontalRange extends Range
{
    public int getPixelFromValue(double value) {
        value += this.getValueShift();
        return this.getPixelMin() + (int)((value - this.getValueMin()) * this.getScale());
    }
    
    public double getValueFromPixel(final int pixelLocation) {
        return this.getValueMin() + (pixelLocation - this.getPixelMin()) / this.getScale() - this.getValueShift();
    }
    
    public double getValueRangeOfPixelLength(final int pixelRange) {
        return this.getValueFromPixel(this.getPixelMin() + pixelRange) - this.getValueMin() + this.getValueShift();
    }
    
    public double getPixelLengthOfValueRange(final int valueRange) {
        return this.getPixelFromValue(valueRange + this.getValueMin() - this.getValueShift()) - this.getPixelMin();
    }
}
