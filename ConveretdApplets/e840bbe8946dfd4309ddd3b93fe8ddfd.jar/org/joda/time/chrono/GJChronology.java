// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.chrono;

import org.joda.time.field.DecoratedDurationField;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.ReadablePartial;
import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.field.BaseDateTimeField;
import java.util.HashMap;
import org.joda.time.DurationField;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import java.util.ArrayList;
import org.joda.time.DateTimeUtils;
import org.joda.time.ReadableInstant;
import org.joda.time.DateTimeZone;
import org.joda.time.Chronology;
import java.util.Map;
import org.joda.time.Instant;

public final class GJChronology extends AssembledChronology
{
    private static final long serialVersionUID = -2545574827706931671L;
    static final Instant DEFAULT_CUTOVER;
    private static final Map cCache;
    private JulianChronology iJulianChronology;
    private GregorianChronology iGregorianChronology;
    private Instant iCutoverInstant;
    private long iCutoverMillis;
    private long iGapDuration;
    
    private static long convertByYear(final long n, final Chronology chronology, final Chronology chronology2) {
        return chronology2.getDateTimeMillis(chronology.year().get(n), chronology.monthOfYear().get(n), chronology.dayOfMonth().get(n), chronology.millisOfDay().get(n));
    }
    
    private static long convertByWeekyear(final long n, final Chronology chronology, final Chronology chronology2) {
        return chronology2.millisOfDay().set(chronology2.dayOfWeek().set(chronology2.weekOfWeekyear().set(chronology2.weekyear().set(0L, chronology.weekyear().get(n)), chronology.weekOfWeekyear().get(n)), chronology.dayOfWeek().get(n)), chronology.millisOfDay().get(n));
    }
    
    public static GJChronology getInstanceUTC() {
        return getInstance(DateTimeZone.UTC, GJChronology.DEFAULT_CUTOVER, 4);
    }
    
    public static GJChronology getInstance() {
        return getInstance(DateTimeZone.getDefault(), GJChronology.DEFAULT_CUTOVER, 4);
    }
    
    public static GJChronology getInstance(final DateTimeZone dateTimeZone) {
        return getInstance(dateTimeZone, GJChronology.DEFAULT_CUTOVER, 4);
    }
    
    public static GJChronology getInstance(final DateTimeZone dateTimeZone, final ReadableInstant readableInstant) {
        return getInstance(dateTimeZone, readableInstant, 4);
    }
    
    public static synchronized GJChronology getInstance(DateTimeZone zone, final ReadableInstant readableInstant, final int n) {
        zone = DateTimeUtils.getZone(zone);
        Instant instant;
        if (readableInstant == null) {
            instant = GJChronology.DEFAULT_CUTOVER;
        }
        else {
            instant = readableInstant.toInstant();
        }
        ArrayList<?> list = GJChronology.cCache.get(zone);
        if (list == null) {
            list = new ArrayList<Object>(2);
            GJChronology.cCache.put(zone, list);
        }
        else {
            int size = list.size();
            while (--size >= 0) {
                final GJChronology gjChronology = list.get(size);
                if (n == gjChronology.getMinimumDaysInFirstWeek() && instant.equals(gjChronology.getGregorianCutover())) {
                    return gjChronology;
                }
            }
        }
        GJChronology gjChronology2;
        if (zone == DateTimeZone.UTC) {
            gjChronology2 = new GJChronology(JulianChronology.getInstance(zone, n), GregorianChronology.getInstance(zone, n), instant);
        }
        else {
            final GJChronology instance = getInstance(DateTimeZone.UTC, instant, n);
            gjChronology2 = new GJChronology(ZonedChronology.getInstance(instance, zone), instance.iJulianChronology, instance.iGregorianChronology, instance.iCutoverInstant);
        }
        list.add(gjChronology2);
        return gjChronology2;
    }
    
    public static GJChronology getInstance(final DateTimeZone dateTimeZone, final long n, final int n2) {
        ReadableInstant readableInstant;
        if (n == GJChronology.DEFAULT_CUTOVER.getMillis()) {
            readableInstant = null;
        }
        else {
            readableInstant = new Instant(n);
        }
        return getInstance(dateTimeZone, readableInstant, n2);
    }
    
    private GJChronology(final JulianChronology julianChronology, final GregorianChronology gregorianChronology, final Instant instant) {
        super(null, new Object[] { julianChronology, gregorianChronology, instant });
    }
    
