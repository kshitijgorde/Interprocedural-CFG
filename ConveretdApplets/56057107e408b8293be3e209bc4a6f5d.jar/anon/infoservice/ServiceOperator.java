// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import java.util.Enumeration;
import anon.util.XMLUtil;
import anon.terms.TermsAndConditions;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import anon.util.Util;
import anon.crypto.CertPath;
import org.w3c.dom.Node;
import java.util.Vector;
import anon.crypto.X509DistinguishedName;
import logging.LogHolder;
import logging.LogType;
import java.net.URL;
import anon.crypto.AbstractX509AlternativeName;
import anon.crypto.X509SubjectAlternativeName;
import anon.crypto.JAPCertificate;
import anon.crypto.MultiCertPath;

public class ServiceOperator extends AbstractDatabaseEntry
{
    public static final String XML_ELEMENT_NAME = "Operator";
    public static final String XML_ELEMENT_ORGANISATION = "Organisation";
    public static final String XML_ELEMENT_COUNTRYCODE = "CountryCode";
    public static final String XML_ELEMENT_URL = "URL";
    public static final String XML_ELEMENT_ORG_UNIT = "OrganisationalUnit";
    public static final String XML_ELEMENT_EMAIL = "EMail";
    public static final String XML_ELEMENT_EMAIL_SPAMSAFE = "Liame";
    private static final String AT_SUBSTITUTE = "([at]";
    private static final String DOT_SUBSTITUTE = "([dot]";
    private static final boolean SPAM_SAFE = true;
    private String m_strOrganization;
    private String m_strOrgUnit;
    private String m_strUrl;
    private String m_strEmail;
    private String m_countryCode;
    private String m_commonName;
    private long m_lastUpdate;
    private String m_strID;
    private MultiCertPath m_certPath;
    
    public ServiceOperator(final JAPCertificate japCertificate) {
        super(Long.MAX_VALUE);
        final X509DistinguishedName subject = japCertificate.getSubject();
        this.m_certPath = null;
        this.m_strOrganization = subject.getOrganisation();
        this.m_commonName = subject.getCommonName();
        if (this.m_strOrganization == null || this.m_strOrganization.trim().length() == 0) {
            this.m_strOrganization = subject.getCommonName();
        }
        this.m_countryCode = subject.getCountryCode();
        this.m_strOrgUnit = subject.getOrganisationalUnit();
        this.m_strEmail = subject.getE_EmailAddress();
        if (this.m_strEmail == null || this.m_strEmail.trim().length() == 0) {
            this.m_strEmail = subject.getEmailAddress();
        }
        final Vector extensions = japCertificate.getExtensions().getExtensions(X509SubjectAlternativeName.IDENTIFIER);
        for (int i = 0; i < extensions.size(); ++i) {
            final X509SubjectAlternativeName x509SubjectAlternativeName = extensions.elementAt(i);
            final Vector tags = x509SubjectAlternativeName.getTags();
            final Vector values = x509SubjectAlternativeName.getValues();
            if (tags.size() == values.size()) {
                for (int j = 0; j < tags.size(); ++j) {
                    if (tags.elementAt(j).equals(AbstractX509AlternativeName.TAG_URL)) {
                        try {
                            this.m_strUrl = new URL(values.elementAt(j).toString()).toString();
                        }
                        catch (Exception ex) {}
                        break;
                    }
                }
            }
        }
        this.m_strID = japCertificate.getSubjectKeyIdentifierConcatenated();
        if (this.m_strID == null) {
            LogHolder.log(1, LogType.DB, "Could not create ID for ServiceOperator entry!");
            this.m_strID = "";
        }
    }
    
