// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.time;

import java.io.ObjectStreamException;
import java.io.Serializable;

public final class TimePeriodAnchor implements Serializable
{
    public static final TimePeriodAnchor START;
    public static final TimePeriodAnchor MIDDLE;
    public static final TimePeriodAnchor END;
    private String name;
    
    private TimePeriodAnchor(final String name) {
        this.name = name;
    }
    
    public String toString() {
        return this.name;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TimePeriodAnchor)) {
            return false;
        }
        final TimePeriodAnchor position = (TimePeriodAnchor)o;
        return this.name.equals(position.name);
    }
    
    public int hashCode() {
        return this.name.hashCode();
    }
    
    private Object readResolve() throws ObjectStreamException {
        if (this.equals(TimePeriodAnchor.START)) {
            return TimePeriodAnchor.START;
        }
        if (this.equals(TimePeriodAnchor.MIDDLE)) {
            return TimePeriodAnchor.MIDDLE;
        }
        if (this.equals(TimePeriodAnchor.END)) {
            return TimePeriodAnchor.END;
        }
        return null;
    }
    
    static {
        START = new TimePeriodAnchor("TimePeriodAnchor.START");
        MIDDLE = new TimePeriodAnchor("TimePeriodAnchor.MIDDLE");
        END = new TimePeriodAnchor("TimePeriodAnchor.END");
    }
}
