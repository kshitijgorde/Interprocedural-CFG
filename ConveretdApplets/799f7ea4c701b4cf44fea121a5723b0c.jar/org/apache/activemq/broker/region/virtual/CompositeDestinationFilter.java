// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region.virtual;

import java.util.Iterator;
import org.apache.activemq.filter.MessageEvaluationContext;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.broker.region.MessageReference;
import org.apache.activemq.filter.NonCachedMessageEvaluationContext;
import org.apache.activemq.command.Message;
import org.apache.activemq.broker.ProducerBrokerExchange;
import org.apache.activemq.broker.region.Destination;
import java.util.Collection;
import org.apache.activemq.broker.region.DestinationFilter;

public class CompositeDestinationFilter extends DestinationFilter
{
    private Collection forwardDestinations;
    private boolean forwardOnly;
    private boolean copyMessage;
    
    public CompositeDestinationFilter(final Destination next, final Collection forwardDestinations, final boolean forwardOnly, final boolean copyMessage) {
        super(next);
        this.forwardDestinations = forwardDestinations;
        this.forwardOnly = forwardOnly;
        this.copyMessage = copyMessage;
    }
    
    @Override
    public void send(final ProducerBrokerExchange context, final Message message) throws Exception {
        MessageEvaluationContext messageContext = null;
        final Iterator iter = this.forwardDestinations.iterator();
        while (iter.hasNext()) {
            ActiveMQDestination destination = null;
            final Object value = iter.next();
            if (value instanceof FilteredDestination) {
                final FilteredDestination filteredDestination = (FilteredDestination)value;
                if (messageContext == null) {
                    messageContext = new NonCachedMessageEvaluationContext();
                    messageContext.setMessageReference(message);
                }
                messageContext.setDestination(filteredDestination.getDestination());
                if (filteredDestination.matches(messageContext)) {
                    destination = filteredDestination.getDestination();
                }
            }
            else if (value instanceof ActiveMQDestination) {
                destination = (ActiveMQDestination)value;
            }
            if (destination == null) {
                continue;
            }
            Message forwarded_message;
            if (this.copyMessage) {
                forwarded_message = message.copy();
                forwarded_message.setDestination(destination);
            }
            else {
                forwarded_message = message;
            }
            this.send(context, forwarded_message, destination);
        }
        if (!this.forwardOnly) {
            super.send(context, message);
        }
    }
}
