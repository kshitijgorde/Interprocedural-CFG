// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.axis;

import java.io.ObjectStreamException;
import java.io.Serializable;

public final class AxisLocation implements Serializable
{
    public static final AxisLocation TOP_OR_LEFT;
    public static final AxisLocation TOP_OR_RIGHT;
    public static final AxisLocation BOTTOM_OR_LEFT;
    public static final AxisLocation BOTTOM_OR_RIGHT;
    private String name;
    
    private AxisLocation(final String name) {
        this.name = name;
    }
    
    public String toString() {
        return this.name;
    }
    
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AxisLocation)) {
            return false;
        }
        final AxisLocation location = (AxisLocation)obj;
        return this.name.equals(location.toString());
    }
    
    public static AxisLocation getOpposite(final AxisLocation location) {
        if (location == null) {
            throw new IllegalArgumentException("Null 'location' argument.");
        }
        AxisLocation result = null;
        if (location == AxisLocation.TOP_OR_LEFT) {
            result = AxisLocation.BOTTOM_OR_RIGHT;
        }
        else if (location == AxisLocation.TOP_OR_RIGHT) {
            result = AxisLocation.BOTTOM_OR_LEFT;
        }
        else if (location == AxisLocation.BOTTOM_OR_LEFT) {
            result = AxisLocation.TOP_OR_RIGHT;
        }
        else {
            if (location != AxisLocation.BOTTOM_OR_RIGHT) {
                throw new IllegalStateException("AxisLocation not recognised.");
            }
            result = AxisLocation.TOP_OR_LEFT;
        }
        return result;
    }
    
    private Object readResolve() throws ObjectStreamException {
        if (this.equals(AxisLocation.TOP_OR_RIGHT)) {
            return AxisLocation.TOP_OR_RIGHT;
        }
        if (this.equals(AxisLocation.BOTTOM_OR_RIGHT)) {
            return AxisLocation.BOTTOM_OR_RIGHT;
        }
        if (this.equals(AxisLocation.TOP_OR_LEFT)) {
            return AxisLocation.TOP_OR_LEFT;
        }
        if (this.equals(AxisLocation.BOTTOM_OR_LEFT)) {
            return AxisLocation.BOTTOM_OR_LEFT;
        }
        return null;
    }
    
    static {
        TOP_OR_LEFT = new AxisLocation("AxisLocation.TOP_OR_LEFT");
        TOP_OR_RIGHT = new AxisLocation("AxisLocation.TOP_OR_RIGHT");
        BOTTOM_OR_LEFT = new AxisLocation("AxisLocation.BOTTOM_OR_LEFT");
        BOTTOM_OR_RIGHT = new AxisLocation("AxisLocation.BOTTOM_OR_RIGHT");
    }
}
