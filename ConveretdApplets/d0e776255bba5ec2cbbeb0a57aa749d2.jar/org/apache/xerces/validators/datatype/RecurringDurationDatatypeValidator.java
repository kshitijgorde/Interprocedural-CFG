// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.Calendar;
import org.apache.xerces.utils.regex.RegularExpression;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Locale;

public class RecurringDurationDatatypeValidator extends AbstractDatatypeValidator
{
    private static final boolean fDbug = false;
    private Locale fLocale;
    DatatypeValidator fBaseValidator;
    String fPattern;
    long fMaxInclusive;
    long fMaxExclusive;
    long fMinInclusive;
    long fMinExclusive;
    long fDuration;
    long fPeriod;
    long[] fEnumrecurringduration;
    boolean isMaxExclusiveDefined;
    boolean isMaxInclusiveDefined;
    boolean isMinExclusiveDefined;
    boolean isMinInclusiveDefined;
    boolean isBaseTypeTimePeriod;
    int fFacetsDefined;
    Hashtable fFacets;
    private DatatypeMessageProvider fMessageProvider;
    
    public RecurringDurationDatatypeValidator() throws InvalidDatatypeFacetException {
        this(null, null, false);
    }
    
    public RecurringDurationDatatypeValidator(final DatatypeValidator basetype, final Hashtable fFacets, final boolean b) throws InvalidDatatypeFacetException {
        this.fLocale = null;
        this.fBaseValidator = null;
        this.fPattern = null;
        this.fMaxInclusive = Long.MAX_VALUE;
        this.fMaxExclusive = 9223372036854775806L;
        this.fMinInclusive = 1L;
        this.fMinExclusive = 0L;
        this.fDuration = 0L;
        this.fPeriod = 0L;
        this.fEnumrecurringduration = null;
        this.isMaxExclusiveDefined = false;
        this.isMaxInclusiveDefined = false;
        this.isMinExclusiveDefined = false;
        this.isMinInclusiveDefined = false;
        this.isBaseTypeTimePeriod = false;
        this.fFacetsDefined = 0;
        this.fFacets = null;
        this.fMessageProvider = new DatatypeMessageProvider();
        if (basetype != null) {
            this.setBasetype(basetype);
            this.fFacets = fFacets;
        }
        if (fFacets != null) {
            final Enumeration keys = fFacets.keys();
            while (keys.hasMoreElements()) {
                final String s = (String)keys.nextElement();
                if (s.equals("pattern")) {
                    this.fFacetsDefined += 8;
                    this.fPattern = (String)fFacets.get(s);
                }
                else {
                    if (!s.equals("enumeration")) {
                        if (s.equals("maxInclusive")) {
                            this.fFacetsDefined += 32;
                            String s2 = null;
                            try {
                                s2 = (String)fFacets.get(s);
                                this.fMaxInclusive = normalizeRecurringDuration(s2.toCharArray(), 0);
                                continue;
                            }
                            catch (InvalidDatatypeValueException ex) {
                                throw new InvalidDatatypeFacetException(this.getErrorString(5, 0, new Object[] { s2, s }));
                            }
                        }
                        if (s.equals("maxExclusive")) {
                            this.fFacetsDefined += 64;
                            String s3 = null;
                            try {
                                s3 = (String)fFacets.get(s);
                                this.fMaxExclusive = normalizeRecurringDuration(s3.toCharArray(), 0);
                                continue;
                            }
                            catch (InvalidDatatypeValueException ex2) {
                                throw new InvalidDatatypeFacetException(this.getErrorString(5, 0, new Object[] { s3, s }));
                            }
                        }
                        if (s.equals("minInclusive")) {
                            this.fFacetsDefined += 128;
                            String s4 = null;
                            try {
                                s4 = (String)fFacets.get(s);
                                this.fMinInclusive = normalizeRecurringDuration(s4.toCharArray(), 0);
                                continue;
                            }
                            catch (InvalidDatatypeValueException ex3) {
                                throw new InvalidDatatypeFacetException(this.getErrorString(5, 0, new Object[] { s4, s }));
                            }
                        }
                        if (s.equals("minExclusive")) {
                            this.fFacetsDefined += 128;
                            String s5 = null;
                            try {
                                s5 = (String)fFacets.get(s);
                                this.fMinExclusive = normalizeRecurringDuration(s5.toCharArray(), 0);
                                continue;
                            }
                            catch (InvalidDatatypeValueException ex4) {
                                throw new InvalidDatatypeFacetException(this.getErrorString(5, 0, new Object[] { s5, s }));
                            }
                        }
                        if (s.equals("period")) {
                            this.fFacetsDefined += 8192;
                            String s6 = null;
                            try {
                                s6 = (String)fFacets.get(s);
                                this.fPeriod = normalizeRecurringDuration(s6.toCharArray(), 0);
                                continue;
                            }
                            catch (InvalidDatatypeValueException ex5) {
                                throw new InvalidDatatypeFacetException(this.getErrorString(5, 0, new Object[] { s6, s }));
                            }
                        }
                        if (s.equals("duration")) {
                            this.fFacetsDefined += 4096;
                            String s7 = null;
                            try {
                                s7 = (String)fFacets.get(s);
                                this.fDuration = normalizeRecurringDuration(s7.toCharArray(), 0);
                                continue;
                            }
                            catch (InvalidDatatypeValueException ex6) {
                                throw new InvalidDatatypeFacetException(this.getErrorString(5, 0, new Object[] { s7, s }));
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
            if ((this.fFacetsDefined & 0x10) != 0x0) {
                final Vector<String> vector = fFacets.get("enumeration");
                if (vector != null) {
                    this.fEnumrecurringduration = new long[vector.size()];
                    int i = 0;
                    try {
                        while (i < vector.size()) {
                            this.boundsCheck(this.fEnumrecurringduration[i] = normalizeRecurringDuration(vector.elementAt(i).toCharArray(), 0));
                            ++i;
                        }
                    }
                    catch (InvalidDatatypeValueException ex7) {
                        throw new InvalidDatatypeFacetException(this.getErrorString(8, 0, new Object[] { vector.elementAt(i) }));
                    }
                }
            }
            if (this.fBaseValidator != null) {
                String s8 = null;
                try {
                    final Hashtable facets = this.fBaseValidator.getFacets();
                    if (facets != null) {
                        s8 = facets.get("period");
                        if (s8 != null) {
                            this.fPeriod = normalizeRecurringDuration(s8.toCharArray(), 0);
                            if (this.fPeriod == 0L) {
                                this.isBaseTypeTimePeriod = true;
                            }
                        }
                    }
                }
                catch (InvalidDatatypeValueException ex8) {
                    throw new InvalidDatatypeFacetException(this.getErrorString(5, 0, new Object[] { s8, "period" }));
                }
            }
        }
    }
    
    public Object validate(final String s, final Object o) throws InvalidDatatypeValueException {
        if (this.fPattern != null && !new RegularExpression(this.fPattern, "X").matches(s)) {
            throw new InvalidDatatypeValueException("Value'" + s + "does not match regular expression facet" + this.fPattern);
        }
        return null;
    }
    
    public void setBasetype(final DatatypeValidator fBaseValidator) {
        this.fBaseValidator = fBaseValidator;
    }
    
    public void setLocale(final Locale locale) {
    }
    
    public int compare(final String s, final String s2) {
        return -1;
    }
    
    public Hashtable getFacets() {
        return this.fFacets;
    }
    
    private static long normalizeRecurringDuration(final char[] array, final int n) throws InvalidDatatypeValueException {
        return 0L;
    }
    
    public static Calendar normalizeInstant(final char[] array, final int n, final int n2) throws InvalidDatatypeValueException {
        int int1 = 0;
        int n3 = n;
        final char[] array2 = { '0', '0', '0' };
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        final int n4 = n + n2 - 1;
        try {
            if (n2 < 16) {
                throw new ParseException("Value is too short.", 0);
            }
            gregorianCalendar.clear();
            gregorianCalendar.setLenient(false);
            if (array[n3] == '-' || array[n3] == '+') {
                gregorianCalendar.set(0, (array[n3] != '-') ? 1 : 0);
                ++n3;
            }
            final int index = indexOf(array, n3, '-', n3 + 5);
            if (index == -1 || index > n4) {
                throw new ParseException("Year separator is missing or misplaced.", n3);
            }
            gregorianCalendar.set(1, parseInt(array, n3, index - n3));
            int n5 = index + 1;
            gregorianCalendar.set(2, parseInt(array, n5, 2) - 1);
            n5 += 2;
            if (array[n5] != '-') {
                throw new ParseException("Month separator is missing or misplaced.", n5);
            }
            gregorianCalendar.set(5, parseInt(array, n5 + 1, 2));
            n5 += 3;
            if (array[n5] != 'T') {
                throw new ParseException("Time separator is missing or misplaced.", n5);
            }
            gregorianCalendar.set(11, parseInt(array, n5 + 1, 2));
            n5 += 3;
            if (array[n5] != ':') {
                throw new ParseException("Hour separator is missing or misplaced.", n5);
            }
            gregorianCalendar.set(12, parseInt(array, n5 + 1, 2));
            n5 += 3;
            if (n4 - n5 > 1 && array[n5] == ':') {
                gregorianCalendar.set(13, parseInt(array, n5 + 1, 2));
                n5 += 3;
                if (n5 < n4 && array[n5] == '.') {
                    ++n5;
                    for (int n6 = 0; n5 <= n4 && n6 < 3 && Character.isDigit(array[n5]); array2[n6++] = array[n5++]) {}
                    gregorianCalendar.set(14, parseInt(array2, 0, 3));
                }
                while (n5 <= n4 && Character.isDigit(array[n5])) {
                    ++n5;
                }
            }
            if (n5 <= n4) {
                if (array[n5] == 'Z') {
                    gregorianCalendar.set(15, 0);
                }
                else {
                    if (array[n5] != '-' && array[n5] != '+') {
                        throw new ParseException("Unrecognized time zone.", n5);
                    }
                    final boolean b = array[n5] == '-';
                    final int int2 = parseInt(array, n5 + 1, 2);
                    if (n4 - n5 == 5) {
                        if (array[n5 + 3] != ':') {
                            throw new ParseException("time zone must be 'hh:mm'.", n5);
                        }
                        int1 = parseInt(array, n5 + 4, 2);
                    }
                    final int n7 = int2 * 3600000 + int1 * 60000;
                    gregorianCalendar.set(15, b ? (-n7) : n7);
                }
            }
            return gregorianCalendar;
        }
        catch (Exception ex) {
            throw new InvalidDatatypeValueException("Unable to parse timeInstant " + ex.toString());
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("clone() is not supported in " + this.getClass().getName());
    }
    
    private void boundsCheck(final long n) throws InvalidDatatypeFacetException {
        boolean b = false;
        boolean b2 = false;
        if (this.isMaxInclusiveDefined) {
            b = (n <= this.fMaxInclusive);
        }
        else if (this.isMaxExclusiveDefined) {
            b = (n < this.fMaxExclusive);
        }
        if (this.isMinInclusiveDefined) {
            b2 = (n >= this.fMinInclusive);
        }
        else if (this.isMinExclusiveDefined) {
            b2 = (n > this.fMinExclusive);
        }
        if (!b || !b2) {
            throw new InvalidDatatypeFacetException(this.getErrorString(9, 0, new Object[] { new Long(n), "", "", "", "" }));
        }
    }
    
    private void enumCheck(final long n) throws InvalidDatatypeValueException {
        for (int i = 0; i < this.fEnumrecurringduration.length; ++i) {
            if (n == this.fEnumrecurringduration[i]) {
                return;
            }
        }
        throw new InvalidDatatypeValueException(this.getErrorString(10, 0, new Object[] { new Long(n) }));
    }
    
    private String getErrorString(final int n, final int n2, final Object[] array) {
        try {
            return this.fMessageProvider.createMessage(this.fLocale, n, n2, array);
        }
        catch (Exception ex) {
            return "Illegal Errorcode " + n2;
        }
    }
    
    private static final int indexOf(final char[] array, final int n, final char c) {
        return indexOf(array, n, c, array.length - 1);
    }
    
    private static final int indexOf(final char[] array, final int n, final char c, final int n2) {
        for (int i = n; i <= n2; ++i) {
            if (array[i] == c) {
                return i;
            }
        }
        return -1;
    }
    
    private static final int indexOneOf(final char[] array, final int n, final String s) {
        return indexOneOf(array, n, s, array.length - 1);
    }
    
    private static final int indexOneOf(final char[] array, final int n, final String s, final int n2) {
        for (int i = n; i < n2; ++i) {
            for (int j = 0; j < s.length(); ++j) {
                if (array[i] == s.charAt(j)) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    private static final int parseInt(final char[] array, final int n, final int n2) throws NumberFormatException {
        if (array == null) {
            throw new NumberFormatException("null");
        }
        final int n3 = 10;
        int n4 = 0;
        boolean b = false;
        int i = n;
        if (n2 <= 0) {
            throw new NumberFormatException(new String(array, n, n2));
        }
        int n5;
        if (array[i] == '-') {
            b = true;
            n5 = Integer.MIN_VALUE;
            ++i;
        }
        else if (array[i] == '+') {
            b = false;
            n5 = -2147483647;
            ++i;
        }
        else {
            n5 = -2147483647;
        }
        final int n6 = n5 / n3;
        if (i < n + n2) {
            final int digit = Character.digit(array[i++], n3);
            if (digit < 0) {
                throw new NumberFormatException(new String(array, n, n2));
            }
            n4 = -digit;
        }
        while (i < n + n2) {
            final int digit2 = Character.digit(array[i++], n3);
            if (digit2 < 0) {
                throw new NumberFormatException(new String(array, n, n2));
            }
            if (n4 < n6) {
                throw new NumberFormatException(new String(array, n, n2));
            }
            final int n7 = n4 * n3;
            if (n7 < n5 + digit2) {
                throw new NumberFormatException(new String(array, n, n2));
            }
            n4 = n7 - digit2;
        }
        if (!b) {
            return -n4;
        }
        if (i > 1) {
            return n4;
        }
        throw new NumberFormatException(new String(array, n, n2));
    }
}
