// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq;

import org.apache.activemq.command.TransactionId;
import org.slf4j.LoggerFactory;
import org.apache.activemq.command.ActiveMQTempDestination;
import javax.jms.TransactionRolledBackException;
import org.apache.activemq.command.MessagePull;
import javax.jms.IllegalStateException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.Iterator;
import java.util.List;
import org.apache.activemq.command.RemoveInfo;
import org.apache.activemq.transaction.Synchronization;
import org.apache.activemq.util.Callback;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import org.apache.activemq.blob.BlobDownloader;
import org.apache.activemq.command.ActiveMQBlobMessage;
import org.apache.activemq.command.ActiveMQMessage;
import javax.jms.Message;
import org.apache.activemq.util.JMSExceptionSupport;
import org.apache.activemq.management.StatsImpl;
import org.apache.activemq.command.Command;
import javax.jms.Destination;
import org.apache.activemq.selector.SelectorParser;
import org.apache.activemq.util.IntrospectionSupport;
import java.util.Map;
import java.util.HashMap;
import javax.jms.JMSException;
import javax.jms.InvalidDestinationException;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ConsumerId;
import java.io.IOException;
import org.apache.activemq.command.MessageAck;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.activemq.management.JMSConsumerStatsImpl;
import javax.jms.MessageListener;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.activemq.command.MessageId;
import org.apache.activemq.command.MessageDispatch;
import java.util.LinkedList;
import org.apache.activemq.command.ConsumerInfo;
import org.apache.activemq.thread.Scheduler;
import org.slf4j.Logger;
import org.apache.activemq.management.StatsCapable;

public class ActiveMQMessageConsumer implements MessageAvailableConsumer, StatsCapable, ActiveMQDispatcher
{
    private static final Logger LOG;
    protected final Scheduler scheduler;
    protected final ActiveMQSession session;
    protected final ConsumerInfo info;
    protected final MessageDispatchChannel unconsumedMessages;
    private final LinkedList<MessageDispatch> deliveredMessages;
    private PreviouslyDeliveredMap<MessageId, Boolean> previouslyDeliveredMessages;
    private int deliveredCounter;
    private int additionalWindowSize;
    private long redeliveryDelay;
    private int ackCounter;
    private int dispatchedCount;
    private final AtomicReference<MessageListener> messageListener;
    private final JMSConsumerStatsImpl stats;
    private final String selector;
    private boolean synchronizationRegistered;
    private final AtomicBoolean started;
    private MessageAvailableListener availableListener;
    private RedeliveryPolicy redeliveryPolicy;
    private boolean optimizeAcknowledge;
    private final AtomicBoolean deliveryingAcknowledgements;
    private ExecutorService executorService;
    private MessageTransformer transformer;
    private boolean clearDispatchList;
    boolean inProgressClearRequiredFlag;
    private MessageAck pendingAck;
    private long lastDeliveredSequenceId;
    private IOException failureError;
    private long optimizeAckTimestamp;
    private final long optimizeAckTimeout = 300L;
    private long failoverRedeliveryWaitPeriod;
    
