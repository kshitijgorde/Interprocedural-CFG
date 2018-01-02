// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import org.w3c.dom.Document;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.util.Date;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import logging.LogHolder;
import logging.LogType;
import anon.crypto.SignatureVerifier;
import anon.util.XMLParseException;
import org.w3c.dom.Element;
import anon.crypto.MultiCertPath;
import anon.crypto.XMLSignature;
import anon.util.IXMLEncodable;
import anon.crypto.IVerifyable;

public final class StatusInfo extends AbstractDatabaseEntry implements IDistributable, IVerifyable, IXMLEncodable
{
    public static final String XML_ELEMENT_NAME = "MixCascadeStatus";
    public static final String XML_ELEMENT_CONTAINER_NAME = "MixCascadeStatusList";
    public static final int ANON_LEVEL_MIN = 0;
    public static final int ANON_LEVEL_MAX = 6;
    private String m_mixCascadeId;
    private long m_lastUpdate;
    private int m_nrOfActiveUsers;
    private int m_currentRisk;
    private int m_trafficSituation;
    private long m_mixedPackets;
    private int m_anonLevel;
    private String m_statusXmlData;
    private byte[] m_statusXmlDataBytes;
    private XMLSignature m_signature;
    private MultiCertPath m_certPath;
    static /* synthetic */ Class class$anon$infoservice$MixCascade;
    static /* synthetic */ Class class$anon$infoservice$PerformanceEntry;
    
    public static StatusInfo createDummyStatusInfo(final String s) {
        return new StatusInfo(s, -1, -1, -1, -1L, -1);
    }
    
    public static String getXmlElementName() {
        return "MixCascadeStatus";
    }
    
    public StatusInfo(final Element element) throws Exception {
        this(element, -1L);
    }
    
    public StatusInfo(final Element element, final long n) throws Exception {
        super(System.currentTimeMillis() + ((n <= 0L) ? 240000L : n));
        if (element == null) {
            throw new XMLParseException("##__null__##");
        }
        if (!element.getNodeName().equals("MixCascadeStatus")) {
            throw new XMLParseException("##__root__##");
        }
        this.m_mixCascadeId = element.getAttribute("id");
        try {
            this.m_signature = SignatureVerifier.getInstance().getVerifiedXml(element, 1);
            if (this.m_signature != null) {
                this.m_certPath = this.m_signature.getMultiCertPath();
            }
        }
        catch (Exception ex) {
            LogHolder.log(3, LogType.CRYPTO, "Error while verifying status info certificates!", ex);
        }
        if (!this.checkId()) {
            throw new XMLParseException("##__root__##", "Malformed Status-Entry for Mix ID: " + this.m_mixCascadeId);
        }
        this.m_currentRisk = Integer.parseInt(element.getAttribute("currentRisk"));
        this.m_mixedPackets = Long.parseLong(element.getAttribute("mixedPackets"));
        this.m_nrOfActiveUsers = Integer.parseInt(element.getAttribute("nrOfActiveUsers"));
        this.m_trafficSituation = Integer.parseInt(element.getAttribute("trafficSituation"));
        this.m_lastUpdate = Long.parseLong(element.getAttribute("LastUpdate"));
        this.m_anonLevel = -1;
        if (this.getNrOfActiveUsers() >= 0 && this.getTrafficSituation() >= 0) {
            if (this.getNrOfActiveUsers() < 30) {
                this.m_anonLevel = 0;
            }
            else if (this.getNrOfActiveUsers() < 90) {
                this.m_anonLevel = 1;
            }
            else if (this.getNrOfActiveUsers() < 200) {
                this.m_anonLevel = 2;
            }
            else if (this.getNrOfActiveUsers() < 300) {
                this.m_anonLevel = 3;
            }
            else if (this.getNrOfActiveUsers() < 400) {
                this.m_anonLevel = 4;
            }
            else if (this.getNrOfActiveUsers() < 500) {
                this.m_anonLevel = 5;
            }
            else {
                this.m_anonLevel = 6;
            }
        }
        if (XMLUtil.getStorageMode() == 2) {
            this.m_statusXmlData = null;
            this.m_statusXmlDataBytes = null;
        }
        else {
            this.m_statusXmlData = XMLUtil.toString(element);
            this.m_statusXmlDataBytes = this.m_statusXmlData.getBytes();
        }
    }
    
