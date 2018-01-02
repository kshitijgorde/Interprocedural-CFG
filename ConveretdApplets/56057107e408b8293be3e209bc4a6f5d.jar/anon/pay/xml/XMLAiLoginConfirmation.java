// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import anon.util.XMLParseException;
import org.w3c.dom.Element;
import anon.util.IXMLEncodable;

public class XMLAiLoginConfirmation implements IXMLEncodable
{
    private int m_code;
    private String m_message;
    public static final String XML_ELEMENT_NAME = "LoginConfirmation";
    
    public XMLAiLoginConfirmation(final Element values) throws XMLParseException {
        this.m_code = -1;
        this.m_message = null;
        this.setValues(values);
    }
    
    private void setValues(final Element element) throws XMLParseException {
        XMLUtil.assertNodeName(element, "LoginConfirmation");
        this.m_code = XMLUtil.parseAttribute(element, "code", -1);
        if (this.m_code == -1) {
            throw new XMLParseException("No or invalid confirmation code for login confirmation specified");
        }
        this.m_message = XMLUtil.parseValue(element, (String)null);
        if (this.m_message == null) {
            throw new XMLParseException("No login confirmation message specified");
        }
    }
    
    public Element toXmlElement(final Document document) {
        return null;
    }
    
    public int getCode() {
        return this.m_code;
    }
    
    public String getMessage() {
        return this.m_message;
    }
}
