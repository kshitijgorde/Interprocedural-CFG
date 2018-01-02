// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import anon.util.XMLParseException;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import java.io.File;
import org.w3c.dom.Element;

public class JAPMinVersion extends AbstractDistributableDatabaseEntry
{
    public static final String DEFAULT_ID = "JAPMinVersion";
    private static final long DATABASE_TIMEOUT = Long.MAX_VALUE;
    private ServiceSoftware m_japSoftware;
    private long m_lastUpdate;
    private Element m_xmlStructure;
    private byte[] m_bytesPostData;
    
    public static String getXmlElementName() {
        return "Jap";
    }
    
    public JAPMinVersion(final File file) throws Exception {
        this(XMLUtil.readXMLDocument(file).getDocumentElement());
    }
    
    public JAPMinVersion(final Element xmlStructure) throws Exception {
        super(Long.MAX_VALUE);
        final Element element = (Element)XMLUtil.getFirstChildByName(xmlStructure, ServiceSoftware.getXmlElementName());
        if (element == null) {
            throw new Exception("JAPMinVersion: Constructor: Error in XML structure: No software node.");
        }
        this.m_japSoftware = new ServiceSoftware(element);
        final String version = this.m_japSoftware.getVersion();
        if (version.charAt(2) != '.' || version.charAt(5) != '.') {
            throw new XMLParseException("Invalid version number format: " + version);
        }
        this.m_lastUpdate = XMLUtil.parseValue(XMLUtil.getFirstChildByName(xmlStructure, "LastUpdate"), -1L);
        if (this.m_lastUpdate == -1L) {
            this.m_lastUpdate = System.currentTimeMillis();
        }
        this.m_xmlStructure = xmlStructure;
        this.m_bytesPostData = super.getPostData();
    }
    
    public String getId() {
        return "JAPMinVersion";
    }
    
    public long getLastUpdate() {
        return this.m_lastUpdate;
    }
    
    public long getVersionNumber() {
        return this.m_lastUpdate;
    }
    
    public ServiceSoftware getJapSoftware() {
        return this.m_japSoftware;
    }
    
    public String getPostFile() {
        return "/currentjapversion";
    }
    
    public Element getXmlStructure() {
        return this.m_xmlStructure;
    }
    
    public byte[] getPostData() {
        return this.m_bytesPostData;
    }
}
