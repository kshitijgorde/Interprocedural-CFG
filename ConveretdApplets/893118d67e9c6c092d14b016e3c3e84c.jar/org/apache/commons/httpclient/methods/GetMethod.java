// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.methods;

import org.apache.commons.httpclient.HttpMethodBase;

public class GetMethod extends HttpMethodBase
{
    public GetMethod() {
        this.setFollowRedirects(true);
    }
    
    public GetMethod(final String uri) {
        super(uri);
        LOG.trace("enter GetMethod(String)");
        this.setFollowRedirects(true);
    }
    
    public String getName() {
        return "GET";
    }
    
    public void recycle() {
        LOG.trace("enter GetMethod.recycle()");
        super.recycle();
        this.setFollowRedirects(true);
    }
}
