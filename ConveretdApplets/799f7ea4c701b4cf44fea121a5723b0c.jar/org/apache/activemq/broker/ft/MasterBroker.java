// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.ft;

import org.slf4j.LoggerFactory;
import org.apache.activemq.command.ExceptionResponse;
import org.apache.activemq.command.Response;
import org.apache.activemq.command.MessageAck;
import org.apache.activemq.broker.ConsumerBrokerExchange;
import org.apache.activemq.broker.ProducerBrokerExchange;
import org.apache.activemq.command.Message;
import org.apache.activemq.command.MessageDispatchNotification;
import org.apache.activemq.command.MessageDispatch;
import org.apache.activemq.command.TransactionInfo;
import org.apache.activemq.command.TransactionId;
import org.apache.activemq.command.DestinationInfo;
import org.apache.activemq.command.RemoveSubscriptionInfo;
import org.apache.activemq.broker.region.Subscription;
import org.apache.activemq.command.ConsumerInfo;
import org.apache.activemq.command.ProducerInfo;
import org.apache.activemq.command.SessionInfo;
import org.apache.activemq.command.DataStructure;
import org.apache.activemq.command.RemoveInfo;
import org.apache.activemq.command.ConnectionInfo;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.broker.Connection;
import org.apache.activemq.command.Command;
import org.apache.activemq.command.ConnectionControl;
import org.apache.activemq.transport.ResponseCorrelator;
import org.apache.activemq.transport.MutexTransport;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.activemq.broker.MutableBrokerFilter;
import org.apache.activemq.command.ConsumerId;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.activemq.transport.Transport;
import org.slf4j.Logger;
import org.apache.activemq.broker.InsertableMutableBrokerFilter;

public class MasterBroker extends InsertableMutableBrokerFilter
{
    private static final Logger LOG;
    private Transport slave;
    private AtomicBoolean started;
    private Map<ConsumerId, ConsumerId> consumers;
    
    public MasterBroker(final MutableBrokerFilter parent, final Transport transport) {
        super(parent);
        this.started = new AtomicBoolean(false);
        this.consumers = new ConcurrentHashMap<ConsumerId, ConsumerId>();
        this.slave = transport;
        this.slave = new MutexTransport(this.slave);
        (this.slave = new ResponseCorrelator(this.slave)).setTransportListener(transport.getTransportListener());
    }
    
    public void startProcessing() {
        this.started.set(true);
        try {
            final Connection[] connections = this.getClients();
            final ConnectionControl command = new ConnectionControl();
            command.setFaultTolerant(true);
            if (connections != null) {
                for (int i = 0; i < connections.length; ++i) {
                    if (connections[i].isActive() && connections[i].isManageable()) {
                        connections[i].dispatchAsync(command);
                    }
                }
            }
        }
        catch (Exception e) {
            MasterBroker.LOG.error("Failed to get Connections", e);
        }
    }
    
    @Override
    public void stop() throws Exception {
        this.stopProcessing();
    }
    
    public void stopProcessing() {
        if (this.started.compareAndSet(true, false)) {
            this.remove();
        }
    }
    
    @Override
    public void addConnection(final ConnectionContext context, final ConnectionInfo info) throws Exception {
        super.addConnection(context, info);
        this.sendAsyncToSlave(info);
    }
    
    @Override
    public void removeConnection(final ConnectionContext context, final ConnectionInfo info, final Throwable error) throws Exception {
        super.removeConnection(context, info, error);
        this.sendAsyncToSlave(new RemoveInfo(info.getConnectionId()));
    }
    
    @Override
    public void addSession(final ConnectionContext context, final SessionInfo info) throws Exception {
        super.addSession(context, info);
        this.sendAsyncToSlave(info);
    }
    
    @Override
    public void removeSession(final ConnectionContext context, final SessionInfo info) throws Exception {
        super.removeSession(context, info);
        this.sendAsyncToSlave(new RemoveInfo(info.getSessionId()));
    }
    
    @Override
    public void addProducer(final ConnectionContext context, final ProducerInfo info) throws Exception {
        super.addProducer(context, info);
        this.sendAsyncToSlave(info);
    }
    
    @Override
    public void removeProducer(final ConnectionContext context, final ProducerInfo info) throws Exception {
        super.removeProducer(context, info);
        this.sendAsyncToSlave(new RemoveInfo(info.getProducerId()));
    }
    
    @Override
    public Subscription addConsumer(final ConnectionContext context, final ConsumerInfo info) throws Exception {
        this.sendSyncToSlave(info);
        this.consumers.put(info.getConsumerId(), info.getConsumerId());
        return super.addConsumer(context, info);
    }
    
    @Override
    public void removeConsumer(final ConnectionContext context, final ConsumerInfo info) throws Exception {
        super.removeConsumer(context, info);
        this.consumers.remove(info.getConsumerId());
        this.sendSyncToSlave(new RemoveInfo(info.getConsumerId()));
    }
    