    private StatusInfo(final String mixCascadeId, final int nrOfActiveUsers, final int currentRisk, final int trafficSituation, final long mixedPackets, final int anonLevel) {
        super(System.currentTimeMillis() + 240000L);
        this.m_mixCascadeId = mixCascadeId;
        this.m_lastUpdate = System.currentTimeMillis();
        this.m_nrOfActiveUsers = nrOfActiveUsers;
        this.m_currentRisk = currentRisk;
        this.m_trafficSituation = trafficSituation;
        this.m_mixedPackets = mixedPackets;
        this.m_anonLevel = anonLevel;
        this.m_statusXmlData = XMLUtil.toString(this.generateXmlRepresentation());
        this.m_statusXmlDataBytes = this.m_statusXmlData.getBytes();
    }
    
    public String getId() {
        return this.m_mixCascadeId;
    }
    
    public long getLastUpdate() {
        return this.m_lastUpdate;
    }
    
    public long getVersionNumber() {
        return this.getLastUpdate();
    }
    
    public int getNrOfActiveUsers() {
        return this.m_nrOfActiveUsers;
    }
    
    public int getCurrentRisk() {
        return this.m_currentRisk;
    }
    
    public int getTrafficSituation() {
        return this.m_trafficSituation;
    }
    
    public long getMixedPackets() {
        return this.m_mixedPackets;
    }
    
    public int getAnonLevel() {
        return this.m_anonLevel;
    }
    
    public boolean isVerified() {
        return this.m_signature != null && this.m_signature.isVerified();
    }
    
    public boolean isValid() {
        return this.m_certPath != null && this.m_certPath.isValid(new Date());
    }
    
    public XMLSignature getSignature() {
        return this.m_signature;
    }
    
    public MultiCertPath getCertPath() {
        return this.m_certPath;
    }
    
    public boolean checkId() {
        return this.m_signature != null && this.m_signature.getXORofSKIs().equalsIgnoreCase(this.getId());
    }
    
    public String getPostFile() {
        return "/feedback";
    }
    
    public byte[] getPostData() {
        return this.m_statusXmlDataBytes;
    }
    
    public int getPostEncoding() {
        return 0;
    }
    
    public String getStatusXmlData() {
        return this.m_statusXmlData;
    }
    
