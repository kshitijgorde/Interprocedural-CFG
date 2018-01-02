// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.format;

import org.joda.time.DurationField;
import java.util.Arrays;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeUtils;
import java.util.Locale;
import org.joda.time.DateTimeZone;
import org.joda.time.Chronology;

public class DateTimeParserBucket
{
    private final Chronology iChrono;
    private final long iMillis;
    private DateTimeZone iZone;
    private int iOffset;
    private Locale iLocale;
    private Integer iPivotYear;
    private SavedField[] iSavedFields;
    private int iSavedFieldsCount;
    private boolean iSavedFieldsShared;
    private Object iSavedState;
    
    public DateTimeParserBucket(final long n, final Chronology chronology, final Locale locale) {
        this(n, chronology, locale, null);
    }
    
    public DateTimeParserBucket(final long iMillis, Chronology chronology, final Locale locale, final Integer iPivotYear) {
        this.iSavedFields = new SavedField[8];
        chronology = DateTimeUtils.getChronology(chronology);
        this.iMillis = iMillis;
        this.iChrono = chronology.withUTC();
        this.iLocale = ((locale == null) ? Locale.getDefault() : locale);
        this.setZone(chronology.getZone());
        this.iPivotYear = iPivotYear;
    }
    
    public Chronology getChronology() {
        return this.iChrono;
    }
    
    public Locale getLocale() {
        return this.iLocale;
    }
    
    public DateTimeZone getZone() {
        return this.iZone;
    }
    
    public void setZone(final DateTimeZone dateTimeZone) {
        this.iSavedState = null;
        this.iZone = ((dateTimeZone == DateTimeZone.UTC) ? null : dateTimeZone);
        this.iOffset = 0;
    }
    
    public int getOffset() {
        return this.iOffset;
    }
    
    public void setOffset(final int iOffset) {
        this.iSavedState = null;
        this.iOffset = iOffset;
        this.iZone = null;
    }
    
    public Integer getPivotYear() {
        return this.iPivotYear;
    }
    
    public void setPivotYear(final Integer iPivotYear) {
        this.iPivotYear = iPivotYear;
    }
    
    public void saveField(final DateTimeField dateTimeField, final int n) {
        this.saveField(new SavedField(dateTimeField, n));
    }
    
    public void saveField(final DateTimeFieldType dateTimeFieldType, final int n) {
        this.saveField(new SavedField(dateTimeFieldType.getField(this.iChrono), n));
    }
    
    public void saveField(final DateTimeFieldType dateTimeFieldType, final String s, final Locale locale) {
        this.saveField(new SavedField(dateTimeFieldType.getField(this.iChrono), s, locale));
    }
    
    private void saveField(final SavedField savedField) {
        SavedField[] iSavedFields = this.iSavedFields;
        final int iSavedFieldsCount = this.iSavedFieldsCount;
        if (iSavedFieldsCount == iSavedFields.length || this.iSavedFieldsShared) {
            final SavedField[] iSavedFields2 = new SavedField[(iSavedFieldsCount == iSavedFields.length) ? (iSavedFieldsCount * 2) : iSavedFields.length];
            System.arraycopy(iSavedFields, 0, iSavedFields2, 0, iSavedFieldsCount);
            iSavedFields = (this.iSavedFields = iSavedFields2);
            this.iSavedFieldsShared = false;
        }
        this.iSavedState = null;
        iSavedFields[iSavedFieldsCount] = savedField;
        this.iSavedFieldsCount = iSavedFieldsCount + 1;
    }
    
    public Object saveState() {
        if (this.iSavedState == null) {
            this.iSavedState = new SavedState();
        }
        return this.iSavedState;
    }
    
    public boolean restoreState(final Object iSavedState) {
        if (iSavedState instanceof SavedState && ((SavedState)iSavedState).restoreState(this)) {
            this.iSavedState = iSavedState;
            return true;
        }
        return false;
    }
    
    public long computeMillis() {
        return this.computeMillis(false, null);
    }
    
    public long computeMillis(final boolean b) {
        return this.computeMillis(b, null);
    }
    