    private GJChronology(final Chronology chronology, final JulianChronology julianChronology, final GregorianChronology gregorianChronology, final Instant instant) {
        super(chronology, new Object[] { julianChronology, gregorianChronology, instant });
    }
    
    private Object readResolve() {
        return getInstance(this.getZone(), this.iCutoverInstant, this.getMinimumDaysInFirstWeek());
    }
    
    public DateTimeZone getZone() {
        final Chronology base;
        if ((base = this.getBase()) != null) {
            return base.getZone();
        }
        return DateTimeZone.UTC;
    }
    
    public Chronology withUTC() {
        return this.withZone(DateTimeZone.UTC);
    }
    
    public Chronology withZone(DateTimeZone default1) {
        if (default1 == null) {
            default1 = DateTimeZone.getDefault();
        }
        if (default1 == this.getZone()) {
            return this;
        }
        return getInstance(default1, this.iCutoverInstant, this.getMinimumDaysInFirstWeek());
    }
    
    public long getDateTimeMillis(final int n, final int n2, final int n3, final int n4) throws IllegalArgumentException {
        final Chronology base;
        if ((base = this.getBase()) != null) {
            return base.getDateTimeMillis(n, n2, n3, n4);
        }
        long n5 = this.iGregorianChronology.getDateTimeMillis(n, n2, n3, n4);
        if (n5 < this.iCutoverMillis) {
            n5 = this.iJulianChronology.getDateTimeMillis(n, n2, n3, n4);
            if (n5 >= this.iCutoverMillis) {
                throw new IllegalArgumentException("Specified date does not exist");
            }
        }
        return n5;
    }
    
    public long getDateTimeMillis(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) throws IllegalArgumentException {
        final Chronology base;
        if ((base = this.getBase()) != null) {
            return base.getDateTimeMillis(n, n2, n3, n4, n5, n6, n7);
        }
        long n8 = this.iGregorianChronology.getDateTimeMillis(n, n2, n3, n4, n5, n6, n7);
        if (n8 < this.iCutoverMillis) {
            n8 = this.iJulianChronology.getDateTimeMillis(n, n2, n3, n4, n5, n6, n7);
            if (n8 >= this.iCutoverMillis) {
                throw new IllegalArgumentException("Specified date does not exist");
            }
        }
        return n8;
    }
    
    public Instant getGregorianCutover() {
        return this.iCutoverInstant;
    }
    
    public int getMinimumDaysInFirstWeek() {
        return this.iGregorianChronology.getMinimumDaysInFirstWeek();
    }
    
    public boolean equals(final Object o) {
        return super.equals(o);
    }
    
    public int hashCode() {
        return "GJ".hashCode() * 11 + this.iJulianChronology.hashCode() + this.iGregorianChronology.hashCode() + this.iCutoverInstant.hashCode();
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(60);
        sb.append("GJChronology");
        sb.append('[');
        sb.append(this.getZone().getID());
        if (this.iCutoverMillis != GJChronology.DEFAULT_CUTOVER.getMillis()) {
            sb.append(",cutover=");
            DateTimeFormatter dateTimeFormatter;
            if (this.withUTC().dayOfYear().remainder(this.iCutoverMillis) == 0L) {
                dateTimeFormatter = ISODateTimeFormat.date();
            }
            else {
                dateTimeFormatter = ISODateTimeFormat.dateTime();
            }
            dateTimeFormatter.withChronology(this.withUTC()).printTo(sb, this.iCutoverMillis);
        }
        if (this.getMinimumDaysInFirstWeek() != 4) {
            sb.append(",mdfw=");
            sb.append(this.getMinimumDaysInFirstWeek());
        }
        sb.append(']');
        return sb.toString();
    }
    
