// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import org.w3c.dom.Document;
import anon.util.XMLParseException;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import anon.util.IXMLEncodable;

public abstract class AbstractMarkedMessageIDDBEntry extends AbstractDatabaseEntry implements IXMLEncodable
{
    private long m_serial;
    private String m_id;
    private long m_creationTimeStamp;
    
    public AbstractMarkedMessageIDDBEntry(final MessageDBEntry messageDBEntry) {
        super(Long.MAX_VALUE);
        this.m_serial = messageDBEntry.getVersionNumber();
        this.m_id = messageDBEntry.getId();
        this.m_creationTimeStamp = messageDBEntry.getLastUpdate();
    }
    
    public AbstractMarkedMessageIDDBEntry(final Element element) throws XMLParseException {
        super(Long.MAX_VALUE);
        XMLUtil.assertNodeName(element, this.getXmlElementName());
        this.m_serial = XMLUtil.parseAttribute(element, "serial", -1);
        this.m_id = XMLUtil.parseAttribute(element, "id", null);
        this.m_creationTimeStamp = XMLUtil.parseAttribute(element, "lastUpdate", System.currentTimeMillis());
        if (this.m_serial < 0L || this.m_id == null) {
            throw new XMLParseException(this.getXmlElementName(), "Illegal attribute values!");
        }
    }
    
    public abstract String getXmlElementName();
    
    public final long getVersionNumber() {
        return this.m_serial;
    }
    
    public final String getId() {
        return this.m_id;
    }
    
    public final long getLastUpdate() {
        return this.m_creationTimeStamp;
    }
    
    public final Element toXmlElement(final Document document) {
        if (document == null) {
            return null;
        }
        final Element element = document.createElement(this.getXmlElementName());
        XMLUtil.setAttribute(element, "serial", this.m_serial);
        XMLUtil.setAttribute(element, "id", this.m_id);
        XMLUtil.setAttribute(element, "lastUpdate", this.m_creationTimeStamp);
        return element;
    }
}
