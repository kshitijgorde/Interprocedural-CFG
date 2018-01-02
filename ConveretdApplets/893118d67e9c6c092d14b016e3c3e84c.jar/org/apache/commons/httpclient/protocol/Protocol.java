// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.protocol;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Protocol
{
    private static final Map PROTOCOLS;
    private ProtocolSocketFactory socketFactory;
    private String scheme;
    private boolean secure;
    private int defaultPort;
    
    static {
        PROTOCOLS = Collections.synchronizedMap(new HashMap<Object, Object>());
    }
    
    public Protocol(final String scheme, final ProtocolSocketFactory factory, final int defaultPort) {
        if (scheme == null) {
            throw new IllegalArgumentException("scheme is null");
        }
        if (factory == null) {
            throw new IllegalArgumentException("socketFactory is null");
        }
        if (defaultPort <= 0) {
            throw new IllegalArgumentException("port is invalid: " + defaultPort);
        }
        this.scheme = scheme;
        this.socketFactory = factory;
        this.defaultPort = defaultPort;
        this.secure = false;
    }
    
    public Protocol(final String scheme, final SecureProtocolSocketFactory factory, final int defaultPort) {
        if (scheme == null) {
            throw new IllegalArgumentException("scheme is null");
        }
        if (factory == null) {
            throw new IllegalArgumentException("socketFactory is null");
        }
        if (defaultPort <= 0) {
            throw new IllegalArgumentException("port is invalid: " + defaultPort);
        }
        this.scheme = scheme;
        this.socketFactory = factory;
        this.defaultPort = defaultPort;
        this.secure = true;
    }
    
    public static Protocol getProtocol(final String id) throws IllegalStateException {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        Protocol protocol = Protocol.PROTOCOLS.get(id);
        if (protocol == null) {
            protocol = lazyRegisterProtocol(id);
        }
        return protocol;
    }
    
    public static void registerProtocol(final String id, final Protocol protocol) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        if (protocol == null) {
            throw new IllegalArgumentException("protocol is null");
        }
        Protocol.PROTOCOLS.put(id, protocol);
    }
    
    public static void unregisterProtocol(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        Protocol.PROTOCOLS.remove(id);
    }
    
    public int getDefaultPort() {
        return this.defaultPort;
    }
    
    public String getScheme() {
        return this.scheme;
    }
    
    public boolean isSecure() {
        return this.secure;
    }
    
    public ProtocolSocketFactory getSocketFactory() {
        return this.socketFactory;
    }
    
    public boolean equals(final Object obj) {
        if (obj instanceof Protocol) {
            final Protocol p = (Protocol)obj;
            return this.defaultPort == p.getDefaultPort() && this.scheme.equalsIgnoreCase(p.getScheme()) && this.secure == p.isSecure() && this.socketFactory.equals(p.getSocketFactory());
        }
        return false;
    }
    
    public int hashCode() {
        return this.scheme.hashCode();
    }
    
    public int resolvePort(final int port) {
        return (port <= 0) ? this.getDefaultPort() : port;
    }
    
    public String toString() {
        return String.valueOf(this.scheme) + ":" + this.defaultPort;
    }
    
    private static Protocol lazyRegisterProtocol(final String id) throws IllegalStateException {
        if ("http".equals(id)) {
            final Protocol http = new Protocol("http", new DefaultProtocolSocketFactory(), 80);
            registerProtocol("http", http);
            return http;
        }
        if ("https".equals(id)) {
            final Protocol https = new Protocol("https", new SSLProtocolSocketFactory(), 443);
            registerProtocol("https", https);
            return https;
        }
        throw new IllegalStateException("unsupported protocol: '" + id + "'");
    }
}
