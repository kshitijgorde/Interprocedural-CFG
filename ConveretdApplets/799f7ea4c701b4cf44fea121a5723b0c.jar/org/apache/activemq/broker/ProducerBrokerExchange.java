// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker;

import org.slf4j.LoggerFactory;
import org.apache.activemq.command.Message;
import org.apache.activemq.state.ProducerState;
import org.apache.activemq.broker.region.Region;
import org.apache.activemq.broker.region.Destination;
import org.slf4j.Logger;

public class ProducerBrokerExchange
{
    private static final Logger LOG;
    private ConnectionContext connectionContext;
    private Destination regionDestination;
    private Region region;
    private ProducerState producerState;
    private boolean mutable;
    private long lastSendSequenceNumber;
    
    public ProducerBrokerExchange() {
        this.mutable = true;
        this.lastSendSequenceNumber = -1L;
    }
    
    public ProducerBrokerExchange copy() {
        final ProducerBrokerExchange rc = new ProducerBrokerExchange();
        rc.connectionContext = this.connectionContext.copy();
        rc.regionDestination = this.regionDestination;
        rc.region = this.region;
        rc.producerState = this.producerState;
        rc.mutable = this.mutable;
        return rc;
    }
    
    public ConnectionContext getConnectionContext() {
        return this.connectionContext;
    }
    
    public void setConnectionContext(final ConnectionContext connectionContext) {
        this.connectionContext = connectionContext;
    }
    
    public boolean isMutable() {
        return this.mutable;
    }
    
    public void setMutable(final boolean mutable) {
        this.mutable = mutable;
    }
    
    public Destination getRegionDestination() {
        return this.regionDestination;
    }
    
    public void setRegionDestination(final Destination regionDestination) {
        this.regionDestination = regionDestination;
    }
    
    public Region getRegion() {
        return this.region;
    }
    
    public void setRegion(final Region region) {
        this.region = region;
    }
    
    public ProducerState getProducerState() {
        return this.producerState;
    }
    
    public void setProducerState(final ProducerState producerState) {
        this.producerState = producerState;
    }
    
    public boolean canDispatch(final Message messageSend) {
        boolean canDispatch = true;
        if (this.lastSendSequenceNumber > 0L && messageSend.getMessageId().getProducerSequenceId() <= this.lastSendSequenceNumber) {
            canDispatch = false;
            ProducerBrokerExchange.LOG.debug("suppressing duplicate message send [" + messageSend.getMessageId() + "] with producerSequenceId [" + messageSend.getMessageId().getProducerSequenceId() + "] less than last stored: " + this.lastSendSequenceNumber);
        }
        return canDispatch;
    }
    
    public void setLastStoredSequenceId(final long l) {
        this.lastSendSequenceNumber = l;
        ProducerBrokerExchange.LOG.debug("last stored sequence id set: " + l);
    }
    
    static {
        LOG = LoggerFactory.getLogger(ProducerBrokerExchange.class);
    }
}
