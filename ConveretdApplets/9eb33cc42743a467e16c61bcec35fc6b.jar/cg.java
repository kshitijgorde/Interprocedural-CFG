import java.io.IOException;
import java.math.BigInteger;
import mindbright.security.KeyPair;

// 
// Decompiled by Procyon v0.5.30
// 

public class cg
{
    public KeyPair m2;
    
    public cg(final KeyPair m2) {
        this.m2 = m2;
    }
    
    public final BigInteger m8(final BigInteger bigInteger) {
        final ce ce = this.m2.fs();
        return bigInteger.modPow(ce.m3(), ce.m2());
    }
    
    public final BigInteger m7(final BigInteger bigInteger) {
        final BigInteger value = BigInteger.valueOf(1L);
        final cf cf = this.m2.fr();
        final BigInteger mod = cf.m1().mod(cf.m_().subtract(value));
        final BigInteger mod2 = cf.m1().mod(cf.mz().subtract(value));
        final BigInteger modPow = bigInteger.mod(cf.m_()).modPow(mod, cf.m_());
        final BigInteger modPow2 = bigInteger.mod(cf.mz()).modPow(mod2, cf.mz());
        if (modPow.compareTo(modPow2) == 0) {
            return modPow;
        }
        return modPow2.subtract(modPow).mod(cf.mz()).multiply(cf.m0()).mod(cf.mz()).multiply(cf.m_()).add(modPow);
    }
    
    public static BigInteger m6(final BigInteger bigInteger) throws IOException {
        final byte[] byteArray = bigInteger.toByteArray();
        if (byteArray[0] != 2) {
            throw new IOException("Invalid strip-data");
        }
        int n;
        for (n = 0; n < byteArray.length && byteArray[n] != 0; ++n) {}
        if (n == byteArray.length) {
            throw new IOException("Invalid strip-data");
        }
        final byte[] array = new byte[byteArray.length - n];
        System.arraycopy(byteArray, n, array, 0, array.length);
        return new BigInteger(array);
    }
    
    public static BigInteger m5(final BigInteger bigInteger, final int n, final cd cd) throws IOException {
        final int n2 = (bigInteger.bitLength() + 7) / 8;
        final int n3 = (n + 7) / 8;
        if (n2 > n3 - 3) {
            throw new IOException("rsaPad: Input too long to pad");
        }
        final byte[] array = new byte[n3 - n2 - 3 + 1];
        cd.nextBytes(array);
        array[0] = 0;
        for (int i = 1; i < n3 - n2 - 3 + 1; ++i) {
            if (array[i] == 0) {
                array[i] = 23;
            }
        }
        return new BigInteger("2").shiftLeft((n3 - 2) * 8).or(new BigInteger(array).shiftLeft((n2 + 1) * 8)).or(bigInteger);
    }
}
