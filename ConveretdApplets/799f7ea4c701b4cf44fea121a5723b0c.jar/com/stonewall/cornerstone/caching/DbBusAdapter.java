// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.caching;

import com.stonewall.cornerstone.jms.msg.event.DbEvent;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import com.stonewall.cornerstone.jms.msg.event.Topic;
import com.stonewall.cornerstone.jms.msg.event.Event;
import org.xmodel.ModelRegistry;
import org.xmodel.IModel;

public class DbBusAdapter extends BusAdapter
{
    private static ThreadLocal<DbBusAdapter> adapters;
    
    static {
        DbBusAdapter.adapters = new ThreadLocal<DbBusAdapter>();
    }
    
    protected DbBusAdapter(final IModel model) throws Exception {
        super("DbEvent", model);
    }
    
    public static DbBusAdapter getInstance() throws Exception {
        DbBusAdapter adapter = DbBusAdapter.adapters.get();
        if (adapter == null) {
            adapter = new DbBusAdapter(ModelRegistry.getInstance().getModel());
            DbBusAdapter.adapters.set(adapter);
        }
        return adapter;
    }
    
    @Override
    protected void addListener() throws JMSException {
        final Topic topic = new Topic(Event.Type.db);
        topic.register(this);
    }
    
    @Override
    protected void removeListener() throws JMSException {
        final Topic topic = new Topic(Event.Type.db);
        topic.unregister(this);
    }
    
    public void processEvent(final DbEvent event) {
        this.routeEvent(event);
    }
}
