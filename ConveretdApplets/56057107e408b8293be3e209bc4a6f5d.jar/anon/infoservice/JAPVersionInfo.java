// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import org.w3c.dom.NodeList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import java.net.URL;
import java.util.Date;

public class JAPVersionInfo extends AbstractDistributableDatabaseEntry
{
    public static final String ID_BETA = "/japDevelopment.jnlp";
    public static final String ID_STABLE = "/japRelease.jnlp";
    public static final int JAP_RELEASE_VERSION = 1;
    public static final int JAP_DEVELOPMENT_VERSION = 2;
    private static final long DATABASE_TIMEOUT = Long.MAX_VALUE;
    private int m_versionInfoType;
    private String m_version;
    private Date m_releaseDate;
    private String m_jarFileName;
    private URL m_codeBase;
    private String m_lastSupportedJavaVersion;
    private long m_lastUpdate;
    private Element m_xmlStructure;
    static /* synthetic */ Class class$anon$infoservice$JAPVersionInfo;
    
    public static String getXmlElementName() {
        return "jnlp";
    }
    
    public JAPVersionInfo(final Element xmlStructure, final int versionInfoType) throws Exception {
        super(Long.MAX_VALUE);
        this.m_versionInfoType = versionInfoType;
        this.m_version = XMLUtil.parseAttribute(xmlStructure, "version", "");
        this.m_version = this.m_version.trim();
        try {
            final String string = xmlStructure.getAttribute("releaseDate") + " GMT";
            try {
                this.m_releaseDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z").parse(string);
            }
            catch (ParseException ex) {
                this.m_releaseDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").parse(string);
            }
        }
        catch (Exception ex2) {
            this.m_releaseDate = null;
        }
        this.m_codeBase = new URL(xmlStructure.getAttribute("codebase"));
        final Element element = (Element)XMLUtil.getFirstChildByName(xmlStructure, "resources");
        final NodeList elementsByTagName = element.getElementsByTagName("jar");
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            try {
                final Element element2 = (Element)elementsByTagName.item(i);
                if (element2.getAttribute("part").equals("jap")) {
                    this.m_jarFileName = element2.getAttribute("href");
                }
            }
            catch (Exception ex3) {}
        }
        final NodeList elementsByTagName2 = XMLUtil.getElementsByTagName(element, "j2se");
        this.m_lastSupportedJavaVersion = JavaVersionDBEntry.CURRENT_JAVA_VERSION;
        if (elementsByTagName2 != null) {
            for (int j = 0; j < elementsByTagName2.getLength(); ++j) {
                final String attribute = XMLUtil.parseAttribute(elementsByTagName2.item(j), "version", JavaVersionDBEntry.CURRENT_JAVA_VERSION);
                if (this.m_lastSupportedJavaVersion.compareTo(attribute) > 0) {
                    this.m_lastSupportedJavaVersion = attribute;
                }
            }
        }
        final int index = this.m_lastSupportedJavaVersion.indexOf("+");
        if (index > 0) {
            this.m_lastSupportedJavaVersion = this.m_lastSupportedJavaVersion.substring(0, index);
        }
        this.m_lastUpdate = XMLUtil.parseValue(XMLUtil.getFirstChildByName(xmlStructure, "LastUpdate"), -1L);
        if (this.m_lastUpdate == -1L) {
            this.m_lastUpdate = System.currentTimeMillis();
        }
        this.m_xmlStructure = xmlStructure;
    }
    
    public static JAPVersionInfo getRecommendedUpdate(final String s, final boolean b) {
        final JAPVersionInfo japVersionInfo = (JAPVersionInfo)Database.getInstance((JAPVersionInfo.class$anon$infoservice$JAPVersionInfo == null) ? (JAPVersionInfo.class$anon$infoservice$JAPVersionInfo = class$("anon.infoservice.JAPVersionInfo")) : JAPVersionInfo.class$anon$infoservice$JAPVersionInfo).getEntryById("/japRelease.jnlp");
        final JAPVersionInfo japVersionInfo2 = (JAPVersionInfo)Database.getInstance((JAPVersionInfo.class$anon$infoservice$JAPVersionInfo == null) ? (JAPVersionInfo.class$anon$infoservice$JAPVersionInfo = class$("anon.infoservice.JAPVersionInfo")) : JAPVersionInfo.class$anon$infoservice$JAPVersionInfo).getEntryById("/japDevelopment.jnlp");
        if (b) {
            if (japVersionInfo != null && japVersionInfo.getJapVersion().compareTo(s) > 0) {
                return japVersionInfo;
            }
            return null;
        }
        else {
            if (japVersionInfo != null) {
                if (japVersionInfo2 == null && japVersionInfo.getJapVersion().compareTo(s) > 0) {
                    return japVersionInfo;
                }
                if (japVersionInfo2 != null && (japVersionInfo.getJapVersion().equals(japVersionInfo2.getJapVersion()) || (japVersionInfo.getJapVersion().compareTo(s) > 0 && japVersionInfo2.getJapVersion().compareTo(s) > 0))) {
                    return japVersionInfo;
                }
            }
            if (japVersionInfo2 != null && japVersionInfo2.getJapVersion().compareTo(s) > 0) {
                return japVersionInfo2;
            }
            return null;
        }
    }
    
    public boolean isJavaVersionStillSupported() {
        return JavaVersionDBEntry.CURRENT_JAVA_VERSION.compareTo(this.m_lastSupportedJavaVersion) >= 0;
    }
    
    public String getSupportedJavaVersion() {
        return this.m_lastSupportedJavaVersion;
    }
    
    public String getId() {
        String s = "/japRelease.jnlp";
        if (this.m_versionInfoType == 2) {
            s = "/japDevelopment.jnlp";
        }
        return s;
    }
    
    public long getVersionNumber() {
        return this.m_lastUpdate;
    }
    
    public long getLastUpdate() {
        return this.m_lastUpdate;
    }
    
    public String getJapVersion() {
        return this.m_version;
    }
    
    public Date getDate() {
        return this.m_releaseDate;
    }
    
    public URL getCodeBase() {
        return this.m_codeBase;
    }
    
    public String getJAPJarFileName() {
        return this.m_jarFileName;
    }
    
    public String getPostFile() {
        return this.getId();
    }
    
    public Element getXmlStructure() {
        return this.m_xmlStructure;
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
