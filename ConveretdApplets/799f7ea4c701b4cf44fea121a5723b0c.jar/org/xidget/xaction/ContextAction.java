// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xaction;

import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xaction.ScriptAction;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.XAction;

public class ContextAction extends XAction
{
    private IExpression sourceExpr;
    private ScriptAction script;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.sourceExpr = xActionDocument.getExpression("source", true);
        this.script = xActionDocument.createScript("source", "name");
    }
    
    @Override
    public Object[] doRun(final IContext context) {
        if (this.sourceExpr == null) {
            return this.script.run(new StatefulContext(context, context.getObject(), context.getPosition(), context.getSize()));
        }
        final IModelObject queryFirst = this.sourceExpr.queryFirst(context);
        if (queryFirst != null) {
            return this.script.run(new StatefulContext(context, queryFirst));
        }
        return null;
    }
}
