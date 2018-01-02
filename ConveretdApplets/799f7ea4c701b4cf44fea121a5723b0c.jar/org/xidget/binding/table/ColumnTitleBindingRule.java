// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding.table;

import org.xmodel.Xlate;
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

public class ColumnTitleBindingRule implements IBindingRule
{
    @Override
    public boolean applies(final IXidget xidget, final IModelObject modelObject) {
        return (modelObject.getParent().isType("column") || modelObject.getParent().isType("cell")) && xidget.getFeature(ITreeWidgetFeature.class) != null;
    }
    
    @Override
    public IExpressionListener getListener(final TagProcessor tagProcessor, final IXidget xidget, final IModelObject modelObject) {
        return new Listener(xidget, modelObject);
    }
    
    private static final class Listener extends ExpressionListener
    {
        private IXidget xidget;
        private IModelObject element;
        
        Listener(final IXidget xidget, final IModelObject element) {
            this.xidget = xidget;
            this.element = element;
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
            this.xidget.getFeature(ITreeWidgetFeature.class).setTitle(this.getColumnIndex(), Boolean.toString(b));
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
            this.xidget.getFeature(ITreeWidgetFeature.class).setTitle(this.getColumnIndex(), Double.toString(n));
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
            this.xidget.getFeature(ITreeWidgetFeature.class).setTitle(this.getColumnIndex(), s);
        }
        
        @Override
        public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.xidget.getFeature(ITreeWidgetFeature.class).setTitle(this.getColumnIndex(), Xlate.get(expression.queryFirst(context), ""));
        }
        
        @Override
        public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.xidget.getFeature(ITreeWidgetFeature.class).setTitle(this.getColumnIndex(), Xlate.get(expression.queryFirst(context), ""));
        }
        
        @Override
        public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
            this.xidget.getFeature(ITreeWidgetFeature.class).setTitle(this.getColumnIndex(), (o != null) ? o.toString() : "");
        }
        
        @Override
        public boolean requiresValueNotification() {
            return true;
        }
        
        private int getColumnIndex() {
            IModelObject element = this.element.getParent();
            if (element.isType("column") || element.isType("cell")) {
                this.element = element;
                element = element.getParent();
            }
            return element.getChildren(this.element.getType()).indexOf(this.element);
        }
    }
}
