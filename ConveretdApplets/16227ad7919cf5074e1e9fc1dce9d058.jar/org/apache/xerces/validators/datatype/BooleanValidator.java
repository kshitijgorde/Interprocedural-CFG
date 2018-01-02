// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import java.util.Hashtable;
import java.util.Locale;

public class BooleanValidator implements InternalDatatypeValidator
{
    private Locale fLocale;
    private DatatypeMessageProvider fMessageProvider;
    
    public void validate(final String s) throws InvalidDatatypeValueException {
        if (!s.equals("true") && !s.equals("false")) {
            throw new InvalidDatatypeValueException(this.getErrorString(2, 0, new Object[] { s }));
        }
    }
    
    public void validate(final int n) throws InvalidDatatypeValueException {
    }
    
    public void setFacets(final Hashtable hashtable) throws UnknownFacetException, IllegalFacetException, IllegalFacetValueException {
        throw new IllegalFacetException();
    }
    
    public void setFacets(final int[] array) throws UnknownFacetException, IllegalFacetException, IllegalFacetValueException {
        throw new IllegalFacetException();
    }
    
    public void setBasetype(final DatatypeValidator datatypeValidator) {
    }
    
    public void setLocale(final Locale locale) {
    }
    
    private String getErrorString(final int n, final int n2, final Object[] array) {
        try {
            return this.fMessageProvider.createMessage(this.fLocale, n, n2, array);
        }
        catch (Exception ex) {
            return "Illegal Errorcode " + n2;
        }
    }
    
    public BooleanValidator() {
        this.fMessageProvider = new DatatypeMessageProvider();
    }
}
