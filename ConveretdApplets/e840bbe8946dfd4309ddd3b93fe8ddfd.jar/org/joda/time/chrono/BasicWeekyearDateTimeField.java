// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.chrono;

import org.joda.time.DurationField;
import org.joda.time.DateTimeField;
import org.joda.time.field.FieldUtils;
import org.joda.time.DateTimeFieldType;
import org.joda.time.field.ImpreciseDateTimeField;

final class BasicWeekyearDateTimeField extends ImpreciseDateTimeField
{
    private static final long serialVersionUID = 6215066916806820644L;
    private static final long WEEK_53 = 31449600000L;
    private final BasicChronology iChronology;
    
    BasicWeekyearDateTimeField(final BasicChronology iChronology) {
        super(DateTimeFieldType.weekyear(), iChronology.getAverageMillisPerYear());
        this.iChronology = iChronology;
    }
    
    public boolean isLenient() {
        return false;
    }
    
    public int get(final long n) {
        return this.iChronology.getWeekyear(n);
    }
    
    public long add(final long n, final int n2) {
        if (n2 == 0) {
            return n;
        }
        return this.set(n, this.get(n) + n2);
    }
    
    public long add(final long n, final long n2) {
        return this.add(n, FieldUtils.safeToInt(n2));
    }
    
    public long addWrapField(final long n, final int n2) {
        return this.add(n, n2);
    }
    
    public long getDifferenceAsLong(final long n, final long n2) {
        if (n < n2) {
            return -this.getDifference(n2, n);
        }
        final int value = this.get(n);
        final int value2 = this.get(n2);
        final long remainder = this.remainder(n);
        long remainder2 = this.remainder(n2);
        if (remainder2 >= 31449600000L && this.iChronology.getWeeksInYear(value) <= 52) {
            remainder2 -= 604800000L;
        }
        int n3 = value - value2;
        if (remainder < remainder2) {
            --n3;
        }
        return n3;
    }
    
    public long set(final long n, final int n2) {
        FieldUtils.verifyValueBounds(this, Math.abs(n2), this.iChronology.getMinYear(), this.iChronology.getMaxYear());
        final int value = this.get(n);
        if (value == n2) {
            return n;
        }
        final int dayOfWeek = this.iChronology.getDayOfWeek(n);
        final int weeksInYear = this.iChronology.getWeeksInYear(value);
        final int weeksInYear2 = this.iChronology.getWeeksInYear(n2);
        final int n3 = (weeksInYear2 < weeksInYear) ? weeksInYear2 : weeksInYear;
        int weekOfWeekyear = this.iChronology.getWeekOfWeekyear(n);
        if (weekOfWeekyear > n3) {
            weekOfWeekyear = n3;
        }
        long setYear = this.iChronology.setYear(n, n2);
        final int value2 = this.get(setYear);
        if (value2 < n2) {
            setYear += 604800000L;
        }
        else if (value2 > n2) {
            setYear -= 604800000L;
        }
        return this.iChronology.dayOfWeek().set(setYear + (weekOfWeekyear - this.iChronology.getWeekOfWeekyear(setYear)) * 604800000L, dayOfWeek);
    }
    
    public DurationField getRangeDurationField() {
        return null;
    }
    
    public boolean isLeap(final long n) {
        return this.iChronology.getWeeksInYear(this.iChronology.getWeekyear(n)) > 52;
    }
    
    public int getLeapAmount(final long n) {
        return this.iChronology.getWeeksInYear(this.iChronology.getWeekyear(n)) - 52;
    }
    
    public DurationField getLeapDurationField() {
        return this.iChronology.weeks();
    }
    
    public int getMinimumValue() {
        return this.iChronology.getMinYear();
    }
    
    public int getMaximumValue() {
        return this.iChronology.getMaxYear();
    }
    
    public long roundFloor(long roundFloor) {
        roundFloor = this.iChronology.weekOfWeekyear().roundFloor(roundFloor);
        final int weekOfWeekyear = this.iChronology.getWeekOfWeekyear(roundFloor);
        if (weekOfWeekyear > 1) {
            roundFloor -= 604800000L * (weekOfWeekyear - 1);
        }
        return roundFloor;
    }
    
    public long remainder(final long n) {
        return n - this.roundFloor(n);
    }
    
    private Object readResolve() {
        return this.iChronology.weekyear();
    }
}
