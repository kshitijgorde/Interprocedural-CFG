// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.field;

import org.joda.time.DateTimeFieldType;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.DateTimeField;

public class FieldUtils
{
    public static int safeNegate(final int n) {
        if (n == Integer.MIN_VALUE) {
            throw new ArithmeticException("Integer.MIN_VALUE cannot be negated");
        }
        return -n;
    }
    
    public static int safeAdd(final int n, final int n2) {
        final int n3 = n + n2;
        if ((n ^ n3) < 0 && (n ^ n2) >= 0) {
            throw new ArithmeticException("The calculation caused an overflow: " + n + " + " + n2);
        }
        return n3;
    }
    
    public static long safeAdd(final long n, final long n2) {
        final long n3 = n + n2;
        if ((n ^ n3) < 0L && (n ^ n2) >= 0L) {
            throw new ArithmeticException("The calculation caused an overflow: " + n + " + " + n2);
        }
        return n3;
    }
    
    public static long safeSubtract(final long n, final long n2) {
        final long n3 = n - n2;
        if ((n ^ n3) < 0L && (n ^ n2) < 0L) {
            throw new ArithmeticException("The calculation caused an overflow: " + n + " - " + n2);
        }
        return n3;
    }
    
    public static int safeMultiply(final int n, final int n2) {
        final long n3 = n * n2;
        if (n3 < -2147483648L || n3 > 2147483647L) {
            throw new ArithmeticException("The calculation caused an overflow: " + n + " * " + n2);
        }
        return (int)n3;
    }
    
    public static long safeMultiply(final long n, final int n2) {
        switch (n2) {
            case -1: {
                return -n;
            }
            case 0: {
                return 0L;
            }
            case 1: {
                return n;
            }
            default: {
                final long n3 = n * n2;
                if (n3 / n2 != n) {
                    throw new ArithmeticException("The calculation caused an overflow: " + n + " * " + n2);
                }
                return n3;
            }
        }
    }
    
    public static long safeMultiply(final long n, final long n2) {
        if (n2 == 1L) {
            return n;
        }
        if (n2 == 0L) {
            return 0L;
        }
        final long n3 = n * n2;
        if (n3 / n2 != n) {
            throw new ArithmeticException("The calculation caused an overflow: " + n + " * " + n2);
        }
        return n3;
    }
    
    public static int safeToInt(final long n) {
        if (-2147483648L <= n && n <= 2147483647L) {
            return (int)n;
        }
        throw new ArithmeticException("Value cannot fit in an int: " + n);
    }
    
    public static int safeMultiplyToInt(final long n, final long n2) {
        return safeToInt(safeMultiply(n, n2));
    }
    
    public static void verifyValueBounds(final DateTimeField dateTimeField, final int n, final int n2, final int n3) {
        if (n < n2 || n > n3) {
            throw new IllegalFieldValueException(dateTimeField.getType(), new Integer(n), new Integer(n2), new Integer(n3));
        }
    }
    
    public static void verifyValueBounds(final DateTimeFieldType dateTimeFieldType, final int n, final int n2, final int n3) {
        if (n < n2 || n > n3) {
            throw new IllegalFieldValueException(dateTimeFieldType, new Integer(n), new Integer(n2), new Integer(n3));
        }
    }
    
    public static void verifyValueBounds(final String s, final int n, final int n2, final int n3) {
        if (n < n2 || n > n3) {
            throw new IllegalFieldValueException(s, new Integer(n), new Integer(n2), new Integer(n3));
        }
    }
    
    public static int getWrappedValue(final int n, final int n2, final int n3, final int n4) {
        return getWrappedValue(n + n2, n3, n4);
    }
    
    public static int getWrappedValue(int n, final int n2, final int n3) {
        if (n2 >= n3) {
            throw new IllegalArgumentException("MIN > MAX");
        }
        final int n4 = n3 - n2 + 1;
        n -= n2;
        if (n >= 0) {
            return n % n4 + n2;
        }
        final int n5 = -n % n4;
        if (n5 == 0) {
            return 0 + n2;
        }
        return n4 - n5 + n2;
    }
    
    public static boolean equals(final Object o, final Object o2) {
        return o == o2 || (o != null && o2 != null && o.equals(o2));
    }
}
