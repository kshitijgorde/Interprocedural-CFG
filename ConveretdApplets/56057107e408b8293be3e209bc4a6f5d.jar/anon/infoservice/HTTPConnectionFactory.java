// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import HTTPClient.DefaultAuthHandler;
import logging.LogHolder;
import logging.LogType;
import HTTPClient.AuthorizationPrompter;
import HTTPClient.AuthorizationInfo;
import HTTPClient.NVPair;
import HTTPClient.SocksException;
import HTTPClient.HTTPConnection;
import java.util.Vector;

public class HTTPConnectionFactory
{
    public static final int HTTP_ENCODING_PLAIN = 0;
    public static final int HTTP_ENCODING_ZLIB = 1;
    public static final int HTTP_ENCODING_GZIP = 2;
    public static final String HTTP_ENCODING_ZLIB_STRING = "deflate";
    public static final String HTTP_ENCODING_GZIP_STRING = "gzip";
    private static Class ms_HTTPConnectionClass;
    private static HTTPConnectionFactory ms_httpConnectionFactoryInstance;
    private Vector m_vecHTTPConnections;
    private int m_timeout;
    private ImmutableProxyInterface m_proxyInterface;
    private boolean m_bUseAuth;
    private Class m_classHTTPCLient_ContentEncodingeModule;
    static /* synthetic */ Class class$HTTPClient$HTTPConnection;
    static /* synthetic */ Class class$java$lang$String;
    
    private HTTPConnectionFactory() {
        this.setNewProxySettings(null, this.m_bUseAuth = false);
        this.m_timeout = 10;
        try {
            this.m_classHTTPCLient_ContentEncodingeModule = Class.forName("HTTPClient.ContentEncodingModule");
        }
        catch (ClassNotFoundException ex) {}
    }
    
    public static HTTPConnectionFactory getInstance() {
        if (HTTPConnectionFactory.ms_httpConnectionFactoryInstance == null) {
            HTTPConnectionFactory.ms_httpConnectionFactoryInstance = new HTTPConnectionFactory();
        }
        return HTTPConnectionFactory.ms_httpConnectionFactoryInstance;
    }
    
    public synchronized void setNewProxySettings(final ImmutableProxyInterface proxyInterface, final boolean bUseAuth) {
        this.m_proxyInterface = proxyInterface;
        this.m_bUseAuth = bUseAuth;
        if (proxyInterface == null || !proxyInterface.isValid()) {
            this.m_proxyInterface = null;
            HTTPConnection.setProxyServer(null, -1);
            HTTPConnection.setSocksServer(null, -1);
            return;
        }
        if (proxyInterface.getProtocol() == 1) {
            HTTPConnection.setProxyServer(proxyInterface.getHost(), proxyInterface.getPort());
            HTTPConnection.setSocksServer(null, -1);
        }
        else if (proxyInterface.getProtocol() == 3) {
            HTTPConnection.setProxyServer(null, -1);
            try {
                HTTPConnection.setSocksServer(proxyInterface.getHost(), proxyInterface.getPort(), 5);
            }
            catch (SocksException ex) {}
            if (this.m_bUseAuth) {
                AuthorizationInfo.addAuthorization(new AuthorizationInfo(proxyInterface.getHost(), proxyInterface.getPort(), "SOCKS5", "USER/PASS", new NVPair[] { new NVPair(proxyInterface.getAuthenticationUserID(), proxyInterface.getAuthenticationPassword()) }, null));
            }
        }
    }
    
    public synchronized void setTimeout(int timeout) {
        if (timeout < 0) {
            timeout = 0;
        }
        this.m_timeout = timeout;
    }
    
    public synchronized int getTimeout() {
        return this.m_timeout;
    }
    
    public synchronized HTTPConnection createHTTPConnection(final ListenerInterface listenerInterface) {
        return this.createHTTPConnection(listenerInterface, 0, true, null);
    }
    
    public synchronized HTTPConnection createHTTPConnection(final ListenerInterface listenerInterface, final int n, final boolean b) {
        return this.createHTTPConnection(listenerInterface, n, b, null);
    }
    
    public synchronized HTTPConnection createHTTPConnection(final ListenerInterface listenerInterface, final int n, final boolean b, final Vector vector) {
        HTTPConnection httpConnectionInternal = null;
        synchronized (this) {
            httpConnectionInternal = this.createHTTPConnectionInternal(listenerInterface);
            if (this.m_proxyInterface != null && this.m_proxyInterface.isAuthenticationUsed()) {
                DefaultAuthHandler.setAuthorizationPrompter(new AuthorizationPrompter() {
                    boolean bAlreadyTried = false;
                    String password;
                    
                    public synchronized NVPair getUsernamePassword(final AuthorizationInfo authorizationInfo) {
                        try {
                            this.password = HTTPConnectionFactory.this.m_proxyInterface.getAuthenticationPassword();
                            if (this.password == null) {
                                return null;
                            }
                            if (this.bAlreadyTried) {
                                if (!(HTTPConnectionFactory.this.m_proxyInterface instanceof ProxyInterface)) {
                                    return null;
                                }
                                ((ProxyInterface)HTTPConnectionFactory.this.m_proxyInterface).clearAuthenticationPassword();
                            }
                            this.bAlreadyTried = true;
                            return new NVPair(HTTPConnectionFactory.this.m_proxyInterface.getAuthenticationUserID(), this.password);
                        }
                        catch (Exception ex) {
                            LogHolder.log(2, LogType.NET, ex);
                            return null;
                        }
                    }
                });
            }
        }
        replaceHeader(httpConnectionInternal, new NVPair("Cache-Control", "no-cache"));
        replaceHeader(httpConnectionInternal, new NVPair("Pragma", "no-cache"));
        if (vector != null) {
            synchronized (vector) {
                for (int i = 0; i < vector.size(); ++i) {
                    replaceHeader(httpConnectionInternal, vector.elementAt(i));
                }
            }
        }
        if (n != 0) {
            if ((n & 0x1) > 0) {
                if (b) {
                    httpConnectionInternal.addModule(this.m_classHTTPCLient_ContentEncodingeModule, -1);
                }
                else {
                    httpConnectionInternal.removeModule(this.m_classHTTPCLient_ContentEncodingeModule);
                    replaceHeader(httpConnectionInternal, new NVPair("Content-Encoding", "deflate"));
                }
            }
        }
        else {
            httpConnectionInternal.removeModule(this.m_classHTTPCLient_ContentEncodingeModule);
        }
        httpConnectionInternal.setTimeout(this.getTimeout() * 1000);
        return httpConnectionInternal;
    }
    
