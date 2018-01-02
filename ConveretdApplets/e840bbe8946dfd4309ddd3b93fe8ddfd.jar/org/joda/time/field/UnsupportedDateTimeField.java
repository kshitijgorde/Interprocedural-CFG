// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.field;

import org.joda.time.ReadablePartial;
import java.util.Locale;
import org.joda.time.DurationField;
import org.joda.time.DateTimeFieldType;
import java.util.HashMap;
import java.io.Serializable;
import org.joda.time.DateTimeField;

public final class UnsupportedDateTimeField extends DateTimeField implements Serializable
{
    private static final long serialVersionUID = -1934618396111902255L;
    private static HashMap cCache;
    private final DateTimeFieldType iType;
    private final DurationField iDurationField;
    
    public static synchronized UnsupportedDateTimeField getInstance(final DateTimeFieldType dateTimeFieldType, final DurationField durationField) {
        UnsupportedDateTimeField unsupportedDateTimeField;
        if (UnsupportedDateTimeField.cCache == null) {
            UnsupportedDateTimeField.cCache = new HashMap(7);
            unsupportedDateTimeField = null;
        }
        else {
            unsupportedDateTimeField = UnsupportedDateTimeField.cCache.get(dateTimeFieldType);
            if (unsupportedDateTimeField != null && unsupportedDateTimeField.getDurationField() != durationField) {
                unsupportedDateTimeField = null;
            }
        }
        if (unsupportedDateTimeField == null) {
            unsupportedDateTimeField = new UnsupportedDateTimeField(dateTimeFieldType, durationField);
            UnsupportedDateTimeField.cCache.put(dateTimeFieldType, unsupportedDateTimeField);
        }
        return unsupportedDateTimeField;
    }
    
    private UnsupportedDateTimeField(final DateTimeFieldType iType, final DurationField iDurationField) {
        if (iType == null || iDurationField == null) {
            throw new IllegalArgumentException();
        }
        this.iType = iType;
        this.iDurationField = iDurationField;
    }
    
    public DateTimeFieldType getType() {
        return this.iType;
    }
    
    public String getName() {
        return this.iType.getName();
    }
    
    public boolean isSupported() {
        return false;
    }
    
    public boolean isLenient() {
        return false;
    }
    
    public int get(final long n) {
        throw this.unsupported();
    }
    
    public String getAsText(final long n, final Locale locale) {
        throw this.unsupported();
    }
    
    public String getAsText(final long n) {
        throw this.unsupported();
    }
    
    public String getAsText(final ReadablePartial readablePartial, final int n, final Locale locale) {
        throw this.unsupported();
    }
    
    public String getAsText(final ReadablePartial readablePartial, final Locale locale) {
        throw this.unsupported();
    }
    
    public String getAsText(final int n, final Locale locale) {
        throw this.unsupported();
    }
    
    public String getAsShortText(final long n, final Locale locale) {
        throw this.unsupported();
    }
    
    public String getAsShortText(final long n) {
        throw this.unsupported();
    }
    
    public String getAsShortText(final ReadablePartial readablePartial, final int n, final Locale locale) {
        throw this.unsupported();
    }
    
    public String getAsShortText(final ReadablePartial readablePartial, final Locale locale) {
        throw this.unsupported();
    }
    
    public String getAsShortText(final int n, final Locale locale) {
        throw this.unsupported();
    }
    
    public long add(final long n, final int n2) {
        return this.getDurationField().add(n, n2);
    }
    
    public long add(final long n, final long n2) {
        return this.getDurationField().add(n, n2);
    }
    
    public int[] add(final ReadablePartial readablePartial, final int n, final int[] array, final int n2) {
        throw this.unsupported();
    }
    
    public int[] addWrapPartial(final ReadablePartial readablePartial, final int n, final int[] array, final int n2) {
        throw this.unsupported();
    }
    
    public long addWrapField(final long n, final int n2) {
        throw this.unsupported();
    }
    
    public int[] addWrapField(final ReadablePartial readablePartial, final int n, final int[] array, final int n2) {
        throw this.unsupported();
    }
    
    public int getDifference(final long n, final long n2) {
        return this.getDurationField().getDifference(n, n2);
    }
    
    public long getDifferenceAsLong(final long n, final long n2) {
        return this.getDurationField().getDifferenceAsLong(n, n2);
    }
    
    public long set(final long n, final int n2) {
        throw this.unsupported();
    }
    
    public int[] set(final ReadablePartial readablePartial, final int n, final int[] array, final int n2) {
        throw this.unsupported();
    }
    
    public long set(final long n, final String s, final Locale locale) {
        throw this.unsupported();
    }
    
    public long set(final long n, final String s) {
        throw this.unsupported();
    }
    
    public int[] set(final ReadablePartial readablePartial, final int n, final int[] array, final String s, final Locale locale) {
        throw this.unsupported();
    }
    
    public DurationField getDurationField() {
        return this.iDurationField;
    }
    
    public DurationField getRangeDurationField() {
        return null;
    }
    
    public boolean isLeap(final long n) {
        throw this.unsupported();
    }
    
    public int getLeapAmount(final long n) {
        throw this.unsupported();
    }
    
    public DurationField getLeapDurationField() {
        return null;
    }
    
    public int getMinimumValue() {
        throw this.unsupported();
    }
    
    public int getMinimumValue(final long n) {
        throw this.unsupported();
    }
    
    public int getMinimumValue(final ReadablePartial readablePartial) {
        throw this.unsupported();
    }
    
    public int getMinimumValue(final ReadablePartial readablePartial, final int[] array) {
        throw this.unsupported();
    }
    
    public int getMaximumValue() {
        throw this.unsupported();
    }
    
    public int getMaximumValue(final long n) {
        throw this.unsupported();
    }
    
    public int getMaximumValue(final ReadablePartial readablePartial) {
        throw this.unsupported();
    }
    
    public int getMaximumValue(final ReadablePartial readablePartial, final int[] array) {
        throw this.unsupported();
    }
    
    public int getMaximumTextLength(final Locale locale) {
        throw this.unsupported();
    }
    
    public int getMaximumShortTextLength(final Locale locale) {
        throw this.unsupported();
    }
    
    public long roundFloor(final long n) {
        throw this.unsupported();
    }
    
    public long roundCeiling(final long n) {
        throw this.unsupported();
    }
    
    public long roundHalfFloor(final long n) {
        throw this.unsupported();
    }
    
    public long roundHalfCeiling(final long n) {
        throw this.unsupported();
    }
    
    public long roundHalfEven(final long n) {
        throw this.unsupported();
    }
    
    public long remainder(final long n) {
        throw this.unsupported();
    }
    
    public String toString() {
        return "UnsupportedDateTimeField";
    }
    
    private Object readResolve() {
        return getInstance(this.iType, this.iDurationField);
    }
    
    private UnsupportedOperationException unsupported() {
        return new UnsupportedOperationException(this.iType + " field is unsupported");
    }
}
