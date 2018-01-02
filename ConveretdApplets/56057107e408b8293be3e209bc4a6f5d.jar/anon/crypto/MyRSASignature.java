// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.asn1.DERObjectIdentifier;
import java.io.OutputStream;
import org.bouncycastle.asn1.DEROutputStream;
import java.io.ByteArrayOutputStream;
import org.bouncycastle.asn1.DEREncodable;
import logging.LogHolder;
import logging.LogType;
import org.bouncycastle.asn1.ASN1Null;
import org.bouncycastle.asn1.x509.DigestInfo;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1InputStream;
import java.io.ByteArrayInputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import java.security.InvalidKeyException;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.digests.SHA1Digest;
import java.security.Key;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public final class MyRSASignature implements IMySignature
{
    private static final AlgorithmIdentifier ms_identifier;
    private PKCS1Encoding m_SignatureAlgorithm;
    private Key m_initKey;
    private SHA1Digest m_Digest;
    private static final AlgorithmIdentifier ms_AlgID;
    
    public MyRSASignature() {
        this.m_SignatureAlgorithm = new PKCS1Encoding(new RSAEngine());
        this.m_Digest = new SHA1Digest();
    }
    
    public synchronized void initVerify(final IMyPublicKey initKey) throws InvalidKeyException {
        this.m_SignatureAlgorithm.init(false, ((MyRSAPublicKey)initKey).getParams());
        this.m_initKey = initKey;
    }
    
    public synchronized void initSign(final IMyPrivateKey initKey) throws InvalidKeyException {
        this.m_SignatureAlgorithm.init(true, ((MyRSAPrivateKey)initKey).getParams());
        this.m_initKey = initKey;
    }
    
    public synchronized boolean verify(final byte[] array, final byte[] array2) {
        return this.verify(array, 0, array.length, array2, 0, array2.length);
    }
    
    public synchronized boolean verify(final byte[] array, final int n, final int n2, final byte[] array2, final int n3, final int n4) {
        try {
            this.m_Digest.reset();
            this.m_Digest.update(array, n, n2);
            final byte[] array3 = new byte[this.m_Digest.getDigestSize()];
            this.m_Digest.doFinal(array3, 0);
            final DigestInfo digestInfo = new DigestInfo((ASN1Sequence)new ASN1InputStream(new ByteArrayInputStream(this.m_SignatureAlgorithm.processBlock(array2, n3, n4))).readObject());
            if (!digestInfo.getAlgorithmId().getObjectId().equals(MyRSASignature.ms_AlgID.getObjectId())) {
                return false;
            }
            final DEREncodable parameters = digestInfo.getAlgorithmId().getParameters();
            if (parameters != null && !(parameters instanceof ASN1Null)) {
                return false;
            }
            final byte[] digest = digestInfo.getDigest();
            if (array3.length != digest.length) {
                return false;
            }
            for (int i = 0; i < array3.length; ++i) {
                if (digest[i] != array3[i]) {
                    return false;
                }
            }
            return true;
        }
        catch (Throwable t) {
            LogHolder.log(7, LogType.CRYPTO, "Signature algorithm does not match!");
            return false;
        }
    }
    
    public synchronized boolean verifyPlain(final byte[] array, final byte[] array2) {
        try {
            final byte[] processBlock = this.m_SignatureAlgorithm.processBlock(array2, 0, array2.length);
            if (array.length != processBlock.length) {
                return false;
            }
            for (int i = 0; i < array.length; ++i) {
                if (processBlock[i] != array[i]) {
                    return false;
                }
            }
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public synchronized byte[] sign(final byte[] array) {
        try {
            final byte[] array2 = new byte[this.m_Digest.getDigestSize()];
            this.m_Digest.reset();
            this.m_Digest.update(array, 0, array.length);
            this.m_Digest.doFinal(array2, 0);
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new DEROutputStream(byteArrayOutputStream).writeObject(new DigestInfo(MyRSASignature.ms_AlgID, array2));
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            return this.m_SignatureAlgorithm.processBlock(byteArray, 0, byteArray.length);
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public synchronized byte[] signPlain(final byte[] array) {
        try {
            return this.m_SignatureAlgorithm.processBlock(array, 0, array.length);
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public AlgorithmIdentifier getIdentifier() {
        return MyRSASignature.ms_identifier;
    }
    
    public byte[] encodeForXMLSignature(final byte[] array) {
        return array;
    }
    
    public byte[] decodeForXMLSignature(final byte[] array) {
        return array;
    }
    
    public String getXMLSignatureAlgorithmReference() {
        return "http://www.w3.org/2000/09/xmldsig#rsa-sha1";
    }
    
    static {
        ms_identifier = new AlgorithmIdentifier(new DERObjectIdentifier("1.2.840.113549.1.1.5"));
        ms_AlgID = new AlgorithmIdentifier(X509ObjectIdentifiers.id_SHA1, null);
    }
}
