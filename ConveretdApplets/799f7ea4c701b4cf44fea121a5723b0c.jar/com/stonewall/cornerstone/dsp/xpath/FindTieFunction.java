// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xpath;

import org.xmodel.xpath.expression.ExpressionException;
import java.util.Iterator;
import java.util.Collections;
import org.xmodel.IChangeSet;
import org.xmodel.diff.IXmlMatcher;
import org.xmodel.diff.DefaultXmlMatcher;
import org.xmodel.diff.XmlDiffer;
import org.xmodel.xpath.expression.Context;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.XPath;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class FindTieFunction extends Function
{
    public static final String name = "dsp:find-tie";
    private IExpression candidateExpr;
    
    public FindTieFunction() {
        this.candidateExpr = XPath.createExpression("en:tie[en:base/*[name()=$type]]");
    }
    
    @Override
    public String getName() {
        return "dsp:find-tie";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NODES;
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        this.assertArgs(2, 2);
        this.assertType(context, 0, IExpression.ResultType.NODES);
        this.assertType(context, 1, IExpression.ResultType.NODES);
        final IModelObject arg0 = this.getArguments().get(0).queryFirst(context);
        final IModelObject lhs = arg0.getFirstChild("en:native");
        final IModelObject ties = this.getArguments().get(1).queryFirst(context);
        this.candidateExpr.setVariable("type", arg0.getFirstChild("en:base").getChild(0).getType());
        final List<IModelObject> candidates = this.candidateExpr.evaluateNodes(new Context(ties));
        final XmlDiffer differ = new XmlDiffer();
        final DefaultXmlMatcher matcher = new DefaultXmlMatcher();
        differ.setMatcher(matcher);
        for (final IModelObject candidate : candidates) {
            final IModelObject rhs = candidate.getFirstChild("en:native");
            if (differ.diff(lhs, rhs, null)) {
                return Collections.singletonList(candidate);
            }
        }
        return Collections.emptyList();
    }
}
