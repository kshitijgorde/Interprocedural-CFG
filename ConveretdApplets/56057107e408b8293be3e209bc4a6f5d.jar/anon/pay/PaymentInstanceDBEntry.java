// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay;

import java.util.Random;
import java.util.Date;
import org.w3c.dom.Document;
import logging.LogHolder;
import logging.LogType;
import anon.crypto.SignatureCreator;
import anon.infoservice.ServiceSoftware;
import java.util.Enumeration;
import anon.crypto.JAPCertificate;
import org.w3c.dom.NodeList;
import anon.infoservice.ListenerInterface;
import anon.crypto.SignatureVerifier;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import anon.util.XMLParseException;
import java.util.Vector;
import anon.crypto.MultiCertPath;
import anon.crypto.XMLSignature;
import org.w3c.dom.Element;
import anon.crypto.IVerifyable;
import anon.infoservice.AbstractDistributableCertifiedDatabaseEntry;

public class PaymentInstanceDBEntry extends AbstractDistributableCertifiedDatabaseEntry implements IVerifyable
{
    public static final String XML_ELEMENT_NAME = "PaymentInstance";
    public static final String XML_ELEMENT_CONTAINER_NAME = "PaymentInstances";
    private static final String XML_ELEM_NAME = "Name";
    private static final String XML_ELEM_CERT = "Certificate";
    private static final String XML_ELEM_NET = "Network";
    private String m_strPaymentInstanceId;
    private Element m_xmlDescription;
    private XMLSignature m_signature;
    private MultiCertPath m_certPath;
    private long m_creationTimeStamp;
    private long m_serialNumber;
    private Vector m_listenerInterfaces;
    private String m_name;
    private String m_strOrganisation;
    
    public PaymentInstanceDBEntry(final Element element) throws XMLParseException {
        this(element, 0L);
    }
    
