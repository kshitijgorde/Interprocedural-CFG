// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time;

public interface ReadableInstant extends Comparable
{
    long getMillis();
    
    Chronology getChronology();
    
    DateTimeZone getZone();
    
    int get(final DateTimeFieldType p0);
    
    boolean isSupported(final DateTimeFieldType p0);
    
    Instant toInstant();
    
    int compareTo(final Object p0);
    
    boolean isEqual(final ReadableInstant p0);
    
    boolean isAfter(final ReadableInstant p0);
    
    boolean isBefore(final ReadableInstant p0);
    
    boolean equals(final Object p0);
    
    int hashCode();
    
    String toString();
}
