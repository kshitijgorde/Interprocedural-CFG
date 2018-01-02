// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import logging.LogHolder;
import logging.LogType;
import java.util.Enumeration;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import anon.util.XMLUtil;
import java.util.Hashtable;
import anon.util.IXMLEncodable;

public class XMLAccountInfo implements IXMLEncodable
{
    public static final String XML_ELEMENT_NAME_COST_CONFIRMATIONS = "CostConfirmations";
    private XMLBalance m_balance;
    private Hashtable m_costConfirmations;
    
    public XMLAccountInfo(final XMLBalance balance) {
        this.m_balance = null;
        this.m_costConfirmations = new Hashtable();
        this.m_balance = balance;
    }
    
    public XMLAccountInfo(final String s) throws Exception {
        this.m_balance = null;
        this.m_costConfirmations = new Hashtable();
        this.setValues(XMLUtil.toXMLDocument(s).getDocumentElement());
    }
    
    public XMLAccountInfo() {
        this.m_balance = null;
        this.m_costConfirmations = new Hashtable();
    }
    
    public XMLAccountInfo(final Element values) throws Exception {
        this.m_balance = null;
        this.m_costConfirmations = new Hashtable();
        this.setValues(values);
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("AccountInfo");
        element.setAttribute("version", "1.1");
        element.appendChild(this.m_balance.toXmlElement(document));
        final Element element2 = document.createElement("CostConfirmations");
        element.appendChild(element2);
        synchronized (this.m_costConfirmations) {
            final Enumeration<XMLEasyCC> elements = this.m_costConfirmations.elements();
            while (elements.hasMoreElements()) {
                element2.appendChild(elements.nextElement().toXmlElement(document));
            }
        }
        return element;
    }
    
    public long addCC(final XMLEasyCC xmlEasyCC) throws Exception {
        long transferredBytes = 0L;
        synchronized (this.m_costConfirmations) {
            final XMLEasyCC xmlEasyCC2 = this.m_costConfirmations.get(xmlEasyCC.getConcatenatedPriceCertHashes());
            if (xmlEasyCC2 != null) {
                transferredBytes = xmlEasyCC2.getTransferredBytes();
            }
            if (xmlEasyCC.getTransferredBytes() >= transferredBytes) {
                this.m_costConfirmations.put(xmlEasyCC.getConcatenatedPriceCertHashes(), xmlEasyCC);
            }
        }
        return xmlEasyCC.getTransferredBytes() - transferredBytes;
    }
    
    private void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals("AccountInfo")) {
            LogHolder.log(2, LogType.PAY, "invalid XML structure: " + XMLUtil.toString(element));
            throw new Exception("XMLAccountInfo wrong XML structure");
        }
        this.m_balance = new XMLBalance((Element)XMLUtil.getFirstChildByName(element, "Balance"));
        for (Element element2 = (Element)XMLUtil.getFirstChildByName(XMLUtil.getFirstChildByName(element, "CostConfirmations"), "CC"); element2 != null; element2 = (Element)XMLUtil.getNextSiblingByName(element2, "CC")) {
            final XMLEasyCC xmlEasyCC = new XMLEasyCC(element2);
            this.m_costConfirmations.put(xmlEasyCC.getConcatenatedPriceCertHashes(), xmlEasyCC);
        }
    }
    
    public XMLBalance getBalance() {
        return this.m_balance;
    }
    
    public XMLEasyCC getCC(final String s) {
        return this.m_costConfirmations.get(s);
    }
    
    public Enumeration getCCs() {
        return ((Hashtable)this.m_costConfirmations.clone()).elements();
    }
    
    public long getAllCCsTransferredBytes() {
        long n = 0L;
        final Enumeration<XMLEasyCC> elements = this.m_costConfirmations.elements();
        while (elements.hasMoreElements()) {
            n += elements.nextElement().getTransferredBytes();
        }
        return n;
    }
    
    public void setBalance(final XMLBalance balance) {
        this.m_balance = balance;
    }
    
    public XMLAccountInfo(final Document document) throws Exception {
        this.m_balance = null;
        this.m_costConfirmations = new Hashtable();
        this.setValues(document.getDocumentElement());
    }
}
