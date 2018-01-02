// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart;

import java.io.ObjectStreamException;
import java.io.Serializable;

public final class LegendRenderingOrder implements Serializable
{
    public static final LegendRenderingOrder STANDARD;
    public static final LegendRenderingOrder REVERSE;
    private String name;
    
    private LegendRenderingOrder(final String name) {
        this.name = name;
    }
    
    public String toString() {
        return this.name;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LegendRenderingOrder)) {
            return false;
        }
        final LegendRenderingOrder order = (LegendRenderingOrder)o;
        return this.name.equals(order.toString());
    }
    
    private Object readResolve() throws ObjectStreamException {
        if (this.equals(LegendRenderingOrder.STANDARD)) {
            return LegendRenderingOrder.STANDARD;
        }
        if (this.equals(LegendRenderingOrder.REVERSE)) {
            return LegendRenderingOrder.REVERSE;
        }
        return null;
    }
    
    static {
        STANDARD = new LegendRenderingOrder("LegendRenderingOrder.STANDARD");
        REVERSE = new LegendRenderingOrder("LegendRenderingOrder.REVERSE");
    }
}
