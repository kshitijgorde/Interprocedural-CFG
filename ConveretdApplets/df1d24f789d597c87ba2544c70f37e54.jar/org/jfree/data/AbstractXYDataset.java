// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

public abstract class AbstractXYDataset extends AbstractSeriesDataset implements XYDataset
{
    public double getX(final int series, final int item) {
        double result = Double.NaN;
        final Number x = this.getXValue(series, item);
        if (x != null) {
            result = x.doubleValue();
        }
        return result;
    }
    
    public double getY(final int series, final int item) {
        double result = Double.NaN;
        final Number y = this.getYValue(series, item);
        if (y != null) {
            result = y.doubleValue();
        }
        return result;
    }
}
