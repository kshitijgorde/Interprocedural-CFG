// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

import org.apache.commons.httpclient.auth.AuthScheme;
import org.apache.commons.httpclient.auth.CredentialsNotAvailableException;
import org.apache.commons.httpclient.auth.MalformedChallengeException;
import java.util.HashSet;
import org.apache.commons.httpclient.auth.AuthenticationException;
import org.apache.commons.httpclient.auth.HttpAuthenticator;
import java.io.IOException;
import java.util.Set;

class HttpMethodDirector
{
    private static final int MAX_FORWARDS = 100;
    private HostConfiguration hostConfiguration;
    private HttpConnection connection;
    private HttpConnectionManager connectionManager;
    private HttpMethod method;
    private HttpState state;
    private Set proxyRealms;
    private Set realms;
    private String proxyRealm;
    private String realm;
    private boolean releaseConnection;
    private boolean strictMode;
    private int connectionTimeout;
    private int recoverableExceptionCount;
    private int soTimeout;
    private long httpConnectionFactoryTimeout;
    
    HttpMethodDirector() {
        this.proxyRealms = null;
        this.realms = null;
        this.proxyRealm = null;
        this.realm = null;
        this.releaseConnection = false;
        this.recoverableExceptionCount = 0;
    }
    
    public void setConnectionManager(final HttpConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
    
    public HttpConnectionManager getConnectionManager() {
        return this.connectionManager;
    }
    
    public void setConnectionTimeout(final int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
    
    public int getConnectionTimeout() {
        return this.connectionTimeout;
    }
    
    public void setHostConfiguration(final HostConfiguration hostConfiguration) {
        this.hostConfiguration = hostConfiguration;
    }
    
    public HostConfiguration getHostConfiguration() {
        return this.hostConfiguration;
    }
    
    public void setHttpConnectionFactoryTimeout(final long httpConnectionTimeout) {
        this.httpConnectionFactoryTimeout = httpConnectionTimeout;
    }
    
    public long getHttpConnectionFactoryTimeout() {
        return this.httpConnectionFactoryTimeout;
    }
    
    public void setMethod(final HttpMethod method) {
        this.method = method;
    }
    
    public HttpMethod getMethod() {
        return this.method;
    }
    
    public void setSoTimeout(final int soTimeout) {
        this.soTimeout = soTimeout;
    }
    
    public int getSoTimeout() {
        return this.soTimeout;
    }
    
    public void setState(final HttpState state) {
        this.state = state;
    }
    
    public HttpState getState() {
        return this.state;
    }
    
    public void setStrictMode(final boolean strictMode) {
        this.strictMode = strictMode;
    }
    
    public boolean isStrictMode() {
        return this.strictMode;
    }
    
    public void executeMethod() throws IOException, HttpException {
        this.method.setStrictMode(this.strictMode);
        try {
            int forwardCount = 0;
            while (forwardCount++ < 100) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Execute loop try " + forwardCount);
                }
                this.executeMethodForHost();
                if (!this.isRetryNeeded()) {
                    break;
                }
                if (this.method.getResponseBodyAsStream() == null) {
                    continue;
                }
                this.method.getResponseBodyAsStream().close();
            }
            if (forwardCount >= 100) {
                LOG.error("Narrowly avoided an infinite loop in execute");
                throw new ProtocolException("Maximum redirects (100) exceeded");
            }
        }
        finally {
            if (this.connection != null) {
                this.connection.setLocked(false);
            }
            if (this.method.getResponseBodyAsStream() == null) {
                this.method.releaseConnection();
            }
            else if (this.releaseConnection && this.connection != null) {
                this.connection.releaseConnection();
            }
        }
        if (this.connection != null) {
            this.connection.setLocked(false);
        }
        if (this.method.getResponseBodyAsStream() == null) {
            this.method.releaseConnection();
        }
        else if (this.releaseConnection && this.connection != null) {
            this.connection.releaseConnection();
        }
    }
    
    private MethodRetryHandler getMethodRetryHandler() {
        if (this.method instanceof HttpMethodBase) {
            return ((HttpMethodBase)this.method).getMethodRetryHandler();
        }
        return new DefaultMethodRetryHandler();
    }
    
    private boolean isRetryNeeded() {
        switch (this.method.getStatusCode()) {
            case 401:
            case 407: {
                LOG.debug("Authorization required");
                if (!this.method.getDoAuthentication()) {
                    return false;
                }
                if (this.processAuthenticationResponse(this.state, this.connection)) {
                    return false;
                }
                break;
            }
            case 301:
            case 302:
            case 303:
            case 307: {
                LOG.debug("Redirect required");
                if (!this.processRedirectResponse()) {
                    return false;
                }
                break;
            }
            default: {
                return false;
            }
        }
        return true;
    }
    
