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
        return 126;
    }
    
    public Object getActualValue(final String content, final ValidationContext context) throws InvalidDatatypeValueException {
        if (!XMLChar.isValidNCName(content)) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { content, "NCName" });
        }
        return content;
    }
    
    public void checkExtraRules(final Object value, final ValidationContext context) throws InvalidDatatypeValueException {
        if (!context.isEntityUnparsed((String)value)) {
            throw new InvalidDatatypeValueException("UndeclaredEntity", new Object[] { value });
        }
    }
}
