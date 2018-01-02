// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import java.util.Locale;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class NOTATIONDatatypeValidator extends AbstractDatatypeValidator
{
    private DatatypeValidator fBaseValidator;
    private Vector fEnumeration;
    private int fFacetsDefined;
    private int fLength;
    
    public NOTATIONDatatypeValidator() throws InvalidDatatypeFacetException {
        this(null, null, false);
    }
    
    public NOTATIONDatatypeValidator(final DatatypeValidator basetype, final Hashtable hashtable, final boolean b) throws InvalidDatatypeFacetException {
        this.fBaseValidator = null;
        this.fEnumeration = new Vector();
        this.fFacetsDefined = 0;
        this.fLength = 0;
        this.setBasetype(basetype);
        if (hashtable != null) {
            final Enumeration<String> keys = (Enumeration<String>)hashtable.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                if (s.equals("enumeration")) {
                    this.fFacetsDefined += 16;
                    this.fEnumeration = (Vector)hashtable.get(s);
                }
                else {
                    if (!s.equals("length")) {
                        continue;
                    }
                    ++this.fFacetsDefined;
                    final String s2 = hashtable.get(s);
                    try {
                        this.fLength = Integer.parseInt(s2);
                    }
                    catch (NumberFormatException ex) {
                        throw new InvalidDatatypeFacetException("Length value '" + s2 + "' is invalid.");
                    }
                    if (this.fLength < 0) {
                        throw new InvalidDatatypeFacetException("Length value '" + s2 + "'  must be a nonNegativeInteger.");
                    }
                    continue;
                }
            }
        }
        if (this.fBaseValidator != null && (this.fFacetsDefined & 0x10) == 0x0) {
            System.err.println("[Internal parser error] Enumeration facet is required for NOTATION decl!");
        }
    }
    
    public Object validate(final String s, final Object o) throws InvalidDatatypeValueException {
        if (this.fBaseValidator != null) {
            if ((this.fFacetsDefined & 0x10) != 0x0 && !this.fEnumeration.contains(s)) {
                throw new InvalidDatatypeValueException("Value '" + s + "' must be one of " + this.fEnumeration);
            }
            if ((this.fFacetsDefined & 0x1) != 0x0 && s.length() != this.fLength) {
                throw new InvalidDatatypeValueException("Value '" + s + "' with length '" + s.length() + "' is not equal to length facet '" + this.fLength + "'.");
            }
        }
        return null;
    }
    
    public void setLocale(final Locale locale) {
    }
    
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("clone() is not supported in " + this.getClass().getName());
    }
    
    private void setBasetype(final DatatypeValidator fBaseValidator) {
        this.fBaseValidator = fBaseValidator;
    }
}
