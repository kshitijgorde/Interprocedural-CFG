// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xpath;

import org.xmodel.xpath.expression.ExpressionException;
import java.util.Iterator;
import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.xpath.expression.Context;
import java.util.Collections;
import java.util.ArrayList;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.XPath;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class DerefTieFunction extends Function
{
    public static final String name = "dsp:deref-tie";
    private IExpression candidateExpr;
    
    public DerefTieFunction() {
        this.candidateExpr = XPath.createExpression("en:tie/en:base[*[name()=$type]/@id=$id]");
    }
    
    @Override
    public String getName() {
        return "dsp:deref-tie";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NODES;
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        this.assertArgs(1, 2);
        this.assertType(context, 0, IExpression.ResultType.NODES);
        final List<IModelObject> result = new ArrayList<IModelObject>();
        final List<IModelObject> refs = this.getArguments().get(0).evaluateNodes(context);
        if (refs.isEmpty()) {
            return (List<IModelObject>)Collections.EMPTY_LIST;
        }
        IModelObject ties = null;
        if (this.getArguments().size() == 2) {
            this.assertType(context, 1, IExpression.ResultType.NODES);
            ties = this.getArguments().get(1).queryFirst(context);
        }
        else {
            final IVariableScope scope = context.getScope();
            final IModelObject pending = ((List)scope.get("pending")).get(0);
            ties = pending.getFirstChild("en:ancillary").getFirstChild("en:ties");
        }
        this.candidateExpr.setVariable("id", refs.get(0).getID());
        this.candidateExpr.setVariable("type", refs.get(0).getType());
        final List<IModelObject> candidates = this.candidateExpr.evaluateNodes(new Context(ties));
        for (final IModelObject candidate : candidates) {
            boolean found = false;
            final List<IModelObject> children = candidate.getChildren();
            if (children.size() != refs.size()) {
                continue;
            }
            for (final IModelObject child : children) {
                for (final IModelObject ref : refs) {
                    if (child.getType().equals(ref.getType()) && child.getID().equals(ref.getID())) {
                        found = true;
                        break;
                    }
                    found = false;
                }
                if (!found) {
                    break;
                }
            }
            if (found) {
                result.add(candidate.getParent());
                break;
            }
        }
        return result;
    }
}
