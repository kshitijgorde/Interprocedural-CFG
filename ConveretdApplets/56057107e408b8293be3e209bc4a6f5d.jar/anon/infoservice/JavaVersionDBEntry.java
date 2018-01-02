// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import java.util.Enumeration;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import anon.util.XMLParseException;
import org.w3c.dom.Element;
import java.net.URL;

public class JavaVersionDBEntry extends AbstractDistributableDatabaseEntry
{
    public static final String CURRENT_JAVA_VENDOR;
    public static final String CURRENT_JAVA_VERSION;
    public static final String HTTP_REQUEST_STRING = "/currentjavaversion";
    public static final String HTTP_SERIALS_REQUEST_STRING = "/currentjavaversionSerials";
    public static final String PROPERTY_NAME = "jreVersionsFileName";
    public static final String VENDOR_ID_SUN_JAVA = "Sun";
    public static final String VENDOR_ID_BLACKDOWN_JAVA = "Blackdown";
    public static final String XML_ELEMENT_NAME = "JavaVersion";
    public static final String XML_ELEMENT_CONTAINER_NAME = "JavaVersionInfos";
    private static final String OS_NAME;
    private static final String XML_ATTR_SUPPORT_FROM_VERSION = "supportFromVersion";
    private static final String XML_ATTR_VENDOR = "vendor";
    private static final String XML_ATTR_OPERATING_SYSTEM = "os";
    private static final String XML_ELEM_VERSION = "LatestVersion";
    private static final String XML_ATTR_VERSION_NAME = "name";
    private static final String XML_ATTR_FORCE = "force";
    private static final String XML_ELEM_DOWNLOAD_URL = "DownloadURL";
    private static final String XML_ELEM_VENDOR_LONG = "VendorLongName";
    private static final String XML_ELEM_LAST_UPDATE = "LastUpdate";
    private static final String[] VENDOR_IDS;
    private static final long TIMEOUT = Long.MAX_VALUE;
    private long m_lastUpdate;
    private String m_latestVersion;
    private String m_lastSupportedVersion;
    private String m_vendor;
    private URL m_downloadURL;
    private String m_vendorLongName;
    private String m_versionName;
    private boolean m_bForce;
    private Element m_xmlDescription;
    static /* synthetic */ Class class$anon$infoservice$JavaVersionDBEntry;
    
