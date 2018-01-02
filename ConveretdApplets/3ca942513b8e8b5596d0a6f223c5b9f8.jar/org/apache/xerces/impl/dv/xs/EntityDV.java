// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.impl.dv.ValidationContext;

public class EntityDV extends TypeValidator
{
    public short getAllowedFacets() {
        return 2079;
    }
    
    public Object getActualValue(final String s, final ValidationContext validationContext) throws InvalidDatatypeValueException {
        if (!XMLChar.isValidNCName(s)) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { s, "NCName" });
        }
        return s;
    }
    
    public void checkExtraRules(final Object o, final ValidationContext validationContext) throws InvalidDatatypeValueException {
        if (!validationContext.isEntityUnparsed((String)o)) {
            throw new InvalidDatatypeValueException("UndeclaredEntity", new Object[] { o });
        }
    }
}
