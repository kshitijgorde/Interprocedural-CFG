// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

public class BooleanDV extends TypeValidator
{
    private static final String[] fValueSpace;
    
    public short getAllowedFacets() {
        return 24;
    }
    
    public Object getActualValue(final String s, final ValidationContext validationContext) throws InvalidDatatypeValueException {
        Boolean b;
        if (s.equals(BooleanDV.fValueSpace[0]) || s.equals(BooleanDV.fValueSpace[2])) {
            b = Boolean.FALSE;
        }
        else {
            if (!s.equals(BooleanDV.fValueSpace[1]) && !s.equals(BooleanDV.fValueSpace[3])) {
                throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { s, "boolean" });
            }
            b = Boolean.TRUE;
        }
        return b;
    }
    
    static {
        fValueSpace = new String[] { "false", "true", "0", "1" };
    }
}
