// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.field;

import org.joda.time.DurationFieldType;
import java.io.Serializable;
import org.joda.time.DurationField;

public final class MillisDurationField extends DurationField implements Serializable
{
    private static final long serialVersionUID = 2656707858124633367L;
    public static final DurationField INSTANCE;
    
    public DurationFieldType getType() {
        return DurationFieldType.millis();
    }
    
    public String getName() {
        return "millis";
    }
    
    public boolean isSupported() {
        return true;
    }
    
    public final boolean isPrecise() {
        return true;
    }
    
    public final long getUnitMillis() {
        return 1L;
    }
    
    public int getValue(final long n) {
        return FieldUtils.safeToInt(n);
    }
    
    public long getValueAsLong(final long n) {
        return n;
    }
    
    public int getValue(final long n, final long n2) {
        return FieldUtils.safeToInt(n);
    }
    
    public long getValueAsLong(final long n, final long n2) {
        return n;
    }
    
    public long getMillis(final int n) {
        return n;
    }
    
    public long getMillis(final long n) {
        return n;
    }
    
    public long getMillis(final int n, final long n2) {
        return n;
    }
    
    public long getMillis(final long n, final long n2) {
        return n;
    }
    
    public long add(final long n, final int n2) {
        return FieldUtils.safeAdd(n, n2);
    }
    
    public long add(final long n, final long n2) {
        return FieldUtils.safeAdd(n, n2);
    }
    
    public int getDifference(final long n, final long n2) {
        return FieldUtils.safeToInt(FieldUtils.safeSubtract(n, n2));
    }
    
    public long getDifferenceAsLong(final long n, final long n2) {
        return FieldUtils.safeSubtract(n, n2);
    }
    
    public int compareTo(final Object o) {
        final long unitMillis = ((DurationField)o).getUnitMillis();
        final long unitMillis2 = this.getUnitMillis();
        if (unitMillis2 == unitMillis) {
            return 0;
        }
        if (unitMillis2 < unitMillis) {
            return -1;
        }
        return 1;
    }
    
    public String toString() {
        return "DurationField[millis]";
    }
    
    private Object readResolve() {
        return MillisDurationField.INSTANCE;
    }
    
    static {
        INSTANCE = new MillisDurationField();
    }
}
