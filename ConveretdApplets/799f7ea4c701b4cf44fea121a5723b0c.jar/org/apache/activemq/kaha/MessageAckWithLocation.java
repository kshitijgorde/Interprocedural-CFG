// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha;

import org.apache.activemq.kaha.impl.async.Location;
import org.apache.activemq.command.MessageAck;

public final class MessageAckWithLocation extends MessageAck
{
    public final Location location;
    
    public MessageAckWithLocation(final MessageAck ack, final Location location) {
        ack.copy(this);
        this.location = location;
    }
}
