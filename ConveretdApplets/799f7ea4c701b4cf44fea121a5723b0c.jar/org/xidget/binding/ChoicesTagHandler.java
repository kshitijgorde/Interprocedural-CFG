// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import org.xidget.ifeature.model.IMultiValueUpdateFeature;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.ExpressionListener;
import org.xmodel.Xlate;
import org.xmodel.xpath.XPath;
import org.xidget.IXidget;
import org.xidget.ifeature.IBindFeature;
import org.xmodel.xpath.expression.IExpressionListener;
import org.xidget.ifeature.model.IMultiValueModelFeature;
import org.xidget.config.TagException;
import org.xidget.config.ifeature.IXidgetFeature;
import org.xmodel.IModelObject;
import org.xidget.config.ITagHandler;
import org.xidget.config.TagProcessor;
import org.xmodel.xpath.expression.IExpression;
import org.xidget.config.AbstractTagHandler;

public class ChoicesTagHandler extends AbstractTagHandler
{
    private IExpression staticChoiceExpr;
    
    @Override
    public boolean enter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
        final IXidgetFeature xidgetFeature = tagHandler.getFeature(IXidgetFeature.class);
        if (xidgetFeature == null) {
            throw new TagException("Parent tag handler must have an IXidgetFeature.");
        }
        final IXidget xidget = xidgetFeature.getXidget();
        final IExpression choiceExpression = this.getChoiceExpression(modelObject);
        if (choiceExpression == null) {
            throw new TagException("Choice list expression not defined.");
        }
        xidget.getFeature(IMultiValueModelFeature.class).setSourceExpression(choiceExpression);
        final XidgetBinding xidgetBinding = new XidgetBinding(choiceExpression, new Listener(xidget));
        final IBindFeature bindFeature = xidget.getFeature(IBindFeature.class);
        if (bindFeature != null) {
            bindFeature.addBindingBeforeChildren(xidgetBinding);
        }
        return false;
    }
    
    private IExpression getChoiceExpression(final IModelObject modelObject) {
        if (modelObject.getNumberOfChildren("choice") > 0) {
            (this.staticChoiceExpr = XPath.createExpression("static( $choices)")).setVariable("choices", modelObject.getChildren("choice"));
            return this.staticChoiceExpr;
        }
        return Xlate.get(modelObject, (IExpression)null);
    }
    
    private static final class Listener extends ExpressionListener
    {
        private IXidget xidget;
        
        Listener(final IXidget xidget) {
            this.xidget = xidget;
        }
        
        @Override
        public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.xidget.getFeature(IMultiValueUpdateFeature.class).updateWidget();
        }
        
        @Override
        public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.xidget.getFeature(IMultiValueUpdateFeature.class).updateWidget();
        }
        
        @Override
        public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
            final int index = expression.query(array[0], null).indexOf(modelObject);
            if (index >= 0) {
                this.xidget.getFeature(IMultiValueUpdateFeature.class).displayUpdate(index, modelObject);
            }
        }
        
        @Override
        public boolean requiresValueNotification() {
            return true;
        }
    }
}
