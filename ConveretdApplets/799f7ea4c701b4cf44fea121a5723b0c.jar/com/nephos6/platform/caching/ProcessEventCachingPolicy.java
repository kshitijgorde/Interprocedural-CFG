// 
// Decompiled by Procyon v0.5.30
// 

package com.nephos6.platform.caching;

import java.util.Iterator;
import java.util.TimerTask;
import java.util.Timer;
import com.stonewall.cornerstone.jms.msg.event.ProcessEvent;
import org.xmodel.Xlate;
import org.xmodel.xpath.expression.Context;
import com.stonewall.cornerstone.entity.EntityReference;
import javax.jms.JMSException;
import org.xmodel.external.IExternalReference;
import org.xmodel.diff.DefaultXmlMatcher;
import org.xmodel.external.ICachingPolicy;
import org.xmodel.external.CachingException;
import com.stonewall.cornerstone.jms.msg.event.Event;
import org.xmodel.ModelRegistry;
import java.util.List;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.ExpressionListener;
import org.xmodel.external.ICache;
import org.xmodel.xpath.XPath;
import com.stonewall.cornerstone.caching.BusAdapter;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.log.Log;
import org.xmodel.xpath.expression.IExpressionListener;
import com.stonewall.cornerstone.caching.EventProcessingCachingPolicy;

public class ProcessEventCachingPolicy extends EventProcessingCachingPolicy
{
    final IExpressionListener processListener;
    private static Log log;
    static final IExpression findProcessPath;
    static final IExpression findProcessListPath;
    static final IExpression processStatusExpr;
    static final IExpression errorsExpression;
    static final IExpression processPath;
    static final int processRemoveDelay = 60000;
    private BusAdapter adapter;
    
    static {
        ProcessEventCachingPolicy.log = Log.getLog(ProcessEventCachingPolicy.class);
        findProcessPath = XPath.createExpression("$processes/evt:process[evt:correlation=$correlation]");
        findProcessListPath = XPath.createExpression("$processes");
        processStatusExpr = XPath.createExpression("evt:status");
        errorsExpression = XPath.createExpression("$services/*[ evt:correlation = $correlation]");
        processPath = XPath.createExpression("evt:process");
    }
    
    public ProcessEventCachingPolicy(final ICache cache) throws CachingException {
        super(cache);
        this.processListener = new ExpressionListener() {
            @Override
            public void notifyChange(final IExpression expression, final IContext context, final String newValue, final String oldValue) {
                if (newValue.equals("completed")) {
                    final IModelObject event = context.getObject();
                    final Object correlation = event.getChildValue("evt:correlation");
                    ProcessEventCachingPolicy.errorsExpression.setVariable("correlation", (correlation != null) ? correlation.toString() : null);
                    List<IModelObject> nodes = null;
                    try {
                        nodes = ProcessEventCachingPolicy.errorsExpression.evaluateNodes();
                    }
                    catch (ExpressionException ex) {}
                    if (nodes == null || nodes.isEmpty()) {
                        ProcessEventCachingPolicy.this.setProcessRemoveTimer(event);
                    }
                }
            }
        };
        try {
            this.adapter = new ProcessEventBusAdapter(ModelRegistry.getInstance().getModel());
            this.register(Event.Type.process, this.adapter);
        }
        catch (Exception e) {
            throw new CachingException("Unable to create bus adapter", e);
        }
        this.defineNextStage(ProcessEventCachingPolicy.processPath, this, false);
        ((DefaultXmlMatcher)this.getDiffer().getMatcher()).ignoreAttribute("id");
        ((DefaultXmlMatcher)this.getDiffer().getMatcher()).ignoreAttribute("xm:background");
        ((DefaultXmlMatcher)this.getDiffer().getMatcher()).ignoreAttribute("xm:bkgName");
    }
    
    public void syncImpl(final IExternalReference reference) throws CachingException {
        try {
            this.adapter.activate();
        }
        catch (JMSException e) {
            throw new CachingException("Unable to register db event source.", e);
        }
    }
    
    public IExternalReference findParent(final EntityReference reference) {
        return (IExternalReference)ProcessEventCachingPolicy.findProcessListPath.queryFirst(this.getContext());
    }
    
