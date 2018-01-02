// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.chrono;

import org.joda.time.field.FieldUtils;
import org.joda.time.ReadablePartial;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeField;
import org.joda.time.field.DecoratedDateTimeField;

final class GJYearOfEraDateTimeField extends DecoratedDateTimeField
{
    private static final long serialVersionUID = -5961050944769862059L;
    private final BasicChronology iChronology;
    
    GJYearOfEraDateTimeField(final DateTimeField dateTimeField, final BasicChronology iChronology) {
        super(dateTimeField, DateTimeFieldType.yearOfEra());
        this.iChronology = iChronology;
    }
    
    public int get(final long n) {
        int value = this.getWrappedField().get(n);
        if (value <= 0) {
            value = 1 - value;
        }
        return value;
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
        FieldUtils.verifyValueBounds(this, n2, 1, this.getMaximumValue());
        if (this.iChronology.getYear(n) <= 0) {
            n2 = 1 - n2;
        }
        return super.set(n, n2);
    }
    
    public int getMinimumValue() {
        return 1;
    }
    
    public int getMaximumValue() {
        return this.getWrappedField().getMaximumValue();
    }
    
    public long roundFloor(final long n) {
        return this.getWrappedField().roundFloor(n);
    }
    
    public long roundCeiling(final long n) {
        return this.getWrappedField().roundCeiling(n);
    }
    
    public long remainder(final long n) {
        return this.getWrappedField().remainder(n);
    }
    
    private Object readResolve() {
        return this.iChronology.yearOfEra();
    }
}
