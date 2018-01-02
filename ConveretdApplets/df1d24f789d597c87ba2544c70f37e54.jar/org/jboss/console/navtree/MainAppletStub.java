// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.navtree;

import java.applet.AppletContext;
import java.util.Properties;
import java.net.URL;
import java.applet.AppletStub;

public class MainAppletStub implements AppletStub
{
    private URL docBase;
    private URL codeBase;
    private Properties params;
    
    public MainAppletStub() throws Exception {
        this.params = new Properties(System.getProperties());
        this.docBase = new URL("http://localhost:8080/web-console/");
        this.codeBase = new URL("http://localhost:8080/web-console/");
        this.params.setProperty("RefreshTime", "5");
        this.params.setProperty("PMJMXName", "jboss.admin:service=PluginManager");
    }
    
    public boolean isActive() {
        return true;
    }
    
    public String getParameter(final String name) {
        return System.getProperty(name);
    }
    
    public AppletContext getAppletContext() {
        return null;
    }
    
    public void appletResize(final int width, final int height) {
    }
    
    public URL getDocumentBase() {
        return this.docBase;
    }
    
    public URL getCodeBase() {
        return this.codeBase;
    }
}
