// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import anon.util.XMLParseException;
import anon.util.XMLUtil;
import org.w3c.dom.Node;
import anon.util.IXMLEncodable;

public class ServiceSoftware implements IXMLEncodable
{
    private String m_strVersion;
    
    public static String getXmlElementName() {
        return "Software";
    }
    
    public ServiceSoftware(final Node node) throws XMLParseException {
        this.m_strVersion = XMLUtil.parseValue(XMLUtil.getFirstChildByName(node, "Version"), null);
        if (this.m_strVersion == null) {
            throw new XMLParseException("Version");
        }
    }
    
    public ServiceSoftware(final String strVersion) {
        this.m_strVersion = strVersion;
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement(getXmlElementName());
        final Element element2 = document.createElement("Version");
        XMLUtil.setValue(element2, this.m_strVersion);
        element.appendChild(element2);
        return element;
    }
    
    public String getVersion() {
        return this.m_strVersion;
    }
}
