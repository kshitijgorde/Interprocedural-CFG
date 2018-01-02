// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region.group;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.activemq.command.ConsumerId;
import java.util.Map;

public class SimpleMessageGroupMap implements MessageGroupMap
{
    private Map<String, ConsumerId> map;
    
    public SimpleMessageGroupMap() {
        this.map = new ConcurrentHashMap<String, ConsumerId>();
    }
    
    @Override
    public void put(final String groupId, final ConsumerId consumerId) {
        this.map.put(groupId, consumerId);
    }
    
    @Override
    public ConsumerId get(final String groupId) {
        return this.map.get(groupId);
    }
    
    @Override
    public ConsumerId removeGroup(final String groupId) {
        return this.map.remove(groupId);
    }
    
    @Override
    public MessageGroupSet removeConsumer(final ConsumerId consumerId) {
        final SimpleMessageGroupSet ownedGroups = new SimpleMessageGroupSet();
        final Iterator<String> iter = this.map.keySet().iterator();
        while (iter.hasNext()) {
            final String group = iter.next();
            final ConsumerId owner = this.map.get(group);
            if (owner.equals(consumerId)) {
                ownedGroups.add(group);
                iter.remove();
            }
        }
        return ownedGroups;
    }
    
    @Override
    public String toString() {
        return "message groups: " + this.map.size();
    }
}
