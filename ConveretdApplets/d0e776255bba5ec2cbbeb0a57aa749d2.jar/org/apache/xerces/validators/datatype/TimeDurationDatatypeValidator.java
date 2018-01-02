// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import java.util.Calendar;
import java.text.ParseException;
import java.util.GregorianCalendar;
import org.apache.xerces.utils.regex.RegularExpression;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Locale;

public class TimeDurationDatatypeValidator extends AbstractDatatypeValidator
{
    private Locale fLocale;
    private DatatypeValidator fBaseValidator;
    private String fPattern;
    private long fMaxInclusive;
    private long fMaxExclusive;
    private long fMinInclusive;
    private long fMinExclusive;
    private long fDuration;
    private long fPeriod;
    private boolean isMaxExclusiveDefined;
    private boolean isMaxInclusiveDefined;
    private boolean isMinExclusiveDefined;
    private boolean isMinInclusiveDefined;
    private int fFacetsDefined;
    private long[] fEnumTimeDuration;
    private DatatypeMessageProvider fMessageProvider;
    
    public TimeDurationDatatypeValidator() throws InvalidDatatypeFacetException {
        this(null, null, false);
    }
    
    public TimeDurationDatatypeValidator(final DatatypeValidator basetype, final Hashtable hashtable, final boolean b) throws InvalidDatatypeFacetException {
        this.fLocale = null;
        this.fBaseValidator = null;
        this.fPattern = null;
        this.fMaxInclusive = 0L;
        this.fMaxExclusive = 0L;
        this.fMinInclusive = 0L;
        this.fMinExclusive = 0L;
        this.fDuration = 0L;
        this.fPeriod = 0L;
        this.isMaxExclusiveDefined = false;
        this.isMaxInclusiveDefined = false;
        this.isMinExclusiveDefined = false;
        this.isMinInclusiveDefined = false;
        this.fFacetsDefined = 0;
        this.fEnumTimeDuration = null;
        this.fMessageProvider = new DatatypeMessageProvider();
        if (basetype != null) {
            this.setBasetype(basetype);
        }
        if (hashtable != null) {
            if (this.fBaseValidator != null) {
                final Enumeration keys = hashtable.keys();
                while (keys.hasMoreElements()) {
                    final String s = (String)keys.nextElement();
                    if (s.equals("pattern")) {
                        this.fFacetsDefined += 8;
                        this.fPattern = (String)hashtable.get(s);
                    }
                    else {
                        if (!s.equals("enumeration")) {
                            if (s.equals("maxInclusive")) {
                                this.fFacetsDefined += 32;
                                String s2 = null;
                                try {
                                    s2 = (String)hashtable.get(s);
                                    this.fMaxInclusive = normalizeDuration(s2.toCharArray(), 0);
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
                                    s3 = (String)hashtable.get(s);
                                    this.fMaxExclusive = normalizeDuration(s3.toCharArray(), 0);
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
                                    s4 = (String)hashtable.get(s);
                                    this.fMinInclusive = normalizeDuration(s4.toCharArray(), 0);
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
                                    s5 = (String)hashtable.get(s);
                                    this.fMinExclusive = normalizeDuration(s5.toCharArray(), 0);
                                    continue;
                                }
                                catch (InvalidDatatypeValueException ex4) {
                                    throw new InvalidDatatypeFacetException(this.getErrorString(5, 0, new Object[] { s5, s }));
                                }
                            }
                            throw new InvalidDatatypeFacetException(this.getErrorString(1, 0, null));
                        }
                        this.fFacetsDefined += 16;
                    }
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
                final Vector<String> vector = hashtable.get("enumeration");
                if (vector != null) {
                    this.fEnumTimeDuration = new long[vector.size()];
                    int i = 0;
                    try {
                        while (i < vector.size()) {
                            this.boundsCheck(this.fEnumTimeDuration[i] = normalizeDuration(vector.elementAt(i).toCharArray(), 0));
                            ++i;
                        }
                    }
                    catch (InvalidDatatypeValueException ex5) {
                        throw new InvalidDatatypeFacetException(this.getErrorString(8, 0, new Object[] { vector.elementAt(i) }));
                    }
                }
            }
        }
    }
    
    public Object validate(final String s, final Object o) throws InvalidDatatypeValueException {
        if (this.fPattern != null && !new RegularExpression(this.fPattern, "X").matches(s)) {
            throw new InvalidDatatypeValueException("Value'" + s + "does not match regular expression facet" + this.fPattern);
        }
        final long normalizeDuration = normalizeDuration(s.toCharArray(), 0);
        try {
            this.boundsCheck(normalizeDuration);
        }
        catch (InvalidDatatypeFacetException ex) {
            throw new InvalidDatatypeValueException("Boundary Exception");
        }
        if (this.fEnumTimeDuration != null) {
            this.enumCheck(normalizeDuration);
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
        return null;
    }
    
    public static long normalizeDuration(final char[] array, final int n) throws InvalidDatatypeValueException {
        int index = 0;
        int n2 = n;
        int n4;
        final int n3 = n4 = n + array.length - 1;
        final char[] array2 = { 'Y', 'M', 'D' };
        final char[] array3 = { 'H', 'M', 'S' };
        final char[] array4 = { '0', '0', '0' };
        final int[] array5 = new int[17];
        for (int i = 0; i < array5.length; ++i) {
            array5[i] = 0;
        }
        boolean b = false;
        int n5 = 0;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        boolean b5 = false;
        GregorianCalendar gregorianCalendar = null;
        GregorianCalendar gregorianCalendar2 = null;
        try {
            if (array[n2] == '-') {
                b2 = true;
            }
            int index2 = indexOf(array, n, '/');
            if (index2 > -1 && index2 < n3) {
                if (array[index2 + 1] == '-') {
                    b3 = true;
                }
                if (array[b2 ? (n2 + 1) : n2] == 'P') {
                    if (b2) {
                        ++n2;
                    }
                    b4 = true;
                    n4 = index2 - 1;
                }
                else {
                    gregorianCalendar = (GregorianCalendar)normalizeInstant(array, n2, index2 - n2);
                }
                if (array[b3 ? (index2 + 2) : (index2 + 1)] == 'P') {
                    b5 = true;
                    n2 = (b3 ? (index2 + 2) : (index2 + 1));
                }
                else {
                    ++index2;
                    gregorianCalendar2 = (GregorianCalendar)normalizeInstant(array, index2, n3 - index2 + 1);
                }
            }
            else {
                n2 = (b2 ? (n + 1) : n);
            }
            if (gregorianCalendar != null && gregorianCalendar2 != null) {
                return gregorianCalendar2.getTime().getTime() - gregorianCalendar.getTime().getTime();
            }
            if (b4 && b5) {
                throw new ParseException("Period cannot be expressed as 2 durations.", 0);
            }
            if (b4 && array[n2] != 'P') {
                throw new ParseException("Invalid start character for timeDuration:" + array[n2], n2);
            }
            if (b5 && array[n2] != 'P') {
                throw new ParseException("Invalid start character for timeDuration:" + array[n2], n2);
            }
        }
        catch (Exception ex) {
            throw new InvalidDatatypeValueException(ex.toString());
        }
        try {
            int n6 = n2 + 1;
            for (int j = n2 + 1; j <= n4; ++j) {
                if (Character.isDigit(array[j]) || array[j] == '.') {
                    if (array[j] == '.') {
                        n5 = 1;
                    }
                }
                else if (array[j] == 'T') {
                    b = true;
                    index = 0;
                    n6 = j + 1;
                }
                else {
                    index = indexOf(b ? array3 : array2, index, array[j]);
                    if (index == -1) {
                        throw new ParseException("Illegal or misplaced separator.", j);
                    }
                    ++index;
                    if (n5 != 0 && array[j] != 'S') {
                        throw new ParseException("Fractional digits allowed only for 'seconds'.", j);
                    }
                    switch (array[j]) {
                        case 'Y': {
                            if (b) {
                                throw new ParseException("Year must be specified before 'T' separator.", j);
                            }
                            array5[1] = parseInt(array, n6, j - n6);
                            break;
                        }
                        case 'D': {
                            if (b) {
                                throw new ParseException("Days must be specified before 'T' separator.", j);
                            }
                            array5[5] = parseInt(array, n6, j - n6);
                            break;
                        }
                        case 'H': {
                            if (!b) {
                                throw new ParseException("Hours must be specified after 'T' separator.", j);
                            }
                            array5[11] = parseInt(array, n6, j - n6);
                            break;
                        }
                        case 'M': {
                            array5[b ? 12 : 2] = parseInt(array, n6, j - n6);
                            break;
                        }
                        case 'S': {
                            if (!b) {
                                throw new ParseException("Seconds must be specified after 'T' separator.", j);
                            }
                            if (n5 == 0) {
                                array5[13] = parseInt(array, n6, j - n6);
                                break;
                            }
                            int index3 = indexOf(array, n6, '.');
                            array5[13] = parseInt(array, n6, index3 - n6);
                            ++index3;
                            for (int n7 = 0; index3 <= n4 && n7 < 3 && Character.isDigit(array[index3]); array4[n7++] = array[index3++]) {}
                            array5[14] = parseInt(array4, 0, 3);
                            n5 = 0;
                            break;
                        }
                        default: {
                            throw new ParseException("Illegal 'picture' character: " + array[j], j);
                        }
                    }
                    n6 = j + 1;
                }
            }
        }
        catch (Exception ex2) {
            throw new InvalidDatatypeValueException(ex2.toString());
        }
        try {
            if (gregorianCalendar != null) {
                final long time = gregorianCalendar.getTime().getTime();
                for (int k = 0; k < array5.length; ++k) {
                    if (array5[k] != 0) {
                        gregorianCalendar.add(k, b3 ? (-array5[k]) : array5[k]);
                    }
                }
                return gregorianCalendar.getTime().getTime() - time;
            }
            if (gregorianCalendar2 != null) {
                final long time2 = gregorianCalendar2.getTime().getTime();
                for (int l = 0; l < array5.length; ++l) {
                    if (array5[l] > 0) {
                        gregorianCalendar2.add(l, b2 ? array5[l] : (-array5[l]));
                    }
                }
                return gregorianCalendar2.getTime().getTime() - time2;
            }
            final long n8 = (array5[1] * 31104000L + array5[2] * 2592000L + array5[5] * 86400L + array5[11] * 3600L + array5[12] * 60L + array5[13]) * 1000L + array5[14];
            return b2 ? (-n8) : n8;
        }
        catch (Exception ex3) {
            throw new InvalidDatatypeValueException(ex3.toString());
        }
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
            ex.printStackTrace();
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
        for (int i = 0; i < this.fEnumTimeDuration.length; ++i) {
            if (n == this.fEnumTimeDuration[i]) {
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
