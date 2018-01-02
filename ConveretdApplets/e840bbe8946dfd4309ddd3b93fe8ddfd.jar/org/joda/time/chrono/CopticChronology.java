// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.chrono;

import java.util.HashMap;
import org.joda.time.field.SkipDateTimeField;
import org.joda.time.ReadableDateTime;
import org.joda.time.DateTime;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import java.util.Map;
import org.joda.time.DateTimeField;

public final class CopticChronology extends BasicFixedMonthChronology
{
    private static final long serialVersionUID = -5972804258688333942L;
    public static final int AM = 1;
    private static final DateTimeField ERA_FIELD;
    private static final int MIN_YEAR = -292269337;
    private static final int MAX_YEAR = 292272708;
    private static final Map cCache;
    private static final CopticChronology INSTANCE_UTC;
    
    public static CopticChronology getInstanceUTC() {
        return CopticChronology.INSTANCE_UTC;
    }
    
    public static CopticChronology getInstance() {
        return getInstance(DateTimeZone.getDefault(), 4);
    }
    
    public static CopticChronology getInstance(final DateTimeZone dateTimeZone) {
        return getInstance(dateTimeZone, 4);
    }
    
    public static CopticChronology getInstance(DateTimeZone default1, final int n) {
        if (default1 == null) {
            default1 = DateTimeZone.getDefault();
        }
        CopticChronology instance;
        synchronized (CopticChronology.cCache) {
            CopticChronology[] array = CopticChronology.cCache.get(default1);
            if (array == null) {
                array = new CopticChronology[7];
                CopticChronology.cCache.put(default1, array);
            }
            try {
                instance = array[n - 1];
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                throw new IllegalArgumentException("Invalid min days in first week: " + n);
            }
            if (instance == null) {
                if (default1 == DateTimeZone.UTC) {
                    instance = new CopticChronology(null, null, n);
                    instance = new CopticChronology(LimitChronology.getInstance(instance, new DateTime(1, 1, 1, 0, 0, 0, 0, instance), null), null, n);
                }
                else {
                    instance = getInstance(DateTimeZone.UTC, n);
                    instance = new CopticChronology(ZonedChronology.getInstance(instance, default1), null, n);
                }
                array[n - 1] = instance;
            }
        }
        return instance;
    }
    
    CopticChronology(final Chronology chronology, final Object o, final int n) {
        super(chronology, o, n);
    }
    
    private Object readResolve() {
        final Chronology base = this.getBase();
        final int minimumDaysInFirstWeek = this.getMinimumDaysInFirstWeek();
        final int n = (minimumDaysInFirstWeek == 0) ? 4 : minimumDaysInFirstWeek;
        return (base == null) ? getInstance(DateTimeZone.UTC, n) : getInstance(base.getZone(), n);
    }
    
    public Chronology withUTC() {
        return CopticChronology.INSTANCE_UTC;
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
    
    long calculateFirstDayOfYearMillis(final int n) {
        final int n2 = n - 1687;
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
        return (n2 * 365L + n3) * 86400000L + 21859200000L;
    }
    
    int getMinYear() {
        return -292269337;
    }
    
    int getMaxYear() {
        return 292272708;
    }
    
    long getApproxMillisAtEpochDividedByTwo() {
        return 26607895200000L;
    }
    
    protected void assemble(final Fields fields) {
        if (this.getBase() == null) {
            super.assemble(fields);
            fields.year = new SkipDateTimeField(this, fields.year);
            fields.weekyear = new SkipDateTimeField(this, fields.weekyear);
            fields.era = CopticChronology.ERA_FIELD;
            fields.monthOfYear = new BasicMonthOfYearDateTimeField(this, 13);
            fields.months = fields.monthOfYear.getDurationField();
        }
    }
    
    static {
        ERA_FIELD = new BasicSingleEraDateTimeField("AM");
        cCache = new HashMap();
        INSTANCE_UTC = getInstance(DateTimeZone.UTC);
    }
}
