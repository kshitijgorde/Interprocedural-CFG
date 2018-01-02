// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.field;

import org.joda.time.DurationField;
import org.joda.time.ReadablePartial;
import java.util.Locale;
import org.joda.time.DateTimeFieldType;
import java.io.Serializable;
import org.joda.time.DateTimeField;

public class DelegatedDateTimeField extends DateTimeField implements Serializable
{
    private static final long serialVersionUID = -4730164440214502503L;
    private final DateTimeField iField;
    private final DateTimeFieldType iType;
    
    public DelegatedDateTimeField(final DateTimeField dateTimeField) {
        this(dateTimeField, null);
    }
    
    public DelegatedDateTimeField(final DateTimeField iField, final DateTimeFieldType dateTimeFieldType) {
        if (iField == null) {
            throw new IllegalArgumentException("The field must not be null");
        }
        this.iField = iField;
        this.iType = ((dateTimeFieldType == null) ? iField.getType() : dateTimeFieldType);
    }
    
    public final DateTimeField getWrappedField() {
        return this.iField;
    }
    
    public DateTimeFieldType getType() {
        return this.iType;
    }
    
    public String getName() {
        return this.iType.getName();
    }
    
    public boolean isSupported() {
        return this.iField.isSupported();
    }
    
    public boolean isLenient() {
        return this.iField.isLenient();
    }
    
    public int get(final long n) {
        return this.iField.get(n);
    }
    
    public String getAsText(final long n, final Locale locale) {
        return this.iField.getAsText(n, locale);
    }
    
    public String getAsText(final long n) {
        return this.iField.getAsText(n);
    }
    
    public String getAsText(final ReadablePartial readablePartial, final int n, final Locale locale) {
        return this.iField.getAsText(readablePartial, n, locale);
    }
    
    public String getAsText(final ReadablePartial readablePartial, final Locale locale) {
        return this.iField.getAsText(readablePartial, locale);
    }
    
    public String getAsText(final int n, final Locale locale) {
        return this.iField.getAsText(n, locale);
    }
    
    public String getAsShortText(final long n, final Locale locale) {
        return this.iField.getAsShortText(n, locale);
    }
    
    public String getAsShortText(final long n) {
        return this.iField.getAsShortText(n);
    }
    
    public String getAsShortText(final ReadablePartial readablePartial, final int n, final Locale locale) {
        return this.iField.getAsShortText(readablePartial, n, locale);
    }
    
    public String getAsShortText(final ReadablePartial readablePartial, final Locale locale) {
        return this.iField.getAsShortText(readablePartial, locale);
    }
    
    public String getAsShortText(final int n, final Locale locale) {
        return this.iField.getAsShortText(n, locale);
    }
    
    public long add(final long n, final int n2) {
        return this.iField.add(n, n2);
    }
    
    public long add(final long n, final long n2) {
        return this.iField.add(n, n2);
    }
    
    public int[] add(final ReadablePartial readablePartial, final int n, final int[] array, final int n2) {
        return this.iField.add(readablePartial, n, array, n2);
    }
    
    public int[] addWrapPartial(final ReadablePartial readablePartial, final int n, final int[] array, final int n2) {
        return this.iField.addWrapPartial(readablePartial, n, array, n2);
    }
    
    public long addWrapField(final long n, final int n2) {
        return this.iField.addWrapField(n, n2);
    }
    
    public int[] addWrapField(final ReadablePartial readablePartial, final int n, final int[] array, final int n2) {
        return this.iField.addWrapField(readablePartial, n, array, n2);
    }
    
    public int getDifference(final long n, final long n2) {
        return this.iField.getDifference(n, n2);
    }
    
    public long getDifferenceAsLong(final long n, final long n2) {
        return this.iField.getDifferenceAsLong(n, n2);
    }
    
    public long set(final long n, final int n2) {
        return this.iField.set(n, n2);
    }
    
    public long set(final long n, final String s, final Locale locale) {
        return this.iField.set(n, s, locale);
    }
    
    public long set(final long n, final String s) {
        return this.iField.set(n, s);
    }
    
    public int[] set(final ReadablePartial readablePartial, final int n, final int[] array, final int n2) {
        return this.iField.set(readablePartial, n, array, n2);
    }
    
    public int[] set(final ReadablePartial readablePartial, final int n, final int[] array, final String s, final Locale locale) {
        return this.iField.set(readablePartial, n, array, s, locale);
    }
    
    public DurationField getDurationField() {
        return this.iField.getDurationField();
    }
    
    public DurationField getRangeDurationField() {
        return this.iField.getRangeDurationField();
    }
    
    public boolean isLeap(final long n) {
        return this.iField.isLeap(n);
    }
    
    public int getLeapAmount(final long n) {
        return this.iField.getLeapAmount(n);
    }
    
    public DurationField getLeapDurationField() {
        return this.iField.getLeapDurationField();
    }
    
    public int getMinimumValue() {
        return this.iField.getMinimumValue();
    }
    
    public int getMinimumValue(final long n) {
        return this.iField.getMinimumValue(n);
    }
    
    public int getMinimumValue(final ReadablePartial readablePartial) {
        return this.iField.getMinimumValue(readablePartial);
    }
    
    public int getMinimumValue(final ReadablePartial readablePartial, final int[] array) {
        return this.iField.getMinimumValue(readablePartial, array);
    }
    
    public int getMaximumValue() {
        return this.iField.getMaximumValue();
    }
    
    public int getMaximumValue(final long n) {
        return this.iField.getMaximumValue(n);
    }
    
    public int getMaximumValue(final ReadablePartial readablePartial) {
        return this.iField.getMaximumValue(readablePartial);
    }
    
    public int getMaximumValue(final ReadablePartial readablePartial, final int[] array) {
        return this.iField.getMaximumValue(readablePartial, array);
    }
    
    public int getMaximumTextLength(final Locale locale) {
        return this.iField.getMaximumTextLength(locale);
    }
    
    public int getMaximumShortTextLength(final Locale locale) {
        return this.iField.getMaximumShortTextLength(locale);
    }
    
    public long roundFloor(final long n) {
        return this.iField.roundFloor(n);
    }
    
    public long roundCeiling(final long n) {
        return this.iField.roundCeiling(n);
    }
    
    public long roundHalfFloor(final long n) {
        return this.iField.roundHalfFloor(n);
    }
    
    public long roundHalfCeiling(final long n) {
        return this.iField.roundHalfCeiling(n);
    }
    
    public long roundHalfEven(final long n) {
        return this.iField.roundHalfEven(n);
    }
    
    public long remainder(final long n) {
        return this.iField.remainder(n);
    }
    
    public String toString() {
        return "DateTimeField[" + this.getName() + ']';
    }
}
