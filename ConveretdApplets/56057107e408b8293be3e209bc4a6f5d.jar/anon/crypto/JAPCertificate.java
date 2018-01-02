// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import org.bouncycastle.asn1.DEREncodableVector;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.asn1.x509.TBSCertificateStructure;
import org.bouncycastle.asn1.DERInteger;
import org.bouncycastle.asn1.DERUTCTime;
import java.util.Date;
import org.bouncycastle.asn1.x509.V3TBSCertificateGenerator;
import java.security.SecureRandom;
import org.bouncycastle.asn1.ASN1InputStream;
import java.io.ByteArrayInputStream;
import java.util.StringTokenizer;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.w3c.dom.Document;
import java.io.OutputStream;
import org.bouncycastle.asn1.DEROutputStream;
import java.io.ByteArrayOutputStream;
import java.util.Enumeration;
import org.bouncycastle.util.encoders.Hex;
import java.util.Vector;
import java.math.BigInteger;
import java.io.FileInputStream;
import java.io.File;
import anon.util.Base64;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.io.IOException;
import java.io.InputStream;
import anon.util.IResourceInstantiator;
import anon.util.ResourceLoader;
import java.util.Hashtable;
import org.bouncycastle.asn1.pkcs.SignedData;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.DERObjectIdentifier;
import java.util.Calendar;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.GeneralDigest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import logging.LogHolder;
import logging.LogType;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.X509CertificateStructure;
import anon.util.IXMLEncodable;

public final class JAPCertificate implements IXMLEncodable, Cloneable, ICertificate
{
    public static final int CERTIFICATE_TYPE_ROOT_MIX = 1;
    public static final int CERTIFICATE_TYPE_ROOT_INFOSERVICE = 5;
    public static final int CERTIFICATE_TYPE_ROOT_UPDATE = 6;
    public static final int CERTIFICATE_TYPE_ROOT_PAYMENT = 8;
    public static final int CERTIFICATE_TYPE_MIX = 2;
    public static final int CERTIFICATE_TYPE_INFOSERVICE = 3;
    public static final int CERTIFICATE_TYPE_UPDATE = 4;
    public static final int CERTIFICATE_TYPE_PAYMENT = 7;
    public static final int CERTIFICATE_TYPE_TERMS_AND_CONDITIONS = 9;
    public static final int CERTIFICATE_TYPE_ROOT_TERMS_AND_CONDITIONS = 10;
    public static final int CERTIFICATE_TYPE_ROOT = 0;
    public static final String XML_ELEMENT_NAME = "X509Certificate";
    public static final String XML_ELEMENT_CONTAINER_NAME = "X509Data";
    private static final String BASE64_TAG = "CERTIFICATE";
    private static final String BASE64_ALTERNATIVE_TAG = "X509 CERTIFICATE";
    private static final String IDENTIFIER_DSA_WITH_SHA1 = "1.2.840.10040.4.3";
    private static final String DSA_WITH_SHA1 = "dsaWithSHA1";
    private static final String IDENTIFIER_MD2_WITH_RSA_ENCRYPTION = "1.2.840.113549.1.1.2";
    private static final String MD2_WITH_RSA_ENCRYPTION = "md2WithRSAEncryption";
    private static final String IDENTIFIER_MD5_WITH_RSA_ENCRYPTION = "1.2.840.113549.1.1.4";
    private static final String MD5_WITH_RSA_ENCRYPTION = "md5WithRSAEncryption";
    private static final String IDENTIFIER_SHA1_WITH_RSA_ENCRYPTION = "1.2.840.113549.1.1.5";
    private static final String SHA1_WITH_RSA_ENCRYPTION = "sha-1WithRSAEncryption";
    private static final String IDENTIFIER_ECDSA_WITH_SHA1 = "1.2.840.10045.4.1";
    private static final String ECDSA_WITH_SHA1 = "ecdsa-with-SHA1";
    private static IMyPrivateKey ms_dummyPrivateKey;
    private X509CertificateStructure m_bcCertificate;
    private X509DistinguishedName m_subject;
    private X509DistinguishedName m_issuer;
    private X509Extensions m_extensions;
    private X509SubjectKeyIdentifier m_subjectKeyIdentifier;
    private IMyPublicKey m_PubKey;
    private String m_id;
    private String m_sha1Fingerprint;
    private String m_md5Fingerprint;
    private Validity m_validity;
    
