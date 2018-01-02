// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.time;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Date;
import java.io.Serializable;

public class Year extends RegularTimePeriod implements Serializable
{
    private int year;
    
    public Year() {
        this(new Date());
    }
    
    public Year(final int year) {
        if (year < 1900 || year > 9999) {
            throw new IllegalArgumentException("Year constructor: year (" + year + ") outside valid range.");
        }
        this.year = year;
    }
    
    public Year(final Date time) {
        this(time, RegularTimePeriod.DEFAULT_TIME_ZONE);
    }
    
    public Year(final Date time, final TimeZone zone) {
        final Calendar calendar = Calendar.getInstance(zone);
        calendar.setTime(time);
        this.year = calendar.get(1);
    }
    
    public int getYear() {
        return this.year;
    }
    
    public RegularTimePeriod previous() {
        if (this.year > 1900) {
            return new Year(this.year - 1);
        }
        return null;
    }
    
    public RegularTimePeriod next() {
        if (this.year < 9999) {
            return new Year(this.year + 1);
        }
        return null;
    }
    
    public long getSerialIndex() {
        return this.year;
    }
    
    public long getFirstMillisecond(final Calendar calendar) {
        final Day jan1 = new Day(1, 1, this.year);
        return jan1.getFirstMillisecond(calendar);
    }
    
    public long getLastMillisecond(final Calendar calendar) {
        final Day dec31 = new Day(31, 12, this.year);
        return dec31.getLastMillisecond(calendar);
    }
    
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof Year) {
            final Year target = (Year)object;
            return this.year == target.getYear();
        }
        return false;
    }
    
    public int hashCode() {
        int result = 17;
        final int c = this.year ^ this.year >>> 32;
        result = 37 * result + c;
        return result;
    }
    
    public int compareTo(final Object o1) {
        int result;
        if (o1 instanceof Year) {
            final Year y = (Year)o1;
            result = this.year - y.getYear();
        }
        else if (o1 instanceof RegularTimePeriod) {
            result = 0;
        }
        else {
            result = 1;
        }
        return result;
    }
    
    public String toString() {
        return Integer.toString(this.year);
    }
    
    public static Year parseYear(final String s) {
        int y;
        try {
            y = Integer.parseInt(s.trim());
        }
        catch (NumberFormatException e) {
            throw new TimePeriodFormatException("Year.parseYear(string): cannot parse string.");
        }
        try {
            return new Year(y);
        }
        catch (IllegalArgumentException e2) {
            throw new TimePeriodFormatException("Year.parseYear(string): year outside valid range.");
        }
    }
}
