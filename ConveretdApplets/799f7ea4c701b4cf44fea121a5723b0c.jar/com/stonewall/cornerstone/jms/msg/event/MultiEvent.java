// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg.event;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public class MultiEvent extends ServiceEvent
{
    private Map<String, ServiceEvent> events;
    
    public MultiEvent() {
        this.events = new HashMap<String, ServiceEvent>();
    }
    
    @Override
    public void send() {
        for (final ServiceEvent event : this.events.values()) {
            event.send();
        }
    }
    
    @Override
    public void setStatus(final Status status) {
        for (final ServiceEvent event : this.events.values()) {
            event.setStatus(status);
        }
    }
    
    public void add(final ServiceEvent event) {
        final String key = String.valueOf(event.getAction().name()) + event.getSubtype().name();
        if (!this.events.containsKey(key)) {
            this.events.put(key, event);
        }
        else {
            final ServiceEvent current = this.events.get(key);
            current.addId(event.getId());
        }
    }
}
