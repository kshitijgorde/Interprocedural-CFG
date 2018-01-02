// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.caching;

import com.stonewall.cornerstone.jms.msg.event.Event;
import org.xmodel.external.ICache;
import org.xmodel.external.ConfiguredCachingPolicy;

public abstract class EventProcessingCachingPolicy extends ConfiguredCachingPolicy
{
    public EventProcessingCachingPolicy(final ICache cache) {
        super(cache);
        final String[] statics = { "id", "parent", "type", "pending", "timestamp" };
        this.setStaticAttributes(statics);
    }
    
    protected void register(final String type, final BusAdapter adapter) {
        final EventRouter router = EventRouter.getInstance();
        router.register(type, adapter, this);
    }
    
    protected void register(final Event.Type type, final BusAdapter adapter) {
        final EventRouter router = EventRouter.getInstance();
        router.register(type, adapter, this);
    }
    
    public abstract void handleEvent(final Event p0);
}
