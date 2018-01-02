// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class ContextAction extends XAction
{
    private IExpression F;
    private ScriptAction E;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.F = xActionDocument.getExpression("source", true);
        this.E = xActionDocument.createScript("source");
    }
    
    @Override
    public Object[] doRun(final IContext context) {
        if (this.F == null) {
            return this.E.run(new StatefulContext(context, context.getObject(), context.getPosition(), context.getSize()));
        }
        final IModelObject queryFirst = this.F.queryFirst(context);
        if (queryFirst != null) {
            return this.E.run(new StatefulContext(context, queryFirst));
        }
        return null;
    }
}
