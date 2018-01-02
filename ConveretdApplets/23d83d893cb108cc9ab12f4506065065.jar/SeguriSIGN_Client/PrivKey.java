// 
// Decompiled by Procyon v0.5.30
// 

package SeguriSIGN_Client;

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
        final ASN1Decoder asn1Decoder = new ASN1Decoder(new ASN1Decoder(new ASN1Decoder(new ASN1Decoder(new ASN1Decoder(new ASN1Decoder(array).value).remaining).remaining).value).value);
        final ASN1Decoder asn1Decoder2 = new ASN1Decoder(asn1Decoder.remaining);
        final ASN1Decoder asn1Decoder3 = new ASN1Decoder(asn1Decoder2.remaining);
        final ASN1Decoder asn1Decoder4 = new ASN1Decoder(asn1Decoder3.remaining);
        final ASN1Decoder asn1Decoder5 = new ASN1Decoder(asn1Decoder4.remaining);
        final ASN1Decoder asn1Decoder6 = new ASN1Decoder(asn1Decoder5.remaining);
        final ASN1Decoder asn1Decoder7 = new ASN1Decoder(asn1Decoder6.remaining);
        final ASN1Decoder asn1Decoder8 = new ASN1Decoder(asn1Decoder7.remaining);
        final ASN1Decoder asn1Decoder9 = new ASN1Decoder(asn1Decoder8.remaining);
        this.version = new BigInteger(asn1Decoder.getValue());
        this.modulus = new BigInteger(asn1Decoder2.getValue());
        this.publicExp = new BigInteger(asn1Decoder3.getValue());
        this.privateExp = new BigInteger(asn1Decoder4.getValue());
        this.primeP = new BigInteger(asn1Decoder5.getValue());
        this.primeQ = new BigInteger(asn1Decoder6.getValue());
        this.expP = new BigInteger(asn1Decoder7.getValue());
        this.expQ = new BigInteger(asn1Decoder8.getValue());
        this.coefficient = new BigInteger(asn1Decoder9.getValue());
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