    public PaymentInstanceDBEntry(final Element xmlDescription, final long n) throws XMLParseException {
        super((n == 0L) ? (System.currentTimeMillis() + 900000L) : n);
        XMLUtil.assertNotNull(xmlDescription);
        this.m_xmlDescription = xmlDescription;
        if (XMLUtil.parseValue(XMLUtil.getFirstChildByName(xmlDescription, "Name"), null) == null) {
            throw new XMLParseException("Name");
        }
        this.m_signature = SignatureVerifier.getInstance().getVerifiedXml(xmlDescription, 4);
        if (this.m_signature != null) {
            this.m_certPath = this.m_signature.getMultiCertPath();
            if (this.m_certPath != null) {
                this.m_strOrganisation = this.m_certPath.getSubject().getOrganisation();
            }
        }
        this.m_strPaymentInstanceId = xmlDescription.getAttribute("id");
        if (!this.checkId()) {
            throw new XMLParseException(xmlDescription.getNodeName(), "Invalid Payment-Instance ID: " + this.m_strPaymentInstanceId);
        }
        this.m_name = XMLUtil.parseValue(XMLUtil.getFirstChildByName(xmlDescription, "Name"), "");
        this.m_creationTimeStamp = XMLUtil.parseValue(XMLUtil.getFirstChildByName(xmlDescription, "LastUpdate"), -1L);
        if (this.m_creationTimeStamp == -1L) {
            throw new XMLParseException("LastUpdate");
        }
        this.m_serialNumber = XMLUtil.parseAttribute(xmlDescription, "serial", this.m_creationTimeStamp);
        final Node firstChildByName = XMLUtil.getFirstChildByName(XMLUtil.getFirstChildByName(xmlDescription, "Network"), "ListenerInterfaces");
        XMLUtil.assertNotNull(firstChildByName);
        final NodeList elementsByTagName = ((Element)firstChildByName).getElementsByTagName("ListenerInterface");
        if (elementsByTagName.getLength() == 0) {
            throw new XMLParseException("ListenerInterface");
        }
        this.m_listenerInterfaces = new Vector();
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            this.m_listenerInterfaces.addElement(new ListenerInterface((Element)elementsByTagName.item(i)));
        }
    }
    
    public PaymentInstanceDBEntry(final String strPaymentInstanceId, final String name, final JAPCertificate japCertificate, final Enumeration enumeration, final String s, final long creationTimeStamp, final long serialNumber) {
        super(System.currentTimeMillis() + 900000L);
        this.m_strPaymentInstanceId = strPaymentInstanceId;
        this.m_creationTimeStamp = creationTimeStamp;
        this.m_serialNumber = serialNumber;
        this.m_name = name;
        final Document document = XMLUtil.createDocument();
        final Element element = document.createElement("PaymentInstance");
        document.appendChild(element);
        XMLUtil.setAttribute(element, "id", this.m_strPaymentInstanceId);
        XMLUtil.setAttribute(element, "serial", this.m_serialNumber);
        final Element element2 = document.createElement("Name");
        XMLUtil.setValue(element2, this.m_name);
        element.appendChild(element2);
        element.appendChild(new ServiceSoftware(s).toXmlElement(document));
        final Element element3 = document.createElement("Network");
        element.appendChild(element3);
        final Element element4 = document.createElement("ListenerInterfaces");
        element3.appendChild(element4);
        while (enumeration.hasMoreElements()) {
            element4.appendChild(enumeration.nextElement().toXmlElement(document));
        }
        final Element element5 = document.createElement("LastUpdate");
        XMLUtil.setValue(element5, this.m_creationTimeStamp);
        element.appendChild(element5);
        if (japCertificate != null) {
            final Element element6 = document.createElement("Certificate");
            element.appendChild(element6);
            element6.appendChild(japCertificate.toXmlElement(document));
            this.m_signature = SignatureCreator.getInstance().getSignedXml(4, element);
            if (this.m_signature != null) {
                this.m_certPath = this.m_signature.getMultiCertPath();
            }
            if (this.m_certPath == null) {
                LogHolder.log(2, LogType.MISC, "Document could not be signed!");
            }
            this.m_strOrganisation = japCertificate.getSubject().getOrganisation();
        }
        this.m_xmlDescription = element;
    }
    
    public boolean isPersistanceDeletionAllowed() {
        return XMLUtil.getStorageMode() == 2;
    }
    
    public void deletePersistence() {
        if (this.isPersistanceDeletionAllowed()) {
            this.m_xmlDescription = null;
            this.m_signature = null;
        }
    }
    
    public boolean isVerified() {
        return this.m_certPath != null && this.m_certPath.isVerified();
    }
    
    public boolean isValid() {
        return this.m_certPath != null && this.m_certPath.isValid(new Date());
    }
    
    public XMLSignature getSignature() {
        return this.m_signature;
    }
    
    public MultiCertPath getCertPath() {
        return this.m_certPath;
    }
    
    public String toString() {
        return this.getName();
    }
    
    public String getOrganisation() {
        return this.m_strOrganisation;
    }
    
    public String getId() {
        return this.m_strPaymentInstanceId;
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof PaymentInstanceDBEntry) || o == null) {
            return false;
        }
        final PaymentInstanceDBEntry paymentInstanceDBEntry = (PaymentInstanceDBEntry)o;
        return paymentInstanceDBEntry.getId() == this.getId() || paymentInstanceDBEntry.getId().equals(this.getId());
    }
    
    public int hashCode() {
        if (this.m_strPaymentInstanceId == null) {
            return 0;
        }
        return this.m_strPaymentInstanceId.hashCode();
    }
    
    public String getName() {
        return this.m_name;
    }
    
    public Enumeration getListenerInterfaces() {
        final Random random = new Random();
        final Vector vector = (Vector)this.m_listenerInterfaces.clone();
        final Vector<Object> vector2 = new Vector<Object>();
        while (vector.size() > 0) {
            final int abs = Math.abs(random.nextInt() % vector.size());
            vector2.addElement(vector.elementAt(abs));
            vector.removeElementAt(abs);
        }
        return vector2.elements();
    }
    
    public long getVersionNumber() {
        return this.m_serialNumber;
    }
    
    public long getLastUpdate() {
        return this.m_creationTimeStamp;
    }
    
    public String getPostFile() {
        return "/paymentinstance";
    }
    
    public Element getXmlStructure() {
        return this.m_xmlDescription;
    }
}
