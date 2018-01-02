// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.urls;

import org.jfree.util.ObjectUtilities;
import org.jfree.data.general.PieDataset;
import java.io.Serializable;

public class StandardPieURLGenerator implements PieURLGenerator, Serializable
{
    private static final long serialVersionUID = 1626966402065883419L;
    private String prefix;
    private String categoryParameterName;
    private String indexParameterName;
    
    public StandardPieURLGenerator() {
        this("index.html");
    }
    
    public StandardPieURLGenerator(final String prefix) {
        this(prefix, "category");
    }
    
    public StandardPieURLGenerator(final String prefix, final String categoryParameterName) {
        this(prefix, categoryParameterName, "pieIndex");
    }
    
    public StandardPieURLGenerator(final String prefix, final String categoryParameterName, final String indexParameterName) {
        this.prefix = "index.html";
        this.categoryParameterName = "category";
        this.indexParameterName = "pieIndex";
        if (prefix == null) {
            throw new IllegalArgumentException("Null 'prefix' argument.");
        }
        if (categoryParameterName == null) {
            throw new IllegalArgumentException("Null 'categoryParameterName' argument.");
        }
        this.prefix = prefix;
        this.categoryParameterName = categoryParameterName;
        this.indexParameterName = indexParameterName;
    }
    
    public String generateURL(final PieDataset dataset, final Comparable key, final int pieIndex) {
        String url = this.prefix;
        if (url.indexOf("?") > -1) {
            url = url + "&amp;" + this.categoryParameterName + "=" + URLUtilities.encode(key.toString(), "UTF-8");
        }
        else {
            url = url + "?" + this.categoryParameterName + "=" + URLUtilities.encode(key.toString(), "UTF-8");
        }
        if (this.indexParameterName != null) {
            url = url + "&amp;" + this.indexParameterName + "=" + String.valueOf(pieIndex);
        }
        return url;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StandardPieURLGenerator)) {
            return false;
        }
        final StandardPieURLGenerator that = (StandardPieURLGenerator)obj;
        return this.prefix.equals(that.prefix) && this.categoryParameterName.equals(that.categoryParameterName) && ObjectUtilities.equal(this.indexParameterName, that.indexParameterName);
    }
}