    public JavaVersionDBEntry(final Element xmlDescription) throws XMLParseException {
        super(Long.MAX_VALUE);
        if (xmlDescription == null || !xmlDescription.getNodeName().equals("JavaVersion")) {
            throw new XMLParseException("##__root__##");
        }
        this.m_vendor = XMLUtil.parseAttribute(xmlDescription, "vendor", null);
        if (!checkVendor(this.m_vendor)) {
            throw new XMLParseException("JavaVersion", "Unknown vendor!");
        }
        this.m_lastSupportedVersion = XMLUtil.parseAttribute(xmlDescription, "supportFromVersion", "");
        final NodeList elementsByTagName = xmlDescription.getElementsByTagName("LatestVersion");
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            final String attribute = XMLUtil.parseAttribute(elementsByTagName.item(i), "os", "");
            if (this.m_latestVersion != null || attribute.length() != 0) {
                if (JavaVersionDBEntry.OS_NAME.indexOf(attribute) < 0) {
                    continue;
                }
            }
            try {
                this.m_latestVersion = XMLUtil.parseValue(elementsByTagName.item(i), null);
                this.m_versionName = XMLUtil.parseAttribute(elementsByTagName.item(i), "name", null);
                this.m_bForce = XMLUtil.parseAttribute(elementsByTagName.item(i), "force", false);
            }
            catch (Exception ex) {}
        }
        if (this.m_latestVersion == null) {
            throw new XMLParseException("LatestVersion");
        }
        this.m_lastUpdate = XMLUtil.parseValue(XMLUtil.getFirstChildByName(xmlDescription, "LastUpdate"), -1L);
        if (this.m_lastUpdate == -1L) {
            this.m_lastUpdate = System.currentTimeMillis();
        }
        final NodeList elementsByTagName2 = xmlDescription.getElementsByTagName("DownloadURL");
        for (int j = 0; j < elementsByTagName2.getLength(); ++j) {
            final String attribute2 = XMLUtil.parseAttribute(elementsByTagName2.item(j), "os", "");
            if (this.m_downloadURL != null || attribute2.length() != 0) {
                if (JavaVersionDBEntry.OS_NAME.indexOf(attribute2) < 0) {
                    continue;
                }
            }
            try {
                this.m_downloadURL = new URL(XMLUtil.parseValue(elementsByTagName2.item(j), null));
            }
            catch (Exception ex2) {}
        }
        if (this.m_downloadURL == null) {
            throw new XMLParseException("DownloadURL");
        }
        final Node firstChildByName = XMLUtil.getFirstChildByName(xmlDescription, "VendorLongName");
        try {
            this.m_vendorLongName = XMLUtil.parseValue(firstChildByName, null);
        }
        catch (Exception ex3) {}
        this.m_xmlDescription = xmlDescription;
    }
    
    public boolean isUpdateForced() {
        return this.m_bForce;
    }
    
    public static JavaVersionDBEntry getNewJavaVersion() {
        final Enumeration entrySnapshotAsEnumeration = Database.getInstance((JavaVersionDBEntry.class$anon$infoservice$JavaVersionDBEntry == null) ? (JavaVersionDBEntry.class$anon$infoservice$JavaVersionDBEntry = class$("anon.infoservice.JavaVersionDBEntry")) : JavaVersionDBEntry.class$anon$infoservice$JavaVersionDBEntry).getEntrySnapshotAsEnumeration();
        while (entrySnapshotAsEnumeration.hasMoreElements()) {
            final JavaVersionDBEntry javaVersionDBEntry = entrySnapshotAsEnumeration.nextElement();
            if (javaVersionDBEntry.isJavaTooOld()) {
                return javaVersionDBEntry;
            }
        }
        return null;
    }
    
    public boolean isJavaTooOld() {
        return this.isJavaOK(false);
    }
    
    public boolean isJavaNoMoreSupported() {
        return this.isJavaOK(true);
    }
    
    private boolean isJavaOK(final boolean b) {
        if (JavaVersionDBEntry.CURRENT_JAVA_VENDOR == null) {
            return false;
        }
        final String lowerCase = this.getVendor().toLowerCase();
        final String lowerCase2 = JavaVersionDBEntry.CURRENT_JAVA_VENDOR.toLowerCase();
        return lowerCase2.indexOf("microsoft") >= 0 || (lowerCase2.indexOf(lowerCase) >= 0 && (JavaVersionDBEntry.CURRENT_JAVA_VERSION == null || JavaVersionDBEntry.CURRENT_JAVA_VERSION.compareTo(b ? this.getLastSupportedJREVersion() : this.getJREVersion()) < 0));
    }
    
    public String getLastSupportedJREVersion() {
        return this.m_lastSupportedVersion;
    }
    
    public URL getDownloadURL() {
        return this.m_downloadURL;
    }
    
    public Element getXmlStructure() {
        return this.m_xmlDescription;
    }
    
    public String getJREVersion() {
        return this.m_latestVersion;
    }
    
    public String getJREVersionName() {
        return this.m_versionName;
    }
    
    public long getVersionNumber() {
        return this.m_lastUpdate;
    }
    
    public long getLastUpdate() {
        return this.m_lastUpdate;
    }
    
    public String getVendor() {
        return this.m_vendor;
    }
    
    public String getVendorLongName() {
        if (this.m_vendorLongName == null || this.m_vendorLongName.trim().length() == 0) {
            return this.m_vendor;
        }
        return this.m_vendorLongName;
    }
    
    public String getId() {
        return this.m_vendor;
    }
    
    public String getPostFile() {
        return "/currentjavaversion";
    }
    
    private static boolean checkVendor(final String s) {
        if (s == null) {
            return false;
        }
        for (int i = 0; i < JavaVersionDBEntry.VENDOR_IDS.length; ++i) {
            if (JavaVersionDBEntry.VENDOR_IDS[i].equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        CURRENT_JAVA_VENDOR = System.getProperty("java.vendor");
        CURRENT_JAVA_VERSION = System.getProperty("java.version");
        OS_NAME = System.getProperty("os.name", "");
        VENDOR_IDS = new String[] { "Sun", "Blackdown" };
    }
}
