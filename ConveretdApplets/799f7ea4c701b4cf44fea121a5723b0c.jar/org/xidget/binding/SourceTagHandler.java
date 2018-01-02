// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.variable.IVariableListener;
import org.xidget.ifeature.model.ISingleValueUpdateFeature;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.ExpressionListener;
import org.xidget.config.TagException;
import org.xmodel.xpath.expression.IExpressionListener;
import org.xmodel.xpath.expression.IExpression;
import org.xidget.ifeature.IBindFeature;
import org.xmodel.Xlate;
import org.xmodel.xpath.AttributeNode;
import org.xidget.IXidget;
import org.xidget.ifeature.model.ISingleValueModelFeature;
import org.xidget.config.ifeature.IXidgetFeature;
import org.xmodel.IModelObject;
import org.xidget.config.ITagHandler;
import org.xidget.config.TagProcessor;
import org.xidget.config.AbstractTagHandler;

public class SourceTagHandler extends AbstractTagHandler
{
    @Override
    public boolean filter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) {
        final IXidgetFeature xidgetFeature = tagHandler.getFeature(IXidgetFeature.class);
        if (xidgetFeature == null) {
            return false;
        }
        final IXidget xidget = xidgetFeature.getXidget();
        return xidget != null && xidget.getFeature(ISingleValueModelFeature.class) != null;
    }
    
    @Override
    public boolean enter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
        final IXidget xidget = tagHandler.getFeature(IXidgetFeature.class).getXidget();
        if (!(modelObject instanceof AttributeNode)) {
            final String value = Xlate.get(modelObject, "var", (String)null);
            if (value != null) {
                xidget.getFeature(IBindFeature.class).addBindingAfterChildren(new VariableBinding(xidget, value));
            }
        }
        final IExpression value2 = Xlate.get(modelObject, (IExpression)null);
        if (value2 != null) {
            xidget.getFeature(IBindFeature.class).addBindingAfterChildren(new XidgetBinding(value2, new Listener(xidget)));
        }
        return true;
    }
    
    private static final class Listener extends ExpressionListener
    {
        private IXidget xidget;
        
        Listener(final IXidget xidget) {
            this.xidget = xidget;
        }
        
        @Override
        public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
            final ISingleValueModelFeature singleValueModelFeature = this.xidget.getFeature(ISingleValueModelFeature.class);
            if (singleValueModelFeature != null) {
                singleValueModelFeature.setSourceNode(expression.queryFirst(context));
            }
            this.xidget.getFeature(ISingleValueUpdateFeature.class).updateWidget();
        }
        
        @Override
        public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
            final ISingleValueModelFeature singleValueModelFeature = this.xidget.getFeature(ISingleValueModelFeature.class);
            if (singleValueModelFeature != null) {
                singleValueModelFeature.setSourceNode(expression.queryFirst(context));
            }
            this.xidget.getFeature(ISingleValueUpdateFeature.class).updateWidget();
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
            final ISingleValueUpdateFeature singleValueUpdateFeature = this.xidget.getFeature(ISingleValueUpdateFeature.class);
            if (singleValueUpdateFeature != null) {
                singleValueUpdateFeature.display(b);
            }
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
            final ISingleValueUpdateFeature singleValueUpdateFeature = this.xidget.getFeature(ISingleValueUpdateFeature.class);
            if (singleValueUpdateFeature != null) {
                singleValueUpdateFeature.display(n);
            }
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
            final ISingleValueUpdateFeature singleValueUpdateFeature = this.xidget.getFeature(ISingleValueUpdateFeature.class);
            if (singleValueUpdateFeature != null) {
                singleValueUpdateFeature.display(s);
            }
        }
        
        @Override
        public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
            final ISingleValueUpdateFeature singleValueUpdateFeature = this.xidget.getFeature(ISingleValueUpdateFeature.class);
            if (singleValueUpdateFeature != null) {
                singleValueUpdateFeature.display(o);
            }
        }
        
        @Override
        public boolean requiresValueNotification() {
            return true;
        }
    }
    
    private static final class VariableBinding implements IXidgetBinding, IVariableListener
    {
        private IXidget xidget;
        private String variable;
        
        public VariableBinding(final IXidget xidget, final String variable) {
            this.xidget = xidget;
            this.variable = variable;
        }
        
        @Override
        public void bind(final StatefulContext statefulContext) {
            this.xidget.getFeature(ISingleValueModelFeature.class).setSourceVariable(this.variable);
            statefulContext.getScope().addListener(this.variable, statefulContext, this);
        }
        
        @Override
        public void unbind(final StatefulContext statefulContext) {
            statefulContext.getScope().removeListener(this.variable, statefulContext, this);
            this.xidget.getFeature(ISingleValueModelFeature.class).setSourceVariable(null);
        }
        
        @Override
        public void notifyAdd(final String s, final IVariableScope variableScope, final IContext context, final List<IModelObject> list) {
        }
        
        @Override
        public void notifyRemove(final String s, final IVariableScope variableScope, final IContext context, final List<IModelObject> list) {
        }
        
        @Override
        public void notifyChange(final String s, final IVariableScope variableScope, final IContext context, final String s2, final String s3) {
            final ISingleValueUpdateFeature singleValueUpdateFeature = this.xidget.getFeature(ISingleValueUpdateFeature.class);
            if (singleValueUpdateFeature != null) {
                singleValueUpdateFeature.display(s2);
            }
        }
        
        @Override
        public void notifyChange(final String s, final IVariableScope variableScope, final IContext context, final Number n, final Number n2) {
            final ISingleValueUpdateFeature singleValueUpdateFeature = this.xidget.getFeature(ISingleValueUpdateFeature.class);
            if (singleValueUpdateFeature != null) {
                singleValueUpdateFeature.display(n);
            }
        }
        
        @Override
        public void notifyChange(final String s, final IVariableScope variableScope, final IContext context, final Boolean b) {
            final ISingleValueUpdateFeature singleValueUpdateFeature = this.xidget.getFeature(ISingleValueUpdateFeature.class);
            if (singleValueUpdateFeature != null) {
                singleValueUpdateFeature.display(b);
            }
        }
    }
}
