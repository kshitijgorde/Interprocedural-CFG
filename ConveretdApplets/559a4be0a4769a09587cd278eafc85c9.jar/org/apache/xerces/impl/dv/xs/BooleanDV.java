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
        return 80;
    }
    
    public Object getActualValue(final String content, final ValidationContext context) throws InvalidDatatypeValueException {
        Boolean ret = null;
        if (content.equals(BooleanDV.fValueSpace[0]) || content.equals(BooleanDV.fValueSpace[2])) {
            ret = Boolean.FALSE;
        }
        else {
            if (!content.equals(BooleanDV.fValueSpace[1]) && !content.equals(BooleanDV.fValueSpace[3])) {
                throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { content, "boolean" });
            }
            ret = Boolean.TRUE;
        }
        return ret;
    }
    
    static {
        fValueSpace = new String[] { "false", "true", "0", "1" };
    }
}
