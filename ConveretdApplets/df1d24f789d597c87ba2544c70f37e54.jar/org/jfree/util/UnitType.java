// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.io.ObjectStreamException;
import java.io.Serializable;

public final class UnitType implements Serializable
{
    public static final UnitType ABSOLUTE;
    public static final UnitType RELATIVE;
    private String name;
    
    private UnitType(final String name) {
        this.name = name;
    }
    
    public String toString() {
        return this.name;
    }
    
    public boolean equals(final Object o) {
        return this == o || (o instanceof UnitType && this.name.equals(((UnitType)o).name));
    }
    
    public int hashCode() {
        return this.name.hashCode();
    }
    
    private Object readResolve() throws ObjectStreamException {
        if (this.equals(UnitType.ABSOLUTE)) {
            return UnitType.ABSOLUTE;
        }
        if (this.equals(UnitType.RELATIVE)) {
            return UnitType.RELATIVE;
        }
        return null;
    }
    
    static {
        ABSOLUTE = new UnitType("UnitType.ABSOLUTE");
        RELATIVE = new UnitType("UnitType.RELATIVE");
    }
}
