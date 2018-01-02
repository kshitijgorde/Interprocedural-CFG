// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.xpath.expression.IContext;

public class ElseAction extends XAction
{
    protected IfAction ifScript;
    private IXAction D;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.D = xActionDocument.createScript("true", "false", "test");
    }
    
    public void setIf(final IfAction ifScript) {
        if (!(ifScript instanceof IfAction)) {
            throw new XActionException("An 'else' element does not following an 'if' element.");
        }
        this.ifScript = ifScript;
    }
    
    @Override
    public Object[] doRun(final IContext context) {
        if (!this.ifScript.I) {
            return this.D.run(context);
        }
        return null;
    }
}
