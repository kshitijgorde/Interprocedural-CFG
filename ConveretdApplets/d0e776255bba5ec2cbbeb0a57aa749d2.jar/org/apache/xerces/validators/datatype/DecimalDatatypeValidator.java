// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;
import org.apache.xerces.utils.regex.RegularExpression;
import java.math.BigDecimal;
import java.util.Locale;

public class DecimalDatatypeValidator extends AbstractDatatypeValidator
{
    private Locale fLocale;
    private DatatypeValidator fBaseValidator;
    private BigDecimal[] fEnumDecimal;
    private String fPattern;
    private BigDecimal fMaxInclusive;
    private BigDecimal fMaxExclusive;
    private BigDecimal fMinInclusive;
    private BigDecimal fMinExclusive;
    private int fFacetsDefined;
    private int fScale;
    private int fPrecision;
    private boolean isMaxExclusiveDefined;
    private boolean isMaxInclusiveDefined;
    private boolean isMinExclusiveDefined;
    private boolean isMinInclusiveDefined;
    private boolean isScaleDefined;
    private boolean isPrecisionDefined;
    private DatatypeMessageProvider fMessageProvider;
    private RegularExpression fRegex;
    private Hashtable fFacets;
    
    public DecimalDatatypeValidator() throws InvalidDatatypeFacetException {
        this(null, null, false);
    }
    
