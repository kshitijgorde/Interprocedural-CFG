// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.stomp;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.util.IntrospectionSupport;
import java.util.Map;
import org.apache.activemq.wireformat.WireFormat;
import org.apache.activemq.transport.Transport;
import org.apache.activemq.broker.BrokerContext;
import org.apache.activemq.broker.BrokerServiceAware;
import org.apache.activemq.transport.tcp.TcpTransportFactory;

public class StompTransportFactory extends TcpTransportFactory implements BrokerServiceAware
{
    private BrokerContext brokerContext;
    
    public StompTransportFactory() {
        this.brokerContext = null;
    }
    
    @Override
    protected String getDefaultWireFormatType() {
        return "stomp";
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
