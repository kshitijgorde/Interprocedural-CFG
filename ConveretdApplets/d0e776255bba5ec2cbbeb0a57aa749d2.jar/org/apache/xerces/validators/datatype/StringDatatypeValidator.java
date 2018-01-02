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

public class StringDatatypeValidator extends AbstractDatatypeValidator
{
    private Locale fLocale;
    DatatypeValidator fBaseValidator;
    private int fLength;
    private int fMaxLength;
    private int fMinLength;
    private String fPattern;
    private Vector fEnumeration;
    private int fFacetsDefined;
    private short fWhiteSpace;
    private RegularExpression fRegex;
    
    public StringDatatypeValidator() throws InvalidDatatypeFacetException {
        this(null, null, false);
    }
    
    public StringDatatypeValidator(final DatatypeValidator basetype, final Hashtable hashtable, final boolean b) throws InvalidDatatypeFacetException {
        this.fLocale = null;
        this.fBaseValidator = null;
        this.fLength = 0;
        this.fMaxLength = Integer.MAX_VALUE;
        this.fMinLength = 0;
        this.fPattern = null;
        this.fEnumeration = null;
        this.fFacetsDefined = 0;
        this.fWhiteSpace = 0;
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
                else if (s.equals("minLength")) {
                    this.fFacetsDefined += 2;
                    final String s3 = hashtable.get(s);
                    try {
                        this.fMinLength = Integer.parseInt(s3);
                    }
                    catch (NumberFormatException ex2) {
                        throw new InvalidDatatypeFacetException("minLength value '" + s3 + "' is invalid.");
                    }
                    if (this.fMinLength < 0) {
                        throw new InvalidDatatypeFacetException("minLength value '" + s3 + "'  must be a nonNegativeInteger.");
                    }
                    continue;
                }
                else if (s.equals("maxLength")) {
                    this.fFacetsDefined += 4;
                    final String s4 = hashtable.get(s);
                    try {
                        this.fMaxLength = Integer.parseInt(s4);
                    }
                    catch (NumberFormatException ex3) {
                        throw new InvalidDatatypeFacetException("maxLength value '" + s4 + "' is invalid.");
                    }
                    if (this.fMaxLength < 0) {
                        throw new InvalidDatatypeFacetException("maxLength value '" + s4 + "'  must be a nonNegativeInteger.");
                    }
                    continue;
                }
                else if (s.equals("pattern")) {
                    this.fFacetsDefined += 8;
                    this.fPattern = hashtable.get(s);
                    this.fRegex = new RegularExpression(this.fPattern, "X");
                }
                else if (s.equals("enumeration")) {
                    this.fFacetsDefined += 16;
                    this.fEnumeration = (Vector)hashtable.get(s);
                }
                else {
                    if (!s.equals("whiteSpace")) {
                        throw new InvalidDatatypeFacetException("invalid facet tag : " + s);
                    }
                    this.fFacetsDefined += 16384;
                    final String s5 = hashtable.get(s);
                    if (s5.equals("replace")) {
                        this.fWhiteSpace = 1;
                    }
                    else if (s5.equals("collapse")) {
                        this.fWhiteSpace = 2;
                    }
                    else {
                        this.fWhiteSpace = 0;
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
                throw new InvalidDatatypeFacetException("Value of minLength = '" + this.fMinLength + "'must be less than the value of maxLength = '" + this.fMaxLength + "'.");
            }
        }
    }
    
    public short getWSFacet() {
        return this.fWhiteSpace;
    }
    
    public Object validate(final String s, final Object o) throws InvalidDatatypeValueException {
        this.checkContent(s, o);
        return null;
    }
    
    public void setLocale(final Locale fLocale) {
        this.fLocale = fLocale;
    }
    
    private void checkContent(final String s, final Object o) throws InvalidDatatypeValueException {
        if (this.fBaseValidator != null) {
            this.fBaseValidator.validate(s, o);
        }
        if ((this.fFacetsDefined & 0x4) != 0x0 && s.length() > this.fMaxLength) {
            throw new InvalidDatatypeValueException("Value '" + s + "' with length '" + s.length() + "' exceeds maximum length facet of '" + this.fMaxLength + "'.");
        }
        if ((this.fFacetsDefined & 0x2) != 0x0 && s.length() < this.fMinLength) {
            throw new InvalidDatatypeValueException("Value '" + s + "' with length '" + s.length() + "' is less than minimum length facet of '" + this.fMinLength + "'.");
        }
        if ((this.fFacetsDefined & 0x1) != 0x0 && s.length() != this.fLength) {
            throw new InvalidDatatypeValueException("Value '" + s + "' with length '" + s.length() + "' is not equal to length facet '" + this.fLength + "'.");
        }
        if ((this.fFacetsDefined & 0x10) != 0x0 && !this.fEnumeration.contains(s)) {
            throw new InvalidDatatypeValueException("Value '" + s + "' must be one of " + this.fEnumeration);
        }
        if ((this.fFacetsDefined & 0x8) != 0x0 && (this.fRegex == null || !this.fRegex.matches(s))) {
            throw new InvalidDatatypeValueException("Value '" + s + "' does not match regular expression facet '" + this.fPattern + "'.");
        }
    }
    
    public int compare(final String s, final String s2) {
        return Collator.getInstance(Locale.getDefault()).compare(s, s2);
    }
    
    public Object clone() throws CloneNotSupportedException {
        StringDatatypeValidator stringDatatypeValidator = null;
        try {
            stringDatatypeValidator = new StringDatatypeValidator();
            stringDatatypeValidator.fLocale = this.fLocale;
            stringDatatypeValidator.fBaseValidator = this.fBaseValidator;
            stringDatatypeValidator.fLength = this.fLength;
            stringDatatypeValidator.fMaxLength = this.fMaxLength;
            stringDatatypeValidator.fMinLength = this.fMinLength;
            stringDatatypeValidator.fPattern = this.fPattern;
            stringDatatypeValidator.fWhiteSpace = this.fWhiteSpace;
            stringDatatypeValidator.fEnumeration = this.fEnumeration;
            stringDatatypeValidator.fFacetsDefined = this.fFacetsDefined;
        }
        catch (InvalidDatatypeFacetException ex) {
            ex.printStackTrace();
        }
        return stringDatatypeValidator;
    }
    
    private void setBasetype(final DatatypeValidator fBaseValidator) {
        this.fBaseValidator = fBaseValidator;
    }
}
