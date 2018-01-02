// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.tool;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.server.Server;

public final class WebServer
{
    public static final int PORT = 8080;
    public static final String WEBAPP_DIR = "src/webapp";
    public static final String WEBAPP_CTX = "/";
    
    public static void main(final String[] args) throws Exception {
        final Server server = new Server();
        final Connector context = (Connector)new SocketConnector();
        context.setServer(server);
        context.setPort(8080);
        String webappDir = "src/webapp";
        if (args.length > 0) {
            webappDir = args[0];
        }
        final WebAppContext webapp = new WebAppContext();
        webapp.setServer(server);
        webapp.setContextPath("/");
        webapp.setResourceBase(webappDir);
        server.setHandler((Handler)webapp);
        server.setConnectors(new Connector[] { context });
        server.start();
    }
}
