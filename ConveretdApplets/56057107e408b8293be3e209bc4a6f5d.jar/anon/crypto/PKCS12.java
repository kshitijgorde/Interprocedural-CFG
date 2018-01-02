// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.crypto.generators.PKCS12ParametersGenerator;
import org.bouncycastle.crypto.params.DESParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import java.math.BigInteger;
import java.util.Vector;
import org.bouncycastle.asn1.BEROutputStream;
import org.bouncycastle.asn1.pkcs.MacData;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.engines.RC2Engine;
import org.bouncycastle.asn1.DEROutputStream;
import org.bouncycastle.asn1.BERConstructedOctetString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.DEREncodableVector;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.DESedeEngine;
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import java.io.OutputStream;
import java.io.IOException;
import anon.util.Base64;
import java.io.ByteArrayOutputStream;
import java.util.Enumeration;
import org.bouncycastle.asn1.pkcs.ContentInfo;
import org.bouncycastle.asn1.DERBMPString;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DERObject;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
import org.bouncycastle.asn1.x509.X509CertificateStructure;
import org.bouncycastle.asn1.pkcs.SignedData;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.pkcs.CertBag;
import org.bouncycastle.asn1.pkcs.SafeBag;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.pkcs.EncryptedData;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.pkcs.AuthenticatedSafe;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.Pfx;
import anon.util.ResourceLoader;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import anon.util.IMiscPasswordReader;
import anon.util.SingleStringPasswordReader;
import java.security.SecureRandom;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;

public final class PKCS12 implements PKCSObjectIdentifiers, X509ObjectIdentifiers, ICertificate
{
    public static final String FILE_EXTENSION = ".pfx";
    private static final int SALT_SIZE = 20;
    private static final int MIN_ITERATIONS = 100;
    private static final String BASE64_TAG = "PKCS12";
    public static final String XML_ELEMENT_NAME = "X509PKCS12";
    private static final String KEY_ALGORITHM = "1.2.840.113549.1.12.1.3";
    private static final String CERT_ALGORITHM = "1.2.840.113549.1.12.1.6";
    private SecureRandom random;
    private AsymmetricCryptoKeyPair m_keyPair;
    private JAPCertificate m_x509certificate;
    
    public PKCS12(final X509DistinguishedName x509DistinguishedName, final AsymmetricCryptoKeyPair asymmetricCryptoKeyPair, final Validity validity) {
        this(x509DistinguishedName, asymmetricCryptoKeyPair, validity, null);
    }
    
    public PKCS12(final X509DistinguishedName x509DistinguishedName, final AsymmetricCryptoKeyPair keyPair, final Validity validity, final X509Extensions x509Extensions) {
        this.random = new SecureRandom();
        this.m_keyPair = keyPair;
        this.m_x509certificate = JAPCertificate.getInstance(x509DistinguishedName, keyPair, validity, x509Extensions);
    }
    
    private PKCS12(final AsymmetricCryptoKeyPair keyPair, final JAPCertificate x509certificate) {
        this.random = new SecureRandom();
        this.m_keyPair = keyPair;
        this.m_x509certificate = x509certificate;
    }
    
    public static PKCS12 getInstance(final byte[] array, final char[] array2) {
        return getInstance(array, new SingleStringPasswordReader(array2));
    }
    
    public static PKCS12 getInstance(final byte[] array, final String s) {
        return getInstance(array, new SingleStringPasswordReader(s));
    }
    
    public static PKCS12 getInstance(final String s, final String s2) {
        return getInstance(s.getBytes(), s2.toCharArray());
    }
    
    public static PKCS12 getInstance(final byte[] array, final IMiscPasswordReader miscPasswordReader) {
        if (array == null) {
            return null;
        }
        return getInstance(new ByteArrayInputStream(array), miscPasswordReader);
    }
    
    public static PKCS12 getInstance(final InputStream inputStream, final char[] array) {
        return getInstance(inputStream, new SingleStringPasswordReader(array));
    }
    
