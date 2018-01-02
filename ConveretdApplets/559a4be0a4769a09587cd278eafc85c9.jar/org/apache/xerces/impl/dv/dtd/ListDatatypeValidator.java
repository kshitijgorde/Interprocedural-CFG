// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.dtd;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import java.util.StringTokenizer;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.impl.dv.DatatypeValidator;

public class ListDatatypeValidator implements DatatypeValidator
{
    DatatypeValidator fItemValidator;
    
    public ListDatatypeValidator(final DatatypeValidator itemDV) {
        this.fItemValidator = itemDV;
    }
    
    public void validate(final String content, final ValidationContext context) throws InvalidDatatypeValueException {
        final StringTokenizer parsedList = new StringTokenizer(content);
        final int numberOfTokens = parsedList.countTokens();
        if (numberOfTokens == 0) {
            throw new InvalidDatatypeValueException("EmptyList", (Object[])null);
        }
        while (parsedList.hasMoreTokens()) {
            this.fItemValidator.validate(parsedList.nextToken(), context);
        }
    }
}