    public IExternalReference findReference(final String id) {
        ProcessEventCachingPolicy.findProcessPath.setVariable("correlation", id);
        return (IExternalReference)ProcessEventCachingPolicy.findProcessPath.queryFirst(this.getContext());
    }
    
    @Override
    public void insert(final IExternalReference parent, final IModelObject object, final int index, final boolean dirty) throws CachingException {
        super.insert(parent, object, index, dirty);
        final IModelObject process = parent.getChild(parent.getNumberOfChildren() - 1);
        ProcessEventCachingPolicy.processStatusExpr.addListener(new Context(process), this.processListener);
    }
    
    @Override
    public void remove(final IExternalReference parent, final IModelObject object) throws CachingException {
        if (parent == null) {
            ProcessEventCachingPolicy.log.debug("Missing parent for delete of child: " + object);
            return;
        }
        if (object == null) {
            ProcessEventCachingPolicy.log.debug("Missing child for delete on parent: " + parent);
            return;
        }
        final IModelObject child = this.findReference(Xlate.get(object.getFirstChild("correlation"), ""));
        if (child == null) {
            ProcessEventCachingPolicy.log.debug("Child not found during delete: " + object);
            return;
        }
        ProcessEventCachingPolicy.processStatusExpr.removeListener(new Context(child), this.processListener);
        this.removeErrors(child);
        child.removeFromParent();
    }
    
    @Override
    public void handleEvent(final Event event) {
        try {
            final ProcessEvent processEvent = (ProcessEvent)event;
            switch (processEvent.getStatus()) {
                case started: {
                    final IExternalReference parent = (IExternalReference)ProcessEventCachingPolicy.findProcessListPath.queryFirst(this.getContext());
                    final IExternalReference reference = this.findReference(processEvent.getCorrelation());
                    ProcessEventCachingPolicy.log.debug("received process event started: process correlation=" + processEvent.getCorrelation());
                    if (reference == null) {
                        this.insert(parent, processEvent.toString(), -1, false);
                        break;
                    }
                    break;
                }
                case progress: {
                    final IExternalReference reference2 = this.findReference(processEvent.getCorrelation());
                    ProcessEventCachingPolicy.log.debug("received update: process=" + processEvent.getCorrelation() + " status:" + processEvent.getStatus().name());
                    this.update(reference2, processEvent.toString());
                    break;
                }
                case completed:
                case failed:
                case cancelled: {
                    final IExternalReference reference2 = this.findReference(processEvent.getCorrelation());
                    ProcessEventCachingPolicy.log.debug("received update: process=" + processEvent.getCorrelation() + " status:" + processEvent.getStatus().name());
                    this.update(reference2, processEvent.toString());
                    break;
                }
            }
        }
        catch (CachingException e) {
            ProcessEventCachingPolicy.log.error(e);
        }
    }
    
    protected void setProcessRemoveTimer(final IModelObject process) {
        final Timer timer = new Timer("ProcessRemoveTimer", true);
        timer.schedule(new RemoveProcessTimerTask(process), 60000L);
    }
    
    private void removeErrors(final IModelObject process) {
        try {
            final Object correlation = process.getChildValue("evt:correlation");
            ProcessEventCachingPolicy.errorsExpression.setVariable("correlation", (correlation != null) ? correlation.toString() : null);
            final List<IModelObject> nodes = ProcessEventCachingPolicy.errorsExpression.evaluateNodes();
            for (final IModelObject node : nodes) {
                node.removeFromParent();
            }
        }
        catch (ExpressionException e) {
            ProcessEventCachingPolicy.log.debug("Error trying to remove error events for correlation:" + process.getChildValue("evt:correlation"));
        }
    }
    
    private class RemoveProcessTimerTask extends TimerTask implements Runnable
    {
        IModelObject process;
        boolean ui;
        
        public RemoveProcessTimerTask(final IModelObject process) {
            this.process = process;
            this.ui = false;
        }
        
        @Override
        public void run() {
            if (!this.ui) {
                this.ui = true;
                this.process.getModel().getDispatcher().execute(this);
            }
            else {
                ProcessEventCachingPolicy.processStatusExpr.removeListener(new Context(this.process), ProcessEventCachingPolicy.this.processListener);
                ProcessEventCachingPolicy.this.removeErrors(this.process);
                this.process.removeFromParent();
            }
        }
    }
}
