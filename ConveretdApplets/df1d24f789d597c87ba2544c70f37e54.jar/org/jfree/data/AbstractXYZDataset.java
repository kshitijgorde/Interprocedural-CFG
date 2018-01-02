// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

public abstract class AbstractXYZDataset extends AbstractXYDataset implements XYZDataset
{
    public double getZ(final int series, final int item) {
        double result = Double.NaN;
        final Number z = this.getZValue(series, item);
        if (z != null) {
            result = z.doubleValue();
        }
        return result;
    }
}
