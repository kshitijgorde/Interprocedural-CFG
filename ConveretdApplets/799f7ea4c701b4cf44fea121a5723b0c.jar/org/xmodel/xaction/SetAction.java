// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import java.util.Iterator;
import org.xmodel.IChangeSet;
import org.xmodel.ModelAlgorithms;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.IModelObjectFactory;

public class SetAction extends GuardedAction
{
    private IModelObjectFactory M;
    private IExpression N;
    private IExpression O;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.N = xActionDocument.getExpression("source", true);
        this.O = xActionDocument.getExpression("target", true);
        if (this.N == null) {
            this.N = xActionDocument.getExpression();
        }
        if (this.O == null) {
            this.O = xActionDocument.getExpression();
        }
        this.M = this.getFactory(xActionDocument.getRoot());
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        if (this.O.query(context, null).size() == 0) {
            ModelAlgorithms.createPathSubtree(context, this.O, this.M, null);
        }
        Object value = null;
        if (this.N.getType(context) == IExpression.ResultType.NODES) {
            final IModelObject queryFirst = this.N.queryFirst(context);
            if (queryFirst != null) {
                value = queryFirst.getValue();
            }
        }
        else {
            value = this.N.evaluateString(context);
        }
        final Iterator<IModelObject> iterator = this.O.query(context, null).iterator();
        while (iterator.hasNext()) {
            iterator.next().setValue(value);
        }
        return null;
    }
}
