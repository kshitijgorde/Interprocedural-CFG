// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region;

import org.slf4j.LoggerFactory;
import org.apache.activemq.command.ConsumerControl;
import org.apache.activemq.command.ProducerInfo;
import org.apache.activemq.command.MessageDispatchNotification;
import org.apache.activemq.broker.DestinationAlreadyExistsException;
import org.apache.activemq.command.Response;
import org.apache.activemq.command.MessagePull;
import org.apache.activemq.command.MessageAck;
import org.apache.activemq.broker.ConsumerBrokerExchange;
import org.apache.activemq.command.Message;
import org.apache.activemq.broker.ProducerBrokerExchange;
import org.apache.activemq.command.RemoveSubscriptionInfo;
import java.util.Collection;
import org.apache.activemq.filter.DestinationFilter;
import org.apache.activemq.command.ConsumerInfo;
import java.util.ArrayList;
import java.util.List;
import javax.jms.JMSException;
import java.util.Iterator;
import java.util.Set;
import org.apache.activemq.security.SecurityContext;
import org.apache.activemq.broker.ConnectionContext;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.activemq.thread.TaskRunnerFactory;
import org.apache.activemq.usage.SystemUsage;
import org.apache.activemq.command.ConsumerId;
import org.apache.activemq.filter.DestinationMap;
import org.apache.activemq.command.ActiveMQDestination;
import java.util.Map;
import org.slf4j.Logger;

public abstract class AbstractRegion implements Region
{
    private static final Logger LOG;
    protected final Map<ActiveMQDestination, Destination> destinations;
    protected final DestinationMap destinationMap;
    protected final Map<ConsumerId, Subscription> subscriptions;
    protected final SystemUsage usageManager;
    protected final DestinationFactory destinationFactory;
    protected final DestinationStatistics destinationStatistics;
    protected final RegionBroker broker;
    protected boolean autoCreateDestinations;
    protected final TaskRunnerFactory taskRunnerFactory;
    protected final Object destinationsMutex;
    protected final Map<ConsumerId, Object> consumerChangeMutexMap;
    protected boolean started;
    
    public AbstractRegion(final RegionBroker broker, final DestinationStatistics destinationStatistics, final SystemUsage memoryManager, final TaskRunnerFactory taskRunnerFactory, final DestinationFactory destinationFactory) {
        this.destinations = new ConcurrentHashMap<ActiveMQDestination, Destination>();
        this.destinationMap = new DestinationMap();
        this.subscriptions = new ConcurrentHashMap<ConsumerId, Subscription>();
        this.autoCreateDestinations = true;
        this.destinationsMutex = new Object();
        this.consumerChangeMutexMap = new HashMap<ConsumerId, Object>();
        if (broker == null) {
            throw new IllegalArgumentException("null broker");
        }
        this.broker = broker;
        this.destinationStatistics = destinationStatistics;
        this.usageManager = memoryManager;
        this.taskRunnerFactory = taskRunnerFactory;
        if (broker == null) {
            throw new IllegalArgumentException("null destinationFactory");
        }
        this.destinationFactory = destinationFactory;
    }
    
    @Override
    public final void start() throws Exception {
        this.started = true;
        final Set<ActiveMQDestination> inactiveDests = this.getInactiveDestinations();
        for (final ActiveMQDestination dest : inactiveDests) {
            final ConnectionContext context = new ConnectionContext();
            context.setBroker(this.broker.getBrokerService().getBroker());
            context.setSecurityContext(SecurityContext.BROKER_SECURITY_CONTEXT);
            context.getBroker().addDestination(context, dest, false);
        }
        synchronized (this.destinationsMutex) {
            for (final Destination dest2 : this.destinations.values()) {
                dest2.start();
            }
        }
    }
    
    @Override
    public void stop() throws Exception {
        this.started = false;
        synchronized (this.destinationsMutex) {
            for (final Destination dest : this.destinations.values()) {
                dest.stop();
            }
        }
        this.destinations.clear();
    }
    
