// 
// Decompiled by Procyon v0.5.30
// 

package com.nephos6.platform.caching;

import com.stonewall.cornerstone.jms.msg.event.ServiceEvent;
import org.xmodel.IModelObject;
import javax.jms.JMSException;
import org.xmodel.external.IExternalReference;
import org.xmodel.external.ICachingPolicy;
import org.xmodel.external.CachingException;
import com.stonewall.cornerstone.jms.msg.event.Event;
import org.xmodel.ModelRegistry;
import org.xmodel.external.ICache;
import org.xmodel.xpath.XPath;
import com.stonewall.cornerstone.caching.BusAdapter;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.caching.EventProcessingCachingPolicy;

public class ServiceEventCachingPolicy extends EventProcessingCachingPolicy
{
    private static Log log;
    static final IExpression findServiceEventListPath;
    static final IExpression servicePath;
    private BusAdapter adapter;
    
    static {
        ServiceEventCachingPolicy.log = Log.getLog(ServiceEventCachingPolicy.class);
        findServiceEventListPath = XPath.createExpression("$services");
        servicePath = XPath.createExpression("evt:service");
    }
    
    public ServiceEventCachingPolicy(final ICache cache) throws CachingException {
        super(cache);
        try {
            this.adapter = new ServiceEventBusAdapter(ModelRegistry.getInstance().getModel());
            this.register(Event.Type.service, this.adapter);
        }
        catch (Exception e) {
            throw new CachingException("Unable to create bus adapter", e);
        }
        this.defineNextStage(ServiceEventCachingPolicy.servicePath, this, false);
    }
    
    public void syncImpl(final IExternalReference reference) throws CachingException {
        try {
            this.adapter.activate();
        }
        catch (JMSException e) {
            throw new CachingException("Unable to register db event source.", e);
        }
    }
    
    @Override
    public void remove(final IExternalReference parent, final IModelObject child) throws CachingException {
        if (parent == null) {
            ServiceEventCachingPolicy.log.debug("Missing parent for delete of child: " + child);
            return;
        }
        if (child == null) {
            return;
        }
        child.removeFromParent();
    }
    
    @Override
    public void handleEvent(final Event event) {
        try {
            final ServiceEvent serviceEvent = (ServiceEvent)event;
            final ServiceEvent.Status status = serviceEvent.getStatus();
            if (status == null || (!status.equals(ServiceEvent.Status.failed) && !status.equals(ServiceEvent.Status.exception))) {
                return;
            }
            final IExternalReference parent = (IExternalReference)ServiceEventCachingPolicy.findServiceEventListPath.queryFirst(this.getContext());
            this.insert(parent, serviceEvent.toString(), -1, false);
        }
        catch (CachingException e) {
            ServiceEventCachingPolicy.log.error(e);
        }
    }
}
