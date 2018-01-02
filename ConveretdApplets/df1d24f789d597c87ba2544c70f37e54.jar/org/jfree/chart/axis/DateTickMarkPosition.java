// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.axis;

import java.io.ObjectStreamException;
import java.io.Serializable;

public final class DateTickMarkPosition implements Serializable
{
    public static final DateTickMarkPosition START;
    public static final DateTickMarkPosition MIDDLE;
    public static final DateTickMarkPosition END;
    private String name;
    
    private DateTickMarkPosition(final String name) {
        this.name = name;
    }
    
    public String toString() {
        return this.name;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DateTickMarkPosition)) {
            return false;
        }
        final DateTickMarkPosition position = (DateTickMarkPosition)o;
        return this.name.equals(position.toString());
    }
    
    private Object readResolve() throws ObjectStreamException {
        if (this.equals(DateTickMarkPosition.START)) {
            return DateTickMarkPosition.START;
        }
        if (this.equals(DateTickMarkPosition.MIDDLE)) {
            return DateTickMarkPosition.MIDDLE;
        }
        if (this.equals(DateTickMarkPosition.END)) {
            return DateTickMarkPosition.END;
        }
        return null;
    }
    
    static {
        START = new DateTickMarkPosition("DateTickMarkPosition.START");
        MIDDLE = new DateTickMarkPosition("DateTickMarkPosition.MIDDLE");
        END = new DateTickMarkPosition("DateTickMarkPosition.END");
    }
}
