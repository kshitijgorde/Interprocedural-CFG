// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.xpath.variable.IVariableScope;
import java.util.Iterator;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class RunAction extends GuardedAction
{
    private String \u0106;
    private IExpression \u0105;
    private IExpression \u0104;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.\u0106 = Conventions.getVarName(xActionDocument.getRoot(), false, "assign");
        this.\u0105 = xActionDocument.getExpression("context", true);
        this.\u0104 = xActionDocument.getExpression();
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        Object[] array = null;
        IXAction ixAction = null;
        final IModelObject queryFirst = this.\u0104.queryFirst(context);
        if (queryFirst != null) {
            final _A a = (queryFirst != null) ? ((_A)queryFirst.getAttribute("compiled")) : null;
            if (a != null) {
                ixAction = a.A;
            }
            if (ixAction == null) {
                ixAction = this.document.createScript(queryFirst, new String[0]);
                queryFirst.setAttribute("compiled", new _A(ixAction));
            }
        }
        if (ixAction == null) {
            return null;
        }
        if (this.\u0105 != null) {
            final Iterator<IModelObject> iterator = this.\u0105.query(context, null).iterator();
            while (iterator.hasNext()) {
                array = ixAction.run(new StatefulContext(context, iterator.next()));
            }
        }
        else {
            array = ixAction.run(context);
        }
        if (this.\u0106 != null && array != null && array.length > 0) {
            final Object o = array[0];
            final IVariableScope scope = context.getScope();
            if (o instanceof List) {
                scope.set(this.\u0106, (List<IModelObject>)o);
            }
            else if (o instanceof String) {
                scope.set(this.\u0106, o.toString());
            }
            else if (o instanceof Number) {
                scope.set(this.\u0106, (Number)o);
            }
            else if (o instanceof Boolean) {
                scope.set(this.\u0106, (Boolean)o);
            }
        }
        return null;
    }
    
    private static final class _A
    {
        public IXAction A;
        
        public _A(final IXAction a) {
            this.A = a;
        }
    }
}
