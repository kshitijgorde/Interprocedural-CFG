// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

public final class Primitives
{
    public static Boolean valueOf(final boolean value) {
        if (value) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    
    public static boolean equals(final double a, final double b) {
        return Double.doubleToLongBits(a) == Double.doubleToLongBits(b);
    }
    
    public static boolean equals(final float a, final float b) {
        return Float.floatToIntBits(a) == Float.floatToIntBits(b);
    }
    
    public static boolean equals(final byte[] a, final int abegin, final byte[] b, final int bbegin, int length) {
        try {
            while (--length >= 0) {
                if (a[abegin + length] != b[bbegin + length]) {
                    return false;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }
    
    public static boolean equals(final byte[] a, final byte[] b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.length != b.length) {
            return false;
        }
        try {
            for (int i = 0; i < a.length; ++i) {
                if (a[i] != b[i]) {
                    return false;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }
    
    public static int toInt(final long value) throws DataConversionException {
        if (value > 2147483647L || value < -2147483648L) {
            throw new DataConversionException("can not safly convert to int: " + value);
        }
        return (int)value;
    }
}
