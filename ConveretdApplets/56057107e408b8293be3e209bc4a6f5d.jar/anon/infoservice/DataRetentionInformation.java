// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import java.util.Enumeration;
import java.util.Vector;
import org.w3c.dom.NodeList;
import java.net.MalformedURLException;
import java.net.URL;
import anon.util.XMLParseException;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import java.util.Hashtable;
import anon.util.XMLDuration;

public class DataRetentionInformation
{
    public static final String XML_ELEMENT_NAME = "DataRetention";
    public static final String XML_ELEMENT_LOGGED_ELEMENTS = "LoggedElements";
    public static final String XML_ELEMENT_RETENTION_PERIOD = "RetentionPeriod";
    public static final String XML_ELEMENT_DESCRIPTION = "Description";
    public static final String XML_ELEMENT_URL = "URL";
    public static final int NOTHING = 1;
    public static final int INPUT_TIME = 2;
    public static final int OUTPUT_TIME = 4;
    public static final int INPUT_CHANNEL_ID = 8;
    public static final int OUTPUT_CHANNEL_ID = 16;
    public static final int INPUT_SOURCE_IP_ADDRESS = 32;
    public static final int INPUT_SOURCE_IP_PORT = 64;
    public static final int OUTPUT_SOURCE_IP_ADDRESS = 128;
    public static final int OUTPUT_SOURCE_IP_PORT = 256;
    public static final int OUTPUT_TARGET_IP_ADDRESS = 512;
    public static final int OUTPUT_TARGET_DOMAIN = 1024;
    public static final String FIELD_NAME_INPUT_SOURCE_IP_ADDRESS_MIX = "INPUT_SOURCE_IP_ADDRESS_MIX";
    public static final String FIELD_NAME_INPUT_SOURCE_IP_PORT_MIX = "INPUT_SOURCE_IP_PORT_MIX";
    private static final int[] FIELDS;
    private static final String[] FIELD_NAMES;
    private XMLDuration m_duration;
    private Hashtable m_hashURLs;
    private int m_loggedElements;
    
