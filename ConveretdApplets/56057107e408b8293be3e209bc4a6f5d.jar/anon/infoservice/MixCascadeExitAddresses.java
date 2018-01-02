// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import org.w3c.dom.Document;
import java.util.Enumeration;
import anon.util.XMLParseException;
import org.w3c.dom.NodeList;
import java.net.UnknownHostException;
import logging.LogHolder;
import logging.LogType;
import java.net.InetAddress;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import java.util.Hashtable;
import anon.util.IXMLEncodable;

public class MixCascadeExitAddresses extends AbstractDatabaseEntry implements IXMLEncodable
{
    public static final int EXIT_ADDRESS_TTL = 86400000;
    public static final String XML_ELEMENT_CONTAINER_NAME = "ExitAddressesList";
    public static final String XML_ELEMENT_NAME = "ExitAddresses";
    public static final String XML_ELEMENT_ADDRESS_NAME = "ExitAddress";
    public static final String XML_ATTR_LAST_UPDATE = "lastUpdate";
    public static final String XML_ATTR_PAYMENT = "payment";
    private long m_lastUpdate;
    private String m_strCascadeId;
    private Hashtable m_tblAddresses;
    static /* synthetic */ Class class$anon$infoservice$MixCascadeExitAddresses;
    static /* synthetic */ Class class$java$net$InetAddress;
    
    private MixCascadeExitAddresses(final String strCascadeId) {
        super(System.currentTimeMillis() + 86400000L);
        this.m_strCascadeId = null;
        this.m_tblAddresses = new Hashtable();
        this.m_strCascadeId = strCascadeId;
        this.m_lastUpdate = System.currentTimeMillis();
    }
    
    public MixCascadeExitAddresses(final Element element) throws XMLParseException {
        super(System.currentTimeMillis() + 86400000L);
        this.m_strCascadeId = null;
        this.m_tblAddresses = new Hashtable();
        XMLUtil.assertNodeName(element, "ExitAddresses");
        XMLUtil.assertNotNull(element, "id");
        this.m_strCascadeId = XMLUtil.parseAttribute(element, "id", null);
        this.m_lastUpdate = System.currentTimeMillis();
        final NodeList elementsByTagName = element.getElementsByTagName("ExitAddress");
        for (int i = 0; i < elementsByTagName.getLength(); ++i) {
            final long attribute = XMLUtil.parseAttribute(elementsByTagName.item(i), "lastUpdate", System.currentTimeMillis());
            XMLUtil.assertNotNull(elementsByTagName.item(i));
            InetAddress byName;
            try {
                byName = InetAddress.getByName(XMLUtil.parseValue(elementsByTagName.item(i), null));
            }
            catch (UnknownHostException ex) {
                LogHolder.log(4, LogType.NET, ex);
                continue;
            }
            this.addInetAddress(byName, attribute);
        }
    }
    
    public String getId() {
        return this.m_strCascadeId;
    }
    
    public long getLastUpdate() {
        return this.m_lastUpdate;
    }
    
    public long getVersionNumber() {
        return this.m_lastUpdate;
    }
    
    public static void addInetAddress(final String s, final InetAddress inetAddress) {
        final Class clazz = (MixCascadeExitAddresses.class$anon$infoservice$MixCascadeExitAddresses == null) ? (MixCascadeExitAddresses.class$anon$infoservice$MixCascadeExitAddresses = class$("anon.infoservice.MixCascadeExitAddresses")) : MixCascadeExitAddresses.class$anon$infoservice$MixCascadeExitAddresses;
        synchronized (Database.getInstance(clazz)) {
            if (s != null && s.trim().length() > 0 && inetAddress != null) {
                MixCascadeExitAddresses mixCascadeExitAddresses = (MixCascadeExitAddresses)Database.getInstance((MixCascadeExitAddresses.class$anon$infoservice$MixCascadeExitAddresses == null) ? (MixCascadeExitAddresses.class$anon$infoservice$MixCascadeExitAddresses = class$("anon.infoservice.MixCascadeExitAddresses")) : MixCascadeExitAddresses.class$anon$infoservice$MixCascadeExitAddresses).getEntryById(s);
                if (mixCascadeExitAddresses == null) {
                    mixCascadeExitAddresses = new MixCascadeExitAddresses(s);
                }
                if (mixCascadeExitAddresses.addInetAddress(inetAddress)) {
                    Database.getInstance((MixCascadeExitAddresses.class$anon$infoservice$MixCascadeExitAddresses == null) ? (MixCascadeExitAddresses.class$anon$infoservice$MixCascadeExitAddresses = class$("anon.infoservice.MixCascadeExitAddresses")) : MixCascadeExitAddresses.class$anon$infoservice$MixCascadeExitAddresses).update(mixCascadeExitAddresses);
                }
            }
        }
    }
    
    public static boolean isValidAddress(final InetAddress inetAddress) {
        return isValidAddress(inetAddress, "isAnyLocalAddress") || isValidAddress(inetAddress, "isLoopbackAddress") || isValidAddress(inetAddress, "isLinkLocalAddress") || isValidAddress(inetAddress, "isMulticastAddress") || isValidAddress(inetAddress, "isSiteLocalAddress");
    }
    
    private static boolean isValidAddress(final InetAddress inetAddress, final String s) {
        try {
            return !(boolean)((MixCascadeExitAddresses.class$java$net$InetAddress == null) ? (MixCascadeExitAddresses.class$java$net$InetAddress = class$("java.net.InetAddress")) : MixCascadeExitAddresses.class$java$net$InetAddress).getMethod(s, (Class[])null).invoke(inetAddress, (Object[])null);
        }
        catch (Exception ex) {
            return true;
        }
    }
    
    private boolean addInetAddress(final InetAddress inetAddress) {
        return this.addInetAddress(inetAddress, System.currentTimeMillis());
    }
    
    private boolean addInetAddress(final InetAddress inetAddress, final long n) {
        boolean b = false;
        final Enumeration<Long> keys = (Enumeration<Long>)this.m_tblAddresses.keys();
        while (keys.hasMoreElements()) {
            final Long n2 = keys.nextElement();
            if (n2 < System.currentTimeMillis() - 86400000L) {
                this.m_tblAddresses.remove(n2);
                b = true;
            }
        }
        if (n < System.currentTimeMillis() - 86400000L) {
            return false;
        }
        if (!this.m_tblAddresses.contains(inetAddress)) {
            this.m_tblAddresses.put(new Long(n), inetAddress);
            b = true;
        }
        return b;
    }
    
    public Element toXmlElement(final Document document) {
        final Element element = document.createElement("ExitAddresses");
        XMLUtil.setAttribute(element, "id", this.getId());
        final Enumeration<Long> keys = this.m_tblAddresses.keys();
        while (keys.hasMoreElements()) {
            final Long n = keys.nextElement();
            final InetAddress inetAddress = this.m_tblAddresses.get(n);
            final Element element2 = document.createElement("ExitAddress");
            XMLUtil.setAttribute(element2, "lastUpdate", n);
            XMLUtil.setValue(element2, inetAddress.getHostAddress());
            element.appendChild(element2);
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
