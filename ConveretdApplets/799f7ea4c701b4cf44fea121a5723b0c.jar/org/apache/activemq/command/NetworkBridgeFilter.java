// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.command;

import org.slf4j.LoggerFactory;
import java.util.Arrays;
import javax.jms.JMSException;
import java.io.IOException;
import org.apache.activemq.util.JMSExceptionSupport;
import org.apache.activemq.filter.MessageEvaluationContext;
import org.slf4j.Logger;
import org.apache.activemq.filter.BooleanExpression;

public class NetworkBridgeFilter implements DataStructure, BooleanExpression
{
    public static final byte DATA_STRUCTURE_TYPE = 91;
    static final Logger LOG;
    private BrokerId networkBrokerId;
    private int networkTTL;
    
    public NetworkBridgeFilter() {
    }
    
    public NetworkBridgeFilter(final BrokerId remoteBrokerPath, final int networkTTL) {
        this.networkBrokerId = remoteBrokerPath;
        this.networkTTL = networkTTL;
    }
    
    @Override
    public byte getDataStructureType() {
        return 91;
    }
    
    @Override
    public boolean isMarshallAware() {
        return false;
    }
    
    @Override
    public boolean matches(final MessageEvaluationContext mec) throws JMSException {
        try {
            final Message message = mec.getMessage();
            return message != null && this.matchesForwardingFilter(message);
        }
        catch (IOException e) {
            throw JMSExceptionSupport.create(e);
        }
    }
    
    @Override
    public Object evaluate(final MessageEvaluationContext message) throws JMSException {
        return this.matches(message) ? Boolean.TRUE : Boolean.FALSE;
    }
    
    protected boolean matchesForwardingFilter(final Message message) {
        if (contains(message.getBrokerPath(), this.networkBrokerId)) {
            if (NetworkBridgeFilter.LOG.isTraceEnabled()) {
                NetworkBridgeFilter.LOG.trace("Message all ready routed once through this broker (" + this.networkBrokerId + "), path: " + Arrays.toString(message.getBrokerPath()) + " - ignoring: " + message);
            }
            return false;
        }
        int hops = (message.getBrokerPath() == null) ? 0 : message.getBrokerPath().length;
        if (hops >= this.networkTTL) {
            if (NetworkBridgeFilter.LOG.isTraceEnabled()) {
                NetworkBridgeFilter.LOG.trace("Message restricted to " + this.networkTTL + " network hops ignoring: " + message);
            }
            return false;
        }
        if (message.isAdvisory() && message.getDataStructure() != null && message.getDataStructure().getDataStructureType() == 5) {
            final ConsumerInfo info = (ConsumerInfo)message.getDataStructure();
            hops = ((info.getBrokerPath() == null) ? 0 : info.getBrokerPath().length);
            if (hops >= this.networkTTL) {
                if (NetworkBridgeFilter.LOG.isTraceEnabled()) {
                    NetworkBridgeFilter.LOG.trace("ConsumerInfo advisory restricted to " + this.networkTTL + " network hops ignoring: " + message);
                }
                return false;
            }
        }
        return true;
    }
    
    public static boolean contains(final BrokerId[] brokerPath, final BrokerId brokerId) {
        if (brokerPath != null && brokerId != null) {
            for (int i = 0; i < brokerPath.length; ++i) {
                if (brokerId.equals(brokerPath[i])) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public int getNetworkTTL() {
        return this.networkTTL;
    }
    
    public void setNetworkTTL(final int networkTTL) {
        this.networkTTL = networkTTL;
    }
    
    public BrokerId getNetworkBrokerId() {
        return this.networkBrokerId;
    }
    
    public void setNetworkBrokerId(final BrokerId remoteBrokerPath) {
        this.networkBrokerId = remoteBrokerPath;
    }
    
    static {
        LOG = LoggerFactory.getLogger(NetworkBridgeFilter.class);
    }
}
