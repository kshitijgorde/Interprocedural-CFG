// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import org.xidget.ifeature.model.ISelectionModelFeature;
import java.util.List;
import org.xmodel.xpath.expression.IExpressionListener;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.expression.ExpressionListener;
import org.xmodel.xpath.expression.IExpression;
import org.xidget.util.XidgetUtil;
import org.xidget.ifeature.IBindFeature;
import org.xmodel.Xlate;
import org.xidget.config.TagException;
import org.xidget.IXidget;
import org.xidget.ifeature.model.ISelectionUpdateFeature;
import org.xidget.config.ifeature.IXidgetFeature;
import org.xmodel.IModelObject;
import org.xidget.config.TagProcessor;
import org.xidget.config.ITagHandler;

public class SelectionTagHandler implements ITagHandler
{
    @Override
    public boolean filter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) {
        final IXidgetFeature xidgetFeature = tagHandler.getFeature(IXidgetFeature.class);
        if (xidgetFeature == null) {
            return false;
        }
        final IXidget xidget = xidgetFeature.getXidget();
        return xidget != null && xidget.getFeature(ISelectionUpdateFeature.class) != null;
    }
    
    @Override
    public boolean enter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
        final IXidgetFeature xidgetFeature = tagHandler.getFeature(IXidgetFeature.class);
        if (xidgetFeature == null) {
            throw new TagException("Parent tag handler must have an IXidgetFeature.");
        }
        final IXidget xidget = xidgetFeature.getXidget();
        final String value = Xlate.get(modelObject, "var", Xlate.get(modelObject, "variable", (String)null));
        if (value != null) {
            XidgetUtil.findTreeRoot(xidget).getFeature(IBindFeature.class).addBindingAfterChildren(new VariableBinding(xidget, value));
        }
        final IExpression value2 = Xlate.get(modelObject, "parent", (IExpression)null);
        if (value2 != null) {
            XidgetUtil.findTreeRoot(xidget).getFeature(IBindFeature.class).addBindingAfterChildren(new ParentBinding(xidget, value2));
        }
        return false;
    }
    
    @Override
    public void exit(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        return null;
    }
    
    private static final class ParentBinding extends ExpressionListener implements IXidgetBinding
    {
        private IXidget xidget;
        private IExpression parentExpr;
        
        public ParentBinding(final IXidget xidget, final IExpression parentExpr) {
            this.xidget = xidget;
            this.parentExpr = parentExpr;
        }
        
        @Override
        public void bind(final StatefulContext statefulContext) {
            this.parentExpr.addNotifyListener(statefulContext, this);
        }
        
        @Override
        public void unbind(final StatefulContext statefulContext) {
            this.parentExpr.removeNotifyListener(statefulContext, this);
        }
        
        @Override
        public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.xidget.getFeature(ISelectionModelFeature.class).setSourceNode(expression.queryFirst(context));
        }
        
        @Override
        public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.xidget.getFeature(ISelectionModelFeature.class).setSourceNode(expression.queryFirst(context));
        }
    }
    
    private static final class VariableBinding implements IXidgetBinding
    {
        private IXidget xidget;
        private String variable;
        
        public VariableBinding(final IXidget xidget, final String variable) {
            this.xidget = xidget;
            this.variable = variable;
        }
        
        @Override
        public void bind(final StatefulContext statefulContext) {
            this.xidget.getFeature(ISelectionModelFeature.class).setSourceVariable(this.variable);
        }
        
        @Override
        public void unbind(final StatefulContext statefulContext) {
            this.xidget.getFeature(ISelectionModelFeature.class).setSourceVariable(null);
        }
    }
}
