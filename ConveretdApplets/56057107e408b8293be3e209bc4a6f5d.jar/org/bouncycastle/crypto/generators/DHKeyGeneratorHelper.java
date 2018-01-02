// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.crypto.generators;

import org.bouncycastle.util.BigIntegers;
import java.util.Random;
import java.security.SecureRandom;
import org.bouncycastle.crypto.params.DHParameters;
import java.math.BigInteger;

class DHKeyGeneratorHelper
{
    static final DHKeyGeneratorHelper INSTANCE;
    private static final BigInteger ONE;
    private static final BigInteger TWO;
    
    BigInteger calculatePrivate(final DHParameters dhParameters, final SecureRandom secureRandom) {
        final BigInteger p2 = dhParameters.getP();
        final int l = dhParameters.getL();
        if (l != 0) {
            return new BigInteger(l, secureRandom).setBit(l - 1);
        }
        BigInteger bigInteger = DHKeyGeneratorHelper.TWO;
        final int m = dhParameters.getM();
        if (m != 0) {
            bigInteger = DHKeyGeneratorHelper.ONE.shiftLeft(m - 1);
        }
        BigInteger bigInteger2 = p2.subtract(DHKeyGeneratorHelper.TWO);
        final BigInteger q = dhParameters.getQ();
        if (q != null) {
            bigInteger2 = q.subtract(DHKeyGeneratorHelper.TWO);
        }
        return BigIntegers.createRandomInRange(bigInteger, bigInteger2, secureRandom);
    }
    
    BigInteger calculatePublic(final DHParameters dhParameters, final BigInteger bigInteger) {
        return dhParameters.getG().modPow(bigInteger, dhParameters.getP());
    }
    
    static {
        INSTANCE = new DHKeyGeneratorHelper();
        ONE = BigInteger.valueOf(1L);
        TWO = BigInteger.valueOf(2L);
    }
}
