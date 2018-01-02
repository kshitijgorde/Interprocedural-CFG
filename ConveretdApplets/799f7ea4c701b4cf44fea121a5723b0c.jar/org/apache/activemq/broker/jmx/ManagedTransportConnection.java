// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.jmx;

import org.slf4j.LoggerFactory;
import java.util.Hashtable;
import org.apache.activemq.util.IOExceptionSupport;
import org.apache.activemq.util.JMXSupport;
import org.apache.activemq.command.Response;
import org.apache.activemq.command.ConnectionInfo;
import java.io.IOException;
import org.apache.activemq.broker.Connection;
import org.apache.activemq.thread.TaskRunnerFactory;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.transport.Transport;
import org.apache.activemq.broker.TransportConnector;
import javax.management.ObjectName;
import org.slf4j.Logger;
import org.apache.activemq.broker.TransportConnection;

public class ManagedTransportConnection extends TransportConnection
{
    private static final Logger LOG;
    private final ManagementContext managementContext;
    private final ObjectName connectorName;
    private ConnectionViewMBean mbean;
    private ObjectName byClientIdName;
    private ObjectName byAddressName;
    
    public ManagedTransportConnection(final TransportConnector connector, final Transport transport, final Broker broker, final TaskRunnerFactory factory, final ManagementContext context, final ObjectName connectorName) throws IOException {
        super(connector, transport, broker, factory);
        this.managementContext = context;
        this.connectorName = connectorName;
        this.mbean = new ConnectionView(this);
        this.registerMBean(this.byAddressName = this.createByAddressObjectName("address", transport.getRemoteAddress()));
    }
    
    public void doStop() throws Exception {
        if (this.isStarting()) {
            this.setPendingStop(true);
            return;
        }
        synchronized (this) {
            this.unregisterMBean(this.byClientIdName);
            this.unregisterMBean(this.byAddressName);
            this.byClientIdName = null;
            this.byAddressName = null;
        }
        super.doStop();
    }
    
    public void setConnectionId(final String connectionId) throws IOException {
    }
    
    @Override
    public Response processAddConnection(final ConnectionInfo info) throws Exception {
        final Response answer = super.processAddConnection(info);
        final String clientId = info.getClientId();
        if (clientId != null && this.byClientIdName == null) {
            this.registerMBean(this.byClientIdName = this.createByClientIdObjectName(clientId));
        }
        return answer;
    }
    
    protected void registerMBean(final ObjectName name) {
        if (name != null) {
            try {
                AnnotatedMBean.registerMBean(this.managementContext, this.mbean, name);
            }
            catch (Throwable e) {
                ManagedTransportConnection.LOG.warn("Failed to register MBean: " + name);
                ManagedTransportConnection.LOG.debug("Failure reason: " + e, e);
            }
        }
    }
    
    protected void unregisterMBean(final ObjectName name) {
        if (name != null) {
            try {
                this.managementContext.unregisterMBean(name);
            }
            catch (Throwable e) {
                ManagedTransportConnection.LOG.warn("Failed to unregister mbean: " + name);
                ManagedTransportConnection.LOG.debug("Failure reason: " + e, e);
            }
        }
    }
    
    protected ObjectName createByAddressObjectName(final String type, final String value) throws IOException {
        final Hashtable map = this.connectorName.getKeyPropertyList();
        try {
            return new ObjectName(this.connectorName.getDomain() + ":" + "BrokerName=" + JMXSupport.encodeObjectNamePart(map.get("BrokerName")) + "," + "Type=Connection," + "ConnectorName=" + JMXSupport.encodeObjectNamePart(map.get("ConnectorName")) + "," + "ViewType=" + JMXSupport.encodeObjectNamePart(type) + "," + "Name=" + JMXSupport.encodeObjectNamePart(value));
        }
        catch (Throwable e) {
            throw IOExceptionSupport.create(e);
        }
    }
    
    protected ObjectName createByClientIdObjectName(final String value) throws IOException {
        final Hashtable map = this.connectorName.getKeyPropertyList();
        try {
            return new ObjectName(this.connectorName.getDomain() + ":" + "BrokerName=" + JMXSupport.encodeObjectNamePart(map.get("BrokerName")) + "," + "Type=Connection," + "ConnectorName=" + JMXSupport.encodeObjectNamePart(map.get("ConnectorName")) + "," + "Connection=" + JMXSupport.encodeObjectNamePart(value));
        }
        catch (Throwable e) {
            throw IOExceptionSupport.create(e);
        }
    }
    
    static {
        LOG = LoggerFactory.getLogger(ManagedTransportConnection.class);
    }
}
