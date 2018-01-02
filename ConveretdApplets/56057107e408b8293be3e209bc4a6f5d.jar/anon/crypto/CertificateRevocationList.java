// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import logging.LogHolder;
import logging.LogType;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEREncodableVector;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import java.util.Enumeration;
import org.bouncycastle.asn1.x509.Time;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.asn1.x509.V2TBSCertListGenerator;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import anon.util.Base64;
import java.io.OutputStream;
import org.bouncycastle.asn1.DEROutputStream;
import java.io.ByteArrayOutputStream;
import org.bouncycastle.asn1.x509.TBSCertList;
import anon.util.IResourceInstantiator;
import java.util.Hashtable;
import java.io.IOException;
import anon.util.ResourceLoader;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.Vector;
import java.util.Date;
import org.bouncycastle.asn1.x509.CertificateList;
import anon.util.IXMLEncodable;

public class CertificateRevocationList implements IXMLEncodable
{
    private static final String BASE64_TAG = "X509 CRL";
    private static final String XML_ELEMENT_NAME = "X509CRL";
    private CertificateList m_crl;
    private Date m_thisUpdate;
    private Date m_nextUpdate;
    private X509DistinguishedName m_issuer;
    private X509Extensions m_extensions;
    
    public CertificateRevocationList(final PKCS12 pkcs12, final Vector vector, final Date date, final X509Extensions x509Extensions) {
        this(new CRLGenerator(pkcs12.getSubject().getX509Name(), vector, date, x509Extensions).sign(pkcs12));
    }
    
    public CertificateRevocationList(final CertificateList crl) {
        this.m_crl = crl;
        this.m_issuer = new X509DistinguishedName(this.m_crl.getIssuer());
        this.m_extensions = new X509Extensions(this.m_crl.getTBSCertList().getExtensions());
        this.m_thisUpdate = this.m_crl.getThisUpdate().getDate();
        if (this.m_crl.getNextUpdate() != null) {
            this.m_nextUpdate = this.m_crl.getNextUpdate().getDate();
        }
    }
    
    public static CertificateRevocationList getInstance(final byte[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        try {
            return new CertificateRevocationList(CertificateList.getInstance(JAPCertificate.toASN1Sequence(array, "X509CRL")));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static CertificateRevocationList getInstance(final File file) {
        if (file != null) {
            try {
                return getInstance(new FileInputStream(file));
            }
            catch (Exception ex) {}
        }
        return null;
    }
    
    public static CertificateRevocationList getInstance(final InputStream inputStream) {
        byte[] streamAsBytes;
        try {
            streamAsBytes = ResourceLoader.getStreamAsBytes(inputStream);
        }
        catch (IOException ex) {
            return null;
        }
        return getInstance(streamAsBytes);
    }
    
    public static Hashtable getInstance(final String s, final boolean b, final String s2) {
        try {
            return ResourceLoader.loadResources(s, new CRLInstantiator(s2), b);
        }
        catch (Exception ex) {
            return new Hashtable();
        }
    }
    
    public X509DistinguishedName getIssuer() {
        return this.m_issuer;
    }
    
    public boolean isIndirectCRL() {
        final X509IssuingDistributionPoint x509IssuingDistributionPoint = (X509IssuingDistributionPoint)this.m_extensions.getExtension(X509IssuingDistributionPoint.IDENTIFIER);
        return x509IssuingDistributionPoint != null && x509IssuingDistributionPoint.isIndirectCRL();
    }
    
    public Date getThisUpdate() {
        return this.m_thisUpdate;
    }
    
    public Date getNextUpdate() {
        return this.m_nextUpdate;
    }
    
    public X509Extensions getExtensions() {
        return this.m_extensions;
    }
    
    public Vector getRevokedCertificates() {
        final Vector<RevokedCertificate> vector = new Vector<RevokedCertificate>();
        final TBSCertList.CRLEntry[] revokedCertificates = this.m_crl.getRevokedCertificates();
        for (int i = 0; i < revokedCertificates.length; ++i) {
            vector.addElement(new RevokedCertificate(revokedCertificates[i]));
        }
        return vector;
    }
    
    public byte[] toByteArray() {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new DEROutputStream(byteArrayOutputStream).writeObject(this.m_crl);
        }
        catch (Exception ex) {}
        return byteArrayOutputStream.toByteArray();
    }
    
    public byte[] toByteArray(final boolean b) {
        if (b) {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(Base64.createBeginTag("X509 CRL").getBytes());
                byteArrayOutputStream.write(Base64.encode(this.toByteArray(), true).getBytes());
                byteArrayOutputStream.write(Base64.createEndTag("X509 CRL").getBytes());
            }
            catch (IOException ex) {}
            return byteArrayOutputStream.toByteArray();
        }
        return this.toByteArray();
    }
    
    public boolean verifiy(final JAPCertificate japCertificate) {
        if (japCertificate == null) {
            return false;
        }
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new DEROutputStream(byteArrayOutputStream).writeObject(this.m_crl.getTBSCertList());
            return ByteSignature.verify(byteArrayOutputStream.toByteArray(), this.m_crl.getSignature().getBytes(), japCertificate.getPublicKey());
        }
        catch (IOException ex) {
            return false;
        }
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("X509CRL");
        element.setAttribute("xml:space", "preserve");
        XMLUtil.setValue(element, Base64.encode(this.toByteArray(), true));
        return element;
    }
    
