// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import anon.util.XMLUtil;
import java.sql.Timestamp;
import anon.util.IXMLEncodable;

public class XMLPayRequest implements IXMLEncodable
{
    private XMLEasyCC m_cc;
    private Timestamp m_balanceNewerThan;
    private boolean m_bIsAccountRequest;
    private boolean m_bInitialCCRequest;
    private int prepaidBytes;
    public static final Object XML_ELEMENT_NAME;
    
    public XMLPayRequest(final String s) throws Exception {
        this.m_cc = null;
        this.m_balanceNewerThan = null;
        this.m_bInitialCCRequest = false;
        this.prepaidBytes = 0;
        this.setValues(XMLUtil.toXMLDocument(s).getDocumentElement());
    }
    
    public XMLPayRequest(final byte[] array) throws Exception {
        this.m_cc = null;
        this.m_balanceNewerThan = null;
        this.m_bInitialCCRequest = false;
        this.prepaidBytes = 0;
        this.setValues(XMLUtil.toXMLDocument(array).getDocumentElement());
    }
    
    public XMLPayRequest(final Document document) throws Exception {
        this.m_cc = null;
        this.m_balanceNewerThan = null;
        this.m_bInitialCCRequest = false;
        this.prepaidBytes = 0;
        this.setValues(document.getDocumentElement());
    }
    
    public XMLPayRequest(final Element values) throws Exception {
        this.m_cc = null;
        this.m_balanceNewerThan = null;
        this.m_bInitialCCRequest = false;
        this.prepaidBytes = 0;
        this.setValues(values);
    }
    
    private void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals(XMLPayRequest.XML_ELEMENT_NAME) || !element.getAttribute("version").equals("1.0")) {
            throw new Exception("PayRequest wrong format or wrong version number");
        }
        final Element element2 = (Element)XMLUtil.getFirstChildByName(element, "BalanceRequest");
        if (element2 != null) {
            this.m_balanceNewerThan = Timestamp.valueOf(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element2, "NewerThan"), ""));
        }
        else {
            this.m_balanceNewerThan = null;
        }
        final Element element3 = (Element)XMLUtil.getFirstChildByName(element, "CC");
        if (element3 != null) {
            this.m_cc = new XMLEasyCC(element3);
        }
        else {
            this.m_cc = null;
        }
        this.m_bInitialCCRequest = XMLUtil.parseAttribute(element, "initialCC", false);
        this.prepaidBytes = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "PrepaidBytes"), 0);
        if (XMLUtil.getFirstChildByName(element, "AccountRequest") != null) {
            this.m_bIsAccountRequest = true;
        }
        else {
            this.m_bIsAccountRequest = false;
        }
    }
    
    public Element toXmlElement(final Document document) {
        return null;
    }
    
    public XMLEasyCC getCC() {
        return this.m_cc;
    }
    
    public Timestamp getBalanceTimestamp() {
        return this.m_balanceNewerThan;
    }
    
    public boolean isAccountRequest() {
        return this.m_bIsAccountRequest;
    }
    
    public boolean isInitialCCRequest() {
        return this.m_bInitialCCRequest;
    }
    
    public int getPrepaidBytes() {
        return this.prepaidBytes;
    }
    
    static {
        XML_ELEMENT_NAME = "PayRequest";
    }
}
