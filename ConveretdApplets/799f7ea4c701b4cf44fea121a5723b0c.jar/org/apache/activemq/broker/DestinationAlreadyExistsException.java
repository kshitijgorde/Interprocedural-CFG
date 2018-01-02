// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker;

import org.apache.activemq.command.ActiveMQDestination;
import javax.jms.JMSException;

public class DestinationAlreadyExistsException extends JMSException
{
    private final ActiveMQDestination destination;
    
    public DestinationAlreadyExistsException(final ActiveMQDestination destination) {
        super("Destination already exists: " + destination);
        this.destination = destination;
    }
    
    public ActiveMQDestination getDestination() {
        return this.destination;
    }
}
