// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.stomp;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.util.IntrospectionSupport;
import java.util.Map;
import java.net.UnknownHostException;
import org.apache.activemq.transport.tcp.TcpTransport;
import javax.net.SocketFactory;
import java.net.URISyntaxException;
import java.io.IOException;
import org.apache.activemq.transport.Transport;
import org.apache.activemq.wireformat.WireFormat;
import java.net.Socket;
import org.apache.activemq.transport.tcp.TcpTransportFactory;
import org.apache.activemq.transport.tcp.TcpTransportServer;
import javax.net.ServerSocketFactory;
import java.net.URI;
import org.apache.activemq.broker.BrokerContext;
import org.apache.activemq.broker.BrokerServiceAware;
import org.apache.activemq.transport.nio.NIOTransportFactory;

public class StompNIOTransportFactory extends NIOTransportFactory implements BrokerServiceAware
{
    private BrokerContext brokerContext;
    
    public StompNIOTransportFactory() {
        this.brokerContext = null;
    }
    
    @Override
    protected String getDefaultWireFormatType() {
        return "stomp";
    }
    
    @Override
    protected TcpTransportServer createTcpTransportServer(final URI location, final ServerSocketFactory serverSocketFactory) throws IOException, URISyntaxException {
        return new TcpTransportServer(this, location, serverSocketFactory) {
            @Override
            protected Transport createTransport(final Socket socket, final WireFormat format) throws IOException {
                return new StompNIOTransport(format, socket);
            }
        };
    }
    
    @Override
    protected TcpTransport createTcpTransport(final WireFormat wf, final SocketFactory socketFactory, final URI location, final URI localLocation) throws UnknownHostException, IOException {
        return new StompNIOTransport(wf, socketFactory, location, localLocation);
    }
    
    @Override
    public Transport compositeConfigure(Transport transport, final WireFormat format, final Map options) {
        transport = new StompTransportFilter(transport, new LegacyFrameTranslator(), this.brokerContext);
        IntrospectionSupport.setProperties(transport, options);
        return super.compositeConfigure(transport, format, options);
    }
    
    @Override
    protected boolean isUseInactivityMonitor(final Transport transport) {
        return false;
    }
    
    @Override
    public void setBrokerService(final BrokerService brokerService) {
        this.brokerContext = brokerService.getBrokerContext();
    }
}
