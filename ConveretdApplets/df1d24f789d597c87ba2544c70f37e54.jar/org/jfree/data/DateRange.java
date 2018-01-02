// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import java.text.DateFormat;
import java.util.Date;
import java.io.Serializable;

public class DateRange extends Range implements Serializable
{
    private Date lowerDate;
    private Date upperDate;
    
    public DateRange() {
        this(new Date(0L), new Date(1L));
    }
    
    public DateRange(final Date lower, final Date upper) {
        super(lower.getTime(), upper.getTime());
        this.lowerDate = lower;
        this.upperDate = upper;
    }
    
    public DateRange(final double lower, final double upper) {
        super(lower, upper);
        this.lowerDate = new Date((long)lower);
        this.upperDate = new Date((long)upper);
    }
    
    public DateRange(final Range other) {
        this(other.getLowerBound(), other.getUpperBound());
    }
    
    public Date getLowerDate() {
        return this.lowerDate;
    }
    
    public Date getUpperDate() {
        return this.upperDate;
    }
    
    public String toString() {
        final DateFormat df = DateFormat.getDateTimeInstance();
        return "[" + df.format(this.lowerDate) + " --> " + df.format(this.upperDate) + "]";
    }
}
