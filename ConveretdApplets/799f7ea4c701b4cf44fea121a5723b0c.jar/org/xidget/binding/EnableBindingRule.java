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
import org.xidget.ifeature.IWidgetFeature;
import org.xmodel.IModelObject;
import org.xidget.IXidget;

public class EnableBindingRule implements IBindingRule
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
            this.setEnabled(true);
        }
        
        @Override
        public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.setEnabled(expression.query(context, null));
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final double enabled, final double n) {
            this.setEnabled(enabled);
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final String enabled, final String s) {
            this.setEnabled(enabled);
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final boolean enabled) {
            this.setEnabled(enabled);
        }
        
        @Override
        public boolean requiresValueNotification() {
            return false;
        }
        
        private void setEnabled(final List<IModelObject> list) {
            this.setEnabled(list.size() > 0);
        }
        
        private void setEnabled(final String s) {
            if (s.equals("true")) {
                this.setEnabled(true);
            }
            else if (s.equals("false")) {
                this.setEnabled(false);
            }
        }
        
        private void setEnabled(final double n) {
            this.setEnabled(n != 0.0);
        }
        
        private void setEnabled(final boolean enabled) {
            this.xidget.getFeature(IWidgetFeature.class).setEnabled(enabled);
        }
    }
}
