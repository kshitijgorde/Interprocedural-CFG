// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.expression.ExpressionListener;
import org.xmodel.xpath.expression.IExpressionListener;
import org.xidget.config.TagProcessor;
import org.xidget.ifeature.IIconFeature;
import org.xmodel.IModelObject;
import org.xidget.IXidget;

public class IconBindingRule implements IBindingRule
{
    @Override
    public boolean applies(final IXidget xidget, final IModelObject modelObject) {
        return xidget.getFeature(IIconFeature.class) != null;
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
            this.node = list.get(0);
            this.xidget.getFeature(IIconFeature.class).setIcon(this.node.getValue());
        }
        
        @Override
        public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.node = expression.queryFirst(context);
            this.xidget.getFeature(IIconFeature.class).setIcon((this.node == null) ? null : this.node.getValue());
        }
        
        @Override
        public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object icon, final Object o) {
            if (modelObject == this.node) {
                this.xidget.getFeature(IIconFeature.class).setIcon(icon);
            }
        }
        
        @Override
        public boolean requiresValueNotification() {
            return true;
        }
    }
}
