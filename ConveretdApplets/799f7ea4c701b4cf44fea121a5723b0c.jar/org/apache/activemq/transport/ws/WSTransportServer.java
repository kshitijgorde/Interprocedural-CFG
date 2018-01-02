// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.ws;

import org.apache.activemq.command.BrokerInfo;
import java.net.InetSocketAddress;
import org.apache.activemq.util.ServiceStopper;
import org.eclipse.jetty.servlet.ServletMapping;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import java.net.URI;
import org.apache.activemq.transport.TransportServerSupport;

public class WSTransportServer extends TransportServerSupport
{
    private URI bindAddress;
    private Server server;
    private Connector connector;
    
    public WSTransportServer(final URI location) {
        super(location);
        this.bindAddress = location;
    }
    
    @Override
    protected void doStart() throws Exception {
        this.server = new Server();
        if (this.connector == null) {
            this.connector = (Connector)new SocketConnector();
        }
        this.connector.setHost(this.bindAddress.getHost());
        this.connector.setPort(this.bindAddress.getPort());
        this.server.setConnectors(new Connector[] { this.connector });
        final ContextHandler contextHandler = new ContextHandler();
        contextHandler.setContextPath("/");
        contextHandler.setServer(this.server);
        this.server.setHandler((Handler)contextHandler);
        final SessionHandler sessionHandler = new SessionHandler();
        contextHandler.setHandler((Handler)sessionHandler);
        final ServletHandler servletHandler = new ServletHandler();
        sessionHandler.setHandler((Handler)servletHandler);
        final ServletHolder holder = new ServletHolder();
        holder.setName("WSStomp");
        holder.setClassName(StompServlet.class.getName());
        servletHandler.setServlets(new ServletHolder[] { holder });
        final ServletMapping mapping = new ServletMapping();
        mapping.setServletName("WSStomp");
        mapping.setPathSpec("/*");
        servletHandler.setServletMappings(new ServletMapping[] { mapping });
        contextHandler.setAttribute("acceptListener", (Object)this.getAcceptListener());
        this.server.start();
    }
    
    @Override
    protected void doStop(final ServiceStopper stopper) throws Exception {
        final Server temp = this.server;
        this.server = null;
        if (temp != null) {
            temp.stop();
        }
    }
    
    @Override
    public InetSocketAddress getSocketAddress() {
        return null;
    }
    
    @Override
    public void setBrokerInfo(final BrokerInfo brokerInfo) {
    }
}
