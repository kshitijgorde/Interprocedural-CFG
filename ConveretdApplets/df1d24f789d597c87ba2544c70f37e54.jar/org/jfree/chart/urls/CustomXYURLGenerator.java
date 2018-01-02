// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.urls;

import org.jfree.data.XYDataset;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class CustomXYURLGenerator implements XYURLGenerator, Serializable
{
    private ArrayList urlSeries;
    
    public CustomXYURLGenerator() {
        this.urlSeries = new ArrayList();
    }
    
    public int getListCount() {
        return this.urlSeries.size();
    }
    
    public int getURLCount(final int list) {
        int result = 0;
        final List tooltips = this.urlSeries.get(list);
        if (tooltips != null) {
            result = tooltips.size();
        }
        return result;
    }
    
    public String getURL(final int series, final int item) {
        String result = null;
        if (series < this.getListCount()) {
            final List tooltips = this.urlSeries.get(series);
            if (tooltips != null && item < tooltips.size()) {
                result = tooltips.get(item);
            }
        }
        return result;
    }
    
    public String generateURL(final XYDataset data, final int series, final int item) {
        return this.getURL(series, item);
    }
    
    public void addURLSeries(final List urls) {
        this.urlSeries.add(urls);
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof CustomXYURLGenerator)) {
            return false;
        }
        final CustomXYURLGenerator generator = (CustomXYURLGenerator)o;
        final int listCount = this.getListCount();
        if (listCount != generator.getListCount()) {
            return false;
        }
        for (int series = 0; series < listCount; ++series) {
            final int urlCount = this.getURLCount(series);
            if (urlCount != generator.getURLCount(series)) {
                return false;
            }
            for (int item = 0; item < urlCount; ++item) {
                final String u1 = this.getURL(series, item);
                final String u2 = generator.getURL(series, item);
                if (u1 != null) {
                    if (!u1.equals(u2)) {
                        return false;
                    }
                }
                else if (u2 != null) {
                    return false;
                }
            }
        }
        return true;
    }
}
