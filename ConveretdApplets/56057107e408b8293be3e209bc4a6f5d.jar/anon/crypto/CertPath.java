// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import logging.LogHolder;
import logging.LogType;
import org.w3c.dom.Document;
import java.util.Enumeration;
import java.util.Date;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import anon.util.XMLParseException;
import org.w3c.dom.Element;
import java.util.Vector;
import anon.util.IXMLEncodable;

public class CertPath implements IXMLEncodable
{
    public static final String XML_ELEMENT_NAME = "CertPath";
    public static final String XML_ATTR_CLASS = "rootCertificateClass";
    public static final String XML_ATTR_TYPE = "certificateType";
    public static final int NO_ERRORS = 0;
    public static final int ERROR_VERIFICATION = 1;
    public static final int ERROR_VALIDITY = 2;
    public static final int ERROR_REVOCATION = 3;
    public static final int ERROR_UNKNOWN_CRITICAL_EXTENSION = 4;
    public static final int ERROR_BASIC_CONSTRAINTS_IS_CA = 5;
    public static final int ERROR_BASIC_CONSTRAINTS_IS_NO_CA = 6;
    public static final int ERROR_BASIC_CONSTRAINTS_PATH_TOO_LONG = 7;
    public static final int ERROR_KEY_USAGE = 8;
    public static final int ERROR_VALIDITY_SEVERE = 9;
    private static final int VERIFICATION_INTERVAL = 180000;
    private static final long GRACE_PERIOD = 5184000000L;
    private int m_documentType;
    private Vector m_certificates;
    private boolean m_rootFound;
    private boolean m_valid;
    private boolean m_verified;
    private long m_verificationTime;
    private int m_pathError;
    private int m_errorPosition;
    
    private CertPath(final JAPCertificate japCertificate, final int documentType) {
        this.m_certificates = new Vector();
        this.m_documentType = documentType;
        this.m_verificationTime = 0L;
        this.m_verified = false;
        this.m_pathError = 0;
        this.m_errorPosition = -1;
        this.appendCertificate(japCertificate);
        this.m_rootFound = false;
    }
    
