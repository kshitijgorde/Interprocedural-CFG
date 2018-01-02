// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region.virtual;

import org.apache.activemq.filter.DestinationFilter;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.region.Destination;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.command.ActiveMQDestination;

public class VirtualTopic implements VirtualDestination
{
    private String prefix;
    private String postfix;
    private String name;
    private boolean selectorAware;
    
    public VirtualTopic() {
        this.prefix = "Consumer.*.";
        this.postfix = "";
        this.name = ">";
        this.selectorAware = false;
    }
    
    @Override
    public ActiveMQDestination getVirtualDestination() {
        return new ActiveMQTopic(this.getName());
    }
    
    @Override
    public Destination intercept(final Destination destination) {
        return this.selectorAware ? new SelectorAwareVirtualTopicInterceptor(destination, this.getPrefix(), this.getPostfix()) : new VirtualTopicInterceptor(destination, this.getPrefix(), this.getPostfix());
    }
    
    @Override
    public void create(final Broker broker, final ConnectionContext context, final ActiveMQDestination destination) throws Exception {
        if (destination.isQueue() && destination.isPattern() && broker.getDestinations(destination).isEmpty()) {
            final DestinationFilter filter = DestinationFilter.parseFilter(new ActiveMQQueue(this.prefix + ">"));
            if (filter.matches(destination)) {
                broker.addDestination(context, destination, false);
            }
        }
    }
    
    @Override
    public void remove(final Destination destination) {
    }
    
    public String getPostfix() {
        return this.postfix;
    }
    
    public void setPostfix(final String postfix) {
        this.postfix = postfix;
    }
    
    public String getPrefix() {
        return this.prefix;
    }
    
    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setSelectorAware(final boolean selectorAware) {
        this.selectorAware = selectorAware;
    }
    
    public boolean isSelectorAware() {
        return this.selectorAware;
    }
}
