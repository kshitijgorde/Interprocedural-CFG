// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.params.DSAValidationParameters;
import java.util.Random;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.params.DSAParameters;
import java.math.BigInteger;
import java.security.SecureRandom;

public class DSAParametersGenerator
{
    private int size;
    private int certainty;
    private SecureRandom random;
    private static final BigInteger ONE;
    private static final BigInteger TWO;
    
    public void init(final int size, final int certainty, final SecureRandom random) {
        this.size = size;
        this.certainty = certainty;
        this.random = random;
    }
    
    private void add(final byte[] array, final byte[] array2, final int n) {
        final int n2 = (array2[array2.length - 1] & 0xFF) + n;
        array[array2.length - 1] = (byte)n2;
        int n3 = n2 >>> 8;
        for (int i = array2.length - 2; i >= 0; --i) {
            final int n4 = n3 + (array2[i] & 0xFF);
            array[i] = (byte)n4;
            n3 = n4 >>> 8;
        }
    }
    
    public DSAParameters generateParameters() {
        final byte[] array = new byte[20];
        final byte[] array2 = new byte[20];
        final byte[] array3 = new byte[20];
        final byte[] array4 = new byte[20];
        final SHA1Digest sha1Digest = new SHA1Digest();
        final int n = (this.size - 1) / 160;
        final byte[] array5 = new byte[this.size / 8];
        BigInteger bigInteger = null;
        BigInteger subtract = null;
        int i = 0;
        for (boolean b = false; !b; b = true) {
            do {
                this.random.nextBytes(array);
                sha1Digest.update(array, 0, array.length);
                sha1Digest.doFinal(array2, 0);
                System.arraycopy(array, 0, array3, 0, array.length);
                this.add(array3, array, 1);
                sha1Digest.update(array3, 0, array3.length);
                sha1Digest.doFinal(array3, 0);
                for (int j = 0; j != array4.length; ++j) {
                    array4[j] = (byte)(array2[j] ^ array3[j]);
                }
                final byte[] array6 = array4;
                final int n2 = 0;
                array6[n2] |= 0xFFFFFF80;
                final byte[] array7 = array4;
                final int n3 = 19;
                array7[n3] |= 0x1;
                bigInteger = new BigInteger(1, array4);
            } while (!bigInteger.isProbablePrime(this.certainty));
            i = 0;
            for (int n4 = 2; i < 4096; ++i, n4 += n + 1) {
                for (int k = 0; k < n; ++k) {
                    this.add(array2, array, n4 + k);
                    sha1Digest.update(array2, 0, array2.length);
                    sha1Digest.doFinal(array2, 0);
                    System.arraycopy(array2, 0, array5, array5.length - (k + 1) * array2.length, array2.length);
                }
                this.add(array2, array, n4 + n);
                sha1Digest.update(array2, 0, array2.length);
                sha1Digest.doFinal(array2, 0);
                System.arraycopy(array2, array2.length - (array5.length - n * array2.length), array5, 0, array5.length - n * array2.length);
                final byte[] array8 = array5;
                final int n5 = 0;
                array8[n5] |= 0xFFFFFF80;
                final BigInteger bigInteger2 = new BigInteger(1, array5);
                subtract = bigInteger2.subtract(bigInteger2.mod(bigInteger.multiply(DSAParametersGenerator.TWO)).subtract(DSAParametersGenerator.ONE));
                if (subtract.testBit(this.size - 1) && subtract.isProbablePrime(this.certainty)) {
                    break;
                }
            }
        }
        final BigInteger divide = subtract.subtract(DSAParametersGenerator.ONE).divide(bigInteger);
        BigInteger modPow;
        while (true) {
            final BigInteger bigInteger3 = new BigInteger(this.size, this.random);
            if (bigInteger3.compareTo(DSAParametersGenerator.ONE) > 0) {
                if (bigInteger3.compareTo(subtract.subtract(DSAParametersGenerator.ONE)) >= 0) {
                    continue;
                }
                modPow = bigInteger3.modPow(divide, subtract);
                if (modPow.compareTo(DSAParametersGenerator.ONE) <= 0) {
                    continue;
                }
                break;
            }
        }
        return new DSAParameters(subtract, bigInteger, modPow, new DSAValidationParameters(array, i));
    }
    
    static {
        ONE = BigInteger.valueOf(1L);
        TWO = BigInteger.valueOf(2L);
    }
}
