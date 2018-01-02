// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import org.w3c.dom.Document;
import HTTPClient.NVPair;
import HTTPClient.Codecs;
import anon.util.XMLParseException;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import org.w3c.dom.Element;
import anon.util.IPasswordReader;
import anon.util.IXMLEncodable;

public final class ProxyInterface extends ListenerInterface implements ImmutableProxyInterface, IXMLEncodable
{
    private static String XML_USE_AUTHENTICATION;
    private static String XML_USE_PROXY;
    private static String XML_AUTHENTICATION_USER_ID;
    private String m_authenticationPassword;
    private String m_authenticationUserID;
    private boolean m_bUseAuthentication;
    private boolean m_bUseInterface;
    private IPasswordReader m_passwordReader;
    private static final long AUTH_PASS_CANCEL_WAIT_TIME = 6000L;
    private volatile long m_authPassLastCancelTime;
    private boolean m_bAuthPassDialogShown;
    
    public ProxyInterface(final Element element, final IPasswordReader passwordReader) throws XMLParseException {
        super(element);
        this.m_authenticationPassword = null;
        this.m_authenticationUserID = null;
        this.m_bUseAuthentication = false;
        this.m_bUseInterface = true;
        this.m_authPassLastCancelTime = 0L;
        this.m_bAuthPassDialogShown = false;
        this.m_passwordReader = passwordReader;
        try {
            this.setAuthenticationUserID(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, ProxyInterface.XML_AUTHENTICATION_USER_ID), null));
        }
        catch (IllegalStateException ex) {
            this.setAuthenticationUserID(null);
        }
        try {
            this.setUseAuthentication(Boolean.valueOf(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, ProxyInterface.XML_USE_AUTHENTICATION), null)));
        }
        catch (IllegalStateException ex2) {
            this.setUseAuthentication(false);
        }
        this.setUseInterface(Boolean.valueOf(XMLUtil.parseValue(XMLUtil.getFirstChildByName(element, ProxyInterface.XML_USE_PROXY), null)));
    }
    
    public ProxyInterface(final String s, final int n, final IPasswordReader passwordReader) throws IllegalArgumentException {
        this(s, n, 1, passwordReader);
    }
    
    public ProxyInterface(final String s, final int n, final int n2, final IPasswordReader passwordReader) throws IllegalArgumentException {
        this(s, n, n2, null, passwordReader, false, true);
    }
    
    public ProxyInterface(final String s, final int n, final String s2, final String s3, final IPasswordReader passwordReader, final boolean b, final boolean b2) throws IllegalArgumentException {
        this(s, n, ListenerInterface.recognizeProtocol(s2), s3, passwordReader, b, b2);
    }
    
    public ProxyInterface(final String s, final int n, final int n2, final String authenticationUserID, final IPasswordReader passwordReader, final boolean useAuthentication, final boolean useInterface) throws IllegalArgumentException {
        super(s, n, n2);
        this.m_authenticationPassword = null;
        this.m_authenticationUserID = null;
        this.m_bUseAuthentication = false;
        this.m_bUseInterface = true;
        this.m_authPassLastCancelTime = 0L;
        this.m_bAuthPassDialogShown = false;
        this.m_passwordReader = passwordReader;
        this.setAuthenticationUserID(authenticationUserID);
        this.setUseAuthentication(useAuthentication);
        this.setUseInterface(useInterface);
    }
    
    public static String getXMLElementName() {
        return "ProxyInterface";
    }
    
    public static boolean isValidUserID(final String s) {
        return s != null;
    }
    
    public boolean isAuthenticationUsed() {
        return this.m_bUseAuthentication;
    }
    
    public boolean setUseAuthentication(final boolean bUseAuthentication) throws IllegalStateException {
        if (!this.isAuthenticationUsed() && bUseAuthentication) {
            String s = null;
            if (this.m_passwordReader == null) {
                s = "No password reader!";
            }
            if (this.getProtocol() != 1 && this.getProtocol() != 3) {
                s = "Wrong protocol type!";
            }
            if (!isValidUserID(this.getAuthenticationUserID())) {
                s = "Invalid user ID!";
            }
            if (s != null) {
                throw new IllegalStateException(": Cannot set proxy authentication! " + s);
            }
        }
        return this.m_bUseAuthentication = bUseAuthentication;
    }
    
    public String getAuthenticationPassword() throws IllegalStateException {
        if (this.m_passwordReader == null) {
            throw new IllegalStateException("No password reader!");
        }
        if (this.m_authPassLastCancelTime >= System.currentTimeMillis()) {
            return this.m_authenticationPassword;
        }
        synchronized (this) {
            if (this.m_bAuthPassDialogShown) {
                return this.m_authenticationPassword;
            }
            this.m_bAuthPassDialogShown = true;
        }
        if (this.m_authenticationPassword == null || this.m_authenticationPassword.length() == 0) {
            this.m_authenticationPassword = this.m_passwordReader.readPassword(this);
        }
        if (this.m_authenticationPassword == null) {
            this.m_authPassLastCancelTime = System.currentTimeMillis() + 6000L;
        }
        this.m_bAuthPassDialogShown = false;
        return this.m_authenticationPassword;
    }
    
    public void clearAuthenticationPassword() {
        this.m_authenticationPassword = null;
    }
    
    public String getAuthenticationUserID() {
        return this.m_authenticationUserID;
    }
    
    public void setAuthenticationUserID(final String authenticationUserID) {
        if (isValidUserID(authenticationUserID)) {
            if (this.m_authenticationUserID == null || !this.m_authenticationUserID.equals(authenticationUserID)) {
                this.m_authenticationUserID = authenticationUserID;
                this.m_authenticationPassword = null;
                if (this.isAuthenticationUsed() && this.isValid()) {
                    this.getAuthenticationPassword();
                }
            }
        }
        else {
            this.m_authenticationUserID = null;
            this.m_authenticationPassword = null;
            this.setUseAuthentication(false);
        }
    }
    
    public String getProxyAuthorizationHeaderAsString() throws IllegalStateException {
        if (!this.isAuthenticationUsed()) {
            throw new IllegalStateException("Authentication mode is not activated! Unknown state!");
        }
        return "Proxy-Authorization: Basic " + Codecs.base64Encode(this.getAuthenticationUserID() + ":" + this.getAuthenticationPassword()) + "\r\n";
    }
    
    public NVPair getProxyAuthorizationHeader() throws IllegalStateException {
        if (!this.isAuthenticationUsed()) {
            throw new IllegalStateException("Authentication mode is not activated! Unknown state!");
        }
        return new NVPair("Proxy-Authorization", "Basic " + Codecs.base64Encode(this.getAuthenticationUserID() + ":" + this.getAuthenticationPassword()));
    }
    
    public boolean equals(final ProxyInterface proxyInterface) {
        return super.equals(proxyInterface) && this.getAuthenticationUserID().equals(proxyInterface.getAuthenticationUserID()) && this.isValid() == proxyInterface.isValid() && this.isAuthenticationUsed() == proxyInterface.isAuthenticationUsed();
    }
    
    public Element toXmlElement(final Document document) {
        final Element xmlElementInternal = this.toXmlElementInternal(document, getXMLElementName());
        final Element element = document.createElement(ProxyInterface.XML_AUTHENTICATION_USER_ID);
        element.appendChild(document.createTextNode(this.getAuthenticationUserID()));
        final Element element2 = document.createElement(ProxyInterface.XML_USE_PROXY);
        XMLUtil.setValue(element2, this.isValid());
        final Element element3 = document.createElement(ProxyInterface.XML_USE_AUTHENTICATION);
        XMLUtil.setValue(element3, this.isAuthenticationUsed());
        xmlElementInternal.appendChild(element);
        xmlElementInternal.appendChild(element3);
        xmlElementInternal.appendChild(element2);
        return xmlElementInternal;
    }
    
    public boolean isValid() {
        return super.isValid() && this.m_bUseInterface;
    }
    
    public void setUseInterface(final boolean b) {
        super.setUseInterface(b);
        this.m_bUseInterface = b;
        if (this.isValid() && this.isAuthenticationUsed() && this.m_passwordReader != null && this.m_authenticationPassword == null) {
            this.getAuthenticationPassword();
        }
    }
    
    static {
        ProxyInterface.XML_USE_AUTHENTICATION = "UseAuthentication";
        ProxyInterface.XML_USE_PROXY = "UseProxy";
        ProxyInterface.XML_AUTHENTICATION_USER_ID = "AuthenticationUserID";
    }
}
