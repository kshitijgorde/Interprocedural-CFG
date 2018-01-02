// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region.virtual;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.Message;
import org.apache.activemq.broker.ProducerBrokerExchange;
import org.apache.activemq.broker.region.Destination;
import org.apache.activemq.broker.region.DestinationFilter;

public class VirtualTopicInterceptor extends DestinationFilter
{
    private String prefix;
    private String postfix;
    
    public VirtualTopicInterceptor(final Destination next, final String prefix, final String postfix) {
        super(next);
        this.prefix = prefix;
        this.postfix = postfix;
    }
    
    @Override
    public void send(final ProducerBrokerExchange context, final Message message) throws Exception {
        if (!message.isAdvisory()) {
            final ActiveMQDestination queueConsumers = this.getQueueConsumersWildcard(message.getDestination());
            this.send(context, message, queueConsumers);
        }
        super.send(context, message);
    }
    
    protected ActiveMQDestination getQueueConsumersWildcard(final ActiveMQDestination original) {
        return new ActiveMQQueue(this.prefix + original.getPhysicalName() + this.postfix);
    }
}
