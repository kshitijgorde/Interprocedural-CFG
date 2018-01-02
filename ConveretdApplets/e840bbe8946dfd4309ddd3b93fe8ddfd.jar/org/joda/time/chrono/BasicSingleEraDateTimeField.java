// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.chrono;

import org.joda.time.field.UnsupportedDurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.DurationField;
import org.joda.time.IllegalFieldValueException;
import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.field.FieldUtils;
import org.joda.time.DateTimeFieldType;
import org.joda.time.field.BaseDateTimeField;

final class BasicSingleEraDateTimeField extends BaseDateTimeField
{
    private static final int ERA_VALUE = 1;
    private final String iEraText;
    
    BasicSingleEraDateTimeField(final String iEraText) {
        super(DateTimeFieldType.era());
        this.iEraText = iEraText;
    }
    
    public boolean isLenient() {
        return false;
    }
    
    public int get(final long n) {
        return 1;
    }
    
    public long set(final long n, final int n2) {
        FieldUtils.verifyValueBounds(this, n2, 1, 1);
        return n;
    }
    
    public long set(final long n, final String s, final Locale locale) {
        if (!this.iEraText.equals(s) && !"1".equals(s)) {
            throw new IllegalFieldValueException(DateTimeFieldType.era(), s);
        }
        return n;
    }
    
    public long roundFloor(final long n) {
        return Long.MIN_VALUE;
    }
    
    public long roundCeiling(final long n) {
        return Long.MAX_VALUE;
    }
    
    public long roundHalfFloor(final long n) {
        return Long.MIN_VALUE;
    }
    
    public long roundHalfCeiling(final long n) {
        return Long.MIN_VALUE;
    }
    
    public long roundHalfEven(final long n) {
        return Long.MIN_VALUE;
    }
    
    public DurationField getDurationField() {
        return UnsupportedDurationField.getInstance(DurationFieldType.eras());
    }
    
    public DurationField getRangeDurationField() {
        return null;
    }
    
    public int getMinimumValue() {
        return 1;
    }
    
    public int getMaximumValue() {
        return 1;
    }
    
    public String getAsText(final int n, final Locale locale) {
        return this.iEraText;
    }
    
    public int getMaximumTextLength(final Locale locale) {
        return this.iEraText.length();
    }
}
