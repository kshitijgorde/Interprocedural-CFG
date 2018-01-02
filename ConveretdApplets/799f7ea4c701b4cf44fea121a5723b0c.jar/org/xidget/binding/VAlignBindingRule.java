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
import org.xidget.ifeature.ITextWidgetFeature;
import org.xmodel.IModelObject;
import org.xidget.IXidget;

public class VAlignBindingRule implements IBindingRule
{
    @Override
    public boolean applies(final IXidget xidget, final IModelObject modelObject) {
        return xidget.getFeature(ITextWidgetFeature.class) != null;
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
            this.update(Xlate.get(this.node, ""));
        }
        
        @Override
        public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.node = expression.queryFirst(context);
            if (this.node != null) {
                this.update(Xlate.get(this.node, ""));
            }
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
            this.update(s);
        }
        
        @Override
        public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
            if (modelObject == this.node) {
                this.update(Xlate.get(modelObject, ""));
            }
        }
        
        @Override
        public boolean requiresValueNotification() {
            return true;
        }
        
        private void update(final String s) {
            final ITextWidgetFeature textWidgetFeature = this.xidget.getFeature(ITextWidgetFeature.class);
            if (textWidgetFeature != null) {
                if (s.equals("top")) {
                    textWidgetFeature.setVAlign(ITextWidgetFeature.VAlign.top);
                }
                if (s.equals("center")) {
                    textWidgetFeature.setVAlign(ITextWidgetFeature.VAlign.center);
                }
                if (s.equals("bottom")) {
                    textWidgetFeature.setVAlign(ITextWidgetFeature.VAlign.bottom);
                }
            }
        }
    }
}
