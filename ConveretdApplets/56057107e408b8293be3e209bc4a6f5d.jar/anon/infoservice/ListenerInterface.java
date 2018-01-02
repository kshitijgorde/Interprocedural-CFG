// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import java.util.Vector;
import org.w3c.dom.Document;
import java.util.StringTokenizer;
import logging.LogHolder;
import logging.LogType;
import anon.util.XMLParseException;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import anon.util.IXMLEncodable;

public class ListenerInterface implements ImmutableListenerInterface, IXMLEncodable
{
    public static final int PORT_MIN_VALUE = 1;
    public static final int PORT_MAX_VALUE = 65535;
    public static final String XML_ELEMENT_NAME = "ListenerInterface";
    public static final String XML_ELEMENT_CONTAINER_NAME = "ListenerInterfaces";
    public static final String XML_ATTR_HIDDEN = "hidden";
    public static final String XML_ATTR_VIRTUAL = "virtual";
    public static final String XML_ELEM_HOST = "Host";
    public static final String XML_ELEM_PORT = "Port";
    public static final String XML_ELEM_FILE = "File";
    private long m_endOfBlocking;
    private String m_strHostname;
    private int m_iInetPort;
    private int m_iProtocolType;
    private boolean m_bUseInterface;
    private boolean m_bVirtual;
    private boolean m_bHidden;
    
