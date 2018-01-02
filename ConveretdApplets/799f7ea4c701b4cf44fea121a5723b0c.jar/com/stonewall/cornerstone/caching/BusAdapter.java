// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.caching;

import com.stonewall.cornerstone.jms.msg.BlindSelector;
import com.stonewall.cornerstone.jms.msg.RegistrySelector;
import com.stonewall.cornerstone.jms.msg.event.Event;
import javax.jms.JMSException;
import org.xmodel.IModelObjectFactory;
import org.xmodel.ModelObjectFactory;
import org.xmodel.IModel;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.jms.msg.Dispatcher;

public abstract class BusAdapter extends Dispatcher
{
    private static final Log log;
    boolean active;
    private String name;
    private IModel model;
    
    static {
        log = Log.getLog(BusAdapter.class);
    }
    
    public BusAdapter(final String name, final IModel model) throws Exception {
        this.register(this.createSelector(), this);
        this.name = name;
        this.model = model;
        this.setFactory(new ModelObjectFactory());
    }
    
    public void activate() throws JMSException {
        if (!this.isActive()) {
            this.addListener();
            BusAdapter.log.info("BusListener.activate:" + this.name);
            this.active = true;
        }
    }
    
    public void deactivate() throws JMSException {
        if (this.isActive()) {
            this.removeListener();
            BusAdapter.log.info("BusListener.deactivate:" + this.name);
            this.active = false;
        }
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    protected void routeEvent(final Event event) {
        this.model.dispatch(new EventRunnable(event));
    }
    
    protected abstract void addListener() throws JMSException;
    
    protected abstract void removeListener() throws JMSException;
    
    protected RegistrySelector createSelector() {
        return new BlindSelector("processEvent");
    }
    
    protected class EventRunnable implements Runnable
    {
        Event event;
        
        public EventRunnable(final Event event) {
            this.event = event;
        }
        
        @Override
        public void run() {
            final EventRouter router = EventRouter.getInstance();
            router.handleEvent(this.event);
        }
    }
}
