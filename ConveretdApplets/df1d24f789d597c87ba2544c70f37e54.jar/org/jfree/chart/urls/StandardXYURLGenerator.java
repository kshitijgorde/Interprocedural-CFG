// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.urls;

import org.jfree.util.ObjectUtils;
import org.jfree.data.XYDataset;
import java.io.Serializable;

public class StandardXYURLGenerator implements XYURLGenerator, Serializable
{
    private String prefix;
    private String seriesParameterName;
    private String itemParameterName;
    
    public StandardXYURLGenerator() {
        this.prefix = "index.html";
        this.seriesParameterName = "series";
        this.itemParameterName = "item";
    }
    
    public StandardXYURLGenerator(final String sPrefix) {
        this.prefix = "index.html";
        this.seriesParameterName = "series";
        this.itemParameterName = "item";
        this.prefix = sPrefix;
    }
    
    public StandardXYURLGenerator(final String prefix, final String seriesParameterName, final String itemParameterName) {
        this.prefix = "index.html";
        this.seriesParameterName = "series";
        this.itemParameterName = "item";
        this.prefix = prefix;
        this.seriesParameterName = seriesParameterName;
        this.itemParameterName = itemParameterName;
    }
    
    public String generateURL(final XYDataset dataset, final int series, final int item) {
        String url = this.prefix;
        final boolean firstParameter = url.indexOf("?") == -1;
        url += (firstParameter ? "?" : "&");
        url = url + this.seriesParameterName + "=" + series + "&" + this.itemParameterName + "=" + item;
        return url;
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof StandardXYURLGenerator) {
            final StandardXYURLGenerator g = (StandardXYURLGenerator)obj;
            final boolean b0 = ObjectUtils.equal(g.prefix, this.prefix);
            final boolean b2 = ObjectUtils.equal(g.seriesParameterName, this.seriesParameterName);
            final boolean b3 = ObjectUtils.equal(g.itemParameterName, this.itemParameterName);
            return b0 && b2 && b3;
        }
        return false;
    }
}
