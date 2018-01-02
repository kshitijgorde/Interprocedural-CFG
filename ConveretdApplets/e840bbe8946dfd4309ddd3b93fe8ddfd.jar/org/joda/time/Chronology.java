// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time;

import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.BuddhistChronology;
import org.joda.time.chrono.JulianChronology;
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.chrono.GJChronology;
import org.joda.time.chrono.ISOChronology;

public abstract class Chronology
{
    public static Chronology getISO() {
        return ISOChronology.getInstance();
    }
    
    public static Chronology getISOUTC() {
        return ISOChronology.getInstanceUTC();
    }
    
    public static Chronology getISO(final DateTimeZone dateTimeZone) {
        return ISOChronology.getInstance(dateTimeZone);
    }
    
    public static Chronology getGJ() {
        return GJChronology.getInstance();
    }
    
    public static Chronology getGJUTC() {
        return GJChronology.getInstanceUTC();
    }
    
    public static Chronology getGJ(final DateTimeZone dateTimeZone) {
        return GJChronology.getInstance(dateTimeZone);
    }
    
    public static Chronology getGregorian() {
        return GregorianChronology.getInstance();
    }
    
    public static Chronology getGregorianUTC() {
        return GregorianChronology.getInstanceUTC();
    }
    
    public static Chronology getGregorian(final DateTimeZone dateTimeZone) {
        return GregorianChronology.getInstance(dateTimeZone);
    }
    
    public static Chronology getJulian() {
        return JulianChronology.getInstance();
    }
    
    public static Chronology getJulianUTC() {
        return JulianChronology.getInstanceUTC();
    }
    
    public static Chronology getJulian(final DateTimeZone dateTimeZone) {
        return JulianChronology.getInstance(dateTimeZone);
    }
    
    public static Chronology getBuddhist() {
        return BuddhistChronology.getInstance();
    }
    
    public static Chronology getBuddhistUTC() {
        return BuddhistChronology.getInstanceUTC();
    }
    
    public static Chronology getBuddhist(final DateTimeZone dateTimeZone) {
        return BuddhistChronology.getInstance(dateTimeZone);
    }
    
    public static Chronology getCoptic() {
        return CopticChronology.getInstance();
    }
    
    public static Chronology getCopticUTC() {
        return CopticChronology.getInstanceUTC();
    }
    
    public static Chronology getCoptic(final DateTimeZone dateTimeZone) {
        return CopticChronology.getInstance(dateTimeZone);
    }
    
    public abstract DateTimeZone getZone();
    
    public abstract Chronology withUTC();
    
    public abstract Chronology withZone(final DateTimeZone p0);
    
    public abstract long getDateTimeMillis(final int p0, final int p1, final int p2, final int p3);
    
    public abstract long getDateTimeMillis(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6);
    
    public abstract long getDateTimeMillis(final long p0, final int p1, final int p2, final int p3, final int p4);
    
    public abstract void validate(final ReadablePartial p0, final int[] p1);
    
    public abstract int[] get(final ReadablePartial p0, final long p1);
    
    public abstract long set(final ReadablePartial p0, final long p1);
    
    public abstract int[] get(final ReadablePeriod p0, final long p1, final long p2);
    
    public abstract int[] get(final ReadablePeriod p0, final long p1);
    
    public abstract long add(final ReadablePeriod p0, final long p1, final int p2);
    
    public abstract long add(final long p0, final long p1, final int p2);
    
    public abstract DurationField millis();
    
    public abstract DateTimeField millisOfSecond();
    
    public abstract DateTimeField millisOfDay();
    
    public abstract DurationField seconds();
    
    public abstract DateTimeField secondOfMinute();
    
    public abstract DateTimeField secondOfDay();
    
    public abstract DurationField minutes();
    
    public abstract DateTimeField minuteOfHour();
    
    public abstract DateTimeField minuteOfDay();
    
    public abstract DurationField hours();
    
    public abstract DateTimeField hourOfDay();
    
    public abstract DateTimeField clockhourOfDay();
    
    public abstract DurationField halfdays();
    
    public abstract DateTimeField hourOfHalfday();
    
    public abstract DateTimeField clockhourOfHalfday();
    
    public abstract DateTimeField halfdayOfDay();
    
    public abstract DurationField days();
    
    public abstract DateTimeField dayOfWeek();
    
    public abstract DateTimeField dayOfMonth();
    
    public abstract DateTimeField dayOfYear();
    
    public abstract DurationField weeks();
    
    public abstract DateTimeField weekOfWeekyear();
    
    public abstract DurationField weekyears();
    
    public abstract DateTimeField weekyear();
    
    public abstract DateTimeField weekyearOfCentury();
    
    public abstract DurationField months();
    
    public abstract DateTimeField monthOfYear();
    
    public abstract DurationField years();
    
    public abstract DateTimeField year();
    
    public abstract DateTimeField yearOfEra();
    
    public abstract DateTimeField yearOfCentury();
    
    public abstract DurationField centuries();
    
    public abstract DateTimeField centuryOfEra();
    
    public abstract DurationField eras();
    
    public abstract DateTimeField era();
    
    public abstract String toString();
}
