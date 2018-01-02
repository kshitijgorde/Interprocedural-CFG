// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import java.util.Vector;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;

public class IntegerValidator implements InternalDatatypeValidator
{
    int fMaxInclusive;
    boolean fIsMaxInclusive;
    int fMaxExclusive;
    boolean fIsMaxExclusive;
    int fMinInclusive;
    boolean fIsMinInclusive;
    int fMinExclusive;
    boolean fIsMinExclusive;
    int[] fEnumValues;
    boolean fHasEnums;
    IntegerValidator fBaseValidator;
    private DatatypeMessageProvider fMessageProvider;
    private Locale fLocale;
    
    public void validate(final String s) throws InvalidDatatypeValueException {
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            throw new InvalidDatatypeValueException(this.getErrorString(11, 0, new Object[] { s }));
        }
        this.boundsCheck(int1);
        if (this.fHasEnums) {
            this.enumCheck(int1);
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
                int int1 = 0;
                try {
                    int1 = Integer.parseInt(s2);
                }
                catch (NumberFormatException ex) {
                    b = false;
                }
                if (s.equals("minInclusive") && this.fIsMinInclusive) {
                    b = (this.fMinInclusive <= int1);
                }
                else if (s.equals("minExclusive") && this.fIsMinExclusive) {
                    b = (this.fMinExclusive < int1);
                }
                else if (s.equals("maxInclusive") && this.fIsMaxInclusive) {
                    b = (this.fMaxInclusive >= int1);
                }
                else {
                    if (!s.equals("maxExclusive") || !this.fIsMaxExclusive) {
                        continue;
                    }
                    b = (this.fMaxExclusive > int1);
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
                int int1;
                try {
                    int1 = Integer.parseInt(s2);
                }
                catch (NumberFormatException ex) {
                    throw new IllegalFacetValueException(this.getErrorString(5, 0, new Object[] { s2, s }));
                }
                if (s.equals("minInclusive")) {
                    this.fIsMinInclusive = true;
                    this.fMinInclusive = int1;
                }
                else if (s.equals("minExclusive")) {
                    this.fIsMinExclusive = true;
                    this.fMinExclusive = int1;
                }
                else if (s.equals("maxInclusive")) {
                    this.fIsMaxInclusive = true;
                    this.fMaxInclusive = int1;
                }
                else if (s.equals("maxExclusive")) {
                    this.fIsMaxExclusive = true;
                    this.fMaxExclusive = int1;
                }
                else {
                    if (s.equals("enumeration")) {
                        continue;
                    }
                    if (s.equals("precision") || s.equals("scale") || s.equals("length") || s.equals("maxLength") || s.equals("literal") || s.equals("lexicalRepresentation") || s.equals("lexical")) {
                        throw new IllegalFacetException(this.getErrorString(12, 0, null));
                    }
                    throw new UnknownFacetException(this.getErrorString(7, 0, new Object[] { s }));
                }
            }
        }
        final Vector<String> vector = hashtable.get("enumeration");
        if (vector != null) {
            this.fHasEnums = true;
            this.fEnumValues = new int[vector.size()];
            for (int i = 0; i < vector.size(); ++i) {
                try {
                    this.boundsCheck(this.fEnumValues[i] = Integer.parseInt(vector.elementAt(i)));
                }
                catch (InvalidDatatypeValueException ex2) {
                    throw new IllegalFacetValueException(this.getErrorString(8, 0, new Object[] { vector.elementAt(i) }));
                }
                catch (NumberFormatException ex3) {
                    System.out.println("Internal Error parsing enumerated values for integer type");
                }
            }
        }
    }
    
    public void setFacets(final int[] array) throws UnknownFacetException, IllegalFacetException, IllegalFacetValueException {
    }
    
    public void setBasetype(final DatatypeValidator datatypeValidator) {
        this.fBaseValidator = (IntegerValidator)datatypeValidator;
    }
    
    private void boundsCheck(final int n) throws InvalidDatatypeValueException {
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
            throw new InvalidDatatypeValueException(this.getErrorString(9, 0, new Object[] { new Integer(n) }));
        }
    }
    
    private void enumCheck(final int n) throws InvalidDatatypeValueException {
        for (int i = 0; i < this.fEnumValues.length; ++i) {
            if (n == this.fEnumValues[i]) {
                return;
            }
        }
        throw new InvalidDatatypeValueException(this.getErrorString(10, 0, new Object[] { new Integer(n) }));
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
    
    public IntegerValidator() {
        this.fIsMaxInclusive = false;
        this.fIsMaxExclusive = false;
        this.fIsMinInclusive = false;
        this.fIsMinExclusive = false;
        this.fHasEnums = false;
        this.fMessageProvider = new DatatypeMessageProvider();
    }
}
