// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import org.xidget.layout.Bounds;
import org.xmodel.Xlate;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.expression.ExpressionListener;
import org.xmodel.xpath.expression.IExpressionListener;
import org.xidget.config.TagProcessor;
import org.xidget.ifeature.IWidgetFeature;
import org.xmodel.IModelObject;
import org.xidget.IXidget;

public class BoundsBindingRule implements IBindingRule
{
    @Override
    public boolean applies(final IXidget xidget, final IModelObject modelObject) {
        return xidget.getFeature(IWidgetFeature.class) != null;
    }
    
    @Override
    public IExpressionListener getListener(final TagProcessor tagProcessor, final IXidget xidget, final IModelObject modelObject) {
        return new Listener(xidget);
    }
    
    private static final class Listener extends ExpressionListener
    {
        private IXidget xidget;
        private IModelObject node;
        
        Listener(final IXidget xidget) {
            this.xidget = xidget;
        }
        
        @Override
        public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
            final List<IModelObject> query = expression.query(context, null);
            if (this.node != query.get(0)) {
                this.node = query.get(0);
                this.setBounds(Xlate.get(this.node, ""));
                this.xidget.getFeature(IWidgetFeature.class).setBoundsNode(this.node);
            }
        }
        
        @Override
        public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
            final IModelObject queryFirst = expression.queryFirst(context);
            if (queryFirst != null && queryFirst != this.node) {
                this.node = queryFirst;
                this.setBounds(Xlate.get(queryFirst, ""));
                this.xidget.getFeature(IWidgetFeature.class).setBoundsNode(queryFirst);
            }
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final String bounds, final String s) {
            this.setBounds(bounds);
        }
        
        @Override
        public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
            if (modelObject == this.node) {
                this.setBounds(Xlate.get(this.node, ""));
            }
        }
        
        @Override
        public boolean requiresValueNotification() {
            return true;
        }
        
        private void setBounds(final String s) {
            final Bounds bounds = new Bounds();
            final IWidgetFeature widgetFeature = this.xidget.getFeature(IWidgetFeature.class);
            if (bounds.parse(s)) {
                widgetFeature.setDefaultBounds(bounds.x, bounds.y, bounds.width, bounds.height, false);
            }
        }
    }
}
