// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.caching;

import com.stonewall.cornerstone.jms.msg.event.DbEvent;
import com.stonewall.cornerstone.jms.msg.event.Event;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.stonewall.cornerstone.utility.ILog;

public class EventRouter implements ILog
{
    private static EventRouter instance;
    private Map<Object, EventProcessingCachingPolicy> routeMap;
    private Map<String, String> processedEvents;
    private List<BusAdapter> adapters;
    
    protected EventRouter() {
        this.routeMap = new HashMap<Object, EventProcessingCachingPolicy>();
        this.adapters = new ArrayList<BusAdapter>();
        this.processedEvents = new LinkedHashMap<String, String>() {
            @Override
            protected boolean removeEldestEntry(final Map.Entry<String, String> eldest) {
                return this.size() > 100;
            }
        };
    }
    
    public void register(final String type, final BusAdapter busAdapter, final EventProcessingCachingPolicy cachingPolicy) {
        this.routeMap.put(type, cachingPolicy);
        this.adapters.add(busAdapter);
    }
    
    public void register(final Event.Type type, final BusAdapter busAdapter, final EventProcessingCachingPolicy cachingPolicy) {
        this.routeMap.put(type, cachingPolicy);
        this.adapters.add(busAdapter);
    }
    
    public void handleEvent(final Event event) {
        final String id = event.getId();
        if (this.processedEvents.get(id) != null) {
            this.processedEvents.remove(id);
            return;
        }
        this.processedEvents.put(id, id);
        EventRouter.log.debug("received event: " + event);
        if (event.getType() == Event.Type.db) {
            final DbEvent dbEvent = (DbEvent)event;
            final EventProcessingCachingPolicy cachingPolicy = this.routeMap.get(dbEvent.getEntityReference().getEntityName());
            if (cachingPolicy != null) {
                cachingPolicy.handleEvent(event);
            }
        }
        else {
            final EventProcessingCachingPolicy cachingPolicy2 = this.routeMap.get(event.getType());
            if (cachingPolicy2 != null) {
                cachingPolicy2.handleEvent(event);
            }
        }
    }
    
    public static EventRouter getInstance() {
        if (EventRouter.instance == null) {
            EventRouter.instance = new EventRouter();
        }
        return EventRouter.instance;
    }
}
