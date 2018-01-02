// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import org.w3c.dom.Document;
import anon.util.Util;
import java.util.Vector;
import java.util.Date;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import logging.LogHolder;
import logging.LogType;
import anon.crypto.SignatureVerifier;
import anon.util.XMLParseException;
import java.util.Hashtable;
import anon.crypto.MultiCertPath;
import anon.crypto.XMLSignature;
import org.w3c.dom.Element;
import anon.util.IXMLEncodable;

public class PerformanceInfo extends AbstractCertifiedDatabaseEntry implements IXMLEncodable
{
    private static final double PERFORMANCE_INFO_MIN_PERCENTAGE_OF_VALID_ENTRIES = 0.6666666666666666;
    private long m_lastUpdate;
    private long m_serial;
    private String m_id;
    private Element m_xmlData;
    private XMLSignature m_signature;
    private MultiCertPath m_certPath;
    private Hashtable m_entries;
    public static final String XML_ATTR_ID = "id";
    public static final String XML_ELEMENT_NAME = "PerformanceInfo";
    public static final String XML_ELEMENT_CONTAINER_NAME = "PerformanceInfoList";
    public static final int PERFORMANCE_INFO_TTL = 518400000;
    static /* synthetic */ Class class$anon$infoservice$PerformanceInfo;
    
