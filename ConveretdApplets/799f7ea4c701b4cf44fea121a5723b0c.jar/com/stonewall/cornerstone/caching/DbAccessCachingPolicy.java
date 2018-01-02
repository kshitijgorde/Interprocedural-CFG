// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.caching;

import com.stonewall.cornerstone.entity.EntityReference;
import com.stonewall.cornerstone.jms.msg.event.DbEvent;
import com.stonewall.cornerstone.jms.msg.event.Event;
import javax.jms.JMSException;
import org.xmodel.external.IExternalReference;
import org.xmodel.external.CachingException;
import org.xmodel.Xlate;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.external.ICache;
import org.xmodel.xpath.expression.IExpression;
import com.stonewall.cornerstone.utility.ILog;

public class DbAccessCachingPolicy extends RMICachingPolicy implements ILog
{
    private String type;
    private DbBusAdapter eventSource;
    private IExpression labelExpr;
    private IExpression parentFinder;
    private IExpression entityFinder;
    
    public DbAccessCachingPolicy(final ICache cache) {
        super(cache);
    }
    
    @Override
    public void configure(final IContext context, final IModelObject annotation) throws CachingException {
        super.configure(context, annotation);
        final ClassLoader loader = this.getClass().getClassLoader();
        final XActionDocument document = new XActionDocument(annotation, loader);
        this.labelExpr = document.getExpression("label", false);
        final IModelObject update = annotation.getFirstChild("update");
        if (update != null) {
            this.type = Xlate.get(update.getFirstChild("type"), (String)null);
            this.parentFinder = document.getExpression(update.getFirstChild("parent"));
            this.entityFinder = document.getExpression(update.getFirstChild("entity"));
            try {
                this.eventSource = DbBusAdapter.getInstance();
                this.register(this.type, this.eventSource);
            }
            catch (Exception e) {
                throw new CachingException("Unable to register for updates: " + annotation, e);
            }
        }
    }
    
    @Override
    protected void syncImpl(final IExternalReference reference) throws CachingException {
        try {
            if (this.eventSource != null) {
                this.eventSource.activate();
            }
        }
        catch (JMSException e) {
            throw new CachingException("Unable to register db event source.", e);
        }
        final IModelObject label = (this.labelExpr != null) ? this.labelExpr.queryFirst(reference) : null;
        if (label != null) {
            this.targetParams = new Object[] { label.getID() };
        }
        super.syncImpl(reference);
    }
    
    @Override
    public void handleEvent(final Event event) {
        final DbEvent dbEvent = (DbEvent)event;
        IModelObject child = null;
        boolean dirty;
        if (dbEvent.isComplete()) {
            child = dbEvent.getEntity().getRoot();
            dirty = false;
        }
        else {
            child = dbEvent.getEntityReference().getRoot();
            dirty = true;
        }
        final String label = dbEvent.getLabel();
        switch (dbEvent.getAction()) {
            case create: {
                final EntityReference parentEntityReference = dbEvent.getParent();
                final String parentID = (parentEntityReference != null) ? parentEntityReference.getId() : "";
                final IExternalReference parent = this.findParent(label, parentID);
                if (parent == null) {
                    DbAccessCachingPolicy.log.warn("no parent, id: " + parentID + " event: " + event);
                    return;
                }
                DbAccessCachingPolicy.log.info("create:" + parent.getType() + "(" + parent.getID() + "), " + child.getType() + "(" + child.getID() + ")");
                if (!parent.isDirty()) {
                    this.insert(parent, child, -1, dirty);
                    break;
                }
                break;
            }
            case delete: {
                final IExternalReference reference = this.findEntity(label, child.getID());
                if (reference != null) {
                    DbAccessCachingPolicy.log.info("delete: " + reference.getType() + "(" + reference.getID() + ")");
                    reference.removeFromParent();
                    break;
                }
                break;
            }
            case update: {
                final IExternalReference reference = this.findEntity(label, child.getID());
                DbAccessCachingPolicy.log.info("update: " + reference.getType() + "(" + reference.getID() + ")" + (dirty ? ", stub" : ""));
                if (reference == null) {
                    break;
                }
                if (dirty) {
                    reference.clearCache();
                    break;
                }
                this.update(reference, child);
                break;
            }
        }
    }
    
    protected IExternalReference findParent(final String label, final String id) {
        this.parentFinder.setVariable("label", label);
        this.parentFinder.setVariable("id", id);
        return (IExternalReference)this.parentFinder.queryFirst(this.getContext());
    }
    
    protected IExternalReference findEntity(final String label, final String id) {
        this.entityFinder.setVariable("label", label);
        this.entityFinder.setVariable("id", id);
        return (IExternalReference)this.entityFinder.queryFirst(this.getContext());
    }
}
