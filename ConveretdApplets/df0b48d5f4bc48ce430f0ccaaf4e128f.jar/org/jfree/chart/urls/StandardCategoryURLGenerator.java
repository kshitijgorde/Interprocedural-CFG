// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.urls;

import org.jfree.util.ObjectUtilities;
import org.jfree.data.category.CategoryDataset;
import java.io.Serializable;

public class StandardCategoryURLGenerator implements CategoryURLGenerator, Cloneable, Serializable
{
    private static final long serialVersionUID = 2276668053074881909L;
    private String prefix;
    private String seriesParameterName;
    private String categoryParameterName;
    
    public StandardCategoryURLGenerator() {
        this.prefix = "index.html";
        this.seriesParameterName = "series";
        this.categoryParameterName = "category";
    }
    
    public StandardCategoryURLGenerator(final String prefix) {
        this.prefix = "index.html";
        this.seriesParameterName = "series";
        this.categoryParameterName = "category";
        if (prefix == null) {
            throw new IllegalArgumentException("Null 'prefix' argument.");
        }
        this.prefix = prefix;
    }
    
    public StandardCategoryURLGenerator(final String prefix, final String seriesParameterName, final String categoryParameterName) {
        this.prefix = "index.html";
        this.seriesParameterName = "series";
        this.categoryParameterName = "category";
        if (prefix == null) {
            throw new IllegalArgumentException("Null 'prefix' argument.");
        }
        if (seriesParameterName == null) {
            throw new IllegalArgumentException("Null 'seriesParameterName' argument.");
        }
        if (categoryParameterName == null) {
            throw new IllegalArgumentException("Null 'categoryParameterName' argument.");
        }
        this.prefix = prefix;
        this.seriesParameterName = seriesParameterName;
        this.categoryParameterName = categoryParameterName;
    }
    
    public String generateURL(final CategoryDataset dataset, final int series, final int category) {
        String url = this.prefix;
        final Comparable seriesKey = dataset.getRowKey(series);
        final Comparable categoryKey = dataset.getColumnKey(category);
        final boolean firstParameter = url.indexOf("?") == -1;
        url += (firstParameter ? "?" : "&amp;");
        url = url + this.seriesParameterName + "=" + URLUtilities.encode(seriesKey.toString(), "UTF-8");
        url = url + "&amp;" + this.categoryParameterName + "=" + URLUtilities.encode(categoryKey.toString(), "UTF-8");
        return url;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StandardCategoryURLGenerator)) {
            return false;
        }
        final StandardCategoryURLGenerator that = (StandardCategoryURLGenerator)obj;
        return ObjectUtilities.equal(this.prefix, that.prefix) && ObjectUtilities.equal(this.seriesParameterName, that.seriesParameterName) && ObjectUtilities.equal(this.categoryParameterName, that.categoryParameterName);
    }
    
    public int hashCode() {
        int result = (this.prefix != null) ? this.prefix.hashCode() : 0;
        result = 29 * result + ((this.seriesParameterName != null) ? this.seriesParameterName.hashCode() : 0);
        result = 29 * result + ((this.categoryParameterName != null) ? this.categoryParameterName.hashCode() : 0);
        return result;
    }
}
