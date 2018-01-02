// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.base;

import org.joda.time.format.FormatUtils;
import org.joda.time.Period;
import org.joda.time.Duration;
import org.joda.time.ReadableDuration;

public abstract class AbstractDuration implements ReadableDuration
{
    public Duration toDuration() {
        return new Duration(this.getMillis());
    }
    
    public Period toPeriod() {
        return new Period(this.getMillis());
    }
    
    public int compareTo(final Object o) {
        final ReadableDuration readableDuration = (ReadableDuration)o;
        final long millis = super.getMillis();
        final long millis2 = readableDuration.getMillis();
        if (millis < millis2) {
            return -1;
        }
        if (millis > millis2) {
            return 1;
        }
        return 0;
    }
    
    public boolean isEqual(ReadableDuration zero) {
        if (zero == null) {
            zero = Duration.ZERO;
        }
        return this.compareTo(zero) == 0;
    }
    
    public boolean isLongerThan(ReadableDuration zero) {
        if (zero == null) {
            zero = Duration.ZERO;
        }
        return this.compareTo(zero) > 0;
    }
    
    public boolean isShorterThan(ReadableDuration zero) {
        if (zero == null) {
            zero = Duration.ZERO;
        }
        return this.compareTo(zero) < 0;
    }
    
    public boolean equals(final Object o) {
        return this == o || (o instanceof ReadableDuration && this.getMillis() == ((ReadableDuration)o).getMillis());
    }
    
    public int hashCode() {
        final long millis = this.getMillis();
        return (int)(millis ^ millis >>> 32);
    }
    
    public String toString() {
        final long millis = this.getMillis();
        final StringBuffer sb = new StringBuffer();
        sb.append("PT");
        FormatUtils.appendUnpaddedInteger(sb, millis / 1000L);
        final long abs = Math.abs(millis % 1000L);
        if (abs > 0L) {
            sb.append('.');
            FormatUtils.appendPaddedInteger(sb, abs, 3);
        }
        sb.append('S');
        return sb.toString();
    }
}
