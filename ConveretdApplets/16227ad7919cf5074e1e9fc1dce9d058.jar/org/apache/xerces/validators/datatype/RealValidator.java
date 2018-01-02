// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import java.util.Vector;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;

public class RealValidator implements InternalDatatypeValidator
{
    double fMaxInclusive;
    boolean fIsMaxInclusive;
    double fMaxExclusive;
    boolean fIsMaxExclusive;
    double fMinInclusive;
    boolean fIsMinInclusive;
    double fMinExclusive;
    boolean fIsMinExclusive;
    double fMinAbsoluteValue;
    boolean fIsMinAbsoluteValue;
    double fMaxAbsoluteValue;
    boolean fIsMaxAbsoluteValue;
    double[] fEnumValues;
    boolean fHasEnums;
    RealValidator fBaseValidator;
    private DatatypeMessageProvider fMessageProvider;
    private Locale fLocale;
    
    public void validate(final String s) throws InvalidDatatypeValueException {
        double doubleValue;
        try {
            doubleValue = Double.valueOf(s);
        }
        catch (NumberFormatException ex) {
            throw new InvalidDatatypeValueException(this.getErrorString(13, 0, new Object[] { s }));
        }
        this.boundsCheck(doubleValue);
        if (this.fHasEnums) {
            this.enumCheck(doubleValue);
        }
    }
    
    public void validate(final int n) throws InvalidDatatypeValueException {
    }
    
    boolean ensureFacetsAreConsistent(final Hashtable hashtable) {
        boolean b = true;
        final Enumeration<String> keys = (Enumeration<String>)hashtable.keys();
        while (b && keys.hasMoreElements()) {
            final String s = keys.nextElement();
            if (!s.equals("enumeration")) {
                final String s2 = hashtable.get(s);
                double doubleValue = 0.0;
                try {
                    doubleValue = Double.valueOf(s2);
                }
                catch (NumberFormatException ex) {
                    b = false;
                }
                if (s.equals("minInclusive") && this.fIsMinInclusive) {
                    b = (this.fMinInclusive <= doubleValue);
                }
                else if (s.equals("minExclusive") && this.fIsMinExclusive) {
                    b = (this.fMinExclusive < doubleValue);
                }
                else if (s.equals("maxInclusive") && this.fIsMaxInclusive) {
                    b = (this.fMaxInclusive >= doubleValue);
                }
                else if (s.equals("maxExclusive") && this.fIsMaxExclusive) {
                    b = (this.fMaxExclusive > doubleValue);
                }
                else if (s.equals("minAbsoluteValue") && this.fIsMinAbsoluteValue) {
                    b = (this.fMinAbsoluteValue <= doubleValue);
                }
                else {
                    if (!s.equals("maxAbsoluteValue") || !this.fIsMaxAbsoluteValue) {
                        continue;
                    }
                    b = (this.fMaxAbsoluteValue >= doubleValue);
                }
            }
        }
        return b;
    }
    