    public DecimalDatatypeValidator(final DatatypeValidator basetype, final Hashtable fFacets, final boolean b) throws InvalidDatatypeFacetException {
        this.fLocale = null;
        this.fBaseValidator = null;
        this.fEnumDecimal = null;
        this.fPattern = null;
        this.fMaxInclusive = null;
        this.fMaxExclusive = null;
        this.fMinInclusive = null;
        this.fMinExclusive = null;
        this.fFacetsDefined = 0;
        this.fScale = 0;
        this.fPrecision = 0;
        this.isMaxExclusiveDefined = false;
        this.isMaxInclusiveDefined = false;
        this.isMinExclusiveDefined = false;
        this.isMinInclusiveDefined = false;
        this.isScaleDefined = false;
        this.isPrecisionDefined = false;
        this.fMessageProvider = new DatatypeMessageProvider();
        this.fRegex = null;
        this.fFacets = null;
        this.setBasetype(basetype);
        if (fFacets != null) {
            this.fFacets = this.checkForFacetConsistency(fFacets, basetype.getFacets());
            this.fFacets = fFacets;
            Vector<String> vector = null;
            String fPattern = null;
            final Enumeration keys = fFacets.keys();
            while (keys.hasMoreElements()) {
                final String s = (String)keys.nextElement();
                try {
                    if (s.equals("pattern")) {
                        fPattern = (String)fFacets.get(s);
                        this.fFacetsDefined += 8;
                        this.fPattern = fPattern;
                        if (this.fPattern == null) {
                            continue;
                        }
                        this.fRegex = new RegularExpression(this.fPattern, "X");
                    }
                    else if (s.equals("enumeration")) {
                        this.fFacetsDefined += 16;
                        vector = fFacets.get(s);
                    }
                    else if (s.equals("maxInclusive")) {
                        fPattern = (String)fFacets.get(s);
                        this.fFacetsDefined += 32;
                        this.fMaxInclusive = new BigDecimal(stripPlusIfPresent(fPattern));
                    }
                    else if (s.equals("maxExclusive")) {
                        fPattern = (String)fFacets.get(s);
                        this.fFacetsDefined += 64;
                        this.fMaxExclusive = new BigDecimal(stripPlusIfPresent(fPattern));
                    }
                    else if (s.equals("minInclusive")) {
                        fPattern = (String)fFacets.get(s);
                        this.fFacetsDefined += 128;
                        this.fMinInclusive = new BigDecimal(stripPlusIfPresent(fPattern));
                    }
                    else if (s.equals("minExclusive")) {
                        fPattern = (String)fFacets.get(s);
                        this.fFacetsDefined += 256;
                        this.fMinExclusive = new BigDecimal(stripPlusIfPresent(fPattern));
                    }
                    else if (s.equals("precision")) {
                        fPattern = (String)fFacets.get(s);
                        this.fFacetsDefined += 512;
                        this.isPrecisionDefined = true;
                        this.fPrecision = Integer.parseInt(fPattern);
                    }
                    else {
                        if (!s.equals("scale")) {
                            throw new InvalidDatatypeFacetException(this.getErrorString(1, 0, null));
                        }
                        fPattern = (String)fFacets.get(s);
                        this.fFacetsDefined += 1024;
                        this.isScaleDefined = true;
                        this.fScale = Integer.parseInt(fPattern);
                    }
                }
                catch (Exception ex) {
                    throw new InvalidDatatypeFacetException(this.getErrorString(5, 0, new Object[] { fPattern, s }));
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
            if (this.isMaxExclusiveDefined && this.isMinExclusiveDefined && this.fMaxExclusive.compareTo(this.fMinExclusive) != 1) {
                throw new InvalidDatatypeFacetException("maxExclusive value ='" + this.fMaxExclusive + "'must be > than minExclusive value ='" + this.fMinExclusive + "'. ");
            }
            if (this.isMaxInclusiveDefined && this.isMinInclusiveDefined && this.fMaxInclusive.compareTo(this.fMinInclusive) == -1) {
                throw new InvalidDatatypeFacetException("maxInclusive value ='" + this.fMaxInclusive + "'must be >= than minInclusive value ='" + this.fMinInclusive + "'. ");
            }
            if (this.isMaxExclusiveDefined && this.isMinInclusiveDefined && this.fMaxExclusive.compareTo(this.fMinInclusive) != 1) {
                throw new InvalidDatatypeFacetException("maxExclusive value ='" + this.fMaxExclusive + "'must be > than minInclusive value ='" + this.fMinInclusive + "'. ");
            }
            if (this.isMaxInclusiveDefined && this.isMinExclusiveDefined && this.fMaxInclusive.compareTo(this.fMinExclusive) != 1) {
                throw new InvalidDatatypeFacetException("maxInclusive value ='" + this.fMaxInclusive + "'must be > than minExclusive value ='" + this.fMinExclusive + "'. ");
            }
            if ((this.fFacetsDefined & 0x10) != 0x0 && vector != null) {
                this.fEnumDecimal = new BigDecimal[vector.size()];
                int i = 0;
                try {
                    while (i < vector.size()) {
                        this.boundsCheck(this.fEnumDecimal[i] = new BigDecimal(stripPlusIfPresent(vector.elementAt(i))));
                        ++i;
                    }
                }
                catch (Exception ex2) {
                    throw new InvalidDatatypeFacetException(this.getErrorString(8, 0, new Object[] { vector.elementAt(i) }));
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
            ((DecimalDatatypeValidator)this.fBaseValidator).checkContentEnum(s, o, vector);
        }
        if ((this.fFacetsDefined & 0x8) != 0x0 && (this.fRegex == null || !this.fRegex.matches(s))) {
            throw new InvalidDatatypeValueException("Value'" + s + "' does not match regular expression facet " + this.fRegex.getPattern());
        }
        BigDecimal bigDecimal;
        try {
            bigDecimal = new BigDecimal(stripPlusIfPresent(s));
        }
        catch (Exception ex) {
            throw new InvalidDatatypeValueException(this.getErrorString(3, 0, new Object[] { "'" + s + "'" }));
        }
        if (vector != null) {
            final int size = vector.size();
            final BigDecimal[] array = new BigDecimal[size];
            int i = 0;
            try {
                while (i < size) {
                    array[i] = new BigDecimal(stripPlusIfPresent(vector.elementAt(i)));
                    ++i;
                }
            }
            catch (NumberFormatException ex2) {
                throw new InvalidDatatypeValueException(this.getErrorString(8, 0, new Object[] { vector.elementAt(i) }));
            }
            this.enumCheck(bigDecimal, array);
        }
        if (this.isScaleDefined && bigDecimal.scale() > this.fScale) {
            throw new InvalidDatatypeValueException(this.getErrorString(17, 0, new Object[] { s }));
        }
        if (this.isPrecisionDefined) {
            final int n = bigDecimal.movePointRight(bigDecimal.scale()).toString().length() - ((bigDecimal.signum() < 0) ? 1 : 0);
            if (n > this.fPrecision) {
                throw new InvalidDatatypeValueException(this.getErrorString(16, 0, new Object[] { "'" + s + "'" + "with precision = '" + n + "'", "'" + this.fPrecision + "'" }));
            }
        }
        this.boundsCheck(bigDecimal);
        if (this.fEnumDecimal != null) {
            this.enumCheck(bigDecimal, this.fEnumDecimal);
        }
    }
    
    public void boundsCheck(final BigDecimal bigDecimal) throws InvalidDatatypeValueException {
        String s = (this.fMaxExclusive != null) ? this.fMaxExclusive.toString() : ((this.fMaxInclusive != null) ? this.fMaxInclusive.toString() : "");
        String s2 = (this.fMinExclusive != null) ? this.fMinExclusive.toString() : ((this.fMinInclusive != null) ? this.fMinInclusive.toString() : "");
        String s3 = "";
        String s4 = "";
        boolean b;
        if (this.isMaxInclusiveDefined) {
            b = (bigDecimal.compareTo(this.fMaxInclusive) <= 0);
            s = this.fMaxInclusive.toString();
            if (s != null) {
                s4 = "<=";
            }
            else {
                s = "";
            }
        }
        else if (this.isMaxExclusiveDefined) {
            b = (bigDecimal.compareTo(this.fMaxExclusive) < 0);
            s = this.fMaxExclusive.toString();
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
            b2 = (bigDecimal.compareTo(this.fMinInclusive) >= 0);
            s2 = this.fMinInclusive.toString();
            if (s2 != null) {
                s3 = "<=";
            }
            else {
                s2 = "";
            }
        }
        else if (this.isMinExclusiveDefined) {
            b2 = (bigDecimal.compareTo(this.fMinExclusive) > 0);
            s2 = this.fMinExclusive.toString();
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
            throw new InvalidDatatypeValueException(this.getErrorString(9, 0, new Object[] { bigDecimal.toString(), s2, s, s3, s4 }));
        }
    }
    
    private void enumCheck(final BigDecimal bigDecimal, final BigDecimal[] array) throws InvalidDatatypeValueException {
        for (int i = 0; i < array.length; ++i) {
            if (bigDecimal.equals(array[i])) {
                return;
            }
        }
        throw new InvalidDatatypeValueException(this.getErrorString(10, 0, new Object[] { bigDecimal }));
    }
    
    public void setLocale(final Locale fLocale) {
        this.fLocale = fLocale;
    }
    
    private String getErrorString(final int n, final int n2, final Object[] array) {
        try {
            return this.fMessageProvider.createMessage(this.fLocale, n, n2, array);
        }
        catch (Exception ex) {
            return "Illegal Errorcode " + n2;
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("clone() is not supported in " + this.getClass().getName());
    }
    
    public int compare(final String s, final String s2) {
        try {
            return new BigDecimal(stripPlusIfPresent(s)).compareTo(new BigDecimal(stripPlusIfPresent(s2)));
        }
        catch (NumberFormatException ex) {
            return -1;
        }
    }
    
    private void setBasetype(final DatatypeValidator fBaseValidator) {
        this.fBaseValidator = fBaseValidator;
    }
    
    private static String stripPlusIfPresent(final String s) {
        String substring = s;
        if (s.length() >= 2 && s.charAt(0) == '+' && s.charAt(1) != '-') {
            substring = s.substring(1);
        }
        return substring;
    }
    
    private Hashtable checkForFacetConsistency(final Hashtable hashtable, final Hashtable hashtable2) throws InvalidDatatypeFacetException {
        if (hashtable2 != null) {
            final Enumeration<String> keys = (Enumeration<String>)hashtable2.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                final String s2 = hashtable2.get(s);
                final String s3 = hashtable.get(s);
                if (s3 == null) {
                    hashtable.put(s, s2);
                    if (s.equals("maxExclusive") && hashtable.containsKey("maxInclusive")) {
                        if (new BigDecimal(stripPlusIfPresent(hashtable.get("maxInclusive"))).compareTo(new BigDecimal(stripPlusIfPresent(s2))) == -1) {
                            hashtable.remove(s);
                        }
                        else {
                            hashtable.remove("maxInclusive");
                        }
                    }
                    else if (s.equals("maxInclusive") && hashtable.containsKey("maxExclusive")) {
                        if (new BigDecimal(stripPlusIfPresent(hashtable.get("maxExclusive"))).compareTo(new BigDecimal(stripPlusIfPresent(s2))) == -1) {
                            hashtable.remove(s);
                        }
                        else {
                            hashtable.remove("maxExclusive");
                        }
                    }
                    else if (s.equals("minExclusive") && hashtable.containsKey("minInclusive")) {
                        if (new BigDecimal(stripPlusIfPresent(hashtable.get("minInclusive"))).compareTo(new BigDecimal(stripPlusIfPresent(s2))) == 1) {
                            hashtable.remove(s);
                        }
                        else {
                            hashtable.remove("minInclusive");
                        }
                    }
                    else {
                        if (!s.equals("minInclusive") || !hashtable.containsKey("minExclusive")) {
                            continue;
                        }
                        if (new BigDecimal(stripPlusIfPresent(hashtable.get("minExclusive"))).compareTo(new BigDecimal(stripPlusIfPresent(s2))) == 1) {
                            hashtable.remove(s);
                        }
                        else {
                            hashtable.remove("minExclusive");
                        }
                    }
                }
                else if (s.equals("maxExclusive")) {
                    if (new BigDecimal(stripPlusIfPresent(s3)).compareTo(new BigDecimal(stripPlusIfPresent(s2))) == -1) {
                        continue;
                    }
                    continue;
                }
                else if (s.equals("maxInclusive")) {
                    if (new BigDecimal(stripPlusIfPresent(s3)).compareTo(new BigDecimal(stripPlusIfPresent(s2))) == -1) {
                        continue;
                    }
                    continue;
                }
                else if (s.equals("minExclusive")) {
                    if (new BigDecimal(stripPlusIfPresent(s3)).compareTo(new BigDecimal(stripPlusIfPresent(s2))) == -1) {
                        continue;
                    }
                    continue;
                }
                else if (!s.equals("minInclusive") || new BigDecimal(stripPlusIfPresent(s3)).compareTo(new BigDecimal(stripPlusIfPresent(s2))) == -1) {}
            }
        }
        return hashtable;
    }
}
