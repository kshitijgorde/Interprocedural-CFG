// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.data;

public class NumUtils
{
    public static double stringToReal(final String s) {
        try {
            return new Double(s);
        }
        catch (NumberFormatException ex) {
            return Double.NaN;
        }
    }
    
    public static String realToString(final double n) {
        return realToString(n, 10);
    }
    
    public static String realToString(double n, int min) {
        min = Math.min(25, Math.max(6, min));
        if (Double.isNaN(n)) {
            return "undefined";
        }
        if (Double.isInfinite(n)) {
            if (n < 0.0) {
                return "-INF";
            }
            return "INF";
        }
        else {
            String s = String.valueOf(n);
            if (Math.rint(n) == n && Math.abs(n) < 5.0E15 && s.length() <= min + 2) {
                return String.valueOf((long)n);
            }
            if (s.length() <= min) {
                return s;
            }
            boolean b = false;
            if (n < 0.0) {
                b = true;
                n = -n;
                --min;
                s = String.valueOf(n);
            }
            final long n2 = 5L * (long)Math.pow(10.0, min - 2);
            String s2;
            if (n >= 5.0E-4 && n <= n2 && s.indexOf(69) == -1 && s.indexOf(101) == -1) {
                s2 = trimZeros(round(s, min));
            }
            else if (n > 1.0) {
                final long n3 = (long)Math.floor(Math.log(n) / Math.log(10.0));
                final String string = "E" + n3;
                final int n4 = min - string.length();
                n /= Math.pow(10.0, n3);
                s2 = String.valueOf(trimZeros(round(String.valueOf(n), n4))) + string;
            }
            else {
                final long n5 = (long)Math.ceil(-Math.log(n) / Math.log(10.0));
                final String string2 = "E-" + n5;
                final int n6 = min - string2.length();
                n *= Math.pow(10.0, n5);
                s2 = String.valueOf(trimZeros(round(String.valueOf(n), n6))) + string2;
            }
            if (b) {
                return "-" + s2;
            }
            return s2;
        }
    }
    
    private static String trimZeros(String s) {
        if (s.indexOf(46) >= 0 && s.charAt(s.length() - 1) == '0') {
            int n;
            for (n = s.length() - 1; s.charAt(n) == '0'; --n) {}
            if (s.charAt(n) == '.') {
                s = s.substring(0, n);
            }
            else {
                s = s.substring(0, n + 1);
            }
        }
        return s;
    }
    
    private static String round(final String s, final int n) {
        if (s.indexOf(46) < 0) {
            return s;
        }
        if (s.length() <= n) {
            return s;
        }
        if (s.charAt(n) >= '5' && s.charAt(n) != '.') {
            final char[] array = new char[n + 1];
            int n2 = n;
            int n3 = 1;
            for (int i = n - 1; i >= 0; --i) {
                array[n2] = s.charAt(i);
                if (n3 != 0 && array[n2] != '.') {
                    if (array[n2] < '9') {
                        final char[] array2 = array;
                        final int n4 = n2;
                        ++array2[n4];
                        n3 = 0;
                    }
                    else {
                        array[n2] = '0';
                    }
                }
                --n2;
            }
            if (n3 != 0) {
                array[n2] = '1';
                --n2;
            }
            return new String(array, n2 + 1, n - n2);
        }
        return s.substring(0, n);
    }
}