    public ListenerInterface(final Element element) throws XMLParseException {
        this.m_endOfBlocking = 0L;
        this.m_bUseInterface = false;
        this.m_bVirtual = false;
        this.m_bHidden = false;
        this.m_bHidden = XMLUtil.parseAttribute(element, "hidden", false);
        this.m_bVirtual = XMLUtil.parseAttribute(element, "virtual", false);
        if (this.m_bVirtual && this.m_bHidden) {
            this.m_bHidden = false;
            this.m_bVirtual = false;
        }
        String protocol = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Type"), null);
        if (protocol == null) {
            protocol = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "NetworkProtocol"), null);
        }
        this.setProtocol(protocol);
        String hostname;
        if (this.getProtocol() == 5) {
            hostname = XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "File"), null);
        }
        else {
            final Node firstChildByName = XMLUtil.getFirstChildByName(element, "Host");
            final Node firstChildByName2 = XMLUtil.getFirstChildByName(element, "IP");
            if (firstChildByName == null && firstChildByName2 == null) {
                throw new XMLParseException("Host,IP", "Neither Host nor IP are given.");
            }
            hostname = XMLUtil.parseValue(firstChildByName, null);
            if (!isValidHostname(hostname)) {
                hostname = XMLUtil.parseValue(firstChildByName2, null);
                if (!isValidIP(hostname)) {
                    throw new XMLParseException("Host, IP", "Invalid Host and IP.");
                }
            }
        }
        this.setHostname(hostname);
        this.setPort(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, "Port"), -1));
        this.setUseInterface(true);
    }
    
    public ListenerInterface(final String s, final int n) throws IllegalArgumentException {
        this(s, n, 1);
    }
    
    public ListenerInterface(final String s) throws IllegalArgumentException {
        this.m_endOfBlocking = 0L;
        this.m_bUseInterface = false;
        this.m_bVirtual = false;
        this.m_bHidden = false;
        if (s == null) {
            throw new IllegalArgumentException("Argument given to ListenInterface constructor is NULL!");
        }
        final int index = s.indexOf(":");
        int int1 = -1;
        String substring = null;
        try {
            int1 = Integer.parseInt(s.substring(index + 1, s.length()));
        }
        catch (Exception ex) {
            LogHolder.log(4, LogType.MISC, "Could not parse listener port: ", ex);
        }
        if (index > 0) {
            substring = s.substring(0, index);
        }
        this.setHostname(substring);
        this.setPort(int1);
        this.setProtocol(1);
        this.setUseInterface(true);
    }
    
    public ListenerInterface(final String hostname, final int port, final int protocol) throws IllegalArgumentException {
        this.m_endOfBlocking = 0L;
        this.m_bUseInterface = false;
        this.m_bVirtual = false;
        this.m_bHidden = false;
        this.setHostname(hostname);
        this.setPort(port);
        this.setProtocol(protocol);
        this.setUseInterface(true);
    }
    
    public static boolean isValidPort(final int n) {
        return n >= 1 && n <= 65535;
    }
    
    public static boolean isValidProtocol(final String s) {
        return recognizeProtocol(s) != -1;
    }
    
    public static boolean isValidProtocol(final int n) {
        return recognizeProtocol(n) != -1;
    }
    
    public static boolean isValidHostname(final String s) {
        return s != null && s.length() > 0;
    }
    
    public static boolean isValidIP(final String s) {
        if (s == null || s.indexOf(45) != -1) {
            return false;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ".");
        try {
            if (stringTokenizer.countTokens() != 4 && stringTokenizer.countTokens() != 16) {
                throw new NumberFormatException();
            }
            while (stringTokenizer.hasMoreTokens()) {
                if (new Integer(stringTokenizer.nextToken()) > 255) {
                    throw new NumberFormatException();
                }
            }
        }
        catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
    
    public int getProtocol() {
        return this.m_iProtocolType;
    }
    
    public String getProtocolAsString() {
        switch (this.m_iProtocolType) {
            case 2: {
                return "RAW/TCP";
            }
            case 3: {
                return "socks";
            }
            case 4: {
                return "https";
            }
            case 1: {
                return "HTTP/TCP";
            }
            case 5: {
                return "RAW/UNIX";
            }
            default: {
                return "UNKNWON/UNKNOWN";
            }
        }
    }
    
    public String getHost() {
        return this.m_strHostname;
    }
    
    public int getPort() {
        return this.m_iInetPort;
    }
    
    public boolean equals(final ListenerInterface listenerInterface) {
        if (this.getHost() == null) {
            if (listenerInterface.getHost() != null) {
                return false;
            }
        }
        else if (!this.getHost().equals(listenerInterface.getHost())) {
            return false;
        }
        return this.getPort() == listenerInterface.getPort() && this.getProtocol() == listenerInterface.getProtocol();
    }
    
    public Element toXmlElement(final Document document) {
        return this.toXmlElementInternal(document, "ListenerInterface");
    }
    
    public void setUseInterface(final boolean bUseInterface) {
        this.m_bUseInterface = bUseInterface;
        if (bUseInterface) {
            this.m_endOfBlocking = 0L;
        }
    }
    
    public void blockInterface(final long n) {
        this.m_endOfBlocking = System.currentTimeMillis() + n;
    }
    
    public boolean isValid() {
        return isValidPort(this.getPort()) && isValidHostname(this.getHost()) && this.m_bUseInterface && this.m_endOfBlocking < System.currentTimeMillis();
    }
    
    public boolean isVirtual() {
        return this.m_bVirtual;
    }
    
    public boolean isHidden() {
        return this.m_bHidden;
    }
    
    protected static int recognizeProtocol(final String s) {
        int n = -1;
        if (s != null) {
            if (s.equalsIgnoreCase("HTTP/TCP")) {
                n = 1;
            }
            else if (s.equalsIgnoreCase("https")) {
                n = 4;
            }
            else if (s.equalsIgnoreCase("socks")) {
                n = 3;
            }
            else if (s.equalsIgnoreCase("RAW/TCP")) {
                n = 2;
            }
            else if (s.equalsIgnoreCase("RAW/UNIX")) {
                n = 5;
            }
        }
        return n;
    }
    
    protected static int recognizeProtocol(final int n) {
        if (n == 1 || n == 4 || n == 2 || n == 3) {
            return n;
        }
        return -1;
    }
    
    public void setProtocol(final String s) {
        if (!isValidProtocol(s)) {
            if (isValidHostname(this.getHost())) {
                LogHolder.log(5, LogType.NET, "Host " + this.getHost() + " has listener with " + "invalid protocol '" + s + "'!");
            }
            this.m_iProtocolType = 2;
        }
        else {
            this.m_iProtocolType = recognizeProtocol(s);
        }
    }
    
    public void setProtocol(final int n) {
        if (!isValidProtocol(n)) {
            if (isValidHostname(this.getHost())) {
                LogHolder.log(5, LogType.NET, "Host " + this.getHost() + " has listener with " + "invalid protocol '" + n + "'!");
            }
            this.m_iProtocolType = 2;
        }
        else {
            this.m_iProtocolType = recognizeProtocol(n);
        }
    }
    
    public void setPort(final int iInetPort) {
        if (!isValidPort(iInetPort)) {
            this.m_iInetPort = -1;
        }
        else {
            this.m_iInetPort = iInetPort;
        }
    }
    
    public void setHostname(final String strHostname) {
        if (!isValidHostname(strHostname)) {
            String s;
            if ((s = strHostname) == null) {
                s = "NULL";
            }
            LogHolder.log(5, LogType.NET, "Invalid host name: '" + s + "'");
        }
        this.m_strHostname = strHostname;
    }
    
    public Vector toVector() {
        final Vector<ListenerInterface> vector = new Vector<ListenerInterface>();
        vector.addElement(this);
        return vector;
    }
    
    protected Element toXmlElementInternal(final Document document, final String s) {
        final Element element = document.createElement(s);
        final Element element2 = document.createElement("Type");
        Node element3 = null;
        XMLUtil.setValue(element2, this.getProtocolAsString());
        Element element4;
        if (this.getProtocol() == 5) {
            element4 = document.createElement("File");
            XMLUtil.setValue(element4, this.m_strHostname);
        }
        else {
            element3 = document.createElement("Port");
            XMLUtil.setValue(element3, this.m_iInetPort);
            element4 = document.createElement("Host");
            XMLUtil.setValue(element4, this.m_strHostname);
        }
        if (this.m_bHidden) {
            XMLUtil.setAttribute(element, "hidden", this.m_bHidden);
        }
        else if (this.m_bVirtual) {
            XMLUtil.setAttribute(element, "virtual", this.m_bVirtual);
        }
        element.appendChild(element2);
        if (element3 != null) {
            element.appendChild(element3);
        }
        element.appendChild(element4);
        return element;
    }
    
    public String toString() {
        return "ListenerInterface (Protocol: " + this.getProtocolAsString() + ")- Host: " + this.getHost() + " Port: " + this.getPort();
    }
}
