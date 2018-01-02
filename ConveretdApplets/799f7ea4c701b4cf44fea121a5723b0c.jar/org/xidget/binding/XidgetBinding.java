// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.expression.IExpressionListener;
import org.xmodel.xpath.expression.IExpression;

public class XidgetBinding implements IXidgetBinding
{
    private IExpression expression;
    private IExpressionListener listener;
    
    public XidgetBinding(final IExpression expression, final IExpressionListener listener) {
        this.expression = expression;
        this.listener = listener;
    }
    
    @Override
    public void bind(final StatefulContext statefulContext) {
        if (this.expression != null) {
            this.expression.addNotifyListener(statefulContext, this.listener);
        }
    }
    
    @Override
    public void unbind(final StatefulContext statefulContext) {
        if (this.expression != null) {
            this.expression.removeListener(statefulContext, this.listener);
        }
    }
}
