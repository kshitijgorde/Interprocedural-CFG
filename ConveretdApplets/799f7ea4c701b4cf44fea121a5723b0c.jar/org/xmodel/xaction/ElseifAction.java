// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.xpath.expression.IContext;

public class ElseifAction extends IfAction
{
    private IfAction J;
    
    public void setIf(final IfAction j) {
        if (!(j instanceof IfAction)) {
            throw new XActionException("An 'else' element does not following an 'if' element.");
        }
        this.J = j;
    }
    
    @Override
    public Object[] doRun(final IContext context) {
        if (!this.J.I) {
            this.I = this.condition.evaluateBoolean(context);
            if (this.negate ^ this.I) {
                return this.script.run(context);
            }
        }
        else {
            this.I = true;
        }
        return null;
    }
}
