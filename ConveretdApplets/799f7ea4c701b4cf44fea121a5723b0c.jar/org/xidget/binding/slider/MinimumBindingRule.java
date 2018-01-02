// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding.slider;

import org.xmodel.Xlate;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.expression.ExpressionListener;
import org.xmodel.xpath.expression.IExpressionListener;
import org.xidget.config.TagProcessor;
import org.xidget.ifeature.slider.ISliderWidgetFeature;
import org.xmodel.IModelObject;
import org.xidget.IXidget;
import org.xidget.binding.IBindingRule;

public class MinimumBindingRule implements IBindingRule
{
    @Override
    public boolean applies(final IXidget xidget, final IModelObject modelObject) {
        return xidget.getFeature(ISliderWidgetFeature.class) != null;
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
            this.xidget.getFeature(ISliderWidgetFeature.class).setMinimum(Xlate.get(this.node, 0.0));
        }
        
        @Override
        public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.node = expression.queryFirst(context);
            this.xidget.getFeature(ISliderWidgetFeature.class).setMinimum(Xlate.get(this.node, 0.0));
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
            try {
                this.xidget.getFeature(ISliderWidgetFeature.class).setMinimum(Double.parseDouble(s));
            }
            catch (Exception ex) {}
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final double minimum, final double n) {
            this.xidget.getFeature(ISliderWidgetFeature.class).setMinimum(minimum);
        }
        
        @Override
        public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
            if (modelObject == this.node) {
                this.xidget.getFeature(ISliderWidgetFeature.class).setMinimum(Xlate.get(modelObject, 0.0));
            }
        }
        
        @Override
        public boolean requiresValueNotification() {
            return true;
        }
    }
}