    public ServiceOperator(final Node node, final MultiCertPath certPath, final long lastUpdate) {
        super(Long.MAX_VALUE);
        this.m_certPath = certPath;
        this.m_lastUpdate = lastUpdate;
        final CertPath path;
        if (this.m_certPath != null && (path = this.m_certPath.getPath()) != null && path.getSecondCertificate() != null) {
            final X509DistinguishedName subject = path.getSecondCertificate().getSubject();
            this.m_strOrganization = subject.getOrganisation();
            this.m_commonName = subject.getCommonName();
            if (this.m_strOrganization == null || this.m_strOrganization.trim().length() == 0) {
                this.m_strOrganization = subject.getCommonName();
            }
            this.m_countryCode = subject.getCountryCode();
            this.m_strOrgUnit = subject.getOrganisationalUnit();
            this.m_strEmail = subject.getE_EmailAddress();
            if (this.m_strEmail == null || this.m_strEmail.trim().length() == 0) {
                this.m_strEmail = subject.getEmailAddress();
            }
            final Vector extensions = path.getSecondCertificate().getExtensions().getExtensions(X509SubjectAlternativeName.IDENTIFIER);
            for (int i = 0; i < extensions.size(); ++i) {
                final X509SubjectAlternativeName x509SubjectAlternativeName = extensions.elementAt(i);
                final Vector tags = x509SubjectAlternativeName.getTags();
                final Vector values = x509SubjectAlternativeName.getValues();
                if (tags.size() == values.size()) {
                    for (int j = 0; j < tags.size(); ++j) {
                        if (tags.elementAt(j).equals(AbstractX509AlternativeName.TAG_URL)) {
                            try {
                                this.m_strUrl = new URL(values.elementAt(j).toString()).toString();
                            }
                            catch (Exception ex) {}
                            break;
                        }
                    }
                }
            }
            final Vector paths = this.m_certPath.getPaths();
            final Vector<JAPCertificate> vector = new Vector<JAPCertificate>();
            for (int k = 0; k < paths.size(); ++k) {
                final JAPCertificate secondCertificate = paths.elementAt(k).getSecondCertificate();
                if (secondCertificate != null) {
                    vector.addElement(secondCertificate);
                }
            }
            this.m_strID = JAPCertificate.calculateXORofSKIs(vector);
        }
        if (this.m_strID == null) {
            LogHolder.log(1, LogType.DB, "Could not create ID for ServiceOperator entry!");
            this.m_strID = "";
        }
    }
    
    public long getVersionNumber() {
        return this.m_lastUpdate;
    }
    
    public long getLastUpdate() {
        return this.m_lastUpdate;
    }
    
    public String getId() {
        return this.m_strID;
    }
    
    public String getEMail() {
        return this.m_strEmail;
    }
    
    public String getEMailSpamSafe() {
        if (this.m_strEmail != null) {
            this.m_strEmail = Util.replaceAll(this.m_strEmail, "@", "([at]");
            this.m_strEmail = Util.replaceAll(this.m_strEmail, ".", "([dot]");
        }
        return this.m_strEmail;
    }
    
    public String getOrganization() {
        return this.m_strOrganization;
    }
    
    public String getCommonName() {
        return this.m_commonName;
    }
    
    public String getOrganizationUnit() {
        return this.m_strOrgUnit;
    }
    
    public MultiCertPath getCertPath() {
        return this.m_certPath;
    }
    
    public JAPCertificate getCertificate() {
        final JAPCertificate secondCertificate;
        if (this.m_certPath == null || this.m_certPath.getPath() == null || (secondCertificate = this.m_certPath.getPath().getSecondCertificate()) == null) {
            return null;
        }
        return secondCertificate;
    }
    
    public String getUrl() {
        return this.m_strUrl;
    }
    
    public String getCountryCode() {
        return this.m_countryCode;
    }
    
    public Element toXMLElement(final Document document) {
        return this.toXMLElement(document, true);
    }
    
    public boolean hasTermsAndConditions() {
        return TermsAndConditions.getTermsAndConditions(this) != null;
    }
    
    public Element toXMLElement(final Document document, final boolean b) {
        return this.toXMLElement(document, null, b);
    }
    
    public Element toXMLElement(final Document document, final OperatorAddress operatorAddress, final boolean b) {
        if (document == null) {
            return null;
        }
        final Element element = document.createElement("Operator");
        if (this.m_strOrganization != null) {
            XMLUtil.createChildElementWithValue(element, "Organisation", XMLUtil.filterXMLChars(this.m_strOrganization));
        }
        if (this.m_strUrl != null) {
            XMLUtil.createChildElementWithValue(element, "URL", XMLUtil.filterXMLChars(this.m_strUrl));
        }
        if (this.m_countryCode != null) {
            XMLUtil.createChildElementWithValue(element, "CountryCode", XMLUtil.filterXMLChars(this.m_countryCode));
        }
        if (this.m_strOrgUnit != null) {
            XMLUtil.createChildElementWithValue(element, "OrganisationalUnit", XMLUtil.filterXMLChars(this.m_strOrgUnit));
        }
        if (this.m_strEmail != null) {
            XMLUtil.createChildElementWithValue(element, b ? "Liame" : "EMail", b ? XMLUtil.filterXMLChars(this.getEMailSpamSafe()) : XMLUtil.filterXMLChars(this.getEMail()));
        }
        if (operatorAddress != null) {
            final Enumeration addressAsNodeList = operatorAddress.getAddressAsNodeList(document);
            while (addressAsNodeList.hasMoreElements()) {
                element.appendChild(addressAsNodeList.nextElement());
            }
        }
        return element;
    }
    
    public int hashCode() {
        return this.getId().hashCode();
    }
    
    public boolean equals(final Object o) {
        return o != null && o instanceof ServiceOperator && this.getId().equals(((ServiceOperator)o).getId());
    }
}