    public ActiveMQMessageConsumer(final ActiveMQSession session, final ConsumerId consumerId, final ActiveMQDestination dest, final String name, final String selector, final int prefetch, final int maximumPendingMessageCount, final boolean noLocal, final boolean browser, final boolean dispatchAsync, final MessageListener messageListener) throws JMSException {
        this.deliveredMessages = new LinkedList<MessageDispatch>();
        this.messageListener = new AtomicReference<MessageListener>();
        this.started = new AtomicBoolean(false);
        this.deliveryingAcknowledgements = new AtomicBoolean();
        this.optimizeAckTimestamp = System.currentTimeMillis();
        this.failoverRedeliveryWaitPeriod = 0L;
        if (dest == null) {
            throw new InvalidDestinationException("Don't understand null destinations");
        }
        if (dest.getPhysicalName() == null) {
            throw new InvalidDestinationException("The destination object was not given a physical name.");
        }
        if (dest.isTemporary()) {
            final String physicalName = dest.getPhysicalName();
            if (physicalName == null) {
                throw new IllegalArgumentException("Physical name of Destination should be valid: " + dest);
            }
            final String connectionID = session.connection.getConnectionInfo().getConnectionId().getValue();
            if (physicalName.indexOf(connectionID) < 0) {
                throw new InvalidDestinationException("Cannot use a Temporary destination from another Connection");
            }
            if (session.connection.isDeleted(dest)) {
                throw new InvalidDestinationException("Cannot use a Temporary destination that has been deleted");
            }
            if (prefetch < 0) {
                throw new JMSException("Cannot have a prefetch size less than zero");
            }
        }
        if (session.connection.isMessagePrioritySupported()) {
            this.unconsumedMessages = new SimplePriorityMessageDispatchChannel();
        }
        else {
            this.unconsumedMessages = new FifoMessageDispatchChannel();
        }
        this.session = session;
        this.scheduler = session.getScheduler();
        this.redeliveryPolicy = session.connection.getRedeliveryPolicy();
        this.setTransformer(session.getTransformer());
        (this.info = new ConsumerInfo(consumerId)).setExclusive(this.session.connection.isExclusiveConsumer());
        this.info.setSubscriptionName(name);
        this.info.setPrefetchSize(prefetch);
        this.info.setCurrentPrefetchSize(prefetch);
        this.info.setMaximumPendingMessageLimit(maximumPendingMessageCount);
        this.info.setNoLocal(noLocal);
        this.info.setDispatchAsync(dispatchAsync);
        this.info.setRetroactive(this.session.connection.isUseRetroactiveConsumer());
        this.info.setSelector(null);
        if (dest.getOptions() != null) {
            final Map<String, String> options = new HashMap<String, String>(dest.getOptions());
            IntrospectionSupport.setProperties(this.info, options, "consumer.");
        }
        this.info.setDestination(dest);
        this.info.setBrowser(browser);
        if (selector != null && selector.trim().length() != 0) {
            SelectorParser.parse(selector);
            this.info.setSelector(selector);
            this.selector = selector;
        }
        else if (this.info.getSelector() != null) {
            SelectorParser.parse(this.info.getSelector());
            this.selector = this.info.getSelector();
        }
        else {
            this.selector = null;
        }
        this.stats = new JMSConsumerStatsImpl(session.getSessionStats(), dest);
        this.optimizeAcknowledge = (session.connection.isOptimizeAcknowledge() && session.isAutoAcknowledge() && !this.info.isBrowser());
        this.info.setOptimizedAcknowledge(this.optimizeAcknowledge);
        this.failoverRedeliveryWaitPeriod = session.connection.getConsumerFailoverRedeliveryWaitPeriod();
        if (messageListener != null) {
            this.setMessageListener(messageListener);
        }
        try {
            this.session.addConsumer(this);
            this.session.syncSendPacket(this.info);
        }
        catch (JMSException e) {
            this.session.removeConsumer(this);
            throw e;
        }
        if (session.connection.isStarted()) {
            this.start();
        }
    }
    
    private boolean isAutoAcknowledgeEach() {
        return this.session.isAutoAcknowledge() || (this.session.isDupsOkAcknowledge() && this.getDestination().isQueue());
    }
    
    private boolean isAutoAcknowledgeBatch() {
        return this.session.isDupsOkAcknowledge() && !this.getDestination().isQueue();
    }
    
    @Override
    public StatsImpl getStats() {
        return this.stats;
    }
    
    public JMSConsumerStatsImpl getConsumerStats() {
        return this.stats;
    }
    
    public RedeliveryPolicy getRedeliveryPolicy() {
        return this.redeliveryPolicy;
    }
    
    public void setRedeliveryPolicy(final RedeliveryPolicy redeliveryPolicy) {
        this.redeliveryPolicy = redeliveryPolicy;
    }
    
    public MessageTransformer getTransformer() {
        return this.transformer;
    }
    
    public void setTransformer(final MessageTransformer transformer) {
        this.transformer = transformer;
    }
    
    public ConsumerId getConsumerId() {
        return this.info.getConsumerId();
    }
    
    public String getConsumerName() {
        return this.info.getSubscriptionName();
    }
    
    protected boolean isNoLocal() {
        return this.info.isNoLocal();
    }
    
    protected boolean isBrowser() {
        return this.info.isBrowser();
    }
    
    protected ActiveMQDestination getDestination() {
        return this.info.getDestination();
    }
    
    public int getPrefetchNumber() {
        return this.info.getPrefetchSize();
    }
    
    public boolean isDurableSubscriber() {
        return this.info.getSubscriptionName() != null && this.info.getDestination().isTopic();
    }
    
    @Override
    public String getMessageSelector() throws JMSException {
        this.checkClosed();
        return this.selector;
    }
    
    @Override
    public MessageListener getMessageListener() throws JMSException {
        this.checkClosed();
        return this.messageListener.get();
    }
    
    @Override
    public void setMessageListener(final MessageListener listener) throws JMSException {
        this.checkClosed();
        if (this.info.getPrefetchSize() == 0) {
            throw new JMSException("Illegal prefetch size of zero. This setting is not supported for asynchronous consumers please set a value of at least 1");
        }
        if (listener != null) {
            final boolean wasRunning = this.session.isRunning();
            if (wasRunning) {
                this.session.stop();
            }
            this.messageListener.set(listener);
            this.session.redispatch(this, this.unconsumedMessages);
            if (wasRunning) {
                this.session.start();
            }
        }
        else {
            this.messageListener.set(null);
        }
    }
    
    @Override
    public MessageAvailableListener getAvailableListener() {
        return this.availableListener;
    }
    
    @Override
    public void setAvailableListener(final MessageAvailableListener availableListener) {
        this.availableListener = availableListener;
    }
    