    protected CertPath(final Element element) throws XMLParseException {
        if (element == null || !element.getNodeName().equals("CertPath")) {
            throw new XMLParseException("##__root__##", "CertPath");
        }
        XMLUtil.parseAttribute(element, "certificateType", -1);
        if (this.m_documentType == -1) {
            this.m_documentType = getDocumentTypeFromRootCertType(XMLUtil.parseAttribute(element, "rootCertificateClass", -1));
        }
        final NodeList elementsByTagName = element.getElementsByTagName("X509Certificate");
        if (elementsByTagName.getLength() == 0) {
            throw new XMLParseException("No certificates found!");
        }
        this.m_certificates = new Vector(elementsByTagName.getLength());
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            this.m_certificates.addElement(JAPCertificate.getInstance(elementsByTagName.item(i)));
        }
        if (this.m_documentType == 0) {
            this.m_valid = true;
        }
        else {
            this.m_valid = this.buildAndValidate(null);
        }
    }
    
    public static CertPath getRootInstance(final JAPCertificate japCertificate) {
        final CertPath certPath = new CertPath(japCertificate, 0);
        certPath.m_valid = true;
        return certPath;
    }
    
    public static CertPath getInstance(final JAPCertificate japCertificate, final int n, final Vector vector) {
        if (japCertificate == null) {
            return null;
        }
        final CertificateInfoStructure certificateInfoStructure = SignatureVerifier.getInstance().getVerificationCertificateStore().getCertificateInfoStructure(japCertificate, getCertType(n));
        if (certificateInfoStructure != null && certificateInfoStructure.getCertPath().m_valid && (certificateInfoStructure.getCertPath().checkValidity(new Date()) || !isPossiblyValid(japCertificate, vector))) {
            return certificateInfoStructure.getCertPath();
        }
        final CertPath certPath = new CertPath(japCertificate, n);
        certPath.m_valid = certPath.buildAndValidate((Vector)vector.clone());
        if (!certPath.m_valid && certificateInfoStructure != null) {
            return certificateInfoStructure.getCertPath();
        }
        SignatureVerifier.getInstance().getVerificationCertificateStore().addCertificateWithVerification(certPath, getCertType(n), false);
        return certPath;
    }
    
    private static boolean isPossiblyValid(final JAPCertificate japCertificate, final Vector vector) {
        if (japCertificate.getValidity().isValid(new Date())) {
            final Enumeration<JAPCertificate> elements = vector.elements();
            while (elements.hasMoreElements()) {
                if (elements.nextElement().getValidity().isValid(new Date())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean buildAndValidate(final Vector vector) {
        int errorPosition = 0;
        this.build(vector);
        synchronized (this.m_certificates) {
            final Enumeration<JAPCertificate> elements = (Enumeration<JAPCertificate>)this.m_certificates.elements();
            if (elements.hasMoreElements()) {
                JAPCertificate japCertificate = elements.nextElement();
                do {
                    JAPCertificate japCertificate2 = null;
                    if (elements.hasMoreElements()) {
                        japCertificate2 = elements.nextElement();
                    }
                    this.m_pathError = this.validate(japCertificate, errorPosition, japCertificate2);
                    if (this.m_pathError != 0) {
                        this.m_errorPosition = errorPosition;
                        if (this.m_pathError == 1 || this.m_pathError == 3 || this.m_pathError == 4 || this.m_pathError == 9) {
                            return false;
                        }
                    }
                    japCertificate = japCertificate2;
                    ++errorPosition;
                } while (japCertificate != null);
            }
            return true;
        }
    }
    
    private void build(final Vector vector) {
        JAPCertificate japCertificate = null;
        if (vector != null) {
            japCertificate = doNameAndKeyChaining(this.getLastCertificate(), vector, false);
        }
        while (japCertificate != null) {
            this.appendCertificate(japCertificate);
            japCertificate = doNameAndKeyChaining(japCertificate, vector, false);
        }
        this.findVerifier();
    }
    
    private void findVerifier() {
        JAPCertificate japCertificate = doNameAndKeyChaining(this.getLastCertificate(), SignatureVerifier.getInstance().getVerificationCertificateStore().getAvailableCertificatesByType(getRootCertType(this.m_documentType)), false);
        if (japCertificate == null) {
            japCertificate = doNameAndKeyChaining(this.getLastCertificate(), SignatureVerifier.getInstance().getVerificationCertificateStore().getUnavailableCertificatesByType(getRootCertType(this.m_documentType)), false);
        }
        if (japCertificate != null) {
            this.m_rootFound = true;
            this.appendCertificate(japCertificate);
        }
    }
    
    private static JAPCertificate doNameAndKeyChaining(final JAPCertificate japCertificate, final Vector vector, final boolean b) {
        JAPCertificate japCertificate2 = null;
        final Enumeration<JAPCertificate> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final JAPCertificate nextElement = elements.nextElement();
            JAPCertificate certificate;
            if (nextElement instanceof JAPCertificate) {
                certificate = nextElement;
            }
            else {
                certificate = ((CertificateInfoStructure)nextElement).getCertificate();
            }
            if (japCertificate.getIssuer() != null && certificate.getSubject() != null && (b || !japCertificate.equals(certificate)) && japCertificate.getIssuer().equals(certificate.getSubject())) {
                final X509AuthorityKeyIdentifier x509AuthorityKeyIdentifier = (X509AuthorityKeyIdentifier)japCertificate.getExtensions().getExtension(X509AuthorityKeyIdentifier.IDENTIFIER);
                if (x509AuthorityKeyIdentifier != null && !x509AuthorityKeyIdentifier.getValue().equals(certificate.getSubjectKeyIdentifier())) {
                    continue;
                }
                if (!japCertificate.equals(certificate)) {
                    return certificate;
                }
                japCertificate2 = certificate;
            }
        }
        return japCertificate2;
    }
    
    private int validate(final JAPCertificate japCertificate, final int n, final JAPCertificate japCertificate2) {
        if (japCertificate2 != null && !japCertificate.verify(japCertificate2)) {
            return 1;
        }
        if (japCertificate.isRevoked()) {
            return 3;
        }
        if (japCertificate.getExtensions().hasUnknownCriticalExtensions()) {
            return 4;
        }
        final Date date = new Date();
        if (japCertificate.getValidity().isValid(date)) {
            final X509BasicConstraints x509BasicConstraints = (X509BasicConstraints)japCertificate.getExtensions().getExtension(X509BasicConstraints.IDENTIFIER);
            if (x509BasicConstraints != null) {
                if (x509BasicConstraints.isCA()) {
                    if (n == 0) {
                        return 5;
                    }
                    final int pathLengthConstraint = x509BasicConstraints.getPathLengthConstraint();
                    if (pathLengthConstraint != -1 && pathLengthConstraint < n) {
                        return 7;
                    }
                }
                else if (n > 0) {
                    return 6;
                }
            }
            final X509KeyUsage x509KeyUsage = (X509KeyUsage)japCertificate.getExtensions().getExtension(X509KeyUsage.IDENTIFIER);
            if (x509KeyUsage != null) {
                if (n == 0) {
                    if (!x509KeyUsage.allowsDigitalSignature()) {
                        return 8;
                    }
                }
                else if (!x509KeyUsage.allowsDigitalSignature() || !x509KeyUsage.allowsKeyCertSign()) {
                    return 8;
                }
            }
            return 0;
        }
        if (japCertificate.getValidity().getValidTo().getTime() + 5184000000L < date.getTime()) {
            return 9;
        }
        return 2;
    }
    
    public Element toXmlElement(final Document document) {
        if (document == null) {
            return null;
        }
        final Element element = document.createElement("CertPath");
        XMLUtil.setAttribute(element, "certificateType", this.m_documentType);
        synchronized (this.m_certificates) {
            final Enumeration<JAPCertificate> elements = this.m_certificates.elements();
            while (elements.hasMoreElements()) {
                element.appendChild(elements.nextElement().toXmlElement(document));
            }
        }
        return element;
    }
    
    private void appendCertificate(final JAPCertificate japCertificate) {
        synchronized (this.m_certificates) {
            if (!this.m_certificates.contains(japCertificate)) {
                this.m_certificates.addElement(japCertificate);
            }
        }
    }
    
    private void removeLastCertificate() {
        synchronized (this.m_certificates) {
            if (this.m_certificates.size() > 1) {
                this.m_certificates.removeElementAt(this.m_certificates.size() - 1);
            }
        }
    }
    
    public JAPCertificate getLastCertificate() {
        synchronized (this.m_certificates) {
            if (this.m_certificates.size() > 0) {
                return this.m_certificates.lastElement();
            }
            return null;
        }
    }
    
    public JAPCertificate getFirstCertificate() {
        synchronized (this.m_certificates) {
            if (this.m_certificates.size() > 0) {
                return this.m_certificates.firstElement();
            }
            return null;
        }
    }
    
    public JAPCertificate getSecondCertificate() {
        synchronized (this.m_certificates) {
            if (this.m_certificates.size() <= 1) {
                return null;
            }
            return this.m_certificates.elementAt(1);
        }
    }
    
    private static int getRootCertType(final int n) {
        switch (n) {
            case 1: {
                return 1;
            }
            case 2: {
                return 5;
            }
            case 3: {
                return 6;
            }
            case 4: {
                return 8;
            }
            case 5: {
                return 10;
            }
            case 0: {
                return 0;
            }
            default: {
                return -1;
            }
        }
    }
    
    private static int getDocumentTypeFromRootCertType(final int n) {
        switch (n) {
            case 1: {
                return 1;
            }
            case 5: {
                return 2;
            }
            case 6: {
                return 3;
            }
            case 8: {
                return 4;
            }
            case 0: {
                return 0;
            }
            default: {
                return -1;
            }
        }
    }
    
    private static int getCertType(final int n) {
        switch (n) {
            case 1: {
                return 2;
            }
            case 2: {
                return 3;
            }
            case 3: {
                return 4;
            }
            case 4: {
                return 7;
            }
            case 5: {
                return 9;
            }
            case 0: {
                return 0;
            }
            default: {
                return -1;
            }
        }
    }
    
    public boolean checkValidity(final Date date) {
        if (date == null) {
            return false;
        }
        synchronized (this.m_certificates) {
            final Enumeration<JAPCertificate> elements = this.m_certificates.elements();
            while (elements.hasMoreElements()) {
                if (!elements.nextElement().getValidity().isValid(date)) {
                    return false;
                }
            }
            return true;
        }
    }
    
    protected boolean isVerifier(final JAPCertificate japCertificate) {
        return japCertificate != null && this.m_valid && ((this.m_rootFound && japCertificate.equals(this.getLastCertificate())) || this.getLastCertificate().verify(japCertificate));
    }
    
    public synchronized boolean verify() {
        if (this.m_documentType == 0) {
            return true;
        }
        if (System.currentTimeMillis() - this.m_verificationTime < 180000L) {
            return this.m_verified;
        }
        this.m_valid = this.buildAndValidate(null);
        this.m_verificationTime = System.currentTimeMillis();
        final CertificateInfoStructure certificateInfoStructure = SignatureVerifier.getInstance().getVerificationCertificateStore().getCertificateInfoStructure(this.getLastCertificate());
        if (this.m_rootFound) {
            if (certificateInfoStructure != null && certificateInfoStructure.getCertificateType() == getRootCertType(this.m_documentType)) {
                if (certificateInfoStructure.isAvailable() && this.m_valid) {
                    return this.m_verified = true;
                }
            }
            else {
                if (certificateInfoStructure != null && certificateInfoStructure.getCertificateType() != getRootCertType(this.m_documentType)) {
                    LogHolder.log(1, LogType.CRYPTO, "Verification root certificate found in wrong type path! Cert doctype: " + certificateInfoStructure.getCertificateType() + " Expected doc type: " + getRootCertType(this.m_documentType) + ((certificateInfoStructure.getCertificate() != null) ? (" SKI:" + certificateInfoStructure.getCertificate().getSubjectKeyIdentifier()) : ""));
                    return this.m_verified = false;
                }
                this.removeLastCertificate();
                this.m_rootFound = false;
                this.resetVerification();
                return this.verify();
            }
        }
        else {
            final Vector<JAPCertificate> vector = new Vector<JAPCertificate>();
            vector.addElement(this.getLastCertificate());
            if (doNameAndKeyChaining(this.getLastCertificate(), vector, true) != null) {
                final Vector availableCertificatesByType = SignatureVerifier.getInstance().getVerificationCertificateStore().getAvailableCertificatesByType(getCertType(this.m_documentType));
                if (this.m_valid && doNameAndKeyChaining(this.getLastCertificate(), availableCertificatesByType, true) != null) {
                    return this.m_verified = true;
                }
            }
        }
        return this.m_verified = false;
    }
    
    public int length() {
        return this.m_certificates.size();
    }
    
    protected void resetVerification() {
        this.m_verificationTime = 0L;
    }
    
    public String toString() {
        synchronized (this.m_certificates) {
            String string = new String("Certification Path (" + this.length() + "):");
            String string2 = new String();
            for (int i = this.m_certificates.size(); i > 0; --i) {
                string2 += "\t";
                string = string + "\n" + string2 + ((JAPCertificate)this.m_certificates.elementAt(i - 1)).getSubject().getCommonName();
            }
            return string;
        }
    }
    
    public CertPathInfo getPathInfo() {
        JAPCertificate firstCertificate = null;
        JAPCertificate secondCertificate = null;
        JAPCertificate lastCertificate = null;
        Vector<Object> vector = null;
        final boolean verify;
        synchronized (this.m_certificates) {
            verify = this.verify();
            int length = this.length();
            firstCertificate = this.getFirstCertificate();
            if (length > 1 && this.m_rootFound) {
                lastCertificate = this.getLastCertificate();
                --length;
            }
            if (length > 1) {
                secondCertificate = this.getSecondCertificate();
            }
            if (length > 2) {
                vector = new Vector<Object>();
                for (int i = 2; i < length; ++i) {
                    vector.addElement(this.m_certificates.elementAt(i));
                }
            }
        }
        final CertPathInfo certPathInfo = new CertPathInfo(firstCertificate, secondCertificate, lastCertificate, vector, 1);
        certPathInfo.setVerified(verify);
        return certPathInfo;
    }
    
    public boolean isValidPath() {
        return this.m_valid;
    }
    
    protected Vector getCertificates() {
        final Vector vector = (Vector)this.m_certificates.clone();
        if (this.m_rootFound) {
            vector.removeElementAt(vector.size() - 1);
        }
        return vector;
    }
    
    public int getErrorCode() {
        return this.m_pathError;
    }
    
    public int getErrorPosition() {
        return this.m_errorPosition;
    }
}
