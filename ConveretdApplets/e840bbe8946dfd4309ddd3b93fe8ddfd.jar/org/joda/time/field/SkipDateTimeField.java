// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.field;

import org.joda.time.IllegalFieldValueException;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeField;
import org.joda.time.Chronology;

public final class SkipDateTimeField extends DelegatedDateTimeField
{
    private static final long serialVersionUID = -8869148464118507846L;
    private final Chronology iChronology;
    private final int iSkip;
    private transient int iMinValue;
    
    public SkipDateTimeField(final Chronology chronology, final DateTimeField dateTimeField) {
        this(chronology, dateTimeField, 0);
    }
    
    public SkipDateTimeField(final Chronology iChronology, final DateTimeField dateTimeField, final int iSkip) {
        super(dateTimeField);
        this.iChronology = iChronology;
        final int minimumValue = super.getMinimumValue();
        if (minimumValue < iSkip) {
            this.iMinValue = minimumValue - 1;
        }
        else if (minimumValue == iSkip) {
            this.iMinValue = iSkip + 1;
        }
        else {
            this.iMinValue = minimumValue;
        }
        this.iSkip = iSkip;
    }
    
    public int get(final long n) {
        int value = super.get(n);
        if (value <= this.iSkip) {
            --value;
        }
        return value;
    }
    
    public long set(final long n, int n2) {
        FieldUtils.verifyValueBounds(this, n2, this.iMinValue, this.getMaximumValue());
        if (n2 <= this.iSkip) {
            if (n2 == this.iSkip) {
                throw new IllegalFieldValueException(DateTimeFieldType.year(), new Integer(n2), null, null);
            }
            ++n2;
        }
        return super.set(n, n2);
    }
    
    public int getMinimumValue() {
        return this.iMinValue;
    }
    
    private Object readResolve() {
        return this.getType().getField(this.iChronology);
    }
}