    protected void assemble(final Fields fields) {
        final Object[] array = (Object[])this.getParam();
        final JulianChronology iJulianChronology = (JulianChronology)array[0];
        final GregorianChronology iGregorianChronology = (GregorianChronology)array[1];
        final Instant iCutoverInstant = (Instant)array[2];
        this.iCutoverMillis = iCutoverInstant.getMillis();
        this.iJulianChronology = iJulianChronology;
        this.iGregorianChronology = iGregorianChronology;
        this.iCutoverInstant = iCutoverInstant;
        if (this.getBase() != null) {
            return;
        }
        if (iJulianChronology.getMinimumDaysInFirstWeek() != iGregorianChronology.getMinimumDaysInFirstWeek()) {
            throw new IllegalArgumentException();
        }
        this.iGapDuration = this.iCutoverMillis - this.julianToGregorianByYear(this.iCutoverMillis);
        fields.copyFieldsFrom(iGregorianChronology);
        if (iGregorianChronology.millisOfDay().get(this.iCutoverMillis) == 0) {
            fields.millisOfSecond = new CutoverField(iJulianChronology.millisOfSecond(), fields.millisOfSecond, this.iCutoverMillis);
            fields.millisOfDay = new CutoverField(iJulianChronology.millisOfDay(), fields.millisOfDay, this.iCutoverMillis);
            fields.secondOfMinute = new CutoverField(iJulianChronology.secondOfMinute(), fields.secondOfMinute, this.iCutoverMillis);
            fields.secondOfDay = new CutoverField(iJulianChronology.secondOfDay(), fields.secondOfDay, this.iCutoverMillis);
            fields.minuteOfHour = new CutoverField(iJulianChronology.minuteOfHour(), fields.minuteOfHour, this.iCutoverMillis);
            fields.minuteOfDay = new CutoverField(iJulianChronology.minuteOfDay(), fields.minuteOfDay, this.iCutoverMillis);
            fields.hourOfDay = new CutoverField(iJulianChronology.hourOfDay(), fields.hourOfDay, this.iCutoverMillis);
            fields.hourOfHalfday = new CutoverField(iJulianChronology.hourOfHalfday(), fields.hourOfHalfday, this.iCutoverMillis);
            fields.clockhourOfDay = new CutoverField(iJulianChronology.clockhourOfDay(), fields.clockhourOfDay, this.iCutoverMillis);
            fields.clockhourOfHalfday = new CutoverField(iJulianChronology.clockhourOfHalfday(), fields.clockhourOfHalfday, this.iCutoverMillis);
            fields.halfdayOfDay = new CutoverField(iJulianChronology.halfdayOfDay(), fields.halfdayOfDay, this.iCutoverMillis);
        }
        fields.era = new CutoverField(iJulianChronology.era(), fields.era, this.iCutoverMillis);
        fields.dayOfYear = new CutoverField(iJulianChronology.dayOfYear(), fields.dayOfYear, iGregorianChronology.year().roundCeiling(this.iCutoverMillis));
        fields.weekOfWeekyear = new CutoverField(iJulianChronology.weekOfWeekyear(), fields.weekOfWeekyear, iGregorianChronology.weekyear().roundCeiling(this.iCutoverMillis), true);
        fields.year = new ImpreciseCutoverField(iJulianChronology.year(), fields.year, this.iCutoverMillis);
        fields.years = fields.year.getDurationField();
        fields.yearOfEra = new ImpreciseCutoverField(iJulianChronology.yearOfEra(), fields.yearOfEra, fields.years, this.iCutoverMillis);
        fields.yearOfCentury = new ImpreciseCutoverField(iJulianChronology.yearOfCentury(), fields.yearOfCentury, fields.years, this.iCutoverMillis);
        fields.centuryOfEra = new ImpreciseCutoverField(iJulianChronology.centuryOfEra(), fields.centuryOfEra, this.iCutoverMillis);
        fields.centuries = fields.centuryOfEra.getDurationField();
        fields.monthOfYear = new ImpreciseCutoverField(iJulianChronology.monthOfYear(), fields.monthOfYear, this.iCutoverMillis);
        fields.months = fields.monthOfYear.getDurationField();
        fields.weekyear = new ImpreciseCutoverField(iJulianChronology.weekyear(), fields.weekyear, null, this.iCutoverMillis, true);
        fields.weekyearOfCentury = new ImpreciseCutoverField(iJulianChronology.weekyearOfCentury(), fields.weekyearOfCentury, fields.weekyears, this.iCutoverMillis);
        fields.weekyears = fields.weekyear.getDurationField();
        final CutoverField dayOfMonth = new CutoverField(iJulianChronology.dayOfMonth(), fields.dayOfMonth, this.iCutoverMillis);
        dayOfMonth.iRangeDurationField = fields.months;
        fields.dayOfMonth = dayOfMonth;
    }
    
    long julianToGregorianByYear(final long n) {
        return convertByYear(n, this.iJulianChronology, this.iGregorianChronology);
    }
    
    long gregorianToJulianByYear(final long n) {
        return convertByYear(n, this.iGregorianChronology, this.iJulianChronology);
    }
    
    long julianToGregorianByWeekyear(final long n) {
        return convertByWeekyear(n, this.iJulianChronology, this.iGregorianChronology);
    }
    
