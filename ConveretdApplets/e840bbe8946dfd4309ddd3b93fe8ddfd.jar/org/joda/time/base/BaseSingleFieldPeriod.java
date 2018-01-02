// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.base;

import org.joda.time.MutablePeriod;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.DurationField;
import org.joda.time.field.FieldUtils;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.Chronology;
import org.joda.time.ReadablePartial;
import org.joda.time.DateTimeUtils;
import org.joda.time.DurationFieldType;
import org.joda.time.ReadableInstant;
import java.io.Serializable;
import org.joda.time.ReadablePeriod;

public abstract class BaseSingleFieldPeriod implements ReadablePeriod, Comparable, Serializable
{
    private static final long serialVersionUID = 9386874258972L;
    private int iPeriod;
    
    protected static int between(final ReadableInstant readableInstant, final ReadableInstant readableInstant2, final DurationFieldType durationFieldType) {
        if (readableInstant == null || readableInstant2 == null) {
            throw new IllegalArgumentException("ReadableInstant objects must not be null");
        }
        return durationFieldType.getField(DateTimeUtils.getInstantChronology(readableInstant)).getDifference(readableInstant2.getMillis(), readableInstant.getMillis());
    }
    
    protected static int between(final ReadablePartial readablePartial, final ReadablePartial readablePartial2, final ReadablePeriod readablePeriod) {
        if (readablePartial == null || readablePartial2 == null) {
            throw new IllegalArgumentException("ReadablePartial objects must not be null");
        }
        if (readablePartial.size() != readablePartial2.size()) {
            throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
        }
        for (int i = 0; i < readablePartial.size(); ++i) {
            if (readablePartial.getFieldType(i) != readablePartial2.getFieldType(i)) {
                throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
            }
        }
        if (!DateTimeUtils.isContiguous(readablePartial)) {
            throw new IllegalArgumentException("ReadablePartial objects must be contiguous");
        }
        final Chronology withUTC = DateTimeUtils.getChronology(readablePartial.getChronology()).withUTC();
        return withUTC.get(readablePeriod, withUTC.set(readablePartial, 0L), withUTC.set(readablePartial2, 0L))[0];
    }
    
    protected static int standardPeriodIn(final ReadablePeriod readablePeriod, final long n) {
        if (readablePeriod == null) {
            return 0;
        }
        final ISOChronology instanceUTC = ISOChronology.getInstanceUTC();
        long safeAdd = 0L;
        for (int i = 0; i < readablePeriod.size(); ++i) {
            final int value = readablePeriod.getValue(i);
            if (value != 0) {
                final DurationField field = readablePeriod.getFieldType(i).getField(instanceUTC);
                if (!field.isPrecise()) {
                    throw new IllegalArgumentException("Cannot convert period to duration as " + field.getName() + " is not precise in the period " + readablePeriod);
                }
                safeAdd = FieldUtils.safeAdd(safeAdd, FieldUtils.safeMultiply(field.getUnitMillis(), value));
            }
        }
        return FieldUtils.safeToInt(safeAdd / n);
    }
    
    protected BaseSingleFieldPeriod(final int iPeriod) {
        this.iPeriod = iPeriod;
    }
    
    protected int getValue() {
        return this.iPeriod;
    }
    
    protected void setValue(final int iPeriod) {
        this.iPeriod = iPeriod;
    }
    
    public abstract DurationFieldType getFieldType();
    
    public abstract PeriodType getPeriodType();
    
    public int size() {
        return 1;
    }
    
    public DurationFieldType getFieldType(final int n) {
        if (n != 0) {
            throw new IndexOutOfBoundsException(String.valueOf(n));
        }
        return this.getFieldType();
    }
    
    public int getValue(final int n) {
        if (n != 0) {
            throw new IndexOutOfBoundsException(String.valueOf(n));
        }
        return this.getValue();
    }
    
    public int get(final DurationFieldType durationFieldType) {
        if (durationFieldType == this.getFieldType()) {
            return this.getValue();
        }
        return 0;
    }
    
    public boolean isSupported(final DurationFieldType durationFieldType) {
        return durationFieldType == this.getFieldType();
    }
    
    public Period toPeriod() {
        return Period.ZERO.withFields(this);
    }
    
    public MutablePeriod toMutablePeriod() {
        final MutablePeriod mutablePeriod = new MutablePeriod();
        mutablePeriod.add(this);
        return mutablePeriod;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReadablePeriod)) {
            return false;
        }
        final ReadablePeriod readablePeriod = (ReadablePeriod)o;
        return readablePeriod.getPeriodType() == this.getPeriodType() && readablePeriod.getValue(0) == this.getValue();
    }
    
    public int hashCode() {
        return 27 * (27 * 17 + this.getValue()) + this.getFieldType().hashCode();
    }
    
    public int compareTo(final Object o) {
        if (o.getClass() != this.getClass()) {
            throw new ClassCastException(this.getClass() + " cannot be compared to " + o.getClass());
        }
        final int value = ((BaseSingleFieldPeriod)o).getValue();
        final int value2 = this.getValue();
        if (value2 > value) {
            return 1;
        }
        if (value2 < value) {
            return -1;
        }
        return 0;
    }
}
