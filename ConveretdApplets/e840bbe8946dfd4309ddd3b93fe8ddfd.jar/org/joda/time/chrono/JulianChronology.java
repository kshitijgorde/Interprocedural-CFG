// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.chrono;

import java.util.HashMap;
import org.joda.time.field.SkipDateTimeField;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.DateTimeFieldType;
import java.util.Map;

public final class JulianChronology extends BasicGJChronology
{
    private static final long serialVersionUID = -8731039522547897247L;
    private static final long MILLIS_PER_YEAR = 31557600000L;
    private static final long MILLIS_PER_MONTH = 2629800000L;
    private static final int MIN_YEAR = -292269054;
    private static final int MAX_YEAR = 292272992;
    private static final JulianChronology INSTANCE_UTC;
    private static final Map cCache;
    
    static int adjustYearForSet(int n) {
        if (n <= 0) {
            if (n == 0) {
                throw new IllegalFieldValueException(DateTimeFieldType.year(), new Integer(n), null, null);
            }
            ++n;
        }
        return n;
    }
    
    public static JulianChronology getInstanceUTC() {
        return JulianChronology.INSTANCE_UTC;
    }
    
    public static JulianChronology getInstance() {
        return getInstance(DateTimeZone.getDefault(), 4);
    }
    
    public static JulianChronology getInstance(final DateTimeZone dateTimeZone) {
        return getInstance(dateTimeZone, 4);
    }
    
    public static JulianChronology getInstance(DateTimeZone default1, final int n) {
        if (default1 == null) {
            default1 = DateTimeZone.getDefault();
        }
        JulianChronology instance;
        synchronized (JulianChronology.cCache) {
            JulianChronology[] array = JulianChronology.cCache.get(default1);
            if (array == null) {
                array = new JulianChronology[7];
                JulianChronology.cCache.put(default1, array);
            }
            try {
                instance = array[n - 1];
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                throw new IllegalArgumentException("Invalid min days in first week: " + n);
            }
            if (instance == null) {
                if (default1 == DateTimeZone.UTC) {
                    instance = new JulianChronology(null, null, n);
                }
                else {
                    instance = getInstance(DateTimeZone.UTC, n);
                    instance = new JulianChronology(ZonedChronology.getInstance(instance, default1), null, n);
                }
                array[n - 1] = instance;
            }
        }
        return instance;
    }
    
    JulianChronology(final Chronology chronology, final Object o, final int n) {
        super(chronology, o, n);
    }
    
    private Object readResolve() {
        final Chronology base = this.getBase();
        final int minimumDaysInFirstWeek = this.getMinimumDaysInFirstWeek();
        final int n = (minimumDaysInFirstWeek == 0) ? 4 : minimumDaysInFirstWeek;
        return (base == null) ? getInstance(DateTimeZone.UTC, n) : getInstance(base.getZone(), n);
    }
    
    public Chronology withUTC() {
        return JulianChronology.INSTANCE_UTC;
    }
    
    public Chronology withZone(DateTimeZone default1) {
        if (default1 == null) {
            default1 = DateTimeZone.getDefault();
        }
        if (default1 == this.getZone()) {
            return this;
        }
        return getInstance(default1);
    }
    
    long getDateMidnightMillis(final int n, final int n2, final int n3) throws IllegalArgumentException {
        return super.getDateMidnightMillis(adjustYearForSet(n), n2, n3);
    }
    
    boolean isLeapYear(final int n) {
        return (n & 0x3) == 0x0;
    }
    
    long calculateFirstDayOfYearMillis(final int n) {
        final int n2 = n - 1968;
        int n3;
        if (n2 <= 0) {
            n3 = n2 + 3 >> 2;
        }
        else {
            n3 = n2 >> 2;
            if (!this.isLeapYear(n)) {
                ++n3;
            }
        }
        return (n2 * 365L + n3) * 86400000L - 62035200000L;
    }
    
    int getMinYear() {
        return -292269054;
    }
    
    int getMaxYear() {
        return 292272992;
    }
    
    long getAverageMillisPerYear() {
        return 31557600000L;
    }
    
    long getAverageMillisPerYearDividedByTwo() {
        return 15778800000L;
    }
    
    long getAverageMillisPerMonth() {
        return 2629800000L;
    }
    
    long getApproxMillisAtEpochDividedByTwo() {
        return 31083663600000L;
    }
    
    protected void assemble(final Fields fields) {
        if (this.getBase() == null) {
            super.assemble(fields);
            fields.year = new SkipDateTimeField(this, fields.year);
            fields.weekyear = new SkipDateTimeField(this, fields.weekyear);
        }
    }
    
    static {
        cCache = new HashMap();
        INSTANCE_UTC = getInstance(DateTimeZone.UTC);
    }
}
