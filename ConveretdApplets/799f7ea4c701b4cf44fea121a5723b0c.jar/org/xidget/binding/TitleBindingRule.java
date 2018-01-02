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
import org.xidget.ifeature.ITitleFeature;
import org.xmodel.IModelObject;
import org.xidget.IXidget;

public class TitleBindingRule implements IBindingRule
{
    @Override
    public boolean applies(final IXidget xidget, final IModelObject modelObject) {
        return xidget.getFeature(ITitleFeature.class) != null;
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
            this.xidget.getFeature(ITitleFeature.class).setTitle(Xlate.get(this.node, ""));
        }
        
        @Override
        public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.node = expression.queryFirst(context);
            this.xidget.getFeature(ITitleFeature.class).setTitle(Xlate.get(this.node, ""));
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
            this.xidget.getFeature(ITitleFeature.class).setTitle(Boolean.toString(b));
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
            this.xidget.getFeature(ITitleFeature.class).setTitle(Double.toString(n));
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final String title, final String s) {
            this.xidget.getFeature(ITitleFeature.class).setTitle(title);
        }
        
        @Override
        public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
            if (modelObject == this.node) {
                this.xidget.getFeature(ITitleFeature.class).setTitle(Xlate.get(this.node, ""));
            }
        }
        
        @Override
        public boolean requiresValueNotification() {
            return true;
        }
    }
}
