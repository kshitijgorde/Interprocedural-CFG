// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.time;

import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;
import org.jfree.date.MonthConstants;

public abstract class RegularTimePeriod implements TimePeriod, Comparable, MonthConstants
{
    public static final TimeZone DEFAULT_TIME_ZONE;
    public static final Calendar WORKING_CALENDAR;
    static /* synthetic */ Class class$java$util$Date;
    static /* synthetic */ Class class$java$util$TimeZone;
    static /* synthetic */ Class class$org$jfree$data$time$Year;
    static /* synthetic */ Class class$org$jfree$data$time$Quarter;
    static /* synthetic */ Class class$org$jfree$data$time$Month;
    static /* synthetic */ Class class$org$jfree$data$time$Day;
    static /* synthetic */ Class class$org$jfree$data$time$Hour;
    static /* synthetic */ Class class$org$jfree$data$time$Minute;
    static /* synthetic */ Class class$org$jfree$data$time$Second;
    static /* synthetic */ Class class$org$jfree$data$time$Millisecond;
    
    public static RegularTimePeriod createInstance(final Class c, final Date millisecond, final TimeZone zone) {
        RegularTimePeriod result = null;
        try {
            final Constructor constructor = c.getDeclaredConstructor((RegularTimePeriod.class$java$util$Date == null) ? (RegularTimePeriod.class$java$util$Date = class$("java.util.Date")) : RegularTimePeriod.class$java$util$Date, (RegularTimePeriod.class$java$util$TimeZone == null) ? (RegularTimePeriod.class$java$util$TimeZone = class$("java.util.TimeZone")) : RegularTimePeriod.class$java$util$TimeZone);
            result = constructor.newInstance(millisecond, zone);
        }
        catch (Exception ex) {}
        return result;
    }
    
    public static Class downsize(final Class c) {
        if (c.equals((RegularTimePeriod.class$org$jfree$data$time$Year == null) ? (RegularTimePeriod.class$org$jfree$data$time$Year = class$("org.jfree.data.time.Year")) : RegularTimePeriod.class$org$jfree$data$time$Year)) {
            return (RegularTimePeriod.class$org$jfree$data$time$Quarter == null) ? (RegularTimePeriod.class$org$jfree$data$time$Quarter = class$("org.jfree.data.time.Quarter")) : RegularTimePeriod.class$org$jfree$data$time$Quarter;
        }
        if (c.equals((RegularTimePeriod.class$org$jfree$data$time$Quarter == null) ? (RegularTimePeriod.class$org$jfree$data$time$Quarter = class$("org.jfree.data.time.Quarter")) : RegularTimePeriod.class$org$jfree$data$time$Quarter)) {
            return (RegularTimePeriod.class$org$jfree$data$time$Month == null) ? (RegularTimePeriod.class$org$jfree$data$time$Month = class$("org.jfree.data.time.Month")) : RegularTimePeriod.class$org$jfree$data$time$Month;
        }
        if (c.equals((RegularTimePeriod.class$org$jfree$data$time$Month == null) ? (RegularTimePeriod.class$org$jfree$data$time$Month = class$("org.jfree.data.time.Month")) : RegularTimePeriod.class$org$jfree$data$time$Month)) {
            return (RegularTimePeriod.class$org$jfree$data$time$Day == null) ? (RegularTimePeriod.class$org$jfree$data$time$Day = class$("org.jfree.data.time.Day")) : RegularTimePeriod.class$org$jfree$data$time$Day;
        }
        if (c.equals((RegularTimePeriod.class$org$jfree$data$time$Day == null) ? (RegularTimePeriod.class$org$jfree$data$time$Day = class$("org.jfree.data.time.Day")) : RegularTimePeriod.class$org$jfree$data$time$Day)) {
            return (RegularTimePeriod.class$org$jfree$data$time$Hour == null) ? (RegularTimePeriod.class$org$jfree$data$time$Hour = class$("org.jfree.data.time.Hour")) : RegularTimePeriod.class$org$jfree$data$time$Hour;
        }
        if (c.equals((RegularTimePeriod.class$org$jfree$data$time$Hour == null) ? (RegularTimePeriod.class$org$jfree$data$time$Hour = class$("org.jfree.data.time.Hour")) : RegularTimePeriod.class$org$jfree$data$time$Hour)) {
            return (RegularTimePeriod.class$org$jfree$data$time$Minute == null) ? (RegularTimePeriod.class$org$jfree$data$time$Minute = class$("org.jfree.data.time.Minute")) : RegularTimePeriod.class$org$jfree$data$time$Minute;
        }
        if (c.equals((RegularTimePeriod.class$org$jfree$data$time$Minute == null) ? (RegularTimePeriod.class$org$jfree$data$time$Minute = class$("org.jfree.data.time.Minute")) : RegularTimePeriod.class$org$jfree$data$time$Minute)) {
            return (RegularTimePeriod.class$org$jfree$data$time$Second == null) ? (RegularTimePeriod.class$org$jfree$data$time$Second = class$("org.jfree.data.time.Second")) : RegularTimePeriod.class$org$jfree$data$time$Second;
        }
        if (c.equals((RegularTimePeriod.class$org$jfree$data$time$Second == null) ? (RegularTimePeriod.class$org$jfree$data$time$Second = class$("org.jfree.data.time.Second")) : RegularTimePeriod.class$org$jfree$data$time$Second)) {
            return (RegularTimePeriod.class$org$jfree$data$time$Millisecond == null) ? (RegularTimePeriod.class$org$jfree$data$time$Millisecond = class$("org.jfree.data.time.Millisecond")) : RegularTimePeriod.class$org$jfree$data$time$Millisecond;
        }
        return (RegularTimePeriod.class$org$jfree$data$time$Millisecond == null) ? (RegularTimePeriod.class$org$jfree$data$time$Millisecond = class$("org.jfree.data.time.Millisecond")) : RegularTimePeriod.class$org$jfree$data$time$Millisecond;
    }
    
    public abstract RegularTimePeriod previous();
    
    public abstract RegularTimePeriod next();
    
    public abstract long getSerialIndex();
    
    public abstract void peg(final Calendar p0);
    
    public Date getStart() {
        return new Date(this.getFirstMillisecond());
    }
    
    public Date getEnd() {
        return new Date(this.getLastMillisecond());
    }
    
    public abstract long getFirstMillisecond();
    
    public long getFirstMillisecond(final TimeZone zone) {
        final Calendar calendar = Calendar.getInstance(zone);
        return this.getFirstMillisecond(calendar);
    }
    
    public abstract long getFirstMillisecond(final Calendar p0);
    
    public abstract long getLastMillisecond();
    
    public long getLastMillisecond(final TimeZone zone) {
        final Calendar calendar = Calendar.getInstance(zone);
        return this.getLastMillisecond(calendar);
    }
    
    public abstract long getLastMillisecond(final Calendar p0);
    
    public long getMiddleMillisecond() {
        final long m1 = this.getFirstMillisecond();
        final long m2 = this.getLastMillisecond();
        return m1 + (m2 - m1) / 2L;
    }
    
    public long getMiddleMillisecond(final TimeZone zone) {
        final Calendar calendar = Calendar.getInstance(zone);
        final long m1 = this.getFirstMillisecond(calendar);
        final long m2 = this.getLastMillisecond(calendar);
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
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        DEFAULT_TIME_ZONE = TimeZone.getDefault();
        WORKING_CALENDAR = Calendar.getInstance(RegularTimePeriod.DEFAULT_TIME_ZONE);
    }
}