    @Override
    public Destination addDestination(final ConnectionContext context, final ActiveMQDestination destination, final boolean createIfTemporary) throws Exception {
        AbstractRegion.LOG.debug(this.broker.getBrokerName() + " adding destination: " + destination);
        synchronized (this.destinationsMutex) {
            Destination dest = this.destinations.get(destination);
            if (dest == null) {
                if (!destination.isTemporary() || createIfTemporary) {
                    dest = this.createDestination(context, destination);
                    final DestinationInterceptor destinationInterceptor = this.broker.getDestinationInterceptor();
                    if (destinationInterceptor != null) {
                        dest = destinationInterceptor.intercept(dest);
                    }
                    dest.start();
                    this.destinations.put(destination, dest);
                    this.destinationMap.put(destination, dest);
                    this.addSubscriptionsForDestination(context, dest);
                }
                if (dest == null) {
                    throw new JMSException("The destination " + destination + " does not exist.");
                }
            }
            return dest;
        }
    }
    
    public Map<ConsumerId, Subscription> getSubscriptions() {
        return this.subscriptions;
    }
    
    protected List<Subscription> addSubscriptionsForDestination(final ConnectionContext context, final Destination dest) throws Exception {
        final List<Subscription> rc = new ArrayList<Subscription>();
        for (final Subscription sub : this.subscriptions.values()) {
            if (sub.matches(dest.getActiveMQDestination())) {
                dest.addSubscription(context, sub);
                rc.add(sub);
            }
        }
        return rc;
    }
    
    @Override
    public void removeDestination(final ConnectionContext context, final ActiveMQDestination destination, final long timeout) throws Exception {
        if (timeout == 0L) {
            for (final Subscription sub : this.subscriptions.values()) {
                if (sub.matches(destination)) {
                    throw new JMSException("Destination still has an active subscription: " + destination);
                }
            }
        }
        if (timeout > 0L) {}
        AbstractRegion.LOG.debug("Removing destination: " + destination);
        synchronized (this.destinationsMutex) {
            final Destination dest = this.destinations.remove(destination);
            if (dest != null) {
                for (final Subscription sub2 : this.subscriptions.values()) {
                    if (sub2.matches(destination)) {
                        dest.removeSubscription(context, sub2, 0L);
                    }
                }
                this.destinationMap.removeAll(destination);
                this.dispose(context, dest);
                final DestinationInterceptor destinationInterceptor = this.broker.getDestinationInterceptor();
                if (destinationInterceptor != null) {
                    destinationInterceptor.remove(dest);
                }
            }
            else {
                AbstractRegion.LOG.debug("Destination doesn't exist: " + dest);
            }
        }
    }
    
    @Override
    public Set<Destination> getDestinations(final ActiveMQDestination destination) {
        synchronized (this.destinationsMutex) {
            return (Set<Destination>)this.destinationMap.get(destination);
        }
    }
    
    @Override
    public Map<ActiveMQDestination, Destination> getDestinationMap() {
        synchronized (this.destinationsMutex) {
            return new HashMap<ActiveMQDestination, Destination>(this.destinations);
        }
    }
    
    @Override
    public Subscription addConsumer(final ConnectionContext context, final ConsumerInfo info) throws Exception {
        AbstractRegion.LOG.debug(this.broker.getBrokerName() + " adding consumer: " + info.getConsumerId() + " for destination: " + info.getDestination());
        final ActiveMQDestination destination = info.getDestination();
        if (destination != null && !destination.isPattern() && !destination.isComposite()) {
            this.lookup(context, destination, true);
        }
        Object addGuard;
        synchronized (this.consumerChangeMutexMap) {
            addGuard = this.consumerChangeMutexMap.get(info.getConsumerId());
            if (addGuard == null) {
                addGuard = new Object();
                this.consumerChangeMutexMap.put(info.getConsumerId(), addGuard);
            }
        }
        synchronized (addGuard) {
            final Subscription o = this.subscriptions.get(info.getConsumerId());
            if (o != null) {
                AbstractRegion.LOG.warn("A duplicate subscription was detected. Clients may be misbehaving. Later warnings you may see about subscription removal are a consequence of this.");
                return o;
            }
            DestinationFilter.parseFilter(info.getDestination());
            final Subscription sub = this.createSubscription(context, info);
            this.subscriptions.put(info.getConsumerId(), sub);
            final List<Destination> addList = new ArrayList<Destination>();
            synchronized (this.destinationsMutex) {
                for (final Destination dest : this.destinationMap.get(info.getDestination())) {
                    addList.add(dest);
                }
            }
            for (final Destination dest2 : addList) {
                dest2.addSubscription(context, sub);
            }
            if (info.isBrowser()) {
                ((QueueBrowserSubscription)sub).destinationsAdded();
            }
            return sub;
        }
    }
    
