// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.urls;

import java.util.Date;
import org.jfree.data.xy.XYDataset;
import java.text.DateFormat;
import java.io.Serializable;

public class TimeSeriesURLGenerator implements XYURLGenerator, Serializable
{
    private static final long serialVersionUID = -9122773175671182445L;
    private DateFormat dateFormat;
    private String prefix;
    private String seriesParameterName;
    private String itemParameterName;
    
    public TimeSeriesURLGenerator() {
        this.dateFormat = DateFormat.getInstance();
        this.prefix = "index.html";
        this.seriesParameterName = "series";
        this.itemParameterName = "item";
    }
    
    public TimeSeriesURLGenerator(final DateFormat dateFormat, final String prefix, final String seriesParameterName, final String itemParameterName) {
        this.dateFormat = DateFormat.getInstance();
        this.prefix = "index.html";
        this.seriesParameterName = "series";
        this.itemParameterName = "item";
        if (dateFormat == null) {
            throw new IllegalArgumentException("Null 'dateFormat' argument.");
        }
        if (prefix == null) {
            throw new IllegalArgumentException("Null 'prefix' argument.");
        }
        if (seriesParameterName == null) {
            throw new IllegalArgumentException("Null 'seriesParameterName' argument.");
        }
        if (itemParameterName == null) {
            throw new IllegalArgumentException("Null 'itemParameterName' argument.");
        }
        this.dateFormat = (DateFormat)dateFormat.clone();
        this.prefix = prefix;
        this.seriesParameterName = seriesParameterName;
        this.itemParameterName = itemParameterName;
    }
    
    public DateFormat getDateFormat() {
        return (DateFormat)this.dateFormat.clone();
    }
    
    public String getPrefix() {
        return this.prefix;
    }
    
    public String getSeriesParameterName() {
        return this.seriesParameterName;
    }
    
    public String getItemParameterName() {
        return this.itemParameterName;
    }
    
    public String generateURL(final XYDataset dataset, final int series, final int item) {
        String result = this.prefix;
        boolean firstParameter = result.indexOf("?") == -1;
        final Comparable seriesKey = dataset.getSeriesKey(series);
        if (seriesKey != null) {
            result += (firstParameter ? "?" : "&amp;");
            result = result + this.seriesParameterName + "=" + URLUtilities.encode(seriesKey.toString(), "UTF-8");
            firstParameter = false;
        }
        final long x = (long)dataset.getXValue(series, item);
        final String xValue = this.dateFormat.format(new Date(x));
        result += (firstParameter ? "?" : "&amp;");
        result = result + this.itemParameterName + "=" + URLUtilities.encode(xValue, "UTF-8");
        return result;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TimeSeriesURLGenerator)) {
            return false;
        }
        final TimeSeriesURLGenerator that = (TimeSeriesURLGenerator)obj;
        return this.dateFormat.equals(that.dateFormat) && this.itemParameterName.equals(that.itemParameterName) && this.prefix.equals(that.prefix) && this.seriesParameterName.equals(that.seriesParameterName);
    }
}
