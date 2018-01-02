// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.labels;

import org.jfree.util.ObjectUtils;
import org.jfree.data.XYZDataset;
import java.text.DateFormat;
import java.text.NumberFormat;

public class StandardXYZToolTipGenerator extends StandardXYToolTipGenerator implements XYZToolTipGenerator
{
    public static final String DEFAULT_TOOL_TIP_FORMAT = "{0}: ({1}, {2}, {3})";
    private NumberFormat zFormat;
    private DateFormat zDateFormat;
    
    public StandardXYZToolTipGenerator() {
        this("{0}: ({1}, {2}, {3})", NumberFormat.getNumberInstance(), NumberFormat.getNumberInstance(), NumberFormat.getNumberInstance());
    }
    
    public StandardXYZToolTipGenerator(final String formatString, final NumberFormat xFormat, final NumberFormat yFormat, final NumberFormat zFormat) {
        super(formatString, xFormat, yFormat);
        if (zFormat == null) {
            throw new IllegalArgumentException("Null 'zFormat' argument.");
        }
        this.zFormat = zFormat;
    }
    
    public StandardXYZToolTipGenerator(final String formatString, final DateFormat xFormat, final DateFormat yFormat, final DateFormat zFormat) {
        super(formatString, xFormat, yFormat);
        if (zFormat == null) {
            throw new IllegalArgumentException("Null 'zFormat' argument.");
        }
        this.zDateFormat = zFormat;
    }
    
    public NumberFormat getZFormat() {
        return this.zFormat;
    }
    
    public DateFormat getZDateFormat() {
        return this.zDateFormat;
    }
    
    public String generateToolTip(final XYZDataset data, final int series, final int item) {
        String result = data.getSeriesName(series) + ": ";
        final Number x = data.getXValue(series, item);
        result = result + "x: " + this.getXFormat().format(x);
        final Number y = data.getYValue(series, item);
        result = result + "y: " + this.getYFormat().format(y);
        final Number z = data.getZValue(series, item);
        if (z != null) {
            result = result + ", z: " + this.zFormat.format(z);
        }
        else {
            result += ", z: null";
        }
        return result;
    }
    
    protected Object[] createItemArray(final XYZDataset dataset, final int series, final int item) {
        final Object[] result = new Object[4];
        result[0] = dataset.getSeriesName(series);
        final Number x = dataset.getXValue(series, item);
        final DateFormat xf = this.getXDateFormat();
        if (xf != null) {
            result[1] = xf.format(x);
        }
        else {
            result[1] = this.getXFormat().format(x);
        }
        final Number y = dataset.getYValue(series, item);
        final DateFormat yf = this.getYDateFormat();
        if (yf != null) {
            result[2] = yf.format(y);
        }
        else {
            result[2] = this.getYFormat().format(y);
        }
        final Number z = dataset.getZValue(series, item);
        if (this.zDateFormat != null) {
            result[3] = this.zDateFormat.format(z);
        }
        else {
            result[3] = this.zFormat.format(z);
        }
        return result;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StandardXYZToolTipGenerator && super.equals(obj)) {
            final StandardXYZToolTipGenerator generator = (StandardXYZToolTipGenerator)obj;
            final boolean b0 = ObjectUtils.equal(this.zFormat, generator.zFormat);
            final boolean b2 = ObjectUtils.equal(this.zDateFormat, generator.zDateFormat);
            return b0 && b2;
        }
        return false;
    }
}