    private static final class CRLGenerator extends V2TBSCertListGenerator
    {
        public CRLGenerator(final X509Name issuer, final Vector vector, final Date date, final X509Extensions x509Extensions) {
            this.setIssuer(issuer);
            this.setThisUpdate(new Time(new Date()));
            if (date != null) {
                this.setNextUpdate(new Time(date));
            }
            this.setExtensions(x509Extensions.getBCX509Extensions());
            if (vector != null) {
                final Enumeration<JAPCertificate> elements = vector.elements();
                while (elements.hasMoreElements()) {
                    X509Extensions x509Extensions2 = null;
                    final JAPCertificate japCertificate = elements.nextElement();
                    if (!japCertificate.getIssuer().equals(issuer)) {
                        x509Extensions2 = new X509Extensions(new X509CertificateIssuer(japCertificate.getIssuer()));
                    }
                    this.addCRLEntry(new RevokedCertificate(japCertificate, new Date(), x509Extensions2).toASN1Sequence());
                }
            }
        }
        
        public CertificateList sign(final PKCS12 pkcs12) {
            return this.sign(pkcs12.getPrivateKey());
        }
        
        public CertificateList sign(final IMyPrivateKey myPrivateKey) {
            try {
                this.setSignature(myPrivateKey.getSignatureAlgorithm().getIdentifier());
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final TBSCertList generateTBSCertList = this.generateTBSCertList();
                new DEROutputStream(byteArrayOutputStream).writeObject(generateTBSCertList);
                final byte[] sign = ByteSignature.sign(byteArrayOutputStream.toByteArray(), myPrivateKey);
                final ASN1EncodableVector asn1EncodableVector = new ASN1EncodableVector();
                asn1EncodableVector.add(generateTBSCertList);
                asn1EncodableVector.add(myPrivateKey.getSignatureAlgorithm().getIdentifier());
                asn1EncodableVector.add(new DERBitString(sign));
                return new CertificateList(new DERSequence(asn1EncodableVector));
            }
            catch (Throwable t) {
                LogHolder.log(2, LogType.CRYPTO, t);
                return null;
            }
        }
    }
    
    private static final class CRLInstantiator implements IResourceInstantiator
    {
        private String m_ignoreCRLMark;
        
        public CRLInstantiator(final String ignoreCRLMark) {
            this.m_ignoreCRLMark = ignoreCRLMark;
        }
        
        public Object getInstance(final File file, final File file2) throws Exception {
            if (file == null || this.isBlocked(file.getName())) {
                return null;
            }
            return CertificateRevocationList.getInstance(file);
        }
        
        public Object getInstance(final ZipEntry zipEntry, final ZipFile zipFile) throws Exception {
            if (zipFile == null || this.isBlocked(zipEntry.getName())) {
                return null;
            }
            return CertificateRevocationList.getInstance(zipFile.getInputStream(zipEntry));
        }
        
        public Object getInstance(final InputStream inputStream, final String s) {
            if (s == null || this.isBlocked(s)) {
                return null;
            }
            return CertificateRevocationList.getInstance(inputStream);
        }
        
        private boolean isBlocked(String substring) {
            if (this.m_ignoreCRLMark == null || substring == null || this.m_ignoreCRLMark.trim().length() == 0) {
                return false;
            }
            if (substring.endsWith(this.m_ignoreCRLMark)) {
                return true;
            }
            final int index;
            if ((index = substring.indexOf(this.m_ignoreCRLMark)) >= 0) {
                substring = substring.substring(index, substring.length());
                if (substring.indexOf("/") < 0 && substring.indexOf(File.separator) < 0) {
                    return true;
                }
            }
            return false;
        }
    }
}
