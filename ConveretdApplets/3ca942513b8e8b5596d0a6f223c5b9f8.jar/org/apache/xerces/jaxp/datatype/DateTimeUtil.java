// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp.datatype;

import java.math.BigInteger;
import java.util.Locale;
import org.apache.xerces.util.DatatypeMessageFormatter;

class DateTimeUtil
{
    static final int indexOf(final String s, final int n, final int n2, final char c) {
        for (int i = n; i < n2; ++i) {
            if (s.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }
    
    static final int parseInt(final String s, int n, final int n2) throws IllegalArgumentException, UnsupportedOperationException {
        final int n3 = 10;
        int n4 = 0;
        final int n5 = -2147483647;
        final int n6 = n5 / n3;
        do {
            final int digit = getDigit(s.charAt(n));
            if (digit < 0) {
                throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "WrongFormat", new Object[] { s }));
            }
            if (n4 < n6) {
                throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "OverflowProblem", new Object[] { s }));
            }
            final int n7 = n4 * n3;
            if (n7 < n5 + digit) {
                throw new UnsupportedOperationException(DatatypeMessageFormatter.formatMessage(null, "OverflowProblem", new Object[] { s }));
            }
            n4 = n7 - digit;
        } while (++n < n2);
        return -n4;
    }
    
    private static final boolean isDigit(final char c) {
        return c >= '0' && c <= '9';
    }
    
    private static final int getDigit(final char c) {
        return isDigit(c) ? (c - '0') : -1;
    }
    
    static final int maxDayInMonthFor(final int n, final int n2) {
        if (n2 == 4 || n2 == 6 || n2 == 9 || n2 == 11) {
            return 30;
        }
        if (n2 != 2) {
            return 31;
        }
        if (isLeapYear(n)) {
            return 29;
        }
        return 28;
    }
    
    private static final boolean isLeapYear(final int n) {
        return n % 4 == 0 && (n % 100 != 0 || n % 400 == 0);
    }
    
    static int mod(final int n, final int n2, final int n3) {
        return n - n3 * n2;
    }
    
    private static final BigInteger fQuotient(final BigInteger bigInteger, final BigInteger bigInteger2) {
        return bigInteger.divide(bigInteger2);
    }
    
    private static final BigInteger mod(final BigInteger bigInteger, final BigInteger bigInteger2, final BigInteger bigInteger3) {
        return bigInteger.subtract(bigInteger3.multiply(bigInteger2));
    }
    
    static final int fQuotient(final int n, final int n2) {
        return (int)Math.floor(n / n2);
    }
    
    static final int modulo(final int n, final int n2, final int n3) {
        final int n4 = n - n2;
        final int n5 = n3 - n2;
        return mod(n4, n5, fQuotient(n4, n5)) + n2;
    }
    
    static final int fQuotient(final int n, final int n2, final int n3) {
        return fQuotient(n - n2, n3 - n2);
    }
    
    static final BigInteger modulo(final BigInteger bigInteger, final BigInteger bigInteger2, final BigInteger bigInteger3) {
        final BigInteger subtract = bigInteger.subtract(bigInteger2);
        final BigInteger subtract2 = bigInteger3.subtract(bigInteger2);
        return mod(subtract, subtract2, fQuotient(subtract, subtract2)).add(bigInteger2);
    }
    
    static final BigInteger fQuotient(final BigInteger bigInteger, final BigInteger bigInteger2, final BigInteger bigInteger3) {
        return fQuotient(bigInteger.subtract(bigInteger2), bigInteger3.subtract(bigInteger2));
    }
}
