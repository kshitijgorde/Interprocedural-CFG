// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.axis;

import java.io.ObjectStreamException;
import java.io.Serializable;

public final class CategoryLabelWidthType implements Serializable
{
    public static final CategoryLabelWidthType CATEGORY;
    public static final CategoryLabelWidthType RANGE;
    private String name;
    
    private CategoryLabelWidthType(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("Null 'name' argument.");
        }
        this.name = name;
    }
    
    public String toString() {
        return this.name;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoryLabelWidthType)) {
            return false;
        }
        final CategoryLabelWidthType t = (CategoryLabelWidthType)o;
        return this.name.equals(t.toString());
    }
    
    private Object readResolve() throws ObjectStreamException {
        if (this.equals(CategoryLabelWidthType.CATEGORY)) {
            return CategoryLabelWidthType.CATEGORY;
        }
        if (this.equals(CategoryLabelWidthType.RANGE)) {
            return CategoryLabelWidthType.RANGE;
        }
        return null;
    }
    
    static {
        CATEGORY = new CategoryLabelWidthType("CategoryLabelWidthType.CATEGORY");
        RANGE = new CategoryLabelWidthType("CategoryLabelWidthType.RANGE");
    }
}
