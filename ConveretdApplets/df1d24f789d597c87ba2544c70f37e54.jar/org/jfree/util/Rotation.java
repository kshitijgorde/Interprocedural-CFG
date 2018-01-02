// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.io.ObjectStreamException;
import java.io.Serializable;

public final class Rotation implements Serializable
{
    public static final Rotation CLOCKWISE;
    public static final Rotation ANTICLOCKWISE;
    private String name;
    private double factor;
    
    private Rotation(final String name, final double factor) {
        this.name = name;
        this.factor = factor;
    }
    
    public String toString() {
        return this.name;
    }
    
    public double getFactor() {
        return this.factor;
    }
    
    public boolean equals(final Object o) {
        return this == o || (o instanceof Rotation && this.factor == ((Rotation)o).factor);
    }
    
    public int hashCode() {
        final long doubleToLongBits = Double.doubleToLongBits(this.factor);
        return (int)(doubleToLongBits ^ doubleToLongBits >>> 32);
    }
    
    private Object readResolve() throws ObjectStreamException {
        if (this.equals(Rotation.CLOCKWISE)) {
            return Rotation.CLOCKWISE;
        }
        if (this.equals(Rotation.ANTICLOCKWISE)) {
            return Rotation.ANTICLOCKWISE;
        }
        return null;
    }
    
    static {
        CLOCKWISE = new Rotation("Rotation.CLOCKWISE", -1.0);
        ANTICLOCKWISE = new Rotation("Rotation.ANTICLOCKWISE", 1.0);
    }
}
