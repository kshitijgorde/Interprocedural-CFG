// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.dtd;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.impl.dv.DatatypeValidator;

public class ENTITYDatatypeValidator implements DatatypeValidator
{
    public void validate(final String content, final ValidationContext context) throws InvalidDatatypeValueException {
        if (!context.isEntityUnparsed(content)) {
            throw new InvalidDatatypeValueException("ENTITYNotUnparsed", new Object[] { content });
        }
    }
}
