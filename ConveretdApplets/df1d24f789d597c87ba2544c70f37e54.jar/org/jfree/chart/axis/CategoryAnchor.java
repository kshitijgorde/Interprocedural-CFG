// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.axis;

import java.io.ObjectStreamException;
import java.io.Serializable;

public final class CategoryAnchor implements Serializable
{
    public static final CategoryAnchor START;
    public static final CategoryAnchor MIDDLE;
    public static final CategoryAnchor END;
    private String name;
    
    private CategoryAnchor(final String name) {
        this.name = name;
    }
    
    public String toString() {
        return this.name;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoryAnchor)) {
            return false;
        }
        final CategoryAnchor position = (CategoryAnchor)o;
        return this.name.equals(position.toString());
    }
    
    private Object readResolve() throws ObjectStreamException {
        if (this.equals(CategoryAnchor.START)) {
            return CategoryAnchor.START;
        }
        if (this.equals(CategoryAnchor.MIDDLE)) {
            return CategoryAnchor.MIDDLE;
        }
        if (this.equals(CategoryAnchor.END)) {
            return CategoryAnchor.END;
        }
        return null;
    }
    
    static {
        START = new CategoryAnchor("CategoryAnchor.START");
        MIDDLE = new CategoryAnchor("CategoryAnchor.MIDDLE");
        END = new CategoryAnchor("CategoryAnchor.END");
    }
}
