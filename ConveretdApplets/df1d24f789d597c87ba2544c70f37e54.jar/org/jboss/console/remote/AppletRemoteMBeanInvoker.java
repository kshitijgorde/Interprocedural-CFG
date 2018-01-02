// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.remote;

import javax.management.ObjectName;
import java.net.MalformedURLException;
import java.net.URL;

public class AppletRemoteMBeanInvoker implements SimpleRemoteMBeanInvoker
{
    URL baseUrl;
    
    public AppletRemoteMBeanInvoker(final String baseUrl) throws MalformedURLException {
        this.baseUrl = null;
        this.baseUrl = new URL(baseUrl);
    }
    
    public Object invoke(final ObjectName name, final String operationName, final Object[] params, final String[] signature) throws Exception {
        return Util.invoke(this.baseUrl, new RemoteMBeanInvocation(name, operationName, params, signature));
    }
    
    public Object getAttribute(final ObjectName name, final String attrName) throws Exception {
        return Util.getAttribute(this.baseUrl, new RemoteMBeanAttributeInvocation(name, attrName));
    }
}
