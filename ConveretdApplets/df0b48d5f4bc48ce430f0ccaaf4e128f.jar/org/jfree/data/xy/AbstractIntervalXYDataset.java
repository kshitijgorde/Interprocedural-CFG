// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.xy;

public abstract class AbstractIntervalXYDataset extends AbstractXYDataset implements IntervalXYDataset
{
    public double getStartXValue(final int series, final int item) {
        double result = Double.NaN;
        final Number x = this.getStartX(series, item);
        if (x != null) {
            result = x.doubleValue();
        }
        return result;
    }
    
    public double getEndXValue(final int series, final int item) {
        double result = Double.NaN;
        final Number x = this.getEndX(series, item);
        if (x != null) {
            result = x.doubleValue();
        }
        return result;
    }
    
    public double getStartYValue(final int series, final int item) {
        double result = Double.NaN;
        final Number y = this.getStartY(series, item);
        if (y != null) {
            result = y.doubleValue();
        }
        return result;
    }
    
    public double getEndYValue(final int series, final int item) {
        double result = Double.NaN;
        final Number y = this.getEndY(series, item);
        if (y != null) {
            result = y.doubleValue();
        }
        return result;
    }
}
