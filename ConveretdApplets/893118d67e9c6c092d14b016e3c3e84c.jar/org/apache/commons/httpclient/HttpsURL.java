// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

public class HttpsURL extends HttpURL
{
    public static final char[] DEFAULT_SCHEME;
    public static final char[] _default_scheme;
    public static final int DEFAULT_PORT = 443;
    public static final int _default_port = 443;
    static final long serialVersionUID = 887844277028676648L;
    
    static {
        DEFAULT_SCHEME = new char[] { 'h', 't', 't', 'p', 's' };
        _default_scheme = HttpsURL.DEFAULT_SCHEME;
    }
    
    public HttpsURL(final char[] escaped, final String charset) throws URIException, NullPointerException {
        super.protocolCharset = charset;
        this.parseUriReference(new String(escaped), true);
        this.checkValid();
    }
    
    public HttpsURL(final char[] escaped) throws URIException, NullPointerException {
        this.parseUriReference(new String(escaped), true);
        this.checkValid();
    }
    
    public HttpsURL(final String original, final String charset) throws URIException {
        super.protocolCharset = charset;
        this.parseUriReference(original, false);
        this.checkValid();
    }
    
    public HttpsURL(final String original) throws URIException {
        this.parseUriReference(original, false);
        this.checkValid();
    }
    
    public HttpsURL(final String host, final int port, final String path) throws URIException {
        this(null, host, port, path, null, null);
        this.checkValid();
    }
    
    public HttpsURL(final String host, final int port, final String path, final String query) throws URIException {
        this(null, host, port, path, query, null);
        this.checkValid();
    }
    
    public HttpsURL(final String user, final String password, final String host) throws URIException {
        this((user == null) ? null : (String.valueOf(user) + ((password == null) ? "" : (String.valueOf(':') + password))), host, -1, null, null, null);
        this.checkValid();
    }
    
    public HttpsURL(final String user, final String password, final String host, final int port) throws URIException {
        this((user == null) ? null : (String.valueOf(user) + ((password == null) ? "" : (String.valueOf(':') + password))), host, port, null, null, null);
        this.checkValid();
    }
    
    public HttpsURL(final String user, final String password, final String host, final int port, final String path) throws URIException {
        this((user == null) ? null : (String.valueOf(user) + ((password == null) ? "" : (String.valueOf(':') + password))), host, port, path, null, null);
        this.checkValid();
    }
    
    public HttpsURL(final String user, final String password, final String host, final int port, final String path, final String query) throws URIException {
        this((user == null) ? null : (String.valueOf(user) + ((password == null) ? "" : (String.valueOf(':') + password))), host, port, path, query, null);
        this.checkValid();
    }
    
    public HttpsURL(final String host, final String path, final String query, final String fragment) throws URIException {
        this(null, host, -1, path, query, fragment);
        this.checkValid();
    }
    
    public HttpsURL(final String userinfo, final String host, final String path, final String query, final String fragment) throws URIException {
        this(userinfo, host, -1, path, query, fragment);
        this.checkValid();
    }
    
    public HttpsURL(final String userinfo, final String host, final int port, final String path) throws URIException {
        this(userinfo, host, port, path, null, null);
        this.checkValid();
    }
    
    public HttpsURL(final String userinfo, final String host, final int port, final String path, final String query) throws URIException {
        this(userinfo, host, port, path, query, null);
        this.checkValid();
    }
    
    public HttpsURL(final String userinfo, final String host, final int port, final String path, final String query, final String fragment) throws URIException {
        final StringBuffer buff = new StringBuffer();
        if (userinfo != null || host != null || port != -1) {
            super._scheme = HttpsURL.DEFAULT_SCHEME;
            buff.append(HttpsURL._default_scheme);
            buff.append("://");
            if (userinfo != null) {
                buff.append(userinfo);
                buff.append('@');
            }
            if (host != null) {
                buff.append(host);
                if (port != -1 || port != 443) {
                    buff.append(':');
                    buff.append(port);
                }
            }
        }
        if (path != null) {
            if (URI.scheme != null && !path.startsWith("/")) {
                throw new URIException(1, "abs_path requested");
            }
            buff.append(path);
        }
        if (query != null) {
            buff.append('?');
            buff.append(query);
        }
        if (fragment != null) {
            buff.append('#');
            buff.append(fragment);
        }
        this.parseUriReference(buff.toString(), false);
        this.checkValid();
    }
    
    public HttpsURL(final HttpsURL base, final String relative) throws URIException {
        this(base, new HttpsURL(relative));
    }
    
    public HttpsURL(final HttpsURL base, final HttpsURL relative) throws URIException {
        super(base, relative);
        this.checkValid();
    }
    
    protected HttpsURL() {
    }
    
    protected void checkValid() throws URIException {
        if (!this.equals(super._scheme, HttpsURL.DEFAULT_SCHEME) && super._scheme != null) {
            throw new URIException(1, "wrong class use");
        }
    }
}
