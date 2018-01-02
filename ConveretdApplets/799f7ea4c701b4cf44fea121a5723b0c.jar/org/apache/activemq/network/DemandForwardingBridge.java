// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.network;

import org.slf4j.LoggerFactory;
import org.apache.activemq.command.NetworkBridgeFilter;
import org.apache.activemq.command.ConsumerInfo;
import java.io.IOException;
import org.apache.activemq.Service;
import org.apache.activemq.util.ServiceSupport;
import org.apache.activemq.command.BrokerInfo;
import org.apache.activemq.command.Command;
import org.apache.activemq.transport.Transport;
import org.apache.activemq.command.BrokerId;
import org.slf4j.Logger;

public class DemandForwardingBridge extends DemandForwardingBridgeSupport
{
    private static final Logger LOG;
    protected final BrokerId[] remoteBrokerPath;
    protected Object brokerInfoMutex;
    protected BrokerId remoteBrokerId;
    
    public DemandForwardingBridge(final NetworkBridgeConfiguration configuration, final Transport localBroker, final Transport remoteBroker) {
        super(configuration, localBroker, remoteBroker);
        this.remoteBrokerPath = new BrokerId[] { null };
        this.brokerInfoMutex = new Object();
    }
    
    @Override
    protected void serviceRemoteBrokerInfo(final Command command) throws IOException {
        synchronized (this.brokerInfoMutex) {
            final BrokerInfo remoteBrokerInfo = (BrokerInfo)command;
            this.remoteBrokerId = remoteBrokerInfo.getBrokerId();
            this.remoteBrokerPath[0] = this.remoteBrokerId;
            this.remoteBrokerName = remoteBrokerInfo.getBrokerName();
            if (this.localBrokerId != null && this.localBrokerId.equals(this.remoteBrokerId)) {
                if (DemandForwardingBridge.LOG.isTraceEnabled()) {
                    DemandForwardingBridge.LOG.trace(this.configuration.getBrokerName() + " disconnecting remote loop back connection: " + this.remoteBrokerName);
                }
                ServiceSupport.dispose(this);
            }
            if (DemandForwardingBridge.LOG.isTraceEnabled()) {
                DemandForwardingBridge.LOG.trace("counting down remoteBrokerNameKnownLatch with: " + command);
            }
            this.remoteBrokerNameKnownLatch.countDown();
        }
    }
    
    @Override
    protected void addRemoteBrokerToBrokerPath(final ConsumerInfo info) {
        info.setBrokerPath(this.appendToBrokerPath(info.getBrokerPath(), this.getRemoteBrokerPath()));
    }
    
    @Override
    protected void serviceLocalBrokerInfo(final Command command) throws InterruptedException {
        synchronized (this.brokerInfoMutex) {
            this.localBrokerId = ((BrokerInfo)command).getBrokerId();
            this.localBrokerPath[0] = this.localBrokerId;
            this.localBrokerIdKnownLatch.countDown();
            if (this.remoteBrokerId != null && this.remoteBrokerId.equals(this.localBrokerId)) {
                if (DemandForwardingBridge.LOG.isTraceEnabled()) {
                    DemandForwardingBridge.LOG.trace(this.configuration.getBrokerName() + " disconnecting local loop back connection.");
                }
                this.waitStarted();
                ServiceSupport.dispose(this);
            }
        }
    }
    
    @Override
    protected NetworkBridgeFilter createNetworkBridgeFilter(final ConsumerInfo info) throws IOException {
        return new NetworkBridgeFilter(this.remoteBrokerPath[0], this.configuration.getNetworkTTL());
    }
    
    @Override
    protected BrokerId[] getRemoteBrokerPath() {
        return this.remoteBrokerPath;
    }
    
    static {
        LOG = LoggerFactory.getLogger(DemandForwardingBridge.class);
    }
}