    public long computeMillis(final boolean b, final String s) {
        SavedField[] iSavedFields = this.iSavedFields;
        final int iSavedFieldsCount = this.iSavedFieldsCount;
        if (this.iSavedFieldsShared) {
            iSavedFields = (this.iSavedFields = this.iSavedFields.clone());
            this.iSavedFieldsShared = false;
        }
        sort(iSavedFields, iSavedFieldsCount);
        long n = this.iMillis;
        try {
            for (int i = 0; i < iSavedFieldsCount; ++i) {
                n = iSavedFields[i].set(n, b);
            }
        }
        catch (IllegalFieldValueException ex) {
            if (s != null) {
                ex.prependMessage("Cannot parse \"" + s + '\"');
            }
            throw ex;
        }
        long n2;
        if (this.iZone == null) {
            n2 = n - this.iOffset;
        }
        else {
            final int offsetFromLocal = this.iZone.getOffsetFromLocal(n);
            n2 = n - offsetFromLocal;
            if (offsetFromLocal != this.iZone.getOffset(n2)) {
                String s2 = "Illegal instant due to time zone offset transition (" + this.iZone + ')';
                if (s != null) {
                    s2 = "Cannot parse \"" + s + "\": " + s2;
                }
                throw new IllegalArgumentException(s2);
            }
        }
        return n2;
    }
    
    private static void sort(final Comparable[] array, final int n) {
        if (n > 10) {
            Arrays.sort(array, 0, n);
        }
        else {
            for (int i = 0; i < n; ++i) {
                for (int n2 = i; n2 > 0 && array[n2 - 1].compareTo(array[n2]) > 0; --n2) {
                    final Comparable comparable = array[n2];
                    array[n2] = array[n2 - 1];
                    array[n2 - 1] = comparable;
                }
            }
        }
    }
    
    class SavedState
    {
        final DateTimeZone iZone;
        final int iOffset;
        final SavedField[] iSavedFields;
        final int iSavedFieldsCount;
        
        SavedState() {
            this.iZone = DateTimeParserBucket.this.iZone;
            this.iOffset = DateTimeParserBucket.this.iOffset;
            this.iSavedFields = DateTimeParserBucket.this.iSavedFields;
            this.iSavedFieldsCount = DateTimeParserBucket.this.iSavedFieldsCount;
        }
        
        boolean restoreState(final DateTimeParserBucket dateTimeParserBucket) {
            if (dateTimeParserBucket != DateTimeParserBucket.this) {
                return false;
            }
            dateTimeParserBucket.iZone = this.iZone;
            dateTimeParserBucket.iOffset = this.iOffset;
            dateTimeParserBucket.iSavedFields = this.iSavedFields;
            if (this.iSavedFieldsCount < dateTimeParserBucket.iSavedFieldsCount) {
                dateTimeParserBucket.iSavedFieldsShared = true;
            }
            dateTimeParserBucket.iSavedFieldsCount = this.iSavedFieldsCount;
            return true;
        }
    }
    
    static class SavedField implements Comparable
    {
        final DateTimeField iField;
        final int iValue;
        final String iText;
        final Locale iLocale;
        
        SavedField(final DateTimeField iField, final int iValue) {
            this.iField = iField;
            this.iValue = iValue;
            this.iText = null;
            this.iLocale = null;
        }
        
        SavedField(final DateTimeField iField, final String iText, final Locale iLocale) {
            this.iField = iField;
            this.iValue = 0;
            this.iText = iText;
            this.iLocale = iLocale;
        }
        
        long set(long n, final boolean b) {
            if (this.iText == null) {
                n = this.iField.set(n, this.iValue);
            }
            else {
                n = this.iField.set(n, this.iText, this.iLocale);
            }
            if (b) {
                n = this.iField.roundFloor(n);
            }
            return n;
        }
        
        public int compareTo(final Object o) {
            final DateTimeField iField = ((SavedField)o).iField;
            final int compareReverse = this.compareReverse(this.iField.getRangeDurationField(), iField.getRangeDurationField());
            if (compareReverse != 0) {
                return compareReverse;
            }
            return this.compareReverse(this.iField.getDurationField(), iField.getDurationField());
        }
        
        private int compareReverse(final DurationField durationField, final DurationField durationField2) {
            if (durationField == null || !durationField.isSupported()) {
                if (durationField2 == null || !durationField2.isSupported()) {
                    return 0;
                }
                return -1;
            }
            else {
                if (durationField2 == null || !durationField2.isSupported()) {
                    return 1;
                }
                return -durationField.compareTo(durationField2);
            }
        }
    }
}
