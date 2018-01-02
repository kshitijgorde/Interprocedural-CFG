// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import java.io.ObjectStreamException;
import java.io.Serializable;

public final class DatasetRenderingOrder implements Serializable
{
    public static final DatasetRenderingOrder FORWARD;
    public static final DatasetRenderingOrder REVERSE;
    private String name;
    
    private DatasetRenderingOrder(final String name) {
        this.name = name;
    }
    
    public String toString() {
        return this.name;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DatasetRenderingOrder)) {
            return false;
        }
        final DatasetRenderingOrder order = (DatasetRenderingOrder)o;
        return this.name.equals(order.toString());
    }
    
    private Object readResolve() throws ObjectStreamException {
        if (this.equals(DatasetRenderingOrder.FORWARD)) {
            return DatasetRenderingOrder.FORWARD;
        }
        if (this.equals(DatasetRenderingOrder.REVERSE)) {
            return DatasetRenderingOrder.REVERSE;
        }
        return null;
    }
    
    static {
        FORWARD = new DatasetRenderingOrder("DatasetRenderingOrder.FORWARD");
        REVERSE = new DatasetRenderingOrder("DatasetRenderingOrder.REVERSE");
    }
}
