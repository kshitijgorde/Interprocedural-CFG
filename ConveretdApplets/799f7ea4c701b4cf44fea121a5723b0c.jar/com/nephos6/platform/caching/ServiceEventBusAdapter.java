// 
// Decompiled by Procyon v0.5.30
// 

package com.nephos6.platform.caching;

import com.stonewall.cornerstone.jms.msg.event.ServiceEvent;
import com.stonewall.cornerstone.jms.msg.DocumentSelector;
import com.stonewall.cornerstone.jms.msg.RegistrySelector;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import com.stonewall.cornerstone.jms.msg.event.Topic;
import com.stonewall.cornerstone.jms.msg.event.Event;
import org.xmodel.IModel;
import org.xmodel.xpath.XPath;
import org.xmodel.xpath.expression.IExpression;
import com.stonewall.cornerstone.caching.BusAdapter;

public class ServiceEventBusAdapter extends BusAdapter
{
    private static IExpression keyExpr;
    
    static {
        ServiceEventBusAdapter.keyExpr = XPath.createExpression("self::evt:service[evt:status = 'failed' or evt:status = 'exception']");
    }
    
    public ServiceEventBusAdapter(final IModel model) throws Exception {
        super("ServiceEvent", model);
    }
    
    @Override
    protected void addListener() throws JMSException {
        final Topic topic = new Topic(Event.Type.service);
        topic.register(this);
    }
    
    @Override
    protected void removeListener() throws JMSException {
        final Topic topic = new Topic(Event.Type.service);
        topic.unregister(this);
    }
    
    @Override
    protected RegistrySelector createSelector() {
        return new DocumentSelector(ServiceEventBusAdapter.keyExpr, "processEvent");
    }
    
    public void processEvent(final ServiceEvent event) {
        this.routeEvent(event);
    }
}
