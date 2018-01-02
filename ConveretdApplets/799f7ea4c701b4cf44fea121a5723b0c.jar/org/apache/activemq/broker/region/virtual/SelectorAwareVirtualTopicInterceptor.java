// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region.virtual;

import java.io.IOException;
import java.util.List;
import org.apache.activemq.filter.MessageEvaluationContext;
import org.apache.activemq.broker.region.Subscription;
import org.apache.activemq.broker.region.MessageReference;
import org.apache.activemq.filter.NonCachedMessageEvaluationContext;
import java.util.Iterator;
import java.util.Set;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.Message;
import org.apache.activemq.broker.ProducerBrokerExchange;
import org.apache.activemq.broker.region.Destination;

public class SelectorAwareVirtualTopicInterceptor extends VirtualTopicInterceptor
{
    public SelectorAwareVirtualTopicInterceptor(final Destination next, final String prefix, final String postfix) {
        super(next, prefix, postfix);
    }
    
    @Override
    protected void send(final ProducerBrokerExchange context, final Message message, final ActiveMQDestination destination) throws Exception {
        final Broker broker = context.getConnectionContext().getBroker();
        final Set<Destination> destinations = broker.getDestinations(destination);
        for (final Destination dest : destinations) {
            if (this.matchesSomeConsumer(message, dest)) {
                dest.send(context, message.copy());
            }
        }
    }
    
    private boolean matchesSomeConsumer(final Message message, final Destination dest) throws IOException {
        boolean matches = false;
        final MessageEvaluationContext msgContext = new NonCachedMessageEvaluationContext();
        msgContext.setDestination(dest.getActiveMQDestination());
        msgContext.setMessageReference(message);
        final List<Subscription> subs = dest.getConsumers();
        for (final Subscription sub : subs) {
            if (sub.matches(message, msgContext)) {
                matches = true;
                break;
            }
        }
        return matches;
    }
}
