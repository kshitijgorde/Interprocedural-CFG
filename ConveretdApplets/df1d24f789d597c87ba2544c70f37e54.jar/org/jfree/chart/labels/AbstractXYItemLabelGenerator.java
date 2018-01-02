// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.labels;

import org.jfree.util.ObjectUtils;
import java.text.MessageFormat;
import org.jfree.data.XYDataset;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.io.Serializable;

public class AbstractXYItemLabelGenerator implements Cloneable, Serializable
{
    private String formatString;
    private NumberFormat xFormat;
    private DateFormat xDateFormat;
    private NumberFormat yFormat;
    private DateFormat yDateFormat;
    
    protected AbstractXYItemLabelGenerator() {
        this("{2}", NumberFormat.getNumberInstance(), NumberFormat.getNumberInstance());
    }
    
    protected AbstractXYItemLabelGenerator(final String formatString, final NumberFormat xFormat, final NumberFormat yFormat) {
        if (formatString == null) {
            throw new IllegalArgumentException("Null 'formatString' argument.");
        }
        if (xFormat == null) {
            throw new IllegalArgumentException("Null 'xFormat' argument.");
        }
        if (yFormat == null) {
            throw new IllegalArgumentException("Null 'yFormat' argument.");
        }
        this.formatString = formatString;
        this.xFormat = xFormat;
        this.yFormat = yFormat;
    }
    
    protected AbstractXYItemLabelGenerator(final String formatString, final DateFormat xFormat, final NumberFormat yFormat) {
        this(formatString, NumberFormat.getInstance(), yFormat);
        this.xDateFormat = xFormat;
    }
    
    protected AbstractXYItemLabelGenerator(final String formatString, final DateFormat xFormat, final DateFormat yFormat) {
        this(formatString, NumberFormat.getInstance(), NumberFormat.getInstance());
        this.xDateFormat = xFormat;
        this.yDateFormat = yFormat;
    }
    
    public String getFormatString() {
        return this.formatString;
    }
    
    public NumberFormat getXFormat() {
        return this.xFormat;
    }
    
    public DateFormat getXDateFormat() {
        return this.xDateFormat;
    }
    
    public NumberFormat getYFormat() {
        return this.yFormat;
    }
    
    public DateFormat getYDateFormat() {
        return this.yDateFormat;
    }
    
    public String generateLabelString(final XYDataset dataset, final int series, final int item) {
        String result = null;
        final Object[] items = this.createItemArray(dataset, series, item);
        result = MessageFormat.format(this.formatString, items);
        return result;
    }
    
    protected Object[] createItemArray(final XYDataset dataset, final int series, final int item) {
        final Object[] result = { dataset.getSeriesName(series), null, null };
        final Number x = dataset.getXValue(series, item);
        if (this.xDateFormat != null) {
            result[1] = this.xDateFormat.format(x);
        }
        else {
            result[1] = this.xFormat.format(x);
        }
        final Number y = dataset.getYValue(series, item);
        if (this.yDateFormat != null) {
            result[2] = this.yDateFormat.format(y);
        }
        else {
            result[2] = this.yFormat.format(y);
        }
        return result;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AbstractXYItemLabelGenerator) {
            final AbstractXYItemLabelGenerator generator = (AbstractXYItemLabelGenerator)obj;
            return this.formatString.equals(generator.formatString) && ObjectUtils.equal(this.xFormat, generator.xFormat) && ObjectUtils.equal(this.xDateFormat, generator.xDateFormat) && ObjectUtils.equal(this.yFormat, generator.yFormat) && ObjectUtils.equal(this.yDateFormat, generator.yDateFormat);
        }
        return false;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final AbstractXYItemLabelGenerator clone = (AbstractXYItemLabelGenerator)super.clone();
        if (this.xFormat != null) {
            clone.xFormat = (NumberFormat)this.xFormat.clone();
        }
        if (this.yFormat != null) {
            clone.yFormat = (NumberFormat)this.yFormat.clone();
        }
        return clone;
    }
}
