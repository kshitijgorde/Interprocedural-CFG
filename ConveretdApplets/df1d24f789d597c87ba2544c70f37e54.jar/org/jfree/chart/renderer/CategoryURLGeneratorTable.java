// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import org.jfree.chart.urls.CategoryURLGenerator;
import java.io.Serializable;
import org.jfree.util.ObjectTable;

public class CategoryURLGeneratorTable extends ObjectTable implements Serializable
{
    public CategoryURLGenerator getURLGenerator(final int row, final int column) {
        return (CategoryURLGenerator)this.getObject(row, column);
    }
    
    public void setURLGenerator(final int row, final int column, final CategoryURLGenerator generator) {
        this.setObject(row, column, generator);
    }
    
    public boolean equals(final Object o) {
        return o instanceof CategoryURLGeneratorTable && super.equals(o);
    }
}
