// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.methods;

import org.apache.commons.httpclient.HttpException;
import java.io.IOException;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpMethodBase;

public abstract class ExpectContinueMethod extends HttpMethodBase
{
    private boolean useExpectHeader;
    
    public ExpectContinueMethod() {
        this.useExpectHeader = false;
    }
    
    public ExpectContinueMethod(final String uri) {
        super(uri);
        this.useExpectHeader = false;
    }
    
    public void setUseExpectHeader(final boolean value) {
        this.useExpectHeader = value;
    }
    
    public boolean getUseExpectHeader() {
        return this.useExpectHeader;
    }
    
    protected abstract boolean hasRequestContent();
    
    protected void addRequestHeaders(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        LOG.trace("enter ExpectContinueMethod.addRequestHeaders(HttpState, HttpConnection)");
        super.addRequestHeaders(state, conn);
        final boolean headerPresent = this.getRequestHeader("Expect") != null;
        if (this.getUseExpectHeader() && this.getHttpVersion().greaterEquals(HttpVersion.HTTP_1_1) && this.hasRequestContent()) {
            if (!headerPresent) {
                this.setRequestHeader("Expect", "100-continue");
            }
        }
        else if (headerPresent) {
            this.removeRequestHeader("Expect");
        }
    }
}
