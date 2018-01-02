// 
// Decompiled by Procyon v0.5.30
// 

package com.nephos6.platform.caching;

import com.stonewall.cornerstone.jms.msg.event.ProcessEvent;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import com.stonewall.cornerstone.jms.msg.event.Topic;
import com.stonewall.cornerstone.jms.msg.event.Event;
import org.xmodel.IModel;
import com.stonewall.cornerstone.caching.BusAdapter;

public class ProcessEventBusAdapter extends BusAdapter
{
    public ProcessEventBusAdapter(final IModel model) throws Exception {
        super("ProcessEvent", model);
    }
    
    @Override
    protected void addListener() throws JMSException {
        final Topic topic = new Topic(Event.Type.process);
        topic.register(this);
    }
    
    @Override
    protected void removeListener() throws JMSException {
        final Topic topic = new Topic(Event.Type.process);
        topic.unregister(this);
    }
    
    public void processEvent(final ProcessEvent event) {
        this.routeEvent(event);
    }
}
