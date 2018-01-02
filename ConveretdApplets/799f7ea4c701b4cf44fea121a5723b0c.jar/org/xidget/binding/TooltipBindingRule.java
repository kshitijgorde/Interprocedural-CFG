// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

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

public class TooltipBindingRule implements IBindingRule
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
        
        Listener(final IXidget xidget) {
            this.xidget = xidget;
        }
        
        @Override
        public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.xidget.getFeature(IWidgetFeature.class).setTooltip(Xlate.get((IModelObject)list.get(0), ""));
        }
        
        @Override
        public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
            final List<IModelObject> query = expression.query(context, null);
            if (query.size() > 0) {
                this.xidget.getFeature(IWidgetFeature.class).setTooltip(Xlate.get((IModelObject)query.get(0), ""));
            }
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final String tooltip, final String s) {
            this.xidget.getFeature(IWidgetFeature.class).setTooltip(tooltip);
        }
        
        @Override
        public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
            this.xidget.getFeature(IWidgetFeature.class).setTooltip(Xlate.get(modelObject, ""));
        }
        
        @Override
        public boolean requiresValueNotification() {
            return true;
        }
    }
}
