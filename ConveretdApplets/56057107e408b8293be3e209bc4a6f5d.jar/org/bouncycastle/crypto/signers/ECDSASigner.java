// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.crypto.signers;

import org.bouncycastle.math.ec.ECAlgorithms;
import java.util.Random;
import java.math.BigInteger;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.CipherParameters;
import java.security.SecureRandom;
import org.bouncycastle.crypto.params.ECKeyParameters;
import org.bouncycastle.crypto.DSA;
import org.bouncycastle.math.ec.ECConstants;

public class ECDSASigner implements ECConstants, DSA
{
    ECKeyParameters key;
    SecureRandom random;
    
    public void init(final boolean b, final CipherParameters cipherParameters) {
        if (b) {
            if (cipherParameters instanceof ParametersWithRandom) {
                final ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
                this.random = parametersWithRandom.getRandom();
                this.key = (ECPrivateKeyParameters)parametersWithRandom.getParameters();
            }
            else {
                this.random = new SecureRandom();
                this.key = (ECPrivateKeyParameters)cipherParameters;
            }
        }
        else {
            this.key = (ECPublicKeyParameters)cipherParameters;
        }
    }
    
    public BigInteger[] generateSignature(final byte[] array) {
        final BigInteger n = this.key.getParameters().getN();
        final BigInteger calculateE = this.calculateE(n, array);
        BigInteger mod;
        BigInteger mod2;
        do {
            final int bitLength = n.bitLength();
            BigInteger bigInteger;
            while (true) {
                bigInteger = new BigInteger(bitLength, this.random);
                if (!bigInteger.equals(ECConstants.ZERO)) {
                    mod2 = this.key.getParameters().getG().multiply(bigInteger).getX().toBigInteger().mod(n);
                    if (!mod2.equals(ECConstants.ZERO)) {
                        break;
                    }
                    continue;
                }
            }
            mod = bigInteger.modInverse(n).multiply(calculateE.add(((ECPrivateKeyParameters)this.key).getD().multiply(mod2))).mod(n);
        } while (mod.equals(ECConstants.ZERO));
        return new BigInteger[] { mod2, mod };
    }
    
    public boolean verifySignature(final byte[] array, final BigInteger bigInteger, final BigInteger bigInteger2) {
        final BigInteger n = this.key.getParameters().getN();
        final BigInteger calculateE = this.calculateE(n, array);
        if (bigInteger.compareTo(ECConstants.ONE) < 0 || bigInteger.compareTo(n) >= 0) {
            return false;
        }
        if (bigInteger2.compareTo(ECConstants.ONE) < 0 || bigInteger2.compareTo(n) >= 0) {
            return false;
        }
        final BigInteger modInverse = bigInteger2.modInverse(n);
        return ECAlgorithms.sumOfTwoMultiplies(this.key.getParameters().getG(), calculateE.multiply(modInverse).mod(n), ((ECPublicKeyParameters)this.key).getQ(), bigInteger.multiply(modInverse).mod(n)).getX().toBigInteger().mod(n).equals(bigInteger);
    }
    
    private BigInteger calculateE(final BigInteger bigInteger, final byte[] array) {
        if (bigInteger.bitLength() > array.length * 8) {
            return new BigInteger(1, array);
        }
        final int n = array.length * 8;
        BigInteger shiftRight = new BigInteger(1, array);
        if (n - bigInteger.bitLength() > 0) {
            shiftRight = shiftRight.shiftRight(n - bigInteger.bitLength());
        }
        return shiftRight;
    }
}
