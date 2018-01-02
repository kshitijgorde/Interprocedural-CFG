// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

public abstract class TypeValidator
{
    public static final short LESS_THAN = -1;
    public static final short EQUAL = 0;
    public static final short GREATER_THAN = 1;
    public static final short INDETERMINATE = 2;
    
    public abstract short getAllowedFacets();
    
    public abstract Object getActualValue(final String p0, final ValidationContext p1) throws InvalidDatatypeValueException;
    
    public void checkExtraRules(final Object o, final ValidationContext validationContext) throws InvalidDatatypeValueException {
    }
    
    public boolean isIdentical(final Object o, final Object o2) {
        return o.equals(o2);
    }
    
    public int compare(final Object o, final Object o2) {
        return -1;
    }
    
    public int getDataLength(final Object o) {
        return (o instanceof String) ? ((String)o).length() : -1;
    }
    
    public int getTotalDigits(final Object o) {
        return -1;
    }
    
    public int getFractionDigits(final Object o) {
        return -1;
    }
    
    public static final boolean isDigit(final char c) {
        return c >= '0' && c <= '9';
    }
    
    public static final int getDigit(final char c) {
        return isDigit(c) ? (c - '0') : -1;
    }
}