    private JAPCertificate(final X509CertificateStructure x509CertificateStructure) throws IllegalArgumentException {
        this.m_bcCertificate = new X509CertificateStructure(ASN1Sequence.getInstance(new DERTaggedObject(true, 3, x509CertificateStructure), true));
        try {
            this.m_PubKey = AsymmetricCryptoKeyPair.createPublicKey(x509CertificateStructure.getSubjectPublicKeyInfo());
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.MISC, ex);
            throw new IllegalArgumentException("Certificate structure contains invalid public key! " + ex);
        }
        final byte[] byteArray = this.toByteArray();
        this.m_sha1Fingerprint = createFingerprint(new SHA1Digest(), byteArray);
        this.m_md5Fingerprint = createFingerprint(new MD5Digest(), byteArray);
        final Calendar instance = Calendar.getInstance();
        instance.setTime(this.m_bcCertificate.getStartDate().getDate());
        final Calendar instance2 = Calendar.getInstance();
        instance2.setTime(this.m_bcCertificate.getEndDate().getDate());
        this.m_validity = new Validity(instance, instance2);
        this.m_subject = new X509DistinguishedName(this.m_bcCertificate.getSubject());
        this.m_issuer = new X509DistinguishedName(this.m_bcCertificate.getIssuer());
        this.m_extensions = new X509Extensions(this.m_bcCertificate.getTBSCertificate().getExtensions());
        this.m_id = this.m_sha1Fingerprint + this.m_validity.getValidFrom() + this.m_validity.getValidTo();
        this.m_subjectKeyIdentifier = (X509SubjectKeyIdentifier)this.m_extensions.getExtension(X509SubjectKeyIdentifier.IDENTIFIER);
        if (this.m_subjectKeyIdentifier == null) {
            this.m_subjectKeyIdentifier = new X509SubjectKeyIdentifier(this.getPublicKey());
        }
    }
    
    public static JAPCertificate getInstance(final X509CertificateStructure x509CertificateStructure) {
        JAPCertificate japCertificate;
        try {
            japCertificate = new JAPCertificate(x509CertificateStructure);
        }
        catch (IllegalArgumentException ex) {
            return null;
        }
        return japCertificate;
    }
    
    public static JAPCertificate getInstance(final JAPCertificate japCertificate) {
        if (japCertificate == null) {
            return null;
        }
        return getInstance(japCertificate.m_bcCertificate);
    }
    
    public static JAPCertificate getInstance(final byte[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        try {
            final ASN1Sequence asn1Sequence = toASN1Sequence(array, "X509Certificate");
            if (asn1Sequence.size() > 1 && asn1Sequence.getObjectAt(1) instanceof DERObjectIdentifier && asn1Sequence.getObjectAt(0).equals(PKCSObjectIdentifiers.signedData)) {
                return getInstance(X509CertificateStructure.getInstance(new SignedData(ASN1Sequence.getInstance((ASN1TaggedObject)asn1Sequence.getObjectAt(1), true)).getCertificates().getObjectAt(0)));
            }
            return getInstance(new X509CertificateStructure(asn1Sequence));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static Hashtable getInstance(final String s, final boolean b, final String s2) {
        try {
            return ResourceLoader.loadResources(s, new X509CertificateInstantiator(s2), b);
        }
        catch (Exception ex) {
            LogHolder.log(3, LogType.MISC, ex);
            return new Hashtable();
        }
    }
    
    public static Hashtable getInstance(final String s, final boolean b) {
        return getInstance(s, b, null);
    }
    
    public static JAPCertificate getInstance(final InputStream inputStream) {
        byte[] streamAsBytes;
        try {
            streamAsBytes = ResourceLoader.getStreamAsBytes(inputStream);
        }
        catch (IOException ex) {
            return null;
        }
        return getInstance(streamAsBytes);
    }
    
    public static JAPCertificate getInstance(final Node node) {
        try {
            if (!node.getNodeName().equals("X509Certificate")) {
                return null;
            }
            return getInstance(Base64.decode(XMLUtil.parseValue(node, null)));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static JAPCertificate getInstance(final File file) {
        if (file == null) {
            return null;
        }
        byte[] array;
        try {
            array = new byte[(int)file.length()];
            final FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(array);
            fileInputStream.close();
        }
        catch (Exception ex) {
            return null;
        }
        return getInstance(array);
    }
    
    public static JAPCertificate getInstance(final String s) {
        try {
            return getInstance(s.getBytes());
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static final JAPCertificate getInstance(final IMyPublicKey myPublicKey, final Calendar calendar) {
        return getInstance(new X509DistinguishedName("CN=void"), new X509DistinguishedName("CN=void"), getDummyPrivateKey(), myPublicKey, new Validity(calendar, -1), null, new BigInteger("1"));
    }
    
    public static JAPCertificate getInstance(final X509DistinguishedName x509DistinguishedName, final AsymmetricCryptoKeyPair asymmetricCryptoKeyPair, final Validity validity) {
        return getInstance(x509DistinguishedName, asymmetricCryptoKeyPair, validity, null);
    }
    
    public static JAPCertificate getInstance(final X509DistinguishedName x509DistinguishedName, final AsymmetricCryptoKeyPair asymmetricCryptoKeyPair, final Validity validity, final X509Extensions x509Extensions) {
        return getInstance(x509DistinguishedName, x509DistinguishedName, asymmetricCryptoKeyPair.getPrivate(), asymmetricCryptoKeyPair.getPublic(), validity, x509Extensions, new BigInteger("1"));
    }
    
    public static String calculateXORofSKIs(final Vector vector) {
        if (vector == null) {
            return null;
        }
        final String s;
        synchronized (vector) {
            if (vector.size() == 0) {
                return null;
            }
            final byte[] array = new byte[20];
            final Enumeration<JAPCertificate> elements = vector.elements();
            while (elements.hasMoreElements()) {
                final byte[] rawSubjectKeyIdentifier = elements.nextElement().getRawSubjectKeyIdentifier();
                if (rawSubjectKeyIdentifier == null) {
                    continue;
                }
                for (int i = 0; i < array.length; ++i) {
                    array[i] ^= rawSubjectKeyIdentifier[i];
                }
            }
            s = new String(Hex.encode(array));
        }
        return s.toUpperCase();
    }
    
    public boolean equals(final Object o) {
        return this == o || (o != null && o instanceof JAPCertificate && this.getId().equals(((JAPCertificate)o).getId()));
    }
    
    public int hashCode() {
        return this.getId().hashCode();
    }
    
    public Object clone() {
        return getInstance(this.m_bcCertificate);
    }
    
    public String getId() {
        return this.m_id;
    }
    
    public IMyPublicKey getPublicKey() {
        return this.m_PubKey;
    }
    
    public X509Extensions getExtensions() {
        return this.m_extensions;
    }
    
    public String getSubjectKeyIdentifier() {
        return this.m_subjectKeyIdentifier.getValue();
    }
    
    public String getSubjectKeyIdentifierConcatenated() {
        return this.m_subjectKeyIdentifier.getValueWithoutColon();
    }
    
    public byte[] getRawSubjectKeyIdentifier() {
        final String valueWithoutColon = this.m_subjectKeyIdentifier.getValueWithoutColon();
        if (valueWithoutColon == null) {
            return null;
        }
        return Hex.decode(valueWithoutColon);
    }
    
    public String getSignatureAlgorithmName() {
        final String id = this.m_bcCertificate.getSignatureAlgorithm().getObjectId().getId();
        if (id.equals("1.2.840.10040.4.3")) {
            return "dsaWithSHA1";
        }
        if (id.equals("1.2.840.113549.1.1.5")) {
            return "sha-1WithRSAEncryption";
        }
        if (id.equals("1.2.840.113549.1.1.4")) {
            return "md5WithRSAEncryption";
        }
        if (id.equals("1.2.840.113549.1.1.2")) {
            return "md2WithRSAEncryption";
        }
        if (id.equals("1.2.840.10045.4.1")) {
            return "ecdsa-with-SHA1";
        }
        return id;
    }
    
    public BigInteger getSerialNumber() {
        return this.m_bcCertificate.getSerialNumber().getPositiveValue();
    }
    
    public X509DistinguishedName getIssuer() {
        return this.m_issuer;
    }
    
    public X509DistinguishedName getSubject() {
        return this.m_subject;
    }
    
    public String getAnyEmailAddress() {
        try {
            final X509DistinguishedName subject = this.getSubject();
            final String e_EmailAddress = subject.getE_EmailAddress();
            if (e_EmailAddress != null) {
                return e_EmailAddress;
            }
            final String emailAddress = subject.getEmailAddress();
            if (emailAddress != null) {
                return emailAddress;
            }
            final Vector extensions = this.getExtensions().getExtensions();
            for (int i = 0; i < extensions.size(); ++i) {
                final AbstractX509Extension abstractX509Extension = extensions.elementAt(i);
                if (abstractX509Extension instanceof X509SubjectAlternativeName) {
                    final X509SubjectAlternativeName x509SubjectAlternativeName = (X509SubjectAlternativeName)abstractX509Extension;
                    final Vector tags = x509SubjectAlternativeName.getTags();
                    for (int j = 0; j < tags.size(); ++j) {
                        final Integer n = tags.elementAt(j);
                        if (n.equals(AbstractX509AlternativeName.TAG_EMAIL)) {
                            final String s = x509SubjectAlternativeName.getValues().elementAt(n);
                            if (s != null) {
                                return s;
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            return null;
        }
        return null;
    }
    
    public JAPCertificate getX509Certificate() {
        return this;
    }
    
    public String getSHA1Fingerprint() {
        return this.m_sha1Fingerprint;
    }
    
    public String getMD5Fingerprint() {
        return this.m_md5Fingerprint;
    }
    
    public byte[] toByteArray() {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new DEROutputStream(byteArrayOutputStream).writeObject(this.m_bcCertificate);
        }
        catch (IOException ex) {}
        return byteArrayOutputStream.toByteArray();
    }
    
    public byte[] toByteArray(final boolean b) {
        if (b) {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(Base64.createBeginTag("CERTIFICATE").getBytes());
                byteArrayOutputStream.write(Base64.encode(this.toByteArray(), true).getBytes());
                byteArrayOutputStream.write(Base64.createEndTag("CERTIFICATE").getBytes());
            }
            catch (IOException ex) {}
            return byteArrayOutputStream.toByteArray();
        }
        return this.toByteArray();
    }
    
    public void store(final OutputStream outputStream) throws IOException {
        new DEROutputStream(outputStream).writeObject(this.m_bcCertificate);
    }
    
    public void store(final OutputStream outputStream, final boolean b) throws IOException {
        outputStream.write(this.toByteArray(b));
    }
    
    public Validity getValidity() {
        return this.m_validity;
    }
    
    public synchronized boolean verify(final Vector vector) {
        return this.verify(vector.elements());
    }
    
    public synchronized boolean verify(final Hashtable hashtable) {
        return this.verify(hashtable.elements());
    }
    
    public synchronized boolean verify(final Enumeration enumeration) {
        if (enumeration == null) {
            return false;
        }
        synchronized (enumeration) {
            while (enumeration.hasMoreElements()) {
                if (this.verify(enumeration.nextElement())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public synchronized boolean verify(final JAPCertificate japCertificate) {
        return japCertificate != null && this.verify(japCertificate.getPublicKey());
    }
    
    public synchronized boolean verify(final IMyPublicKey myPublicKey) {
        if (myPublicKey == null) {
            return false;
        }
        if (myPublicKey.getSignatureAlgorithm().getIdentifier().equals(this.m_bcCertificate.getSignatureAlgorithm())) {
            try {
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                new DEROutputStream(byteArrayOutputStream).writeObject(this.m_bcCertificate.getTBSCertificate());
                return ByteSignature.verify(byteArrayOutputStream.toByteArray(), this.m_bcCertificate.getSignature().getBytes(), myPublicKey);
            }
            catch (IOException ex) {}
        }
        return false;
    }
    
    public JAPCertificate sign(final PKCS12 pkcs12) {
        return getInstance(new X509CertificateGenerator(this.m_bcCertificate.getTBSCertificate()).sign(pkcs12));
    }
    
    public JAPCertificate sign(final PKCS12 pkcs12, final Validity validity, final X509Extensions x509Extensions, final BigInteger bigInteger) {
        return getInstance(new X509DistinguishedName(this.m_bcCertificate.getSubject()), pkcs12.getSubject(), pkcs12.getPrivateKey(), this.getPublicKey(), validity, x509Extensions, bigInteger);
    }
    
    public static JAPCertificate getInstance(final X509DistinguishedName x509DistinguishedName, final X509DistinguishedName x509DistinguishedName2, final IMyPrivateKey myPrivateKey, final IMyPublicKey myPublicKey, final Validity validity, final X509Extensions x509Extensions, final BigInteger bigInteger) {
        return getInstance(new X509CertificateGenerator(x509DistinguishedName, validity.getValidFrom(), validity.getValidTo(), myPublicKey, x509Extensions, bigInteger).sign(x509DistinguishedName2.getX509Name(), myPrivateKey));
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("X509Certificate");
        element.setAttribute("xml:space", "preserve");
        XMLUtil.setValue(element, Base64.encode(this.toByteArray(), true));
        return element;
    }
    
    DEREncodable getBouncyCastleCertificate() {
        return this.m_bcCertificate;
    }
    
    SubjectPublicKeyInfo getBouncyCastleSubjectPublicKeyInfo() {
        return this.m_bcCertificate.getSubjectPublicKeyInfo();
    }
    
    static ASN1Sequence toASN1Sequence(final byte[] array, String s) {
        InputStream inputStream = null;
        if (array == null || array.length == 0) {
            return null;
        }
        try {
            if (array[0] != 48) {
                final String s2 = new String(array);
                final StringTokenizer stringTokenizer = new StringTokenizer(s2);
                final StringBuffer sb = new StringBuffer();
                int n = 0;
                if (s != null && (s.trim().length() == 0 || new StringTokenizer(s).countTokens() > 1)) {
                    s = null;
                }
            Label_0260:
                while (stringTokenizer.hasMoreTokens()) {
                    String s3 = stringTokenizer.nextToken();
                    if (s3.startsWith("-----BEGIN ".trim())) {
                        while (!s3.endsWith("-----")) {
                            if (!stringTokenizer.hasMoreTokens()) {
                                continue Label_0260;
                            }
                            if ((s3 = stringTokenizer.nextToken()) == null) {
                                continue Label_0260;
                            }
                        }
                        break;
                    }
                    final int index;
                    if (s == null || (index = s3.indexOf("<" + s)) < 0) {
                        continue;
                    }
                    if (index >= s2.indexOf(">")) {
                        continue;
                    }
                    final int index2 = s2.indexOf("</" + s + ">");
                    if (index2 >= 0) {
                        n = 1;
                        sb.append(s2.substring(s2.indexOf(">") + 1, index2));
                        break;
                    }
                }
                if (n == 0) {
                    if (!stringTokenizer.hasMoreTokens()) {
                        throw new Exception();
                    }
                    while (stringTokenizer.hasMoreTokens()) {
                        String s4 = stringTokenizer.nextToken();
                        Label_0344: {
                            if (s4.startsWith("-----END ".trim())) {
                                while (!s4.endsWith("-----")) {
                                    if (!stringTokenizer.hasMoreTokens() || (s4 = stringTokenizer.nextToken()) == null) {
                                        break Label_0344;
                                    }
                                }
                                n = 1;
                                break;
                            }
                        }
                        sb.append(s4);
                    }
                }
                if (n == 0) {
                    throw new Exception();
                }
                inputStream = new ByteArrayInputStream(Base64.decode(sb.toString()));
            }
            if (inputStream == null && array[1] == 128) {
                return (ASN1Sequence)new ASN1InputStream(new ByteArrayInputStream(array)).readObject();
            }
            if (inputStream == null) {
                inputStream = new ByteArrayInputStream(array);
            }
            return (ASN1Sequence)new ASN1InputStream(inputStream).readObject();
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Byte array is no valid ASN1 sequence data!");
        }
    }
    
    protected static String createFingerprint(final GeneralDigest generalDigest, final byte[] array) {
        final byte[] array2 = new byte[generalDigest.getDigestSize()];
        generalDigest.update(array, 0, array.length);
        generalDigest.doFinal(array2, 0);
        return ByteSignature.toHexString(array2);
    }
    
    private static IMyPrivateKey getDummyPrivateKey() {
        if (JAPCertificate.ms_dummyPrivateKey == null) {
            final SecureRandom secureRandom = new SecureRandom();
            secureRandom.setSeed(58921787L);
            JAPCertificate.ms_dummyPrivateKey = DSAKeyPair.getInstance(secureRandom, 256, 100).getPrivate();
        }
        return JAPCertificate.ms_dummyPrivateKey;
    }
    
    public boolean isSelfSigned() {
        return this.verify(this.getPublicKey());
    }
    
    public boolean isRevoked() {
        return RevokedCertifcateStore.getInstance().isCertificateRevoked(this);
    }
    
    private static final class X509CertificateGenerator extends V3TBSCertificateGenerator
    {
        public X509CertificateGenerator(final X509DistinguishedName x509DistinguishedName, final Date date, final Date date2, final IMyPublicKey myPublicKey, final X509Extensions x509Extensions, final BigInteger bigInteger) {
            this.setStartDate(new DERUTCTime(date));
            this.setEndDate(new DERUTCTime(date2));
            if (bigInteger == null) {
                this.setSerialNumber(new DERInteger(1));
            }
            else {
                this.setSerialNumber(new DERInteger(bigInteger));
            }
            this.setSubject(x509DistinguishedName.getX509Name());
            this.setSubjectPublicKeyInfo(myPublicKey.getAsSubjectPublicKeyInfo());
            if (x509Extensions != null && x509Extensions.getSize() > 0) {
                this.setExtensions(x509Extensions.getBCX509Extensions());
            }
            else {
                this.setExtensions(new X509Extensions(new Vector()).getBCX509Extensions());
            }
        }
        
        public X509CertificateGenerator(final TBSCertificateStructure tbsCertificateStructure) {
            this.setStartDate(tbsCertificateStructure.getStartDate());
            this.setEndDate(tbsCertificateStructure.getEndDate());
            this.setSerialNumber(tbsCertificateStructure.getSerialNumber());
            this.setSubject(tbsCertificateStructure.getSubject());
            this.setSubjectPublicKeyInfo(tbsCertificateStructure.getSubjectPublicKeyInfo());
            this.setExtensions(tbsCertificateStructure.getExtensions());
            this.setIssuer(tbsCertificateStructure.getIssuer());
            this.setSignature(tbsCertificateStructure.getSignature());
        }
        
        public X509CertificateStructure sign(final PKCS12 pkcs12) {
            return this.sign(pkcs12.getX509Certificate().m_bcCertificate.getSubject(), pkcs12.getPrivateKey());
        }
        
        public X509CertificateStructure sign(final X509Name issuer, final IMyPrivateKey myPrivateKey) {
            try {
                this.setIssuer(issuer);
                this.setSignature(myPrivateKey.getSignatureAlgorithm().getIdentifier());
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final TBSCertificateStructure generateTBSCertificate = this.generateTBSCertificate();
                new DEROutputStream(byteArrayOutputStream).writeObject(generateTBSCertificate);
                final byte[] sign = ByteSignature.sign(byteArrayOutputStream.toByteArray(), myPrivateKey);
                final ASN1EncodableVector asn1EncodableVector = new ASN1EncodableVector();
                asn1EncodableVector.add(generateTBSCertificate);
                asn1EncodableVector.add(myPrivateKey.getSignatureAlgorithm().getIdentifier());
                asn1EncodableVector.add(new DERBitString(sign));
                return new X509CertificateStructure(new DERSequence(asn1EncodableVector));
            }
            catch (Throwable t) {
                LogHolder.log(2, LogType.MISC, t);
                return null;
            }
        }
    }
    
    private static final class X509CertificateInstantiator implements IResourceInstantiator
    {
        private String m_strIgnoreCertMark;
        
        public X509CertificateInstantiator(final String strIgnoreCertMark) {
            this.m_strIgnoreCertMark = strIgnoreCertMark;
        }
        
        public Object getInstance(final File file, final File file2) throws IOException {
            if (file == null || this.isBlocked(file.getName())) {
                return null;
            }
            return JAPCertificate.getInstance(new FileInputStream(file));
        }
        
        public Object getInstance(final ZipEntry zipEntry, final ZipFile zipFile) throws IOException {
            if (zipFile == null || this.isBlocked(zipEntry.getName())) {
                return null;
            }
            return JAPCertificate.getInstance(zipFile.getInputStream(zipEntry));
        }
        
        public Object getInstance(final InputStream inputStream, final String s) {
            if (s == null || this.isBlocked(s)) {
                return null;
            }
            return JAPCertificate.getInstance(inputStream);
        }
        
        private boolean isBlocked(String substring) {
            if (this.m_strIgnoreCertMark == null || substring == null || this.m_strIgnoreCertMark.trim().length() == 0) {
                return false;
            }
            if (substring.endsWith(this.m_strIgnoreCertMark)) {
                return true;
            }
            final int index;
            if ((index = substring.indexOf(this.m_strIgnoreCertMark)) >= 0) {
                substring = substring.substring(index, substring.length());
                if (substring.indexOf("/") < 0 && substring.indexOf(File.separator) < 0) {
                    return true;
                }
            }
            return false;
        }
    }
}
