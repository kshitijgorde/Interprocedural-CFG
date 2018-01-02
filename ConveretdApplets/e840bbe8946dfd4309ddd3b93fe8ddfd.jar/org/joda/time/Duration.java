// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time;

import org.joda.time.field.FieldUtils;
import java.io.Serializable;
import org.joda.time.base.BaseDuration;

public final class Duration extends BaseDuration implements ReadableDuration, Serializable
{
    public static final Duration ZERO;
    private static final long serialVersionUID = 2471658376918L;
    
    public static Duration standardDays(final long n) {
        if (n == 0L) {
            return Duration.ZERO;
        }
        return new Duration(FieldUtils.safeMultiply(n, 86400000));
    }
    
    public static Duration standardHours(final long n) {
        if (n == 0L) {
            return Duration.ZERO;
        }
        return new Duration(FieldUtils.safeMultiply(n, 3600000));
    }
    
    public static Duration standardMinutes(final long n) {
        if (n == 0L) {
            return Duration.ZERO;
        }
        return new Duration(FieldUtils.safeMultiply(n, 60000));
    }
    
    public static Duration standardSeconds(final long n) {
        if (n == 0L) {
            return Duration.ZERO;
        }
        return new Duration(FieldUtils.safeMultiply(n, 1000));
    }
    
    public Duration(final long n) {
        super(n);
    }
    
    public Duration(final long n, final long n2) {
        super(n, n2);
    }
    
    public Duration(final ReadableInstant readableInstant, final ReadableInstant readableInstant2) {
        super(readableInstant, readableInstant2);
    }
    
    public Duration(final Object o) {
        super(o);
    }
    
    public long getStandardSeconds() {
        return this.getMillis() / 1000L;
    }
    
    public Duration toDuration() {
        return this;
    }
    
    public Seconds toStandardSeconds() {
        return Seconds.seconds(FieldUtils.safeToInt(this.getStandardSeconds()));
    }
    
    public Duration withMillis(final long n) {
        if (n == this.getMillis()) {
            return this;
        }
        return new Duration(n);
    }
    
    public Duration withDurationAdded(final long n, final int n2) {
        if (n == 0L || n2 == 0) {
            return this;
        }
        return new Duration(FieldUtils.safeAdd(this.getMillis(), FieldUtils.safeMultiply(n, n2)));
    }
    
    public Duration withDurationAdded(final ReadableDuration readableDuration, final int n) {
        if (readableDuration == null || n == 0) {
            return this;
        }
        return this.withDurationAdded(readableDuration.getMillis(), n);
    }
    
    public Duration plus(final long n) {
        return this.withDurationAdded(n, 1);
    }
    
    public Duration plus(final ReadableDuration readableDuration) {
        if (readableDuration == null) {
            return this;
        }
        return this.withDurationAdded(readableDuration.getMillis(), 1);
    }
    
    public Duration minus(final long n) {
        return this.withDurationAdded(n, -1);
    }
    
    public Duration minus(final ReadableDuration readableDuration) {
        if (readableDuration == null) {
            return this;
        }
        return this.withDurationAdded(readableDuration.getMillis(), -1);
    }
    
    static {
        ZERO = new Duration(0L);
    }
}
