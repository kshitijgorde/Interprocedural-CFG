// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.field;

import org.joda.time.DurationFieldType;

public class PreciseDurationField extends BaseDurationField
{
    private static final long serialVersionUID = -8346152187724495365L;
    private final long iUnitMillis;
    
    public PreciseDurationField(final DurationFieldType durationFieldType, final long iUnitMillis) {
        super(durationFieldType);
        this.iUnitMillis = iUnitMillis;
    }
    
    public final boolean isPrecise() {
        return true;
    }
    
    public final long getUnitMillis() {
        return this.iUnitMillis;
    }
    
    public long getValueAsLong(final long n, final long n2) {
        return n / this.iUnitMillis;
    }
    
    public long getMillis(final int n, final long n2) {
        return n * this.iUnitMillis;
    }
    
    public long getMillis(final long n, final long n2) {
        return FieldUtils.safeMultiply(n, this.iUnitMillis);
    }
    
    public long add(final long n, final int n2) {
        return FieldUtils.safeAdd(n, n2 * this.iUnitMillis);
    }
    
    public long add(final long n, final long n2) {
        return FieldUtils.safeAdd(n, FieldUtils.safeMultiply(n2, this.iUnitMillis));
    }
    
    public long getDifferenceAsLong(final long n, final long n2) {
        return FieldUtils.safeSubtract(n, n2) / this.iUnitMillis;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof PreciseDurationField) {
            final PreciseDurationField preciseDurationField = (PreciseDurationField)o;
            return this.getType() == preciseDurationField.getType() && this.iUnitMillis == preciseDurationField.iUnitMillis;
        }
        return false;
    }
    
    public int hashCode() {
        final long iUnitMillis = this.iUnitMillis;
        return (int)(iUnitMillis ^ iUnitMillis >>> 32) + this.getType().hashCode();
    }
}
