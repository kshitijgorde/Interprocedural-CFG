// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.util;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Radix
{
    private static final char[] A;
    
    static {
        A = A();
    }
    
    public static int getMaxRadix() {
        return Radix.A.length;
    }
    
    public static String convert(final long n) {
        return convert(n, Radix.A.length);
    }
    
    public static String convert(long n, final int n2) {
        final StringBuilder sb = new StringBuilder();
        while (n > 0L) {
            sb.append(Radix.A[(int)(n % n2)]);
            n /= n2;
        }
        sb.reverse();
        return sb.toString();
    }
    
    public static String convert(final BigInteger bigInteger) {
        return convert(bigInteger, Radix.A.length);
    }
    
    public static String convert(BigInteger bigInteger, final int n) {
        final BigInteger value = BigInteger.valueOf(n);
        final StringBuilder sb = new StringBuilder();
        while (bigInteger.compareTo(BigInteger.ZERO) > 0) {
            final BigInteger[] divideAndRemainder = bigInteger.divideAndRemainder(value);
            sb.append(Radix.A[divideAndRemainder[1].intValue()]);
            bigInteger = divideAndRemainder[0];
        }
        sb.reverse();
        return sb.toString();
    }
    
    public static long convertLong(final String s, final int n) {
        final int length = s.length();
        long n2 = 0L;
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 != ' ') {
                if (i > 0) {
                    n2 *= n;
                }
                for (int j = 0; j < Radix.A.length; ++j) {
                    if (Radix.A[j] == char1) {
                        n2 += j;
                        break;
                    }
                }
            }
        }
        return n2;
    }
    
    public static BigInteger convertBig(final String s, final int n) {
        final int length = s.length();
        BigInteger bigInteger = BigInteger.ZERO;
        final BigInteger value = BigInteger.valueOf(n);
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 != ' ') {
                if (i > 0) {
                    bigInteger = bigInteger.multiply(value);
                }
                for (int j = 0; j < Radix.A.length; ++j) {
                    if (Radix.A[j] == char1) {
                        bigInteger = bigInteger.add(BigInteger.valueOf(j));
                        break;
                    }
                }
            }
        }
        return bigInteger;
    }
    
    private static char[] A() {
        final StringBuilder sb = new StringBuilder();
        sb.append("0123456789");
        sb.append("abcdefghjkmnopqrstuvwxyz");
        sb.append("ABCDEFGHJKLMNPQRSTUVWXYZ");
        sb.append("!@#$%^&*-+=:.<>?~|_");
        return sb.toString().toCharArray();
    }
    
    public static void main(final String[] array) throws Exception {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("> ");
            final String[] split = bufferedReader.readLine().split(",");
            if (split.length > 1) {
                final BigInteger bigInteger = new BigInteger(split[0].trim());
                final int int1 = Integer.parseInt(split[1].trim());
                final String convert = convert(bigInteger, int1);
                System.out.printf("%s -> %d\n\n", convert, convertBig(convert, int1));
            }
            else {
                final String convert2 = convert(new BigInteger(split[0].trim()), getMaxRadix());
                System.out.printf("%s -> %d\n\n", convert2, convertBig(convert2, getMaxRadix()));
            }
        }
    }
}
