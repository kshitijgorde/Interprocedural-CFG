// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.labels;

import java.util.Date;
import org.jfree.data.HighLowDataset;
import org.jfree.data.XYDataset;
import java.text.NumberFormat;
import java.text.DateFormat;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class HighLowItemLabelGenerator implements XYLabelGenerator, XYToolTipGenerator, Cloneable, PublicCloneable, Serializable
{
    private DateFormat dateFormatter;
    private NumberFormat numberFormatter;
    
    public HighLowItemLabelGenerator() {
        this(DateFormat.getInstance(), NumberFormat.getInstance());
    }
    
    public HighLowItemLabelGenerator(final DateFormat dateFormatter, final NumberFormat numberFormatter) {
        if (dateFormatter == null) {
            throw new IllegalArgumentException("Null 'dateFormatter' argument.");
        }
        if (numberFormatter == null) {
            throw new IllegalArgumentException("Null 'numberFormatter' argument.");
        }
        this.dateFormatter = dateFormatter;
        this.numberFormatter = numberFormatter;
    }
    
    public String generateToolTip(final XYDataset dataset, final int series, final int item) {
        String result = null;
        if (dataset instanceof HighLowDataset) {
            final HighLowDataset d = (HighLowDataset)dataset;
            final Number high = d.getHighValue(series, item);
            final Number low = d.getLowValue(series, item);
            final Number open = d.getOpenValue(series, item);
            final Number close = d.getCloseValue(series, item);
            final Number x = d.getXValue(series, item);
            result = d.getSeriesName(series);
            if (x != null) {
                final Date date = new Date(x.longValue());
                result = result + "--> Date=" + this.dateFormatter.format(date);
                if (high != null) {
                    result = result + " High=" + this.numberFormatter.format(high.doubleValue());
                }
                if (low != null) {
                    result = result + " Low=" + this.numberFormatter.format(low.doubleValue());
                }
                if (open != null) {
                    result = result + " Open=" + this.numberFormatter.format(open.doubleValue());
                }
                if (close != null) {
                    result = result + " Close=" + this.numberFormatter.format(close.doubleValue());
                }
            }
        }
        return result;
    }
    
    public String generateLabel(final XYDataset dataset, final int series, final int category) {
        return null;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final HighLowItemLabelGenerator clone = (HighLowItemLabelGenerator)super.clone();
        if (this.dateFormatter != null) {
            clone.dateFormatter = (DateFormat)this.dateFormatter.clone();
        }
        if (this.numberFormatter != null) {
            clone.numberFormatter = (NumberFormat)this.numberFormatter.clone();
        }
        return clone;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof HighLowItemLabelGenerator) {
            final HighLowItemLabelGenerator generator = (HighLowItemLabelGenerator)o;
            return this.dateFormatter.equals(generator.dateFormatter) && this.numberFormatter.equals(generator.numberFormatter);
        }
        return false;
    }
}
