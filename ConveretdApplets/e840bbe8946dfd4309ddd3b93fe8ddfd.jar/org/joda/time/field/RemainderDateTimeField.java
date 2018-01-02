// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.field;

import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeField;
import org.joda.time.DurationField;

public class RemainderDateTimeField extends DecoratedDateTimeField
{
    private static final long serialVersionUID = 5708241235177666790L;
    final int iDivisor;
    final DurationField iRangeField;
    
    public RemainderDateTimeField(final DateTimeField dateTimeField, final DateTimeFieldType dateTimeFieldType, final int iDivisor) {
        super(dateTimeField, dateTimeFieldType);
        if (iDivisor < 2) {
            throw new IllegalArgumentException("The divisor must be at least 2");
        }
        final DurationField durationField = dateTimeField.getDurationField();
        if (durationField == null) {
            this.iRangeField = null;
        }
        else {
            this.iRangeField = new ScaledDurationField(durationField, dateTimeFieldType.getRangeDurationType(), iDivisor);
        }
        this.iDivisor = iDivisor;
    }
    
    public RemainderDateTimeField(final DividedDateTimeField dividedDateTimeField) {
        this(dividedDateTimeField, dividedDateTimeField.getType());
    }
    
    public RemainderDateTimeField(final DividedDateTimeField dividedDateTimeField, final DateTimeFieldType dateTimeFieldType) {
        super(dividedDateTimeField.getWrappedField(), dateTimeFieldType);
        this.iDivisor = dividedDateTimeField.iDivisor;
        this.iRangeField = dividedDateTimeField.iDurationField;
    }
    
    public int get(final long n) {
        final int value = this.getWrappedField().get(n);
        if (value >= 0) {
            return value % this.iDivisor;
        }
        return this.iDivisor - 1 + (value + 1) % this.iDivisor;
    }
    
    public long addWrapField(final long n, final int n2) {
        return this.set(n, FieldUtils.getWrappedValue(this.get(n), n2, 0, this.iDivisor - 1));
    }
    
    public long set(final long n, final int n2) {
        FieldUtils.verifyValueBounds(this, n2, 0, this.iDivisor - 1);
        return this.getWrappedField().set(n, this.getDivided(this.getWrappedField().get(n)) * this.iDivisor + n2);
    }
    
    public DurationField getRangeDurationField() {
        return this.iRangeField;
    }
    
    public int getMinimumValue() {
        return 0;
    }
    
    public int getMaximumValue() {
        return this.iDivisor - 1;
    }
    
    public long roundFloor(final long n) {
        return this.getWrappedField().roundFloor(n);
    }
    
    public long roundCeiling(final long n) {
        return this.getWrappedField().roundCeiling(n);
    }
    
    public long roundHalfFloor(final long n) {
        return this.getWrappedField().roundHalfFloor(n);
    }
    
    public long roundHalfCeiling(final long n) {
        return this.getWrappedField().roundHalfCeiling(n);
    }
    
    public long roundHalfEven(final long n) {
        return this.getWrappedField().roundHalfEven(n);
    }
    
    public long remainder(final long n) {
        return this.getWrappedField().remainder(n);
    }
    
    public int getDivisor() {
        return this.iDivisor;
    }
    
    private int getDivided(final int n) {
        if (n >= 0) {
            return n / this.iDivisor;
        }
        return (n + 1) / this.iDivisor - 1;
    }
}
