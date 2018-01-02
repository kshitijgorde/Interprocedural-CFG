// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.chrono;

import java.util.Locale;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.field.PreciseDurationDateTimeField;

final class GJDayOfWeekDateTimeField extends PreciseDurationDateTimeField
{
    private static final long serialVersionUID = -3857947176719041436L;
    private final BasicChronology iChronology;
    
    GJDayOfWeekDateTimeField(final BasicChronology iChronology, final DurationField durationField) {
        super(DateTimeFieldType.dayOfWeek(), durationField);
        this.iChronology = iChronology;
    }
    
    public int get(final long n) {
        return this.iChronology.getDayOfWeek(n);
    }
    
    public String getAsText(final int n, final Locale locale) {
        return GJLocaleSymbols.forLocale(locale).dayOfWeekValueToText(n);
    }
    
    public String getAsShortText(final int n, final Locale locale) {
        return GJLocaleSymbols.forLocale(locale).dayOfWeekValueToShortText(n);
    }
    
    protected int convertText(final String s, final Locale locale) {
        return GJLocaleSymbols.forLocale(locale).dayOfWeekTextToValue(s);
    }
    
    public DurationField getRangeDurationField() {
        return this.iChronology.weeks();
    }
    
    public int getMinimumValue() {
        return 1;
    }
    
    public int getMaximumValue() {
        return 7;
    }
    
    public int getMaximumTextLength(final Locale locale) {
        return GJLocaleSymbols.forLocale(locale).getDayOfWeekMaxTextLength();
    }
    
    public int getMaximumShortTextLength(final Locale locale) {
        return GJLocaleSymbols.forLocale(locale).getDayOfWeekMaxShortTextLength();
    }
    
    private Object readResolve() {
        return this.iChronology.dayOfWeek();
    }
}
