// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.time;

import java.util.TimeZone;
import java.util.Calendar;
import java.util.Date;
import java.io.Serializable;

public class Second extends RegularTimePeriod implements Serializable
{
    private static final long serialVersionUID = -6536564190712383466L;
    public static final int FIRST_SECOND_IN_MINUTE = 0;
    public static final int LAST_SECOND_IN_MINUTE = 59;
    private Day day;
    private byte hour;
    private byte minute;
    private byte second;
    private long firstMillisecond;
    
    public Second() {
        this(new Date());
    }
    
    public Second(final int second, final Minute minute) {
        if (minute == null) {
            throw new IllegalArgumentException("Null 'minute' argument.");
        }
        this.day = minute.getDay();
        this.hour = (byte)minute.getHourValue();
        this.minute = (byte)minute.getMinute();
        this.second = (byte)second;
        this.peg(Calendar.getInstance());
    }
    
    public Second(final int second, final int minute, final int hour, final int day, final int month, final int year) {
        this(second, new Minute(minute, hour, day, month, year));
    }
    
    public Second(final Date time) {
        this(time, RegularTimePeriod.DEFAULT_TIME_ZONE);
    }
    
    public Second(final Date time, final TimeZone zone) {
        final Calendar calendar = Calendar.getInstance(zone);
        calendar.setTime(time);
        this.second = (byte)calendar.get(13);
        this.minute = (byte)calendar.get(12);
        this.hour = (byte)calendar.get(11);
        this.day = new Day(time, zone);
        this.peg(calendar);
    }
    
    public int getSecond() {
        return this.second;
    }
    
    public Minute getMinute() {
        return new Minute(this.minute, new Hour(this.hour, this.day));
    }
    
    public long getFirstMillisecond() {
        return this.firstMillisecond;
    }
    
    public long getLastMillisecond() {
        return this.firstMillisecond + 999L;
    }
    
    public void peg(final Calendar calendar) {
        this.firstMillisecond = this.getFirstMillisecond(calendar);
    }
    
    public RegularTimePeriod previous() {
        Second result = null;
        if (this.second != 0) {
            result = new Second(this.second - 1, this.getMinute());
        }
        else {
            final Minute previous = (Minute)this.getMinute().previous();
            if (previous != null) {
                result = new Second(59, previous);
            }
        }
        return result;
    }
    
    public RegularTimePeriod next() {
        Second result = null;
        if (this.second != 59) {
            result = new Second(this.second + 1, this.getMinute());
        }
        else {
            final Minute next = (Minute)this.getMinute().next();
            if (next != null) {
                result = new Second(0, next);
            }
        }
        return result;
    }
    
    public long getSerialIndex() {
        final long hourIndex = this.day.getSerialIndex() * 24L + this.hour;
        final long minuteIndex = hourIndex * 60L + this.minute;
        return minuteIndex * 60L + this.second;
    }
    
    public long getFirstMillisecond(final Calendar calendar) {
        final int year = this.day.getYear();
        final int month = this.day.getMonth() - 1;
        final int day = this.day.getDayOfMonth();
        calendar.clear();
        calendar.set(year, month, day, this.hour, this.minute, this.second);
        calendar.set(14, 0);
        return calendar.getTime().getTime();
    }
    
    public long getLastMillisecond(final Calendar calendar) {
        return this.getFirstMillisecond(calendar) + 999L;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Second)) {
            return false;
        }
        final Second that = (Second)obj;
        return this.second == that.second && this.minute == that.minute && this.hour == that.hour && this.day.equals(that.day);
    }
    
    public int hashCode() {
        int result = 17;
        result = 37 * result + this.second;
        result = 37 * result + this.minute;
        result = 37 * result + this.hour;
        result = 37 * result + this.day.hashCode();
        return result;
    }
    
    public int compareTo(final Object o1) {
        if (!(o1 instanceof Second)) {
            int result;
            if (o1 instanceof RegularTimePeriod) {
                result = 0;
            }
            else {
                result = 1;
            }
            return result;
        }
        final Second s = (Second)o1;
        if (this.firstMillisecond < s.firstMillisecond) {
            return -1;
        }
        if (this.firstMillisecond > s.firstMillisecond) {
            return 1;
        }
        return 0;
    }
    
    public static Second parseSecond(String s) {
        Second result = null;
        s = s.trim();
        final String daystr = s.substring(0, Math.min(10, s.length()));
        final Day day = Day.parseDay(daystr);
        if (day != null) {
            String hmsstr = s.substring(Math.min(daystr.length() + 1, s.length()), s.length());
            hmsstr = hmsstr.trim();
            final int l = hmsstr.length();
            final String hourstr = hmsstr.substring(0, Math.min(2, l));
            final String minstr = hmsstr.substring(Math.min(3, l), Math.min(5, l));
            final String secstr = hmsstr.substring(Math.min(6, l), Math.min(8, l));
            final int hour = Integer.parseInt(hourstr);
            if (hour >= 0 && hour <= 23) {
                final int minute = Integer.parseInt(minstr);
                if (minute >= 0 && minute <= 59) {
                    final Minute m = new Minute(minute, new Hour(hour, day));
                    final int second = Integer.parseInt(secstr);
                    if (second >= 0 && second <= 59) {
                        result = new Second(second, m);
                    }
                }
            }
        }
        return result;
    }
}
