// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.network;

import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.ArrayList;
import org.apache.activemq.command.ConsumerId;
import java.util.Iterator;
import org.apache.activemq.filter.DestinationFilter;
import java.util.Arrays;
import org.apache.activemq.command.BrokerId;
import java.io.IOException;
import org.apache.activemq.command.ConsumerInfo;
import org.apache.activemq.transport.Transport;
import org.slf4j.Logger;

public class ConduitBridge extends DemandForwardingBridge
{
    private static final Logger LOG;
    
    public ConduitBridge(final NetworkBridgeConfiguration configuration, final Transport localBroker, final Transport remoteBroker) {
        super(configuration, localBroker, remoteBroker);
    }
    
    @Override
    protected DemandSubscription createDemandSubscription(final ConsumerInfo info) throws IOException {
        if (this.addToAlreadyInterestedConsumers(info)) {
            return null;
        }
        info.addNetworkConsumerId(info.getConsumerId());
        info.setSelector(null);
        return this.doCreateDemandSubscription(info);
    }
    
    protected boolean checkPaths(final BrokerId[] first, final BrokerId[] second) {
        return first == null || second == null || Arrays.equals(first, second) || !first[0].equals(second[0]) || !first[first.length - 1].equals(second[second.length - 1]);
    }
    
    protected boolean addToAlreadyInterestedConsumers(final ConsumerInfo info) {
        boolean matched = false;
        for (final DemandSubscription ds : this.subscriptionMapByLocalId.values()) {
            final DestinationFilter filter = DestinationFilter.parseFilter(ds.getLocalInfo().getDestination());
            if (filter.matches(info.getDestination())) {
                if (ConduitBridge.LOG.isDebugEnabled()) {
                    ConduitBridge.LOG.debug(this.configuration.getBrokerName() + " matched (add interest) to exsting sub for: " + ds.getRemoteInfo() + " with sub: " + info.getConsumerId());
                }
                if (this.checkPaths(info.getBrokerPath(), ds.getRemoteInfo().getBrokerPath())) {
                    ds.add(info.getConsumerId());
                }
                matched = true;
            }
        }
        return matched;
    }
    
    @Override
    protected void removeDemandSubscription(final ConsumerId id) throws IOException {
        final List<DemandSubscription> tmpList = new ArrayList<DemandSubscription>();
        for (final DemandSubscription ds : this.subscriptionMapByLocalId.values()) {
            if (ds.remove(id) && ConduitBridge.LOG.isDebugEnabled()) {
                ConduitBridge.LOG.debug(this.configuration.getBrokerName() + " removing interest in sub on " + this.localBroker + " from " + this.remoteBrokerName + " : sub: " + id + " existing matched sub: " + ds.getRemoteInfo());
            }
            if (ds.isEmpty()) {
                tmpList.add(ds);
            }
        }
        for (final DemandSubscription ds : tmpList) {
            this.removeSubscription(ds);
            if (ConduitBridge.LOG.isDebugEnabled()) {
                ConduitBridge.LOG.debug(this.configuration.getBrokerName() + " removing sub on " + this.localBroker + " from " + this.remoteBrokerName + " :  " + ds.getRemoteInfo());
            }
        }
    }
    
    static {
        LOG = LoggerFactory.getLogger(ConduitBridge.class);
    }
}
