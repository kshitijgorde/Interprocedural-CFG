// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding.table;

import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.ifeature.tree.IRowSetFeature;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.expression.ExpressionListener;
import org.xmodel.xpath.expression.IExpressionListener;
import org.xidget.config.TagProcessor;
import org.xidget.ifeature.tree.ITreeWidgetFeature;
import org.xmodel.IModelObject;
import org.xidget.IXidget;
import org.xidget.binding.IBindingRule;

public class RowSetBindingRule implements IBindingRule
{
    @Override
    public boolean applies(final IXidget xidget, final IModelObject modelObject) {
        return modelObject.getParent().isType("table") && xidget.getFeature(ITreeWidgetFeature.class) != null;
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
            this.xidget.getFeature(IRowSetFeature.class).setRows((StatefulContext)context, expression.query(context, null));
        }
        
        @Override
        public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.xidget.getFeature(IRowSetFeature.class).setRows((StatefulContext)context, expression.query(context, null));
        }
        
        @Override
        public boolean requiresValueNotification() {
            return false;
        }
    }
}
