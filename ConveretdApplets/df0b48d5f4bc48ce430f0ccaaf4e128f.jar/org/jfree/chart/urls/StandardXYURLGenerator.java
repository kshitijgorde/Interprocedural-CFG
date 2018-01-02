// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.urls;

import org.jfree.util.ObjectUtilities;
import org.jfree.data.xy.XYDataset;
import java.io.Serializable;

public class StandardXYURLGenerator implements XYURLGenerator, Serializable
{
    private static final long serialVersionUID = -1771624523496595382L;
    public static final String DEFAULT_PREFIX = "index.html";
    public static final String DEFAULT_SERIES_PARAMETER = "series";
    public static final String DEFAULT_ITEM_PARAMETER = "item";
    private String prefix;
    private String seriesParameterName;
    private String itemParameterName;
    
    public StandardXYURLGenerator() {
        this("index.html", "series", "item");
    }
    
    public StandardXYURLGenerator(final String prefix) {
        this(prefix, "series", "item");
    }
    
    public StandardXYURLGenerator(final String prefix, final String seriesParameterName, final String itemParameterName) {
        if (prefix == null) {
            throw new IllegalArgumentException("Null 'prefix' argument.");
        }
        if (seriesParameterName == null) {
            throw new IllegalArgumentException("Null 'seriesParameterName' argument.");
        }
        if (itemParameterName == null) {
            throw new IllegalArgumentException("Null 'itemParameterName' argument.");
        }
        this.prefix = prefix;
        this.seriesParameterName = seriesParameterName;
        this.itemParameterName = itemParameterName;
    }
    
    public String generateURL(final XYDataset dataset, final int series, final int item) {
        String url = this.prefix;
        final boolean firstParameter = url.indexOf("?") == -1;
        url += (firstParameter ? "?" : "&amp;");
        url = url + this.seriesParameterName + "=" + series + "&amp;" + this.itemParameterName + "=" + item;
        return url;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StandardXYURLGenerator)) {
            return false;
        }
        final StandardXYURLGenerator that = (StandardXYURLGenerator)obj;
        return ObjectUtilities.equal(that.prefix, this.prefix) && ObjectUtilities.equal(that.seriesParameterName, this.seriesParameterName) && ObjectUtilities.equal(that.itemParameterName, this.itemParameterName);
    }
}
