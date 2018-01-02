// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import org.w3c.dom.Document;
import anon.util.ClassUtil;
import anon.util.XMLParseException;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import anon.util.IXMLEncodable;

public abstract class AbstractCascadeIDEntry extends AbstractDatabaseEntry implements IXMLEncodable
{
    private static final String XML_ID = "ID";
    private static final String XML_CASCADE_ID = "CascadeID";
    private static final String XML_ATTR_UPDATE_TIME = "updateTime";
    private static final String XML_ATTR_EXPIRE_TIME = "expireTime";
    private String m_ID;
    private long m_version;
    private String m_cascadeID;
    
    public AbstractCascadeIDEntry(final MixCascade mixCascade, final long n) throws IllegalArgumentException {
        super(n);
        if (mixCascade == null) {
            throw new IllegalArgumentException("Given cascade is null!");
        }
        this.m_ID = mixCascade.getMixIDsAsString();
        this.m_version = System.currentTimeMillis();
        this.m_cascadeID = mixCascade.getId();
    }
    
    public AbstractCascadeIDEntry(final AbstractCascadeIDEntry abstractCascadeIDEntry, final long n) throws IllegalArgumentException {
        super(n);
        if (abstractCascadeIDEntry == null) {
            throw new IllegalArgumentException("Given cascade is null!");
        }
        this.m_ID = abstractCascadeIDEntry.getId();
        this.m_version = System.currentTimeMillis();
        this.m_cascadeID = abstractCascadeIDEntry.getCascadeId();
    }
    
    public AbstractCascadeIDEntry(final Element element) throws XMLParseException {
        super(XMLUtil.parseAttribute(element, "expireTime", 0L));
        if (element == null) {
            throw new XMLParseException("##__null__##");
        }
        if (!element.getNodeName().equals(ClassUtil.getShortClassName(this.getClass()))) {
            throw new XMLParseException("##__root__##");
        }
        this.m_version = XMLUtil.parseAttribute(element, "updateTime", 0);
        this.m_ID = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "ID"), null);
        this.m_cascadeID = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "CascadeID"), null);
        if (this.m_ID == null || this.m_cascadeID == null) {
            throw new XMLParseException("This is no valid " + ClassUtil.getShortClassName(this.getClass()) + " node!");
        }
    }
    
    public final String getCascadeId() {
        return this.m_cascadeID;
    }
    
    public final String getId() {
        return this.m_ID;
    }
    
    public long getLastUpdate() {
        return this.m_version;
    }
    
    public final long getVersionNumber() {
        return this.m_version;
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement(ClassUtil.getShortClassName(this.getClass()));
        final Element element2 = document.createElement("ID");
        XMLUtil.setAttribute(element, "updateTime", this.m_version);
        XMLUtil.setAttribute(element, "expireTime", this.getExpireTime());
        XMLUtil.setValue(element2, this.m_ID);
        element.appendChild(element2);
        final Element element3 = document.createElement("CascadeID");
        XMLUtil.setValue(element3, this.m_cascadeID);
        element.appendChild(element3);
        return element;
    }
}
