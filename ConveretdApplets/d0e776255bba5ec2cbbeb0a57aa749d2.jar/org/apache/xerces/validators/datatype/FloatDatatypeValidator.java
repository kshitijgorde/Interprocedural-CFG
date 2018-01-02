// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;
import org.apache.xerces.utils.regex.RegularExpression;
import java.util.Locale;

public class FloatDatatypeValidator extends AbstractDatatypeValidator
{
    private Locale fLocale;
    private DatatypeValidator fBaseValidator;
    private float[] fEnumFloats;
    private String fPattern;
    private float fMaxInclusive;
    private float fMaxExclusive;
    private float fMinInclusive;
    private float fMinExclusive;
    private int fFacetsDefined;
    private boolean isMaxExclusiveDefined;
    private boolean isMaxInclusiveDefined;
    private boolean isMinExclusiveDefined;
    private boolean isMinInclusiveDefined;
    private DatatypeMessageProvider fMessageProvider;
    private RegularExpression fRegex;
    
    public FloatDatatypeValidator() throws InvalidDatatypeFacetException {
        this(null, null, false);
    }
    
    public FloatDatatypeValidator(final DatatypeValidator basetype, final Hashtable hashtable, final boolean b) throws InvalidDatatypeFacetException {
        this.fLocale = null;
        this.fBaseValidator = null;
        this.fEnumFloats = null;
        this.fPattern = null;
        this.fMaxInclusive = Float.POSITIVE_INFINITY;
        this.fMaxExclusive = Float.POSITIVE_INFINITY;
        this.fMinInclusive = Float.NEGATIVE_INFINITY;
        this.fMinExclusive = Float.NEGATIVE_INFINITY;
        this.fFacetsDefined = 0;
        this.isMaxExclusiveDefined = false;
        this.isMaxInclusiveDefined = false;
        this.isMinExclusiveDefined = false;
        this.isMinInclusiveDefined = false;
        this.fMessageProvider = new DatatypeMessageProvider();
        this.fRegex = null;
        if (basetype != null) {
            this.setBasetype(basetype);
        }
        if (hashtable != null) {
            final Enumeration keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                final String s = (String)keys.nextElement();
                if (s.equals("pattern")) {
                    this.fFacetsDefined += 8;
                    this.fPattern = (String)hashtable.get(s);
                    if (this.fPattern == null) {
                        continue;
                    }
                    this.fRegex = new RegularExpression(this.fPattern, "X");
                }
                else {
                    if (!s.equals("enumeration")) {
                        if (s.equals("maxInclusive")) {
                            this.fFacetsDefined += 32;
                            String s2 = null;
                            try {
                                s2 = (String)hashtable.get(s);
                                this.fMaxInclusive = Float.valueOf(s2);
                                continue;
                            }
                            catch (NumberFormatException ex) {
                                throw new InvalidDatatypeFacetException(this.getErrorString(5, 0, new Object[] { s2, s }));
                            }
                        }
                        if (s.equals("maxExclusive")) {
                            this.fFacetsDefined += 64;
                            String s3 = null;
                            try {
                                s3 = (String)hashtable.get(s);
                                this.fMaxExclusive = Float.valueOf(s3);
                                continue;
                            }
                            catch (NumberFormatException ex2) {
                                throw new InvalidDatatypeFacetException(this.getErrorString(5, 0, new Object[] { s3, s }));
                            }
                        }
                        if (s.equals("minInclusive")) {
                            this.fFacetsDefined += 128;
                            String s4 = null;
                            try {
                                s4 = (String)hashtable.get(s);
                                this.fMinInclusive = Float.valueOf(s4);
                                continue;
                            }
                            catch (NumberFormatException ex3) {
                                throw new InvalidDatatypeFacetException(this.getErrorString(5, 0, new Object[] { s4, s }));
                            }
                        }
                        if (s.equals("minExclusive")) {
                            this.fFacetsDefined += 256;
                            String s5 = null;
                            try {
                                s5 = (String)hashtable.get(s);
                                this.fMinExclusive = Float.valueOf(s5);
                                continue;
                            }
                            catch (NumberFormatException ex4) {
                                throw new InvalidDatatypeFacetException(this.getErrorString(5, 0, new Object[] { s5, s }));
                            }
                        }
                        throw new InvalidDatatypeFacetException(this.getErrorString(1, 0, null));
                    }
                    this.fFacetsDefined += 16;
                }
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
            if (this.isMaxExclusiveDefined && this.isMinExclusiveDefined && this.fMaxExclusive <= this.fMinExclusive) {
                throw new InvalidDatatypeFacetException("maxExclusive value ='" + this.fMaxExclusive + "'must be > than minExclusive value ='" + this.fMinExclusive + "'. ");
            }
            if (this.isMaxInclusiveDefined && this.isMinInclusiveDefined && this.fMaxInclusive < this.fMinInclusive) {
                throw new InvalidDatatypeFacetException("maxInclusive value ='" + this.fMaxInclusive + "'must be >= than minInclusive value ='" + this.fMinInclusive + "'. ");
            }
            if (this.isMaxExclusiveDefined && this.isMinInclusiveDefined && this.fMaxExclusive <= this.fMinInclusive) {
                throw new InvalidDatatypeFacetException("maxExclusive value ='" + this.fMaxExclusive + "'must be > than minInclusive value ='" + this.fMinInclusive + "'. ");
            }
            if (this.isMaxInclusiveDefined && this.isMinExclusiveDefined && this.fMaxInclusive <= this.fMinExclusive) {
                throw new InvalidDatatypeFacetException("maxInclusive value ='" + this.fMaxInclusive + "'must be > than minExclusive value ='" + this.fMinExclusive + "'. ");
            }
            if ((this.fFacetsDefined & 0x10) != 0x0) {
                final Vector<String> vector = hashtable.get("enumeration");
                if (vector != null) {
                    this.fEnumFloats = new float[vector.size()];
                    for (int i = 0; i < vector.size(); ++i) {
                        try {
                            this.boundsCheck(this.fEnumFloats[i] = Float.valueOf(vector.elementAt(i)));
                        }
                        catch (InvalidDatatypeValueException ex5) {
                            throw new InvalidDatatypeFacetException(this.getErrorString(8, 0, new Object[] { vector.elementAt(i) }));
                        }
                        catch (NumberFormatException ex6) {
                            if (vector.elementAt(i).equals("INF")) {
                                this.fEnumFloats[i] = Float.POSITIVE_INFINITY;
                            }
                            else if (vector.elementAt(i).equals("-INF")) {
                                this.fEnumFloats[i] = Float.NEGATIVE_INFINITY;
                            }
                            else {
                                if (!vector.elementAt(i).equals("NaN")) {
                                    throw new InvalidDatatypeFacetException(this.getErrorString(5, 0, new Object[] { vector.elementAt(i), "enumeration" }));
                                }
                                this.fEnumFloats[i] = Float.NaN;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public Object validate(final String s, final Object o) throws InvalidDatatypeValueException {
        this.checkContentEnum(s, o, null);
        return null;
    }
    
    protected void checkContentEnum(final String s, final Object o, final Vector vector) throws InvalidDatatypeValueException {
        if (this.fBaseValidator != null) {
            ((FloatDatatypeValidator)this.fBaseValidator).checkContentEnum(s, o, vector);
        }
        if ((this.fFacetsDefined & 0x8) != 0x0 && (this.fRegex == null || !this.fRegex.matches(s))) {
            throw new InvalidDatatypeValueException("Value'" + s + "does not match regular expression facet" + this.fPattern);
        }
        float floatValue;
        try {
            floatValue = Float.valueOf(s);
        }
        catch (NumberFormatException ex) {
            if (s.equals("INF")) {
                floatValue = Float.POSITIVE_INFINITY;
            }
            else if (s.equals("-INF")) {
                floatValue = Float.NEGATIVE_INFINITY;
            }
            else {
                if (!s.equals("NaN")) {
                    throw new InvalidDatatypeValueException(this.getErrorString(18, 0, new Object[] { s }));
                }
                floatValue = Float.NaN;
            }
        }
        if (vector != null) {
            final int size = vector.size();
            final float[] array = new float[size];
            int i = 0;
            try {
                while (i < size) {
                    array[i] = Float.valueOf(vector.elementAt(i));
                    ++i;
                }
            }
            catch (NumberFormatException ex2) {
                if (vector.elementAt(i).equals("INF")) {
                    array[i] = Float.POSITIVE_INFINITY;
                }
                else if (vector.elementAt(i).equals("-INF")) {
                    array[i] = Float.NEGATIVE_INFINITY;
                }
                else {
                    if (!vector.elementAt(i).equals("NaN")) {
                        throw new InvalidDatatypeValueException(this.getErrorString(8, 0, new Object[] { vector.elementAt(i) }));
                    }
                    array[i] = Float.NaN;
                }
            }
            this.enumCheck(floatValue, array);
        }
        this.boundsCheck(floatValue);
        if ((this.fFacetsDefined & 0x10) != 0x0) {
            this.enumCheck(floatValue, this.fEnumFloats);
        }
    }
    
    private void boundsCheck(final float n) throws InvalidDatatypeValueException {
        String s = (this.fMaxExclusive != Float.MAX_VALUE) ? Float.toString(this.fMaxExclusive) : ((this.fMaxInclusive != Float.MAX_VALUE) ? Float.toString(this.fMaxInclusive) : "");
        String s2 = (this.fMinExclusive != Float.MIN_VALUE) ? Float.toString(this.fMinExclusive) : ((this.fMinInclusive != Float.MIN_VALUE) ? Float.toString(this.fMinInclusive) : "");
        String s3 = "";
        String s4 = "";
        boolean b;
        if (this.isMaxInclusiveDefined) {
            b = (n <= this.fMaxInclusive);
            s = Float.toString(this.fMaxInclusive);
            if (s != null) {
                s4 = "<=";
            }
            else {
                s = "";
            }
        }
        else if (this.isMaxExclusiveDefined) {
            b = (n < this.fMaxExclusive);
            s = Float.toString(this.fMaxExclusive);
            if (s != null) {
                s4 = "<";
            }
            else {
                s = "";
            }
        }
        else {
            b = (!this.isMaxInclusiveDefined && !this.isMaxExclusiveDefined);
        }
        boolean b2;
        if (this.isMinInclusiveDefined) {
            b2 = (n >= this.fMinInclusive);
            s2 = Float.toString(this.fMinInclusive);
            if (s2 != null) {
                s3 = "<=";
            }
            else {
                s2 = "";
            }
        }
        else if (this.isMinExclusiveDefined) {
            b2 = (n > this.fMinExclusive);
            s2 = Float.toString(this.fMinExclusive);
            if (s2 != null) {
                s3 = "<";
            }
            else {
                s2 = "";
            }
        }
        else {
            b2 = (!this.isMinInclusiveDefined && !this.isMinExclusiveDefined);
        }
        if (!b2 || !b) {
            throw new InvalidDatatypeValueException(this.getErrorString(9, 0, new Object[] { Float.toString(n), s2, s, s3, s4 }));
        }
    }
    
    public void setLocale(final Locale fLocale) {
        this.fLocale = fLocale;
    }
    
    public int compare(final String s, final String s2) {
        try {
            return Float.valueOf(s).compareTo(Float.valueOf(s2));
        }
        catch (NumberFormatException ex) {
            return -1;
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("clone() is not supported in " + this.getClass().getName());
    }
    
    private void enumCheck(final float n, final float[] array) throws InvalidDatatypeValueException {
        for (int i = 0; i < array.length; ++i) {
            if (n == array[i]) {
                return;
            }
        }
        throw new InvalidDatatypeValueException(this.getErrorString(10, 0, new Object[] { new Float(n) }));
    }
    
    private String getErrorString(final int n, final int n2, final Object[] array) {
        try {
            return this.fMessageProvider.createMessage(this.fLocale, n, n2, array);
        }
        catch (Exception ex) {
            return "Illegal Errorcode " + n2;
        }
    }
    
    private void setBasetype(final DatatypeValidator fBaseValidator) {
        this.fBaseValidator = fBaseValidator;
    }
}
