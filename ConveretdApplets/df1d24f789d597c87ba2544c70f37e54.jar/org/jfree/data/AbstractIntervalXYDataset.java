// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

public abstract class AbstractIntervalXYDataset extends AbstractXYDataset implements IntervalXYDataset
{
    public double getStartX(final int series, final int item) {
        double result = Double.NaN;
        final Number x = this.getStartXValue(series, item);
        if (x != null) {
            result = x.doubleValue();
        }
        return result;
    }
    
    public double getEndX(final int series, final int item) {
        double result = Double.NaN;
        final Number x = this.getEndXValue(series, item);
        if (x != null) {
            result = x.doubleValue();
        }
        return result;
    }
    
    public double getStartY(final int series, final int item) {
        double result = Double.NaN;
        final Number y = this.getStartYValue(series, item);
        if (y != null) {
            result = y.doubleValue();
        }
        return result;
    }
    
    public double getEndY(final int series, final int item) {
        double result = Double.NaN;
        final Number y = this.getEndYValue(series, item);
        if (y != null) {
            result = y.doubleValue();
        }
        return result;
    }
}
