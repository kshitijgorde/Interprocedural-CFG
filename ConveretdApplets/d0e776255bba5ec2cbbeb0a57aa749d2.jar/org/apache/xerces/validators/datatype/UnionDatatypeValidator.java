// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.xerces.utils.regex.RegularExpression;
import java.util.Vector;
import java.util.Locale;

public class UnionDatatypeValidator extends AbstractDatatypeValidator
{
    private Locale fLocale;
    private Vector fBaseValidators;
    private DatatypeValidator fBaseValidator;
    private int fValidatorsSize;
    private String fPattern;
    private Vector fEnumeration;
    private int fFacetsDefined;
    private StringBuffer errorMsg;
    private RegularExpression fRegex;
    
    public UnionDatatypeValidator() throws InvalidDatatypeFacetException {
        this(null, null, false);
    }
    
    public UnionDatatypeValidator(final DatatypeValidator basetype, final Hashtable hashtable, final boolean b) throws InvalidDatatypeFacetException {
        this.fLocale = null;
        this.fBaseValidators = null;
        this.fBaseValidator = null;
        this.fValidatorsSize = 0;
        this.fPattern = null;
        this.fEnumeration = null;
        this.fFacetsDefined = 0;
        this.errorMsg = null;
        this.fRegex = null;
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
                    if (!s.equals("pattern")) {
                        throw new InvalidDatatypeFacetException("invalid facet tag : " + s);
                    }
                    this.fFacetsDefined += 8;
                    this.fPattern = hashtable.get(s);
                    this.fRegex = new RegularExpression(this.fPattern, "X");
                }
            }
        }
    }
    
    public UnionDatatypeValidator(final Vector fBaseValidators) {
        this.fLocale = null;
        this.fBaseValidators = null;
        this.fBaseValidator = null;
        this.fValidatorsSize = 0;
        this.fPattern = null;
        this.fEnumeration = null;
        this.fFacetsDefined = 0;
        this.errorMsg = null;
        this.fRegex = null;
        if (fBaseValidators != null) {
            this.fValidatorsSize = fBaseValidators.size();
            this.fBaseValidators = new Vector(this.fValidatorsSize);
            this.fBaseValidators = fBaseValidators;
        }
    }
    
    public Object validate(final String s, final Object o) throws InvalidDatatypeValueException {
        if (s == null && o != null) {
            this.fBaseValidator.validate(s, o);
        }
        else {
            this.checkContentEnum(s, o, false, null);
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
        if (this.fBaseValidator != null) {
            return this.fBaseValidator.compare(s, s2);
        }
        int n = -1;
        while (++n < this.fValidatorsSize) {
            if (((DatatypeValidator)this.fBaseValidators.elementAt(n)).compare(s, s2) == 0) {
                return 0;
            }
        }
        return -1;
    }
    
    public Object clone() throws CloneNotSupportedException {
        UnionDatatypeValidator unionDatatypeValidator = null;
        try {
            unionDatatypeValidator = new UnionDatatypeValidator();
            unionDatatypeValidator.fLocale = this.fLocale;
            unionDatatypeValidator.fBaseValidator = this.fBaseValidator;
            unionDatatypeValidator.fBaseValidators = (Vector)this.fBaseValidators.clone();
            unionDatatypeValidator.fPattern = this.fPattern;
            unionDatatypeValidator.fEnumeration = this.fEnumeration;
            unionDatatypeValidator.fFacetsDefined = this.fFacetsDefined;
        }
        catch (InvalidDatatypeFacetException ex) {
            ex.printStackTrace();
        }
        return unionDatatypeValidator;
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
    
    private void checkContentEnum(final String s, final Object o, boolean b, Vector vector) throws InvalidDatatypeValueException {
        int n = -1;
        int n2 = 0;
        if (this.fBaseValidator != null) {
            if ((this.fFacetsDefined & 0x8) != 0x0) {
                if (this.fRegex == null || !this.fRegex.matches(s)) {
                    throw new InvalidDatatypeValueException("Value '" + s + "' does not match regular expression facet '" + this.fPattern + "'.");
                }
                b = true;
            }
            if (vector != null) {
                if (!this.verifyEnum(vector)) {
                    throw new InvalidDatatypeValueException("Enumeration '" + vector + "' for value '" + s + "' is based on enumeration '" + this.fEnumeration + "'");
                }
            }
            else {
                vector = ((this.fEnumeration != null) ? this.fEnumeration : null);
            }
            ((UnionDatatypeValidator)this.fBaseValidator).checkContentEnum(s, o, b, vector);
            return;
        }
        while (++n < this.fValidatorsSize) {
            final DatatypeValidator datatypeValidator = this.fBaseValidators.elementAt(n);
            if (n2 != 0) {
                break;
            }
            try {
                if (datatypeValidator instanceof ListDatatypeValidator) {
                    if (b) {
                        throw new InvalidDatatypeValueException("Facet \"Pattern\" can not be applied to a list datatype");
                    }
                    ((ListDatatypeValidator)datatypeValidator).checkContentEnum(s, o, vector);
                }
                else if (datatypeValidator instanceof UnionDatatypeValidator) {
                    ((UnionDatatypeValidator)datatypeValidator).checkContentEnum(s, o, b, vector);
                }
                else if (vector != null) {
                    if (datatypeValidator instanceof DecimalDatatypeValidator) {
                        ((DecimalDatatypeValidator)datatypeValidator).checkContentEnum(s, o, vector);
                    }
                    else if (datatypeValidator instanceof FloatDatatypeValidator) {
                        ((FloatDatatypeValidator)datatypeValidator).checkContentEnum(s, o, vector);
                    }
                    else if (datatypeValidator instanceof DoubleDatatypeValidator) {
                        ((DoubleDatatypeValidator)datatypeValidator).checkContentEnum(s, o, vector);
                    }
                    else {
                        if (!vector.contains(s)) {
                            throw new InvalidDatatypeValueException("Value '" + s + "' must be one of " + vector);
                        }
                        datatypeValidator.validate(s, o);
                    }
                }
                else {
                    datatypeValidator.validate(s, o);
                }
                n2 = 1;
            }
            catch (InvalidDatatypeValueException ex) {}
        }
        if (n2 == 0) {
            throw new InvalidDatatypeValueException("Content '" + s + "' does not match any union types");
        }
    }
    
    private void setBasetype(final DatatypeValidator fBaseValidator) {
        this.fBaseValidator = fBaseValidator;
    }
}
