// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui;

import java.io.ObjectStreamException;
import java.io.Serializable;

public final class VerticalAlignment implements Serializable
{
    public static final VerticalAlignment TOP;
    public static final VerticalAlignment BOTTOM;
    public static final VerticalAlignment CENTER;
    private String name;
    
    private VerticalAlignment(final String name) {
        this.name = name;
    }
    
    public String toString() {
        return this.name;
    }
    
    public boolean equals(final Object o) {
        return this == o || (o instanceof VerticalAlignment && this.name.equals(((VerticalAlignment)o).name));
    }
    
    public int hashCode() {
        return this.name.hashCode();
    }
    
    private Object readResolve() throws ObjectStreamException {
        if (this.equals(VerticalAlignment.TOP)) {
            return VerticalAlignment.TOP;
        }
        if (this.equals(VerticalAlignment.BOTTOM)) {
            return VerticalAlignment.BOTTOM;
        }
        if (this.equals(VerticalAlignment.CENTER)) {
            return VerticalAlignment.CENTER;
        }
        return null;
    }
    
    static {
        TOP = new VerticalAlignment("VerticalAlignment.TOP");
        BOTTOM = new VerticalAlignment("VerticalAlignment.BOTTOM");
        CENTER = new VerticalAlignment("VerticalAlignment.CENTER");
    }
}
