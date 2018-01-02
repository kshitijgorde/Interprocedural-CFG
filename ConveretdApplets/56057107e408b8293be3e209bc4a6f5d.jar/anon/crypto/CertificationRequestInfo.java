// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.DERObject;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEREncodableVector;
import org.bouncycastle.asn1.DERInteger;
import org.bouncycastle.asn1.DERSequence;

final class CertificationRequestInfo extends DERSequence
{
    private IMyPublicKey m_publicKey;
    private X509DistinguishedName m_subject;
    private X509Extensions m_extensions;
    
    public CertificationRequestInfo(final X509DistinguishedName subject, final IMyPublicKey myPublicKey, final X509Extensions extensions) {
        super(createRequestInfo(new DERInteger(0), subject.getX509Name(), myPublicKey.getAsSubjectPublicKeyInfo(), extensions));
        this.m_subject = subject;
        try {
            this.m_publicKey = AsymmetricCryptoKeyPair.createPublicKey(myPublicKey.getAsSubjectPublicKeyInfo());
        }
        catch (Exception ex) {
            throw new RuntimeException("Could not create public key: " + ex.getMessage());
        }
        this.m_extensions = extensions;
    }
    
    CertificationRequestInfo(final ASN1Sequence asn1Sequence) {
        super(createRequestInfo(asn1Sequence));
        try {
            this.m_publicKey = AsymmetricCryptoKeyPair.createPublicKey(SubjectPublicKeyInfo.getInstance(asn1Sequence.getObjectAt(2)));
        }
        catch (Exception ex) {
            throw new RuntimeException("Could not create public key: " + ex.getMessage());
        }
        this.m_subject = new X509DistinguishedName(X509Name.getInstance(this.getObjectAt(1)));
        final DERObject derObject = ((DERTaggedObject)this.getObjectAt(3)).getDERObject();
        if (derObject instanceof DERSet) {
            this.m_extensions = new X509Extensions((DERSet)derObject);
        }
        else {
            this.m_extensions = new X509Extensions(new DERSet());
        }
    }
    
    public IMyPublicKey getPublicKey() {
        return this.m_publicKey;
    }
    
    public X509Extensions getExtensions() {
        return this.m_extensions;
    }
    
    public X509DistinguishedName getX509DistinguishedName() {
        return this.m_subject;
    }
    
    private static ASN1EncodableVector createRequestInfo(final DERInteger derInteger, final X509Name x509Name, final SubjectPublicKeyInfo subjectPublicKeyInfo, final X509Extensions x509Extensions) {
        final ASN1EncodableVector asn1EncodableVector = new ASN1EncodableVector();
        asn1EncodableVector.add(derInteger);
        asn1EncodableVector.add(x509Name);
        asn1EncodableVector.add(subjectPublicKeyInfo);
        if (x509Extensions != null) {
            asn1EncodableVector.add(new DERTaggedObject(false, 0, x509Extensions.getBCExtensions()));
        }
        return asn1EncodableVector;
    }
    
    private static ASN1EncodableVector createRequestInfo(final ASN1Sequence asn1Sequence) {
        final DERInteger derInteger = (DERInteger)asn1Sequence.getObjectAt(0);
        final X509Name instance = X509Name.getInstance(asn1Sequence.getObjectAt(1));
        final SubjectPublicKeyInfo instance2 = SubjectPublicKeyInfo.getInstance(asn1Sequence.getObjectAt(2));
        X509Extensions x509Extensions = null;
        if (asn1Sequence.size() > 3) {
            x509Extensions = new X509Extensions((DERSet)ASN1Set.getInstance((ASN1TaggedObject)asn1Sequence.getObjectAt(3), false));
        }
        return createRequestInfo(derInteger, instance, instance2, x509Extensions);
    }
}
