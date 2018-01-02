// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

public class DecimalDV extends TypeValidator
{
    public final short getAllowedFacets() {
        return 8176;
    }
    
    public Object getActualValue(final String content, final ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return new XDecimal(content);
        }
        catch (NumberFormatException nfe) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { content, "decimal" });
        }
    }
    
    public final int compare(final Object value1, final Object value2) {
        return ((XDecimal)value1).compareTo((XDecimal)value2);
    }
    
    public final int getTotalDigits(final Object value) {
        return ((XDecimal)value).totalDigits;
    }
    
    public final int getFractionDigits(final Object value) {
        return ((XDecimal)value).fracDigits;
    }
}