    long gregorianToJulianByWeekyear(final long n) {
        return convertByWeekyear(n, this.iGregorianChronology, this.iJulianChronology);
    }
    
    static {
        DEFAULT_CUTOVER = new Instant(-12219292800000L);
        cCache = new HashMap();
    }
    
    private class CutoverField extends BaseDateTimeField
    {
        private static final long serialVersionUID = 3528501219481026402L;
        final DateTimeField iJulianField;
        final DateTimeField iGregorianField;
        final long iCutover;
        final boolean iConvertByWeekyear;
        protected DurationField iDurationField;
        protected DurationField iRangeDurationField;
        
        CutoverField(final GJChronology gjChronology, final DateTimeField dateTimeField, final DateTimeField dateTimeField2, final long n) {
            this(gjChronology, dateTimeField, dateTimeField2, n, false);
        }
        
        CutoverField(final DateTimeField iJulianField, final DateTimeField iGregorianField, final long iCutover, final boolean iConvertByWeekyear) {
            super(iGregorianField.getType());
            this.iJulianField = iJulianField;
            this.iGregorianField = iGregorianField;
            this.iCutover = iCutover;
            this.iConvertByWeekyear = iConvertByWeekyear;
            this.iDurationField = iGregorianField.getDurationField();
            DurationField iRangeDurationField = iGregorianField.getRangeDurationField();
            if (iRangeDurationField == null) {
                iRangeDurationField = iJulianField.getRangeDurationField();
            }
            this.iRangeDurationField = iRangeDurationField;
        }
        
        public boolean isLenient() {
            return false;
        }
        
        public int get(final long n) {
            if (n >= this.iCutover) {
                return this.iGregorianField.get(n);
            }
            return this.iJulianField.get(n);
        }
        
        public String getAsText(final long n, final Locale locale) {
            if (n >= this.iCutover) {
                return this.iGregorianField.getAsText(n, locale);
            }
            return this.iJulianField.getAsText(n, locale);
        }
        
        public String getAsText(final int n, final Locale locale) {
            return this.iGregorianField.getAsText(n, locale);
        }
        
        public String getAsShortText(final long n, final Locale locale) {
            if (n >= this.iCutover) {
                return this.iGregorianField.getAsShortText(n, locale);
            }
            return this.iJulianField.getAsShortText(n, locale);
        }
        
        public String getAsShortText(final int n, final Locale locale) {
            return this.iGregorianField.getAsShortText(n, locale);
        }
        
        public long add(final long n, final int n2) {
            return this.iGregorianField.add(n, n2);
        }
        
        public long add(final long n, final long n2) {
            return this.iGregorianField.add(n, n2);
        }
        
        public int[] add(final ReadablePartial readablePartial, final int n, final int[] array, final int n2) {
            if (n2 == 0) {
                return array;
            }
            if (DateTimeUtils.isContiguous(readablePartial)) {
                long set = 0L;
                for (int i = 0; i < readablePartial.size(); ++i) {
                    set = readablePartial.getFieldType(i).getField(GJChronology.this).set(set, array[i]);
                }
                return GJChronology.this.get(readablePartial, this.add(set, n2));
            }
            return super.add(readablePartial, n, array, n2);
        }
        
        public int getDifference(final long n, final long n2) {
            return this.iGregorianField.getDifference(n, n2);
        }
        
        public long getDifferenceAsLong(final long n, final long n2) {
            return this.iGregorianField.getDifferenceAsLong(n, n2);
        }
        
        public long set(long n, final int n2) {
            if (n >= this.iCutover) {
                n = this.iGregorianField.set(n, n2);
                if (n < this.iCutover) {
                    if (n + GJChronology.this.iGapDuration < this.iCutover) {
                        n = this.gregorianToJulian(n);
                    }
                    if (this.get(n) != n2) {
                        throw new IllegalFieldValueException(this.iGregorianField.getType(), new Integer(n2), null, null);
                    }
                }
            }
            else {
                n = this.iJulianField.set(n, n2);
                if (n >= this.iCutover) {
                    if (n - GJChronology.this.iGapDuration >= this.iCutover) {
                        n = this.julianToGregorian(n);
                    }
                    if (this.get(n) != n2) {
                        throw new IllegalFieldValueException(this.iJulianField.getType(), new Integer(n2), null, null);
                    }
                }
            }
            return n;
        }
        
