// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import org.xmodel.Xlate;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xidget.layout.Size;
import org.xidget.layout.Bounds;
import org.xmodel.xpath.expression.ExpressionListener;
import org.xmodel.xpath.expression.IExpressionListener;
import org.xidget.config.TagProcessor;
import org.xidget.ifeature.IWidgetFeature;
import org.xmodel.IModelObject;
import org.xidget.IXidget;

public class SizeBindingRule implements IBindingRule
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
        private Bounds bounds;
        private Size size;
        
        Listener(final IXidget xidget) {
            this.xidget = xidget;
            this.bounds = new Bounds();
            this.size = new Size();
        }
        
        @Override
        public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.node = list.get(0);
            this.setSize(Xlate.get(this.node, ""));
        }
        
        @Override
        public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.node = expression.queryFirst(context);
            this.setSize(Xlate.get(this.node, ""));
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final String size, final String s) {
            this.setSize(size);
        }
        
        @Override
        public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
            if (modelObject == this.node) {
                this.setSize(Xlate.get(this.node, ""));
            }
        }
        
        @Override
        public boolean requiresValueNotification() {
            return true;
        }
        
        private void setSize(final String s) {
            final IWidgetFeature widgetFeature = this.xidget.getFeature(IWidgetFeature.class);
            this.size.parse(s);
            this.bounds.width = this.size.width;
            this.bounds.height = this.size.height;
            widgetFeature.setDefaultBounds(this.bounds.x, this.bounds.y, this.size.width, this.size.height, false);
        }
    }
}
