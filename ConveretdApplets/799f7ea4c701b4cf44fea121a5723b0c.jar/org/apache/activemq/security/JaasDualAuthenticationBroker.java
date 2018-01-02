// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.security;

import org.apache.activemq.broker.jmx.ManagedTransportConnector;
import org.apache.activemq.broker.Connector;
import org.apache.activemq.transport.tcp.SslTransportServer;
import org.apache.activemq.broker.TransportConnector;
import org.apache.activemq.command.ConnectionInfo;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.broker.EmptyBroker;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerFilter;

public class JaasDualAuthenticationBroker extends BrokerFilter
{
    private final JaasCertificateAuthenticationBroker sslBroker;
    private final JaasAuthenticationBroker nonSslBroker;
    
    public JaasDualAuthenticationBroker(final Broker next, final String jaasConfiguration, final String jaasSslConfiguration) {
        super(next);
        this.nonSslBroker = new JaasAuthenticationBroker(new EmptyBroker(), jaasConfiguration);
        this.sslBroker = new JaasCertificateAuthenticationBroker(new EmptyBroker(), jaasSslConfiguration);
    }
    
    @Override
    public void addConnection(final ConnectionContext context, final ConnectionInfo info) throws Exception {
        if (context.getSecurityContext() == null) {
            final Connector connector = context.getConnector();
            boolean isSSL;
            if (connector instanceof TransportConnector) {
                final TransportConnector transportConnector = (TransportConnector)connector;
                isSSL = (transportConnector.getServer() instanceof SslTransportServer);
            }
            else {
                isSSL = false;
            }
            if (isSSL) {
                this.sslBroker.addConnection(context, info);
            }
            else {
                this.nonSslBroker.addConnection(context, info);
            }
            super.addConnection(context, info);
        }
    }
    
    @Override
    public void removeConnection(final ConnectionContext context, final ConnectionInfo info, final Throwable error) throws Exception {
        final Connector connector = context.getConnector();
        boolean isSSL;
        if (connector instanceof ManagedTransportConnector) {
            final ManagedTransportConnector managedTransportConnector = (ManagedTransportConnector)connector;
            isSSL = (managedTransportConnector.getServer() instanceof SslTransportServer);
        }
        else {
            isSSL = false;
        }
        super.removeConnection(context, info, error);
        if (isSSL) {
            this.sslBroker.removeConnection(context, info, error);
        }
        else {
            this.nonSslBroker.removeConnection(context, info, error);
        }
    }
}
