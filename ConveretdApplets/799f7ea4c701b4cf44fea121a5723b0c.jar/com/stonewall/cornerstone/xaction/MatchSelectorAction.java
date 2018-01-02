// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.xaction;

import org.xmodel.xpath.variable.IVariableScope;
import java.util.Iterator;
import com.stonewall.cornerstone.entity.policy.Selector;
import java.util.List;
import com.stonewall.cornerstone.entity.policy.security.IPHeader;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelObject;
import org.xmodel.Xlate;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import com.stonewall.cornerstone.utility.ILog;
import org.xmodel.xaction.GuardedAction;

public class MatchSelectorAction extends GuardedAction implements ILog
{
    private String assign;
    private IExpression selectorExpr;
    private IExpression listExpr;
    
    @Override
    public void configure(final XActionDocument document) {
        super.configure(document);
        final IModelObject root = document.getRoot();
        this.assign = Xlate.get(root, "assign", (String)null);
        this.selectorExpr = document.getExpression("selector", false);
        this.listExpr = document.getExpression("list", false);
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        IModelObject result = null;
        final List<IModelObject> list = this.listExpr.evaluateNodes(context);
        final IModelObject iselector = this.selectorExpr.queryFirst(context);
        if (iselector == null) {
            throw new IllegalArgumentException("Cannot locate the selector: " + this);
        }
        final Selector selector1 = new IPHeader(iselector);
        for (final IModelObject o : list) {
            final Selector selector2 = new IPHeader(o);
            if (selector1.equals(selector2)) {
                result = o;
                break;
            }
        }
        final IVariableScope scope = context.getScope();
        if (scope == null) {
            throw new IllegalArgumentException("Context does not allow variable assignment: " + this);
        }
        scope.set(this.assign, result);
        return null;
    }
}
