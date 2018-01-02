// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import java.util.Enumeration;
import anon.crypto.IVerifyable;
import org.w3c.dom.NodeList;
import anon.util.XMLParseException;
import java.util.Hashtable;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import anon.util.Util;
import anon.util.IXMLEncodable;

public abstract class AbstractDistributableDatabaseEntry extends AbstractDatabaseEntry implements IDistributable, IXMLEncodable
{
    public static final String XML_ATTR_SERIAL = "serial";
    public static final String XML_ATTR_VERIFIED = "verified";
    public static final String XML_ATTR_VALID = "valid";
    public static final String XML_ATTR_LAST_UPDATE = "lastUpdate";
    
    public AbstractDistributableDatabaseEntry(final long n) {
        super(n);
    }
    
    public static String getHttpRequestString(final Class clazz) {
        return Util.getStaticFieldValue(clazz, "HTTP_REQUEST_STRING");
    }
    
    public static String getHttpSerialsRequestString(final Class clazz) {
        return Util.getStaticFieldValue(clazz, "HTTP_SERIALS_REQUEST_STRING");
    }
    
    public abstract Element getXmlStructure();
    
    public byte[] getPostData() {
        return XMLUtil.toString(this.getXmlStructure()).getBytes();
    }
    
    public int getPostEncoding() {
        return 0;
    }
    
    public final Element toXmlElement(final Document document) {
        Element element = null;
        try {
            element = (Element)XMLUtil.importNode(document, this.getXmlStructure(), true);
        }
        catch (Exception ex) {}
        return element;
    }
    
    public abstract String getPostFile();
    
    public static class Serials implements IXMLEncodable
    {
        private static final String XML_ELEMENT_NAME = "Serials";
        private Class m_thisDBEntryClass;
        static /* synthetic */ Class class$anon$infoservice$AbstractDistributableDatabaseEntry;
        
        public Serials(final Class thisDBEntryClass) throws IllegalArgumentException {
            if (thisDBEntryClass == null || !((Serials.class$anon$infoservice$AbstractDistributableDatabaseEntry == null) ? (Serials.class$anon$infoservice$AbstractDistributableDatabaseEntry = class$("anon.infoservice.AbstractDistributableDatabaseEntry")) : Serials.class$anon$infoservice$AbstractDistributableDatabaseEntry).isAssignableFrom(thisDBEntryClass)) {
                throw new IllegalArgumentException("Illegal class argument!");
            }
            this.m_thisDBEntryClass = thisDBEntryClass;
        }
        
        public Hashtable parse(final Element element) throws XMLParseException {
            if (element == null || element.getNodeName() == null || !element.getNodeName().equals("Serials")) {
                throw new XMLParseException("##__null__##");
            }
            final NodeList elementsByTagName = element.getElementsByTagName(XMLUtil.getXmlElementName(this.m_thisDBEntryClass));
            Hashtable<String, SerialDBEntry> hashtable;
            if (elementsByTagName.getLength() > 0) {
                hashtable = new Hashtable<String, SerialDBEntry>(elementsByTagName.getLength());
            }
            else {
                hashtable = new Hashtable<String, SerialDBEntry>();
            }
            for (int i = 0; i < elementsByTagName.getLength(); ++i) {
                final String attribute = XMLUtil.parseAttribute(elementsByTagName.item(i), "id", null);
                if (attribute != null) {
                    final long attribute2 = XMLUtil.parseAttribute(elementsByTagName.item(i), "serial", 0L);
                    final long attribute3 = XMLUtil.parseAttribute(elementsByTagName.item(i), "lastUpdate", 0L);
                    final boolean attribute4 = XMLUtil.parseAttribute(elementsByTagName.item(i), "verified", false);
                    final boolean attribute5 = XMLUtil.parseAttribute(elementsByTagName.item(i), "valid", false);
                    String attribute6 = XMLUtil.parseAttribute(elementsByTagName.item(i), "context", "jondonym");
                    if (attribute6.equals("de.jondos.jondonym")) {
                        attribute6 = "jondonym";
                    }
                    hashtable.put(attribute, new SerialDBEntry(attribute, attribute2, attribute3, attribute4, attribute5, attribute6));
                }
            }
            return hashtable;
        }
        
        public Element toXmlElement(final Document document) {
            if (document == null) {
                return null;
            }
            final Element element = document.createElement("Serials");
            final Enumeration entrySnapshotAsEnumeration = Database.getInstance(this.m_thisDBEntryClass).getEntrySnapshotAsEnumeration();
            while (entrySnapshotAsEnumeration.hasMoreElements()) {
                final AbstractDistributableDatabaseEntry abstractDistributableDatabaseEntry = entrySnapshotAsEnumeration.nextElement();
                if (abstractDistributableDatabaseEntry instanceof IBoostrapable && ((IBoostrapable)abstractDistributableDatabaseEntry).isBootstrap()) {
                    continue;
                }
                if (abstractDistributableDatabaseEntry.getVersionNumber() <= 0L) {}
                final Element element2 = document.createElement(XMLUtil.getXmlElementName(this.m_thisDBEntryClass));
                element.appendChild(element2);
                XMLUtil.setAttribute(element2, "id", abstractDistributableDatabaseEntry.getId());
                XMLUtil.setAttribute(element2, "lastUpdate", abstractDistributableDatabaseEntry.getLastUpdate());
                XMLUtil.setAttribute(element2, "serial", abstractDistributableDatabaseEntry.getVersionNumber());
                if (abstractDistributableDatabaseEntry instanceof IVerifyable) {
                    XMLUtil.setAttribute(element2, "valid", ((IVerifyable)abstractDistributableDatabaseEntry).isValid());
                    XMLUtil.setAttribute(element2, "verified", ((IVerifyable)abstractDistributableDatabaseEntry).isVerified() && ((IVerifyable)abstractDistributableDatabaseEntry).getCertPath().isVerified());
                }
                if (!(abstractDistributableDatabaseEntry instanceof IServiceContextContainer)) {
                    continue;
                }
                final String context = ((IServiceContextContainer)abstractDistributableDatabaseEntry).getContext();
                if (context == null) {
                    continue;
                }
                XMLUtil.setAttribute(element2, "context", context);
            }
            return element;
        }
        
        static /* synthetic */ Class class$(final String s) {
            try {
                return Class.forName(s);
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
    }
    
    public static class SerialDBEntry extends AbstractDatabaseEntry implements IServiceContextContainer
    {
        private String m_id;
        private long m_version;
        private long m_lastUpdate;
        private boolean m_bVerified;
        private boolean m_bValid;
        private String m_context;
        
        public SerialDBEntry(final String id, final long version, final long lastUpdate, final boolean bVerified, final boolean bValid, final String context) {
            super(0L);
            this.m_id = id;
            this.m_version = version;
            this.m_lastUpdate = lastUpdate;
            this.m_bVerified = bVerified;
            this.m_bValid = bValid;
            this.m_context = context;
        }
        
        public boolean isVerified() {
            return this.m_bVerified;
        }
        
        public boolean isValid() {
            return this.m_bValid;
        }
        
        public long getLastUpdate() {
            return this.m_lastUpdate;
        }
        
        public String getId() {
            return this.m_id;
        }
        
        public long getVersionNumber() {
            return this.m_version;
        }
        
        public String getContext() {
            return this.m_context;
        }
        
        public void setContext(final String context) {
            this.m_context = context;
        }
    }
}