    public PerformanceInfo(final Element xmlData) throws XMLParseException {
        super(System.currentTimeMillis() + 518400000L);
        this.m_entries = new Hashtable();
        if (xmlData == null) {
            throw new XMLParseException("Could not parse PerformanceInfo. Invalid document element.");
        }
        final NodeList elementsByTagName = xmlData.getElementsByTagName("PerformanceEntry");
        try {
            this.m_signature = SignatureVerifier.getInstance().getVerifiedXml(xmlData, 2);
            if (this.m_signature != null) {
                this.m_certPath = this.m_signature.getMultiCertPath();
            }
        }
        catch (Exception ex) {
            LogHolder.log(3, LogType.MISC, ex);
        }
        this.m_id = XMLUtil.parseAttribute(xmlData, "id", "");
        if (!this.checkId()) {
            throw new XMLParseException("PerformanceInfo: invalid id");
        }
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            final PerformanceEntry performanceEntry = new PerformanceEntry((Element)elementsByTagName.item(i));
            this.m_entries.put(performanceEntry.getId(), performanceEntry);
        }
        this.m_lastUpdate = XMLUtil.parseAttribute(xmlData, "lastUpdate", System.currentTimeMillis());
        this.m_serial = this.m_lastUpdate;
        this.m_xmlData = xmlData;
    }
    
    public XMLSignature getSignature() {
        return this.m_signature;
    }
    
    public MultiCertPath getCertPath() {
        return this.m_certPath;
    }
    
    public boolean isVerified() {
        return this.m_certPath != null && this.m_certPath.isVerified();
    }
    
    public boolean isValid() {
        return this.m_certPath != null && this.m_certPath.isValid(new Date());
    }
    
    public String getId() {
        return this.m_id;
    }
    
    public long getLastUpdate() {
        return this.m_lastUpdate;
    }
    
    public long getVersionNumber() {
        return this.m_serial;
    }
    
    private PerformanceEntry getEntry(final String s) {
        return this.m_entries.get(s);
    }
    
    public static PerformanceEntry getLowestCommonBoundEntry(final String s) {
        final PerformanceEntry performanceEntry = new PerformanceEntry(s, true);
        final Vector vector = new Vector<PerformanceEntry>();
        final Vector<Integer> vector2 = new Vector<Integer>();
        final Vector<Integer> vector3 = new Vector<Integer>();
        final Vector<Integer> vector4 = new Vector<Integer>();
        final Vector<Integer> vector5 = new Vector<Integer>();
        final Vector<Integer> vector6 = new Vector<Integer>();
        final Vector<Integer> vector7 = new Vector<Integer>();
        final Vector<Integer> vector8 = new Vector<Integer>();
        final Vector<Integer> vector9 = new Vector<Integer>();
        final Vector<Integer> vector10 = new Vector<Integer>();
        final Vector entryList = Database.getInstance((PerformanceInfo.class$anon$infoservice$PerformanceInfo == null) ? (PerformanceInfo.class$anon$infoservice$PerformanceInfo = class$("anon.infoservice.PerformanceInfo")) : PerformanceInfo.class$anon$infoservice$PerformanceInfo).getEntryList();
        for (int i = 0; i < entryList.size(); ++i) {
            final PerformanceEntry entry = entryList.elementAt(i).getEntry(s);
            if (entry != null) {
                vector.addElement(entry);
                final Integer n = new Integer(entry.getBound(0).getBound());
                if (n != Integer.MAX_VALUE && n >= 0) {
                    vector2.addElement(n);
                }
                final Integer n2 = new Integer(entry.getBound(0).getNotRecoveredBound());
                if (n2 != Integer.MAX_VALUE && n2 >= 0) {
                    vector3.addElement(n2);
                }
                final Integer n3 = new Integer(entry.getBound(1).getBound());
                if (n3 > 0) {
                    vector5.addElement(n3);
                }
                final Integer n4 = new Integer(entry.getBound(1).getNotRecoveredBound());
                if (n4 > 0) {
                    vector6.addElement(n4);
                }
                final Integer n5 = new Integer(entry.getBestBound(0));
                if (n5 != Integer.MAX_VALUE && n5 >= 0) {
                    vector4.addElement(n5);
                }
                final Integer n6 = new Integer(entry.getBestBound(1));
                if (n6 > 0) {
                    vector7.addElement(n6);
                }
                final PerformanceEntry.StabilityAttributes stabilityAttributes = entry.getStabilityAttributes();
                if (stabilityAttributes.getValueSize() > 0) {
                    vector8.addElement(new Integer(stabilityAttributes.getBoundErrors()));
                    vector9.addElement(new Integer(stabilityAttributes.getBoundUnknown()));
                    vector10.addElement(new Integer(stabilityAttributes.getBoundResets()));
                }
            }
        }
        entryList.removeAllElements();
        if (vector.size() == 0) {
            performanceEntry.setBound(0, new PerformanceEntry.Bound(Integer.MAX_VALUE, Integer.MAX_VALUE));
            performanceEntry.setBestBound(0, Integer.MAX_VALUE);
            performanceEntry.setBound(1, new PerformanceEntry.Bound(0, 0));
            performanceEntry.setBestBound(1, 0);
            performanceEntry.setStabilityAttributes(new PerformanceEntry.StabilityAttributes(0, 0, 0, 0));
            return performanceEntry;
        }
        Util.sort(vector2, new Util.IntegerSortDesc());
        Util.sort(vector3, new Util.IntegerSortDesc());
        Util.sort(vector4, new Util.IntegerSortDesc());
        Util.sort(vector5, new Util.IntegerSortAsc());
        Util.sort(vector6, new Util.IntegerSortAsc());
        Util.sort(vector7, new Util.IntegerSortAsc());
        Util.sort(vector8, new Util.IntegerSortAsc());
        Util.sort(vector9, new Util.IntegerSortAsc());
        Util.sort(vector10, new Util.IntegerSortAsc());
        final PerformanceEntry.StabilityAttributes stabilityAttributes2 = new PerformanceEntry.StabilityAttributes(100, getMajorityBoundFromSortedBounds(vector9, 0), getMajorityBoundFromSortedBounds(vector8, 0), getMajorityBoundFromSortedBounds(vector10, 0));
        performanceEntry.setBound(0, new PerformanceEntry.Bound(getMajorityBoundFromSortedBounds(vector2, Integer.MAX_VALUE), getMajorityBoundFromSortedBounds(vector3, Integer.MAX_VALUE)));
        performanceEntry.setBestBound(0, getMajorityBoundFromSortedBounds(vector4, Integer.MAX_VALUE));
        performanceEntry.setBound(1, new PerformanceEntry.Bound(getMajorityBoundFromSortedBounds(vector5, 0), getMajorityBoundFromSortedBounds(vector6, 0)));
        performanceEntry.setBestBound(1, getMajorityBoundFromSortedBounds(vector7, 0));
        performanceEntry.setStabilityAttributes(stabilityAttributes2);
        return performanceEntry;
    }
    
    private static int getMajorityBoundFromSortedBounds(final Vector vector, final int n) {
        int intValue = n;
        for (int i = 0; i < vector.size(); ++i) {
            intValue = vector.elementAt(i);
            if ((i + 1) / vector.size() >= 0.6666666666666666) {
                break;
            }
        }
        return intValue;
    }
    
    public Element toXmlElement(final Document document) {
        try {
            return (Element)XMLUtil.importNode(document, this.m_xmlData, true);
        }
        catch (XMLParseException ex) {
            LogHolder.log(2, LogType.NET, "Could not store PerformanceInfo to XML element", ex);
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