    private void addPreemtiveAuthenticationHeaders() {
        if (this.state.isAuthenticationPreemptive()) {
            LOG.debug("Preemptively sending default basic credentials");
            try {
                if (HttpAuthenticator.authenticateDefault(this.method, this.connection, this.state)) {
                    LOG.debug("Default basic credentials applied");
                }
                if (this.connection.isProxied() && HttpAuthenticator.authenticateProxyDefault(this.method, this.connection, this.state)) {
                    LOG.debug("Default basic proxy credentials applied");
                }
            }
            catch (AuthenticationException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }
    
    private boolean establishValidOpenConnection() throws IOException, HttpException {
        if (this.connection != null && !this.hostConfiguration.hostEquals(this.connection)) {
            this.connection.setLocked(false);
            this.connection.releaseConnection();
            this.connection = null;
        }
        if (this.connection == null) {
            (this.connection = this.connectionManager.getConnectionWithTimeout(this.hostConfiguration, this.httpConnectionFactoryTimeout)).setLocked(true);
            this.realms = new HashSet();
            this.proxyRealms = new HashSet();
            this.addPreemtiveAuthenticationHeaders();
        }
        try {
            if (!this.connection.isOpen()) {
                this.connection.setSoTimeout(this.soTimeout);
                this.connection.setConnectionTimeout(this.connectionTimeout);
                this.connection.open();
                if (this.connection.isProxied() && this.connection.isSecure() && !this.executeConnect()) {
                    return false;
                }
            }
            else if (!(this.method instanceof ConnectMethod) && this.connection.isProxied() && this.connection.isSecure() && !this.connection.isTransparent() && !this.executeConnect()) {
                return false;
            }
        }
        catch (IOException e) {
            this.releaseConnection = true;
            throw e;
        }
        catch (RuntimeException e2) {
            this.releaseConnection = true;
            throw e2;
        }
        return true;
    }
    
    private boolean executeConnect() throws IOException, HttpException {
        final ConnectMethod connectMethod = new ConnectMethod();
        final HttpMethod tempMethod = this.method;
        this.method = connectMethod;
        try {
            this.executeMethod();
        }
        catch (HttpException e) {
            this.method = tempMethod;
            throw e;
        }
        catch (IOException e2) {
            this.method = tempMethod;
            throw e2;
        }
        final int code = this.method.getStatusCode();
        if (code >= 200 && code < 300) {
            this.method = tempMethod;
            return true;
        }
        LOG.debug("CONNECT failed, fake the response for the original method");
        if (tempMethod instanceof HttpMethodBase) {
            ((HttpMethodBase)tempMethod).fakeResponse(connectMethod.getStatusLine(), connectMethod.getResponseHeaderGroup(), connectMethod.getResponseBodyAsStream());
        }
        else {
            this.releaseConnection = true;
            LOG.warn("Unable to fake response on method as it is not derived from HttpMethodBase.");
        }
        this.method = tempMethod;
        return false;
    }
    
    private void executeMethodForHost() throws IOException, HttpException {
        int execCount = 0;
        boolean requestSent = false;
        while (true) {
            ++execCount;
            requestSent = false;
            if (!this.establishValidOpenConnection()) {
                return;
            }
            if (LOG.isTraceEnabled()) {
                LOG.trace("Attempt number " + execCount + " to process request");
            }
            try {
                this.method.execute(this.state, this.connection);
            }
            catch (HttpRecoverableException httpre) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Closing the connection.");
                }
                this.connection.close();
                LOG.info("Recoverable exception caught when processing request");
                ++this.recoverableExceptionCount;
                if (!this.getMethodRetryHandler().retryMethod(this.method, this.connection, httpre, execCount, requestSent)) {
                    LOG.warn("Recoverable exception caught but MethodRetryHandler.retryMethod() returned false, rethrowing exception");
                    throw httpre;
                }
                continue;
            }
        }
    }
    