    private MessageDispatch dequeue(long timeout) throws JMSException {
        try {
            long deadline = 0L;
            if (timeout > 0L) {
                deadline = System.currentTimeMillis() + timeout;
            }
            while (true) {
                final MessageDispatch md = this.unconsumedMessages.dequeue(timeout);
                if (md == null) {
                    if (timeout > 0L && !this.unconsumedMessages.isClosed()) {
                        timeout = Math.max(deadline - System.currentTimeMillis(), 0L);
                    }
                    else {
                        if (this.failureError != null) {
                            throw JMSExceptionSupport.create(this.failureError);
                        }
                        return null;
                    }
                }
                else {
                    if (md.getMessage() == null) {
                        return null;
                    }
                    if (!md.getMessage().isExpired()) {
                        if (ActiveMQMessageConsumer.LOG.isTraceEnabled()) {
                            ActiveMQMessageConsumer.LOG.trace(this.getConsumerId() + " received message: " + md);
                        }
                        return md;
                    }
                    if (ActiveMQMessageConsumer.LOG.isDebugEnabled()) {
                        ActiveMQMessageConsumer.LOG.debug(this.getConsumerId() + " received expired message: " + md);
                    }
                    this.beforeMessageIsConsumed(md);
                    this.afterMessageIsConsumed(md, true);
                    if (timeout <= 0L) {
                        continue;
                    }
                    timeout = Math.max(deadline - System.currentTimeMillis(), 0L);
                }
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw JMSExceptionSupport.create(e);
        }
    }
    
    @Override
    public Message receive() throws JMSException {
        this.checkClosed();
        this.checkMessageListener();
        this.sendPullCommand(0L);
        final MessageDispatch md = this.dequeue(-1L);
        if (md == null) {
            return null;
        }
        this.beforeMessageIsConsumed(md);
        this.afterMessageIsConsumed(md, false);
        return this.createActiveMQMessage(md);
    }
    
    private ActiveMQMessage createActiveMQMessage(final MessageDispatch md) throws JMSException {
        ActiveMQMessage m = (ActiveMQMessage)md.getMessage().copy();
        if (m.getDataStructureType() == 29) {
            ((ActiveMQBlobMessage)m).setBlobDownloader(new BlobDownloader(this.session.getBlobTransferPolicy()));
        }
        if (this.transformer != null) {
            final Message transformedMessage = this.transformer.consumerTransform(this.session, this, m);
            if (transformedMessage != null) {
                m = ActiveMQMessageTransformation.transformMessage(transformedMessage, this.session.connection);
            }
        }
        if (this.session.isClientAcknowledge()) {
            m.setAcknowledgeCallback(new Callback() {
                @Override
                public void execute() throws Exception {
                    ActiveMQMessageConsumer.this.session.checkClosed();
                    ActiveMQMessageConsumer.this.session.acknowledge();
                }
            });
        }
        else if (this.session.isIndividualAcknowledge()) {
            m.setAcknowledgeCallback(new Callback() {
                @Override
                public void execute() throws Exception {
                    ActiveMQMessageConsumer.this.session.checkClosed();
                    ActiveMQMessageConsumer.this.acknowledge(md);
                }
            });
        }
        return m;
    }
    
    @Override
    public Message receive(final long timeout) throws JMSException {
        this.checkClosed();
        this.checkMessageListener();
        if (timeout == 0L) {
            return this.receive();
        }
        this.sendPullCommand(timeout);
        if (timeout <= 0L) {
            return null;
        }
        MessageDispatch md;
        if (this.info.getPrefetchSize() == 0) {
            md = this.dequeue(-1L);
        }
        else {
            md = this.dequeue(timeout);
        }
        if (md == null) {
            return null;
        }
        this.beforeMessageIsConsumed(md);
        this.afterMessageIsConsumed(md, false);
        return this.createActiveMQMessage(md);
    }
    
    @Override
    public Message receiveNoWait() throws JMSException {
        this.checkClosed();
        this.checkMessageListener();
        this.sendPullCommand(-1L);
        MessageDispatch md;
        if (this.info.getPrefetchSize() == 0) {
            md = this.dequeue(-1L);
        }
        else {
            md = this.dequeue(0L);
        }
        if (md == null) {
            return null;
        }
        this.beforeMessageIsConsumed(md);
        this.afterMessageIsConsumed(md, false);
        return this.createActiveMQMessage(md);
    }
    
    @Override
    public void close() throws JMSException {
        if (!this.unconsumedMessages.isClosed()) {
            if (this.session.getTransactionContext().isInTransaction()) {
                this.session.getTransactionContext().addSynchronization(new Synchronization() {
                    @Override
                    public void afterCommit() throws Exception {
                        ActiveMQMessageConsumer.this.doClose();
                    }
                    
                    @Override
                    public void afterRollback() throws Exception {
                        ActiveMQMessageConsumer.this.doClose();
                    }
                });
            }
            else {
                this.doClose();
            }
        }
    }
    
    void doClose() throws JMSException {
        this.dispose();
        final RemoveInfo removeCommand = this.info.createRemoveCommand();
        if (ActiveMQMessageConsumer.LOG.isDebugEnabled()) {
            ActiveMQMessageConsumer.LOG.debug("remove: " + this.getConsumerId() + ", lastDeliveredSequenceId:" + this.lastDeliveredSequenceId);
        }
        removeCommand.setLastDeliveredSequenceId(this.lastDeliveredSequenceId);
        this.session.asyncSendPacket(removeCommand);
    }
    
    void inProgressClearRequired() {
        this.inProgressClearRequiredFlag = true;
        this.clearDispatchList = true;
    }
    
    void clearMessagesInProgress() {
        if (this.inProgressClearRequiredFlag) {
            synchronized (this.unconsumedMessages.getMutex()) {
                if (this.inProgressClearRequiredFlag) {
                    if (ActiveMQMessageConsumer.LOG.isDebugEnabled()) {
                        ActiveMQMessageConsumer.LOG.debug(this.getConsumerId() + " clearing dispatched list (" + this.unconsumedMessages.size() + ") on transport interrupt");
                    }
                    final List<MessageDispatch> list = this.unconsumedMessages.removeAll();
                    if (!this.info.isBrowser()) {
                        for (final MessageDispatch old : list) {
                            this.session.connection.rollbackDuplicate(this, old.getMessage());
                        }
                    }
                    this.session.connection.transportInterruptionProcessingComplete();
                    this.inProgressClearRequiredFlag = false;
                }
            }
        }
    }
    
    void deliverAcks() {
        MessageAck ack = null;
        if (this.deliveryingAcknowledgements.compareAndSet(false, true)) {
            if (this.isAutoAcknowledgeEach()) {
                synchronized (this.deliveredMessages) {
                    ack = this.makeAckForAllDeliveredMessages((byte)2);
                    if (ack != null) {
                        this.deliveredMessages.clear();
                        this.ackCounter = 0;
                    }
                    else {
                        ack = this.pendingAck;
                        this.pendingAck = null;
                    }
                }
            }
            else if (this.pendingAck != null && this.pendingAck.isStandardAck()) {
                ack = this.pendingAck;
                this.pendingAck = null;
            }
            if (ack != null) {
                final MessageAck ackToSend = ack;
                if (this.executorService == null) {
                    this.executorService = Executors.newSingleThreadExecutor();
                }
                this.executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ActiveMQMessageConsumer.this.session.sendAck(ackToSend, true);
                        }
                        catch (JMSException e) {
                            ActiveMQMessageConsumer.LOG.error(ActiveMQMessageConsumer.this.getConsumerId() + " failed to delivered acknowledgements", e);
                        }
                        finally {
                            ActiveMQMessageConsumer.this.deliveryingAcknowledgements.set(false);
                        }
                    }
                });
            }
            else {
                this.deliveryingAcknowledgements.set(false);
            }
        }
    }
    
    public void dispose() throws JMSException {
        if (!this.unconsumedMessages.isClosed()) {
            if (!this.session.getTransacted()) {
                this.deliverAcks();
                if (this.isAutoAcknowledgeBatch()) {
                    this.acknowledge();
                }
            }
            if (this.executorService != null) {
                this.executorService.shutdown();
                try {
                    this.executorService.awaitTermination(60L, TimeUnit.SECONDS);
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (this.session.isClientAcknowledge() && !this.info.isBrowser()) {
                List<MessageDispatch> tmp = null;
                synchronized (this.deliveredMessages) {
                    tmp = new ArrayList<MessageDispatch>(this.deliveredMessages);
                }
                for (final MessageDispatch old : tmp) {
                    this.session.connection.rollbackDuplicate(this, old.getMessage());
                }
                tmp.clear();
            }
            if (!this.session.isTransacted()) {
                synchronized (this.deliveredMessages) {
                    this.deliveredMessages.clear();
                }
            }
            this.unconsumedMessages.close();
            this.session.removeConsumer(this);
            final List<MessageDispatch> list = this.unconsumedMessages.removeAll();
            if (!this.info.isBrowser()) {
                for (final MessageDispatch old : list) {
                    this.session.connection.rollbackDuplicate(this, old.getMessage());
                }
            }
        }
    }
    
    protected void checkClosed() throws IllegalStateException {
        if (this.unconsumedMessages.isClosed()) {
            throw new IllegalStateException("The Consumer is closed");
        }
    }
    
    protected void sendPullCommand(final long timeout) throws JMSException {
        this.clearDispatchList();
        if (this.info.getPrefetchSize() == 0 && this.unconsumedMessages.isEmpty()) {
            final MessagePull messagePull = new MessagePull();
            messagePull.configure(this.info);
            messagePull.setTimeout(timeout);
            this.session.asyncSendPacket(messagePull);
        }
    }
    
    protected void checkMessageListener() throws JMSException {
        this.session.checkMessageListener();
    }
    
    protected void setOptimizeAcknowledge(final boolean value) {
        if (this.optimizeAcknowledge && !value) {
            this.deliverAcks();
        }
        this.optimizeAcknowledge = value;
    }
    
    protected void setPrefetchSize(final int prefetch) {
        this.deliverAcks();
        this.info.setCurrentPrefetchSize(prefetch);
    }
    
    private void beforeMessageIsConsumed(final MessageDispatch md) throws JMSException {
        md.setDeliverySequenceId(this.session.getNextDeliveryId());
        this.lastDeliveredSequenceId = md.getMessage().getMessageId().getBrokerSequenceId();
        if (!this.isAutoAcknowledgeBatch()) {
            synchronized (this.deliveredMessages) {
                this.deliveredMessages.addFirst(md);
            }
            if (this.session.getTransacted()) {
                this.ackLater(md, (byte)0);
            }
        }
    }
    
    private void afterMessageIsConsumed(final MessageDispatch md, final boolean messageExpired) throws JMSException {
        if (this.unconsumedMessages.isClosed()) {
            return;
        }
        if (messageExpired) {
            synchronized (this.deliveredMessages) {
                this.deliveredMessages.remove(md);
            }
            this.stats.getExpiredMessageCount().increment();
            this.ackLater(md, (byte)0);
        }
        else {
            this.stats.onMessage();
            if (!this.session.getTransacted()) {
                if (this.isAutoAcknowledgeEach()) {
                    if (this.deliveryingAcknowledgements.compareAndSet(false, true)) {
                        synchronized (this.deliveredMessages) {
                            if (!this.deliveredMessages.isEmpty()) {
                                if (this.optimizeAcknowledge) {
                                    ++this.ackCounter;
                                    if (this.ackCounter >= this.info.getPrefetchSize() * 0.65 || System.currentTimeMillis() >= this.optimizeAckTimestamp + 300L) {
                                        final MessageAck ack = this.makeAckForAllDeliveredMessages((byte)2);
                                        if (ack != null) {
                                            this.deliveredMessages.clear();
                                            this.ackCounter = 0;
                                            this.session.sendAck(ack);
                                            this.optimizeAckTimestamp = System.currentTimeMillis();
                                        }
                                    }
                                }
                                else {
                                    final MessageAck ack = this.makeAckForAllDeliveredMessages((byte)2);
                                    if (ack != null) {
                                        this.deliveredMessages.clear();
                                        this.session.sendAck(ack);
                                    }
                                }
                            }
                        }
                        this.deliveryingAcknowledgements.set(false);
                    }
                }
                else if (this.isAutoAcknowledgeBatch()) {
                    this.ackLater(md, (byte)2);
                }
                else {
                    if (!this.session.isClientAcknowledge() && !this.session.isIndividualAcknowledge()) {
                        throw new IllegalStateException("Invalid session state.");
                    }
                    boolean messageUnackedByConsumer = false;
                    synchronized (this.deliveredMessages) {
                        messageUnackedByConsumer = this.deliveredMessages.contains(md);
                    }
                    if (messageUnackedByConsumer) {
                        this.ackLater(md, (byte)0);
                    }
                }
            }
        }
    }
    
    private MessageAck makeAckForAllDeliveredMessages(final byte type) {
        synchronized (this.deliveredMessages) {
            if (this.deliveredMessages.isEmpty()) {
                return null;
            }
            final MessageDispatch md = this.deliveredMessages.getFirst();
            final MessageAck ack = new MessageAck(md, type, this.deliveredMessages.size());
            ack.setFirstMessageId(this.deliveredMessages.getLast().getMessage().getMessageId());
            return ack;
        }
    }
    
    private void ackLater(final MessageDispatch md, final byte ackType) throws JMSException {
        if (this.session.getTransacted()) {
            this.session.doStartTransaction();
            if (!this.synchronizationRegistered) {
                this.synchronizationRegistered = true;
                this.session.getTransactionContext().addSynchronization(new Synchronization() {
                    @Override
                    public void beforeEnd() throws Exception {
                        ActiveMQMessageConsumer.this.acknowledge();
                        ActiveMQMessageConsumer.this.synchronizationRegistered = false;
                    }
                    
                    @Override
                    public void afterCommit() throws Exception {
                        ActiveMQMessageConsumer.this.commit();
                        ActiveMQMessageConsumer.this.synchronizationRegistered = false;
                    }
                    
                    @Override
                    public void afterRollback() throws Exception {
                        ActiveMQMessageConsumer.this.rollback();
                        ActiveMQMessageConsumer.this.synchronizationRegistered = false;
                    }
                });
            }
        }
        ++this.deliveredCounter;
        final MessageAck oldPendingAck = this.pendingAck;
        (this.pendingAck = new MessageAck(md, ackType, this.deliveredCounter)).setTransactionId(this.session.getTransactionContext().getTransactionId());
        if (oldPendingAck == null) {
            this.pendingAck.setFirstMessageId(this.pendingAck.getLastMessageId());
        }
        else if (oldPendingAck.getAckType() == this.pendingAck.getAckType()) {
            this.pendingAck.setFirstMessageId(oldPendingAck.getFirstMessageId());
        }
        else if (!oldPendingAck.isDeliveredAck()) {
            if (ActiveMQMessageConsumer.LOG.isDebugEnabled()) {
                ActiveMQMessageConsumer.LOG.debug("Sending old pending ack " + oldPendingAck + ", new pending: " + this.pendingAck);
            }
            this.session.sendAck(oldPendingAck);
        }
        else if (ActiveMQMessageConsumer.LOG.isDebugEnabled()) {
            ActiveMQMessageConsumer.LOG.debug("dropping old pending ack " + oldPendingAck + ", new pending: " + this.pendingAck);
        }
        if (0.5 * this.info.getPrefetchSize() <= this.deliveredCounter - this.additionalWindowSize) {
            this.session.sendAck(this.pendingAck);
            this.pendingAck = null;
            this.deliveredCounter = 0;
            this.additionalWindowSize = 0;
        }
    }
    
    public void acknowledge() throws JMSException {
        this.clearDispatchList();
        this.waitForRedeliveries();
        synchronized (this.deliveredMessages) {
            final MessageAck ack = this.makeAckForAllDeliveredMessages((byte)2);
            if (ack == null) {
                return;
            }
            if (this.session.getTransacted()) {
                this.rollbackOnFailedRecoveryRedelivery();
                this.session.doStartTransaction();
                ack.setTransactionId(this.session.getTransactionContext().getTransactionId());
            }
            this.session.sendAck(ack);
            this.pendingAck = null;
            this.deliveredCounter = Math.max(0, this.deliveredCounter - this.deliveredMessages.size());
            this.additionalWindowSize = Math.max(0, this.additionalWindowSize - this.deliveredMessages.size());
            if (!this.session.getTransacted()) {
                this.deliveredMessages.clear();
            }
        }
    }
    
    private void waitForRedeliveries() {
        if (this.failoverRedeliveryWaitPeriod > 0L && this.previouslyDeliveredMessages != null) {
            final long expiry = System.currentTimeMillis() + this.failoverRedeliveryWaitPeriod;
            int numberNotReplayed;
            do {
                numberNotReplayed = 0;
                synchronized (this.deliveredMessages) {
                    if (this.previouslyDeliveredMessages != null) {
                        for (final Map.Entry<MessageId, Boolean> entry : this.previouslyDeliveredMessages.entrySet()) {
                            if (!entry.getValue()) {
                                ++numberNotReplayed;
                            }
                        }
                    }
                }
                if (numberNotReplayed > 0) {
                    ActiveMQMessageConsumer.LOG.info("waiting for redelivery of " + numberNotReplayed + " in transaction: " + this.previouslyDeliveredMessages.transactionId + ", to consumer :" + this.getConsumerId());
                    try {
                        Thread.sleep(Math.max(500L, this.failoverRedeliveryWaitPeriod / 4L));
                    }
                    catch (InterruptedException outOfhere) {
                        break;
                    }
                }
            } while (numberNotReplayed > 0 && expiry < System.currentTimeMillis());
        }
    }
    
    private void rollbackOnFailedRecoveryRedelivery() throws JMSException {
        if (this.previouslyDeliveredMessages != null) {
            int numberNotReplayed = 0;
            for (final Map.Entry<MessageId, Boolean> entry : this.previouslyDeliveredMessages.entrySet()) {
                if (!entry.getValue()) {
                    ++numberNotReplayed;
                    if (!ActiveMQMessageConsumer.LOG.isDebugEnabled()) {
                        continue;
                    }
                    ActiveMQMessageConsumer.LOG.debug("previously delivered message has not been replayed in transaction: " + this.previouslyDeliveredMessages.transactionId + " , messageId: " + entry.getKey());
                }
            }
            if (numberNotReplayed > 0) {
                final String message = "rolling back transaction (" + this.previouslyDeliveredMessages.transactionId + ") post failover recovery. " + numberNotReplayed + " previously delivered message(s) not replayed to consumer: " + this.getConsumerId();
                ActiveMQMessageConsumer.LOG.warn(message);
                throw new TransactionRolledBackException(message);
            }
        }
    }
    
    void acknowledge(final MessageDispatch md) throws JMSException {
        final MessageAck ack = new MessageAck(md, (byte)4, 1);
        this.session.sendAck(ack);
        synchronized (this.deliveredMessages) {
            this.deliveredMessages.remove(md);
        }
    }
    
    public void commit() throws JMSException {
        synchronized (this.deliveredMessages) {
            this.deliveredMessages.clear();
            this.clearPreviouslyDelivered();
        }
        this.redeliveryDelay = 0L;
    }
    
    public void rollback() throws JMSException {
        synchronized (this.unconsumedMessages.getMutex()) {
            if (this.optimizeAcknowledge && !this.info.isBrowser()) {
                synchronized (this.deliveredMessages) {
                    for (int i = 0; i < this.deliveredMessages.size() && i < this.ackCounter; ++i) {
                        final MessageDispatch md = this.deliveredMessages.removeLast();
                        this.session.connection.rollbackDuplicate(this, md.getMessage());
                    }
                }
            }
            synchronized (this.deliveredMessages) {
                this.rollbackPreviouslyDeliveredAndNotRedelivered();
                if (this.deliveredMessages.isEmpty()) {
                    return;
                }
                final MessageDispatch lastMd = this.deliveredMessages.getFirst();
                final int currentRedeliveryCount = lastMd.getMessage().getRedeliveryCounter();
                if (currentRedeliveryCount > 0) {
                    this.redeliveryDelay = this.redeliveryPolicy.getNextRedeliveryDelay(this.redeliveryDelay);
                }
                else {
                    this.redeliveryDelay = this.redeliveryPolicy.getInitialRedeliveryDelay();
                }
                final MessageId firstMsgId = this.deliveredMessages.getLast().getMessage().getMessageId();
                for (final MessageDispatch md2 : this.deliveredMessages) {
                    md2.getMessage().onMessageRolledBack();
                    this.session.connection.rollbackDuplicate(this, md2.getMessage());
                }
                if (this.redeliveryPolicy.getMaximumRedeliveries() != -1 && lastMd.getMessage().getRedeliveryCounter() > this.redeliveryPolicy.getMaximumRedeliveries()) {
                    final MessageAck ack = new MessageAck(lastMd, (byte)1, this.deliveredMessages.size());
                    ack.setPoisonCause(lastMd.getRollbackCause());
                    ack.setFirstMessageId(firstMsgId);
                    this.session.sendAck(ack, true);
                    this.additionalWindowSize = Math.max(0, this.additionalWindowSize - this.deliveredMessages.size());
                    this.redeliveryDelay = 0L;
                }
                else {
                    if (currentRedeliveryCount > 0) {
                        final MessageAck ack = new MessageAck(lastMd, (byte)3, this.deliveredMessages.size());
                        ack.setFirstMessageId(firstMsgId);
                        this.session.sendAck(ack, true);
                    }
                    this.unconsumedMessages.stop();
                    for (final MessageDispatch md2 : this.deliveredMessages) {
                        this.unconsumedMessages.enqueueFirst(md2);
                    }
                    if (this.redeliveryDelay > 0L && !this.unconsumedMessages.isClosed()) {
                        this.scheduler.executeAfterDelay(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    if (ActiveMQMessageConsumer.this.started.get()) {
                                        ActiveMQMessageConsumer.this.start();
                                    }
                                }
                                catch (JMSException e) {
                                    ActiveMQMessageConsumer.this.session.connection.onAsyncException(e);
                                }
                            }
                        }, this.redeliveryDelay);
                    }
                    else {
                        this.start();
                    }
                }
                this.deliveredCounter -= this.deliveredMessages.size();
                this.deliveredMessages.clear();
            }
        }
        if (this.messageListener.get() != null) {
            this.session.redispatch(this, this.unconsumedMessages);
        }
    }
    
    private void rollbackPreviouslyDeliveredAndNotRedelivered() {
        if (this.previouslyDeliveredMessages != null) {
            for (final Map.Entry<MessageId, Boolean> entry : this.previouslyDeliveredMessages.entrySet()) {
                if (!entry.getValue()) {
                    this.removeFromDeliveredMessages(entry.getKey());
                }
            }
            this.clearPreviouslyDelivered();
        }
    }
    
    private void removeFromDeliveredMessages(final MessageId key) {
        final Iterator<MessageDispatch> iterator = this.deliveredMessages.iterator();
        while (iterator.hasNext()) {
            final MessageDispatch candidate = iterator.next();
            if (key.equals(candidate.getMessage().getMessageId())) {
                this.session.connection.rollbackDuplicate(this, candidate.getMessage());
                iterator.remove();
                break;
            }
        }
    }
    
    private void clearPreviouslyDelivered() {
        if (this.previouslyDeliveredMessages != null) {
            this.previouslyDeliveredMessages.clear();
            this.previouslyDeliveredMessages = null;
        }
    }
    
    @Override
    public void dispatch(final MessageDispatch md) {
        final MessageListener listener = this.messageListener.get();
        try {
            this.clearMessagesInProgress();
            this.clearDispatchList();
            synchronized (this.unconsumedMessages.getMutex()) {
                if (!this.unconsumedMessages.isClosed()) {
                    if (this.info.isBrowser() || !this.session.connection.isDuplicate(this, md.getMessage())) {
                        if (listener != null && this.unconsumedMessages.isRunning()) {
                            final ActiveMQMessage message = this.createActiveMQMessage(md);
                            this.beforeMessageIsConsumed(md);
                            try {
                                final boolean expired = message.isExpired();
                                if (!expired) {
                                    listener.onMessage(message);
                                }
                                this.afterMessageIsConsumed(md, expired);
                            }
                            catch (RuntimeException e) {
                                ActiveMQMessageConsumer.LOG.error(this.getConsumerId() + " Exception while processing message: " + md.getMessage().getMessageId(), e);
                                if (this.isAutoAcknowledgeBatch() || this.isAutoAcknowledgeEach() || this.session.isIndividualAcknowledge()) {
                                    md.setRollbackCause(e);
                                    this.rollback();
                                }
                                else {
                                    this.afterMessageIsConsumed(md, false);
                                }
                            }
                        }
                        else {
                            if (!this.unconsumedMessages.isRunning()) {
                                this.session.connection.rollbackDuplicate(this, md.getMessage());
                            }
                            this.unconsumedMessages.enqueue(md);
                            if (this.availableListener != null) {
                                this.availableListener.onMessageAvailable(this);
                            }
                        }
                    }
                    else if (!this.session.isTransacted()) {
                        if (ActiveMQMessageConsumer.LOG.isDebugEnabled()) {
                            ActiveMQMessageConsumer.LOG.debug(this.getConsumerId() + " ignoring (auto acking) duplicate: " + md.getMessage());
                        }
                        final MessageAck ack = new MessageAck(md, (byte)2, 1);
                        this.session.sendAck(ack);
                    }
                    else {
                        if (ActiveMQMessageConsumer.LOG.isDebugEnabled()) {
                            ActiveMQMessageConsumer.LOG.debug(this.getConsumerId() + " tracking transacted redelivery of duplicate: " + md.getMessage());
                        }
                        boolean needsPoisonAck = false;
                        synchronized (this.deliveredMessages) {
                            if (this.previouslyDeliveredMessages != null) {
                                this.previouslyDeliveredMessages.put(md.getMessage().getMessageId(), true);
                            }
                            else {
                                needsPoisonAck = true;
                            }
                        }
                        if (needsPoisonAck) {
                            ActiveMQMessageConsumer.LOG.warn("acking duplicate delivery as poison, redelivery must be pending to another consumer on this connection, failoverRedeliveryWaitPeriod=" + this.failoverRedeliveryWaitPeriod + ". Message: " + md);
                            final MessageAck poisonAck = new MessageAck(md, (byte)1, 1);
                            poisonAck.setFirstMessageId(md.getMessage().getMessageId());
                            this.session.sendAck(poisonAck);
                        }
                        else {
                            this.ackLater(md, (byte)0);
                        }
                    }
                }
            }
            if (++this.dispatchedCount % 1000 == 0) {
                this.dispatchedCount = 0;
                Thread.yield();
            }
        }
        catch (Exception e2) {
            this.session.connection.onClientInternalException(e2);
        }
    }
    
    private void clearDispatchList() {
        if (this.clearDispatchList) {
            synchronized (this.deliveredMessages) {
                if (this.clearDispatchList) {
                    if (!this.deliveredMessages.isEmpty()) {
                        if (this.session.isTransacted()) {
                            if (ActiveMQMessageConsumer.LOG.isDebugEnabled()) {
                                ActiveMQMessageConsumer.LOG.debug(this.getConsumerId() + " tracking existing transacted delivered list (" + this.deliveredMessages.size() + ") on transport interrupt");
                            }
                            if (this.previouslyDeliveredMessages == null) {
                                this.previouslyDeliveredMessages = new PreviouslyDeliveredMap<MessageId, Boolean>(this.session.getTransactionContext().getTransactionId());
                            }
                            for (final MessageDispatch delivered : this.deliveredMessages) {
                                this.previouslyDeliveredMessages.put(delivered.getMessage().getMessageId(), false);
                            }
                        }
                        else {
                            if (ActiveMQMessageConsumer.LOG.isDebugEnabled()) {
                                ActiveMQMessageConsumer.LOG.debug(this.getConsumerId() + " clearing delivered list (" + this.deliveredMessages.size() + ") on transport interrupt");
                            }
                            this.deliveredMessages.clear();
                            this.pendingAck = null;
                        }
                    }
                    this.clearDispatchList = false;
                }
            }
        }
    }
    
    public int getMessageSize() {
        return this.unconsumedMessages.size();
    }
    
    public void start() throws JMSException {
        if (this.unconsumedMessages.isClosed()) {
            return;
        }
        this.started.set(true);
        this.unconsumedMessages.start();
        this.session.executor.wakeup();
    }
    
    public void stop() {
        this.started.set(false);
        this.unconsumedMessages.stop();
    }
    
    @Override
    public String toString() {
        return "ActiveMQMessageConsumer { value=" + this.info.getConsumerId() + ", started=" + this.started.get() + " }";
    }
    
    public boolean iterate() {
        final MessageListener listener = this.messageListener.get();
        if (listener != null) {
            final MessageDispatch md = this.unconsumedMessages.dequeueNoWait();
            if (md != null) {
                this.dispatch(md);
                return true;
            }
        }
        return false;
    }
    
    public boolean isInUse(final ActiveMQTempDestination destination) {
        return this.info.getDestination().equals(destination);
    }
    
    public long getLastDeliveredSequenceId() {
        return this.lastDeliveredSequenceId;
    }
    
    public IOException getFailureError() {
        return this.failureError;
    }
    
    public void setFailureError(final IOException failureError) {
        this.failureError = failureError;
    }
    
    static {
        LOG = LoggerFactory.getLogger(ActiveMQMessageConsumer.class);
    }
    
    class PreviouslyDeliveredMap<K, V> extends HashMap<K, V>
    {
        final TransactionId transactionId;
        
        public PreviouslyDeliveredMap(final TransactionId transactionId) {
            this.transactionId = transactionId;
        }
    }
}
