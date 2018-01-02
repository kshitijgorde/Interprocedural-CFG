// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.entity;

import java.awt.Shape;
import java.io.Serializable;

public class ContourEntity extends ChartEntity implements Cloneable, Serializable
{
    private int index;
    
    public ContourEntity(final Shape area, final String toolTipText) {
        super(area, toolTipText);
        this.index = -1;
    }
    
    public ContourEntity(final Shape area, final String toolTipText, final String urlText) {
        super(area, toolTipText, urlText);
        this.index = -1;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public void setIndex(final int index) {
        this.index = index;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ContourEntity && super.equals(obj)) {
            final ContourEntity ce = (ContourEntity)obj;
            return this.index == ce.index;
        }
        return false;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
