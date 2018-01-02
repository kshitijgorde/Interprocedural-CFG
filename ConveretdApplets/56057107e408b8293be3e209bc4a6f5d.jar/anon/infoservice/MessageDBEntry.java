// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import anon.util.Base64;
import anon.util.URLDecoder;
import logging.LogHolder;
import logging.LogType;
import java.util.Locale;
import org.w3c.dom.NodeList;
import java.net.MalformedURLException;
import java.net.URL;
import anon.util.XMLParseException;
import java.security.SignatureException;
import anon.crypto.SignatureVerifier;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import java.util.Hashtable;
import org.w3c.dom.Element;

public class MessageDBEntry extends AbstractDistributableDatabaseEntry implements IDistributable
{
    public static final String XML_ELEMENT_CONTAINER_NAME = "Messages";
    public static final String XML_ELEMENT_NAME = "Message";
    public static final String HTTP_REQUEST_STRING = "/messages";
    public static final String HTTP_SERIALS_REQUEST_STRING = "/messageserials";
    public static final String PROPERTY_NAME = "messageFileName";
    public static final String POST_FILE = "/message";
    private static final String XML_TEXT = "MessageText";
    private static final String XML_URL = "MessageURL";
    private static final String XML_ATTR_LANG = "lang";
    private static final String XML_ATTR_POPUP = "popup";
    private static final String XML_ATTR_ENCODING = "encoding";
    private static final String XML_ATTR_FREE = "free";
    private static final String XML_ELEM_POPUP_TEXT = "MessagePopupText";
    private static final String ENCODING_URL = "url";
    private static final String ENCODING_BASE64 = "base64";
    private static final long TIMEOUT = 604800000L;
    private int m_externalIdentifier;
    private long m_serial;
    private long m_lastUpdate;
    private boolean m_bIsDummy;
    private boolean m_bFree;
    private boolean m_bShowPopup;
    private String m_id;
    private Element m_xmlDescription;
    private Hashtable m_hashText;
    private Hashtable m_hashPopupText;
    private Hashtable m_hashUrl;
    
    public MessageDBEntry(final Element xmlDescription) throws XMLParseException, SignatureException {
        super(System.currentTimeMillis() + 604800000L);
        this.m_hashText = new Hashtable();
        this.m_hashPopupText = new Hashtable();
        this.m_hashUrl = new Hashtable();
        XMLUtil.assertNodeName(xmlDescription, "Message");
        if (SignatureVerifier.getInstance().getVerifiedXml(xmlDescription, 2) == null) {
            throw new SignatureException();
        }
        this.m_serial = XMLUtil.parseAttribute(xmlDescription, "serial", Long.MIN_VALUE);
        this.m_id = XMLUtil.parseAttribute(xmlDescription, "id", null);
        this.m_bShowPopup = XMLUtil.parseAttribute(xmlDescription, "popup", false);
        this.m_bFree = XMLUtil.parseAttribute(xmlDescription, "free", false);
        if (this.m_id == null) {
            throw new XMLParseException("No id given!");
        }
        if (!(this.m_bIsDummy = this.parseTextNodes(xmlDescription.getElementsByTagName("MessageText"), this.m_hashText))) {
            final NodeList elementsByTagName = xmlDescription.getElementsByTagName("MessageURL");
            for (int i = 0; i < elementsByTagName.getLength(); ++i) {
                final String value = XMLUtil.parseValue(elementsByTagName.item(i), null);
                final String attribute = XMLUtil.parseAttribute(elementsByTagName.item(i), "lang", "en");
                if (value != null) {
                    try {
                        this.m_hashUrl.put(attribute, new URL(value));
                    }
                    catch (MalformedURLException ex) {}
                }
            }
            this.parseTextNodes(xmlDescription.getElementsByTagName("MessagePopupText"), this.m_hashPopupText);
        }
        this.m_lastUpdate = XMLUtil.parseValue(XMLUtil.getFirstChildByName(xmlDescription, "LastUpdate"), -1L);
        if (this.m_lastUpdate == -1L) {
            this.m_lastUpdate = System.currentTimeMillis();
        }
        this.m_xmlDescription = xmlDescription;
    }
    
    public URL getURL(final Locale locale) {
        if (locale == null) {
            return null;
        }
        URL url = null;
        final URL value = this.m_hashUrl.get(locale.getLanguage());
        if (value != null && value instanceof URL) {
            url = value;
        }
        else {
            final URL value2 = this.m_hashUrl.get("en");
            if (value2 != null && value2 instanceof URL) {
                url = value2;
            }
        }
        if (url == null) {
            LogHolder.log(4, LogType.MISC, "Could not get URL for message: " + this.getText(locale));
        }
        return url;
    }
    
    public String getText(final Locale locale) {
        return this.getText(locale, this.m_hashText);
    }
    
    public String getPopupText(final Locale locale) {
        return this.getText(locale, this.m_hashPopupText);
    }
    
    public int getExternalIdentifier() {
        return this.m_externalIdentifier;
    }
    
    public void setExternalIdentifier(final int externalIdentifier) {
        this.m_externalIdentifier = externalIdentifier;
    }
    
    public boolean isPopupShown() {
        return this.m_bShowPopup;
    }
    
    public boolean isForFreeCascadesOnly() {
        return this.m_bFree;
    }
    
    public boolean isDummy() {
        return this.m_bIsDummy;
    }
    
    public long getVersionNumber() {
        return this.m_serial;
    }
    
    public String getId() {
        return this.m_id;
    }
    
    public String getPostFile() {
        return "/message";
    }
    
    public long getLastUpdate() {
        return this.m_lastUpdate;
    }
    
    public Element getXmlStructure() {
        return this.m_xmlDescription;
    }
    
    private String getText(final Locale locale, final Hashtable hashtable) {
        if (locale == null) {
            return null;
        }
        String s = hashtable.get(locale.getLanguage());
        if (s == null) {
            s = hashtable.get("en");
        }
        return s;
    }
    
    private boolean parseTextNodes(final NodeList list, final Hashtable hashtable) {
        for (int i = 0; i < list.getLength(); ++i) {
            final String value = XMLUtil.parseValue(list.item(i), null);
            final String attribute = XMLUtil.parseAttribute(list.item(i), "lang", "en");
            final String attribute2 = XMLUtil.parseAttribute(list.item(i), "encoding", "base64");
            if (value != null) {
                String s;
                if (attribute2.equals("url")) {
                    s = URLDecoder.decode(value);
                }
                else if (attribute2.equals("base64")) {
                    s = Base64.decodeToString(value);
                }
                else {
                    s = null;
                }
                if (s != null) {
                    hashtable.put(attribute, s);
                }
            }
        }
        return hashtable.size() == 0 || hashtable.get("en") == null;
    }
}