    public Set getDurableDestinations() {
        return this.destinationFactory.getDestinations();
    }
    
    protected Set<ActiveMQDestination> getInactiveDestinations() {
        final Set<ActiveMQDestination> inactiveDests = this.destinationFactory.getDestinations();
        synchronized (this.destinationsMutex) {
            inactiveDests.removeAll(this.destinations.keySet());
        }
        return inactiveDests;
    }
    
    @Override
    public void removeConsumer(final ConnectionContext context, final ConsumerInfo info) throws Exception {
        AbstractRegion.LOG.debug(this.broker.getBrokerName() + " removing consumer: " + info.getConsumerId() + " for destination: " + info.getDestination());
        final Subscription sub = this.subscriptions.remove(info.getConsumerId());
        if (sub != null) {
            final List<Destination> removeList = new ArrayList<Destination>();
            synchronized (this.destinationsMutex) {
                for (final Destination dest : this.destinationMap.get(info.getDestination())) {
                    removeList.add(dest);
                }
            }
            for (final Destination dest2 : removeList) {
                dest2.removeSubscription(context, sub, info.getLastDeliveredSequenceId());
            }
            this.destroySubscription(sub);
        }
        synchronized (this.consumerChangeMutexMap) {
            this.consumerChangeMutexMap.remove(info.getConsumerId());
        }
    }
    
    protected void destroySubscription(final Subscription sub) {
        sub.destroy();
    }
    
    @Override
    public void removeSubscription(final ConnectionContext context, final RemoveSubscriptionInfo info) throws Exception {
        throw new JMSException("Invalid operation.");
    }
    
    @Override
    public void send(final ProducerBrokerExchange producerExchange, final Message messageSend) throws Exception {
        final ConnectionContext context = producerExchange.getConnectionContext();
        if (producerExchange.isMutable() || producerExchange.getRegionDestination() == null) {
            final Destination regionDestination = this.lookup(context, messageSend.getDestination(), false);
            producerExchange.setRegionDestination(regionDestination);
        }
        producerExchange.getRegionDestination().send(producerExchange, messageSend);
    }
    
    @Override
    public void acknowledge(final ConsumerBrokerExchange consumerExchange, final MessageAck ack) throws Exception {
        Subscription sub = consumerExchange.getSubscription();
        if (sub == null) {
            sub = this.subscriptions.get(ack.getConsumerId());
            if (sub == null) {
                if (!consumerExchange.getConnectionContext().isInRecoveryMode()) {
                    AbstractRegion.LOG.warn("Ack for non existent subscription, ack:" + ack);
                    throw new IllegalArgumentException("The subscription does not exist: " + ack.getConsumerId());
                }
                AbstractRegion.LOG.debug("Ack for non existent subscription in recovery, ack:" + ack);
                return;
            }
            else {
                consumerExchange.setSubscription(sub);
            }
        }
        sub.acknowledge(consumerExchange.getConnectionContext(), ack);
    }
    
    @Override
    public Response messagePull(final ConnectionContext context, final MessagePull pull) throws Exception {
        final Subscription sub = this.subscriptions.get(pull.getConsumerId());
        if (sub == null) {
            throw new IllegalArgumentException("The subscription does not exist: " + pull.getConsumerId());
        }
        return sub.pullMessage(context, pull);
    }
    
