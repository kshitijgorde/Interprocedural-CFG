// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import org.xidget.layout.Margins;
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

public class InsideMarginsBindingRule implements IBindingRule
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
                this.setMargins(Xlate.get(this.node, "0"));
            }
        }
        
        @Override
        public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
            final IModelObject queryFirst = expression.queryFirst(context);
            if (queryFirst != null && queryFirst != this.node) {
                this.node = queryFirst;
                this.setMargins(Xlate.get(queryFirst, "0"));
            }
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
            this.xidget.getFeature(IWidgetFeature.class).setInsideMargins(new Margins((int)n));
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final String margins, final String s) {
            this.setMargins(margins);
        }
        
        @Override
        public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
            if (modelObject == this.node) {
                this.setMargins(Xlate.get(this.node, "0"));
            }
        }
        
        @Override
        public boolean requiresValueNotification() {
            return true;
        }
        
        private void setMargins(final String s) {
            this.xidget.getFeature(IWidgetFeature.class).setInsideMargins(new Margins(s));
        }
    }
}
