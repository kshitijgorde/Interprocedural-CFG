// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.network;

import org.slf4j.LoggerFactory;
import org.apache.activemq.command.NetworkBridgeFilter;
import org.apache.activemq.command.ConsumerInfo;
import java.io.IOException;
import org.apache.activemq.command.Endpoint;
import org.apache.activemq.Service;
import org.apache.activemq.util.ServiceSupport;
import org.apache.activemq.command.BrokerInfo;
import org.apache.activemq.command.Command;
import org.apache.activemq.transport.Transport;
import org.apache.activemq.command.BrokerId;
import org.slf4j.Logger;

public class CompositeDemandForwardingBridge extends DemandForwardingBridgeSupport
{
    private static final Logger LOG;
    protected final BrokerId[] remoteBrokerPath;
    protected Object brokerInfoMutex;
    
    public CompositeDemandForwardingBridge(final NetworkBridgeConfiguration configuration, final Transport localBroker, final Transport remoteBroker) {
        super(configuration, localBroker, remoteBroker);
        this.remoteBrokerPath = new BrokerId[] { null };
        this.brokerInfoMutex = new Object();
        this.remoteBrokerName = remoteBroker.toString();
        this.remoteBrokerNameKnownLatch.countDown();
    }
    
    @Override
    protected void serviceRemoteBrokerInfo(final Command command) throws IOException {
        synchronized (this.brokerInfoMutex) {
            final BrokerInfo remoteBrokerInfo = (BrokerInfo)command;
            final BrokerId remoteBrokerId = remoteBrokerInfo.getBrokerId();
            final Endpoint from = command.getFrom();
            if (from == null) {
                CompositeDemandForwardingBridge.LOG.warn("Incoming command does not have a from endpoint: " + command);
            }
            else {
                from.setBrokerInfo(remoteBrokerInfo);
            }
            if (this.localBrokerId != null && this.localBrokerId.equals(remoteBrokerId)) {
                CompositeDemandForwardingBridge.LOG.info("Disconnecting loop back connection.");
                ServiceSupport.dispose(this);
            }
            if (!this.disposed.get()) {
                this.triggerLocalStartBridge();
            }
        }
    }
    
    @Override
    protected void addRemoteBrokerToBrokerPath(final ConsumerInfo info) throws IOException {
        info.setBrokerPath(this.appendToBrokerPath(info.getBrokerPath(), this.getFromBrokerId(info)));
    }
    
    protected BrokerId getFromBrokerId(final Command command) throws IOException {
        BrokerId answer = null;
        final Endpoint from = command.getFrom();
        if (from == null) {
            CompositeDemandForwardingBridge.LOG.warn("Incoming command does not have a from endpoint: " + command);
        }
        else {
            answer = from.getBrokerId();
        }
        if (answer != null) {
            return answer;
        }
        throw new IOException("No broker ID is available for endpoint: " + from + " from command: " + command);
    }
    
    @Override
    protected void serviceLocalBrokerInfo(final Command command) throws InterruptedException {
    }
    
    @Override
    protected NetworkBridgeFilter createNetworkBridgeFilter(final ConsumerInfo info) throws IOException {
        return new NetworkBridgeFilter(this.getFromBrokerId(info), this.configuration.getNetworkTTL());
    }
    
    @Override
    protected BrokerId[] getRemoteBrokerPath() {
        return this.remoteBrokerPath;
    }
    
    static {
        LOG = LoggerFactory.getLogger(CompositeDemandForwardingBridge.class);
    }
}
