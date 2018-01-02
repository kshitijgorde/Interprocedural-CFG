// 
// Decompiled by Procyon v0.5.30
// 

package seguriSign_Client;

import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.math.BigInteger;

public class PrivKey
{
    public byte[] encoded;
    public BigInteger version;
    public BigInteger modulus;
    public BigInteger publicExp;
    public BigInteger privateExp;
    public BigInteger primeP;
    public BigInteger primeQ;
    public BigInteger expP;
    public BigInteger expQ;
    public BigInteger coefficient;
    
    public PrivKey(final byte[] array) {
        this.encoded = new byte[array.length];
        for (int i = 0; i < array.length; ++i) {
            this.encoded[i] = array[i];
        }
        final ASN1 asn1 = new ASN1(new ASN1(new ASN1(new ASN1(new ASN1(new ASN1(array).value).remaining).remaining).value).value);
        final ASN1 asn2 = new ASN1(asn1.remaining);
        final ASN1 asn3 = new ASN1(asn2.remaining);
        final ASN1 asn4 = new ASN1(asn3.remaining);
        final ASN1 asn5 = new ASN1(asn4.remaining);
        final ASN1 asn6 = new ASN1(asn5.remaining);
        final ASN1 asn7 = new ASN1(asn6.remaining);
        final ASN1 asn8 = new ASN1(asn7.remaining);
        final ASN1 asn9 = new ASN1(asn8.remaining);
        this.version = new BigInteger(asn1.value);
        this.modulus = new BigInteger(asn2.value);
        this.publicExp = new BigInteger(asn3.value);
        this.privateExp = new BigInteger(asn4.value);
        this.primeP = new BigInteger(asn5.value);
        this.primeQ = new BigInteger(asn6.value);
        this.expP = new BigInteger(asn7.value);
        this.expQ = new BigInteger(asn8.value);
        this.coefficient = new BigInteger(asn9.value);
    }
    
    public PrivateKey buildRSAPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        return KeyFactory.getInstance("RSA").generatePrivate(new RSAPrivateCrtKeySpec(this.modulus, this.publicExp, this.publicExp, this.primeP, this.primeQ, this.expP, this.expQ, this.coefficient));
    }
    
    public void print() {
        for (int i = 0; i < this.encoded.length; ++i) {
            if (i % 16 == 0) {
                System.out.println();
            }
            System.out.print(Integer.toHexString(byte2Int(this.encoded[i])) + " ");
        }
    }
    
    public static int byte2Int(final byte b) {
        if (b >= 0) {
            return b;
        }
        return (b & 0x7F) + 128;
    }
}