    public void setFacets(final Hashtable hashtable) throws UnknownFacetException, IllegalFacetException, IllegalFacetValueException {
        if (this.fBaseValidator != null && !this.fBaseValidator.ensureFacetsAreConsistent(hashtable)) {
            throw new IllegalFacetValueException(this.getErrorString(4, 0, null));
        }
        final boolean fIsMinInclusive = false;
        this.fHasEnums = fIsMinInclusive;
        this.fIsMaxExclusive = fIsMinInclusive;
        this.fIsMaxInclusive = fIsMinInclusive;
        this.fIsMinExclusive = fIsMinInclusive;
        this.fIsMinInclusive = fIsMinInclusive;
        final Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            final String s = (String)keys.nextElement();
            if (!s.equals("enumeration")) {
                final String s2 = (String)hashtable.get(s);
                double doubleValue;
                try {
                    doubleValue = Double.valueOf(s2);
                }
                catch (NumberFormatException ex) {
                    throw new IllegalFacetValueException(this.getErrorString(5, 0, new Object[] { s2, s }));
                }
                if (s.equals("minInclusive")) {
                    this.fIsMinInclusive = true;
                    this.fMinInclusive = doubleValue;
                }
                else if (s.equals("minExclusive")) {
                    this.fIsMinExclusive = true;
                    this.fMinExclusive = doubleValue;
                }
                else if (s.equals("maxInclusive")) {
                    this.fIsMaxInclusive = true;
                    this.fMaxInclusive = doubleValue;
                }
                else if (s.equals("maxExclusive")) {
                    this.fIsMaxExclusive = true;
                    this.fMaxExclusive = doubleValue;
                }
                else if (s.equals("minAbsoluteValue")) {
                    this.fIsMinAbsoluteValue = true;
                    this.fMinAbsoluteValue = doubleValue;
                }
                else if (s.equals("maxAbsoluteValue")) {
                    this.fIsMaxAbsoluteValue = true;
                    this.fMaxAbsoluteValue = doubleValue;
                }
                else {
                    if (s.equals("enumeration")) {
                        continue;
                    }
                    if (s.equals("precision") || s.equals("scale") || s.equals("length") || s.equals("maxLength") || s.equals("literal") || s.equals("lexicalRepresentation") || s.equals("lexical")) {
                        throw new IllegalFacetException(this.getErrorString(14, 0, null));
                    }
                    throw new UnknownFacetException(this.getErrorString(7, 0, new Object[] { s }));
                }
            }
        }
        final Vector<String> vector = hashtable.get("enumeration");
        if (vector != null) {
            this.fHasEnums = true;
            this.fEnumValues = new double[vector.size()];
            for (int i = 0; i < vector.size(); ++i) {
                try {
                    this.boundsCheck(this.fEnumValues[i] = Double.valueOf(vector.elementAt(i)));
                }
                catch (InvalidDatatypeValueException ex2) {
                    throw new IllegalFacetValueException(this.getErrorString(8, 0, new Object[] { vector.elementAt(i) }));
                }
                catch (NumberFormatException ex3) {
                    System.out.println("Internal Error parsing enumerated values for real type");
                }
            }
        }
    }
    
    public void setFacets(final int[] array) throws UnknownFacetException, IllegalFacetException, IllegalFacetValueException {
    }
    
    public void setBasetype(final DatatypeValidator datatypeValidator) {
        this.fBaseValidator = (RealValidator)datatypeValidator;
    }
    
    private void boundsCheck(final double n) throws InvalidDatatypeValueException {
        boolean b;
        if (this.fIsMaxInclusive) {
            b = (n <= this.fMaxInclusive);
        }
        else if (this.fIsMaxExclusive) {
            b = (n < this.fMaxExclusive);
        }
        else {
            b = (!this.fIsMaxInclusive && !this.fIsMaxExclusive);
        }
        boolean b2;
        if (this.fIsMinInclusive) {
            b2 = (n >= this.fMinInclusive);
        }
        else if (this.fIsMinExclusive) {
            b2 = (n > this.fMinInclusive);
        }
        else {
            b2 = (!this.fIsMinInclusive && !this.fIsMinExclusive);
        }
        if (!b2 || !b) {
            throw new InvalidDatatypeValueException(this.getErrorString(9, 0, new Object[] { new Double(n) }));
        }
    }
    
    private void enumCheck(final double n) throws InvalidDatatypeValueException {
        for (int i = 0; i < this.fEnumValues.length; ++i) {
            if (n == this.fEnumValues[i]) {
                return;
            }
        }
        throw new InvalidDatatypeValueException(this.getErrorString(10, 0, new Object[] { new Double(n) }));
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
    
    public RealValidator() {
        this.fIsMaxInclusive = false;
        this.fIsMaxExclusive = false;
        this.fIsMinInclusive = false;
        this.fIsMinExclusive = false;
        this.fIsMinAbsoluteValue = false;
        this.fIsMaxAbsoluteValue = false;
        this.fHasEnums = false;
        this.fMessageProvider = new DatatypeMessageProvider();
    }
}