    public static PKCS12 getInstance(final InputStream inputStream, final String s) {
        return getInstance(inputStream, new SingleStringPasswordReader(s));
    }
    
    public static PKCS12 getInstance(final InputStream inputStream, IMiscPasswordReader miscPasswordReader) {
        int i = 0;
        char[] array = new char[0];
        if (miscPasswordReader == null) {
            miscPasswordReader = new SingleStringPasswordReader(new char[0]);
        }
        try {
            String string = null;
            IMyPrivateKey private1 = null;
            X509CertificateStructure x509CertificateStructure = null;
            final ASN1Sequence asn1Sequence = JAPCertificate.toASN1Sequence(ResourceLoader.getStreamAsBytes(inputStream), "X509PKCS12");
            if (asn1Sequence == null) {
                return null;
            }
            final ContentInfo authSafe = new Pfx(asn1Sequence).getAuthSafe();
            if (!authSafe.getContentType().equals(PKCSObjectIdentifiers.data)) {
                return null;
            }
            final ContentInfo[] contentInfo = new AuthenticatedSafe((ASN1Sequence)new ASN1InputStream(new ByteArrayInputStream(((ASN1OctetString)authSafe.getContent()).getOctets())).readObject()).getContentInfo();
            for (int j = 0; j < contentInfo.length; ++j) {
                ASN1Sequence asn1Sequence2;
                if (contentInfo[j].getContentType().equals(PKCSObjectIdentifiers.data)) {
                    asn1Sequence2 = (ASN1Sequence)new ASN1InputStream(new ByteArrayInputStream(((DEROctetString)contentInfo[j].getContent()).getOctets())).readObject();
                }
                else {
                    if (!contentInfo[j].getContentType().equals(PKCSObjectIdentifiers.encryptedData)) {
                        continue;
                    }
                    final EncryptedData encryptedData = new EncryptedData((ASN1Sequence)contentInfo[j].getContent());
                    final MyCipher cipher = getCipher(encryptedData.getEncryptionAlgorithm().getObjectId().getId());
                    if (cipher == null) {
                        return null;
                    }
                    final PKCS12PBEParams pkcs12PBEParams = new PKCS12PBEParams((ASN1Sequence)encryptedData.getEncryptionAlgorithm().getParameters());
                    ASN1InputStream asn1InputStream = null;
                    do {
                        try {
                            asn1InputStream = new ASN1InputStream(new ByteArrayInputStream(codeData(false, encryptedData.getContent().getOctets(), pkcs12PBEParams, array, cipher.cipher, cipher.keysize)));
                            asn1Sequence2 = (ASN1Sequence)asn1InputStream.readObject();
                            i = 1;
                        }
                        catch (Throwable t) {
                            asn1Sequence2 = null;
                            asn1InputStream.close();
                            if (array.length == 0) {
                                array = new char[] { '\0' };
                            }
                            else {
                                array = miscPasswordReader.readPassword(null).toCharArray();
                            }
                        }
                    } while (i == 0);
                }
                for (int k = 0; k < asn1Sequence2.size(); ++k) {
                    final SafeBag safeBag = new SafeBag((ASN1Sequence)asn1Sequence2.getObjectAt(k));
                    if (safeBag.getBagId().equals(PKCSObjectIdentifiers.certBag)) {
                        final ASN1Sequence asn1Sequence3 = (ASN1Sequence)new ASN1InputStream(new ByteArrayInputStream(((DEROctetString)new CertBag((ASN1Sequence)safeBag.getBagValue()).getCertValue()).getOctets())).readObject();
                        if (asn1Sequence3.size() > 1 && asn1Sequence3.getObjectAt(1) instanceof DERObjectIdentifier && asn1Sequence3.getObjectAt(0).equals(PKCSObjectIdentifiers.signedData)) {
                            x509CertificateStructure = X509CertificateStructure.getInstance(new SignedData(ASN1Sequence.getInstance((ASN1TaggedObject)asn1Sequence3.getObjectAt(1), true)).getCertificates().getObjectAt(0));
                        }
                        else {
                            x509CertificateStructure = X509CertificateStructure.getInstance(asn1Sequence3);
                        }
                    }
                    else if (safeBag.getBagId().equals(PKCSObjectIdentifiers.pkcs8ShroudedKeyBag)) {
                        final EncryptedPrivateKeyInfo encryptedPrivateKeyInfo = new EncryptedPrivateKeyInfo((ASN1Sequence)safeBag.getBagValue());
                        final MyCipher cipher2 = getCipher(encryptedPrivateKeyInfo.getEncryptionAlgorithm().getObjectId().getId());
                        if (cipher2 == null) {
                            return null;
                        }
                        final PKCS12PBEParams pkcs12PBEParams2 = new PKCS12PBEParams((ASN1Sequence)encryptedPrivateKeyInfo.getEncryptionAlgorithm().getParameters());
                        PrivateKeyInfo privateKeyInfo = null;
                    Label_0738:
                        do {
                            ASN1InputStream asn1InputStream2 = null;
                            try {
                                asn1InputStream2 = new ASN1InputStream(new ByteArrayInputStream(codeData(false, encryptedPrivateKeyInfo.getEncryptedData(), pkcs12PBEParams2, array, cipher2.cipher, cipher2.keysize)));
                                privateKeyInfo = new PrivateKeyInfo((ASN1Sequence)asn1InputStream2.readObject());
                                i = 1;
                            }
                            catch (Throwable t2) {
                                privateKeyInfo = null;
                                asn1InputStream2.close();
                                if (array.length == 0) {
                                    array = new char[] { '\0' };
                                }
                                else {
                                    while (true) {
                                        array = miscPasswordReader.readPassword(null).toCharArray();
                                        if (array.length != 0) {
                                            if (array.length != 1) {
                                                continue Label_0738;
                                            }
                                            if (array[0] != '0') {
                                                break;
                                            }
                                            continue;
                                        }
                                    }
                                }
                            }
                        } while (i == 0);
                        private1 = new AsymmetricCryptoKeyPair(privateKeyInfo).getPrivate();
                    }
                    if (string == null && safeBag.getBagAttributes() != null) {
                        final Enumeration objects = safeBag.getBagAttributes().getObjects();
                        while (objects.hasMoreElements()) {
                            final ASN1Sequence asn1Sequence4 = objects.nextElement();
                            final DERObjectIdentifier derObjectIdentifier = (DERObjectIdentifier)asn1Sequence4.getObjectAt(0);
                            final DERObject derObject = (DERObject)((ASN1Set)asn1Sequence4.getObjectAt(1)).getObjectAt(0);
                            if (derObjectIdentifier.equals(PKCSObjectIdentifiers.pkcs_9_at_friendlyName)) {
                                string = ((DERBMPString)derObject).getString();
                            }
                        }
                    }
                }
            }
            if (x509CertificateStructure != null) {
                return new PKCS12(new AsymmetricCryptoKeyPair(private1), JAPCertificate.getInstance(x509CertificateStructure));
            }
        }
        catch (Throwable t3) {}
        return null;
    }
    
