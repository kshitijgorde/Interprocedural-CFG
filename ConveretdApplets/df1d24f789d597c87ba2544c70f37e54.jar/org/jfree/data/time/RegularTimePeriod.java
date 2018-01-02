// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.time;

import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;
import org.jfree.date.MonthConstants;

public abstract class RegularTimePeriod implements TimePeriod, Comparable, MonthConstants
{
    public static final TimeZone DEFAULT_TIME_ZONE;
    public static final Calendar WORKING_CALENDAR;
    
    public abstract RegularTimePeriod previous();
    
    public abstract RegularTimePeriod next();
    
    public abstract long getSerialIndex();
    
    public Date getStart() {
        return new Date(this.getFirstMillisecond());
    }
    
    public Date getEnd() {
        return new Date(this.getLastMillisecond());
    }
    
    public long getFirstMillisecond() {
        return this.getFirstMillisecond(RegularTimePeriod.DEFAULT_TIME_ZONE);
    }
    
    public long getFirstMillisecond(final TimeZone zone) {
        RegularTimePeriod.WORKING_CALENDAR.setTimeZone(zone);
        return this.getFirstMillisecond(RegularTimePeriod.WORKING_CALENDAR);
    }
    
    public abstract long getFirstMillisecond(final Calendar p0);
    
    public long getLastMillisecond() {
        return this.getLastMillisecond(RegularTimePeriod.DEFAULT_TIME_ZONE);
    }
    
    public long getLastMillisecond(final TimeZone zone) {
        RegularTimePeriod.WORKING_CALENDAR.setTimeZone(zone);
        return this.getLastMillisecond(RegularTimePeriod.WORKING_CALENDAR);
    }
    
    public abstract long getLastMillisecond(final Calendar p0);
    
    public long getMiddleMillisecond() {
        final long m1 = this.getFirstMillisecond();
        final long m2 = this.getLastMillisecond();
        return m1 + (m2 - m1) / 2L;
    }
    
    public long getMiddleMillisecond(final TimeZone zone) {
        final long m1 = this.getFirstMillisecond(zone);
        final long m2 = this.getLastMillisecond(zone);
        return m1 + (m2 - m1) / 2L;
    }
    
    public long getMiddleMillisecond(final Calendar calendar) {
        final long m1 = this.getFirstMillisecond(calendar);
        final long m2 = this.getLastMillisecond(calendar);
        return m1 + (m2 - m1) / 2L;
    }
    
    public String toString() {
        return String.valueOf(this.getStart());
    }
    
    static {
        DEFAULT_TIME_ZONE = TimeZone.getDefault();
        WORKING_CALENDAR = Calendar.getInstance(RegularTimePeriod.DEFAULT_TIME_ZONE);
    }
}
