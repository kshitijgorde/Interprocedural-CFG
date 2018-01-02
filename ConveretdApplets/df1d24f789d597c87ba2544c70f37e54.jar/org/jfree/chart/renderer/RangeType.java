// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

public final class RangeType
{
    public static final RangeType STANDARD;
    public static final RangeType STACKED;
    public static final RangeType SERIES_CUMULATIVE;
    private String name;
    
    private RangeType(final String name) {
        this.name = name;
    }
    
    public String toString() {
        return this.name;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RangeType)) {
            return false;
        }
        final RangeType order = (RangeType)o;
        return this.name.equals(order.toString());
    }
    
    static {
        STANDARD = new RangeType("RangeType.STANDARD");
        STACKED = new RangeType("RangeType.STACKED");
        SERIES_CUMULATIVE = new RangeType("RangeType.SERIES_CUMULATIVE");
    }
}
