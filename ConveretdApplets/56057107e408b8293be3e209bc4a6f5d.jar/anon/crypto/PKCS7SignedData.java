// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.DERTaggedObject;
import java.security.SignatureException;
import java.security.InvalidKeyException;
import org.bouncycastle.asn1.x509.X509Name;
import java.math.BigInteger;
import org.bouncycastle.asn1.pkcs.IssuerAndSerialNumber;
import java.util.Enumeration;
import org.bouncycastle.asn1.DERObject;
import java.security.NoSuchAlgorithmException;
import org.bouncycastle.asn1.pkcs.SignerInfo;
import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.x509.X509CertificateStructure;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.pkcs.SignedData;
import org.bouncycastle.asn1.pkcs.ContentInfo;
import org.bouncycastle.asn1.ASN1Sequence;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1InputStream;
import java.io.ByteArrayInputStream;
import java.util.Vector;
import java.util.Hashtable;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;

public class PKCS7SignedData implements PKCSObjectIdentifiers
{
    private int version;
    private int signerversion;
    private Hashtable digestalgos;
    private Vector certs;
    private JAPCertificate signCert;
    private byte[] digest;
    private String digestAlgorithm;
    private String digestEncryptionAlgorithm;
    private final String ID_MD5 = "1.2.840.113549.2.5";
    private final String ID_MD2 = "1.2.840.113549.2.2";
    private final String ID_SHA1 = "1.3.14.3.2.26";
    private final String ID_RSA = "1.2.840.113549.1.1.1";
    private final String ID_DSA = "1.2.840.10040.4.1";
    
    public PKCS7SignedData(final byte[] array) throws SecurityException, InvalidKeyException, NoSuchAlgorithmException {
        final ASN1InputStream asn1InputStream = new ASN1InputStream(new ByteArrayInputStream(array));
        DERObject object;
        try {
            object = asn1InputStream.readObject();
        }
        catch (IOException ex) {
            throw new SecurityException("can't decode PKCS7SignedData object");
        }
        if (!(object instanceof ASN1Sequence)) {
            throw new SecurityException("Not a valid PKCS#7 object - not a sequence");
        }
        final ContentInfo instance = ContentInfo.getInstance(object);
        if (!instance.getContentType().equals(PKCSObjectIdentifiers.signedData)) {
            throw new SecurityException("Not a valid PKCS#7 signed-data object - wrong header " + instance.getContentType().getId());
        }
        final SignedData instance2 = SignedData.getInstance(instance.getContent());
        this.certs = new Vector();
        if (instance2.getCertificates() != null) {
            final Enumeration objects = ASN1Set.getInstance(instance2.getCertificates()).getObjects();
            while (objects.hasMoreElements()) {
                JAPCertificate instance3 = null;
                try {
                    instance3 = JAPCertificate.getInstance(X509CertificateStructure.getInstance(objects.nextElement()));
                }
                catch (Exception ex2) {}
                if (instance3 != null) {
                    this.certs.addElement(instance3);
                }
            }
        }
        this.version = instance2.getVersion().getValue().intValue();
        this.digestalgos = new Hashtable();
        final Enumeration objects2 = instance2.getDigestAlgorithms().getObjects();
        while (objects2.hasMoreElements()) {
            final DERObjectIdentifier derObjectIdentifier = (DERObjectIdentifier)objects2.nextElement().getObjectAt(0);
            this.digestalgos.put(derObjectIdentifier.getId(), derObjectIdentifier.getId());
        }
        final ASN1Set signerInfos = instance2.getSignerInfos();
        if (signerInfos.size() != 1) {
            throw new SecurityException("This PKCS#7 object has multiple SignerInfos - only one is supported at this time");
        }
        final SignerInfo instance4 = SignerInfo.getInstance(signerInfos.getObjectAt(0));
        this.signerversion = instance4.getVersion().getValue().intValue();
        final IssuerAndSerialNumber issuerAndSerialNumber = instance4.getIssuerAndSerialNumber();
        final BigInteger value = issuerAndSerialNumber.getCertificateSerialNumber().getValue();
        final X509Name name = issuerAndSerialNumber.getName();
        final Enumeration<JAPCertificate> elements = this.certs.elements();
        while (elements.hasMoreElements()) {
            final JAPCertificate signCert = elements.nextElement();
            if (value.equals(signCert.getSerialNumber()) && name.equals(signCert.getIssuer().getX509Name())) {
                this.signCert = signCert;
                break;
            }
        }
        if (this.signCert == null) {
            throw new SecurityException("Can't find signing certificate with serial " + value.toString(16));
        }
        this.digestAlgorithm = instance4.getDigestAlgorithm().getObjectId().getId();
        this.digest = instance4.getEncryptedDigest().getOctets();
        this.digestEncryptionAlgorithm = instance4.getDigestEncryptionAlgorithm().getObjectId().getId();
        if (!this.getDigestAlgorithm().equalsIgnoreCase("sha1withdsa")) {
            throw new NoSuchAlgorithmException("Signature Algorithm unknown!");
        }
    }
    
    public String getDigestAlgorithm() {
        String digestAlgorithm = this.digestAlgorithm;
        String digestEncryptionAlgorithm = this.digestEncryptionAlgorithm;
        if (this.digestAlgorithm.equals("1.2.840.113549.2.5")) {
            digestAlgorithm = "MD5";
        }
        else if (this.digestAlgorithm.equals("1.2.840.113549.2.2")) {
            digestAlgorithm = "MD2";
        }
        else if (this.digestAlgorithm.equals("1.3.14.3.2.26")) {
            digestAlgorithm = "SHA1";
        }
        if (this.digestEncryptionAlgorithm.equals("1.2.840.113549.1.1.1")) {
            digestEncryptionAlgorithm = "RSA";
        }
        else if (this.digestEncryptionAlgorithm.equals("1.2.840.10040.4.1")) {
            digestEncryptionAlgorithm = "DSA";
        }
        return digestAlgorithm + "with" + digestEncryptionAlgorithm;
    }
    
    public JAPCertificate[] getCertificates() {
        final JAPCertificate[] array = new JAPCertificate[this.certs.size()];
        this.certs.copyInto(array);
        return array;
    }
    
    public JAPCertificate getSigningCertificate() {
        return this.signCert;
    }
    
    public int getVersion() {
        return this.version;
    }
    
    public int getSigningInfoVersion() {
        return this.signerversion;
    }
    
    public boolean verify(final byte[] array) throws SignatureException {
        return ByteSignature.verify(array, this.digest, this.signCert.getPublicKey());
    }
    
    private DERObject getIssuer(final byte[] array) {
        try {
            final ASN1Sequence asn1Sequence = (ASN1Sequence)new ASN1InputStream(new ByteArrayInputStream(array)).readObject();
            return (DERObject)asn1Sequence.getObjectAt((asn1Sequence.getObjectAt(0) instanceof DERTaggedObject) ? 3 : 2);
        }
        catch (IOException ex) {
            throw new Error("IOException reading from ByteArray: " + ex);
        }
    }
}
