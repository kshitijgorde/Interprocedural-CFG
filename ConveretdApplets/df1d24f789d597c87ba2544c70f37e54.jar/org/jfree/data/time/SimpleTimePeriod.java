// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.time;

import java.util.Date;
import java.io.Serializable;

public class SimpleTimePeriod implements TimePeriod, Serializable
{
    private Date start;
    private Date end;
    
    public SimpleTimePeriod(final Date start, final Date end) {
        if (start.getTime() > end.getTime()) {
            throw new IllegalArgumentException("SimpleTimePeriod: requires end >= start.");
        }
        this.start = start;
        this.end = end;
    }
    
    public Date getStart() {
        return this.start;
    }
    
    public Date getEnd() {
        return this.end;
    }
    
    public boolean equals(final Object obj) {
        boolean result = false;
        if (obj instanceof TimePeriod) {
            final TimePeriod p = (TimePeriod)obj;
            result = (this.start.equals(p.getStart()) && this.end.equals(p.getEnd()));
        }
        return result;
    }
    
    public int hashCode() {
        int result = 17;
        result = 37 * result + this.start.hashCode();
        result = 37 * result + this.end.hashCode();
        return result;
    }
}
