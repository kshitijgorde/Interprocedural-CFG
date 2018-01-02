// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.util;

import java.util.StringTokenizer;
import java.util.Calendar;
import java.util.BitSet;
import java.math.BigDecimal;

public class Util
{
    public static String[] concat(final String[] array1, final String[] array2) {
        final String[] result = new String[array1.length + array2.length];
        System.arraycopy(array1, 0, result, 0, array1.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }
    
    public static String formatBigDecimal(BigDecimal value, final char thousandSep, final char decimalPoint, final int numDecimals) {
        if (numDecimals >= 0 && value.scale() != numDecimals) {
            value = value.setScale(numDecimals, 4);
        }
        final String stringValue = value.toString();
        int origLen = stringValue.length();
        int decPoint = stringValue.indexOf(46);
        boolean hasDecPoint;
        if (decPoint < 0) {
            decPoint = origLen;
            hasDecPoint = false;
        }
        else {
            hasDecPoint = true;
            if (numDecimals == -1) {
                while (stringValue.charAt(origLen - 1) == '0') {
                    --origLen;
                }
                if (stringValue.charAt(origLen - 1) == '.') {
                    --origLen;
                    hasDecPoint = false;
                }
            }
        }
        boolean isNegative;
        int thousandSepCount;
        int mag;
        if (stringValue.charAt(0) == '-') {
            isNegative = true;
            thousandSepCount = (decPoint - 2) / 3;
            mag = decPoint - 2;
        }
        else {
            isNegative = false;
            thousandSepCount = (decPoint - 1) / 3;
            mag = decPoint - 1;
        }
        final StringBuffer buf = new StringBuffer(origLen + thousandSepCount);
        int srcIndex = 0;
        if (isNegative) {
            ++srcIndex;
            buf.append('-');
        }
        while (srcIndex < decPoint) {
            buf.append(stringValue.charAt(srcIndex));
            ++srcIndex;
            if (mag > 0 && mag % 3 == 0) {
                buf.append(thousandSep);
            }
            --mag;
        }
        if (hasDecPoint) {
            ++srcIndex;
            buf.append(decimalPoint);
            while (srcIndex < origLen) {
                buf.append(stringValue.charAt(srcIndex));
                ++srcIndex;
            }
        }
        return buf.toString();
    }
    
    public static int hashString(final String s) {
        int hash = 1;
        for (int i = 0; i < s.length(); ++i) {
            hash *= '!' + s.charAt(i);
        }
        return hash;
    }
    
    public static BitSet insertBits(final BitSet fromSet, final int insertBefore, final int count) {
        final BitSet newSet = new BitSet(fromSet.size() + count);
        for (int i = 0; i < insertBefore; ++i) {
            if (fromSet.get(i)) {
                newSet.set(i);
            }
        }
        for (int i = insertBefore; i < fromSet.size(); ++i) {
            if (fromSet.get(i)) {
                newSet.set(i + count);
            }
        }
        return newSet;
    }
    
    public static Calendar parseGregorianDate(final String s) {
        final int[] daysInMonth = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        char dateDelimiter = '?';
        int j;
        int m;
        int t;
        try {
            for (int i = 0; i < s.length(); ++i) {
                if (!Character.isDigit(s.charAt(i))) {
                    dateDelimiter = s.charAt(i);
                    break;
                }
            }
            if (dateDelimiter != '.' && dateDelimiter != '/') {
                return null;
            }
            final StringTokenizer toks = new StringTokenizer(s, new StringBuffer().append(dateDelimiter).toString());
            if (toks.countTokens() != 3) {
                return null;
            }
            String tag;
            String monat;
            if (dateDelimiter == '.') {
                tag = toks.nextToken();
                monat = toks.nextToken();
            }
            else {
                monat = toks.nextToken();
                tag = toks.nextToken();
            }
            final String jahr = toks.nextToken();
            if (jahr.length() != 2 && jahr.length() != 4) {
                return null;
            }
            j = Integer.parseInt(jahr);
            m = Integer.parseInt(monat);
            t = Integer.parseInt(tag);
            if (j < 99 && jahr.length() == 2) {
                j += 2000;
            }
            else if (j == 99 && jahr.length() == 2) {
                j += 1900;
            }
            if (j < 1) {
                return null;
            }
            if (m < 1 || m > 12) {
                return null;
            }
            if (t < 1 || t > daysInMonth[m - 1]) {
                return null;
            }
            if (m == 2 && t == 29) {
                final boolean leapYear = j % 400 == 0 || (j % 100 != 0 && j % 4 == 0);
                if (!leapYear) {
                    return null;
                }
            }
        }
        catch (Exception e) {
            return null;
        }
        final Calendar c = Calendar.getInstance();
        c.set(j, m - 1, t);
        return c;
    }
    
    public static BitSet removeBits(final BitSet fromSet, final int startIndex, final int pastIndex) {
        final int skipCount = pastIndex - startIndex;
        final int iSize = fromSet.size() - skipCount;
        if (iSize >= 0) {
            final BitSet newSet = new BitSet(iSize);
            for (int i = 0; i < startIndex; ++i) {
                if (fromSet.get(i)) {
                    newSet.set(i);
                }
            }
            for (int i = pastIndex; i < fromSet.size(); ++i) {
                if (fromSet.get(i)) {
                    newSet.set(i - skipCount);
                }
            }
            return newSet;
        }
        return new BitSet(0);
    }
    
    public static String padNumeric(final String src, final int numericLength) {
        final int origLen = countNumeric(src);
        if (origLen >= numericLength || origLen == 0) {
            return src;
        }
        final int diff = numericLength - origLen;
        final StringBuffer sb = new StringBuffer();
        for (int k = 0; k < diff; ++k) {
            sb.append("0");
        }
        sb.append(src);
        return sb.toString();
    }
    
    private static int countNumeric(final String src) {
        int sum = 0;
        for (int k = 0; k < src.length(); ++k) {
            if (!Character.isDigit(src.charAt(k))) {
                return sum;
            }
            ++sum;
        }
        return sum;
    }
}
