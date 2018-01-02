// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xaction.trigger.ITrigger;
import org.xidget.ifeature.IBindFeature;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xaction.trigger.EntityTrigger;
import org.xmodel.xaction.trigger.WhenTrigger;
import org.xmodel.xaction.trigger.SourceTrigger;
import org.xidget.config.TagException;
import org.xidget.config.ifeature.IXidgetFeature;
import org.xmodel.IModelObject;
import org.xidget.config.ITagHandler;
import org.xidget.config.TagProcessor;
import org.xidget.config.AbstractTagHandler;

public class TriggerTagHandler extends AbstractTagHandler
{
    @Override
    public boolean enter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject root) throws TagException {
        final IXidgetFeature xidgetFeature = tagHandler.getFeature(IXidgetFeature.class);
        if (xidgetFeature == null) {
            throw new TagException("Parent tag handler must have an IXidgetFeature.");
        }
        ITrigger trigger = null;
        if (root.getAttribute("source") != null || root.getFirstChild("source") != null) {
            trigger = new SourceTrigger();
        }
        else if (root.getAttribute("when") != null || root.getFirstChild("when") != null) {
            trigger = new WhenTrigger();
        }
        else if (root.getAttribute("entity") != null || root.getFirstChild("entity") != null) {
            trigger = new EntityTrigger();
        }
        final XActionDocument xActionDocument = new XActionDocument(tagProcessor.getClassLoader());
        xActionDocument.addPackage("org.xidget.xaction");
        xActionDocument.addPackage("org.xidget.layout.xaction");
        xActionDocument.setRoot(root);
        trigger.configure(xActionDocument);
        final TriggerBinding triggerBinding = new TriggerBinding(trigger);
        final IBindFeature bindFeature = xidgetFeature.getXidget().getFeature(IBindFeature.class);
        if (bindFeature != null) {
            bindFeature.addBindingAfterChildren(triggerBinding);
        }
        return false;
    }
    
    private static final class TriggerBinding implements IXidgetBinding
    {
        private ITrigger trigger;
        
        TriggerBinding(final ITrigger trigger) {
            this.trigger = trigger;
        }
        
        @Override
        public void bind(final StatefulContext statefulContext) {
            this.trigger.activate(statefulContext);
        }
        
        @Override
        public void unbind(final StatefulContext statefulContext) {
            this.trigger.deactivate(statefulContext);
        }
    }
}
