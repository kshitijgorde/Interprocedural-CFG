// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time;

import org.joda.time.chrono.ISOChronology;
import java.security.Permission;

public class DateTimeUtils
{
    private static final SystemMillisProvider SYSTEM_MILLIS_PROVIDER;
    private static volatile MillisProvider cMillisProvider;
    
    public static final long currentTimeMillis() {
        return DateTimeUtils.cMillisProvider.getMillis();
    }
    
    public static final void setCurrentMillisSystem() throws SecurityException {
        checkPermission();
        DateTimeUtils.cMillisProvider = DateTimeUtils.SYSTEM_MILLIS_PROVIDER;
    }
    
    public static final void setCurrentMillisFixed(final long n) throws SecurityException {
        checkPermission();
        DateTimeUtils.cMillisProvider = new FixedMillisProvider(n);
    }
    
    public static final void setCurrentMillisOffset(final long n) throws SecurityException {
        checkPermission();
        if (n == 0L) {
            DateTimeUtils.cMillisProvider = DateTimeUtils.SYSTEM_MILLIS_PROVIDER;
        }
        else {
            DateTimeUtils.cMillisProvider = new OffsetMillisProvider(n);
        }
    }
    
    private static void checkPermission() throws SecurityException {
        final SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new JodaTimePermission("CurrentTime.setProvider"));
        }
    }
    
    public static final long getInstantMillis(final ReadableInstant readableInstant) {
        if (readableInstant == null) {
            return currentTimeMillis();
        }
        return readableInstant.getMillis();
    }
    
    public static final Chronology getInstantChronology(final ReadableInstant readableInstant) {
        if (readableInstant == null) {
            return ISOChronology.getInstance();
        }
        final Chronology chronology = readableInstant.getChronology();
        if (chronology == null) {
            return ISOChronology.getInstance();
        }
        return chronology;
    }
    
    public static final Chronology getIntervalChronology(final ReadableInstant readableInstant, final ReadableInstant readableInstant2) {
        Chronology chronology = null;
        if (readableInstant != null) {
            chronology = readableInstant.getChronology();
        }
        else if (readableInstant2 != null) {
            chronology = readableInstant2.getChronology();
        }
        if (chronology == null) {
            chronology = ISOChronology.getInstance();
        }
        return chronology;
    }
    
    public static final Chronology getIntervalChronology(final ReadableInterval readableInterval) {
        if (readableInterval == null) {
            return ISOChronology.getInstance();
        }
        final Chronology chronology = readableInterval.getChronology();
        if (chronology == null) {
            return ISOChronology.getInstance();
        }
        return chronology;
    }
    
    public static final ReadableInterval getReadableInterval(ReadableInterval readableInterval) {
        if (readableInterval == null) {
            final long currentTimeMillis = currentTimeMillis();
            readableInterval = new Interval(currentTimeMillis, currentTimeMillis);
        }
        return readableInterval;
    }
    
    public static final Chronology getChronology(final Chronology chronology) {
        if (chronology == null) {
            return ISOChronology.getInstance();
        }
        return chronology;
    }
    
    public static final DateTimeZone getZone(final DateTimeZone dateTimeZone) {
        if (dateTimeZone == null) {
            return DateTimeZone.getDefault();
        }
        return dateTimeZone;
    }
    
    public static final PeriodType getPeriodType(final PeriodType periodType) {
        if (periodType == null) {
            return PeriodType.standard();
        }
        return periodType;
    }
    
    public static final long getDurationMillis(final ReadableDuration readableDuration) {
        if (readableDuration == null) {
            return 0L;
        }
        return readableDuration.getMillis();
    }
    
    public static final boolean isContiguous(final ReadablePartial readablePartial) {
        if (readablePartial == null) {
            throw new IllegalArgumentException("Partial must not be null");
        }
        DurationFieldType type = null;
        for (int i = 0; i < readablePartial.size(); ++i) {
            final DateTimeField field = readablePartial.getField(i);
            if (i > 0 && field.getRangeDurationField().getType() != type) {
                return false;
            }
            type = field.getDurationField().getType();
        }
        return true;
    }
    
    static {
        SYSTEM_MILLIS_PROVIDER = new SystemMillisProvider();
        DateTimeUtils.cMillisProvider = DateTimeUtils.SYSTEM_MILLIS_PROVIDER;
    }
    
    abstract static class MillisProvider
    {
        abstract long getMillis();
    }
    
    static class SystemMillisProvider extends MillisProvider
    {
        long getMillis() {
            return System.currentTimeMillis();
        }
    }
    
    static class FixedMillisProvider extends MillisProvider
    {
        private final long iMillis;
        
        FixedMillisProvider(final long iMillis) {
            this.iMillis = iMillis;
        }
        
        long getMillis() {
            return this.iMillis;
        }
    }
    
    static class OffsetMillisProvider extends MillisProvider
    {
        private final long iMillis;
        
        OffsetMillisProvider(final long iMillis) {
            this.iMillis = iMillis;
        }
        
        long getMillis() {
            return System.currentTimeMillis() + this.iMillis;
        }
    }
}
