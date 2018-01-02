// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.field;

import org.joda.time.DurationField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeField;

public abstract class DecoratedDateTimeField extends BaseDateTimeField
{
    private static final long serialVersionUID = 203115783733757597L;
    private final DateTimeField iField;
    
    protected DecoratedDateTimeField(final DateTimeField iField, final DateTimeFieldType dateTimeFieldType) {
        super(dateTimeFieldType);
        if (iField == null) {
            throw new IllegalArgumentException("The field must not be null");
        }
        if (!iField.isSupported()) {
            throw new IllegalArgumentException("The field must be supported");
        }
        this.iField = iField;
    }
    
    public final DateTimeField getWrappedField() {
        return this.iField;
    }
    
    public boolean isLenient() {
        return this.iField.isLenient();
    }
    
    public int get(final long n) {
        return this.iField.get(n);
    }
    
    public long set(final long n, final int n2) {
        return this.iField.set(n, n2);
    }
    
    public DurationField getDurationField() {
        return this.iField.getDurationField();
    }
    
    public DurationField getRangeDurationField() {
        return this.iField.getRangeDurationField();
    }
    
    public int getMinimumValue() {
        return this.iField.getMinimumValue();
    }
    
    public int getMaximumValue() {
        return this.iField.getMaximumValue();
    }
    
    public long roundFloor(final long n) {
        return this.iField.roundFloor(n);
    }
}