        public long set(long n, final String s, final Locale locale) {
            if (n >= this.iCutover) {
                n = this.iGregorianField.set(n, s, locale);
                if (n < this.iCutover && n + GJChronology.this.iGapDuration < this.iCutover) {
                    n = this.gregorianToJulian(n);
                }
            }
            else {
                n = this.iJulianField.set(n, s, locale);
                if (n >= this.iCutover && n - GJChronology.this.iGapDuration >= this.iCutover) {
                    n = this.julianToGregorian(n);
                }
            }
            return n;
        }
        
        public DurationField getDurationField() {
            return this.iDurationField;
        }
        
        public DurationField getRangeDurationField() {
            return this.iRangeDurationField;
        }
        
        public boolean isLeap(final long n) {
            if (n >= this.iCutover) {
                return this.iGregorianField.isLeap(n);
            }
            return this.iJulianField.isLeap(n);
        }
        
        public int getLeapAmount(final long n) {
            if (n >= this.iCutover) {
                return this.iGregorianField.getLeapAmount(n);
            }
            return this.iJulianField.getLeapAmount(n);
        }
        
        public DurationField getLeapDurationField() {
            return this.iGregorianField.getLeapDurationField();
        }
        
        public int getMinimumValue() {
            return this.iJulianField.getMinimumValue();
        }
        
        public int getMinimumValue(final ReadablePartial readablePartial) {
            return this.iJulianField.getMinimumValue(readablePartial);
        }
        
        public int getMinimumValue(final ReadablePartial readablePartial, final int[] array) {
            return this.iJulianField.getMinimumValue(readablePartial, array);
        }
        
        public int getMinimumValue(long set) {
            if (set < this.iCutover) {
                return this.iJulianField.getMinimumValue(set);
            }
            int n = this.iGregorianField.getMinimumValue(set);
            set = this.iGregorianField.set(set, n);
            if (set < this.iCutover) {
                n = this.iGregorianField.get(this.iCutover);
            }
            return n;
        }
        
        public int getMaximumValue() {
            return this.iGregorianField.getMaximumValue();
        }
        
        public int getMaximumValue(long set) {
            if (set >= this.iCutover) {
                return this.iGregorianField.getMaximumValue(set);
            }
            int n = this.iJulianField.getMaximumValue(set);
            set = this.iJulianField.set(set, n);
            if (set >= this.iCutover) {
                n = this.iJulianField.get(this.iJulianField.add(this.iCutover, -1));
            }
            return n;
        }
        
        public int getMaximumValue(final ReadablePartial readablePartial) {
            return this.getMaximumValue(GJChronology.getInstanceUTC().set(readablePartial, 0L));
        }
        
        public int getMaximumValue(final ReadablePartial readablePartial, final int[] array) {
            final GJChronology instanceUTC = GJChronology.getInstanceUTC();
            long set = 0L;
            for (int i = 0; i < readablePartial.size(); ++i) {
                final DateTimeField field = readablePartial.getFieldType(i).getField(instanceUTC);
                if (array[i] <= field.getMaximumValue(set)) {
                    set = field.set(set, array[i]);
                }
            }
            return this.getMaximumValue(set);
        }
        
        public long roundFloor(long n) {
            if (n >= this.iCutover) {
                n = this.iGregorianField.roundFloor(n);
                if (n < this.iCutover && n + GJChronology.this.iGapDuration < this.iCutover) {
                    n = this.gregorianToJulian(n);
                }
            }
            else {
                n = this.iJulianField.roundFloor(n);
            }
            return n;
        }
        
        public long roundCeiling(long n) {
            if (n >= this.iCutover) {
                n = this.iGregorianField.roundCeiling(n);
            }
            else {
                n = this.iJulianField.roundCeiling(n);
                if (n >= this.iCutover && n - GJChronology.this.iGapDuration >= this.iCutover) {
                    n = this.julianToGregorian(n);
                }
            }
            return n;
        }
        
        public int getMaximumTextLength(final Locale locale) {
            return Math.max(this.iJulianField.getMaximumTextLength(locale), this.iGregorianField.getMaximumTextLength(locale));
        }
        
        public int getMaximumShortTextLength(final Locale locale) {
            return Math.max(this.iJulianField.getMaximumShortTextLength(locale), this.iGregorianField.getMaximumShortTextLength(locale));
        }
        
        protected long julianToGregorian(final long n) {
            if (this.iConvertByWeekyear) {
                return GJChronology.this.julianToGregorianByWeekyear(n);
            }
            return GJChronology.this.julianToGregorianByYear(n);
        }
        
        protected long gregorianToJulian(final long n) {
            if (this.iConvertByWeekyear) {
                return GJChronology.this.gregorianToJulianByWeekyear(n);
            }
            return GJChronology.this.gregorianToJulianByYear(n);
        }
    }
    
