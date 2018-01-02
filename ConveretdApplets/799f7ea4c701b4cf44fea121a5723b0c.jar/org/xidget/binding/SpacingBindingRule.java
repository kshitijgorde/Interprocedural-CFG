// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import org.xmodel.Xlate;
import org.xidget.ifeature.IWidgetContainerFeature;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.expression.ExpressionListener;
import org.xmodel.xpath.expression.IExpressionListener;
import org.xidget.config.TagProcessor;
import org.xidget.ifeature.IWidgetFeature;
import org.xmodel.IModelObject;
import org.xidget.IXidget;

public class SpacingBindingRule implements IBindingRule
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
            this.node = list.get(0);
            this.xidget.getFeature(IWidgetContainerFeature.class).setSpacing(Xlate.get(this.node, 2));
        }
        
        @Override
        public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.node = expression.queryFirst(context);
            this.xidget.getFeature(IWidgetContainerFeature.class).setSpacing(Xlate.get(this.node, 2));
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
            try {
                this.xidget.getFeature(IWidgetContainerFeature.class).setSpacing(Integer.parseInt(s));
            }
            catch (Exception ex) {}
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
            this.xidget.getFeature(IWidgetContainerFeature.class).setSpacing((int)n);
        }
        
        @Override
        public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
            if (modelObject == this.node) {
                this.xidget.getFeature(IWidgetContainerFeature.class).setSpacing(Xlate.get(this.node, 2));
            }
        }
        
        @Override
        public boolean requiresValueNotification() {
            return true;
        }
    }
}
