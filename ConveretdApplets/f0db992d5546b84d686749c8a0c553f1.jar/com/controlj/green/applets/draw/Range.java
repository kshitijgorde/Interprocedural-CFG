// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets.draw;

public abstract class Range
{
    private double valueMin;
    private int pixelMin;
    private double valueMax;
    private int pixelMax;
    private double scale;
    private double valueShift;
    private boolean valueLocked;
    
    public Range() {
        this.valueMin = -1.0;
        this.pixelMin = -1;
        this.valueMax = 0.0;
        this.pixelMax = 0;
        this.scale = 1.0;
        this.valueShift = 0.0;
        this.valueLocked = false;
    }
    
    public void syncScale(final Range range) {
        if (!this.isValueLocked()) {
            this.valueMin = range.valueMin;
            this.pixelMin = range.pixelMin;
            this.valueMax = range.valueMax;
            this.pixelMax = range.pixelMax;
            this.scale = range.scale;
        }
    }
    
    public double getValueMin() {
        return this.valueMin;
    }
    
    public void setValues(final double valueMin, final double valueMax) {
        if (!this.isValueLocked()) {
            this.valueMax = valueMax;
            this.valueMin = valueMin;
            this.recalcScale();
        }
    }
    
    public void setValueMin(final double valueMin) {
        if (!this.isValueLocked()) {
            this.valueMin = valueMin;
            this.recalcScale();
        }
    }
    
    public double getValueMax() {
        return this.valueMax;
    }
    
    public void setValueMax(final double valueMax) {
        if (!this.isValueLocked()) {
            this.valueMax = valueMax;
            this.recalcScale();
        }
    }
    
    public double getValueRange() {
        return this.valueMax - this.valueMin;
    }
    
    public double getValueShift() {
        return this.valueShift;
    }
    
    public void setValueShift(final double valueShift) {
        if (!this.isValueLocked()) {
            this.valueShift = valueShift;
        }
    }
    
    public boolean isValueLocked() {
        return this.valueLocked;
    }
    
    public void setValueLocked(final boolean valueLocked) {
        this.valueLocked = valueLocked;
    }
    
    public int getPixelMin() {
        return this.pixelMin;
    }
    
    public void setPixels(final int pixelMin, final int pixelMax) {
        this.pixelMin = pixelMin;
        this.pixelMax = pixelMax;
        this.recalcScale();
    }
    
    public void setPixelMin(final int pixelMin) {
        this.pixelMin = pixelMin;
        this.recalcScale();
    }
    
    public int getPixelMax() {
        return this.pixelMax;
    }
    
    public void setPixelMax(final int pixelMax) {
        this.pixelMax = pixelMax;
        this.recalcScale();
    }
    
    public int getPixelRange() {
        return this.pixelMax - this.pixelMin;
    }
    
    public double getScale() {
        return this.scale;
    }
    
    private void recalcScale() {
        this.scale = this.getPixelRange() / this.getValueRange();
    }
    
    public abstract int getPixelFromValue(final double p0);
    
    public abstract double getValueFromPixel(final int p0);
    
    public abstract double getValueRangeOfPixelLength(final int p0);
    
    public abstract double getPixelLengthOfValueRange(final int p0);
}
