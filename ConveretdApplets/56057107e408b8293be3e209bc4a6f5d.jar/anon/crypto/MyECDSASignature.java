// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import logging.LogHolder;
import logging.LogType;
import java.security.InvalidKeyException;
import org.bouncycastle.crypto.CipherParameters;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Key;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public final class MyECDSASignature implements IMySignature
{
    private static final AlgorithmIdentifier ms_identifier;
    SHA1Digest m_digest;
    ECDSASigner m_signatureAlgorithm;
    private Key m_initKey;
    
    public MyECDSASignature() {
        this.m_digest = new SHA1Digest();
        this.m_signatureAlgorithm = new ECDSASigner();
    }
    
    public byte[] encodeForXMLSignature(final byte[] array) {
        final int length = ((MyECPrivateKey)this.m_initKey).getPrivateParams().getParameters().getN().toByteArray().length;
        final byte b = array[3];
        final byte b2 = array[3 + b + 2];
        final byte[] array2 = new byte[2 * length];
        for (byte b3 = 0; b3 < 2 * length; ++b3) {
            array2[b3] = 0;
        }
        System.arraycopy(array, 4, array2, length - b, b);
        System.arraycopy(array, 4 + b + 2, array2, 2 * length - b2, b2);
        return array2;
    }
    
    public byte[] decodeForXMLSignature(final byte[] array) {
        final int length = ((MyECPublicKey)this.m_initKey).getPublicParams().getParameters().getN().toByteArray().length;
        if (array.length != 2 * length) {
            return null;
        }
        final byte[] array2 = new byte[length];
        final byte[] array3 = new byte[length];
        System.arraycopy(array, 0, array2, 0, length);
        System.arraycopy(array, length, array3, 0, length);
        byte[] derEncode;
        try {
            derEncode = MyDSASignature.derEncode(new BigInteger(array2), new BigInteger(array3));
        }
        catch (IOException ex) {
            derEncode = null;
        }
        return derEncode;
    }
    
    public String getXMLSignatureAlgorithmReference() {
        return "http://www.w3.org/2001/04/xmldsig-more#ecdsa-sha1";
    }
    
    public synchronized void initSign(final IMyPrivateKey initKey) throws InvalidKeyException {
        try {
            this.m_signatureAlgorithm.init(true, ((MyECPrivateKey)initKey).getPrivateParams());
            this.m_initKey = initKey;
        }
        catch (Exception ex) {
            throw new InvalidKeyException("MyECDSASignautre - initVerify - dont know how to handle the given key");
        }
    }
    
    public synchronized void initVerify(final IMyPublicKey initKey) throws InvalidKeyException {
        try {
            this.m_signatureAlgorithm.init(false, ((MyECPublicKey)initKey).getPublicParams());
            this.m_digest.reset();
            this.m_initKey = initKey;
        }
        catch (Exception ex) {
            throw new InvalidKeyException("MyECDSASignautre - initVerify - dont know how to handle the given key");
        }
    }
    
    public synchronized byte[] sign(final byte[] array) {
        try {
            this.m_digest.reset();
            this.m_digest.update(array, 0, array.length);
            final byte[] array2 = new byte[this.m_digest.getDigestSize()];
            this.m_digest.doFinal(array2, 0);
            final BigInteger[] generateSignature = this.m_signatureAlgorithm.generateSignature(array2);
            return MyDSASignature.derEncode(generateSignature[0], generateSignature[1]);
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public synchronized boolean verify(final byte[] array, final int n, final int n2, final byte[] array2, final int n3, final int n4) {
        try {
            this.m_digest.reset();
            this.m_digest.update(array, n, n2);
            final byte[] array3 = new byte[this.m_digest.getDigestSize()];
            this.m_digest.doFinal(array3, 0);
            final BigInteger[] derDecode = MyDSASignature.derDecode(array2, n3, n4);
            return this.m_signatureAlgorithm.verifySignature(array3, derDecode[0], derDecode[1]);
        }
        catch (Throwable t) {
            LogHolder.log(7, LogType.CRYPTO, "Signature algorithm does not match!");
            return false;
        }
    }
    
    public synchronized boolean verify(final byte[] array, final byte[] array2) {
        return this.verify(array, 0, array.length, array2, 0, array2.length);
    }
    
    public AlgorithmIdentifier getIdentifier() {
        return MyECDSASignature.ms_identifier;
    }
    
    static {
        ms_identifier = new AlgorithmIdentifier(X9ObjectIdentifiers.ecdsa_with_SHA1);
    }
}
