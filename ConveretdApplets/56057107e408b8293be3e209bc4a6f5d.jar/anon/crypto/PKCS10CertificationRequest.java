// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.DEROutputStream;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.GeneralDigest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import java.math.BigInteger;
import anon.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import anon.util.ResourceLoader;
import java.io.InputStream;

public final class PKCS10CertificationRequest
{
    public static final String FILE_EXTENSION = ".p10";
    private static final String BASE64_TAG = "CERTIFICATE REQUEST";
    private static final String BASE64_ALTERNATIVE_TAG = "NEW CERTIFICATE REQUEST";
    private CertificationRequest m_certificationRequest;
    private String m_sha1Fingerprint;
    private String m_md5Fingerprint;
    
    public PKCS10CertificationRequest(final InputStream inputStream) throws IOException {
        this(ResourceLoader.getStreamAsBytes(inputStream));
    }
    
    public PKCS10CertificationRequest(final byte[] array) {
        this.m_certificationRequest = new CertificationRequest(JAPCertificate.toASN1Sequence(array, null));
        this.createFingerprints();
    }
    
    public PKCS10CertificationRequest(final X509DistinguishedName x509DistinguishedName, final AsymmetricCryptoKeyPair asymmetricCryptoKeyPair, final X509Extensions x509Extensions) {
        this.m_certificationRequest = new CertificationRequest(new CertificationRequestInfo(x509DistinguishedName, asymmetricCryptoKeyPair.getPublic(), x509Extensions), asymmetricCryptoKeyPair);
        this.createFingerprints();
    }
    
    public PKCS10CertificationRequest(final PKCS12 pkcs12) {
        this(pkcs12.getSubject(), pkcs12.getKeyPair(), pkcs12.getExtensions());
    }
    
    public void toOutputStream(final OutputStream outputStream, final boolean b) throws IOException {
        outputStream.write(this.toByteArray(b));
    }
    
    public byte[] toByteArray(final boolean b) {
        if (b) {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(Base64.createBeginTag("CERTIFICATE REQUEST").getBytes());
                byteArrayOutputStream.write(Base64.encode(this.getEncoded(), true).getBytes());
                byteArrayOutputStream.write(Base64.createEndTag("CERTIFICATE REQUEST").getBytes());
            }
            catch (IOException ex) {
                throw new RuntimeException("Could not write encoded bytes to byte array: " + ex.getMessage());
            }
            return byteArrayOutputStream.toByteArray();
        }
        return this.getEncoded();
    }
    
    public boolean verify() {
        return this.m_certificationRequest.verify();
    }
    
    public JAPCertificate createX509Certificate(final PKCS12 pkcs12, final Validity validity, final X509Extensions x509Extensions, final BigInteger bigInteger) {
        return JAPCertificate.getInstance(this.getX509DistinguishedName(), pkcs12.getSubject(), pkcs12.getPrivateKey(), pkcs12.getPublicKey(), validity, x509Extensions, bigInteger);
    }
    
    public IMyPublicKey getPublicKey() {
        return this.m_certificationRequest.getPublicKey();
    }
    
    public String getSHA1Fingerprint() {
        return this.m_sha1Fingerprint;
    }
    
    public String getMD5Fingerprint() {
        return this.m_md5Fingerprint;
    }
    
    public X509DistinguishedName getX509DistinguishedName() {
        return this.m_certificationRequest.getCertificationRequestInfo().getX509DistinguishedName();
    }
    
    public X509Extensions getExtensions() {
        return this.m_certificationRequest.getCertificationRequestInfo().getExtensions();
    }
    
    private void createFingerprints() {
        final byte[] byteArray = this.toByteArray(false);
        this.m_sha1Fingerprint = JAPCertificate.createFingerprint(new SHA1Digest(), byteArray);
        this.m_md5Fingerprint = JAPCertificate.createFingerprint(new MD5Digest(), byteArray);
    }
    
    private byte[] getEncoded() {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DEROutputStream derOutputStream = new DEROutputStream(byteArrayOutputStream);
        try {
            derOutputStream.writeObject(this.m_certificationRequest);
        }
        catch (IOException ex) {
            throw new RuntimeException(ex.toString());
        }
        return byteArrayOutputStream.toByteArray();
    }
}
