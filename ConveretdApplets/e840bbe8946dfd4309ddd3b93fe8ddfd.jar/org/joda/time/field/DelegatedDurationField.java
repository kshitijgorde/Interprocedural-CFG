// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.field;

import org.joda.time.DurationFieldType;
import java.io.Serializable;
import org.joda.time.DurationField;

public class DelegatedDurationField extends DurationField implements Serializable
{
    private static final long serialVersionUID = -5576443481242007829L;
    private final DurationField iField;
    private final DurationFieldType iType;
    
    protected DelegatedDurationField(final DurationField durationField) {
        this(durationField, null);
    }
    
    protected DelegatedDurationField(final DurationField iField, final DurationFieldType durationFieldType) {
        if (iField == null) {
            throw new IllegalArgumentException("The field must not be null");
        }
        this.iField = iField;
        this.iType = ((durationFieldType == null) ? iField.getType() : durationFieldType);
    }
    
    public final DurationField getWrappedField() {
        return this.iField;
    }
    
    public DurationFieldType getType() {
        return this.iType;
    }
    
    public String getName() {
        return this.iType.getName();
    }
    
    public boolean isSupported() {
        return this.iField.isSupported();
    }
    
    public boolean isPrecise() {
        return this.iField.isPrecise();
    }
    
    public int getValue(final long n) {
        return this.iField.getValue(n);
    }
    
    public long getValueAsLong(final long n) {
        return this.iField.getValueAsLong(n);
    }
    
    public int getValue(final long n, final long n2) {
        return this.iField.getValue(n, n2);
    }
    
    public long getValueAsLong(final long n, final long n2) {
        return this.iField.getValueAsLong(n, n2);
    }
    
    public long getMillis(final int n) {
        return this.iField.getMillis(n);
    }
    
    public long getMillis(final long n) {
        return this.iField.getMillis(n);
    }
    
    public long getMillis(final int n, final long n2) {
        return this.iField.getMillis(n, n2);
    }
    
    public long getMillis(final long n, final long n2) {
        return this.iField.getMillis(n, n2);
    }
    
    public long add(final long n, final int n2) {
        return this.iField.add(n, n2);
    }
    
    public long add(final long n, final long n2) {
        return this.iField.add(n, n2);
    }
    
    public int getDifference(final long n, final long n2) {
        return this.iField.getDifference(n, n2);
    }
    
    public long getDifferenceAsLong(final long n, final long n2) {
        return this.iField.getDifferenceAsLong(n, n2);
    }
    
    public long getUnitMillis() {
        return this.iField.getUnitMillis();
    }
    
    public int compareTo(final Object o) {
        return this.iField.compareTo(o);
    }
    
    public String toString() {
        return (this.iType == null) ? this.iField.toString() : ("DurationField[" + this.iType + ']');
    }
}
