// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.field;

import org.joda.time.DurationFieldType;
import org.joda.time.DurationField;

public class ScaledDurationField extends DecoratedDurationField
{
    private static final long serialVersionUID = -3205227092378684157L;
    private final int iScalar;
    
    public ScaledDurationField(final DurationField durationField, final DurationFieldType durationFieldType, final int iScalar) {
        super(durationField, durationFieldType);
        if (iScalar == 0 || iScalar == 1) {
            throw new IllegalArgumentException("The scalar must not be 0 or 1");
        }
        this.iScalar = iScalar;
    }
    
    public int getValue(final long n) {
        return this.getWrappedField().getValue(n) / this.iScalar;
    }
    
    public long getValueAsLong(final long n) {
        return this.getWrappedField().getValueAsLong(n) / this.iScalar;
    }
    
    public int getValue(final long n, final long n2) {
        return this.getWrappedField().getValue(n, n2) / this.iScalar;
    }
    
    public long getValueAsLong(final long n, final long n2) {
        return this.getWrappedField().getValueAsLong(n, n2) / this.iScalar;
    }
    
    public long getMillis(final int n) {
        return this.getWrappedField().getMillis(n * this.iScalar);
    }
    
    public long getMillis(final long n) {
        return this.getWrappedField().getMillis(FieldUtils.safeMultiply(n, this.iScalar));
    }
    
    public long getMillis(final int n, final long n2) {
        return this.getWrappedField().getMillis(n * this.iScalar, n2);
    }
    
    public long getMillis(final long n, final long n2) {
        return this.getWrappedField().getMillis(FieldUtils.safeMultiply(n, this.iScalar), n2);
    }
    
    public long add(final long n, final int n2) {
        return this.getWrappedField().add(n, n2 * this.iScalar);
    }
    
    public long add(final long n, final long n2) {
        return this.getWrappedField().add(n, FieldUtils.safeMultiply(n2, this.iScalar));
    }
    
    public int getDifference(final long n, final long n2) {
        return this.getWrappedField().getDifference(n, n2) / this.iScalar;
    }
    
    public long getDifferenceAsLong(final long n, final long n2) {
        return this.getWrappedField().getDifferenceAsLong(n, n2) / this.iScalar;
    }
    
    public long getUnitMillis() {
        return this.getWrappedField().getUnitMillis() * this.iScalar;
    }
    
    public int getScalar() {
        return this.iScalar;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof ScaledDurationField) {
            final ScaledDurationField scaledDurationField = (ScaledDurationField)o;
            return this.getWrappedField().equals(scaledDurationField.getWrappedField()) && this.getType() == scaledDurationField.getType() && this.iScalar == scaledDurationField.iScalar;
        }
        return false;
    }
    
    public int hashCode() {
        final long n = this.iScalar;
        return (int)(n ^ n >>> 32) + this.getType().hashCode() + this.getWrappedField().hashCode();
    }
}