    public DataRetentionInformation(final Element element) throws XMLParseException {
        this.m_hashURLs = new Hashtable();
        this.m_loggedElements = 1;
        XMLUtil.assertNodeName(element, "DataRetention");
        final Node firstChildByName = XMLUtil.getFirstChildByName(element, "LoggedElements");
        this.m_loggedElements = 0;
        if (XMLUtil.parseValue(XMLUtil.getFirstChildByName(firstChildByName, "InputTime"), false)) {
            this.m_loggedElements += 2;
        }
        if (XMLUtil.parseValue(XMLUtil.getFirstChildByName(firstChildByName, "OutputTime"), false)) {
            this.m_loggedElements += 4;
        }
        if (XMLUtil.parseValue(XMLUtil.getFirstChildByName(firstChildByName, "InputChannelID"), false)) {
            this.m_loggedElements += 8;
        }
        if (XMLUtil.parseValue(XMLUtil.getFirstChildByName(firstChildByName, "OutputChannelID"), false)) {
            this.m_loggedElements += 16;
        }
        if (XMLUtil.parseValue(XMLUtil.getFirstChildByName(firstChildByName, "InputSourceIPAddress"), false)) {
            this.m_loggedElements += 32;
        }
        if (XMLUtil.parseValue(XMLUtil.getFirstChildByName(firstChildByName, "InputSourceIPPort"), false)) {
            this.m_loggedElements += 64;
        }
        if (XMLUtil.parseValue(XMLUtil.getFirstChildByName(firstChildByName, "OutputSourceIPAddress"), false)) {
            this.m_loggedElements += 128;
        }
        if (XMLUtil.parseValue(XMLUtil.getFirstChildByName(firstChildByName, "OutputSourceIPPort"), false)) {
            this.m_loggedElements += 256;
        }
        if (XMLUtil.parseValue(XMLUtil.getFirstChildByName(firstChildByName, "OutputTargetIPAddress"), false)) {
            this.m_loggedElements += 512;
        }
        if (XMLUtil.parseValue(XMLUtil.getFirstChildByName(firstChildByName, "OutputTargetDomain"), false)) {
            this.m_loggedElements += 1024;
        }
        if (this.m_loggedElements == 0) {
            this.m_loggedElements = 1;
        }
        this.m_duration = new XMLDuration(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "RetentionPeriod"), null));
        if (this.m_duration.getSign() < 0) {
            throw new XMLParseException("Negative retention duration is not allowed!");
        }
        final NodeList elementsByTagName = XMLUtil.getElementsByTagName(element, "Description");
        if (elementsByTagName != null) {
            for (int i = 0; i < elementsByTagName.getLength(); ++i) {
                try {
                    this.m_hashURLs.put(XMLUtil.parseAttribute(elementsByTagName.item(i), "lang", "en"), new URL(XMLUtil.parseValue(XMLUtil.getFirstChildByName(elementsByTagName.item(i), "URL"), null)));
                }
                catch (MalformedURLException ex) {
                    throw new XMLParseException(ex.getMessage());
                }
            }
        }
    }
    
    private DataRetentionInformation() {
        this.m_hashURLs = new Hashtable();
        this.m_loggedElements = 1;
        this.m_duration = new XMLDuration();
    }
    
    public static DataRetentionInformation getCascadeDataRetentionInformation(final MixCascade mixCascade) {
        if (mixCascade == null) {
            return null;
        }
        final Vector<DataRetentionInformation> vector = new Vector<DataRetentionInformation>();
        for (int i = 0; i < mixCascade.getNumberOfMixes(); ++i) {
            DataRetentionInformation dataRetentionInformation;
            if (mixCascade.getMixInfo(i) == null || mixCascade.getMixInfo(i).getDataRetentionInformation() == null) {
                if (i == 0) {
                    return null;
                }
                dataRetentionInformation = new DataRetentionInformation();
            }
            else {
                dataRetentionInformation = mixCascade.getMixInfo(i).getDataRetentionInformation();
                if (i == 0 && dataRetentionInformation != null && (!dataRetentionInformation.isLogged(8) || !dataRetentionInformation.isLogged(32))) {
                    return null;
                }
            }
            vector.addElement(dataRetentionInformation);
        }
        return getCascadeDataRetentionInformation(vector);
    }
    
    private static DataRetentionInformation getCascadeDataRetentionInformation(final Vector vector) {
        final DataRetentionInformation dataRetentionInformation = new DataRetentionInformation();
        if (vector == null) {
            return dataRetentionInformation;
        }
        if (((Vector)vector.clone()).size() == 0) {
            return dataRetentionInformation;
        }
        final DataRetentionInformation dataRetentionInformation2 = vector.elementAt(0);
        dataRetentionInformation.m_loggedElements = dataRetentionInformation2.getLoggedElementIDs();
        dataRetentionInformation.m_duration = dataRetentionInformation2.m_duration;
        dataRetentionInformation.m_hashURLs = (Hashtable)dataRetentionInformation2.m_hashURLs.clone();
        for (int i = 1; i < vector.size(); ++i) {
            final DataRetentionInformation dataRetentionInformation3 = vector.elementAt(i);
            if (i == vector.size() - 1) {
                final DataRetentionInformation dataRetentionInformation4 = dataRetentionInformation;
                dataRetentionInformation4.m_loggedElements &= (dataRetentionInformation3.getLoggedElementIDs() | 0x10);
            }
            else {
                final DataRetentionInformation dataRetentionInformation5 = dataRetentionInformation;
                dataRetentionInformation5.m_loggedElements &= dataRetentionInformation3.getLoggedElementIDs();
            }
            if (dataRetentionInformation.m_duration.isLongerThan(dataRetentionInformation3.m_duration)) {
                dataRetentionInformation.m_duration = dataRetentionInformation3.m_duration;
            }
            if (dataRetentionInformation.m_hashURLs.size() == dataRetentionInformation3.m_hashURLs.size()) {
                final Enumeration keys = dataRetentionInformation3.m_hashURLs.keys();
                while (keys.hasMoreElements()) {
                    final Object nextElement = keys.nextElement();
                    if (!dataRetentionInformation.m_hashURLs.containsKey(nextElement) || !dataRetentionInformation.m_hashURLs.get(nextElement).equals(dataRetentionInformation3.m_hashURLs.get(nextElement))) {
                        dataRetentionInformation.m_hashURLs.clear();
                        break;
                    }
                }
            }
            else {
                dataRetentionInformation.m_hashURLs.clear();
            }
        }
        if (dataRetentionInformation.m_loggedElements == 0) {
            dataRetentionInformation.m_loggedElements = 1;
        }
        return dataRetentionInformation;
    }
    
    public static int getLoggedElementsLength() {
        return DataRetentionInformation.FIELDS.length;
    }
    
    public static int getLoggedElementID(final int n) {
        if (n < 0 || n > DataRetentionInformation.FIELDS.length) {
            return -1;
        }
        return DataRetentionInformation.FIELDS[n];
    }
    
    public static String getLoggedElementName(final int n) {
        if (n < 0 || n > DataRetentionInformation.FIELD_NAMES.length) {
            return null;
        }
        return DataRetentionInformation.FIELD_NAMES[n];
    }
    
    public boolean isLogged(final int n) {
        return (n & this.m_loggedElements) == n;
    }
    
    public int getLoggedElementIDs() {
        return this.m_loggedElements;
    }
    
    public URL getURL(final String s) {
        URL url = this.m_hashURLs.get(s);
        if (url == null) {
            url = this.m_hashURLs.get("en");
        }
        return url;
    }
    
    public XMLDuration getDuration() {
        return this.m_duration;
    }
    
    static {
        FIELDS = new int[] { 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024 };
        FIELD_NAMES = new String[] { "INPUT_TIME", "OUTPUT_TIME", "INPUT_CHANNEL_ID", "OUTPUT_CHANNEL_ID", "INPUT_SOURCE_IP_ADDRESS", "INPUT_SOURCE_IP_PORT", "OUTPUT_SOURCE_IP_ADDRESS", "OUTPUT_SOURCE_IP_PORT", "OUTPUT_TARGET_IP_ADDRESS", "OUTPUT_TARGET_DOMAIN" };
    }
}
