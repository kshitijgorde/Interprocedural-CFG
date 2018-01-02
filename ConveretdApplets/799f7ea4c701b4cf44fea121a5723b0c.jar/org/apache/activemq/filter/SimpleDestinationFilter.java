// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.filter;

import org.apache.activemq.command.ActiveMQDestination;

public class SimpleDestinationFilter extends DestinationFilter
{
    private ActiveMQDestination destination;
    
    public SimpleDestinationFilter(final ActiveMQDestination destination) {
        this.destination = destination;
    }
    
    @Override
    public boolean matches(final ActiveMQDestination destination) {
        return this.destination.equals(destination);
    }
    
    public boolean isWildcard() {
        return false;
    }
}
