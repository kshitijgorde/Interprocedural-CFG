// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import org.w3c.dom.Document;
import anon.util.Base64;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import anon.util.XMLUtil;
import anon.util.IXMLEncodable;

public class XMLResponse implements IXMLEncodable
{
    private byte[] m_arbResponse;
    
    public XMLResponse(final String s) throws Exception {
        this.setValues(XMLUtil.toXMLDocument(s).getDocumentElement());
    }
    
    public XMLResponse(final byte[] arbResponse) throws Exception {
        this.m_arbResponse = arbResponse;
    }
    
    private void setValues(final Element element) throws Exception {
        if (!element.getTagName().equals("Response")) {
            throw new Exception("XMLResponse wrong xml structure");
        }
        this.m_arbResponse = Base64.decode(XMLUtil.parseValue(element, ""));
    }
    
    public byte[] getResponse() {
        return this.m_arbResponse;
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("Response");
        XMLUtil.setValue(element, Base64.encodeBytes(this.m_arbResponse));
        XMLUtil.createChildElementWithValue(element, "ClientVersion", "00.12.003");
        return element;
    }
}
