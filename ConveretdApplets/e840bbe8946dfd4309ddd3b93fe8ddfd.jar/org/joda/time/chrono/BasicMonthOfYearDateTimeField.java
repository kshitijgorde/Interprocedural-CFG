// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.chrono;

import org.joda.time.DurationField;
import org.joda.time.DateTimeField;
import org.joda.time.field.FieldUtils;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.ReadablePartial;
import org.joda.time.DateTimeFieldType;
import org.joda.time.field.ImpreciseDateTimeField;

class BasicMonthOfYearDateTimeField extends ImpreciseDateTimeField
{
    private static final long serialVersionUID = -8258715387168736L;
    private static final int MIN = 1;
    private final BasicChronology iChronology;
    private final int iMax;
    private final int iLeapMonth;
    
    BasicMonthOfYearDateTimeField(final BasicChronology iChronology, final int iLeapMonth) {
        super(DateTimeFieldType.monthOfYear(), iChronology.getAverageMillisPerMonth());
        this.iChronology = iChronology;
        this.iMax = this.iChronology.getMaxMonth();
        this.iLeapMonth = iLeapMonth;
    }
    
    public boolean isLenient() {
        return false;
    }
    
    public int get(final long n) {
        return this.iChronology.getMonthOfYear(n);
    }
    
    public long add(final long n, final int n2) {
        if (n2 == 0) {
            return n;
        }
        final long n3 = this.iChronology.getMillisOfDay(n);
        final int year = this.iChronology.getYear(n);
        final int monthOfYear = this.iChronology.getMonthOfYear(n, year);
        final int n4 = monthOfYear - 1 + n2;
        int n5;
        int n6;
        if (n4 >= 0) {
            n5 = year + n4 / this.iMax;
            n6 = n4 % this.iMax + 1;
        }
        else {
            n5 = year + n4 / this.iMax - 1;
            int iMax = Math.abs(n4) % this.iMax;
            if (iMax == 0) {
                iMax = this.iMax;
            }
            n6 = this.iMax - iMax + 1;
            if (n6 == 1) {
                ++n5;
            }
        }
        int dayOfMonth = this.iChronology.getDayOfMonth(n, year, monthOfYear);
        final int daysInYearMonth = this.iChronology.getDaysInYearMonth(n5, n6);
        if (dayOfMonth > daysInYearMonth) {
            dayOfMonth = daysInYearMonth;
        }
        return this.iChronology.getYearMonthDayMillis(n5, n6, dayOfMonth) + n3;
    }
    
    public long add(final long n, final long n2) {
        final int n3 = (int)n2;
        if (n3 == n2) {
            return this.add(n, n3);
        }
        final long n4 = this.iChronology.getMillisOfDay(n);
        final int year = this.iChronology.getYear(n);
        final int monthOfYear = this.iChronology.getMonthOfYear(n, year);
        final long n5 = monthOfYear - 1 + n2;
        long n6;
        long n7;
        if (n5 >= 0L) {
            n6 = year + n5 / this.iMax;
            n7 = n5 % this.iMax + 1L;
        }
        else {
            n6 = year + n5 / this.iMax - 1L;
            int iMax = (int)(Math.abs(n5) % this.iMax);
            if (iMax == 0) {
                iMax = this.iMax;
            }
            n7 = this.iMax - iMax + 1;
            if (n7 == 1L) {
                ++n6;
            }
        }
        if (n6 < this.iChronology.getMinYear() || n6 > this.iChronology.getMaxYear()) {
            throw new IllegalArgumentException("Magnitude of add amount is too large: " + n2);
        }
        final int n8 = (int)n6;
        final int n9 = (int)n7;
        int dayOfMonth = this.iChronology.getDayOfMonth(n, year, monthOfYear);
        final int daysInYearMonth = this.iChronology.getDaysInYearMonth(n8, n9);
        if (dayOfMonth > daysInYearMonth) {
            dayOfMonth = daysInYearMonth;
        }
        return this.iChronology.getYearMonthDayMillis(n8, n9, dayOfMonth) + n4;
    }
    
    public int[] add(final ReadablePartial readablePartial, final int n, final int[] array, final int n2) {
        if (n2 == 0) {
            return array;
        }
        if (DateTimeUtils.isContiguous(readablePartial)) {
            long set = 0L;
            for (int i = 0; i < readablePartial.size(); ++i) {
                set = readablePartial.getFieldType(i).getField(this.iChronology).set(set, array[i]);
            }
            return this.iChronology.get(readablePartial, this.add(set, n2));
        }
        return super.add(readablePartial, n, array, n2);
    }
    
    public long addWrapField(final long n, final int n2) {
        return this.set(n, FieldUtils.getWrappedValue(this.get(n), n2, 1, this.iMax));
    }
    
    public long getDifferenceAsLong(final long n, long set) {
        if (n < set) {
            return -this.getDifference(set, n);
        }
        final int year = this.iChronology.getYear(n);
        final int monthOfYear = this.iChronology.getMonthOfYear(n, year);
        final int year2 = this.iChronology.getYear(set);
        final int monthOfYear2 = this.iChronology.getMonthOfYear(set, year2);
        long n2 = (year - year2) * this.iMax + monthOfYear - monthOfYear2;
        final int dayOfMonth = this.iChronology.getDayOfMonth(n, year, monthOfYear);
        if (dayOfMonth == this.iChronology.getDaysInYearMonth(year, monthOfYear) && this.iChronology.getDayOfMonth(set, year2, monthOfYear2) > dayOfMonth) {
            set = this.iChronology.dayOfMonth().set(set, dayOfMonth);
        }
        if (n - this.iChronology.getYearMonthMillis(year, monthOfYear) < set - this.iChronology.getYearMonthMillis(year2, monthOfYear2)) {
            --n2;
        }
        return n2;
    }
    
    public long set(final long n, final int n2) {
        FieldUtils.verifyValueBounds(this, n2, 1, this.iMax);
        final int year = this.iChronology.getYear(n);
        int dayOfMonth = this.iChronology.getDayOfMonth(n, year);
        final int daysInYearMonth = this.iChronology.getDaysInYearMonth(year, n2);
        if (dayOfMonth > daysInYearMonth) {
            dayOfMonth = daysInYearMonth;
        }
        return this.iChronology.getYearMonthDayMillis(year, n2, dayOfMonth) + this.iChronology.getMillisOfDay(n);
    }
    
    public DurationField getRangeDurationField() {
        return this.iChronology.years();
    }
    
    public boolean isLeap(final long n) {
        final int year = this.iChronology.getYear(n);
        return this.iChronology.isLeapYear(year) && this.iChronology.getMonthOfYear(n, year) == this.iLeapMonth;
    }
    
    public int getLeapAmount(final long n) {
        return this.isLeap(n) ? 1 : 0;
    }
    
    public DurationField getLeapDurationField() {
        return this.iChronology.days();
    }
    
    public int getMinimumValue() {
        return 1;
    }
    
    public int getMaximumValue() {
        return this.iMax;
    }
    
    public long roundFloor(final long n) {
        final int year = this.iChronology.getYear(n);
        return this.iChronology.getYearMonthMillis(year, this.iChronology.getMonthOfYear(n, year));
    }
    
    public long remainder(final long n) {
        return n - this.roundFloor(n);
    }
    
    private Object readResolve() {
        return this.iChronology.monthOfYear();
    }
}
