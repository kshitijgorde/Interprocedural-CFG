// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.diff;

import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.IChangeSet;
import org.xmodel.IModelObject;
import java.util.ArrayList;
import org.xmodel.xpath.expression.IExpression;
import java.util.Stack;
import java.util.List;

public class RuleBasedMatcher implements IXmlMatcher
{
    private IXmlMatcher C;
    private List<IRule> D;
    private Stack<IXmlMatcher> A;
    private IXmlMatcher B;
    
    public RuleBasedMatcher() {
        this.A = new Stack<IXmlMatcher>();
        this.C = new DefaultXmlMatcher();
    }
    
    public void setDefaultRule(final IXmlMatcher c) {
        this.C = c;
    }
    
    public void addRule(final IExpression expression, final IXmlMatcher xmlMatcher) {
        this.addRule(new ExpressionRule(expression, xmlMatcher));
    }
    
    public void addRule(final IRule rule) {
        if (this.D == null) {
            this.D = new ArrayList<IRule>();
        }
        this.D.add(rule);
    }
    
    public void addRule(final int n, final IRule rule) {
        if (this.D == null) {
            this.D = new ArrayList<IRule>();
        }
        this.D.add(n, rule);
    }
    
    public void removeRule(final IRule rule) {
        if (this.D == null) {
            return;
        }
        this.D.remove(rule);
    }
    
    public void setRules(final List<IRule> d) {
        this.D = d;
    }
    
    public List<IRule> getRules() {
        if (this.D == null) {
            this.D = new ArrayList<IRule>();
        }
        return this.D;
    }
    
    @Override
    public boolean shouldDiff(final IModelObject modelObject, final String s, final boolean b) {
        return this.B.shouldDiff(modelObject, s, b);
    }
    
    @Override
    public boolean shouldDiff(final IModelObject modelObject, final boolean b) {
        return this.B.shouldDiff(modelObject, b);
    }
    
    @Override
    public void startDiff(final IModelObject modelObject, final IModelObject modelObject2, final IChangeSet set) {
        this.B = this.A(modelObject, modelObject2);
        if (this.B == null) {
            this.B = this.C;
        }
    }
    
    @Override
    public void endDiff(final IModelObject modelObject, final IModelObject modelObject2, final IChangeSet set) {
        this.B = null;
    }
    
    @Override
    public void enterDiff(final IModelObject modelObject, final IModelObject modelObject2, final IChangeSet set) {
        this.A.push(this.B);
        final IXmlMatcher a = this.A(modelObject, modelObject2);
        if (a != null) {
            this.B = a;
        }
        this.B.enterDiff(modelObject, modelObject2, set);
    }
    
    @Override
    public void exitDiff(final IModelObject modelObject, final IModelObject modelObject2, final IChangeSet set) {
        this.B.exitDiff(modelObject, modelObject2, set);
        this.B = this.A.pop();
    }
    
    @Override
    public int findMatch(final List<IModelObject> list, final IModelObject modelObject) {
        return this.B.findMatch(list, modelObject);
    }
    
    @Override
    public boolean isList(final IModelObject modelObject) {
        return this.B.isList(modelObject);
    }
    
    @Override
    public boolean isMatch(final IModelObject modelObject, final IModelObject modelObject2) {
        return this.B.isMatch(modelObject, modelObject2);
    }
    
    private IXmlMatcher A(final IModelObject modelObject, final IModelObject modelObject2) {
        if (this.D != null) {
            for (int i = this.D.size() - 1; i >= 0; --i) {
                final IRule rule = this.D.get(i);
                if (rule.doesRuleApply(modelObject, modelObject2)) {
                    return rule.getMatcher();
                }
            }
        }
        return null;
    }
    
    public static class ExpressionRule implements IRule
    {
        private IExpression B;
        private IXmlMatcher A;
        
        public ExpressionRule(final IExpression b, final IXmlMatcher a) {
            this.B = b;
            this.A = a;
        }
        
        @Override
        public boolean doesRuleApply(final IModelObject modelObject, final IModelObject modelObject2) {
            final StatefulContext statefulContext = new StatefulContext(modelObject);
            statefulContext.set("lhs", modelObject);
            statefulContext.set("rhs", modelObject2);
            return this.B.evaluateBoolean(statefulContext);
        }
        
        @Override
        public IXmlMatcher getMatcher() {
            return this.A;
        }
    }
    
    public interface IRule
    {
        boolean doesRuleApply(final IModelObject p0, final IModelObject p1);
        
        IXmlMatcher getMatcher();
    }
}
