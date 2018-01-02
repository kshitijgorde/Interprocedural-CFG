// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import java.util.List;
import java.util.HashMap;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.expression.IContext;
import java.util.Map;
import org.xmodel.xpath.expression.ExpressionListener;
import org.xidget.config.TagException;
import org.xmodel.xpath.expression.IExpressionListener;
import org.xidget.ifeature.IBindFeature;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.Xlate;
import org.xidget.IXidget;
import org.xidget.config.ifeature.IXidgetFeature;
import org.xmodel.IModelObject;
import org.xidget.config.ITagHandler;
import org.xidget.config.TagProcessor;
import org.xidget.config.AbstractTagHandler;

public class ContextTagHandler extends AbstractTagHandler
{
    @Override
    public boolean filter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) {
        final IXidgetFeature xidgetFeature = tagHandler.getFeature(IXidgetFeature.class);
        if (xidgetFeature == null) {
            return false;
        }
        final IXidget xidget = xidgetFeature.getXidget();
        return xidget != null && modelObject.getParent() == xidget.getConfig();
    }
    
    @Override
    public boolean enter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
        final IXidget xidget = tagHandler.getFeature(IXidgetFeature.class).getXidget();
        if (xidget == null) {
            return false;
        }
        final IXidget parent = xidget.getParent();
        if (parent == null) {
            return false;
        }
        final IExpression value = Xlate.get(modelObject, (IExpression)null);
        if (value == null) {
            return false;
        }
        parent.getFeature(IBindFeature.class).addBindingAfterChildren(new XidgetBinding(value, new Listener(xidget)));
        return false;
    }
    
    private static final class Listener extends ExpressionListener
    {
        private IXidget xidget;
        private IModelObject node;
        private Map<IContext, StatefulContext> map;
        
        Listener(final IXidget xidget) {
            this.xidget = xidget;
            this.map = new HashMap<IContext, StatefulContext>();
        }
        
        private void rebind(final IContext context, final IModelObject modelObject) {
            final IBindFeature bindFeature = this.xidget.getFeature(IBindFeature.class);
            final StatefulContext statefulContext = this.map.remove(context);
            if (statefulContext != null) {
                if (statefulContext.getObject() == modelObject) {
                    return;
                }
                bindFeature.unbind(statefulContext);
            }
            if (modelObject != null) {
                final StatefulContext statefulContext2 = new StatefulContext(context, modelObject);
                this.map.put(context, statefulContext2);
                bindFeature.bind(statefulContext2);
            }
        }
        
        @Override
        public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.rebind(context, this.node = list.get(0));
        }
        
        @Override
        public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.rebind(context, this.node = expression.queryFirst(context));
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        }
        
        @Override
        public boolean requiresValueNotification() {
            return false;
        }
    }
}
