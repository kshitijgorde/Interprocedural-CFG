// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelObject;

public class ProfileAction extends GuardedAction
{
    private String Q;
    private ScriptAction P;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        final IModelObject root = this.getDocument().getRoot();
        this.Q = Conventions.getVarName(root, true, "assign");
        final Object removeAttribute = root.removeAttribute("when");
        this.P = xActionDocument.createScript("source");
        if (removeAttribute != null) {
            root.setAttribute("when", removeAttribute);
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final long nanoTime = System.nanoTime();
        final Object[] run = this.P.run(context);
        final int n = (int)(System.nanoTime() - nanoTime);
        IVariableScope scope = null;
        if (scope == null) {
            scope = context.getScope();
            if (scope == null) {
                throw new IllegalArgumentException("ProfileAction context does not have a variable scope: " + this);
            }
        }
        scope.set(this.Q, n);
        return run;
    }
}
