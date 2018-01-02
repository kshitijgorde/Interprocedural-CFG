// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.InputStream;

public class SimpleHttpConnectionManager implements HttpConnectionManager
{
    private HttpConnection httpConnection;
    private boolean connectionStaleCheckingEnabled;
    
    public SimpleHttpConnectionManager() {
        this.connectionStaleCheckingEnabled = true;
    }
    
    public HttpConnection getConnection(final HostConfiguration hostConfiguration) {
        return this.getConnection(hostConfiguration, 0L);
    }
    
    public HttpConnection getConnection(final HostConfiguration hostConfiguration, final long timeout) {
        return this.getConnectionWithTimeout(hostConfiguration, timeout);
    }
    
    public void setConnectionStaleCheckingEnabled(final boolean connectionStaleCheckingEnabled) {
        this.connectionStaleCheckingEnabled = connectionStaleCheckingEnabled;
    }
    
    public boolean isConnectionStaleCheckingEnabled() {
        return this.connectionStaleCheckingEnabled;
    }
    
    public HttpConnection getConnectionWithTimeout(final HostConfiguration hostConfiguration, final long timeout) {
        if (this.httpConnection == null) {
            (this.httpConnection = new HttpConnection(hostConfiguration)).setHttpConnectionManager(this);
            this.httpConnection.setStaleCheckingEnabled(this.connectionStaleCheckingEnabled);
        }
        else if (!hostConfiguration.hostEquals(this.httpConnection) || !hostConfiguration.proxyEquals(this.httpConnection)) {
            if (this.httpConnection.isOpen()) {
                this.httpConnection.close();
            }
            this.httpConnection.setStaleCheckingEnabled(this.connectionStaleCheckingEnabled);
            this.httpConnection.setHost(hostConfiguration.getHost());
            this.httpConnection.setVirtualHost(hostConfiguration.getVirtualHost());
            this.httpConnection.setPort(hostConfiguration.getPort());
            this.httpConnection.setProtocol(hostConfiguration.getProtocol());
            this.httpConnection.setLocalAddress(hostConfiguration.getLocalAddress());
            this.httpConnection.setProxyHost(hostConfiguration.getProxyHost());
            this.httpConnection.setProxyPort(hostConfiguration.getProxyPort());
        }
        else {
            finishLastResponse(this.httpConnection);
        }
        return this.httpConnection;
    }
    
    public void releaseConnection(final HttpConnection conn) {
        if (conn != this.httpConnection) {
            throw new IllegalStateException("Unexpected release of an unknown connection.");
        }
        finishLastResponse(this.httpConnection);
    }
    
    static void finishLastResponse(final HttpConnection conn) {
        final InputStream lastResponse = conn.getLastResponseInputStream();
        if (lastResponse != null) {
            conn.setLastResponseInputStream(null);
            try {
                lastResponse.close();
            }
            catch (IOException ioe) {
                conn.close();
            }
        }
    }
}