    private final class ImpreciseCutoverField extends CutoverField
    {
        private static final long serialVersionUID = 3410248757173576441L;
        
        ImpreciseCutoverField(final GJChronology gjChronology, final DateTimeField dateTimeField, final DateTimeField dateTimeField2, final long n) {
            this(gjChronology, dateTimeField, dateTimeField2, null, n, false);
        }
        
        ImpreciseCutoverField(final GJChronology gjChronology, final DateTimeField dateTimeField, final DateTimeField dateTimeField2, final DurationField durationField, final long n) {
            this(gjChronology, dateTimeField, dateTimeField2, durationField, n, false);
        }
        
        ImpreciseCutoverField(final DateTimeField dateTimeField, final DateTimeField dateTimeField2, DurationField iDurationField, final long n, final boolean b) {
            super(dateTimeField, dateTimeField2, n, b);
            if (iDurationField == null) {
                iDurationField = new LinkedDurationField(this.iDurationField, this);
            }
            this.iDurationField = iDurationField;
        }
        
        public long add(long n, final int n2) {
            if (n >= this.iCutover) {
                n = this.iGregorianField.add(n, n2);
                if (n < this.iCutover && n + GJChronology.this.iGapDuration < this.iCutover) {
                    n = this.gregorianToJulian(n);
                }
            }
            else {
                n = this.iJulianField.add(n, n2);
                if (n >= this.iCutover && n - GJChronology.this.iGapDuration >= this.iCutover) {
                    n = this.julianToGregorian(n);
                }
            }
            return n;
        }
        
        public long add(long n, final long n2) {
            if (n >= this.iCutover) {
                n = this.iGregorianField.add(n, n2);
                if (n < this.iCutover && n + GJChronology.this.iGapDuration < this.iCutover) {
                    n = this.gregorianToJulian(n);
                }
            }
            else {
                n = this.iJulianField.add(n, n2);
                if (n >= this.iCutover && n - GJChronology.this.iGapDuration >= this.iCutover) {
                    n = this.julianToGregorian(n);
                }
            }
            return n;
        }
        
        public int getDifference(long n, final long n2) {
            if (n >= this.iCutover) {
                if (n2 >= this.iCutover) {
                    return this.iGregorianField.getDifference(n, n2);
                }
                n = this.gregorianToJulian(n);
                return this.iJulianField.getDifference(n, n2);
            }
            else {
                if (n2 < this.iCutover) {
                    return this.iJulianField.getDifference(n, n2);
                }
                n = this.julianToGregorian(n);
                return this.iGregorianField.getDifference(n, n2);
            }
        }
        
        public long getDifferenceAsLong(long n, final long n2) {
            if (n >= this.iCutover) {
                if (n2 >= this.iCutover) {
                    return this.iGregorianField.getDifferenceAsLong(n, n2);
                }
                n = this.gregorianToJulian(n);
                return this.iJulianField.getDifferenceAsLong(n, n2);
            }
            else {
                if (n2 < this.iCutover) {
                    return this.iJulianField.getDifferenceAsLong(n, n2);
                }
                n = this.julianToGregorian(n);
                return this.iGregorianField.getDifferenceAsLong(n, n2);
            }
        }
        
        public int getMinimumValue(final long n) {
            if (n >= this.iCutover) {
                return this.iGregorianField.getMinimumValue(n);
            }
            return this.iJulianField.getMinimumValue(n);
        }
        
        public int getMaximumValue(final long n) {
            if (n >= this.iCutover) {
                return this.iGregorianField.getMaximumValue(n);
            }
            return this.iJulianField.getMaximumValue(n);
        }
    }
    
    private static class LinkedDurationField extends DecoratedDurationField
    {
        private static final long serialVersionUID = 4097975388007713084L;
        private final ImpreciseCutoverField iField;
        
        LinkedDurationField(final DurationField durationField, final ImpreciseCutoverField iField) {
            super(durationField, durationField.getType());
            this.iField = iField;
        }
        
        public long add(final long n, final int n2) {
            return this.iField.add(n, n2);
        }
        
        public long add(final long n, final long n2) {
            return this.iField.add(n, n2);
        }
        
        public int getDifference(final long n, final long n2) {
            return this.iField.getDifference(n, n2);
        }
        
        public long getDifferenceAsLong(final long n, final long n2) {
            return this.iField.getDifferenceAsLong(n, n2);
        }
    }
}
