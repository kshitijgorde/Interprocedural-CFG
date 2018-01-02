// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.http;

import java.net.InetSocketAddress;
import org.apache.activemq.util.ServiceStopper;
import org.eclipse.jetty.servlet.ServletMapping;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.apache.activemq.transport.xstream.XStreamWireFormat;
import org.apache.activemq.command.BrokerInfo;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.apache.activemq.transport.util.TextWireFormat;
import java.net.URI;
import org.apache.activemq.transport.TransportServerSupport;

public class HttpTransportServer extends TransportServerSupport
{
    private URI bindAddress;
    private TextWireFormat wireFormat;
    private Server server;
    private Connector connector;
    private HttpTransportFactory transportFactory;
    
    public HttpTransportServer(final URI uri, final HttpTransportFactory factory) {
        super(uri);
        this.bindAddress = uri;
        this.transportFactory = factory;
    }
    
    @Override
    public void setBrokerInfo(final BrokerInfo brokerInfo) {
    }
    
    public TextWireFormat getWireFormat() {
        if (this.wireFormat == null) {
            this.wireFormat = this.createWireFormat();
        }
        return this.wireFormat;
    }
    
    public void setWireFormat(final TextWireFormat wireFormat) {
        this.wireFormat = wireFormat;
    }
    
    protected TextWireFormat createWireFormat() {
        return new XStreamWireFormat();
    }
    
    protected void setConnector(final Connector connector) {
        this.connector = connector;
    }
    
    @Override
    protected void doStart() throws Exception {
        this.server = new Server();
        if (this.connector == null) {
            this.connector = (Connector)new SocketConnector();
        }
        this.connector.setHost(this.bindAddress.getHost());
        this.connector.setPort(this.bindAddress.getPort());
        this.connector.setServer(this.server);
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
        holder.setName("httpTunnel");
        holder.setClassName(HttpTunnelServlet.class.getName());
        servletHandler.setServlets(new ServletHolder[] { holder });
        final ServletMapping mapping = new ServletMapping();
        mapping.setServletName("httpTunnel");
        mapping.setPathSpec("/*");
        servletHandler.setServletMappings(new ServletMapping[] { mapping });
        contextHandler.setAttribute("acceptListener", (Object)this.getAcceptListener());
        contextHandler.setAttribute("wireFormat", (Object)this.getWireFormat());
        contextHandler.setAttribute("transportFactory", (Object)this.transportFactory);
        contextHandler.setAttribute("transportOptions", (Object)this.transportOptions);
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
}
