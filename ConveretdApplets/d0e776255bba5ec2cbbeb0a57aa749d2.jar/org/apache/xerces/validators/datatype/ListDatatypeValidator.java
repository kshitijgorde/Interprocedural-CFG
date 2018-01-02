// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.xerces.utils.regex.RegularExpression;
import java.util.Vector;
import java.util.Locale;

public class ListDatatypeValidator extends AbstractDatatypeValidator
{
    private Locale fLocale;
    DatatypeValidator fBaseValidator;
    private int fLength;
    private int fMaxLength;
    private int fMinLength;
    private String fPattern;
    private Vector fEnumeration;
    private int fFacetsDefined;
    private boolean fDerivedByList;
    private RegularExpression fRegex;
    
    public ListDatatypeValidator() throws InvalidDatatypeFacetException {
        this(null, null, false);
    }
    
    public ListDatatypeValidator(final DatatypeValidator basetype, final Hashtable hashtable, final boolean fDerivedByList) throws InvalidDatatypeFacetException {
        this.fLocale = null;
        this.fBaseValidator = null;
        this.fLength = 0;
        this.fMaxLength = Integer.MAX_VALUE;
        this.fMinLength = 0;
        this.fPattern = null;
        this.fEnumeration = null;
        this.fFacetsDefined = 0;
        this.fDerivedByList = false;
        this.fRegex = null;
        this.setBasetype(basetype);
        this.fDerivedByList = fDerivedByList;
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
                    if (!s.equals("enumeration")) {
                        throw new InvalidDatatypeFacetException("invalid facet tag : " + s);
                    }
                    this.fFacetsDefined += 16;
                    this.fEnumeration = (Vector)hashtable.get(s);
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
                throw new InvalidDatatypeFacetException("Value of minLength = " + this.fMinLength + "must be greater that the value of maxLength" + this.fMaxLength);
            }
        }
    }
    
    public Object validate(final String s, final Object o) throws InvalidDatatypeValueException {
        if (s == null && o != null) {
            this.fBaseValidator.validate(s, o);
        }
        else {
            this.checkContentEnum(s, o, null);
        }
        return null;
    }
    
    public void setLocale(final Locale fLocale) {
        this.fLocale = fLocale;
    }
    
    public Hashtable getFacets() {
        return null;
    }
    
    public int compare(final String s, final String s2) {
        if (!this.fDerivedByList) {
            return ((ListDatatypeValidator)this.fBaseValidator).compare(s, s2);
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        final StringTokenizer stringTokenizer2 = new StringTokenizer(s2);
        final int countTokens = stringTokenizer.countTokens();
        if (countTokens < stringTokenizer2.countTokens()) {
            return -1;
        }
        if (countTokens > stringTokenizer2.countTokens()) {
            return 1;
        }
        int n = 0;
        while (n++ < countTokens) {
            if (this.fBaseValidator != null) {
                final int compare = this.fBaseValidator.compare(stringTokenizer.nextToken(), stringTokenizer2.nextToken());
                if (compare != 0) {
                    return compare;
                }
                continue;
            }
        }
        return 0;
    }
    
    public Object clone() throws CloneNotSupportedException {
        ListDatatypeValidator listDatatypeValidator = null;
        try {
            listDatatypeValidator = new ListDatatypeValidator();
            listDatatypeValidator.fLocale = this.fLocale;
            listDatatypeValidator.fBaseValidator = this.fBaseValidator;
            listDatatypeValidator.fLength = this.fLength;
            listDatatypeValidator.fMaxLength = this.fMaxLength;
            listDatatypeValidator.fMinLength = this.fMinLength;
            listDatatypeValidator.fPattern = this.fPattern;
            listDatatypeValidator.fEnumeration = this.fEnumeration;
            listDatatypeValidator.fFacetsDefined = this.fFacetsDefined;
            listDatatypeValidator.fDerivedByList = this.fDerivedByList;
        }
        catch (InvalidDatatypeFacetException ex) {
            ex.printStackTrace();
        }
        return listDatatypeValidator;
    }
    
    protected void checkContentEnum(final String s, final Object o, Vector vector) throws InvalidDatatypeValueException {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        final int countTokens = stringTokenizer.countTokens();
        if (!this.fDerivedByList) {
            try {
                if ((this.fFacetsDefined & 0x4) != 0x0 && countTokens > this.fMaxLength) {
                    throw new InvalidDatatypeValueException("Value '" + s + "' with length ='" + countTokens + "' tokens" + "' exceeds maximum length facet of '" + this.fMaxLength + "' tokens.");
                }
                if ((this.fFacetsDefined & 0x2) != 0x0 && countTokens < this.fMinLength) {
                    throw new InvalidDatatypeValueException("Value '" + s + "' with length ='" + countTokens + "' tokens" + "' is less than minimum length facet of '" + this.fMinLength + "' tokens.");
                }
                if ((this.fFacetsDefined & 0x1) != 0x0 && countTokens != this.fLength) {
                    throw new InvalidDatatypeValueException("Value '" + s + "' with length ='" + countTokens + "' tokens" + "' is not equal to length facet of '" + this.fLength + "' tokens.");
                }
                if (vector != null) {
                    if (!this.verifyEnum(vector)) {
                        throw new InvalidDatatypeValueException("Enumeration '" + vector + "' for value '" + s + "' is based on enumeration '" + this.fEnumeration + "'");
                    }
                }
                else {
                    vector = ((this.fEnumeration != null) ? this.fEnumeration : null);
                }
                ((ListDatatypeValidator)this.fBaseValidator).checkContentEnum(s, o, vector);
            }
            catch (NoSuchElementException ex) {
                ex.printStackTrace();
            }
        }
        else if (vector != null) {
            boolean b = true;
            final int size = vector.size();
            final Vector vector2 = new Vector<String>();
            vector2.setSize(1);
            for (int i = 0; i < size; ++i) {
                final String s2 = vector.elementAt(i);
                final StringTokenizer stringTokenizer2 = new StringTokenizer(s2);
                b = true;
                final StringTokenizer stringTokenizer3 = stringTokenizer;
                if (countTokens == stringTokenizer2.countTokens()) {
                    try {
                        if (s2.equals(s)) {
                            for (int j = 0; j < countTokens; ++j) {
                                if (this.fBaseValidator != null) {
                                    this.fBaseValidator.validate(stringTokenizer3.nextToken(), o);
                                }
                            }
                        }
                        else {
                            for (int k = 0; k < countTokens; ++k) {
                                final String nextToken = stringTokenizer3.nextToken();
                                final String nextToken2 = stringTokenizer2.nextToken();
                                vector2.setElementAt(nextToken2, 0);
                                if (this.fBaseValidator instanceof DecimalDatatypeValidator) {
                                    ((DecimalDatatypeValidator)this.fBaseValidator).checkContentEnum(nextToken, o, vector2);
                                }
                                else if (this.fBaseValidator instanceof FloatDatatypeValidator) {
                                    ((FloatDatatypeValidator)this.fBaseValidator).checkContentEnum(nextToken, o, vector2);
                                }
                                else if (this.fBaseValidator instanceof DoubleDatatypeValidator) {
                                    ((DoubleDatatypeValidator)this.fBaseValidator).checkContentEnum(nextToken, o, vector2);
                                }
                                else {
                                    if (!nextToken.equals(nextToken2)) {
                                        throw new InvalidDatatypeValueException("Value '" + s + "' must be one of " + vector);
                                    }
                                    this.fBaseValidator.validate(nextToken, o);
                                }
                            }
                        }
                    }
                    catch (InvalidDatatypeValueException ex2) {
                        b = false;
                    }
                }
                else {
                    b = false;
                }
                if (b) {
                    break;
                }
            }
            if (!b) {
                throw new InvalidDatatypeValueException("Value '" + s + "' does not match list type");
            }
        }
        else {
            for (int l = 0; l < countTokens; ++l) {
                if (this.fBaseValidator != null) {
                    this.fBaseValidator.validate(stringTokenizer.nextToken(), o);
                }
            }
        }
    }
    
    private boolean verifyEnum(final Vector vector) {
        if ((this.fFacetsDefined & 0x10) != 0x0) {
            final Enumeration<Object> elements = vector.elements();
            while (elements.hasMoreElements()) {
                if (!this.fEnumeration.contains(elements.nextElement())) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void setBasetype(final DatatypeValidator fBaseValidator) {
        this.fBaseValidator = fBaseValidator;
    }
}
