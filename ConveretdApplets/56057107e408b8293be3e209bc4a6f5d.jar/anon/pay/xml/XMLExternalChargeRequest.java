// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import java.util.Enumeration;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import anon.util.XMLUtil;
import java.util.Vector;
import anon.util.IXMLEncodable;

public class XMLExternalChargeRequest implements IXMLEncodable
{
    private Vector m_chargeRequest;
    private String m_password;
    
    public XMLExternalChargeRequest() {
        this.m_chargeRequest = new Vector();
    }
    
    public XMLExternalChargeRequest(final String s) throws Exception {
        this.m_chargeRequest = new Vector();
        this.setValues(XMLUtil.toXMLDocument(s).getDocumentElement());
    }
    
    public XMLExternalChargeRequest(final char[] array) throws Exception {
        this(new String(array));
    }
    
    public XMLExternalChargeRequest(final byte[] array) throws Exception {
        this.m_chargeRequest = new Vector();
        this.setValues(XMLUtil.toXMLDocument(array).getDocumentElement());
    }
    
    public XMLExternalChargeRequest(final Document document) {
        this.m_chargeRequest = new Vector();
        try {
            this.setValues(document.getDocumentElement());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void setPassword(final String password) {
        this.m_password = password;
    }
    
    public String getPassword() {
        return this.m_password;
    }
    
    public void addCharge(final String s, final String s2, final String s3) {
        this.m_chargeRequest.addElement(new String[] { s, s2, s3 });
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("ExternalChargeRequest");
        final Element element2 = document.createElement("Password");
        element2.appendChild(document.createTextNode(this.m_password));
        element.appendChild(element2);
        for (int i = 0; i < this.m_chargeRequest.size(); ++i) {
            final String[] array = this.m_chargeRequest.elementAt(i);
            final Element element3 = document.createElement("Charge");
            final Element element4 = document.createElement("TransferNumber");
            element4.appendChild(document.createTextNode(array[0]));
            element3.appendChild(element4);
            final Element element5 = document.createElement("Currency");
            element5.appendChild(document.createTextNode(array[1]));
            element3.appendChild(element5);
            final Element element6 = document.createElement("Amount");
            element6.appendChild(document.createTextNode(array[2]));
            element3.appendChild(element6);
            element.appendChild(element3);
        }
        return element;
    }
    
    protected void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals("ExternalChargeRequest")) {
            throw new Exception("ExternalChargeRequest wrong XML structure");
        }
        final Node firstChildByName = XMLUtil.getFirstChildByName(element, "Password");
        if (firstChildByName != null) {
            this.m_password = XMLUtil.parseValue(firstChildByName, "");
        }
        final NodeList elementsByTagName = element.getElementsByTagName("Charge");
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            String value = "";
            String value2 = "";
            String value3 = "";
            final Node firstChildByName2 = XMLUtil.getFirstChildByName(elementsByTagName.item(i), "TransferNumber");
            if (firstChildByName2 != null) {
                value = XMLUtil.parseValue(firstChildByName2, "");
            }
            final Node firstChildByName3 = XMLUtil.getFirstChildByName(elementsByTagName.item(i), "Currency");
            if (firstChildByName3 != null) {
                value2 = XMLUtil.parseValue(firstChildByName3, "");
            }
            final Node firstChildByName4 = XMLUtil.getFirstChildByName(elementsByTagName.item(i), "Amount");
            if (firstChildByName4 != null) {
                value3 = XMLUtil.parseValue(firstChildByName4, "");
            }
            this.addCharge(value, value2, value3);
        }
    }
    
    public Enumeration getChargeLines() {
        return this.m_chargeRequest.elements();
    }
}