    public byte[] toByteArray() {
        return this.toByteArray("".toCharArray());
    }
    
    public byte[] toByteArray(final boolean b) {
        if (b) {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(Base64.createBeginTag("PKCS12").getBytes());
                byteArrayOutputStream.write(Base64.encode(this.toByteArray(), true).getBytes());
                byteArrayOutputStream.write(Base64.createEndTag("PKCS12").getBytes());
            }
            catch (IOException ex) {}
            return byteArrayOutputStream.toByteArray();
        }
        return this.toByteArray();
    }
    
    public byte[] toByteArray(final char[] array, final boolean b) {
        if (b) {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(Base64.createBeginTag("PKCS12").getBytes());
                byteArrayOutputStream.write(Base64.encode(this.toByteArray(array), true).getBytes());
                byteArrayOutputStream.write(Base64.createEndTag("PKCS12").getBytes());
            }
            catch (IOException ex) {}
            return byteArrayOutputStream.toByteArray();
        }
        return this.toByteArray(array);
    }
    
    public byte[] toByteArray(final char[] array) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            this.store(byteArrayOutputStream, array);
            byteArrayOutputStream.close();
        }
        catch (IOException ex) {}
        return byteArrayOutputStream.toByteArray();
    }
    
    public void store(final OutputStream outputStream, char[] array) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (array == null) {
            array = new char[0];
        }
        final byte[] array2 = new byte[20];
        this.random.nextBytes(array2);
        final PKCS12PBEParams pkcs12PBEParams = new PKCS12PBEParams(array2, 100);
        final EncryptedPrivateKeyInfo encryptedPrivateKeyInfo = new EncryptedPrivateKeyInfo(new AlgorithmIdentifier(new DERObjectIdentifier("1.2.840.113549.1.12.1.3"), pkcs12PBEParams.getDERObject()), codeData(true, this.m_keyPair.getPrivate().getEncoded(), pkcs12PBEParams, array, new DESedeEngine(), 192));
        final DERSequence[] array3 = new DERSequence[2];
        final ASN1EncodableVector asn1EncodableVector = new ASN1EncodableVector();
        asn1EncodableVector.add(PKCSObjectIdentifiers.pkcs_9_at_localKeyId);
        asn1EncodableVector.add(new DERSet(this.createSubjectKeyId()));
        array3[0] = new DERSequence(asn1EncodableVector);
        final ASN1EncodableVector asn1EncodableVector2 = new ASN1EncodableVector();
        asn1EncodableVector2.add(PKCSObjectIdentifiers.pkcs_9_at_friendlyName);
        asn1EncodableVector2.add(new DERSet(new DERBMPString(this.getAlias())));
        array3[1] = new DERSequence(asn1EncodableVector2);
        final BERConstructedOctetString berConstructedOctetString = new BERConstructedOctetString(new DERSequence(new SafeBag(PKCSObjectIdentifiers.pkcs8ShroudedKeyBag, encryptedPrivateKeyInfo.getDERObject(), new DERSet(array3))));
        final byte[] array4 = new byte[20];
        this.random.nextBytes(array4);
        final PKCS12PBEParams pkcs12PBEParams2 = new PKCS12PBEParams(array4, 100);
        final AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(new DERObjectIdentifier("1.2.840.113549.1.12.1.6"), pkcs12PBEParams2);
        final CertBag certBag = new CertBag(PKCSObjectIdentifiers.x509Certificate, new DEROctetString(this.m_x509certificate.getBouncyCastleCertificate()));
        final ASN1EncodableVector asn1EncodableVector3 = new ASN1EncodableVector();
        asn1EncodableVector3.add(PKCSObjectIdentifiers.pkcs_9_at_localKeyId);
        asn1EncodableVector3.add(new DERSet(this.createSubjectKeyId()));
        final DERSequence[] array5 = { new DERSequence(asn1EncodableVector3), null };
        final ASN1EncodableVector asn1EncodableVector4 = new ASN1EncodableVector();
        asn1EncodableVector4.add(PKCSObjectIdentifiers.pkcs_9_at_friendlyName);
        asn1EncodableVector4.add(new DERSet(new DERBMPString(this.getAlias())));
        array5[1] = new DERSequence(asn1EncodableVector4);
        final SafeBag safeBag = new SafeBag(PKCSObjectIdentifiers.certBag, certBag.getDERObject(), new DERSet(array5));
        byteArrayOutputStream.reset();
        final DEROutputStream derOutputStream = new DEROutputStream(byteArrayOutputStream);
        derOutputStream.writeObject(new DERSequence(safeBag));
        derOutputStream.close();
        final ContentInfo contentInfo = new ContentInfo(PKCSObjectIdentifiers.data, new BERConstructedOctetString(new AuthenticatedSafe(new ContentInfo[] { new ContentInfo(PKCSObjectIdentifiers.data, berConstructedOctetString), new ContentInfo(PKCSObjectIdentifiers.encryptedData, new EncryptedData(PKCSObjectIdentifiers.data, algorithmIdentifier, new BERConstructedOctetString(codeData(true, byteArrayOutputStream.toByteArray(), pkcs12PBEParams2, array, new RC2Engine(), 40)))) })));
        final byte[] array6 = new byte[20];
        final int n = 100;
        this.random.nextBytes(array6);
        final byte[] octets = ((DEROctetString)contentInfo.getContent()).getOctets();
        MacData macData;
        try {
            final HMac hMac = new HMac(new SHA1Digest());
            hMac.init(makePBEMacParameters(array, new PKCS12PBEParams(array6, n), 160));
            hMac.update(octets, 0, octets.length);
            final byte[] array7 = new byte[hMac.getMacSize()];
            hMac.doFinal(array7, 0);
            macData = new MacData(new DigestInfo(new AlgorithmIdentifier(X509ObjectIdentifiers.id_SHA1, null), array7), array6, n);
        }
        catch (Exception ex) {
            throw new IOException("error constructing MAC: " + ex.toString());
        }
        new BEROutputStream(outputStream).writeObject(new Pfx(contentInfo, macData));
    }
    
    public String getAlias() {
        final Vector<String> vector = new Vector<String>();
        final X509DistinguishedName subject = this.getSubject();
        vector.addElement(subject.getCommonName());
        vector.addElement(subject.getEmailAddress());
        vector.addElement(subject.getOrganisation());
        for (int i = 0; i < vector.size(); ++i) {
            if (vector.elementAt(i) != null && vector.elementAt(i).trim().length() != 0) {
                return vector.elementAt(i);
            }
        }
        return "alias unknown";
    }
    
    public X509Extensions getExtensions() {
        return this.m_x509certificate.getExtensions();
    }
    
    public X509DistinguishedName getSubject() {
        return this.m_x509certificate.getSubject();
    }
    
    public X509DistinguishedName getIssuer() {
        return this.m_x509certificate.getIssuer();
    }
    
    public IMyPrivateKey getPrivateKey() {
        return this.m_keyPair.getPrivate();
    }
    
    public IMyPublicKey getPublicKey() {
        return this.m_keyPair.getPublic();
    }
    
    public AsymmetricCryptoKeyPair getKeyPair() {
        return this.m_keyPair;
    }
    
    public JAPCertificate getX509Certificate() {
        return this.m_x509certificate;
    }
    
    public PKCS10CertificationRequest createCertifcationRequest() {
        return new PKCS10CertificationRequest(this);
    }
    
    public boolean setX509Certificate(final JAPCertificate japCertificate) {
        if (japCertificate != null && this.m_x509certificate.getPublicKey().equals(japCertificate.getPublicKey())) {
            this.m_x509certificate = (JAPCertificate)japCertificate.clone();
            return true;
        }
        return false;
    }
    
    public void sign(final PKCS12 pkcs12) {
        this.m_x509certificate = this.m_x509certificate.sign(pkcs12);
    }
    
    public void sign(final PKCS12 pkcs12, final Validity validity, final X509Extensions x509Extensions, final BigInteger bigInteger) {
        this.m_x509certificate = this.m_x509certificate.sign(pkcs12, validity, x509Extensions, bigInteger);
    }
    
    private static byte[] codeData(final boolean b, final byte[] array, final PKCS12PBEParams pkcs12PBEParams, final char[] array2, final BlockCipher blockCipher, final int n) throws IOException {
        byte[] array4;
        try {
            final PaddedBufferedBlockCipher paddedBufferedBlockCipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(blockCipher));
            paddedBufferedBlockCipher.init(b, new ParametersWithRandom(makePBEParameters(array2, pkcs12PBEParams, paddedBufferedBlockCipher.getUnderlyingCipher().getAlgorithmName(), n, 64), new SecureRandom()));
            final int length = array.length;
            int processBytes = 0;
            final byte[] array3 = new byte[paddedBufferedBlockCipher.getOutputSize(length)];
            if (length != 0) {
                processBytes = paddedBufferedBlockCipher.processBytes(array, 0, length, array3, 0);
            }
            try {
                processBytes += paddedBufferedBlockCipher.doFinal(array3, processBytes);
            }
            catch (Exception ex2) {}
            array4 = new byte[processBytes];
            System.arraycopy(array3, 0, array4, 0, processBytes);
        }
        catch (Exception ex) {
            throw new IOException("exception encrypting data - " + ex.toString());
        }
        return array4;
    }
    
    private static CipherParameters makePBEMacParameters(final char[] array, final PKCS12PBEParams pkcs12PBEParams, final int n) {
        final PBEParametersGenerator pbeGenerator = makePBEGenerator();
        final byte[] pkcs12PasswordToBytes = PBEParametersGenerator.PKCS12PasswordToBytes(array);
        pbeGenerator.init(pkcs12PasswordToBytes, pkcs12PBEParams.getIV(), pkcs12PBEParams.getIterations().intValue());
        final CipherParameters generateDerivedMacParameters = pbeGenerator.generateDerivedMacParameters(n);
        for (int i = 0; i != pkcs12PasswordToBytes.length; ++i) {
            pkcs12PasswordToBytes[i] = 0;
        }
        return generateDerivedMacParameters;
    }
    
    private static CipherParameters makePBEParameters(final char[] array, final PKCS12PBEParams pkcs12PBEParams, final String s, final int n, final int n2) {
        final PBEParametersGenerator pbeGenerator = makePBEGenerator();
        final byte[] pkcs12PasswordToBytes = PBEParametersGenerator.PKCS12PasswordToBytes(array);
        pbeGenerator.init(pkcs12PasswordToBytes, pkcs12PBEParams.getIV(), pkcs12PBEParams.getIterations().intValue());
        CipherParameters cipherParameters;
        if (n2 != 0) {
            cipherParameters = pbeGenerator.generateDerivedParameters(n, n2);
        }
        else {
            cipherParameters = pbeGenerator.generateDerivedParameters(n);
        }
        if (s.startsWith("DES")) {
            if (cipherParameters instanceof ParametersWithIV) {
                DESParameters.setOddParity(((KeyParameter)((ParametersWithIV)cipherParameters).getParameters()).getKey());
            }
            else {
                DESParameters.setOddParity(((KeyParameter)cipherParameters).getKey());
            }
        }
        for (int i = 0; i != pkcs12PasswordToBytes.length; ++i) {
            pkcs12PasswordToBytes[i] = 0;
        }
        return cipherParameters;
    }
    
    private static PBEParametersGenerator makePBEGenerator() {
        return new PKCS12ParametersGenerator(new SHA1Digest());
    }
    
    private static MyCipher getCipher(final String s) {
        if (s.equals("1.2.840.113549.1.12.1.3")) {
            return new MyCipher(new DESedeEngine(), 192);
        }
        if (s.equals("1.2.840.113549.1.12.1.4")) {
            return new MyCipher(new DESedeEngine(), 128);
        }
        if (s.equals("1.2.840.113549.1.12.1.5")) {
            return new MyCipher(new RC2Engine(), 128);
        }
        if (s.equals("1.2.840.113549.1.12.1.6")) {
            return new MyCipher(new RC2Engine(), 40);
        }
        return null;
    }
    
    private SubjectKeyIdentifier createSubjectKeyId() {
        try {
            return new SubjectKeyIdentifier(this.m_x509certificate.getBouncyCastleSubjectPublicKeyInfo());
        }
        catch (Exception ex) {
            throw new RuntimeException("error creating key");
        }
    }
    
    private static class MyCipher
    {
        public BlockCipher cipher;
        public int keysize;
        
        MyCipher(final BlockCipher cipher, final int keysize) {
            this.cipher = cipher;
            this.keysize = keysize;
        }
    }
}
