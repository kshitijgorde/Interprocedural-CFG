// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.entity;

import java.awt.Shape;

public class CategoryLabelEntity extends TickLabelEntity
{
    private Comparable key;
    
    public CategoryLabelEntity(final Comparable key, final Shape area, final String toolTipText, final String urlText) {
        super(area, toolTipText, urlText);
        this.key = key;
    }
    
    public Comparable getKey() {
        return this.key;
    }
    
    public String toString() {
        final StringBuffer buf = new StringBuffer("CategoryLabelEntity: ");
        buf.append("category=");
        buf.append(this.key);
        buf.append(", tooltip=" + this.getToolTipText());
        buf.append(", url=" + this.getURLText());
        return buf.toString();
    }
}
