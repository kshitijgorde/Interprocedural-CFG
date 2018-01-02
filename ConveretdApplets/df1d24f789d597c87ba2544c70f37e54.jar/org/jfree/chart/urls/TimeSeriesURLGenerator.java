// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.urls;

import java.util.Date;
import org.jfree.data.XYDataset;
import java.text.DateFormat;
import java.io.Serializable;

public class TimeSeriesURLGenerator implements XYURLGenerator, Serializable
{
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
    
    public TimeSeriesURLGenerator(final DateFormat dDateFormat, final String sPrefix, final String sSeriesParameterName, final String sItemParameterName) {
        this.dateFormat = DateFormat.getInstance();
        this.prefix = "index.html";
        this.seriesParameterName = "series";
        this.itemParameterName = "item";
        this.dateFormat = dDateFormat;
        this.prefix = sPrefix;
        this.seriesParameterName = sSeriesParameterName;
        this.itemParameterName = sItemParameterName;
    }
    
    public String generateURL(final XYDataset dataset, final int series, final int item) {
        String result = this.prefix;
        boolean firstParameter = result.indexOf("?") == -1;
        final String seriesName = dataset.getSeriesName(series);
        if (seriesName != null) {
            result += (firstParameter ? "?" : "&");
            result = result + this.seriesParameterName + "=" + seriesName;
            firstParameter = false;
        }
        final long x = dataset.getXValue(series, item).longValue();
        final String xValue = this.dateFormat.format(new Date(x));
        result += (firstParameter ? "?" : "&");
        result = result + this.itemParameterName + "=" + xValue;
        return result;
    }
}