    private boolean processAuthenticationResponse(final HttpState state, final HttpConnection conn) {
        LOG.trace("enter HttpMethodBase.processAuthenticationResponse(HttpState, HttpConnection)");
        final int statusCode = this.method.getStatusCode();
        Header[] challenges = null;
        Set realmsUsed = null;
        String host = null;
        switch (statusCode) {
            case 401: {
                challenges = this.method.getResponseHeaders("WWW-Authenticate");
                realmsUsed = this.realms;
                host = conn.getVirtualHost();
                if (host == null) {
                    host = conn.getHost();
                    break;
                }
                break;
            }
            case 407: {
                challenges = this.method.getResponseHeaders("Proxy-Authenticate");
                realmsUsed = this.proxyRealms;
                host = conn.getProxyHost();
                break;
            }
        }
        boolean authenticated = false;
        if (challenges.length > 0) {
            AuthScheme authscheme = null;
            try {
                authscheme = HttpAuthenticator.selectAuthScheme(challenges);
            }
            catch (MalformedChallengeException e) {
                if (LOG.isErrorEnabled()) {
                    LOG.error(e.getMessage(), e);
                }
                return true;
            }
            catch (UnsupportedOperationException e2) {
                if (LOG.isErrorEnabled()) {
                    LOG.error(e2.getMessage(), e2);
                }
                return true;
            }
            StringBuffer buffer = new StringBuffer();
            buffer.append(host);
            buffer.append('#');
            buffer.append(authscheme.getID());
            final String realm = buffer.toString();
            if (realmsUsed.contains(realm)) {
                if (LOG.isInfoEnabled()) {
                    buffer = new StringBuffer();
                    buffer.append("Already tried to authenticate with '");
                    buffer.append(authscheme.getRealm());
                    buffer.append("' authentication realm at ");
                    buffer.append(host);
                    buffer.append(", but still receiving: ");
                    buffer.append(this.method.getStatusLine().toString());
                    LOG.info(buffer.toString());
                }
                return true;
            }
            realmsUsed.add(realm);
            try {
                switch (statusCode) {
                    case 401: {
                        this.method.removeRequestHeader("Authorization");
                        authenticated = HttpAuthenticator.authenticate(authscheme, this.method, conn, state);
                        this.realm = authscheme.getRealm();
                        break;
                    }
                    case 407: {
                        this.method.removeRequestHeader("Proxy-Authorization");
                        authenticated = HttpAuthenticator.authenticateProxy(authscheme, this.method, conn, state);
                        this.proxyRealm = authscheme.getRealm();
                        break;
                    }
                }
            }
            catch (CredentialsNotAvailableException e3) {
                if (LOG.isWarnEnabled()) {
                    LOG.warn(e3.getMessage());
                }
                return true;
            }
            catch (AuthenticationException e4) {
                if (LOG.isErrorEnabled()) {
                    LOG.error(e4.getMessage(), e4);
                }
                return true;
            }
            if (!authenticated) {
                LOG.debug("HttpMethodBase.execute(): Server demands authentication credentials, but none are available, so aborting.");
            }
            else {
                LOG.debug("HttpMethodBase.execute(): Server demanded authentication credentials, will try again.");
            }
        }
        return !authenticated;
    }
    
    private boolean processRedirectResponse() {
        if (!this.method.getFollowRedirects()) {
            LOG.info("Redirect requested but followRedirects is disabled");
            return false;
        }
        final Header locationHeader = this.method.getResponseHeader("location");
        if (locationHeader == null) {
            LOG.error("Received redirect response " + this.method.getStatusCode() + " but no location header");
            return false;
        }
        final String location = locationHeader.getValue();
        if (LOG.isDebugEnabled()) {
            LOG.debug("Redirect requested to location '" + location + "'");
        }
        URI redirectUri = null;
        URI currentUri = null;
        try {
            currentUri = new URI(this.connection.getProtocol().getScheme(), null, this.connection.getHost(), this.connection.getPort(), this.method.getPath());
            redirectUri = new URI(location, true);
            if (redirectUri.isRelativeURI()) {
                if (this.method.isStrictMode()) {
                    LOG.warn("Redirected location '" + location + "' is not acceptable in strict mode");
                    return false;
                }
                LOG.debug("Redirect URI is not absolute - parsing as relative");
                redirectUri = new URI(currentUri, redirectUri);
            }
        }
        catch (URIException e) {
            LOG.warn("Redirected location '" + location + "' is malformed");
            return false;
        }
        this.realms.clear();
        this.method.removeRequestHeader("Authorization");
        this.method.setPath(redirectUri.getEscapedPath());
        this.method.setQueryString(redirectUri.getEscapedQuery());
        this.hostConfiguration.setHost(redirectUri);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Redirecting from '" + currentUri.getEscapedURI() + "' to '" + redirectUri.getEscapedURI());
        }
        return true;
    }
}
