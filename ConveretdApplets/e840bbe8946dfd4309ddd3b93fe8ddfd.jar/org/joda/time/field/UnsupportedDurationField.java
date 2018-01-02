// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.field;

import org.joda.time.DurationFieldType;
import java.util.HashMap;
import java.io.Serializable;
import org.joda.time.DurationField;

public final class UnsupportedDurationField extends DurationField implements Serializable
{
    private static final long serialVersionUID = -6390301302770925357L;
    private static HashMap cCache;
    private final DurationFieldType iType;
    
    public static synchronized UnsupportedDurationField getInstance(final DurationFieldType durationFieldType) {
        UnsupportedDurationField unsupportedDurationField;
        if (UnsupportedDurationField.cCache == null) {
            UnsupportedDurationField.cCache = new HashMap(7);
            unsupportedDurationField = null;
        }
        else {
            unsupportedDurationField = UnsupportedDurationField.cCache.get(durationFieldType);
        }
        if (unsupportedDurationField == null) {
            unsupportedDurationField = new UnsupportedDurationField(durationFieldType);
            UnsupportedDurationField.cCache.put(durationFieldType, unsupportedDurationField);
        }
        return unsupportedDurationField;
    }
    
    private UnsupportedDurationField(final DurationFieldType iType) {
        this.iType = iType;
    }
    
    public final DurationFieldType getType() {
        return this.iType;
    }
    
    public String getName() {
        return this.iType.getName();
    }
    
    public boolean isSupported() {
        return false;
    }
    
    public boolean isPrecise() {
        return true;
    }
    
    public int getValue(final long n) {
        throw this.unsupported();
    }
    
    public long getValueAsLong(final long n) {
        throw this.unsupported();
    }
    
    public int getValue(final long n, final long n2) {
        throw this.unsupported();
    }
    
    public long getValueAsLong(final long n, final long n2) {
        throw this.unsupported();
    }
    
    public long getMillis(final int n) {
        throw this.unsupported();
    }
    
    public long getMillis(final long n) {
        throw this.unsupported();
    }
    
    public long getMillis(final int n, final long n2) {
        throw this.unsupported();
    }
    
    public long getMillis(final long n, final long n2) {
        throw this.unsupported();
    }
    
    public long add(final long n, final int n2) {
        throw this.unsupported();
    }
    
    public long add(final long n, final long n2) {
        throw this.unsupported();
    }
    
    public int getDifference(final long n, final long n2) {
        throw this.unsupported();
    }
    
    public long getDifferenceAsLong(final long n, final long n2) {
        throw this.unsupported();
    }
    
    public long getUnitMillis() {
        return 0L;
    }
    
    public int compareTo(final Object o) {
        return 0;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnsupportedDurationField)) {
            return false;
        }
        final UnsupportedDurationField unsupportedDurationField = (UnsupportedDurationField)o;
        if (unsupportedDurationField.getName() == null) {
            return this.getName() == null;
        }
        return unsupportedDurationField.getName().equals(this.getName());
    }
    
    public int hashCode() {
        return this.getName().hashCode();
    }
    
    public String toString() {
        return "UnsupportedDurationField[" + this.getName() + ']';
    }
    
    private Object readResolve() {
        return getInstance(this.iType);
    }
    
    private UnsupportedOperationException unsupported() {
        return new UnsupportedOperationException(this.iType + " field is unsupported");
    }
}