    public String getHtmlTableLine(final boolean b) {
        String string = "<TR><TD CLASS=\"name\">";
        final MixCascade mixCascade = (MixCascade)Database.getInstance((StatusInfo.class$anon$infoservice$MixCascade == null) ? (StatusInfo.class$anon$infoservice$MixCascade = class$("anon.infoservice.MixCascade")) : StatusInfo.class$anon$infoservice$MixCascade).getEntryById(this.getId());
        final PerformanceEntry performanceEntry = (PerformanceEntry)Database.getInstance((StatusInfo.class$anon$infoservice$PerformanceEntry == null) ? (StatusInfo.class$anon$infoservice$PerformanceEntry = class$("anon.infoservice.PerformanceEntry")) : StatusInfo.class$anon$infoservice$PerformanceEntry).getEntryById(this.getId());
        int maxUsers = 0;
        if (mixCascade != null) {
            string += mixCascade.getName();
            maxUsers = mixCascade.getMaxUsers();
        }
        String s = " (n/a)";
        if (this.getTrafficSituation() >= 0) {
            s = " (low)";
        }
        if (this.getTrafficSituation() > 30) {
            s = " (medium)";
        }
        if (this.getTrafficSituation() > 60) {
            s = " (high)";
        }
        final String string2 = string + "</TD><TD CLASS=\"name\"><a href=\"" + "/cascadewebinfo/" + this.getId() + "\">" + this.getId() + "</a></TD><TD CLASS=\"status\" ALIGN=\"right\">" + (b ? "" : ("<a href=\"/values/users/" + this.getId() + "\">")) + Integer.toString(this.getNrOfActiveUsers()) + ((maxUsers > 0) ? (" / " + maxUsers) : "") + (b ? "" : "</a>") + "</TD><TD CLASS=\"status\" ALIGN=\"center\">" + Integer.toString(this.getTrafficSituation()) + s + "</TD><TD CLASS=\"status\" ALIGN=\"right\">" + "<a href=\"/values/delay/" + this.getId() + "\">" + (b ? "" : (((performanceEntry != null && System.currentTimeMillis() - performanceEntry.getLastTestTime() < 1200000L && performanceEntry.getLastTestAverage(1) != 0) ? String.valueOf(performanceEntry.getLastTestAverage(1)) : "?") + " (" + ((performanceEntry != null && performanceEntry.getAverage(1) != 0) ? String.valueOf(performanceEntry.getAverage(1)) : "?") + ") ")) + (b ? "" : "[");
        int bound;
        if (performanceEntry == null) {
            bound = 0;
        }
        else {
            bound = performanceEntry.getBound(1).getBound();
        }
        String s2;
        if (bound == Integer.MAX_VALUE) {
            s2 = string2 + ">" + PerformanceEntry.BOUNDARIES[1][PerformanceEntry.BOUNDARIES[1].length - 2];
        }
        else if (bound <= 0) {
            s2 = string2 + "?";
        }
        else {
            s2 = string2 + bound;
        }
        final String string3 = s2 + (b ? "" : "]") + " ms" + "</a>" + "</TD><TD CLASS=\"status\" ALIGN=\"right\">" + "<a href=\"/values/speed/" + this.getId() + "\">" + (b ? "" : (((performanceEntry != null && System.currentTimeMillis() - performanceEntry.getLastTestTime() < 1200000L && performanceEntry.getLastTestAverage(0) != 0) ? String.valueOf(performanceEntry.getLastTestAverage(0)) : "?") + " (" + ((performanceEntry != null && performanceEntry.getAverage(0) != 0) ? String.valueOf(performanceEntry.getAverage(0)) : "?") + ") ")) + (b ? "" : "[");
        int bound2;
        if (performanceEntry == null) {
            bound2 = Integer.MAX_VALUE;
        }
        else {
            bound2 = performanceEntry.getBound(0).getBound();
        }
        String s3;
        if (bound2 == 0) {
            s3 = string3 + "<" + PerformanceEntry.BOUNDARIES[0][1];
        }
        else if (bound2 < 0 || bound2 == Integer.MAX_VALUE) {
            s3 = string3 + "?";
        }
        else {
            s3 = string3 + bound2;
        }
        return s3 + (b ? "" : "]") + " kbit/s" + "</a>" + "</TD><TD CLASS=\"status\" ALIGN=\"right\">" + NumberFormat.getInstance(Constants.LOCAL_FORMAT).format(this.getMixedPackets()) + "</TD><TD CLASS=\"status\">" + new SimpleDateFormat("HH:mm:ss").format(new Date(this.getLastUpdate())) + "</TD></TR>";
    }
    
    public Node generateMixCascadeCurrentStatus() {
        final Element element = XMLUtil.createDocument().createElement("CurrentStatus");
        element.setAttribute("CurrentRisk", Integer.toString(this.getCurrentRisk()));
        element.setAttribute("TrafficSituation", Integer.toString(this.getTrafficSituation()));
        element.setAttribute("ActiveUsers", Integer.toString(this.getNrOfActiveUsers()));
        element.setAttribute("MixedPackets", Long.toString(this.getMixedPackets()));
        element.setAttribute("LastUpdate", Long.toString(this.getLastUpdate()));
        return element;
    }
    
    private Element generateXmlRepresentation() {
        final Element element = XMLUtil.createDocument().createElement("MixCascadeStatus");
        element.setAttribute("id", this.getId());
        element.setAttribute("currentRisk", Integer.toString(this.getCurrentRisk()));
        element.setAttribute("mixedPackets", Long.toString(this.getMixedPackets()));
        element.setAttribute("nrOfActiveUsers", Integer.toString(this.getNrOfActiveUsers()));
        element.setAttribute("trafficSituation", Integer.toString(this.getTrafficSituation()));
        element.setAttribute("LastUpdate", Long.toString(this.getLastUpdate()));
        return element;
    }
    
    public Element toXmlElement(final Document document) {
        try {
            return (Element)XMLUtil.importNode(document, XMLUtil.toXMLDocument(this.m_statusXmlDataBytes).getDocumentElement(), true);
        }
        catch (XMLParseException ex) {
            return null;
        }
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
