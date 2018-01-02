// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import java.util.Vector;
import anon.util.IXMLEncodable;

public class CertificateContainer implements IXMLEncodable
{
    public static final String XML_ELEMENT_NAME = "CertificateContainer";
    private static final String XML_ATTR_REMOVABLE = "removable";
    private JAPCertificate m_parentCertificate;
    private CertPath m_certPath;
    private int m_certificateType;
    private boolean m_enabled;
    private boolean m_certificateNeedsVerification;
    private boolean m_onlyHardRemovable;
    private boolean m_bNotRemovable;
    private Vector m_lockList;
    
    public CertificateContainer(final CertPath certPath, final int certificateType, final boolean certificateNeedsVerification) {
        this.m_bNotRemovable = false;
        if (certPath == null || certPath.getFirstCertificate() == null) {
            throw new IllegalArgumentException("Invalid cert path!");
        }
        this.m_certPath = certPath;
        this.m_certificateType = certificateType;
        this.m_certificateNeedsVerification = certificateNeedsVerification;
        this.m_enabled = true;
        this.m_parentCertificate = null;
        this.m_onlyHardRemovable = false;
        this.m_bNotRemovable = false;
        this.m_lockList = new Vector();
    }
    
    public CertificateContainer(final Element element) throws Exception {
        this.m_bNotRemovable = false;
        final Element element2 = (Element)XMLUtil.getFirstChildByName(element, "CertificateType");
        if (element2 == null) {
            throw new Exception("No CertificateType node found.");
        }
        this.m_certificateType = XMLUtil.parseValue(element2, -1);
        if (this.m_certificateType == -1) {
            throw new Exception("Invalid CertificateType value.");
        }
        final Element element3 = (Element)XMLUtil.getFirstChildByName(element, "CertificateNeedsVerification");
        if (element3 == null) {
            throw new Exception("No CertificateNeedsVerification node found.");
        }
        this.m_certificateNeedsVerification = XMLUtil.parseValue(element3, true);
        final Element element4 = (Element)XMLUtil.getFirstChildByName(element, "CertificateEnabled");
        if (element4 == null) {
            throw new Exception("No CertificateEnabled node found.");
        }
        this.m_enabled = XMLUtil.parseValue(element4, false);
        final Element element5 = (Element)XMLUtil.getFirstChildByName(element, "CertificateData");
        if (element5 == null) {
            throw new Exception("No CertificateData node found.");
        }
        if (JAPCertificate.getInstance(XMLUtil.getFirstChildByName(element5, "X509Certificate")) == null) {
            this.m_certPath = new CertPath((Element)XMLUtil.getFirstChildByName(element5, "CertPath"));
        }
        this.m_parentCertificate = null;
        this.m_onlyHardRemovable = true;
        this.m_bNotRemovable = !XMLUtil.parseAttribute(element, "removable", false);
        this.m_lockList = new Vector();
    }
    
    public JAPCertificate getCertificate() {
        return this.m_certPath.getFirstCertificate();
    }
    
    public CertPath getCertPath() {
        return this.m_certPath;
    }
    
    public void setParentCertificate(final JAPCertificate parentCertificate) {
        this.m_parentCertificate = parentCertificate;
    }
    
    public JAPCertificate getParentCertificate() {
        return this.m_parentCertificate;
    }
    
    public int getCertificateType() {
        return this.m_certificateType;
    }
    
    public boolean getCertificateNeedsVerification() {
        return this.m_certificateNeedsVerification;
    }
    
    public boolean isAvailable() {
        boolean b = false;
        synchronized (this) {
            b = ((!this.m_certificateNeedsVerification || this.m_parentCertificate != null) && this.m_enabled);
        }
        return b;
    }
    
    public boolean isEnabled() {
        return this.m_enabled;
    }
    
    public void setEnabled(final boolean enabled) {
        this.m_enabled = enabled;
        this.m_certPath.resetVerification();
    }
    
    public void enableOnlyHardRemovable() {
        this.m_onlyHardRemovable = true;
    }
    
    public boolean isOnlyHardRemovable() {
        return this.m_onlyHardRemovable;
    }
    
    public void enableNotRemovable() {
        this.m_bNotRemovable = true;
    }
    
    public boolean isNotRemovable() {
        return this.m_bNotRemovable;
    }
    
    public Vector getLockList() {
        return this.m_lockList;
    }
    
    public CertificateInfoStructure getInfoStructure() {
        return new CertificateInfoStructure(this.m_certPath, this.m_parentCertificate, this.m_certificateType, this.m_enabled, this.m_certificateNeedsVerification, this.m_onlyHardRemovable, this.m_bNotRemovable);
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("CertificateContainer");
        synchronized (this) {
            XMLUtil.setAttribute(element, "removable", !this.m_bNotRemovable);
            final Element element2 = document.createElement("CertificateType");
            XMLUtil.setValue(element2, this.m_certificateType);
            final Element element3 = document.createElement("CertificateNeedsVerification");
            XMLUtil.setValue(element3, this.m_certificateNeedsVerification);
            final Element element4 = document.createElement("CertificateEnabled");
            XMLUtil.setValue(element4, this.m_enabled);
            final Element element5 = document.createElement("CertificateData");
            element5.appendChild(this.m_certPath.toXmlElement(document));
            element.appendChild(element2);
            element.appendChild(element3);
            element.appendChild(element4);
            element.appendChild(element5);
        }
        return element;
    }
    
    public boolean equals(final Object o) {
        return this == o || (o != null && o instanceof CertificateContainer && this.m_certPath.getFirstCertificate().getId().equals(((CertificateContainer)o).getCertificate().getId()));
    }
    
    public String getId() {
        return this.m_certPath.getFirstCertificate().getId();
    }
    
    public int hashCode() {
        return this.m_certPath.getFirstCertificate().hashCode();
    }
}
