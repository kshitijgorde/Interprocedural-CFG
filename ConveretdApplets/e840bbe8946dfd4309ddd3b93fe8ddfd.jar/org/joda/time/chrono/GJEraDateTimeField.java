// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.chrono;

import org.joda.time.field.UnsupportedDurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.DurationField;
import org.joda.time.DateTimeField;
import org.joda.time.field.FieldUtils;
import java.util.Locale;
import org.joda.time.DateTimeFieldType;
import org.joda.time.field.BaseDateTimeField;

final class GJEraDateTimeField extends BaseDateTimeField
{
    private static final long serialVersionUID = 4240986525305515528L;
    private final BasicChronology iChronology;
    
    GJEraDateTimeField(final BasicChronology iChronology) {
        super(DateTimeFieldType.era());
        this.iChronology = iChronology;
    }
    
    public boolean isLenient() {
        return false;
    }
    
    public int get(final long n) {
        if (this.iChronology.getYear(n) <= 0) {
            return 0;
        }
        return 1;
    }
    
    public String getAsText(final int n, final Locale locale) {
        return GJLocaleSymbols.forLocale(locale).eraValueToText(n);
    }
    
    public long set(final long n, final int n2) {
        FieldUtils.verifyValueBounds(this, n2, 0, 1);
        if (this.get(n) != n2) {
            return this.iChronology.setYear(n, -this.iChronology.getYear(n));
        }
        return n;
    }
    
    public long set(final long n, final String s, final Locale locale) {
        return this.set(n, GJLocaleSymbols.forLocale(locale).eraTextToValue(s));
    }
    
    public long roundFloor(final long n) {
        if (this.get(n) == 1) {
            return this.iChronology.setYear(0L, 1);
        }
        return Long.MIN_VALUE;
    }
    
    public long roundCeiling(final long n) {
        if (this.get(n) == 0) {
            return this.iChronology.setYear(0L, 1);
        }
        return Long.MAX_VALUE;
    }
    
    public long roundHalfFloor(final long n) {
        return this.roundFloor(n);
    }
    
    public long roundHalfCeiling(final long n) {
        return this.roundFloor(n);
    }
    
    public long roundHalfEven(final long n) {
        return this.roundFloor(n);
    }
    
    public DurationField getDurationField() {
        return UnsupportedDurationField.getInstance(DurationFieldType.eras());
    }
    
    public DurationField getRangeDurationField() {
        return null;
    }
    
    public int getMinimumValue() {
        return 0;
    }
    
    public int getMaximumValue() {
        return 1;
    }
    
    public int getMaximumTextLength(final Locale locale) {
        return GJLocaleSymbols.forLocale(locale).getEraMaxTextLength();
    }
    
    private Object readResolve() {
        return this.iChronology.era();
    }
}