    protected Destination lookup(final ConnectionContext context, final ActiveMQDestination destination, final boolean createTemporary) throws Exception {
        Destination dest = null;
        synchronized (this.destinationsMutex) {
            dest = this.destinations.get(destination);
        }
        if (dest == null) {
            if (this.isAutoCreateDestinations()) {
                try {
                    context.getBroker().addDestination(context, destination, createTemporary);
                    dest = this.addDestination(context, destination, false);
                }
                catch (DestinationAlreadyExistsException ex) {}
                synchronized (this.destinationsMutex) {
                    dest = this.destinations.get(destination);
                }
            }
            if (dest == null) {
                throw new JMSException("The destination " + destination + " does not exist.");
            }
        }
        return dest;
    }
    
    @Override
    public void processDispatchNotification(final MessageDispatchNotification messageDispatchNotification) throws Exception {
        final Subscription sub = this.subscriptions.get(messageDispatchNotification.getConsumerId());
        if (sub != null) {
            sub.processMessageDispatchNotification(messageDispatchNotification);
            return;
        }
        throw new JMSException("Slave broker out of sync with master - Subscription: " + messageDispatchNotification.getConsumerId() + " on " + messageDispatchNotification.getDestination() + " does not exist for dispatch of message: " + messageDispatchNotification.getMessageId());
    }
    
    protected void processDispatchNotificationViaDestination(final MessageDispatchNotification messageDispatchNotification) throws Exception {
        Destination dest = null;
        synchronized (this.destinationsMutex) {
            dest = this.destinations.get(messageDispatchNotification.getDestination());
        }
        if (dest != null) {
            dest.processDispatchNotification(messageDispatchNotification);
            return;
        }
        throw new JMSException("Slave broker out of sync with master - Destination: " + messageDispatchNotification.getDestination() + " does not exist for consumer " + messageDispatchNotification.getConsumerId() + " with message: " + messageDispatchNotification.getMessageId());
    }
    
    @Override
    public void gc() {
        for (final Subscription sub : this.subscriptions.values()) {
            sub.gc();
        }
        synchronized (this.destinationsMutex) {
            for (final Destination dest : this.destinations.values()) {
                dest.gc();
            }
        }
    }
    
    protected abstract Subscription createSubscription(final ConnectionContext p0, final ConsumerInfo p1) throws Exception;
    
    protected Destination createDestination(final ConnectionContext context, final ActiveMQDestination destination) throws Exception {
        return this.destinationFactory.createDestination(context, destination, this.destinationStatistics);
    }
    
    public boolean isAutoCreateDestinations() {
        return this.autoCreateDestinations;
    }
    
    public void setAutoCreateDestinations(final boolean autoCreateDestinations) {
        this.autoCreateDestinations = autoCreateDestinations;
    }
    
    @Override
    public void addProducer(final ConnectionContext context, final ProducerInfo info) throws Exception {
        synchronized (this.destinationsMutex) {
            for (final Destination dest : this.destinationMap.get(info.getDestination())) {
                dest.addProducer(context, info);
            }
        }
    }
    
    @Override
    public void removeProducer(final ConnectionContext context, final ProducerInfo info) throws Exception {
        synchronized (this.destinationsMutex) {
            for (final Destination dest : this.destinationMap.get(info.getDestination())) {
                dest.removeProducer(context, info);
            }
        }
    }
    
    protected void dispose(final ConnectionContext context, final Destination dest) throws Exception {
        dest.dispose(context);
        dest.stop();
        this.destinationFactory.removeDestination(dest);
    }
    
    @Override
    public void processConsumerControl(final ConsumerBrokerExchange consumerExchange, final ConsumerControl control) {
        final Subscription sub = this.subscriptions.get(control.getConsumerId());
        if (sub != null && sub instanceof AbstractSubscription) {
            ((AbstractSubscription)sub).setPrefetchSize(control.getPrefetch());
            if (AbstractRegion.LOG.isDebugEnabled()) {
                AbstractRegion.LOG.debug("setting prefetch: " + control.getPrefetch() + ", on subscription: " + control.getConsumerId());
            }
            try {
                this.lookup(consumerExchange.getConnectionContext(), control.getDestination(), false).wakeup();
            }
            catch (Exception e) {
                AbstractRegion.LOG.warn("failed to deliver consumerControl to destination: " + control.getDestination(), e);
            }
        }
    }
    
    static {
        LOG = LoggerFactory.getLogger(AbstractRegion.class);
    }
}
