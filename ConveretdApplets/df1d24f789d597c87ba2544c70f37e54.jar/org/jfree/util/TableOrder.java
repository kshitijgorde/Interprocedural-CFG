// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.io.ObjectStreamException;
import java.io.Serializable;

public final class TableOrder implements Serializable
{
    public static final TableOrder BY_ROW;
    public static final TableOrder BY_COLUMN;
    private String name;
    
    private TableOrder(final String name) {
        this.name = name;
    }
    
    public String toString() {
        return this.name;
    }
    
    public boolean equals(final Object o) {
        return this == o || (o instanceof TableOrder && this.name.equals(((TableOrder)o).name));
    }
    
    public int hashCode() {
        return this.name.hashCode();
    }
    
    private Object readResolve() throws ObjectStreamException {
        if (this.equals(TableOrder.BY_ROW)) {
            return TableOrder.BY_ROW;
        }
        if (this.equals(TableOrder.BY_COLUMN)) {
            return TableOrder.BY_COLUMN;
        }
        return null;
    }
    
    static {
        BY_ROW = new TableOrder("TableOrder.BY_ROW");
        BY_COLUMN = new TableOrder("TableOrder.BY_COLUMN");
    }
}
