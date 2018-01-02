// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.methods;

import java.io.IOException;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.ProtocolException;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpMethodBase;

public class HeadMethod extends HttpMethodBase
{
    private int bodyCheckTimeout;
    
    public HeadMethod() {
        this.bodyCheckTimeout = -1;
        this.setFollowRedirects(true);
    }
    
    public HeadMethod(final String uri) {
        super(uri);
        this.bodyCheckTimeout = -1;
        this.setFollowRedirects(true);
    }
    
    public void setBodyCheckTimeout(final int timeout) {
        this.bodyCheckTimeout = timeout;
    }
    
    public int getBodyCheckTimeout() {
        return this.bodyCheckTimeout;
    }
    
    public String getName() {
        return "HEAD";
    }
    
    public void recycle() {
        super.recycle();
        this.setFollowRedirects(true);
    }
    
    protected void readResponseBody(final HttpState state, final HttpConnection conn) throws HttpException, IOException {
        LOG.trace("enter HeadMethod.readResponseBody(HttpState, HttpConnection)");
        if (this.bodyCheckTimeout < 0) {
            this.responseBodyConsumed();
        }
        else {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Check for non-compliant response body. Timeout in " + this.bodyCheckTimeout + " ms");
            }
            if (conn.isResponseAvailable(this.bodyCheckTimeout)) {
                if (this.isStrictMode()) {
                    throw new ProtocolException("Body content may not be sent in response to HTTP HEAD request");
                }
                LOG.warn("Body content returned in response to HTTP HEAD");
                super.readResponseBody(state, conn);
            }
        }
    }
}
