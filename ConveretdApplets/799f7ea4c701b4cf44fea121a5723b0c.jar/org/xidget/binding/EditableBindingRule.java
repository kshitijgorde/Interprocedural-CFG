// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import org.xidget.ifeature.ITextWidgetFeature;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.expression.ExpressionListener;
import org.xmodel.xpath.expression.IExpressionListener;
import org.xidget.config.TagProcessor;
import org.xidget.ifeature.IWidgetFeature;
import org.xmodel.IModelObject;
import org.xidget.IXidget;

public class EditableBindingRule implements IBindingRule
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
            final ITextWidgetFeature textWidgetFeature = this.xidget.getFeature(ITextWidgetFeature.class);
            if (textWidgetFeature != null) {
                textWidgetFeature.setEditable(expression.evaluateBoolean(context));
            }
        }
        
        @Override
        public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
            final ITextWidgetFeature textWidgetFeature = this.xidget.getFeature(ITextWidgetFeature.class);
            if (textWidgetFeature != null) {
                textWidgetFeature.setEditable(expression.evaluateBoolean(context));
            }
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final boolean editable) {
            final ITextWidgetFeature textWidgetFeature = this.xidget.getFeature(ITextWidgetFeature.class);
            if (textWidgetFeature != null) {
                textWidgetFeature.setEditable(editable);
            }
        }
    }
}
