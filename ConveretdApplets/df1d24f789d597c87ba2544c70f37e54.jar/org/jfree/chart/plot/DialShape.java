// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import java.io.ObjectStreamException;
import java.io.Serializable;

public final class DialShape implements Serializable
{
    public static final DialShape CIRCLE;
    public static final DialShape CHORD;
    public static final DialShape PIE;
    private String name;
    
    private DialShape(final String name) {
        this.name = name;
    }
    
    public String toString() {
        return this.name;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DialShape)) {
            return false;
        }
        final DialShape shape = (DialShape)o;
        return this.name.equals(shape.toString());
    }
    
    private Object readResolve() throws ObjectStreamException {
        if (this.equals(DialShape.CIRCLE)) {
            return DialShape.CIRCLE;
        }
        if (this.equals(DialShape.CHORD)) {
            return DialShape.CHORD;
        }
        if (this.equals(DialShape.PIE)) {
            return DialShape.PIE;
        }
        return null;
    }
    
    static {
        CIRCLE = new DialShape("DialShape.CIRCLE");
        CHORD = new DialShape("DialShape.CHORD");
        PIE = new DialShape("DialShape.PIE");
    }
}
