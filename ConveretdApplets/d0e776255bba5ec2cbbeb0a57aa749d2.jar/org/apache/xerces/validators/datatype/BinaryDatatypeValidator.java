// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import org.apache.xerces.utils.HexBin;
import org.apache.xerces.utils.Base64;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class BinaryDatatypeValidator extends AbstractDatatypeValidator
{
    private DatatypeValidator fBaseValidator;
    private int fLength;
    private int fMaxLength;
    private int fMinLength;
    private String fPattern;
    private Vector fEnumeration;
    private int fFacetsDefined;
    private String fEncoding;
    
    public BinaryDatatypeValidator() throws InvalidDatatypeFacetException {
        this(null, null, false);
    }
    
    public BinaryDatatypeValidator(final DatatypeValidator basetype, final Hashtable hashtable, final boolean b) throws InvalidDatatypeFacetException {
        this.fBaseValidator = null;
        this.fLength = 0;
        this.fMaxLength = Integer.MAX_VALUE;
        this.fMinLength = 0;
        this.fPattern = null;
        this.fEnumeration = null;
        this.fFacetsDefined = 0;
        this.fEncoding = "base64";
        if (basetype != null) {
            this.setBasetype(basetype);
        }
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
                    }
                    else if (s.equals("enumeration")) {
                        this.fFacetsDefined += 16;
                        this.fEnumeration = (Vector)hashtable.get(s);
                    }
                    else {
                        if (!s.equals("encoding")) {
                            throw new InvalidDatatypeFacetException();
                        }
                        this.fFacetsDefined += 32;
                        this.fEncoding = hashtable.get(s);
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
        if (this.fBaseValidator != null) {
            this.fBaseValidator.validate(s, o);
        }
        if ((this.fFacetsDefined & 0x800) != 0x0) {
            if (this.fEncoding.equals("base64")) {
                if (!Base64.isBase64(s)) {
                    throw new InvalidDatatypeValueException("Value '" + s + "'  must be" + "is not encoded in Base64");
                }
            }
            else if (!HexBin.isHex(s)) {
                throw new InvalidDatatypeValueException("Value '" + s + "'  must be" + "is not encoded in Hex");
            }
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
