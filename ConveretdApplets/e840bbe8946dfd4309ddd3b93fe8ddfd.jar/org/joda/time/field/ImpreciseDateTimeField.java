// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.field;

import org.joda.time.DurationFieldType;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

public abstract class ImpreciseDateTimeField extends BaseDateTimeField
{
    private static final long serialVersionUID = 7190739608550251860L;
    final long iUnitMillis;
    private final DurationField iDurationField;
    
    public ImpreciseDateTimeField(final DateTimeFieldType dateTimeFieldType, final long iUnitMillis) {
        super(dateTimeFieldType);
        this.iUnitMillis = iUnitMillis;
        this.iDurationField = new LinkedDurationField(dateTimeFieldType.getDurationType());
    }
    
    public abstract int get(final long p0);
    
    public abstract long set(final long p0, final int p1);
    
    public abstract long add(final long p0, final int p1);
    
    public abstract long add(final long p0, final long p1);
    
    public int getDifference(final long n, final long n2) {
        return FieldUtils.safeToInt(this.getDifferenceAsLong(n, n2));
    }
    
    public long getDifferenceAsLong(final long n, final long n2) {
        if (n < n2) {
            return -this.getDifferenceAsLong(n2, n);
        }
        long n3 = (n - n2) / this.iUnitMillis;
        if (this.add(n2, n3) < n) {
            do {
                ++n3;
            } while (this.add(n2, n3) <= n);
            --n3;
        }
        else if (this.add(n2, n3) > n) {
            do {
                --n3;
            } while (this.add(n2, n3) > n);
        }
        return n3;
    }
    
    public final DurationField getDurationField() {
        return this.iDurationField;
    }
    
    public abstract DurationField getRangeDurationField();
    
    public abstract long roundFloor(final long p0);
    
    protected final long getDurationUnitMillis() {
        return this.iUnitMillis;
    }
    
    private final class LinkedDurationField extends BaseDurationField
    {
        private static final long serialVersionUID = -203813474600094134L;
        
        LinkedDurationField(final DurationFieldType durationFieldType) {
            super(durationFieldType);
        }
        
        public boolean isPrecise() {
            return false;
        }
        
        public long getUnitMillis() {
            return ImpreciseDateTimeField.this.iUnitMillis;
        }
        
        public int getValue(final long n, final long n2) {
            return ImpreciseDateTimeField.this.getDifference(n2 + n, n2);
        }
        
        public long getValueAsLong(final long n, final long n2) {
            return ImpreciseDateTimeField.this.getDifferenceAsLong(n2 + n, n2);
        }
        
        public long getMillis(final int n, final long n2) {
            return ImpreciseDateTimeField.this.add(n2, n) - n2;
        }
        
        public long getMillis(final long n, final long n2) {
            return ImpreciseDateTimeField.this.add(n2, n) - n2;
        }
        
        public long add(final long n, final int n2) {
            return ImpreciseDateTimeField.this.add(n, n2);
        }
        
        public long add(final long n, final long n2) {
            return ImpreciseDateTimeField.this.add(n, n2);
        }
        
        public int getDifference(final long n, final long n2) {
            return ImpreciseDateTimeField.this.getDifference(n, n2);
        }
        
        public long getDifferenceAsLong(final long n, final long n2) {
            return ImpreciseDateTimeField.this.getDifferenceAsLong(n, n2);
        }
    }
}
