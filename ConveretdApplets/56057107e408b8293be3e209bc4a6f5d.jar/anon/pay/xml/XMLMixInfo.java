// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import anon.util.XMLUtil;
import org.w3c.dom.Document;
import java.sql.Timestamp;
import anon.util.IXMLEncodable;

public class XMLMixInfo implements IXMLEncodable
{
    private String m_mixCert;
    private int m_balance;
    private Timestamp m_updateTime;
    private int m_operatorId;
    private int m_id;
    private Document m_docTheMixInfo;
    
    public XMLMixInfo(final String mixCert, final int balance, final Timestamp timestamp, final int operatorId, final int id) {
        this.m_mixCert = mixCert;
        this.m_updateTime = timestamp;
        this.m_balance = balance;
        this.m_updateTime = timestamp;
        this.m_operatorId = operatorId;
        this.m_id = id;
        this.m_docTheMixInfo = XMLUtil.createDocument();
    }
    
    public XMLMixInfo(final String s) throws Exception {
        final Document xmlDocument = XMLUtil.readXMLDocument(new ByteArrayInputStream(s.getBytes()));
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docTheMixInfo = xmlDocument;
    }
    
    public XMLMixInfo(final byte[] array) throws Exception {
        final Document xmlDocument = XMLUtil.readXMLDocument(new ByteArrayInputStream(array));
        this.setValues(xmlDocument.getDocumentElement());
        this.m_docTheMixInfo = xmlDocument;
    }
    
    public XMLMixInfo(final Element values) throws Exception {
        this.setValues(values);
        (this.m_docTheMixInfo = XMLUtil.createDocument()).appendChild(XMLUtil.importNode(this.m_docTheMixInfo, values, true));
    }
    
    private void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals("MixInfo")) {
            throw new Exception("XMLMixInfo: cannot parse, wrong xml format!");
        }
        if (!element.getAttribute("version").equals("1.0")) {
            throw new Exception("XMLMixInfo: cannot parse, cert version is " + element.getAttribute("version") + " but 1.0 was expected.");
        }
        this.m_mixCert = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "MixCert"), "error");
        if (this.m_mixCert.equals("error")) {
            throw new Exception("XMLMixInfo: cannot parse the MixCertificate");
        }
        this.m_balance = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Balance"), -1000);
        if (this.m_balance == -1000) {
            throw new Exception("XMLMixInfo: cannot parse balance");
        }
        this.m_updateTime = Timestamp.valueOf(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "updateTime"), "0"));
        this.m_operatorId = XMLUtil.parseValue(XMLUtil.getLastChildByName(element, "operatorId"), -1);
        if (this.m_operatorId == -1) {
            throw new Exception("XMLMixInfo: cannot parse operator id");
        }
        this.m_id = XMLUtil.parseValue(XMLUtil.getLastChildByName(element, "id"), -1);
        if (this.m_id == -1) {
            throw new Exception("XMLMixInfo: cannot parse id");
        }
    }
    
    public Timestamp getUpdateTime() {
        return this.m_updateTime;
    }
    
    public int getBalance() {
        return this.m_balance;
    }
    
    public String getMixCert() {
        return this.m_mixCert;
    }
    
    public int getOperatorId() {
        return this.m_operatorId;
    }
    
    public int getId() {
        return this.m_id;
    }
    
    public Element toXmlElement(final Document document) {
        try {
            return (Element)XMLUtil.importNode(document, this.m_docTheMixInfo.getDocumentElement(), true);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
