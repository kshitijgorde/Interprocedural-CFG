// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.dtd;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.impl.dv.DatatypeValidator;

public class IDDatatypeValidator implements DatatypeValidator
{
    public void validate(final String content, final ValidationContext context) throws InvalidDatatypeValueException {
        if (!XMLChar.isValidName(content)) {
            throw new InvalidDatatypeValueException("IDInvalid", new Object[] { content });
        }
        if (context.isIdDeclared(content)) {
            throw new InvalidDatatypeValueException("IDNotUnique", new Object[] { content });
        }
        context.addId(content);
    }
}
