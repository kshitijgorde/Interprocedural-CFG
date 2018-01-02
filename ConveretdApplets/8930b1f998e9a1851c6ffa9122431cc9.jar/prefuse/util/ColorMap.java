// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util;

public class ColorMap
{
    private int[] palette;
    private double minValue;
    private double maxValue;
    
    public ColorMap(final int[] palette, final double minValue, final double maxValue) {
        this.palette = palette;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
    
    public int getColor(final double n) {
        if (n < this.minValue) {
            return this.palette[0];
        }
        if (n >= this.maxValue) {
            return this.palette[this.palette.length - 1];
        }
        return this.palette[(int)(this.palette.length * (n - this.minValue) / (this.maxValue - this.minValue))];
    }
    
    public int[] getColorPalette() {
        return this.palette;
    }
    
    public void setColorPalette(final int[] palette) {
        this.palette = palette;
    }
    
    public double getMaxValue() {
        return this.maxValue;
    }
    
    public void setMaxValue(final double maxValue) {
        this.maxValue = maxValue;
    }
    
    public double getMinValue() {
        return this.minValue;
    }
    
    public void setMinValue(final double minValue) {
        this.minValue = minValue;
    }
}
