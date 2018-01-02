// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.util.URI;

public class AnyURIDV extends TypeValidator
{
    private static final URI BASE_URI;
    
    public short getAllowedFacets() {
        return 126;
    }
    
    public Object getActualValue(final String content, final ValidationContext context) throws InvalidDatatypeValueException {
        try {
            if (content.length() != 0) {
                new URI(AnyURIDV.BASE_URI, content);
            }
        }
        catch (URI.MalformedURIException ex) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { content, "anyURI" });
        }
        return content;
    }
    
    static {
        URI uri = null;
        try {
            uri = new URI("abc://def.ghi.jkl");
        }
        catch (URI.MalformedURIException ex) {}
        BASE_URI = uri;
    }
}
