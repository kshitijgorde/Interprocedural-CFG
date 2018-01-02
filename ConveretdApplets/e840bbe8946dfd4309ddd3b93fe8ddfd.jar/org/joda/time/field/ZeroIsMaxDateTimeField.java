// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.field;

import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeField;

public final class ZeroIsMaxDateTimeField extends DecoratedDateTimeField
{
    private static final long serialVersionUID = 961749798233026866L;
    
    public ZeroIsMaxDateTimeField(final DateTimeField dateTimeField, final DateTimeFieldType dateTimeFieldType) {
        super(dateTimeField, dateTimeFieldType);
        if (dateTimeField.getMinimumValue() != 0) {
            throw new IllegalArgumentException("Wrapped field's minumum value must be zero");
        }
    }
    
    public int get(final long n) {
        int n2 = this.getWrappedField().get(n);
        if (n2 == 0) {
            n2 = this.getMaximumValue();
        }
        return n2;
    }
    
    public long add(final long n, final int n2) {
        return this.getWrappedField().add(n, n2);
    }
    
    public long add(final long n, final long n2) {
        return this.getWrappedField().add(n, n2);
    }
    
    public long addWrapField(final long n, final int n2) {
        return this.getWrappedField().addWrapField(n, n2);
    }
    
    public int[] addWrapField(final ReadablePartial readablePartial, final int n, final int[] array, final int n2) {
        return this.getWrappedField().addWrapField(readablePartial, n, array, n2);
    }
    
    public int getDifference(final long n, final long n2) {
        return this.getWrappedField().getDifference(n, n2);
    }
    
    public long getDifferenceAsLong(final long n, final long n2) {
        return this.getWrappedField().getDifferenceAsLong(n, n2);
    }
    
    public long set(final long n, int n2) {
        final int maximumValue = this.getMaximumValue();
        FieldUtils.verifyValueBounds(this, n2, 1, maximumValue);
        if (n2 == maximumValue) {
            n2 = 0;
        }
        return this.getWrappedField().set(n, n2);
    }
    
    public boolean isLeap(final long n) {
        return this.getWrappedField().isLeap(n);
    }
    
    public int getLeapAmount(final long n) {
        return this.getWrappedField().getLeapAmount(n);
    }
    
    public DurationField getLeapDurationField() {
        return this.getWrappedField().getLeapDurationField();
    }
    
    public int getMinimumValue() {
        return 1;
    }
    
    public int getMinimumValue(final long n) {
        return 1;
    }
    
    public int getMinimumValue(final ReadablePartial readablePartial) {
        return 1;
    }
    
    public int getMinimumValue(final ReadablePartial readablePartial, final int[] array) {
        return 1;
    }
    
    public int getMaximumValue() {
        return this.getWrappedField().getMaximumValue() + 1;
    }
    
    public int getMaximumValue(final long n) {
        return this.getWrappedField().getMaximumValue(n) + 1;
    }
    
    public int getMaximumValue(final ReadablePartial readablePartial) {
        return this.getWrappedField().getMaximumValue(readablePartial) + 1;
    }
    
    public int getMaximumValue(final ReadablePartial readablePartial, final int[] array) {
        return this.getWrappedField().getMaximumValue(readablePartial, array) + 1;
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
}
