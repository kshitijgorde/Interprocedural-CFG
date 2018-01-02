// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.console.command.store.amq.reader;

import org.apache.activemq.kaha.impl.async.Location;
import javax.jms.Message;

class MessageLocation
{
    private Message message;
    private Location location;
    
    public Location getLocation() {
        return this.location;
    }
    
    public void setLocation(final Location location) {
        this.location = location;
    }
    
    public Message getMessage() {
        return this.message;
    }
    
    public void setMessage(final Message message) {
        this.message = message;
    }
}
