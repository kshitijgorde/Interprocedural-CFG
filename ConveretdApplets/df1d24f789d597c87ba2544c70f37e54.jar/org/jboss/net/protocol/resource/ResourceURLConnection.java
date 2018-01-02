// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.protocol.resource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.jboss.logging.Logger;
import org.jboss.net.protocol.DelegatingURLConnection;

public class ResourceURLConnection extends DelegatingURLConnection
{
    private static final Logger log;
    static /* synthetic */ Class class$org$jboss$net$protocol$resource$ResourceURLConnection;
    
    public ResourceURLConnection(final URL url) throws MalformedURLException, IOException {
        super(url);
    }
    
    protected URL makeDelegateUrl(final URL url) throws MalformedURLException, IOException {
        String name = url.getHost();
        final String file = url.getFile();
        if (file != null && !file.equals("")) {
            name += file;
        }
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL target = cl.getResource(name);
        if (target == null) {
            cl = ClassLoader.getSystemClassLoader();
            target = cl.getResource(name);
        }
        if (target == null) {
            throw new FileNotFoundException("Could not locate resource: " + name);
        }
        if (ResourceURLConnection.log.isTraceEnabled()) {
            ResourceURLConnection.log.trace("Target resource URL: " + target);
            try {
                ResourceURLConnection.log.trace("Target resource URL connection: " + target.openConnection());
            }
            catch (Exception ex) {}
        }
        return new URL(target.toExternalForm());
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError().initCause(x);
        }
    }
    
    static {
        log = Logger.getLogger((ResourceURLConnection.class$org$jboss$net$protocol$resource$ResourceURLConnection == null) ? (ResourceURLConnection.class$org$jboss$net$protocol$resource$ResourceURLConnection = class$("org.jboss.net.protocol.resource.ResourceURLConnection")) : ResourceURLConnection.class$org$jboss$net$protocol$resource$ResourceURLConnection);
    }
}
