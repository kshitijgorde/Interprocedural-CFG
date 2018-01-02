// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding.table;

import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.expression.ExpressionListener;
import org.xmodel.xpath.expression.IExpressionListener;
import org.xidget.config.TagProcessor;
import org.xidget.ifeature.table.ITableWidgetFeature;
import org.xmodel.IModelObject;
import org.xidget.IXidget;
import org.xidget.binding.IBindingRule;

public class ShowGridBindingRule implements IBindingRule
{
    @Override
    public boolean applies(final IXidget xidget, final IModelObject modelObject) {
        return xidget.getFeature(ITableWidgetFeature.class) != null;
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
        public void notifyChange(final IExpression expression, final IContext context, final boolean showGrid) {
            this.xidget.getFeature(ITableWidgetFeature.class).setShowGrid(showGrid);
        }
    }
}
