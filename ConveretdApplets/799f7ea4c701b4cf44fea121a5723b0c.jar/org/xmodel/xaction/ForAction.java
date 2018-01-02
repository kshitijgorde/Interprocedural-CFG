// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import java.util.List;
import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IExpression;

public class ForAction extends GuardedAction
{
    private String u;
    private IExpression w;
    private IExpression y;
    private IExpression v;
    private IExpression x;
    private ScriptAction t;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        final IModelObject root = xActionDocument.getRoot();
        this.u = Conventions.getVarName(root, false, "assign");
        this.w = xActionDocument.getExpression("source", true);
        this.y = xActionDocument.getExpression("from", true);
        this.v = xActionDocument.getExpression("to", true);
        this.x = xActionDocument.getExpression("by", true);
        final Object removeAttribute = root.removeAttribute("when");
        this.t = xActionDocument.createScript("source");
        if (removeAttribute != null) {
            root.setAttribute("when", removeAttribute);
        }
    }
    
    @Override
    protected Object[] doAction(IContext context) {
        IVariableScope scope = null;
        if (scope == null) {
            scope = context.getScope();
            if (scope == null) {
                throw new IllegalArgumentException("ForAction context does not have a variable scope: " + this);
            }
        }
        if (this.w != null) {
            final List<IModelObject> evaluateNodes = this.w.evaluateNodes(context);
            for (int i = 0; i < evaluateNodes.size(); ++i) {
                if (this.u != null) {
                    scope.set(this.u, evaluateNodes.get(i));
                }
                else {
                    context = new StatefulContext(context, evaluateNodes.get(i), i + 1, evaluateNodes.size());
                }
                final Object[] run = this.t.run(context);
                if (run != null) {
                    return run;
                }
            }
        }
        if (this.u != null && this.y != null && this.v != null && this.x != null) {
            final double evaluateNumber = this.y.evaluateNumber(context);
            final double evaluateNumber2 = this.v.evaluateNumber(context);
            final double evaluateNumber3 = this.x.evaluateNumber(context);
            if (evaluateNumber3 >= 0.0) {
                for (double n = evaluateNumber; n <= evaluateNumber2; n += evaluateNumber3) {
                    scope.set(this.u, n);
                    final Object[] run2 = this.t.run(context);
                    if (run2 != null) {
                        return run2;
                    }
                }
            }
            else {
                for (double n2 = evaluateNumber; n2 >= evaluateNumber2; n2 += evaluateNumber3) {
                    scope.set(this.u, n2);
                    final Object[] run3 = this.t.run(context);
                    if (run3 != null) {
                        return run3;
                    }
                }
            }
        }
        return null;
    }
}
