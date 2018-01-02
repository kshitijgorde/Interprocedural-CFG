// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import org.apache.xerces.utils.URI;
import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.xerces.utils.regex.RegularExpression;
import java.util.Vector;

public class URIReferenceDatatypeValidator extends AbstractDatatypeValidator
{
    private DatatypeValidator fBaseValidator;
    private int fLength;
    private int fMaxLength;
    private int fMinLength;
    private String fPattern;
    private Vector fEnumeration;
    private int fFacetsDefined;
    private RegularExpression fRegex;
    
    public URIReferenceDatatypeValidator() throws InvalidDatatypeFacetException {
        this(null, null, false);
    }
    
    public URIReferenceDatatypeValidator(final DatatypeValidator basetype, final Hashtable hashtable, final boolean b) throws InvalidDatatypeFacetException {
        this.fBaseValidator = null;
        this.fLength = 0;
        this.fMaxLength = Integer.MAX_VALUE;
        this.fMinLength = 0;
        this.fPattern = null;
        this.fEnumeration = null;
        this.fFacetsDefined = 0;
        this.fRegex = null;
        this.setBasetype(basetype);
        if (hashtable != null) {
            final Enumeration<String> keys = (Enumeration<String>)hashtable.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                if (s.equals("length")) {
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
                else {
                    if (s.equals("minLength")) {
                        this.fFacetsDefined += 2;
                        final String s3 = hashtable.get(s);
                        try {
                            this.fMinLength = Integer.parseInt(s3);
                            continue;
                        }
                        catch (NumberFormatException ex2) {
                            throw new InvalidDatatypeFacetException("maxLength value '" + s3 + "' is invalid.");
                        }
                    }
                    if (s.equals("maxLength")) {
                        this.fFacetsDefined += 4;
                        final String s4 = hashtable.get(s);
                        try {
                            this.fMaxLength = Integer.parseInt(s4);
                            continue;
                        }
                        catch (NumberFormatException ex3) {
                            throw new InvalidDatatypeFacetException("maxLength value '" + s4 + "' is invalid.");
                        }
                    }
                    if (s.equals("pattern")) {
                        this.fFacetsDefined += 8;
                        this.fPattern = hashtable.get(s);
                        if (this.fPattern == null) {
                            continue;
                        }
                        this.fRegex = new RegularExpression(this.fPattern, "X");
                    }
                    else {
                        if (!s.equals("enumeration")) {
                            throw new InvalidDatatypeFacetException();
                        }
                        this.fFacetsDefined += 16;
                        this.fEnumeration = (Vector)hashtable.get(s);
                    }
                }
            }
            if ((this.fFacetsDefined & 0x1) != 0x0) {
                if ((this.fFacetsDefined & 0x4) != 0x0) {
                    throw new InvalidDatatypeFacetException("It is an error for both length and maxLength to be members of facets.");
                }
                if ((this.fFacetsDefined & 0x2) != 0x0) {
                    throw new InvalidDatatypeFacetException("It is an error for both length and minLength to be members of facets.");
                }
            }
            if ((this.fFacetsDefined & 0x6) != 0x0 && this.fMinLength > this.fMaxLength) {
                throw new InvalidDatatypeFacetException("Value of maxLength = " + this.fMaxLength + "must be greater that the value of minLength" + this.fMinLength);
            }
        }
    }
    
    public Object validate(final String s, final Object o) throws InvalidDatatypeValueException {
        final int length = s.length();
        if ((this.fFacetsDefined & 0x8) != 0x0 && (this.fRegex == null || !this.fRegex.matches(s))) {
            throw new InvalidDatatypeValueException("Value '" + s + "' does not match regular expression facet" + this.fPattern);
        }
        if ((this.fFacetsDefined & 0x4) != 0x0 && length > this.fMaxLength) {
            throw new InvalidDatatypeValueException("Value '" + s + "' with length '" + s.length() + "' exceeds maximum length facet of '" + this.fMaxLength + "'.");
        }
        if ((this.fFacetsDefined & 0x2) != 0x0 && length < this.fMinLength) {
            throw new InvalidDatatypeValueException("Value '" + s + "' with length '" + s.length() + "' is less than minimum length facet of '" + this.fMinLength + "'.");
        }
        if ((this.fFacetsDefined & 0x1) != 0x0 && length != this.fLength) {
            throw new InvalidDatatypeValueException("Value '" + s + "' with length '" + s.length() + "' is not equal to length facet '" + this.fLength + "'.");
        }
        try {
            if (length != 0) {
                final URI uri = new URI(s);
            }
        }
        catch (URI.MalformedURIException ex) {
            throw new InvalidDatatypeValueException("Value '" + s + "' is a Malformed URI ");
        }
        return null;
    }
    
    public int compare(final String s, final String s2) {
        return 0;
    }
    
    public Hashtable getFacets() {
        return null;
    }
    
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("clone() is not supported in " + this.getClass().getName());
    }
    
    private void setBasetype(final DatatypeValidator fBaseValidator) {
        this.fBaseValidator = fBaseValidator;
    }
}