    @Override
    public void removeSubscription(final ConnectionContext context, final RemoveSubscriptionInfo info) throws Exception {
        super.removeSubscription(context, info);
        this.sendAsyncToSlave(info);
    }
    
    @Override
    public void addDestinationInfo(final ConnectionContext context, final DestinationInfo info) throws Exception {
        super.addDestinationInfo(context, info);
        if (info.getDestination().isTemporary()) {
            this.sendAsyncToSlave(info);
        }
    }
    
    @Override
    public void removeDestinationInfo(final ConnectionContext context, final DestinationInfo info) throws Exception {
        super.removeDestinationInfo(context, info);
        if (info.getDestination().isTemporary()) {
            this.sendAsyncToSlave(info);
        }
    }
    
    @Override
    public void beginTransaction(final ConnectionContext context, final TransactionId xid) throws Exception {
        final TransactionInfo info = new TransactionInfo(context.getConnectionId(), xid, (byte)0);
        this.sendAsyncToSlave(info);
        super.beginTransaction(context, xid);
    }
    
    @Override
    public int prepareTransaction(final ConnectionContext context, final TransactionId xid) throws Exception {
        final TransactionInfo info = new TransactionInfo(context.getConnectionId(), xid, (byte)1);
        this.sendSyncToSlave(info);
        final int result = super.prepareTransaction(context, xid);
        return result;
    }
    
    @Override
    public void rollbackTransaction(final ConnectionContext context, final TransactionId xid) throws Exception {
        final TransactionInfo info = new TransactionInfo(context.getConnectionId(), xid, (byte)4);
        this.sendAsyncToSlave(info);
        super.rollbackTransaction(context, xid);
    }
    
    @Override
    public void commitTransaction(final ConnectionContext context, final TransactionId xid, final boolean onePhase) throws Exception {
        final TransactionInfo info = new TransactionInfo(context.getConnectionId(), xid, (byte)2);
        this.sendSyncToSlave(info);
        super.commitTransaction(context, xid, onePhase);
    }
    
    @Override
    public void forgetTransaction(final ConnectionContext context, final TransactionId xid) throws Exception {
        final TransactionInfo info = new TransactionInfo(context.getConnectionId(), xid, (byte)6);
        this.sendAsyncToSlave(info);
        super.forgetTransaction(context, xid);
    }
    
    @Override
    public void preProcessDispatch(final MessageDispatch messageDispatch) {
        super.preProcessDispatch(messageDispatch);
        final MessageDispatchNotification mdn = new MessageDispatchNotification();
        mdn.setConsumerId(messageDispatch.getConsumerId());
        mdn.setDeliverySequenceId(messageDispatch.getDeliverySequenceId());
        mdn.setDestination(messageDispatch.getDestination());
        if (messageDispatch.getMessage() != null) {
            final Message msg = messageDispatch.getMessage();
            mdn.setMessageId(msg.getMessageId());
            if (this.consumers.containsKey(messageDispatch.getConsumerId())) {
                this.sendSyncToSlave(mdn);
            }
        }
    }
    
    @Override
    public void send(final ProducerBrokerExchange producerExchange, final Message message) throws Exception {
        this.sendSyncToSlave(message.copy());
        super.send(producerExchange, message);
    }
    
    @Override
    public void acknowledge(final ConsumerBrokerExchange consumerExchange, final MessageAck ack) throws Exception {
        this.sendToSlave(ack);
        super.acknowledge(consumerExchange, ack);
    }
    
    @Override
    public boolean isFaultTolerantConfiguration() {
        return true;
    }
    
    protected void sendToSlave(final Message message) {
        if (message.isResponseRequired()) {
            this.sendSyncToSlave(message);
        }
        else {
            this.sendAsyncToSlave(message);
        }
    }
    
    protected void sendToSlave(final MessageAck ack) {
        if (ack.isResponseRequired()) {
            this.sendAsyncToSlave(ack);
        }
        else {
            this.sendSyncToSlave(ack);
        }
    }
    
    protected void sendAsyncToSlave(final Command command) {
        try {
            this.slave.oneway(command);
        }
        catch (Throwable e) {
            MasterBroker.LOG.error("Slave Failed", e);
            this.stopProcessing();
        }
    }
    
    protected void sendSyncToSlave(final Command command) {
        try {
            final Response response = (Response)this.slave.request(command);
            if (response.isException()) {
                final ExceptionResponse er = (ExceptionResponse)response;
                MasterBroker.LOG.error("Slave Failed", er.getException());
            }
        }
        catch (Throwable e) {
            MasterBroker.LOG.error("Slave Failed", e);
        }
    }
    
    static {
        LOG = LoggerFactory.getLogger(MasterBroker.class);
    }
}
