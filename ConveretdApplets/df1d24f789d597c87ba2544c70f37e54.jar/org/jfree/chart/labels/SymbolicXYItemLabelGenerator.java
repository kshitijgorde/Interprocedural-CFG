// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.labels;

import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.XisSymbolic;
import org.jfree.data.YisSymbolic;
import org.jfree.data.XYDataset;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class SymbolicXYItemLabelGenerator implements XYLabelGenerator, XYToolTipGenerator, Cloneable, PublicCloneable, Serializable
{
    public String generateToolTip(final XYDataset data, final int series, final int item) {
        String y;
        if (data instanceof YisSymbolic) {
            y = ((YisSymbolic)data).getYSymbolicValue(series, item);
        }
        else {
            final Number n = data.getYValue(series, item);
            y = Double.toString(round(n.doubleValue(), 2));
        }
        String x;
        if (data instanceof XisSymbolic) {
            x = ((XisSymbolic)data).getXSymbolicValue(series, item);
        }
        else if (data instanceof TimeSeriesCollection) {
            final RegularTimePeriod p = ((TimeSeriesCollection)data).getSeries(series).getTimePeriod(item);
            x = p.toString();
        }
        else {
            final Number n = data.getXValue(series, item);
            x = Double.toString(round(n.doubleValue(), 2));
        }
        return "X: " + x + ", Y: " + y;
    }
    
    public String generateLabel(final XYDataset dataset, final int series, final int category) {
        return null;
    }
    
    private static double round(final double value, final int nb) {
        if (nb <= 0) {
            return Math.floor(value + 0.5);
        }
        final double p = Math.pow(10.0, nb);
        final double tempval = Math.floor(value * p + 0.5);
        return tempval / p;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    public boolean equals(final Object o) {
        return o == this || o instanceof SymbolicXYItemLabelGenerator;
    }
}
