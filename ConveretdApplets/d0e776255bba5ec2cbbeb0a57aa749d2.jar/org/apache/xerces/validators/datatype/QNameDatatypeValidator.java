// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import java.text.Collator;
import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.xerces.utils.regex.RegularExpression;
import java.util.Vector;
import java.util.Locale;

public class QNameDatatypeValidator extends AbstractDatatypeValidator
{
    private Locale fLocale;
    private DatatypeValidator fBaseValidator;
    private int fLength;
    private int fMaxLength;
    private int fMinLength;
    private String fPattern;
    private Vector fEnumeration;
    private String fMaxInclusive;
    private String fMaxExclusive;
    private String fMinInclusive;
    private String fMinExclusive;
    private int fFacetsDefined;
    private boolean isMaxExclusiveDefined;
    private boolean isMaxInclusiveDefined;
    private boolean isMinExclusiveDefined;
    private boolean isMinInclusiveDefined;
    private RegularExpression fRegex;
    
    public QNameDatatypeValidator() throws InvalidDatatypeFacetException {
        this(null, null, false);
    }
    
    public QNameDatatypeValidator(final DatatypeValidator basetype, final Hashtable hashtable, final boolean b) throws InvalidDatatypeFacetException {
        this.fLocale = null;
        this.fBaseValidator = null;
        this.fLength = 0;
        this.fMaxLength = Integer.MAX_VALUE;
        this.fMinLength = 0;
        this.fPattern = null;
        this.fEnumeration = null;
        this.fMaxInclusive = null;
        this.fMaxExclusive = null;
        this.fMinInclusive = null;
        this.fMinExclusive = null;
        this.fFacetsDefined = 0;
        this.isMaxExclusiveDefined = false;
        this.isMaxInclusiveDefined = false;
        this.isMinExclusiveDefined = false;
        this.isMinInclusiveDefined = false;
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
                        this.fRegex = new RegularExpression(this.fPattern, "X");
                    }
                    else if (s.equals("enumeration")) {
                        this.fFacetsDefined += 16;
                        this.fEnumeration = (Vector)hashtable.get(s);
                    }
                    else if (s.equals("maxInclusive")) {
                        this.fFacetsDefined += 32;
                        this.fMaxInclusive = hashtable.get(s);
                    }
                    else if (s.equals("maxExclusive")) {
                        this.fFacetsDefined += 64;
                        this.fMaxExclusive = hashtable.get(s);
                    }
                    else if (s.equals("minInclusive")) {
                        this.fFacetsDefined += 128;
                        this.fMinInclusive = hashtable.get(s);
                    }
                    else {
                        if (!s.equals("minExclusive")) {
                            throw new InvalidDatatypeFacetException();
                        }
                        this.fFacetsDefined += 256;
                        this.fMinExclusive = hashtable.get(s);
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
            this.isMaxExclusiveDefined = ((this.fFacetsDefined & 0x40) != 0x0);
            this.isMaxInclusiveDefined = ((this.fFacetsDefined & 0x20) != 0x0);
            this.isMinExclusiveDefined = ((this.fFacetsDefined & 0x100) != 0x0);
            this.isMinInclusiveDefined = ((this.fFacetsDefined & 0x80) != 0x0);
            if (this.isMaxExclusiveDefined && this.isMaxInclusiveDefined) {
                throw new InvalidDatatypeFacetException("It is an error for both maxInclusive and maxExclusive to be specified for the same datatype.");
            }
            if (this.isMinExclusiveDefined && this.isMinInclusiveDefined) {
                throw new InvalidDatatypeFacetException("It is an error for both minInclusive and minExclusive to be specified for the same datatype.");
            }
        }
    }
    
    public Object validate(final String s, final Object o) throws InvalidDatatypeValueException {
        this.checkContent(s);
        return null;
    }
    
    public void setLocale(final Locale fLocale) {
        this.fLocale = fLocale;
    }
    
    private void checkContent(final String s) throws InvalidDatatypeValueException {
        if ((this.fFacetsDefined & 0x4) != 0x0 && s.length() > this.fMaxLength) {
            throw new InvalidDatatypeValueException("Value '" + s + "' with length '" + s.length() + "' exceeds maximum length of " + this.fMaxLength + ".");
        }
        if ((this.fFacetsDefined & 0x10) != 0x0 && !this.fEnumeration.contains(s)) {
            throw new InvalidDatatypeValueException("Value '" + s + "' must be one of " + this.fEnumeration);
        }
        if (this.isMaxExclusiveDefined && this.compare(s, this.fMaxExclusive) >= 0) {
            throw new InvalidDatatypeValueException("Value '" + s + "'  must be " + "lexicographically less than '" + this.fMaxExclusive + "'.");
        }
        if (this.isMaxInclusiveDefined && this.compare(s, this.fMaxInclusive) > 0) {
            throw new InvalidDatatypeValueException("Value '" + s + "' must be " + "lexicographically less or equal than '" + this.fMaxInclusive + "'.");
        }
        if (this.isMinExclusiveDefined && this.compare(s, this.fMinExclusive) <= 0) {
            throw new InvalidDatatypeValueException("Value '" + s + "' must be " + "lexicographically greater than '" + this.fMinExclusive + "'.");
        }
        if (this.isMinInclusiveDefined && this.compare(s, this.fMinInclusive) < 0) {
            throw new InvalidDatatypeValueException("Value '" + s + "' must be " + "lexicographically greater or equal than '" + this.fMinInclusive + "'.");
        }
        if ((this.fFacetsDefined & 0x8) != 0x0 && (this.fRegex == null || !this.fRegex.matches(s))) {
            throw new InvalidDatatypeValueException("Value '" + s + "' does not match regular expression facet '" + this.fPattern + "'.");
        }
    }
    
    public Hashtable getFacets() {
        return null;
    }
    
    public int compare(final String s, final String s2) {
        return Collator.getInstance(Locale.getDefault()).compare(s, s2);
    }
    
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("clone() is not supported in " + this.getClass().getName());
    }
    
    private void setBasetype(final DatatypeValidator fBaseValidator) {
        this.fBaseValidator = fBaseValidator;
    }
}
