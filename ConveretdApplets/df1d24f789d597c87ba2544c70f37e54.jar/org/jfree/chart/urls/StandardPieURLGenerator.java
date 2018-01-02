// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.urls;

import org.jfree.data.PieDataset;
import java.io.Serializable;

public class StandardPieURLGenerator implements PieURLGenerator, Serializable
{
    private String prefix;
    private String categoryParameterName;
    private String indexParameterName;
    
    public StandardPieURLGenerator() {
        this.prefix = "index.html";
        this.categoryParameterName = "category";
        this.indexParameterName = "pieIndex";
    }
    
    public StandardPieURLGenerator(final String prefix) {
        this.prefix = "index.html";
        this.categoryParameterName = "category";
        this.indexParameterName = "pieIndex";
        this.prefix = prefix;
    }
    
    public StandardPieURLGenerator(final String prefix, final String categoryParameterName) {
        this.prefix = "index.html";
        this.categoryParameterName = "category";
        this.indexParameterName = "pieIndex";
        this.prefix = prefix;
        this.categoryParameterName = categoryParameterName;
    }
    
    public StandardPieURLGenerator(final String prefix, final String categoryParameterName, final String indexParameterName) {
        this.prefix = "index.html";
        this.categoryParameterName = "category";
        this.indexParameterName = "pieIndex";
        this.prefix = prefix;
        this.categoryParameterName = categoryParameterName;
        this.indexParameterName = indexParameterName;
    }
    
    public String generateURL(final PieDataset data, final Comparable key, final int pieIndex) {
        String url = this.prefix;
        if (url.indexOf("?") > -1) {
            url = url + "&" + this.categoryParameterName + "=" + key.toString();
        }
        else {
            url = url + "?" + this.categoryParameterName + "=" + key.toString();
        }
        if (this.indexParameterName != null) {
            url = url + "&" + this.indexParameterName + "=" + String.valueOf(pieIndex);
        }
        return url;
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof StandardPieURLGenerator)) {
            return false;
        }
        final StandardPieURLGenerator generator = (StandardPieURLGenerator)o;
        return this.categoryParameterName.equals(generator.categoryParameterName) && this.prefix.equals(generator.prefix);
    }
}
