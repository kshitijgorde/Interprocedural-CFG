// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region;

import org.slf4j.LoggerFactory;
import org.apache.activemq.broker.region.policy.PolicyEntry;
import org.apache.activemq.command.MessageDispatchNotification;
import javax.jms.JMSException;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.command.ConsumerInfo;
import org.apache.activemq.store.MessageStore;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.thread.TaskRunnerFactory;
import org.apache.activemq.usage.SystemUsage;
import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;

public class TempQueueRegion extends AbstractTempRegion
{
    private static final Logger LOG;
    private final BrokerService brokerService;
    
    public TempQueueRegion(final RegionBroker broker, final BrokerService brokerService, final DestinationStatistics destinationStatistics, final SystemUsage memoryManager, final TaskRunnerFactory taskRunnerFactory, final DestinationFactory destinationFactory) {
        super(broker, destinationStatistics, memoryManager, taskRunnerFactory, destinationFactory);
        this.brokerService = brokerService;
    }
    
    @Override
    protected Destination doCreateDestination(final ConnectionContext context, final ActiveMQDestination destination) throws Exception {
        final TempQueue result = new TempQueue(this.brokerService, destination, null, this.destinationStatistics, this.taskRunnerFactory);
        this.brokerService.getDestinationPolicy();
        this.configureQueue(result, destination);
        result.initialize();
        return result;
    }
    
    @Override
    protected Subscription createSubscription(final ConnectionContext context, final ConsumerInfo info) throws JMSException {
        if (info.isBrowser()) {
            return new QueueBrowserSubscription(this.broker, this.usageManager, context, info);
        }
        return new QueueSubscription(this.broker, this.usageManager, context, info);
    }
    
    @Override
    public String toString() {
        return "TempQueueRegion: destinations=" + this.destinations.size() + ", subscriptions=" + this.subscriptions.size() + ", memory=" + this.usageManager.getMemoryUsage().getPercentUsage() + "%";
    }
    
    @Override
    public void removeDestination(final ConnectionContext context, final ActiveMQDestination destination, long timeout) throws Exception {
        if (timeout == 0L) {
            timeout = 1L;
        }
        super.removeDestination(context, destination, timeout);
    }
    
    @Override
    public void processDispatchNotification(final MessageDispatchNotification messageDispatchNotification) throws Exception {
        this.processDispatchNotificationViaDestination(messageDispatchNotification);
    }
    
    protected void configureQueue(final Queue queue, final ActiveMQDestination destination) {
        if (this.broker == null) {
            throw new IllegalStateException("broker property is not set");
        }
        if (this.broker.getDestinationPolicy() != null) {
            final PolicyEntry entry = this.broker.getDestinationPolicy().getEntryFor(destination);
            if (entry != null) {
                entry.configure(this.broker, queue);
            }
        }
    }
    
    static {
        LOG = LoggerFactory.getLogger(TempQueueRegion.class);
    }
}
