// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.util;

import java.util.Random;
import java.security.SecureRandom;
import java.math.BigInteger;

public final class BigIntegers
{
    private static final int MAX_ITERATIONS = 1000;
    private static final BigInteger ZERO;
    
    public static byte[] asUnsignedByteArray(final BigInteger bigInteger) {
        final byte[] byteArray = bigInteger.toByteArray();
        if (byteArray[0] == 0) {
            final byte[] array = new byte[byteArray.length - 1];
            System.arraycopy(byteArray, 1, array, 0, array.length);
            return array;
        }
        return byteArray;
    }
    
    public static BigInteger createRandomInRange(final BigInteger bigInteger, final BigInteger bigInteger2, final SecureRandom secureRandom) {
        final int compareTo = bigInteger.compareTo(bigInteger2);
        if (compareTo >= 0) {
            if (compareTo > 0) {
                throw new IllegalArgumentException("'min' may not be greater than 'max'");
            }
            return bigInteger;
        }
        else {
            if (bigInteger.bitLength() > bigInteger2.bitLength() / 2) {
                return createRandomInRange(BigIntegers.ZERO, bigInteger2.subtract(bigInteger), secureRandom).add(bigInteger);
            }
            for (int i = 0; i < 1000; ++i) {
                final BigInteger bigInteger3 = new BigInteger(bigInteger2.bitLength(), secureRandom);
                if (bigInteger3.compareTo(bigInteger) >= 0 && bigInteger3.compareTo(bigInteger2) <= 0) {
                    return bigInteger3;
                }
            }
            return new BigInteger(bigInteger2.subtract(bigInteger).bitLength() - 1, secureRandom).add(bigInteger);
        }
    }
    
    static {
        ZERO = BigInteger.valueOf(0L);
    }
}