    public synchronized HTTPConnection createHTTPConnection(final ListenerInterface listenerInterface, final ImmutableProxyInterface immutableProxyInterface) {
        return this.createHTTPConnection(listenerInterface, immutableProxyInterface, 0, true, null);
    }
    
    public synchronized HTTPConnection createHTTPConnection(final ListenerInterface listenerInterface, final ImmutableProxyInterface immutableProxyInterface, final int n, final boolean b, final Vector vector) {
        final ImmutableProxyInterface proxyInterface = this.m_proxyInterface;
        this.setNewProxySettings(immutableProxyInterface, this.m_bUseAuth);
        final HTTPConnection httpConnection = this.createHTTPConnection(listenerInterface, n, b, vector);
        this.setNewProxySettings(proxyInterface, this.m_bUseAuth);
        return httpConnection;
    }
    
    private static void replaceHeader(final HTTPConnection httpConnection, final NVPair nvPair) {
        final NVPair[] defaultHeaders = httpConnection.getDefaultHeaders();
        if (defaultHeaders == null || defaultHeaders.length == 0) {
            httpConnection.setDefaultHeaders(new NVPair[] { nvPair });
        }
        else {
            for (int i = 0; i < defaultHeaders.length; ++i) {
                if (defaultHeaders[i].getName().equalsIgnoreCase(nvPair.getName())) {
                    defaultHeaders[i] = nvPair;
                    httpConnection.setDefaultHeaders(defaultHeaders);
                    return;
                }
            }
            final NVPair[] defaultHeaders2 = new NVPair[defaultHeaders.length + 1];
            System.arraycopy(defaultHeaders, 0, defaultHeaders2, 0, defaultHeaders.length);
            defaultHeaders2[defaultHeaders.length] = nvPair;
            httpConnection.setDefaultHeaders(defaultHeaders2);
        }
    }
    
    private static void setHTTPConnectionClass(final Class ms_HTTPConnectionClass) {
        if (((HTTPConnectionFactory.class$HTTPClient$HTTPConnection == null) ? (HTTPConnectionFactory.class$HTTPClient$HTTPConnection = class$("HTTPClient.HTTPConnection")) : HTTPConnectionFactory.class$HTTPClient$HTTPConnection).isAssignableFrom(ms_HTTPConnectionClass)) {
            HTTPConnectionFactory.ms_httpConnectionFactoryInstance = null;
            HTTPConnectionFactory.ms_HTTPConnectionClass = ms_HTTPConnectionClass;
            return;
        }
        throw new IllegalArgumentException("This is not a valid HTTPConnection class: " + ms_HTTPConnectionClass);
    }
    
    private Vector getCreatedHTTPConnections() {
        return this.m_vecHTTPConnections;
    }
    
    private HTTPConnection createHTTPConnectionInternal(final ListenerInterface listenerInterface) {
        HTTPConnection httpConnection;
        if (HTTPConnectionFactory.ms_HTTPConnectionClass == ((HTTPConnectionFactory.class$HTTPClient$HTTPConnection == null) ? (HTTPConnectionFactory.class$HTTPClient$HTTPConnection = class$("HTTPClient.HTTPConnection")) : HTTPConnectionFactory.class$HTTPClient$HTTPConnection)) {
            httpConnection = new HTTPConnection(listenerInterface.getHost(), listenerInterface.getPort());
        }
        else {
            final Class[] array = new Class[2];
            final Object[] array2 = new Object[2];
            array[0] = ((HTTPConnectionFactory.class$java$lang$String == null) ? (HTTPConnectionFactory.class$java$lang$String = class$("java.lang.String")) : HTTPConnectionFactory.class$java$lang$String);
            array[1] = Integer.TYPE;
            array2[0] = listenerInterface.getHost();
            array2[1] = new Integer(listenerInterface.getPort());
            try {
                httpConnection = HTTPConnectionFactory.ms_HTTPConnectionClass.getConstructor((Class<?>[])array).newInstance(array2);
            }
            catch (Exception ex) {
                throw new IllegalArgumentException("Could not construct an HTTPConnection! " + ex);
            }
            if (this.m_vecHTTPConnections == null) {
                this.m_vecHTTPConnections = new Vector();
            }
            this.m_vecHTTPConnections.addElement(httpConnection);
        }
        return httpConnection;
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
        HTTPConnectionFactory.ms_HTTPConnectionClass = ((HTTPConnectionFactory.class$HTTPClient$HTTPConnection == null) ? (HTTPConnectionFactory.class$HTTPClient$HTTPConnection = class$("HTTPClient.HTTPConnection")) : HTTPConnectionFactory.class$HTTPClient$HTTPConnection);
    }
}
