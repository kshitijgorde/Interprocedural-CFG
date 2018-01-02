// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.DEREncodableVector;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.Time;
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.DERInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.asn1.x509.TBSCertList;
import java.util.Date;
import java.math.BigInteger;

public class RevokedCertificate
{
    public static final Class[] CRL_ENTRY_EXTENSIONS;
    private static BigInteger ZERO;
    private static BigInteger ONE;
    private BigInteger m_serial;
    private Date m_revocationDate;
    private X509Extensions m_extensions;
    static /* synthetic */ Class class$anon$crypto$X509CertificateIssuer;
    
    public RevokedCertificate(final JAPCertificate japCertificate, final Date revocationDate, final X509Extensions extensions) {
        this.m_revocationDate = revocationDate;
        this.m_serial = getUniqueSerial(japCertificate);
        this.m_extensions = extensions;
    }
    
    protected RevokedCertificate(final TBSCertList.CRLEntry crlEntry) {
        this.m_serial = crlEntry.getUserCertificate().getPositiveValue();
        this.m_revocationDate = crlEntry.getRevocationDate().getDate();
        if (crlEntry.getExtensions() != null) {
            this.m_extensions = new X509Extensions(crlEntry.getExtensions());
        }
    }
    
    protected static BigInteger getUniqueSerial(final JAPCertificate japCertificate) {
        if (japCertificate.getSerialNumber().equals(RevokedCertificate.ZERO) || japCertificate.getSerialNumber().equals(RevokedCertificate.ONE)) {
            return createPseudoSerial(japCertificate.toByteArray());
        }
        return japCertificate.getSerialNumber();
    }
    
    private static BigInteger createPseudoSerial(final byte[] array) {
        final SHA1Digest sha1Digest = new SHA1Digest();
        final byte[] array2 = new byte[sha1Digest.getDigestSize()];
        sha1Digest.update(array, 0, array.length);
        sha1Digest.doFinal(array2, 0);
        return new BigInteger(array2).abs();
    }
    
    protected ASN1Sequence toASN1Sequence() {
        final ASN1EncodableVector asn1EncodableVector = new ASN1EncodableVector();
        asn1EncodableVector.add(new DERInteger(this.m_serial));
        asn1EncodableVector.add(new Time(this.m_revocationDate));
        if (this.m_extensions != null) {
            asn1EncodableVector.add(this.m_extensions.getBCX509Extensions());
        }
        return new DERSequence(asn1EncodableVector);
    }
    
    public BigInteger getSerialNumber() {
        return this.m_serial;
    }
    
    public X509DistinguishedName getCertificateIssuer() {
        if (this.m_extensions != null) {
            final X509CertificateIssuer x509CertificateIssuer = (X509CertificateIssuer)this.m_extensions.getExtension(X509CertificateIssuer.IDENTIFIER);
            if (x509CertificateIssuer != null) {
                return x509CertificateIssuer.getDistinguishedName();
            }
        }
        return null;
    }
    
    public Date getRevocationDate() {
        return this.m_revocationDate;
    }
    
    public X509Extensions getExtensions() {
        return this.m_extensions;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        CRL_ENTRY_EXTENSIONS = new Class[] { (RevokedCertificate.class$anon$crypto$X509CertificateIssuer == null) ? (RevokedCertificate.class$anon$crypto$X509CertificateIssuer = class$("anon.crypto.X509CertificateIssuer")) : RevokedCertificate.class$anon$crypto$X509CertificateIssuer };
        RevokedCertificate.ZERO = BigInteger.valueOf(0L);
        RevokedCertificate.ONE = BigInteger.valueOf(1L);
    }
}
