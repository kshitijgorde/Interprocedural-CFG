// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.DEROutputStream;
import java.io.ByteArrayOutputStream;
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEREncodableVector;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;

final class CertificationRequest extends DERSequence
{
    private CertificationRequestInfo m_certificationRequestInfo;
    private DERBitString m_signature;
    
    public CertificationRequest(final CertificationRequestInfo certificationRequestInfo, final AsymmetricCryptoKeyPair asymmetricCryptoKeyPair) {
        super(createRequest(certificationRequestInfo, asymmetricCryptoKeyPair.getPrivate().getSignatureAlgorithm().getIdentifier(), new DERBitString(ByteSignature.sign(DERtoBytes(certificationRequestInfo), asymmetricCryptoKeyPair))));
        this.m_certificationRequestInfo = certificationRequestInfo;
        this.m_signature = new DERBitString(ByteSignature.sign(DERtoBytes(certificationRequestInfo), asymmetricCryptoKeyPair));
    }
    
    CertificationRequest(final ASN1Sequence asn1Sequence) {
        super(createRequest((DERSequence)asn1Sequence.getObjectAt(0), AlgorithmIdentifier.getInstance(asn1Sequence.getObjectAt(1)), (DERBitString)asn1Sequence.getObjectAt(2)));
        this.m_certificationRequestInfo = new CertificationRequestInfo((ASN1Sequence)asn1Sequence.getObjectAt(0));
        this.m_signature = (DERBitString)asn1Sequence.getObjectAt(2);
    }
    
    public IMyPublicKey getPublicKey() {
        return this.m_certificationRequestInfo.getPublicKey();
    }
    
    public CertificationRequestInfo getCertificationRequestInfo() {
        return this.m_certificationRequestInfo;
    }
    
    public boolean verify() {
        return ByteSignature.verify(DERtoBytes(this.m_certificationRequestInfo), this.m_signature.getBytes(), this.getPublicKey());
    }
    
    private static ASN1EncodableVector createRequest(final DERSequence derSequence, final AlgorithmIdentifier algorithmIdentifier, final DERBitString derBitString) {
        final ASN1EncodableVector asn1EncodableVector = new ASN1EncodableVector();
        asn1EncodableVector.add(derSequence);
        asn1EncodableVector.add(algorithmIdentifier);
        asn1EncodableVector.add(derBitString);
        return asn1EncodableVector;
    }
    
    private static byte[] DERtoBytes(final Object o) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new DEROutputStream(byteArrayOutputStream).writeObject(o);
        }
        catch (IOException ex) {
            throw new RuntimeException("Could not write DER data to bytes.");
        }
        return byteArrayOutputStream.toByteArray();
    }
}
