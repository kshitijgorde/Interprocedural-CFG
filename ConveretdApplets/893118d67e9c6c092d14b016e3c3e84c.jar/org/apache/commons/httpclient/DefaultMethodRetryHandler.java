// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

public class DefaultMethodRetryHandler implements MethodRetryHandler
{
    private boolean requestSentRetryEnabled;
    private int retryCount;
    
    public DefaultMethodRetryHandler() {
        this.retryCount = 3;
        this.requestSentRetryEnabled = false;
    }
    
    public void setRequestSentRetryEnabled(final boolean requestSentRetryEnabled) {
        this.requestSentRetryEnabled = requestSentRetryEnabled;
    }
    
    public boolean isRequestSentRetryEnabled() {
        return this.requestSentRetryEnabled;
    }
    
    public void setRetryCount(final int retryCount) {
        this.retryCount = retryCount;
    }
    
    public int getRetryCount() {
        return this.retryCount;
    }
    
    public boolean retryMethod(final HttpMethod method, final HttpConnection connection, final HttpRecoverableException recoverableException, final int executionCount, final boolean requestSent) {
        return (!requestSent || this.requestSentRetryEnabled) && executionCount <= this.retryCount;
    }
}
