// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

import java.io.IOException;

public class HttpClient
{
    private HostConfiguration hostConfiguration;
    private HttpConnectionManager httpConnectionManager;
    private HttpState state;
    private boolean strictMode;
    private int connectionTimeout;
    private int timeoutInMilliseconds;
    private long httpConnectionTimeout;
    
    public HttpClient() {
        this(new SimpleHttpConnectionManager());
    }
    
    public HttpClient(final HttpConnectionManager httpConnectionManager) {
        this.strictMode = false;
        this.connectionTimeout = 0;
        this.timeoutInMilliseconds = 0;
        this.httpConnectionTimeout = 0L;
        if (httpConnectionManager == null) {
            throw new IllegalArgumentException("httpConnectionManager cannot be null");
        }
        this.state = new HttpState();
        this.httpConnectionManager = httpConnectionManager;
        this.hostConfiguration = new HostConfiguration();
    }
    
    public synchronized void setConnectionTimeout(final int newTimeoutInMilliseconds) {
        this.connectionTimeout = newTimeoutInMilliseconds;
    }
    
    public String getHost() {
        return this.hostConfiguration.getHost();
    }
    
    public synchronized void setHostConfiguration(final HostConfiguration hostConfiguration) {
        this.hostConfiguration = hostConfiguration;
    }
    
    public synchronized HostConfiguration getHostConfiguration() {
        return this.hostConfiguration;
    }
    
    public synchronized void setHttpConnectionFactoryTimeout(final long timeout) {
        this.httpConnectionTimeout = timeout;
    }
    
    public synchronized void setHttpConnectionManager(final HttpConnectionManager httpConnectionManager) {
        this.httpConnectionManager = httpConnectionManager;
    }
    
    public synchronized HttpConnectionManager getHttpConnectionManager() {
        return this.httpConnectionManager;
    }
    
    public int getPort() {
        return this.hostConfiguration.getPort();
    }
    
    public synchronized void setState(final HttpState state) {
        this.state = state;
    }
    
    public synchronized HttpState getState() {
        return this.state;
    }
    
    public synchronized void setStrictMode(final boolean strictMode) {
        this.strictMode = strictMode;
    }
    
    public synchronized boolean isStrictMode() {
        return this.strictMode;
    }
    
    public synchronized void setTimeout(final int newTimeoutInMilliseconds) {
        this.timeoutInMilliseconds = newTimeoutInMilliseconds;
    }
    
    public int executeMethod(final HttpMethod method) throws IOException, HttpException {
        return this.executeMethod((method.getHostConfiguration() != null) ? method.getHostConfiguration() : this.getHostConfiguration(), method, null);
    }
    
    public int executeMethod(final HostConfiguration hostConfiguration, final HttpMethod method) throws IOException, HttpException {
        return this.executeMethod(hostConfiguration, method, null);
    }
    
    public int executeMethod(HostConfiguration hostConfiguration, final HttpMethod method, final HttpState state) throws IOException, HttpException {
        if (method == null) {
            throw new IllegalArgumentException("HttpMethod parameter may not be null");
        }
        if (hostConfiguration == null) {
            hostConfiguration = ((method.getHostConfiguration() != null) ? method.getHostConfiguration() : this.getHostConfiguration());
        }
        HostConfiguration defaultHostConfiguration = null;
        final HttpMethodDirector methodDirector = new HttpMethodDirector();
        synchronized (this) {
            methodDirector.setSoTimeout(this.timeoutInMilliseconds);
            methodDirector.setStrictMode(this.strictMode);
            methodDirector.setConnectionTimeout(this.connectionTimeout);
            methodDirector.setHttpConnectionFactoryTimeout(this.httpConnectionTimeout);
            methodDirector.setState((state == null) ? this.getState() : state);
            methodDirector.setConnectionManager(this.httpConnectionManager);
            defaultHostConfiguration = this.getHostConfiguration();
        }
        final HostConfiguration methodConfiguration = new HostConfiguration(hostConfiguration);
        if (hostConfiguration != defaultHostConfiguration) {
            if (!methodConfiguration.isHostSet()) {
                methodConfiguration.setHost(defaultHostConfiguration.getHost(), defaultHostConfiguration.getVirtualHost(), defaultHostConfiguration.getPort(), defaultHostConfiguration.getProtocol());
            }
            if (!methodConfiguration.isProxySet() && defaultHostConfiguration.isProxySet()) {
                methodConfiguration.setProxy(defaultHostConfiguration.getProxyHost(), defaultHostConfiguration.getProxyPort());
            }
            if (methodConfiguration.getLocalAddress() == null && defaultHostConfiguration.getLocalAddress() != null) {
                methodConfiguration.setLocalAddress(defaultHostConfiguration.getLocalAddress());
            }
        }
        methodDirector.setHostConfiguration(methodConfiguration);
        methodDirector.setMethod(method);
        methodDirector.executeMethod();
        return methodDirector.getMethod().getStatusCode();
    }
}
