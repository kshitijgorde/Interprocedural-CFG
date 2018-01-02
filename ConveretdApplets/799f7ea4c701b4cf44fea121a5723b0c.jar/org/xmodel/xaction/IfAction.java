// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class IfAction extends XAction
{
    boolean I;
    protected IExpression condition;
    protected ScriptAction script;
    protected boolean negate;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.condition = xActionDocument.getExpression("true", true);
        if (this.condition == null) {
            this.condition = xActionDocument.getExpression("test", true);
            if (this.condition == null) {
                this.condition = xActionDocument.getExpression("false", true);
                this.negate = true;
            }
        }
        this.script = xActionDocument.createScript("true", "false", "test");
    }
    
    @Override
    public Object[] doRun(final IContext context) {
        this.I = this.condition.evaluateBoolean(context);
        if (this.negate ^ this.I) {
            return this.script.run(context);
        }
        return null;
    }
}
