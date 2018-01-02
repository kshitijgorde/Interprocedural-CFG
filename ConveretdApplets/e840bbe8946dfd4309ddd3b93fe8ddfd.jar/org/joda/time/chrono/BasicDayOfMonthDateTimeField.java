// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.chrono;

import org.joda.time.ReadablePartial;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.field.PreciseDurationDateTimeField;

final class BasicDayOfMonthDateTimeField extends PreciseDurationDateTimeField
{
    private static final long serialVersionUID = -4677223814028011723L;
    private final BasicChronology iChronology;
    
    BasicDayOfMonthDateTimeField(final BasicChronology iChronology, final DurationField durationField) {
        super(DateTimeFieldType.dayOfMonth(), durationField);
        this.iChronology = iChronology;
    }
    
    public int get(final long n) {
        return this.iChronology.getDayOfMonth(n);
    }
    
    public DurationField getRangeDurationField() {
        return this.iChronology.months();
    }
    
    public int getMinimumValue() {
        return 1;
    }
    
    public int getMaximumValue() {
        return this.iChronology.getDaysInMonthMax();
    }
    
    public int getMaximumValue(final long n) {
        return this.iChronology.getDaysInMonthMax(n);
    }
    
    public int getMaximumValue(final ReadablePartial readablePartial) {
        if (!readablePartial.isSupported(DateTimeFieldType.monthOfYear())) {
            return this.getMaximumValue();
        }
        final int value = readablePartial.get(DateTimeFieldType.monthOfYear());
        if (readablePartial.isSupported(DateTimeFieldType.year())) {
            return this.iChronology.getDaysInYearMonth(readablePartial.get(DateTimeFieldType.year()), value);
        }
        return this.iChronology.getDaysInMonthMax(value);
    }
    
    public int getMaximumValue(final ReadablePartial readablePartial, final int[] array) {
        for (int size = readablePartial.size(), i = 0; i < size; ++i) {
            if (readablePartial.getFieldType(i) == DateTimeFieldType.monthOfYear()) {
                final int n = array[i];
                for (int j = 0; j < size; ++j) {
                    if (readablePartial.getFieldType(j) == DateTimeFieldType.year()) {
                        return this.iChronology.getDaysInYearMonth(array[j], n);
                    }
                }
                return this.iChronology.getDaysInMonthMax(n);
            }
        }
        return this.getMaximumValue();
    }
    
    protected int getMaximumValueForSet(final long n, final int n2) {
        return this.iChronology.getDaysInMonthMaxForSet(n, n2);
    }
    
    private Object readResolve() {
        return this.iChronology.dayOfMonth();
    }
}
