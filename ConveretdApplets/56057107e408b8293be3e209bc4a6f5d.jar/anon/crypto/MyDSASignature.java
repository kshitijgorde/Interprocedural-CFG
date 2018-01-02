// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1InputStream;
import java.io.ByteArrayInputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import java.io.IOException;
import org.bouncycastle.asn1.DEREncodableVector;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.DERInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import java.io.OutputStream;
import org.bouncycastle.asn1.DEROutputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import logging.LogHolder;
import logging.LogType;
import java.security.InvalidKeyException;
import org.bouncycastle.crypto.CipherParameters;
import java.security.Key;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.signers.DSASigner;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public final class MyDSASignature implements IMySignature
{
    private static final AlgorithmIdentifier ms_identifier;
    private DSASigner m_SignatureAlgorithm;
    private SHA1Digest m_Digest;
    private Key m_initKey;
    
    public MyDSASignature() {
        try {
            this.m_SignatureAlgorithm = new DSASigner();
            this.m_Digest = new SHA1Digest();
        }
        catch (Exception ex) {
            this.m_SignatureAlgorithm = null;
        }
    }
    
    public synchronized void initVerify(final IMyPublicKey initKey) throws InvalidKeyException {
        try {
            this.m_SignatureAlgorithm.init(false, ((MyDSAPublicKey)initKey).getPublicParams());
            this.m_Digest.reset();
            this.m_initKey = initKey;
        }
        catch (Exception ex) {
            throw new InvalidKeyException("MyDSASignautre - initVerify - dont know how to hnalde the given key");
        }
    }
    
    public synchronized void initSign(final IMyPrivateKey initKey) throws InvalidKeyException {
        try {
            this.m_SignatureAlgorithm.init(true, ((MyDSAPrivateKey)initKey).getPrivateParams());
            this.m_initKey = initKey;
        }
        catch (Exception ex) {
            throw new InvalidKeyException("MyDSASignautre - initVerify - dont know how to hnalde the given key");
        }
    }
    
    public synchronized boolean verify(final byte[] array, final int n, final int n2, final byte[] array2, final int n3, final int n4) {
        try {
            this.m_Digest.reset();
            this.m_Digest.update(array, n, n2);
            final byte[] array3 = new byte[this.m_Digest.getDigestSize()];
            this.m_Digest.doFinal(array3, 0);
            final BigInteger[] derDecode = derDecode(array2, n3, n4);
            return this.m_SignatureAlgorithm.verifySignature(array3, derDecode[0], derDecode[1]);
        }
        catch (Throwable t) {
            LogHolder.log(7, LogType.CRYPTO, "Signature algorithm does not match!");
            return false;
        }
    }
    
    public synchronized boolean verify(final byte[] array, final byte[] array2) {
        return this.verify(array, 0, array.length, array2, 0, array2.length);
    }
    
    public synchronized byte[] sign(final byte[] array) {
        try {
            this.m_Digest.reset();
            this.m_Digest.update(array, 0, array.length);
            final byte[] array2 = new byte[this.m_Digest.getDigestSize()];
            this.m_Digest.doFinal(array2, 0);
            final BigInteger[] generateSignature = this.m_SignatureAlgorithm.generateSignature(array2);
            return derEncode(generateSignature[0], generateSignature[1]);
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public AlgorithmIdentifier getIdentifier() {
        return MyDSASignature.ms_identifier;
    }
    
    public byte[] encodeForXMLSignature(final byte[] array) {
        int n = array[3];
        int n2 = array[3 + n + 2];
        final byte[] array2 = new byte[40];
        for (int i = 0; i < 40; ++i) {
            array2[i] = 0;
        }
        int n3 = 0;
        if (n == 21) {
            n3 = 1;
            n = 20;
        }
        System.arraycopy(array, 4 + n3, array2, 20 - n, n);
        final byte b = (byte)(n + n3);
        byte b2 = 0;
        if (n2 == 21) {
            b2 = 1;
            n2 = 20;
        }
        System.arraycopy(array, 4 + b + 2 + b2, array2, 40 - n2, n2);
        return array2;
    }
    
    public byte[] decodeForXMLSignature(final byte[] array) {
        try {
            int n = 46;
            if (array[0] < 0) {
                ++n;
            }
            if (array[20] < 0) {
                ++n;
            }
            final byte[] array2 = new byte[n];
            array2[0] = 48;
            array2[1] = (byte)(n - 2);
            array2[2] = 2;
            int n2;
            if (array[0] < 0) {
                n2 = 5;
                array2[3] = 21;
                array2[4] = 0;
            }
            else {
                array2[3] = 20;
                n2 = 4;
            }
            System.arraycopy(array, 0, array2, n2, 20);
            n2 += 20;
            array2[n2++] = 2;
            if (array[20] < 0) {
                array2[n2++] = 21;
                array2[n2++] = 0;
            }
            else {
                array2[n2++] = 20;
            }
            System.arraycopy(array, 20, array2, n2, 20);
            return array2;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public String getXMLSignatureAlgorithmReference() {
        return "http://www.w3.org/2000/09/xmldsig#dsa-sha1";
    }
    
    static byte[] derEncode(final BigInteger bigInteger, final BigInteger bigInteger2) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DEROutputStream derOutputStream = new DEROutputStream(byteArrayOutputStream);
        final ASN1EncodableVector asn1EncodableVector = new ASN1EncodableVector();
        asn1EncodableVector.add(new DERInteger(bigInteger));
        asn1EncodableVector.add(new DERInteger(bigInteger2));
        derOutputStream.writeObject(new DERSequence(asn1EncodableVector));
        return byteArrayOutputStream.toByteArray();
    }
    
    static BigInteger[] derDecode(final byte[] array, final int n, final int n2) throws IOException {
        final ASN1Sequence asn1Sequence = (ASN1Sequence)new ASN1InputStream(new ByteArrayInputStream(array, n, n2)).readObject();
        return new BigInteger[] { ((DERInteger)asn1Sequence.getObjectAt(0)).getValue(), ((DERInteger)asn1Sequence.getObjectAt(1)).getValue() };
    }
    
    static {
        ms_identifier = new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa_with_sha1);
    }
}
